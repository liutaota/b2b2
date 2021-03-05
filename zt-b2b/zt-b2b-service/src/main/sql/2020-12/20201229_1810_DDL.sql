/***************************************************************/
-- 功能描述: 订单快照表限购活动id
-- 创建人员：tang
-- 创建日期：2020-12-28
-- /**************************************************************/

-- Create table
create table TMP_ORDER_REC_DOC
(
  id          NUMBER(18),
  order_id    NUMBER(18),
  order_no    VARCHAR2(100),
  total       NUMBER(18,2),
  tmp_no      VARCHAR2(100),
  pay_flow_no VARCHAR2(100),
  pay_time    DATE,
  create_time DATE,
  user_id     NUMBER(18)
)
tablespace GDZT_SPACE
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table TMP_ORDER_REC_DOC
  is '现结账单';
-- Add comments to the columns
comment on column TMP_ORDER_REC_DOC.id
  is 'id';
comment on column TMP_ORDER_REC_DOC.order_id
  is '订单id';
comment on column TMP_ORDER_REC_DOC.order_no
  is '订单号';
comment on column TMP_ORDER_REC_DOC.total
  is '金额';
comment on column TMP_ORDER_REC_DOC.tmp_no
  is '支付单号';
comment on column TMP_ORDER_REC_DOC.pay_flow_no
  is '流水号';
comment on column TMP_ORDER_REC_DOC.pay_time
  is '支付时间';
comment on column TMP_ORDER_REC_DOC.create_time
  is '创建时间';
comment on column TMP_ORDER_REC_DOC.user_id
  is '创建人';



  -- Create sequence
  create sequence SEQ_TMP_ORDER_REC_DOC
  minvalue 1
  maxvalue 9999999999999999999999999999
  start with 1
  increment by 1
  cache 20
  order;


  -- Add/modify columns
  alter table TMP_ORDER_REC_DOC add pay_amount NUMBER(18,2);
  -- Add comments to the columns
  comment on column TMP_ORDER_REC_DOC.pay_amount
    is '支付金额';
