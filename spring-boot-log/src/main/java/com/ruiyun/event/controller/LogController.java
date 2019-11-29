package com.ruiyun.event.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ruiyun.common.Response;
import com.ruiyun.common.ResponseHead;
import com.ruiyun.event.annotation.Log;
import com.ruiyun.event.bean.SearchInfo;
import com.ruiyun.event.bean.TblAppInfo;
import com.ruiyun.event.bean.TblEventLog;
import com.ruiyun.event.service.LogService;
import com.zoulab.common.util.ValidateUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * 用户日志记录与查询类
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/log")
@CrossOrigin
@EnableSwagger2
@Api(value = "用户操作日志记录与查询" , description="用户操作日志记录与查询")
public class LogController {
	private static Logger log=LoggerFactory.getLogger(LogController.class);
   
    @Autowired
    private LogService logService;
    
    @SuppressWarnings("rawtypes")
	@PostMapping(value="/searchevents")
    @ApiOperation(value="查询所有用户操作日志",notes="查询所有用户操作日志")
    public @ResponseBody Response SearchEvents(@RequestBody SearchInfo searchInfo) {
    	try {
    		if(ValidateUtil.isEmpty(searchInfo.getNick()) || ValidateUtil.isEmpty(searchInfo.getApp_name())) {
    			return new Response<>(null,new ResponseHead(-1L,"查询用户操作日志出错","nick或应用名称为空",-1L)) ;
    		}
    		searchInfo.setStartNum((searchInfo.getCurrentPageNo()-1)*searchInfo.getPageSize());
    		Map<String,Object>  map = new HashMap<String,Object>();
            List<TblEventLog> Events = logService.searchEvents(searchInfo);
            int count = logService.searchEventCount(searchInfo);
            for (TblEventLog tblEventLog : Events) {
            	String event_time = tblEventLog.getEvent_time();
            	 event_time = event_time.substring(0,event_time.length()-2);
            	 tblEventLog.setEvent_time(event_time);
			}
            map.put("allEventLog", Events);
            Response response = new Response<>(map);
            response.setTotal(count);
            return response;
		} catch (Exception e) {
			log.error("查询用户操作日志出错："+e);
			 return new Response<>(null,new ResponseHead(-1L,"查询用户操作日志出错","查询用户操作日志出错",-1L)) ;
		}
    }
    @SuppressWarnings("rawtypes")
	@PostMapping(value="/searchappinfos")
    @ApiOperation(value="查询日志应用信息",notes="查询日志应用信息")
    public @ResponseBody Response searchappinfos(String appName) {
    	try {
            List<TblAppInfo> AppInfos = logService.searchAppInfos(appName);
            Map<String,Object>  map = new HashMap<String,Object>();
            map.put("appInfos", AppInfos);
            Response response = new Response<>(map);
            return response;
		} catch (Exception e) {
			log.error("查询日志应用出错："+e);
			 return new Response<>(null,new ResponseHead(-1L,"查询日志应用信息出错","查询日志应用信息出错",-1L)) ;
		}
    }
    @Log("插入用户操作行为到数据库")
    @PostMapping(value="/insertevent")
    @ApiOperation(value="用户操作日志记录",notes="用户操作日志记录")
    public void insertEvent(@RequestBody TblEventLog tblEventLog) {
    	try {
    		log.info("开始记录一条用户操作事件日志数据:"+tblEventLog.toString());
		} catch (Exception e) {
			log.error("用户["+tblEventLog.getNick()+"]插入操作日志记录出错",e);
		}
    	
    }
}
