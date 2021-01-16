/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : course-online

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 16/01/2021 12:19:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `parent` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '父id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `sort` int(11) NULL DEFAULT NULL COMMENT '顺序',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('00000100', NULL, '前端技术', 100, NULL, '2021-01-09 11:16:39');
INSERT INTO `category` VALUES ('00000101', '00000100', 'Html/Css', 101, NULL, '2021-01-11 21:43:01');
INSERT INTO `category` VALUES ('00000102', '00000100', 'JavaScript', 102, NULL, '2021-01-11 21:43:40');
INSERT INTO `category` VALUES ('00000103', '00000100', 'Vue.js', 103, NULL, '2021-01-16 11:35:28');
INSERT INTO `category` VALUES ('00000104', '00000100', 'React.js', 104, NULL, '2021-01-16 11:35:32');
INSERT INTO `category` VALUES ('00000105', '00000100', 'Angular', 105, NULL, '2021-01-16 11:35:36');
INSERT INTO `category` VALUES ('00000106', '00000100', 'Node.js', 106, NULL, '2021-01-16 11:35:40');
INSERT INTO `category` VALUES ('00000108', '00000100', '小程序', 108, NULL, NULL);
INSERT INTO `category` VALUES ('00000200', '', '后端技术', 200, NULL, '2020-12-28 15:16:48');
INSERT INTO `category` VALUES ('00000201', '00000200', 'java', 201, NULL, NULL);
INSERT INTO `category` VALUES ('00000202', '00000200', 'springboot', 202, NULL, '2021-01-09 15:43:46');
INSERT INTO `category` VALUES ('00000205', '00000200', 'python', 205, NULL, NULL);
INSERT INTO `category` VALUES ('00000206', '00000200', '爬虫', 206, NULL, NULL);
INSERT INTO `category` VALUES ('00000300', '', '移动开发', 300, NULL, '2020-12-28 15:17:03');
INSERT INTO `category` VALUES ('00000301', '00000300', 'android', 301, NULL, NULL);
INSERT INTO `category` VALUES ('00000302', '00000300', 'ios', 302, NULL, NULL);
INSERT INTO `category` VALUES ('00000303', '00000300', 'react native', 303, NULL, NULL);
INSERT INTO `category` VALUES ('00000304', '00000300', 'ionic', 304, NULL, NULL);
INSERT INTO `category` VALUES ('00000400', NULL, '前沿技术', 400, NULL, '2021-01-09 14:45:55');
INSERT INTO `category` VALUES ('00000402', '00000400', '区块链', 402, NULL, NULL);
INSERT INTO `category` VALUES ('00000403', '00000400', '机器学习', 403, NULL, NULL);
INSERT INTO `category` VALUES ('00000404', '00000400', '深度学习', 404, NULL, NULL);
INSERT INTO `category` VALUES ('00000405', '00000400', '数据挖掘', 405, NULL, '2021-01-09 15:54:09');
INSERT INTO `category` VALUES ('00000500', NULL, '云计算&大数据', 500, NULL, '2020-12-28 15:17:39');
INSERT INTO `category` VALUES ('00000501', '00000500', '大数据', 501, NULL, NULL);
INSERT INTO `category` VALUES ('00000502', '00000500', 'hadoop', 502, NULL, NULL);
INSERT INTO `category` VALUES ('00000503', '00000500', 'spark', 503, NULL, NULL);
INSERT INTO `category` VALUES ('00000504', '00000500', 'hbase', 504, NULL, NULL);
INSERT INTO `category` VALUES ('00000505', '00000500', '阿里云', 505, NULL, NULL);
INSERT INTO `category` VALUES ('00000506', '00000500', 'docker', 506, NULL, NULL);
INSERT INTO `category` VALUES ('00000507', '00000500', 'kubernetes', 507, NULL, NULL);
INSERT INTO `category` VALUES ('00000600', NULL, '运维&测试', 600, NULL, '2020-12-28 15:17:39');
INSERT INTO `category` VALUES ('00000601', '00000600', '运维', 601, NULL, '2021-01-09 15:39:54');
INSERT INTO `category` VALUES ('00000602', '00000600', '自动化运维', 602, NULL, NULL);
INSERT INTO `category` VALUES ('00000603', '00000600', '中间件', 603, NULL, '2021-01-09 15:39:46');
INSERT INTO `category` VALUES ('00000604', '00000600', 'linux', 604, NULL, NULL);
INSERT INTO `category` VALUES ('00000605', '00000600', '测试', 605, NULL, '2021-01-09 15:39:42');
INSERT INTO `category` VALUES ('00000606', '00000600', '自动化测试', 606, NULL, NULL);
INSERT INTO `category` VALUES ('00000700', NULL, '数据库', 700, NULL, '2020-12-28 15:17:39');
INSERT INTO `category` VALUES ('00000701', '00000700', 'mysql', 701, NULL, NULL);
INSERT INTO `category` VALUES ('00000702', '00000700', 'redis', 702, NULL, NULL);
INSERT INTO `category` VALUES ('1347743751056924674', '00000100', 'Typescript', NULL, '2021-01-09 11:15:25', '2021-01-16 11:35:44');
INSERT INTO `category` VALUES ('1347795498152505346', '', '消息中间件', NULL, '2021-01-09 14:41:02', '2021-01-11 11:08:00');
INSERT INTO `category` VALUES ('1347795741317279745', '00000200', 'go', NULL, '2021-01-09 14:42:00', '2021-01-09 14:42:00');
INSERT INTO `category` VALUES ('1347800117612130305', '00000200', '支付技术', NULL, '2021-01-09 14:59:24', '2021-01-09 14:59:24');
INSERT INTO `category` VALUES ('1347810222948757505', '1347795498152505346', 'RocketMQ', NULL, '2021-01-09 15:39:33', '2021-01-16 11:36:08');
INSERT INTO `category` VALUES ('1347810351441260546', '00000600', 'selenium', NULL, '2021-01-09 15:40:04', '2021-01-09 15:40:04');
INSERT INTO `category` VALUES ('1347810882482089986', '00000600', 'ansible', NULL, '2021-01-09 15:42:10', '2021-01-09 15:42:10');
INSERT INTO `category` VALUES ('1347815177990451202', '00000600', 'saltstack', NULL, '2021-01-09 15:59:14', '2021-01-09 15:59:14');
INSERT INTO `category` VALUES ('1347826684526604290', '00000700', 'oracle', NULL, '2021-01-09 16:44:58', '2021-01-09 16:44:58');
INSERT INTO `category` VALUES ('1347882057077907458', '1347795498152505346', 'RabbitMQ', NULL, '2021-01-09 20:25:00', '2021-01-16 11:36:01');
INSERT INTO `category` VALUES ('1347882076388483074', '1347795498152505346', 'Kafka', NULL, '2021-01-09 20:25:04', '2021-01-16 11:35:52');
INSERT INTO `category` VALUES ('1348099061206487041', '00000200', '微服务', NULL, '2021-01-10 10:47:17', '2021-01-10 10:47:17');
INSERT INTO `category` VALUES ('1348100476045889537', '00000200', 'springcloud', NULL, '2021-01-10 10:52:55', '2021-01-10 10:52:55');
INSERT INTO `category` VALUES ('1348104277695922177', '00000700', 'mongodb', NULL, '2021-01-10 11:08:01', '2021-01-10 11:08:01');
INSERT INTO `category` VALUES ('1348104300877840385', '00000700', 'sqlserver', NULL, '2021-01-10 11:08:07', '2021-01-10 11:08:07');
INSERT INTO `category` VALUES ('1348104394968662018', '', '计算机基础', NULL, '2021-01-10 11:08:29', '2021-01-11 21:44:03');
INSERT INTO `category` VALUES ('1348104422453936129', '1348104394968662018', '数据结构', NULL, '2021-01-10 11:08:36', '2021-01-10 11:08:36');
INSERT INTO `category` VALUES ('1348104438253879298', '1348104394968662018', '算法', NULL, '2021-01-10 11:08:39', '2021-01-10 11:08:39');
INSERT INTO `category` VALUES ('1348104477806166018', '1348104394968662018', '计算机组成原理', NULL, '2021-01-10 11:08:49', '2021-01-10 11:08:49');
INSERT INTO `category` VALUES ('1348104497351622658', '1348104394968662018', '操作系统', NULL, '2021-01-10 11:08:53', '2021-01-10 11:08:53');
INSERT INTO `category` VALUES ('1348104515328409602', '1348104394968662018', '计算机网络', NULL, '2021-01-10 11:08:58', '2021-01-10 11:08:58');
INSERT INTO `category` VALUES ('1348460835772583938', '00000200', 'C', NULL, '2021-01-11 10:44:51', '2021-01-11 10:44:51');
INSERT INTO `category` VALUES ('1348460862230253569', '00000200', 'C++', NULL, '2021-01-11 10:44:57', '2021-01-11 10:45:04');
INSERT INTO `category` VALUES ('1348460912087945218', '00000200', 'C#', NULL, '2021-01-11 10:45:09', '2021-01-11 10:45:09');
INSERT INTO `category` VALUES ('1348524836267249665', '00000200', 'JVM', NULL, '2021-01-11 14:59:10', '2021-01-11 14:59:10');
INSERT INTO `category` VALUES ('1348524923273891842', '00000200', '嵌入式', NULL, '2021-01-11 14:59:31', '2021-01-11 14:59:31');
INSERT INTO `category` VALUES ('1348525026122420225', '00000500', '容器', NULL, '2021-01-11 14:59:55', '2021-01-11 14:59:55');
INSERT INTO `category` VALUES ('1348527133269446657', '00000600', 'Jenkins', NULL, '2021-01-11 15:08:18', '2021-01-11 15:08:18');
INSERT INTO `category` VALUES ('1348626599330824193', '00000100', 'WebApp', NULL, '2021-01-11 21:43:32', '2021-01-11 21:43:32');

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `course_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `is_delete` int(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '大章' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chapter
-- ----------------------------
INSERT INTO `chapter` VALUES ('00000001', '11111111', '测试大章01', NULL, '2020-12-14 21:39:18', 1);
INSERT INTO `chapter` VALUES ('00000002', '123123213', '测试大章02', NULL, '2020-12-14 21:33:47', 1);
INSERT INTO `chapter` VALUES ('00000003', '00000000', '测试大章03', NULL, NULL, 1);
INSERT INTO `chapter` VALUES ('00000004', '1343041788956405761', '测试大章04', NULL, '2020-12-20 11:13:05', 0);
INSERT INTO `chapter` VALUES ('00000005', '1343041788956405761', '测试大章05', NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000006', '1343041788956405761', '测试大章06', NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000007', '1343041788956405761', '测试大章07', NULL, NULL, 1);
INSERT INTO `chapter` VALUES ('00000008', '1343041788956405761', '测试大章08', NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000009', '1343041788956405761', '测试大章09', NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000010', '1343041788956405761', '测试大章10', NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000011', '1343041788956405761', '测试大章11', NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000012', '123213132213', '测试大章12', NULL, '2020-12-14 21:39:25', 0);
INSERT INTO `chapter` VALUES ('00000013', '1343094701405794305', '测试大章13', NULL, '2020-12-15 15:36:26', 0);
INSERT INTO `chapter` VALUES ('00000014', '1343094701405794305', '测试大章14', NULL, NULL, 1);
INSERT INTO `chapter` VALUES ('1338468722569986049', '00000001', '1111111', '2020-12-14 20:59:46', '2020-12-14 21:00:39', 1);
INSERT INTO `chapter` VALUES ('1338477928027037697', '00000001', 'aaaaa', '2020-12-14 21:36:20', '2020-12-14 21:36:20', 1);
INSERT INTO `chapter` VALUES ('1338478637887823874', '00000001', 'qqweqwe', '2020-12-14 21:39:10', '2020-12-14 21:39:10', 1);
INSERT INTO `chapter` VALUES ('1338661445444935682', '00000001', 'ceshi123', '2020-12-15 09:45:34', '2020-12-15 09:45:34', 1);
INSERT INTO `chapter` VALUES ('1338687023719960577', '00000001', '123', '2020-12-15 11:27:13', '2020-12-15 11:27:13', 1);
INSERT INTO `chapter` VALUES ('1338748459397775361', '00000001', '123123', '2020-12-15 15:31:20', '2020-12-15 15:31:20', 1);
INSERT INTO `chapter` VALUES ('1338748531288145922', '00000001', '123', '2020-12-15 15:31:37', '2020-12-15 15:31:37', 1);
INSERT INTO `chapter` VALUES ('1340505698831720450', '1343040305078759425', '123', '2020-12-20 11:53:59', '2020-12-20 11:53:59', 1);
INSERT INTO `chapter` VALUES ('1343094701405794305', '1343040305078759425', '123123', '2020-12-27 15:21:45', '2020-12-27 15:21:45', 1);
INSERT INTO `chapter` VALUES ('1343100259013857281', '00000001', '123', '2020-12-27 15:43:50', '2020-12-27 15:43:50', 1);
INSERT INTO `chapter` VALUES ('1343100290047512578', '1343040305078759425', '123aaaa', '2020-12-27 15:43:57', '2020-12-27 15:44:15', 1);
INSERT INTO `chapter` VALUES ('1348100676453928961', '000012123', '对接支付宝', '2021-01-10 10:53:42', '2021-01-11 10:48:30', 0);
INSERT INTO `chapter` VALUES ('1348114393761611777', '1347880545798942721', '概论', '2021-01-10 11:48:13', '2021-01-10 11:48:13', 0);
INSERT INTO `chapter` VALUES ('1348461791998398466', '000012123', '对接微信支付', '2021-01-11 10:48:39', '2021-01-11 10:48:39', 0);
INSERT INTO `chapter` VALUES ('1348518999402500097', '1347880545798942721', '物理层', '2021-01-11 14:35:58', '2021-01-11 14:35:58', 0);
INSERT INTO `chapter` VALUES ('1348519035309936641', '1347880545798942721', '数据链路层', '2021-01-11 14:36:07', '2021-01-11 14:36:07', 0);
INSERT INTO `chapter` VALUES ('1348519740418576386', '00000001', '导论', '2021-01-11 14:38:55', '2021-01-11 14:38:55', 0);
INSERT INTO `chapter` VALUES ('1348519767064989697', '00000001', '线性数据结构', '2021-01-11 14:39:01', '2021-01-11 14:39:01', 0);
INSERT INTO `chapter` VALUES ('1348519929569103874', '1343040305078759425', '导论', '2021-01-11 14:39:40', '2021-01-11 14:39:40', 1);

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `summary` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '概述',
  `time` int(11) NULL DEFAULT 0 COMMENT '时长|单位秒',
  `price` decimal(8, 2) NULL COMMENT '价格（元）',
  `image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面',
  `level` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '级别|枚举[CourseLevelEnum]：ONE(1, 初级),TWO(2, 中级),THREE(3, 高级)',
  `charge` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收费|枚举[CourseChargeEnum]：CHARGE(C, 收费),FREE(F, 免费)',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态|枚举[CourseStatusEnum]：PUBLISH(P, 发布),DRAFT(D, 草稿)',
  `enroll` int(11) NULL DEFAULT 0 COMMENT '报名数',
  `sort` int(11) NULL DEFAULT NULL COMMENT '顺序',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `teacher_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '讲师|teacher.id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '课程' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('00000001', '数据结构和算法', '这是一门测试课程', 246, 19.90, '', '2', 'C', 'P', 100, 0, 0, '2020-11-29 13:33:54', '2021-01-11 16:41:23', '123');
INSERT INTO `course` VALUES ('000012123', 'Java支付项目实战', '这是一门测试课程', 246, 19.90, 'http://localhost:9000/api/file/show/2021/01/16/6b61aa21-cc7c-403e-acf8-99decc3fba0e.jpg', '1', 'C', 'P', 100, 0, 0, '2020-11-29 13:33:54', '2021-01-16 12:09:58', '1348547421621317633');
INSERT INTO `course` VALUES ('1343040305078759425', 'Spring cloud入门', '123213', 623, 123.00, NULL, '1', 'F', 'P', 123, 123, 1, '2020-12-27 11:45:36', '2021-01-11 16:42:12', '123');
INSERT INTO `course` VALUES ('1347880545798942721', '计算机网络精讲', '计算机网络知识梳理', 123, 123.00, 'http://localhost:9000/api/file/show/2021/01/12/506c9af6-ce39-4d16-a7a3-8ff94936b452.jpg', '1', 'F', 'D', 123, NULL, 0, '2021-01-09 20:18:59', '2021-01-12 16:28:34', '1348627061677342722');
INSERT INTO `course` VALUES ('1348469445936304130', '123', '123', 0, 123.00, NULL, '1', 'F', 'D', 0, NULL, 1, '2021-01-11 11:19:04', '2021-01-11 11:19:04', '123');
INSERT INTO `course` VALUES ('1348551526196572161', '操作系统精讲', '123', 0, 123.00, 'http://localhost:9000/api/file/show/2021/01/12/1b62bce5-0f9e-498b-8521-5de2e104892e.jpg', '1', 'F', 'D', 0, NULL, 0, '2021-01-11 16:45:13', '2021-01-12 16:06:11', '1348627420260974593');

-- ----------------------------
-- Table structure for course_category
-- ----------------------------
DROP TABLE IF EXISTS `course_category`;
CREATE TABLE `course_category`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `course_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程|course.id',
  `category_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类|category.id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_course_id`(`course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '课程分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_category
-- ----------------------------
INSERT INTO `course_category` VALUES ('1348469446049550338', '1348469445936304130', '1347882057077907458', '2021-01-11 11:19:04', '2021-01-11 11:19:04');
INSERT INTO `course_category` VALUES ('1348550561259188225', '00000001', '1348104438253879298', '2021-01-11 16:41:23', '2021-01-11 16:41:23');
INSERT INTO `course_category` VALUES ('1348550561267576833', '00000001', '1348104422453936129', '2021-01-11 16:41:23', '2021-01-11 16:41:23');
INSERT INTO `course_category` VALUES ('1348550767564419074', '1343040305078759425', '1348100476045889537', '2021-01-11 16:42:13', '2021-01-11 16:42:13');
INSERT INTO `course_category` VALUES ('1348550767572807682', '1343040305078759425', '1348099061206487041', '2021-01-11 16:42:13', '2021-01-11 16:42:13');
INSERT INTO `course_category` VALUES ('1348550767572807683', '1343040305078759425', '00000202', '2021-01-11 16:42:13', '2021-01-11 16:42:13');
INSERT INTO `course_category` VALUES ('1348550767572807684', '1343040305078759425', '00000201', '2021-01-11 16:42:13', '2021-01-11 16:42:13');
INSERT INTO `course_category` VALUES ('1348904088116412417', '1348551526196572161', '1348104497351622658', '2021-01-12 16:06:11', '2021-01-12 16:06:11');
INSERT INTO `course_category` VALUES ('1348909722945748993', '1347880545798942721', '1348104515328409602', '2021-01-12 16:28:34', '2021-01-12 16:28:34');
INSERT INTO `course_category` VALUES ('1350294195310555137', '000012123', '1347882057077907458', '2021-01-16 12:09:58', '2021-01-16 12:09:58');
INSERT INTO `course_category` VALUES ('1350294195318943745', '000012123', '00000702', '2021-01-16 12:09:58', '2021-01-16 12:09:58');
INSERT INTO `course_category` VALUES ('1350294195318943746', '000012123', '00000701', '2021-01-16 12:09:58', '2021-01-16 12:09:58');
INSERT INTO `course_category` VALUES ('1350294195318943747', '000012123', '1348527133269446657', '2021-01-16 12:09:58', '2021-01-16 12:09:58');
INSERT INTO `course_category` VALUES ('1350294195318943748', '000012123', '00000604', '2021-01-16 12:09:58', '2021-01-16 12:09:58');
INSERT INTO `course_category` VALUES ('1350294195327332354', '000012123', '00000603', '2021-01-16 12:09:58', '2021-01-16 12:09:58');
INSERT INTO `course_category` VALUES ('1350294195327332355', '000012123', '00000506', '2021-01-16 12:09:58', '2021-01-16 12:09:58');
INSERT INTO `course_category` VALUES ('1350294195327332356', '000012123', '00000505', '2021-01-16 12:09:58', '2021-01-16 12:09:58');
INSERT INTO `course_category` VALUES ('1350294195327332357', '000012123', '00000301', '2021-01-16 12:09:58', '2021-01-16 12:09:58');
INSERT INTO `course_category` VALUES ('1350294195327332358', '000012123', '1348100476045889537', '2021-01-16 12:09:58', '2021-01-16 12:09:58');
INSERT INTO `course_category` VALUES ('1350294195327332359', '000012123', '1348099061206487041', '2021-01-16 12:09:58', '2021-01-16 12:09:58');
INSERT INTO `course_category` VALUES ('1350294195327332360', '000012123', '1347800117612130305', '2021-01-16 12:09:58', '2021-01-16 12:09:58');
INSERT INTO `course_category` VALUES ('1350294195335720961', '000012123', '00000206', '2021-01-16 12:09:58', '2021-01-16 12:09:58');
INSERT INTO `course_category` VALUES ('1350294195335720962', '000012123', '00000202', '2021-01-16 12:09:58', '2021-01-16 12:09:58');
INSERT INTO `course_category` VALUES ('1350294195335720963', '000012123', '00000201', '2021-01-16 12:09:58', '2021-01-16 12:09:58');
INSERT INTO `course_category` VALUES ('1350294195335720964', '000012123', '00000103', '2021-01-16 12:09:58', '2021-01-16 12:09:58');
INSERT INTO `course_category` VALUES ('1350294195335720965', '000012123', '00000102', '2021-01-16 12:09:58', '2021-01-16 12:09:58');

-- ----------------------------
-- Table structure for course_content
-- ----------------------------
DROP TABLE IF EXISTS `course_content`;
CREATE TABLE `course_content`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '课程id',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程内容',
  `is_delete` int(1) NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `edit_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '课程内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_content
-- ----------------------------
INSERT INTO `course_content` VALUES ('00000001', '<p><span class=\"fr-emoticon fr-deletable fr-emoticon-img\" style=\"background: url(https://cdnjs.cloudflare.com/ajax/libs/emojione/2.0.1/assets/svg/1f603.svg);\">&nbsp;</span> <span class=\"fr-emoticon fr-deletable fr-emoticon-img\" style=\"background: url(https://cdnjs.cloudflare.com/ajax/libs/emojione/2.0.1/assets/svg/1f69c.svg);\">&nbsp;</span> </p>', 0, '2021-01-11 13:17:16', '2021-01-11 13:17:16');
INSERT INTO `course_content` VALUES ('000012123', '<p><br></p><blockquote><p><strong>123123123</strong></p></blockquote><p><br></p>', 0, NULL, '2021-01-12 15:55:15');
INSERT INTO `course_content` VALUES ('1347880545798942721', '<p><img src=\"http://localhost:9000/api/file/show/2021/01/16/aafb173d-7c64-4e9b-a8d1-2bf5efc7b266.jpg\" style=\"width: 300px;\" class=\"fr-fic fr-dib fr-fil\"><img src=\"http://localhost:9000/api/file/show/2021/01/12/52b3ba94-baf4-466c-8c11-daf50648c9da.jpg\" style=\"width: 300px;\" class=\"fr-fic fr-dib fr-fil\"></p><p><img src=\"http://localhost:9000/api/file/show/2021/01/12/d0d3e9b3-6d48-4d64-a155-5a330a1b1366.jpg\" style=\"width: 300px;\" class=\"fr-fic fr-dib fr-fil\"></p><p><br></p><p><br></p><blockquote><p><strong>123123123</strong></p></blockquote><p><br></p>', 0, '2021-01-11 15:03:17', '2021-01-16 11:56:01');

-- ----------------------------
-- Table structure for course_content_file
-- ----------------------------
DROP TABLE IF EXISTS `course_content_file`;
CREATE TABLE `course_content_file`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `course_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程id',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `size` int(11) NULL DEFAULT NULL COMMENT '大小|字节b',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `is_delete` int(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '课程内容文件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_content_file
-- ----------------------------

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `path` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '相对路径',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `suffix` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '后缀',
  `size` int(11) NULL DEFAULT NULL COMMENT '大小|字节B',
  `use_to` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用途|枚举[FileUseEnum]：COURSE(C, 讲师), TEACHER(T, 课程)',
  `shard_index` int(11) NULL DEFAULT NULL COMMENT '已上传分片',
  `shard_size` int(11) NULL DEFAULT NULL COMMENT '分片大小|B',
  `shard_total` int(11) NULL DEFAULT NULL COMMENT '分片总数',
  `file_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件标识',
  `vod` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'vod|阿里云vod',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `key_unique`(`file_key`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('1350293487777492993', '2021/01/16/24148b54-e333-4759-aec9-231a0481f541.jpg', '24148b54-e333-4759-aec9-231a0481f541.jpg', '.jpg', 526843, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-16 12:07:09', '2021-01-16 12:07:09');
INSERT INTO `file` VALUES ('1350294183151153153', '2021/01/16/6b61aa21-cc7c-403e-acf8-99decc3fba0e.jpg', '6b61aa21-cc7c-403e-acf8-99decc3fba0e.jpg', '.jpg', 1313969, NULL, NULL, NULL, NULL, NULL, NULL, '2021-01-16 12:09:55', '2021-01-16 12:09:55');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `photo` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像url',
  `register_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '注册时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `is_delete` int(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `mobile_unique`(`mobile`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('00000000', '12345678901', 'e70e2222a9d67c4f2eae107533359aa4', '测试', NULL, '2020-11-29 13:33:54', '2020-12-03 13:44:16', NULL, NULL);

-- ----------------------------
-- Table structure for member_course
-- ----------------------------
DROP TABLE IF EXISTS `member_course`;
CREATE TABLE `member_course`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `member_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '会员id',
  `course_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程id',
  `at` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '报名时间',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `is_delete` int(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `member_course_unique`(`member_id`, `course_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会员课程报名' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member_course
-- ----------------------------

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称|菜单或按钮',
  `page` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '页面|路由',
  `request` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求|接口',
  `parent` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('01', '系统管理', NULL, NULL, NULL, '2020-12-03 13:47:32', NULL);
INSERT INTO `resource` VALUES ('0101', '用户管理', '/system/user', NULL, '01', '2020-12-03 13:47:32', NULL);
INSERT INTO `resource` VALUES ('010101', '保存', NULL, '[\"/system/admin/user/list\", \"/system/admin/user/save\"]', '0101', '2020-12-03 13:47:32', NULL);
INSERT INTO `resource` VALUES ('010102', '删除', NULL, '[\"/system/admin/user/delete\"]', '0101', '2020-12-03 13:47:32', NULL);
INSERT INTO `resource` VALUES ('010103', '重置密码', NULL, '[\"/system/admin/user/save-password\"]', '0101', '2020-12-03 13:47:32', NULL);
INSERT INTO `resource` VALUES ('0102', '资源管理', '/system/resource', NULL, '01', '2020-12-03 13:47:32', NULL);
INSERT INTO `resource` VALUES ('010201', '保存/显示', NULL, '[\"/system/admin/resource\"]', '0102', '2020-12-03 13:47:32', NULL);
INSERT INTO `resource` VALUES ('0103', '角色管理', '/system/role', NULL, '01', '2020-12-03 13:47:32', NULL);
INSERT INTO `resource` VALUES ('010301', '角色/权限管理', NULL, '[\"/system/admin/role\"]', '0103', '2020-12-03 13:47:32', NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色',
  `desc` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('00000000', '系统管理员', '管理用户、角色权限', '2020-12-03 13:48:13', '2020-12-03 13:48:13');
INSERT INTO `role` VALUES ('00000001', '开发', '维护资源', '2020-12-03 13:48:13', '2020-12-03 13:48:13');
INSERT INTO `role` VALUES ('00000002', '业务管理员', '负责业务管理', '2020-12-03 13:48:13', '2020-12-03 13:48:13');

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `role_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色|id',
  `resource_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源|id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色资源关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('00000000', '00000000', '01', '2020-12-03 13:49:49', '2020-12-03 13:49:49');
INSERT INTO `role_resource` VALUES ('00000001', '00000000', '0101', '2020-12-03 13:49:49', '2020-12-03 13:49:49');
INSERT INTO `role_resource` VALUES ('00000002', '00000000', '010101', '2020-12-03 13:49:49', '2020-12-03 13:49:49');
INSERT INTO `role_resource` VALUES ('00000003', '00000000', '010102', '2020-12-03 13:49:49', '2020-12-03 13:49:49');
INSERT INTO `role_resource` VALUES ('00000004', '00000000', '010103', '2020-12-03 13:49:49', '2020-12-03 13:49:49');
INSERT INTO `role_resource` VALUES ('00000005', '00000000', '0102', '2020-12-03 13:49:49', '2020-12-03 13:49:49');
INSERT INTO `role_resource` VALUES ('00000006', '00000000', '010201', '2020-12-03 13:49:49', '2020-12-03 13:49:49');
INSERT INTO `role_resource` VALUES ('00000007', '00000000', '0103', '2020-12-03 13:49:49', '2020-12-03 13:49:49');
INSERT INTO `role_resource` VALUES ('00000008', '00000000', '010301', '2020-12-03 13:49:49', '2020-12-03 13:49:49');

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `role_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色|id',
  `user_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户|id',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色用户关联' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_user
-- ----------------------------
INSERT INTO `role_user` VALUES ('00000000', '00000000', '10000000', '2020-12-03 13:50:23', '2020-12-03 13:50:23');

-- ----------------------------
-- Table structure for section
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `course_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程|course.id',
  `chapter_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '大章|chapter.id',
  `video` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '视频',
  `time` int(11) NULL DEFAULT NULL COMMENT '时长|单位秒',
  `charge` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '收费|C 收费；F 免费',
  `sort` int(11) NULL DEFAULT NULL COMMENT '顺序',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `vod` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'vod|阿里云vod',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '小节' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of section
-- ----------------------------
INSERT INTO `section` VALUES ('1343162951296737282', '123', '1343041788956405761', '00000004', '123', 123, 'F', 123, '2020-12-27 19:52:57', '2020-12-27 19:52:57', '123');
INSERT INTO `section` VALUES ('1348100769185796097', '支付宝api', '000012123', '1348100676453928961', '123', 123, 'F', NULL, '2021-01-10 10:54:05', '2021-01-10 10:54:05', '123');
INSERT INTO `section` VALUES ('1348114448295952385', '概论', '1347880545798942721', '1348114393761611777', '123', 123, 'F', NULL, '2021-01-10 11:48:26', '2021-01-10 11:48:26', '123');
INSERT INTO `section` VALUES ('1348115629302906881', '支付宝sdk', '000012123', '1348100676453928961', '123', 123, 'C', NULL, '2021-01-10 11:53:07', '2021-01-10 11:53:07', '123');
INSERT INTO `section` VALUES ('1348519809431654401', '概述', '00000001', '1348519740418576386', '123', 123, 'F', NULL, '2021-01-11 14:39:12', '2021-01-11 14:39:12', '123');
INSERT INTO `section` VALUES ('1348519862774812673', '数组', '00000001', '1348519767064989697', '123', 123, 'F', NULL, '2021-01-11 14:39:24', '2021-01-11 14:39:24', '123');

-- ----------------------------
-- Table structure for sms
-- ----------------------------
DROP TABLE IF EXISTS `sms`;
CREATE TABLE `sms`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `mobile` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `code` char(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '验证码',
  `use` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用途|枚举[SmsUseEnum]：REGISTER(R, 注册), FORGET(F, 忘记密码)',
  `at` datetime(3) NOT NULL COMMENT '生成时间',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用途|枚举[SmsStatusEnum]：USED(U, 已使用), NOT_USED(N, 未使用)',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '短信验证码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sms
-- ----------------------------
INSERT INTO `sms` VALUES ('00000000', '12345678901', '123456', 'R', '2020-11-29 13:33:54.000', 'N', '2020-12-03 13:51:29', '2020-12-03 13:51:29');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `image` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `position` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '职位',
  `motto` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '座右铭',
  `intro` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '简介',
  `is_delete` int(1) NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '讲师' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1348536842768740353', '张窟窿', 'cap', NULL, '前百度架构师', '哇哦', '中国科学院计算机技术研究所博士', 0, '2021-01-11 15:46:52', '2021-01-11 15:46:52');
INSERT INTO `teacher` VALUES ('1348547421621317633', '黄哈哈', '神思者', NULL, '前美团架构师', '向前冲', '有多年开发经验', 0, '2021-01-11 16:28:54', '2021-01-11 16:28:54');
INSERT INTO `teacher` VALUES ('1348627061677342722', '关过江', 'lewis', 'http://localhost:9000/api/file/show/2021/01/12/caa62043-1acb-42db-9175-d0817a561e82.jpg', 'Java高级工程师', '123', '中国科学院计算机技术研究所博士', 0, '2021-01-11 21:45:22', '2021-01-11 21:45:22');
INSERT INTO `teacher` VALUES ('1348627420260974593', '赵撞柱', 'javaman', 'http://localhost:9000/api/file/show/2021/01/12/cc6a9a1e-163d-4ece-9f0b-209b4ad92048.jpg', '资深全栈工程师', '啊啊啊啊啊啊啊啊', '中国科学院计算机技术研究所博士，拥有7年国内外IT公司工作和算法面试经验，在搜索、话题发现、社交网络方面、推荐算法等相关领域有所研究。曾为浙江大学、浙江省大学生程序设计竞赛的命题人，也是PAT 的命题人，国内ZOJ排名第一。', 0, '2021-01-11 21:46:47', '2021-01-11 21:46:47');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登陆名',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_name_unique`(`login_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('10000000', 'test', '测试', 'e70e2222a9d67c4f2eae107533359aa4', '2020-12-03 13:37:46', NULL);

SET FOREIGN_KEY_CHECKS = 1;
