package com.hanhuang.springcloud.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.hanhuang.springcloud.entities.Dept;

import feign.hystrix.FallbackFactory;

/**
 * 备选响应
 * @author Administrator
 *
 */
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {

	@Override
	public DeptClientService create(Throwable cause) {
		// TODO Auto-generated method stub
		return new DeptClientService() {
			
			@Override
			public List<Dept> list() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Dept get(long deptno) {
				// TODO Auto-generated method stub
				return new Dept().setDeptno(deptno).setDname("该Deptno：" + deptno + "没有对应的信息，Consumer客户端提供的降级信息，此刻服务Provider已经关闭")
				.setDb_source("no this database in MySQL");
			}
			
			@Override
			public boolean add(Dept dept) {
				// TODO Auto-generated method stub
				return false;
			}
		};
	}

}
