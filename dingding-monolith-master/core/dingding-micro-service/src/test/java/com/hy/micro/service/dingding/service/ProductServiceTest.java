package com.hy.micro.service.dingding.service;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import com.hy.micro.service.dingding.entity.ProductInfo;
import com.hy.micro.service.dingding.enums.ProductStatus;
import com.hy.micro.service.dingding.service.impl.ProductServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductServiceTest {
	private final static String PRODUCT_ID_1 = "200595696666";
	private final static String PRODUCT_ID_2 = "200595698888";

	
	@Autowired
	private ProductServiceImpl productService;

	@Test
	public void findOne() {
		ProductInfo productInfo = productService.findByProductId(PRODUCT_ID_1);
		Assert.assertEquals(PRODUCT_ID_1, productInfo.getProductId());
	}

	@Test
	public void findOnlineAll() {
		List<ProductInfo> productInfos = productService.findAllOnline();
		Assert.assertNotEquals(0, productInfos.size());
	}

	@Test
	public void findAll() {
		PageRequest requesst = PageRequest.of(0, 2);
		Page<ProductInfo> productInfoPage = productService.findAll(requesst);

		log.info("The total amount of elements is {}", productInfoPage.getTotalElements());

		Assert.assertNotEquals(0, productInfoPage.getTotalElements());
	}

	@Test
	public void saveOneTest1() {
		ProductInfo productInfo = new ProductInfo();

		productInfo.setProductId(PRODUCT_ID_1);
		productInfo.setProductName("海底两万里");
		productInfo.setProductDesc("好看的儿童读物");
		productInfo.setProductIcon("http://resource.hy.com/static/pics/books/children/strange-old-man.jpg");
		productInfo.setProductPrice(new BigDecimal(12.5));
		productInfo.setProductStock(500);
		productInfo.setCategoryType(100);
		productInfo.setProductStatus(ProductStatus.PRODUCT_ONLINE);

		ProductInfo result = productService.save(productInfo);

		Assert.assertNotNull(result);
	}
	
	@Test
	public void saveOneTest2() {
		ProductInfo productInfo = new ProductInfo();

		productInfo.setProductId(PRODUCT_ID_2);
		productInfo.setProductName("安徒生童话");
		productInfo.setProductDesc("好看的儿童读物");
		productInfo.setProductIcon("http://resource.hy.com/static/pics/books/children/strange-old-man.jpg");
		productInfo.setProductPrice(new BigDecimal(12.5));
		productInfo.setProductStock(500);
		productInfo.setCategoryType(200);
		productInfo.setProductStatus(ProductStatus.PRODUCT_ONLINE);

		ProductInfo result = productService.save(productInfo);

		Assert.assertNotNull(result);
	}
	
	@Test
	public void onlineSale() {
		ProductInfo result = productService.online(PRODUCT_ID_1);
		Assert.assertEquals(ProductStatus.PRODUCT_ONLINE, result.getProductStatus());
	}

	@Test
	public void offlineSale() {
		ProductInfo result = productService.offline(PRODUCT_ID_1);
		Assert.assertEquals(ProductStatus.PRODUCT_OFFLINE, result.getProductStatus());
	}
}
