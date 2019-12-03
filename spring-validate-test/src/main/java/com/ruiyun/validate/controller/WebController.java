package com.ruiyun.validate.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ruiyun.validate.bean.Person;
/**
 * WebMvcConfigurer是一个MVC配置类，可以增加拦截器，静态资源，跨域请求，MessageConvert,参数解析，返回值处理
 * @author fff
 *
 */
@Controller
public class WebController implements WebMvcConfigurer {
	/**
	 * 配置视图控制器
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/results").setViewName("results");
	}
	
	@GetMapping("/")
	public String showForm(Person person) {
		return "form";
	}
	
	/**
	 * 表单提交有错误返回表单页面，正确重定向到results页面
	 * @param person
	 * @param bindingResult
	 * @return
	 */
	@PostMapping("/")
	public String checkPersonInfo(@Valid Person person,BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "form";
		}
		
		return "redirect:results";
	}
}
