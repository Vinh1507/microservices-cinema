-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: cinema_movie_show_db
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
-- Table structure for table `tblmovie`
--

DROP TABLE IF EXISTS `tblmovie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblmovie` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(255) DEFAULT NULL,
  `Category` varchar(255) DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `Price` float DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblmovie`
--

LOCK TABLES `tblmovie` WRITE;
/*!40000 ALTER TABLE `tblmovie` DISABLE KEYS */;
INSERT INTO `tblmovie` VALUES (1,'The Shawshank Redemption','Drama','Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.',100000000),(2,'The Godfather','Crime','The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.',200000000),(3,'The Dark Knight','Action','When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice.',300000000),(4,'Pulp Fiction','Crime','The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.',400000000),(5,'The Lord of the Rings: The Return of the King','Adventure','Gandalf and Aragorn lead the World of Men against Sauron\'s army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.',500000000),(6,'Schindler List','Biography','In German-occupied Poland during World War II, industrialist Oskar Schindler gradually becomes concerned for his Jewish workforce after witnessing their persecution by the Nazis.',600000000),(7,'Inception','Action','A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O.',700000000),(8,'Forrest Gump','Drama','The presidencies of Kennedy and Johnson, the Vietnam War, the Watergate scandal and other historical events unfold from the perspective of an Alabama man with an IQ of 75, whose only desire is to be reunited with his childhood sweetheart.',800000000),(9,'Fight Club','Drama','An insomniac office worker and a devil-may-care soapmaker form an underground fight club that evolves into something much, much more.',900000000),(10,'The Matrix','Action','A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.',1000000000);
/*!40000 ALTER TABLE `tblmovie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblmoviedetail`
--

DROP TABLE IF EXISTS `tblmoviedetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblmoviedetail` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `MovieId` int NOT NULL,
  `ImportPrice` float NOT NULL,
  `Note` varchar(255) DEFAULT NULL,
  `ImportingBillId` int DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKtblMovieDe537485` (`MovieId`),
  CONSTRAINT `FKtblMovieDe537485` FOREIGN KEY (`MovieId`) REFERENCES `tblmovie` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblmoviedetail`
--

LOCK TABLES `tblmoviedetail` WRITE;
/*!40000 ALTER TABLE `tblmoviedetail` DISABLE KEYS */;
INSERT INTO `tblmoviedetail` VALUES (2,1,100000000,NULL,3),(3,2,200000000,NULL,3),(4,4,400000000,NULL,3),(5,5,500000000,NULL,3),(6,4,400000000,NULL,4),(7,7,700000000,NULL,4),(8,8,800000000,NULL,4),(9,1,100000000,NULL,5),(10,10,1000000000,NULL,5),(11,1,100000000,NULL,6),(12,2,200000000,NULL,6),(13,1,100000000,NULL,7),(14,2,200000000,NULL,7),(15,1,100000000,NULL,8),(16,3,300000000,NULL,8),(17,1,100000000,NULL,9),(18,2,200000000,NULL,9),(19,3,300000000,NULL,9);
/*!40000 ALTER TABLE `tblmoviedetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblmovieshowschedule`
--

DROP TABLE IF EXISTS `tblmovieshowschedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblmovieshowschedule` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `MovieId` int NOT NULL,
  `RoomId` int NOT NULL,
  `ScheduleId` int NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKtblMovieSh940938` (`RoomId`),
  KEY `FKtblMovieSh763279` (`MovieId`),
  KEY `FKtblMovieSh480088` (`ScheduleId`),
  CONSTRAINT `FKtblMovieSh480088` FOREIGN KEY (`ScheduleId`) REFERENCES `tblschedule` (`Id`),
  CONSTRAINT `FKtblMovieSh763279` FOREIGN KEY (`MovieId`) REFERENCES `tblmovie` (`Id`),
  CONSTRAINT `FKtblMovieSh940938` FOREIGN KEY (`RoomId`) REFERENCES `tblroom` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblmovieshowschedule`
--

LOCK TABLES `tblmovieshowschedule` WRITE;
/*!40000 ALTER TABLE `tblmovieshowschedule` DISABLE KEYS */;
INSERT INTO `tblmovieshowschedule` VALUES (1,8,4,8),(2,3,1,5),(3,4,3,7),(4,2,10,6),(5,7,7,10),(6,1,6,9),(7,1,5,3),(8,10,1,2),(9,2,2,4),(10,4,8,1),(11,5,4,6),(12,7,8,10),(13,6,10,8),(14,6,6,5),(15,4,3,7),(16,9,7,1),(17,1,3,3),(18,7,2,2),(19,10,9,4),(20,2,8,9),(21,5,1,4),(22,3,8,5),(23,4,3,9),(24,10,10,6),(25,8,7,10),(26,9,2,8),(27,3,10,3),(28,5,9,7),(29,10,2,2),(30,5,3,1),(31,7,2,2),(32,5,9,1),(33,8,6,10),(34,10,3,8),(35,7,8,5),(36,7,6,6),(37,4,7,9),(38,1,2,7),(39,5,1,3),(40,5,4,4),(41,9,2,6),(42,2,10,9),(43,6,10,8),(44,4,9,5),(45,3,10,1),(46,10,1,10),(47,4,7,3),(48,3,7,7),(49,2,8,4),(50,3,1,2);
/*!40000 ALTER TABLE `tblmovieshowschedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblroom`
--

DROP TABLE IF EXISTS `tblroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblroom` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `Type` varchar(255) DEFAULT NULL,
  `Position` varchar(255) DEFAULT NULL,
  `totalRows` int DEFAULT NULL,
  `totalColumns` int DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblroom`
--

LOCK TABLES `tblroom` WRITE;
/*!40000 ALTER TABLE `tblroom` DISABLE KEYS */;
INSERT INTO `tblroom` VALUES (1,'Standard','Front',5,5),(2,'VIP','Back',5,5),(3,'Standard','Middle',5,5),(4,'Standard','Front',5,5),(5,'VIP','Middle',5,5),(6,'Standard','Back',5,5),(7,'VIP','Front',5,5),(8,'Standard','Middle',5,5),(9,'Standard','Back',5,5),(10,'VIP','Front',5,5);
/*!40000 ALTER TABLE `tblroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblschedule`
--

DROP TABLE IF EXISTS `tblschedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblschedule` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `StartTime` varchar(255) DEFAULT NULL,
  `EndTime` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=228 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblschedule`
--

LOCK TABLES `tblschedule` WRITE;
/*!40000 ALTER TABLE `tblschedule` DISABLE KEYS */;
INSERT INTO `tblschedule` VALUES (1,'2024-04-13 08:02:00','2024-04-13 10:02:00'),(2,'2024-04-09 11:00:00','2024-04-09 13:00:00'),(3,'2024-05-13 23:10:00','2024-05-14 01:10:00'),(4,'2024-04-16 22:58:00','2024-04-17 00:58:00'),(5,'2024-04-08 11:12:00','2024-04-08 13:12:00'),(6,'2024-04-13 06:41:00','2024-04-13 08:41:00'),(7,'2024-04-14 22:55:00','2024-04-15 00:55:00'),(8,'2024-04-15 02:15:00','2024-04-15 04:15:00'),(9,'2024-04-17 19:17:00','2024-04-17 21:17:00'),(10,'2024-05-18 05:06:00','2024-05-18 07:06:00'),(11,'2024-04-16 19:36:00','2024-04-16 21:36:00'),(12,'2024-04-13 01:30:00','2024-04-13 03:30:00'),(13,'2024-04-12 13:37:00','2024-04-12 15:37:00'),(14,'2024-04-11 23:43:00','2024-04-12 01:43:00'),(15,'2024-04-15 14:42:00','2024-04-15 16:42:00'),(16,'2024-04-15 14:43:00','2024-04-15 16:43:00'),(17,'2024-04-16 01:51:00','2024-04-16 03:51:00'),(18,'2024-04-08 12:28:00','2024-04-08 14:28:00'),(19,'2024-04-16 00:28:00','2024-04-16 02:28:00'),(20,'2024-04-10 23:01:00','2024-04-11 01:01:00'),(21,'2024-04-09 20:46:00','2024-04-09 22:46:00'),(22,'2024-04-10 23:04:00','2024-04-11 01:04:00'),(23,'2024-04-12 03:22:00','2024-04-12 05:22:00'),(24,'2024-04-12 22:30:00','2024-04-13 00:30:00'),(25,'2024-04-15 23:50:00','2024-04-16 01:50:00'),(26,'2024-04-10 15:26:00','2024-04-10 17:26:00'),(27,'2024-04-11 07:36:00','2024-04-11 09:36:00'),(28,'2024-04-08 15:59:00','2024-04-08 17:59:00'),(29,'2024-04-17 23:55:00','2024-04-18 01:55:00'),(30,'2024-04-14 15:08:00','2024-04-14 17:08:00'),(31,'2024-04-16 14:38:00','2024-04-16 16:38:00'),(32,'2024-04-11 23:43:00','2024-04-12 01:43:00'),(33,'2024-04-14 04:55:00','2024-04-14 06:55:00'),(34,'2024-04-08 12:23:00','2024-04-08 14:23:00'),(35,'2024-04-12 02:03:00','2024-04-12 04:03:00'),(36,'2024-04-08 21:30:00','2024-04-08 23:30:00'),(37,'2024-04-15 10:48:00','2024-04-15 12:48:00'),(38,'2024-04-15 02:26:00','2024-04-15 04:26:00'),(39,'2024-04-16 00:28:00','2024-04-16 02:28:00'),(40,'2024-04-11 05:06:00','2024-04-11 07:06:00'),(41,'2024-04-16 00:27:00','2024-04-16 02:27:00'),(42,'2024-04-10 00:13:00','2024-04-10 02:13:00'),(43,'2024-04-08 19:42:00','2024-04-08 21:42:00'),(44,'2024-04-09 17:09:00','2024-04-09 19:09:00'),(45,'2024-04-13 11:37:00','2024-04-13 13:37:00'),(46,'2024-04-14 10:15:00','2024-04-14 12:15:00'),(47,'2024-04-17 01:19:00','2024-04-17 03:19:00'),(48,'2024-04-12 10:47:00','2024-04-12 12:47:00'),(49,'2024-04-14 15:25:00','2024-04-14 17:25:00'),(50,'2024-04-09 15:40:00','2024-04-09 17:40:00'),(51,'2024-04-12 01:59:00','2024-04-12 03:59:00'),(52,'2024-04-16 04:30:00','2024-04-16 06:30:00'),(53,'2024-04-17 01:34:00','2024-04-17 03:34:00'),(54,'2024-04-14 12:42:00','2024-04-14 14:42:00'),(55,'2024-04-17 16:28:00','2024-04-17 18:28:00'),(56,'2024-04-11 06:16:00','2024-04-11 08:16:00'),(57,'2024-04-13 03:53:00','2024-04-13 05:53:00'),(58,'2024-04-08 17:23:00','2024-04-08 19:23:00'),(59,'2024-04-15 15:57:00','2024-04-15 17:57:00'),(60,'2024-04-16 06:56:00','2024-04-16 08:56:00'),(61,'2024-04-16 06:54:00','2024-04-16 08:54:00'),(62,'2024-04-15 21:23:00','2024-04-15 23:23:00'),(63,'2024-04-10 22:02:00','2024-04-11 00:02:00'),(64,'2024-04-11 15:06:00','2024-04-11 17:06:00'),(65,'2024-04-14 22:45:00','2024-04-15 00:45:00'),(66,'2024-04-17 12:39:00','2024-04-17 14:39:00'),(67,'2024-04-16 00:41:00','2024-04-16 02:41:00'),(68,'2024-04-12 01:03:00','2024-04-12 03:03:00'),(69,'2024-04-08 01:08:00','2024-04-08 03:08:00'),(70,'2024-04-13 04:24:00','2024-04-13 06:24:00'),(71,'2024-04-12 23:37:00','2024-04-13 01:37:00'),(72,'2024-04-09 21:03:00','2024-04-09 23:03:00'),(73,'2024-04-13 18:08:00','2024-04-13 20:08:00'),(74,'2024-04-11 06:25:00','2024-04-11 08:25:00'),(75,'2024-04-10 23:06:00','2024-04-11 01:06:00'),(76,'2024-04-14 21:32:00','2024-04-14 23:32:00'),(77,'2024-04-08 11:19:00','2024-04-08 13:19:00'),(78,'2024-04-09 00:28:00','2024-04-09 02:28:00'),(79,'2024-04-11 08:38:00','2024-04-11 10:38:00'),(80,'2024-04-09 21:00:00','2024-04-09 23:00:00'),(81,'2024-04-11 13:48:00','2024-04-11 15:48:00'),(82,'2024-04-11 11:20:00','2024-04-11 13:20:00'),(83,'2024-04-10 03:02:00','2024-04-10 05:02:00'),(84,'2024-04-15 17:23:00','2024-04-15 19:23:00'),(85,'2024-04-15 09:54:00','2024-04-15 11:54:00'),(86,'2024-04-10 16:39:00','2024-04-10 18:39:00'),(87,'2024-04-10 00:35:00','2024-04-10 02:35:00'),(88,'2024-04-16 07:02:00','2024-04-16 09:02:00'),(89,'2024-04-11 12:42:00','2024-04-11 14:42:00'),(90,'2024-04-16 08:04:00','2024-04-16 10:04:00'),(91,'2024-04-11 07:43:00','2024-04-11 09:43:00'),(92,'2024-04-14 23:04:00','2024-04-15 01:04:00'),(93,'2024-04-11 15:08:00','2024-04-11 17:08:00'),(94,'2024-04-15 05:58:00','2024-04-15 07:58:00'),(95,'2024-04-09 19:42:00','2024-04-09 21:42:00'),(96,'2024-04-08 06:10:00','2024-04-08 08:10:00'),(97,'2024-04-17 11:23:00','2024-04-17 13:23:00'),(98,'2024-04-13 11:51:00','2024-04-13 13:51:00'),(99,'2024-04-16 11:58:00','2024-04-16 13:58:00'),(100,'2024-04-12 07:07:00','2024-04-12 09:07:00');
/*!40000 ALTER TABLE `tblschedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblseat`
--

DROP TABLE IF EXISTS `tblseat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblseat` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `RoomId` int NOT NULL,
  `Position` varchar(255) DEFAULT NULL,
  `seatRow` int DEFAULT NULL,
  `seatColumn` int DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKtblSeat73845` (`RoomId`),
  CONSTRAINT `FKtblSeat73845` FOREIGN KEY (`RoomId`) REFERENCES `tblroom` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblseat`
--

LOCK TABLES `tblseat` WRITE;
/*!40000 ALTER TABLE `tblseat` DISABLE KEYS */;
INSERT INTO `tblseat` VALUES (46,1,'Row A Seat 1',1,1),(47,1,'Row A Seat 2',1,2),(48,1,'Row A Seat 3',1,3),(49,1,'Row A Seat 4',1,4),(50,1,'Row A Seat 5',1,5),(51,1,'Row B Seat 1',2,1),(52,1,'Row B Seat 2',2,2),(53,1,'Row B Seat 3',2,3),(54,1,'Row B Seat 4',2,4),(55,1,'Row B Seat 5',2,5),(56,1,'Row C Seat 1',3,1),(57,1,'Row C Seat 2',3,2),(58,1,'Row C Seat 3',3,3),(59,1,'Row C Seat 4',3,4),(60,1,'Row C Seat 5',3,5),(61,1,'Row D Seat 1',4,1),(62,1,'Row D Seat 2',4,2),(63,1,'Row D Seat 3',4,3),(64,1,'Row D Seat 4',4,4),(65,1,'Row D Seat 5',4,5),(66,1,'Row E Seat 1',5,1),(67,1,'Row E Seat 2',5,2),(68,1,'Row E Seat 3',5,3),(69,1,'Row E Seat 4',5,4),(70,1,'Row E Seat 5',5,5),(71,2,'Row A Seat 1',1,1),(72,2,'Row A Seat 2',1,2),(73,2,'Row A Seat 3',1,3),(74,2,'Row A Seat 4',1,4),(75,2,'Row A Seat 5',1,5),(76,2,'Row B Seat 1',2,1),(77,2,'Row B Seat 2',2,2),(78,2,'Row B Seat 3',2,3),(79,2,'Row B Seat 4',2,4),(80,2,'Row B Seat 5',2,5),(81,2,'Row C Seat 1',3,1),(82,2,'Row C Seat 2',3,2),(83,2,'Row C Seat 3',3,3),(84,2,'Row C Seat 4',3,4),(85,2,'Row C Seat 5',3,5),(86,2,'Row D Seat 1',4,1),(87,2,'Row D Seat 2',4,2),(88,2,'Row D Seat 3',4,3),(89,2,'Row D Seat 4',4,4),(90,2,'Row D Seat 5',4,5),(91,2,'Row E Seat 1',5,1),(92,2,'Row E Seat 2',5,2),(93,2,'Row E Seat 3',5,3),(94,2,'Row E Seat 4',5,4),(95,2,'Row E Seat 5',5,5),(96,3,'Row A Seat 1',1,1),(97,3,'Row A Seat 2',1,2),(98,3,'Row A Seat 3',1,3),(99,3,'Row A Seat 4',1,4),(100,3,'Row A Seat 5',1,5),(101,3,'Row B Seat 1',2,1),(102,3,'Row B Seat 2',2,2),(103,3,'Row B Seat 3',2,3),(104,3,'Row B Seat 4',2,4),(105,3,'Row B Seat 5',2,5),(106,3,'Row C Seat 1',3,1),(107,3,'Row C Seat 2',3,2),(108,3,'Row C Seat 3',3,3),(109,3,'Row C Seat 4',3,4),(110,3,'Row C Seat 5',3,5),(111,3,'Row D Seat 1',4,1),(112,3,'Row D Seat 2',4,2),(113,3,'Row D Seat 3',4,3),(114,3,'Row D Seat 4',4,4),(115,3,'Row D Seat 5',4,5),(116,3,'Row E Seat 1',5,1),(117,3,'Row E Seat 2',5,2),(118,3,'Row E Seat 3',5,3),(119,3,'Row E Seat 4',5,4),(120,3,'Row E Seat 5',5,5),(121,4,'Row A Seat 1',1,1),(122,4,'Row A Seat 2',1,2),(123,4,'Row A Seat 3',1,3),(124,4,'Row A Seat 4',1,4),(125,4,'Row A Seat 5',1,5),(126,4,'Row B Seat 1',2,1),(127,4,'Row B Seat 2',2,2),(128,4,'Row B Seat 3',2,3),(129,4,'Row B Seat 4',2,4),(130,4,'Row B Seat 5',2,5),(131,4,'Row C Seat 1',3,1),(132,4,'Row C Seat 2',3,2),(133,4,'Row C Seat 3',3,3),(134,4,'Row C Seat 4',3,4),(135,4,'Row C Seat 5',3,5),(136,4,'Row D Seat 1',4,1),(137,4,'Row D Seat 2',4,2),(138,4,'Row D Seat 3',4,3),(139,4,'Row D Seat 4',4,4),(140,4,'Row D Seat 5',4,5),(141,4,'Row E Seat 1',5,1),(142,4,'Row E Seat 2',5,2),(143,4,'Row E Seat 3',5,3),(144,4,'Row E Seat 4',5,4),(145,4,'Row E Seat 5',5,5),(146,5,'Row A Seat 1',1,1),(147,5,'Row A Seat 2',1,2),(148,5,'Row A Seat 3',1,3),(149,5,'Row A Seat 4',1,4),(150,5,'Row A Seat 5',1,5),(151,5,'Row B Seat 1',2,1),(152,5,'Row B Seat 2',2,2),(153,5,'Row B Seat 3',2,3),(154,5,'Row B Seat 4',2,4),(155,5,'Row B Seat 5',2,5),(156,5,'Row C Seat 1',3,1),(157,5,'Row C Seat 2',3,2),(158,5,'Row C Seat 3',3,3),(159,5,'Row C Seat 4',3,4),(160,5,'Row C Seat 5',3,5),(161,5,'Row D Seat 1',4,1),(162,5,'Row D Seat 2',4,2),(163,5,'Row D Seat 3',4,3),(164,5,'Row D Seat 4',4,4),(165,5,'Row D Seat 5',4,5),(166,5,'Row E Seat 1',5,1),(167,5,'Row E Seat 2',5,2),(168,5,'Row E Seat 3',5,3),(169,5,'Row E Seat 4',5,4),(170,5,'Row E Seat 5',5,5);
/*!40000 ALTER TABLE `tblseat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tblticket`
--

DROP TABLE IF EXISTS `tblticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tblticket` (
  `Id` int NOT NULL AUTO_INCREMENT,
  `SeatId` int NOT NULL,
  `MovieShowScheduleId` int NOT NULL,
  `Price` float NOT NULL,
  `IsAvailable` int DEFAULT NULL,
  `BillId` int DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKtblTicket140193` (`SeatId`),
  KEY `FKtblTicket745065` (`MovieShowScheduleId`),
  CONSTRAINT `FKtblTicket140193` FOREIGN KEY (`SeatId`) REFERENCES `tblseat` (`Id`),
  CONSTRAINT `FKtblTicket745065` FOREIGN KEY (`MovieShowScheduleId`) REFERENCES `tblmovieshowschedule` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=226 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tblticket`
--

LOCK TABLES `tblticket` WRITE;
/*!40000 ALTER TABLE `tblticket` DISABLE KEYS */;
INSERT INTO `tblticket` VALUES (76,146,7,50000,1,3),(77,147,7,50000,1,NULL),(78,148,7,50000,1,4),(79,149,7,50000,1,4),(80,150,7,50000,1,NULL),(81,151,7,50000,1,3),(82,152,7,50000,1,3),(83,153,7,50000,1,NULL),(84,154,7,50000,1,NULL),(85,155,7,50000,1,17),(86,156,7,50000,1,NULL),(87,157,7,50000,1,13),(88,158,7,50000,1,NULL),(89,159,7,50000,1,NULL),(90,160,7,50000,1,NULL),(91,161,7,50000,1,NULL),(92,162,7,50000,1,13),(93,163,7,50000,1,NULL),(94,164,7,50000,1,4),(95,165,7,50000,1,17),(96,166,7,50000,1,6),(97,167,7,50000,1,6),(98,168,7,50000,1,6),(99,169,7,50000,1,5),(100,170,7,50000,1,NULL),(101,96,17,50000,1,18),(102,97,17,50000,1,18),(103,98,17,50000,1,18),(104,99,17,50000,1,NULL),(105,100,17,50000,1,26),(106,101,17,50000,1,NULL),(107,102,17,50000,1,NULL),(108,103,17,50000,1,NULL),(109,104,17,50000,1,NULL),(110,105,17,50000,1,26),(111,106,17,50000,1,NULL),(112,107,17,50000,1,NULL),(113,108,17,50000,1,NULL),(114,109,17,50000,1,22),(115,110,17,50000,1,22),(116,111,17,50000,1,NULL),(117,112,17,50000,1,NULL),(118,113,17,50000,1,NULL),(119,114,17,50000,1,22),(120,115,17,50000,1,22),(121,116,17,50000,1,NULL),(122,117,17,50000,1,NULL),(123,118,17,50000,1,NULL),(124,119,17,50000,1,NULL),(125,120,17,50000,1,NULL),(126,71,38,50000,1,NULL),(127,72,38,50000,1,NULL),(128,73,38,50000,1,NULL),(129,74,38,50000,1,NULL),(130,75,38,50000,1,NULL),(131,76,38,50000,1,NULL),(132,77,38,50000,1,NULL),(133,78,38,50000,1,21),(134,79,38,50000,1,21),(135,80,38,50000,1,NULL),(136,81,38,50000,1,NULL),(137,82,38,50000,1,NULL),(138,83,38,50000,1,NULL),(139,84,38,50000,1,21),(140,85,38,50000,1,NULL),(141,86,38,50000,1,NULL),(142,87,38,50000,1,NULL),(143,88,38,50000,1,NULL),(144,89,38,50000,1,NULL),(145,90,38,50000,1,NULL),(146,91,38,50000,1,NULL),(147,92,38,50000,1,25),(148,93,38,50000,1,25),(149,94,38,50000,1,NULL),(150,95,38,50000,1,NULL),(151,71,9,60000,1,1),(152,72,9,60000,1,1),(153,73,9,60000,1,1),(154,74,9,60000,1,1),(155,75,9,60000,1,2),(156,76,9,60000,1,2),(157,77,9,60000,1,2),(158,78,9,60000,1,1),(159,79,9,60000,1,NULL),(160,80,9,60000,1,NULL),(161,81,9,60000,1,NULL),(162,82,9,60000,1,14),(163,83,9,60000,1,7),(164,84,9,60000,1,7),(165,85,9,60000,1,19),(166,86,9,60000,1,NULL),(167,87,9,60000,1,14),(168,88,9,60000,1,11),(169,89,9,60000,1,11),(170,90,9,60000,1,11),(171,91,9,60000,1,NULL),(172,92,9,60000,1,11),(173,93,9,60000,1,NULL),(174,94,9,60000,1,8),(175,95,9,60000,1,8),(176,46,2,70000,1,12),(177,47,2,70000,1,NULL),(178,48,2,70000,1,NULL),(179,49,2,70000,1,NULL),(180,50,2,70000,1,NULL),(181,51,2,70000,1,NULL),(182,52,2,70000,1,NULL),(183,53,2,70000,1,15),(184,54,2,70000,1,NULL),(185,55,2,70000,1,NULL),(186,56,2,70000,1,28),(187,57,2,70000,1,NULL),(188,58,2,70000,1,NULL),(189,59,2,70000,1,NULL),(190,60,2,70000,1,9),(191,61,2,70000,1,10),(192,62,2,70000,1,10),(193,63,2,70000,1,10),(194,64,2,70000,1,10),(195,65,2,70000,1,NULL),(196,66,2,70000,1,10),(197,67,2,70000,1,10),(198,68,2,70000,1,10),(199,69,2,70000,1,10),(200,70,2,70000,1,29),(201,46,50,70000,1,23),(202,47,50,70000,1,NULL),(203,48,50,70000,1,20),(204,49,50,70000,1,20),(205,50,50,70000,1,NULL),(206,51,50,70000,1,NULL),(207,52,50,70000,1,NULL),(208,53,50,70000,1,NULL),(209,54,50,70000,1,NULL),(210,55,50,70000,1,24),(211,56,50,70000,1,20),(212,57,50,70000,1,27),(213,58,50,70000,1,27),(214,59,50,70000,1,27),(215,60,50,70000,1,24),(216,61,50,70000,1,20),(217,62,50,70000,1,NULL),(218,63,50,70000,1,20),(219,64,50,70000,1,20),(220,65,50,70000,1,20),(221,66,50,70000,1,20),(222,67,50,70000,1,NULL),(223,68,50,70000,1,20),(224,69,50,70000,1,20),(225,70,50,70000,1,20);
/*!40000 ALTER TABLE `tblticket` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-08 23:35:30
