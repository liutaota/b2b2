/***************************************************************/
-- 功能描述：首页菜单表
-- 创建人员：sjl
-- 创建日期：2020-10-26
-- /**************************************************************/
create table HOME_MENU
(
	ID NUMBER(18) not null
		constraint HOME_MENU_pk
			primary key,
	HM_NAME VARCHAR2(1024),
	HM_ZONE_ID NUMBER(18),
	PARENT_ID NUMBER(18),
	SORT NUMBER(18),
	IS_USE VARCHAR2(1024),
	IS_DEL NUMBER(1),
	MODIFY_TIME DATE,
	CREATE_TIME DATE
)
/

comment on table HOME_MENU is '首页菜单'
/

comment on column HOME_MENU.ID is '主键id'
/

comment on column HOME_MENU.HM_NAME is '菜单名'
/

comment on column HOME_MENU.HM_ZONE_ID is '页面id'
/

comment on column HOME_MENU.PARENT_ID is '上级id'
/

comment on column HOME_MENU.SORT is '排序'
/

comment on column HOME_MENU.IS_USE is '是否显示：ON 是 OFF 否'
/

comment on column HOME_MENU.IS_DEL is '是否删除：0 否 1 是'
/

comment on column HOME_MENU.MODIFY_TIME is '修改时间'
/

comment on column HOME_MENU.CREATE_TIME is '添加时间'
/

create sequence SEQ_HOME_MENU
/


-- 添加提示图标

alter table HOME_MENU
	add HINT_ICON VARCHAR2(1024)
/

comment on column HOME_MENU.HINT_ICON is '提示图标'
/

