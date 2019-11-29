package com.ruiyun.event.bean;
/**
 * 事件发生所在app的信息
 * @author fanyong 2019-4-26
 *
 */
public class TblAppInfo {
	private String app_name;
	private String app_module;
	private String event_type;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((app_module == null) ? 0 : app_module.hashCode());
		result = prime * result + ((app_name == null) ? 0 : app_name.hashCode());
		result = prime * result + ((event_type == null) ? 0 : event_type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TblAppInfo other = (TblAppInfo) obj;
		if (app_module == null) {
			if (other.app_module != null)
				return false;
		} else if (!app_module.equals(other.app_module))
			return false;
		if (app_name == null) {
			if (other.app_name != null)
				return false;
		} else if (!app_name.equals(other.app_name))
			return false;
		if (event_type == null) {
			if (other.event_type != null)
				return false;
		} else if (!event_type.equals(other.event_type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TblAppInfo [app_name=" + app_name + ", app_module=" + app_module + ", event_type=" + event_type + "]";
	}
	
	
}
