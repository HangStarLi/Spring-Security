/*
Navicat MySQL Data Transfer

Source Server         : Mysql
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : security

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-12-12 19:34:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for my_userconnection
-- ----------------------------
DROP TABLE IF EXISTS `my_userconnection`;
CREATE TABLE `my_userconnection` (
  `userId` varchar(255) NOT NULL,
  `providerId` varchar(255) NOT NULL,
  `providerUserId` varchar(255) NOT NULL,
  `socialrank` int(11) NOT NULL,
  `displayName` varchar(255) DEFAULT NULL,
  `profileUrl` varchar(512) DEFAULT NULL,
  `imageUrl` varchar(512) DEFAULT NULL,
  `accessToken` varchar(512) NOT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `refreshToken` varchar(255) DEFAULT NULL,
  `expireTime` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`userId`,`providerId`,`providerUserId`),
  UNIQUE KEY `UserConnectionRank` (`userId`,`providerId`,`socialrank`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
