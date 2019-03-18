package com.suntak.cloud.user.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.suntak.admin.entity.T_power_role;
import com.suntak.admin.entity.T_power_user;
import com.suntak.admin.entity.T_power_user_role_r;
import com.suntak.admin.entity.ext.T_power_user_ext;
import com.suntak.admin.user.exception.BusinessException;
import com.suntak.cloud.user.service.UserService;
import com.szmengran.common.orm.DBManager;
import com.szmengran.common.orm.dao.AbstractDao;
import com.szmengran.common.orm.util.Tools;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	@Qualifier("mySqlDao")
	AbstractDao abstractDao;
	
	@Override
	public T_power_user_ext login(String username, String password) throws BusinessException,Exception {
		StringBuffer conditions = new StringBuffer();
		conditions.append(" and username = ?");
		List<T_power_user> list = abstractDao.findByConditions(T_power_user.class, conditions, new Object[]{username});
		if(list == null || list.size() == 0){
			throw new BusinessException(1010);
		}else{
			T_power_user t_power_user = list.get(0);
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
				List<T_power_role> roleList = abstractDao.findByConditions(T_power_role.class, strCondition, new Object[]{t_power_user.getUserid()});
				if(roleList != null && roleList.size() > 0){
					String permissions[] =new String[roleList.size()];
					for(int i=0;i<roleList.size();i++){
						permissions[i] =roleList.get(i).getRolename();
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
		DBManager dbManager = null;
		try{
			dbManager = new DBManager(abstractDao.getWriteDataSource());
			dbManager.openConnection();
			dbManager.beginTransaction();
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			t_power_user.setUserid(Tools.generatePrimaryKeyByTime());
			t_power_user.setCreatestamp(new Timestamp(System.currentTimeMillis()));
			t_power_user.setUpdatestamp(new Timestamp(System.currentTimeMillis()));
			t_power_user.setPassword(bCryptPasswordEncoder.encode(t_power_user.getPassword()));
			t_power_user.setValidstatus("1");
			abstractDao.insert(dbManager,t_power_user);
			List<T_power_user_role_r> list = new ArrayList<T_power_user_role_r>();
			if(roleids != null){
				for(String roleid:roleids){
					T_power_user_role_r t_power_user_role_r = new T_power_user_role_r();
					t_power_user_role_r.setRoleid(Integer.parseInt(roleid));
					t_power_user_role_r.setUserid(t_power_user.getUserid());
					list.add(t_power_user_role_r);
				}
				abstractDao.addBatch(dbManager, list);
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
		abstractDao.executeSql(strSql, params);
	}
	
	@Override
	public void updatePwd(String userid, String password, String oldPassword) throws Exception {
		DBManager dbManager = null;
		try{
			dbManager = new DBManager(abstractDao.getWriteDataSource());
			dbManager.openConnection();
			dbManager.beginTransaction();
			T_power_user t_power_user = new T_power_user();
			t_power_user.setUserid(userid);
			t_power_user = abstractDao.findByPrimaryKey(t_power_user);
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
	
	@Override
	public List<T_power_user> findUserByRole(String assignrole) throws Exception {
		StringBuffer strSql = new StringBuffer();
		strSql.append("select userid, username, nickname from t_power_user where userid in(")
		.append("select userid from t_power_user_role_r where roleid in(")
		.append("select roleid from t_power_role where rolename=?))");
		return abstractDao.findBySql(T_power_user.class, strSql.toString(), new Object[] {assignrole});
	}

	@Override
	public List<T_power_role> findRoleByUsername(String username) throws Exception {
		StringBuffer strCondition = new StringBuffer();
		strCondition.append(" and roleid in (select roleid from t_power_user_role_r where userid in (")
				    .append(" select userid from t_power_user where username = ?))");
		return abstractDao.findByConditions(T_power_role.class, strCondition, new Object[]{username});
	}
}
