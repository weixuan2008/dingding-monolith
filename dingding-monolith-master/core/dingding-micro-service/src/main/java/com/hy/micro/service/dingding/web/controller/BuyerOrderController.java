package com.hy.micro.service.dingding.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hy.micro.service.dingding.dto.OrderDTO;
import com.hy.micro.service.dingding.enums.ExceptionCode;
import com.hy.micro.service.dingding.exception.BloomingException;
import com.hy.micro.service.dingding.form.OrderForm;
import com.hy.micro.service.dingding.service.BuyerService;
import com.hy.micro.service.dingding.service.OrderService;
import com.hy.micro.service.dingding.utils.ConvertOrderForm2OrderDTO;
import com.hy.micro.service.dingding.utils.ResultUtils;
import com.hy.micro.service.dingding.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @Author: Eddie.Wei   
 * @Date:   2018-10-17 
 * @github: https://github.com/weixuan2008
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private BuyerService buyerService;
	
	/**
	 * 
	 * The api to create order.
	 * @param orderForm
	 * @param bindingResult
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@PostMapping("/create")
	public ResultVO<Object> create(@Valid OrderForm orderForm,
			BindingResult bindingResult) throws IllegalAccessException, InvocationTargetException{
		if(bindingResult.hasErrors()) {
			log.error("[Create Order] incorrect parameters, orderForm = {}", orderForm);
			
			throw new BloomingException(ExceptionCode.PARAM_INCORRECT,
					bindingResult.getFieldError().getDefaultMessage());
		}
		
		OrderDTO orderDTO = ConvertOrderForm2OrderDTO.convert(orderForm);
		if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
			log.error("[Create Order] shopping cart cannot be empty.");
			
			throw new BloomingException(ExceptionCode.ORDER_CART_EMPTY);
		}
		
		OrderDTO createResult = orderService.create(orderDTO);
		
		Map<String, String> map = new HashMap<>();
		map.put("orderId", createResult.getOrderId());
		
		return ResultUtils.success(map);
	}
	
	/**
	 * The api for order list.
	 * @param buyerId
	 * @param page
	 * @param size
	 * @return
	 * @throws
	 */
	@GetMapping("/list")
	public ResultVO<Object> list(@RequestParam("buyerId") String buyerId,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size){
		
		if(StringUtils.isEmpty(buyerId)) {
			log.error("[Query Order] buyerId cannot be empty.");
			throw new BloomingException(ExceptionCode.PARAM_INCORRECT);
		}
		
		PageRequest request = PageRequest.of(page, size);
		Page<OrderDTO> orderDTOPage = orderService.findAllByBuyerId(buyerId, request);
		
		return ResultUtils.success(orderDTOPage.getContent());
	}
	
	
	/** 
	 * The api for order detail.
	 * @param buyerId
	 * @param orderId
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException
	 */
	@GetMapping("/detail")
	public ResultVO<Object> detail(@RequestParam("buyerId") String buyerId,
			@RequestParam("orderId") String orderId) throws IllegalAccessException, InvocationTargetException{
		
		OrderDTO orderDTO = buyerService.findOrderOne(buyerId, orderId);
		return ResultUtils.success(orderDTO);
	}
	
	/**
	 * The api for cancel order.
	 * @param buyerId
	 * @param orderId
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException
	 */
	@PostMapping("/cancel")
	public ResultVO<Object> cancel(@RequestParam("buyerId") String buyerId,
			@RequestParam("orderId") String orderId) throws IllegalAccessException, InvocationTargetException{
		buyerService.cancelOrder(buyerId, orderId);
		
		return ResultUtils.success();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
