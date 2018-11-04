package com.hy.micro.service.dingding.enums;

import lombok.Getter;

/**
 * The status code of exception.
 * 
 * @Author: Eddie.Wei
 * @Date: 2017-09-30
 * @github: https://github.com/weixuan2008
 */

@Getter
public enum ExceptionCode {
	// @formatter:off
	// General exceptions
	PARAM_INCORRECT(1, "Incorrect Parameters"),
	NOT_CURRENT_USER(2, "Incorrect Current User"),
	WECHAT_MP_ERROR(3, "WeiChat Authorization Failure"),
	PAY_MONEY_NOT_EQUAL(4, "Inconsistent Pay Amount"),
	LOGIN_FAIL(5, "Login Failure"),
	LOGIN_SUCCESS(6, "Login Success"),
	LOGOUT_SUCCESS(7, "Logout Success"),
	
	// Order exceptions
	ORDER_NOT_FOUND(30, "Not Found Order"),
	ORDER_DETAIL_NOT_FOUND(31, "Not Found Order Detail"),
	ORDER_STATUS_ERROR(32, "Incorrect Order Status"),
	ORDER_STATUS_UPDATE_FAIL(33, "Update Order Status Failure"),
	ORDER_PAY_NOT_FOUND(34, "Not found Pay"),
	ORDER_PAY_STATUS_ERROR(35, "Incorrect Order Pay Status"),
	ORDER_PAY_STATUS_UPDATE_FAIL(36, "Update Order Pay Status Failure"),
	ORDER_CART_EMPTY(37, "Empty Cart"),
	ORDER_CANCEL_SUCCESS(38, "Cancel Order Success"),
	ORDER_FINISH_SUCCESS(39, "Complete Order Success"),
	ORDER_OWNER_NOT_MATCH(40, "Owner of order not match user id"),
	ORDER_UPDATE_FAIL(41, "Update the order failure"),
	ORDER_DETAIL_EMPTY(42, "Not found order detail"),
	
	// Product exceptions
	PRODUCT_ONLINE_SALE_FAIL(60, "Product Online Failure"),
	PRODUCT_OFFLINE_SALE_FAIL(61, "Product Offline Failure"),
	PRODUCT_OFFLINE_SALE_SUCCESS(62, "Product Offline Success"),
	PRODUCT_ON_SALE_SUCCESS(63, "Product Online Success"),
	PRODUCT_UPDATE_SUCCESS(64, "Update Product Success"),
	PRODUCT_NOT_FOUND(65, "Not Found Product"),
	PRODUCT_STOCK_ERROR(66, "Not Enough Inventory"),

	// Category exceptions
	CATEGORY_NOT_FOUND(90, "Not Found Category"),
	CATEGORY_UPDATE_SUCCESS(91, "Update Category Sucess"),
	CATEGORY_UPDATE_FAIL(92, "Update Category Faivlure"),
	
	
	// weichat exceptions
	WXPAY_NOTIFY_MONEY_VERIFY_ERROR(100, "Money Verify failure"),
	
	
	// @formatter:on
	;

	private Integer code;
	private String msg;

	/**
	 * Create a new instance of ExceptionCodes.
	 * 
	 * @param code
	 * @param msg
	 */
	private ExceptionCode(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}
}
