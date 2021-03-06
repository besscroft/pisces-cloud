/*
 Navicat Premium Data Transfer

 Source Server         : localPostgreSQL
 Source Server Type    : PostgreSQL
 Source Server Version : 140001
 Source Host           : localhost:5432
 Source Catalog        : pisces-cloud
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 140001
 File Encoding         : 65001

 Date: 03/06/2022 22:37:15
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
COMMENT ON COLUMN "public"."pisces_auth_depart"."parent_id" IS '??????ID';
COMMENT ON COLUMN "public"."pisces_auth_depart"."name" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_depart"."description" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_depart"."sort" IS '??????';
COMMENT ON COLUMN "public"."pisces_auth_depart"."creator" IS '?????????';
COMMENT ON COLUMN "public"."pisces_auth_depart"."updater" IS '?????????';
COMMENT ON COLUMN "public"."pisces_auth_depart"."create_time" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_depart"."update_time" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_depart"."del" IS '???????????????0->???????????????1->????????????';
COMMENT ON TABLE "public"."pisces_auth_depart" IS '???????????????';

-- ----------------------------
-- Records of pisces_auth_depart
-- ----------------------------
INSERT INTO "public"."pisces_auth_depart" VALUES (1, 0, 'Pisces Item', 'Pisces ????????????', 1, 'admin', 'admin', '2022-03-24 18:16:17', '2022-03-24 18:16:19', 1);
INSERT INTO "public"."pisces_auth_depart" VALUES (2, 1, '?????????', '????????????', 2, 'admin', 'admin', '2022-03-24 18:16:39', '2022-03-24 18:16:41', 1);
INSERT INTO "public"."pisces_auth_depart" VALUES (3, 1, '?????????', '????????????', 3, 'admin', 'admin', '2022-03-24 18:17:01', '2022-03-24 18:17:02', 1);
INSERT INTO "public"."pisces_auth_depart" VALUES (6, 1, 'gfd', '', 2, 'admin', 'admin', '2022-04-30 17:55:55', '2022-04-30 17:56:17', 0);

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
COMMENT ON COLUMN "public"."pisces_auth_menu"."parent_id" IS '??????ID';
COMMENT ON COLUMN "public"."pisces_auth_menu"."title" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_menu"."name" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_menu"."parent_title" IS '???????????????';
COMMENT ON COLUMN "public"."pisces_auth_menu"."level" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_menu"."component" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_menu"."path" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_menu"."icon" IS '??????????????????';
COMMENT ON COLUMN "public"."pisces_auth_menu"."sort" IS '??????';
COMMENT ON COLUMN "public"."pisces_auth_menu"."creator" IS '?????????';
COMMENT ON COLUMN "public"."pisces_auth_menu"."updater" IS '?????????';
COMMENT ON COLUMN "public"."pisces_auth_menu"."create_time" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_menu"."update_time" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_menu"."hidden" IS '?????????????????????0->?????????1->??????';
COMMENT ON COLUMN "public"."pisces_auth_menu"."del" IS '???????????????0->???????????????1->????????????';
COMMENT ON TABLE "public"."pisces_auth_menu" IS '?????????';

-- ----------------------------
-- Records of pisces_auth_menu
-- ----------------------------
INSERT INTO "public"."pisces_auth_menu" VALUES (11, 10, '??????', '??????', '???????????????', 3, '??????', '??????', '??????', 1, 'admin', NULL, '2022-05-08 14:23:03', '2022-05-08 14:23:03', 0, 0);
INSERT INTO "public"."pisces_auth_menu" VALUES (6, 1, '????????????', 'menu', '????????????', 2, '/auth/menu/index', 'menu', 'Menu', 4, 'admin', 'admin', '2022-03-20 18:44:08', '2022-05-15 12:48:04', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (3, 1, '????????????', 'authUser', '????????????', 2, '/auth/user/index', 'user', 'User', 2, 'admin', 'admin', '2022-03-04 20:56:48', '2022-05-15 12:49:55', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (8, 1, '????????????', 'resource', '????????????', 2, '/auth/resource/index', 'resource', 'Finished', 6, 'admin', 'admin', '2022-03-20 18:45:47', '2022-03-20 18:45:49', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (5, 1, '????????????', 'role', '????????????', 2, '/auth/role/index', 'role', 'UserFilled', 3, 'admin', 'admin', '2022-03-20 18:43:01', '2022-03-20 18:43:03', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (4, 2, '????????????', 'log', '????????????', 2, '/system/log/index', 'log', 'Document', 2, 'admin', 'admin', '2022-03-06 12:33:45', '2022-03-06 12:33:48', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (7, 1, '??????????????????', 'resourceCategory', '??????????????????', 2, '/auth/resourceCategory/index', 'resourceCategory', 'Briefcase', 5, 'admin', 'admin', '2022-03-20 18:45:09', '2022-03-24 17:11:57', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (1, 0, '????????????', 'auth', NULL, 1, 'Layout', '/auth', 'Promotion', 1, 'admin', 'admin', '2022-03-04 20:54:23', '2022-03-04 20:54:26', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (2, 0, '????????????', 'system', NULL, 1, 'Layout', '/system', 'SetUp', 2, 'admin', 'admin', '2022-03-04 20:55:19', '2022-04-26 20:31:24', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (10, 2, '???????????????', 'whiteList', '???????????????', 2, '/system/whiteList/index', 'whiteList', 'CircleCheckFilled', 2, 'admin', 'admin', '2022-04-26 21:29:06', '2022-04-26 21:29:06', 1, 1);
INSERT INTO "public"."pisces_auth_menu" VALUES (9, 1, '????????????', 'depart', '????????????', 2, '/auth/depart/index', 'depart', 'Briefcase', 7, 'admin', 'admin', '2022-03-20 18:46:20', '2022-05-08 13:54:12', 1, 1);

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
  "del" int2 NOT NULL DEFAULT 1
)
;
COMMENT ON COLUMN "public"."pisces_auth_resource"."name" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_resource"."url" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_resource"."description" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_resource"."category_id" IS '????????????ID';
COMMENT ON COLUMN "public"."pisces_auth_resource"."sort" IS '??????';
COMMENT ON COLUMN "public"."pisces_auth_resource"."creator" IS '?????????';
COMMENT ON COLUMN "public"."pisces_auth_resource"."updater" IS '?????????';
COMMENT ON COLUMN "public"."pisces_auth_resource"."create_time" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_resource"."update_time" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_resource"."del" IS '???????????????0->???????????????1->????????????';
COMMENT ON TABLE "public"."pisces_auth_resource" IS '?????????';

-- ----------------------------
-- Records of pisces_auth_resource
-- ----------------------------
INSERT INTO "public"."pisces_auth_resource" VALUES (2, '????????????', '/user/list', '??????????????????????????????', 1, 2, 'admin', 'admin', '2022-03-13 16:48:42', '2022-03-13 16:48:44', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (1, '??????????????????', '/user/info', '??????????????????????????????????????????????????????????????????', 1, 1, 'admin', 'admin', '2022-03-04 21:25:12', '2022-03-04 21:25:14', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (4, '??????????????????', '/user/update', '??????????????????????????????????????????', 1, 4, 'admin', 'admin', '2022-03-13 19:42:56', '2022-03-13 19:42:58', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (5, '????????????????????????', '/user/change', '??????????????????????????????', 1, 5, 'admin', 'admin', '2022-03-20 11:44:45', '2022-03-20 11:44:47', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (6, '????????????', '/user/add', '??????????????????', 1, 6, 'admin', 'admin', '2022-03-20 14:54:51', '2022-03-20 14:54:51', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (7, '??????????????????', '/user/update', '????????????????????????', 1, 7, 'admin', 'admin', '2022-03-20 16:50:25', '2022-03-20 16:50:25', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (9, '????????????', '/user/delete/**', '????????????id??????????????????', 1, 8, 'admin', 'admin', '2022-03-20 16:50:59', '2022-03-20 16:50:59', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (10, '????????????', '/role/list', '??????????????????????????????', 2, 1, 'admin', 'admin', '2022-03-20 19:27:39', '2022-03-20 19:27:42', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (12, '????????????', '/user/loginOut', '??????????????????', 1, 9, 'admin', 'admin', '2022-03-20 20:49:33', '2022-03-20 20:49:35', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (14, '????????????', '/menu/list', '??????????????????????????????', 3, 1, 'admin', 'admin', '2022-03-24 14:53:49', '2022-03-24 14:53:51', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (15, '????????????????????????', '/menu/change', '??????????????????????????????', 3, 2, 'admin', 'admin', '2022-03-24 15:20:31', '2022-03-24 15:20:33', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (16, '????????????', '/menu/delete/**', '????????????id??????????????????', 3, 3, 'admin', 'admin', '2022-03-24 15:35:58', '2022-03-24 15:36:01', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (17, '????????????', '/menu/update', '????????????????????????', 3, 4, 'admin', 'admin', '2022-03-24 16:08:06', '2022-03-24 16:08:07', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (18, '????????????', '/resource/list', '??????????????????????????????', 5, 1, 'admin', 'admin', '2022-03-24 17:44:40', '2022-03-24 17:44:42', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (19, '??????????????????', '/resource/category/list', '????????????????????????????????????', 4, 1, 'admin', 'admin', '2022-03-24 17:45:22', '2022-03-24 17:45:24', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (20, '??????/????????????', '/depart/list', '??????/??????????????????????????????', 6, 1, 'admin', 'admin', '2022-03-24 17:46:04', '2022-03-24 17:46:06', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (22, '????????????????????????', '/menu/getAll', '????????????????????????', 3, 6, 'admin', 'admin', '2022-04-02 22:09:40', '2022-04-02 22:09:40', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (21, '????????????id????????????id??????', '/menu/getId/role/**', '????????????id????????????id??????', 3, 5, 'admin', 'admin', '2022-04-02 21:56:57', '2022-04-02 21:56:59', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (23, '????????????????????????', '/role/update/menu', '????????????????????????', 2, 3, 'admin', 'admin', '2022-04-03 10:57:05', '2022-04-03 10:57:05', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (24, '?????????????????????', '/resource/getAll', '?????????????????????', 4, 2, 'admin', 'admin', '2022-04-03 11:26:00', '2022-04-03 11:26:00', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (25, '????????????id????????????id????????????', '/resource/getId/role/**', '????????????id????????????id????????????', 4, 3, 'admin', 'admin', '2022-04-03 12:10:47', '2022-04-03 12:10:47', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (26, '????????????????????????', '/role/update/resource', '????????????????????????', 2, 4, 'admin', 'admin', '2022-04-03 20:08:54', '2022-04-03 20:08:54', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (27, '??????????????????', '/role/delete/**', '??????????????????', 2, 5, 'admin', 'admin', '2022-04-03 20:22:36', '2022-04-03 20:22:36', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (29, '??????????????????', '/role/update', '??????????????????', 2, 7, 'admin', 'admin', '2022-04-03 21:36:42', '2022-04-03 21:36:42', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (28, '??????????????????', '/role/add', '??????????????????', 2, 6, 'admin', 'admin', '2022-04-03 21:36:23', '2022-04-03 21:36:23', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (30, '??????????????????', '/role/getRoleDict', '??????????????????', 2, 8, 'admin', 'admin', '2022-04-10 18:46:03', '2022-04-10 18:46:03', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (32, '????????????????????????', '/user/update/role', '????????????????????????', 2, 9, 'admin', 'admin', '2022-04-10 21:15:24', '2022-04-10 21:15:24', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (33, '????????????????????????', '/resource/category/delete/**', '????????????????????????', 4, 2, 'admin', 'admin', '2022-04-26 20:40:31', '2022-04-26 20:40:31', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (34, '??????/??????????????????', '/depart/delete/**', '??????/??????????????????', 6, 2, 'admin', 'admin', '2022-04-26 20:58:43', '2022-04-26 20:58:43', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (35, '??????????????????', '/resource/delete/**', '??????????????????', 5, 2, 'admin', 'admin', '2022-04-26 21:16:16', '2022-04-26 21:16:16', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (11, '????????????????????????', '/role/change', '??????????????????????????????', 2, 2, 'admin', 'admin', '2022-03-20 20:06:33', '2022-03-20 20:06:34', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (36, '??????????????????', '/resource/add', '??????????????????', 5, 3, 'admin', 'admin', '2022-04-30 06:41:04', '2022-04-30 06:41:04', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (37, '??????????????????', '/resource/update', '??????????????????', 5, 4, 'admin', 'admin', '2022-04-30 06:58:26', '2022-04-30 06:58:26', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (40, '????????????????????????', '/resource/category/getResourceCategoryDict', '????????????????????????', 4, 4, 'admin', 'admin', '2022-04-30 07:53:28', '2022-04-30 07:53:28', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (51, '????????????????????????', '/resource/category/add', '????????????????????????', 4, 5, 'admin', 'admin', '2022-05-08 15:02:07', '2022-05-08 15:02:07', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (52, '????????????????????????', '/resource/category/update', '????????????????????????', 4, 6, 'admin', 'admin', '2022-05-08 15:02:41', '2022-05-08 15:02:41', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (45, '????????????/????????????', '/depart/add', '????????????/????????????', 6, 3, 'admin', 'admin', '2022-04-30 08:59:01', '2022-04-30 08:59:01', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (46, '????????????/????????????', '/depart/update', '????????????/????????????', 6, 4, 'admin', 'admin', '2022-04-30 08:59:01', '2022-04-30 08:59:01', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (47, '??????????????????', '/depart/getDepartDict', '??????????????????', 6, 5, 'admin', 'admin', '2022-04-30 09:46:45', '2022-04-30 09:46:45', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (48, '????????????????????????', '/role/get/**', '???????????? id ????????????????????????', 2, 3, 'admin', 'admin', '2022-04-30 13:23:20', '2022-04-30 13:23:20', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (49, '??????????????????', '/menu/add', '??????????????????', 3, 6, 'admin', 'admin', '2022-05-04 18:27:56', '2022-05-04 18:27:56', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (50, '??????????????????', '/menu/getMenuDict', '??????????????????', 3, 7, 'admin', 'admin', '2022-05-04 18:51:53', '2022-05-04 18:51:53', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (53, '?????????????????????', '/white/list', '?????????????????????', 7, 1, 'admin', 'admin', '2022-05-14 18:37:03', '2022-05-14 18:37:03', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (54, '?????????????????????', '/white/add', '?????????????????????', 7, 2, 'admin', 'admin', '2022-05-14 21:28:57', '2022-05-14 21:28:57', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (56, '?????????????????????', '/white/delete/**', '?????????????????????', 7, 4, 'admin', 'admin', '2022-05-14 21:29:28', '2022-05-14 21:29:28', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (3, '????????????', '/user/info/**', '??????????????????', 1, 3, 'admin', 'admin', '2022-03-13 19:41:51', '2022-05-21 22:59:30', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (55, '?????????????????????', '/white/update', '?????????????????????', 7, 3, 'admin', 'admin', '2022-05-14 21:29:09', '2022-05-14 21:29:09', 1);
INSERT INTO "public"."pisces_auth_resource" VALUES (57, '?????????????????????', '/white/getWhiteDict', '?????????????????????', 7, 5, 'admin', 'admin', '2022-05-14 22:20:02', '2022-05-14 22:20:02', 1);

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
COMMENT ON COLUMN "public"."pisces_auth_resource_category"."category_name" IS '??????????????????';
COMMENT ON COLUMN "public"."pisces_auth_resource_category"."description" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_resource_category"."sort" IS '??????';
COMMENT ON COLUMN "public"."pisces_auth_resource_category"."creator" IS '?????????';
COMMENT ON COLUMN "public"."pisces_auth_resource_category"."updater" IS '?????????';
COMMENT ON COLUMN "public"."pisces_auth_resource_category"."create_time" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_resource_category"."update_time" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_resource_category"."del" IS '???????????????0->???????????????1->????????????';
COMMENT ON TABLE "public"."pisces_auth_resource_category" IS '???????????????';

-- ----------------------------
-- Records of pisces_auth_resource_category
-- ----------------------------
INSERT INTO "public"."pisces_auth_resource_category" VALUES (1, '????????????', '????????????', 1, 'admin', 'admin', '2022-03-04 21:25:53', '2022-03-04 21:25:51', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (2, '????????????', '????????????', 2, 'admin', 'admin', '2022-03-20 19:28:04', '2022-03-20 19:28:06', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (3, '????????????', '????????????', 3, 'admin', 'admin', '2022-03-24 14:54:21', '2022-03-24 14:54:23', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (4, '??????????????????', '??????????????????', 4, 'admin', 'admin', '2022-03-24 14:54:44', '2022-03-24 14:54:46', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (5, '????????????', '????????????', 5, 'admin', 'admin', '2022-03-24 14:55:02', '2022-03-24 14:55:04', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (6, '????????????', '????????????', 6, 'admin', 'admin', '2022-03-24 14:55:19', '2022-03-24 14:55:22', 1);
INSERT INTO "public"."pisces_auth_resource_category" VALUES (7, '???????????????', '???????????????', 7, 'admin', 'admin', '2022-04-26 21:27:30', '2022-04-26 21:27:30', 1);

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
COMMENT ON COLUMN "public"."pisces_auth_role"."role_name" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_role"."role_code" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_role"."description" IS '??????';
COMMENT ON COLUMN "public"."pisces_auth_role"."sort" IS '??????';
COMMENT ON COLUMN "public"."pisces_auth_role"."creator" IS '?????????';
COMMENT ON COLUMN "public"."pisces_auth_role"."updater" IS '?????????';
COMMENT ON COLUMN "public"."pisces_auth_role"."create_time" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_role"."update_time" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_role"."status" IS '?????????????????????0->?????????1->??????';
COMMENT ON COLUMN "public"."pisces_auth_role"."del" IS '???????????????0->???????????????1->????????????';
COMMENT ON TABLE "public"."pisces_auth_role" IS '?????????';

-- ----------------------------
-- Records of pisces_auth_role
-- ----------------------------
INSERT INTO "public"."pisces_auth_role" VALUES (1, '???????????????', 'administrator', '???????????????????????????????????????', 0, 'administrator', 'administrator', '2022-02-04 08:28:07', '2022-02-04 08:28:07', 1, 1);
INSERT INTO "public"."pisces_auth_role" VALUES (2, '?????????', 'test', '??????????????????', 1, 'admin', 'admin', '2022-03-04 23:32:26', '2022-03-04 23:32:27', 1, 1);

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
COMMENT ON COLUMN "public"."pisces_auth_role_menu"."role_id" IS '??????ID';
COMMENT ON COLUMN "public"."pisces_auth_role_menu"."menu_id" IS '??????ID';
COMMENT ON TABLE "public"."pisces_auth_role_menu" IS '?????????????????????';

-- ----------------------------
-- Records of pisces_auth_role_menu
-- ----------------------------
INSERT INTO "public"."pisces_auth_role_menu" VALUES (18, 2, 1);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (19, 2, 3);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (20, 2, 6);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (21, 2, 7);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (22, 1, 1);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (23, 1, 2);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (24, 1, 3);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (25, 1, 4);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (26, 1, 5);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (27, 1, 6);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (28, 1, 7);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (29, 1, 8);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (30, 1, 9);
INSERT INTO "public"."pisces_auth_role_menu" VALUES (31, 1, 10);

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
COMMENT ON COLUMN "public"."pisces_auth_role_resource"."role_id" IS '??????ID';
COMMENT ON COLUMN "public"."pisces_auth_role_resource"."resource_id" IS '??????ID';
COMMENT ON TABLE "public"."pisces_auth_role_resource" IS '?????????????????????';

-- ----------------------------
-- Records of pisces_auth_role_resource
-- ----------------------------
INSERT INTO "public"."pisces_auth_role_resource" VALUES (120, 1, 53);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (121, 1, 54);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (122, 1, 55);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (123, 1, 56);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (124, 1, 57);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (73, 2, 1);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (74, 2, 21);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (75, 2, 22);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (76, 2, 9);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (77, 1, 1);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (78, 1, 2);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (79, 1, 3);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (80, 1, 4);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (81, 1, 5);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (82, 1, 6);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (83, 1, 7);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (84, 1, 9);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (85, 1, 10);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (86, 1, 11);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (87, 1, 12);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (88, 1, 14);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (89, 1, 15);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (90, 1, 16);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (91, 1, 17);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (92, 1, 18);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (93, 1, 19);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (94, 1, 20);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (95, 1, 21);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (96, 1, 22);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (97, 1, 23);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (98, 1, 24);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (99, 1, 25);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (100, 1, 26);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (101, 1, 27);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (102, 1, 28);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (103, 1, 29);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (104, 1, 30);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (105, 1, 32);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (106, 1, 33);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (107, 1, 34);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (108, 1, 35);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (109, 1, 36);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (110, 1, 37);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (111, 1, 40);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (112, 1, 45);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (113, 1, 46);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (114, 1, 47);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (115, 1, 48);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (116, 1, 49);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (117, 1, 50);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (118, 1, 51);
INSERT INTO "public"."pisces_auth_role_resource" VALUES (119, 1, 52);

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
COMMENT ON COLUMN "public"."pisces_auth_user"."username" IS '??????(?????????)';
COMMENT ON COLUMN "public"."pisces_auth_user"."password" IS '??????';
COMMENT ON COLUMN "public"."pisces_auth_user"."avatar" IS '??????(??????)';
COMMENT ON COLUMN "public"."pisces_auth_user"."email" IS '??????';
COMMENT ON COLUMN "public"."pisces_auth_user"."name" IS '??????';
COMMENT ON COLUMN "public"."pisces_auth_user"."real_name" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_user"."telephone" IS '??????';
COMMENT ON COLUMN "public"."pisces_auth_user"."birthday" IS '??????';
COMMENT ON COLUMN "public"."pisces_auth_user"."sex" IS '??????';
COMMENT ON COLUMN "public"."pisces_auth_user"."remark" IS '??????';
COMMENT ON COLUMN "public"."pisces_auth_user"."creator" IS '?????????';
COMMENT ON COLUMN "public"."pisces_auth_user"."updater" IS '?????????';
COMMENT ON COLUMN "public"."pisces_auth_user"."create_time" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_user"."update_time" IS '????????????';
COMMENT ON COLUMN "public"."pisces_auth_user"."status" IS '?????????????????????0->?????????1->??????';
COMMENT ON COLUMN "public"."pisces_auth_user"."del" IS '???????????????0->???????????????1->????????????';
COMMENT ON TABLE "public"."pisces_auth_user" IS '?????????';

-- ----------------------------
-- Records of pisces_auth_user
-- ----------------------------
INSERT INTO "public"."pisces_auth_user" VALUES (1, 'admin', '{bcrypt}$2a$10$yftPnyoJe7a/psadq55CyO6dcw9VBjntigB.lXoM9hfPg6xLiawRK', 'https://besscroft.com/uploads/avatar.png', 'admin@qq.com', '?????????', '?????????', '12345678901', '2022-02-04 08:30:06', 1, '????????????????????????', 'administrator', 'administrator', '2022-02-04 08:30:06', '2022-02-04 08:30:06', 1, 1);
INSERT INTO "public"."pisces_auth_user" VALUES (2, 'test', '{bcrypt}$2a$10$yftPnyoJe7a/psadq55CyO6dcw9VBjntigB.lXoM9hfPg6xLiawRK', 'https://besscroft.com/uploads/avatar.png', 'test@qq.com', '?????????', '?????????', '12335584848', '2022-03-19 19:17:47', 0, '????????????????????????', 'administrator', 'administrator', '2022-03-19 19:17:47', '2022-03-19 19:17:47', 1, 1);
INSERT INTO "public"."pisces_auth_user" VALUES (6, 'test3', '{bcrypt}$2a$10$yftPnyoJe7a/psadq55CyO6dcw9VBjntigB.lXoM9hfPg6xLiawRK', 'https://besscroft.com/uploads/avatar.png', 'test3@qq.com', '????????????', '?????????', '12414', '2022-03-19 19:20:01', 1, '????????????????????????', 'administrator', 'administrator', '2022-03-19 19:20:01', '2022-03-19 19:20:01', 1, 0);
INSERT INTO "public"."pisces_auth_user" VALUES (5, 'test2', '{bcrypt}$2a$10$yftPnyoJe7a/psadq55CyO6dcw9VBjntigB.lXoM9hfPg6xLiawRK', 'https://besscroft.com/uploads/avatar.png', 'test2@qq.com', '?????????', '?????????', '1231412', '2022-03-20 10:31:16', 1, '????????????????????????', 'administrator', 'admin', '2022-03-19 19:19:24', '2022-04-03 22:28:20', 1, 1);
INSERT INTO "public"."pisces_auth_user" VALUES (10, 'unitTest', '{bcrypt}$2a$10$4LQZj6AUm9pVoTfXxg9sNeeTD2bljF57gyr9Jr4yISFyWANJDr9ma', 'https://besscroft.com/uploads/avatar.png', 'unitTest@qq.com', '????????????', '????????????', '0', '2022-03-22 13:42:44', 1, '???????????????????????????????????????', NULL, 'admin', '2022-03-22 13:42:45', '2022-05-08 14:38:02', 1, 1);

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
COMMENT ON COLUMN "public"."pisces_auth_user_depart"."user_id" IS '??????ID';
COMMENT ON COLUMN "public"."pisces_auth_user_depart"."depart_id" IS '??????ID';
COMMENT ON TABLE "public"."pisces_auth_user_depart" IS '?????????????????????';

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
COMMENT ON COLUMN "public"."pisces_auth_user_role"."user_id" IS '??????ID';
COMMENT ON COLUMN "public"."pisces_auth_user_role"."role_id" IS '??????ID';
COMMENT ON TABLE "public"."pisces_auth_user_role" IS '?????????????????????';

-- ----------------------------
-- Records of pisces_auth_user_role
-- ----------------------------
INSERT INTO "public"."pisces_auth_user_role" VALUES (17, 2, 2);
INSERT INTO "public"."pisces_auth_user_role" VALUES (18, 10, 2);
INSERT INTO "public"."pisces_auth_user_role" VALUES (19, 1, 1);
INSERT INTO "public"."pisces_auth_user_role" VALUES (22, 5, 2);

-- ----------------------------
-- Table structure for pisces_sys_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."pisces_sys_log";
CREATE TABLE "public"."pisces_sys_log" (
  "id" int8 NOT NULL DEFAULT nextval('pisces_sys_log_id_seq'::regclass)
)
;
COMMENT ON TABLE "public"."pisces_sys_log" IS '???????????????';

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
COMMENT ON COLUMN "public"."pisces_sys_white"."title" IS '?????????????????????';
COMMENT ON COLUMN "public"."pisces_sys_white"."path" IS '?????????????????????';
COMMENT ON COLUMN "public"."pisces_sys_white"."remark" IS '??????';
COMMENT ON COLUMN "public"."pisces_sys_white"."creator" IS '?????????';
COMMENT ON COLUMN "public"."pisces_sys_white"."updater" IS '?????????';
COMMENT ON COLUMN "public"."pisces_sys_white"."create_time" IS '????????????';
COMMENT ON COLUMN "public"."pisces_sys_white"."update_time" IS '????????????';
COMMENT ON COLUMN "public"."pisces_sys_white"."status" IS '??????????????????????????????0->?????????1->??????';
COMMENT ON COLUMN "public"."pisces_sys_white"."del" IS '???????????????0->???????????????1->????????????';
COMMENT ON TABLE "public"."pisces_sys_white" IS '??????????????????';

-- ----------------------------
-- Records of pisces_sys_white
-- ----------------------------
INSERT INTO "public"."pisces_sys_white" VALUES (15, 'Actuator ????????????', '/actuator/**', 'Pisces-Gateway Actuator ????????????', 'admin', 'admin', '2022-05-28 18:54:24', '2022-05-28 18:54:24', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (12, 'Actuator ????????????', '/pisces-admin/actuator/**', 'Pisces-Admin Actuator ????????????', 'admin', 'admin', '2022-05-28 18:44:39', '2022-05-28 18:44:39', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (14, 'Actuator ????????????', '/pisces-auth/actuator/**', 'Pisces-Auth Actuator ????????????', 'admin', 'admin', '2022-05-28 18:54:09', '2022-05-28 18:54:09', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (21, '?????? swagger ??????', '/swagger-ui.html', '?????? swagger ??????', 'admin', 'admin', '2022-06-03 20:19:23', '2022-06-03 20:19:23', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (20, '????????????', '/**/*.ico', '????????????', 'admin', 'admin', '2022-06-03 20:18:29', '2022-06-03 20:18:29', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (1, '????????????', '/pisces-admin/user/logout', '?????????????????????????????????', 'admin', 'admin', '2022-05-14 20:13:58', '2022-05-14 20:13:58', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (11, '??????????????????', '/pisces-auth/publicKey/get', '??????????????????', 'admin', 'admin', '2022-05-17 17:10:00', '2022-05-21 22:49:45', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (7, '??????', '/pisces-admin/user/login', '????????????', 'admin', 'admin', '2022-05-17 17:09:23', '2022-05-21 22:59:13', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (25, 'openapi', '/v3/**', 'openapi', 'admin', 'admin', '2022-06-03 20:59:11', '2022-06-03 20:59:11', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (24, '????????????', '/**/*.js', '????????????', 'admin', 'admin', '2022-06-03 20:58:53', '2022-06-03 20:58:53', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (23, '????????????', '/**/*.css', '????????????', 'admin', 'admin', '2022-06-03 20:58:43', '2022-06-03 20:58:43', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (22, '????????????', '/**/*.png', '????????????', 'admin', 'admin', '2022-06-03 20:58:31', '2022-06-03 20:58:31', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (26, 'swagger ??????', '/webjars/**', 'swagger ??????', 'admin', 'admin', '2022-06-03 21:00:01', '2022-06-03 21:00:01', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (19, 'Auth ?????? SpringDoc', '/pisces-auth/v3/api-docs', 'Auth ?????? SpringDoc ?????????', 'admin', 'admin', '2022-06-03 20:16:38', '2022-06-03 20:16:38', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (16, 'SpringBoot Admin ????????????', '/pisces-monitor/**', 'SpringBoot Admin ????????????', 'admin', 'admin', '2022-05-29 14:40:46', '2022-05-29 14:40:46', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (17, '?????? SpringDoc ?????????', '/v3/api-docs', '?????? SpringDoc ?????????', 'admin', 'admin', '2022-06-03 20:15:53', '2022-06-03 20:15:53', 1, 1);
INSERT INTO "public"."pisces_sys_white" VALUES (18, 'Admin ?????? SpringDoc', '/pisces-admin/v3/api-docs', 'Admin ?????? SpringDoc ?????????', 'admin', 'admin', '2022-06-03 20:16:16', '2022-06-03 20:16:16', 1, 1);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_depart_id_seq"
OWNED BY "public"."pisces_auth_depart"."id";
SELECT setval('"public"."pisces_auth_depart_id_seq"', 6, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_menu_id_seq"
OWNED BY "public"."pisces_auth_menu"."id";
SELECT setval('"public"."pisces_auth_menu_id_seq"', 11, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_resource_category_id_seq"
OWNED BY "public"."pisces_auth_resource_category"."id";
SELECT setval('"public"."pisces_auth_resource_category_id_seq"', 10, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_resource_id_seq"
OWNED BY "public"."pisces_auth_resource"."id";
SELECT setval('"public"."pisces_auth_resource_id_seq"', 57, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_role_id_seq"
OWNED BY "public"."pisces_auth_role"."id";
SELECT setval('"public"."pisces_auth_role_id_seq"', 1, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_role_menu_id_seq"
OWNED BY "public"."pisces_auth_role_menu"."id";
SELECT setval('"public"."pisces_auth_role_menu_id_seq"', 31, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_role_resource_id_seq"
OWNED BY "public"."pisces_auth_role_resource"."id";
SELECT setval('"public"."pisces_auth_role_resource_id_seq"', 124, true);

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
SELECT setval('"public"."pisces_auth_user_id_seq"', 10, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_user_role_id_seq"
OWNED BY "public"."pisces_auth_user_role"."id";
SELECT setval('"public"."pisces_auth_user_role_id_seq"', 22, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_sys_log_id_seq"
OWNED BY "public"."pisces_sys_log"."id";
SELECT setval('"public"."pisces_sys_log_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
SELECT setval('"public"."pisces_sys_white_id_seq"', 26, true);

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
COMMENT ON INDEX "public"."idx_pisces_auth_user_username" IS '?????????????????????';

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
-- Primary Key structure for table pisces_sys_log
-- ----------------------------
ALTER TABLE "public"."pisces_sys_log" ADD CONSTRAINT "pisces_sys_log_pk" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table pisces_sys_white
-- ----------------------------
ALTER TABLE "public"."pisces_sys_white" ADD CONSTRAINT "pisces_sys_white_pkey" PRIMARY KEY ("id");
