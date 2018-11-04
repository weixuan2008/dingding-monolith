package com.hy.micro.service.dingding.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hy.micro.service.dingding.dto.OrderDTO;
import com.hy.micro.service.dingding.entity.OrderDetail;
import com.hy.micro.service.dingding.enums.ExceptionCode;
import com.hy.micro.service.dingding.exception.BloomingException;
import com.hy.micro.service.dingding.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;

/**
 * Convert form order to DTO order
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-08
 * @github: https://github.com/weixuan2008
 */
@Slf4j
public class ConvertOrderForm2OrderDTO {

	public static OrderDTO convert(OrderForm orderForm) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setBuyerName(orderForm.getName());
		orderDTO.setBuyerPhone(orderForm.getPhone());
		orderDTO.setBuyerAddress(orderForm.getAddress());
		orderDTO.setBuyerOpenid(orderForm.getOpenid());

		// order detail
		Gson gson = new Gson();
		List<OrderDetail> orderDetailList = new ArrayList<>();
		try {
			orderDetailList = gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>() {
			}.getType());
		} catch (Exception e) {
			log.error("Incorrect Object Convert, error = {}", orderForm.getItems());
			throw new BloomingException(ExceptionCode.PARAM_INCORRECT);
		}

		orderDTO.setOrderDetailList(orderDetailList);

		return orderDTO;
	}

}
