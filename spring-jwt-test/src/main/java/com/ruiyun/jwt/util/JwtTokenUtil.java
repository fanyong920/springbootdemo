package com.ruiyun.jwt.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * token工具类
 * @author fff
 *
 */
public class JwtTokenUtil {
	
	private static final Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);
	private static final String SECRET = "miyao123456789";
	
	/**
	 * 生成token
	 * @param nick
	 * @return
	 */
	public static String createToken(String nick) {
		Map<String,Object> headerMap = new HashMap<String,Object>();
		headerMap.put("owner", "everyBody"); 
		//issuer 签发人  
		//issuedAt 签发时间  
		//jWTId 签发的id
		//audience 受众
		//subject 主题
		//not before 生效时间
		//expireAt 过期时间
		
		Date date = new Date();
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.MINUTE, 30);
	
		Algorithm hmac256 = Algorithm.HMAC256(SECRET);
		String token = JWT.create().withHeader(headerMap).withIssuer(nick).withIssuedAt(date).withJWTId(UUID.randomUUID().toString())
		.withAudience("hello").withSubject("test").withNotBefore(date).withExpiresAt(instance.getTime()).sign(hmac256);
		log.info("jwtToken ="+token);
		return token;
	}
	
	public static void main(String[] args) {
		JwtTokenUtil.createToken("nick");
	}
}
