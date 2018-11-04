package com.hy.micro.service.dingding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * The general websocket configuration
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-11
 * @github: https://github.com/weixuan2008
 */

@Configuration
public class WebSocketConfig {
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}
}
