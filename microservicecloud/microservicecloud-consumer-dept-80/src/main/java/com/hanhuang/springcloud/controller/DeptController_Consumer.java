package com.hanhuang.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hanhuang.springcloud.entities.Dept;

@RestController
public class DeptController_Consumer {
	
	//服务方地址前缀 
//	private static final String REST_URL_PREFIX = "http://localhost:8001";
//	private static final String REST_URL_PREFIX = "http://MICROSERVICECLOUD-DEPT"; //注册Eureka后必须使用服务名来访问
	private static final String REST_URL_PREFIX = "http://microservicecloud-dept";
	
	/**
	 * 使用restTemplate访问restful接口非常的简单粗暴无脑。
	 * (url, requestMap, ResponseBean.class)这三个参数分别代表 REST请求地址、请求参数、HTTP响应转换被转换成的对象类型
	 */
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/consumer/dept/add")
	public boolean add(@RequestBody Dept dept) {
		
		return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
		
	}
	
	@RequestMapping(value = "/consumer/dept/get/{deptno}")
	public Dept get(@PathVariable("deptno") Long deptno) {
		
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + deptno, Dept.class);
		
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/consumer/dept/list")
	public List<Dept> list() {
		
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
		
	}
	
	//测试@EnableDiscoveryClient，消费端可以调用服务发现
	@RequestMapping(value = "/consumer/dept/discovery")
	public Object discovery() {
		return restTemplate.getForObject(REST_URL_PREFIX + "/dept/discovery", Object.class);
	}

}
