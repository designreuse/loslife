package com.asgab.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asgab.util.CommonUtil;

public class User {
	private String id;
	private String admin_account;
	private String version_type;
	private Long date_register;
	private Long expires_time;
	private int backup_count;
	private Long lastBackup_date;
	private String name;
	private String contact_phoneMobile;
	// 账户的 总money
	private BigDecimal accountMoney;
	// 设备名
	private String deviceName;
	// 会员卡类型数
	private int membercardcategoryCount;
	// 员工数
	private int employeeCount;
	// 产品数
	private int serviceCount;

	private List<RechargeRecord> rechargeRecords = new ArrayList<>();

	// for search
	private Long search_start;
	private Long search_end;
	private String searchType;

	// for app
	private String pay_id;
	private Long pay_date;
	// 充值记录的 money
	private BigDecimal money;
	private String month;
	private Long recharge_expires_time;
	private String recharge_version_type;
	private String description;
	private String operator;
	private String day;

	public String getAdmin_account() {
		return admin_account;
	}

	public void setAdmin_account(String admin_account) {
		this.admin_account = admin_account;
	}

	public String getVersion_type() {
		return version_type;
	}

	public void setVersion_type(String version_type) {
		this.version_type = version_type;
	}

	public Long getDate_register() {
		return date_register;
	}

	public String getFmtDate_register() {
		return CommonUtil.formatDate(date_register, "yyyy-MM-dd HH:mm");
	}

	public String getFmtDate_register2() {
		return CommonUtil.formatDate(date_register, "yyyy年MM月dd日");
	}

	public void setDate_register(Long date_register) {
		this.date_register = date_register;
	}

	public Long getExpires_time() {
		return expires_time;
	}

	public String getFmtExpires_time() {
		return CommonUtil.formatDate(expires_time, "yyyy-MM-dd");
	}

	public String getFmtExpires_time2() {
		return CommonUtil.formatDate(expires_time, "yyyy年MM月dd日");
	}

	public void setExpires_time(Long expires_time) {
		this.expires_time = expires_time;
	}

	public int getBackup_count() {
		return backup_count;
	}

	public void setBackup_count(int backup_count) {
		this.backup_count = backup_count;
	}

	public Long getLastBackup_date() {
		return lastBackup_date;
	}

	public String getFmtLastBackup_date() {
		return CommonUtil.formatDate(lastBackup_date, "MM-dd HH:mm");
	}

	public void setLastBackup_date(Long lastBackup_date) {
		this.lastBackup_date = lastBackup_date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public Long getSearch_start() {
		return search_start;
	}

	public void setSearch_start(Long search_start) {
		this.search_start = search_start;
	}

	public Long getSearch_end() {
		return search_end;
	}

	public void setSearch_end(Long search_end) {
		this.search_end = search_end;
	}

	public List<RechargeRecord> getRechargeRecords() {
		return rechargeRecords;
	}

	public void setRechargeRecords(List<RechargeRecord> rechargeRecords) {
		this.rechargeRecords = rechargeRecords;
	}

	public String getPay_id() {
		return pay_id;
	}

	public void setPay_id(String pay_id) {
		this.pay_id = pay_id;
	}

	public Long getPay_date() {
		return pay_date;
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

	public Long getRecharge_expires_time() {
		return recharge_expires_time;
	}

	public void setRecharge_expires_time(Long recharge_expires_time) {
		this.recharge_expires_time = recharge_expires_time;
	}

	public String getRecharge_version_type() {
		return recharge_version_type;
	}

	public void setRecharge_version_type(String recharge_version_type) {
		this.recharge_version_type = recharge_version_type;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContact_phoneMobile() {
		return contact_phoneMobile;
	}

	public void setContact_phoneMobile(String contact_phoneMobile) {
		this.contact_phoneMobile = contact_phoneMobile;
	}

	public BigDecimal getAccountMoney() {
		return accountMoney;
	}

	public void setAccountMoney(BigDecimal accountMoney) {
		this.accountMoney = accountMoney;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public int getMembercardcategoryCount() {
		return membercardcategoryCount;
	}

	public void setMembercardcategoryCount(int membercardcategoryCount) {
		this.membercardcategoryCount = membercardcategoryCount;
	}

	public int getEmployeeCount() {
		return employeeCount;
	}

	public void setEmployeeCount(int employeeCount) {
		this.employeeCount = employeeCount;
	}

	public int getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(int serviceCount) {
		this.serviceCount = serviceCount;
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

}
