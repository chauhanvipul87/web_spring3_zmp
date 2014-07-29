-- MySQL dump 10.13  Distrib 5.5.28, for Win32 (x86)
--
-- Host: localhost    Database: zmp
-- ------------------------------------------------------
-- Server version	5.5.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin_login`
--

DROP TABLE IF EXISTS `admin_login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin_login` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(80) NOT NULL DEFAULT '',
  `first_name` varchar(100) DEFAULT '',
  `last_name` varchar(100) DEFAULT NULL,
  `usertype_id` int(11) NOT NULL DEFAULT '0',
  `access_zones` varchar(20) DEFAULT NULL,
  `description` text,
  `modify_datetime` varchar(20) DEFAULT '',
  `modified_by` int(11) DEFAULT '0',
  `delete_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_login`
--

LOCK TABLES `admin_login` WRITE;
/*!40000 ALTER TABLE `admin_login` DISABLE KEYS */;
INSERT INTO `admin_login` VALUES (1,'admin','*4ACFE3202A5FF5CF467898FC58AAB1D615029441','admin','admin',0,'abc',NULL,NULL,0,0);
/*!40000 ALTER TABLE `admin_login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agent`
--

DROP TABLE IF EXISTS `agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent` (
  `agent_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  `mobile_no` varchar(20) DEFAULT NULL,
  `reference` varchar(20) DEFAULT NULL,
  `password` varchar(20) NOT NULL,
  `user_status` int(11) DEFAULT '0' COMMENT '0 for active and 1 for deactivated client.',
  `created_datetime` varchar(20) DEFAULT NULL,
  `modify_datetime` varchar(20) DEFAULT NULL,
  `modify_by` int(11) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT '0' COMMENT '0 for valid entry and 1 for invalid entry.',
  PRIMARY KEY (`agent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent`
--

LOCK TABLES `agent` WRITE;
/*!40000 ALTER TABLE `agent` DISABLE KEYS */;
INSERT INTO `agent` VALUES (1,'chauhan_vipul87@yahoo.com','9898745818','9898989898','friend','vipul',0,'2012-05-03 18:41:56',NULL,NULL,0),(2,'chauhan_vipul87@yahoo.com','9898745818','9898989898','friend','vipul',0,'2012-05-03 18:41:56',NULL,NULL,0),(3,'chauhan_vipul87@yahoo.com','9898745818','9898989898','friend','vipul',0,'2012-05-03 18:41:56',NULL,NULL,0),(4,'chauhan_vipul87@yahoo.com','9898745818','9898989898','friend','vipul',0,'2012-05-03 18:41:56',NULL,NULL,0),(5,'ppatel@cdl.uk.com','07429078771','','friend','patelat123',0,'2012-06-11 21:23:27',NULL,NULL,0);
/*!40000 ALTER TABLE `agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agent_address`
--

DROP TABLE IF EXISTS `agent_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent_address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `country_id` int(11) NOT NULL,
  `postcode _id` int(11) NOT NULL,
  `agent_id` int(11) NOT NULL,
  `address1` varchar(50) DEFAULT NULL,
  `address2` varchar(50) DEFAULT NULL,
  `address3` varchar(50) DEFAULT NULL,
  `town` varchar(20) DEFAULT NULL,
  `address_status` int(11) DEFAULT '0' COMMENT '0 for active and 1 for deactivated address',
  `created_datetime` varchar(20) DEFAULT NULL,
  `modify_datetime` varchar(20) DEFAULT NULL,
  `modify_by` int(11) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT '0' COMMENT '0 for valid entry and 1 for invalid entry',
  PRIMARY KEY (`address_id`),
  KEY `fk_country` (`country_id`),
  KEY `fk_postcode` (`postcode _id`),
  KEY `fk_agent` (`agent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent_address`
--

LOCK TABLES `agent_address` WRITE;
/*!40000 ALTER TABLE `agent_address` DISABLE KEYS */;
INSERT INTO `agent_address` VALUES (1,1,1,1,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `agent_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carriers`
--

DROP TABLE IF EXISTS `carriers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carriers` (
  `carrier_id` int(11) NOT NULL AUTO_INCREMENT,
  `carrier_name` varchar(50) DEFAULT NULL,
  `carrier_code` varchar(50) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `modify_by` int(11) DEFAULT NULL,
  `created_datetime` int(11) DEFAULT NULL,
  `modify_datetime` int(11) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`carrier_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carriers`
--

LOCK TABLES `carriers` WRITE;
/*!40000 ALTER TABLE `carriers` DISABLE KEYS */;
INSERT INTO `carriers` VALUES (1,'DHL','DHL',1,NULL,NULL,NULL,0),(2,'YODEL','YODEL',1,NULL,NULL,NULL,0),(3,'Royal Mail','Royal Mail',1,NULL,NULL,NULL,0),(4,'City Link','City Link',1,NULL,NULL,NULL,0),(5,'Parcel Force','Parcel Force',1,NULL,NULL,NULL,0),(6,'Harmish','Harmish',1,NULL,NULL,NULL,0),(7,'DPD','DPD',1,NULL,NULL,NULL,0),(8,'UPS','UPS',1,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `carriers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact_us`
--

DROP TABLE IF EXISTS `contact_us`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contact_us` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `message` varchar(400) DEFAULT NULL,
  `created_datetime` varchar(20) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact_us`
--

LOCK TABLES `contact_us` WRITE;
/*!40000 ALTER TABLE `contact_us` DISABLE KEYS */;
INSERT INTO `contact_us` VALUES (1,'Pukar','07429078771','ws@cdllogistics.com','Need Help sending parcel','2012-06-11 20:24:24',0);
/*!40000 ALTER TABLE `contact_us` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
  `country_id` int(11) NOT NULL AUTO_INCREMENT,
  `country_code` varchar(20) DEFAULT NULL,
  `country_name` varchar(20) DEFAULT NULL,
  `country_status` int(11) DEFAULT '0' COMMENT '0 for active and 1 for deactivated country.',
  `created_datatime` varchar(20) DEFAULT NULL,
  `modify_datetime` varchar(20) DEFAULT NULL,
  `modify_by` int(11) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT '0' COMMENT '0 for valid entry and 1 for invalid entry.',
  `created_by` int(11) DEFAULT NULL,
  `country_description` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'UK','UK - Mainland',0,NULL,NULL,NULL,0,NULL,'The United Kingdom of Great Britain and Northern Ireland (commonly known as the United Kingdom, the UK or Britain) is a sovereign state located off the north-western coast of continental Europe. The country includes the island of Great Britain, the north-eastern part of the island of Ireland and many smaller islands. Northern Ireland is the only part of the UK that shares a land border with another sovereign state-the Republic of Ireland. Apart from this land border the UK is surrounded by the Atlantic Ocean, the North Sea, the English Channel and the Irish Sea.'),(2,'UKO','UK - OffShore',0,NULL,NULL,NULL,0,NULL,'Canada is the second largest country in the world after Russia, covering almost 10 million square kilometres of land area. It is estimated that Canada has been inhabited by humans for around 26,000 years. It is now listed as the worlds 6th most highly developed country by the UN Human Development Index and is currently one of the world\'s top trading nations. Approximately 75% of Canada\'s workforce is employed in the tertiary sector of the economy (also known as the service industry) and it has a diverse, mixed economy, which relies heavily on foreign trade. The UK is actually one of the largest importers of Canadian goods, along with the USA and China. It has a plentiful supply of natural products to use and sell, such as oil, gold and gas, amongst many others. '),(3,'USA','USA',0,NULL,NULL,NULL,0,NULL,'The United Kingdom of Great Britain and Northern Ireland (commonly known as the United Kingdom, the UK or Britain) is a sovereign state located off the north-western coast of continental Europe. The country includes the island of Great Britain, the north-eastern part of the island of Ireland and many smaller islands. Northern Ireland is the only part of the UK that shares a land border with another sovereign state-the Republic of Ireland. Apart from this land border the UK is surrounded by the Atlantic Ocean, the North Sea, the English Channel and the Irish Sea.'),(4,'AF','Africa',0,NULL,NULL,NULL,0,NULL,'Canada is the second largest country in the world after Russia, covering almost 10 million square kilometres of land area. It is estimated that Canada has been inhabited by humans for around 26,000 years. It is now listed as the worlds 6th most highly developed country by the UN Human Development Index and is currently one of the world\'s top trading nations. Approximately 75% of Canada\'s workforce is employed in the tertiary sector of the economy (also known as the service industry) and it has a diverse, mixed economy, which relies heavily on foreign trade. The UK is actually one of the largest importers of Canadian goods, along with the USA and China. It has a plentiful supply of natural products to use and sell, such as oil, gold and gas, amongst many others. '),(5,'IN','India',0,NULL,NULL,NULL,0,NULL,'The United Kingdom of Great Britain and Northern Ireland (commonly known as the United Kingdom, the UK or Britain) is a sovereign state located off the north-western coast of continental Europe. The country includes the island of Great Britain, the north-eastern part of the island of Ireland and many smaller islands. Northern Ireland is the only part of the UK that shares a land border with another sovereign state-the Republic of Ireland. Apart from this land border the UK is surrounded by the Atlantic Ocean, the North Sea, the English Channel and the Irish Sea.'),(6,'CN','Canada',0,NULL,NULL,NULL,0,NULL,'Canada is the second largest country in the world after Russia, covering almost 10 million square kilometres of land area. It is estimated that Canada has been inhabited by humans for around 26,000 years. It is now listed as the worlds 6th most highly developed country by the UN Human Development Index and is currently one of the world\'s top trading nations. Approximately 75% of Canada\'s workforce is employed in the tertiary sector of the economy (also known as the service industry) and it has a diverse, mixed economy, which relies heavily on foreign trade. The UK is actually one of the largest importers of Canadian goods, along with the USA and China. It has a plentiful supply of natural products to use and sell, such as oil, gold and gas, amongst many others. '),(7,'AU','Australia',0,NULL,NULL,NULL,0,NULL,NULL),(8,'AR','Argentina',0,NULL,NULL,NULL,0,NULL,NULL),(9,'GAN','Ghana',0,NULL,NULL,NULL,0,NULL,NULL),(10,'MDV','Maldives',0,NULL,NULL,NULL,0,NULL,NULL),(11,'NPL','Nepal',0,NULL,NULL,NULL,0,NULL,NULL),(12,'NZL','New Zealand',0,NULL,NULL,NULL,0,NULL,NULL),(13,'NRW','Norway',0,NULL,NULL,NULL,0,NULL,NULL),(14,'SLVA','Slovakia',0,NULL,NULL,NULL,0,NULL,NULL);
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_address`
--

DROP TABLE IF EXISTS `delivery_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delivery_address` (
  `delivery_id` int(11) NOT NULL AUTO_INCREMENT,
  `country_id` int(11) DEFAULT NULL,
  `user_key` varchar(70) DEFAULT NULL,
  `d_name` varchar(200) DEFAULT NULL,
  `d_postcode` varchar(20) DEFAULT NULL,
  `d_addressLine1` varchar(400) DEFAULT NULL,
  `d_addressLine2` varchar(300) DEFAULT NULL,
  `d_addressLine3` varchar(300) DEFAULT NULL,
  `d_town` varchar(40) DEFAULT NULL,
  `d_city` varchar(40) DEFAULT NULL,
  `d_phone` varchar(40) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`delivery_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_address`
--

LOCK TABLES `delivery_address` WRITE;
/*!40000 ALTER TABLE `delivery_address` DISABLE KEYS */;
INSERT INTO `delivery_address` VALUES (1,1,'99362010-63ff-485b-81ac-916fd5c0b8d3','Pukar','E15 4QD','2 Paul Street','','','Stratford','London','07429078771',0),(2,3,'99362010-63ff-485b-81ac-916fd5c0b8d3','Pukar','28007','Appt 4','58th Street','New York Road','New York','New York','0017429078781',0),(3,1,'11419063-4b9e-458f-8828-52b1975251ae','Sam','E15 4QD','2 Paul Street','','','Stratford','London','07429078771',0),(4,1,'d58b25ff-eaf7-4bc3-b03b-0ff0a4d7acfd','Pukar','jfkjgkf','jdfkl','jkjk','jk','jk','j','4554',0),(5,1,'cb181095-d8b1-4b7c-933f-81cb3e9a0aa5','Chirag','BR1 2PR','2 Paul Street','','','Bromley','Kent','07930382650',0),(6,1,'b6ba814a-0778-4ac2-8d08-509793b9db4f','vi','i','i','i','i','i','i','8989898989',0),(7,2,'d71c022a-ee88-4c29-ad7b-264a2eac96ca','vipul','chauhan','padra','','','padra','padra','9898584878',0),(8,1,'3328a82c-9b40-4bf3-b9e5-5335e17c25ee','Sophie','AB1 2CD','154 Address Line','','','Sidcup','London','09876543210',0),(9,1,'3328a82c-9b40-4bf3-b9e5-5335e17c25ee','Sophie','DC2 1BA','321 Address Line','','','Sidcup','London','09876543210',0),(10,1,'406a1273-231f-4a72-a93e-54cbad31254a','Pukar','PO20 5BZ','24 Surrey Cottages','','','Portsmouth','Portsmouth','01234567897',0),(11,1,'406a1273-231f-4a72-a93e-54cbad31254a','Pukar','PO20 5BZ','24 Surrey Cottages','','','Portsmouth','Portsmouth','01234567891',0),(12,1,'aed5d996-d4a9-40c2-acdd-a92265301f53','Pukar','PO20 5BZ','24 Surrey Cottages','','','Portsmouth','Portsmouth','01234567891',0),(13,1,'e6de01aa-3e5f-4cc8-8496-28e7b68467d6','2','2dd','2','2','2','2','2','22',0),(14,3,'10c23a91-8291-4763-830f-6cd092b38f1f','vasdf','dsaf','jk','kjk','jk','jkj','kjk','9898989898',0),(15,1,'10c23a91-8291-4763-830f-6cd092b38f1f','jk','kl','jkj','j','lkj','klk','jkl','12121212',0),(16,1,'f10148ac-15bc-429b-b25f-a84801cfa0be','12','12','21','22','12','12','1','121212',0),(17,1,'1bae162f-95b3-4b87-a842-5ec14685542c','Dipen','E7 8NN','188 Monega Road','','','Upton Park','London','01234567988',0),(18,3,'1bae162f-95b3-4b87-a842-5ec14685542c','Ashish','40056','120th Street','','','New York','New York','001235565898',0),(19,1,'9a1072b0-67a7-4c0e-b19f-6e7a0b8f9957','cdl','da14 5nl ','unit 1','fitxroy business park','sandy lane','sidcup','da14 5nl','02047896541',0),(20,1,'c86638f0-129e-478e-a9e3-57c5ab03e421','CDL','da14 5nl','Fitzroy Business park','sandy lane','','Bromlet','kent','489757426381',0),(21,1,'3dcc04e7-bb29-48bd-a3d8-e01d695a9e33','Vishal Patel','E15 4QD','2 Paul Street','Stratford','','London','London','07429078771',0),(22,1,'da24708c-92c1-4d8d-b1b9-734fa34f893e','Chigs Pptel','BR1 3BY','17 Cedar road','','','Bromley','Kent','07930382650',0),(23,1,'3d83004e-231e-4731-97ab-e61475c099ab','vipul','test1','vadodara','','','vadodara','vadodara','9898985858',0),(24,3,'61ef17f2-8d1f-4f11-81d9-af8e6f514090','Pukar Patel','10010','CDL','Clement road','','Bermondsey','New York','0208308690',0),(25,3,'61ef17f2-8d1f-4f11-81d9-af8e6f514090','Pukar Patel','10010','CDL','Clement road','','Bermondsey','New York','0208308690',0),(26,1,'67586c5c-d93a-4b5e-962f-f6bf92b4de37','Jayes','E15 4QD','kgsfkgsdf','jhj','','njjhk','jkjk','07429078771',0),(27,1,'554bed2c-2ad2-4f88-911f-93d069ee03f0','vipul','vipu','asdf','','','vip','vip','9898989898',0),(28,1,'d2d11af2-9401-4984-ab7c-bf51595862a0','hjjkh','hjkh','hkjh','','','vkkgkj','ind','789789',0),(29,1,'c845e6ab-e305-44c8-93a8-a8688c32e29b','test','tes','te','','','k','kl','78787878',0),(32,1,'dc0cb57f-aa1f-4c1c-81b9-8ae7e6fa206d','first','1','1','1','1','1','1','112',0),(33,1,'0eb090a0-39f8-43a3-9ca7-aadc72ec54f6','2','2ww','2','2','2','2','2','2',0),(34,1,'1a6a3010-bab5-4d57-b1da-30707edddd27','d','d','2','2','2','2','2','2',0),(35,2,'3ece4f80-7476-4773-b58a-89e30d8926d1','1','2ww','2','2','2','2','2','2',0),(36,4,'d80bde95-9480-46f9-aae3-786a0978634c','12','asdfsa','asdf','asdf','asdf','asdf','asdf','1234656',0),(37,1,'d80bde95-9480-46f9-aae3-786a0978634c','df','er','er','er','er','er','er','121221212',0);
/*!40000 ALTER TABLE `delivery_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dropoff_agent`
--

DROP TABLE IF EXISTS `dropoff_agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dropoff_agent` (
  `agent_id` int(11) NOT NULL AUTO_INCREMENT,
  `agent_name` varchar(40) DEFAULT NULL,
  `agent_code` varchar(40) DEFAULT NULL,
  `Postcode` varchar(20) DEFAULT NULL,
  `address1` varchar(200) DEFAULT NULL,
  `address2` varchar(200) DEFAULT NULL,
  `address3` varchar(200) DEFAULT NULL,
  `town` varchar(40) DEFAULT NULL,
  `city` varchar(40) DEFAULT NULL,
  `County` varchar(40) DEFAULT NULL,
  `country_code` varchar(40) DEFAULT NULL,
  `Phone` varchar(40) DEFAULT NULL,
  `Email` varchar(40) DEFAULT NULL,
  `modify_by` varchar(20) DEFAULT NULL,
  `modify_datetime` varchar(20) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT '0',
  `country_name` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`agent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dropoff_agent`
--

LOCK TABLES `dropoff_agent` WRITE;
/*!40000 ALTER TABLE `dropoff_agent` DISABLE KEYS */;
INSERT INTO `dropoff_agent` VALUES (1,'Pukar Patel','PK','IP27 0HP','Unit 1 Fitzroy Business park','Sandy Lan','Sidcup','kent','Sidcup','Sidcup','UK','+44(0)208 308 6983','pukar@cdl.com',NULL,NULL,0,'United Kingdom'),(2,'Jay Bhatt','JB','IG1 1PR','Unit 1 Fitzroy Business park','Sandy Lan','Sidcup','Kent','Sidcup','Sidcup','UK','+44(0)208 308 6984','jay@cdl.com','',NULL,0,'United Kingdom'),(3,'Vipul  Chauhan','VC','SL3 8Ds','Unit 1 Fitzroy Business park','Sandy Lan','Sidcup','Kent','Sidcup','Sidcup','UK','+44(0)208 308 6985','vipul@cdl.com',NULL,NULL,0,'United Kingdom');
/*!40000 ALTER TABLE `dropoff_agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nextid`
--

DROP TABLE IF EXISTS `nextid`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nextid` (
  `next_id` int(11) NOT NULL AUTO_INCREMENT,
  `tablename` varchar(50) DEFAULT NULL,
  `fieldname` varchar(20) DEFAULT NULL,
  `nextnum` int(11) DEFAULT NULL,
  PRIMARY KEY (`next_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nextid`
--

LOCK TABLES `nextid` WRITE;
/*!40000 ALTER TABLE `nextid` DISABLE KEYS */;
INSERT INTO `nextid` VALUES (1,'temporder','temp_id',58),(2,'orders','order_id',21),(3,'paypay_response','paypal_response_id',17),(4,'paypay_errors','paypal_errorid',5);
/*!40000 ALTER TABLE `nextid` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_detail` (
  `orderdetail_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `user_order_id` varchar(20) DEFAULT NULL,
  `temporders_id` int(11) DEFAULT NULL,
  `user_key` varchar(70) DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  `quantity` float DEFAULT NULL,
  `Item_price` float DEFAULT NULL,
  `Item_subtotal` float DEFAULT NULL,
  `delivery_address_id` int(11) DEFAULT NULL,
  `Created_by` int(11) DEFAULT NULL,
  `Modify _by` int(11) DEFAULT NULL,
  `created_datetime` varchar(20) DEFAULT NULL,
  `modify_datetime` varchar(20) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`orderdetail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1,1,'VSUDPKM1',3,'11419063-4b9e-458f-8828-52b1975251ae',5,1,1,56,56,3,NULL,NULL,'2012-06-11 19:24:42','2012-06-11 19:24:42',0),(2,2,'VSUDPKM2',4,'d58b25ff-eaf7-4bc3-b03b-0ff0a4d7acfd',2,1,2,9,18,4,NULL,NULL,'2012-06-11 20:51:31','2012-06-11 20:51:31',0),(3,2,'VSUDPKM2',5,'d58b25ff-eaf7-4bc3-b03b-0ff0a4d7acfd',1,1,3,18,54,4,NULL,NULL,'2012-06-11 20:51:31','2012-06-11 20:51:31',0),(4,3,'VSUDPKM3',8,'cb181095-d8b1-4b7c-933f-81cb3e9a0aa5',2,1,1,9,9,5,NULL,NULL,'2012-06-15 14:05:23','2012-06-15 14:05:23',0),(5,4,'VSUDPKM4',10,'b6ba814a-0778-4ac2-8d08-509793b9db4f',1,1,1,18,18,6,NULL,NULL,'2012-06-16 16:04:13','2012-06-16 16:04:13',0),(6,5,'VSUDPKM5',11,'d71c022a-ee88-4c29-ad7b-264a2eac96ca',2,2,1,84,84,7,NULL,NULL,'2012-06-16 16:21:34','2012-06-16 16:21:34',0),(7,6,'VSUDPKM6',14,'3328a82c-9b40-4bf3-b9e5-5335e17c25ee',1,1,1,18,18,8,NULL,NULL,'2012-06-18 17:29:07','2012-06-18 17:29:07',0),(8,6,'VSUDPKM6',15,'3328a82c-9b40-4bf3-b9e5-5335e17c25ee',2,1,3,9,27,8,NULL,NULL,'2012-06-18 17:29:08','2012-06-18 17:29:08',0),(9,7,'VSUDPKM7',17,'aed5d996-d4a9-40c2-acdd-a92265301f53',2,1,1,9,9,12,NULL,NULL,'2012-06-19 15:30:48','2012-06-19 15:30:48',0),(11,9,'VSUDPKM9',20,'10c23a91-8291-4763-830f-6cd092b38f1f',15,3,5,89,445,14,NULL,NULL,'2012-06-20 19:22:35','2012-06-20 19:22:35',0),(12,9,'VSUDPKM9',21,'10c23a91-8291-4763-830f-6cd092b38f1f',1,1,1,18,18,15,NULL,NULL,'2012-06-20 19:22:35','2012-06-20 19:22:35',0),(13,10,'VSUDPK10',22,'f10148ac-15bc-429b-b25f-a84801cfa0be',1,1,2,18,36,16,NULL,NULL,'2012-06-21 14:08:58','2012-06-21 14:08:58',0),(14,11,'VSUDPK11',26,'c86638f0-129e-478e-a9e3-57c5ab03e421',1,1,1,18,18,20,NULL,NULL,'2012-06-25 20:34:20','2012-06-25 20:34:20',0),(15,12,'VSUDPK12',30,'3d83004e-231e-4731-97ab-e61475c099ab',1,1,1,18,18,23,NULL,NULL,'2012-10-12 16:14:52','2012-10-12 16:14:52',0),(16,13,'VSUDPK13',29,'da24708c-92c1-4d8d-b1b9-734fa34f893e',1,1,1,18,18,22,NULL,NULL,'2012-10-12 16:15:48','2012-10-12 16:15:48',0),(17,14,'VSUDPK14',31,'61ef17f2-8d1f-4f11-81d9-af8e6f514090',2,3,1,55,55,24,NULL,NULL,'2012-10-12 16:34:12','2012-10-12 16:34:12',0),(18,14,'VSUDPK14',32,'61ef17f2-8d1f-4f11-81d9-af8e6f514090',2,3,1,55,55,24,NULL,NULL,'2012-10-12 16:34:12','2012-10-12 16:34:12',0),(19,15,'VSUDPK15',38,'c845e6ab-e305-44c8-93a8-a8688c32e29b',2,1,1,9,9,29,NULL,NULL,'2012-10-13 14:57:06','2012-10-13 14:57:06',0),(25,16,'BJJLNA16',46,'dc0cb57f-aa1f-4c1c-81b9-8ae7e6fa206d',1,1,1,18,18,32,NULL,NULL,'2012-10-13 15:42:43','2012-10-13 15:42:43',0),(26,17,'TCXKOK17',47,'0eb090a0-39f8-43a3-9ca7-aadc72ec54f6',1,1,1,18,18,33,NULL,NULL,'2012-10-13 15:55:35','2012-10-13 15:55:35',0),(27,17,'TCXKOK17',48,'0eb090a0-39f8-43a3-9ca7-aadc72ec54f6',1,1,5,18,90,33,NULL,NULL,'2012-10-13 15:55:35','2012-10-13 15:55:35',0),(28,18,'LKKJFD18',54,'1a6a3010-bab5-4d57-b1da-30707edddd27',1,1,1,18,18,34,NULL,NULL,'2012-10-14 14:57:05','2012-10-14 14:57:05',0),(29,19,'NBZFIH19',55,'3ece4f80-7476-4773-b58a-89e30d8926d1',2,2,1,84,84,35,NULL,NULL,'2012-10-14 16:07:41','2012-10-14 16:07:41',0),(30,20,'KBUFTR20',56,'d80bde95-9480-46f9-aae3-786a0978634c',1,4,1,18,18,36,NULL,NULL,'2013-08-17 15:16:53','2013-08-17 15:16:53',0),(31,20,'KBUFTR20',57,'d80bde95-9480-46f9-aae3-786a0978634c',1,1,1,18,18,0,NULL,NULL,'2013-08-17 15:28:08','2013-08-17 15:28:08',0);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL DEFAULT '0',
  `user_order_id` varchar(20) DEFAULT NULL,
  `user_key` varchar(70) DEFAULT NULL,
  `paypal_response_id` int(11) DEFAULT NULL,
  `order_status` varchar(20) DEFAULT NULL,
  `collection_type` varchar(20) DEFAULT NULL,
  `s_name` varchar(200) DEFAULT NULL,
  `s_postcode` varchar(20) DEFAULT NULL,
  `s_addressLine1` varchar(200) DEFAULT NULL,
  `s_addressLine2` varchar(200) DEFAULT NULL,
  `s_addressLine3` varchar(200) DEFAULT NULL,
  `s_town` varchar(40) DEFAULT NULL,
  `s_city` varchar(40) DEFAULT NULL,
  `s_county` varchar(40) DEFAULT NULL,
  `s_phone` varchar(40) DEFAULT NULL,
  `s_email` varchar(40) DEFAULT NULL,
  `package_content` varchar(40) DEFAULT NULL,
  `value_parcel` float DEFAULT NULL,
  `num_parcel` float DEFAULT NULL,
  `insurance_cover` float DEFAULT NULL,
  `delivery_Instructions` varchar(400) DEFAULT NULL,
  `dropoff_agent_id` int(11) DEFAULT NULL,
  `Remote_IP` varchar(100) DEFAULT NULL,
  `carrier_id` int(11) DEFAULT NULL,
  `tracking_no` varchar(200) DEFAULT NULL,
  `signature` varchar(300) DEFAULT NULL,
  `total_price` float DEFAULT NULL COMMENT 'total price that means final value of the order',
  `order_date` date DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `modify_by` int(11) DEFAULT NULL,
  `created_datetime` varchar(20) DEFAULT NULL,
  `modify_datetime` varchar(20) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT '0',
  `sub_total` float DEFAULT NULL,
  `vat` float DEFAULT NULL,
  `user_register_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'VSUDPKM1','11419063-4b9e-458f-8828-52b1975251ae',NULL,'enroute','droptoagent','Pukar','SE16 2UN','166 Lower Road','','','Surrey Quays','London','London','07429078771','ppatel@cdl.uk.com','Shoes',50,1,0,'Do Not Send Out',2,'81.136.165.60:59826',1,' ',NULL,67.2,'2012-06-11',NULL,NULL,'2012-06-11 19:24:42','2012-06-11 19:24:42',0,56,11.2,1),(2,'VSUDPKM2','d58b25ff-eaf7-4bc3-b03b-0ff0a4d7acfd',NULL,'enroute','reversed','Pukar','sjdfkds','kjskfl','jkljl','jk','jk','jk','kl','456465','ppatel@cdl.uk.com','clothes',49.99,2,0,'',0,'81.136.165.60:60080',2,' ',NULL,86.4,'2012-06-11',NULL,NULL,'2012-06-11 20:51:31','2012-06-11 20:51:31',0,72,14.4,2),(3,'VSUDPKM3','cb181095-d8b1-4b7c-933f-81cb3e9a0aa5',NULL,'enroute','droptoagent','Pukar','SE16 2UN','166 Lower Road','','','Surrey Quays','London','London','07429078771','pukar05@yahoo.com','Shoes',100,1,3.75,'Handle Carefully',1,'81.136.165.60:58494',1,' ',NULL,11.55,'2012-06-15',NULL,NULL,'2012-06-15 14:05:22','2012-06-15 14:05:22',0,9,2.55,NULL),(4,'VSUDPKM4','b6ba814a-0778-4ac2-8d08-509793b9db4f',NULL,'ordered','droptoagent','123','2','2','2','2','2','2','2','2','chetan@cdl-it.com','2',2,1,0,'2',2,'115.249.84.19:11634',NULL,NULL,NULL,21.6,'2012-06-16',NULL,NULL,'2012-06-16 16:04:13','2012-06-16 16:04:13',0,18,3.6,NULL),(5,'VSUDPKM5','d71c022a-ee88-4c29-ad7b-264a2eac96ca',NULL,'ordered','droptoagent','vipul','chauhani','padra','','','padra','vadodara','India','9898585689','chetan@cdl-it.com','dresses ',20,1,18.75,'',1,'115.249.84.19:36392',NULL,NULL,NULL,123.3,'2012-06-16',NULL,NULL,'2012-06-16 16:21:34','2012-06-16 16:21:34',0,84,20.55,NULL),(6,'VSUDPKM6','3328a82c-9b40-4bf3-b9e5-5335e17c25ee',NULL,'ordered','droptoagent','Marika','AB1 2CD','123 Address Line','','','Orpington','London','Kent','01234567891','mlogan@cdllogistics.com','Clothes',200,2,7.5,'',2,'81.136.165.60:60751',NULL,NULL,NULL,63,'2012-06-18',NULL,NULL,'2012-06-18 17:29:07','2012-06-18 17:29:07',0,45,10.5,NULL),(7,'VSUDPKM7','aed5d996-d4a9-40c2-acdd-a92265301f53',NULL,'enroute','droptoagent','Vishal','AB1 2YZ','188 Monega Road','','','East Ham','London','Newham','07930382650','pukar05@yahoo.com','Clothes',50,1,3.75,'',1,'80.176.74.180:59699',1,NULL,NULL,15.3,'2012-06-19',NULL,NULL,'2012-06-19 15:30:48','2012-06-19 15:30:48',0,9,2.55,NULL),(8,'VSUDPKM8','e6de01aa-3e5f-4cc8-8496-28e7b68467d6',NULL,'notified','reversed','2','2','2','2','2','2','2','2','2','2@l.com','2',2,1,0,'2',0,'115.249.84.19:55941',NULL,NULL,NULL,21.6,'2012-06-20',NULL,NULL,'2012-06-20 14:51:46','2012-06-20 14:51:46',0,18,3.6,NULL),(9,'VSUDPKM9','10c23a91-8291-4763-830f-6cd092b38f1f',NULL,'notified','droptoagent','adsf','jk','jkj','kjk','jkj','kj','kj','kjk','9889898','chauhanvipul87@gmail.com','12',24,2,0,'',3,'117.254.6.21:2032',NULL,NULL,NULL,555.6,'2012-06-20',NULL,NULL,'2012-06-20 19:22:35','2012-06-20 19:22:35',0,463,92.6,NULL),(10,'VSUDPK10','f10148ac-15bc-429b-b25f-a84801cfa0be',NULL,'enroute','droptoagent','vipul','chauhan','vip','vi','pvi','pi','i','pvfp','12','vipul@cdl-it.com','12',12,2,0,'1212',1,'0:0:0:0:0:0:0:1:59845',1,'f',NULL,52.2,'2012-06-21',NULL,NULL,'2012-06-21 14:08:58','2012-06-21 14:08:58',0,36,8.7,NULL),(11,'VSUDPK11','c86638f0-129e-478e-a9e3-57c5ab03e421',NULL,'notified','droptoagent','john  smith','le11 5eh','109 storer road','','','loughborough','loughborough','leicestershire','07896558745','krisma.patel@live.co.ukq','clothes',12,1,0,'',1,'81.136.165.60:60614',NULL,NULL,NULL,21.6,'2012-06-25',NULL,NULL,'2012-06-25 20:34:20','2012-06-25 20:34:20',0,18,3.6,NULL),(12,'VSUDPK12','3d83004e-231e-4731-97ab-e61475c099ab',NULL,'delivered','droptoagent','vipul','chauhan','vadodara','','','vadodara','vadodara','vadodara','9898787878','vipul@cdl-it.com','gift card',12,1,0,'',1,'212.240.235.178:42146',6,'c patels','vipul',21.6,'2012-10-12',NULL,NULL,'2012-10-12 16:14:52','2012-10-12 16:14:52',0,18,3.6,NULL),(13,'VSUDPK13','da24708c-92c1-4d8d-b1b9-734fa34f893e',NULL,'enroute','droptoagent','Marika Logan','da14 5nl','CDL ','Nit 1, fitzroy business park','','Sidcup','Sidcup','Kent','02083086960','mlogan@cdl.uk.com','Mobile Phone',100,1,3.75,'leave safe',2,'80.176.74.180:58507',1,'pukar','pukar patel',26.1,'2012-10-12',NULL,NULL,'2012-10-12 16:15:48','2012-10-12 16:15:48',0,18,4.35,NULL),(14,'VSUDPK14','61ef17f2-8d1f-4f11-81d9-af8e6f514090',NULL,'enroute','reversed','Marika Logan','DA14 5NL','Unit 1','','','Sidcup','Kent','UK','02083086960','mlogan@cdl.uk.com','Mobile',500,2,18.75,'',0,'80.176.74.180:58593',1,'122332','vipul',154.5,'2012-10-12',NULL,NULL,'2012-10-12 16:34:12','2012-10-12 16:34:12',0,110,25.75,NULL),(15,'VSUDPK15','c845e6ab-e305-44c8-93a8-a8688c32e29b',NULL,'notified','reversed','vipul','ww','cha','c','','ch','ch','ch','45454545','ch@yahoo.com','test',10,1,0,'10',0,'0:0:0:0:0:0:0:1:49742',NULL,NULL,NULL,10.8,'2012-10-13',NULL,NULL,'2012-10-13 14:57:06','2012-10-13 14:57:06',0,9,1.8,NULL),(16,'BJJLNA16','dc0cb57f-aa1f-4c1c-81b9-8ae7e6fa206d',NULL,'notified','reversed','2','2','2','2','2','2','2','2','2','2@yahoo.com','2',1,1,0,'1',0,'0:0:0:0:0:0:0:1:49787',NULL,NULL,NULL,21.6,'2012-10-13',NULL,NULL,'2012-10-13 15:20:59','2012-10-13 15:20:59',0,18,3.6,NULL),(17,'TCXKOK17','0eb090a0-39f8-43a3-9ca7-aadc72ec54f6',NULL,'notified','reversed','2','2','2','2','2','2','2','2','2','2@y.com','2',2,6,0,'2',0,'0:0:0:0:0:0:0:1:49864',NULL,NULL,NULL,129.6,'2012-10-13',NULL,NULL,'2012-10-13 15:55:34','2012-10-13 15:55:34',0,108,21.6,NULL),(18,'LKKJFD18','1a6a3010-bab5-4d57-b1da-30707edddd27',NULL,'delivered','reversed','vipul','12','12','121','2','3','1','1','1','1@y.com','23',23,1,0,'23',0,'0:0:0:0:0:0:0:1:49677',1,'vip','123',21.6,'2012-10-14',NULL,NULL,'2012-10-14 14:57:05','2012-10-14 14:57:05',0,18,3.6,NULL),(19,'NBZFIH19','3ece4f80-7476-4773-b58a-89e30d8926d1',NULL,'ordered','reversed','2','2','2','2','2','2','2','2','2','chauhanvipul87@gmail.com','12',121,1,0,'12',0,'0:0:0:0:0:0:0:1:49795',NULL,NULL,'tushar jethva',100.8,'2012-10-14',NULL,NULL,'2012-10-14 16:07:41','2012-10-14 16:07:41',0,84,16.8,NULL),(20,'KBUFTR20','d80bde95-9480-46f9-aae3-786a0978634c',NULL,'notified','droptoagent','vipul','123456','chauhan','padfa','asdfsd','asdfasdf','asdfsadf','sdsfasdf','123232565','chau@yahoo.com','20',10,1,7.5,'asdfasfd',1,'127.0.0.1:49439',NULL,NULL,NULL,52.2,'2013-08-17',NULL,NULL,'2013-08-17 15:16:53','2013-08-17 15:16:53',0,36,8.7,NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paypay_errors`
--

DROP TABLE IF EXISTS `paypay_errors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paypay_errors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paypal_errorid` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `error_code` varchar(50) DEFAULT NULL,
  `short_errormsg` varchar(800) DEFAULT NULL,
  `long_errormsg` varchar(800) DEFAULT NULL,
  `severity_code` varchar(100) DEFAULT NULL,
  `modify_datetime` varchar(20) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paypay_errors`
--

LOCK TABLES `paypay_errors` WRITE;
/*!40000 ALTER TABLE `paypay_errors` DISABLE KEYS */;
INSERT INTO `paypay_errors` VALUES (1,1,3,'10764','This transaction cannot be processed at this time. Please try again later.','This transaction cannot be processed at this time. Please try again later.','Error','2012-06-15 14:17:07',0),(2,2,9,'10001','Internal Error','Internal Error','Error','2012-06-20 19:25:49',0),(3,3,11,'10527','Invalid Data','This transaction cannot be processed. Please enter a valid credit card number and type.','Error','2012-06-25 20:35:21',0),(4,4,20,'10527','Invalid Data','This transaction cannot be processed. Please enter a valid credit card number and type.','Error','2013-08-17 15:24:47',0),(5,4,20,'10536','Invalid Data','The transaction was refused as a result of a duplicate invoice ID supplied.  Attempt with a new invoice ID','Error','2013-08-17 15:26:06',0),(6,4,20,'10536','Invalid Data','The transaction was refused as a result of a duplicate invoice ID supplied.  Attempt with a new invoice ID','Error','2013-08-17 15:27:11',0);
/*!40000 ALTER TABLE `paypay_errors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paypay_log`
--

DROP TABLE IF EXISTS `paypay_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paypay_log` (
  `paypal_log_id` int(11) NOT NULL AUTO_INCREMENT,
  `card_type` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `card_no` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `fname` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `lname` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `smonth` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `syear` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `emonth` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `eyear` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `cvv_no` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `Issue_no` varchar(150) CHARACTER SET latin1 DEFAULT NULL,
  `modify_by` int(11) DEFAULT NULL,
  `modify_datetime` varchar(20) DEFAULT NULL,
  `ref_orderid` varchar(40) DEFAULT NULL,
  `Ip_address` varchar(40) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT '0' COMMENT '0 for valid entry and 1 for invalid entry.',
  `type` varchar(40) DEFAULT NULL COMMENT 'Set request or response type in this field',
  PRIMARY KEY (`paypal_log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paypay_log`
--

LOCK TABLES `paypay_log` WRITE;
/*!40000 ALTER TABLE `paypay_log` DISABLE KEYS */;
INSERT INTO `paypay_log` VALUES (1,'Visa','4462729221966812','Pukar','Patel','4','2010','3','2013','636','',NULL,'2012-06-11 19:29:28','1','81.136.165.60',0,'request'),(2,'Visa','4462729221966812','Pukar','Patel','4','2010','3','2013','636','',NULL,'2012-06-11 19:29:39','1','81.136.165.60',0,'response'),(3,'Visa','4462729221966812','Pukar','Patel','4','2010','3','2013','636','',NULL,'2012-06-11 21:04:22','2','81.136.165.60',0,'request'),(4,'Visa','4462729221966812','Pukar','Patel','4','2010','3','2013','636','',NULL,'2012-06-11 21:04:30','2','81.136.165.60',0,'response'),(5,'Visa','4375452465565721','John','Smith','5','2005','5','2015','0','',NULL,'2012-06-15 14:16:23','3','81.136.165.60',0,'request'),(6,'Visa','4375452465565721','John','Smith','5','2005','5','2015','0','',NULL,'2012-06-15 14:17:08','3','81.136.165.60',0,'response'),(7,'Visa','4375452465565721','John','Smith','5','2007','5','2017','0','',NULL,'2012-06-15 14:17:55','3','81.136.165.60',0,'request'),(8,'Visa','4375452465565721','John','Smith','5','2007','5','2017','0','',NULL,'2012-06-15 14:18:24','3','81.136.165.60',0,'response'),(9,'Visa','4375452465565721','2','2','5','2005','5','2017','0','',NULL,'2012-06-16 16:09:50','4','115.249.84.19',0,'request'),(10,'Visa','4375452465565721','2','2','5','2005','5','2017','0','',NULL,'2012-06-16 16:10:17','4','115.249.84.19',0,'response'),(11,'Visa','4375452465565721','2','2','5','2005','5','2017','0','',NULL,'2012-06-16 16:15:14','4','115.249.84.19',0,'request'),(12,'Visa','4375452465565721','2','2','5','2005','5','2017','0','',NULL,'2012-06-16 16:15:21','4','115.249.84.19',0,'response'),(13,'Visa','4375452465565721','k','k','5','2005','5','2017','0','',NULL,'2012-06-16 16:22:11','5','115.249.84.19',0,'request'),(14,'Visa','4375452465565721','k','k','5','2005','5','2017','0','',NULL,'2012-06-16 16:22:19','5','115.249.84.19',0,'response'),(15,'Visa','4375452465565721','John','Smith','5','2007','5','2017','0','',NULL,'2012-06-18 17:32:11','6','81.136.165.60',0,'request'),(16,'Visa','4375452465565721','John','Smith','5','2007','5','2017','0','',NULL,'2012-06-18 17:32:26','6','81.136.165.60',0,'response'),(17,'Visa','4375452465565721','John','Smith','5','2007','5','2017','0','',NULL,'2012-06-19 15:32:43','7','80.176.74.180',0,'request'),(18,'Visa','4375452465565721','John','Smith','5','2007','5','2017','0','',NULL,'2012-06-19 15:32:51','7','80.176.74.180',0,'response'),(19,'Visa','4111111111111111','John','Smith','5','2005','5','2017','0','',NULL,'2012-06-20 19:25:16','9','117.254.6.21',0,'request'),(20,'Visa','4111111111111111','John','Smith','5','2005','5','2017','0','',NULL,'2012-06-20 19:25:49','9','117.254.6.21',0,'response'),(21,'Visa','4375452465565721','12','12','5','2005','5','2017','0','',NULL,'2012-06-21 14:11:46','10','127.0.0.1',0,'request'),(22,'Visa','4375452465565721','12','12','5','2005','5','2017','0','',NULL,'2012-06-21 14:11:55','10','127.0.0.1',0,'response'),(23,'Visa','4375452465565721 ','John ','smith','5','2007','5','2017','0','',NULL,'2012-06-25 20:35:15','11','81.136.165.60',0,'request'),(24,'Visa','4375452465565721 ','John ','smith','5','2007','5','2017','0','',NULL,'2012-06-25 20:35:21','11','81.136.165.60',0,'response'),(25,'Visa','4375452465565721','vipul','chauhan','1','2012','5','2017','0','',NULL,'2012-10-12 16:16:08','12','212.240.235.178',0,'request'),(26,'Visa','4375452465565721','vipul','chauhan','1','2012','5','2017','0','',NULL,'2012-10-12 16:16:20','12','212.240.235.178',0,'response'),(27,'MasterCard','5569510001114115','Hitesh','Patel','6','2010','7','2013','688','',NULL,'2012-10-12 16:17:33','13','80.176.74.180',0,'request'),(28,'MasterCard','5569510001114115','Hitesh','Patel','6','2010','7','2013','688','',NULL,'2012-10-12 16:17:45','13','80.176.74.180',0,'response'),(29,'Visa','4375452465565721','Pukar','patel','10','2011','5','2017','0','',NULL,'2012-10-12 16:43:12','14','80.176.74.180',0,'request'),(30,'Visa','4375452465565721','Pukar','patel','10','2011','5','2017','0','',NULL,'2012-10-12 16:43:20','14','80.176.74.180',0,'response'),(31,'tìÂqbaN²Ô²/B³åž','\\¦…Ý	¤óôºb\\ººócÒgeÂPz¥ÁûG§y','×,÷‰ÇQÿOQº-óÿ•','’ÒÃ°É¶†³èOù×¦1',' §Ìf¯å\'œVòéÝ‹ÓÞ','³hä)ûÚó‘»b_}ÓÌ',' §Ìf¯å\'œVòéÝ‹ÓÞ','±Ô@?¥P—c±z‘OŒâõ','‘\'¶CÌŸz–µHØÂ­','×éÇ¾IG¸)Ðpb«5³c’',NULL,'2012-10-14 15:11:40','18','127.0.0.1',0,'request'),(32,'tìÂqbaN²Ô²/B³åž','\\¦…Ý	¤óôºb\\ººócÒgeÂPz¥ÁûG§y','×,÷‰ÇQÿOQº-óÿ•','’ÒÃ°É¶†³èOù×¦1',' §Ìf¯å\'œVòéÝ‹ÓÞ','³hä)ûÚó‘»b_}ÓÌ',' §Ìf¯å\'œVòéÝ‹ÓÞ','±Ô@?¥P—c±z‘OŒâõ','‘\'¶CÌŸz–µHØÂ­','×éÇ¾IG¸)Ðpb«5³c’',NULL,'2012-10-14 15:12:03','18','127.0.0.1',0,'response'),(33,'tìÂqbaN²Ô²/B³åž','\\¦…Ý	¤óôºb\\ººócÒgeÂPz¥ÁûG§y','g²’e\rE©Ãwµ!Ç','•œÉS1ÒVå#Æ˜Cf|','»X¤‚ç¹äÏi0¥Ñ¹h','Y8Öødåìîîq!+¦\0©',' §Ìf¯å\'œVòéÝ‹ÓÞ','r¨§,R21˜¬(Lé','‘\'¶CÌŸz–µHØÂ­','geÂPz¥ÁûG§y',NULL,'2012-10-14 16:08:28','19','127.0.0.1',0,'request'),(34,'tìÂqbaN²Ô²/B³åž','\\¦…Ý	¤óôºb\\ººócÒgeÂPz¥ÁûG§y','g²’e\rE©Ãwµ!Ç','•œÉS1ÒVå#Æ˜Cf|','»X¤‚ç¹äÏi0¥Ñ¹h','Y8Öødåìîîq!+¦\0©',' §Ìf¯å\'œVòéÝ‹ÓÞ','r¨§,R21˜¬(Lé','‘\'¶CÌŸz–µHØÂ­','geÂPz¥ÁûG§y',NULL,'2012-10-14 16:08:29','19','127.0.0.1',0,'response'),(35,'tìÂqbaN²Ô²/B³åž','\\¦…Ý	¤óôºb\\ººócÒgeÂPz¥ÁûG§y','ˆÒOŽ?3ë˜zITsØG','ˆÒOŽ?3ë˜zITsØG','Í¾EñH,âègHkÈ[','r¨§,R21˜¬(Lé','Í¾EñH,âègHkÈ[','±Ô@?¥P—c±z‘OŒâõ','‘\'¶CÌŸz–µHØÂ­','geÂPz¥ÁûG§y',NULL,'2012-10-14 16:24:54','19','127.0.0.1',0,'request'),(36,'tìÂqbaN²Ô²/B³åž','\\¦…Ý	¤óôºb\\ººócÒgeÂPz¥ÁûG§y','ˆÒOŽ?3ë˜zITsØG','ˆÒOŽ?3ë˜zITsØG','Í¾EñH,âègHkÈ[','r¨§,R21˜¬(Lé','Í¾EñH,âègHkÈ[','±Ô@?¥P—c±z‘OŒâõ','‘\'¶CÌŸz–µHØÂ­','geÂPz¥ÁûG§y',NULL,'2012-10-14 16:25:10','19','127.0.0.1',0,'response'),(37,'tìÂqbaN²Ô²/B³åž','\\¦…Ý	¤óôºb\\ººócÒÃ- ÎŒéi¡`ÁÀu','~AT`—˜f««·£ê„6~','»úÈvSƒÈ·˜#œq\"ÄÑ','gM…….Siüs…×ôi\r\"','±H/ëÛõeœÆF„|a‡#','»X¤‚ç¹äÏi0¥Ñ¹h','±Ô@?¥P—c±z‘OŒâõ','‘\'¶CÌŸz–µHØÂ­','geÂPz¥ÁûG§y',NULL,'2013-08-17 15:20:52','20','127.0.0.1',0,'request'),(38,'tìÂqbaN²Ô²/B³åž','\\¦…Ý	¤óôºb\\ººócÒÃ- ÎŒéi¡`ÁÀu','~AT`—˜f««·£ê„6~','»úÈvSƒÈ·˜#œq\"ÄÑ','gM…….Siüs…×ôi\r\"','±H/ëÛõeœÆF„|a‡#','»X¤‚ç¹äÏi0¥Ñ¹h','±Ô@?¥P—c±z‘OŒâõ','‘\'¶CÌŸz–µHØÂ­','geÂPz¥ÁûG§y',NULL,'2013-08-17 15:20:52','20','127.0.0.1',0,'response'),(39,'tìÂqbaN²Ô²/B³åž','\\¦…Ý	¤óôºb\\ººócÒÃ- ÎŒéi¡`ÁÀu','8E€oB|íh€ª’b™’–','ƒ î8O¶“¿ù‹?óàµ','»X¤‚ç¹äÏi0¥Ñ¹h','±H/ëÛõeœÆF„|a‡#','»X¤‚ç¹äÏi0¥Ñ¹h','±Ô@?¥P—c±z‘OŒâõ','‘\'¶CÌŸz–µHØÂ­','geÂPz¥ÁûG§y',NULL,'2013-08-17 15:24:39','20','127.0.0.1',0,'request'),(40,'tìÂqbaN²Ô²/B³åž','\\¦…Ý	¤óôºb\\ººócÒÃ- ÎŒéi¡`ÁÀu','8E€oB|íh€ª’b™’–','ƒ î8O¶“¿ù‹?óàµ','»X¤‚ç¹äÏi0¥Ñ¹h','±H/ëÛõeœÆF„|a‡#','»X¤‚ç¹äÏi0¥Ñ¹h','±Ô@?¥P—c±z‘OŒâõ','‘\'¶CÌŸz–µHØÂ­','geÂPz¥ÁûG§y',NULL,'2013-08-17 15:24:47','20','127.0.0.1',0,'response'),(41,'tìÂqbaN²Ô²/B³åž','\\¦…Ý	¤óôºb\\ººócÒgeÂPz¥ÁûG§y','×,÷‰ÇQÿOQº-óÿ•','»úÈvSƒÈ·˜#œq\"ÄÑ','»X¤‚ç¹äÏi0¥Ñ¹h','±H/ëÛõeœÆF„|a‡#','»X¤‚ç¹äÏi0¥Ñ¹h','±Ô@?¥P—c±z‘OŒâõ','‘\'¶CÌŸz–µHØÂ­','geÂPz¥ÁûG§y',NULL,'2013-08-17 15:25:55','20','127.0.0.1',0,'request'),(42,'tìÂqbaN²Ô²/B³åž','\\¦…Ý	¤óôºb\\ººócÒgeÂPz¥ÁûG§y','×,÷‰ÇQÿOQº-óÿ•','»úÈvSƒÈ·˜#œq\"ÄÑ','»X¤‚ç¹äÏi0¥Ñ¹h','±H/ëÛõeœÆF„|a‡#','»X¤‚ç¹äÏi0¥Ñ¹h','±Ô@?¥P—c±z‘OŒâõ','‘\'¶CÌŸz–µHØÂ­','geÂPz¥ÁûG§y',NULL,'2013-08-17 15:26:06','20','127.0.0.1',0,'response'),(43,'V\"î))¥*ˆ÷\r\"[*Ñ','Zj¨tã5«þÉ@•l¬ÑugeÂPz¥ÁûG§y','×,÷‰ÇQÿOQº-óÿ•','»úÈvSƒÈ·˜#œq\"ÄÑ','»X¤‚ç¹äÏi0¥Ñ¹h','±H/ëÛõeœÆF„|a‡#','»X¤‚ç¹äÏi0¥Ñ¹h','±Ô@?¥P—c±z‘OŒâõ','‘\'¶CÌŸz–µHØÂ­','geÂPz¥ÁûG§y',NULL,'2013-08-17 15:27:00','20','127.0.0.1',0,'request'),(44,'V\"î))¥*ˆ÷\r\"[*Ñ','Zj¨tã5«þÉ@•l¬ÑugeÂPz¥ÁûG§y','×,÷‰ÇQÿOQº-óÿ•','»úÈvSƒÈ·˜#œq\"ÄÑ','»X¤‚ç¹äÏi0¥Ñ¹h','±H/ëÛõeœÆF„|a‡#','»X¤‚ç¹äÏi0¥Ñ¹h','±Ô@?¥P—c±z‘OŒâõ','‘\'¶CÌŸz–µHØÂ­','geÂPz¥ÁûG§y',NULL,'2013-08-17 15:27:11','20','127.0.0.1',0,'response');
/*!40000 ALTER TABLE `paypay_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paypay_response`
--

DROP TABLE IF EXISTS `paypay_response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paypay_response` (
  `paypal_response_id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `error_id` varchar(20) DEFAULT NULL,
  `status` varchar(40) DEFAULT NULL,
  `timestamps` varchar(40) DEFAULT NULL,
  `correlationid` varchar(40) DEFAULT NULL,
  `versions` varchar(20) DEFAULT NULL,
  `build` varchar(20) DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `currency_code` varchar(20) DEFAULT NULL COMMENT 'Currency Code (By Default GBP)',
  `avscode` varchar(50) DEFAULT NULL,
  `cvv2Match` varchar(200) DEFAULT NULL,
  `transactionid` varchar(50) DEFAULT NULL,
  `user_key` varchar(70) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT '0' COMMENT '0 for valid entry and 1 for invalid entry.',
  `modify_datetime` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`paypal_response_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paypay_response`
--

LOCK TABLES `paypay_response` WRITE;
/*!40000 ALTER TABLE `paypay_response` DISABLE KEYS */;
INSERT INTO `paypay_response` VALUES (1,1,'0','Success','2012-06-11T13:53:07Z','d42ba46b1cc7','76.0','2945183',67.2,'GBP','X','M','5AC89385G5034584C','11419063-4b9e-458f-8828-52b1975251ae',0,'2012-06-11 19:29:38'),(2,2,'0','Success','2012-06-11T15:27:58Z','91947612a9e7a','76.0','2945183',86.4,'GBP','X','M','5TD25041584398632','d58b25ff-eaf7-4bc3-b03b-0ff0a4d7acfd',0,'2012-06-11 21:04:30'),(3,3,'1','Success','2012-06-15T08:40:37Z','665396e9d1b55','76.0','3067390',11.55,'GBP','X','M','7TV70155LH565260R','cb181095-d8b1-4b7c-933f-81cb3e9a0aa5',0,'2012-06-15 14:17:08'),(4,4,'0','Success','2012-06-16T10:38:51Z','2e062bbf98af9','76.0','3067390',21.6,'GBP','X','M','91E9453684470071L','b6ba814a-0778-4ac2-8d08-509793b9db4f',0,'2012-06-16 16:15:21'),(5,5,'0','Success','2012-06-16T10:45:49Z','f6dacb6cc152d','76.0','3067390',123.3,'GBP','X','M','8M740782A8924700D','d71c022a-ee88-4c29-ad7b-264a2eac96ca',0,'2012-06-16 16:22:19'),(6,6,'0','Success','2012-06-18T11:55:59Z','433b4b7b12397','76.0','3067390',63,'GBP','X','M','8UA52458X2440121E','3328a82c-9b40-4bf3-b9e5-5335e17c25ee',0,'2012-06-18 17:32:25'),(7,7,'0','Success','2012-06-19T09:56:25Z','a90d5bf1ec944','76.0','3067390',15.3,'GBP','X','M','34K164517V9828229','aed5d996-d4a9-40c2-acdd-a92265301f53',0,'2012-06-19 15:32:50'),(8,9,'2','Failure','2012-06-20T13:49:26Z','b82bb1e0b4cdc','76.0','3067390',555.6,'GBP','','','','10c23a91-8291-4763-830f-6cd092b38f1f',0,'2012-06-20 19:25:49'),(9,10,'0','Success','2012-06-21T08:35:33Z','49fd8ecf34edf','76.0','3067390',52.2,'GBP','X','M','50444580D3021670P','f10148ac-15bc-429b-b25f-a84801cfa0be',0,'2012-06-21 14:11:55'),(10,11,'3','Failure','2012-06-25T14:59:05Z','bb771ba6c4c7d','76.0','3067390',21.6,'GBP','','','','c86638f0-129e-478e-a9e3-57c5ab03e421',0,'2012-06-25 20:35:21'),(11,12,'0','Success','2012-10-12T10:34:31Z','f4067e306d9ce','76.0','3719653',21.6,'GBP','X','M','3EF70330WA124025J','3d83004e-231e-4731-97ab-e61475c099ab',0,'2012-10-12 16:16:20'),(12,13,'0','Success','2012-10-12T10:35:55Z','4ab6e9b5a0a9','76.0','3719653',26.1,'GBP','X','M','33131776R19781741','da24708c-92c1-4d8d-b1b9-734fa34f893e',0,'2012-10-12 16:17:45'),(13,14,'0','Success','2012-10-12T11:01:31Z','95e97050d7da8','76.0','3719653',154.5,'GBP','X','M','1B024896BG551374T','61ef17f2-8d1f-4f11-81d9-af8e6f514090',0,'2012-10-12 16:43:20'),(14,18,'0','Success','2012-10-14T09:42:01Z','82fb8792251ab','76.0','3719653',21.6,'GBP','X','M','5LN90216EV082831U','1a6a3010-bab5-4d57-b1da-30707edddd27',0,'2012-10-14 15:12:03'),(15,19,'0','Success','2012-10-14T10:55:08Z','dd14567946c5f','76.0','3719653',100.8,'GBP','X','M','1T341411C69561339','3ece4f80-7476-4773-b58a-89e30d8926d1',0,'2012-10-14 16:25:10'),(16,20,'4','Failure','2013-08-17T09:54:43Z','991f763218221','76.0','7165512',30.6,'GBP','','','','d80bde95-9480-46f9-aae3-786a0978634c',0,'2013-08-17 15:24:47');
/*!40000 ALTER TABLE `paypay_response` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postcode`
--

DROP TABLE IF EXISTS `postcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `postcode` (
  `postcode_id` int(11) NOT NULL AUTO_INCREMENT,
  `country _id` int(11) NOT NULL,
  `postcode` varchar(20) DEFAULT NULL,
  `postal_status` int(11) DEFAULT '0' COMMENT '0 for active and 1 for deactivated postcode.',
  `created_datetime` varchar(20) DEFAULT NULL,
  `modify_datetime` varchar(20) DEFAULT NULL,
  `modify_by` int(11) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT '0' COMMENT '0 for valid entry and 1 for invalid entry',
  `created_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`postcode_id`),
  KEY `foreign key of country` (`country _id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postcode`
--

LOCK TABLES `postcode` WRITE;
/*!40000 ALTER TABLE `postcode` DISABLE KEYS */;
INSERT INTO `postcode` VALUES (1,1,'IP27 0HP',0,NULL,NULL,NULL,0,NULL),(2,1,'IG1 1GR',0,NULL,NULL,NULL,0,NULL),(3,1,'SL3 8DS',0,NULL,NULL,NULL,0,NULL),(4,1,'S91 XT',0,NULL,NULL,NULL,0,NULL),(5,1,'S92 XT2',0,NULL,NULL,NULL,0,NULL);
/*!40000 ALTER TABLE `postcode` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `price_item`
--

DROP TABLE IF EXISTS `price_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `price_item` (
  `price_id` int(11) NOT NULL AUTO_INCREMENT,
  `country_id` int(11) NOT NULL,
  `weight_from` float DEFAULT NULL,
  `weight_to` float DEFAULT NULL,
  `price` float DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `modify_by` int(11) DEFAULT NULL,
  `created_datetime` varchar(20) DEFAULT NULL,
  `modify_datetime` varchar(20) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT '0' COMMENT '0 for valid entry and 1 for invalid entry.',
  PRIMARY KEY (`price_id`),
  KEY `fk_countrytbl` (`country_id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price_item`
--

LOCK TABLES `price_item` WRITE;
/*!40000 ALTER TABLE `price_item` DISABLE KEYS */;
INSERT INTO `price_item` VALUES (1,1,0,1,18,NULL,NULL,NULL,NULL,0),(2,1,1,2,9,NULL,NULL,NULL,NULL,0),(3,1,2,5,56,NULL,NULL,NULL,NULL,0),(4,1,5,10,89,NULL,NULL,NULL,NULL,0),(5,1,10,15,48,NULL,NULL,NULL,NULL,0),(6,1,15,20,50,NULL,NULL,NULL,NULL,0),(7,2,0,1,56,NULL,NULL,NULL,NULL,0),(8,2,1,2,84,NULL,NULL,NULL,NULL,0),(9,2,2,5,86,NULL,NULL,NULL,NULL,0),(10,2,5,10,77,NULL,NULL,NULL,NULL,0),(11,2,10,15,44,NULL,NULL,NULL,NULL,0),(12,2,15,20,55,NULL,NULL,NULL,NULL,0),(13,3,0,1,99,NULL,NULL,NULL,NULL,0),(14,3,1,2,55,NULL,NULL,NULL,NULL,0),(15,3,2,5,56,NULL,NULL,NULL,NULL,0),(16,3,5,10,59,NULL,NULL,NULL,NULL,0),(17,3,10,15,89,NULL,NULL,NULL,NULL,0),(18,3,15,20,22,NULL,NULL,NULL,NULL,0),(19,4,0,1,18,NULL,NULL,NULL,NULL,0),(20,4,1,2,9,NULL,NULL,NULL,NULL,0),(21,4,2,5,56,NULL,NULL,NULL,NULL,0),(22,4,5,10,89,NULL,NULL,NULL,NULL,0),(23,4,10,15,48,NULL,NULL,NULL,NULL,0),(24,4,15,20,50,NULL,NULL,NULL,NULL,0),(25,5,0,1,56,NULL,NULL,NULL,NULL,0),(26,5,1,2,84,NULL,NULL,NULL,NULL,0),(27,5,2,5,86,NULL,NULL,NULL,NULL,0),(28,5,5,10,77,NULL,NULL,NULL,NULL,0),(29,5,10,15,44,NULL,NULL,NULL,NULL,0),(30,5,15,20,55,NULL,NULL,NULL,NULL,0),(31,6,0,1,99,NULL,NULL,NULL,NULL,0),(32,6,1,2,55,NULL,NULL,NULL,NULL,0),(33,6,2,5,56,NULL,NULL,NULL,NULL,0),(34,6,5,10,59,NULL,NULL,NULL,NULL,0),(35,6,10,15,89,NULL,NULL,NULL,NULL,0),(36,6,15,20,22,NULL,NULL,NULL,NULL,0),(37,7,0,1,18,NULL,NULL,NULL,NULL,0),(38,7,1,2,9,NULL,NULL,NULL,NULL,0),(39,7,2,5,56,NULL,NULL,NULL,NULL,0),(40,7,5,10,89,NULL,NULL,NULL,NULL,0),(41,7,10,15,48,NULL,NULL,NULL,NULL,0),(42,7,15,20,50,NULL,NULL,NULL,NULL,0),(43,8,0,1,56,NULL,NULL,NULL,NULL,0),(44,8,1,2,84,NULL,NULL,NULL,NULL,0),(45,8,2,5,86,NULL,NULL,NULL,NULL,0),(46,8,5,10,77,NULL,NULL,NULL,NULL,0),(47,8,10,15,44,NULL,NULL,NULL,NULL,0),(48,8,15,20,55,NULL,NULL,NULL,NULL,0),(49,9,0,1,99,NULL,NULL,NULL,NULL,0),(50,9,1,2,55,NULL,NULL,NULL,NULL,0),(51,9,2,5,56,NULL,NULL,NULL,NULL,0),(52,9,5,10,59,NULL,NULL,NULL,NULL,0),(53,9,10,15,89,NULL,NULL,NULL,NULL,0),(54,9,15,20,22,NULL,NULL,NULL,NULL,0),(55,10,0,1,18,NULL,NULL,NULL,NULL,0),(56,10,1,2,9,NULL,NULL,NULL,NULL,0),(57,10,2,5,56,NULL,NULL,NULL,NULL,0),(58,10,5,10,89,NULL,NULL,NULL,NULL,0),(59,10,10,15,48,NULL,NULL,NULL,NULL,0),(60,10,15,20,50,NULL,NULL,NULL,NULL,0),(61,11,0,1,56,NULL,NULL,NULL,NULL,0),(62,11,1,2,84,NULL,NULL,NULL,NULL,0),(63,11,2,5,86,NULL,NULL,NULL,NULL,0),(64,11,5,10,77,NULL,NULL,NULL,NULL,0),(65,11,10,15,44,NULL,NULL,NULL,NULL,0),(66,11,15,20,55,NULL,NULL,NULL,NULL,0),(67,12,0,1,99,NULL,NULL,NULL,NULL,0),(68,12,1,2,55,NULL,NULL,NULL,NULL,0),(69,12,2,5,56,NULL,NULL,NULL,NULL,0),(70,12,5,10,59,NULL,NULL,NULL,NULL,0),(71,12,10,15,89,NULL,NULL,NULL,NULL,0),(72,12,15,20,22,NULL,NULL,NULL,NULL,0),(73,13,0,1,18,NULL,NULL,NULL,NULL,0),(74,13,1,2,9,NULL,NULL,NULL,NULL,0),(75,13,2,5,56,NULL,NULL,NULL,NULL,0),(76,13,5,10,89,NULL,NULL,NULL,NULL,0),(77,13,10,15,48,NULL,NULL,NULL,NULL,0),(78,13,15,20,50,NULL,NULL,NULL,NULL,0),(79,14,0,1,56,NULL,NULL,NULL,NULL,0),(80,14,1,2,84,NULL,NULL,NULL,NULL,0),(81,14,2,5,86,NULL,NULL,NULL,NULL,0),(82,14,5,10,77,NULL,NULL,NULL,NULL,0),(83,14,10,15,44,NULL,NULL,NULL,NULL,0),(84,14,15,20,55,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `price_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quote_details`
--

DROP TABLE IF EXISTS `quote_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `quote_details` (
  `quote_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(60) DEFAULT NULL,
  `email` varchar(60) DEFAULT NULL,
  `contanctno` varchar(50) DEFAULT NULL,
  `destination_country` varchar(50) DEFAULT NULL,
  `comments` varchar(200) DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `modify_by` int(11) DEFAULT NULL,
  `created_datetime` varchar(20) DEFAULT NULL,
  `modify_datetime` varchar(20) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT '0' COMMENT '0 for valid entry and 1 for invalid entry',
  PRIMARY KEY (`quote_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quote_details`
--

LOCK TABLES `quote_details` WRITE;
/*!40000 ALTER TABLE `quote_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `quote_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temporder`
--

DROP TABLE IF EXISTS `temporder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temporder` (
  `temp_id` int(11) NOT NULL,
  `user_key` varchar(70) DEFAULT NULL,
  `temp_weight` float DEFAULT NULL,
  `country_id` int(11) DEFAULT NULL,
  `temp_quantity` float DEFAULT NULL,
  `temp_price` float DEFAULT NULL,
  `temp_subtotal` float DEFAULT NULL,
  `checkout_flag` int(11) DEFAULT '0',
  `created_by` int(11) DEFAULT NULL,
  `modify_by` int(11) DEFAULT NULL,
  `created_datetime` varchar(20) DEFAULT NULL,
  `modify_datetime` varchar(20) DEFAULT NULL,
  `order_flag` int(11) DEFAULT '0' COMMENT '0 for default means that order is new , 1 is for already inserted into order table, this concept use when buymore facility is used becoz at that time the order which is new is only need to update in order table that’s why old order whose order_flag =1 will not be considered.',
  `address_flag` int(11) DEFAULT '0' COMMENT '0 for remaining delivery address details and 1 for entered delivery address details entry.',
  `delete_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`temp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temporder`
--

LOCK TABLES `temporder` WRITE;
/*!40000 ALTER TABLE `temporder` DISABLE KEYS */;
INSERT INTO `temporder` VALUES (1,'99362010-63ff-485b-81ac-916fd5c0b8d3',1,1,2,18,36,0,NULL,NULL,'2012-06-11 18:57:06','2012-06-11 18:57:06',0,1,0),(3,'11419063-4b9e-458f-8828-52b1975251ae',5,1,1,56,56,1,NULL,NULL,'2012-06-11 19:22:23','2012-06-11 19:22:23',1,1,0),(4,'d58b25ff-eaf7-4bc3-b03b-0ff0a4d7acfd',2,1,2,9,18,1,NULL,NULL,'2012-06-11 20:37:45','2012-06-11 20:37:45',1,1,0),(5,'d58b25ff-eaf7-4bc3-b03b-0ff0a4d7acfd',1,1,3,18,54,1,NULL,NULL,'2012-06-11 20:48:00','2012-06-11 20:48:00',1,1,0),(8,'cb181095-d8b1-4b7c-933f-81cb3e9a0aa5',2,1,1,9,9,1,NULL,NULL,'2012-06-15 14:00:58','2012-06-15 14:00:58',1,1,0),(10,'b6ba814a-0778-4ac2-8d08-509793b9db4f',1,1,1,18,18,1,NULL,NULL,'2012-06-16 16:03:28','2012-06-16 16:03:28',1,1,0),(11,'d71c022a-ee88-4c29-ad7b-264a2eac96ca',2,2,1,84,84,1,NULL,NULL,'2012-06-16 16:16:43','2012-06-16 16:16:43',1,1,0),(12,'f81b3a13-aff2-4ad7-8e6c-86d6ff5cc896',1,1,1,18,18,0,NULL,NULL,'2012-06-18 16:37:39','2012-06-18 16:37:39',0,0,0),(13,'9f72883b-b245-4e55-a97f-df4beff3c57d',1,1,1,18,18,0,NULL,NULL,'2012-06-18 16:39:24','2012-06-18 16:39:24',0,0,0),(14,'3328a82c-9b40-4bf3-b9e5-5335e17c25ee',1,1,1,18,18,1,NULL,NULL,'2012-06-18 17:10:13','2012-06-18 17:10:13',1,1,0),(15,'3328a82c-9b40-4bf3-b9e5-5335e17c25ee',2,1,3,9,27,1,NULL,NULL,'2012-06-18 17:13:31','2012-06-18 17:13:31',1,1,0),(16,'406a1273-231f-4a72-a93e-54cbad31254a',2,1,1,9,9,0,NULL,NULL,'2012-06-19 15:22:02','2012-06-19 15:22:02',0,1,0),(17,'aed5d996-d4a9-40c2-acdd-a92265301f53',2,1,1,9,9,1,NULL,NULL,'2012-06-19 15:29:08','2012-06-19 15:29:08',1,1,0),(19,'e6de01aa-3e5f-4cc8-8496-28e7b68467d6',1,1,1,18,18,0,NULL,NULL,'2012-06-20 14:52:21','2012-06-20 14:52:21',0,0,0),(20,'10c23a91-8291-4763-830f-6cd092b38f1f',15,3,5,89,445,0,NULL,NULL,'2012-06-20 19:14:26','2012-06-20 19:14:26',1,1,0),(21,'10c23a91-8291-4763-830f-6cd092b38f1f',1,1,1,18,18,0,NULL,NULL,'2012-06-20 19:17:10','2012-06-20 19:17:10',1,1,0),(22,'f10148ac-15bc-429b-b25f-a84801cfa0be',1,1,2,18,36,1,NULL,NULL,'2012-06-21 14:05:13','2012-06-21 14:05:13',1,1,0),(23,'1bae162f-95b3-4b87-a842-5ec14685542c',2,1,3,9,27,0,NULL,NULL,'2012-06-22 15:21:17','2012-06-22 15:21:17',0,1,0),(24,'1bae162f-95b3-4b87-a842-5ec14685542c',10,3,4,59,236,0,NULL,NULL,'2012-06-22 15:22:10','2012-06-22 15:22:10',0,1,0),(25,'9a1072b0-67a7-4c0e-b19f-6e7a0b8f9957',1,1,1,18,18,0,NULL,NULL,'2012-06-25 20:24:53','2012-06-25 20:24:53',0,1,0),(26,'c86638f0-129e-478e-a9e3-57c5ab03e421',1,1,1,18,18,0,NULL,NULL,'2012-06-25 20:32:15','2012-06-25 20:32:15',1,1,0),(27,'3dcc04e7-bb29-48bd-a3d8-e01d695a9e33',2,1,1,9,9,0,NULL,NULL,'2012-09-14 15:58:15','2012-09-14 15:58:15',0,1,0),(28,'a399b78d-aceb-49fc-ba22-dda7a79bc549',2,1,1,9,9,0,NULL,NULL,'2012-09-14 17:17:26','2012-09-14 17:17:26',0,0,0),(29,'da24708c-92c1-4d8d-b1b9-734fa34f893e',1,1,1,18,18,1,NULL,NULL,'2012-10-12 16:11:27','2012-10-12 16:11:27',1,1,0),(30,'3d83004e-231e-4731-97ab-e61475c099ab',1,1,1,18,18,1,NULL,NULL,'2012-10-12 16:13:24','2012-10-12 16:13:24',1,1,0),(31,'61ef17f2-8d1f-4f11-81d9-af8e6f514090',2,3,1,55,55,1,NULL,NULL,'2012-10-12 16:31:00','2012-10-12 16:31:00',1,1,0),(32,'61ef17f2-8d1f-4f11-81d9-af8e6f514090',2,3,1,55,55,1,NULL,NULL,'2012-10-12 16:31:12','2012-10-12 16:31:12',1,1,0),(33,'67586c5c-d93a-4b5e-962f-f6bf92b4de37',1,1,1,18,18,0,NULL,NULL,'2012-10-12 19:05:47','2012-10-12 19:05:47',0,1,0),(34,'554bed2c-2ad2-4f88-911f-93d069ee03f0',1,1,1,18,18,0,NULL,NULL,'2012-10-12 19:05:51','2012-10-12 19:05:51',0,1,0),(35,'d2d11af2-9401-4984-ab7c-bf51595862a0',1,1,1,18,18,0,NULL,NULL,'2012-10-12 19:10:10','2012-10-12 19:10:10',0,1,0),(36,'00ae8856-20e8-4e80-a299-89f430b379a4',1,1,1,18,18,0,NULL,NULL,'2012-10-13 11:48:47','2012-10-13 11:48:47',0,0,0),(37,'00ae8856-20e8-4e80-a299-89f430b379a4',2,1,1,9,9,0,NULL,NULL,'2012-10-13 13:13:32','2012-10-13 13:13:32',0,0,0),(38,'c845e6ab-e305-44c8-93a8-a8688c32e29b',2,1,1,9,9,0,NULL,NULL,'2012-10-13 14:56:12','2012-10-13 14:56:12',1,1,0),(39,'c845e6ab-e305-44c8-93a8-a8688c32e29b',2,3,1,55,55,0,NULL,NULL,'2012-10-13 15:00:09','2012-10-13 15:00:09',0,0,0),(40,'c845e6ab-e305-44c8-93a8-a8688c32e29b',2,3,1,55,55,0,NULL,NULL,'2012-10-13 15:05:39','2012-10-13 15:05:39',0,0,0),(46,'dc0cb57f-aa1f-4c1c-81b9-8ae7e6fa206d',1,1,1,18,18,0,NULL,NULL,'2012-10-13 15:38:39','2012-10-13 15:38:39',1,1,0),(47,'0eb090a0-39f8-43a3-9ca7-aadc72ec54f6',1,1,1,18,18,0,NULL,NULL,'2012-10-13 15:54:31','2012-10-13 15:54:31',1,1,0),(48,'0eb090a0-39f8-43a3-9ca7-aadc72ec54f6',1,1,5,18,90,0,NULL,NULL,'2012-10-13 15:54:48','2012-10-13 15:54:48',1,1,0),(53,'a72e5ccb-0241-4c04-bcdb-ec288c07c70d',1,3,1,99,99,0,NULL,NULL,'2012-10-14 09:27:12','2012-10-14 09:27:12',0,0,0),(54,'1a6a3010-bab5-4d57-b1da-30707edddd27',1,1,1,18,18,1,NULL,NULL,'2012-10-14 14:56:13','2012-10-14 14:56:13',1,1,0),(55,'3ece4f80-7476-4773-b58a-89e30d8926d1',2,2,1,84,84,1,NULL,NULL,'2012-10-14 16:06:54','2012-10-14 16:06:54',1,1,0),(56,'d80bde95-9480-46f9-aae3-786a0978634c',1,4,1,18,18,0,NULL,NULL,'2013-08-17 15:15:38','2013-08-17 15:15:38',1,1,0),(57,'d80bde95-9480-46f9-aae3-786a0978634c',1,1,1,18,18,0,NULL,NULL,'2013-08-17 15:28:08','2013-08-17 15:28:08',1,1,0);
/*!40000 ALTER TABLE `temporder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_register`
--

DROP TABLE IF EXISTS `user_register`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_register` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(100) DEFAULT NULL,
  `user_password` varchar(100) DEFAULT NULL,
  `user_key` varchar(70) DEFAULT NULL,
  `delete_flag` int(11) DEFAULT '0',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_register`
--

LOCK TABLES `user_register` WRITE;
/*!40000 ALTER TABLE `user_register` DISABLE KEYS */;
INSERT INTO `user_register` VALUES (1,'ppatel@cdl.uk.com','ppatel','11419063-4b9e-458f-8828-52b1975251ae',0),(2,'ppatel@cdl.uk.com','pukar','d58b25ff-eaf7-4bc3-b03b-0ff0a4d7acfd',0);
/*!40000 ALTER TABLE `user_register` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-07-27 17:17:25
