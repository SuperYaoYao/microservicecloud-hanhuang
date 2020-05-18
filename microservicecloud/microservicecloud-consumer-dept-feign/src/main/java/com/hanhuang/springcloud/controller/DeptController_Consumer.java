package com.hanhuang.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hanhuang.springcloud.entities.Dept;
import com.hanhuang.springcloud.service.DeptClientService;

@RestController
public class DeptController_Consumer {
	
	@Autowired
	private DeptClientService service;
	
	@RequestMapping(value = "/consumer/dept/get/{deptno}", method = RequestMethod.GET)
	public Dept get(@PathVariable("deptno") long deptno) {
		return this.service.get(deptno);
	};
	
	@RequestMapping(value = "/consumer/dept/list", method = RequestMethod.GET)
	public List<Dept> list(){
		return this.service.list();
	};
	
	@RequestMapping(value = "/consumer/dept/add", method = RequestMethod.GET)
	public boolean add(Dept dept){
		return this.service.add(dept);
	};

}
