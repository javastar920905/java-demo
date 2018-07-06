/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 10.2.10-MariaDB-10.2.10+maria~jessie : Database - rcj
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test`;

/*Table structure for table `constants_record` */

DROP TABLE IF EXISTS `constants_record`;

CREATE TABLE `constants_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(72) CHARACTER SET utf8 DEFAULT NULL,
  `value` int(11) NOT NULL,
  `type` varchar(50) CHARACTER SET utf8 NOT NULL,
  `description` varchar(1000) CHARACTER SET utf8 DEFAULT NULL,
  `parent_value` int(11) DEFAULT NULL,
  `order` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_v_t` (`value`,`type`)
) ENGINE=InnoDB AUTO_INCREMENT=1293 DEFAULT CHARSET=utf8mb4;

/*Data for the table `constants_record` */

insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1037,'已认证',1,'authentication_status','认证状态',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1038,'未认证',0,'authentication_status','认证状态',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1039,'班组级',10,'awards_level','奖项级别',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1040,'院校级',20,'awards_level','奖项级别',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1041,'县市级',30,'awards_level','奖项级别',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1042,'省区级',40,'awards_level','奖项级别',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1043,'国家级',50,'awards_level','奖项级别',NULL,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1044,'国际级',60,'awards_level','奖项级别',NULL,60);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1045,'公办',1,'college_nature','学院性质',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1046,'民办',2,'college_nature','学院性质',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1047,'高中',30,'college_type','学院类型',NULL,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1048,'中专技校',31,'college_type','学院类型',NULL,51);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1049,'初中',32,'college_type','学院类型',NULL,52);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1050,'大学',20,'college_type','学院类型',NULL,16);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1051,'外资（欧美）',10,'company_nature','公司性质',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1052,'外资（非欧美）',20,'company_nature','公司性质',NULL,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1053,'合资',30,'company_nature','公司性质',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1054,'国企',40,'company_nature','公司性质',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1055,'民营企业',50,'company_nature','公司性质',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1056,'外企代表处',60,'company_nature','公司性质',NULL,60);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1057,'政府机关',70,'company_nature','公司性质',NULL,80);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1058,'事业单位',80,'company_nature','公司性质',NULL,70);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1059,'非盈利机构',90,'company_nature','公司性质',NULL,90);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1060,'少于50人',10,'company_scale','公司规模',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1061,'50-150人',20,'company_scale','公司规模',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1062,'150-500人',30,'company_scale','公司规模',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1063,'500-1000人',40,'company_scale','公司规模',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1064,'1000-5000人',50,'company_scale','公司规模',NULL,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1065,'5000-10000人',60,'company_scale','公司规模',NULL,60);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1066,'10000人以上',70,'company_scale','公司规模',NULL,70);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1067,'初创公司',10,'company_stage','公司阶段',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1068,'天使轮',20,'company_stage','公司阶段',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1069,'A轮',30,'company_stage','公司阶段',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1070,'B轮',40,'company_stage','公司阶段',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1071,'C轮',50,'company_stage','公司阶段',NULL,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1072,'D轮及以上',60,'company_stage','公司阶段',NULL,60);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1073,'上市公司',70,'company_stage','公司阶段',NULL,70);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1074,'不需要融资',80,'company_stage','公司阶段',NULL,5);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1075,'211',1,'country_build_key','国家重点建设项目',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1076,'985',2,'country_build_key','国家重点建设项目',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1077,'2011计划',3,'country_build_key','国家重点建设项目',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1078,'111计划',4,'country_build_key','国家重点建设项目',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1079,'其他',5,'country_build_key','国家重点建设项目',NULL,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1080,'有效',10,'data_status','数据状态',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1081,'无效',-1,'data_status','数据状态',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1082,'可见',1,'data_visible','可见状态',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1083,'不可见',0,'data_visible','可见状态',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1084,'100',101010,'day_salary','日薪100',1010,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1085,'200',101020,'day_salary','日薪200',1010,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1086,'300',101030,'day_salary','日薪300',1010,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1087,'300-500',101040,'day_salary','日薪300-500',1010,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1088,'500以上',101050,'day_salary','日薪500以上',1010,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1089,'面议',101060,'day_salary','日薪面议',1010,60);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1090,'高中以下',10,'education','学历',NULL,70);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1091,'中专',25,'education','学历',NULL,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1092,'高中',20,'education','学历',NULL,60);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1093,'大专',30,'education','学历',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1094,'本科',40,'education','学历',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1095,'硕士',50,'education','学历',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1096,'博士',60,'education','学历',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1097,'是',10,'education_full_time','全日制',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1098,'否',20,'education_full_time','全日制',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1099,'是',10,'education_overseas_experience','海外学习经历',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1100,'否',20,'education_overseas_experience','海外学习经历',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1101,'高中/中专及以上',20,'education_requirement','学历要求',NULL,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1102,'大专及以上',30,'education_requirement','学历要求',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1103,'本科及以上',40,'education_requirement','学历要求',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1104,'硕士及以上',50,'education_requirement','学历要求',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1105,'博士及以上',60,'education_requirement','学历要求',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1106,'是',10,'it_project','IT项目',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1107,'否',20,'it_project','IT项目',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1108,'全职',10,'job_nature','工作性质',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1109,'兼职',20,'job_nature','工作性质',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1110,'实习',30,'job_nature','工作性质',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1111,'正在找工作',10,'job_wanted_status','求职状态',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1112,'寻找新机会',20,'job_wanted_status','求职状态',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1113,'1年以下',10,'join_work_year','参加工作年限',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1114,'1-2年',20,'join_work_year','参加工作年限',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1115,'3-5年',30,'join_work_year','参加工作年限',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1116,'6-7年',40,'join_work_year','参加工作年限',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1117,'8-9年',50,'join_work_year','参加工作年限',NULL,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1118,'10年以上',60,'join_work_year','参加工作年限',NULL,60);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1119,'应届毕业生',70,'join_work_year','参加工作年限',NULL,5);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1120,'11',10,'join_work_year_end','工作年限1年以下结束值',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1121,'35',20,'join_work_year_end','工作年限1-2年结束值',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1122,'71',30,'join_work_year_end','工作年限3-5年结束值',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1123,'95',40,'join_work_year_end','工作年限6-7年结束值',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1124,'119',50,'join_work_year_end','工作年限8-9年结束值',NULL,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1125,'-1',70,'join_work_year_end','工作年限应届毕业生结束值',NULL,70);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1126,'-1',10,'join_work_year_start','工作年限1年以下起始值',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1127,'12',20,'join_work_year_start','工作年限1-2年起始值',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1128,'36',30,'join_work_year_start','工作年限3-5年起始值',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1129,'72',40,'join_work_year_start','工作年限6-7年起始值',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1130,'96',50,'join_work_year_start','工作年限8-9年起始值',NULL,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1131,'120',60,'join_work_year_start','工作年限10年以上起始值',NULL,60);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1132,'-1',70,'join_work_year_start','工作年限应届毕业生起始值',NULL,70);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1133,'法语',40,'language','语言类别',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1134,'德语',50,'language','语言类别',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1135,'俄语',60,'language','语言类别',NULL,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1136,'韩语/朝鲜语',70,'language','语言类别',NULL,60);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1137,'西班牙语',80,'language','语言类别',NULL,70);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1138,'葡萄牙语',90,'language','语言类别',NULL,80);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1139,'阿拉伯语',100,'language','语言类别',NULL,90);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1140,'意大利语',110,'language','语言类别',NULL,100);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1141,'粤语',120,'language','语言类别',NULL,120);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1142,'闽南语',130,'language','语言类别',NULL,130);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1143,'上海话',140,'language','语言类别',NULL,140);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1144,'普通话',10,'language','语言类别',NULL,110);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1145,'英语',20,'language','语言类别',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1146,'日语',30,'language','语言类别',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1147,'不限',0,'language_level','语言掌握程度',NULL,0);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1148,'精通',10,'language_level','语言掌握程度',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1149,'熟练',20,'language_level','语言掌握程度',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1150,'良好',30,'language_level','语言掌握程度',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1151,'一般',40,'language_level','语言掌握程度',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1152,'一般',10,'listen_speak_ability','听说能力',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1153,'良好',20,'listen_speak_ability','听说能力',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1154,'熟练',30,'listen_speak_ability','听说能力',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1155,'精通',40,'listen_speak_ability','听说能力',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1156,'一般',10,'literacy','读写能力',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1157,'良好',20,'literacy','读写能力',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1158,'熟练',30,'literacy','读写能力',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1159,'精通',40,'literacy','读写能力',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1160,'未婚',2,'marital_status','婚姻状况',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1161,'已婚',1,'marital_status','婚姻状况',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1162,'HR向人才发送邀请',100,'matching_created_method','匹配对创建方式',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1163,'HR下载简历并关联职位',110,'matching_created_method','匹配对创建方式',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1164,'HR上传简历并关联职位',120,'matching_created_method','匹配对创建方式',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1165,'人才向HR的职位投递简历',200,'matching_created_method','匹配对创建方式',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1166,'人才经纪向HR推荐简历',210,'matching_created_method','匹配对创建方式',NULL,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1167,'2000以下',102010,'monthly_salary','月薪2000以下',1020,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1168,'2000-3999',102020,'monthly_salary','月薪2000-3999',1020,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1169,'4000-6999',102030,'monthly_salary','月薪4000-6999',1020,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1170,'7000-9999',102040,'monthly_salary','月薪7000-9999',1020,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1171,'10000-12999',102050,'monthly_salary','月薪10000-12999',1020,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1172,'13000-15999',102060,'monthly_salary','月薪13000-15999',1020,60);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1173,'16000-19999',102070,'monthly_salary','月薪16000-19999',1020,70);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1174,'20000-29999',102080,'monthly_salary','月薪20000-29999',1020,80);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1175,'30000-49999',102090,'monthly_salary','月薪30000-49999',1020,90);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1176,'50000以上',1020100,'monthly_salary','月薪50000以上',1020,100);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1177,'面议',1020110,'monthly_salary','月薪面议',1020,110);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1178,'人才',0,'person_type','人员类型',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1179,'HR',1,'person_type','人员类型',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1180,'身份证',10,'pid_type','证件类型',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1181,'香港身份证',20,'pid_type','证件类型',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1182,'军人证',30,'pid_type','证件类型',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1183,'护照',40,'pid_type','证件类型',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1184,'群众',10,'politics_status','政治面貌',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1185,'无党派人士',20,'politics_status','政治面貌',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1186,'中共党员',30,'politics_status','政治面貌',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1187,'民主党派',40,'politics_status','政治面貌',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1188,'团员',50,'politics_status','政治面貌',NULL,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1189,'其他',60,'politics_status','政治面貌',NULL,60);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1190,'250',1,'position_level_price','职位分级价格（低）',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1191,'500',2,'position_level_price','职位分级价格（中）',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1192,'1500',3,'position_level_price','职位分级价格（高）',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1193,'通过下载简历获取联系方式',1,'resume_contact_obtain_method','简历联系方式获取途径',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1194,'通过上传简历获取联系方式',2,'resume_contact_obtain_method','简历联系方式获取途径',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1195,'人才同意HR的邀约',3,'resume_contact_obtain_method','简历联系方式获取途径',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1196,'250',10,'resume_price_base','简历基础价格(日薪100)',101010,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1197,'500',20,'resume_price_base','简历基础价格(日薪200)',101020,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1198,'1000',30,'resume_price_base','简历基础价格(日薪300)',101030,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1199,'1500',40,'resume_price_base','简历基础价格(日薪300-500)',101040,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1200,'2500',50,'resume_price_base','简历基础价格(日薪500以上)',101050,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1201,'250',60,'resume_price_base','简历基础价格(月薪2000以下)',102010,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1202,'250',70,'resume_price_base','简历基础价格(月薪2000-3999)',102020,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1203,'250',80,'resume_price_base','简历基础价格(月薪4000-6999)',102030,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1204,'500',90,'resume_price_base','简历基础价格(月薪7000-9999)',102040,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1205,'500',100,'resume_price_base','简历基础价格(月薪10000-12999)',102050,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1206,'500',110,'resume_price_base','简历基础价格(月薪13000-15999)',102060,60);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1207,'1000',120,'resume_price_base','简历基础价格(月薪16000-19999)',102070,70);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1208,'1000',130,'resume_price_base','简历基础价格(月薪20000-29999)',102080,80);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1209,'1500',140,'resume_price_base','简历基础价格(月薪30000-49999)',102090,90);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1210,'2500',150,'resume_price_base','简历基础价格(月薪50000以上)',1020100,100);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1211,'20',100,'resume_price_timeliness_coefficient','简历时效性价格系数(不确定)',100,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1212,'50',200,'resume_price_timeliness_coefficient','简历时效性价格系数(低)',200,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1213,'100',300,'resume_price_timeliness_coefficient','简历时效性价格系数(中)',300,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1214,'150',400,'resume_price_timeliness_coefficient','简历时效性价格系数(高)',400,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1215,'200',500,'resume_price_timeliness_coefficient','简历时效性价格系数(强)',500,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1216,'人才创建的简历',10,'resume_source','简历来源',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1217,'HR上传的简历',20,'resume_source','简历来源',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1218,'一键同步功能下载的简历',30,'resume_source','简历来源',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1219,'一键同步功能接收的简历',40,'resume_source','简历来源',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1220,'不确定',100,'resume_timeliness','简历时效性',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1221,'低',200,'resume_timeliness','简历时效性',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1222,'中',300,'resume_timeliness','简历时效性',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1223,'高',400,'resume_timeliness','简历时效性',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1224,'超强',500,'resume_timeliness','简历时效性',NULL,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1225,'薪酬类型',10,'salary','薪酬',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1226,'日薪',1010,'salary_type','日薪',10,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1227,'月薪',1020,'salary_type','月薪',10,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1228,'保密',1030,'salary_type','薪资-保密',10,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1229,'面议',1040,'salary_type','薪资-面议',10,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1230,'院校级',10,'scholarship_level','奖学金级别',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1231,'省市级',20,'scholarship_level','奖学金级别',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1232,'国家级',30,'scholarship_level','奖学金级别',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1233,'一等奖',10,'scholarship_type','奖学金类型',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1234,'二等奖',20,'scholarship_type','奖学金类型',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1235,'三等奖',30,'scholarship_type','奖学金类型',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1236,'男',10,'sex','性别',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1237,'女',20,'sex','性别',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1238,'综合院校',1,'subject_type','学校学科类型',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1239,'工科院校',2,'subject_type','学校学科类型',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1240,'农业院校',3,'subject_type','学校学科类型',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1241,'林业院校',4,'subject_type','学校学科类型',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1242,'医药院校',5,'subject_type','学校学科类型',NULL,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1243,'师范院校',6,'subject_type','学校学科类型',NULL,60);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1244,'语言院校',7,'subject_type','学校学科类型',NULL,70);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1245,'财经院校',8,'subject_type','学校学科类型',NULL,80);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1246,'政法院校',9,'subject_type','学校学科类型',NULL,90);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1247,'体育院校',10,'subject_type','学校学科类型',NULL,100);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1248,'艺术院校',11,'subject_type','学校学科类型',NULL,110);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1249,'民族院校',12,'subject_type','学校学科类型',NULL,120);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1250,'军事院校',13,'subject_type','学校学科类型',NULL,130);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1251,'高职高专',10,'university_type','大学类型',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1252,'普通本科',20,'university_type','大学类型',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1253,'其他',30,'university_type','大学类型',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1254,'独立学院',40,'university_type','大学类型',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1255,'成人教育',50,'university_type','大学类型',NULL,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1256,'中外合作办学',60,'university_type','大学类型',NULL,60);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1257,'HND项目',70,'university_type','大学类型',NULL,70);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1258,'远程教育学院',80,'university_type','大学类型',NULL,80);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1259,'五险一金',10,'position_benefits','职位福利',NULL,10);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1260,'交通补助',20,'position_benefits','职位福利',NULL,20);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1270,'年底双薪',30,'position_benefits','职位福利',NULL,30);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1271,'节日福利',40,'position_benefits','职位福利',NULL,40);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1272,'餐费补贴',50,'position_benefits','职位福利',NULL,50);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1273,'包吃',60,'position_benefits','职位福利',NULL,60);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1274,'住房补贴',70,'position_benefits','职位福利',NULL,70);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1275,'高温补贴',80,'position_benefits','职位福利',NULL,80);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1276,'绩效奖金',90,'position_benefits','职位福利',NULL,90);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1277,'年终分红',100,'position_benefits','职位福利',NULL,100);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1278,'员工旅游',110,'position_benefits','职位福利',NULL,110);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1280,'包住',120,'position_benefits','职位福利',NULL,120);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1281,'加班补助',130,'position_benefits','职位福利',NULL,130);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1282,'全勤奖',140,'position_benefits','职位福利',NULL,140);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1283,'通讯补贴',150,'position_benefits','职位福利',NULL,150);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1284,'弹性工作',160,'position_benefits','职位福利',NULL,160);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1285,'定期体检',170,'position_benefits','职位福利',NULL,170);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1286,'免费班车',180,'position_benefits','职位福利',NULL,180);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1287,'专业培训',190,'position_benefits','职位福利',NULL,190);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1288,'出国机会',200,'position_benefits','职位福利',NULL,200);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1289,'股票期权',210,'position_benefits','职位福利',NULL,210);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1290,'周末双休',220,'position_benefits','职位福利',NULL,220);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1291,'带薪年假',230,'position_benefits','职位福利',NULL,230);
insert  into `constants_record`(`id`,`name`,`value`,`type`,`description`,`parent_value`,`order`) values (1292,'不加班',240,'position_benefits','职位福利',NULL,240);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
