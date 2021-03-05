/***************************************************************/
-- 功能描述: 订单快照表限购活动id
-- 创建人员：tang
-- 创建日期：2020-12-28
-- /**************************************************************/


-- Add/modify columns
alter table ORDER_GOODS add purchase_id NUMBER(18);
-- Add comments to the columns
comment on column ORDER_GOODS.purchase_id
  is '限购活动id';


  -- Add/modify columns
  alter table SETTING add update_time date;
  alter table SETTING add update_user_id NUMBER(18);
  -- Add comments to the columns
  comment on column SETTING.update_time
    is '修改时间';
  comment on column SETTING.update_user_id
    is '修改人';

-- Add/modify columns
alter table USER_INFO add zx_ph_order VARCHAR2(1024);
-- Add comments to the columns
comment on column USER_INFO.TRANSLINENAME
  is '运输路线';