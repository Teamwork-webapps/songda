package com.songda.recruit.pojo;
// Generated 2018-3-4 17:26:48 by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * BaseRole generated by hbm2java
 */
@Entity
@Table(name = "base_role", catalog = "recruit")
public class BaseRole implements java.io.Serializable {

	private String code;
	private String remark;

	public BaseRole() {
	}

	public BaseRole(String code, String remark) {
		this.code = code;
		this.remark = remark;
	}

	@Id

	@Column(name = "code", unique = true, nullable = false, length = 32)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "remark", nullable = false, length = 120)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
