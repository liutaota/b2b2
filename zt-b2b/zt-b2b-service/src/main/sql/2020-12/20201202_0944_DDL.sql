/***************************************************************/
-- 功能描述: 每日统计
-- 创建人员：tang
-- 创建日期：2020-12-02
-- /**************************************************************/


ALTER TABLE "B2B"."ORDER_INFO"
ADD ( "ERP_ORDER_AMOUNT" NUMBER(18) NULL  ) ;

COMMENT ON COLUMN "B2B"."ORDER_INFO"."ERP_AMOUNT" IS 'erp短减金额';

COMMENT ON COLUMN "B2B"."ORDER_INFO"."ERP_ORDER_AMOUNT" IS 'ERP发货单金额';


