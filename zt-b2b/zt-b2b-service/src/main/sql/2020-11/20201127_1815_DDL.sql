/***************************************************************/
-- 功能描述: 拦截订单
-- 创建人员：tang
-- 创建日期：2020-11-26
-- /**************************************************************/

ALTER TABLE "B2B"."ORDER_INFO"
ADD ( "INTERCEPT_STATUS" VARCHAR2(255) NULL  )
ADD ( "INTERCEPT_TIME" DATE NULL  )
ADD ( "INTERCEPT_REMARK" VARCHAR2(1024) NULL  ) ;

COMMENT ON COLUMN "B2B"."ORDER_INFO"."INTERCEPT_STATUS" IS '拦截状态';

COMMENT ON COLUMN "B2B"."ORDER_INFO"."INTERCEPT_TIME" IS '拦截时间';

COMMENT ON COLUMN "B2B"."ORDER_INFO"."INTERCEPT_REMARK" IS '拦截备注';


update ORDER_INFO  set INTERCEPT_STATUS='NORMAL' ;

update order_info set REFUND_AMOUNT=0;

ALTER TABLE "B2B"."STATEMENT"
ADD ( "PAY_AMOUNT" NUMBER(18) NULL  ) ;

COMMENT ON COLUMN "B2B"."STATEMENT"."PAY_AMOUNT" IS '实际金额';