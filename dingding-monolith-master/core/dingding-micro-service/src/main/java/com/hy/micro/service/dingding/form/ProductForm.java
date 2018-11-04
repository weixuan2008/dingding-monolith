package com.hy.micro.service.dingding.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * The form data of front-end for product.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-08
 * @github: https://github.com/weixuan2008
 */

@Data
public class ProductForm {
	/** product id **/
	private String productId;

	/** product name **/
	@NotEmpty(message = "The product name cannot be empty")
	private String productName;

	/** product price **/
	private BigDecimal productPrice;

	/** product stock **/
	private Integer productStock;

	/** product description **/
	private String productDesc;

	/** product icon **/
	private String productIcon;

	/** product category **/
	private Integer categoryType;
}
