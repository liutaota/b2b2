/***************************************************************/
-- 功能描述: 新增 账单支付订单号
-- 创建人员：tang
-- 创建日期：2020-11-26
-- /**************************************************************/

-- Add/modify columns
alter table STATEMENT add pay_order_no VARCHAR2(100);
-- Add comments to the columns
comment on column STATEMENT.pay_order_no
  is '支付订单号';


  -- Add/modify columns
  alter table LICENCE modify valid_on_time VARCHAR2(20);
  alter table LICENCE modify valid_end_time VARCHAR2(20);


  -- Add/modify columns
  alter table LICENCE_APPLY modify valid_on_time VARCHAR2(20);
  alter table LICENCE_APPLY modify valid_end_time VARCHAR2(20);