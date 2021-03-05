/***************************************************************/
-- 功能描述：商品分类
-- 创建人员：sjl
-- 创建日期：2020-09-28
-- /**************************************************************/
create table CLASS
(
	ID NUMBER(18) not null
		constraint CLASS_pk
			primary key,
	ERP_CLASS_ID NUMBER(18),
	CLASS_NAME VARCHAR2(50),
	CLASS_IMG VARCHAR2(500),
	CREATE_TIME DATE
)
/

comment on table CLASS is '商品分类'
/

comment on column CLASS.ID is '主键id'
/

comment on column CLASS.ERP_CLASS_ID is 'erp分类id'
/

comment on column CLASS.CLASS_NAME is '分类简称'
/

comment on column CLASS.CLASS_IMG is '分类图片'
/

comment on column CLASS.CREATE_TIME is '添加时间'
/

/***************************************************************/
-- 功能描述：货品表关联商品分类
-- 创建人员：sjl
-- 创建日期：2020-09-28
-- /**************************************************************/
create sequence SEQ_CLASS
/

alter table GOODS
	add CLASS_ID NUMBER(18)
/

comment on column GOODS.CLASS_ID is '商品分类id'
/



