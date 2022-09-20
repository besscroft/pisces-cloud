/*
 Navicat Premium Data Transfer

 Source Server         : localhostPostgre
 Source Server Type    : PostgreSQL
 Source Server Version : 140004
 Source Host           : localhost:5432
 Source Catalog        : pisces
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 140004
 File Encoding         : 65001

 Date: 20/09/2022 21:01:11
*/


-- ----------------------------
-- Sequence structure for pisces_auth_depart_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."pisces_auth_depart_id_seq";
CREATE SEQUENCE "public"."pisces_auth_depart_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for pisces_auth_menu_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."pisces_auth_menu_id_seq";
CREATE SEQUENCE "public"."pisces_auth_menu_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for pisces_auth_resource_category_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."pisces_auth_resource_category_id_seq";
CREATE SEQUENCE "public"."pisces_auth_resource_category_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for pisces_auth_resource_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."pisces_auth_resource_id_seq";
CREATE SEQUENCE "public"."pisces_auth_resource_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for pisces_auth_role_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."pisces_auth_role_id_seq";
CREATE SEQUENCE "public"."pisces_auth_role_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for pisces_auth_role_menu_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."pisces_auth_role_menu_id_seq";
CREATE SEQUENCE "public"."pisces_auth_role_menu_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for pisces_auth_role_resource_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."pisces_auth_role_resource_id_seq";
CREATE SEQUENCE "public"."pisces_auth_role_resource_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for pisces_auth_user_depart_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."pisces_auth_user_depart_id_seq";
CREATE SEQUENCE "public"."pisces_auth_user_depart_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for pisces_auth_user_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."pisces_auth_user_id_seq";
CREATE SEQUENCE "public"."pisces_auth_user_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for pisces_auth_user_role_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."pisces_auth_user_role_id_seq";
CREATE SEQUENCE "public"."pisces_auth_user_role_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for pisces_sys_dict_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."pisces_sys_dict_id_seq";
CREATE SEQUENCE "public"."pisces_sys_dict_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for pisces_sys_log_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."pisces_sys_log_id_seq";
CREATE SEQUENCE "public"."pisces_sys_log_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for pisces_sys_white_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."pisces_sys_white_id_seq";
CREATE SEQUENCE "public"."pisces_sys_white_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Table structure for pisces_auth_depart
-- ----------------------------
DROP TABLE IF EXISTS "public"."pisces_auth_depart";
CREATE TABLE "public"."pisces_auth_depart" (
  "id" int8 NOT NULL DEFAULT nextval('pisces_auth_depart_id_seq'::regclass),
  "parent_id" int8,
  "name" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "description" varchar(500) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "sort" int4 DEFAULT 0,
  "creator" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "updater" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "create_time" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "del" int2 NOT NULL DEFAULT 1
)
;
COMMENT ON COLUMN "public"."pisces_auth_depart"."parent_id" IS '上级ID';
COMMENT ON COLUMN "public"."pisces_auth_depart"."name" IS '部门名称';
COMMENT ON COLUMN "public"."pisces_auth_depart"."description" IS '部门描述';
COMMENT ON COLUMN "public"."pisces_auth_depart"."sort" IS '排序';
COMMENT ON COLUMN "public"."pisces_auth_depart"."creator" IS '创建者';
COMMENT ON COLUMN "public"."pisces_auth_depart"."updater" IS '更新者';
COMMENT ON COLUMN "public"."pisces_auth_depart"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."pisces_auth_depart"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."pisces_auth_depart"."del" IS '逻辑删除：0->删除状态；1->可用状态';
COMMENT ON TABLE "public"."pisces_auth_depart" IS '组织机构表';

-- ----------------------------
-- Records of pisces_auth_depart
-- ----------------------------
INSERT INTO "public"."pisces_auth_depart" VALUES (1, 0, 'Pisces Item', 'Pisces 开源组织', 1, 'admin', 'admin', '2022-03-24 18:16:17', '2022-03-24 18:16:19', 1);
INSERT INTO "public"."pisces_auth_depart" VALUES (2, 1, '研发部', '研发部门', 2, 'admin', 'admin', '2022-03-24 18:16:39', '2022-03-24 18:16:41', 1);
INSERT INTO "public"."pisces_auth_depart" VALUES (3, 1, '设计部', '设计部门', 3, 'admin', 'admin', '2022-03-24 18:17:01', '2022-03-24 18:17:02', 1);
INSERT INTO "public"."pisces_auth_depart" VALUES (6, 1, 'gfd', '', 2, 'admin', 'admin', '2022-04-30 17:55:55', '2022-04-30 17:56:17', 0);
INSERT INTO "public"."pisces_auth_depart" VALUES (8, 1, '测试1', '', 2, 'admin', 'admin', '2022-08-28 15:40:32', '2022-08-28 15:40:32', 0);
INSERT INTO "public"."pisces_auth_depart" VALUES (7, 3, '测试', '测试', 2, 'admin', 'admin', '2022-08-28 15:40:18', '2022-08-28 15:40:18', 0);

-- ----------------------------
-- Table structure for pisces_auth_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."pisces_auth_menu";
CREATE TABLE "public"."pisces_auth_menu" (
  "id" int8 NOT NULL DEFAULT nextval('pisces_auth_menu_id_seq'::regclass),
  "parent_id" int8,
  "title" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "name" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "parent_title" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "level" int4,
  "component" varchar(255) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "path" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "icon" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "sort" int4 DEFAULT 0,
  "creator" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "updater" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "create_time" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "hidden" int2 NOT NULL DEFAULT 0,
  "del" int2 NOT NULL DEFAULT 1
)
;
COMMENT ON COLUMN "public"."pisces_auth_menu"."parent_id" IS '父级ID';
COMMENT ON COLUMN "public"."pisces_auth_menu"."title" IS '菜单名称';
COMMENT ON COLUMN "public"."pisces_auth_menu"."name" IS '前端名称';
COMMENT ON COLUMN "public"."pisces_auth_menu"."parent_title" IS '父菜单名称';
COMMENT ON COLUMN "public"."pisces_auth_menu"."level" IS '菜单级数';
COMMENT ON COLUMN "public"."pisces_auth_menu"."component" IS '组件路径';
COMMENT ON COLUMN "public"."pisces_auth_menu"."path" IS '路由地址';
COMMENT ON COLUMN "public"."pisces_auth_menu"."icon" IS '菜单图标名称';
COMMENT ON COLUMN "public"."pisces_auth_menu"."sort" IS '排序';
COMMENT ON COLUMN "public"."pisces_auth_menu"."creator" IS '创建者';
COMMENT ON COLUMN "public"."pisces_auth_menu"."updater" IS '更新者';
COMMENT ON COLUMN "public"."pisces_auth_menu"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."pisces_auth_menu"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."pisces_auth_menu"."hidden" IS '菜单显示状态：0->禁用；1->启用';
COMMENT ON COLUMN "public"."pisces_auth_menu"."del" IS '逻辑删除：0->删除状态；1->可用状态';
COMMENT ON TABLE "public"."pisces_auth_menu" IS '菜单表';

-- ----------------------------
-- Records of pisces_auth_menu
-- ----------------------------
INSERT INTO "public"."pisces_auth_menu" VALUES (6, 1, '菜单管理', 'menu', '权限管理', 2, '/auth/menu/index', 'menu', 'Menu', 4, 'admin', 'admin', '2022-03-20 18:44:08', '2022-05-15 12:48:04', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (3, 1, '用户管理', 'authUser', '权限管理', 2, '/auth/user/index', 'user', 'User', 2, 'admin', 'admin', '2022-03-04 20:56:48', '2022-05-15 12:49:55', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (8, 1, '资源管理', 'resource', '资源管理', 2, '/auth/resource/index', 'resource', 'Finished', 6, 'admin', 'admin', '2022-03-20 18:45:47', '2022-03-20 18:45:49', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (5, 1, '角色管理', 'role', '角色管理', 2, '/auth/role/index', 'role', 'UserFilled', 3, 'admin', 'admin', '2022-03-20 18:43:01', '2022-03-20 18:43:03', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (7, 1, '资源类别管理', 'resourceCategory', '资源类别管理', 2, '/auth/resourceCategory/index', 'resourceCategory', 'Briefcase', 5, 'admin', 'admin', '2022-03-20 18:45:09', '2022-03-24 17:11:57', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (1, 0, '权限管理', 'auth', NULL, 1, 'Layout', '/auth', 'Promotion', 1, 'admin', 'admin', '2022-03-04 20:54:23', '2022-03-04 20:54:26', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (10, 2, '白名单管理', 'whiteList', '白名单管理', 2, '/system/whiteList/index', 'whiteList', 'CircleCheckFilled', 2, 'admin', 'admin', '2022-04-26 21:29:06', '2022-04-26 21:29:06', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (9, 1, '部门管理', 'depart', '部门管理', 2, '/auth/depart/index', 'depart', 'Briefcase', 7, 'admin', 'admin', '2022-03-20 18:46:20', '2022-05-08 13:54:12', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (2, 0, '系统管理', 'system', NULL, 1, 'Layout', '/system', 'SetUp', 3, 'admin', 'admin', '2022-03-04 20:55:19', '2022-04-26 20:31:24', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (12, 0, '组件', 'component', NULL, 1, 'Layout', '/component', 'Box', 2, 'admin', 'admin', '2022-07-19 18:23:24', '2022-07-19 18:23:24', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (13, 12, 'OSS 管理', 'alioss', '组件', 2, '/component/alioss/index', 'alioss', 'Coin', 1, 'admin', 'admin', '2022-07-19 18:27:38', '2022-07-19 18:27:38', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (4, 2, '日志管理', 'log', '系统管理', 2, '/system/log/index', 'log', 'Document', 2, 'admin', 'admin', '2022-03-06 12:33:45', '2022-03-06 12:33:48', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (14, 2, '字典管理', 'dict', '系统管理', 2, '/system/dict/index', 'dict', 'MessageBox', 3, 'admin', 'admin', '2022-08-24 17:19:29', '2022-08-24 17:35:15', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (15, 13, 'oss测试1', 'oss测试1', 'OSS 管理', 0, 'oss测试', 'oss测试111', 'oss测试', 1, 'admin', 'admin', '2022-08-28 15:22:46', '2022-08-28 15:22:57', 1, 0);

-- ----------------------------
-- Table structure for pisces_auth_resource
-- ----------------------------
DROP TABLE IF EXISTS "public"."pisces_auth_resource";
CREATE TABLE "public"."pisces_auth_resource" (
  "id" int8 NOT NULL DEFAULT nextval('pisces_auth_resource_id_seq'::regclass),
  "name" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "url" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "description" varchar(500) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "category_id" int8 NOT NULL,
  "sort" int4 DEFAULT 0,
  "creator" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "updater" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "create_time" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "del" int2 NOT NULL DEFAULT 1,
  "route_key" varchar(32) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "public"."pisces_auth_resource"."name" IS '资源名称';
COMMENT ON COLUMN "public"."pisces_auth_resource"."url" IS '资源路径';
COMMENT ON COLUMN "public"."pisces_auth_resource"."description" IS '资源描述';
COMMENT ON COLUMN "public"."pisces_auth_resource"."category_id" IS '资源类别ID';
COMMENT ON COLUMN "public"."pisces_auth_resource"."sort" IS '排序';
COMMENT ON COLUMN "public"."pisces_auth_resource"."creator" IS '创建者';
COMMENT ON COLUMN "public"."pisces_auth_resource"."updater" IS '更新者';
COMMENT ON COLUMN "public"."pisces_auth_resource"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."pisces_auth_resource"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."pisces_auth_resource"."del" IS '逻辑删除：0->删除状态；1->可用状态';
COMMENT ON COLUMN "public"."pisces_auth_resource"."route_key" IS '路由分配 key';
COMMENT ON TABLE "public"."pisces_auth_resource" IS '资源表';

-- ----------------------------
-- Records of pisces_auth_resource
-- ----------------------------
INSERT INTO "public"."pisces_auth_resource" VALUES (2, '用户列表', '/user/list', '用户信息列表（分页）', 1, 2, 'admin', 'admin', '2022-03-13 16:48:42', '2022-03-13 16:48:44', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (1, '用户权限信息', '/user/info', '用户信息接口，需要认证！获取当前登录用户信息', 1, 1, 'admin', 'admin', '2022-03-04 21:25:12', '2022-03-04 21:25:14', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (4, '用户信息更新', '/user/update', '用户信息更新接口，需要认证！', 1, 4, 'admin', 'admin', '2022-03-13 19:42:56', '2022-03-13 19:42:58', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (5, '用户可用状态更新', '/user/change', '更改用户可用状态接口', 1, 5, 'admin', 'admin', '2022-03-20 11:44:45', '2022-03-20 11:44:47', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (6, '新增用户', '/user/add', '新增用户接口', 1, 6, 'admin', 'admin', '2022-03-20 14:54:51', '2022-03-20 14:54:51', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (7, '更新用户信息', '/user/update', '更新用户信息接口', 1, 7, 'admin', 'admin', '2022-03-20 16:50:25', '2022-03-20 16:50:25', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (9, '删除用户', '/user/delete/**', '根据用户id删除用户接口', 1, 8, 'admin', 'admin', '2022-03-20 16:50:59', '2022-08-05 15:29:45', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (10, '角色列表', '/role/list', '角色列表接口（分页）', 2, 1, 'admin', 'admin', '2022-03-20 19:27:39', '2022-03-20 19:27:42', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (12, '退出登录', '/user/loginOut', '退出登录接口', 1, 9, 'admin', 'admin', '2022-03-20 20:49:33', '2022-03-20 20:49:35', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (14, '菜单列表', '/menu/list', '菜单列表接口（分页）', 3, 1, 'admin', 'admin', '2022-03-24 14:53:49', '2022-03-24 14:53:51', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (15, '菜单可用状态更新', '/menu/change', '更改菜单可用状态接口', 3, 2, 'admin', 'admin', '2022-03-24 15:20:31', '2022-03-24 15:20:33', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (16, '删除菜单', '/menu/delete/**', '根据菜单id删除菜单接口', 3, 3, 'admin', 'admin', '2022-03-24 15:35:58', '2022-03-24 15:36:01', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (17, '更新菜单', '/menu/update', '更新菜单信息接口', 3, 4, 'admin', 'admin', '2022-03-24 16:08:06', '2022-03-24 16:08:07', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (18, '资源列表', '/resource/list', '资源列表接口（分页）', 5, 1, 'admin', 'admin', '2022-03-24 17:44:40', '2022-03-24 17:44:42', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (19, '资源类别列表', '/resource/category/list', '资源类别列表接口（分页）', 4, 1, 'admin', 'admin', '2022-03-24 17:45:22', '2022-03-24 17:45:24', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (20, '组织/部门列表', '/depart/list', '组织/部门列表接口（分页）', 6, 1, 'admin', 'admin', '2022-03-24 17:46:04', '2022-03-24 17:46:06', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (22, '获取所有菜单接口', '/menu/getAll', '获取所有菜单接口', 3, 6, 'admin', 'admin', '2022-04-02 22:09:40', '2022-04-02 22:09:40', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (21, '根据角色id查询菜单id列表', '/menu/getId/role/**', '根据角色id查询菜单id列表', 3, 5, 'admin', 'admin', '2022-04-02 21:56:57', '2022-04-02 21:56:59', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (23, '更改角色菜单接口', '/role/update/menu', '更改角色菜单接口', 2, 3, 'admin', 'admin', '2022-04-03 10:57:05', '2022-04-03 10:57:05', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (24, '获取资源树接口', '/resource/getAll', '获取资源树接口', 4, 2, 'admin', 'admin', '2022-04-03 11:26:00', '2022-04-03 11:26:00', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (25, '根据角色id查询资源id列表接口', '/resource/getId/role/**', '根据角色id查询资源id列表接口', 4, 3, 'admin', 'admin', '2022-04-03 12:10:47', '2022-04-03 12:10:47', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (26, '更改角色菜单接口', '/role/update/resource', '更改角色菜单接口', 2, 4, 'admin', 'admin', '2022-04-03 20:08:54', '2022-04-03 20:08:54', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (27, '角色删除接口', '/role/delete/**', '角色删除接口', 2, 5, 'admin', 'admin', '2022-04-03 20:22:36', '2022-04-03 20:22:36', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (29, '更新角色接口', '/role/update', '更新角色接口', 2, 7, 'admin', 'admin', '2022-04-03 21:36:42', '2022-04-03 21:36:42', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (28, '角色新增接口', '/role/add', '角色新增接口', 2, 6, 'admin', 'admin', '2022-04-03 21:36:23', '2022-04-03 21:36:23', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (30, '角色字典接口', '/role/getRoleDict', '角色字典接口', 2, 8, 'admin', 'admin', '2022-04-10 18:46:03', '2022-04-10 18:46:03', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (32, '更新用户角色接口', '/user/update/role', '更新用户角色接口', 2, 9, 'admin', 'admin', '2022-04-10 21:15:24', '2022-04-10 21:15:24', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (33, '资源类别删除接口', '/resource/category/delete/**', '资源类别删除接口', 4, 2, 'admin', 'admin', '2022-04-26 20:40:31', '2022-04-26 20:40:31', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (34, '组织/部门删除接口', '/depart/delete/**', '组织/部门删除接口', 6, 2, 'admin', 'admin', '2022-04-26 20:58:43', '2022-04-26 20:58:43', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (35, '资源删除接口', '/resource/delete/**', '资源删除接口', 5, 2, 'admin', 'admin', '2022-04-26 21:16:16', '2022-04-26 21:16:16', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (11, '角色可用状态更新', '/role/change', '更改角色可用状态接口', 2, 2, 'admin', 'admin', '2022-03-20 20:06:33', '2022-03-20 20:06:34', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (36, '新增资源接口', '/resource/add', '新增资源接口', 5, 3, 'admin', 'admin', '2022-04-30 06:41:04', '2022-04-30 06:41:04', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (37, '更新资源接口', '/resource/update', '更新资源接口', 5, 4, 'admin', 'admin', '2022-04-30 06:58:26', '2022-04-30 06:58:26', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (40, '资源类别查询接口', '/resource/category/getResourceCategoryDict', '资源类别查询接口', 4, 4, 'admin', 'admin', '2022-04-30 07:53:28', '2022-04-30 07:53:28', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (51, '新增资源类别接口', '/resource/category/add', '新增资源类别接口', 4, 5, 'admin', 'admin', '2022-05-08 15:02:07', '2022-05-08 15:02:07', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (52, '更新资源类别接口', '/resource/category/update', '更新资源类别接口', 4, 6, 'admin', 'admin', '2022-05-08 15:02:41', '2022-05-08 15:02:41', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (45, '新增组织/部门接口', '/depart/add', '新增组织/部门接口', 6, 3, 'admin', 'admin', '2022-04-30 08:59:01', '2022-04-30 08:59:01', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (46, '更新组织/部门接口', '/depart/update', '更新组织/部门接口', 6, 4, 'admin', 'admin', '2022-04-30 08:59:01', '2022-04-30 08:59:01', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (47, '部门字典接口', '/depart/getDepartDict', '部门字典接口', 6, 5, 'admin', 'admin', '2022-04-30 09:46:45', '2022-04-30 09:46:45', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (48, '获取角色信息接口', '/role/get/**', '根据角色 id 获取角色信息接口', 2, 3, 'admin', 'admin', '2022-04-30 13:23:20', '2022-04-30 13:23:20', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (49, '新增菜单接口', '/menu/add', '新增菜单接口', 3, 6, 'admin', 'admin', '2022-05-04 18:27:56', '2022-05-04 18:27:56', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (50, '菜单字典接口', '/menu/getMenuDict', '菜单字典接口', 3, 7, 'admin', 'admin', '2022-05-04 18:51:53', '2022-05-04 18:51:53', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (53, '白名单列表接口', '/white/list', '白名单列表接口', 7, 1, 'admin', 'admin', '2022-05-14 18:37:03', '2022-05-14 18:37:03', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (54, '新增白名单接口', '/white/add', '新增白名单接口', 7, 2, 'admin', 'admin', '2022-05-14 21:28:57', '2022-05-14 21:28:57', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (56, '删除白名单接口', '/white/delete/**', '删除白名单接口', 7, 4, 'admin', 'admin', '2022-05-14 21:29:28', '2022-05-14 21:29:28', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (3, '用户信息', '/user/info/**', '用户信息接口', 1, 3, 'admin', 'admin', '2022-03-13 19:41:51', '2022-05-21 22:59:30', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (55, '更新白名单接口', '/white/update', '更新白名单接口', 7, 3, 'admin', 'admin', '2022-05-14 21:29:09', '2022-05-14 21:29:09', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (57, '获取白名单字典', '/white/getWhiteDict', '获取白名单字典', 7, 5, 'admin', 'admin', '2022-05-14 22:20:02', '2022-05-14 22:20:02', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (58, '阿里 OSS 文件上传', '/aliyun/oss/upload', '阿里云 OSS 文件上传', 11, 1, 'admin', 'admin', '2022-08-05 13:59:03', '2022-08-05 13:59:03', 1, 'file');
INSERT INTO "public"."pisces_auth_resource" VALUES (61, '字典分页', '/dict/list', '字典管理分页接口', 12, 2, 'admin', 'admin', '2022-08-24 17:11:49', '2022-08-24 17:11:49', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (62, '字典下拉框', '/dict/groupQuery', '查询分组内所有字典接口', 12, 1, 'admin', 'admin', '2022-08-24 17:15:22', '2022-08-24 17:15:22', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (63, '新增字典', '/dict/add', '新增字典接口', 12, 3, 'admin', 'admin', '2022-08-28 13:35:17', '2022-08-28 13:35:17', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (64, '更新字典', '/dict/update', '更新字典成功', 12, 4, 'admin', 'admin', '2022-08-28 13:35:51', '2022-08-28 13:35:51', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (65, '删除字典', '/dict/delete/**', '删除字典接口（软删除）', 12, 6, 'admin', 'admin', '2022-08-28 13:43:19', '2022-08-28 13:43:19', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (68, '阿里 OSS 文件上传', '/aliyun/oss/uploadCdn', '阿里 OSS 文件上传 CDN', 11, 2, 'admin', 'admin', '2022-09-20 20:50:43', '2022-09-20 20:50:43', 1, 'file');

-- ----------------------------
-- Table structure for pisces_auth_resource_category
-- ----------------------------
DROP TABLE IF EXISTS "public"."pisces_auth_resource_category";
CREATE TABLE "public"."pisces_auth_resource_category" (
  "id" int8 NOT NULL DEFAULT nextval('pisces_auth_resource_category_id_seq'::regclass),
  "category_name" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "description" varchar(500) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "sort" int4 DEFAULT 0,
  "creator" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "updater" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "create_time" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "del" int2 NOT NULL DEFAULT 1
)
;
COMMENT ON COLUMN "public"."pisces_auth_resource_category"."category_name" IS '资源类别名称';
COMMENT ON COLUMN "public"."pisces_auth_resource_category"."description" IS '资源描述';
COMMENT ON COLUMN "public"."pisces_auth_resource_category"."sort" IS '排序';
COMMENT ON COLUMN "public"."pisces_auth_resource_category"."creator" IS '创建者';
COMMENT ON COLUMN "public"."pisces_auth_resource_category"."updater" IS '更新者';
COMMENT ON COLUMN "public"."pisces_auth_resource_category"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."pisces_auth_resource_category"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."pisces_auth_resource_category"."del" IS '逻辑删除：0->删除状态；1->可用状态';
COMMENT ON TABLE "public"."pisces_auth_resource_category" IS '资源类别表';

-- ----------------------------
-- Records of pisces_auth_resource_category
-- ----------------------------
INSERT INTO "public"."pisces_auth_resource_category" VALUES (1, '用户管理', '用户管理', 1, 'admin', 'admin', '2022-03-04 21:25:53', '2022-03-04 21:25:51', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (2, '角色管理', '角色管理', 2, 'admin', 'admin', '2022-03-20 19:28:04', '2022-03-20 19:28:06', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (3, '菜单管理', '菜单管理', 3, 'admin', 'admin', '2022-03-24 14:54:21', '2022-03-24 14:54:23', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (4, '资源类别管理', '资源类别管理', 4, 'admin', 'admin', '2022-03-24 14:54:44', '2022-03-24 14:54:46', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (5, '资源管理', '资源管理', 5, 'admin', 'admin', '2022-03-24 14:55:02', '2022-03-24 14:55:04', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (6, '部门管理', '部门管理', 6, 'admin', 'admin', '2022-03-24 14:55:19', '2022-03-24 14:55:22', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (7, '白名单管理', '白名单管理', 7, 'admin', 'admin', '2022-04-26 21:27:30', '2022-04-26 21:27:30', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (11, '文件中心', '分布式文件中心接口', 8, 'admin', 'admin', '2022-08-05 13:58:23', '2022-08-05 13:58:23', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (12, '字典管理', '系统字典管理资源', 9, 'admin', 'admin', '2022-08-24 17:06:51', '2022-08-24 17:06:51', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (13, 'ces', 'ces', 0, 'admin', 'admin', '2022-08-28 15:23:05', '2022-08-28 15:27:20', 0);

-- ----------------------------
-- Table structure for pisces_auth_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."pisces_auth_role";
CREATE TABLE "public"."pisces_auth_role" (
  "id" int8 NOT NULL DEFAULT nextval('pisces_auth_role_id_seq'::regclass),
  "role_name" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "role_code" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "description" varchar(500) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "sort" int4 DEFAULT 0,
  "creator" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "updater" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "create_time" timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "status" int2 NOT NULL DEFAULT 0,
  "del" int2 NOT NULL DEFAULT 1
)
;
COMMENT ON COLUMN "public"."pisces_auth_role"."role_name" IS '角色名称';
COMMENT ON COLUMN "public"."pisces_auth_role"."role_code" IS '角色编码';
COMMENT ON COLUMN "public"."pisces_auth_role"."description" IS '描述';
COMMENT ON COLUMN "public"."pisces_auth_role"."sort" IS '排序';
COMMENT ON COLUMN "public"."pisces_auth_role"."creator" IS '创建者';
COMMENT ON COLUMN "public"."pisces_auth_role"."updater" IS '更新者';
COMMENT ON COLUMN "public"."pisces_auth_role"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."pisces_auth_role"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."pisces_auth_role"."status" IS '角色启用状态：0->禁用；1->启用';
COMMENT ON COLUMN "public"."pisces_auth_role"."del" IS '逻辑删除：0->删除状态；1->可用状态';
COMMENT ON TABLE "public"."pisces_auth_role" IS '角色表';

-- ----------------------------
-- Records of pisces_auth_role
-- ----------------------------
INSERT INTO "public"."pisces_auth_role" VALUES (1, '超级管理员', 'administrator', '超级管理员，拥有所有的权限', 0, 'administrator', 'administrator', '2022-02-04 08:28:07', '2022-02-04 08:28:07', 1, 1);
INSERT INTO "public"."pisces_auth_role" VALUES (3, 'ces', 'ces', 'ss1111s', 2, 'admin', 'admin', '2022-08-28 15:22:06', '2022-08-28 15:22:19', 1, 0);
INSERT INTO "public"."pisces_auth_role" VALUES (4, '3112', '32131', '31', 2, 'admin', NULL, '2022-08-28 17:05:54', '2022-08-28 17:05:54', 0, 0);
INSERT INTO "public"."pisces_auth_role" VALUES (5, '测试', '测试', '测试', 2, 'admin', NULL, '2022-08-28 17:08:17', '2022-08-28 17:08:17', 1, 0);
INSERT INTO "public"."pisces_auth_role" VALUES (2, '测试员', 'test', '测试专用角色', 1, 'admin', 'admin', '2022-03-04 23:32:26', '2022-03-04 23:32:27', 1, 1);

-- ----------------------------
-- Table structure for pisces_auth_role_menu
-- ----------------------------
DROP TABLE IF EXISTS "public"."pisces_auth_role_menu";
CREATE TABLE "public"."pisces_auth_role_menu" (
  "id" int8 NOT NULL DEFAULT nextval('pisces_auth_role_menu_id_seq'::regclass),
  "role_id" int8 NOT NULL,
  "menu_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "public"."pisces_auth_role_menu"."role_id" IS '角色ID';
COMMENT ON COLUMN "public"."pisces_auth_role_menu"."menu_id" IS '菜单ID';
COMMENT ON TABLE "public"."pisces_auth_role_menu" IS '角色菜单关系表';

-- ----------------------------
-- Records of pisces_auth_role_menu
-- ----------------------------
INSERT INTO "public"."pisces_auth_role_menu" VALUES (18, 2, 1);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (19, 2, 3);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (20, 2, 6);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (21, 2, 7);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (67, 1, 1);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (68, 1, 2);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (69, 1, 3);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (70, 1, 4);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (71, 1, 5);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (72, 1, 6);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (73, 1, 7);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (74, 1, 8);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (75, 1, 9);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (76, 1, 10);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (77, 1, 12);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (78, 1, 13);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (79, 1, 14);

-- ----------------------------
-- Table structure for pisces_auth_role_resource
-- ----------------------------
DROP TABLE IF EXISTS "public"."pisces_auth_role_resource";
CREATE TABLE "public"."pisces_auth_role_resource" (
  "id" int8 NOT NULL DEFAULT nextval('pisces_auth_role_resource_id_seq'::regclass),
  "role_id" int8 NOT NULL,
  "resource_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "public"."pisces_auth_role_resource"."role_id" IS '角色ID';
COMMENT ON COLUMN "public"."pisces_auth_role_resource"."resource_id" IS '资源ID';
COMMENT ON TABLE "public"."pisces_auth_role_resource" IS '角色资源关系表';

-- ----------------------------
-- Records of pisces_auth_role_resource
-- ----------------------------
INSERT INTO "public"."pisces_auth_role_resource" VALUES (73, 2, 1);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (74, 2, 21);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (75, 2, 22);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (76, 2, 9);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (598, 1, 1);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (599, 1, 2);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (600, 1, 3);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (601, 1, 4);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (602, 1, 5);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (603, 1, 6);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (604, 1, 7);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (605, 1, 9);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (606, 1, 10);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (607, 1, 11);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (608, 1, 12);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (609, 1, 14);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (610, 1, 15);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (611, 1, 16);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (612, 1, 17);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (613, 1, 18);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (614, 1, 19);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (615, 1, 20);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (616, 1, 21);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (617, 1, 22);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (618, 1, 23);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (619, 1, 24);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (620, 1, 25);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (621, 1, 26);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (622, 1, 27);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (623, 1, 28);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (624, 1, 29);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (625, 1, 30);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (626, 1, 32);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (627, 1, 33);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (628, 1, 34);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (629, 1, 35);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (630, 1, 36);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (631, 1, 37);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (632, 1, 40);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (633, 1, 45);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (634, 1, 46);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (635, 1, 47);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (636, 1, 48);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (637, 1, 49);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (638, 1, 50);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (639, 1, 51);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (640, 1, 52);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (641, 1, 53);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (642, 1, 54);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (643, 1, 55);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (644, 1, 56);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (645, 1, 57);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (646, 1, 58);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (647, 1, 61);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (648, 1, 62);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (649, 1, 63);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (650, 1, 64);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (651, 1, 65);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (652, 1, 68);

-- ----------------------------
-- Table structure for pisces_auth_user
-- ----------------------------
DROP TABLE IF EXISTS "public"."pisces_auth_user";
CREATE TABLE "public"."pisces_auth_user" (
  "id" int8 NOT NULL DEFAULT nextval('pisces_auth_user_id_seq'::regclass),
  "username" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "avatar" varchar(255) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "email" varchar(45) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "name" varchar(20) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "real_name" varchar(10) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "telephone" varchar(45) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "birthday" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "sex" int2,
  "remark" varchar(255) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "creator" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "updater" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "create_time" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "status" int2 NOT NULL DEFAULT 0,
  "del" int2 NOT NULL DEFAULT 1
)
;
COMMENT ON COLUMN "public"."pisces_auth_user"."username" IS '账号(用户名)';
COMMENT ON COLUMN "public"."pisces_auth_user"."password" IS '密码';
COMMENT ON COLUMN "public"."pisces_auth_user"."avatar" IS '头像(地址)';
COMMENT ON COLUMN "public"."pisces_auth_user"."email" IS '邮箱';
COMMENT ON COLUMN "public"."pisces_auth_user"."name" IS '昵称';
COMMENT ON COLUMN "public"."pisces_auth_user"."real_name" IS '真实姓名';
COMMENT ON COLUMN "public"."pisces_auth_user"."telephone" IS '手机';
COMMENT ON COLUMN "public"."pisces_auth_user"."birthday" IS '生日';
COMMENT ON COLUMN "public"."pisces_auth_user"."sex" IS '性别';
COMMENT ON COLUMN "public"."pisces_auth_user"."remark" IS '备注';
COMMENT ON COLUMN "public"."pisces_auth_user"."creator" IS '创建者';
COMMENT ON COLUMN "public"."pisces_auth_user"."updater" IS '更新者';
COMMENT ON COLUMN "public"."pisces_auth_user"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."pisces_auth_user"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."pisces_auth_user"."status" IS '帐号启用状态：0->禁用；1->启用';
COMMENT ON COLUMN "public"."pisces_auth_user"."del" IS '逻辑删除：0->删除状态；1->可用状态';
COMMENT ON TABLE "public"."pisces_auth_user" IS '用户表';

-- ----------------------------
-- Records of pisces_auth_user
-- ----------------------------
INSERT INTO "public"."pisces_auth_user" VALUES (1, 'admin', '{bcrypt}$2a$10$yftPnyoJe7a/psadq55CyO6dcw9VBjntigB.lXoM9hfPg6xLiawRK', 'https://besscroft.com/uploads/avatar.png', 'admin@qq.com', '管理员', '管你员', '12345678901', '2022-02-04 08:30:06', 1, '这个是管理员账号', 'administrator', 'administrator', '2022-02-04 08:30:06', '2022-02-04 08:30:06', 1, 1);
INSERT INTO "public"."pisces_auth_user" VALUES (2, 'test', '{bcrypt}$2a$10$yftPnyoJe7a/psadq55CyO6dcw9VBjntigB.lXoM9hfPg6xLiawRK', 'https://besscroft.com/uploads/avatar.png', 'test@qq.com', '测试员', '测试员', '12335584848', '2022-03-19 19:17:47', 0, '这个是测试员账号', 'administrator', 'administrator', '2022-03-19 19:17:47', '2022-03-19 19:17:47', 1, 1);
INSERT INTO "public"."pisces_auth_user" VALUES (6, 'test3', '{bcrypt}$2a$10$yftPnyoJe7a/psadq55CyO6dcw9VBjntigB.lXoM9hfPg6xLiawRK', 'https://besscroft.com/uploads/avatar.png', 'test3@qq.com', '瞅你咋地', '测试员', '12414', '2022-03-19 19:20:01', 1, '这个是测试员账号', 'administrator', 'administrator', '2022-03-19 19:20:01', '2022-03-19 19:20:01', 1, 0);
INSERT INTO "public"."pisces_auth_user" VALUES (5, 'test2', '{bcrypt}$2a$10$yftPnyoJe7a/psadq55CyO6dcw9VBjntigB.lXoM9hfPg6xLiawRK', 'https://besscroft.com/uploads/avatar.png', 'test2@qq.com', '你瞅啥', '测试员', '1231412', '2022-03-20 10:31:16', 1, '这个是测试员账号', 'administrator', 'admin', '2022-03-19 19:19:24', '2022-04-03 22:28:20', 1, 1);
INSERT INTO "public"."pisces_auth_user" VALUES (10, 'unitTest', '{bcrypt}$2a$10$4LQZj6AUm9pVoTfXxg9sNeeTD2bljF57gyr9Jr4yISFyWANJDr9ma', 'https://besscroft.com/uploads/avatar.png', 'unitTest@qq.com', '瞅你咋地', '瞅你咋地', '0', '2022-03-22 13:42:44', 1, '这是一条单元测试新增的数据', NULL, 'admin', '2022-03-22 13:42:45', '2022-05-08 14:38:02', 1, 1);
INSERT INTO "public"."pisces_auth_user" VALUES (11, 'zhangsan', '{bcrypt}$2a$10$DRP9lWqHg1ZVOq1fV3jh7uJD2BYjxzRzuwo25hdeaJ8MVJL5mVq1K', '1231212212112', '3211', '321', '321', '135468532', '2022-08-28 07:18:40', 1, 'dasdas', NULL, 'admin', '2022-08-28 15:18:59', '2022-08-28 15:19:05', 0, 0);

-- ----------------------------
-- Table structure for pisces_auth_user_depart
-- ----------------------------
DROP TABLE IF EXISTS "public"."pisces_auth_user_depart";
CREATE TABLE "public"."pisces_auth_user_depart" (
  "id" int8 NOT NULL DEFAULT nextval('pisces_auth_user_depart_id_seq'::regclass),
  "user_id" int8 NOT NULL,
  "depart_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "public"."pisces_auth_user_depart"."user_id" IS '用户ID';
COMMENT ON COLUMN "public"."pisces_auth_user_depart"."depart_id" IS '角色ID';
COMMENT ON TABLE "public"."pisces_auth_user_depart" IS '用户组织关系表';

-- ----------------------------
-- Records of pisces_auth_user_depart
-- ----------------------------

-- ----------------------------
-- Table structure for pisces_auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS "public"."pisces_auth_user_role";
CREATE TABLE "public"."pisces_auth_user_role" (
  "id" int8 NOT NULL DEFAULT nextval('pisces_auth_user_role_id_seq'::regclass),
  "user_id" int8 NOT NULL,
  "role_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "public"."pisces_auth_user_role"."user_id" IS '用户ID';
COMMENT ON COLUMN "public"."pisces_auth_user_role"."role_id" IS '角色ID';
COMMENT ON TABLE "public"."pisces_auth_user_role" IS '用户角色关系表';

-- ----------------------------
-- Records of pisces_auth_user_role
-- ----------------------------
INSERT INTO "public"."pisces_auth_user_role" VALUES (17, 2, 2);
INSERT INTO "public"."pisces_auth_user_role" VALUES (18, 10, 2);
INSERT INTO "public"."pisces_auth_user_role" VALUES (19, 1, 1);
INSERT INTO "public"."pisces_auth_user_role" VALUES (22, 5, 2);

-- ----------------------------
-- Table structure for pisces_sys_dict
-- ----------------------------
DROP TABLE IF EXISTS "public"."pisces_sys_dict";
CREATE TABLE "public"."pisces_sys_dict" (
  "id" int8 NOT NULL DEFAULT nextval('pisces_sys_dict_id_seq'::regclass),
  "group_name" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "key" varchar(64) COLLATE "pg_catalog"."default" NOT NULL,
  "value" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "creator" varchar(32) COLLATE "pg_catalog"."default",
  "updater" varchar(32) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "del" int2 NOT NULL DEFAULT 1,
  "remark" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "public"."pisces_sys_dict"."group_name" IS '字典分组名称';
COMMENT ON COLUMN "public"."pisces_sys_dict"."key" IS '字典 key';
COMMENT ON COLUMN "public"."pisces_sys_dict"."value" IS '字典值';
COMMENT ON COLUMN "public"."pisces_sys_dict"."creator" IS '创建者';
COMMENT ON COLUMN "public"."pisces_sys_dict"."updater" IS '更新者';
COMMENT ON COLUMN "public"."pisces_sys_dict"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."pisces_sys_dict"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."pisces_sys_dict"."del" IS '逻辑删除：0->删除状态；1->可用状态';
COMMENT ON COLUMN "public"."pisces_sys_dict"."remark" IS '备注';
COMMENT ON TABLE "public"."pisces_sys_dict" IS '系统字典';

-- ----------------------------
-- Records of pisces_sys_dict
-- ----------------------------
INSERT INTO "public"."pisces_sys_dict" VALUES (1, 'RESOURCE', 'admin', 'pisces-admin', 'admin', 'admin', '2022-08-05 14:13:22', '2022-08-05 14:13:25', 1, '资源—系统服务');
INSERT INTO "public"."pisces_sys_dict" VALUES (3, 'RESOURCE', 'auth', 'pisces-auth', 'admin', 'admin', '2022-08-05 14:16:09.510033', '2022-08-05 14:16:09.510033', 1, '资源—权限服务');
INSERT INTO "public"."pisces_sys_dict" VALUES (2, 'RESOURCE', 'file', 'pisces-file', 'admin', 'admin', '2022-08-05 14:14:48.557072', '2022-08-05 14:14:48.557072', 1, '资源—分布式文件服务');
INSERT INTO "public"."pisces_sys_dict" VALUES (4, 'TEST', '测试key', '测试字典value', NULL, NULL, '2022-08-28 13:37:27.673659', '2022-08-28 13:37:27.673659', 0, '备注');

-- ----------------------------
-- Table structure for pisces_sys_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."pisces_sys_log";
CREATE TABLE "public"."pisces_sys_log" (
  "id" int8 NOT NULL DEFAULT nextval('pisces_sys_log_id_seq'::regclass),
  "creator" varchar(32) COLLATE "pg_catalog"."default",
  "updater" varchar(32) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(6) DEFAULT CURRENT_TIMESTAMP
)
;
COMMENT ON COLUMN "public"."pisces_sys_log"."creator" IS '创建者';
COMMENT ON COLUMN "public"."pisces_sys_log"."updater" IS '更新者';
COMMENT ON COLUMN "public"."pisces_sys_log"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."pisces_sys_log"."update_time" IS '更新时间';
COMMENT ON TABLE "public"."pisces_sys_log" IS '系统日志表';

-- ----------------------------
-- Records of pisces_sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for pisces_sys_white
-- ----------------------------
DROP TABLE IF EXISTS "public"."pisces_sys_white";
CREATE TABLE "public"."pisces_sys_white" (
  "id" int8 NOT NULL DEFAULT nextval('pisces_sys_white_id_seq'::regclass),
  "title" varchar(32) COLLATE "pg_catalog"."default" NOT NULL,
  "path" varchar(100) COLLATE "pg_catalog"."default" NOT NULL,
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "creator" varchar(32) COLLATE "pg_catalog"."default",
  "updater" varchar(32) COLLATE "pg_catalog"."default",
  "create_time" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "status" int2 NOT NULL DEFAULT 1,
  "del" int2 NOT NULL DEFAULT 1
)
;
COMMENT ON COLUMN "public"."pisces_sys_white"."title" IS '白名单规则名称';
COMMENT ON COLUMN "public"."pisces_sys_white"."path" IS '白名单规则地址';
COMMENT ON COLUMN "public"."pisces_sys_white"."remark" IS '备注';
COMMENT ON COLUMN "public"."pisces_sys_white"."creator" IS '创建者';
COMMENT ON COLUMN "public"."pisces_sys_white"."updater" IS '更新者';
COMMENT ON COLUMN "public"."pisces_sys_white"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."pisces_sys_white"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."pisces_sys_white"."status" IS '白名单规则启用状态：0->禁用；1->启用';
COMMENT ON COLUMN "public"."pisces_sys_white"."del" IS '逻辑删除：0->删除状态；1->可用状态';
COMMENT ON TABLE "public"."pisces_sys_white" IS '白名单配置表';

-- ----------------------------
-- Records of pisces_sys_white
-- ----------------------------
INSERT INTO "public"."pisces_sys_white" VALUES (15, 'Actuator 监控路径', '/actuator/**', 'Pisces-Gateway Actuator 监控路径', 'admin', 'admin', '2022-05-28 18:54:24', '2022-05-28 18:54:24', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (12, 'Actuator 监控路径', '/pisces-admin/actuator/**', 'Pisces-Admin Actuator 监控路径', 'admin', 'admin', '2022-05-28 18:44:39', '2022-05-28 18:44:39', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (14, 'Actuator 监控路径', '/pisces-auth/actuator/**', 'Pisces-Auth Actuator 监控路径', 'admin', 'admin', '2022-05-28 18:54:09', '2022-05-28 18:54:09', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (21, '网关 swagger 页面', '/swagger-ui.html', '网关 swagger 页面', 'admin', 'admin', '2022-06-03 20:19:23', '2022-06-03 20:19:23', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (20, '静态资源', '/**/*.ico', '静态资源', 'admin', 'admin', '2022-06-03 20:18:29', '2022-06-03 20:18:29', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (1, '退出登录', '/pisces-admin/user/logout', '退出登录接口白名单处理', 'admin', 'admin', '2022-05-14 20:13:58', '2022-05-14 20:13:58', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (11, '公钥获取接口', '/pisces-auth/publicKey/get', '公钥获取接口', 'admin', 'admin', '2022-05-17 17:10:00', '2022-05-21 22:49:45', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (7, '登录', '/pisces-admin/user/login', '登录接口', 'admin', 'admin', '2022-05-17 17:09:23', '2022-05-21 22:59:13', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (25, 'openapi', '/v3/**', 'openapi', 'admin', 'admin', '2022-06-03 20:59:11', '2022-06-03 20:59:11', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (24, '静态资源', '/**/*.js', '静态资源', 'admin', 'admin', '2022-06-03 20:58:53', '2022-06-03 20:58:53', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (23, '静态资源', '/**/*.css', '静态资源', 'admin', 'admin', '2022-06-03 20:58:43', '2022-06-03 20:58:43', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (22, '静态资源', '/**/*.png', '静态资源', 'admin', 'admin', '2022-06-03 20:58:31', '2022-06-03 20:58:31', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (26, 'swagger 资源', '/webjars/**', 'swagger 资源', 'admin', 'admin', '2022-06-03 21:00:01', '2022-06-03 21:00:01', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (19, 'Auth 服务 SpringDoc', '/pisces-auth/v3/api-docs', 'Auth 服务 SpringDoc 白名单', 'admin', 'admin', '2022-06-03 20:16:38', '2022-06-03 20:16:38', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (16, 'SpringBoot Admin 监控路径', '/pisces-monitor/**', 'SpringBoot Admin 监控路径', 'admin', 'admin', '2022-05-29 14:40:46', '2022-05-29 14:40:46', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (17, '网关 SpringDoc 白名单', '/v3/api-docs', '网关 SpringDoc 白名单', 'admin', 'admin', '2022-06-03 20:15:53', '2022-06-03 20:15:53', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (18, 'Admin 服务 SpringDoc', '/pisces-admin/v3/api-docs', 'Admin 服务 SpringDoc 白名单', 'admin', 'admin', '2022-06-03 20:16:16', '2022-06-03 20:16:16', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (27, '测试1', '测试', '测试', 'admin', 'admin', '2022-08-28 15:40:54', '2022-08-28 15:42:46', 1, 0);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_depart_id_seq"
OWNED BY "public"."pisces_auth_depart"."id";
SELECT setval('"public"."pisces_auth_depart_id_seq"', 8, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_menu_id_seq"
OWNED BY "public"."pisces_auth_menu"."id";
SELECT setval('"public"."pisces_auth_menu_id_seq"', 15, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_resource_category_id_seq"
OWNED BY "public"."pisces_auth_resource_category"."id";
SELECT setval('"public"."pisces_auth_resource_category_id_seq"', 13, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_resource_id_seq"
OWNED BY "public"."pisces_auth_resource"."id";
SELECT setval('"public"."pisces_auth_resource_id_seq"', 68, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_role_id_seq"
OWNED BY "public"."pisces_auth_role"."id";
SELECT setval('"public"."pisces_auth_role_id_seq"', 5, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_role_menu_id_seq"
OWNED BY "public"."pisces_auth_role_menu"."id";
SELECT setval('"public"."pisces_auth_role_menu_id_seq"', 79, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_role_resource_id_seq"
OWNED BY "public"."pisces_auth_role_resource"."id";
SELECT setval('"public"."pisces_auth_role_resource_id_seq"', 652, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_user_depart_id_seq"
OWNED BY "public"."pisces_auth_user_depart"."id";
SELECT setval('"public"."pisces_auth_user_depart_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_user_id_seq"
OWNED BY "public"."pisces_auth_user"."id";
SELECT setval('"public"."pisces_auth_user_id_seq"', 11, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_user_role_id_seq"
OWNED BY "public"."pisces_auth_user_role"."id";
SELECT setval('"public"."pisces_auth_user_role_id_seq"', 22, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_sys_dict_id_seq"
OWNED BY "public"."pisces_sys_dict"."id";
SELECT setval('"public"."pisces_sys_dict_id_seq"', 4, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_sys_log_id_seq"
OWNED BY "public"."pisces_sys_log"."id";
SELECT setval('"public"."pisces_sys_log_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."pisces_sys_white_id_seq"', 27, true);

-- ----------------------------
-- Primary Key structure for table pisces_auth_depart
-- ----------------------------
ALTER TABLE "public"."pisces_auth_depart" ADD CONSTRAINT "pisces_auth_depart_pk" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table pisces_auth_menu
-- ----------------------------
ALTER TABLE "public"."pisces_auth_menu" ADD CONSTRAINT "pisces_auth_menu_pk" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table pisces_auth_resource
-- ----------------------------
ALTER TABLE "public"."pisces_auth_resource" ADD CONSTRAINT "pisces_auth_resource_pk" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table pisces_auth_resource_category
-- ----------------------------
ALTER TABLE "public"."pisces_auth_resource_category" ADD CONSTRAINT "pisces_auth_resource_category_pk" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table pisces_auth_role
-- ----------------------------
CREATE UNIQUE INDEX "idx_pisces_auth_role_role_code" ON "public"."pisces_auth_role" USING btree (
  "role_code" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table pisces_auth_role
-- ----------------------------
ALTER TABLE "public"."pisces_auth_role" ADD CONSTRAINT "pisces_auth_role_pk" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table pisces_auth_role_menu
-- ----------------------------
CREATE INDEX "idx_pisces_role_menu_role_id" ON "public"."pisces_auth_role_menu" USING btree (
  "role_id" "pg_catalog"."int8_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table pisces_auth_role_menu
-- ----------------------------
ALTER TABLE "public"."pisces_auth_role_menu" ADD CONSTRAINT "pisces_auth_role_menu_pk" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table pisces_auth_role_resource
-- ----------------------------
CREATE INDEX "idx_pisces_role_resource_role_id" ON "public"."pisces_auth_role_resource" USING btree (
  "role_id" "pg_catalog"."int8_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table pisces_auth_role_resource
-- ----------------------------
ALTER TABLE "public"."pisces_auth_role_resource" ADD CONSTRAINT "pisces_auth_role_resource_pk" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table pisces_auth_user
-- ----------------------------
CREATE UNIQUE INDEX "idx_pisces_auth_user_username" ON "public"."pisces_auth_user" USING btree (
  "username" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);
COMMENT ON INDEX "public"."idx_pisces_auth_user_username" IS '用户名唯一索引';

-- ----------------------------
-- Primary Key structure for table pisces_auth_user
-- ----------------------------
ALTER TABLE "public"."pisces_auth_user" ADD CONSTRAINT "pisces_auth_user_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table pisces_auth_user_depart
-- ----------------------------
CREATE INDEX "idx_pisces_user_depart_user_id" ON "public"."pisces_auth_user_depart" USING btree (
  "user_id" "pg_catalog"."int8_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table pisces_auth_user_depart
-- ----------------------------
ALTER TABLE "public"."pisces_auth_user_depart" ADD CONSTRAINT "pisces_auth_user_depart_pk" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table pisces_auth_user_role
-- ----------------------------
CREATE INDEX "idx_pisces_user_role_user_id" ON "public"."pisces_auth_user_role" USING btree (
  "user_id" "pg_catalog"."int8_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table pisces_auth_user_role
-- ----------------------------
ALTER TABLE "public"."pisces_auth_user_role" ADD CONSTRAINT "pisces_auth_user_role_pk" PRIMARY KEY ("id");

-- ----------------------------
-- Indexes structure for table pisces_sys_dict
-- ----------------------------
CREATE UNIQUE INDEX "pisces_sys_dict_key_uindex" ON "public"."pisces_sys_dict" USING btree (
  "key" COLLATE "pg_catalog"."default" "pg_catalog"."text_ops" ASC NULLS LAST
);

-- ----------------------------
-- Primary Key structure for table pisces_sys_dict
-- ----------------------------
ALTER TABLE "public"."pisces_sys_dict" ADD CONSTRAINT "pisces_sys_dict_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table pisces_sys_log
-- ----------------------------
ALTER TABLE "public"."pisces_sys_log" ADD CONSTRAINT "pisces_sys_log_pk" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table pisces_sys_white
-- ----------------------------
ALTER TABLE "public"."pisces_sys_white" ADD CONSTRAINT "pisces_sys_white_pkey" PRIMARY KEY ("id");
