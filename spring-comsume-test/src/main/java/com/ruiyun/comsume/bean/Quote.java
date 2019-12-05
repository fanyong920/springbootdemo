package com.ruiyun.comsume.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)//忽略找不到的字段
public class Quote {
	
	private String type;
	private Value value;
	
	public Quote() {
		super();
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Value getValue() {
		return value;
	}
	
	public void setValue(Value value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Quote [type=" + type + ", value=" + value + "]";
	}
	
	
	
}
