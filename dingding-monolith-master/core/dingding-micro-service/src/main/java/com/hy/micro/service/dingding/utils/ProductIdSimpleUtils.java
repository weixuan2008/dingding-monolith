package com.hy.micro.service.dingding.utils;

import java.util.Random;
import java.util.UUID;

public class ProductIdSimpleUtils {
	public static synchronized String getProductIdByUUID() {
		//int machineId = 1;//最大支持1-9个集群机器部署
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if(hashCodeV < 0) {//有可能是负数
			hashCodeV = - hashCodeV;
		}
		
		// 0 代表前面补充0 
		// 4 代表长度为4 
		// d 代表参数为正数型
		//return machineId + String.format("%011d", hashCodeV);
		Random random = new Random();
		return (random.nextInt(9)+1) + String.format("%011d", hashCodeV);
	}
}
