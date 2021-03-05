/***************************************************************/
-- 功能描述: erp收款单
-- 创建人员：tang
-- 创建日期：2021-01-08
-- /**************************************************************/

-- Add/modify columns
alter table ERP_B2B_ORDER_REC_DOC add finance_true NUMBER(11);
alter table ERP_B2B_ORDER_REC_DOC add erp_finance_remark VARCHAR2(100);
alter table ERP_B2B_ORDER_REC_DOC add finance_user_id NUMBER(18);
alter table ERP_B2B_ORDER_REC_DOC add finance_time date;
alter table ERP_B2B_ORDER_REC_DOC add finance_remark VARCHAR2(100);
-- Add comments to the columns
comment on column ERP_B2B_ORDER_REC_DOC.finance_true
  is '核销是否通过';
comment on column ERP_B2B_ORDER_REC_DOC.erp_finance_remark
  is 'erp核销备注';
comment on column ERP_B2B_ORDER_REC_DOC.finance_user_id
  is '核销操作人';
comment on column ERP_B2B_ORDER_REC_DOC.finance_time
  is '核销时间';
comment on column ERP_B2B_ORDER_REC_DOC.finance_remark
  is '核销备注';


-- Add/modify columns
alter table TMP_ORDER_REC_DOC add erp_user_id NUMBER(18);
-- Add comments to the columns
comment on column TMP_ORDER_REC_DOC.erp_user_id
  is 'erp客户id';