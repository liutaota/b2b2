/***************************************************************/
-- 功能描述：订单积分
-- 创建人员：tang
-- 创建日期：2020-10-26
-- /**************************************************************/


ALTER TABLE "B2B"."ORDER_INFO"
ADD ("USE_SCORE" NUMBER(18) )
ADD ("SEND_SCORE" NUMBER(18) );

COMMENT ON COLUMN "B2B"."ORDER_INFO"."USE_SCORE" IS '使用积分';

COMMENT ON COLUMN "B2B"."ORDER_INFO"."SEND_SCORE" IS '赠送积分';


ALTER TABLE "B2B"."ORDER_GOODS"
ADD ("GOODS_SCORE" NUMBER(18) );

COMMENT ON COLUMN "B2B"."ORDER_GOODS"."GOODS_SCORE" IS '商品积分';

--初始化客户积分--0
update member set INTEGRAL=0 where INTEGRAL is null;

INSERT INTO "B2B"."SETTING"("ID", "NAME", "VALUE", "CREATE_TIME", "REMARK", "TYPE", "SORT", "KEY", "USER_ID") VALUES ('2', '下单满1000赠送10积分', '1000', TO_DATE('2020-10-26 13:39:05', 'SYYYY-MM-DD HH24:MI:SS'), '下单满1000赠送10积分', 'SYSTEM', '1', 'ORDER_SCORE', '4');



-- Alter sequence
alter sequence SEQ_SCORE_RECORD
cycle
order;


ALTER TABLE "B2B"."SCORE_RECORD" RENAME COLUMN "MEMBER__ID" TO "MEMBER_ID";