/*
 Author: Bess Croft

 Source Server         : localPostgreSQL
 Source Server Type    : PostgreSQL
 Source Server Version : 140001
 Source Host           : localhost:5432
 Source Catalog        : pisces-cloud
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 140001
 File Encoding         : 65001

 Date: 12/02/2022 18:18:13
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
  "del" char(1) COLLATE "pg_catalog"."default" NOT NULL DEFAULT 1
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
  "hidden" char(1) COLLATE "pg_catalog"."default" NOT NULL DEFAULT 0,
  "del" char(1) COLLATE "pg_catalog"."default" NOT NULL DEFAULT 1
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
  "del" char(1) COLLATE "pg_catalog"."default" NOT NULL DEFAULT 1
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
COMMENT ON TABLE "public"."pisces_auth_resource" IS '资源表';

-- ----------------------------
-- Records of pisces_auth_resource
-- ----------------------------

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
  "del" char(1) COLLATE "pg_catalog"."default" NOT NULL DEFAULT 1
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
  "status" char(1) COLLATE "pg_catalog"."default" NOT NULL DEFAULT 0,
  "del" char(1) COLLATE "pg_catalog"."default" NOT NULL DEFAULT 1
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
INSERT INTO "public"."pisces_auth_role" VALUES (1, '超级管理员', 'administrator', '超级管理员，拥有所有的权限', 0, 'administrator', 'administrator', '2022-02-04 08:28:07', '2022-02-04 08:28:07', '1', '1');

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
  "status" char(1) COLLATE "pg_catalog"."default" NOT NULL DEFAULT 0,
  "del" char(1) COLLATE "pg_catalog"."default" NOT NULL DEFAULT 1
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
INSERT INTO "public"."pisces_auth_user" VALUES (1, 'admin', '{bcrypt}$2a$10$yftPnyoJe7a/psadq55CyO6dcw9VBjntigB.lXoM9hfPg6xLiawRK', NULL, NULL, '管你员', NULL, NULL, '2022-02-04 08:30:06', 1, NULL, 'administrator', 'administrator', '2022-02-04 08:30:06', '2022-02-04 08:30:06', '1', '1');

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

-- ----------------------------
-- Table structure for pisces_sys_log
-- ----------------------------
DROP TABLE IF EXISTS "public"."pisces_sys_log";
CREATE TABLE "public"."pisces_sys_log" (
  "id" int8 NOT NULL DEFAULT nextval('pisces_sys_log_id_seq'::regclass)
)
;
COMMENT ON TABLE "public"."pisces_sys_log" IS '系统日志表';

-- ----------------------------
-- Records of pisces_sys_log
-- ----------------------------

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_depart_id_seq"
OWNED BY "public"."pisces_auth_depart"."id";
SELECT setval('"public"."pisces_auth_depart_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_menu_id_seq"
OWNED BY "public"."pisces_auth_menu"."id";
SELECT setval('"public"."pisces_auth_menu_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_resource_category_id_seq"
OWNED BY "public"."pisces_auth_resource_category"."id";
SELECT setval('"public"."pisces_auth_resource_category_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_resource_id_seq"
OWNED BY "public"."pisces_auth_resource"."id";
SELECT setval('"public"."pisces_auth_resource_id_seq"', 1, false);

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
SELECT setval('"public"."pisces_auth_role_menu_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_role_resource_id_seq"
OWNED BY "public"."pisces_auth_role_resource"."id";
SELECT setval('"public"."pisces_auth_role_resource_id_seq"', 1, false);

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
SELECT setval('"public"."pisces_auth_user_id_seq"', 1, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_auth_user_role_id_seq"
OWNED BY "public"."pisces_auth_user_role"."id";
SELECT setval('"public"."pisces_auth_user_role_id_seq"', 1, false);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."pisces_sys_log_id_seq"
OWNED BY "public"."pisces_sys_log"."id";
SELECT setval('"public"."pisces_sys_log_id_seq"', 1, false);

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
