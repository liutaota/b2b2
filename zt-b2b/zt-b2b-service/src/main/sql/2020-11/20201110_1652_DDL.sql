/***************************************************************/
-- 功能描述：收款单表-订单id
-- 创建人员：tang
-- 创建日期：2020-11-10
-- /**************************************************************/



ALTER TABLE "B2B"."REC_DOC"
ADD ("ORDER_ID" NUMBER(18) );

COMMENT ON COLUMN "B2B"."REC_DOC"."ORDER_ID" IS '订单id';


ALTER TABLE "B2B"."REC_DTL"
ADD ("ERP_LEASTSALEQTY" NUMBER(18) );

COMMENT ON COLUMN "B2B"."REC_DTL"."ERP_LEASTSALEQTY" IS '最小销售数量'