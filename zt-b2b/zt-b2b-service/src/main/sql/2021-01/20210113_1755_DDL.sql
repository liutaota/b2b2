/***************************************************************/
-- 功能描述: erp收款单-支付类型
-- 创建人员：tang
-- 创建日期：2021-01-13
-- /**************************************************************/

      -- Add/modify columns
      alter table ERP_B2B_ORDER_REC_DOC add pay_type VARCHAR2(1024);
      -- Add comments to the columns
      comment on column ERP_B2B_ORDER_REC_DOC.pay_type
        is '支付类型';