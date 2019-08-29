package com.ruiyun.http;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ruiyun.server.Server;

public  class HttpServletA extends HttpServlet {
	private static final Log log = LogFactory.getLog(Server.class);
	@Override
	public void init() {
		super.init();
		log.info("----HttpServletA init----");
	}

	@Override
	public void service(OutputStream outputStream) {
		super.service(outputStream);
		log.info("----HttpServletA service----");
		try {
			outputStream.write("A say Hello to World".getBytes());
		} catch (IOException e) {
			log.error("404 error a");
		}
	}

	@Override
	public void destory() {
		super.destory();
		log.info("----HttpServletA destory----");
	}

	

}
