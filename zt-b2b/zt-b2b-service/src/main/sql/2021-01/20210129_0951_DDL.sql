/***************************************************************/
-- 功能描述:货品细单id
-- 创建人员：tang
-- 创建日期：2021-01-22
-- /**************************************************************/




  -- Add/modify columns
  alter table ERP_B2B_ORDER_REC_DTL add SOURCETYPE NUMBER(18);
  -- Add comments to the columns
  comment on column ERP_B2B_ORDER_REC_DTL.SOURCETYPE
    is '1是销售类，2是配送类,3是销配退类';