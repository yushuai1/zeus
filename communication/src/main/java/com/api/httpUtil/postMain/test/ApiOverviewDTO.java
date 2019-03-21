package com.api.httpUtil.postMain.test;

import java.io.Serializable;
import java.util.List;

public class ApiOverviewDTO extends PageBase implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 9102935920832025536L;
	private int timeType = 2; // 统计类型 , 1: 按月统计 , 2: 按天统计 , 3 按小时
    private String regST = "2018-06-14";  // 开始时间 例：2018-06-14
    private String regET = "2018-06-15";  // 结束时间 例：2018-06-15
    private int serviceType; // service类型：1、虹膜搜索   2、虹膜对比
    private int interfaceId; // 接口
    private int industry; //行业
    private String currentTime = "2018-06-15";  //列表中的时间
    private List<UserDosageDTO> resultList;
	public int getTimeType() {
		return timeType;
	}
	public void setTimeType(int timeType) {
		this.timeType = timeType;
	}
	public String getRegST() {
		return regST;
	}
	public void setRegST(String regST) {
		this.regST = regST;
	}
	public String getRegET() {
		return regET;
	}
	public void setRegET(String regET) {
		this.regET = regET;
	}
	public int getServiceType() {
		return serviceType;
	}
	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}
	public int getInterfaceId() {
		return interfaceId;
	}
	public void setInterfaceId(int interfaceId) {
		this.interfaceId = interfaceId;
	}
	public int getIndustry() {
		return industry;
	}
	public void setIndustry(int industry) {
		this.industry = industry;
	}
	public String getCurrentTime() {
		return currentTime;
	}
	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
	public List<UserDosageDTO> getResultList() {
		return resultList;
	}
	public void setResultList(List<UserDosageDTO> resultList) {
		this.resultList = resultList;
	}
}