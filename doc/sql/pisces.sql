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

 Date: 13/11/2022 21:34:14
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
INSERT INTO "public"."pisces_auth_depart" VALUES (6, 1, 'gfd', '', 2, 'admin', 'admin', '2022-04-30 17:55:55', '2022-04-30 17:56:17', 0);
INSERT INTO "public"."pisces_auth_depart" VALUES (8, 1, '测试1', '', 2, 'admin', 'admin', '2022-08-28 15:40:32', '2022-08-28 15:40:32', 0);
INSERT INTO "public"."pisces_auth_depart" VALUES (7, 3, '测试', '测试', 2, 'admin', 'admin', '2022-08-28 15:40:18', '2022-08-28 15:40:18', 0);
INSERT INTO "public"."pisces_auth_depart" VALUES (3, 1, '设计部', '设计部门', 3, 'admin', 'admin', '2022-03-24 18:17:01', '2022-11-02 21:18:54', 1);

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
  "redirect" varchar(255) COLLATE "pg_catalog"."default",
  "path" varchar(200) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "icon" varchar(64) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "is_link" varchar(200) COLLATE "pg_catalog"."default" NOT NULL,
  "is_hide" int2 NOT NULL DEFAULT 0,
  "is_full" int2 NOT NULL DEFAULT 0,
  "is_affix" int2 NOT NULL DEFAULT 0,
  "is_keep_alive" int2 NOT NULL DEFAULT 0,
  "sort" int4 DEFAULT 0,
  "creator" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "updater" varchar(32) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "create_time" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "update_time" timestamp(0) DEFAULT CURRENT_TIMESTAMP,
  "del" int2 NOT NULL DEFAULT 1
)
;
COMMENT ON COLUMN "public"."pisces_auth_menu"."parent_id" IS '父级ID';
COMMENT ON COLUMN "public"."pisces_auth_menu"."title" IS '菜单名称';
COMMENT ON COLUMN "public"."pisces_auth_menu"."name" IS '前端名称';
COMMENT ON COLUMN "public"."pisces_auth_menu"."parent_title" IS '父菜单名称';
COMMENT ON COLUMN "public"."pisces_auth_menu"."level" IS '菜单级数';
COMMENT ON COLUMN "public"."pisces_auth_menu"."component" IS '组件路径';
COMMENT ON COLUMN "public"."pisces_auth_menu"."redirect" IS '重定向地址';
COMMENT ON COLUMN "public"."pisces_auth_menu"."path" IS '路由地址';
COMMENT ON COLUMN "public"."pisces_auth_menu"."icon" IS '菜单图标名称';
COMMENT ON COLUMN "public"."pisces_auth_menu"."is_link" IS '是否外链：0->否；1->是';
COMMENT ON COLUMN "public"."pisces_auth_menu"."is_hide" IS '菜单显示状态：0->禁用；1->启用';
COMMENT ON COLUMN "public"."pisces_auth_menu"."is_full" IS '是否全屏：0->否；1->是';
COMMENT ON COLUMN "public"."pisces_auth_menu"."is_affix" IS '是否固定在 tabs nav：0->否；1->是';
COMMENT ON COLUMN "public"."pisces_auth_menu"."is_keep_alive" IS '是否缓存：0->否；1->是';
COMMENT ON COLUMN "public"."pisces_auth_menu"."sort" IS '排序';
COMMENT ON COLUMN "public"."pisces_auth_menu"."creator" IS '创建者';
COMMENT ON COLUMN "public"."pisces_auth_menu"."updater" IS '更新者';
COMMENT ON COLUMN "public"."pisces_auth_menu"."create_time" IS '创建时间';
COMMENT ON COLUMN "public"."pisces_auth_menu"."update_time" IS '更新时间';
COMMENT ON COLUMN "public"."pisces_auth_menu"."del" IS '逻辑删除：0->删除状态；1->可用状态';
COMMENT ON TABLE "public"."pisces_auth_menu" IS '菜单表';

-- ----------------------------
-- Records of pisces_auth_menu
-- ----------------------------
INSERT INTO "public"."pisces_auth_menu" VALUES (43, 41, '菜单2-2', 'menu22', '菜单2', 2, NULL, '/menu2/menu22/menu221', '/menu2/menu22', 'Menu', '', 1, 0, 0, 1, 28, 'admin', 'admin', '2022-10-31 18:07:08', '2022-10-31 18:07:08', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (44, 43, '菜单2-21', 'menu221', '菜单2-2', 3, '/menu/menu2/menu22/menu221/index', NULL, '/menu2/menu22/menu221', 'Menu', '', 1, 0, 0, 1, 29, 'admin', 'admin', '2022-10-31 18:07:08', '2022-10-31 18:07:08', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (45, 43, '菜单2-22', 'menu222', '菜单2-2', 3, '/menu/menu2/menu22/menu222/index', NULL, '/menu2/menu22/menu222', 'Menu', '', 1, 0, 0, 1, 30, 'admin', 'admin', '2022-10-31 18:07:08', '2022-10-31 18:07:08', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (1, 0, '首页', 'home', NULL, 0, '/home/index', NULL, '/home/index', 'HomeFilled', '', 1, 0, 1, 1, 2, 'admin', 'admin', '2022-10-31 16:39:50', '2022-10-31 16:39:50', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (8, 0, 'Dashboard', 'dashboard', NULL, 1, NULL, '/dashboard/dataVisualize', '/dashboard', 'Odometer', '', 1, 0, 0, 1, 8, 'admin', 'admin', '2022-10-31 16:45:34', '2022-10-31 16:45:34', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (10, 8, '内嵌页面', 'embedded', 'Dashboard', 1, '/dashboard/embedded/index', NULL, '/dashboard/embedded', 'Menu', '', 1, 0, 0, 1, 3, 'admin', 'admin', '2022-10-31 16:46:44', '2022-10-31 16:46:44', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (39, 0, '菜单嵌套', 'menuRoot', NULL, 0, NULL, '/menu1', '/menuRoot', 'List', '', 1, 0, 0, 1, 23, 'admin', 'admin', '2022-10-31 17:27:48', '2022-10-31 17:27:48', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (60, 0, '权限管理', 'auth', NULL, 0, NULL, '/user', '/auth', 'Promotion', '', 1, 0, 0, 1, 3, 'admin', 'admin', '2022-11-02 17:36:40', '2022-11-02 17:36:40', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (40, 39, '菜单1', 'menu1', '菜单嵌套', 1, '/menu/menu1/index', NULL, '/menu1', 'Menu', '', 1, 0, 0, 1, 25, 'admin', 'admin', '2022-10-31 17:28:27', '2022-10-31 17:28:27', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (41, 39, '菜单2', 'menu2', '菜单嵌套', 1, NULL, '/menu2/menu21', '/menu2', 'Menu', '', 1, 0, 0, 1, 26, 'admin', 'admin', '2022-10-31 18:05:39', '2022-10-31 18:05:39', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (42, 41, '菜单2-1', 'menu21', '菜单2', 2, '/menu/menu2/menu21/index', NULL, '/menu2/menu21', 'Menu', '', 1, 0, 0, 1, 27, 'admin', 'admin', '2022-10-31 18:06:10', '2022-10-31 18:06:10', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (65, 60, '用户管理', 'authUser', '权限管理', 1, '/auth/user/index', NULL, '/user', 'User', '', 1, 0, 0, 1, 4, 'admin', 'admin', '2022-11-02 17:55:45', '2022-11-02 17:55:45', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (67, 60, '角色管理', 'role', '权限管理', 1, '/auth/role/index', NULL, '/role', 'UserFilled', '', 1, 0, 0, 1, 5, 'admin', 'admin', '2022-11-02 17:57:23', '2022-11-02 17:57:23', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (68, 60, '菜单管理', 'menu', '权限管理', 1, '/auth/menu/index', NULL, '/menu', 'Menu', '', 1, 0, 0, 1, 6, 'admin', 'admin', '2022-11-02 17:58:00', '2022-11-02 17:58:00', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (76, 74, '字典管理', 'dict', '系统管理', 1, '/system/dict/index', NULL, '/dict', 'MessageBox', '', 1, 0, 0, 1, 6, 'admin', 'admin', '2022-11-02 19:22:05', '2022-11-02 19:22:05', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (46, 41, '菜单2-3', 'menu23', '菜单2', 2, '/menu/menu2/menu23/index', NULL, '/menu2/menu23', 'Menu', '', 1, 0, 0, 1, 31, 'admin', 'admin', '2022-10-31 18:09:06', '2022-10-31 18:09:06', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (47, 39, '菜单3', 'menu3', '菜单嵌套', 1, '/menu/menu3/index', NULL, '/menu3', 'Menu', '', 1, 0, 0, 1, 32, 'admin', 'admin', '2022-10-31 18:09:35', '2022-10-31 18:09:35', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (74, 0, '系统管理', 'system', NULL, 0, NULL, '/whiteList', '/system', 'SetUp', '', 1, 0, 0, 1, 4, 'admin', 'admin', '2022-11-02 19:20:48', '2022-11-02 19:20:48', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (77, 0, '组件', 'modules', NULL, 0, NULL, '/alioss', '/modules', 'MessageBox', '', 1, 0, 0, 1, 5, 'admin', 'admin', '2022-11-02 19:22:43', '2022-11-02 19:22:43', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (48, 0, '外部链接', 'link', NULL, 0, NULL, '/link/github', '/link', 'Histogram', '', 1, 0, 0, 1, 33, 'admin', 'admin', '2022-10-31 18:18:33', '2022-10-31 18:18:33', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (49, 48, 'GitHub 仓库', 'github', NULL, 1, '/link/github/index', NULL, '/link/github', 'Histogram', 'https://github.com/besscroft/pisces-cloud', 1, 0, 0, 1, 34, 'admin', 'admin', '2022-10-31 18:19:02', '2022-10-31 18:19:02', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (69, 60, '资源类别管理', 'resourceCategory', '权限管理', 1, '/auth/resourceCategory/index', NULL, '/resourceCategory', 'Briefcase', '', 1, 0, 0, 1, 7, 'admin', 'admin', '2022-11-02 19:18:35', '2022-11-02 19:18:35', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (71, 60, '资源管理', 'resource', '权限管理', 1, '/auth/resource/index', NULL, '/resource', 'Finished', '', 1, 0, 0, 1, 8, 'admin', 'admin', '2022-11-02 19:19:17', '2022-11-02 19:19:17', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (72, 60, '部门管理', 'depart', '权限管理', 1, '/auth/depart/index', NULL, '/depart', 'Briefcase', '', 1, 0, 0, 1, 9, 'admin', 'admin', '2022-11-02 19:19:50', '2022-11-02 19:19:50', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (75, 74, '白名单管理', 'whiteList', '系统管理', 1, '/system/whiteList/index', NULL, '/whiteList', 'CircleCheckFilled', '', 1, 0, 0, 1, 5, 'admin', 'admin', '2022-11-02 19:21:31', '2022-11-02 19:21:31', 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (78, 77, 'OSS 管理', 'alioss', '组件', 1, '/modules/alioss/index', NULL, '/alioss', 'MessageBox', '', 1, 0, 0, 1, 6, 'admin', 'admin', '2022-11-02 19:23:23', '2022-11-02 19:23:23', 1);

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
INSERT INTO "public"."pisces_auth_resource" VALUES (2, '用户列表', '/user/list', '用户信息列表（分页）', 1, 2, 'admin', 'admin', '2022-03-13 16:48:42', '2022-11-02 21:14:38', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (1, '用户权限信息', '/user/info', '用户信息接口，需要认证！获取当前登录用户信息', 1, 1, 'admin', 'admin', '2022-03-04 21:25:12', '2022-03-04 21:25:14', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (4, '用户信息更新', '/user/update', '用户信息更新接口，需要认证！', 1, 4, 'admin', 'admin', '2022-03-13 19:42:56', '2022-03-13 19:42:58', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (5, '用户可用状态更新', '/user/change', '更改用户可用状态接口', 1, 5, 'admin', 'admin', '2022-03-20 11:44:45', '2022-03-20 11:44:47', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (6, '新增用户', '/user/add', '新增用户接口', 1, 6, 'admin', 'admin', '2022-03-20 14:54:51', '2022-03-20 14:54:51', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (7, '更新用户信息', '/user/update', '更新用户信息接口', 1, 7, 'admin', 'admin', '2022-03-20 16:50:25', '2022-03-20 16:50:25', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (21, '根据角色id查询菜单id列表', '/menu/getId/role/{roleId:[\d]+}', '根据角色id查询菜单id列表', 3, 5, 'admin', 'admin', '2022-04-02 21:56:57', '2022-10-28 10:26:36', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (10, '角色列表', '/role/list', '角色列表接口（分页）', 2, 1, 'admin', 'admin', '2022-03-20 19:27:39', '2022-03-20 19:27:42', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (12, '退出登录', '/user/loginOut', '退出登录接口', 1, 9, 'admin', 'admin', '2022-03-20 20:49:33', '2022-03-20 20:49:35', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (14, '菜单列表', '/menu/list', '菜单列表接口（分页）', 3, 1, 'admin', 'admin', '2022-03-24 14:53:49', '2022-03-24 14:53:51', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (15, '菜单可用状态更新', '/menu/change', '更改菜单可用状态接口', 3, 2, 'admin', 'admin', '2022-03-24 15:20:31', '2022-03-24 15:20:33', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (27, '角色删除接口', '/role/delete/{id:[\d]+}', '角色删除接口', 2, 5, 'admin', 'admin', '2022-04-03 20:22:36', '2022-10-28 10:24:46', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (17, '更新菜单', '/menu/update', '更新菜单信息接口', 3, 4, 'admin', 'admin', '2022-03-24 16:08:06', '2022-03-24 16:08:07', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (18, '资源列表', '/resource/list', '资源列表接口（分页）', 5, 1, 'admin', 'admin', '2022-03-24 17:44:40', '2022-03-24 17:44:42', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (19, '资源类别列表', '/resource/category/list', '资源类别列表接口（分页）', 4, 1, 'admin', 'admin', '2022-03-24 17:45:22', '2022-03-24 17:45:24', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (20, '组织/部门列表', '/depart/list', '组织/部门列表接口（分页）', 6, 1, 'admin', 'admin', '2022-03-24 17:46:04', '2022-03-24 17:46:06', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (22, '获取所有菜单接口', '/menu/getAll', '获取所有菜单接口', 3, 6, 'admin', 'admin', '2022-04-02 22:09:40', '2022-04-02 22:09:40', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (25, '根据角色id查询资源id列表接口', '/resource/getId/role/{roleId:[\d]+}', '根据角色id查询资源id列表接口', 4, 3, 'admin', 'admin', '2022-04-03 12:10:47', '2022-10-28 10:27:28', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (23, '更改角色菜单接口', '/role/update/menu', '更改角色菜单接口', 2, 3, 'admin', 'admin', '2022-04-03 10:57:05', '2022-04-03 10:57:05', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (24, '获取资源树接口', '/resource/getAll', '获取资源树接口', 4, 2, 'admin', 'admin', '2022-04-03 11:26:00', '2022-04-03 11:26:00', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (35, '资源删除接口', '/resource/delete/{id:[\d]+}', '资源删除接口', 5, 2, 'admin', 'admin', '2022-04-26 21:16:16', '2022-10-28 10:27:48', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (26, '更改角色菜单接口', '/role/update/resource', '更改角色菜单接口', 2, 4, 'admin', 'admin', '2022-04-03 20:08:54', '2022-04-03 20:08:54', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (34, '组织/部门删除接口', '/depart/delete/{id:[\d]+}', '组织/部门删除接口', 6, 2, 'admin', 'admin', '2022-04-26 20:58:43', '2022-10-28 10:24:58', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (29, '更新角色接口', '/role/update', '更新角色接口', 2, 7, 'admin', 'admin', '2022-04-03 21:36:42', '2022-04-03 21:36:42', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (28, '角色新增接口', '/role/add', '角色新增接口', 2, 6, 'admin', 'admin', '2022-04-03 21:36:23', '2022-04-03 21:36:23', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (30, '角色字典接口', '/role/getRoleDict', '角色字典接口', 2, 8, 'admin', 'admin', '2022-04-10 18:46:03', '2022-04-10 18:46:03', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (32, '更新用户角色接口', '/user/update/role', '更新用户角色接口', 2, 9, 'admin', 'admin', '2022-04-10 21:15:24', '2022-04-10 21:15:24', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (56, '删除白名单接口', '/white/delete/{id:[\d]+}', '删除白名单接口', 7, 4, 'admin', 'admin', '2022-05-14 21:29:28', '2022-10-28 10:25:11', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (16, '删除菜单', '/menu/delete/{menuId:[\d]+}', '根据菜单id删除菜单接口', 3, 3, 'admin', 'admin', '2022-03-24 15:35:58', '2022-10-28 10:26:00', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (9, '删除用户', '/user/delete/{userId:[\d]+}', '根据用户id删除用户接口', 1, 8, 'admin', 'admin', '2022-03-20 16:50:59', '2022-10-28 10:29:28', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (11, '角色可用状态更新', '/role/change', '更改角色可用状态接口', 2, 2, 'admin', 'admin', '2022-03-20 20:06:33', '2022-03-20 20:06:34', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (36, '新增资源接口', '/resource/add', '新增资源接口', 5, 3, 'admin', 'admin', '2022-04-30 06:41:04', '2022-04-30 06:41:04', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (37, '更新资源接口', '/resource/update', '更新资源接口', 5, 4, 'admin', 'admin', '2022-04-30 06:58:26', '2022-04-30 06:58:26', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (40, '资源类别查询接口', '/resource/category/getResourceCategoryDict', '资源类别查询接口', 4, 4, 'admin', 'admin', '2022-04-30 07:53:28', '2022-04-30 07:53:28', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (51, '新增资源类别接口', '/resource/category/add', '新增资源类别接口', 4, 5, 'admin', 'admin', '2022-05-08 15:02:07', '2022-05-08 15:02:07', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (52, '更新资源类别接口', '/resource/category/update', '更新资源类别接口', 4, 6, 'admin', 'admin', '2022-05-08 15:02:41', '2022-05-08 15:02:41', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (45, '新增组织/部门接口', '/depart/add', '新增组织/部门接口', 6, 3, 'admin', 'admin', '2022-04-30 08:59:01', '2022-04-30 08:59:01', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (46, '更新组织/部门接口', '/depart/update', '更新组织/部门接口', 6, 4, 'admin', 'admin', '2022-04-30 08:59:01', '2022-04-30 08:59:01', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (47, '部门字典接口', '/depart/getDepartDict', '部门字典接口', 6, 5, 'admin', 'admin', '2022-04-30 09:46:45', '2022-04-30 09:46:45', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (49, '新增菜单接口', '/menu/add', '新增菜单接口', 3, 6, 'admin', 'admin', '2022-05-04 18:27:56', '2022-05-04 18:27:56', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (50, '菜单字典接口', '/menu/getMenuDict', '菜单字典接口', 3, 7, 'admin', 'admin', '2022-05-04 18:51:53', '2022-05-04 18:51:53', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (53, '白名单列表接口', '/white/list', '白名单列表接口', 7, 1, 'admin', 'admin', '2022-05-14 18:37:03', '2022-05-14 18:37:03', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (54, '新增白名单接口', '/white/add', '新增白名单接口', 7, 2, 'admin', 'admin', '2022-05-14 21:28:57', '2022-05-14 21:28:57', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (3, '用户信息', '/user/info/**', '用户信息接口', 1, 3, 'admin', 'admin', '2022-03-13 19:41:51', '2022-05-21 22:59:30', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (55, '更新白名单接口', '/white/update', '更新白名单接口', 7, 3, 'admin', 'admin', '2022-05-14 21:29:09', '2022-05-14 21:29:09', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (57, '获取白名单字典', '/white/getWhiteDict', '获取白名单字典', 7, 5, 'admin', 'admin', '2022-05-14 22:20:02', '2022-05-14 22:20:02', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (58, '阿里 OSS 文件上传', '/aliyun/oss/upload', '阿里云 OSS 文件上传', 11, 1, 'admin', 'admin', '2022-08-05 13:59:03', '2022-08-05 13:59:03', 1, 'file');
INSERT INTO "public"."pisces_auth_resource" VALUES (61, '字典分页', '/dict/list', '字典管理分页接口', 12, 2, 'admin', 'admin', '2022-08-24 17:11:49', '2022-08-24 17:11:49', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (62, '字典下拉框', '/dict/groupQuery', '查询分组内所有字典接口', 12, 1, 'admin', 'admin', '2022-08-24 17:15:22', '2022-08-24 17:15:22', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (63, '新增字典', '/dict/add', '新增字典接口', 12, 3, 'admin', 'admin', '2022-08-28 13:35:17', '2022-08-28 13:35:17', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (64, '更新字典', '/dict/update', '更新字典成功', 12, 4, 'admin', 'admin', '2022-08-28 13:35:51', '2022-08-28 13:35:51', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (68, '阿里 OSS 文件上传', '/aliyun/oss/uploadCdn', '阿里 OSS 文件上传 CDN', 11, 2, 'admin', 'admin', '2022-09-20 20:50:43', '2022-09-20 20:50:43', 1, 'file');
INSERT INTO "public"."pisces_auth_resource" VALUES (70, '获取部门树', '/depart/getUserDepartList', '部门树接口', 6, 6, 'admin', 'admin', '2022-10-14 18:15:00', '2022-10-14 18:15:23', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (71, '更新用户部门', '/user/update/depart', '更新用户部门接口', 1, 7, 'admin', 'admin', '2022-10-15 12:59:23', '2022-10-15 12:59:23', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (48, '获取角色信息接口', '/role/get/{id:[\d]+}', '根据角色 id 获取角色信息接口', 2, 3, 'admin', 'admin', '2022-04-30 13:23:20', '2022-10-28 09:50:49', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (33, '资源类别删除接口', '/resource/category/delete/{id:[\d]+}', '资源类别删除接口', 4, 2, 'admin', 'admin', '2022-04-26 20:40:31', '2022-10-28 10:24:52', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (65, '删除字典', '/dict/delete/{id:[\d]+}', '删除字典接口（软删除）', 12, 6, 'admin', 'admin', '2022-08-28 13:43:19', '2022-10-28 10:25:03', 1, 'admin');
INSERT INTO "public"."pisces_auth_resource" VALUES (72, '密码修改', '/user/update/password', '密码修改接口', 1, 8, 'admin', 'admin', '2022-11-13 20:32:02', '2022-11-13 20:34:36', 1, 'admin');

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
INSERT INTO "public"."pisces_auth_resource_category" VALUES (3, '菜单管理', '菜单管理', 3, 'admin', 'admin', '2022-03-24 14:54:21', '2022-03-24 14:54:23', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (4, '资源类别管理', '资源类别管理', 4, 'admin', 'admin', '2022-03-24 14:54:44', '2022-03-24 14:54:46', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (5, '资源管理', '资源管理', 5, 'admin', 'admin', '2022-03-24 14:55:02', '2022-03-24 14:55:04', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (6, '部门管理', '部门管理', 6, 'admin', 'admin', '2022-03-24 14:55:19', '2022-03-24 14:55:22', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (7, '白名单管理', '白名单管理', 7, 'admin', 'admin', '2022-04-26 21:27:30', '2022-04-26 21:27:30', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (11, '文件中心', '分布式文件中心接口', 8, 'admin', 'admin', '2022-08-05 13:58:23', '2022-08-05 13:58:23', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (12, '字典管理', '系统字典管理资源', 9, 'admin', 'admin', '2022-08-24 17:06:51', '2022-08-24 17:06:51', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (13, 'ces', 'ces', 0, 'admin', 'admin', '2022-08-28 15:23:05', '2022-08-28 15:27:20', 0);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (2, '角色管理', '角色管理', 2, 'admin', 'admin', '2022-03-20 19:28:04', '2022-11-02 21:14:14', 1);

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
INSERT INTO "public"."pisces_auth_role" VALUES (3, 'ces', 'ces', 'ss1111s', 2, 'admin', 'admin', '2022-08-28 15:22:06', '2022-08-28 15:22:19', 1, 0);
INSERT INTO "public"."pisces_auth_role" VALUES (4, '3112', '32131', '31', 2, 'admin', NULL, '2022-08-28 17:05:54', '2022-08-28 17:05:54', 0, 0);
INSERT INTO "public"."pisces_auth_role" VALUES (5, '测试', '测试', '测试', 2, 'admin', NULL, '2022-08-28 17:08:17', '2022-08-28 17:08:17', 1, 0);
INSERT INTO "public"."pisces_auth_role" VALUES (2, '测试员', 'test', '测试专用角色', 1, 'admin', 'admin', '2022-03-04 23:32:26', '2022-03-04 23:32:27', 1, 1);
INSERT INTO "public"."pisces_auth_role" VALUES (1, '超级管理员', 'administrator', '超级管理员，拥有所有的权限', 0, 'administrator', 'admin', '2022-02-04 08:28:07', '2022-11-02 21:13:45', 1, 1);

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
INSERT INTO "public"."pisces_auth_role_menu" VALUES (275, 2, 1);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (276, 2, 65);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (277, 2, 67);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (278, 2, 68);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (279, 2, 69);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (280, 2, 39);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (281, 2, 71);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (282, 2, 8);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (283, 2, 40);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (284, 2, 72);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (285, 2, 41);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (286, 2, 10);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (287, 2, 42);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (288, 2, 43);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (289, 2, 44);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (290, 2, 45);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (291, 2, 46);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (292, 2, 47);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (293, 2, 48);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (294, 2, 49);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (295, 2, 60);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (248, 1, 1);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (249, 1, 65);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (250, 1, 67);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (251, 1, 68);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (252, 1, 69);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (253, 1, 71);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (254, 1, 8);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (255, 1, 72);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (257, 1, 10);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (258, 1, 74);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (259, 1, 75);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (260, 1, 76);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (261, 1, 77);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (262, 1, 78);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (263, 1, 39);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (264, 1, 40);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (265, 1, 41);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (266, 1, 42);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (267, 1, 43);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (268, 1, 44);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (269, 1, 45);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (270, 1, 46);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (271, 1, 47);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (272, 1, 48);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (273, 1, 49);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (274, 1, 60);

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
INSERT INTO "public"."pisces_auth_role_resource" VALUES (779, 2, 1);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (780, 2, 2);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (781, 2, 3);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (782, 2, 70);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (783, 2, 10);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (784, 2, 12);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (785, 2, 14);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (786, 2, 48);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (787, 2, 18);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (788, 2, 19);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (789, 2, 20);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (790, 2, 21);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (791, 2, 53);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (792, 2, 22);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (793, 2, 24);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (794, 2, 25);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (795, 2, 57);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (796, 2, 61);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (797, 2, 30);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (798, 2, 62);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (857, 1, 1);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (858, 1, 2);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (859, 1, 3);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (860, 1, 4);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (861, 1, 5);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (862, 1, 6);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (863, 1, 7);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (864, 1, 9);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (865, 1, 10);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (866, 1, 11);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (867, 1, 12);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (868, 1, 14);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (869, 1, 15);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (870, 1, 16);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (871, 1, 17);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (872, 1, 18);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (873, 1, 19);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (874, 1, 20);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (875, 1, 21);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (876, 1, 22);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (877, 1, 23);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (878, 1, 24);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (879, 1, 25);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (880, 1, 26);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (881, 1, 27);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (882, 1, 28);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (883, 1, 29);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (884, 1, 30);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (885, 1, 32);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (886, 1, 33);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (887, 1, 34);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (888, 1, 35);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (889, 1, 36);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (890, 1, 37);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (891, 1, 40);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (892, 1, 45);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (893, 1, 46);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (894, 1, 47);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (895, 1, 48);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (896, 1, 49);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (897, 1, 50);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (898, 1, 51);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (899, 1, 52);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (900, 1, 53);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (901, 1, 54);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (902, 1, 55);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (903, 1, 56);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (904, 1, 57);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (905, 1, 58);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (906, 1, 61);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (907, 1, 62);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (908, 1, 63);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (909, 1, 64);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (910, 1, 65);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (911, 1, 68);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (912, 1, 70);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (913, 1, 71);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (914, 1, 72);

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
INSERT INTO "public"."pisces_auth_user" VALUES (2, 'test', '{bcrypt}$2a$10$yftPnyoJe7a/psadq55CyO6dcw9VBjntigB.lXoM9hfPg6xLiawRK', 'https://besscroft.com/uploads/avatar.png', 'test@qq.com', '测试员', '测试员', '12335584848', '2022-03-19 19:17:47', 0, '这个是测试员账号', 'administrator', 'administrator', '2022-03-19 19:17:47', '2022-03-19 19:17:47', 1, 1);
INSERT INTO "public"."pisces_auth_user" VALUES (6, 'test3', '{bcrypt}$2a$10$yftPnyoJe7a/psadq55CyO6dcw9VBjntigB.lXoM9hfPg6xLiawRK', 'https://besscroft.com/uploads/avatar.png', 'test3@qq.com', '瞅你咋地', '测试员', '12414', '2022-03-19 19:20:01', 1, '这个是测试员账号', 'administrator', 'administrator', '2022-03-19 19:20:01', '2022-03-19 19:20:01', 1, 0);
INSERT INTO "public"."pisces_auth_user" VALUES (5, 'test2', '{bcrypt}$2a$10$yftPnyoJe7a/psadq55CyO6dcw9VBjntigB.lXoM9hfPg6xLiawRK', 'https://besscroft.com/uploads/avatar.png', 'test2@qq.com', '你瞅啥', '测试员', '1231412', '2022-03-20 10:31:16', 1, '这个是测试员账号', 'administrator', 'admin', '2022-03-19 19:19:24', '2022-04-03 22:28:20', 1, 1);
INSERT INTO "public"."pisces_auth_user" VALUES (10, 'unitTest', '{bcrypt}$2a$10$4LQZj6AUm9pVoTfXxg9sNeeTD2bljF57gyr9Jr4yISFyWANJDr9ma', 'https://besscroft.com/uploads/avatar.png', 'unitTest@qq.com', '瞅你咋地', '瞅你咋地', '0', '2022-03-22 13:42:44', 1, '这是一条单元测试新增的数据', NULL, 'admin', '2022-03-22 13:42:45', '2022-05-08 14:38:02', 1, 1);
INSERT INTO "public"."pisces_auth_user" VALUES (1, 'admin', '{bcrypt}$2a$10$xlssdD8p5bGXnrmIGOJVU.Niye93oxOCsMQ6T4wFCLyWtNMOERJ8C', 'https://besscroft.com/uploads/avatar.png', 'admin@qq.com', '管理员', '管你员', '12345678902', '2022-02-04 08:30:06', 1, '这个是管理员账号', 'administrator', 'admin', '2022-02-04 08:30:06', '2022-11-02 20:48:44', 1, 1);

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
INSERT INTO "public"."pisces_auth_user_depart" VALUES (2, 2, 2);
INSERT INTO "public"."pisces_auth_user_depart" VALUES (3, 5, 3);
INSERT INTO "public"."pisces_auth_user_depart" VALUES (4, 6, 3);
INSERT INTO "public"."pisces_auth_user_depart" VALUES (5, 10, 2);
INSERT INTO "public"."pisces_auth_user_depart" VALUES (6, 11, 3);
INSERT INTO "public"."pisces_auth_user_depart" VALUES (7, 0, 3);
INSERT INTO "public"."pisces_auth_user_depart" VALUES (1, 1, 2);
INSERT INTO "public"."pisces_auth_user_depart" VALUES (8, 12, 2);

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
INSERT INTO "public"."pisces_auth_user_role" VALUES (18, 10, 2);
INSERT INTO "public"."pisces_auth_user_role" VALUES (19, 1, 1);
INSERT INTO "public"."pisces_auth_user_role" VALUES (22, 5, 2);
INSERT INTO "public"."pisces_auth_user_role" VALUES (23, 2, 2);

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
INSERT INTO "public"."pisces_sys_dict" VALUES (4, 'TEST', '测试key', '测试字典value', NULL, NULL, '2022-08-28 13:37:27.673659', '2022-08-28 13:37:27.673659', 0, '备注');
INSERT INTO "public"."pisces_sys_dict" VALUES (2, 'RESOURCE', 'file', 'pisces-file', 'admin', 'admin', '2022-08-05 14:14:48.557072', NULL, 1, '资源—分布式文件服务');

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
INSERT INTO "public"."pisces_sys_white" VALUES (17, '网关 SpringDoc 白名单', '/v3/api-docs', '网关 SpringDoc 白名单', 'admin', 'admin', '2022-06-03 20:15:53', '2022-06-03 20:15:53', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (18, 'Admin 服务 SpringDoc', '/pisces-admin/v3/api-docs', 'Admin 服务 SpringDoc 白名单', 'admin', 'admin', '2022-06-03 20:16:16', '2022-06-03 20:16:16', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (27, '测试1', '测试', '测试', 'admin', 'admin', '2022-08-28 15:40:54', '2022-08-28 15:42:46', 1, 0);
INSERT INTO "public"."pisces_sys_white" VALUES (15, 'Actuator 监控路径', '/actuator/**', 'Pisces-Gateway Actuator 监控路径', 'admin', 'admin', '2022-05-28 18:54:24', '2022-11-02 21:20:15', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (14, 'Actuator 监控路径', '/pisces-auth/actuator/**', 'Pisces-Auth Actuator 监控路径', 'admin', 'admin', '2022-05-28 18:54:09', '2022-11-02 21:20:19', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (12, 'Actuator 监控路径', '/pisces-admin/actuator/**', 'Pisces-Admin Actuator 监控路径', 'admin', 'admin', '2022-05-28 18:44:39', '2022-11-02 21:20:22', 1, 1);

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
SELECT setval('"public"."pisces_auth_menu_id_seq"', 78, true);

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
SELECT setval('"public"."pisces_auth_resource_id_seq"', 72, true);

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
SELECT setval('"public"."pisces_auth_role_menu_id_seq"', 295, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_role_resource_id_seq"
OWNED BY "public"."pisces_auth_role_resource"."id";
SELECT setval('"public"."pisces_auth_role_resource_id_seq"', 914, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_user_depart_id_seq"
OWNED BY "public"."pisces_auth_user_depart"."id";
SELECT setval('"public"."pisces_auth_user_depart_id_seq"', 8, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_user_id_seq"
OWNED BY "public"."pisces_auth_user"."id";
SELECT setval('"public"."pisces_auth_user_id_seq"', 22, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_user_role_id_seq"
OWNED BY "public"."pisces_auth_user_role"."id";
SELECT setval('"public"."pisces_auth_user_role_id_seq"', 23, true);

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
ALTER TABLE "public"."pisces_auth_menu" ADD CONSTRAINT "auth_menu_pkey" PRIMARY KEY ("id");

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
