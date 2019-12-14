package com.ruiyun.jwt.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class User {
	@NotNull
	@Size(min=2,max=18,message="名字必须在{min}和{max}个字符之间" )
	private String name;
	
	@NotNull
	@Size(min=6,max=18,message="密码长度在{min}和{max}个字符之间" )
	private String password;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
