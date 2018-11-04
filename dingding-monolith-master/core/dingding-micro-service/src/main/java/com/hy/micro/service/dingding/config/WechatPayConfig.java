package com.hy.micro.service.dingding.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-11
 * @github: https://github.com/weixuan2008
 */
@Configuration
public class WechatPayConfig {
	@Autowired
	private WechatAccountConfig wechatAccountConfig;

	@Bean
	public BestPayServiceImpl bestPayService() {
		BestPayServiceImpl bestPayService = new BestPayServiceImpl();
		bestPayService.setWxPayH5Config(wxPayH5Config());
		return bestPayService;
	}

	@Bean
	public WxPayH5Config wxPayH5Config() {
		WxPayH5Config wxPayH5Config = new WxPayH5Config();
		wxPayH5Config.setAppId(wechatAccountConfig.getMpAppId());
		wxPayH5Config.setAppSecret(wechatAccountConfig.getMpAppSecret());
		wxPayH5Config.setKeyPath(wechatAccountConfig.getKeyPath());
		wxPayH5Config.setMchId(wechatAccountConfig.getMchId());
		wxPayH5Config.setMchKey(wechatAccountConfig.getMchKey());
		wxPayH5Config.setNotifyUrl(wechatAccountConfig.getNotify_url());
		return wxPayH5Config;
	}
}
