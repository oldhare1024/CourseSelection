/*
 Navicat Premium Data Transfer

 Source Server         : javaweb
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : localhost:3306
 Source Schema         : studb

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 04/12/2023 17:22:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `cno` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `credit` int NULL DEFAULT NULL,
  `book` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `teacher` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ctime` int NULL DEFAULT NULL,
  PRIMARY KEY (`cno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', 'linux网络编程', 6, '深入理解linux内核', '张三', 12);
INSERT INTO `course` VALUES ('2', '计算机应用基础', 2, '计算机导论', '李四', 8);
INSERT INTO `course` VALUES ('3', '数据库', 8, 'Mysql从入门到精通', '赵五', 12);
INSERT INTO `course` VALUES ('4', 'C语言程序设计', 12, 'C语言嵌入式开发', '王六', 12);
INSERT INTO `course` VALUES ('5', '软件工程', 12, '软件工程导论', '孙七', 10);
INSERT INTO `course` VALUES ('6', '概率论', 8, '概率论与数理统计', '韩八', 10);

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc`  (
  `sno` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `cno` char(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `score` int NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sc
-- ----------------------------
INSERT INTO `sc` VALUES ('2', '3', 85);
INSERT INTO `sc` VALUES ('1', '1', 90);
INSERT INTO `sc` VALUES ('3', '4', 60);
INSERT INTO `sc` VALUES ('4', '2', 58);
INSERT INTO `sc` VALUES ('5', '5', 78);
INSERT INTO `sc` VALUES ('6', '6', 66);
INSERT INTO `sc` VALUES ('2', '1', 0);

-- ----------------------------
-- Table structure for stu
-- ----------------------------
DROP TABLE IF EXISTS `stu`;
CREATE TABLE `stu`  (
  `sno` int NOT NULL,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sex` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` int NULL DEFAULT NULL,
  `sdept` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`sno`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of stu
-- ----------------------------
INSERT INTO `stu` VALUES (1, '燕南飞', '男', 16, '计算机科学与技术');
INSERT INTO `stu` VALUES (2, '沈浪', '男', 18, '软件工程');
INSERT INTO `stu` VALUES (3, '李寻欢', '男', 18, '大数据');
INSERT INTO `stu` VALUES (4, '花满楼', '男', 20, '物联网工程');
INSERT INTO `stu` VALUES (5, '明月心', '女', 15, '软件工程');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uno` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `level` int NOT NULL,
  PRIMARY KEY (`uno`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'root', '123456', 1);
INSERT INTO `user` VALUES (2, 'zhangsan', '123456', 2);
INSERT INTO `user` VALUES (4, 'rabb1t', '110120', 2);

SET FOREIGN_KEY_CHECKS = 1;
