package com.hanhuang.springcloud.entities;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@SuppressWarnings("serial")
@AllArgsConstructor //全参构造函数
@NoArgsConstructor //空参构造函数
@Data //Set-Get
@Accessors(chain = true) //链式访问
public class Dept implements Serializable {

	private Long deptno; //主键
	private String dname; //部门名称
	private String db_source; //来自哪个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存储到不同数据库
	
}
