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

 Date: 19/02/2021 20:56:49
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
INSERT INTO `category` VALUES ('00000505', '00000500', '阿里云', 505, NULL, NULL);
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
INSERT INTO `category` VALUES ('1348460835772583938', '00000200', 'C', NULL, '2021-01-11 10:44:51', '2021-01-11 10:44:51');
INSERT INTO `category` VALUES ('1348460862230253569', '00000200', 'C++', NULL, '2021-01-11 10:44:57', '2021-01-11 10:45:04');
INSERT INTO `category` VALUES ('1348460912087945218', '00000200', 'C#', NULL, '2021-01-11 10:45:09', '2021-01-11 10:45:09');
INSERT INTO `category` VALUES ('1348525026122420225', '00000500', '容器', NULL, '2021-01-11 14:59:55', '2021-01-11 14:59:55');
INSERT INTO `category` VALUES ('1362210423173672962', '00000100', 'HTML/CSS', NULL, '2021-02-18 09:20:48', '2021-02-18 09:20:48');

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `course_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `sort` int(11) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '顺序',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `is_delete` int(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '大章' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chapter
-- ----------------------------
INSERT INTO `chapter` VALUES ('00000001', '11111111', '测试大章01', NULL, NULL, '2020-12-14 21:39:18', 1);
INSERT INTO `chapter` VALUES ('00000002', '123123213', '测试大章02', NULL, NULL, '2020-12-14 21:33:47', 1);
INSERT INTO `chapter` VALUES ('00000003', '00000000', '测试大章03', NULL, NULL, NULL, 1);
INSERT INTO `chapter` VALUES ('00000004', '1343041788956405761', '测试大章04', NULL, NULL, '2020-12-20 11:13:05', 0);
INSERT INTO `chapter` VALUES ('00000005', '1343041788956405761', '测试大章05', NULL, NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000006', '1343041788956405761', '测试大章06', NULL, NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000007', '1343041788956405761', '测试大章07', NULL, NULL, NULL, 1);
INSERT INTO `chapter` VALUES ('00000008', '1343041788956405761', '测试大章08', NULL, NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000009', '1343041788956405761', '测试大章09', NULL, NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000010', '1343041788956405761', '测试大章10', NULL, NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000011', '1343041788956405761', '测试大章11', NULL, NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000012', '123213132213', '测试大章12', NULL, NULL, '2020-12-14 21:39:25', 0);
INSERT INTO `chapter` VALUES ('00000013', '1343094701405794305', '测试大章13', NULL, NULL, '2020-12-15 15:36:26', 0);
INSERT INTO `chapter` VALUES ('00000014', '1343094701405794305', '测试大章14', NULL, NULL, NULL, 1);
INSERT INTO `chapter` VALUES ('1338468722569986049', '00000001', '1111111', NULL, '2020-12-14 20:59:46', '2020-12-14 21:00:39', 1);
INSERT INTO `chapter` VALUES ('1338477928027037697', '00000001', 'aaaaa', NULL, '2020-12-14 21:36:20', '2020-12-14 21:36:20', 1);
INSERT INTO `chapter` VALUES ('1338478637887823874', '00000001', 'qqweqwe', NULL, '2020-12-14 21:39:10', '2020-12-14 21:39:10', 1);
INSERT INTO `chapter` VALUES ('1338661445444935682', '00000001', 'ceshi123', NULL, '2020-12-15 09:45:34', '2020-12-15 09:45:34', 1);
INSERT INTO `chapter` VALUES ('1338687023719960577', '00000001', '123', NULL, '2020-12-15 11:27:13', '2020-12-15 11:27:13', 1);
INSERT INTO `chapter` VALUES ('1338748459397775361', '00000001', '123123', NULL, '2020-12-15 15:31:20', '2020-12-15 15:31:20', 1);
INSERT INTO `chapter` VALUES ('1338748531288145922', '00000001', '123', NULL, '2020-12-15 15:31:37', '2020-12-15 15:31:37', 1);
INSERT INTO `chapter` VALUES ('1340505698831720450', '1343040305078759425', '123', NULL, '2020-12-20 11:53:59', '2020-12-20 11:53:59', 1);
INSERT INTO `chapter` VALUES ('1343094701405794305', '1343040305078759425', '123123', NULL, '2020-12-27 15:21:45', '2020-12-27 15:21:45', 1);
INSERT INTO `chapter` VALUES ('1343100259013857281', '00000001', '123', NULL, '2020-12-27 15:43:50', '2020-12-27 15:43:50', 1);
INSERT INTO `chapter` VALUES ('1343100290047512578', '1343040305078759425', '123aaaa', NULL, '2020-12-27 15:43:57', '2020-12-27 15:44:15', 1);
INSERT INTO `chapter` VALUES ('1348100676453928961', '000012123', '第三方支付——支付宝', 00000000000, '2021-01-10 10:53:42', '2021-01-25 20:57:35', 0);
INSERT INTO `chapter` VALUES ('1348114393761611777', '1347880545798942721', '概论', 00000000000, '2021-01-10 11:48:13', '2021-01-17 16:33:22', 0);
INSERT INTO `chapter` VALUES ('1348461791998398466', '000012123', '第三方支付——接微信支付', 00000000001, '2021-01-11 10:48:39', '2021-01-25 20:57:44', 0);
INSERT INTO `chapter` VALUES ('1348518999402500097', '1347880545798942721', '物理层', 00000000001, '2021-01-11 14:35:58', '2021-01-17 16:33:27', 0);
INSERT INTO `chapter` VALUES ('1348519035309936641', '1347880545798942721', '数据链路层', 00000000002, '2021-01-11 14:36:07', '2021-01-17 16:33:17', 0);
INSERT INTO `chapter` VALUES ('1348519740418576386', '00000001', '导论', NULL, '2021-01-11 14:38:55', '2021-01-11 14:38:55', 0);
INSERT INTO `chapter` VALUES ('1348519767064989697', '00000001', '线性数据结构', NULL, '2021-01-11 14:39:01', '2021-01-11 14:39:01', 0);
INSERT INTO `chapter` VALUES ('1348519929569103874', '1343040305078759425', '导论', NULL, '2021-01-11 14:39:40', '2021-01-11 14:39:40', 1);
INSERT INTO `chapter` VALUES ('1350721096449564674', '1348551526196572161', '序列', 00000000000, '2021-01-17 16:26:19', '2021-01-17 16:35:00', 1);
INSERT INTO `chapter` VALUES ('1355363441228996610', '1355363330293850114', '为什么需要消息队列', 00000000000, '2021-01-30 11:53:20', '2021-01-30 11:53:20', 0);
INSERT INTO `chapter` VALUES ('1355479248218902530', '1347880545798942721', '应用层', 00000000003, '2021-01-30 19:33:31', '2021-01-30 19:33:31', 0);
INSERT INTO `chapter` VALUES ('1359347981123342338', '1355363330293850114', 'RabbitMQ介绍', 00000000001, '2021-02-10 11:46:29', '2021-02-10 11:46:29', 0);

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
INSERT INTO `course` VALUES ('00000001', '数据结构和算法', '这是一门测试课程', 246, 19.90, 'https://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/efc5c3c5-791d-49e7-a8b8-ea1543c537bb.jpg', '2', 'C', 'P', 100, 0, 0, '2020-11-29 13:33:54', '2021-01-29 13:39:42', '123');
INSERT INTO `course` VALUES ('000012123', 'Java支付项目实战', '这是一门测试课程', 1132, 19.90, 'https://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/a8c1d47a-4a57-43fb-9f46-613de92cfb6b.jpg', '1', 'C', 'P', 100, 0, 0, '2020-11-29 13:33:54', '2021-01-30 11:51:45', '1348547421621317633');
INSERT INTO `course` VALUES ('1347880545798942721', '计算机网络精讲', '计算机网络知识梳理', 1727, 123.00, 'https://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/f310bea1-4293-4f14-8078-ae2d6552b45f.png', '1', 'F', 'D', 123, NULL, 0, '2021-01-09 20:18:59', '2021-01-30 17:22:16', '1348627061677342722');
INSERT INTO `course` VALUES ('1348551526196572161', '操作系统精讲', '1231', 123, 123.00, 'https://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/ae3c6623-e534-4ee6-9456-43e3d95c1b0a.jpg', '1', 'F', 'D', 0, NULL, 1, '2021-01-11 16:45:13', '2021-01-30 11:51:25', '1348627420260974593');
INSERT INTO `course` VALUES ('1355363330293850114', '消息中间件精讲', '消息中间件精讲', 3678, 199.00, 'https://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/ae3c6623-e534-4ee6-9456-43e3d95c1b0a.jpg', '1', 'F', 'D', 0, NULL, 0, '2021-01-30 11:52:54', '2021-02-11 17:08:36', '1348627420260974593');

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
INSERT INTO `course_category` VALUES ('1355027819735363586', '00000001', '1348104438253879298', '2021-01-29 13:39:42', '2021-01-29 13:39:42');
INSERT INTO `course_category` VALUES ('1355027819743752194', '00000001', '1348104422453936129', '2021-01-29 13:39:42', '2021-01-29 13:39:42');
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
INSERT INTO `course_category` VALUES ('1355363039825715206', '000012123', '00000505', '2021-01-30 11:51:45', '2021-01-30 11:51:45');
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
INSERT INTO `course_category` VALUES ('1359791431732015106', '1355363330293850114', '1347795498152505346', '2021-02-11 17:08:36', '2021-02-11 17:08:36');
INSERT INTO `course_category` VALUES ('1359791431740403713', '1355363330293850114', '1347882076388483074', '2021-02-11 17:08:36', '2021-02-11 17:08:36');
INSERT INTO `course_category` VALUES ('1359791431740403714', '1355363330293850114', '1347882057077907458', '2021-02-11 17:08:36', '2021-02-11 17:08:36');
INSERT INTO `course_category` VALUES ('1359791431740403715', '1355363330293850114', '1347810222948757505', '2021-02-11 17:08:36', '2021-02-11 17:08:36');
INSERT INTO `course_category` VALUES ('1359791431740403716', '1355363330293850114', '00000201', '2021-02-11 17:08:36', '2021-02-11 17:08:36');

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
INSERT INTO `course_content` VALUES ('00000001', '', 0, '2021-01-11 13:17:16', '2021-01-29 21:49:27');
INSERT INTO `course_content` VALUES ('000012123', '<p><img src=\"http://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/f310bea1-4293-4f14-8078-ae2d6552b45f.png?append&position=0\" class=\"fr-fic fr-dib\"><br></p><p><br></p><blockquote><p><strong>123123123</strong></p></blockquote>', 0, NULL, '2021-01-29 21:48:42');
INSERT INTO `course_content` VALUES ('1347880545798942721', '<p><img src=\"http://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/a8c1d47a-4a57-43fb-9f46-613de92cfb6b.jpg?append&position=0\" class=\"fr-fic fr-dib fr-rounded fr-shadow\" alt=\"123\"></p><p><br></p><p><img src=\"https://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/5c3f87b3-3078-4017-9b4d-cb1ac5769304.jpg\" style=\"width: 300px;\" class=\"fr-fic fr-dib\"><span style=\"color: rgb(147, 101, 184); font-family: sans-serif; font-size: 17.5px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: left; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; float: none; display: inline !important;\"></span></p><blockquote><p style=\"text-align: left;\">123123<span style=\"color: rgb(94, 53, 177); font-family: sans-serif; font-size: 17.5px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: left; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: rgb(255, 255, 255); text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;\">1</span></p></blockquote>', 0, '2021-01-11 15:03:17', '2021-01-30 21:19:43');
INSERT INTO `course_content` VALUES ('1348551526196572161', '<p><img src=\"http://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/5752753e-4348-4414-870e-ab85c4d18c28.jpg?append&position=0\" style=\"width: 300px;\" class=\"fr-fic fr-dib\"></p><p><span style=\"background-color: rgb(247, 218, 100); font-family: Georgia, serif; font-size: 48px; color: rgb(26, 188, 156);\">123123</span></p>', 1, '2021-01-26 19:47:50', '2021-01-29 21:45:07');

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
INSERT INTO `oauth_client_details` VALUES ('client', 'client', '$2a$10$mx9iAfhiAUc95R17NI1O3.ZpQtFvGDF/p2XDndRXcQLull/TnUrYu', 'app', 'authorization_code,refresh_token,password', 'http://localhost:9000/callback', NULL, 1800, NULL, NULL, 'true');

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
INSERT INTO `resource` VALUES ('0101', '01', '用户管理', 'user', '/system/user', NULL, '2020-12-03 13:47:32', '2021-02-18 20:19:16');
INSERT INTO `resource` VALUES ('010101', '0101', '保存', 'user.save', '', '[\"/system/admin/user/list\", \"/system/admin/user/save\"]', '2020-12-03 13:47:32', '2021-02-18 20:30:01');
INSERT INTO `resource` VALUES ('010102', '0101', '删除', NULL, NULL, '[\"/system/admin/user/delete\"]', '2020-12-03 13:47:32', NULL);
INSERT INTO `resource` VALUES ('0102', '01', '资源管理', NULL, '/system/resource', NULL, '2020-12-03 13:47:32', NULL);
INSERT INTO `resource` VALUES ('010201', '0102', '保存/显示', NULL, NULL, '[\"/system/admin/resource\"]', '2020-12-03 13:47:32', NULL);

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
INSERT INTO `role` VALUES ('00000002', '系统管理员', '负责用户角色权限', '2020-12-03 13:48:13', '2020-12-03 13:48:13');
INSERT INTO `role` VALUES ('00000003', '讲师', '编辑课程', '2020-12-03 13:48:13', '2021-02-14 11:45:26');

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
INSERT INTO `section` VALUES ('1348100769185796097', '支付宝api', '000012123', '1348100676453928961', '', 566, 'F', 0, '2021-01-10 10:54:05', '2021-02-10 11:57:49', '58eda9138fd04bd8a5805fbd6598fdee');
INSERT INTO `section` VALUES ('1348114448295952385', '概论', '1347880545798942721', '1348114393761611777', '', 990, 'F', 0, '2021-01-10 11:48:26', '2021-02-11 17:02:56', 'a604b029d7da4cfbbecd3be189aed805');
INSERT INTO `section` VALUES ('1348115629302906881', '支付宝sdk', '000012123', '1348100676453928961', '', 566, 'C', 1, '2021-01-10 11:53:07', '2021-02-10 15:57:39', '');
INSERT INTO `section` VALUES ('1348519809431654401', '概述', '00000001', '1348519740418576386', '', 123, 'F', NULL, '2021-01-11 14:39:12', '2021-01-11 14:39:12', '');
INSERT INTO `section` VALUES ('1348519862774812673', '数组', '00000001', '1348519767064989697', '', 123, 'F', NULL, '2021-01-11 14:39:24', '2021-01-11 14:39:24', '');
INSERT INTO `section` VALUES ('1355363707567300610', '概论', '1355363330293850114', '1355363441228996610', '', 990, 'F', 1, '2021-01-30 11:54:24', '2021-02-13 10:01:09', 'a604b029d7da4cfbbecd3be189aed805');
INSERT INTO `section` VALUES ('1355363891990847489', '各种mq介绍', '1355363330293850114', '1355363441228996610', '', 566, 'F', 1, '2021-01-30 11:55:08', '2021-02-11 10:08:43', '5853386ddf58440cbeccf569b4225e5a');
INSERT INTO `section` VALUES ('1355479493107535874', 'http协议', '1347880545798942721', '1355479248218902530', '', 737, 'C', 3, '2021-01-30 19:34:29', '2021-01-31 10:57:14', '');
INSERT INTO `section` VALUES ('1355796199969394689', 'MQ使用心得', '1355363330293850114', '1355363441228996610', '', 990, 'F', 1, '2021-01-31 16:32:58', '2021-02-12 11:57:23', 'a604b029d7da4cfbbecd3be189aed805');
INSERT INTO `section` VALUES ('1359348333478432770', 'rabbitmq介绍', '1355363330293850114', '1359347981123342338', '', 566, 'F', 0, '2021-02-10 11:47:53', '2021-02-11 10:23:40', '5853386ddf58440cbeccf569b4225e5a');
INSERT INTO `section` VALUES ('1359355427795513345', 'rabbit', '1355363330293850114', '1359347981123342338', '', 566, 'F', 1, '2021-02-10 12:16:04', '2021-02-11 10:23:25', '5853386ddf58440cbeccf569b4225e5a');

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
INSERT INTO `teacher` VALUES ('1348627061677342722', '关过江', 'lewis', 'https://chaoslife.oss-cn-hangzhou.aliyuncs.com/2021/01/29/63d34cfb-e317-4a22-9605-c9d05504d6cf.jpg', 'Java高级工程师', '123', '中国科学院计算机技术研究所博士', 0, '2021-01-11 21:45:22', '2021-01-11 21:45:22');
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
INSERT INTO `user` VALUES ('10000000', 'test3', '张三', '$2a$10$SCpDoWnDzwZH3D3TjcO/YeKwYjWwZZje1cL1o7HkZZRyiE31n8/lK', '2020-12-03 13:37:46', NULL);
INSERT INTO `user` VALUES ('1360179965009653762', 'user', '123123', '$2a$10$3ZuqY842qiw9UTp5.Z3NcOboG7xkB1WHPsOWc.voBA2oixwSpTyta', '2021-02-12 18:52:29', '2021-02-12 18:52:29');

SET FOREIGN_KEY_CHECKS = 1;
