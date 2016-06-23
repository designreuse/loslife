package com.asgab.entity;

import com.asgab.util.CommonUtil;

public class User {
	private Long id;
	private String admin_account;
	private String version_type;
	private Long date_register;
	private Long expires_time;
	private int backup_count;
	private Long lastBackup_date;

	private Long search_start;
	private Long search_end;

	private String searchType;

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

	public void setDate_register(Long date_register) {
		this.date_register = date_register;
	}

	public Long getExpires_time() {
		return expires_time;
	}

	public String getFmtExpires_time() {
		return CommonUtil.formatDate(expires_time, "yyyy-MM-dd");
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

}
