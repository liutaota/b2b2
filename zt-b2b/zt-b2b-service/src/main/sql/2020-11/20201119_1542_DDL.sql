/***************************************************************/
-- 功能描述: 账单
-- 创建人员：tang
-- 创建日期：2020-11-19
-- /**************************************************************/

-- Create table
create table STATEMENT
(
  id          NUMBER(18) not null,
  member_id   NUMBER(18),
  totel       NUMBER(18),
  lines       NUMBER(18),
  pay_type    VARCHAR2(100),
  source      VARCHAR2(100),
  pay_flow_no VARCHAR2(100),
  pay_time    DATE,
  status      VARCHAR2(100),
  remark      VARCHAR2(100),
  create_time DATE
)
tablespace GDZT_SPACE
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table STATEMENT
  is '账单';
-- Add comments to the columns
comment on column STATEMENT.id
  is 'id';
comment on column STATEMENT.member_id
  is '用户id';
comment on column STATEMENT.totel
  is '账单金额';
comment on column STATEMENT.lines
  is '账单细单条数';
comment on column STATEMENT.pay_type
  is '支付类型';
comment on column STATEMENT.source
  is '来源（1号自动生成，手动生成）';
comment on column STATEMENT.pay_flow_no
  is '支付流水号';
comment on column STATEMENT.pay_time
  is '支付时间';
comment on column STATEMENT.status
  is '状态：支付，未支付';
comment on column STATEMENT.remark
  is '备注';
comment on column STATEMENT.create_time
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table STATEMENT
  add constraint STATEMENT_PK primary key (ID)
  using index
  tablespace GDZT_SPACE
  pctfree 10
  initrans 2
  maxtrans 255;


-- Create sequence
create sequence SEQ_STATEMENT
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20
order;





-- Create table
create table STATEMENT_REC_DOC
(
  id           NUMBER(18) not null,
  statement_id NUMBER(18),
  rec_doc_id   NUMBER(18),
  create_time  DATE
)
tablespace GDZT_SPACE
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table STATEMENT_REC_DOC
  is '账单-收款单中间表';
-- Add comments to the columns
comment on column STATEMENT_REC_DOC.id
  is 'id';
comment on column STATEMENT_REC_DOC.statement_id
  is '账单id';
comment on column STATEMENT_REC_DOC.rec_doc_id
  is '收款单id';
comment on column STATEMENT_REC_DOC.create_time
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table STATEMENT_REC_DOC
  add constraint STATEMENT_REC_DOC_PK primary key (ID)
  using index
  tablespace GDZT_SPACE
  pctfree 10
  initrans 2
  maxtrans 255;

-- Create sequence
create sequence SEQ_STATEMENT_REC_DOC
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20
order;
