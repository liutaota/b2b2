/***************************************************************/
-- 功能描述：优惠券日志
-- 创建人员：tang
-- 创建日期：2020-10-12
-- /**************************************************************/


ALTER TABLE "B2B"."COUPON_LOG"
ADD ("ORDER_ID" NUMBER(18) )
ADD ("GIFT_NUM" NUMBER(11) );

COMMENT ON COLUMN "B2B"."COUPON_LOG"."ORDER_ID" IS '订单id';

COMMENT ON COLUMN "B2B"."COUPON_LOG"."GIFT_NUM" IS '赠送数量';


ALTER TABLE "B2B"."COUPON_RECEIVE"
MODIFY ("COUPON_CODE" VARCHAR2(50 BYTE) )