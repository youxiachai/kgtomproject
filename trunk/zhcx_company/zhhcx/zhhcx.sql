/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50140
Source Host           : localhost:3306
Source Database       : zhhcx

Target Server Type    : MYSQL
Target Server Version : 50140
File Encoding         : 65001

Date: 2011-01-13 20:11:18
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
-- Table structure for `zhh_comcontacts`
-- ----------------------------
DROP TABLE IF EXISTS `zhh_comcontacts`;
CREATE TABLE `zhh_comcontacts` (
  `id` int(11) NOT NULL DEFAULT '0',
  `com_name` varchar(255) DEFAULT NULL,
  `com_address` varchar(255) DEFAULT NULL,
  `com_telphone` varchar(255) DEFAULT NULL,
  `com_mobile` varchar(255) DEFAULT NULL,
  `com_fax` varchar(255) DEFAULT NULL,
  `com_website` varchar(255) DEFAULT NULL,
  `com_email` varchar(255) DEFAULT NULL,
  `com_person` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zhh_comcontacts
-- ----------------------------
INSERT INTO `zhh_comcontacts` VALUES ('0', '珠海创星有限公司', '中国珠海市民营科技园 珠海市唐家湾镇港湾大道科技一路10号', '86-0756-3810018', '', '86-0756-3810019', 'http://www.zhchuangxing.com', 'xcxxx@gamil.com', 'yyyy');

-- ----------------------------
-- Table structure for `zhh_comsummary`
-- ----------------------------
DROP TABLE IF EXISTS `zhh_comsummary`;
CREATE TABLE `zhh_comsummary` (
  `id` int(11) NOT NULL DEFAULT '0',
  `summary` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zhh_comsummary
-- ----------------------------
INSERT INTO `zhh_comsummary` VALUES ('0', '<p class=\"p0\" style=\"margin-top:0pt;margin-bottom:0pt;text-indent:26.88pt;\"><span style=\"font-size:14pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\">珠海创新塑胶制品有限公司构建于1995年，公司自成立之日起致力于</span><span style=\"font-size:14pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\">特殊高分子胶粘系列产品</span><span style=\"font-size:14pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\">、工业胶粘制品、打印机耗材、</span><span style=\"font-size:14pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\">软性电子线路</span><span style=\"font-size:14pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\">板</span><span style=\"font-size:14pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\">材料</span><span style=\"font-size:14pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\">方面的发展，并发展中形成科研、制造、贸易一体化的经营模式。同时在行业内赢得“科技领先、品质可靠、优质服务”的声誉。</span><span style=\"font-size:14pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\"><?xml:namespace prefix = o ns = \"urn:schemas-microsoft-com:office:office\" /><o:p></o:p></span></p>\r\n<p class=\"p0\" style=\"margin-top:0pt;margin-bottom:0pt;text-indent:26.88pt;\"><span style=\"font-size:14pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\">公司在发展中形成“营造一流团队，培育一流员工，制造一流产品，提供一流服务”的经营理念。</span><span style=\"font-size:14pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\"><o:p></o:p></span></p>\r\n<p class=\"p0\" style=\"margin-top:0pt;margin-bottom:0pt;text-indent:26.88pt;\"><span style=\"font-size:14pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\">我们研发、制造、销售的产品以高品质、高性能赢得国内外业内客户的信赖，我们顺应日益发展的IT时代潮流，不断的推出符合时代需求的新产品，从而形成具有竞争力的核心产业链。</span><span style=\"font-size:14pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\"><o:p></o:p></span></p>\r\n<p class=\"p18\" style=\"margin-top:0pt;margin-bottom:0pt;\"><span style=\"font-size:14pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\">我们用“优良品质、完善服务、持续创新”赢得了客户及合作伙伴的信任，并成为中国权威打印机耗材生产厂商的首选供应商。</span><span style=\"font-size:14pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\"><o:p></o:p></span></p>\r\n<p class=\"p18\" style=\"margin-top:0pt;margin-bottom:0pt;\"><span style=\"font-size:14pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\">主营范围：</span><span style=\"font-size:14pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\"><o:p></o:p></span></p>\r\n<p class=\"p0\" style=\"margin-top:0pt;margin-bottom:0pt;margin-left:41.05pt;text-indent:-18pt;\"><span style=\"font-size:10.5pt;font-family:\'宋体\';mso-spacerun:\'yes\';\">１)&nbsp;</span><span style=\"font-weight:bold;font-size:12pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\">&nbsp;&nbsp;工业胶粘制品（工业双面胶带、封装胶带、警示标志胶带等）；</span><span style=\"font-weight:bold;font-size:12pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\"><o:p></o:p></span></p>\r\n<p class=\"p0\" style=\"margin-top:0pt;margin-bottom:0pt;text-indent:23.04pt;\"><span style=\"font-weight:bold;font-size:12pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\">２)&nbsp;&nbsp;特殊工业胶粘制品（金手指、铜箔、铝箔等）；</span><span style=\"font-weight:bold;font-size:12pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\"><o:p></o:p></span></p>\r\n<p class=\"p0\" style=\"margin-top:0pt;margin-bottom:0pt;text-indent:23.04pt;\"><span style=\"font-weight:bold;font-size:12pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\">３)&nbsp;&nbsp;精密模切制品;&nbsp;</span><span style=\"font-weight:bold;font-size:12pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\"><o:p></o:p></span></p>\r\n<p class=\"p0\" style=\"margin-top:0pt;margin-bottom:0pt;text-indent:23.04pt;\"><span style=\"font-weight:bold;font-size:12pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\">４)&nbsp;&nbsp;打印耗材产品原材料配套;</span><span style=\"font-weight:bold;font-size:12pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\"><o:p></o:p></span></p>\r\n<p class=\"p0\" style=\"margin-top:0pt;margin-bottom:0pt;text-indent:27pt;\"><span style=\"font-weight:bold;font-size:12pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\">5)&nbsp;&nbsp;手机、MP播放器配套产品。</span><span style=\"font-weight:bold;font-size:12pt;font-family:\'华文中宋\';mso-spacerun:\'yes\';\"><o:p></o:p></span></p>\r\n<!--EndFragment-->');

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
  `img_category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zhh_photo
-- ----------------------------
INSERT INTO `zhh_photo` VALUES ('4', 'one.jpg', '1294590138', '精密模切');
INSERT INTO `zhh_photo` VALUES ('5', 'three.jpg', '1294590154', '特殊胶制品');
INSERT INTO `zhh_photo` VALUES ('6', 'two.jpg', '1294590170', '打印耗材');
INSERT INTO `zhh_photo` VALUES ('7', '精密模切-7-6.png', '1294592433', '精密模切');
INSERT INTO `zhh_photo` VALUES ('8', '精密模切-7-9.png', '1294592466', '精密模切');
INSERT INTO `zhh_photo` VALUES ('9', '精密模切-7-78.png', '1294592535', '精密模切');
INSERT INTO `zhh_photo` VALUES ('10', '精密模切-7-143.png', '1294592544', '精密模切');
INSERT INTO `zhh_photo` VALUES ('11', '精密模切-7-145.png', '1294592550', '精密模切');
INSERT INTO `zhh_photo` VALUES ('12', '精密模切-7-209.png', '1294592592', '精密模切');
INSERT INTO `zhh_photo` VALUES ('13', '精密模切-7-356.png', '1294592598', '精密模切');
INSERT INTO `zhh_photo` VALUES ('14', '精密模切-7-361.png', '1294592607', '精密模切');
INSERT INTO `zhh_photo` VALUES ('15', '打印机耗材配套产品-6-12.png', '1294592637', '打印耗材');
INSERT INTO `zhh_photo` VALUES ('16', '打印机耗材配套产品-6-16.png', '1294592642', '打印耗材');
INSERT INTO `zhh_photo` VALUES ('17', '打印机耗材配套产品-6-39.png', '1294592648', '打印耗材');
INSERT INTO `zhh_photo` VALUES ('18', '打印机耗材配套产品-6-42.png', '1294592652', '打印耗材');
INSERT INTO `zhh_photo` VALUES ('19', '打印机耗材配套产品-6-45.png', '1294592657', '打印耗材');
INSERT INTO `zhh_photo` VALUES ('20', '打印机耗材配套产品-6-60.png', '1294592662', '打印耗材');
INSERT INTO `zhh_photo` VALUES ('21', '打印机耗材配套产品-6-76.png', '1294592667', '打印耗材');
INSERT INTO `zhh_photo` VALUES ('22', '打印机耗材配套产品-6-80.png', '1294592671', '打印耗材');
INSERT INTO `zhh_photo` VALUES ('23', '打印机耗材配套产品-6-90.png', '1294592675', '打印耗材');
INSERT INTO `zhh_photo` VALUES ('24', '打印机耗材配套产品-6-105.png', '1294592680', '打印耗材');
INSERT INTO `zhh_photo` VALUES ('25', '打印机耗材配套产品-6-127.png', '1294592684', '打印耗材');
INSERT INTO `zhh_photo` VALUES ('26', '打印机耗材配套产品-6-130.png', '1294592688', '打印耗材');
INSERT INTO `zhh_photo` VALUES ('27', '精密模切-7-289.png', '1294592701', '精密模切');
INSERT INTO `zhh_photo` VALUES ('28', '精密模切-7-696.png', '1294592708', '精密模切');
INSERT INTO `zhh_photo` VALUES ('29', '精密模切-7-702.png', '1294592715', '精密模切');
INSERT INTO `zhh_photo` VALUES ('30', '精密模切-7-769.png', '1294592721', '精密模切');
INSERT INTO `zhh_photo` VALUES ('31', '精密模切-7-776.png', '1294592727', '精密模切');
INSERT INTO `zhh_photo` VALUES ('32', '精密模切-7-845.png', '1294592738', '精密模切');
INSERT INTO `zhh_photo` VALUES ('33', '精密模切-7-849.png', '1294592745', '精密模切');
INSERT INTO `zhh_photo` VALUES ('34', '精密模切-7-849.png', '2011-01-10 05:19:43', '打印耗材');
INSERT INTO `zhh_photo` VALUES ('35', '精密模切-7-6.png', '2011-01-10 05:20:22', '打印耗材');
INSERT INTO `zhh_photo` VALUES ('36', '精密模切-7-356.png', '2011-01-10 05:20:30', '打印耗材');
