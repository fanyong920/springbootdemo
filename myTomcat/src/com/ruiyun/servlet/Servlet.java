package com.ruiyun.servlet;

import java.io.OutputStream;

/**
 * Servlet�ӿ�
 * @author fanyong
 *
 */
public interface Servlet {
	/**
	 * ��ʼ������
	 */
	void init();
	/**
	 * ������÷���
	 */
	void service(OutputStream outputStream);
	/**
	 * ���ٷ���
	 */
	void destory();
}
