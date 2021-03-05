/***************************************************************/
-- 功能描述: 每日统计
-- 创建人员：tang
-- 创建日期：2020-12-02
-- /**************************************************************/

ALTER TABLE "B2B"."ORDER_INFO"
ADD ( "SENT_TIME" DATE NULL  ) ;

COMMENT ON COLUMN "B2B"."ORDER_INFO"."SENT_TIME" IS '送货时间';


ALTER TABLE "B2B"."ORDER_INFO"
ADD ( "SENT_USER_ID" NUMBER(18) NULL  ) ;

COMMENT ON COLUMN "B2B"."ORDER_INFO"."SENT_USER_ID" IS '收款人';