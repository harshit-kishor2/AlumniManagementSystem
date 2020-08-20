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
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `useraccount` (
  `RID` varchar(50) NOT NULL,
  `Fname` varchar(40) NOT NULL,
  `Lname` varchar(40) DEFAULT NULL,
  `email` varchar(60) NOT NULL,
  `address` varchar(200) DEFAULT NULL,
  `phone_number` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`RID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES ('harshit','Harshit','Kishor','harshit.kishor2@gmail.com','Lucknow','09621162566'),('R-11111','Harshit','Kishor','harshit.kishor2@gmail.com','Lucknow','09621162566'),('R-11112','Ravi','Verma','harshitkishor2@gmail.com','Vill-Chandauli,Po-Tikra(Usma),Barabanki','09621162566'),('R-22222','Harshit','Kishor','harshit.kishor2@gmail.com','Lucknow','09621162566'),('R-23234','Harshit','Kishor','harshit.kishor2@gmail.com','Lucknow','09621162566'),('R-23235','Harshit','Kishor','harshit.kishor2@gmail.com','Lucknow','09621162566'),('R-33333','Harshit','Kishor','harshit.kishor2@gmail.com','Lucknow','09621162566'),('R01241235','oliver','carlin','olivercarlin@mail.com','Kanpur','5709871234'),('R01241335','john','doe','johndoe@mail.com','Lucknow','7329870091'),('R01241366','David ','Adler','davidadler@mail.com','Varanasi','8797864567'),('R01341335','jack','william','jackwilliam@mail.com','Delhi','9088764354'),('R02221325','george','harrison','georgeharrison@mail.com','alahabad','9321459876');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-23 22:54:25
