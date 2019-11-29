package com.ruiyun.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruiyun.event.bean.SearchInfo;
import com.ruiyun.event.bean.TblAppInfo;
import com.ruiyun.event.bean.TblEventLog;
import com.ruiyun.event.mapper.TblAppInfoMapper;
import com.ruiyun.event.mapper.TblEventLogMapper;

@Service
public class LogService {
	@Autowired
	private TblEventLogMapper tblEventLogMapper;
	
	@Autowired
	private TblAppInfoMapper tblAppInfoMapper;
	
	/**
	 * 查询符合条件的操作日志
	 * @return
	 */
	public List<TblEventLog> searchEvents(SearchInfo searchInfo) {
		return tblEventLogMapper.searchEvents(searchInfo);
	}
	public int searchEventCount(SearchInfo searchInfo) {
		return tblEventLogMapper.searchEventsCount(searchInfo);
	}
	/**
	 * 记录一条用户操作日志数据
	 * @param eventInfo
	 */
	public void insertEvent(TblEventLog eventInfo) {
		tblEventLogMapper.insertEvent(eventInfo);
	}
	
	public List<TblAppInfo> searchAppInfos(String appName) {
		return tblAppInfoMapper.queryAppInfos(appName);
	}
	/**
	 * 插入一条应用信息数据
	 * @param appInfo
	 */
	public void insertAppInfo(TblAppInfo appInfo) {
		tblAppInfoMapper.insertAppInfo(appInfo);
	}
	/**
	 * 查询所有应用信息
	 */
	public List<TblAppInfo> getAllAppInfo() {
		return tblAppInfoMapper.queryAllAppInfo();
	}
	public TblAppInfo searchAppInfo(TblAppInfo tblAppInfo) {
		return tblAppInfoMapper.searchAppInfo(tblAppInfo);
	}
	
}
