package com.songda.recruit.pojo;
// Generated 2018-3-2 0:27:34 by Hibernate Tools 4.3.5.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * UserRoleMap generated by hbm2java
 */
@Entity
@Table(name = "user_role_map", catalog = "recruit")
public class UserRoleMap implements java.io.Serializable {

	private UserRoleMapId id;

	public UserRoleMap() {
	}

	public UserRoleMap(UserRoleMapId id) {
		this.id = id;
	}

	@EmbeddedId

	@AttributeOverrides({ @AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false)),
			@AttributeOverride(name = "roleCode", column = @Column(name = "role_code", nullable = false, length = 32)) })
	public UserRoleMapId getId() {
		return this.id;
	}

	public void setId(UserRoleMapId id) {
		this.id = id;
	}

}
