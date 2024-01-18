-- MySQL dump 10.13  Distrib 8.0.35, for Linux (x86_64)
--
-- Host: localhost    Database: basicdb
-- ------------------------------------------------------
-- Server version	8.0.35-0ubuntu0.22.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `cartId` int NOT NULL AUTO_INCREMENT,
  `itemId` int NOT NULL,
  `memberId` int NOT NULL,
  `formWritten` int DEFAULT '0',
  `cartTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`cartId`),
  KEY `itemId` (`itemId`),
  KEY `memberId` (`memberId`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`itemId`) REFERENCES `item` (`itemId`) ON DELETE CASCADE,
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`memberId`) REFERENCES `member` (`memberId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (4,6,9,0,'2024-01-10 07:38:34'),(6,5,5,0,'2024-01-12 05:46:01'),(10,3,13,0,'2024-01-16 06:48:05'),(11,6,13,0,'2024-01-16 06:48:10');
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `item_category` varchar(20) NOT NULL,
  PRIMARY KEY (`item_category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES ('기타장비'),('무대'),('상업용가전'),('악기'),('오피스'),('자동차'),('해외'),('현장장비');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `checkRequest`
--

DROP TABLE IF EXISTS `checkRequest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `checkRequest` (
  `memberId` int NOT NULL,
  `formId` int NOT NULL,
  `checked` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`memberId`,`formId`),
  KEY `formId` (`formId`),
  CONSTRAINT `checkRequest_ibfk_1` FOREIGN KEY (`memberId`) REFERENCES `member` (`memberId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `checkRequest_ibfk_2` FOREIGN KEY (`formId`) REFERENCES `rentalForms` (`formId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `checkRequest`
--

LOCK TABLES `checkRequest` WRITE;
/*!40000 ALTER TABLE `checkRequest` DISABLE KEYS */;
INSERT INTO `checkRequest` VALUES (1,3,1),(1,5,1),(1,9,1),(1,10,1),(1,11,1),(1,12,1),(8,1,1),(8,2,1),(8,4,1),(8,8,0);
/*!40000 ALTER TABLE `checkRequest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comId` int NOT NULL AUTO_INCREMENT,
  `postNum` int DEFAULT NULL,
  `comWriter` varchar(20) DEFAULT NULL,
  `comContent` text,
  `comDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`comId`),
  KEY `postNum` (`postNum`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`postNum`) REFERENCES `post` (`post_num`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,2,'8','한남동이 좋을 것 같네요! 옷 구경하면서 많이 가더라고요','2024-01-10 06:22:01');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `itemId` int NOT NULL AUTO_INCREMENT,
  `domestic` tinyint(1) NOT NULL,
  `title` varchar(100) NOT NULL,
  `name` varchar(50) NOT NULL,
  `rentalStartDate` date NOT NULL,
  `rentalEndDate` date NOT NULL,
  `rentalPrice` bigint NOT NULL,
  `address` varchar(100) NOT NULL,
  `carrier` varchar(50) DEFAULT NULL,
  `content` text,
  `uploadDate` date DEFAULT NULL,
  `folderName` varchar(10) DEFAULT NULL,
  `fileName` varchar(200) DEFAULT NULL,
  `car_type` varchar(20) DEFAULT NULL,
  `car_age` varchar(10) DEFAULT NULL,
  `car_fuelType` varchar(10) DEFAULT NULL,
  `item_category` varchar(20) NOT NULL,
  `memberId` int DEFAULT NULL,
  PRIMARY KEY (`itemId`),
  KEY `item_category` (`item_category`),
  KEY `memberId` (`memberId`),
  CONSTRAINT `item_ibfk_1` FOREIGN KEY (`item_category`) REFERENCES `category` (`item_category`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `item_ibfk_2` FOREIGN KEY (`memberId`) REFERENCES `member` (`memberId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,1,'장기 출장으로 인한 차량 렌탈','bmw i7','2024-01-10','2024-02-10',700000,'서울특별시 강남구 ','OOCL','필요한 서류는 저한테 메시지로 보여주시기 바랍니다. 전기차 사용하시던 분이 렌탈 하시는게 좋을 것 같습니다.','2024-01-10','20240110','eab46626-94f3-4766-870e-7c3718f2aaeb.jpg','전기차','2024','전기','자동차',8),(3,1,'캐스퍼 타보고 싶으신 분!','캐스퍼 가솔린 1.0 터보 인스퍼레이션','2024-03-11','2024-03-25',200000,'울산광역시 북구','KCTC','2주간 여행으로 인해 캐스퍼 빌려드립니다. 캐스퍼 구매 고민하고 있으셨던 분들 2주간 타보시고 결정해보면 어떨까요? 필요한 서류는 운전면허증, 차량 보험 서류 보여주시면 될 것 같습니다.','2024-01-10','20240110','817fac52-d818-498a-90ac-74657fac341c.jpg','소형','2023','휘발유','자동차',8),(4,1,'매장용 냉장고 2년 렌탈','프레쉬 업소용 수직형 냉장고','2024-01-10','2024-01-10',500000,'부산광역시 진구','CJ','위에가 냉장, 아래가 냉동고입니다. 2년간 렌탈 하실 분 구해요. 사업하시는데 초기 비용 줄이고 싶으신 분 추천합니다.','2024-01-10','20240110','c13852bb-6692-4666-8ee0-9650101c6d01.jpg',NULL,NULL,NULL,'상업용가전',8),(5,1,'아이맥 1달!','Apple 2023 아이맥 24 M3','2024-01-12','2024-02-22',300000,'서울특별시 송파구 ','owner','아이맥 한달간 빌려드립니다!! 어도비도 설치되어있어서 한달간 무료로 쓰실 수 있습니다. 연락주세용','2024-01-10','20240110','c2595477-08b7-4567-b01d-229a8d190c45.webp',NULL,NULL,NULL,'오피스',8),(6,1,'신형 싼타페 장기렌트 합니다.','현대 싼타페','2024-01-16','2024-02-02',350000,'부산광역시 연제구','renter','신형 싼타페 체험하실 분 ','2024-01-10','20240110','f8f45385-1a14-4075-bf60-e7cebe8edf0c.jpg','중형','2023','하이브리드','자동차',1),(7,1,'볼보 렌트합니다.','볼보 XC90','2024-01-16','2024-02-03',500000,'서울특별시 강남구 ','CJ','ㄴㅇㄹ','2024-01-10','20240110','61e54148-13d4-47df-9a38-e2d4b434bb57.jpg','대형','2025','휘발유','자동차',1);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `love`
--

DROP TABLE IF EXISTS `love`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `love` (
  `loveId` int NOT NULL AUTO_INCREMENT,
  `memberId` int NOT NULL,
  `post_num` int NOT NULL,
  `state` tinyint DEFAULT '0',
  PRIMARY KEY (`loveId`),
  KEY `memberId` (`memberId`),
  KEY `post_num` (`post_num`),
  CONSTRAINT `love_ibfk_1` FOREIGN KEY (`memberId`) REFERENCES `member` (`memberId`) ON DELETE CASCADE,
  CONSTRAINT `love_ibfk_2` FOREIGN KEY (`post_num`) REFERENCES `post` (`post_num`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `love`
--

LOCK TABLES `love` WRITE;
/*!40000 ALTER TABLE `love` DISABLE KEYS */;
INSERT INTO `love` VALUES (1,10,5,1),(2,10,3,1),(3,8,5,1),(4,8,2,1),(5,8,6,1);
/*!40000 ALTER TABLE `love` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `memberId` int NOT NULL AUTO_INCREMENT,
  `memberEmail` varchar(50) NOT NULL,
  `memberPasswd` varchar(20) NOT NULL,
  `memberName` varchar(20) NOT NULL,
  `memberPhone` varchar(11) NOT NULL,
  `memberNickName` varchar(10) DEFAULT NULL,
  `memberBirth` date DEFAULT NULL,
  `memberPostcode` varchar(10) DEFAULT NULL,
  `memberAddress` varchar(60) DEFAULT NULL,
  `memberInterest` varchar(50) DEFAULT NULL,
  `memberRegDate` date DEFAULT NULL,
  `memberPoint` bigint DEFAULT NULL,
  `memberRole` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`memberId`),
  UNIQUE KEY `memberEmail` (`memberEmail`),
  UNIQUE KEY `memberNickName` (`memberNickName`),
  UNIQUE KEY `memberNickName_2` (`memberNickName`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'aaa','123','철이','01077777777','dddd','1999-09-09','','서울 성북구 아리랑로 3  (동소문동6가)','차량','2023-12-20',1450,'Admin'),(5,'rkldud@naver.com','asdfg12!','송가영','01099991111','초코','2000-09-12','',' ','','2024-01-03',1050,'회원'),(8,'asdfg@naver.com','asdfg12!','김지영','01098989189','졍',NULL,'',' ',NULL,'2024-01-04',50,'회원'),(9,'pretty@daum.net','asdfg12!','한소희','01090901212','나비','1994-11-18','','서울 강남구 가로수길 5 아파트 (신사동)','방송,악기','2024-01-09',50,'회원'),(10,'dkdk@daum.net','asdfg12!','아아','01082738273','앙',NULL,'',' ',NULL,'2024-01-09',50,'회원'),(12,'asdfg@daum.net','asdfg12!','유지혜','01099991212','졔','1992-12-12','03693','서울 서대문구 가재울로 8-3  (남가좌동)','악기','2024-01-12',1000,'회원'),(13,'asdf','asdf','asdf','01012345678','asdf',NULL,'',' ','','2024-01-16',1050,'회원');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `msgId` int NOT NULL AUTO_INCREMENT,
  `itemId` int NOT NULL,
  `renterId` int NOT NULL,
  `ownerId` int NOT NULL,
  `sentBy` int NOT NULL,
  `msgContent` text,
  `checked` tinyint(1) DEFAULT '0',
  `msgTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`msgId`),
  KEY `itemId` (`itemId`),
  KEY `renterId` (`renterId`),
  KEY `ownerId` (`ownerId`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`itemId`) REFERENCES `item` (`itemId`) ON DELETE CASCADE,
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`renterId`) REFERENCES `member` (`memberId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `message_ibfk_3` FOREIGN KEY (`ownerId`) REFERENCES `member` (`memberId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (2,1,9,8,9,'저 렌탈 하고 싶은데 혹시 필요한 서류는 어떤 것이 있나요??',1,'2024-01-11 00:34:08'),(3,1,9,8,8,'정확하게 언제부터 언제까지 필요하신가요?',1,'2024-01-11 00:35:41'),(4,1,5,8,5,'필요한 서류 제출할게요\r\n',1,'2024-01-12 05:47:37'),(5,1,5,8,8,'응\n',0,'2024-01-12 05:48:39'),(6,1,5,8,8,'',0,'2024-01-12 05:48:40'),(7,6,8,1,8,'저여\r\n',1,'2024-01-12 05:58:17'),(8,6,8,1,1,'이미끝남',0,'2024-01-12 05:58:47'),(11,7,13,1,13,'sadf',1,'2024-01-16 06:46:52'),(12,7,13,1,1,'djkkdjkds',0,'2024-01-16 06:52:35');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `post_num` int NOT NULL AUTO_INCREMENT,
  `post_type` varchar(8) NOT NULL,
  `post_title` varchar(80) NOT NULL,
  `post_writer` varchar(20) NOT NULL,
  `post_content` text NOT NULL,
  `post_date` date DEFAULT NULL,
  PRIMARY KEY (`post_num`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (2,'고민','사업(카페)할 지역을 못고르겠어요','dddd','카페를 차리려고 하는데 요즘 핫플이 어딘지 말해주실 분?!  망원동이랑 한남동 쪽이 괜찮은 것 같은데 \n\n둘이 다른 느낌이어서 고민이 되네요..카페는 약간 힙하면서도 코지한 느낌을 낼 거라서 두 지역 중에 고민이 됩니다. ','2024-01-10'),(3,'자유','요즘 인스타 릴스 때문에...','졍','요즘 인스타 때문에 도파민 중독된 거 같아요... 릴스 안 보려면 어떻게 해야될까요??ㅠㅠ','2024-01-10'),(5,'자유','교촌치킨 가격 올랐어요','초코','','2024-01-10'),(6,'자유','망해가는 파스타집 구원해주세요..','앙','','2024-01-10');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rentalForms`
--

DROP TABLE IF EXISTS `rentalForms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rentalForms` (
  `formId` int NOT NULL AUTO_INCREMENT,
  `domestic` tinyint(1) NOT NULL,
  `userName` varchar(20) NOT NULL,
  `userPhone` varchar(13) NOT NULL,
  `rentalStartDate` date NOT NULL,
  `rentalEndDate` date NOT NULL,
  `postcode` varchar(10) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `carrier` varchar(50) DEFAULT NULL,
  `userContent` text,
  `itemId` int NOT NULL,
  `ownerId` int NOT NULL,
  `renterId` int NOT NULL,
  `formStatus` varchar(10) DEFAULT 'waiting',
  `createdAt` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`formId`),
  KEY `itemId` (`itemId`),
  KEY `ownerId` (`ownerId`),
  KEY `renterId` (`renterId`),
  CONSTRAINT `rentalForms_ibfk_1` FOREIGN KEY (`itemId`) REFERENCES `item` (`itemId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rentalForms_ibfk_2` FOREIGN KEY (`ownerId`) REFERENCES `member` (`memberId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `rentalForms_ibfk_3` FOREIGN KEY (`renterId`) REFERENCES `member` (`memberId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rentalForms`
--

LOCK TABLES `rentalForms` WRITE;
/*!40000 ALTER TABLE `rentalForms` DISABLE KEYS */;
INSERT INTO `rentalForms` VALUES (1,1,'한소희','01099998888','2024-01-10','2024-02-10','48100','부산 해운대구 동백로 52 아파트 (우동)','OOCL','',1,8,9,'confirmed','2024-01-10 07:12:07'),(2,1,'한소희','01099998888','2024-01-10','2024-02-10','05831','서울 송파구 동남로 105 아파트 (가락동)','특급운송사','렌탈하고싶어요!!',5,8,9,'waiting','2024-01-10 07:40:04'),(3,1,'송강','01099998888','2024-01-10','2024-01-31','02719','서울 성북구 서경로 2  (정릉동)','Doora','',6,1,1,'confirmed','2024-01-12 03:00:28'),(4,1,'송가영','01099991111','2024-01-10','2024-02-10','46770','부산 강서구 거가대로 2659  (천성동)','OOCL','렌탈 하겠습니다',1,8,5,'confirmed','2024-01-12 05:46:54'),(5,1,'123','123','2024-01-18','2024-01-23','13480','경기 성남시 분당구 대왕판교로 477  (판교동)','12345','123456',7,1,13,'waiting','2024-01-16 01:06:28'),(8,1,'dfg','dfg','2024-01-10','2024-02-10','dfg','dfg dfg','OOCL','',1,8,1,'waiting','2024-01-16 06:23:04'),(9,1,'sdf','sadf','2024-01-16','2024-02-03','','dsf ','CJ','',7,1,1,'confirmed','2024-01-16 06:23:58'),(10,1,'123','123','2024-01-16','2024-02-03','13480','경기 성남시 분당구 대왕판교로 477 123 (판교동)','CJ','123',7,1,13,'waiting','2024-01-16 06:39:30'),(11,1,'asdf','asdf','2024-01-16','2024-02-03','asdf',' asdf','CJ','sadf',7,1,13,'rejected','2024-01-16 06:47:19'),(12,1,'sdf','asdfasdf','2024-01-16','2024-02-03','',' ','CJ','',7,1,13,'confirmed','2024-01-16 06:49:36');
/*!40000 ALTER TABLE `rentalForms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `reportId` int NOT NULL AUTO_INCREMENT,
  `itemId` int NOT NULL,
  `renterId` int NOT NULL,
  `ownerId` int NOT NULL,
  `renterNickName` varchar(10) NOT NULL,
  `ownerNickName` varchar(10) NOT NULL,
  `reportType` varchar(20) NOT NULL,
  `reportContent` text,
  `reportTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`reportId`),
  KEY `itemId` (`itemId`),
  KEY `renterId` (`renterId`),
  KEY `ownerId` (`ownerId`),
  KEY `renterNickName` (`renterNickName`),
  KEY `ownerNickName` (`ownerNickName`),
  CONSTRAINT `report_ibfk_1` FOREIGN KEY (`itemId`) REFERENCES `item` (`itemId`) ON DELETE CASCADE,
  CONSTRAINT `report_ibfk_2` FOREIGN KEY (`renterId`) REFERENCES `member` (`memberId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `report_ibfk_3` FOREIGN KEY (`ownerId`) REFERENCES `member` (`memberId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `report_ibfk_4` FOREIGN KEY (`renterNickName`) REFERENCES `member` (`memberNickName`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `report_ibfk_5` FOREIGN KEY (`ownerNickName`) REFERENCES `member` (`memberNickName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (1,7,9,1,'나비','dddd','비매너','이 회원 거래 비매너입니다.','2024-01-11 01:44:35'),(2,7,13,1,'asdf','dddd','사기','사기\r\n','2024-01-16 01:05:59'),(6,7,13,1,'asdf','dddd','비매너','sadf','2024-01-16 06:47:00');
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `request_id` int NOT NULL AUTO_INCREMENT,
  `request_name` varchar(30) DEFAULT NULL,
  `request_num` varchar(50) DEFAULT NULL,
  `request_email` varchar(50) DEFAULT NULL,
  `request_export_country` varchar(50) DEFAULT NULL,
  `request_export_city` varchar(50) DEFAULT NULL,
  `request_export_date` varchar(50) DEFAULT NULL,
  `request_import_country` varchar(50) DEFAULT NULL,
  `request_import_city` varchar(50) DEFAULT NULL,
  `request_import_date` varchar(50) DEFAULT NULL,
  `request_product` text,
  `request_content` text,
  `id` int NOT NULL,
  PRIMARY KEY (`request_id`),
  KEY `id` (`id`),
  CONSTRAINT `request_ibfk_1` FOREIGN KEY (`id`) REFERENCES `shipping` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,'신짱구','010-1004-0099','jjanga@gmail.com','korea','서울특별시','01/11/2024','korea','부산광역시','01/16/2024','자동차','',1),(2,'신짱구','010-1004-0099','jjanga@gmail.com','korea','서울특별시','01/11/2024','korea','부산광역시','01/16/2024','자동차','',1),(3,'정재호','1231','ㄴㅇㄻㅇㄹ','korea','ㄴㅇㄹ','01/10/2024','korea','ㅁㄴㅇㄹ','01/24/2024','자동차','ㅁㄴㅇㄹ',3),(4,'ㄴㅇㄹ','ㄴㅇㄹ','ㄴㅇㄹ','korea','ㄴㅇㄹ','01/17/2024','korea','ㄴㅁㅇㄹ','01/19/2024','IT 오피스','ㄴㅁㅇㄹ',3),(5,'ㄴㅇㄹ','ㄴㅇㄹ','ㄴㅇㄹ','korea','ㄴㅇㄹ','01/17/2024','korea','ㄴㅁㅇㄹ','01/19/2024','IT 오피스','ㄴㅁㅇㄹ',3),(6,'dfg','sdfg','sdfg','korea','sdfg','01/17/2024','korea','sdfg','01/19/2024',NULL,'f',3),(7,'dfg','sdfg','sdfg','korea','sdfg','01/17/2024','korea','sdfg','01/19/2024',NULL,'f',3),(8,'dfg','sdfg','sdfg','korea','sdfg','01/17/2024','korea','sdfg','01/19/2024',NULL,'f',3),(9,'dfg','sdfg','sdfg','korea','sdfg','01/17/2024','korea','sdfg','01/19/2024',NULL,'f',3);
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping`
--

DROP TABLE IF EXISTS `shipping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipping` (
  `id` int NOT NULL AUTO_INCREMENT,
  `domestic` tinyint(1) NOT NULL,
  `name` varchar(50) NOT NULL,
  `com_num` varchar(30) NOT NULL,
  `fax_num` varchar(30) DEFAULT NULL,
  `address1` varchar(100) NOT NULL,
  `address2` varchar(100) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL,
  `postcode` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `m_name` varchar(50) DEFAULT NULL,
  `m_email` varchar(50) DEFAULT NULL,
  `m_com_num` varchar(50) DEFAULT NULL,
  `m_tel_num` varchar(50) DEFAULT NULL,
  `regdate` date DEFAULT NULL,
  `orgName` varchar(255) DEFAULT NULL,
  `savedFileName` varchar(255) DEFAULT NULL,
  `savedFilePathName` varchar(255) DEFAULT NULL,
  `savedFileSize` bigint DEFAULT NULL,
  `folderName` varchar(10) DEFAULT NULL,
  `ext` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping`
--

LOCK TABLES `shipping` WRITE;
/*!40000 ALTER TABLE `shipping` DISABLE KEYS */;
INSERT INTO `shipping` VALUES (1,1,'두라로지스틱스','1588-4151','02-2665-1345','강서구 금낭화로 54-7, (방화동)','동해빌딩 1~3층','서울','대한민국','07607','doora@gmail.com','김철수','shinjjang@gmail.com','02-113-9887','010-1234-5678','2024-01-11','43747_img.png','b7748e1a-caec-4659-a3b7-299761269861.png','C:/Users/ITPS/Desktop/project33/project33/src/main/resources/static/upload/20240112/b7748e1a-caec-4659-a3b7-299761269861.png',12476,'20240112','.png'),(2,1,'FEDEX','1588-0588','','마포구 양화로 19, (합정동)','합정오피스빌딩 5~6층','서울','대한민국','04027','fede@gmail.com','','','','','2024-01-11','Fedex.png','77573088-da37-41c9-a169-9c94eb48efee.png','C:/Users/ITPS/Desktop/project33/project33/src/main/resources/static/upload/20240112/77573088-da37-41c9-a169-9c94eb48efee.png',57872,'20240112','.png'),(3,1,'EXPRESS LOGISTICS','866-470-2776','','킹스크로스 스트릿','세인트피터 빌딩','런던','대한민국','40452','showseaside@gmail.com','','','','','2024-01-11','화면 캡처 2024-01-11 101230.png','e620aaa8-9bd9-476a-82f8-cc2780ae1eb5.png','C:/Users/ITPS/Desktop/project33/project33/src/main/resources/static/upload/20240112/e620aaa8-9bd9-476a-82f8-cc2780ae1eb5.png',5534,'20240112','.png');
/*!40000 ALTER TABLE `shipping` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-16  8:23:22
