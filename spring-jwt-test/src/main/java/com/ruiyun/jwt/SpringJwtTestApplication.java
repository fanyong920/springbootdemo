package com.ruiyun.jwt;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.ruiyun.jwt.filter.JwtFilter;

@SpringBootApplication
public class SpringJwtTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtTestApplication.class, args);
	}
	
	
	@SuppressWarnings("rawtypes")
	@Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean<JwtFilter> filterRegistrationBean = new FilterRegistrationBean<JwtFilter>();
        filterRegistrationBean.setFilter(new JwtFilter());
        List<String> urlList = new ArrayList<>();
        urlList.add("/*");
        filterRegistrationBean.setUrlPatterns(urlList);
        return filterRegistrationBean;
    }
	
	@Bean
	public StringRedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory){
		return new StringRedisTemplate(redisConnectionFactory);
	}
}
