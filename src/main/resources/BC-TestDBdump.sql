-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: bc-test
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Computer Science'),(2,'Mathematics'),(3,'Physics'),(4,'Chemistry'),(5,'Biology'),(6,'History'),(7,'Literature'),(8,'Psychology'),(9,'Philosophy'),(10,'Law'),(11,'Economics'),(12,'Business'),(13,'Management'),(14,'Marketing'),(15,'Linguistics'),(16,'Education'),(17,'Art'),(18,'Music'),(19,'Theater'),(20,'Film');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department_lector`
--

DROP TABLE IF EXISTS `department_lector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department_lector` (
  `department_id` int NOT NULL,
  `lector_id` int NOT NULL,
  `is_head` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`department_id`,`lector_id`),
  KEY `lector_id` (`lector_id`),
  CONSTRAINT `department_id` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `lector_id` FOREIGN KEY (`lector_id`) REFERENCES `lector` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department_lector`
--

LOCK TABLES `department_lector` WRITE;
/*!40000 ALTER TABLE `department_lector` DISABLE KEYS */;
INSERT INTO `department_lector` VALUES (1,1,1),(1,21,0),(1,22,0),(2,2,1),(2,23,0),(2,24,0),(3,3,1),(3,25,0),(3,26,0),(4,4,1),(4,27,0),(4,28,0),(5,5,1),(5,29,0),(5,30,0),(6,6,1),(6,31,0),(6,32,0),(7,7,1),(7,33,0),(7,34,0),(8,8,1),(8,35,0),(8,36,0),(9,9,1),(9,37,0),(9,38,0),(10,10,1),(10,39,0),(10,40,0),(11,11,1),(11,41,0),(11,42,0),(12,12,1),(12,43,0),(12,44,0),(13,13,1),(13,45,0),(13,46,0),(14,14,1),(14,47,0),(14,48,0),(15,15,1),(15,49,0),(15,50,0),(16,16,1),(16,51,0),(16,52,0),(17,17,1),(17,53,0),(17,54,0),(18,18,1),(18,55,0),(18,56,0),(19,19,1),(19,57,0),(19,58,0),(20,20,1),(20,59,0),(20,60,0);
/*!40000 ALTER TABLE `department_lector` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lector`
--

DROP TABLE IF EXISTS `lector`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lector` (
  `id` int NOT NULL AUTO_INCREMENT,
  `full_name` varchar(255) NOT NULL,
  `degree` varchar(255) DEFAULT NULL,
  `salary` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lector`
--

LOCK TABLES `lector` WRITE;
/*!40000 ALTER TABLE `lector` DISABLE KEYS */;
INSERT INTO `lector` VALUES (1,'John Doe','Professor',100000.00),(2,'Jane Doe','Associate Professor',80000.00),(3,'Peter Smith','Assistant',60000.00),(4,'Alice Johnson','Professor',120000.00),(5,'Michael Williams','Associate Professor',90000.00),(6,'Karen Brown','Assistant',70000.00),(7,'David Davis','Professor',110000.00),(8,'Emily Wilson','Associate Professor',85000.00),(9,'Richard Martin','Assistant',65000.00),(10,'Laura Miller','Professor',130000.00),(11,'Brian Taylor','Associate Professor',95000.00),(12,'Nancy Anderson','Assistant',75000.00),(13,'Kevin Thompson','Professor',140000.00),(14,'Jennifer Clark','Associate Professor',100000.00),(15,'Mark Wright','Assistant',80000.00),(16,'Jessica Scott','Professor',150000.00),(17,'Ryan Robinson','Associate Professor',110000.00),(18,'Amanda Parker','Assistant',85000.00),(19,'Steven Turner','Professor',160000.00),(20,'Rachel Campbell','Associate Professor',120000.00),(21,'John Doe','Professor',100000.00),(22,'Jane Doe','Associate Professor',80000.00),(23,'Peter Smith','Assistant',60000.00),(24,'Alice Johnson','Professor',120000.00),(25,'Michael Williams','Associate Professor',90000.00),(26,'Karen Brown','Assistant',70000.00),(27,'David Davis','Professor',110000.00),(28,'Emily Wilson','Associate Professor',95000.00),(29,'William Brown','Assistant',65000.00),(30,'Olivia Davis','Professor',105000.00),(31,'Benjamin Jackson','Assistant',75000.00),(32,'Avery Taylor','Professor',125000.00),(33,'Ethan Lee','Associate Professor',85000.00),(34,'Mia Harris','Assistant',67000.00),(35,'Lucas Clark','Professor',115000.00),(36,'Aria Thomas','Associate Professor',92000.00),(37,'Oliver White','Assistant',62000.00),(38,'Lila Martin','Professor',130000.00),(39,'Jacob Perez','Associate Professor',88000.00),(40,'Ella Anderson','Assistant',69000.00),(41,'Noah Wilson','Professor',118000.00),(42,'Chloe Turner','Associate Professor',84000.00),(43,'Logan Nelson','Assistant',67000.00),(44,'Sofia Foster','Professor',105000.00),(45,'Aiden Collins','Associate Professor',92000.00),(46,'Lily Bryant','Assistant',71000.00),(47,'Caleb Gomez','Professor',125000.00),(48,'Evelyn Alexander','Associate Professor',89000.00),(49,'Harper Scott','Assistant',64000.00),(50,'Abigail Reed','Professor',110000.00),(51,'Isaac Murphy','Associate Professor',96000.00),(52,'Mila Turner','Assistant',68000.00),(53,'Daniel Parker','Professor',122000.00),(54,'Sophie Fisher','Associate Professor',87000.00),(55,'Matthew Ramirez','Assistant',63000.00),(56,'Victoria Campbell','Professor',128000.00),(57,'Cameron Price','Associate Professor',91000.00),(58,'Zoe Parker','Assistant',66000.00),(59,'Eli Gray','Professor',115000.00),(60,'Aaliyah Turner','Associate Professor',98000.00);
/*!40000 ALTER TABLE `lector` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-25 15:06:17
