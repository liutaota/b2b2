/***************************************************************/
-- 功能描述: 异常处理备注
-- 创建人员：tang
-- 创建日期：2020-11-16
-- /**************************************************************/

ALTER TABLE "B2B"."ORDER_INFO"
ADD ("USER_REMARK" VARCHAR2(1024) )
ADD ("IS_TRUE" VARCHAR2(255) DEFAULT 0 );

COMMENT ON COLUMN "B2B"."ORDER_INFO"."USER_REMARK" IS '处理备注';

COMMENT ON COLUMN "B2B"."ORDER_INFO"."IS_TRUE" IS '是否已处理'

update order_info set is_true=0 ;