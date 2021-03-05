/***************************************************************/
-- 功能描述：正式环境要改的数据
-- 创建人员：tang
-- 创建日期：2020-09-29
-- /**************************************************************/

--数量相关*10000
update ORDER_GOODS set SELL_NUM=SELL_NUM*10000,GOODS_NUM=GOODS_NUM*10000,ERP_LEASTSALEQTY=ERP_LEASTSALEQTY*10000 where SELL_NUM>0;


--最小销售数量*10000

--erp_validity_goods_v
 t1.zx_b2b_num_limit*10000 zx_b2b_num_limit  --B2B最小销售数量

--erp_goods_v/erp_admin_goods_v
  (CASE
          WHEN sum(t1.zx_b2b_num_limit)*10000 IS NULL THEN
           10000
          ELSE
           sum(t1.zx_b2b_num_limit)*10000
        END) zx_b2b_num_limit, --B2B最小销售数量



