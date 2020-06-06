-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bdd_projet
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `image` (
  `idImage` int NOT NULL AUTO_INCREMENT,
  `idUser` int NOT NULL,
  `chemin` varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nom_image` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `class` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`idImage`),
  UNIQUE KEY `idImage_UNIQUE` (`idImage`),
  KEY `fk_user_idx` (`idUser`),
  CONSTRAINT `fk_user` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (4,1,'1','1','1'),(5,1,'2','2','0'),(6,2,'3','5','1'),(7,2,'2','2','2'),(8,1,'C:\\Users\\Romain\\Documents\\Projet-M1-TNSI - Copie\\Images','test.png','2'),(9,1,'C:\\Users\\Romain\\Documents\\Projet-M1-TNSI - Copie\\Images','test.png','2'),(10,1,'C:\\Users\\Romain\\Documents\\Projet-M1-TNSI - Copie\\Images\\','test.png','2'),(11,1,'C:\\Users\\Romain\\Documents\\Projet-M1-TNSI - Copie\\Images\\','test2.png','2'),(12,1,'C:\\Users\\Romain\\Documents\\Projet-M1-TNSI - Copie\\Images\\','test3.png','2'),(13,1,'C:\\Users\\Romain\\Documents\\Projet-M1-TNSI - Copie\\Images\\','f64a1550a7d345f48eee74965b1d80b2xX_jonh_Xx.png','2'),(14,1,'C:\\Users\\Romain\\Documents\\Projet-M1-TNSI - Copie\\Images\\','8183f634ba984464be650ce19ad23d3axX_jonh_Xx.png','2'),(15,1,'C:\\Users\\Romain\\Documents\\Projet-M1-TNSI - Copie\\Images\\','2a44cf74fe35414eb02ef091d1172a0exX_jonh_Xx.png','0'),(16,1,'C:\\Users\\Romain\\Documents\\Projet-M1-TNSI - Copie\\Images\\','18ec448e9791400e8ed20da647e3eb90xX_jonh_Xx.png','2'),(17,1,'C:\\Users\\Romain\\Documents\\Projet-M1-TNSI - Copie\\Images\\','0e4f3cf11f774b5aa0623eed4debe6ffxX_jonh_Xx.png','2'),(18,3,'C:\\Users\\Romain\\Documents\\Projet-M1-TNSI - Copie\\Images\\','105e02c71f0d4bdc990594e132d4af75a.png','2'),(19,3,'C:\\Users\\Romain\\Documents\\Projet-M1-TNSI - Copie\\Images\\','dd7fb155b0bf45e39efe70696f50da25a.png','2'),(20,3,'C:\\Users\\Romain\\Documents\\Projet-M1-TNSI - Copie\\Images\\','15cb63530e264a969bede081fb0a8ac9a.png','2'),(21,3,'C:\\Users\\Romain\\Documents\\Projet-M1-TNSI - Copie\\Images\\','c91ca8bc9d794c048778374ef51a9769a.png','2');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `statistique`
--

DROP TABLE IF EXISTS `statistique`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `statistique` (
  `idStat` int NOT NULL AUTO_INCREMENT,
  `idImage` int NOT NULL,
  `stat1` int NOT NULL,
  `stat2` int NOT NULL,
  `stat3` int NOT NULL,
  PRIMARY KEY (`idStat`),
  UNIQUE KEY `idStat_UNIQUE` (`idStat`),
  KEY `fk_image_idx` (`idImage`),
  CONSTRAINT `fk_image` FOREIGN KEY (`idImage`) REFERENCES `image` (`idImage`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `statistique`
--

LOCK TABLES `statistique` WRITE;
/*!40000 ALTER TABLE `statistique` DISABLE KEYS */;
/*!40000 ALTER TABLE `statistique` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `idUser` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nom` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `prenom` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `score` bigint NOT NULL,
  `pseudo` varchar(50) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `idUser_UNIQUE` (`idUser`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `pseudo_UNIQUE` (`pseudo`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'b@b.com','9F86D081884C7D659A2FEAA0C55AD015A3BF4F1B2B0B822CD15D6C15B0F00A08','Smith','John',0,'xX_jonh_Xx'),(2,'c@b.com','9F86D081884C7D659A2FEAA0C55AD015A3BF4F1B2B0B822CD15D6C15B0F00A08','Smith','John',0,'yY_jinh_Xx'),(3,'aa@aa.com','CA978112CA1BBDCAFAC231B39A23DC4DA786EFF8147C4E72B9807785AFEE48BB','a','a',0,'a'),(4,'yop@yop.fr','AB4DD0315EB9F3EC424AB4A94472412812905C1EF737D1002DA84DE2D1427531','Cacheux','Kevin',0,'yop');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-07  0:42:07
