/***************************************************************/
-- 功能描述：触发器
-- 创建人员：tang
-- 创建日期：2020-11-06
-- /**************************************************************/

--活动策略表
CREATE OR REPLACE TRIGGER ACTIVITY_STRATEGY_TEST
 BEFORE UPDATE ON ACTIVITY_STRATEGY
 FOR EACH ROW
BEGIN
 INSERT INTO  ACTIVITY_Log(AC_ID, AS_ID,NUM,PRICE,CREATE_TIME,REDUCED_AMOUNT,DISCOUNT,COUPON_ID,GIFT_NUM)
 VALUES (:old.ACTIVITY_ID, :old.ID,:old.MEET_QUANTITY,:old.AMOUNT_SATISFIED,SYSDATE,:old.REDUCED_AMOUNT,:old.DISCOUNT,:old.COUPON_ID,:old.GIFT_NUM);
END;





--活动商品表
CREATE OR REPLACE TRIGGER ACTIVITY_GOODS_TEST
 BEFORE UPDATE ON ACTIVITY_GOODS
 FOR EACH ROW
BEGIN
 INSERT INTO  ACTIVITY_Log(AC_ID, AS_ID,NUM,PRICE,CREATE_TIME,ERP_GOODS_ID,GOODS_PRICE,ACTIVITY_GOODS_ID)
 VALUES (:old.ACTIVITY_ID, :old.AS_ID,:old.CONDITION_NUM,:old.CONDITION_PRICE,SYSDATE,:old.ERP_GOODS_ID,:old.GOODS_PRICE,:old.id);
END;




--活动赠品表
CREATE OR REPLACE TRIGGER ACTIVITY_GIFT_TEST
 BEFORE UPDATE ON ACTIVITY_GIFT
 FOR EACH ROW
BEGIN
 INSERT INTO  ACTIVITY_Log(AC_ID, AS_ID,CREATE_TIME,ERP_GOODS_ID,GOODS_PRICE,ACTIVITY_GIFT_ID,GIFT_NUM)
 VALUES (:old.ACTIVITY_ID, :old.AS_ID,SYSDATE,:old.ERP_GOODS_ID,:old.GOODS_PRICE,:old.id,:old.GIFT_NUM);
END;





-- Create table
create table ACTIVITY_LOG
(
  ac_id             NUMBER(18),
  as_id             NUMBER(18),
  erp_goods_id      NUMBER(18),
  num               NUMBER(18),
  price             NUMBER(18),
  goods_price       NUMBER(18),
  create_time       DATE,
  reduced_amount    NUMBER(18),
  discount          NUMBER(18),
  coupon_id         NUMBER(18),
  gift_num          NUMBER(18),
  activity_gift_id  NUMBER(18),
  activity_goods_id NUMBER(18)
)
tablespace GDZT_SPACE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the columns
comment on column ACTIVITY_LOG.ac_id
  is '活动id';
comment on column ACTIVITY_LOG.as_id
  is '策略id';
comment on column ACTIVITY_LOG.erp_goods_id
  is '商品id';
comment on column ACTIVITY_LOG.num
  is '满足数量';
comment on column ACTIVITY_LOG.price
  is '满足金额';
comment on column ACTIVITY_LOG.goods_price
  is '商品价格';
comment on column ACTIVITY_LOG.create_time
  is '创建时间';
comment on column ACTIVITY_LOG.reduced_amount
  is '减少的金额';
comment on column ACTIVITY_LOG.discount
  is '折扣';
comment on column ACTIVITY_LOG.coupon_id
  is '优惠券id';
comment on column ACTIVITY_LOG.gift_num
  is '赠送数量';
comment on column ACTIVITY_LOG.activity_gift_id
  is '赠品id';
comment on column ACTIVITY_LOG.activity_goods_id
  is '活动商品id';
