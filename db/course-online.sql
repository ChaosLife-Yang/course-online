/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : 127.0.0.1:3306
 Source Schema         : course-online

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 15/03/2021 11:08:10
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
INSERT INTO `category` VALUES ('00000102', '00000100', 'JavaScript', 102, NULL, '2021-01-11 21:43:40');
INSERT INTO `category` VALUES ('00000103', '00000100', 'Vue.js', 103, NULL, '2021-01-16 11:35:28');
INSERT INTO `category` VALUES ('00000104', '00000100', 'React.js', 104, NULL, '2021-01-16 11:35:32');
INSERT INTO `category` VALUES ('00000105', '00000100', 'Angular', 105, NULL, '2021-01-16 11:35:36');
INSERT INTO `category` VALUES ('00000106', '00000100', 'Node.js', 106, NULL, '2021-01-16 11:35:40');
INSERT INTO `category` VALUES ('00000108', '00000100', '小程序', 108, NULL, NULL);
INSERT INTO `category` VALUES ('00000200', '', '后端技术', 200, NULL, '2020-12-28 15:16:48');
INSERT INTO `category` VALUES ('00000201', '00000200', 'Java', 201, NULL, '2021-01-16 17:23:54');
INSERT INTO `category` VALUES ('00000202', '00000200', 'SpringBoot', 202, NULL, '2021-01-16 17:24:00');
INSERT INTO `category` VALUES ('00000205', '00000200', 'Python', 205, NULL, '2021-01-16 17:24:05');
INSERT INTO `category` VALUES ('00000206', '00000200', '爬虫', 206, NULL, NULL);
INSERT INTO `category` VALUES ('00000300', '', '移动开发', 300, NULL, '2020-12-28 15:17:03');
INSERT INTO `category` VALUES ('00000301', '00000300', '安卓开发', 301, NULL, '2021-01-16 17:26:17');
INSERT INTO `category` VALUES ('00000302', '00000300', 'IOS', 302, NULL, '2021-01-16 17:26:24');
INSERT INTO `category` VALUES ('00000303', '00000300', 'React Native', 303, NULL, '2021-01-16 17:26:35');
INSERT INTO `category` VALUES ('00000400', NULL, '前沿技术', 400, NULL, '2021-01-09 14:45:55');
INSERT INTO `category` VALUES ('00000402', '00000400', '区块链', 402, NULL, NULL);
INSERT INTO `category` VALUES ('00000403', '00000400', '机器学习', 403, NULL, NULL);
INSERT INTO `category` VALUES ('00000404', '00000400', '深度学习', 404, NULL, NULL);
INSERT INTO `category` VALUES ('00000405', '00000400', '数据挖掘', 405, NULL, '2021-01-09 15:54:09');
INSERT INTO `category` VALUES ('00000500', NULL, '云计算&大数据', 500, NULL, '2020-12-28 15:17:39');
INSERT INTO `category` VALUES ('00000501', '00000500', '大数据', 501, NULL, NULL);
INSERT INTO `category` VALUES ('00000507', '00000500', 'Kubernetes', 507, NULL, '2021-01-16 17:25:36');
INSERT INTO `category` VALUES ('00000600', NULL, '运维&测试', 600, NULL, '2020-12-28 15:17:39');
INSERT INTO `category` VALUES ('00000601', '00000600', '运维', 601, NULL, '2021-01-09 15:39:54');
INSERT INTO `category` VALUES ('00000602', '00000600', '自动化运维', 602, NULL, NULL);
INSERT INTO `category` VALUES ('00000603', '00000600', '中间件', 603, NULL, '2021-01-09 15:39:46');
INSERT INTO `category` VALUES ('00000604', '00000600', 'Linux', 604, NULL, '2021-01-16 17:25:24');
INSERT INTO `category` VALUES ('00000605', '00000600', '测试', 605, NULL, '2021-01-09 15:39:42');
INSERT INTO `category` VALUES ('00000606', '00000600', '自动化测试', 606, NULL, NULL);
INSERT INTO `category` VALUES ('00000700', NULL, '数据库', 700, NULL, '2020-12-28 15:17:39');
INSERT INTO `category` VALUES ('00000701', '00000700', 'MySql', 701, NULL, '2021-01-16 17:24:59');
INSERT INTO `category` VALUES ('00000702', '00000700', 'Redis', 702, NULL, '2021-01-16 17:24:53');
INSERT INTO `category` VALUES ('1347743751056924674', '00000100', 'Typescript', NULL, '2021-01-09 11:15:25', '2021-01-16 11:35:44');
INSERT INTO `category` VALUES ('1347795498152505346', '', '消息中间件', NULL, '2021-01-09 14:41:02', '2021-01-11 11:08:00');
INSERT INTO `category` VALUES ('1347795741317279745', '00000200', 'go', NULL, '2021-01-09 14:42:00', '2021-01-09 14:42:00');
INSERT INTO `category` VALUES ('1347800117612130305', '00000200', '支付技术', NULL, '2021-01-09 14:59:24', '2021-01-09 14:59:24');
INSERT INTO `category` VALUES ('1347810222948757505', '1347795498152505346', 'RocketMQ', NULL, '2021-01-09 15:39:33', '2021-01-16 11:36:08');
INSERT INTO `category` VALUES ('1347826684526604290', '00000700', 'Oracle', NULL, '2021-01-09 16:44:58', '2021-01-16 17:24:50');
INSERT INTO `category` VALUES ('1347882057077907458', '1347795498152505346', 'RabbitMQ', NULL, '2021-01-09 20:25:00', '2021-01-16 11:36:01');
INSERT INTO `category` VALUES ('1347882076388483074', '1347795498152505346', 'Kafka', NULL, '2021-01-09 20:25:04', '2021-01-16 11:35:52');
INSERT INTO `category` VALUES ('1348099061206487041', '00000200', '微服务', NULL, '2021-01-10 10:47:17', '2021-01-10 10:47:17');
INSERT INTO `category` VALUES ('1348100476045889537', '00000200', 'SpringCloud', NULL, '2021-01-10 10:52:55', '2021-01-16 17:24:17');
INSERT INTO `category` VALUES ('1348104277695922177', '00000700', 'MongoDB', NULL, '2021-01-10 11:08:01', '2021-01-16 17:24:46');
INSERT INTO `category` VALUES ('1348104300877840385', '00000700', 'Sql Server', NULL, '2021-01-10 11:08:07', '2021-01-16 17:24:39');
INSERT INTO `category` VALUES ('1348104394968662018', '', '计算机基础', NULL, '2021-01-10 11:08:29', '2021-01-11 21:44:03');
INSERT INTO `category` VALUES ('1348104422453936129', '1348104394968662018', '数据结构', NULL, '2021-01-10 11:08:36', '2021-01-10 11:08:36');
INSERT INTO `category` VALUES ('1348104438253879298', '1348104394968662018', '算法', NULL, '2021-01-10 11:08:39', '2021-01-10 11:08:39');
INSERT INTO `category` VALUES ('1348104477806166018', '1348104394968662018', '计算机组成原理', NULL, '2021-01-10 11:08:49', '2021-01-10 11:08:49');
INSERT INTO `category` VALUES ('1348104497351622658', '1348104394968662018', '操作系统', NULL, '2021-01-10 11:08:53', '2021-01-10 11:08:53');
INSERT INTO `category` VALUES ('1348104515328409602', '1348104394968662018', '计算机网络', NULL, '2021-01-10 11:08:58', '2021-01-10 11:08:58');
INSERT INTO `category` VALUES ('1348460862230253569', '00000200', 'C / C++', NULL, '2021-01-11 10:44:57', '2021-03-14 11:48:27');
INSERT INTO `category` VALUES ('1348460912087945218', '00000200', 'C#', NULL, '2021-01-11 10:45:09', '2021-01-11 10:45:09');
INSERT INTO `category` VALUES ('1348525026122420225', '00000500', '容器化技术', NULL, '2021-01-11 14:59:55', '2021-03-14 11:47:34');
INSERT INTO `category` VALUES ('1362210423173672962', '00000100', 'HTML/CSS', NULL, '2021-02-18 09:20:48', '2021-02-18 09:20:48');
INSERT INTO `category` VALUES ('1367806028090986498', '1348104394968662018', '微机接口', NULL, '2021-03-05 19:55:44', '2021-03-05 19:55:44');
INSERT INTO `category` VALUES ('1367806101751353346', '1347795498152505346', '消息队列', NULL, '2021-03-05 19:56:02', '2021-03-05 19:56:02');
INSERT INTO `category` VALUES ('1370944745647439873', '00000400', '人工智能', NULL, '2021-03-14 11:47:53', '2021-03-14 11:47:53');
INSERT INTO `category` VALUES ('1370944926782652417', '00000200', '.net', NULL, '2021-03-14 11:48:36', '2021-03-14 11:48:36');
INSERT INTO `category` VALUES ('1370945075638501378', '00000700', '分库分表', NULL, '2021-03-14 11:49:12', '2021-03-14 11:49:12');

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `course_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `sort` int(11) NULL DEFAULT NULL COMMENT '顺序',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `is_delete` int(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '大章' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chapter
-- ----------------------------
INSERT INTO `chapter` VALUES ('00000004', '1343041788956405761', '测试大章04', NULL, NULL, '2020-12-20 11:13:05', 0);
INSERT INTO `chapter` VALUES ('00000005', '1343041788956405761', '测试大章05', NULL, NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000006', '1343041788956405761', '测试大章06', NULL, NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000008', '1343041788956405761', '测试大章08', NULL, NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000009', '1343041788956405761', '测试大章09', NULL, NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000010', '1343041788956405761', '测试大章10', NULL, NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000011', '1343041788956405761', '测试大章11', NULL, NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000012', '000012123', '测试大章12', 2, NULL, '2021-03-14 11:36:30', 0);
INSERT INTO `chapter` VALUES ('00000013', '1343094701405794305', '测试大章13', NULL, NULL, '2020-12-15 15:36:26', 0);
INSERT INTO `chapter` VALUES ('1348100676453928961', '000012123', '第三方支付——支付宝', 0, '2021-01-10 10:53:42', '2021-01-25 20:57:35', 0);
INSERT INTO `chapter` VALUES ('1348114393761611777', '1347880545798942721', '概论', 0, '2021-01-10 11:48:13', '2021-01-17 16:33:22', 0);
INSERT INTO `chapter` VALUES ('1348461791998398466', '000012123', '第三方支付——接微信支付', 1, '2021-01-11 10:48:39', '2021-01-25 20:57:44', 0);
INSERT INTO `chapter` VALUES ('1348518999402500097', '1347880545798942721', '物理层', 1, '2021-01-11 14:35:58', '2021-01-17 16:33:27', 0);
INSERT INTO `chapter` VALUES ('1348519035309936641', '1347880545798942721', '数据链路层', 2, '2021-01-11 14:36:07', '2021-01-17 16:33:17', 0);
INSERT INTO `chapter` VALUES ('1348519740418576386', '00000001', '导论', NULL, '2021-01-11 14:38:55', '2021-01-11 14:38:55', 0);
INSERT INTO `chapter` VALUES ('1348519767064989697', '00000001', '线性数据结构', NULL, '2021-01-11 14:39:01', '2021-01-11 14:39:01', 0);
INSERT INTO `chapter` VALUES ('1355363441228996610', '1355363330293850114', '为什么需要消息队列', 0, '2021-01-30 11:53:20', '2021-01-30 11:53:20', 0);
INSERT INTO `chapter` VALUES ('1355479248218902530', '1347880545798942721', '应用层', 3, '2021-01-30 19:33:31', '2021-01-30 19:33:31', 0);
INSERT INTO `chapter` VALUES ('1359347981123342338', '1355363330293850114', 'RabbitMQ入门', 1, '2021-02-10 11:46:29', '2021-03-14 11:56:18', 0);
INSERT INTO `chapter` VALUES ('1370946841574703105', '1355363330293850114', 'RocketMQ入门', 2, '2021-03-14 11:56:13', '2021-03-14 11:56:13', 0);
INSERT INTO `chapter` VALUES ('1370971839190290433', '1355363330293850114', 'ActiveMQ介绍', 3, '2021-03-14 13:35:33', '2021-03-14 13:35:33', 0);
INSERT INTO `chapter` VALUES ('1370971887458340865', '1355363330293850114', 'Kafka介绍', 4, '2021-03-14 13:35:44', '2021-03-14 13:35:44', 1);

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
INSERT INTO `course` VALUES ('00000001', '数据结构和算法精讲', '这是一门测试课程', 1113, 19.90, 'https://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/efc5c3c5-791d-49e7-a8b8-ea1543c537bb.jpg', '2', 'C', 'P', 100, 0, 0, '2020-11-29 13:33:54', '2021-03-14 11:52:46', '1348627420260974593');
INSERT INTO `course` VALUES ('000012123', 'Java支付项目实战', '这是一门测试课程', 990, 19.90, 'https://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/a8c1d47a-4a57-43fb-9f46-613de92cfb6b.jpg', '1', 'C', 'P', 100, 0, 0, '2020-11-29 13:33:54', '2021-01-30 11:51:45', '1348547421621317633');
INSERT INTO `course` VALUES ('1347880545798942721', '计算机网络精讲', '计算机网络知识梳理', 1727, 123.00, 'https://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/f310bea1-4293-4f14-8078-ae2d6552b45f.png', '1', 'F', 'D', 123, NULL, 0, '2021-01-09 20:18:59', '2021-01-30 17:22:16', '1348627061677342722');
INSERT INTO `course` VALUES ('1348551526196572161', '操作系统精讲', '1231', 123, 123.00, 'https://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/ae3c6623-e534-4ee6-9456-43e3d95c1b0a.jpg', '1', 'F', 'D', 0, NULL, 1, '2021-01-11 16:45:13', '2021-01-30 11:51:25', '1348627420260974593');
INSERT INTO `course` VALUES ('1355363330293850114', '消息中间件精讲', '消息中间件精讲', 3678, 0.00, 'https://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/ae3c6623-e534-4ee6-9456-43e3d95c1b0a.jpg', '1', 'F', 'P', 0, NULL, 0, '2021-01-30 11:52:54', '2021-03-14 12:08:31', '1348627420260974593');
INSERT INTO `course` VALUES ('1370289707725414401', 'Vue 从入门到实战', '课程从 Vue3 基础语法，到组件原理、动画、代码设计，再到新语法扩展，全面系统地梳理 Vue 知识点。学习过程中，老师将倾囊相授多年的“避坑经验” ，带你以企业级代码质量和工程开发流程完成“京东到家”应用，实现对框架的彻底掌握', 0, 123.00, NULL, '1', 'F', 'D', 0, NULL, 0, '2021-03-12 16:25:00', '2021-03-12 16:57:26', '1348536842768740353');

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
INSERT INTO `course_category` VALUES ('1348550767564419074', '1343040305078759425', '1348100476045889537', '2021-01-11 16:42:13', '2021-01-11 16:42:13');
INSERT INTO `course_category` VALUES ('1348550767572807682', '1343040305078759425', '1348099061206487041', '2021-01-11 16:42:13', '2021-01-11 16:42:13');
INSERT INTO `course_category` VALUES ('1348550767572807683', '1343040305078759425', '00000202', '2021-01-11 16:42:13', '2021-01-11 16:42:13');
INSERT INTO `course_category` VALUES ('1348550767572807684', '1343040305078759425', '00000201', '2021-01-11 16:42:13', '2021-01-11 16:42:13');
INSERT INTO `course_category` VALUES ('1355092891946024962', '1355092891740504065', '1348104477806166018', '2021-01-29 17:58:17', '2021-01-29 17:58:17');
INSERT INTO `course_category` VALUES ('1355157541277003778', '1355157541126008833', '1347882057077907458', '2021-01-29 22:15:10', '2021-01-29 22:15:10');
INSERT INTO `course_category` VALUES ('1355361476482785282', '1355361476390510593', '1348104497351622658', '2021-01-30 11:45:32', '2021-01-30 11:45:32');
INSERT INTO `course_category` VALUES ('1355361802615087105', '1355361802547978242', '1348104497351622658', '2021-01-30 11:46:50', '2021-01-30 11:46:50');
INSERT INTO `course_category` VALUES ('1355362955818000385', '1348551526196572161', '1348104497351622658', '2021-01-30 11:51:25', '2021-01-30 11:51:25');
INSERT INTO `course_category` VALUES ('1355363039817326594', '000012123', '1347882057077907458', '2021-01-30 11:51:45', '2021-01-30 11:51:45');
INSERT INTO `course_category` VALUES ('1355363039825715202', '000012123', '00000702', '2021-01-30 11:51:45', '2021-01-30 11:51:45');
INSERT INTO `course_category` VALUES ('1355363039825715203', '000012123', '00000701', '2021-01-30 11:51:45', '2021-01-30 11:51:45');
INSERT INTO `course_category` VALUES ('1355363039825715204', '000012123', '00000604', '2021-01-30 11:51:45', '2021-01-30 11:51:45');
INSERT INTO `course_category` VALUES ('1355363039825715205', '000012123', '00000603', '2021-01-30 11:51:45', '2021-01-30 11:51:45');
INSERT INTO `course_category` VALUES ('1355363039825715207', '000012123', '00000405', '2021-01-30 11:51:45', '2021-01-30 11:51:45');
INSERT INTO `course_category` VALUES ('1355363039834103809', '000012123', '00000301', '2021-01-30 11:51:45', '2021-01-30 11:51:45');
INSERT INTO `course_category` VALUES ('1355363039834103810', '000012123', '1348100476045889537', '2021-01-30 11:51:45', '2021-01-30 11:51:45');
INSERT INTO `course_category` VALUES ('1355363039834103811', '000012123', '1348099061206487041', '2021-01-30 11:51:45', '2021-01-30 11:51:45');
INSERT INTO `course_category` VALUES ('1355363039834103812', '000012123', '1347800117612130305', '2021-01-30 11:51:45', '2021-01-30 11:51:45');
INSERT INTO `course_category` VALUES ('1355363039834103813', '000012123', '00000206', '2021-01-30 11:51:45', '2021-01-30 11:51:45');
INSERT INTO `course_category` VALUES ('1355363039842492418', '000012123', '00000202', '2021-01-30 11:51:45', '2021-01-30 11:51:45');
INSERT INTO `course_category` VALUES ('1355363039842492419', '000012123', '00000201', '2021-01-30 11:51:45', '2021-01-30 11:51:45');
INSERT INTO `course_category` VALUES ('1355363039842492420', '000012123', '00000103', '2021-01-30 11:51:45', '2021-01-30 11:51:45');
INSERT INTO `course_category` VALUES ('1355363039842492421', '000012123', '00000102', '2021-01-30 11:51:45', '2021-01-30 11:51:45');
INSERT INTO `course_category` VALUES ('1355446219614212097', '1347880545798942721', '1348104515328409602', '2021-01-30 17:22:16', '2021-01-30 17:22:16');
INSERT INTO `course_category` VALUES ('1370297870902157313', '1370289707725414401', '1362210423173672962', '2021-03-12 16:57:26', '2021-03-12 16:57:26');
INSERT INTO `course_category` VALUES ('1370297870902157314', '1370289707725414401', '00000108', '2021-03-12 16:57:26', '2021-03-12 16:57:26');
INSERT INTO `course_category` VALUES ('1370297870902157315', '1370289707725414401', '00000106', '2021-03-12 16:57:26', '2021-03-12 16:57:26');
INSERT INTO `course_category` VALUES ('1370297870902157316', '1370289707725414401', '00000103', '2021-03-12 16:57:26', '2021-03-12 16:57:26');
INSERT INTO `course_category` VALUES ('1370297870902157317', '1370289707725414401', '00000102', '2021-03-12 16:57:26', '2021-03-12 16:57:26');
INSERT INTO `course_category` VALUES ('1370945975459315713', '00000001', '1348104438253879298', '2021-03-14 11:52:46', '2021-03-14 11:52:46');
INSERT INTO `course_category` VALUES ('1370945975467704322', '00000001', '1348104422453936129', '2021-03-14 11:52:46', '2021-03-14 11:52:46');
INSERT INTO `course_category` VALUES ('1370945975467704323', '00000001', '1348460862230253569', '2021-03-14 11:52:46', '2021-03-14 11:52:46');
INSERT INTO `course_category` VALUES ('1370945975467704324', '00000001', '00000201', '2021-03-14 11:52:46', '2021-03-14 11:52:46');
INSERT INTO `course_category` VALUES ('1370949938187403265', '1355363330293850114', '1347795498152505346', '2021-03-14 12:08:31', '2021-03-14 12:08:31');
INSERT INTO `course_category` VALUES ('1370949938225152002', '1355363330293850114', '1367806101751353346', '2021-03-14 12:08:31', '2021-03-14 12:08:31');
INSERT INTO `course_category` VALUES ('1370949938233540609', '1355363330293850114', '1347882076388483074', '2021-03-14 12:08:31', '2021-03-14 12:08:31');
INSERT INTO `course_category` VALUES ('1370949938246123522', '1355363330293850114', '1347882057077907458', '2021-03-14 12:08:31', '2021-03-14 12:08:31');
INSERT INTO `course_category` VALUES ('1370949938246123523', '1355363330293850114', '1347810222948757505', '2021-03-14 12:08:31', '2021-03-14 12:08:31');
INSERT INTO `course_category` VALUES ('1370949938246123524', '1355363330293850114', '00000201', '2021-03-14 12:08:31', '2021-03-14 12:08:31');

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
INSERT INTO `course_content` VALUES ('00000001', '<p></p><blockquote><p>数据结构和算法</p></blockquote><p><br></p><img src=\"http://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/f310bea1-4293-4f14-8078-ae2d6552b45f.png?append&position=0\" class=\"fr-fic fr-dib\"><p></p>', 0, '2021-01-11 13:17:16', '2021-03-14 11:51:13');
INSERT INTO `course_content` VALUES ('000012123', '<p><img src=\"http://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/f310bea1-4293-4f14-8078-ae2d6552b45f.png?append&position=0\" class=\"fr-fic fr-dib\"><br></p><p><br></p><blockquote><p><strong>支付技术</strong></p></blockquote>', 0, NULL, '2021-03-14 11:50:44');
INSERT INTO `course_content` VALUES ('1347880545798942721', '<p><img src=\"http://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/a8c1d47a-4a57-43fb-9f46-613de92cfb6b.jpg?append&position=0\" class=\"fr-fic fr-dib fr-rounded fr-shadow\" alt=\"123\"></p><p><br></p><p><img src=\"https://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/5c3f87b3-3078-4017-9b4d-cb1ac5769304.jpg\" style=\"width: 300px;\" class=\"fr-fic fr-dib\"><span style=\"color: rgb(147, 101, 184); font-family: sans-serif; font-size: 17.5px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: left; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; float: none; display: inline !important;\"></span></p><blockquote><p style=\"text-align: left;\">123123<span style=\"color: rgb(94, 53, 177); font-family: sans-serif; font-size: 17.5px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: left; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;\">1</span></p></blockquote>', 0, '2021-01-11 15:03:17', '2021-01-30 21:19:43');
INSERT INTO `course_content` VALUES ('1348551526196572161', '<p><img src=\"http://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/5752753e-4348-4414-870e-ab85c4d18c28.jpg?append&position=0\" style=\"width: 300px;\" class=\"fr-fic fr-dib\"></p><p><span style=\"background-color: rgb(247, 218, 100); font-family: Georgia, serif; font-size: 48px; color: rgb(26, 188, 156);\">123123</span></p>', 1, '2021-01-26 19:47:50', '2021-01-29 21:45:07');
INSERT INTO `course_content` VALUES ('1355363330293850114', '<blockquote><p>消息中间件</p></blockquote><p><br></p><p><img src=\"https://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/f310bea1-4293-4f14-8078-ae2d6552b45f.png\" class=\"fr-fic fr-dib fr-shadow fr-rounded\"></p><p><br></p><p><br></p>', 0, '2021-03-12 17:00:52', '2021-03-14 11:50:23');
INSERT INTO `course_content` VALUES ('1370289707725414401', '<p></p><blockquote><p>开发单页面应用</p></blockquote><p><br></p><img src=\"https://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/f310bea1-4293-4f14-8078-ae2d6552b45f.png\" class=\"fr-fic fr-dib fr-rounded fr-shadow\"><p></p>', 0, '2021-03-12 16:29:31', '2021-03-14 11:51:44');

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `path` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '相对路径',
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
INSERT INTO `file` VALUES ('1355026840759615489', '2021/01/29/ae3c6623-e534-4ee6-9456-43e3d95c1b0a.jpg', '311075.jpg', 'jpg', 1367158, 'C', 0, 10485760, 1, '355c6be61ff6120baa5bd9f42626bd37', NULL, '2021-01-29 13:35:48', '2021-01-29 13:35:48');
INSERT INTO `file` VALUES ('1355027747811409921', '2021/01/29/f7c08f0e-7a3c-494c-b8e1-f7ebb3b8e1f2.jpg', '317980.jpg', 'jpg', 526843, 'C', 0, 10485760, 1, '65f301d2bad1ead54c1761c7d1f40dae', NULL, '2021-01-29 13:39:24', '2021-01-29 13:39:24');
INSERT INTO `file` VALUES ('1355027781076434945', '2021/01/29/89610db1-f2c0-43a0-838d-9cd4ac90441c.jpg', '325337.jpg', 'jpg', 1313969, 'C', 0, 10485760, 1, '752490c314365d214166011a411c1df0', NULL, '2021-01-29 13:39:32', '2021-01-29 13:39:32');
INSERT INTO `file` VALUES ('1355027812563075073', '2021/01/29/efc5c3c5-791d-49e7-a8b8-ea1543c537bb.jpg', '天使.jpg', 'jpg', 1314107, 'C', 0, 10485760, 1, '437560a1d65a5064d28d5365b8240dae', NULL, '2021-01-29 13:39:40', '2021-01-29 13:39:40');
INSERT INTO `file` VALUES ('1355027871115558914', '2021/01/29/a8c1d47a-4a57-43fb-9f46-613de92cfb6b.jpg', '1000494.jpg', 'jpg', 1360153, 'C', 0, 1360153, 1, 'efaa1644c0a8ee33e589bf680c7f6605', NULL, '2021-01-29 13:39:54', '2021-01-29 13:39:54');
INSERT INTO `file` VALUES ('1355027934541824002', '2021/01/29/5752753e-4348-4414-870e-ab85c4d18c28.jpg', '1000332.jpg', 'jpg', 787934, 'C', 0, 787934, 1, '6b351963ba67968fe51e2862fd2b1eae', NULL, '2021-01-29 13:40:09', '2021-01-29 13:40:09');
INSERT INTO `file` VALUES ('1355036055461031937', '2021/01/29/5c3f87b3-3078-4017-9b4d-cb1ac5769304.jpg', '头像3.jpg', 'jpg', 26968, 'T', 0, 10485760, 1, '58b2635016b3f4cc5a2e61c8beafbc09', NULL, '2021-01-29 14:12:25', '2021-01-29 14:12:25');
INSERT INTO `file` VALUES ('1355036083189575682', '2021/01/29/63d34cfb-e317-4a22-9605-c9d05504d6cf.jpg', '头像2.jpg', 'jpg', 29612, 'T', 0, 10485760, 1, 'ef299ca7fd216f2dfaa6c928e45769ad', NULL, '2021-01-29 14:12:32', '2021-01-29 14:12:32');
INSERT INTO `file` VALUES ('1355150828356239361', '2021/01/29/f310bea1-4293-4f14-8078-ae2d6552b45f.png', '88b3ce4d-15e2-4869-af23-f89093a0852e.png', 'png', 879570, 'C', 0, 879570, 1, 'ec2c8071aa92066837e71dbfdca8e6da', NULL, '2021-01-29 21:48:29', '2021-01-29 21:48:29');
INSERT INTO `file` VALUES ('1355446092027727874', '2021/01/30/0ee25d47-f607-4fad-b119-5897813ffd92.png', 'A8HRH@W7{E97QCYPDM_V0EA.png', 'png', 521954, 'C', 0, 10485760, 1, '6fc428fb167b32cab99a3d386443e650', NULL, '2021-01-30 17:21:45', '2021-01-30 17:21:45');
INSERT INTO `file` VALUES ('1355446171706920962', '2021/01/30/c6ab5d04-3df7-4fc0-8abd-7a50f4c62fa5.png', 'WJK%$BTGCX5[%WOB~@8KDSL.png', 'png', 133416, 'C', 0, 10485760, 1, 'ac50c7bedb81b29c830a44cb39626dbc', NULL, '2021-01-30 17:22:04', '2021-01-30 17:22:04');
INSERT INTO `file` VALUES ('1359685045123571714', NULL, '概论.mp4', 'mp4', 14842958, 'C', NULL, 14842958, NULL, 'a894b0ba812ebecfbb50a96982b6943a', '5853386ddf58440cbeccf569b4225e5a', '2021-02-11 10:05:51', '2021-02-11 10:05:51');
INSERT INTO `file` VALUES ('1359695470061965314', NULL, 'MQ使用心得.mp4', 'mp4', 36434152, 'C', NULL, 36434152, NULL, '4205e1c6d9a1428458f276215f6af13e', 'a604b029d7da4cfbbecd3be189aed805', '2021-02-11 10:47:16', '2021-02-11 10:47:16');
INSERT INTO `file` VALUES ('1359788778566250497', NULL, '123.mp4', 'mp4', 25912470, 'C', NULL, 25912470, NULL, '5645c406feb4c1b9909fa759b4517ea6', '4fb87119591e46a990f0501eb53c123e', '2021-02-11 16:58:03', '2021-02-11 16:58:03');
INSERT INTO `file` VALUES ('1359799555394109442', NULL, '123.mp4', 'mp4', 3251578, 'C', NULL, 3251578, NULL, 'fac787910cab17ab98d36bab931642b5', '455d676d162a40c591db2fad1982e7ba', '2021-02-11 17:40:52', '2021-02-11 17:40:52');
INSERT INTO `file` VALUES ('1367806418941440002', '2021/03/05/93df4540-6690-43b1-8e46-67098ecab6f0.jpg', '头像1.jpg', 'jpg', 21520, 'T', 0, 10485760, 1, '5465bd0aba9cb162c37981ee436cf86c', NULL, '2021-03-05 19:57:17', '2021-03-05 19:57:17');

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
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `resource_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorities` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `autoapprove` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('auth_server', 'course-online', '$2a$10$mx9iAfhiAUc95R17NI1O3.ZpQtFvGDF/p2XDndRXcQLull/TnUrYu', 'app', 'authorization_code,refresh_token,password', 'http://localhost:8250/callback', NULL, 1800, NULL, NULL, 'true');
INSERT INTO `oauth_client_details` VALUES ('yudi_client', 'course-online', '$2a$10$CQ6018J0hhboK7oyRKVfWOJNE2jh08o4fEveHL4EkbZbxb4g3Bxfm', 'app', 'authorization_code,refresh_token,password', 'http://127.0.0.1:9000/api/auth/callback', NULL, 1800, NULL, NULL, 'true');

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `parent` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '父id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称|菜单或按钮',
  `permission_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `page` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '页面|路由',
  `request` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '请求|接口',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '资源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('01', '', '系统管理', NULL, NULL, NULL, '2020-12-03 13:47:32', NULL);
INSERT INTO `resource` VALUES ('1362749946721263617', '01', '业务管理', 'business', NULL, NULL, '2021-02-19 21:04:41', '2021-02-20 13:28:12');
INSERT INTO `resource` VALUES ('1362749981349437441', '01', '权限管理', 'authority', NULL, NULL, '2021-02-19 21:04:49', '2021-02-20 13:27:49');
INSERT INTO `resource` VALUES ('1362750014241169409', '01', '系统管理', 'system', NULL, NULL, '2021-02-19 21:04:57', '2021-02-20 13:38:55');
INSERT INTO `resource` VALUES ('1362750065931771905', '1362749946721263617', '分类管理', 'business.category', '', '', '2021-02-19 21:05:09', '2021-02-21 11:37:49');
INSERT INTO `resource` VALUES ('1362750091839987714', '1362749946721263617', '课程管理', 'business.course', '', '', '2021-02-19 21:05:15', '2021-02-21 11:38:59');
INSERT INTO `resource` VALUES ('1362750120763908097', '1362749946721263617', '教师管理', 'business.teacher', '', '', '2021-02-19 21:05:22', '2021-02-21 11:41:39');
INSERT INTO `resource` VALUES ('1362750172295127041', '1362749981349437441', '用户管理', 'authority.user', '', '', '2021-02-19 21:05:34', '2021-02-21 11:42:36');
INSERT INTO `resource` VALUES ('1362750224442908673', '1362749981349437441', '角色管理', 'authority.role', '', '', '2021-02-19 21:05:47', '2021-02-21 11:43:10');
INSERT INTO `resource` VALUES ('1362750250296598530', '1362749981349437441', '资源管理', 'authority.resource', '', '', '2021-02-19 21:05:53', '2021-02-21 11:43:47');
INSERT INTO `resource` VALUES ('1362750276443889665', '1362750014241169409', '文件管理', 'system.file', '', '', '2021-02-19 21:05:59', '2021-02-21 11:44:29');
INSERT INTO `resource` VALUES ('1362750366843723777', '1362750065931771905', '添加一级分类', 'business.category.addLv1', NULL, '[\"/category/saveOrUpdate\"]', '2021-02-19 21:06:21', '2021-02-20 13:28:47');
INSERT INTO `resource` VALUES ('1362750398930149378', '1362750065931771905', '添加二级分类', 'business.category.addLv2', NULL, '[\"/category/saveOrUpdate\"]', '2021-02-19 21:06:28', '2021-02-20 13:28:51');
INSERT INTO `resource` VALUES ('1362750465141432322', '1362750065931771905', '更新', 'business.category.update', NULL, '[\"/category/{id}\",\"/category/saveOrUpdate\"]', '2021-02-19 21:06:44', '2021-02-20 13:28:55');
INSERT INTO `resource` VALUES ('1362750487107002370', '1362750065931771905', '删除', 'business.category.delete', NULL, '[\"/category/delete/{id}\"]', '2021-02-19 21:06:49', '2021-02-20 13:28:59');
INSERT INTO `resource` VALUES ('1362957415235473410', '1362750091839987714', '添加课程', 'business.course.add', NULL, '[\"/course/saveOrUpdate\",\"/courseCategory/saveOrUpdate\"]', '2021-02-20 10:49:05', '2021-02-20 14:05:19');
INSERT INTO `resource` VALUES ('1362957434961285122', '1362750091839987714', '大章', 'business.chapter', '', '', '2021-02-20 10:49:10', '2021-02-21 11:39:56');
INSERT INTO `resource` VALUES ('1362957459372134401', '1362750091839987714', '内容', 'business.course.content', NULL, '[\"/courseContent/{id}\",\"/courseContent/saveOrUpdate\"]', '2021-02-20 10:49:15', '2021-02-20 15:23:16');
INSERT INTO `resource` VALUES ('1362957567316742145', '1362750091839987714', '更新', 'business.course.update', NULL, '[\"/course/saveOrUpdate\",\"/courseCategory/saveOrUpdate\",\"/course/{id}\",\"/courseCategory/{id}\"]', '2021-02-20 10:49:41', '2021-02-20 15:43:24');
INSERT INTO `resource` VALUES ('1362957591698231297', '1362750091839987714', '删除', 'business.course.delete', NULL, '[\"/course/delete/{id}\"]', '2021-02-20 10:49:47', '2021-02-20 15:24:59');
INSERT INTO `resource` VALUES ('1362957675689168898', '1362957434961285122', '小节', 'business.section', '', '', '2021-02-20 10:50:07', '2021-02-21 11:40:35');
INSERT INTO `resource` VALUES ('1362957700334899201', '1362957434961285122', '添加大章', 'business.chapter.add', NULL, '[\"/chapter/saveOrUpdate\"]', '2021-02-20 10:50:13', '2021-02-20 15:17:51');
INSERT INTO `resource` VALUES ('1362957721331585025', '1362957434961285122', '更新', 'business.chapter.update', NULL, '[\"/chapter/{id}\",\"/chapter/saveOrUpdate\"]', '2021-02-20 10:50:18', '2021-02-20 15:21:17');
INSERT INTO `resource` VALUES ('1362957738033303554', '1362957434961285122', '删除', 'business.chapter.delete', NULL, '[\"/chapter/delete/{id}\"]', '2021-02-20 10:50:22', '2021-02-20 15:18:32');
INSERT INTO `resource` VALUES ('1362957836058382337', '1362957675689168898', '添加小节', 'business.section.add', NULL, '[\"/section/saveOrUpdate\"]', '2021-02-20 10:50:45', '2021-02-20 15:10:43');
INSERT INTO `resource` VALUES ('1362957857948454914', '1362957675689168898', '更新', 'business.section.update', NULL, '[\"/section/{id}\",\"/section/saveOrUpdate\"]', '2021-02-20 10:50:51', '2021-02-20 15:18:57');
INSERT INTO `resource` VALUES ('1362957874570481665', '1362957675689168898', '删除', 'business.section.delete', NULL, '[\"/section/delete/{id}\"]', '2021-02-20 10:50:54', '2021-02-20 15:11:25');
INSERT INTO `resource` VALUES ('1362957953519865857', '1362750120763908097', '添加教师', 'business.teacher.add', NULL, '[\"/teacher/saveOrUpdate\"]', '2021-02-20 10:51:13', '2021-02-20 15:26:24');
INSERT INTO `resource` VALUES ('1362957970926227458', '1362750120763908097', '更新', 'business.teacher.update', NULL, '[\"/teacher/{id}\",\"/teacher/saveOrUpdate\"]', '2021-02-20 10:51:17', '2021-02-20 16:00:25');
INSERT INTO `resource` VALUES ('1362957985539182594', '1362750120763908097', '删除', 'business.teacher.delete', NULL, '[\"/teacher/delete/{id}\"]', '2021-02-20 10:51:21', '2021-02-20 15:26:46');
INSERT INTO `resource` VALUES ('1362958897435398145', '1362750172295127041', '新增用户', 'authority.user.add', NULL, '[\"/user/saveOrUpdate\",\"/roleUser/saveOrUpdate\"]', '2021-02-20 10:54:58', '2021-02-20 15:45:00');
INSERT INTO `resource` VALUES ('1362958920751534081', '1362750172295127041', '更新', 'authority.user.update', NULL, '[\"/user/{id}\",\"/roleUser/{id}\",\"/user/saveOrUpdate\",\"/roleUser/saveOrUpdate\"]', '2021-02-20 10:55:04', '2021-02-20 15:45:54');
INSERT INTO `resource` VALUES ('1362958934580154369', '1362750172295127041', '删除', 'authority.user.delete', NULL, '[\"/user/delete/{id}\"]', '2021-02-20 10:55:07', '2021-02-20 15:46:22');
INSERT INTO `resource` VALUES ('1362958980843327490', '1362750224442908673', '新增角色', 'authority.role.add', NULL, '[\"/role/saveOrUpdate\",\"/roleResource/saveOrUpdate\"]', '2021-02-20 10:55:18', '2021-02-20 15:55:10');
INSERT INTO `resource` VALUES ('1362959006797680642', '1362750224442908673', '更新', 'authority.role.update', NULL, '[\"/role/{id}\",\"/roleResource/{id}\",\"/role/saveOrUpdate\",\"/roleResource/saveOrUpdate\"]', '2021-02-20 10:55:24', '2021-02-20 15:56:45');
INSERT INTO `resource` VALUES ('1362959024652832769', '1362750224442908673', '删除', 'authority.role.delete', NULL, '[\"/role/delete/{id}\"]', '2021-02-20 10:55:29', '2021-02-20 15:57:03');
INSERT INTO `resource` VALUES ('1362959097696636929', '1362750250296598530', '新增资源', 'authority.resource.add', NULL, '[\"/resource/saveOrUpdate\"]', '2021-02-20 10:55:46', '2021-02-20 16:01:01');
INSERT INTO `resource` VALUES ('1362959118630408194', '1362750250296598530', '更新', 'authority.resource.update', NULL, '[\"/resource/{id}\",\"/resource/saveOrUpdate\"]', '2021-02-20 10:55:51', '2021-02-20 16:02:10');
INSERT INTO `resource` VALUES ('1362959135470538754', '1362750250296598530', '删除', 'authority.resource.delete', NULL, '[\"/resource/delete/{id}\"]', '2021-02-20 10:55:55', '2021-02-20 16:02:25');
INSERT INTO `resource` VALUES ('1362959174167187457', '1362750250296598530', '添加子资源', 'authority.resource.children', NULL, '[\"/resource/saveOrUpdate\"]', '2021-02-20 10:56:04', '2021-02-20 16:01:11');
INSERT INTO `resource` VALUES ('1362961763042611201', '1362750172295127041', '重置密码', 'authority.user.reset', NULL, '[\"/user/reset/{id}\"]', '2021-02-20 11:06:22', '2021-02-20 15:53:44');
INSERT INTO `resource` VALUES ('1363331893726068737', '1362750065931771905', '访问', 'business.category.view', 'business/category', '[\"/category/list\"]', '2021-02-21 11:37:08', '2021-02-21 11:37:43');
INSERT INTO `resource` VALUES ('1363332170311057410', '1362750091839987714', '访问', 'business.course.view', 'business/course', '[\"/course/list\",\"/category/list\",\"/teacher/all\"]', '2021-02-21 11:38:14', '2021-02-21 11:38:52');
INSERT INTO `resource` VALUES ('1363332392458174466', '1362957434961285122', '访问', 'business.chapter.view', 'business/chapter', '[\"/chapter/list\"]', '2021-02-21 11:39:07', '2021-02-21 11:39:47');
INSERT INTO `resource` VALUES ('1363332648281358337', '1362957675689168898', '访问', 'business.section.view', 'business/section', '[\"/section/list\"]', '2021-02-21 11:40:08', '2021-02-21 11:40:29');
INSERT INTO `resource` VALUES ('1363332890934427649', '1362750120763908097', '访问', 'business.teacher.view', 'business/teacher', '[\"/teacher/list\"]', '2021-02-21 11:41:05', '2021-02-21 11:41:34');
INSERT INTO `resource` VALUES ('1363333103224930305', '1362750172295127041', '访问', 'authority.user.view', 'authority/user', '[\"/user/list\",\"/role/all\"]', '2021-02-21 11:41:56', '2021-02-21 11:42:30');
INSERT INTO `resource` VALUES ('1363333120849395714', '1362750224442908673', '访问', 'authority.role.view', 'authority/role', '[\"/role/list\",\"/resource/list\"]', '2021-02-21 11:42:00', '2021-02-21 11:43:05');
INSERT INTO `resource` VALUES ('1363333141242101762', '1362750250296598530', '访问', 'authority.resource.view', 'authority/resource', '[\"/resource/list\"]', '2021-02-21 11:42:05', '2021-02-21 11:43:40');
INSERT INTO `resource` VALUES ('1363333614535753730', '1362750276443889665', '访问', 'system.file.view', 'system/file', '[\"/file/list\"]', '2021-02-21 11:43:58', '2021-02-21 11:44:24');
INSERT INTO `resource` VALUES ('1367010450453159937', '01', '欢迎页面', 'welcome', 'welcome', NULL, '2021-03-03 15:14:24', '2021-03-08 20:52:14');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色',
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('00000001', '开发管理员', '管理所有', '2020-12-03 13:48:13', '2020-12-03 13:48:13');
INSERT INTO `role` VALUES ('00000002', '系统管理员', '负责管理系统基本事务', '2020-12-03 13:48:13', '2020-12-03 13:48:13');
INSERT INTO `role` VALUES ('00000003', '讲师课程管理员', '编辑课程和讲师', '2020-12-03 13:48:13', '2021-02-14 11:45:26');
INSERT INTO `role` VALUES ('1368425051375910914', '权限管理员', '管理用户的权限', '2021-03-07 12:55:31', '2021-03-07 12:55:31');

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
INSERT INTO `role_resource` VALUES ('1367036400528052226', '00000003', '1362750091839987714', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036400528052227', '00000003', '1362957415235473410', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036400528052228', '00000003', '1362957434961285122', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036400540635138', '00000003', '1362957675689168898', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036400540635139', '00000003', '1362957836058382337', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036400540635140', '00000003', '1362957857948454914', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036400540635141', '00000003', '1362957874570481665', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036400540635142', '00000003', '1363332648281358337', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036400549023746', '00000003', '1362957700334899201', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036400549023747', '00000003', '1362957721331585025', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036400549023748', '00000003', '1362957738033303554', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036400549023749', '00000003', '1363332392458174466', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036400549023750', '00000003', '1362957459372134401', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036400549023751', '00000003', '1362957567316742145', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036400561606657', '00000003', '1362957591698231297', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036400561606658', '00000003', '1363332170311057410', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036400569995266', '00000003', '1362750120763908097', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036400569995267', '00000003', '1362957953519865857', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036400569995268', '00000003', '1362957970926227458', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036400569995269', '00000003', '1362957985539182594', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036400578383873', '00000003', '1363332890934427649', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036400578383874', '00000003', '1367010450453159937', '2021-03-03 16:57:30', '2021-03-03 16:57:30');
INSERT INTO `role_resource` VALUES ('1367036421864476674', '00000002', '1362749946721263617', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421864476675', '00000002', '1362750065931771905', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421872865282', '00000002', '1362750366843723777', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421872865283', '00000002', '1362750398930149378', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421872865284', '00000002', '1362750465141432322', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421877059585', '00000002', '1362750487107002370', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421877059586', '00000002', '1363331893726068737', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421877059587', '00000002', '1362750091839987714', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421877059588', '00000002', '1362957415235473410', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421877059589', '00000002', '1362957434961285122', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421885448194', '00000002', '1362957675689168898', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421885448195', '00000002', '1362957836058382337', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421885448196', '00000002', '1362957857948454914', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421885448197', '00000002', '1362957874570481665', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421885448198', '00000002', '1363332648281358337', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421885448199', '00000002', '1362957700334899201', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421885448200', '00000002', '1362957721331585025', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421885448201', '00000002', '1362957738033303554', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421885448202', '00000002', '1363332392458174466', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421885448203', '00000002', '1362957459372134401', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421885448204', '00000002', '1362957567316742145', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421893836801', '00000002', '1362957591698231297', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421893836802', '00000002', '1363332170311057410', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421893836803', '00000002', '1362750120763908097', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421893836804', '00000002', '1362957953519865857', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421898031105', '00000002', '1362957970926227458', '2021-03-03 16:57:35', '2021-03-03 16:57:35');
INSERT INTO `role_resource` VALUES ('1367036421898031106', '00000002', '1362957985539182594', '2021-03-03 16:57:36', '2021-03-03 16:57:36');
INSERT INTO `role_resource` VALUES ('1367036421898031107', '00000002', '1363332890934427649', '2021-03-03 16:57:36', '2021-03-03 16:57:36');
INSERT INTO `role_resource` VALUES ('1367036421898031108', '00000002', '1362750172295127041', '2021-03-03 16:57:36', '2021-03-03 16:57:36');
INSERT INTO `role_resource` VALUES ('1367036421898031109', '00000002', '1362958897435398145', '2021-03-03 16:57:36', '2021-03-03 16:57:36');
INSERT INTO `role_resource` VALUES ('1367036421898031110', '00000002', '1362958920751534081', '2021-03-03 16:57:36', '2021-03-03 16:57:36');
INSERT INTO `role_resource` VALUES ('1367036421898031111', '00000002', '1362958934580154369', '2021-03-03 16:57:36', '2021-03-03 16:57:36');
INSERT INTO `role_resource` VALUES ('1367036421898031112', '00000002', '1362961763042611201', '2021-03-03 16:57:36', '2021-03-03 16:57:36');
INSERT INTO `role_resource` VALUES ('1367036421898031113', '00000002', '1363333103224930305', '2021-03-03 16:57:36', '2021-03-03 16:57:36');
INSERT INTO `role_resource` VALUES ('1367036421906419714', '00000002', '1362750224442908673', '2021-03-03 16:57:36', '2021-03-03 16:57:36');
INSERT INTO `role_resource` VALUES ('1367036421906419715', '00000002', '1362958980843327490', '2021-03-03 16:57:36', '2021-03-03 16:57:36');
INSERT INTO `role_resource` VALUES ('1367036421906419716', '00000002', '1362959006797680642', '2021-03-03 16:57:36', '2021-03-03 16:57:36');
INSERT INTO `role_resource` VALUES ('1367036421906419717', '00000002', '1362959024652832769', '2021-03-03 16:57:36', '2021-03-03 16:57:36');
INSERT INTO `role_resource` VALUES ('1367036421906419718', '00000002', '1363333120849395714', '2021-03-03 16:57:36', '2021-03-03 16:57:36');
INSERT INTO `role_resource` VALUES ('1367036421906419719', '00000002', '1363333141242101762', '2021-03-03 16:57:36', '2021-03-03 16:57:36');
INSERT INTO `role_resource` VALUES ('1367036421906419720', '00000002', '1362750014241169409', '2021-03-03 16:57:36', '2021-03-03 16:57:36');
INSERT INTO `role_resource` VALUES ('1367036421906419721', '00000002', '1362750276443889665', '2021-03-03 16:57:36', '2021-03-03 16:57:36');
INSERT INTO `role_resource` VALUES ('1367036421906419722', '00000002', '1363333614535753730', '2021-03-03 16:57:36', '2021-03-03 16:57:36');
INSERT INTO `role_resource` VALUES ('1367036421914808321', '00000002', '1367010450453159937', '2021-03-03 16:57:36', '2021-03-03 16:57:36');
INSERT INTO `role_resource` VALUES ('1367036433298149378', '00000001', '01', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433298149379', '00000001', '1362749946721263617', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433298149380', '00000001', '1362750065931771905', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433298149381', '00000001', '1362750366843723777', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433298149382', '00000001', '1362750398930149378', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433298149383', '00000001', '1362750465141432322', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433298149384', '00000001', '1362750487107002370', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433298149385', '00000001', '1363331893726068737', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433306537985', '00000001', '1362750091839987714', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433310732289', '00000001', '1362957415235473410', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433310732290', '00000001', '1362957434961285122', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433310732291', '00000001', '1362957675689168898', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433310732292', '00000001', '1362957836058382337', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433310732293', '00000001', '1362957857948454914', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433310732294', '00000001', '1362957874570481665', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433310732295', '00000001', '1363332648281358337', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433310732296', '00000001', '1362957700334899201', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433310732297', '00000001', '1362957721331585025', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433310732298', '00000001', '1362957738033303554', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433319120898', '00000001', '1363332392458174466', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433319120899', '00000001', '1362957459372134401', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433319120900', '00000001', '1362957567316742145', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433319120901', '00000001', '1362957591698231297', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433319120902', '00000001', '1363332170311057410', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433319120903', '00000001', '1362750120763908097', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433319120904', '00000001', '1362957953519865857', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433319120905', '00000001', '1362957970926227458', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433319120906', '00000001', '1362957985539182594', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433319120907', '00000001', '1363332890934427649', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433327509505', '00000001', '1362749981349437441', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433327509506', '00000001', '1362750172295127041', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433327509507', '00000001', '1362958897435398145', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433327509508', '00000001', '1362958920751534081', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433327509509', '00000001', '1362958934580154369', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433327509510', '00000001', '1362961763042611201', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433327509511', '00000001', '1363333103224930305', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433327509512', '00000001', '1362750224442908673', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433327509513', '00000001', '1362958980843327490', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433327509514', '00000001', '1362959006797680642', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433335898113', '00000001', '1362959024652832769', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433335898114', '00000001', '1363333120849395714', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433335898115', '00000001', '1362750250296598530', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433335898116', '00000001', '1362959097696636929', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433335898117', '00000001', '1362959118630408194', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433335898118', '00000001', '1362959135470538754', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433335898119', '00000001', '1362959174167187457', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433335898120', '00000001', '1363333141242101762', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433335898121', '00000001', '1362750014241169409', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433335898122', '00000001', '1362750276443889665', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433335898123', '00000001', '1363333614535753730', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1367036433335898124', '00000001', '1367010450453159937', '2021-03-03 16:57:38', '2021-03-03 16:57:38');
INSERT INTO `role_resource` VALUES ('1368425051665317889', '1368425051375910914', '1362749981349437441', '2021-03-07 12:55:31', '2021-03-07 12:55:31');
INSERT INTO `role_resource` VALUES ('1368425051665317890', '1368425051375910914', '1362750172295127041', '2021-03-07 12:55:31', '2021-03-07 12:55:31');
INSERT INTO `role_resource` VALUES ('1368425051665317891', '1368425051375910914', '1362958897435398145', '2021-03-07 12:55:31', '2021-03-07 12:55:31');
INSERT INTO `role_resource` VALUES ('1368425051665317892', '1368425051375910914', '1362958920751534081', '2021-03-07 12:55:31', '2021-03-07 12:55:31');
INSERT INTO `role_resource` VALUES ('1368425051673706497', '1368425051375910914', '1362958934580154369', '2021-03-07 12:55:31', '2021-03-07 12:55:31');
INSERT INTO `role_resource` VALUES ('1368425051673706498', '1368425051375910914', '1362961763042611201', '2021-03-07 12:55:31', '2021-03-07 12:55:31');
INSERT INTO `role_resource` VALUES ('1368425051673706499', '1368425051375910914', '1363333103224930305', '2021-03-07 12:55:31', '2021-03-07 12:55:31');
INSERT INTO `role_resource` VALUES ('1368425051673706500', '1368425051375910914', '1362750224442908673', '2021-03-07 12:55:31', '2021-03-07 12:55:31');
INSERT INTO `role_resource` VALUES ('1368425051673706501', '1368425051375910914', '1362958980843327490', '2021-03-07 12:55:31', '2021-03-07 12:55:31');
INSERT INTO `role_resource` VALUES ('1368425051673706502', '1368425051375910914', '1362959006797680642', '2021-03-07 12:55:31', '2021-03-07 12:55:31');
INSERT INTO `role_resource` VALUES ('1368425051673706503', '1368425051375910914', '1362959024652832769', '2021-03-07 12:55:31', '2021-03-07 12:55:31');
INSERT INTO `role_resource` VALUES ('1368425051673706504', '1368425051375910914', '1363333120849395714', '2021-03-07 12:55:31', '2021-03-07 12:55:31');
INSERT INTO `role_resource` VALUES ('1368425051682095106', '1368425051375910914', '1362750250296598530', '2021-03-07 12:55:31', '2021-03-07 12:55:31');
INSERT INTO `role_resource` VALUES ('1368425051682095107', '1368425051375910914', '1362959097696636929', '2021-03-07 12:55:31', '2021-03-07 12:55:31');
INSERT INTO `role_resource` VALUES ('1368425051682095108', '1368425051375910914', '1362959118630408194', '2021-03-07 12:55:31', '2021-03-07 12:55:31');
INSERT INTO `role_resource` VALUES ('1368425051682095109', '1368425051375910914', '1362959135470538754', '2021-03-07 12:55:31', '2021-03-07 12:55:31');
INSERT INTO `role_resource` VALUES ('1368425051682095110', '1368425051375910914', '1362959174167187457', '2021-03-07 12:55:31', '2021-03-07 12:55:31');
INSERT INTO `role_resource` VALUES ('1368425051682095111', '1368425051375910914', '1363333141242101762', '2021-03-07 12:55:31', '2021-03-07 12:55:31');
INSERT INTO `role_resource` VALUES ('1368425051682095112', '1368425051375910914', '1367010450453159937', '2021-03-07 12:55:31', '2021-03-07 12:55:31');

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
INSERT INTO `role_user` VALUES ('1363004811478917121', '00000003', '1363004811176927233', '2021-02-20 13:57:25', '2021-02-20 13:57:25');
INSERT INTO `role_user` VALUES ('1363004832874061825', '00000003', '1363004165950382081', '2021-02-20 13:57:30', '2021-02-20 13:57:30');
INSERT INTO `role_user` VALUES ('1363004918010044418', '00000003', '1363004917900992514', '2021-02-20 13:57:50', '2021-02-20 13:57:50');
INSERT INTO `role_user` VALUES ('1368424006943883266', '00000002', '1363003973029175298', '2021-03-07 12:51:22', '2021-03-07 12:51:22');
INSERT INTO `role_user` VALUES ('1368424469273624577', '00000003', '1368424469097463810', '2021-03-07 12:53:12', '2021-03-07 12:53:12');
INSERT INTO `role_user` VALUES ('1368424559841230850', '00000003', '1368424559719596033', '2021-03-07 12:53:33', '2021-03-07 12:53:33');
INSERT INTO `role_user` VALUES ('1368429062141362178', '00000001', '1363001437412970498', '2021-03-07 13:11:27', '2021-03-07 13:11:27');

-- ----------------------------
-- Table structure for section
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标题',
  `course_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程|course.id',
  `chapter_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '大章|chapter.id',
  `video` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '视频',
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
INSERT INTO `section` VALUES ('1348114448295952385', '概论', '1347880545798942721', '1348114393761611777', '', 990, 'F', 0, '2021-01-10 11:48:26', '2021-02-11 17:02:56', 'a604b029d7da4cfbbecd3be189aed805');
INSERT INTO `section` VALUES ('1348115629302906881', '支付宝api与sdk', '000012123', '1348100676453928961', '', 990, 'C', 1, '2021-01-10 11:53:07', '2021-03-14 11:37:20', 'a604b029d7da4cfbbecd3be189aed805');
INSERT INTO `section` VALUES ('1348519809431654401', '概述', '00000001', '1348519740418576386', '', 990, 'F', 0, '2021-01-11 14:39:12', '2021-03-12 16:44:29', 'a604b029d7da4cfbbecd3be189aed805');
INSERT INTO `section` VALUES ('1348519862774812673', '数组', '00000001', '1348519767064989697', '', 123, 'F', NULL, '2021-01-11 14:39:24', '2021-01-11 14:39:24', '');
INSERT INTO `section` VALUES ('1355363707567300610', '概论', '1355363330293850114', '1355363441228996610', '', 990, 'F', 0, '2021-01-30 11:54:24', '2021-03-14 11:38:51', 'a604b029d7da4cfbbecd3be189aed805');
INSERT INTO `section` VALUES ('1355363891990847489', '各大开源消息队列介绍', '1355363330293850114', '1355363441228996610', '', 566, 'F', 1, '2021-01-30 11:55:08', '2021-03-14 18:06:20', '5853386ddf58440cbeccf569b4225e5a');
INSERT INTO `section` VALUES ('1355479493107535874', 'http协议', '1347880545798942721', '1355479248218902530', '', 737, 'C', 3, '2021-01-30 19:34:29', '2021-01-31 10:57:14', '');
INSERT INTO `section` VALUES ('1355796199969394689', 'MQ使用场景', '1355363330293850114', '1355363441228996610', '', 990, 'F', 2, '2021-01-31 16:32:58', '2021-03-14 11:39:37', 'a604b029d7da4cfbbecd3be189aed805');
INSERT INTO `section` VALUES ('1359348333478432770', 'rabbitmq介绍', '1355363330293850114', '1359347981123342338', '', 566, 'F', 0, '2021-02-10 11:47:53', '2021-02-11 10:23:40', '5853386ddf58440cbeccf569b4225e5a');
INSERT INTO `section` VALUES ('1359355427795513345', 'rabbitmq安装与配置', '1355363330293850114', '1359347981123342338', '', 566, 'F', 1, '2021-02-10 12:16:04', '2021-03-14 11:39:14', '5853386ddf58440cbeccf569b4225e5a');

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
INSERT INTO `teacher` VALUES ('1348536842768740353', '张窟窿', 'cap', 'https://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/63d34cfb-e317-4a22-9605-c9d05504d6cf.jpg', '前百度架构师', '哇哦', '中国科学院计算机技术研究所博士', 0, '2021-01-11 15:46:52', '2021-01-11 15:46:52');
INSERT INTO `teacher` VALUES ('1348547421621317633', '黄哈哈', '神思者', 'https://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/5c3f87b3-3078-4017-9b4d-cb1ac5769304.jpg', '前美团架构师', '向前冲', '丰富的互联网项目经验，公司内部技术讲师，热爱技术，乐于分享；教学格言：把复杂的技术简单化，简单的技术极致化', 0, '2021-01-11 16:28:54', '2021-01-11 16:28:54');
INSERT INTO `teacher` VALUES ('1348627061677342722', '关过江', 'lewis', 'https://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/03/05/93df4540-6690-43b1-8e46-67098ecab6f0.jpg', 'Java高级工程师', '123', '中国科学院计算机技术研究所博士', 0, '2021-01-11 21:45:22', '2021-01-11 21:45:22');
INSERT INTO `teacher` VALUES ('1348627420260974593', '赵撞柱', 'javaman', 'https://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/5c3f87b3-3078-4017-9b4d-cb1ac5769304.jpg', '资深全栈工程师', '啊啊啊啊啊啊啊啊', '中国科学院计算机技术研究所博士，拥有7年国内外IT公司工作和算法面试经验，在搜索、话题发现、社交网络方面、推荐算法等相关领域有所研究。曾为浙江大学、浙江省大学生程序设计竞赛的命题人，也是PAT 的命题人，国内ZOJ排名第一。', 0, '2021-01-11 21:46:47', '2021-01-11 21:46:47');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登陆名',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `login_name_unique`(`login_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1363001437412970498', 'developer', '开发管理员', '$2a$10$I4jfG12izVfZtsk4oYR8jemMyU/GTZazZGMgAXGlnL6ZPMn6m/qga', '2021-02-20 13:44:00', '2021-02-20 13:44:00');
INSERT INTO `user` VALUES ('1363003973029175298', 'system', '系统管理员', '$2a$10$8Myx.OrW9Tl4H070TMo0S.UJy5GcT9M5IXy5JbDH4ay0aT/aZMMHu', '2021-02-20 13:54:05', '2021-02-20 13:54:05');
INSERT INTO `user` VALUES ('1363004165950382081', 'liu', '刘知兵', '$2a$10$8Myx.OrW9Tl4H070TMo0S.UJy5GcT9M5IXy5JbDH4ay0aT/aZMMHu', '2021-02-20 13:54:51', '2021-02-20 13:54:51');
INSERT INTO `user` VALUES ('1363004811176927233', 'zhang', '张窟窿', '$2a$10$yz3728gSwMzjxqoIuqZyVeTrPJar2AogIVmramF0s1S/n17hv2Hr6', '2021-02-20 13:57:25', '2021-02-20 13:57:25');
INSERT INTO `user` VALUES ('1363004917900992514', 'zhao', '赵撞柱', '$2a$10$2VSRfbzESUa/YuMy55cEF.xYa3AWFFZ/t5xGS1GX3N0y3ILfkwYTa', '2021-02-20 13:57:50', '2021-02-20 13:57:50');
INSERT INTO `user` VALUES ('1368424469097463810', 'guan', '关过江', '$2a$10$xLoiW88qYKQ4VEnxY5ILT.t8XFKo4Vtx1B56UtMj7muAsUfLk7oIa', '2021-03-07 12:53:12', '2021-03-07 12:53:12');
INSERT INTO `user` VALUES ('1368424559719596033', 'huang', '黄哈哈', '$2a$10$k8J7ibOjtJsZsV/bgoFwD.9OM6FzzuPtR42mPlJjmUiyJtHm6Vqvq', '2021-03-07 12:53:33', '2021-03-07 12:53:33');

SET FOREIGN_KEY_CHECKS = 1;
