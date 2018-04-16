package com.szmengran.cloud.user.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.szmengran.admin.entity.T_power_role;
import com.szmengran.admin.entity.T_power_user;
import com.szmengran.admin.entity.T_power_user_role_r;
import com.szmengran.admin.entity.ext.T_power_user_ext;
import com.szmengran.admin.user.exception.BusinessException;
import com.szmengran.cloud.user.service.UserService;
import com.szmengran.common.Constants;
import com.szmengran.common.orm.DBManager;
import com.szmengran.common.orm.service.AbstractService;

@Service
public class UserServiceImpl extends AbstractService implements UserService{
	
	@Override
	public T_power_user_ext login(String username, String password) throws BusinessException,Exception {
		StringBuffer conditions = new StringBuffer();
		conditions.append(" and username = ?");
		List<Object> list = super.findByConditions(new T_power_user(), conditions, new Object[]{username});
		if(list == null || list.size() == 0){
			throw new BusinessException(1010);
		}else{
			T_power_user t_power_user = (T_power_user)list.get(0);
			if(!"1".equals(t_power_user.getValidstatus())) {
				throw new BusinessException(1013);
			}
			T_power_user_ext t_power_user_ext = new T_power_user_ext();
			BeanUtils.copyProperties(t_power_user, t_power_user_ext);
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			if(bCryptPasswordEncoder.matches(password, t_power_user.getPassword())){
				t_power_user_ext.setPassword(null);
				StringBuffer strCondition = new StringBuffer();
				strCondition.append(" and roleid in (select roleid from t_power_user_role_r where userid = ?)");
				List<Object> roleList = super.findByConditions(new T_power_role(), strCondition, new Object[]{t_power_user.getUserid()});
				if(roleList != null && roleList.size() > 0){
					String permissions[] =new String[roleList.size()];
					for(int i=0;i<roleList.size();i++){
						permissions[i] =((T_power_role)roleList.get(i)).getRolename();
					}
					t_power_user_ext.setPermissions(permissions);
				}
				return t_power_user_ext;
			}else{
				throw new BusinessException(3002);
			}
		}
	}
	
	@Override
	public void save(T_power_user t_power_user, String[] roleids) throws Exception {
		DBManager dbManager = super.getDBManager(Constants.DATASOURCE_WRITE);
		try{
			dbManager.openConnection();
			dbManager.beginTransaction();
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			t_power_user.setUserid(super.generatePrimaryKeyByTime());
			t_power_user.setCreatestamp(new Timestamp(System.currentTimeMillis()));
			t_power_user.setUpdatestamp(new Timestamp(System.currentTimeMillis()));
			t_power_user.setPassword(bCryptPasswordEncoder.encode(t_power_user.getPassword()));
			t_power_user.setValidstatus("1");
			super.save(dbManager,t_power_user);
			List<T_power_user_role_r> list = new ArrayList<T_power_user_role_r>();
			if(roleids != null){
				for(String roleid:roleids){
					T_power_user_role_r t_power_user_role_r = new T_power_user_role_r();
					t_power_user_role_r.setRoleid(Integer.parseInt(roleid));
					t_power_user_role_r.setUserid(t_power_user.getUserid());
					list.add(t_power_user_role_r);
				}
				super.addBatch(dbManager, list);
				dbManager.commitBatch();
			}
			dbManager.commitTransaction();
		}catch(Exception e){
			dbManager.rollbackTransaction();
			throw e;
		}finally{
			dbManager.close();
		}
	}
	
	@Override
	public void updatePwd(String userid, String password) throws Exception {
		String strSql = "update t_power_user set password=?,updatestamp=? where userid=?";
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String cryPassword = bCryptPasswordEncoder.encode(password);
		Object params[] = new Object[3];
		params[0] = cryPassword;
		params[1] = new Timestamp(System.currentTimeMillis());
		params[2] = userid;
		super.executeSql(strSql, params);
	}
	
	@Override
	public void updatePwd(String userid, String password, String oldPassword) throws Exception {
		DBManager dbManager = null;
		try{
			dbManager = super.getDBManager(Constants.DATASOURCE_WRITE);
			dbManager.openConnection();
			dbManager.beginTransaction();
			T_power_user t_power_user = new T_power_user();
			t_power_user.setUserid(userid);
			t_power_user = super.findByPrimaryKey(t_power_user);
			if (null == t_power_user) {
				throw new BusinessException(1010);
			} else if (!"1".equals(t_power_user.getValidstatus())) {
				throw new BusinessException(1013);
			} else {
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				if (bCryptPasswordEncoder.matches(oldPassword, t_power_user.getPassword())) {
					updatePwd(userid, password);
				} else {
					throw new BusinessException(3002);
				}
			}
			dbManager.commitTransaction();
		}catch(Exception e){
			dbManager.rollbackTransaction();
			throw e;
		}finally{
			dbManager.close();
		}
	}
}
