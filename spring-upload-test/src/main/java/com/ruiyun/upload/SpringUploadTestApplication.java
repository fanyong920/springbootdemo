package com.ruiyun.upload;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.ruiyun.upload.storage.StorageProperties;
import com.ruiyun.upload.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class SpringUploadTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringUploadTestApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(StorageService storageService) {
	    return (args) -> {
	      storageService.deleteAll();
	      storageService.init();
	    };
	}
}
