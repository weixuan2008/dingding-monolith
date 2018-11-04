package com.hy.micro.service.dingding.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hy.micro.service.dingding.dto.CartDTO;
import com.hy.micro.service.dingding.entity.ProductInfo;

/**
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-07
 * @github: https://github.com/weixuan2008
 */
public interface ProductService {
	ProductInfo findByProductId(String productId);

	/** query all online products **/
	List<ProductInfo> findAllOnline();

	Page<ProductInfo> findAll(Pageable pageable);

	ProductInfo save(ProductInfo productInfo);

	/** decrease the quantity from stock **/
	void decreaseStock(List<CartDTO> cartDTOList);

	/** increase the quantity to stock **/
	void increaseStock(List<CartDTO> cartDTOList);

	/** online the product **/
	ProductInfo offline(String productId);

	/** offline the product **/
	ProductInfo online(String productId);
}
