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
 * TCompanyJob generated by hbm2java
 */
@Entity
@Table(name = "t_company_ job", catalog = "recruit")
public class TCompanyJob implements java.io.Serializable {

	private Long id;
	private String title;
	private String content;
	private String cond;
	private Date endDate;
	private Integer number;
	private BigDecimal commision;
	private Integer commisionType;
	private String workType;
	private String workAddress;
	private BigDecimal salaryLow;
	private BigDecimal salaryHigh;
	private Date publishTime;
	private Integer tag;
	private Integer type;
	private String salaryBriefs;
	private String subsidy;
	private String regime;
	private Long publisherId;
	private String mobile;
	private String qq;
	private String weixin;
	private Long createBy;
	private Date createTime;
	private Long updateBy;
	private Date updateTime;
	private Boolean delflag;

	public TCompanyJob() {
	}

	public TCompanyJob(String title, String content, String cond, Date endDate, Integer number, BigDecimal commision,
			Integer commisionType, String workType, String workAddress, BigDecimal salaryLow, BigDecimal salaryHigh,
			Date publishTime, Integer tag, Integer type, String salaryBriefs, String subsidy, String regime,
			Long publisherId, String mobile, String qq, String weixin, Long createBy, Date createTime, Long updateBy,
			Date updateTime, Boolean delflag) {
		this.title = title;
		this.content = content;
		this.cond = cond;
		this.endDate = endDate;
		this.number = number;
		this.commision = commision;
		this.commisionType = commisionType;
		this.workType = workType;
		this.workAddress = workAddress;
		this.salaryLow = salaryLow;
		this.salaryHigh = salaryHigh;
		this.publishTime = publishTime;
		this.tag = tag;
		this.type = type;
		this.salaryBriefs = salaryBriefs;
		this.subsidy = subsidy;
		this.regime = regime;
		this.publisherId = publisherId;
		this.mobile = mobile;
		this.qq = qq;
		this.weixin = weixin;
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

	@Column(name = "title", length = 256)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 1200)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "cond", length = 1200)
	public String getCond() {
		return this.cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date", length = 19)
	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Column(name = "number")
	public Integer getNumber() {
		return this.number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	@Column(name = "commision", precision = 20)
	public BigDecimal getCommision() {
		return this.commision;
	}

	public void setCommision(BigDecimal commision) {
		this.commision = commision;
	}

	@Column(name = "commision_type")
	public Integer getCommisionType() {
		return this.commisionType;
	}

	public void setCommisionType(Integer commisionType) {
		this.commisionType = commisionType;
	}

	@Column(name = "work_type", length = 256)
	public String getWorkType() {
		return this.workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	@Column(name = "work_address", length = 256)
	public String getWorkAddress() {
		return this.workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	@Column(name = "salary_low", precision = 20)
	public BigDecimal getSalaryLow() {
		return this.salaryLow;
	}

	public void setSalaryLow(BigDecimal salaryLow) {
		this.salaryLow = salaryLow;
	}

	@Column(name = "salary_high", precision = 20)
	public BigDecimal getSalaryHigh() {
		return this.salaryHigh;
	}

	public void setSalaryHigh(BigDecimal salaryHigh) {
		this.salaryHigh = salaryHigh;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "publish_time", length = 19)
	public Date getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	@Column(name = "tag")
	public Integer getTag() {
		return this.tag;
	}

	public void setTag(Integer tag) {
		this.tag = tag;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "salary_briefs", length = 256)
	public String getSalaryBriefs() {
		return this.salaryBriefs;
	}

	public void setSalaryBriefs(String salaryBriefs) {
		this.salaryBriefs = salaryBriefs;
	}

	@Column(name = "subsidy", length = 256)
	public String getSubsidy() {
		return this.subsidy;
	}

	public void setSubsidy(String subsidy) {
		this.subsidy = subsidy;
	}

	@Column(name = "regime", length = 256)
	public String getRegime() {
		return this.regime;
	}

	public void setRegime(String regime) {
		this.regime = regime;
	}

	@Column(name = "publisher_id")
	public Long getPublisherId() {
		return this.publisherId;
	}

	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}

	@Column(name = "mobile", length = 64)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "QQ", length = 64)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "weixin", length = 128)
	public String getWeixin() {
		return this.weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	@Column(name = "create_by")
	public Long getCreateBy() {
		return this.createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time", length = 19)
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

	@Column(name = "delflag")
	public Boolean getDelflag() {
		return this.delflag;
	}

	public void setDelflag(Boolean delflag) {
		this.delflag = delflag;
	}

}
