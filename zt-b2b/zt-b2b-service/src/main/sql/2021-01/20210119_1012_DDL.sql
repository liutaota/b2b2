/***************************************************************/
-- 功能描述: 下发版本号
-- 创建人员：tang
-- 创建日期：2021-01-19
-- /**************************************************************/

          -- Add/modify columns
          alter table ERP_B2B_ORDER_REC_DOC add to_erp_num NUMBER(18,2);
          -- Add comments to the columns
          comment on column ERP_B2B_ORDER_REC_DOC.to_erp_num
            is '下发版本（次数）';
