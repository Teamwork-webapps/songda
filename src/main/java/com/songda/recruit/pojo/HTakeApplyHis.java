package com.songda.recruit.pojo;
// Generated 2018-3-2 20:14:30 by Hibernate Tools 4.3.5.Final

import java.math.BigDecimal;
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
 * HTakeApplyHis generated by hbm2java
 */
@Entity
@Table(name = "h_take_apply_his", catalog = "recruit")
public class HTakeApplyHis implements java.io.Serializable {

	private Long id;
	private long userId;
	private BigDecimal price;
	private long approverId;
	private Boolean stat;
	private Date approveTime;
	private String remark;
	private Boolean payStat;
	private Date payTime;
	private BigDecimal payPrice;
	private long createBy;
	private Date createTime;
	private Long updateBy;
	private Date updateTime;
	private boolean delflag;

	public HTakeApplyHis() {
	}

	public HTakeApplyHis(long userId, BigDecimal price, long approverId, Date approveTime, Date payTime,
			BigDecimal payPrice, long createBy, Date createTime, boolean delflag) {
		this.userId = userId;
		this.price = price;
		this.approverId = approverId;
		this.approveTime = approveTime;
		this.payTime = payTime;
		this.payPrice = payPrice;
		this.createBy = createBy;
		this.createTime = createTime;
		this.delflag = delflag;
	}

	public HTakeApplyHis(long userId, BigDecimal price, long approverId, Boolean stat, Date approveTime, String remark,
			Boolean payStat, Date payTime, BigDecimal payPrice, long createBy, Date createTime, Long updateBy,
			Date updateTime, boolean delflag) {
		this.userId = userId;
		this.price = price;
		this.approverId = approverId;
		this.stat = stat;
		this.approveTime = approveTime;
		this.remark = remark;
		this.payStat = payStat;
		this.payTime = payTime;
		this.payPrice = payPrice;
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

	@Column(name = "user_id", nullable = false)
	public long getUserId() {
		return this.userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name = "price", nullable = false, precision = 20)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "approver_id", nullable = false)
	public long getApproverId() {
		return this.approverId;
	}

	public void setApproverId(long approverId) {
		this.approverId = approverId;
	}

	@Column(name = "stat")
	public Boolean getStat() {
		return this.stat;
	}

	public void setStat(Boolean stat) {
		this.stat = stat;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "approve_time", nullable = false, length = 19)
	public Date getApproveTime() {
		return this.approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	@Column(name = "remark", length = 100)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "pay_stat")
	public Boolean getPayStat() {
		return this.payStat;
	}

	public void setPayStat(Boolean payStat) {
		this.payStat = payStat;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "pay_time", nullable = false, length = 19)
	public Date getPayTime() {
		return this.payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	@Column(name = "pay_price", nullable = false, precision = 20)
	public BigDecimal getPayPrice() {
		return this.payPrice;
	}

	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
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
