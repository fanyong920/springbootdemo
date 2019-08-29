package com.ruiyun.servlet;

import java.io.OutputStream;

/**
 * Servlet接口
 * @author fanyong
 *
 */
public interface Servlet {
	/**
	 * 初始化方法
	 */
	void init();
	/**
	 * 请求调用方法
	 */
	void service(OutputStream outputStream);
	/**
	 * 销毁方法
	 */
	void destory();
}
