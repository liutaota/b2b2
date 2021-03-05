/***************************************************************/
-- 功能描述：账户权限相关
-- 创建人员：tang
-- 创建日期：2020-09-10
-- /**************************************************************/

-- Add/modify columns
alter table USER_INFO add employee_no VARCHAR2(50);
alter table USER_INFO add email VARCHAR2(50);
alter table USER_INFO add end_time date;
alter table USER_INFO add is_use VARCHAR2(50);
-- Add comments to the columns
comment on column USER_INFO.employee_no
  is '工号';
comment on column USER_INFO.email
  is '邮箱';
comment on column USER_INFO.end_time
  is '最近一次登录时间';
comment on column USER_INFO.is_use
  is '是否启用';

ALTER TABLE "B2B"."USER_INFO"
ADD ("TELEPHONE" VARCHAR2(50));

COMMENT ON COLUMN "B2B"."USER_INFO"."TELEPHONE" IS '手机号'

--默认启用
ALTER TABLE "B2B"."USER_INFO"
MODIFY ("IS_USE"  DEFAULT 'ON');

--设置现有的启用
update user_info set is_use='ON';


--角色表
-- Create table
create table SYS_ROLE
(
  id          NUMBER(18),
  user_id     NUMBER(18),
  role_name   VARCHAR2(50),
  role_py     VARCHAR2(50),
  create_time date
)
;
-- Add comments to the table
comment on table SYS_ROLE
  is '系统角色';
-- Add comments to the columns
comment on column SYS_ROLE.id
  is 'id';
comment on column SYS_ROLE.user_id
  is '创建人';
comment on column SYS_ROLE.role_name
  is '角色名称';
comment on column SYS_ROLE.role_py
  is '角色名称拼音';
comment on column SYS_ROLE.create_time
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table SYS_ROLE
  add constraint SYS_ROLE_PK primary key (ID);

-- Create sequence
create sequence SEQ_SYS_ROLE
order;


--角色权限表
-- Create table
create table role_permission
(
  id              NUMBER(18),
  sys_role_id     NUMBER(18),
  permission_name VARCHAR2(50),
  permission_path VARCHAR2(50),
  create_time     date,
  user_id         NUMBER(18)
)
;
-- Add comments to the table
comment on table role_permission
  is '角色权限';
-- Add comments to the columns
comment on column role_permission.id
  is 'id';
comment on column role_permission.sys_role_id
  is '角色id';
comment on column role_permission.permission_name
  is '权限名称';
comment on column role_permission.permission_path
  is '权限路径';
comment on column role_permission.create_time
  is '创建时间';
comment on column role_permission.user_id
  is '创建人';
-- Create/Recreate primary, unique and foreign key constraints
alter table role_permission
  add constraint role_permission_pk primary key (ID);

-- Create sequence
create sequence SEQ_ROLE_PERMISSION
order;


--账户权限表
-- Create table
create table user_role
(
  id           NUMBER(18),
  sys_role_id  NUMBER(18),
  user_info_id NUMBER(18),
  user_id      NUMBER(18),
  create_time  date
)
;
-- Add comments to the table
comment on table user_role
  is '账户权限表';
-- Add comments to the columns
comment on column user_role.id
  is 'id';
comment on column user_role.sys_role_id
  is '角色id';
comment on column user_role.user_info_id
  is '账户id';
comment on column user_role.user_id
  is '创建人';
comment on column user_role.create_time
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table user_role
  add constraint user_role_pk primary key (ID);

-- Create sequence
create sequence SEQ_USER_ROLE
order;
