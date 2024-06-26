-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: cinema_ticket_bill_db
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tblbill`
--

DROP TABLE IF EXISTS `tblbill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblbill` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `CustomerId` int NOT NULL,
  `bookingTime` datetime NOT NULL,
  `PaymentId` int NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKtblBill519872` (`PaymentId`),
  CONSTRAINT `FKtblBill519872` FOREIGN KEY (`PaymentId`) REFERENCES `tblpayment` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblbill`
--

LOCK TABLES `tblbill` WRITE;
/*!40000 ALTER TABLE `tblbill` DISABLE KEYS */;
INSERT INTO `tblbill` VALUES (1,3,'2024-04-11 17:44:19',1),(2,3,'2024-04-11 18:16:57',2),(3,4,'2024-04-11 18:19:57',3),(4,5,'2024-04-11 18:23:03',4),(5,6,'2024-04-11 18:23:11',5),(6,1,'2024-04-11 18:30:04',6),(7,2,'2024-04-11 18:32:37',7),(8,1,'2024-04-11 18:47:11',8),(9,3,'2024-04-11 18:47:29',9),(10,1,'2024-04-11 18:48:17',10),(11,5,'2024-04-12 15:40:35',11),(12,2,'2024-04-12 16:02:04',12),(13,2,'2024-04-12 20:23:28',13),(14,2,'2024-04-13 20:10:35',14),(15,4,'2024-04-14 14:31:05',15),(17,5,'2024-04-15 20:07:52',16),(18,1,'2024-04-15 20:20:36',17),(19,6,'2024-04-16 03:23:39',18),(20,2,'2024-04-16 03:23:59',19),(21,2,'2024-04-16 06:12:05',20),(22,2,'2024-04-16 06:27:13',21),(23,5,'2024-04-16 06:28:10',22),(24,5,'2024-04-16 06:28:26',23),(25,2,'2024-04-16 07:07:58',24),(26,2,'2024-04-16 07:15:40',25),(27,2,'2024-04-16 14:34:27',26),(28,2,'2024-05-07 16:26:38',27),(29,1,'2024-05-07 17:37:23',31);
/*!40000 ALTER TABLE `tblbill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblpayment`
--

DROP TABLE IF EXISTS `tblpayment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblpayment` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `PaymentMethod` varchar(255) NOT NULL,
  `PaymentNote` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblpayment`
--

LOCK TABLES `tblpayment` WRITE;
/*!40000 ALTER TABLE `tblpayment` DISABLE KEYS */;
INSERT INTO `tblpayment` VALUES (1,'momo',NULL),(2,'paypal',NULL),(3,'momo',NULL),(4,'paypal',NULL),(5,'momo',NULL),(6,'vnpay',NULL),(7,'momo',NULL),(8,'momo',NULL),(9,'paypal',NULL),(10,'momo',NULL),(11,'paypal',NULL),(12,'paypal',NULL),(13,'paypal',NULL),(14,'paypal',NULL),(15,'momo',NULL),(16,'paypal',NULL),(17,'vnpay',NULL),(18,'momo',NULL),(19,'momo',NULL),(20,'paypal',NULL),(21,'paypal',NULL),(22,'momo',NULL),(23,'momo',NULL),(24,'momo',NULL),(25,'momo',NULL),(26,'paypal',NULL),(27,'paypal',NULL),(28,'paypal',NULL),(29,'momo',NULL),(30,'vnpay',NULL),(31,'paypal',NULL);
/*!40000 ALTER TABLE `tblpayment` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-14  3:42:07
