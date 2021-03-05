/***************************************************************/
-- 功能描述：订单表-->优惠券id
-- 创建人员：tang
-- 创建日期：2020-10-10
-- /**************************************************************/


-- Add/modify columns
alter table ORDER_INFO add coupon_id NUMBER(18);
-- Add comments to the columns
comment on column ORDER_INFO.coupon_id
  is '优惠券id';