package com.ruiyun.event.bean;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * 事件日志的具体信息
 * @author fanyong 2019-4-26
 *
 */
public class TblEventLog {
	private Long event_id;
	private String nick;
	private String ip;
	private String app_name;
	private String app_module;
	private String event_type;
	private String event_desc;
	private String event_time;
	private String event_location;
	private String event_platform;
	private String relate_iids;
	private String relate_task_id;
	
	
	public String getEvent_location() {
		return event_location;
	}
	public void setEvent_location(String event_location) {
		this.event_location = event_location;
	}
	public Long getEvent_id() {
		return event_id;
	}
	public void setEvent_id(Long event_id) {
		this.event_id = event_id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
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
	public String getEvent_desc() {
		return event_desc;
	}
	public void setEvent_desc(String event_desc) {
		this.event_desc = event_desc;
	}
	public String getEvent_time() {
		return event_time;
	}
	public void setEvent_time(String event_time) {
		this.event_time = event_time;
	}
	public String getEvent_platform() {
		return event_platform;
	}
	public void setEvent_platform(String event_platform) {
		this.event_platform = event_platform;
	}
	public String getRelate_iids() {
		return relate_iids;
	}
	public void setRelate_iids(String relate_iids) {
		this.relate_iids = relate_iids;
	}
	public String getRelate_task_id() {
		return relate_task_id;
	}
	public void setRelate_task_id(String relate_task_id) {
		this.relate_task_id = relate_task_id;
	}
	@Override
	public String toString() {
		return "TblEventLog [event_id=" + event_id + ", nick=" + nick + ", ip=" + ip + ", app_name=" + app_name
				+ ", app_module=" + app_module + ", event_type=" + event_type + ", event_desc=" + event_desc
				+ ", event_time=" + event_time + ", event_location=" + event_location + ", event_platform="
				+ event_platform + ", relate_iids=" + relate_iids + ", relate_task_id=" + relate_task_id + "]";
	}

	
	
	
}
