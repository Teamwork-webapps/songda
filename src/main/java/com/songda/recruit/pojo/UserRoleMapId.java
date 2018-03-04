package com.songda.recruit.pojo;
// Generated 2018-3-2 0:27:34 by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * UserRoleMapId generated by hbm2java
 */
@Embeddable
public class UserRoleMapId implements java.io.Serializable {

	private long userId;
	private String roleCode;

	public UserRoleMapId() {
	}

	public UserRoleMapId(long userId, String roleCode) {
		this.userId = userId;
		this.roleCode = roleCode;
	}

	@Column(name = "user_id", nullable = false)
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name = "role_code", nullable = false, length = 32)
	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof UserRoleMapId))
			return false;
		UserRoleMapId castOther = (UserRoleMapId) other;

		return (this.getUserId() == castOther.getUserId())
				&& ((this.getRoleCode() == castOther.getRoleCode()) || (this.getRoleCode() != null
						&& castOther.getRoleCode() != null && this.getRoleCode().equals(castOther.getRoleCode())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getUserId();
		result = 37 * result + (getRoleCode() == null ? 0 : this.getRoleCode().hashCode());
		return result;
	}

}