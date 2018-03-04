package com.songda.recruit.dto.admin.user;

import com.songda.recruit.pojo.support.BaseUserStatusEnum;

public class UserEditFormDTO {

	private BaseUserStatusEnum status;
	private String[] roles;
	public BaseUserStatusEnum getStatus() {
		return status;
	}
	public void setStatus(BaseUserStatusEnum status) {
		this.status = status;
	}
	public String[] getRoles() {
		return roles;
	}
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	
}
