package com.asgab.entity;

import java.math.BigDecimal;

import com.asgab.util.CommonUtil;

public class RechargeRecord {
	private String pay_id;
	private Long pay_date;
	private BigDecimal money;
	private String month;
	private Long expires_time;
	private String version_type;
	private String status;
	private String enterpriseId;
	private String description;
	private String operator;
	private String day;

	public String getPay_id() {
		return pay_id;
	}

	public void setPay_id(String pay_id) {
		this.pay_id = pay_id;
	}

	public Long getPay_date() {
		return pay_date;
	}

	public String getFmtPay_date() {
		return CommonUtil.formatDate(pay_date, "yyyy-MM-dd HH:mm");
	}

	public void setPay_date(Long pay_date) {
		this.pay_date = pay_date;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Long getExpires_time() {
		return expires_time;
	}

	public void setExpires_time(Long expires_time) {
		this.expires_time = expires_time;
	}

	public String getFmtExpires_time() {
		return CommonUtil.formatDate(expires_time, "yyyy-MM-dd HH:mm");
	}

	public String getVersion_type() {
		return version_type;
	}

	public void setVersion_type(String version_type) {
		this.version_type = version_type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public RechargeRecord() {
	}

	public RechargeRecord(User user) {
		this.pay_id = user.getPay_id();
		this.pay_date = user.getPay_date();
		this.money = user.getMoney();
		this.month = user.getMonth();
		this.expires_time = user.getExpires_time();
		this.version_type = user.getVersion_type();
		this.enterpriseId = user.getId();
		this.description = user.getDescription();
		this.operator = user.getOperator();
		this.day = user.getDay();
	}

}
