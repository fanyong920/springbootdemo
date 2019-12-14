package com.ruiyun.jwt.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ruiyun.jwt.bean.User;
import com.ruiyun.jwt.util.JwtTokenUtil;

/**
 * jwt test controller
 * @author fff
 *
 */

@Controller
public class JwtController implements WebMvcConfigurer {
	
	private static final Logger log = LoggerFactory.getLogger(JwtController.class);
	
	@Autowired
	StringRedisTemplate redisTemplate;
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
//		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/result").setViewName("result");
	}
	
	@RequestMapping("/")
	public String portal(User user){
		return "login";
	}
	
	@RequestMapping(value = "/",method = RequestMethod.POST)
	public String login(@Valid User user,BindingResult bindingResult){
		
		 RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HttpServletResponse response = ((ServletRequestAttributes)requestAttributes).getResponse();
		if(bindingResult.hasErrors()){
			return "login";
		}
		String token = JwtTokenUtil.createToken(user.getName());
		
		try {
			ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
			opsForValue.set(user.getName(), token);
		} catch (Exception e) {
			log.error("用户"+user.getName()+"token存redis失败：",e);
		}
		
		
		response.addCookie(new Cookie("Authorization", token));
		response.addCookie(new Cookie("name", user.getName()));
		return "redirect:result";
	}
}
