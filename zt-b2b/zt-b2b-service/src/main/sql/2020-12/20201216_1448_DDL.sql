/***************************************************************/
-- 功能描述: 报表
-- 创建人员：tang
-- 创建日期：2020-12-16
-- /**************************************************************/


    -- Add/modify columns
    alter table DAY_BILL add reduction_num NUMBER(20);
    alter table DAY_BILL add rec_doc_amount NUMBER(20);
    -- Add comments to the columns
    comment on column DAY_BILL.reduction_num
      is '短减商品数量（除以最小销售数量）';
    comment on column DAY_BILL.rec_doc_amount
      is '账单收款额';