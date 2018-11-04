package com.hy.micro.service.dingding.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hy.micro.service.dingding.dto.OrderDTO;
import com.hy.micro.service.dingding.enums.ExceptionCode;
import com.hy.micro.service.dingding.exception.BloomingException;
import com.hy.micro.service.dingding.service.OrderService;
import com.hy.micro.service.dingding.service.PayService;
import com.lly835.bestpay.model.PayResponse;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-17
 * @github: https://github.com/weixuan2008
 */
@Controller
@RequestMapping("/pay")
public class PayController {
	@Autowired
	private OrderService orderService;

	@Autowired
	private PayService payService;

	/**
	 * TODO:
	 * @param orderId
	 * @param returnUrl
	 * @param map
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@GetMapping("/create")
	public ModelAndView create(@RequestParam("orderId") String orderId, 
			@RequestParam("returnUrl") String returnUrl,
			Map<String, Object> map) throws IllegalAccessException, InvocationTargetException {
		
		// 1. query order
		OrderDTO orderDTO = orderService.findByOrderId(orderId);
		if (orderDTO == null) {
			throw new BloomingException(ExceptionCode.ORDER_NOT_FOUND);
		}

		// 2. begin to pay
		PayResponse payResponse = payService.create(orderDTO);

		map.put("payResponse", payResponse);
		map.put("returnUrl", returnUrl);

		return new ModelAndView("pay/create", map);
	}

	/**
	 * Async notification by weichat
	 * @param notifyData
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@PostMapping("/notify")
	public ModelAndView notify(@RequestBody String notifyData) throws IllegalAccessException, InvocationTargetException {
		payService.notify(notifyData);

		// return handling result
		return new ModelAndView("pay/success");
	}
}
