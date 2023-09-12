-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.33 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for student_management
CREATE DATABASE IF NOT EXISTS `student_management` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `student_management`;

-- Dumping structure for table student_management.admin
CREATE TABLE IF NOT EXISTS `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL DEFAULT '0',
  `password` varchar(100) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table student_management.admin: ~0 rows (approximately)

-- Dumping structure for table student_management.assignments
CREATE TABLE IF NOT EXISTS `assignments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `link` varchar(50) NOT NULL DEFAULT '',
  `status` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table student_management.assignments: ~0 rows (approximately)

-- Dumping structure for table student_management.assignments_student
CREATE TABLE IF NOT EXISTS `assignments_student` (
  `id_assignments` int NOT NULL,
  `link` varchar(50) NOT NULL DEFAULT '',
  `status` bit(1) NOT NULL DEFAULT b'0',
  `reason` text,
  KEY `FK_as_assignments` (`id_assignments`),
  CONSTRAINT `FK_as_assignments` FOREIGN KEY (`id_assignments`) REFERENCES `assignments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table student_management.assignments_student: ~0 rows (approximately)

-- Dumping structure for table student_management.class
CREATE TABLE IF NOT EXISTS `class` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table student_management.class: ~0 rows (approximately)

-- Dumping structure for table student_management.class_subject
CREATE TABLE IF NOT EXISTS `class_subject` (
  `id_class` int NOT NULL,
  `id_subject` int NOT NULL,
  `id_teacher` int NOT NULL,
  `id_student` int NOT NULL,
  `id_assignments` int DEFAULT NULL,
  `id_exam` int DEFAULT NULL,
  KEY `FK_cs_class` (`id_class`),
  KEY `FK_cs_subject` (`id_subject`),
  KEY `FK_class_subject_teacher` (`id_teacher`),
  KEY `FK_cs_assignments` (`id_assignments`),
  KEY `FK_cs_exam` (`id_exam`),
  KEY `FK_cs_student` (`id_student`),
  CONSTRAINT `FK_class_subject_teacher` FOREIGN KEY (`id_teacher`) REFERENCES `teacher` (`id`),
  CONSTRAINT `FK_cs_assignments` FOREIGN KEY (`id_assignments`) REFERENCES `assignments` (`id`),
  CONSTRAINT `FK_cs_class` FOREIGN KEY (`id_class`) REFERENCES `class` (`id`),
  CONSTRAINT `FK_cs_exam` FOREIGN KEY (`id_exam`) REFERENCES `exam_schedule` (`id`),
  CONSTRAINT `FK_cs_student` FOREIGN KEY (`id_student`) REFERENCES `student` (`id`),
  CONSTRAINT `FK_cs_subject` FOREIGN KEY (`id_subject`) REFERENCES `subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table student_management.class_subject: ~0 rows (approximately)

-- Dumping structure for table student_management.exam_schedule
CREATE TABLE IF NOT EXISTS `exam_schedule` (
  `id` int NOT NULL AUTO_INCREMENT,
  `start` datetime NOT NULL,
  `end` datetime NOT NULL,
  `link_exam` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table student_management.exam_schedule: ~0 rows (approximately)

-- Dumping structure for table student_management.solution
CREATE TABLE IF NOT EXISTS `solution` (
  `id_assignments` int NOT NULL,
  `link` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` bit(1) NOT NULL,
  KEY `FK_solution_assignments` (`id_assignments`),
  CONSTRAINT `FK_solution_assignments` FOREIGN KEY (`id_assignments`) REFERENCES `assignments` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table student_management.solution: ~0 rows (approximately)

-- Dumping structure for table student_management.student
CREATE TABLE IF NOT EXISTS `student` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '0',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0',
  `phone` int DEFAULT '0',
  `since` date NOT NULL,
  `password` varchar(50) NOT NULL DEFAULT '',
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `Column 3` (`username`) USING BTREE,
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table student_management.student: ~0 rows (approximately)

-- Dumping structure for table student_management.subject
CREATE TABLE IF NOT EXISTS `subject` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `session` int NOT NULL DEFAULT '0',
  `lession_link` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table student_management.subject: ~0 rows (approximately)

-- Dumping structure for table student_management.teacher
CREATE TABLE IF NOT EXISTS `teacher` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '0',
  `username` varchar(50) NOT NULL,
  `phone` int DEFAULT '0',
  `since` date NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `status` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table student_management.teacher: ~0 rows (approximately)

-- Dumping structure for table student_management.transcript
CREATE TABLE IF NOT EXISTS `transcript` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_exam` int NOT NULL,
  `link` varchar(50) NOT NULL DEFAULT '',
  `score` float DEFAULT '0',
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_transcript_subject` (`id_exam`) USING BTREE,
  CONSTRAINT `FK_transcript_exam` FOREIGN KEY (`id_exam`) REFERENCES `exam_schedule` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table student_management.transcript: ~0 rows (approximately)

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
