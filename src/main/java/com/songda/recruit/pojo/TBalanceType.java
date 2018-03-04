package com.songda.recruit.pojo;
// Generated 2018-3-2 20:14:30 by Hibernate Tools 4.3.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TBalanceType generated by hbm2java
 */
@Entity
@Table(name = "t_balance_type", catalog = "recruit")
public class TBalanceType implements java.io.Serializable {

	private Long id;
	private String name;
	private int type;
	private long prId;
	private long createBy;
	private Date createTime;
	private Long updateBy;
	private Date updateTime;
	private boolean delflag;

	public TBalanceType() {
	}

	public TBalanceType(String name, int type, long prId, long createBy, Date createTime, boolean delflag) {
		this.name = name;
		this.type = type;
		this.prId = prId;
		this.createBy = createBy;
		this.createTime = createTime;
		this.delflag = delflag;
	}

	public TBalanceType(String name, int type, long prId, long createBy, Date createTime, Long updateBy,
			Date updateTime, boolean delflag) {
		this.name = name;
		this.type = type;
		this.prId = prId;
		this.createBy = createBy;
		this.createTime = createTime;
		this.updateBy = updateBy;
		this.updateTime = updateTime;
		this.delflag = delflag;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, length = 32)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type", nullable = false)
	public int getType() {
		return this.type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Column(name = "pr_id", nullable = false)
	public long getPrId() {
		return this.prId;
	}

	public void setPrId(long prId) {
		this.prId = prId;
	}

	@Column(name = "create_by", nullable = false)
	public long getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(long createBy) {
		this.createBy = createBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", nullable = false, length = 19)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_by")
	public Long getUpdateBy() {
		return this.updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_time", length = 19)
	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "delflag", nullable = false)
	public boolean isDelflag() {
		return this.delflag;
	}

	public void setDelflag(boolean delflag) {
		this.delflag = delflag;
	}

}
