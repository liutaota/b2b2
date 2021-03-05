/***************************************************************/
-- 功能描述: 每日统计
-- 创建人员：tang
-- 创建日期：2020-12-02
-- /**************************************************************/


-- Create table
create table DAY_BILL
(
  id                  NUMBER(20) not null,
  bill_no             VARCHAR2(100),
  start_time          DATE,
  end_time            DATE,
  order_amount        NUMBER(20),
  payable_amount      NUMBER(20),
  case_amount         NUMBER(20),
  monthly_amount      NUMBER(20),
  online_amount       NUMBER(20),
  refund_amount       NUMBER(20),
  reduction_amount    NUMBER(20),
  promotion_amount    NUMBER(20),
  order_num           NUMBER(20),
  return_order_num    NUMBER(20),
  exception_order_num NUMBER(20),
  remark              VARCHAR2(100),
  create_time         DATE,
  bill_date           VARCHAR2(100)
)
tablespace GDZT_SPACE
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table DAY_BILL
  is '每日报表';
-- Add comments to the columns
comment on column DAY_BILL.id
  is 'id';
comment on column DAY_BILL.bill_no
  is '报表编号';
comment on column DAY_BILL.start_time
  is '开始时间';
comment on column DAY_BILL.end_time
  is '结束时间';
comment on column DAY_BILL.order_amount
  is '订单总额';
comment on column DAY_BILL.payable_amount
  is '应付金额';
comment on column DAY_BILL.case_amount
  is '现结金额';
comment on column DAY_BILL.monthly_amount
  is '月结金额';
comment on column DAY_BILL.online_amount
  is '在线支付金额';
comment on column DAY_BILL.refund_amount
  is '退款金额';
comment on column DAY_BILL.reduction_amount
  is '短减金额';
comment on column DAY_BILL.promotion_amount
  is '促销金额';
comment on column DAY_BILL.order_num
  is '订单数';
comment on column DAY_BILL.return_order_num
  is '退单数';
comment on column DAY_BILL.exception_order_num
  is '异常订单数';
comment on column DAY_BILL.remark
  is '备注';
comment on column DAY_BILL.create_time
  is '创建时间';
comment on column DAY_BILL.bill_date
  is '订单日期';
-- Create/Recreate primary, unique and foreign key constraints
alter table DAY_BILL
  add constraint DAY_BILL_PK primary key (ID)
  using index
  tablespace GDZT_SPACE
  pctfree 10
  initrans 2
  maxtrans 255;


-- Create sequence
create sequence SEQ_DAY_BILL
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20
order;
