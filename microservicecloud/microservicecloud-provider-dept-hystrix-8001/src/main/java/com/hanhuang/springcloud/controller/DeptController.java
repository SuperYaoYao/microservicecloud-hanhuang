package com.hanhuang.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hanhuang.springcloud.entities.Dept;
import com.hanhuang.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class DeptController {

	@Autowired
	private DeptService service;
	
	//服务发现
	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return service.add(dept);
	}
	
	@RequestMapping(value = "/dept/get/{deptno}", method = RequestMethod.GET)
	//一旦调用服务方法失败并抛出了错误信息，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
	@HystrixCommand(fallbackMethod = "processHystrix_Get")
	public Dept get(@PathVariable("deptno") Long deptno) {
		Dept dept = service.get(deptno);
		if(dept == null) {
			throw new RuntimeException("该Deptno：" + deptno + "没有对应的信息");
		}
		return dept;
	}
	
	public Dept processHystrix_Get(@PathVariable("deptno") Long deptno) {
		return new Dept().setDeptno(deptno).setDname("该Deptno：" + deptno + "没有对应的信息，null--@HystrixCommand")
				.setDb_source("no this database in MySQL");
	}
	
	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list() {
		return service.list();
	}
	
	@RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
	public Object discovery() {
		List<String> list = client.getServices();
		System.out.println("*************");
		
		List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
		for(ServiceInstance element : srvList) {
			System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t" + element.getUri());
		}
		return this.client;
	}
	
}
