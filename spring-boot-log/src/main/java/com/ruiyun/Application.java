package com.ruiyun;

import org.mybatis.spring.annotation.MapperScan;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.ruiyun.login.LoginConfiguration;
import com.ruiyun.login.YMLSpringConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@ServletComponentScan
@SpringBootApplication 
@EnableConfigurationProperties({LoginConfiguration.class,YMLSpringConfiguration.class})
@MapperScan("com.ruiyun*mapper")
@EnableScheduling
public class Application {
	
//	private static String[] fn = null;
//	public static DisconfDemoTask t = null;
	// 初始化spring文档
//    private static void contextInitialized() {
//        fn = new String[] {"applicationContext.xml"};
//    }
	public static void main(String[] args) {  

        SpringApplication.run(Application.class);  
    }  
}
