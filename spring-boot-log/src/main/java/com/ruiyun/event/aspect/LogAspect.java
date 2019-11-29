package com.ruiyun.event.aspect;

import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruiyun.event.bean.TblAppInfo;
import com.ruiyun.event.bean.TblEventLog;
import com.ruiyun.event.service.LogService;

@Aspect // 切面类 把当前类标识为一个切面供容器读取
@Component // 注入springboot容器
public class LogAspect {
	private static Logger log=LoggerFactory.getLogger(LogAspect.class);
	@Autowired
    private LogService logService;

	// 建立日志对象线程变量
	private  ThreadLocal<TblEventLog> threadLocal = new ThreadLocal<>();

	/**
	 * 定义切点 @annotation代表匹配注解
	 */
	@Pointcut(value = "@annotation(com.ruiyun.event.annotation.Log)")
	public void pointCut() {

	}
	/**
	 * 编码
	 * @param point
	 * @throws Exception
	 */
	@Before("pointCut()")
	public void insertBefore(JoinPoint point) throws Exception {
		//获取方法的参数
		Object[] args = point.getArgs();
		if(args != null && args.length > 0) {
			Object paramObject = args[0];
			if(paramObject instanceof TblEventLog) {
				//编码 
				TblEventLog tblEventLog = (TblEventLog)paramObject;
	    		tblEventLog.setNick(URLDecoder.decode(tblEventLog.getNick(), "utf-8"));
	    		tblEventLog.setApp_name(URLDecoder.decode(tblEventLog.getApp_name(), "utf-8"));
	    		tblEventLog.setApp_module(URLDecoder.decode(tblEventLog.getApp_module(), "utf-8"));
	    		tblEventLog.setEvent_type(URLDecoder.decode(tblEventLog.getEvent_type(), "utf-8"));
	    		tblEventLog.setEvent_desc(URLDecoder.decode(tblEventLog.getEvent_desc(), "utf-8"));
	    		tblEventLog.setEvent_platform(URLDecoder.decode(tblEventLog.getEvent_platform(), "utf-8"));
	    		
	    		if(StringUtils.isNotBlank(tblEventLog.getIp()))tblEventLog.setIp(URLDecoder.decode(tblEventLog.getIp(), "utf-8"));
	    		if(StringUtils.isNotBlank(tblEventLog.getEvent_location()))tblEventLog.setEvent_location(URLDecoder.decode(tblEventLog.getEvent_location(), "utf-8"));
	    		if(StringUtils.isNotBlank(tblEventLog.getRelate_iids()))tblEventLog.setRelate_iids(URLDecoder.decode(tblEventLog.getRelate_iids(), "utf-8"));
	    		if(StringUtils.isNotBlank(tblEventLog.getRelate_task_id()))tblEventLog.setRelate_task_id(URLDecoder.decode(tblEventLog.getRelate_task_id(), "utf-8"));
	    		threadLocal.set(tblEventLog);
			}
		}
	}
	
	/**
	 * 编码后调用
	 */
	@After("pointCut()")
	public void after() {
		TblEventLog tblEventLog = threadLocal.get();
		try {
			if(tblEventLog != null) {
				logService.insertEvent(tblEventLog);
				TblAppInfo appInfo = new TblAppInfo();
		    	appInfo.setApp_module(tblEventLog.getApp_module());
		    	appInfo.setApp_name(tblEventLog.getApp_name());
		    	appInfo.setEvent_type(tblEventLog.getEvent_type());
		    	
				TblAppInfo queryAppInfo = logService.searchAppInfo(appInfo);
		    	//没包涵appInfo
		    	if(queryAppInfo == null) {
		    		logService.insertAppInfo(appInfo);
		    		log.info("记录一条操作日志应用信息数据" +appInfo);
		    	}
			}
		}catch(Exception e){
			log.error("记录用户操作行为出错：",e);
		}finally {
			threadLocal.remove();
		}
		
	}
}
