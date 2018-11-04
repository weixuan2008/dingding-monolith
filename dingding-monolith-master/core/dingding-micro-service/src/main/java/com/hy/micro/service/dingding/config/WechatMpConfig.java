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
 * @Date: 2018-10-11
 * @github: https://github.com/weixuan2008
 */

@Configuration
public class WechatMpConfig {
	@Autowired
	private WechatAccountConfig wechatAccountConfig;

	@Bean
	public WxMpService wxMpService() {
		WxMpService wxMpService = new WxMpServiceImpl();
		wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
		return wxMpService;
	}

	@Bean
	public WxMpConfigStorage wxMpConfigStorage() {
		WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
		/** 设置微信公众号APPID. */
		wxMpConfigStorage.setAppId(wechatAccountConfig.getMpAppId());
		/** 设置微信公众号secretId. */
		wxMpConfigStorage.setSecret(wechatAccountConfig.getMpAppSecret());
		return wxMpConfigStorage;
	}
}
