/***************************************************************/
-- 功能描述: 抽奖商品表
-- 创建人员：tang
-- 创建日期：2021-01-18
-- /**************************************************************/


-- Create table
create table LOTTERY_GOODS
(
  id            NUMBER(18) not null,
  erp_goods_id  NUMBER(18),
  lot_num       NUMBER(18),
  lot_total_num NUMBER(18),
  status        VARCHAR2(50),
  remark        VARCHAR2(1024),
  user_id       NUMBER(18),
  create_time   DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table LOTTERY_GOODS
  is '抽奖商品';
-- Add comments to the columns
comment on column LOTTERY_GOODS.id
  is 'id';
comment on column LOTTERY_GOODS.erp_goods_id
  is '商品id';
comment on column LOTTERY_GOODS.lot_num
  is '抽奖数量';
comment on column LOTTERY_GOODS.lot_total_num
  is '抽奖总数量';
comment on column LOTTERY_GOODS.status
  is '状态，上下架';
comment on column LOTTERY_GOODS.remark
  is '备注';
comment on column LOTTERY_GOODS.user_id
  is '创建人';
comment on column LOTTERY_GOODS.create_time
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table LOTTERY_GOODS
  add constraint LOTTERY_GOODS_PK primary key (ID)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;


-- Create sequence
create sequence SEQ_LOTTERY_GOODS
minvalue 1
maxvalue 9999999999999999999999999999
start with 1
increment by 1
cache 20
order;

-- Add/modify columns
alter table ERP_B2B_ORDER_REC_DOC add is_del NUMBER(18) default 0;
-- Add comments to the columns
comment on column ERP_B2B_ORDER_REC_DOC.is_del
  is '是否删除';


  -- Add/modify columns
  alter table ERP_B2B_ORDER_REC_DTL add is_del NUMBER(18) default 0;
  -- Add comments to the columns
  comment on column ERP_B2B_ORDER_REC_DTL.is_del
    is '是否删除';

    -- Add/modify columns
    alter table ERP_B2B_ORDER_REC_DOC add del_user_id NUMBER(18);
    alter table ERP_B2B_ORDER_REC_DOC add del_create_time date;
    -- Add comments to the columns
    comment on column ERP_B2B_ORDER_REC_DOC.del_user_id
      is '删除人';
    comment on column ERP_B2B_ORDER_REC_DOC.del_create_time
      is '删除时间';

      -- Add/modify columns
      alter table ERP_B2B_ORDER_REC_DOC rename column del_create_time to DEL_TIME;

      -- Add/modify columns
      alter table ERP_B2B_ORDER_REC_DOC add del_remark VARCHAR2(1024);
      -- Add comments to the columns
      comment on column ERP_B2B_ORDER_REC_DOC.del_remark
        is '删除备注';


        -- Add/modify columns
        alter table ERP_B2B_ORDER_REC_DOC add difference_amount NUMBER(18,2);
        -- Add comments to the columns
        comment on column ERP_B2B_ORDER_REC_DOC.difference_amount
          is '差异金额';

          -- Add/modify columns
          alter table ERP_B2B_ORDER_REC_DOC modify difference_amount default 0;