package com.suntak.cloud.ehr.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.suntak.cloud.ehr.client.EnterPriseWechatClient;
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
 * @Description: 通讯录管理服务
 * @date Dec 18, 2018 1:31:00 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class ContactServiceImpl implements ContactService{

	private static final ExecutorService executor     = Executors.newFixedThreadPool(100);
	private static final Logger LOG = LoggerFactory.getLogger(ContactServiceImpl.class);

	@Value("${wechat.qy.contact.Secret}")
	private String secret;
	
	@Autowired
	ContactMapper contactMapper;
	
	@Autowired
	EnterPriseWechatClient constactClient;
	
	@Autowired
	WechatClient wechatClient;
	
	@Override
	public Contact getContact(String userid) throws Exception {
		Response response = wechatClient.getQYToken(secret);
		final String access_token = (String)response.getData();
		return constactClient.getContact(access_token, userid);
	}
	
	@Override
	public void synchContact() throws Exception {
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
					BeanUtils.copyProperties(contactExt, contact);
					
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
					Integer[] department = null;
					if (null == contactExt.getDeptid()) {
						department = new Integer[] {1}; //找不到部门时，部门设置为“崇达技术”
						if (StringUtils.isBlank(contact.getPosition())) {
							contact.setPosition(contact.getDeptname());
						} else {
							contact.setPosition(contact.getDeptname()+contact.getPosition());
						}
					} else {
						department = new Integer[]{contactExt.getDeptid()};
					}
					contact.setDepartment(department);
					if (currentDate.before(contactExt.getLabordate())) {
						ContactResponse contactResponse = constactClient.createContact(access_token, contact);
						if (contactResponse.getErrcode() != 0) {
							LOG.error("synchContact error:{}",contactResponse.getErrmsg());
						}
					} else {
						LOG.info("synchContact:{}",new Gson().toJson(contact));
						ContactResponse contactResponse = constactClient.updateContact(access_token, contact);
						if (60111 == contactResponse.getErrcode()) { //员工信息不存在时，需要创建员工信息
							contactResponse = constactClient.createContact(access_token, contact);
							if (contactResponse.getErrcode() != 0) {
								LOG.error("synchContact error:{}",contactResponse.getErrmsg());
							}
						} else if (contactResponse.getErrcode() != 0) {
							LOG.error("synchContact error:{}",contactResponse.getErrmsg());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					LOG.error(e.getMessage());
				}
			});
		}
	}

	@Override
	public void deleteContact() throws Exception {
		Future<Response> futurn = executor.submit(() -> {
			return wechatClient.getQYToken(secret);
		});
		List<ContactExt> contactExts = contactMapper.findDisableContact();
		Response response = futurn.get();
		final String access_token = (String)response.getData();
		for (ContactExt contactExt: contactExts) {
			executor.submit(() -> {
				try {
					LOG.info("delete contact:{},{}", access_token, contactExt.getUserid());
					ContactResponse contactResponse = constactClient.deleteContact(access_token, contactExt.getUserid());
					LOG.info("删除结果：{},{}",contactExt.getUserid(), new Gson().toJson(contactResponse));
				} catch (Exception e) {
					e.printStackTrace();
					LOG.error(e.getMessage());
				}
			});
		}
	}
}
