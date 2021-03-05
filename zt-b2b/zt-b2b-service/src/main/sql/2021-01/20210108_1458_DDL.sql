/***************************************************************/
-- 功能描述: erp收款单
-- 创建人员：tang
-- 创建日期：2021-01-08
-- /**************************************************************/


-- Create table
create table ERP_B2B_ORDER_REC_DOC
(
  id           NUMBER(18) not null,
  erp_user_id  NUMBER(18),
  tmp_no       VARCHAR2(100),
  pay_time     DATE,
  online_total NUMBER(18,2),
  cash_total   NUMBER(18,2),
  pay_total    NUMBER(18,2),
  pay_flow_no  VARCHAR2(100),
  user_id      NUMBER(18),
  create_time  DATE,
  type         VARCHAR2(100)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table ERP_B2B_ORDER_REC_DOC
  is 'erpb2b收款单总表';
-- Add comments to the columns
comment on column ERP_B2B_ORDER_REC_DOC.id
  is 'id';
comment on column ERP_B2B_ORDER_REC_DOC.erp_user_id
  is '客户id';
comment on column ERP_B2B_ORDER_REC_DOC.tmp_no
  is '支付单号';
comment on column ERP_B2B_ORDER_REC_DOC.pay_time
  is '支付时间';
comment on column ERP_B2B_ORDER_REC_DOC.online_total
  is '应付金额';
comment on column ERP_B2B_ORDER_REC_DOC.cash_total
  is '现付金额';
comment on column ERP_B2B_ORDER_REC_DOC.pay_total
  is '实付金额';
comment on column ERP_B2B_ORDER_REC_DOC.pay_flow_no
  is '支付流水号';
comment on column ERP_B2B_ORDER_REC_DOC.user_id
  is '创建人';
comment on column ERP_B2B_ORDER_REC_DOC.create_time
  is '创建时间';
comment on column ERP_B2B_ORDER_REC_DOC.type
  is '类型';
-- Create/Recreate primary, unique and foreign key constraints
alter table ERP_B2B_ORDER_REC_DOC
  add constraint ERP_B2B_ORDER_REC_DOC_PK primary key (ID)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;


-- Create sequence
create sequence SEQ_ERP_B2B_ORDER_REC_DOC
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20
order;



-- Add/modify columns
alter table TMP_ORDER_REC_DOC add erp_b2b_order_rec_doc_id NUMBER(18);
-- Add comments to the columns
comment on column TMP_ORDER_REC_DOC.erp_b2b_order_rec_doc_id
  is 'erpb2b收款单总表id';


  -- Add/modify columns
  alter table TMP_ORDER_REC_DOC add satypeid VARCHAR2(100);
  -- Add comments to the columns
  comment on column TMP_ORDER_REC_DOC.satypeid
    is '销售单类型';