SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for se_sensorinfo
-- ----------------------------
DROP TABLE IF EXISTS `se_sensorinfo`;
CREATE TABLE `se_sensorinfo` (
  `se_si_id` int(11) NOT NULL AUTO_INCREMENT,
  `sy_di_code` varchar(20) DEFAULT NULL,
  `se_si_tem` double DEFAULT NULL,
  `se_si_hum` double DEFAULT NULL,
  `se_si_pressure` double DEFAULT NULL,
  `se_si_imei` varchar(50) DEFAULT NULL,
  `se_si_sim` varchar(50) DEFAULT NULL,
  `se_si_online` smallint(6) DEFAULT NULL,
  `se_si_electric` double DEFAULT NULL,
  `se_si_time` datetime DEFAULT NULL,
  `se_si_up_time` double DEFAULT NULL,
  PRIMARY KEY (`se_si_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sl_lockinfo
-- ----------------------------
DROP TABLE IF EXISTS `sl_lockinfo`;
CREATE TABLE `sl_lockinfo` (
  `sl_li_id` int(11) NOT NULL AUTO_INCREMENT,
  `sy_di_code` varchar(20) DEFAULT NULL,
  `sl_li_imei` varchar(50) DEFAULT NULL,
  `sl_li_sim` varchar(50) DEFAULT NULL,
  `sl_li_electric` double DEFAULT NULL,
  `sl_li_status` smallint(6) DEFAULT NULL,
  `sl_li_online` smallint(6) DEFAULT NULL,
  `sl_li_time` datetime DEFAULT NULL,
  PRIMARY KEY (`sl_li_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for st_carinfo
-- ----------------------------
DROP TABLE IF EXISTS `st_carinfo`;
CREATE TABLE `st_carinfo` (
  `st_ci_id` int(11) NOT NULL AUTO_INCREMENT,
  `sy_di_code` varchar(20) DEFAULT NULL,
  `st_ci_nfc` varchar(30) DEFAULT NULL,
  `st_ci_carnum` varchar(20) DEFAULT NULL,
  `st_ci_imei` varchar(50) DEFAULT NULL,
  `st_ci_sim` varchar(50) DEFAULT NULL,
  `st_ci_status` smallint(6) DEFAULT NULL,
  `st_ci_money` double DEFAULT '0',
  `st_ci_online` smallint(6) DEFAULT NULL,
  `st_ci_electric` double DEFAULT NULL,
  PRIMARY KEY (`st_ci_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sy_deviceinfo
-- ----------------------------
DROP TABLE IF EXISTS `sy_deviceinfo`;
CREATE TABLE `sy_deviceinfo` (
  `sy_di_id` int(11) NOT NULL AUTO_INCREMENT,
  `sy_di_code` varchar(20) DEFAULT NULL,
  `sy_di_identify` varchar(20) DEFAULT NULL,
  `sy_di_type` smallint(6) DEFAULT NULL,
  `sy_di_status` smallint(6) DEFAULT NULL,
  `sy_di_reg_time` datetime DEFAULT NULL,
  `sy_ui_id` varchar(20) DEFAULT NULL,
  `sy_di_con_time` datetime DEFAULT NULL,
  `sy_di_con_num` int(6) DEFAULT NULL,
  `sy_di_con_type` smallint(6) DEFAULT NULL,
  `sy_di_ip` varchar(20) DEFAULT NULL,
  `sy_di_port` varchar(10) DEFAULT NULL,
  `sy_di_gps_num` double DEFAULT NULL,
  `sy_di_gps_value` varchar(60) DEFAULT NULL,
  `sy_di_gps_other` varchar(50) DEFAULT NULL,
  `sy_di_gps_time` datetime DEFAULT NULL,
  `sy_di_gprs_signal` smallint(6) DEFAULT NULL,
  `sy_di_gps_satellite` smallint(6) DEFAULT NULL,
  `sy_di_heartbeat_num` smallint(6) DEFAULT '10',
  `sy_di_nfc` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`sy_di_id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sy_userinfo
-- ----------------------------
DROP TABLE IF EXISTS `sy_userinfo`;
CREATE TABLE `sy_userinfo` (
  `sy_ui_id` varchar(20) NOT NULL,
  `sy_ui_name` varchar(20) DEFAULT NULL,
  `sy_ui_pwd` varchar(30) DEFAULT NULL,
  `sy_ui_phone` varchar(20) DEFAULT NULL,
  `sy_ui_email` varchar(50) DEFAULT NULL,
  `sy_ui_role` smallint(6) DEFAULT NULL,
  `sy_si_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`sy_ui_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
