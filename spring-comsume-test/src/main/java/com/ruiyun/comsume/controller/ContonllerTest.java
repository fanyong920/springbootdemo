package com.ruiyun.comsume.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ruiyun.comsume.bean.Quote;

@RestController
public class ContonllerTest {
	
	private static final Log log = LogFactory.getLog(ContonllerTest.class);
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/get")
	public Quote getQuote() { 
		return get();
	}
	
	public Quote get() {
		Quote quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		log.info(quote);
		return quote;
	}
}
