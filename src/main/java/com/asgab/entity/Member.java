package com.asgab.entity;

import java.util.HashMap;
import java.util.Map;

import com.asgab.util.CommonUtil;

public class Member {
	private String id;
	private String memberNo;
	private String name;
	private Integer sex;
	private Long birthday;
	private String phoneMobile;
	private Long joinDate;
	private Double currentScore;
	private Double totalScore;
	private String job;
	private String description;
	
	private static Map<Integer,String> sexs = new HashMap<Integer,String>();
	static{
		sexs.put(0, "Å®");
		sexs.put(1, "ÄÐ");
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Long getBirthday() {
		return birthday;
	}
	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}
	public String getPhoneMobile() {
		return phoneMobile;
	}
	public void setPhoneMobile(String phoneMobile) {
		this.phoneMobile = phoneMobile;
	}
	public Long getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Long joinDate) {
		this.joinDate = joinDate;
	}
	
	public Double getCurrentScore() {
		return currentScore;
	}
	public void setCurrentScore(Double currentScore) {
		this.currentScore = currentScore;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Double getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(Double totalScore) {
		this.totalScore = totalScore;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDecodeSex(){
		return sexs.get(sex);
	}
	public String getFmtBirthday(){
		if(birthday>0){
			return CommonUtil.formatDate(birthday, "yyyy-MM-dd"); 
		}
		return "";
	}
	
	public String getFmtJoinDate(){
		if(joinDate>0){
			return CommonUtil.formatDate(joinDate, "yyyy-MM-dd"); 
		}
		return "";
	}
	
}
