package com.ruiyun.event.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ruiyun.event.bean.SearchInfo;
import com.ruiyun.event.bean.TblAppInfo;

@Mapper
public interface TblAppInfoMapper {
	void  insertAppInfo(TblAppInfo appInfo);
	List<TblAppInfo>  queryAllAppInfo();
	List<TblAppInfo> queryAppInfos(String appName);
	TblAppInfo searchAppInfo(TblAppInfo tblAppInfo);
}
