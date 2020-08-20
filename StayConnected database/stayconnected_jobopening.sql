-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: stayconnected
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `jobopening`
--

DROP TABLE IF EXISTS `jobopening`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobopening` (
  `jobID` int(11) NOT NULL,
  `position` varchar(50) NOT NULL,
  `location` varchar(200) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `payRate` varchar(150) DEFAULT NULL,
  `jobDescription` varchar(200) NOT NULL,
  `hoursperWeek` int(11) DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `companyName` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`jobID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobopening`
--

LOCK TABLES `jobopening` WRITE;
/*!40000 ALTER TABLE `jobopening` DISABLE KEYS */;
INSERT INTO `jobopening` VALUES (101,'software Developer','Sanfransisco,CA','2018-11-24',NULL,'Required SoftwareDeveloper with 3 years of working experience',40,NULL,'Google'),(102,'software Developer Intern','Lucknow,UP','2018-11-24','2019-02-30','Required SoftwareDeveloper with 3 years of computer science experience',20,NULL,'Google'),(103,'software Developer','Delhi','2018-11-25',NULL,'Required SoftwareDeveloper with 3 years of experience',40,NULL,'IBM'),(104,'software Developer Intern','Sanfransisco,CA','2018-11-24',NULL,'Required SoftwareDeveloper with 3 years of computer science experience',40,NULL,'IBM'),(105,'Software Architecture','Kanpur,UP','2018-11-27',NULL,'Required SoftwareDeveloper with 8 years of experience',40,NULL,'MICROSOFT'),(107,'WEB DEVELOPER','BARABANKI','2020-05-06','30','Required INTERN',40,'2020-09-09','GOOGLE PVT LTD');
/*!40000 ALTER TABLE `jobopening` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-23 22:54:22
