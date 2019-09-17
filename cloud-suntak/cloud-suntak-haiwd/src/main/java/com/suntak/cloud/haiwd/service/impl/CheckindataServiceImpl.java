package com.suntak.cloud.haiwd.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.suntak.cloud.haiwd.client.QyapiClient;
import com.suntak.cloud.haiwd.client.WechatClient;
import com.suntak.cloud.haiwd.entity.CheckindataRequest;
import com.suntak.cloud.haiwd.entity.CheckindataResponse;
import com.suntak.cloud.haiwd.entity.Kq_kqdata;
import com.suntak.cloud.haiwd.entity.T_haiwd_checkindata;
import com.suntak.cloud.haiwd.entity.T_haiwd_user;
import com.suntak.cloud.haiwd.entity.Tx_empcard;
import com.suntak.cloud.haiwd.mapper.cux.CheckindataMapper;
import com.suntak.cloud.haiwd.mapper.cux.UserMapper;
import com.suntak.cloud.haiwd.mapper.dl.DLEmpcardMapper;
import com.suntak.cloud.haiwd.mapper.dl.DLKqdataMapper;
import com.suntak.cloud.haiwd.mapper.sz.EmpcardMapper;
import com.suntak.cloud.haiwd.mapper.sz.KqdataMapper;
import com.suntak.cloud.haiwd.service.CheckindataService;
import com.suntak.exception.model.Response;

@Service
public class CheckindataServiceImpl implements CheckindataService {

	private ExecutorService executor = new ThreadPoolExecutor(10, 3000, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
	private Logger logger = LoggerFactory.getLogger(CheckindataServiceImpl.class);
	
	@Autowired
	private CheckindataMapper checkindataMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private QyapiClient qyapiClient;
	
	@Autowired
	private WechatClient wechatClient;
	
	@Autowired
	private KqdataMapper kqdataMapper;
	
	@Autowired
	private DLKqdataMapper dlKqdataMapper;
	
	@Autowired
	private EmpcardMapper empcardMapper;
	
	@Autowired
	private DLEmpcardMapper dlEmpcardMapper;
	
	@Value("${wechat.qy.Secret}")
	private String secret;
	
	@Override
	public void load(CheckindataRequest checkindataRequest) throws Exception {
		Future<List<T_haiwd_user>> userListFuture = executor.submit(() -> {
			return userMapper.findByConditions(T_haiwd_user.class, null, null);
		});
		Response response = wechatClient.getQYToken(secret);
		if (response.getStatus() == 200) {
			String access_token = (String)response.getData();
			final List<T_haiwd_user> users = userListFuture.get();
			Map<String, String> userMap = new HashMap<>();
			for (T_haiwd_user user: users) {
				userMap.put(user.getUserid(), user.getType());
			}
			String[] useridlist = new String[users.size()];
			for (int i=0; i<useridlist.length; i++) {
				useridlist[i] = users.get(i).getUserid();
			}
			checkindataRequest.setUseridlist(useridlist);
			CheckindataResponse checkindataResponse = qyapiClient.query(checkindataRequest, access_token);
			if ("ok".equals(checkindataResponse.getErrmsg())) {
				T_haiwd_checkindata[] checkindatas = checkindataResponse.getCheckindata();
				for (T_haiwd_checkindata checkindata: checkindatas) {
					if ("未打卡".equals(checkindata.getException_type())) {
						continue;
					}
					executor.submit(() -> {
						checkindata.setCreatestamp(new Timestamp(System.currentTimeMillis()));
						checkindata.setStatus("1");
						try {
							int count = checkindataMapper.insert(checkindata);
							if (count == 1) {
								Map<String, Object> params = new HashMap<String, Object>();
								String userid = checkindata.getUserid();
								params.put("empsysid", userid);
								String type = userMap.get(userid);
								List<Tx_empcard> empcardList = null;
								if ("0".equals(type)) {
									empcardList = dlEmpcardMapper.findByConditions(Tx_empcard.class, params, "cardstatuschgday desc");
								} else {
									empcardList = empcardMapper.findByConditions(Tx_empcard.class, params, "cardstatuschgday desc");
								}
								Kq_kqdata kqdata = new Kq_kqdata();
								kqdata.setGuid(UUID.randomUUID().toString());
								kqdata.setEmpsysid(checkindata.getUserid());
								Long checkin_time = checkindata.getCheckin_time();
								Timestamp time = new Timestamp(checkin_time * 1000);
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(time);
								kqdata.setKqdate(new SimpleDateFormat("yyyy-MM-dd").format(time));
								kqdata.setKqtime(calendar.get(Calendar.SECOND)*60+calendar.get(Calendar.MINUTE)*60+calendar.get(Calendar.HOUR_OF_DAY)*60*60);
								kqdata.setIskeyin("N");
								kqdata.setIskoukuan("N");
								kqdata.setIsmodified("N");
								kqdata.setDevid(0);
								kqdata.setKeyinday(time);
								if (empcardList != null && empcardList.size() > 0) {
									kqdata.setCardid(empcardList.get(0).getCardid());
								} else {
									kqdata.setCardid(0);
								}
								kqdata.setSynchflagid(0);
								kqdata.setKqdatetime(time);
								if ("0".equals(type)) {
									dlKqdataMapper.insert(kqdata);
								} else {
									kqdataMapper.insert(kqdata);
								}
							}
						} catch (DuplicateKeyException e) {
							
						} catch (Exception e) {
							logger.error("企业微信打卡记录保存失败：{}", e);
						}
					});
				}
			}
		}
	}

}
