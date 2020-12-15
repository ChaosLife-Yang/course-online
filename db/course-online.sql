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

 Date: 15/12/2020 14:16:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `parent` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '父id',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `sort` int(11) NULL DEFAULT NULL COMMENT '顺序',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `is_delete` int(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('100', '00000000', '前端技术', 100, NULL, NULL, 0);
INSERT INTO `category` VALUES ('101', '00000100', 'html/css', 101, NULL, NULL, 0);
INSERT INTO `category` VALUES ('102', '00000100', 'javascript', 102, NULL, NULL, 0);
INSERT INTO `category` VALUES ('103', '00000100', 'vue.js', 103, NULL, NULL, 0);
INSERT INTO `category` VALUES ('104', '00000100', 'react.js', 104, NULL, NULL, 0);
INSERT INTO `category` VALUES ('105', '00000100', 'angular', 105, NULL, NULL, 0);
INSERT INTO `category` VALUES ('106', '00000100', 'node.js', 106, NULL, NULL, 0);
INSERT INTO `category` VALUES ('107', '00000100', 'jquery', 107, NULL, NULL, 0);
INSERT INTO `category` VALUES ('108', '00000100', '小程序', 108, NULL, NULL, 0);
INSERT INTO `category` VALUES ('200', '00000000', '后端技术', 200, NULL, NULL, 0);
INSERT INTO `category` VALUES ('201', '00000200', 'java', 201, NULL, NULL, 0);
INSERT INTO `category` VALUES ('202', '00000200', 'springboot', 202, NULL, NULL, 0);
INSERT INTO `category` VALUES ('203', '00000200', 'spring cloud', 203, NULL, NULL, 0);
INSERT INTO `category` VALUES ('204', '00000200', 'ssm', 204, NULL, NULL, 0);
INSERT INTO `category` VALUES ('205', '00000200', 'python', 205, NULL, NULL, 0);
INSERT INTO `category` VALUES ('206', '00000200', '爬虫', 206, NULL, NULL, 0);
INSERT INTO `category` VALUES ('300', '00000000', '移动开发', 300, NULL, NULL, 0);
INSERT INTO `category` VALUES ('301', '00000300', 'android', 301, NULL, NULL, 0);
INSERT INTO `category` VALUES ('302', '00000300', 'ios', 302, NULL, NULL, 0);
INSERT INTO `category` VALUES ('303', '00000300', 'react native', 303, NULL, NULL, 0);
INSERT INTO `category` VALUES ('304', '00000300', 'ionic', 304, NULL, NULL, 0);
INSERT INTO `category` VALUES ('400', '00000000', '前沿技术', 400, NULL, NULL, 0);
INSERT INTO `category` VALUES ('401', '00000400', '微服务', 401, NULL, NULL, 0);
INSERT INTO `category` VALUES ('402', '00000400', '区块链', 402, NULL, NULL, 0);
INSERT INTO `category` VALUES ('403', '00000400', '机器学习', 403, NULL, NULL, 0);
INSERT INTO `category` VALUES ('404', '00000400', '深度学习', 404, NULL, NULL, 0);
INSERT INTO `category` VALUES ('405', '00000400', '数据分析&挖掘', 405, NULL, NULL, 0);
INSERT INTO `category` VALUES ('500', '00000000', '云计算&大数据', 500, NULL, NULL, 0);
INSERT INTO `category` VALUES ('501', '00000500', '大数据', 501, NULL, NULL, 0);
INSERT INTO `category` VALUES ('502', '00000500', 'hadoop', 502, NULL, NULL, 0);
INSERT INTO `category` VALUES ('503', '00000500', 'spark', 503, NULL, NULL, 0);
INSERT INTO `category` VALUES ('504', '00000500', 'hbase', 504, NULL, NULL, 0);
INSERT INTO `category` VALUES ('505', '00000500', '阿里云', 505, NULL, NULL, 0);
INSERT INTO `category` VALUES ('506', '00000500', 'docker', 506, NULL, NULL, 0);
INSERT INTO `category` VALUES ('507', '00000500', 'kubernetes', 507, NULL, NULL, 0);
INSERT INTO `category` VALUES ('600', '00000000', '运维&测试', 600, NULL, NULL, 0);
INSERT INTO `category` VALUES ('601', '00000600', '运维', 601, NULL, NULL, 0);
INSERT INTO `category` VALUES ('602', '00000600', '自动化运维', 602, NULL, NULL, 0);
INSERT INTO `category` VALUES ('603', '00000600', '中间件', 603, NULL, NULL, 0);
INSERT INTO `category` VALUES ('604', '00000600', 'linux', 604, NULL, NULL, 0);
INSERT INTO `category` VALUES ('605', '00000600', '测试', 605, NULL, NULL, 0);
INSERT INTO `category` VALUES ('606', '00000600', '自动化测试', 606, NULL, NULL, 0);
INSERT INTO `category` VALUES ('700', '00000000', '数据库', 700, NULL, NULL, 0);
INSERT INTO `category` VALUES ('701', '00000700', 'mysql', 701, NULL, NULL, 0);
INSERT INTO `category` VALUES ('702', '00000700', 'redis', 702, NULL, NULL, 0);
INSERT INTO `category` VALUES ('703', '00000700', 'mongodb', 703, NULL, NULL, 0);

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '大章' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chapter
-- ----------------------------
INSERT INTO `chapter` VALUES ('00000001', '11111111', '测试大章01', NULL, '2020-12-14 21:39:18', 1);
INSERT INTO `chapter` VALUES ('00000002', '123123213', '测试大章02', NULL, '2020-12-14 21:33:47', 1);
INSERT INTO `chapter` VALUES ('00000003', '00000000', '测试大章03', NULL, NULL, 1);
INSERT INTO `chapter` VALUES ('00000004', '123123123', '测试大章04', NULL, '2020-12-15 09:45:21', 0);
INSERT INTO `chapter` VALUES ('00000005', '00000000', '测试大章05', NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000006', '00000000', '测试大章06', NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000007', '00000000', '测试大章07', NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000008', '00000000', '测试大章08', NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000009', '00000000', '测试大章09', NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000010', '00000000', '测试大章10', NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000011', '00000000', '测试大章11', NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000012', '123213132213', '测试大章12', NULL, '2020-12-14 21:39:25', 0);
INSERT INTO `chapter` VALUES ('00000013', '00000000', '测试大章13', NULL, NULL, 0);
INSERT INTO `chapter` VALUES ('00000014', '00000000', '测试大章14', NULL, NULL, 1);
INSERT INTO `chapter` VALUES ('1338468722569986049', '12323213', '1111111', '2020-12-14 20:59:46', '2020-12-14 21:00:39', 0);
INSERT INTO `chapter` VALUES ('1338477928027037697', '123', 'aaaaa', '2020-12-14 21:36:20', '2020-12-14 21:36:20', 0);
INSERT INTO `chapter` VALUES ('1338478637887823874', '21213', 'qqweqwe', '2020-12-14 21:39:10', '2020-12-14 21:39:10', 0);
INSERT INTO `chapter` VALUES ('1338661445444935682', '123123123', 'ceshi123', '2020-12-15 09:45:34', '2020-12-15 09:45:34', 0);
INSERT INTO `chapter` VALUES ('1338682718074019841', '', '', '2020-12-15 11:10:06', '2020-12-15 11:10:06', 1);
INSERT INTO `chapter` VALUES ('1338687023719960577', '123', '123', '2020-12-15 11:27:13', '2020-12-15 11:27:13', 0);

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('00000001', '测试课程01', '这是一门测试课程', 7200, 19.90, '', '1', 'C', 'P', 100, 0, NULL, '2020-11-29 13:33:54', '2020-11-29 13:33:54', NULL);

-- ----------------------------
-- Table structure for course_category
-- ----------------------------
DROP TABLE IF EXISTS `course_category`;
CREATE TABLE `course_category`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'id',
  `course_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '课程|course.id',
  `category_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分类|category.id',
  `is_delete` int(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `edit_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程分类' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course_category
-- ----------------------------

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程内容' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '课程内容文件' ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '文件' ROW_FORMAT = Dynamic;

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
INSERT INTO `section` VALUES ('00000001', '测试小节01', '00000001', '00000000', '', 500, 'f', 1, '2020-11-29 13:33:54', '2020-11-29 13:33:54', NULL);

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('10000000', 'test', '测试', 'e70e2222a9d67c4f2eae107533359aa4', '2020-12-03 13:37:46', NULL);

SET FOREIGN_KEY_CHECKS = 1;
