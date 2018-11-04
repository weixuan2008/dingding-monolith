package com.hy.micro.service.dingding.service.impl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.hy.micro.service.dingding.entity.Vendor;
import com.hy.micro.service.dingding.service.VendorService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VendorServiceImplTest {

	@Autowired
	private VendorService inventoryVendorService;

	private final static String VENDOR_ID1 = "100100010109999";
	private final static String VENDOR_ID2 = "100100010108888";

	@Test
	public void saveTest1() {
		Vendor inventoryVendor = new Vendor();

		inventoryVendor.setVendorId(VENDOR_ID1);
		inventoryVendor.setVendorName("levono");
		inventoryVendor.setPhoneNumber("13521218888");
		inventoryVendor.setVendorAddress("HaiDian suzhoujie builder18");
		inventoryVendor.setVendorCity("Beijing");
		inventoryVendor.setVendorCountry("China");
		inventoryVendor.setVendorState("Beijing");
		inventoryVendor.setVendorZipcode("100000");
		inventoryVendor.setVendorLevel(1);

		Vendor result = inventoryVendorService.save(inventoryVendor);
		Assert.assertEquals(VENDOR_ID1, result.getVendorId());
	}

	@Test
	public void saveTest2() {
		Vendor inventoryVendor = new Vendor();

		inventoryVendor.setVendorId(VENDOR_ID2);
		inventoryVendor.setVendorName("levono");
		inventoryVendor.setPhoneNumber("13521218888");
		inventoryVendor.setVendorAddress("HaiDian suzhoujie builder18");
		inventoryVendor.setVendorCity("Beijing");
		inventoryVendor.setVendorCountry("China");
		inventoryVendor.setVendorState("Beijing");
		inventoryVendor.setVendorZipcode("100000");
		inventoryVendor.setVendorLevel(1);

		Vendor result = inventoryVendorService.save(inventoryVendor);
		Assert.assertEquals(VENDOR_ID2, result.getVendorId());
	}

	@Test
	public void getByVendorIdTest() {
		Vendor result = inventoryVendorService.findByVendorId(VENDOR_ID1);
		Assert.assertEquals(VENDOR_ID1, result.getVendorId());
	}

	@Test
	public void getAllVendorTest() {
		List<Vendor> result = inventoryVendorService.findAllVendors();
		Assert.assertNotEquals(0, result.size());
	}
}
