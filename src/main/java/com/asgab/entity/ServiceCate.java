package com.asgab.entity;

public class ServiceCate {
	private String id;
	private String name;
	private String image;
	private Long create_date;
	private Integer type;
	private String enterprise_id;
	private String description;
	private Integer sort_no;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Long create_date) {
		this.create_date = create_date;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(String enterprise_id) {
		this.enterprise_id = enterprise_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort_no() {
		return sort_no;
	}

	public void setSort_no(Integer sort_no) {
		this.sort_no = sort_no;
	}

}
