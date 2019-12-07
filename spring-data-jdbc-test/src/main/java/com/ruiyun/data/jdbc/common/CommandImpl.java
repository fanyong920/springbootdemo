package com.ruiyun.data.jdbc.common;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ruiyun.data.jdbc.bean.Comsumer;

/**
 * 应用启动时候初始化数据
 * @author fff
 *
 */
@Component
public class CommandImpl implements CommandLineRunner {
	
	private static final Log log = LogFactory.getLog(CommandImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void run(String... args) throws Exception {
		//1、存在则删除
		jdbcTemplate.execute("DROP TABLE comsumer IF EXISTS");
		//2、创建表
		jdbcTemplate.execute("CREATE TABLE comsumer(id SERIAL,first_name VARCHAR(255),last_name VARCHAR(255))");
		//插入数据
		List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
		        .map(name -> name.split(" "))
		        .collect(Collectors.toList());

		// Use a Java 8 stream to print out each tuple of the list
		splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

		jdbcTemplate.batchUpdate("INSERT INTO comsumer(first_name,last_name) values(?,?)", splitUpNames);
		
		log.info("Querying for customer records where first_name = 'Josh':");
		
		//query
		jdbcTemplate.query("SELECT id, first_name, last_name FROM comsumer where first_name = ?",new Object[] {"Josh"},(rs,rowNum) -> new Comsumer(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))).forEach(customer -> log.info(customer.toString()));
		
	}

}
