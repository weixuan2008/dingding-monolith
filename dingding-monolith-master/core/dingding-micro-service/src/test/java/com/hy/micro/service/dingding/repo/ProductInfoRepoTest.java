package com.hy.micro.service.dingding.repo;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hy.micro.service.dingding.DingdingServiceTest;
import com.hy.micro.service.dingding.entity.ProductInfo;
import com.hy.micro.service.dingding.enums.ProductStatus;
import com.hy.micro.service.dingding.repo.ProductInfoRepo;
import com.hy.micro.service.dingding.utils.ProductIdSimpleUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * The unit test for ProductInfoRepository
 * 
 * @Author: Eddie.Wei
 * @Date: 2018-10-03
 * @github: https://github.com/weixuan2008
 */

@Slf4j
@Component
public class ProductInfoRepoTest extends DingdingServiceTest{
	@Autowired
	private ProductInfoRepo productInfoRepository;

	@Test
	public void saveOneTest() {
		ProductInfo productInfo = new ProductInfo();

		//productInfo.setProductId("200595696666");
		productInfo.setProductId("200595698888");
		productInfo.setProductName("怪老头儿");
		productInfo.setProductDesc("好看的儿童读物");
		productInfo.setProductIcon("/images/Strange_old_man.jpg");
		//productInfo.setProductIcon("http://resource.hy.com/static/pics/books/children/strange-old-man.jpg");
		productInfo.setProductPrice(new BigDecimal(3.5));
		productInfo.setProductStock(100);
		productInfo.setCategoryType(100);
		productInfo.setProductStatus(ProductStatus.PRODUCT_ONLINE);

		ProductInfo result = productInfoRepository.save(productInfo);

		log.info(result.toString());
		Assert.assertNotEquals(null, result);
	}

	@Test
	public void saveMultiTest() {
		for (int i = 0; i < 2; i++) {
			ProductInfo productInfo = new ProductInfo();

			productInfo.setProductId(ProductIdSimpleUtils.getProductIdByUUID());
			productInfo.setProductName("怪老头儿");
			productInfo.setProductDesc("好看的儿童读物");
			//productInfo.setProductIcon("http://resource.hy.com/static/pics/books/children/strange-old-man.jpg");
			productInfo.setProductIcon("/images/Strange_old_man.jpg");
			productInfo.setProductPrice(new BigDecimal(3.5));
			productInfo.setProductStock(100);
			productInfo.setCategoryType(200);
			productInfo.setProductStatus(ProductStatus.PRODUCT_NEW);

			ProductInfo result = productInfoRepository.save(productInfo);

			log.info(result.toString());
			Assert.assertNotEquals(null, result);
		}
	}

	@Test
	public void findByProductStatus() {
		List<ProductInfo> prductInfos = productInfoRepository.findByProductStatus(ProductStatus.PRODUCT_NEW);

		for (ProductInfo productInfo : prductInfos) {
			log.info(productInfo.toString());
		}

		Assert.assertNotEquals(0, prductInfos.size());
	}

}
