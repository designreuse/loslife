package com.asgab.entity;

import com.asgab.util.CommonUtil;

public class BackupHistory {
	private String enterprise_id;
	private Long backup_time;
	private String id;

	public String getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(String enterprise_id) {
		this.enterprise_id = enterprise_id;
	}

	public Long getBackup_time() {
		return backup_time;
	}

	public void setBackup_time(Long backup_time) {
		this.backup_time = backup_time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFmtBackup_time() {
		return CommonUtil.formatDate(backup_time, "yyyy-MM-dd");
	}
	
	public String getFmtBackup_time2() {
		return CommonUtil.formatDate(backup_time, "HH:mm:ss");
	}

}
