/*
SQLyog Enterprise v12.09 (64 bit)
MySQL - 5.7.21 : Database - blooming
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blooming` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `blooming`;

/*Table structure for table `inventory_master` */

DROP TABLE IF EXISTS `inventory_master`;

CREATE TABLE `inventory_master` (
  `product_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `product_category` varchar(32) COLLATE utf8_bin NOT NULL COMMENT 'product category',
  `vendor_id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT 'vendor id',
  `base_price` decimal(8,2) NOT NULL COMMENT 'base price',
  `product_sku` int(11) NOT NULL COMMENT 'product sku',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `inventory_vendor` */

DROP TABLE IF EXISTS `inventory_vendor`;

CREATE TABLE `inventory_vendor` (
  `vendor_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `vendor_name` varchar(30) COLLATE utf8_bin NOT NULL COMMENT 'vendor name',
  `vendor_address` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'vendor address',
  `phone_number` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT 'phone number',
  `vendor_city` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT 'vendor city',
  `vendor_state` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT 'vendor state',
  `vendor_country` varchar(30) COLLATE utf8_bin DEFAULT NULL COMMENT 'vendor country',
  `vendor_zipcode` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT 'vendor zipcode',
  `vendor_level` int(11) NOT NULL COMMENT 'vendor level',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`vendor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `order_detail` */

DROP TABLE IF EXISTS `order_detail`;

CREATE TABLE `order_detail` (
  `detail_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `order_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `product_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `product_price` decimal(8,2) NOT NULL COMMENT 'current price, unit is fen',
  `product_quantity` int(11) NOT NULL COMMENT 'quantity of products',
  `product_icon` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT 'icon of product',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`detail_id`),
  KEY `idx_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `order_master` */

DROP TABLE IF EXISTS `order_master`;

CREATE TABLE `order_master` (
  `order_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `buyer_id` varchar(32) COLLATE utf8_bin NOT NULL COMMENT 'buyer id',
  `order_amount` decimal(8,2) NOT NULL COMMENT 'total amount of order',
  `order_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT 'order status, default is 0 for new',
  `pay_status` tinyint(3) NOT NULL DEFAULT '0' COMMENT 'pay status, default is 0 for unpayed',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`order_id`),
  KEY `idx_buyer_id` (`buyer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `product_category` */

DROP TABLE IF EXISTS `product_category`;

CREATE TABLE `product_category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT 'category name',
  `category_type` int(11) NOT NULL COMMENT 'category number',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time for this category',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time for this category',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `product_info` */

DROP TABLE IF EXISTS `product_info`;

CREATE TABLE `product_info` (
  `product_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `product_name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT 'product name',
  `product_price` decimal(8,2) NOT NULL COMMENT 'price',
  `product_stock` int(11) NOT NULL COMMENT 'product stock',
  `product_desc` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'product description',
  `product_icon` varchar(512) COLLATE utf8_bin DEFAULT NULL COMMENT 'icon for product',
  `product_status` tinyint(3) DEFAULT '0' COMMENT 'product status, 0 is normal and 1 is down',
  `category_type` int(11) NOT NULL COMMENT 'category number',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Table structure for table `user_info` */

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `user_id` varchar(32) COLLATE utf8_bin NOT NULL,
  `user_name` varchar(32) COLLATE utf8_bin NOT NULL,
  `password` varchar(32) COLLATE utf8_bin NOT NULL,
  `gender` tinyint(1) NOT NULL,
  `phone` varchar(11) COLLATE utf8_bin NOT NULL,
  `country` varchar(32) COLLATE utf8_bin NOT NULL,
  `province` varchar(32) COLLATE utf8_bin NOT NULL,
  `city` varchar(32) COLLATE utf8_bin NOT NULL,
  `address` varchar(32) COLLATE utf8_bin NOT NULL,
  `email` varchar(32) COLLATE utf8_bin NOT NULL,
  `open_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT 'weixin openid',
  `user_role` varchar(15) COLLATE utf8_bin NOT NULL COMMENT 'user role',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'create time',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'update time',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
