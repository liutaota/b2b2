/***************************************************************/
-- 功能描述: 抽奖表
-- 创建人员：tang
-- 创建日期：2020-12-30
-- /**************************************************************/

-- Create table
create table LOTTERY_DIAL_DETAIL
(
  id           NUMBER(18) not null,
  lot_id       NUMBER(18),
  member_id    NUMBER(18),
  member_name  VARCHAR2(1024),
  rate_name    VARCHAR2(1024),
  rate_type    VARCHAR2(1024),
  prize_info   VARCHAR2(1024),
  prize_state  VARCHAR2(1024),
  prize_time   DATE,
  prize_member NUMBER(18),
  prize_remark VARCHAR2(1024),
  erp_goods_id NUMBER(18),
  create_time  DATE
)
tablespace GDZT_SPACE
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table LOTTERY_DIAL_DETAIL
  is '转盘抽奖详情';
-- Add comments to the columns
comment on column LOTTERY_DIAL_DETAIL.id
  is 'id';
comment on column LOTTERY_DIAL_DETAIL.lot_id
  is '活动id';
comment on column LOTTERY_DIAL_DETAIL.member_id
  is '客户id';
comment on column LOTTERY_DIAL_DETAIL.member_name
  is '客户名称';
comment on column LOTTERY_DIAL_DETAIL.rate_name
  is '奖项名称';
comment on column LOTTERY_DIAL_DETAIL.rate_type
  is '奖项类型';
comment on column LOTTERY_DIAL_DETAIL.prize_info
  is '奖品信息';
comment on column LOTTERY_DIAL_DETAIL.prize_state
  is '派奖状态';
comment on column LOTTERY_DIAL_DETAIL.prize_time
  is '派奖时间';
comment on column LOTTERY_DIAL_DETAIL.prize_member
  is '发奖人';
comment on column LOTTERY_DIAL_DETAIL.prize_remark
  is '派奖备注';
comment on column LOTTERY_DIAL_DETAIL.erp_goods_id
  is '商品id';
comment on column LOTTERY_DIAL_DETAIL.create_time
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table LOTTERY_DIAL_DETAIL
  add constraint LOTTERY_DIAL_DETAIL_PK primary key (ID)
  using index
  tablespace GDZT_SPACE
  pctfree 10
  initrans 2
  maxtrans 255;



-- Create sequence
create sequence SEQ_LOTTERY_DIAL_DETAIL
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20
order;


-- Create table
create table LOTTER_DAIL_QUALIFICATIONS
(
  id          NUMBER(18) not null,
  lot_id      NUMBER(18),
  member_id   NUMBER(18),
  lot_type    VARCHAR2(50),
  lot_num     NUMBER(18),
  create_time DATE
)
tablespace GDZT_SPACE
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table LOTTER_DAIL_QUALIFICATIONS
  is '可抽奖次数';
-- Add comments to the columns
comment on column LOTTER_DAIL_QUALIFICATIONS.id
  is 'id';
comment on column LOTTER_DAIL_QUALIFICATIONS.lot_id
  is '活动id';
comment on column LOTTER_DAIL_QUALIFICATIONS.member_id
  is '客户id';
comment on column LOTTER_DAIL_QUALIFICATIONS.lot_type
  is '抽奖活动类型';
comment on column LOTTER_DAIL_QUALIFICATIONS.lot_num
  is '可抽奖次数';
comment on column LOTTER_DAIL_QUALIFICATIONS.create_time
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table LOTTER_DAIL_QUALIFICATIONS
  add constraint LOTTER_DAIL_QUALIFICATIONS_PK primary key (ID)
  using index
  tablespace GDZT_SPACE
  pctfree 10
  initrans 2
  maxtrans 255;



-- Create sequence
create sequence SEQ_LOTTER_DAIL_QUALIFICATIONS
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20
order;


-- Create table
create table LOTTERY_LOG
(
  id          NUMBER(18) not null,
  member_id   NUMBER(18),
  lot_id      NUMBER(18),
  create_time DATE,
  type        VARCHAR2(50)
)
tablespace GDZT_SPACE
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table LOTTERY_LOG
  is '抽奖日志表';
-- Add comments to the columns
comment on column LOTTERY_LOG.id
  is 'id';
comment on column LOTTERY_LOG.member_id
  is '客户id';
comment on column LOTTERY_LOG.lot_id
  is '活动id';
comment on column LOTTERY_LOG.create_time
  is '创建时间';
comment on column LOTTERY_LOG.type
  is '类型';
-- Create/Recreate primary, unique and foreign key constraints
alter table LOTTERY_LOG
  add constraint LOTTERY_LOG_PK primary key (ID)
  using index
  tablespace GDZT_SPACE
  pctfree 10
  initrans 2
  maxtrans 255;


-- Create sequence
create sequence SEQ_LOTTERY_LOG
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20
order;


-- Add/modify columns
alter table GOODS add is_lot NUMBER(18) default 0;
-- Add comments to the columns
comment on column GOODS.is_lot
  is '是否参与抽奖';


  -- Add/modify columns
  alter table ACTIVITY_STRATEGY add lot_num NUMBER(11);
  -- Add comments to the columns
  comment on column ACTIVITY_STRATEGY.lot_num
    is '抽奖次数';

    -- Add/modify columns
    alter table ACTIVITY_STRATEGY modify lot_num default 0;