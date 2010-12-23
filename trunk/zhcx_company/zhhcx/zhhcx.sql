/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50140
Source Host           : localhost:3306
Source Database       : zhhcx

Target Server Type    : MYSQL
Target Server Version : 50140
File Encoding         : 65001

Date: 2010-12-23 12:26:46
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `zhh_admin`
-- ----------------------------
DROP TABLE IF EXISTS `zhh_admin`;
CREATE TABLE `zhh_admin` (
  `adm_id` int(10) NOT NULL AUTO_INCREMENT,
  `adm_username` varchar(20) DEFAULT NULL,
  `adm_password` varchar(20) DEFAULT NULL,
  `adm_createtime` date DEFAULT NULL,
  PRIMARY KEY (`adm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zhh_admin
-- ----------------------------
INSERT INTO `zhh_admin` VALUES ('1', 'admin', 'admin', '2010-12-20');
INSERT INTO `zhh_admin` VALUES ('2', 'tom', 'tom', '2010-12-20');

-- ----------------------------
-- Table structure for `zhh_company`
-- ----------------------------
DROP TABLE IF EXISTS `zhh_company`;
CREATE TABLE `zhh_company` (
  `id` int(11) NOT NULL DEFAULT '0',
  `com_name` varchar(0) DEFAULT NULL,
  `com_email` varchar(255) DEFAULT NULL,
  `com_address` varchar(255) DEFAULT NULL,
  `com_mobile` varchar(255) DEFAULT NULL,
  `com_intro` varchar(255) DEFAULT NULL,
  `com_user` varchar(255) DEFAULT NULL,
  `com_phone` varchar(255) DEFAULT NULL,
  `com_net` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zhh_company
-- ----------------------------

-- ----------------------------
-- Table structure for `zhh_message`
-- ----------------------------
DROP TABLE IF EXISTS `zhh_message`;
CREATE TABLE `zhh_message` (
  `MEG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MEG_CONTENT` varchar(255) DEFAULT NULL,
  `MEG_EMAIL` varchar(255) DEFAULT NULL,
  `MEG_USERNAME` varchar(255) DEFAULT NULL,
  `MEG_TITLE` varchar(255) DEFAULT NULL,
  `MEG_CREATETIME` date DEFAULT NULL,
  PRIMARY KEY (`MEG_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zhh_message
-- ----------------------------
INSERT INTO `zhh_message` VALUES ('1', '4', '4', '4', '4', '2010-12-21');
INSERT INTO `zhh_message` VALUES ('2', '2', '2', '2', '2', '2010-12-21');

-- ----------------------------
-- Table structure for `zhh_photo`
-- ----------------------------
DROP TABLE IF EXISTS `zhh_photo`;
CREATE TABLE `zhh_photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `img_name` varchar(255) DEFAULT NULL,
  `img_create_time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zhh_photo
-- ----------------------------
INSERT INTO `zhh_photo` VALUES ('2', 'IMAGE_061.jpg', '1292950688');
