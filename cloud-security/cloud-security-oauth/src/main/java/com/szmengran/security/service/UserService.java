package com.szmengran.security.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.szmengran.common.service.AbstractService;
import com.szmengran.security.entity.CustomUserDetails;
import com.szmengran.security.entity.Role;
import com.szmengran.security.entity.User;

public class UserService extends AbstractService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    		String lowcaseUsername = username.toLowerCase();
        StringBuffer sql = new StringBuffer(" select userid,username,password from t_power_user where username=?");
        Object[] params = new Object[1];
        params[0] = username;
        List<Object> list = null;
		try {
			list = super.findBySql(new User(), sql.toString() , params);
		} catch (Exception e) {
			e.printStackTrace();
		}
        if(list == null || list.size()==0) {
        		throw new UsernameNotFoundException("用户" + lowcaseUsername + "不存在!");
        }
        User user = (User) list.get(0);
        StringBuffer roleSql = new StringBuffer();
        roleSql.append("select rolename name from t_power_role where roleid in (")
        .append(" select roleid from t_power_user_role_r where userid=?)");
        List<Object> roleList = null;
        Object roleParams[] = new Object[1];
        roleParams[0] = user.getUserid();
		try {
			roleList = super.findBySql(new Role(), roleSql.toString() , roleParams);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setRoles(roleList);
		String password = "123456";
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		user.setPassword(hashedPassword);
        return new CustomUserDetails(user);
    }
}
