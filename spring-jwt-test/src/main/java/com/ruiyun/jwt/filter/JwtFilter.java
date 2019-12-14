package com.ruiyun.jwt.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.ruiyun.jwt.util.JwtTokenUtil;

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
		String token = JwtTokenUtil.getCookie(httpRequest,"Authorization");
		String requestURI = httpRequest.getRequestURI();

		if( "/".equals(requestURI)){
			chain.doFilter(request, response);
		}else{
			
				if(StringUtils.isEmpty(token)) {
					writer.write("missing auth header ");
					writer.close();
					return;
				}else{
					if(!token.startsWith("Bearer")) {
						writer.write("auth header is invalid");
						writer.close();
						return;
					}
					
					String name = JwtTokenUtil.getCookie(httpRequest,"name");
					if(JwtTokenUtil.Validate(token, name)){
						chain.doFilter(request, response);
					}else{
						writer.write("auth header is invalid");
						writer.close();
						return;
					}
				}
				
				
		}
		
		
		
		
		
	}

}
