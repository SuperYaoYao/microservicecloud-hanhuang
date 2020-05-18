package com.hanhuang.springcloud.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hanhuang.springcloud.entities.Dept;

//@FeignClient(value = "MICROSERVICECLOUD-DEPT")
@FeignClient(value = "MICROSERVICECLOUD-DEPT", fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {

	@RequestMapping(value = "/dept/get/{deptno}", method = RequestMethod.GET)
	public Dept get(@PathVariable("deptno") long deptno);
	
	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list();
	
	@RequestMapping(value = "/dept/add", method = RequestMethod.GET)
	public boolean add(Dept dept);
	
}
