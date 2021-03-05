/***************************************************************/
-- 功能描述: erp收款单-退款
-- 创建人员：tang
-- 创建日期：2021-01-11
-- /**************************************************************/

    -- Add/modify columns
    alter table ERP_B2B_ORDER_REC_DOC add refund_state VARCHAR2(100);
    alter table ERP_B2B_ORDER_REC_DOC add refund_money NUMBER(18,2);
    alter table ERP_B2B_ORDER_REC_DOC add refund_user_id NUMBER(18);
    alter table ERP_B2B_ORDER_REC_DOC add refund_remark VARCHAR2(1024);
    alter table ERP_B2B_ORDER_REC_DOC add pay_refund_no VARCHAR2(1024);
    alter table ERP_B2B_ORDER_REC_DOC add abc_message VARCHAR2(1024);
    alter table ERP_B2B_ORDER_REC_DOC add refund_time date;
    -- Add comments to the columns
    comment on column ERP_B2B_ORDER_REC_DOC.refund_state
      is '退款状态';
    comment on column ERP_B2B_ORDER_REC_DOC.refund_money
      is '退款金额';
    comment on column ERP_B2B_ORDER_REC_DOC.refund_user_id
      is '退款操作人';
    comment on column ERP_B2B_ORDER_REC_DOC.refund_remark
      is '退款备注';
    comment on column ERP_B2B_ORDER_REC_DOC.pay_refund_no
      is '退款流水号';
    comment on column ERP_B2B_ORDER_REC_DOC.abc_message
      is '农行日志';
    comment on column ERP_B2B_ORDER_REC_DOC.refund_time
      is '退款时间';

-- Add/modify columns
alter table ERP_B2B_ORDER_REC_DOC add pay_abc_no VARCHAR2(100);
-- Add comments to the columns
comment on column ERP_B2B_ORDER_REC_DOC.pay_abc_no
  is '退款单号';

  update ERP_B2B_ORDER_REC_DOC set  REFUND_STATE='NO_REFUND';


  -- Add/modify columns
  alter table ERP_B2B_ORDER_REC_DOC add confirm_time date;
  alter table ERP_B2B_ORDER_REC_DOC add confirm_user_id NUMBER(18);
  alter table ERP_B2B_ORDER_REC_DOC add confirm_remark VARCHAR2(1024);
  -- Add comments to the columns
  comment on column ERP_B2B_ORDER_REC_DOC.confirm_time
    is '确定时间';
  comment on column ERP_B2B_ORDER_REC_DOC.confirm_user_id
    is '确定人';
  comment on column ERP_B2B_ORDER_REC_DOC.confirm_remark
    is '确定备注';