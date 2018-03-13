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
			// TODO Auto-generated catch block
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
//        return new User(t_power_user.getUsername(),t_power_user.getPassword(), null);
//        return new UserRepositoryUserDetails((T_power_user) list.get(0), this);
    }

//    private final static class UserRepositoryUserDetails extends T_power_user implements UserDetails {
//
//		private static final long serialVersionUID = 1L;
//		private UserService userService = null;
//		private UserRepositoryUserDetails(T_power_user t_power_user, UserService userService) {
//			this.setUsername(t_power_user.getUsername());
//			this.setUserid(t_power_user.getUserid());
//			this.userService = userService;
//		}
//
//		@Override
//		public Collection<? extends GrantedAuthority> getAuthorities() {
//			StringBuffer conditions = new StringBuffer();
//			
//			return this.userService.findByConditions(object, conditions, params);
//		}
//
//		@Override
//		public String getUsername() {
//			return this.getUsername();
//		}
//
//		@Override
//		public boolean isAccountNonExpired() {
//			return true;
//		}
//
//		@Override
//		public boolean isAccountNonLocked() {
//			return true;
//		}
//
//		@Override
//		public boolean isCredentialsNonExpired() {
//			return true;
//		}
//
//		@Override
//		public boolean isEnabled() {
//			return true;
//		}
//
//	}
}
