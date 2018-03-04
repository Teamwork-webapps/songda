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
 * TCompany generated by hbm2java
 */
@Entity
@Table(name = "t_company", catalog = "recruit")
public class TCompany implements java.io.Serializable {

	private Long id;
	private String name;
	private String address;
	private String telephone;
	private String mobile;
	private String email;
	private String content;
	private String type;
	private String legalPerson;
	private String longitude;
	private String latitude;
	private String contactName;
	private String contactMobile;
	private String companySize;
	private Integer isBanner;
	private String province;
	private String city;
	private String area;
	private Integer isMessage;
	private Long createBy;
	private Date createTime;
	private Long updateBy;
	private Date updateTime;
	private Boolean delflag;

	public TCompany() {
	}

	public TCompany(String name, String address, String telephone, String mobile, String email, String content,
			String type, String legalPerson, String longitude, String latitude, String contactName,
			String contactMobile, String companySize, Integer isBanner, String province, String city, String area,
			Integer isMessage, Long createBy, Date createTime, Long updateBy, Date updateTime, Boolean delflag) {
		this.name = name;
		this.address = address;
		this.telephone = telephone;
		this.mobile = mobile;
		this.email = email;
		this.content = content;
		this.type = type;
		this.legalPerson = legalPerson;
		this.longitude = longitude;
		this.latitude = latitude;
		this.contactName = contactName;
		this.contactMobile = contactMobile;
		this.companySize = companySize;
		this.isBanner = isBanner;
		this.province = province;
		this.city = city;
		this.area = area;
		this.isMessage = isMessage;
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

	@Column(name = "name", length = 256)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "address", length = 256)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	@Column(name = "email", length = 256)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "content", length = 65535)
	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "type")
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "legal_person", length = 64)
	public String getLegalPerson() {
		return this.legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	@Column(name = "longitude", length = 64)
	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "latitude", length = 64)
	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	@Column(name = "contact_name", length = 64)
	public String getContactName() {
		return this.contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	@Column(name = "contact_mobile", length = 64)
	public String getContactMobile() {
		return this.contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	@Column(name = "company_size")
	public String getCompanySize() {
		return this.companySize;
	}

	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}

	@Column(name = "is_banner")
	public Integer getIsBanner() {
		return this.isBanner;
	}

	public void setIsBanner(Integer isBanner) {
		this.isBanner = isBanner;
	}

	@Column(name = "province", length = 32)
	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	@Column(name = "city", length = 32)
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name = "area", length = 32)
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "is_message")
	public Integer getIsMessage() {
		return this.isMessage;
	}

	public void setIsMessage(Integer isMessage) {
		this.isMessage = isMessage;
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
