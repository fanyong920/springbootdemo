package com.ruiyun.jwt.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

/**
 * token工具类
 * 
 * @author fff
 *
 */
public class JwtTokenUtil {

	private static final Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);
	private static final String SECRET = "miyao123456789";
	private static final Algorithm HMAC256 = Algorithm.HMAC256(SECRET);
	
	
	/**
	 * 生成token
	 * 
	 * @param nick
	 * @return
	 */
	public static String createToken(String nick) {
		Map<String, Object> headerMap = new HashMap<String, Object>();
		headerMap.put("owner", "everyBody");
		// issuer 签发人
		// issuedAt 签发时间
		// jWTId 签发的id
		// audience 受众
		// subject 主题
		// not before 生效时间
		// expireAt 过期时间

		Date date = new Date();
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.MINUTE, 30);

		String token = JWT.create().withHeader(headerMap).withIssuer(nick).withIssuedAt(date)
				.withJWTId(UUID.randomUUID().toString()).withAudience("hello").withSubject("test").withNotBefore(date)
				.withExpiresAt(instance.getTime()).sign(HMAC256);
		log.info("jwtToken =" + token);
		//存redis
		token = addBearer(token);
		
		return token;
	}

	/**
	 * 验证token
	 * 验证算法、签名、受众和主题 这四个同时符合才能验证通过
	 * @param token
	 * @return
	 */
	public static boolean Validate(String token, String nick) {
		
		token = eraseToken(token);
		
		try {
			JWTVerifier jwtVerifier = JWT.require(HMAC256).withIssuer(nick).withAudience("hello").withSubject("test")
					.build();
			jwtVerifier.verify(token);
		} catch (JWTVerificationException e) {
			log.error("用户"+nick+"token验证不通过：",e);
			return false;
		}

		return true;
	}
	
	/**
	 * 添加 "Bearer" 在 token的开始位置
	 * @param token
	 * @return
	 */
	public static String  addBearer(String token){
		return "Bearer<"+token+">";
	}
	
	/**
	 * 抹去token中的"Bearer"
	 * @param token
	 * @return
	 */
	public static String eraseToken(String token){
		if(StringUtils.isEmpty(token)){
			throw new NullPointerException("token is null");
		}
		return token.replace("Bearer<", "").replace(">", "");
	}
	
	public static String getCookie(HttpServletRequest request,String name){
		
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if(name.equals(cookie.getName())){
				return cookie.getValue();
			}
		}
		return null;
	}
}
