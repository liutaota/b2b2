/***************************************************************/
-- 功能描述:货品细单id
-- 创建人员：tang
-- 创建日期：2021-01-22
-- /**************************************************************/



-- Add/modify columns
alter table ORDER_GOODS add goods_dtl_id NUMBER(18);
-- Add comments to the columns
comment on column ORDER_GOODS.goods_dtl_id
  is '货品细单id';


-- Add/modify columns
alter table REFUND_DETAIL add goods_dtl_id NUMBER(18);
-- Add comments to the columns
comment on column REFUND_DETAIL.goods_dtl_id
  is '货品细单id';

  -- Add/modify columns
  alter table ERP_B2B_ORDER_REC_DOC add update_time date;
  -- Add comments to the columns
  comment on column ERP_B2B_ORDER_REC_DOC.update_time
    is '修改时间';