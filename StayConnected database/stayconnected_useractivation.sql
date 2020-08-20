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
-- Table structure for table `useractivation`
--

DROP TABLE IF EXISTS `useractivation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `useractivation` (
  `code` varchar(60) DEFAULT NULL,
  `expiration` date DEFAULT NULL,
  `RID` varchar(30) DEFAULT NULL,
  KEY `RID` (`RID`),
  CONSTRAINT `useractivation_ibfk_1` FOREIGN KEY (`RID`) REFERENCES `useraccount` (`RID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useractivation`
--

LOCK TABLES `useractivation` WRITE;
/*!40000 ALTER TABLE `useractivation` DISABLE KEYS */;
INSERT INTO `useractivation` VALUES ('JEK29EFB4C83U','2020-04-04','R01241335'),('JEK2JE0OC83U','2020-04-04','R01241235'),('JEK29QOVM383U','2020-04-06','R01341335'),('JEK292KFM4ID0','2020-04-06','R01241366'),('JEK22KFMDJQ0P','2020-04-08','R02221325'),('nzbgfzhbbx','2020-03-20','R-23234'),('shunvqycdw','2020-03-25','R-23235'),('ubluwevztc','2020-04-29','harshit'),('huvfzsujwk','2020-05-16','R-11111'),('musbmmvvtf','2020-05-17','R-22222'),('lcpmzcmfwj','2020-05-17','R-33333'),('ochlxcikad','2020-06-23','R-11112');
/*!40000 ALTER TABLE `useractivation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-23 22:54:21
