/***************************************************************/
-- 功能描述：优惠券关联订单/优惠券来源
-- 创建人员：tang
-- 创建日期：2020-09-23
-- /**************************************************************/
alter table COUPON_RECEIVE
	add ORDER_ID NUMBER(18)
/

comment on column COUPON_RECEIVE.ORDER_ID is '订单id'
/

alter table COUPON_RECEIVE
	add SOURCE VARCHAR2(20)
/

comment on column COUPON_RECEIVE.SOURCE is '来源：ADMIN 后台赠 ORDER 下单赠'
/