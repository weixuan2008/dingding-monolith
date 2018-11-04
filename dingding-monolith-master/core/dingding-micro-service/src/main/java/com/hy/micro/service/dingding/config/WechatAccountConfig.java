package com.hy.micro.service.dingding.config;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * The configuration for wechat
 * @Author: Eddie.Wei
 * @Date: 2018-10-11
 * @github: https://github.com/weixuan2008
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "wechat.account.config")
public class WechatAccountConfig {
	/** wechat appid **/
	private String mpAppId;

	/** wechat appSecret **/
	private String mpAppSecret;

	/** wechat open platform appid **/
	private String openAppId;

	/** wechat open platform appSecret **/
	private String openAppSecret;

	/** commercial tenant id **/
	private String mchId;

	/** commercial tenant key **/
	private String mchKey;

	/** certificate path **/
	private String keyPath;

	/** callback for async notification **/
	private String notify_url;

	/** templaate id */
	private Map<String, String> templateIds;
}
