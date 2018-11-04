package com.hy.micro.service.dingding.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hy.micro.service.dingding.dto.CartDTO;
import com.hy.micro.service.dingding.entity.ProductInfo;
import com.hy.micro.service.dingding.enums.ExceptionCode;
import com.hy.micro.service.dingding.enums.ProductStatus;
import com.hy.micro.service.dingding.exception.BloomingException;
import com.hy.micro.service.dingding.repo.ProductInfoRepo;
import com.hy.micro.service.dingding.service.ProductService;

/**
 * The implementation for product service.
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-12
 * @github: https://github.com/weixuan2008
 */

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductInfoRepo productInfoRepository;

	@Override
	public ProductInfo findByProductId(String productId) {
		return productInfoRepository.findByProductId(productId);
	}

	@Override
	public List<ProductInfo> findAllOnline() {
		return productInfoRepository.findByProductStatus(ProductStatus.PRODUCT_ONLINE);
	}

	@Override
	public Page<ProductInfo> findAll(Pageable pageable) {
		return productInfoRepository.findAll(pageable);
	}

	@Override
	public ProductInfo save(ProductInfo productInfo) {
		return productInfoRepository.save(productInfo);
	}

	@Override
	@Transactional
	public void decreaseStock(List<CartDTO> cartDTOList) {
		for (CartDTO cartDTO : cartDTOList) {
			ProductInfo productInfo = productInfoRepository.findById(cartDTO.getProductId()).get();
			if (productInfo == null) {
				throw new BloomingException(ExceptionCode.PRODUCT_NOT_FOUND);
			}

			Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
			if (result < 0) {
				throw new BloomingException(ExceptionCode.PRODUCT_STOCK_ERROR);
			}

			productInfo.setProductStock(result);

			productInfoRepository.save(productInfo);
		}
	}

	@Override
	@Transactional
	public void increaseStock(List<CartDTO> cartDTOList) {
		for (CartDTO cartDTO : cartDTOList) {
			ProductInfo productInfo = productInfoRepository.findById(cartDTO.getProductId()).get();
			if (productInfo == null) {
				throw new BloomingException(ExceptionCode.PRODUCT_NOT_FOUND);
			}

			Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
			productInfo.setProductStock(result);

			productInfoRepository.save(productInfo);
		}

	}

	@Override
	@Transactional
	public ProductInfo offline(String productId) {
		ProductInfo productInfo = productInfoRepository.findById(productId).get();
		if (productInfo == null) {
			throw new BloomingException(ExceptionCode.PRODUCT_NOT_FOUND);
		}

		if (productInfo.getProductStatus() == ProductStatus.PRODUCT_OFFLINE) {
			throw new BloomingException(ExceptionCode.PRODUCT_OFFLINE_SALE_FAIL);
		}

		productInfo.setProductStatus(ProductStatus.PRODUCT_OFFLINE);
		return productInfoRepository.save(productInfo);
	}

	@Override
	@Transactional
	public ProductInfo online(String productId) {
		ProductInfo productInfo = productInfoRepository.findById(productId).get();
		if (productInfo == null) {
			throw new BloomingException(ExceptionCode.PRODUCT_NOT_FOUND);
		}

		/*if (productInfo.getProductStatus() == ProductStatus.PRODUCT_ONLINE) {
			throw new BloomingException(ExceptionCode.PRODUCT_ONLINE_SALE_FAIL);
		}*/

		productInfo.setProductStatus(ProductStatus.PRODUCT_ONLINE);

		return productInfoRepository.save(productInfo);
	}

}
