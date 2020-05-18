package com.hanhuang.springcloud.service;

import java.util.List;

import com.hanhuang.springcloud.entities.Dept;

public interface DeptService {
	
	public boolean add(Dept dept);
	
	public Dept get(long deptno);
	
	public List<Dept> list();

}
