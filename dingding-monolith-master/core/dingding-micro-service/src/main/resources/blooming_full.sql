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

/*Data for the table `inventory_master` */

insert  into `inventory_master`(`product_id`,`product_category`,`vendor_id`,`base_price`,`product_sku`,`create_time`,`update_time`) values ('200595696666','Children\'s Books','100100010108888','25.00',2000,'2018-10-25 15:52:28','2018-11-01 19:27:26'),('200595698888','Children\'s Books','100100010109999','25.00',2000,'2018-10-25 15:52:28','2018-10-25 15:52:28'),('200595699999','Children\'s Books','100100010107777','30.00',300,'2018-11-01 19:32:06','2018-11-01 19:32:32');

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

/*Data for the table `inventory_vendor` */

insert  into `inventory_vendor`(`vendor_id`,`vendor_name`,`vendor_address`,`phone_number`,`vendor_city`,`vendor_state`,`vendor_country`,`vendor_zipcode`,`vendor_level`,`create_time`,`update_time`) values ('100100010107777','IBM','ShangDi, software park','15231663172','BeiJing','BeiJing','China','100000',5,'2018-10-29 22:40:40','2018-11-01 19:26:58'),('100100010108888','levono','HaiDian suzhoujie builder18','13521216666','Beijing','Beijing','China','100000',2,'2018-10-25 15:46:35','2018-11-01 19:26:34'),('100100010109999','levono','HaiDian suzhoujie builder18','13521218888','Beijing','Beijing','China','100000',9,'2018-10-25 15:46:34','2018-10-29 22:39:28');

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

/*Data for the table `order_detail` */

insert  into `order_detail`(`detail_id`,`order_id`,`product_id`,`product_price`,`product_quantity`,`product_icon`,`create_time`,`update_time`) values ('499258418910636666','20181009150138804266666','200595696666','25.50',2,'http://resource.hy.com/static/pics/books/children/strange-old-man.jpg','2018-11-01 19:39:31','2018-11-01 20:33:19'),('507639632398819328','20181101193526873579958','200595698888','25.50',3,'http://resource.hy.com/static/pics/books/children/strange-old-man.jpg','2018-11-01 19:38:40','2018-11-01 20:33:21'),('507639632457539584','20181009150138804266666','200595683826','25.50',5,'http://resource.hy.com/static/pics/books/children/strange-old-man.jpg','2018-11-01 19:38:40','2018-11-01 20:33:24'),('507687327833846688','20181101193526908918484','200595683826','25.60',3,'http://resource.hy.com/static/pics/books/children/strange-old-man.jpg','2018-11-01 20:58:44','2018-11-01 20:58:50');

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

/*Data for the table `order_master` */

insert  into `order_master`(`order_id`,`buyer_id`,`order_amount`,`order_status`,`pay_status`,`create_time`,`update_time`) values ('20181009150138804266666','360428197006156666','178.50',0,0,'2018-11-01 19:37:39','2018-11-01 20:55:22'),('20181101193526873579958','350302199205203355','76.50',0,0,'2018-11-01 19:35:27','2018-11-01 20:56:09'),('20181101193526908918484','370500197403047173','76.80',0,0,'2018-11-01 19:35:27','2018-11-01 20:59:11');

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

/*Data for the table `product_category` */

insert  into `product_category`(`category_id`,`category_name`,`category_type`,`create_time`,`update_time`) values (1,'Teen & Young Adult',10,'2018-10-29 13:42:43','2018-10-29 13:42:43'),(2,'Teen & Young Adult Literature & Fiction',20,'2018-10-29 13:42:53','2018-10-29 13:42:53'),(3,'Children\'s Books',30,'2018-10-29 13:43:02','2018-10-29 13:43:02'),(4,'Contemporary Literature & Fiction',40,'2018-10-29 13:43:11','2018-10-29 13:43:11'),(5,'Arts & Photography',50,'2018-10-29 13:43:22','2018-10-29 13:43:22'),(6,'Biographies & Memoirs',60,'2018-10-29 13:43:33','2018-10-29 13:43:33'),(7,'Business & Money',70,'2018-10-29 13:43:44','2018-10-29 13:43:44'),(8,'Calendars',80,'2018-10-29 13:43:54','2018-10-29 13:43:54'),(9,'Christian Books & Bibles',90,'2018-10-29 13:44:06','2018-10-29 13:44:06'),(10,'Comics & Graphic Novels',100,'2018-10-29 13:44:16','2018-10-29 13:44:16'),(11,'Computers & Technology',110,'2018-10-29 13:44:25','2018-10-29 13:44:25'),(12,'Cookbooks, Food & Wine',120,'2018-10-29 13:44:36','2018-10-29 13:44:36'),(13,'Crafts, Hobbies & Home',130,'2018-10-29 13:44:45','2018-10-29 13:44:45'),(14,'Education & Teaching',140,'2018-10-29 13:44:55','2018-10-29 13:44:55'),(15,'Engineering & Transportation',150,'2018-10-29 13:45:05','2018-10-29 13:45:05'),(16,'Health, Fitness & Dieting',160,'2018-10-29 13:45:13','2018-10-29 13:45:13'),(17,'History',170,'2018-10-29 13:45:23','2018-10-29 13:45:23'),(18,'Humor & Entertainment',180,'2018-10-29 13:45:32','2018-10-29 13:45:32'),(19,'Lesbian, Gay, Bisexual & Transgender Books',190,'2018-10-29 13:45:42','2018-10-29 13:45:42'),(20,'Literature & Fiction',200,'2018-10-29 13:45:50','2018-10-29 13:45:50'),(21,'Medical Books',210,'2018-10-29 13:46:02','2018-10-29 13:46:02'),(22,'Mystery, Thriller & Suspense',220,'2018-10-29 13:46:10','2018-10-29 13:46:10'),(23,'Parenting & Relationships',230,'2018-10-29 13:46:19','2018-10-29 13:46:19'),(24,'Politics & Social Sciences',240,'2018-10-29 13:46:28','2018-10-29 13:46:28'),(25,'Reference',250,'2018-10-29 13:46:38','2018-10-29 13:46:38'),(26,'Religion & Spirituality',260,'2018-10-29 13:46:47','2018-10-29 13:46:47'),(27,'Romance',270,'2018-10-29 13:46:56','2018-10-29 13:46:56'),(28,'Science & Math',280,'2018-10-29 13:47:06','2018-10-29 13:47:06'),(29,'Science Fiction & Fantasy',290,'2018-10-29 13:47:15','2018-10-29 13:47:15'),(30,'Self-Help',300,'2018-10-29 13:47:24','2018-10-29 13:47:24'),(31,'Sports & Outdoors',310,'2018-10-29 13:47:33','2018-10-29 13:47:33'),(32,'Test Preparation',320,'2018-10-29 13:47:43','2018-10-29 13:47:43'),(33,'Travel',330,'2018-10-29 13:47:54','2018-10-29 13:47:54');

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

/*Data for the table `product_info` */

insert  into `product_info`(`product_id`,`product_name`,`product_price`,`product_stock`,`product_desc`,`product_icon`,`product_status`,`category_type`,`create_time`,`update_time`) values ('200595696666','Giraffes Can\'t Dance','28.00',500,'Giraffes Can\'t Dance','/images/books/51qvh4MALwL._AC_US218_.jpg',0,30,'2018-10-29 14:19:10','2018-11-01 19:29:24'),('200595698888','I Need a New Butt!','45.00',200,'I Need a New Butt!','/images/books/51w+CMqd+rL._AC_US218_.jpg',0,30,'2018-10-29 14:17:11','2018-11-01 19:29:30'),('200595699999','The Wonderful Things You Will Be','70.00',100,'The Wonderful Things You Will Be','/images/books/51gEY86ijML._AC_US218_.jpg',0,30,'2018-10-29 14:16:23','2018-11-01 19:29:51'),('201873423303','The Gift That I Can Give','88.00',500,'The Gift That I Can Give','/images/books/51QhR4-ms9L._AC_US218_.jpg',0,30,'2018-10-29 14:20:22','2018-10-29 16:02:17'),('400928942197','The Existential Giraffe (Animalosophy Book 1)','30.00',200,'The Existential Giraffe (Animalosophy Book 1)','/images/books/The Existential_Giraffe_Animalosophy_Book 1.jpg',1,30,'2018-10-29 14:02:26','2018-10-29 14:09:22'),('401465007027','Klutz LEGO Chain Reactions Craft Kit','105.00',50,'Klutz LEGO Chain Reactions Craft Kit','/images/books/61aUY28XbmL._AC_US218_.jpg',1,30,'2018-10-29 14:17:52','2018-11-01 19:28:20'),('401861234588','Fatemarked (The Fatemarked Epic Book 1)','28.00',200,'Fatemarked (The Fatemarked Epic Book 1)','/images/books/51n3nHrFE2L._AC_US218_.jpg',1,30,'2018-10-29 14:07:18','2018-11-01 19:28:21'),('501361145704','The Meltdown (Diary of a Wimpy Kid Book 13)','56.00',100,'The Meltdown (Diary of a Wimpy Kid Book 13)','/images/books/512EFepj8GL._AC_US218_.jpg',1,30,'2018-10-29 14:11:37','2018-11-01 19:28:19'),('601146626546','Dog Man','41.00',100,'Dog Man: Lord of the Fleas','/images/books/61SY1Up5T9L._AC_US218_.jpg',1,30,'2018-10-29 14:15:02','2018-11-01 19:28:18'),('701224814471','Goodnight Moon','49.00',200,'Goodnight Moon','/images/books/51+mV1XUUQL._AC_US218_.jpg',1,30,'2018-10-29 14:19:52','2018-11-01 19:28:17'),('701664725169','The Wonky Donkey','49.00',200,'The Wonky Donkey','/images/books/51p5GTPnfOL._AC_US218_.jpg',1,30,'2018-10-29 14:10:59','2018-11-01 19:28:17'),('701700507822','First 100 Words','21.00',500,'First 100 Words','/images/books/51AvKQWCl+L._AC_US218_.jpg',1,30,'2018-10-29 14:18:25','2018-11-01 19:28:16'),('802026858720','Room on the Broom','35.00',200,'Room on the Broom','/images/books/61717NOl-IL._AC_US218_.jpg',1,30,'2018-10-29 14:15:50','2018-11-01 19:28:15');

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

/*Data for the table `user_info` */

insert  into `user_info`(`user_id`,`user_name`,`password`,`gender`,`phone`,`country`,`province`,`city`,`address`,`email`,`open_id`,`user_role`,`create_time`,`update_time`) values ('350302199205203355','Eddie','e10adc3949ba59abbe56e057f20f883e',0,'15836525206','Chine','Beijing','Beijing','HePing Street District No.12','eddie.wei@hy.com','t549ba59abbe56','3','2018-11-01 20:20:37','2018-11-01 20:23:27'),('360428197006156666','Eddie','e10adc3949ba59abbe56e057f20f883e',0,'18812612095','China','Beijing','Beijing','HePing Street District No.12','eddie.wei@hy.com','t549ba59abbe56','3','2018-11-01 20:20:37','2018-11-01 20:20:37'),('370500197403047173','Eddie','e10adc3949ba59abbe56e057f20f883e',0,'13245489152','Chine','Beijing','Beijing','HePing Street District No.12','eddie.wei@hy.com','t549ba59abbe56','3','2018-11-01 20:20:37','2018-11-01 20:23:29'),('42011619680128865X','Eddie','e10adc3949ba59abbe56e057f20f883e',0,'13517681146','Chine','Beijing','Beijing','HePing Street District No.12','eddie.wei@hy.com','t549ba59abbe56','3','2018-11-01 20:20:37','2018-11-01 20:20:37');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
