package com.ruiyun.event.bean;

import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 操作日志查询条件
 * @author fanyong 2019-4-28
 *
 */
public class SearchInfo {
	private String app_name;
	private String app_module;//应用模块
	private String event_type;//事件类型
	private String startTime;//开始时间
	private String endTime;//戒指时间
	private String searchIid;//搜索关键字
	private String nick;//用户昵称
	public int currentPageNo=1;//查询页码
	public int pageSize=10;//一页多少条记录
	public int startNum;
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum  = startNum;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getApp_module() {
		return app_module;
	}
	public void setApp_module(String app_module) {
		this.app_module = app_module;
	}
	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSearchIid() {
		return searchIid;
	}
	public void setSearchIid(String searchIid) {
		if(StringUtils.isNotBlank(searchIid)) {
			this.searchIid = "%" + searchIid + "%";
		}
		
	}
	
	
	
}
