package com.hy.micro.service.dingding.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import lombok.Data;

/**
 * Some url configuration for service.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-11
 * @github: https://github.com/weixuan2008
 */

@Configuration
@Data
@ConfigurationProperties(prefix = "project.url.config")
public class ProjectUrlConfig {

	/** weichat authorization url **/
	private String mpAuthorizeUrl;

	/** weichat web authorization url **/
	private String openAuthorizeUrl;

	/** front-end portal **/
	private String sell;
}
