/***************************************************************/
-- 功能描述：订单快照表加备注
-- 创建人员：tang
-- 创建日期：2020-10-12
-- /**************************************************************/


ALTER TABLE "B2B"."ORDER_GOODS"
ADD ("REMARK" VARCHAR2(500) );

COMMENT ON COLUMN "B2B"."ORDER_GOODS"."REMARK" IS '备注'