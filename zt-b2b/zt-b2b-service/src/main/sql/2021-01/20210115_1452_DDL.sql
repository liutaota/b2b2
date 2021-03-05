/***************************************************************/
-- 功能描述: 门店id
-- 创建人员：tang
-- 创建日期：2021-01-15
-- /**************************************************************/


-- Add/modify columns
alter table CART add store_id NUMBER(18) default 0;
-- Add comments to the columns
comment on column CART.store_id
  is '门店id';


-- Add/modify columns
alter table ORDER_GOODS add store_id NUMBER(18);
-- Add comments to the columns
comment on column ORDER_GOODS.store_id
  is '门店id';


-- Add/modify columns
alter table ORDER_INFO add src_order_id NUMBER(18);
alter table ORDER_INFO add src_order_no VARCHAR2(1024);
-- Add comments to the columns
comment on column ORDER_INFO.src_order_id
  is '门店订单id';
comment on column ORDER_INFO.src_order_no
  is '门店订单号';

  -- Add/modify columns
  alter table ORDER_INFO add src_order_time date;
  -- Add comments to the columns
  comment on column ORDER_INFO.src_order_time
    is '门店订单时间';