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
 * TGroupMembers generated by hbm2java
 */
@Entity
@Table(name = "t_group_members", catalog = "recruit")
public class TGroupMembers implements java.io.Serializable {

	private Long id;
	private long groupLeaderId;
	private String name;
	private Integer gender;
	private Integer age;
	private String telephone;
	private String mobile;
	private String qq;
	private String weixin;
	private String email;
	private Long baseEducationId;
	private String homeAddress;
	private String address;
	private String bankCardNum;
	private int currentState;
	private Long currentJob;
	private Date beginTime;
	private boolean leaderCollection;
	private int entryNum;
	private BigDecimal priceToPay;
	private BigDecimal priceTotal;
	private long createBy;
	private Date createTime;
	private Long updateBy;
	private Date updateTime;
	private boolean delflag;

	public TGroupMembers() {
	}

	public TGroupMembers(long groupLeaderId, int currentState, boolean leaderCollection, int entryNum,
			BigDecimal priceToPay, BigDecimal priceTotal, long createBy, Date createTime, boolean delflag) {
		this.groupLeaderId = groupLeaderId;
		this.currentState = currentState;
		this.leaderCollection = leaderCollection;
		this.entryNum = entryNum;
		this.priceToPay = priceToPay;
		this.priceTotal = priceTotal;
		this.createBy = createBy;
		this.createTime = createTime;
		this.delflag = delflag;
	}

	public TGroupMembers(long groupLeaderId, String name, Integer gender, Integer age, String telephone, String mobile,
			String qq, String weixin, String email, Long baseEducationId, String homeAddress, String address,
			String bankCardNum, int currentState, Long currentJob, Date beginTime, boolean leaderCollection,
			int entryNum, BigDecimal priceToPay, BigDecimal priceTotal, long createBy, Date createTime, Long updateBy,
			Date updateTime, boolean delflag) {
		this.groupLeaderId = groupLeaderId;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.telephone = telephone;
		this.mobile = mobile;
		this.qq = qq;
		this.weixin = weixin;
		this.email = email;
		this.baseEducationId = baseEducationId;
		this.homeAddress = homeAddress;
		this.address = address;
		this.bankCardNum = bankCardNum;
		this.currentState = currentState;
		this.currentJob = currentJob;
		this.beginTime = beginTime;
		this.leaderCollection = leaderCollection;
		this.entryNum = entryNum;
		this.priceToPay = priceToPay;
		this.priceTotal = priceTotal;
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

	@Column(name = "group_leader_id", nullable = false)
	public long getGroupLeaderId() {
		return this.groupLeaderId;
	}

	public void setGroupLeaderId(long groupLeaderId) {
		this.groupLeaderId = groupLeaderId;
	}

	@Column(name = "name", length = 256)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "gender")
	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@Column(name = "age")
	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Column(name = "telephone", length = 64)
	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Column(name = "mobile", length = 64)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "qq", length = 64)
	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Column(name = "weixin", length = 64)
	public String getWeixin() {
		return this.weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	@Column(name = "email", length = 256)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "base_education_id")
	public Long getBaseEducationId() {
		return this.baseEducationId;
	}

	public void setBaseEducationId(Long baseEducationId) {
		this.baseEducationId = baseEducationId;
	}

	@Column(name = "home_address", length = 256)
	public String getHomeAddress() {
		return this.homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	@Column(name = "address", length = 256)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "bank_card_num", length = 128)
	public String getBankCardNum() {
		return this.bankCardNum;
	}

	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}

	@Column(name = "current_state", nullable = false)
	public int getCurrentState() {
		return this.currentState;
	}

	public void setCurrentState(int currentState) {
		this.currentState = currentState;
	}

	@Column(name = "current_job")
	public Long getCurrentJob() {
		return this.currentJob;
	}

	public void setCurrentJob(Long currentJob) {
		this.currentJob = currentJob;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "begin_time", length = 19)
	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	@Column(name = "leader_collection", nullable = false)
	public boolean isLeaderCollection() {
		return this.leaderCollection;
	}

	public void setLeaderCollection(boolean leaderCollection) {
		this.leaderCollection = leaderCollection;
	}

	@Column(name = "entry_num", nullable = false)
	public int getEntryNum() {
		return this.entryNum;
	}

	public void setEntryNum(int entryNum) {
		this.entryNum = entryNum;
	}

	@Column(name = "price_to_pay", nullable = false, precision = 20)
	public BigDecimal getPriceToPay() {
		return this.priceToPay;
	}

	public void setPriceToPay(BigDecimal priceToPay) {
		this.priceToPay = priceToPay;
	}

	@Column(name = "price_total", nullable = false, precision = 20)
	public BigDecimal getPriceTotal() {
		return this.priceTotal;
	}

	public void setPriceTotal(BigDecimal priceTotal) {
		this.priceTotal = priceTotal;
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