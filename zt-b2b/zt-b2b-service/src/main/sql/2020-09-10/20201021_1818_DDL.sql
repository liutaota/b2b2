/***************************************************************/
-- 功能描述：商品表添加积分字段
-- 创建人员：sjl
-- 创建日期：2020-10-12
-- /**************************************************************/
alter table GOODS
	add INTEGRAL_GOODS NUMBER(18)
/

comment on column GOODS.INTEGRAL_GOODS is '积分商品上下架：0下架 1上架'
/

alter table GOODS
	add CONVERTIBLE_INTEGRAL NUMBER(18)
/

comment on column GOODS.CONVERTIBLE_INTEGRAL is '可兑换积分'
/

/***************************************************************/
-- 功能描述：客户表添加积分字段
-- 创建人员：sjl
-- 创建日期：2020-10-12
-- /**************************************************************/
alter table MEMBER
	add INTEGRAL NUMBER(18)
/

comment on column MEMBER.INTEGRAL is '积分'
/
