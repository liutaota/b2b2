/***************************************************************/
-- 功能描述:门店细单id
-- 创建人员：tang
-- 创建日期：2021-02-19
-- /**************************************************************/


-- Add/modify columns
alter table CART add src_order_dtl_id NUMBER(18);
-- Add comments to the columns
comment on column CART.src_order_dtl_id
  is '门店细单id';


  -- Add/modify columns
  alter table ORDER_GOODS add src_order_dtl_id NUMBER(18);
  -- Add comments to the columns
  comment on column ORDER_GOODS.src_order_dtl_id
    is '门店细单id';
