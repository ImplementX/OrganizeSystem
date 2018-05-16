/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : organization_system

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2018-05-16 18:29:37
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_admin
-- ----------------------------
INSERT INTO `tb_admin` VALUES ('1', '超级管理员', '男', null, null, null, null, '1');

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
  `course_name` varchar(255) NOT NULL,
  `course_description` varchar(1024) DEFAULT NULL,
  `teacher_id` int(11) NOT NULL,
  `course_type` varchar(32) DEFAULT NULL,
  `organization_id` int(11) NOT NULL,
  `course_tag` int(255) DEFAULT NULL,
  `course_is_full` int(255) NOT NULL,
  `course_number_limit` int(11) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_course
-- ----------------------------
INSERT INTO `tb_course` VALUES ('1', '你所不知道的农村', '三农问题一直是国家关注的重点，浙江大学学生三农协会自成立以来一直践行聚焦三农的主旨，广泛开展、参与和三农问题相关的社会实践、社会调研和理论研究活动，尽己所能为农民、农村、农业服务。借助精品文化课程这一平台，三农协会希望可以使大家更加了解农村关注农村，更加了解三农支持三农。\r\n\r\n今年三农协会的精品文化课程延续了去年的“你所不知道的农村”的主题，但本次课程提升知识的广度，涉及理论层面的农村研究，创客和学生眼中的农村、农村传统文化中的趣味等多方面的内容。这有别于去年，我们希望以这样的方式，给以大家对农村的更全面的了解。', '1', '1', '1', '1', '0', '100');
INSERT INTO `tb_course` VALUES ('2', '笛箫艺术与演奏进阶班', '本课程面向有一定笛箫基础的同学（原则上为完成上学期完成基础课程任务学习的学员或具备相同基础同学），由于课时较短，本课程并不特别强调演奏技巧的熟练掌握，而注重于通过课程教学传播普及竹笛/洞箫艺术，同时为有意愿深入学习的同学打下基础。学员课程结束后应当在原有演奏基础上得以提升，达到演奏所教授简单曲目的目标。对笛箫文化能够加深了解，增加对笛箫等民乐艺术的兴趣。', '1', null, '3', '3', '0', '40');
INSERT INTO `tb_course` VALUES ('3', '跆拳道培训班', '跆拳道是一项以技击格斗为核心，以修身养性为基础，以磨练人的意志，振奋人的精神为目的的现代竞技体育运动。通过跆拳道的训练，可以使练习者在行为规范，道德修养和完善人格等方面得到提高和发展。\r\n\r\n开设本课程的目的是让同学们锻炼身体，增强体质，同时培养学生懂得：礼仪、廉耻、忍耐、克己、百折不屈的跆拳道精神。\r\n\r\n培训班包括对跆拳道基础（白带）内容和高级（色带）内容的教学，无论是否有跆拳道基础均可参加。本课程共20个课时，教练为原专业队队员赵杨炀，授课内容包括：步伐、腿法、品势和身体素质。\r\n\r\n教学内容：1.礼仪：跆拳道基本的礼仪，道服穿着及道带的系法，跆拳道立正姿势；铭记跆拳道精神。2.柔韧：横叉、竖叉等。3.步伐：前滑步，后滑步，上步，撤步及换架。4.腿法：前踢，跳前踢，横踢，下劈，蹬踢；定腿训练。5.品势：太极一章，直拳。6.身体素质：通过俯卧撑、仰卧起坐等方式锻炼身体素质。', '1', null, '2', '2', '0', '70');

-- ----------------------------
-- Table structure for tb_course_application
-- ----------------------------
DROP TABLE IF EXISTS `tb_course_application`;
CREATE TABLE `tb_course_application` (
  `course_application_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `student_id` int(11) NOT NULL,
  `course_application_result` int(11) NOT NULL DEFAULT '-1',
  `course_application_type` int(11) NOT NULL,
  PRIMARY KEY (`course_application_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_course_application
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
  `organization_name` varchar(32) NOT NULL,
  `organization_description` varchar(1024) NOT NULL,
  `organization_tag` int(255) DEFAULT NULL,
  `organization_owner` int(11) DEFAULT NULL,
  `organization_vice_owner` int(255) DEFAULT NULL,
  PRIMARY KEY (`organization_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_organization
-- ----------------------------
INSERT INTO `tb_organization` VALUES ('1', '学生跆拳道协会', '浙江大学学生跆拳道协会成立于2000年，是浙江省首个有权颁发跆拳道级别证书的跆拳道组织。协会以“推广跆拳道运动，弘扬跆拳道精神，提高大学生体质,丰富大学生生活”为宗旨，是校级三星级学生社团，协会分设训练部、行政部、宣传部三个部门，历届会员累计近8000人，现有在训会员近100人。历届教练、助教均为国家队、省队队员，全国冠军。协会通过组织会员进行跆拳道技术的训练，和跆拳道知识的讲座，以达到强身健体、磨练意志、完善人格的目的。', '2', null, null);
INSERT INTO `tb_organization` VALUES ('2', '浙江大学学生三农协会', '', '1', null, null);
INSERT INTO `tb_organization` VALUES ('3', '浙江大学学生笛箫协会', ' ', '3', null, null);

-- ----------------------------
-- Table structure for tb_student
-- ----------------------------
DROP TABLE IF EXISTS `tb_student`;
CREATE TABLE `tb_student` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_number` int(13) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_student
-- ----------------------------
INSERT INTO `tb_student` VALUES ('1', '601420301', '张三', '男', null, null, null, null, null, null, '2');

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
  `student_course` int(255) DEFAULT NULL,
  PRIMARY KEY (`student_course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_student_course
-- ----------------------------
INSERT INTO `tb_student_course` VALUES ('1', '1', '1', null);
INSERT INTO `tb_student_course` VALUES ('2', '1', '2', null);

-- ----------------------------
-- Table structure for tb_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_tag`;
CREATE TABLE `tb_tag` (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(255) NOT NULL,
  `tag_description` varchar(2048) DEFAULT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_tag
-- ----------------------------
INSERT INTO `tb_tag` VALUES ('1', '实践公益类', null);
INSERT INTO `tb_tag` VALUES ('2', '体育运动类', null);
INSERT INTO `tb_tag` VALUES ('3', '文化艺术类', null);
INSERT INTO `tb_tag` VALUES ('4', '兴趣爱好类', null);
INSERT INTO `tb_tag` VALUES ('5', '学术科技类', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', '4QrcOUm6Wau+VuBX8g+IPg==', '1');
INSERT INTO `tb_user` VALUES ('2', 'student', '4QrcOUm6Wau+VuBX8g+IPg==', '3');
INSERT INTO `tb_user` VALUES ('3', 'teacher', '4QrcOUm6Wau+VuBX8g+IPg==', '2');
SET FOREIGN_KEY_CHECKS=1;
