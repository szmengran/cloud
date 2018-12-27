package com.suntak.cloud.ehr.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.suntak.cloud.ehr.client.ConstactClient;
import com.suntak.cloud.ehr.client.WechatClient;
import com.suntak.cloud.ehr.entity.Attr;
import com.suntak.cloud.ehr.entity.Contact;
import com.suntak.cloud.ehr.entity.ContactExt;
import com.suntak.cloud.ehr.entity.ContactResponse;
import com.suntak.cloud.ehr.entity.Extattr;
import com.suntak.cloud.ehr.entity.Text;
import com.suntak.cloud.ehr.mapper.ContactMapper;
import com.suntak.cloud.ehr.service.ContactService;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.ehr.service.impl
 * @Description: TODO
 * @date Dec 18, 2018 1:31:00 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class ContactServiceImpl implements ContactService{

	private static final Logger LOG = LoggerFactory.getLogger(ContactServiceImpl.class);

	@Value("${wechat.qy.Secret}")
	private String secret;
	
	@Autowired
	ContactMapper contactMapper;
	
	@Autowired
	ConstactClient constactClient;
	
	@Autowired
	WechatClient wechatClient;
	
	@Override
	public void synchContact() throws Exception {
		ExecutorService executor     = Executors.newFixedThreadPool(20);
		Future<Response> futurn = executor.submit(() -> {
			return wechatClient.getQYToken(secret);
		});

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date date                    = new SimpleDateFormat("yyyy-MM-dd").parse(calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DATE));
		Timestamp currentDate        = new Timestamp(date.getTime());
		List<ContactExt> contactExts = contactMapper.findContact(currentDate);
		Response response = futurn.get();
		final String access_token = (String)response.getData();
		for (ContactExt contactExt: contactExts) {
			executor.submit(() -> {
				try {
					Contact contact = new Contact();
					BeanUtils.copyProperties(contact, contactExt);
					
					String short_tel = contactExt.getShort_tel();
					if (StringUtils.isNotBlank(short_tel)) {
						Extattr extattr = new Extattr();
						Attr attr = new Attr();
						attr.setName("短号");
						attr.setType(0);
						Text text = new Text();
						text.setValue(short_tel);
						attr.setText(text);
						Attr[] attrs = {attr};
						extattr.setAttrs(attrs);
						contact.setExtattr(extattr);
					}
					Integer[] department = {contactExt.getDeptid()};
					contact.setDepartment(department);
					if (currentDate.before(contactExt.getLabordate())) {
						ContactResponse contactResponse = constactClient.createContact(access_token, contact);
						if (contactResponse.getErrcode() != 0) {
							LOG.error(contactResponse.getErrmsg());
						}
					} else {
						LOG.info(new Gson().toJson(contact));
						LOG.info(new Gson().toJson(access_token));
						ContactResponse contactResponse = constactClient.updateContact(access_token, contact);
						if (contactResponse.getErrcode() != 0) {
							LOG.error(contactResponse.getErrmsg());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					LOG.error(e.getMessage());
				}
			});
		}
	}
}
