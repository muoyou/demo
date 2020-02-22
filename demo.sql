/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50535
Source Host           : localhost:3306
Source Database       : demo

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2020-02-22 17:49:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` varchar(36) NOT NULL,
  `name` varchar(20) DEFAULT NULL COMMENT '产品名',
  `number` varchar(18) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品表';

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('0de2ab51d205475c94eea636bb408d30', '234', '000009', null, '2020-02-22 13:52:53');
INSERT INTO `product` VALUES ('e2b5b865bf824a5eb877f0dd7bdd40de', '123', '000001', null, '2020-02-22 13:50:35');
INSERT INTO `product` VALUES ('e5e880cfcf4d41bfb30794f1fc5bf54d', '123', '000007', null, '2020-02-22 13:52:44');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(36) NOT NULL,
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(36) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123');

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse` (
  `id` varchar(36) NOT NULL,
  `product_id` varchar(36) DEFAULT NULL COMMENT '产品id',
  `amount` int(10) DEFAULT '0' COMMENT '数量',
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `warehouse_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='仓库表';

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES ('1ee2d43d4a6c42b397450070c5621053', 'e5e880cfcf4d41bfb30794f1fc5bf54d', '21', '2020-02-22 17:34:04', '2020-02-22 16:55:53');
DROP TRIGGER IF EXISTS `product_trigger`;
DELIMITER ;;
CREATE TRIGGER `product_trigger` BEFORE INSERT ON `product` FOR EACH ROW BEGIN
	DECLARE
		n INT;
 
SELECT
	IFNULL(max(number), 0)+1 INTO n FROM product;
SET NEW.number =  RIGHT ('1000000' +n ,6);
 
 END
;;
DELIMITER ;
