/***************************************************************/
-- 功能描述：支付表，收款表
-- 创建人员：tang
-- 创建日期：2020-11-06
-- /**************************************************************/

-- Create table
create table pay_order
(
  id           number(18),
  merchant_id  varchar2(100),
  merchant_key varchar2(100),
  order_no     varchar2(100),
  pay_flow_no  varchar2(100),
  fee_type     varchar2(100),
  total_fee    number(18),
  refund_fee   number(18),
  time_start   date,
  time_expire  date,
  time_end     date,
  status       varchar2(100),
  notify_url   varchar2(100),
  data_source  varchar2(100),
  pay_type     varchar2(100),
  meta_data    clob,
  create_time  date,
  update_time  date,
  body         varchar2(100)
)
;
-- Add comments to the table
comment on table pay_order
  is '支付订单';
-- Add comments to the columns
comment on column pay_order.id
  is '主键';
comment on column pay_order.merchant_id
  is '商户号';
comment on column pay_order.merchant_key
  is '商户key(appid)';
comment on column pay_order.order_no
  is '业务订单号';
comment on column pay_order.pay_flow_no
  is '支付订单号';
comment on column pay_order.fee_type
  is '币种';
comment on column pay_order.total_fee
  is '金额';
comment on column pay_order.refund_fee
  is '退款金额';
comment on column pay_order.time_start
  is '交易起始时间	';
comment on column pay_order.time_expire
  is '交易过期时间';
comment on column pay_order.time_end
  is '交易完成时间	';
comment on column pay_order.status
  is '状态';
comment on column pay_order.notify_url
  is '通知地址';
comment on column pay_order.data_source
  is '数据来源';
comment on column pay_order.pay_type
  is '支付方式';
comment on column pay_order.meta_data
  is '扩展内容';
comment on column pay_order.create_time
  is '创建时间';
comment on column pay_order.update_time
  is '更新时间';
comment on column pay_order.body
  is '商品描述	';
-- Create/Recreate primary, unique and foreign key constraints
alter table pay_order
  add constraint PAY_ORDER_PK primary key (ID);


-- Create sequence
create sequence SEQ_PAY_ORDER
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20
order;



-- Create table
create table pay_trade
(
  id            number(18),
  pay_order_id  number(18),
  pay_flow_no   VARCHAR2(100),
  merchant_id   VARCHAR2(100),
  create_time   date,
  request       VARCHAR2(100),
  refun_fee     number(18),
  refund_no     VARCHAR2(100),
  response      clob,
  out_refund_no VARCHAR2(100),
  status        VARCHAR2(100),
  remark        VARCHAR2(100)
)
;
-- Add comments to the table
comment on table pay_trade
  is '支付交易';
-- Add comments to the columns
comment on column pay_trade.id
  is '主键';
comment on column pay_trade.pay_order_id
  is '支付订单ID';
comment on column pay_trade.pay_flow_no
  is '支付订单号';
comment on column pay_trade.merchant_id
  is '商户号';
comment on column pay_trade.create_time
  is '创建时间';
comment on column pay_trade.request
  is '请求报文';
comment on column pay_trade.refun_fee
  is '退款金额';
comment on column pay_trade.refund_no
  is '退款单号';
comment on column pay_trade.response
  is '响应报文';
comment on column pay_trade.out_refund_no
  is '第三退款单号';
comment on column pay_trade.status
  is '状态';
comment on column pay_trade.remark
  is '备注';
-- Create/Recreate primary, unique and foreign key constraints
alter table pay_trade
  add constraint pay_trade_pk primary key (ID);


-- Create sequence
create sequence SEQ_PAY_TRADE
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20
order;



-- Create table
create table rec_doc
(
  id              number(18),
  entry_id        number(18),
  member_id       number(18),
  total           number(18),
  rec_method      varchar2(100),
  rec_type        varchar2(100),
  dtl_lines       number(18),
  premoney        number(18),
  status          varchar2(100),
  rec_user_id     number(18),
  remark          varchar2(100),
  pay_time        date,
  confirm_date    date,
  confirm_user_id number(18),
  create_time     date
)
;
-- Add comments to the table
comment on table rec_doc
  is '收款总单';
-- Add comments to the columns
comment on column rec_doc.id
  is '主键';
comment on column rec_doc.entry_id
  is '独立单元id';
comment on column rec_doc.member_id
  is '客户id';
comment on column rec_doc.total
  is '收款金额';
comment on column rec_doc.rec_method
  is '收款类别';
comment on column rec_doc.rec_type
  is '结算方式：在线支付，现金';
comment on column rec_doc.dtl_lines
  is '细单条数';
comment on column rec_doc.premoney
  is '细单金额';
comment on column rec_doc.status
  is '状态（已取消，待结算，已结算）';
comment on column rec_doc.rec_user_id
  is '收款人id（app端用）';
comment on column rec_doc.remark
  is '备注';
comment on column rec_doc.pay_time
  is '支付时间';
comment on column rec_doc.confirm_date
  is '确定时间';
comment on column rec_doc.confirm_user_id
  is '确定人';
comment on column rec_doc.create_time
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table rec_doc
  add constraint rec_doc_pk primary key (ID);


-- Create sequence
create sequence SEQ_REC_DOC
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20
order;


-- Create table
create table REC_DTL
(
  id              NUMBER(18) not null,
  sa_rec_id       NUMBER(18),
  total_line      NUMBER(18),
  unt_price       NUMBER(18),
  goods_id        NUMBER(18),
  goods_qty       NUMBER(18),
  rec_saler_id    NUMBER(18),
  invalid_flag    VARCHAR2(100),
  invalid_date    VARCHAR2(100),
  invalid_user_id NUMBER(18),
  saler_id        NUMBER(18),
  correct_flag    VARCHAR2(100),
  print_line      VARCHAR2(100),
  print_no        VARCHAR2(100),
  remark          VARCHAR2(100),
  create_time     DATE
)
tablespace GDZT_SPACE
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table REC_DTL
  is '收款细单';
-- Add comments to the columns
comment on column REC_DTL.id
  is '主键';
comment on column REC_DTL.sa_rec_id
  is '收款总单id';
comment on column REC_DTL.total_line
  is '收款金额';
comment on column REC_DTL.unt_price
  is '单价';
comment on column REC_DTL.goods_id
  is '货品id';
comment on column REC_DTL.goods_qty
  is '收款数量';
comment on column REC_DTL.rec_saler_id
  is '收款员id';
comment on column REC_DTL.invalid_flag
  is '作废标志';
comment on column REC_DTL.invalid_date
  is '作废日期';
comment on column REC_DTL.invalid_user_id
  is '作废人id';
comment on column REC_DTL.saler_id
  is '业务员id';
comment on column REC_DTL.correct_flag
  is '冲调标识';
comment on column REC_DTL.print_line
  is '打印列号';
comment on column REC_DTL.print_no
  is '打印号';
comment on column REC_DTL.remark
  is '备注';
comment on column REC_DTL.create_time
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table REC_DTL
  add constraint REC_DTL_PK primary key (ID)
  using index
  tablespace GDZT_SPACE
  pctfree 10
  initrans 2
  maxtrans 255;


-- Create sequence
create sequence SEQ_REC_DTL
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20
order;











