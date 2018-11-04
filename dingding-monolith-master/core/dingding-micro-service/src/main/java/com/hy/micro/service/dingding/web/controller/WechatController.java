package com.hy.micro.service.dingding.web.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hy.micro.service.dingding.config.ProjectUrlConfig;
import com.hy.micro.service.dingding.enums.ExceptionCode;
import com.hy.micro.service.dingding.exception.BloomingException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-17
 * @github: https://github.com/weixuan2008
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {
	private static final String CHAR_ENC = "UTF-8";
	@Autowired
	private WxMpService wxMpService;

	@Autowired
	private WxMpService wxOpenService;

	@Autowired
	private ProjectUrlConfig projectUrlConfig;

	/**
	 * TODO:
	 * @param returnUrl
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws
	 */
	@GetMapping("/authorize")
	public String authorize(@RequestParam("returnUrl") String returnUrl) throws UnsupportedEncodingException {
		// 1. config
		// 2. call
		String url = projectUrlConfig.getMpAuthorizeUrl() + "/sell/wechat/userInfo";
		String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE,
				URLEncoder.encode(returnUrl, CHAR_ENC));
		return "redirect:" + redirectUrl;
	}

	/**
	 * TODO:
	 * @param code
	 * @param returnUrl
	 * @return
	 * @throws
	 */
	@GetMapping("/userInfo")
	public String userInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl) {
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
		try {
			wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
		} catch (WxErrorException ex) {
			log.error("[weichat Auth] {}", ex);
			throw new BloomingException(ExceptionCode.WECHAT_MP_ERROR, ex.getError().getErrorMsg());
		}

		String openId = wxMpOAuth2AccessToken.getOpenId();

		return "redirect:" + returnUrl + "?openid=" + openId;
	}

	/**
	 * TODO:
	 * @param returnUrl
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws
	 */
	@GetMapping("/qrAuthorize")
	public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) throws UnsupportedEncodingException {
		String url = projectUrlConfig.getOpenAuthorizeUrl() + "/sell/wechat/qrUserInfo";
		String redirectUrl = wxOpenService.buildQrConnectUrl(url, WxConsts.QrConnectScope.SNSAPI_LOGIN,
				URLEncoder.encode(returnUrl, CHAR_ENC));
		return "redirect:" + redirectUrl;
	}

	/**
	 * TODO:
	 * @param code
	 * @param returnUrl
	 * @return
	 * @throws
	 */
	@GetMapping("/qrUserInfo")
	public String qrUserInfo(@RequestParam("code") String code, @RequestParam("state") String returnUrl) {
		WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
		try {
			wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
		} catch (WxErrorException ex) {
			log.error("[weichat Auth] {}", ex);
			throw new BloomingException(ExceptionCode.WECHAT_MP_ERROR, ex.getError().getErrorMsg());
		}
		log.info("wxMpOAuth2AccessToken={}", wxMpOAuth2AccessToken);
		String openId = wxMpOAuth2AccessToken.getOpenId();

		return "redirect:" + returnUrl + "?openid=" + openId;
	}
}
