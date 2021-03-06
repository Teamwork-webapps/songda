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
 * HBalanceHis generated by hbm2java
 */
@Entity
@Table(name = "h_balance_his", catalog = "recruit")
public class HBalanceHis implements java.io.Serializable {

	private Long id;
	private long baseUserId;
	private BigDecimal price;
	private long baseBalanceType1;
	private Long baseBalanceType2;
	private long createBy;
	private Date createTime;
	private Long updateBy;
	private Date updateTime;
	private boolean delflag;

	public HBalanceHis() {
	}

	public HBalanceHis(long baseUserId, BigDecimal price, long baseBalanceType1, long createBy, Date createTime,
			boolean delflag) {
		this.baseUserId = baseUserId;
		this.price = price;
		this.baseBalanceType1 = baseBalanceType1;
		this.createBy = createBy;
		this.createTime = createTime;
		this.delflag = delflag;
	}

	public HBalanceHis(long baseUserId, BigDecimal price, long baseBalanceType1, Long baseBalanceType2, long createBy,
			Date createTime, Long updateBy, Date updateTime, boolean delflag) {
		this.baseUserId = baseUserId;
		this.price = price;
		this.baseBalanceType1 = baseBalanceType1;
		this.baseBalanceType2 = baseBalanceType2;
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

	@Column(name = "base_user_id", nullable = false)
	public long getBaseUserId() {
		return this.baseUserId;
	}

	public void setBaseUserId(long baseUserId) {
		this.baseUserId = baseUserId;
	}

	@Column(name = "price", nullable = false, precision = 20)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "base_balance_type1", nullable = false)
	public long getBaseBalanceType1() {
		return this.baseBalanceType1;
	}

	public void setBaseBalanceType1(long baseBalanceType1) {
		this.baseBalanceType1 = baseBalanceType1;
	}

	@Column(name = "base_balance_type2")
	public Long getBaseBalanceType2() {
		return this.baseBalanceType2;
	}

	public void setBaseBalanceType2(Long baseBalanceType2) {
		this.baseBalanceType2 = baseBalanceType2;
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
