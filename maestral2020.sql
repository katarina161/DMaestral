/*
SQLyog Community v13.1.5  (64 bit)
MySQL - 8.0.22 : Database - maestral
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`maestral` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `maestral`;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(250) COLLATE utf8_vietnamese_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_vietnamese_ci;

/*Data for the table `category` */

insert  into `category`(`id`,`name`) values 
(1,'One Piece'),
(2,'Dresses and Skirts'),
(3,'T-Shirts'),
(4,'Sweaters & Hoodies');

/*Table structure for table `invoice` */

DROP TABLE IF EXISTS `invoice`;

CREATE TABLE `invoice` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(250) COLLATE utf8_unicode_ci NOT NULL,
  `partner` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `date` date NOT NULL,
  `total` double NOT NULL DEFAULT '0',
  `processed` tinyint(1) NOT NULL DEFAULT '0',
  `canceled` tinyint(1) NOT NULL DEFAULT '0',
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `invoice` */

insert  into `invoice`(`id`,`number`,`partner`,`date`,`total`,`processed`,`canceled`,`user_id`) values 
(2,'INV-10022021-1','Petar Petrovic','2021-02-16',11520,0,0,1),
(3,'INV-10022021-2','Ivana Ivanovic','2021-02-16',207960,1,1,1),
(7,'INV-14022021-1','Jovan Markovic','2021-02-14',115920,0,0,5),
(8,'INV-14022021-2','Miodrag Radulovic','2021-02-16',36960,1,0,1),
(16,'INV-10022021-2','Ivana Ivanovic','2021-02-16',-207960,1,1,1),
(18,'INV-16022021-1','rferfre','2021-02-16',708,1,1,1),
(19,'INV-16022021-1','rferfre','2021-02-16',-708,1,1,1);

/*Table structure for table `invoice_item` */

DROP TABLE IF EXISTS `invoice_item`;

CREATE TABLE `invoice_item` (
  `invoice_id` bigint unsigned NOT NULL,
  `order_number` int NOT NULL,
  `product_article` int NOT NULL,
  `size_id` int NOT NULL,
  `quantity` int NOT NULL DEFAULT '0',
  `price` double NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`invoice_id`,`order_number`),
  KEY `product_article` (`product_article`),
  KEY `size_id` (`size_id`),
  CONSTRAINT `invoice_item_ibfk_2` FOREIGN KEY (`product_article`) REFERENCES `product` (`article`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `invoice_item_ibfk_3` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `invoice_item_ibfk_4` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `invoice_item` */

insert  into `invoice_item`(`invoice_id`,`order_number`,`product_article`,`size_id`,`quantity`,`price`,`total`) values 
(2,1,23,3,5,576,2880),
(2,2,23,4,5,576,2880),
(2,3,23,5,10,576,5760),
(3,1,338,1,100,708,70800),
(3,2,338,2,100,708,70800),
(3,3,338,3,70,708,49560),
(3,4,395,4,10,840,8400),
(3,5,395,5,10,840,8400),
(7,1,368,3,30,588,17640),
(7,2,368,4,30,588,17640),
(7,3,23,6,50,576,28800),
(7,4,23,5,50,576,28800),
(7,5,23,4,20,576,11520),
(7,6,23,3,20,576,11520),
(8,1,338,1,20,708,14160),
(8,2,338,2,10,708,7080),
(8,3,377,1,10,708,7080),
(8,4,23,6,15,576,8640),
(18,1,338,1,1,708,708);

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `article` int NOT NULL,
  `name` varchar(250) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(800) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price` double NOT NULL,
  `price_with_vat` double NOT NULL,
  `category_id` int NOT NULL,
  PRIMARY KEY (`article`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `product_chk_1` CHECK ((`price` >= 0)),
  CONSTRAINT `product_chk_2` CHECK ((`price_with_vat` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `product` */

insert  into `product`(`article`,`name`,`description`,`price`,`price_with_vat`,`category_id`) values 
(23,'Batman','White bloesw with black sleeves',480,576,4),
(338,'Girl Doctor','White one piece with pink shirt print and \nheart shaped stratoscope',590,708,1),
(367,'Minnie Mouse','Pink blouse with Minnie Mouse print',490,588,4),
(368,'Mickey Mouse','Blue hoodie with Mickey Mouse print',490,588,4),
(377,'Little Gentleman','Black one piece with red tie',590,708,1),
(395,'Princess dress','White dress with black stripes',700,840,2),
(822,'Ballerina','Red skirt with bow',650,780,2);

/*Table structure for table `product_size` */

DROP TABLE IF EXISTS `product_size`;

CREATE TABLE `product_size` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `product_article` int NOT NULL,
  `size_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `size_id` (`size_id`),
  KEY `product_size_ibfk_1` (`product_article`),
  CONSTRAINT `product_size_ibfk_1` FOREIGN KEY (`product_article`) REFERENCES `product` (`article`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `product_size_ibfk_2` FOREIGN KEY (`size_id`) REFERENCES `size` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `product_size` */

insert  into `product_size`(`id`,`product_article`,`size_id`) values 
(1,822,3),
(2,822,4),
(3,822,5),
(21,368,1),
(30,368,4),
(38,23,5),
(39,23,3),
(40,368,2),
(41,368,3),
(44,338,1),
(45,338,2),
(46,338,3),
(47,377,1),
(48,377,2),
(49,377,3),
(50,395,1),
(51,395,2),
(52,395,3),
(53,395,4),
(54,395,5),
(55,822,1),
(56,822,2),
(57,23,4),
(58,23,6),
(59,367,6),
(60,367,5),
(61,367,4);

/*Table structure for table `size` */

DROP TABLE IF EXISTS `size`;

CREATE TABLE `size` (
  `id` int NOT NULL AUTO_INCREMENT,
  `number` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `size` */

insert  into `size`(`id`,`number`) values 
(1,62),
(2,68),
(3,74),
(4,80),
(5,86),
(6,92);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `admin` tinyint(1) DEFAULT '0',
  `image` bigint DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `image` (`image`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`image`) REFERENCES `user_image` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`first_name`,`last_name`,`admin`,`image`) values 
(1,'admin','admin','Katarina','Novakovic',1,6),
(2,'rooney','tit','Rodney','Trotter',0,3),
(5,'delboy','tit','Derek','Trotter',1,2);

/*Table structure for table `user_image` */

DROP TABLE IF EXISTS `user_image`;

CREATE TABLE `user_image` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `path` varchar(2000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user_image` */

insert  into `user_image`(`id`,`path`) values 
(1,'images/unknown.png'),
(2,'images/Derek_Trotter.jpeg'),
(3,'images/Rodney_Trotter.jpeg'),
(4,'images/Albert_Trotter.jpeg'),
(5,'images/Boycie.jpeg'),
(6,'images/Katarina_Novakovic.jpeg');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
