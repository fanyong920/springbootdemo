package com.ruiyun.event.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ruiyun.event.bean.SearchInfo;
import com.ruiyun.event.bean.TblEventLog;

@Mapper
public interface TblEventLogMapper {
	 void  insertEvent(TblEventLog eventlog);
	 List<TblEventLog>  searchEvents(SearchInfo searchInfo);
	 int  searchEventsCount(SearchInfo searchInfo);
	 
}
