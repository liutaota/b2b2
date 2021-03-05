/***************************************************************/
-- 功能描述：优惠券商品可见集合类型
-- 创建人员：sjl
-- 创建日期：2020-10-12
-- /**************************************************************/
alter table COUPON_GOODS
	add VISIBLE_TYPE VARCHAR2(50)
/

comment on column COUPON_GOODS.VISIBLE_TYPE is '可见类型'
/

/***************************************************************/
-- 功能描述：优惠券客户可见集合类型
-- 创建人员：sjl
-- 创建日期：2020-10-12
-- /**************************************************************/
alter table COUPON_MEMBER
	add VISIBLE_TYPE VARCHAR2(50)
/

comment on column COUPON_MEMBER.VISIBLE_TYPE is '可见类型'
/