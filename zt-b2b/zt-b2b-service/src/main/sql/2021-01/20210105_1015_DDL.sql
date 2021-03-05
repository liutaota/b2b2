/***************************************************************/
-- 功能描述: erp收款单
-- 创建人员：tang
-- 创建日期：2021-01-05
-- /**************************************************************/

-- Add/modify columns
alter table TMP_ORDER_REC_DOC add type VARCHAR2(100);
alter table TMP_ORDER_REC_DOC add salesid VARCHAR2(100);
-- Add comments to the columns
comment on column TMP_ORDER_REC_DOC.type
  is '类型';
comment on column TMP_ORDER_REC_DOC.salesid
  is 'erpid';


  -- Add/modify columns
  alter table ACTIVITY add lot_end_time date;
  -- Add comments to the columns
  comment on column ACTIVITY.lot_end_time
    is '抽奖结束时间';


-- Add/modify columns
alter table LOTTER_DAIL_QUALIFICATIONS add lot_end_time date;
-- Add comments to the columns
comment on column LOTTER_DAIL_QUALIFICATIONS.lot_end_time
  is '抽奖结束时间';