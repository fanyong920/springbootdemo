package com.ruiyun.validate.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 表单实体类
 * @author fff
 *
 */
public class Person {
	
	@NotNull //不为空
	@Size(min = 2,max = 30)//长度在18到30
	private String name;
	
	@NotNull
	@Min(18)
	private int age;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
	
}
