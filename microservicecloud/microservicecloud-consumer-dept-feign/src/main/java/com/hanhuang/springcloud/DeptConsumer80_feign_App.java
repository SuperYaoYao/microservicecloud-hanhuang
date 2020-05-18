package com.hanhuang.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient //服务发现
@EnableFeignClients(basePackages = {"com.hanhuang.springcloud"})
@ComponentScan("com.hanhuang.springcloud")
public class DeptConsumer80_feign_App {

	public static void main(String[] args) {
		
		SpringApplication.run(DeptConsumer80_feign_App.class, args);
		
	}
	
}
