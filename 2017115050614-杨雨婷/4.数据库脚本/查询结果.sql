# Host:   (Version: 5.5.44)
# Date: 2019-06-25 14:58:57
# Generator: MySQL-Front 5.3  (Build 4.271)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "scheduling"
#

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

#
# Data for table "scheduling"
#

INSERT INTO `scheduling` VALUES (1,1,'1','1',1,1,1,'1');
