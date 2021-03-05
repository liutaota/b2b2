/***************************************************************/
-- 功能描述: 订单中间表
-- 创建人员：tang
-- 创建日期：2020-12-07
-- /**************************************************************/



-- Create table
create table IN_ORDER_DATE
(
  id              NUMBER(20) not null,
  order_no        VARCHAR2(100),
  create_time     DATE,
  erp_customer_id NUMBER(18),
  member_id       NUMBER(18)
)
tablespace GDZT_SPACE
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table IN_ORDER_DATE
  is '订单中间表';
-- Add comments to the columns
comment on column IN_ORDER_DATE.id
  is 'id';
comment on column IN_ORDER_DATE.order_no
  is '订单号';
comment on column IN_ORDER_DATE.create_time
  is '创建时间';
comment on column IN_ORDER_DATE.erp_customer_id
  is '客户id';
comment on column IN_ORDER_DATE.member_id
  is 'b2b客户id';
-- Create/Recreate primary, unique and foreign key constraints
alter table IN_ORDER_DATE
  add constraint IN_ORDER_DATE_PK primary key (ID)
  using index
  tablespace GDZT_SPACE
  pctfree 10
  initrans 2
  maxtrans 255;




-- Create sequence
create sequence SEQ_IN_ORDER_DATE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20
order;
