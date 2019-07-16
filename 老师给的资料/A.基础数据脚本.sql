/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.113
Source Server Version : 50713
Source Host           : 192.168.1.113:3306
Source Database       : ib

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2018-07-07 19:25:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bus
-- ----------------------------
DROP TABLE IF EXISTS `bus`;
CREATE TABLE `bus` (
  `busCode` int(11) NOT NULL AUTO_INCREMENT,
  `busLicense` varchar(20) NOT NULL,
  `busType` varchar(25) DEFAULT NULL,
  `busStatus` varchar(2) DEFAULT NULL,
  `startTime` datetime DEFAULT NULL,
  PRIMARY KEY (`busCode`),
  KEY `AK_Key_2` (`busLicense`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bus
-- ----------------------------

-- ----------------------------
-- Table structure for line
-- ----------------------------
DROP TABLE IF EXISTS `line`;
CREATE TABLE `line` (
  `lineCode` int(11) NOT NULL AUTO_INCREMENT,
  `lineName` varchar(20) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `startLineTime` datetime DEFAULT NULL,
  `direction` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`lineCode`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of line
-- ----------------------------

-- ----------------------------
-- Table structure for line_station_ref
-- ----------------------------
DROP TABLE IF EXISTS `line_station_ref`;
CREATE TABLE `line_station_ref` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `lineCode` int(11) DEFAULT NULL,
  `stationCode` int(11) DEFAULT NULL,
  `stationOrder` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_8` (`lineCode`),
  KEY `FK_Reference_9` (`stationCode`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`lineCode`) REFERENCES `line` (`lineCode`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`stationCode`) REFERENCES `station` (`stationCode`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of line_station_ref
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `permissionCode` int(11) NOT NULL AUTO_INCREMENT,
  `permissionName` varchar(50) DEFAULT NULL,
  `permissionDescribe` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`permissionCode`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'user_change_pass_home_menu', null);
INSERT INTO `permission` VALUES ('2', 'user_add', null);
INSERT INTO `permission` VALUES ('3', 'user_del', null);
INSERT INTO `permission` VALUES ('4', 'user_save', null);
INSERT INTO `permission` VALUES ('5', 'bus_add', null);
INSERT INTO `permission` VALUES ('6', 'bus_del', null);
INSERT INTO `permission` VALUES ('7', 'bus_save', null);
INSERT INTO `permission` VALUES ('8', 'line_add', null);
INSERT INTO `permission` VALUES ('9', 'line_del', null);
INSERT INTO `permission` VALUES ('10', 'line_save', null);
INSERT INTO `permission` VALUES ('11', 'station_add', null);
INSERT INTO `permission` VALUES ('12', 'station_del', null);
INSERT INTO `permission` VALUES ('13', 'station_save', null);
INSERT INTO `permission` VALUES ('14', 'scheduling_add', null);
INSERT INTO `permission` VALUES ('15', 'scheduling_del', null);
INSERT INTO `permission` VALUES ('16', 'scheduling_save', null);
INSERT INTO `permission` VALUES ('17', 'scheduling_read', null);
INSERT INTO `permission` VALUES ('18', 'scheduling_write', null);
INSERT INTO `permission` VALUES ('19', 'scheduling_home_menu', null);
INSERT INTO `permission` VALUES ('20', 'station_home_menu', null);
INSERT INTO `permission` VALUES ('21', 'line_home_menu', null);
INSERT INTO `permission` VALUES ('22', 'bus_home_menu', null);
INSERT INTO `permission` VALUES ('23', 'user_home_menu', null);
INSERT INTO `permission` VALUES ('24', 'base_data', null);
INSERT INTO `permission` VALUES ('25', 'scheduling', null);
INSERT INTO `permission` VALUES ('26', 'user_manager', null);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `roleCode` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(50) DEFAULT NULL,
  `roledescribe` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`roleCode`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员', '管理员角色');

-- ----------------------------
-- Table structure for role_permission_ref
-- ----------------------------
DROP TABLE IF EXISTS `role_permission_ref`;
CREATE TABLE `role_permission_ref` (
  `relationCode` int(11) NOT NULL AUTO_INCREMENT,
  `roleCode` int(11) DEFAULT NULL,
  `permissionCode` int(11) DEFAULT NULL,
  PRIMARY KEY (`relationCode`),
  KEY `FK_Reference_1` (`roleCode`),
  KEY `FK_Reference_2` (`permissionCode`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`roleCode`) REFERENCES `role` (`roleCode`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`permissionCode`) REFERENCES `permission` (`permissionCode`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_permission_ref
-- ----------------------------
INSERT INTO `role_permission_ref` VALUES ('1', '1', '1');
INSERT INTO `role_permission_ref` VALUES ('2', '1', '2');
INSERT INTO `role_permission_ref` VALUES ('3', '1', '3');
INSERT INTO `role_permission_ref` VALUES ('4', '1', '4');
INSERT INTO `role_permission_ref` VALUES ('5', '1', '5');
INSERT INTO `role_permission_ref` VALUES ('6', '1', '6');
INSERT INTO `role_permission_ref` VALUES ('7', '1', '7');
INSERT INTO `role_permission_ref` VALUES ('8', '1', '8');
INSERT INTO `role_permission_ref` VALUES ('9', '1', '9');
INSERT INTO `role_permission_ref` VALUES ('10', '1', '10');
INSERT INTO `role_permission_ref` VALUES ('11', '1', '11');
INSERT INTO `role_permission_ref` VALUES ('12', '1', '12');
INSERT INTO `role_permission_ref` VALUES ('13', '1', '13');
INSERT INTO `role_permission_ref` VALUES ('14', '1', '14');
INSERT INTO `role_permission_ref` VALUES ('15', '1', '15');
INSERT INTO `role_permission_ref` VALUES ('16', '1', '16');
INSERT INTO `role_permission_ref` VALUES ('17', '1', '17');
INSERT INTO `role_permission_ref` VALUES ('18', '1', '18');
INSERT INTO `role_permission_ref` VALUES ('19', '1', '19');
INSERT INTO `role_permission_ref` VALUES ('20', '1', '20');
INSERT INTO `role_permission_ref` VALUES ('21', '1', '21');
INSERT INTO `role_permission_ref` VALUES ('22', '1', '22');
INSERT INTO `role_permission_ref` VALUES ('23', '1', '23');
INSERT INTO `role_permission_ref` VALUES ('24', '1', '24');
INSERT INTO `role_permission_ref` VALUES ('25', '1', '25');
INSERT INTO `role_permission_ref` VALUES ('26', '1', '26');

-- ----------------------------
-- Table structure for scheduling
-- ----------------------------
DROP TABLE IF EXISTS `scheduling`;
CREATE TABLE `scheduling` (
  `code` int(20) NOT NULL AUTO_INCREMENT,
  `lineCode` int(11) DEFAULT NULL,
  `tcNumber` varchar(25) DEFAULT NULL,
  `tcTime` varchar(20) DEFAULT NULL,
  `userCode` int(11) DEFAULT NULL,
  `startStation` int(11) DEFAULT NULL,
  `endStation` int(11) DEFAULT NULL,
  `busLicense` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `FK_Reference_4` (`lineCode`),
  KEY `FK_Reference_5` (`userCode`),
  KEY `FK_SCH_REF_STATION_START` (`startStation`),
  KEY `FK_SCH_REF_STATION_END` (`endStation`),
  KEY `FK_Reference_3` (`busLicense`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`busLicense`) REFERENCES `bus` (`busLicense`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`lineCode`) REFERENCES `line` (`lineCode`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`userCode`) REFERENCES `sysuser` (`code`),
  CONSTRAINT `FK_SCH_REF_STATION_END` FOREIGN KEY (`endStation`) REFERENCES `station` (`stationCode`),
  CONSTRAINT `FK_SCH_REF_STATION_START` FOREIGN KEY (`startStation`) REFERENCES `station` (`stationCode`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of scheduling
-- ----------------------------

-- ----------------------------
-- Table structure for station
-- ----------------------------
DROP TABLE IF EXISTS `station`;
CREATE TABLE `station` (
  `stationCode` int(11) NOT NULL AUTO_INCREMENT,
  `stationName` varchar(50) DEFAULT NULL,
  `longitude` varchar(50) DEFAULT NULL,
  `latitude` varchar(50) DEFAULT NULL,
  `region` varchar(50) DEFAULT NULL,
  `street` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`stationCode`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of station
-- ----------------------------

-- ----------------------------
-- Table structure for sysuser
-- ----------------------------
DROP TABLE IF EXISTS `sysuser`;
CREATE TABLE `sysuser` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(25) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `name` varchar(25) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `idCard` varchar(25) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `driving` decimal(10,0) DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`code`),
  KEY `FK_Reference_10` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sysuser
-- ----------------------------
INSERT INTO `sysuser` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '管理员', '13122334455', '135487198010220011', '1', '0', '1');

-- ----------------------------
-- View structure for v_permission
-- ----------------------------
DROP VIEW IF EXISTS `v_permission`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`%`  VIEW `v_permission` AS SELECT DISTINCT
	sysuser.loginName,
	permission.permissionName
FROM
	sysuser
JOIN role ON sysuser.role = role.roleCode
JOIN role_permission_ref ON role_permission_ref.rolecode = role.roleCode
JOIN permission ON role_permission_ref.permissioncode = permission.permissioncode ;

-- ----------------------------
-- Procedure structure for PRO_DEL_LINE
-- ----------------------------
DROP PROCEDURE IF EXISTS `PRO_DEL_LINE`;

CREATE PROCEDURE PRO_DEL_LINE(IN in_lineCode varchar(20),OUT out_code int(1))
BEGIN
  #声明使用线路code查询排班数据结果个数
	declare countScheduling int default 0;
	#查询当前线路是否在排班表中使用
	select count(lineCode) countScheduling from scheduling where scheduling.lineCode = in_lineCode;
	#判断线路是否被使用
	if countScheduling = 0
	then
    #删除线路与站点关联关系
		delete from line_station_ref where line_station_ref.lineCode = in_lineCode;
		#删除线路
		delete from line where line.lineCode = in_lineCode;
		#返回成功信息编码 线路删除成功！
		set out_code = 1;
	else
	    #返回失败信息编码 线路数据已被排班，删除失败！
		set out_code = 0;
  end if;

END
;;
DELIMITER ;
