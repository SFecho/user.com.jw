/*
 Navicat Premium Data Transfer

 Source Server         : LocalMariaDB
 Source Server Type    : MariaDB
 Source Server Version : 100138
 Source Host           : localhost:3306
 Source Schema         : user_manager

 Target Server Type    : MariaDB
 Target Server Version : 100138
 File Encoding         : 65001

 Date: 23/05/2019 00:13:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for content
-- ----------------------------
DROP TABLE IF EXISTS `content`;
CREATE TABLE `content`  (
  `id` int(20) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `layer` int(255) NULL DEFAULT NULL,
  `parentid` int(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  INDEX `id`(`id`) USING BTREE,
  CONSTRAINT `FK_CONTENT_ID` FOREIGN KEY (`id`) REFERENCES `content` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of content
-- ----------------------------
INSERT INTO `content` VALUES (1, '肝脏', 1, 0);
INSERT INTO `content` VALUES (2, '弥漫性病变', 2, 1);
INSERT INTO `content` VALUES (3, '占位性病变', 2, 1);
INSERT INTO `content` VALUES (4, '良性', 3, 3);
INSERT INTO `content` VALUES (5, '恶性', 3, 3);
INSERT INTO `content` VALUES (6, '原发', 4, 5);
INSERT INTO `content` VALUES (7, '转移', 4, 5);
INSERT INTO `content` VALUES (8, '肝胆外伤', 2, 1);
INSERT INTO `content` VALUES (9, '胆囊', 1, 0);
INSERT INTO `content` VALUES (10, '炎症', 2, 9);
INSERT INTO `content` VALUES (11, '急性', 3, 10);
INSERT INTO `content` VALUES (12, '慢性', 3, 10);
INSERT INTO `content` VALUES (13, '结石', 2, 9);
INSERT INTO `content` VALUES (14, '息肉', 2, 9);
INSERT INTO `content` VALUES (15, '胆囊占位性病变', 2, 9);
INSERT INTO `content` VALUES (16, '恶性', 3, 15);
INSERT INTO `content` VALUES (17, '良性增生性疾病', 3, 15);
INSERT INTO `content` VALUES (18, '胆道', 1, 0);
INSERT INTO `content` VALUES (19, '结石', 2, 18);
INSERT INTO `content` VALUES (20, '占位性疾病', 2, 18);
INSERT INTO `content` VALUES (21, '先天性疾病', 2, 18);
INSERT INTO `content` VALUES (22, '梗阻性黄疸', 2, 18);
INSERT INTO `content` VALUES (23, '胰腺', 1, 0);
INSERT INTO `content` VALUES (24, '胰腺炎', 2, 23);
INSERT INTO `content` VALUES (25, '急性', 3, 24);
INSERT INTO `content` VALUES (26, '慢性', 3, 24);
INSERT INTO `content` VALUES (27, '胰腺囊肿', 2, 23);
INSERT INTO `content` VALUES (28, '真性囊肿', 3, 27);
INSERT INTO `content` VALUES (29, '假性囊肿', 3, 27);
INSERT INTO `content` VALUES (30, '胰腺肿瘤', 2, 23);
INSERT INTO `content` VALUES (31, '脾', 1, 0);
INSERT INTO `content` VALUES (32, '脾大', 2, 31);
INSERT INTO `content` VALUES (33, '脾外伤、梗死', 2, 31);
INSERT INTO `content` VALUES (34, '脾肿瘤', 2, 31);
INSERT INTO `content` VALUES (35, '良性', 3, 34);
INSERT INTO `content` VALUES (36, '恶性', 3, 34);
INSERT INTO `content` VALUES (37, '原发', 4, 36);
INSERT INTO `content` VALUES (38, '转移', 4, 36);
INSERT INTO `content` VALUES (39, '肾脏', 1, 0);
INSERT INTO `content` VALUES (40, '弥漫性病变', 2, 39);
INSERT INTO `content` VALUES (41, '肾结石', 2, 39);
INSERT INTO `content` VALUES (42, '肾脏囊性疾病', 2, 39);
INSERT INTO `content` VALUES (43, '肾肿瘤', 2, 39);
INSERT INTO `content` VALUES (44, '良性', 3, 43);
INSERT INTO `content` VALUES (45, '恶性', 3, 43);
INSERT INTO `content` VALUES (46, '肾结核', 2, 39);
INSERT INTO `content` VALUES (47, '先天性变异', 2, 39);
INSERT INTO `content` VALUES (48, '移植肾', 2, 39);
INSERT INTO `content` VALUES (49, '输尿管', 1, 0);
INSERT INTO `content` VALUES (50, '结石', 2, 49);
INSERT INTO `content` VALUES (51, '占位性病变', 2, 49);
INSERT INTO `content` VALUES (52, '先天性变异', 2, 49);
INSERT INTO `content` VALUES (53, '狭窄', 2, 49);
INSERT INTO `content` VALUES (54, '膀胱', 1, 0);
INSERT INTO `content` VALUES (55, '炎症', 2, 54);
INSERT INTO `content` VALUES (56, '结石', 2, 54);
INSERT INTO `content` VALUES (57, '占位性病变', 2, 54);
INSERT INTO `content` VALUES (58, '良性', 3, 57);
INSERT INTO `content` VALUES (59, '恶性', 3, 57);
INSERT INTO `content` VALUES (60, '异物', 2, 54);
INSERT INTO `content` VALUES (61, '膀胱憩室', 2, 54);
INSERT INTO `content` VALUES (62, '前列腺', 1, 0);
INSERT INTO `content` VALUES (63, '前列腺炎', 2, 62);
INSERT INTO `content` VALUES (64, '良性增生', 2, 62);
INSERT INTO `content` VALUES (65, '前列腺癌', 2, 62);
INSERT INTO `content` VALUES (66, '囊肿', 2, 62);
INSERT INTO `content` VALUES (67, '结石', 2, 62);
INSERT INTO `content` VALUES (68, '睾丸、附睾', 1, 0);
INSERT INTO `content` VALUES (69, '鞘膜积液', 2, 68);
INSERT INTO `content` VALUES (70, '炎症', 2, 68);
INSERT INTO `content` VALUES (71, '睾丸扭转', 2, 68);
INSERT INTO `content` VALUES (72, '肿瘤', 2, 68);
INSERT INTO `content` VALUES (73, '原发', 3, 72);
INSERT INTO `content` VALUES (74, '转移', 3, 72);
INSERT INTO `content` VALUES (75, '外伤', 2, 68);
INSERT INTO `content` VALUES (76, '精索静脉曲张', 2, 68);
INSERT INTO `content` VALUES (77, '囊肿', 2, 68);
INSERT INTO `content` VALUES (78, '隐睾', 2, 68);
INSERT INTO `content` VALUES (79, '胃肠', 1, 0);
INSERT INTO `content` VALUES (80, '占位性病变', 2, 79);
INSERT INTO `content` VALUES (81, '良性', 3, 80);
INSERT INTO `content` VALUES (82, '恶性', 3, 80);
INSERT INTO `content` VALUES (83, '炎症', 2, 79);
INSERT INTO `content` VALUES (84, '腹膜后肿瘤', 1, 0);
INSERT INTO `content` VALUES (85, '原发性', 2, 84);
INSERT INTO `content` VALUES (86, '继发性', 2, 84);
INSERT INTO `content` VALUES (87, '血肿', 2, 84);
INSERT INTO `content` VALUES (88, '肾上腺肿瘤', 1, 0);
INSERT INTO `content` VALUES (89, '原发性', 2, 88);
INSERT INTO `content` VALUES (90, '继发性', 2, 88);
INSERT INTO `content` VALUES (91, '甲状腺', 1, 0);
INSERT INTO `content` VALUES (92, '甲状腺功能亢进', 2, 91);
INSERT INTO `content` VALUES (93, '桥本氏甲状腺炎', 2, 91);
INSERT INTO `content` VALUES (94, '弥漫性', 3, 93);
INSERT INTO `content` VALUES (95, '局灶性', 3, 93);
INSERT INTO `content` VALUES (96, '甲减', 2, 91);
INSERT INTO `content` VALUES (97, '结节性甲状腺肿', 2, 91);
INSERT INTO `content` VALUES (98, '甲状腺癌', 2, 91);
INSERT INTO `content` VALUES (99, '乳头状', 3, 98);
INSERT INTO `content` VALUES (100, '其他类型', 3, 98);
INSERT INTO `content` VALUES (101, '甲状旁腺', 1, 0);
INSERT INTO `content` VALUES (102, '增生', 2, 101);
INSERT INTO `content` VALUES (103, '腺瘤', 2, 101);
INSERT INTO `content` VALUES (104, '癌', 2, 101);
INSERT INTO `content` VALUES (105, '乳腺', 1, 0);
INSERT INTO `content` VALUES (106, '乳腺炎', 2, 105);
INSERT INTO `content` VALUES (107, '良性病变', 2, 105);
INSERT INTO `content` VALUES (108, '恶性病变', 2, 105);
INSERT INTO `content` VALUES (109, '淋巴系统', 1, 0);
INSERT INTO `content` VALUES (110, '炎症', 2, 109);
INSERT INTO `content` VALUES (111, '结核', 2, 109);
INSERT INTO `content` VALUES (112, '恶性淋巴瘤', 2, 109);
INSERT INTO `content` VALUES (113, '淋巴结转移癌', 2, 109);
INSERT INTO `content` VALUES (114, '肌肉、骨骼超声', 1, 0);

-- ----------------------------
-- Table structure for file_path
-- ----------------------------
DROP TABLE IF EXISTS `file_path`;
CREATE TABLE `file_path`  (
  `parentid` int(20) NOT NULL,
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `path` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of file_path
-- ----------------------------
INSERT INTO `file_path` VALUES (3, 'ddd', 'asda');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('201521012358', '奶子尧', '123456', 'M', 'simonfuecho@163.com');
INSERT INTO `user_info` VALUES ('201521012362', 'SimonFu', '123456', 'M', 'simonfuecho@163.com');

-- ----------------------------
-- Procedure structure for get_path
-- ----------------------------
DROP PROCEDURE IF EXISTS `get_path`;
delimiter ;;
CREATE PROCEDURE `get_path`(IN `file_name` varchar(100))
BEGIN
	#Routine body goes here...
	
#声明部分变量，游标
	DECLARE current_pid int;
	DECLARE prev int;
	DECLARE tmp_path VARCHAR(100);
	
	
	DROP TABLE IF EXISTS DUAL_TBL;
	
	CREATE TEMPORARY TABLE DUAL_TBL(
		path VARCHAR(100)
	)ENGINE=memory;	/*在内存中创建临时表*/
	
	SELECT path, parentid INTO tmp_path, current_pid from file_path WHERE name = file_name;
	INSERT INTO DUAL_TBL VALUE(tmp_path);
	
	WHILE current_pid <> 0 DO
		SELECT parentid, name INTO prev, tmp_path FROM content where id = current_pid;
		INSERT INTO DUAL_TBL VALUE(tmp_path);
		SET current_pid = prev;
	END WHILE;

	SELECT * FROM DUAL_TBL;
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
