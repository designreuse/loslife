package com.asgab.entity;

public class ServiceAttrMap {
	private String id;
	private String service_id;
	private String groupName;
	private String keyName;
	private String value;
	private Long create_date;
	private String enterprise_id;
	
	private String serviceName;
	
	public static String cannotusecard = "cannotUseCard";
	public static String noDiscount = "noDiscount";
	public static String fixed_bonus = "fixed_bonus";
	public static String hasCommonUsedProject = "hasCommonUsedProject";
	// 有如果是卖品就有服务
	public static String service = "service";
	public static String stored_in_shop = "stored_in_shop";
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}


	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Long create_date) {
		this.create_date = create_date;
	}

	public String getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(String enterprise_id) {
		this.enterprise_id = enterprise_id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

}
