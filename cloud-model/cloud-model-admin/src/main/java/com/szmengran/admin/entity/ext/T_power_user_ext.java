package com.szmengran.admin.entity.ext;

import java.io.Serializable;

import com.szmengran.admin.entity.T_power_user;

public class T_power_user_ext extends T_power_user implements Serializable{
	private static final long serialVersionUID = 1L;
	private String avatar;
	private String[] permissions;
	
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String[] getPermissions() {
		return permissions;
	}
	public void setPermissions(String[] permissions) {
		this.permissions = permissions;
	}
	
}
