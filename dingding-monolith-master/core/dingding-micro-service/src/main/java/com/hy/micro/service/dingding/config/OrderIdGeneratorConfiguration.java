package com.hy.micro.service.dingding.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hy.micro.service.dingding.utils.OrderIdUtils;

import lombok.Data;

/**
 * The utility to generate the unique order id.
 * @Author: Eddie.Wei   
 * @Date:   2018-10-09 
 * @github: https://github.com/weixuan2008
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "order.id.generator")
public class OrderIdGeneratorConfiguration {
	private int workerId;
	private int datacenterId;
	
	@Bean
	public OrderIdUtils orderIdUtil() {
		return new OrderIdUtils(workerId, datacenterId);
	}
}
