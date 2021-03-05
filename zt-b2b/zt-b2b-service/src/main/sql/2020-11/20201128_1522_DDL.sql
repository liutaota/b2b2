/***************************************************************/
-- 功能描述: 新增拦截订单表
-- 创建人员：tang
-- 创建日期：2020-11-28
-- /**************************************************************/



-- Create table
create table MANUALLY_INTERCEPT
(
  id             NUMBER(18) not null,
  b2b_order_no   VARCHAR2(100),
  erp_order_no   VARCHAR2(100),
  status         VARCHAR2(100),
  create_time    DATE,
  deal_with_time DATE,
  remark         VARCHAR2(100)
)
tablespace GDZT_SPACE
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table MANUALLY_INTERCEPT
  is '手工拦截';
-- Add comments to the columns
comment on column MANUALLY_INTERCEPT.id
  is 'id';
comment on column MANUALLY_INTERCEPT.b2b_order_no
  is 'b2b订单号';
comment on column MANUALLY_INTERCEPT.erp_order_no
  is 'erp订单号';
comment on column MANUALLY_INTERCEPT.status
  is '状态';
comment on column MANUALLY_INTERCEPT.create_time
  is '创建时间';
comment on column MANUALLY_INTERCEPT.deal_with_time
  is '处理时间';
comment on column MANUALLY_INTERCEPT.remark
  is '备注';
-- Create/Recreate primary, unique and foreign key constraints
alter table MANUALLY_INTERCEPT
  add constraint MANUALLY_INTERCEPT_PK primary key (ID)
  using index
  tablespace GDZT_SPACE
  pctfree 10
  initrans 2
  maxtrans 255;




-- Create sequence
create sequence SEQ_MANUALLY_INTERCEPT
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20
order;