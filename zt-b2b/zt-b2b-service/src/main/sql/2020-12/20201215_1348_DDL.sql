/***************************************************************/
-- 功能描述: 加开发票状态
-- 创建人员：tang
-- 创建日期：2020-12-15
-- /**************************************************************/


-- Add/modify columns
alter table ORDER_INFO add fp_status VARCHAR2(1024);
-- Add comments to the columns
comment on column ORDER_INFO.fp_status
  is '开发票状态';

  update  order_info  set FP_STATUS='OFF';

  -- Add/modify columns
  alter table ORDER_INFO add pay_type VARCHAR2(1024);
  alter table ORDER_INFO add pay_type_doc VARCHAR2(1024);
  -- Add comments to the columns
  comment on column ORDER_INFO.pay_type
    is '支付交易码';
  comment on column ORDER_INFO.pay_type_doc
    is '支付交易码说明';