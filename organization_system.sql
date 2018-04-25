/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : organization_system

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-04-25 18:24:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_admin
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin`;
CREATE TABLE `tb_admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(8) DEFAULT NULL,
  `admin_gender` varchar(4) DEFAULT NULL,
  `admin_tel` varchar(16) DEFAULT NULL,
  `admin_age` varchar(4) DEFAULT NULL,
  `admin_wechat` varchar(16) DEFAULT NULL,
  `admin_qq` varchar(16) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------

-- ----------------------------
-- Table structure for tb_class
-- ----------------------------
DROP TABLE IF EXISTS `tb_class`;
CREATE TABLE `tb_class` (
  `class_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(32) DEFAULT NULL,
  `course_id` int(11) NOT NULL,
  `class_max` int(11) DEFAULT NULL,
  `class_time` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_class
-- ----------------------------

-- ----------------------------
-- Table structure for tb_course
-- ----------------------------
DROP TABLE IF EXISTS `tb_course`;
CREATE TABLE `tb_course` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_description` varchar(1024) DEFAULT NULL,
  `teacher_id` int(11) NOT NULL,
  `course_type` varchar(32) DEFAULT NULL,
  `organization_id` int(11) NOT NULL,
  `course_tags` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_course
-- ----------------------------

-- ----------------------------
-- Table structure for tb_grade
-- ----------------------------
DROP TABLE IF EXISTS `tb_grade`;
CREATE TABLE `tb_grade` (
  `grade_id` int(11) NOT NULL AUTO_INCREMENT,
  `course_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `grade_number` int(11) NOT NULL,
  PRIMARY KEY (`grade_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_grade
-- ----------------------------

-- ----------------------------
-- Table structure for tb_organization
-- ----------------------------
DROP TABLE IF EXISTS `tb_organization`;
CREATE TABLE `tb_organization` (
  `organization_id` int(11) NOT NULL AUTO_INCREMENT,
  `organization_name` varchar(32) DEFAULT NULL,
  `organization_description` varchar(1024) DEFAULT NULL,
  `organization_tags` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`organization_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_organization
-- ----------------------------

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_number` int(11) NOT NULL,
  `student_name` varchar(8) NOT NULL,
  `student_gender` varchar(4) DEFAULT NULL,
  `student_tel` varchar(16) DEFAULT NULL,
  `student_age` varchar(4) DEFAULT NULL,
  `student_qq` varchar(32) DEFAULT NULL,
  `student_wechat` varchar(32) DEFAULT NULL,
  `student_major` varchar(32) DEFAULT NULL,
  `student_department` varchar(16) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_student
-- ----------------------------

-- ----------------------------
-- Table structure for tb_student_class
-- ----------------------------
DROP TABLE IF EXISTS `tb_student_class`;
CREATE TABLE `tb_student_class` (
  `student_class_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `class_id` int(11) NOT NULL,
  PRIMARY KEY (`student_class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_student_class
-- ----------------------------

-- ----------------------------
-- Table structure for tb_student_course
-- ----------------------------
DROP TABLE IF EXISTS `tb_student_course`;
CREATE TABLE `tb_student_course` (
  `student_course_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  PRIMARY KEY (`student_course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_student_course
-- ----------------------------

-- ----------------------------
-- Table structure for tb_teacher
-- ----------------------------
DROP TABLE IF EXISTS `tb_teacher`;
CREATE TABLE `tb_teacher` (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `teacher_number` int(11) NOT NULL,
  `teacher_name` varchar(8) DEFAULT NULL,
  `teacher_gender` varchar(4) DEFAULT NULL,
  `teacher_age` varchar(4) DEFAULT NULL,
  `teacher_tel` varchar(16) DEFAULT NULL,
  `teacher_qq` varchar(32) DEFAULT NULL,
  `teacher_wechat` varchar(32) DEFAULT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_teacher
-- ----------------------------

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) DEFAULT NULL,
  `user_pwd` varchar(30) DEFAULT NULL,
  `user_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', '4QrcOUm6Wau+VuBX8g+IPg==', '1');
SET FOREIGN_KEY_CHECKS=1;
