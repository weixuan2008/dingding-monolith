package com.hy.micro.service.dingding.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hy.micro.service.dingding.config.WechatAccountConfig;
import com.hy.micro.service.dingding.dto.OrderDTO;
import com.hy.micro.service.dingding.service.PushMessageService;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-11
 * @github: https://github.com/weixuan2008
 */

@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {
	@Autowired
	private WxMpService wxMpService;

	@Autowired
	private WechatAccountConfig wechatAccountConfig;

	@Override
	public void notifyWhenOrderStatusChanged(OrderDTO orderDTO) {
		WxMpTemplateMessage wxMpTemplateMessage = new WxMpTemplateMessage();
		wxMpTemplateMessage.setTemplateId(wechatAccountConfig.getTemplateIds().get("orderStatus"));
		wxMpTemplateMessage.setToUser(orderDTO.getBuyerOpenid());

		List<WxMpTemplateData> datas = Arrays.asList(new WxMpTemplateData("keyword1", orderDTO.getOrderId()),
				new WxMpTemplateData("keyword2", "微信点餐"), new WxMpTemplateData("keyword3", "1"),
				new WxMpTemplateData("keyword4", "￥" + orderDTO.getOrderAmount()),
				new WxMpTemplateData("keyword5", orderDTO.getOrderStatus().getMsg()),
				new WxMpTemplateData("remark", "欢迎下次管理!"));

		wxMpTemplateMessage.setData(datas);

		try {
			wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
		} catch (WxErrorException e) {
			log.error("【订单状态变化通知】通知失败，e={}", e);
			e.printStackTrace();
		}
	}

}
