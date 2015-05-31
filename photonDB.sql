-- MySQL dump 10.13  Distrib 5.6.17, for Win64 (x86_64)
--
-- Host: localhost    Database: photon
-- ------------------------------------------------------
-- Server version	5.6.21-log

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
-- Table structure for table `top_list`
--

DROP TABLE IF EXISTS `top_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `top_list` (
  `id` int(8) NOT NULL,
  `name` varchar(25) DEFAULT NULL,
  `placeInTopList` int(6) DEFAULT NULL,
  `score` int(6) DEFAULT '0',
  `bestTime` time DEFAULT '00:00:00',
  `gameDate` datetime(6) DEFAULT NULL,
  `isBestScore` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `top_list`
--

LOCK TABLES `top_list` WRITE;
/*!40000 ALTER TABLE `top_list` DISABLE KEYS */;
INSERT INTO `top_list` VALUES (1,'Player1',3,13269,'00:02:59','2015-04-13 00:00:00.000000',1),(2,'Player2',9,665,'00:00:42','2015-05-02 00:00:00.000000',1),(3,'Player3',41,27,'00:00:12','2015-04-27 00:00:00.000000',1),(4,'Player4',5,4651,'00:01:17','2015-04-27 00:00:00.000000',1),(5,'Player5',1,39756,'00:02:41','2015-05-03 00:00:00.000000',1),(6,'Player6',25,17,'00:00:09','2015-05-03 00:00:00.000000',1),(7,'Player7',50,13,'00:00:06','2015-04-27 00:00:00.000000',1),(8,'Player8',17,65,'00:00:13','2015-05-03 00:00:00.000000',1),(9,'Player9',47,16,'00:00:06','2015-04-27 00:00:00.000000',1),(10,'Player10',30,10,'00:00:05','2015-05-07 00:00:00.000000',1),(11,'Player_2',48,16,'00:00:06','2015-04-27 00:00:00.000000',1),(12,'Player_BOT',19,12,'00:00:05','2015-05-07 00:00:00.000000',1),(14,'Player14',42,26,'00:00:08','2015-04-27 00:00:00.000000',1),(18,'Player18',38,32,'00:00:09','2015-04-27 00:00:00.000000',1),(19,'Player_2',39,32,'00:00:09','2015-04-27 00:00:00.000000',1),(22,'Player22',44,20,'00:00:08','2015-04-27 00:00:00.000000',1),(23,'Player_2',45,20,'00:00:08','2015-04-27 00:00:00.000000',1),(26,'Player26',11,122,'00:00:22','2015-04-27 00:00:00.000000',1),(27,'Player_2',12,121,'00:00:22','2015-04-27 00:00:00.000000',1),(28,'Player28',13,110,'00:00:20','2015-04-27 00:00:00.000000',1),(29,'Player_2',15,109,'00:00:20','2015-04-27 00:00:00.000000',1),(68,'Player68',4,10751,'00:01:55','2015-04-17 00:00:00.000000',1),(75,'Player75',15,828,'00:00:46','2015-04-17 00:00:00.000000',1),(157,'Player157',37,33,'00:00:13','2015-05-02 00:00:00.000000',1),(158,'Player158',6,2497,'00:00:57','2015-05-02 00:00:00.000000',1),(159,'Player159',7,1765,'00:00:56','2015-05-02 00:00:00.000000',1),(162,'Player162',16,95,'00:00:03','2015-05-02 00:00:00.000000',1),(164,'Player164',10,147,'00:00:25','2015-05-02 00:00:00.000000',1),(166,'Player166',2,21388,'00:02:35','2015-05-02 00:00:00.000000',1),(167,'Player167',8,1420,'00:01:03','2015-05-02 00:00:00.000000',1);
/*!40000 ALTER TABLE `top_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'photon'
--

--
-- Dumping routines for database 'photon'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-18 21:16:28