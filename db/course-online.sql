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

 Date: 09/01/2021 20:32:25
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
INSERT INTO `category` VALUES ('00000101', '00000100', 'html/css', 101, NULL, NULL);
INSERT INTO `category` VALUES ('00000102', '00000100', 'javascript', 102, NULL, NULL);
INSERT INTO `category` VALUES ('00000103', '00000100', 'vue.js', 103, NULL, NULL);
INSERT INTO `category` VALUES ('00000104', '00000100', 'react.js', 104, NULL, NULL);
INSERT INTO `category` VALUES ('00000105', '00000100', 'angular', 105, NULL, NULL);
INSERT INTO `category` VALUES ('00000106', '00000100', 'node.js', 106, NULL, NULL);
INSERT INTO `category` VALUES ('00000107', '00000100', 'jquery', 107, NULL, NULL);
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
INSERT INTO `category` VALUES ('00000401', '00000400', '微服务', 401, NULL, NULL);
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
INSERT INTO `category` VALUES ('00000703', '00000700', 'mongodb', 703, NULL, '2021-01-09 15:20:46');
INSERT INTO `category` VALUES ('1347743751056924674', '00000100', 'typescript', NULL, '2021-01-09 11:15:25', '2021-01-09 11:15:25');
INSERT INTO `category` VALUES ('1347795498152505346', '', '中间件', NULL, '2021-01-09 14:41:02', '2021-01-09 14:41:02');
INSERT INTO `category` VALUES ('1347795741317279745', '00000200', 'go', NULL, '2021-01-09 14:42:00', '2021-01-09 14:42:00');
INSERT INTO `category` VALUES ('1347800117612130305', '00000200', '支付技术', NULL, '2021-01-09 14:59:24', '2021-01-09 14:59:24');
INSERT INTO `category` VALUES ('1347805835912294402', '00000700', 'mongodb', NULL, '2021-01-09 15:22:07', '2021-01-09 15:22:07');
INSERT INTO `category` VALUES ('1347810222948757505', '1347795498152505346', 'rocketmq', NULL, '2021-01-09 15:39:33', '2021-01-09 15:39:33');
INSERT INTO `category` VALUES ('1347810245128237057', '00000600', 'k8s', NULL, '2021-01-09 15:39:38', '2021-01-09 15:41:56');
INSERT INTO `category` VALUES ('1347810351441260546', '00000600', 'selenium', NULL, '2021-01-09 15:40:04', '2021-01-09 15:40:04');
INSERT INTO `category` VALUES ('1347810882482089986', '00000600', 'ansible', NULL, '2021-01-09 15:42:10', '2021-01-09 15:42:10');
INSERT INTO `category` VALUES ('1347815177990451202', '00000600', 'saltstack', NULL, '2021-01-09 15:59:14', '2021-01-09 15:59:14');
INSERT INTO `category` VALUES ('1347826684526604290', '00000700', 'oracle', NULL, '2021-01-09 16:44:58', '2021-01-09 16:44:58');
INSERT INTO `category` VALUES ('1347882057077907458', '1347795498152505346', 'rabbitmq', NULL, '2021-01-09 20:25:00', '2021-01-09 20:25:00');
INSERT INTO `category` VALUES ('1347882076388483074', '1347795498152505346', 'kafka', NULL, '2021-01-09 20:25:04', '2021-01-09 20:25:04');
INSERT INTO `category` VALUES ('1347882666032128002', '00000200', '微服务', NULL, '2021-01-09 20:27:25', '2021-01-09 20:27:25');

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
INSERT INTO `chapter` VALUES ('1338468722569986049', '00000001', '1111111', '2020-12-14 20:59:46', '2020-12-14 21:00:39', 0);
INSERT INTO `chapter` VALUES ('1338477928027037697', '00000001', 'aaaaa', '2020-12-14 21:36:20', '2020-12-14 21:36:20', 0);
INSERT INTO `chapter` VALUES ('1338478637887823874', '00000001', 'qqweqwe', '2020-12-14 21:39:10', '2020-12-14 21:39:10', 0);
INSERT INTO `chapter` VALUES ('1338661445444935682', '00000001', 'ceshi123', '2020-12-15 09:45:34', '2020-12-15 09:45:34', 0);
INSERT INTO `chapter` VALUES ('1338687023719960577', '00000001', '123', '2020-12-15 11:27:13', '2020-12-15 11:27:13', 0);
INSERT INTO `chapter` VALUES ('1338748459397775361', '00000001', '123123', '2020-12-15 15:31:20', '2020-12-15 15:31:20', 0);
INSERT INTO `chapter` VALUES ('1338748531288145922', '00000001', '123', '2020-12-15 15:31:37', '2020-12-15 15:31:37', 0);
INSERT INTO `chapter` VALUES ('1340505698831720450', '1343040305078759425', '123', '2020-12-20 11:53:59', '2020-12-20 11:53:59', 1);
INSERT INTO `chapter` VALUES ('1343094701405794305', '1343040305078759425', '123123', '2020-12-27 15:21:45', '2020-12-27 15:21:45', 0);
INSERT INTO `chapter` VALUES ('1343100259013857281', '00000001', '123', '2020-12-27 15:43:50', '2020-12-27 15:43:50', 0);
INSERT INTO `chapter` VALUES ('1343100290047512578', '1343040305078759425', '123aaaa', '2020-12-27 15:43:57', '2020-12-27 15:44:15', 0);

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
INSERT INTO `course` VALUES ('00000001', '测试课程0123', '这是一门测试课程', 223, 19.90, '', '2', 'C', 'P', 100, 0, 0, '2020-11-29 13:33:54', '2021-01-09 19:20:35', '123');
INSERT INTO `course` VALUES ('000012123', '测试课程01', '这是一门测试课程', 7200, 19.90, '', '1', 'C', 'P', 100, 0, 0, '2020-11-29 13:33:54', '2021-01-09 20:27:35', '123');
INSERT INTO `course` VALUES ('1343040305078759425', '123123', '123213', 623, 123.00, NULL, '1', 'F', 'P', 123, 123, 0, '2020-12-27 11:45:36', '2021-01-09 19:50:52', '123');
INSERT INTO `course` VALUES ('1347880545798942721', '123', '123', 0, 123.00, '', '1', 'F', 'D', 123, NULL, 0, '2021-01-09 20:18:59', '2021-01-09 20:19:34', '123');

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
INSERT INTO `course_category` VALUES ('1347882709493506049', '000012123', '1347882666032128002', '2021-01-09 20:27:35', '2021-01-09 20:27:35');
INSERT INTO `course_category` VALUES ('1347882709493506050', '000012123', '00000202', '2021-01-09 20:27:35', '2021-01-09 20:27:35');
INSERT INTO `course_category` VALUES ('1347882709493506051', '000012123', '00000201', '2021-01-09 20:27:35', '2021-01-09 20:27:35');

-- ----------------------------
-- Table structure for course_content
-- ----------------------------
DROP TABLE IF EXISTS `course_content`;
CREATE TABLE `course_content`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '课程id',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '课程内容',
  `is_delete` int(1) NULL DEFAULT 0,
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '课程内容' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_content
-- ----------------------------

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
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '相对路径',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `suffix` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '后缀',
  `size` int(11) NULL DEFAULT NULL COMMENT '大小|字节B',
  `use` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用途|枚举[FileUseEnum]：COURSE(C, 讲师), TEACHER(T, 课程)',
  `shard_index` int(11) NULL DEFAULT NULL COMMENT '已上传分片',
  `shard_size` int(11) NULL DEFAULT NULL COMMENT '分片大小|B',
  `shard_total` int(11) NULL DEFAULT NULL COMMENT '分片总数',
  `key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件标识',
  `vod` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'vod|阿里云vod',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `is_delete` int(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `path_unique`(`path`) USING BTREE,
  UNIQUE INDEX `key_unique`(`key`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文件' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of file
-- ----------------------------

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
INSERT INTO `section` VALUES ('00000001', '测试小节01', '1343040305078759425', '1343100290047512578', '123', 500, 'C', 1, '2020-11-29 13:33:54', '2020-12-20 13:19:11', '123');
INSERT INTO `section` VALUES ('1343162641283145730', '123123123', '00000001', '1338468722569986049', '123', 123, 'C', 123, '2020-12-27 19:51:43', '2020-12-27 19:54:13', '123');
INSERT INTO `section` VALUES ('1343162951296737282', '123', '1343041788956405761', '00000004', '123', 123, 'F', 123, '2020-12-27 19:52:57', '2020-12-27 19:52:57', '123');
INSERT INTO `section` VALUES ('1343454009268129793', 'asdasda', '00000001', '1338468722569986049', '123123', 100, 'F', 123, '2020-12-28 15:09:31', '2020-12-28 15:09:31', '123');
INSERT INTO `section` VALUES ('1347740905058082817', '123', '1343040305078759425', '1343094701405794305', '123', 123, 'F', 123, '2021-01-09 11:04:06', '2021-01-09 11:04:06', '123');

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
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '讲师' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------

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
