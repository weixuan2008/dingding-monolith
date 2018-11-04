package com.hy.micro.service.dingding.utils;

import org.springframework.beans.BeanUtils;

import com.hy.micro.service.dingding.dto.OrderDTO;
import com.hy.micro.service.dingding.entity.OrderMaster;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Convert master order to DTO order
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-08
 * @github: https://github.com/weixuan2008
 */
public class ConvertOrderMaster2OrderDTO {

	public static OrderDTO convert(OrderMaster orderMaster) {
		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);
		return orderDTO;
	}

	public static List<OrderDTO> convert(List<OrderMaster> orderMasterList) {
		return orderMasterList.stream().map(e -> convert(e)).collect(Collectors.toList());
	}
}
