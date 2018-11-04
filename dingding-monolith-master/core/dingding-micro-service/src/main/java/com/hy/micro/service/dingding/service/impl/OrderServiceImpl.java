package com.hy.micro.service.dingding.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hy.micro.service.dingding.dto.CartDTO;
import com.hy.micro.service.dingding.dto.OrderDTO;
import com.hy.micro.service.dingding.entity.OrderDetail;
import com.hy.micro.service.dingding.entity.OrderMaster;
import com.hy.micro.service.dingding.entity.ProductInfo;
import com.hy.micro.service.dingding.entity.UserInfo;
import com.hy.micro.service.dingding.enums.ExceptionCode;
import com.hy.micro.service.dingding.enums.OrderStatus;
import com.hy.micro.service.dingding.enums.PayStatus;
import com.hy.micro.service.dingding.exception.BloomingException;
import com.hy.micro.service.dingding.repo.OrderDetailRepo;
import com.hy.micro.service.dingding.repo.OrderMasterRepo;
import com.hy.micro.service.dingding.service.OrderService;
import com.hy.micro.service.dingding.service.PayService;
import com.hy.micro.service.dingding.service.ProductService;
import com.hy.micro.service.dingding.service.PushMessageService;
import com.hy.micro.service.dingding.service.UserInfoService;
import com.hy.micro.service.dingding.service.WebSocketService;
import com.hy.micro.service.dingding.utils.ConvertOrderMaster2OrderDTO;
import com.hy.micro.service.dingding.utils.OrderIdSimpleUtils;
import com.hy.micro.service.dingding.utils.OrderIdUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-07
 * @github: https://github.com/weixuan2008
 */

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
	@Autowired
	private ProductService productService;

	@Autowired
	private OrderDetailRepo orderDetailRepository;

	@Autowired
	private OrderMasterRepo orderMasterRepository;

	@Autowired
	private PayService payService;

	@Autowired
	private PushMessageService pushMessageService;

	@Autowired
	private WebSocketService webSocketService;

	@Autowired
	private OrderIdUtils OrderIdUtils;

	@Autowired
	private UserInfoService userInfoService;
	
	@Override
	@Transactional
	public OrderDTO create(OrderDTO orderDTO) throws IllegalAccessException, InvocationTargetException {
		String orderId = OrderIdSimpleUtils.generateOrderId();
		BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

		// 1. query the quantity and price of product
		for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
			ProductInfo productInfo = productService.findByProductId(orderDetail.getProductId());
			if (productInfo == null) {
				throw new BloomingException(ExceptionCode.PRODUCT_NOT_FOUND);
			}

			// 2. calculate the tatal amount of this order
			orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

			// put order detail into stock
			orderDetail.setDetailId(Long.toString(OrderIdUtils.nextId()));
			orderDetail.setOrderId(orderId);

			BeanUtils.copyProperties(productInfo, orderDetail);
			orderDetailRepository.save(orderDetail);
		}

		// 3. write the order database, include master and detail.
		OrderMaster orderMaster = new OrderMaster();
		orderDTO.setOrderId(orderId);
		BeanUtils.copyProperties(orderDTO, orderMaster);
		orderMaster.setOrderAmount(orderAmount);
		orderMaster.setOrderStatus(OrderStatus.ORDER_NEW);
		orderMaster.setPayStatus(PayStatus.PAYMENT_NEW);
		orderMasterRepository.save(orderMaster);

		// 4. decrease the product quantity from the inventory.
		List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
				.map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
		productService.decreaseStock(cartDTOList);

		// 5. send message by websocket
		webSocketService.sendMessage(orderDTO.getOrderId());

		return orderDTO;
	}

	@Override
	@Transactional
	public OrderDTO findByOrderId(String orderId) throws IllegalAccessException, InvocationTargetException {
		OrderMaster orderMaster = orderMasterRepository.findByOrderId(orderId);
		if (orderMaster == null) {
			throw new BloomingException(ExceptionCode.ORDER_NOT_FOUND);
		}

		List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
		if (CollectionUtils.isEmpty(orderDetailList)) {
			throw new BloomingException(ExceptionCode.ORDER_DETAIL_NOT_FOUND);
		}

		OrderDTO orderDTO = new OrderDTO();
		BeanUtils.copyProperties(orderMaster, orderDTO);
		orderDTO.setOrderDetailList(orderDetailList);
		
		UserInfo userInfo = userInfoService.findByUserId(orderDTO.getBuyerId());
		orderDTO.setBuyerName(userInfo.getUsername());
		orderDTO.setBuyerAddress(userInfo.getAddress());
		orderDTO.setBuyerOpenid(userInfo.getOpenId());
		orderDTO.setBuyerPhone(userInfo.getPhone());
		
		return orderDTO;
	}

	@Override
	public Page<OrderDTO> findAllByBuyerId(String buyerId, Pageable pageable) {
		Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerId(buyerId, pageable);
		List<OrderDTO> orderDTOList = ConvertOrderMaster2OrderDTO.convert(orderMasterPage.getContent());

		return new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());
	}

	@Override
	@Transactional
	public OrderDTO cancel(OrderDTO orderDTO) throws IllegalAccessException, InvocationTargetException {
		OrderMaster orderMaster = new OrderMaster();

		// 1. checking the status of order
		if (!orderDTO.getOrderStatus().equals(OrderStatus.ORDER_NEW)) {
			log.error("The status of order is incorrect, orderId={}, orderStatus={}", orderDTO.getOrderId(),
					orderDTO.getOrderStatus());
			throw new BloomingException(ExceptionCode.ORDER_STATUS_ERROR);
		}

		// 2. Update the status of order
		orderDTO.setOrderStatus(OrderStatus.ORDER_CANCEL);
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster updateResult = orderMasterRepository.save(orderMaster);

		if (updateResult == null) {
			log.error("Failed to update the order when cancelling it, orderMaster={}", orderMaster);
			throw new BloomingException(ExceptionCode.ORDER_UPDATE_FAIL);
		}

		// 3. return the quantity of inventory
		if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
			log.error("Not found oerder detail when cancelling order, orderDTO={}", orderDTO);
			throw new BloomingException(ExceptionCode.ORDER_DETAIL_EMPTY);
		}
		List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
				.map(e -> new CartDTO(e.getProductId(), e.getProductQuantity())).collect(Collectors.toList());
		productService.increaseStock(cartDTOList);

		// 4. need refund if paid.
		if (orderDTO.getPayStatus().equals(PayStatus.PAYMENT_COMPLETED)) {
			payService.refund(orderDTO);
		}

		return orderDTO;
	}

	@Override
	public OrderDTO finish(OrderDTO orderDTO) throws IllegalAccessException, InvocationTargetException {
		// 1. checking the status of order
		if (!orderDTO.getOrderStatus().equals(OrderStatus.ORDER_NEW)) {
			log.error("The status of order is incorrect, orderId={}, orderStatus={}", orderDTO.getOrderId(),
					orderDTO.getOrderStatus());
			throw new BloomingException(ExceptionCode.ORDER_STATUS_ERROR);
		}

		// 2. updating the status of order
		orderDTO.setOrderStatus(OrderStatus.ORDER_FINISH);
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster updateResult = orderMasterRepository.save(orderMaster);

		if (updateResult == null) {
			log.error("Failed to update the status of order when finishing order, orderMaster={}", orderMaster);
			throw new BloomingException(ExceptionCode.ORDER_UPDATE_FAIL);
		}

		// 3. push the message
		pushMessageService.notifyWhenOrderStatusChanged(orderDTO);

		return orderDTO;
	}

	@Override
	@Transactional
	public OrderDTO pay(OrderDTO orderDTO) throws IllegalAccessException, InvocationTargetException {
		// 1. checking the status of order
		if (!orderDTO.getOrderStatus().equals(OrderStatus.ORDER_NEW)) {
			log.error("【订单支付完成】订单状态不正确, orderId={}, orderStatus={}", orderDTO.getOrderId(), orderDTO.getOrderStatus());
			throw new BloomingException(ExceptionCode.ORDER_STATUS_ERROR);
		}

		// 2. checking the status of pay
		if (!orderDTO.getPayStatus().equals(PayStatus.PAYMENT_NEW)) {
			log.error("【订单支付完成】订单支付状态不正确, orderDTO={}", orderDTO);
			throw new BloomingException(ExceptionCode.ORDER_PAY_STATUS_ERROR);
		}

		// 3. update the status of pay
		orderDTO.setPayStatus(PayStatus.PAYMENT_COMPLETED);
		OrderMaster orderMaster = new OrderMaster();
		BeanUtils.copyProperties(orderDTO, orderMaster);
		OrderMaster updateResult = orderMasterRepository.save(orderMaster);

		if (updateResult == null) {
			log.error("【订单支付完成】更新失败, orderMaster={}", orderMaster);
			throw new BloomingException(ExceptionCode.ORDER_UPDATE_FAIL);
		}

		return orderDTO;
	}

	@Override
	@Transactional
	public Page<OrderDTO> findAll(Pageable pageable) {
		Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);

		List<OrderDTO> orderDTOs = ConvertOrderMaster2OrderDTO.convert(orderMasterPage.getContent());
		for(OrderDTO orderDTO : orderDTOs) {
			UserInfo userInfo = userInfoService.findByUserId(orderDTO.getBuyerId());
			
			orderDTO.setBuyerName(userInfo.getUsername());
			orderDTO.setBuyerAddress(userInfo.getAddress());
			orderDTO.setBuyerOpenid(userInfo.getOpenId());
			orderDTO.setBuyerPhone(userInfo.getPhone());
		}
		
		return new PageImpl<>(orderDTOs, pageable, orderMasterPage.getTotalElements());
	}

}
