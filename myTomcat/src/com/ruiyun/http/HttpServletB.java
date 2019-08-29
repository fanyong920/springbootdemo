package com.ruiyun.http;

import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ruiyun.server.Server;

public  class HttpServletB extends HttpServlet {
	private static final Log log = LogFactory.getLog(Server.class);
	@Override
	public void init() {
		super.init();
		log.info("----HttpServletB init----");
	}

	@Override
	public void service(OutputStream outputStream) {
		super.service( outputStream);
		log.info("----HttpServletB service----");
		try {
			outputStream.write("B say Hello to World".getBytes());
		} catch (IOException e) {
			log.error("404 error b");
		}
	}

	@Override
	public void destory() {
		super.destory();
		log.info("----HttpServletB destory----");
	}

	

}
