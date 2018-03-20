/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.53-MariaDB-1~wheezy : Database - paydb
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*CREATE DATABASE `paydb`;

USE `paydb`;*/

/*Table structure for table `red_packet` */

DROP TABLE IF EXISTS `red_packet`;

CREATE TABLE `red_packet` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL,
  `money` double DEFAULT NULL COMMENT '红包金额',
  `rest_money` double DEFAULT NULL COMMENT '字段冗余,剩余金额',
  `expire_time` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `packet_size` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

/*Table structure for table `red_packet_detail` */

DROP TABLE IF EXISTS `red_packet_detail`;

CREATE TABLE `red_packet_detail` (
  `id` bigint(32) NOT NULL,
  `red_packet_id` varchar(32) DEFAULT NULL COMMENT '红包标识',
  `oepn_id` varchar(32) DEFAULT NULL COMMENT '微信用户标识',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '微信昵称',
  `money` double DEFAULT NULL COMMENT '抢到红包金额',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
  PRIMARY KEY (`id`)
) ;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
