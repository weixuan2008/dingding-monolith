package com.hy.micro.service.dingding.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

/**
 * 
 * @Author: Eddie.Wei   
 * @Date:   2018-10-11 
 * @github: https://github.com/weixuan2008
 */
@Configuration
public class WechatOpenConfig {
	@Autowired
	private WechatAccountConfig wechatAccountConfig;

	@Bean
	public WxMpService wxOpenService() {
		WxMpService wxOpenService = new WxMpServiceImpl();
		wxOpenService.setWxMpConfigStorage(wxOpenConfigStorage());
		return wxOpenService;
	}

	@Bean
	public WxMpConfigStorage wxOpenConfigStorage() {
		WxMpInMemoryConfigStorage wxOpenConfigStorage = new WxMpInMemoryConfigStorage();
		wxOpenConfigStorage.setSecret(wechatAccountConfig.getOpenAppSecret());
		wxOpenConfigStorage.setAppId(wechatAccountConfig.getOpenAppId());
		return wxOpenConfigStorage;
	}
}
