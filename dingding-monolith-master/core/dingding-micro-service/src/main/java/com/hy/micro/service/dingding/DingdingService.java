package com.hy.micro.service.dingding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-09-30
 * @github: https://github.com/weixuan2008
 */
@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
public class DingdingService {
	public static void main(String[] args) {
		SpringApplication.run(DingdingService.class, args);
	}
}
