package com.api.httpUtil.精简.httpUtil.postMain.test;

import java.io.Serializable;

/**
 * @创建人 : djl
 * @创建时间 : 2018-07-03 下午 下午5:13:00
 * @描述 : 用户用量
 */
public class UserDosageDTO implements Serializable {

	private static final long serialVersionUID = -332865019044403368L;
	private int number; // 序号
	private long dosage; // 用量
	private String userType; // 用户类型
	private String userName; // 用户名
	private String area; // 地区
	private String industry; // 行业
	private String userId; //用户id
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public long getDosage() {
		return dosage;
	}
	public void setDosage(long dosage) {
		this.dosage = dosage;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
