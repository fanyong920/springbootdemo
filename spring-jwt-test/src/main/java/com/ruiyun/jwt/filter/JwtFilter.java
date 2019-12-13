package com.ruiyun.jwt.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

/**
 * jwt filter
 * @author fff
 *
 */

@WebFilter
public class JwtFilter implements Filter {
	
	//验证请求是否合法
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		PrintWriter writer = response.getWriter();
		String authHeader = httpRequest.getHeader("Authorization");
		
		if(StringUtils.isEmpty(authHeader)) {
			writer.write("missing auth header");
			writer.close();
			return;
		}
		
		if(!authHeader.startsWith("Bearer")) {
			writer.write("auth header is invalid");
			writer.close();
			return;
		}
		
		
		
	}

}
