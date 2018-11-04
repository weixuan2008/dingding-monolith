package com.hy.micro.service.dingding.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.hy.micro.service.dingding.dto.OrderDTO;
import com.hy.micro.service.dingding.enums.ExceptionCode;
import com.hy.micro.service.dingding.service.OrderService;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-16
 * @github: https://github.com/weixuan2008
 */

@Controller
@RequestMapping("seller/order")
@Slf4j
public class SellerOrderController {
	@Autowired
	private OrderService orderService;

	/**
	 * The api for get order list
	 * @param page
	 * @param size
	 * @param map
	 * @return
	 * @throws
	 */
	@GetMapping("/list")
	public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size, Map<String, Object> map) {
		PageRequest request = PageRequest.of(page - 1, size);
		Page<OrderDTO> orderDTOPage = orderService.findAll(request);

		map.put("orderDTOPage", orderDTOPage);
		map.put("currentPage", page);
		map.put("size", size);

		return new ModelAndView("order/list", map);
	}

	/**
	 * The api to cancel order.
	 * @param orderId
	 * @param map
	 * @return
	 * @throws
	 */
	@GetMapping("/cancel")
	public ModelAndView cancel(@RequestParam("orderId") String orderId, Map<String, Object> map) {
		try {
			OrderDTO orderDTO = orderService.findByOrderId(orderId);
			orderService.cancel(orderDTO);
		} catch (IllegalAccessException | InvocationTargetException ex) {
			log.error("Failed to cancel order, error msg: {}", ex);
			map.put("msg", ex.getMessage());
			map.put("url", "/seller/order/list");

			return new ModelAndView("common/error", map);
		}

		map.put("msg", ExceptionCode.ORDER_CANCEL_SUCCESS);
		map.put("url", "/seller/order/list");

		return new ModelAndView("common/success");

	}
	
	/**
	 * The api for get order detail.
	 * @param orderId
	 * @param map
	 * @return
	 * @throws
	 */
	@GetMapping("/detail")
	public ModelAndView detail(@RequestParam("orderId") String orderId,
			Map<String, Object> map) {
		OrderDTO orderDTO = new OrderDTO();
		try {
			orderDTO = orderService.findByOrderId(orderId);
		} catch (IllegalAccessException | InvocationTargetException ex) {
			log.error("Failed to query the buyer order, error msg: {}", ex);
			map.put("msg", ex.getMessage());
			map.put("url", "/seller/order/list");
			
			return new ModelAndView("common/error", map);
		}
		
		map.put("orderDTO", orderDTO);
		return new ModelAndView("order/detail", map);
	}
	
	
	/**
	 * The api to finish order.
	 * @param orderId
	 * @param map
	 * @return
	 * @throws
	 */
	@GetMapping("/finish")
	public ModelAndView finished(@RequestParam("orderId") String orderId,
			Map<String, Object> map) {
		try {
			OrderDTO orderDTO = orderService.findByOrderId(orderId);
			orderService.finish(orderDTO);
		} catch (IllegalAccessException | InvocationTargetException ex) {
			log.error("Failed to finish order, error msg: {}", ex);
			map.put("msg", ex.getMessage());
			map.put("url", "/seller/order/list");
			
			return new ModelAndView("common/error", map);
		}
			
		map.put("msg", ExceptionCode.ORDER_FINISH_SUCCESS);
		map.put("url", "/seller/order/list");
		
		return new ModelAndView("common/success");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
