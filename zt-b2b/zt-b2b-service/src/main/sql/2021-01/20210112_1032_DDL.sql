/***************************************************************/
-- 功能描述: erp收款单-线路线序
-- 创建人员：tang
-- 创建日期：2021-01-12
-- /**************************************************************/
    -- Add/modify columns
    alter table ERP_B2B_ORDER_REC_DOC add tranposname VARCHAR2(1024);
    alter table ERP_B2B_ORDER_REC_DOC add transortno VARCHAR2(1024);
    -- Add comments to the columns
    comment on column ERP_B2B_ORDER_REC_DOC.tranposname
      is '线路';
    comment on column ERP_B2B_ORDER_REC_DOC.transortno
      is '线序';