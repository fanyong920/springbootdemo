package com.ruiyun.comsume.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.ruiyun.comsume.bean.Quote;


/**
 * <b>配置类</b>
 * @author fff
 *
 */
@Configuration
public class ConfigureRest {
	private static final Log log = LogFactory.getLog(ConfigureRest.class);
	
	/**
	 * 创建一个client
	 * @param builder
	 * @return
	 */
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(RestTemplate restTemplate) {
		return (args) -> {
			 Quote quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
			 log.info(quote);
		};
	}
	
	
}
