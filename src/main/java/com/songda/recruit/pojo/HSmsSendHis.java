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
 * HSmsSendHis generated by hbm2java
 */
@Entity
@Table(name = "h_sms_send_his", catalog = "recruit")
public class HSmsSendHis implements java.io.Serializable {

	private Long id;
	private String phone;
	private String sign;
	private String templateCode;
	private String templateParam;
	private String upExtendCode;
	private Integer outId;
	private String requestId;
	private String code;
	private String message;
	private String bizId;
	private Integer dataState;
	private Integer type;
	private String validateCode;
	private long createBy;
	private Date createTime;
	private Long updateBy;
	private Date updateTime;
	private boolean delflag;

	public HSmsSendHis() {
	}

	public HSmsSendHis(long createBy, Date createTime, boolean delflag) {
		this.createBy = createBy;
		this.createTime = createTime;
		this.delflag = delflag;
	}

	public HSmsSendHis(String phone, String sign, String templateCode, String templateParam, String upExtendCode,
			Integer outId, String requestId, String code, String message, String bizId, Integer dataState, Integer type,
			String validateCode, long createBy, Date createTime, Long updateBy, Date updateTime, boolean delflag) {
		this.phone = phone;
		this.sign = sign;
		this.templateCode = templateCode;
		this.templateParam = templateParam;
		this.upExtendCode = upExtendCode;
		this.outId = outId;
		this.requestId = requestId;
		this.code = code;
		this.message = message;
		this.bizId = bizId;
		this.dataState = dataState;
		this.type = type;
		this.validateCode = validateCode;
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

	@Column(name = "phone", length = 64)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "sign")
	public String getSign() {
		return this.sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Column(name = "template_code", length = 128)
	public String getTemplateCode() {
		return this.templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	@Column(name = "template_param")
	public String getTemplateParam() {
		return this.templateParam;
	}

	public void setTemplateParam(String templateParam) {
		this.templateParam = templateParam;
	}

	@Column(name = "up_extend_code", length = 128)
	public String getUpExtendCode() {
		return this.upExtendCode;
	}

	public void setUpExtendCode(String upExtendCode) {
		this.upExtendCode = upExtendCode;
	}

	@Column(name = "out_id")
	public Integer getOutId() {
		return this.outId;
	}

	public void setOutId(Integer outId) {
		this.outId = outId;
	}

	@Column(name = "request_id", length = 64)
	public String getRequestId() {
		return this.requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	@Column(name = "code", length = 32)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "message")
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "biz_id", length = 128)
	public String getBizId() {
		return this.bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	@Column(name = "data_state")
	public Integer getDataState() {
		return this.dataState;
	}

	public void setDataState(Integer dataState) {
		this.dataState = dataState;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "validate_code", length = 32)
	public String getValidateCode() {
		return this.validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
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
