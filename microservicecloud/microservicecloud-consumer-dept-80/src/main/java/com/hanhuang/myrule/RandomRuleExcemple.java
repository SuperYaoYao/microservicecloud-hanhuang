package com.hanhuang.myrule;

import java.util.List;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

/**
 * 自定义Ribbon算法
 * 
 */
public class RandomRuleExcemple extends AbstractLoadBalancerRule {

	/**
	 * 分析：当前共有三个服务，每个服务被调用5次，才会继续调用下个服务，所以机器号需要0-3轮询出现
	 */
	private Integer total = 0;			//总共被调用的次数，目前要求每台被调用5次
	private Integer currentIndex = 0;	//当前提供服务的机器号
	
	@SuppressWarnings("unused")
	public Server choose(ILoadBalancer lb, Object key) {
		if(lb == null) {
			return null;
		}
		Server server = null;
		
		while(server == null) {
			if(Thread.interrupted()) {
				return null;
			}
			List<Server> upList = lb.getReachableServers();
			List<Server> allList = lb.getAllServers();
			
			int serverCount = allList.size();
			if(serverCount == 0) {
				return null;
			}
			
			if(total < 5) {
				Integer index = currentIndex % allList.size();
				server = upList.get(index);
				total++;
			}else {
				total = 0;
				currentIndex++;
			}
			
			if(server == null) {
				Thread.yield();
				continue;
			}
			
			if(server.isAlive()) {
				return (server);
			}
			
			server = null;
			Thread.yield();
		}
		return server;
	}
	
	@Override
	public Server choose(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initWithNiwsConfig(IClientConfig clientConfig) {
		// TODO Auto-generated method stub
		
	}

}
