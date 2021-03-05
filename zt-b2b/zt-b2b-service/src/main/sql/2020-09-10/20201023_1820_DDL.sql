/***************************************************************/
-- 功能描述：购物车临时表
-- 创建人员：tang
-- 创建日期：2020-10-23
-- /**************************************************************/


-- Create table
create table cart_gift_tmp
(
  id           number,
  member_id    number,
  erp_goods_id number,
  goods_id     number,
  ac_id        number,
  as_id        number,
  goods_num    number,
  create_time  date
)
;
-- Add comments to the table
comment on table cart_gift_tmp
  is '购物车商品赠品临时表';
-- Add comments to the columns
comment on column cart_gift_tmp.id
  is 'id';
comment on column cart_gift_tmp.member_id
  is '客户id';
comment on column cart_gift_tmp.erp_goods_id
  is 'erp商品id';
comment on column cart_gift_tmp.goods_id
  is '商品id';
comment on column cart_gift_tmp.ac_id
  is '活动id';
comment on column cart_gift_tmp.as_id
  is '策略id';
comment on column cart_gift_tmp.goods_num
  is '商品数量';
comment on column cart_gift_tmp.create_time
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table cart_gift_tmp
  add constraint cart_gift_tmp_pk primary key (ID);



ALTER TABLE "B2B"."CART_GIFT_TMP"
MODIFY ("ID" NUMBER(18) )
MODIFY ("MEMBER_ID" NUMBER(18) )
MODIFY ("ERP_GOODS_ID" NUMBER(18) )
MODIFY ("GOODS_ID" NUMBER(18) )
MODIFY ("AC_ID" NUMBER(18) )
MODIFY ("AS_ID" NUMBER(18) )
MODIFY ("GOODS_NUM" NUMBER(18) )
ADD ("NUM" NUMBER(18) );

COMMENT ON COLUMN "B2B"."CART_GIFT_TMP"."NUM" IS '可选数量';


-- Create sequence
create sequence SEQ_CART_GIFT_TMP
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20
order;



ALTER TABLE "B2B"."ORDER_INFO"
ADD ("COUPON_IDS" VARCHAR2(255) );

COMMENT ON COLUMN "B2B"."ORDER_INFO"."COUPON_IDS" IS '多张优惠券';