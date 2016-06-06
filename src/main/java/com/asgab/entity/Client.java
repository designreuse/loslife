package com.asgab.entity;

import java.util.Date;
import java.util.List;

public class Client {

  /***
   * 广告主
   * 
   * @author Siuvan
   */

  private Long id;
  // 广告主名称/公司名称
  private String name;
  // 品牌
  private String brand;
  // 行业
  private int industry_id;
  // 是否是代理
  private int whether_channel;
  // 代理
  private String channel;
  // 货币
  private int currency_id;
  // 公司地址
  private String address;
  // 是否跨区
  private int whether_cross_district;
  // 资质名称
  private String qualification_name;
  // 网站名称
  private String website_name;
  // 网址
  private String website_address;
  // 组织代码
  private String organization_code;
  // icp
  private String icp;
  // 营业执照
  private String business_licence;
  //
  private String organization_code_scan_file;
  private String business_licence_scan_file;
  private String icp_scan_file;

  private String linkman_name;
  private String linkman_tel;
  private String linkman_position;
  // 平台
  private String platform;

  private Date created_at;
  private String created_user;
  private Long user_id;
  private Date updated_at;
  private String client_status;

  private String channel_name;
  private String industry_name;

  // 广告主联系人
  private List<ClientContact> contacts;

  // 删除的联系人ID
  private String[] deleteContactIds;

  // 销售人员
  private String userIds;
  private String userNames;

  private String dateRange;
  private String createDateStart;
  private String createDateEnd;

  public Client() {
    super();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public int getIndustry_id() {
    return industry_id;
  }

  public void setIndustry_id(int industry_id) {
    this.industry_id = industry_id;
  }

  public int getWhether_channel() {
    return whether_channel;
  }

  public void setWhether_channel(int whether_channel) {
    this.whether_channel = whether_channel;
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public int getCurrency_id() {
    return currency_id;
  }

  public void setCurrency_id(int currency_id) {
    this.currency_id = currency_id;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getWhether_cross_district() {
    return whether_cross_district;
  }

  public void setWhether_cross_district(int whether_cross_district) {
    this.whether_cross_district = whether_cross_district;
  }

  public Date getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }

  public String getCreated_user() {
    return created_user;
  }

  public void setCreated_user(String created_user) {
    this.created_user = created_user;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public Date getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(Date updated_at) {
    this.updated_at = updated_at;
  }

  public String getClient_status() {
    return client_status;
  }

  public void setClient_status(String client_status) {
    this.client_status = client_status;
  }

  public String getChannel_name() {
    return channel_name;
  }

  public void setChannel_name(String channel_name) {
    this.channel_name = channel_name;
  }

  public String getIndustry_name() {
    return industry_name;
  }

  public void setIndustry_name(String industry_name) {
    this.industry_name = industry_name;
  }

  public List<ClientContact> getContacts() {
    return contacts;
  }

  public void setContacts(List<ClientContact> contacts) {
    this.contacts = contacts;
  }

  public String[] getDeleteContactIds() {
    return deleteContactIds;
  }

  public void setDeleteContactIds(String[] deleteContactIds) {
    this.deleteContactIds = deleteContactIds;
  }

  public String getUserIds() {
    return userIds;
  }

  public void setUserIds(String userIds) {
    this.userIds = userIds;
  }

  public String getUserNames() {
    return userNames;
  }

  public void setUserNames(String userNames) {
    this.userNames = userNames;
  }

  public String getQualification_name() {
    return qualification_name;
  }

  public void setQualification_name(String qualification_name) {
    this.qualification_name = qualification_name;
  }

  public String getWebsite_name() {
    return website_name;
  }

  public void setWebsite_name(String website_name) {
    this.website_name = website_name;
  }

  public String getWebsite_address() {
    return website_address;
  }

  public void setWebsite_address(String website_address) {
    this.website_address = website_address;
  }

  public String getOrganization_code() {
    return organization_code;
  }

  public void setOrganization_code(String organization_code) {
    this.organization_code = organization_code;
  }

  public String getIcp() {
    return icp;
  }

  public void setIcp(String icp) {
    this.icp = icp;
  }

  public String getBusiness_licence() {
    return business_licence;
  }

  public void setBusiness_licence(String business_licence) {
    this.business_licence = business_licence;
  }

  public String getOrganization_code_scan_file() {
    return organization_code_scan_file;
  }

  public void setOrganization_code_scan_file(String organization_code_scan_file) {
    this.organization_code_scan_file = organization_code_scan_file;
  }

  public String getBusiness_licence_scan_file() {
    return business_licence_scan_file;
  }

  public void setBusiness_licence_scan_file(String business_licence_scan_file) {
    this.business_licence_scan_file = business_licence_scan_file;
  }

  public String getIcp_scan_file() {
    return icp_scan_file;
  }

  public void setIcp_scan_file(String icp_scan_file) {
    this.icp_scan_file = icp_scan_file;
  }

  public String getLinkman_name() {
    return linkman_name;
  }

  public void setLinkman_name(String linkman_name) {
    this.linkman_name = linkman_name;
  }

  public String getLinkman_tel() {
    return linkman_tel;
  }

  public void setLinkman_tel(String linkman_tel) {
    this.linkman_tel = linkman_tel;
  }

  public String getLinkman_position() {
    return linkman_position;
  }

  public void setLinkman_position(String linkman_position) {
    this.linkman_position = linkman_position;
  }

  public String getPlatform() {
    return platform;
  }

  public void setPlatform(String platform) {
    this.platform = platform;
  }

  public String getDateRange() {
    return dateRange;
  }

  public void setDateRange(String dateRange) {
    this.dateRange = dateRange;
  }

  public String getCreateDateStart() {
    return createDateStart;
  }

  public void setCreateDateStart(String createDateStart) {
    this.createDateStart = createDateStart;
  }

  public String getCreateDateEnd() {
    return createDateEnd;
  }

  public void setCreateDateEnd(String createDateEnd) {
    this.createDateEnd = createDateEnd;
  }

}
