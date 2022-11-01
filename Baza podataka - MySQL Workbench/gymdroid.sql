-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: gymdroid
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
-- Table structure for table `done_set`
--

DROP TABLE IF EXISTS `done_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `done_set` (
  `doneSetId` int NOT NULL AUTO_INCREMENT,
  `doneSetNumberOfReps` int NOT NULL,
  `doneSetWeight` double(10,2) NOT NULL DEFAULT '0.00',
  `doneSetTimeInMilliseconds` int NOT NULL DEFAULT '0',
  `doneWorkoutId` int NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deletedAt` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`doneSetId`),
  KEY `fk_table1_done_workout1_idx` (`doneWorkoutId`),
  CONSTRAINT `fk_table1_done_workout1` FOREIGN KEY (`doneWorkoutId`) REFERENCES `done_workout` (`doneWorkoutId`)
) ENGINE=InnoDB AUTO_INCREMENT=491 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `done_set`
--

LOCK TABLES `done_set` WRITE;
/*!40000 ALTER TABLE `done_set` DISABLE KEYS */;
/*!40000 ALTER TABLE `done_set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `done_training`
--

DROP TABLE IF EXISTS `done_training`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `done_training` (
  `doneTrainingId` int NOT NULL AUTO_INCREMENT,
  `doneTrainingAt` timestamp NULL DEFAULT NULL,
  `trainingId` int NOT NULL,
  `createdAt` timestamp NULL DEFAULT NULL,
  `updatedAt` timestamp NULL DEFAULT NULL,
  `deletedAt` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`doneTrainingId`),
  KEY `fk_DoneTraining_Training1_idx` (`trainingId`),
  CONSTRAINT `fk_DoneTraining_Training1` FOREIGN KEY (`trainingId`) REFERENCES `training` (`trainingId`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `done_training`
--

LOCK TABLES `done_training` WRITE;
/*!40000 ALTER TABLE `done_training` DISABLE KEYS */;
/*!40000 ALTER TABLE `done_training` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `done_workout`
--

DROP TABLE IF EXISTS `done_workout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `done_workout` (
  `doneWorkoutId` int NOT NULL AUTO_INCREMENT,
  `workoutId` int NOT NULL,
  `userId` int NOT NULL,
  `doneWorkoutDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deletedAt` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`doneWorkoutId`),
  KEY `fk_done_workout_workout1_idx` (`workoutId`),
  KEY `fk_done_workout_user1_idx` (`userId`),
  CONSTRAINT `fk_done_workout_user1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`),
  CONSTRAINT `fk_done_workout_workout1` FOREIGN KEY (`workoutId`) REFERENCES `workout` (`workoutId`)
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `done_workout`
--

LOCK TABLES `done_workout` WRITE;
/*!40000 ALTER TABLE `done_workout` DISABLE KEYS */;
/*!40000 ALTER TABLE `done_workout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipment` (
  `equipmentId` int NOT NULL AUTO_INCREMENT,
  `equipmentWeight` double(10,2) NOT NULL,
  `equipmentCount` int NOT NULL,
  `equipmentTypeId` int NOT NULL,
  `userOwnerId` int NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deletedAt` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`equipmentId`),
  UNIQUE KEY `equipmentId_UNIQUE` (`equipmentId`),
  KEY `fk_equipment_user1_idx` (`userOwnerId`),
  KEY `fk_equipment_equipment_type1_idx` (`equipmentTypeId`),
  CONSTRAINT `fk_equipment_equipment_type1` FOREIGN KEY (`equipmentTypeId`) REFERENCES `equipment_type` (`equipmentTypeId`),
  CONSTRAINT `fk_equipment_user1` FOREIGN KEY (`userOwnerId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment`
--

LOCK TABLES `equipment` WRITE;
/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `equipment_type`
--

DROP TABLE IF EXISTS `equipment_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipment_type` (
  `equipmentTypeId` int NOT NULL AUTO_INCREMENT,
  `equipmentTypeName` varchar(45) NOT NULL,
  `equipmentIsUpgradable` int NOT NULL,
  `equipmentIsWeight` int NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deletedAt` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`equipmentTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipment_type`
--

LOCK TABLES `equipment_type` WRITE;
/*!40000 ALTER TABLE `equipment_type` DISABLE KEYS */;
INSERT INTO `equipment_type` VALUES (1,'Barbell',1,1,'2020-05-12 22:28:52','2020-05-12 22:28:52',NULL),(2,'Dumbbell',1,1,'2020-05-12 22:28:52','2020-05-12 22:28:52',NULL),(3,'Weight plate',0,1,'2020-05-12 22:28:52','2020-05-12 22:28:52',NULL),(4,'Hand weight',0,1,'2020-05-12 22:28:52','2020-05-12 22:28:52',NULL),(5,'Ankle weight',0,1,'2020-05-12 22:28:52','2020-05-12 22:28:52',NULL),(6,'Resistance band',0,0,'2020-05-12 22:28:52','2020-05-12 22:28:52',NULL),(7,'Mat',0,0,'2020-05-12 22:28:52','2020-05-12 22:28:52',NULL);
/*!40000 ALTER TABLE `equipment_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `muscle`
--

DROP TABLE IF EXISTS `muscle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `muscle` (
  `muscleId` int NOT NULL AUTO_INCREMENT,
  `muscleName` varchar(45) NOT NULL,
  `muscleSize` double(10,2) NOT NULL,
  `muscleGroupId` int NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deletedAt` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`muscleId`),
  KEY `fk_muscle_muscle_group1_idx` (`muscleGroupId`),
  CONSTRAINT `fk_muscle_muscle_group1` FOREIGN KEY (`muscleGroupId`) REFERENCES `muscle_group` (`muscleGroupId`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `muscle`
--

LOCK TABLES `muscle` WRITE;
/*!40000 ALTER TABLE `muscle` DISABLE KEYS */;
INSERT INTO `muscle` VALUES (1,'Upper Chest',152.57,1,'2020-05-12 22:28:47','2020-05-12 22:28:47',NULL),(2,'Middle Chest',125.40,1,'2020-05-12 22:28:47','2020-05-12 22:28:47',NULL),(3,'Lower Chest',169.00,1,'2020-05-12 22:28:47','2020-05-12 22:28:47',NULL),(4,'Lats',262.30,2,'2020-05-12 22:28:47','2020-05-12 22:28:47',NULL),(5,'Traps',242.73,2,'2020-05-12 22:28:47','2020-05-12 22:28:47',NULL),(6,'Middle Back - Rhomboid',147.91,2,'2020-05-12 22:28:48','2020-05-12 22:28:48',NULL),(7,'Lower Back',147.20,2,'2020-05-12 22:28:48','2020-05-12 22:28:48',NULL),(8,'Biceps',180.89,3,'2020-05-12 22:28:48','2020-05-12 22:28:48',NULL),(9,'Triceps',174.45,3,'2020-05-12 22:28:48','2020-05-12 22:28:48',NULL),(10,'Forearms',143.00,3,'2020-05-12 22:28:49','2020-05-12 22:28:49',NULL),(11,'Front shoulder',174.45,4,'2020-05-12 22:28:49','2020-05-12 22:28:49',NULL),(12,'Middle shoulder',143.00,4,'2020-05-12 22:28:49','2020-05-12 22:28:49',NULL),(13,'Back shoulder',143.70,4,'2020-05-12 22:28:49','2020-05-12 22:28:49',NULL),(14,'Upper Abs',78.12,5,'2020-05-12 22:28:50','2020-05-12 22:28:50',NULL),(15,'Lower Abs',68.95,5,'2020-05-12 22:28:50','2020-05-12 22:28:50',NULL),(16,'Obliques',249.23,5,'2020-05-12 22:28:50','2020-05-12 22:28:50',NULL),(17,'Quadriceps',1420.00,6,'2020-05-12 22:28:50','2020-05-12 22:28:50',NULL),(18,'Hamstrings',334.78,6,'2020-05-12 22:28:51','2020-05-12 22:28:51',NULL),(19,'Calves',250.12,6,'2020-05-12 22:28:51','2020-05-12 22:28:51',NULL),(20,'Gluteus maximus',879.65,6,'2020-05-12 22:28:51','2020-05-12 22:28:51',NULL),(21,'Gluteus medius',458.29,6,'2020-05-12 22:28:52','2020-05-12 22:28:52',NULL),(22,'Gluteus minimus',353.12,6,'2020-05-12 22:28:52','2020-05-12 22:28:52',NULL);
/*!40000 ALTER TABLE `muscle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `muscle_group`
--

DROP TABLE IF EXISTS `muscle_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `muscle_group` (
  `muscleGroupId` int NOT NULL AUTO_INCREMENT,
  `muscleGroupName` varchar(45) NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deletedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`muscleGroupId`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `muscle_group`
--

LOCK TABLES `muscle_group` WRITE;
/*!40000 ALTER TABLE `muscle_group` DISABLE KEYS */;
INSERT INTO `muscle_group` VALUES (1,'Chest Muscles','2020-05-12 22:28:40','2020-05-12 22:28:40',NULL),(2,'Back Muscles','2020-05-12 22:28:40','2020-05-12 22:28:40',NULL),(3,'Arm Muscles','2020-05-12 22:28:40','2020-05-12 22:28:40',NULL),(4,'Shoulder Muscles','2020-05-12 22:28:40','2020-05-12 22:28:40',NULL),(5,'Abdominal Muscles','2020-05-12 22:28:41','2020-05-12 22:28:41',NULL),(6,'Legs and Buttocks Muscles','2020-05-12 22:28:41','2020-05-12 22:28:41',NULL);
/*!40000 ALTER TABLE `muscle_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relation_workout_muscle`
--

DROP TABLE IF EXISTS `relation_workout_muscle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `relation_workout_muscle` (
  `relationId` int NOT NULL AUTO_INCREMENT,
  `workoutId` int NOT NULL,
  `muscleId` int NOT NULL,
  `muscleTargetPriority` int NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deletedAt` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`relationId`),
  KEY `fk_relation_workout_muscle_workout1_idx` (`workoutId`),
  KEY `fk_relation_workout_muscle_muscle1_idx` (`muscleId`),
  CONSTRAINT `fk_relation_workout_muscle_muscle1` FOREIGN KEY (`muscleId`) REFERENCES `muscle` (`muscleId`),
  CONSTRAINT `fk_relation_workout_muscle_workout1` FOREIGN KEY (`workoutId`) REFERENCES `workout` (`workoutId`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relation_workout_muscle`
--

LOCK TABLES `relation_workout_muscle` WRITE;
/*!40000 ALTER TABLE `relation_workout_muscle` DISABLE KEYS */;
INSERT INTO `relation_workout_muscle` VALUES (1,1,17,1,'2020-05-12 22:28:54','2020-05-12 22:28:54',NULL),(2,1,20,2,'2020-05-12 22:28:54','2020-05-12 22:28:54',NULL),(3,1,18,3,'2020-05-12 22:28:54','2020-05-12 22:28:54',NULL),(4,2,19,1,'2020-05-12 22:28:54','2020-05-12 22:28:54',NULL),(5,3,20,1,'2020-05-12 22:28:55','2020-05-12 22:28:55',NULL),(6,3,21,2,'2020-05-12 22:28:55','2020-05-12 22:28:55',NULL),(7,3,18,3,'2020-05-12 22:28:55','2020-05-12 22:28:55',NULL),(8,4,22,1,'2020-05-12 22:28:55','2020-05-12 22:28:55',NULL),(9,4,18,2,'2020-05-12 22:28:56','2020-05-12 22:28:56',NULL),(10,4,21,3,'2020-05-12 22:28:56','2020-05-12 22:28:56',NULL),(11,5,14,1,'2020-05-12 22:28:56','2020-05-12 22:28:56',NULL),(12,5,15,2,'2020-05-12 22:28:56','2020-05-12 22:28:56',NULL),(13,5,16,3,'2020-05-12 22:28:57','2020-05-12 22:28:57',NULL),(14,6,15,1,'2020-05-12 22:28:57','2020-05-12 22:28:57',NULL),(15,6,16,2,'2020-05-12 22:28:57','2020-05-12 22:28:57',NULL),(16,6,14,3,'2020-05-12 22:28:57','2020-05-12 22:28:57',NULL),(17,7,8,1,'2020-05-12 22:28:58','2020-05-12 22:28:58',NULL),(18,7,10,2,'2020-05-12 22:28:58','2020-05-12 22:28:58',NULL),(19,8,9,1,'2020-05-12 22:28:58','2020-05-12 22:28:58',NULL),(20,9,2,1,'2020-05-12 22:28:58','2020-05-12 22:28:58',NULL),(21,9,9,2,'2020-05-12 22:28:59','2020-05-12 22:28:59',NULL),(22,9,11,3,'2020-05-12 22:28:59','2020-05-12 22:28:59',NULL),(23,10,1,1,'2020-05-12 22:28:59','2020-05-12 22:28:59',NULL),(24,11,3,1,'2020-05-12 22:28:59','2020-05-12 22:28:59',NULL),(25,11,9,2,'2020-05-12 22:29:00','2020-05-12 22:29:00',NULL),(26,12,11,1,'2020-05-12 22:29:00','2020-05-12 22:29:00',NULL),(27,12,12,2,'2020-05-12 22:29:00','2020-05-12 22:29:00',NULL),(28,12,1,3,'2020-05-12 22:29:00','2020-05-12 22:29:00',NULL),(29,13,13,1,'2020-05-12 22:29:01','2020-05-12 22:29:01',NULL),(30,13,6,2,'2020-05-12 22:29:01','2020-05-12 22:29:01',NULL),(31,13,5,3,'2020-05-12 22:29:01','2020-05-12 22:29:01',NULL),(32,14,4,1,'2020-05-12 22:29:01','2020-05-12 22:29:01',NULL),(33,14,5,2,'2020-05-12 22:29:02','2020-05-12 22:29:02',NULL),(34,14,6,3,'2020-05-12 22:29:02','2020-05-12 22:29:02',NULL),(35,15,7,1,'2020-05-12 22:29:02','2020-05-12 22:29:02',NULL),(36,15,20,2,'2020-05-12 22:29:02','2020-05-12 22:29:02',NULL),(37,15,18,3,'2020-05-12 22:29:03','2020-05-12 22:29:03',NULL),(38,16,4,1,'2020-05-12 22:29:03','2020-05-12 22:29:03',NULL),(39,16,5,2,'2020-05-12 22:29:03','2020-05-12 22:29:03',NULL),(40,16,6,3,'2020-05-12 22:29:03','2020-05-12 22:29:03',NULL);
/*!40000 ALTER TABLE `relation_workout_muscle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training`
--

DROP TABLE IF EXISTS `training`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `training` (
  `trainingId` int NOT NULL AUTO_INCREMENT,
  `trainingName` varchar(60) DEFAULT NULL,
  `trainingDescription` varchar(500) DEFAULT NULL,
  `trainingIntensityLevel` int DEFAULT NULL,
  `durationOfPauseBetweenWorkouts` mediumtext,
  `userCreatorId` int NOT NULL,
  `createdAt` timestamp NULL DEFAULT NULL,
  `updatedAt` timestamp NULL DEFAULT NULL,
  `deletedAt` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`trainingId`),
  KEY `fk_Training_user1_idx` (`userCreatorId`),
  CONSTRAINT `fk_Training_user1` FOREIGN KEY (`userCreatorId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training`
--

LOCK TABLES `training` WRITE;
/*!40000 ALTER TABLE `training` DISABLE KEYS */;
/*!40000 ALTER TABLE `training` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training_set`
--

DROP TABLE IF EXISTS `training_set`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `training_set` (
  `trainingSetId` int NOT NULL AUTO_INCREMENT,
  `trainingSetTime` mediumtext,
  `trainingSetNumberOfReps` int DEFAULT NULL,
  `trainingWorkoutId` int NOT NULL,
  `createdAt` timestamp NULL DEFAULT NULL,
  `updatedAt` timestamp NULL DEFAULT NULL,
  `deletedAt` timestamp NULL DEFAULT NULL,
  `trainingSetWeight` double DEFAULT NULL,
  PRIMARY KEY (`trainingSetId`),
  KEY `fk_TrainingSet_TrainingWorkout1_idx` (`trainingWorkoutId`),
  CONSTRAINT `fk_TrainingSet_TrainingWorkout1` FOREIGN KEY (`trainingWorkoutId`) REFERENCES `training_workout` (`trainingWorkoutId`)
) ENGINE=InnoDB AUTO_INCREMENT=297 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_set`
--

LOCK TABLES `training_set` WRITE;
/*!40000 ALTER TABLE `training_set` DISABLE KEYS */;
/*!40000 ALTER TABLE `training_set` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `training_workout`
--

DROP TABLE IF EXISTS `training_workout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `training_workout` (
  `trainingWorkoutId` int NOT NULL AUTO_INCREMENT,
  `orderNumber` int DEFAULT NULL,
  `durationOfPauseBetweenSets` mediumtext,
  `trainingId` int NOT NULL,
  `workoutId` int NOT NULL,
  `createdAt` timestamp NULL DEFAULT NULL,
  `updatedAt` timestamp NULL DEFAULT NULL,
  `deletedAt` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`trainingWorkoutId`),
  KEY `fk_TrainingWorkout_Training1_idx` (`trainingId`),
  KEY `fk_TrainingWorkout_workout1_idx` (`workoutId`),
  CONSTRAINT `fk_TrainingWorkout_Training1` FOREIGN KEY (`trainingId`) REFERENCES `training` (`trainingId`),
  CONSTRAINT `fk_TrainingWorkout_workout1` FOREIGN KEY (`workoutId`) REFERENCES `workout` (`workoutId`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `training_workout`
--

LOCK TABLES `training_workout` WRITE;
/*!40000 ALTER TABLE `training_workout` DISABLE KEYS */;
/*!40000 ALTER TABLE `training_workout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userId` int NOT NULL AUTO_INCREMENT,
  `userEmail` varchar(45) NOT NULL,
  `userPassword` varchar(64) NOT NULL,
  `firebaseId` varchar(45) NOT NULL,
  `registrationType` varchar(45) NOT NULL,
  `isActive` varchar(45) NOT NULL DEFAULT '1',
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deletedAt` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin@hotmail.com','613c68933b9142e182ff123dc5dd11bd56fcb7acfe9b84f62ccc1fb68d585010','dbZqg8PNiDcGZM2loUUYvaSJSJv2','EMAIL','1','2020-05-13 11:26:29','2020-05-13 11:26:29',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_weight`
--

DROP TABLE IF EXISTS `user_weight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_weight` (
  `weightId` int NOT NULL AUTO_INCREMENT,
  `weight` decimal(10,1) NOT NULL,
  `weightNotes` varchar(300) DEFAULT NULL,
  `weightDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `userId` int NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deletedAt` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`weightId`),
  UNIQUE KEY `weightId_UNIQUE` (`weightId`),
  KEY `fk_user_weight_user1_idx` (`userId`),
  CONSTRAINT `fk_user_weight_user1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_weight`
--

LOCK TABLES `user_weight` WRITE;
/*!40000 ALTER TABLE `user_weight` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_weight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workout`
--

DROP TABLE IF EXISTS `workout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workout` (
  `workoutId` int NOT NULL AUTO_INCREMENT,
  `workoutName` varchar(100) NOT NULL,
  `workoutDescription` varchar(500) NOT NULL,
  `workoutStatusIsApproved` int NOT NULL DEFAULT '0',
  `workoutStatusWaitApproval` int NOT NULL DEFAULT '0',
  `workoutNeedTime` int NOT NULL,
  `workoutNeedWeight` int NOT NULL,
  `userCreatorId` int NOT NULL,
  `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `deletedAt` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`workoutId`),
  KEY `fk_workout_user_idx` (`userCreatorId`),
  CONSTRAINT `fk_workout_user` FOREIGN KEY (`userCreatorId`) REFERENCES `user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workout`
--

LOCK TABLES `workout` WRITE;
/*!40000 ALTER TABLE `workout` DISABLE KEYS */;
INSERT INTO `workout` VALUES (1,'Bulgarian split squat','Elevate the back leg on a bench or a sturdy chair. Lower all the way down into the Bulgarian split squat, and then come all the way up. That’s 1 rep.',1,0,0,1,1,'2020-05-12 22:28:53','2020-05-12 22:28:53',NULL),(2,'Single leg calf raise','Stand on a block or step with one leg, your weight resting on the ball of your foot. Wrap your free foot around the back of the working leg. Allow your body to sink toward the floor and stretch your calf. Hold for one second and then drive the ball of your foot into the surface as you raise your heel up. Hold the top position for two seconds.',1,0,0,0,1,'2020-05-12 22:28:53','2020-05-12 22:28:53',NULL),(3,'Curtsy Lunge','Start by standing with your feet shoulder-width apart and your hands on your hips for stability. Use your left leg to take a large step back and across to the right -- you\'ll cross behind the right leg. Squat so your right thigh is parallel with the ground. Push off with your left leg to straighten your legs and return to your starting position. For an added challenge, hold hand weights in each hand during the exercise.',1,0,0,1,1,'2020-05-12 22:28:53','2020-05-12 22:28:53',NULL),(4,'Single leg side bridges','Lie on your back, and place your hands on the floor for stability as you bend one leg and lift the other leg toward the ceiling. Pressing your heel into the floor, lift your pelvis up, keeping your body in a stiff bridge position. Slowly lower your body to the floor.',1,0,0,0,1,'2020-05-12 22:28:53','2020-05-12 22:28:53',NULL),(5,'Bicycle crunches','Lift one leg just off the ground and extend it out. Lift the other leg and bend your knee towards your chest. As you do so twist through your core so the opposite arm comes towards the raised knee. You don\'t need to touch elbow to knee, instead focus on moving through your core as you turn your torso.',1,0,0,0,1,'2020-05-12 22:28:53','2020-05-12 22:28:53',NULL),(6,'Scissors','Lie on the floor face up, feet together. Keep your legs together and lift them off the ground 6 inches by contracting your abs. Create a scissor like motion by simultaneously raising one leg and lowering the other. Do not let lower leg touch the ground.\n',1,0,0,0,1,'2020-05-12 22:28:53','2020-05-12 22:28:53',NULL),(7,'Barbell curl','Start off standing up straight with your feet shoulder-width apart, keeping your knees slightly bent and abs drawn in tight.\n\nGrab a barbell with a shoulder width underhand (palms up) grip, lowering your arms down to your thighs fully and bending slightly at your elbows as this will be your starting position.\n\nSlowly raise the bar towards your upper chest, squeezing your muscles and isolating the biceps.\n\nHold this position for a count and then return back to the starting position.',1,0,0,1,1,'2020-05-12 22:28:53','2020-05-12 22:28:53',NULL),(8,'Triceps bench dips','Sit down on a bench, put your hands next to your thighs.\nWalk your feet out and extend your legs, lifting your bottom off the bench and holding there with extended arms.\nHinging at the elbow, lower your body down as far as you can go, or until your arms form a 90-degree angle.\nPush up through your palms back to start.',1,0,0,0,1,'2020-05-12 22:28:53','2020-05-12 22:28:53',NULL),(9,'Bench press','Begin by lying on a bench.\nGrip the bar overhand and wider than shoulder-width.\nLower the weight in a controlled manner to the nipple line, exhaling throughout the movement. (It is conventionally thought best to pause briefly at the bottom of the movement and not to rest the weight on your chest).\nPress and extend the arms, while exhaling throughout the movement.\n',1,0,0,1,1,'2020-05-12 22:28:53','2020-05-12 22:28:53',NULL),(10,'Incline bench press','Position your body on an incline bench on a 30-45 degree angle. Grab a barbell with an overhand grip that\'s shoulder-width apart and hold it above your chest. Extend arms upward, locking out elbows. Lower the bar straight down in a slow, controlled movement to your chest.',1,0,0,1,1,'2020-05-12 22:28:53','2020-05-12 22:28:53',NULL),(11,'Incline pushup','Stand in front of the bench. Place the hands shoulder-width apart on the edge of the bench.\nAdopt a plank position by extending the legs backward until the legs and back form a straight line. Keep the weight on the balls of the feet.\nSlowly bend the arms to lower the chest toward the bench. Remember to keep the elbows and arms close to the body.\nSlowly push the body away from the bench, extending the arms but maintaining a slight bend in the elbow.',1,0,0,0,1,'2020-05-12 22:28:53','2020-05-12 22:28:53',NULL),(12,'Front arm raises','Stand with the feet shoulder width apart or less and grasp the dumbbells with an overhand grip.\nRaise the arm to horizontal (or slightly above), exhaling throughout the movement.\nLower the arm to the starting position (to the thigh), inhaling throughout the movement.',1,0,0,1,1,'2020-05-12 22:28:53','2020-05-12 22:28:53',NULL),(13,'Bent-over dumbbell reverse fly','Stand with feet apart, and bend over at about 90°. Hold a dumbbell in each hand, palms facing each other. Hang your arms perpendicular to the ground with elbows slightly bent.\nEngage your rear delt to raise the dumbbells laterally, tilting your hand forward.\nKeep your elbows fixed and shoulders contracted. Visualize moving your entire arm and the dumbbell as a whole.\nLower the dumbbells back down slowly.',1,0,0,1,1,'2020-05-12 22:28:53','2020-05-12 22:28:53',NULL),(14,'Barbell Row','Stand with your mid-foot under the bar (medium stance).\nBend over and grab the bar (palms down, medium-grip).\nUnlock your knees while keeping your hips high.\nLift your chest and straighten your back.\nPull the bar against your lower chest.\nLower to the start with control. That is 1 rep.',1,0,0,1,1,'2020-05-12 22:28:53','2020-05-12 22:28:53',NULL),(15,'Hyperextensions','Lie face down on a hyperextension bench.\nWith your body straight, cross your arms in front of you (or place behind your head).\nThen start to bend forward slowly at the waist as far as you can while keeping your back flat.\nKeep moving forward until you feel a nice stretch on the hamstrings and you can no longer keep going without a rounding of the back.\nSlowly raise your torso back to the initial position without arching your back.',1,0,0,1,1,'2020-05-12 22:28:53','2020-05-12 22:28:53',NULL),(16,'Dumbbell single arm row','Put your left leg on the bench and grab the far side with your left hand, then bend over so your upper body is parallel with the ground.\nReach down and pick up the dumbbell in your right hand with a neutral grip (palm facing you), then hold it with your arm extended, keeping your back straight.\nLower the dumbbell back down slowly.',1,0,0,1,1,'2020-05-12 22:28:53','2020-05-12 22:28:53',NULL);
/*!40000 ALTER TABLE `workout` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-14 23:55:10
