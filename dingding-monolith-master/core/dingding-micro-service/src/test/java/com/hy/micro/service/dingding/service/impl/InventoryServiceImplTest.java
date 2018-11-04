package com.hy.micro.service.dingding.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.hy.micro.service.dingding.entity.Inventory;
import com.hy.micro.service.dingding.service.InventoryService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InventoryServiceImplTest {

	@Autowired
	private InventoryService inventoryMasterService;

	private final static String PRODUCT_ID1 = "200595696666";
	private final static String PRODUCT_ID2 = "200595698888";
	private final static String PRODUCT_CATEGORY = "Children's Books";
	private final static String VENDOR_ID = "100100010109999";

	@Test
	public void saveTest1() {
		Inventory inventoryMaster = new Inventory();
		inventoryMaster.setProductId(PRODUCT_ID1);
		inventoryMaster.setBasePrice(new BigDecimal(25));
		inventoryMaster.setProductCategory(PRODUCT_CATEGORY);
		inventoryMaster.setSku(2000);
		inventoryMaster.setVendorId(VENDOR_ID);

		Inventory result = inventoryMasterService.save(inventoryMaster);
		Assert.assertEquals(PRODUCT_ID1, result.getProductId());
	}

	@Test
	public void saveTest2() {
		Inventory inventoryMaster = new Inventory();
		inventoryMaster.setProductId(PRODUCT_ID2);
		inventoryMaster.setBasePrice(new BigDecimal(25));
		inventoryMaster.setProductCategory(PRODUCT_CATEGORY);
		inventoryMaster.setSku(2000);
		inventoryMaster.setVendorId(VENDOR_ID);

		Inventory result = inventoryMasterService.save(inventoryMaster);
		Assert.assertEquals(PRODUCT_ID2, result.getProductId());
	}

	@Test
	public void findByProductIdTest() {
		Inventory result = inventoryMasterService.findByProductId(PRODUCT_ID1);
		Assert.assertEquals(PRODUCT_ID1, result.getProductId());
	}

	@Test
	public void findAll() {
		List<Inventory> result = inventoryMasterService.findAll();
		Assert.assertNotEquals(0, result.size());
	}
	
	@Test
	public void decreaseSkuTest() {
		
	}
	
	@Test
	public void increaseSkuTest() {
		
	}
}
