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
  private int cross_regional;
  private Date created_at;
  private Date updated_at;
  private String status;

  private String channel_name;
  private String industry_name;

  // 广告主联系人
  private List<ClientContact> contacts;

  // 删除的联系人ID
  private String[] deleteContactIds;

  // 销售人员
  private String userIds;

  public String getUserIds() {
    return userIds;
  }

  public void setUserIds(String userIds) {
    this.userIds = userIds;
  }

  public String[] getDeleteContactIds() {
    return deleteContactIds;
  }

  public void setDeleteContactIds(String[] deleteContactIds) {
    this.deleteContactIds = deleteContactIds;
  }

  public List<ClientContact> getContacts() {
    return contacts;
  }

  public void setContacts(List<ClientContact> contacts) {
    this.contacts = contacts;
  }

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

  public int getCross_regional() {
    return cross_regional;
  }

  public void setCross_regional(int cross_regional) {
    this.cross_regional = cross_regional;
  }

  public Date getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Date created_at) {
    this.created_at = created_at;
  }

  public Date getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(Date updated_at) {
    this.updated_at = updated_at;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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


}
