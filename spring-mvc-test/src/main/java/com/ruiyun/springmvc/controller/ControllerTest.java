package com.ruiyun.springmvc.controller;

import javax.websocket.server.PathParam;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * controller类
 * 
 * @author fff
 *
 */
@RestController //=@Controller + @ResponseBody
@RequestMapping(value = "/test")
public class ControllerTest {
	/**
	 * 测试PathValiable注解
	 * {} 为占位符 
	 * 占位符里面的变量名称与目标方法的入参名称相同，@PathVariable会自动将将占位符的值绑定到目标方法的入参
	 * 占位符里面的变量名称与目标方法的入参名称不相同，@PathVariable，需要指定name属性，name属性就是占位符的变量名称，会自动将将占位符的值绑定到目标方法的入参
	 * testid就是占位符变量名称
	 * name属性需要指定为testid
	 * 如果占位符变量名称是id,则不需要指定name属性
	 */
	@RequestMapping(value = "/path/{testid}")
	public void testPathValiable(@PathVariable(name = "testid") String id) {
		System.out.println("id = "+id);
	}
	/**
	 * 这个@PathPaeram是javax包下的，不是spring公司的，要和@Path搭配使用
	 * @param id
	 */
	@RequestMapping(value = "/param/{id}")
	public void testPathParam(@PathParam(value = "id") String id) {
		System.out.println("paramid = "+ id);
	}
	/**
	 * 获取request中的请求头
	 * @param user_Agent
	 */
	@RequestMapping(value = "/header",produces = "text/html;charset=utf-8")
	public void testRequestHeader(@RequestHeader(value = "User-Agent",required = true) String user_Agent) {
		System.out.println("user-Agent= "+ user_Agent);
	}
	/**
	 * 请求参数中name必须等于zhangsan
	 */
	@RequestMapping(value = "/request/param",params = {"name=zhangsan"})
	public void testParams() {
		System.out.println("test Params...");
	}
	/**
	 * content-type == "application/x-www-form-urlencoded"
	 * consumes指定该方法仅处理content-type为指定类型的request请求
	 */
	@RequestMapping(value = "/request/consumer",consumes = "text/html;charset=UTF-8")
	public void testComsumer() {
		System.out.println("test comsumer...");
	}
	
	/**
	 * 当request的请求头中的accept字段中包含 "text\html"时才返回数据
	 * producer指定返回的数据格式，仅当request的accept的字段包含该指定类型时才返回
	 */
	@RequestMapping(value = "/request/producer",produces = MediaType.TEXT_HTML_VALUE)
	public void testProducer() {
		System.out.println("test producer...");
	}
	
	
}
