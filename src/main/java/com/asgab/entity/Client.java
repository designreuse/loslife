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
  private String name;
  private String brand;
  private int industry_id;
  private int whether_channel;
  private String channel;
  private int currency_id;
  private String address;
  private int whether_cross_district;
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


}
