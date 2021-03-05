/***************************************************************/
-- 功能描述：自动收货sql
-- 创建人员：tang
-- 创建日期：2020-10-28
-- /**************************************************************/

INSERT INTO "B2B"."SETTING"("ID", "NAME", "VALUE", "CREATE_TIME", "REMARK", "TYPE", "SORT", "KEY", "USER_ID") VALUES ('6', '7天自动确认收货(分钟)', '10080', TO_DATE('2020-10-28 11:42:38', 'SYYYY-MM-DD HH24:MI:SS'), '7天自动确认收货(分钟)', 'SYSTEM', '6', 'ORDER_TO_RECEIVED', '121');
INSERT INTO "B2B"."SETTING"("ID", "NAME", "VALUE", "CREATE_TIME", "REMARK", "TYPE", "SORT", "KEY", "USER_ID") VALUES ('2', '下单满10赠送1积分', '10', TO_DATE('2020-10-26 13:39:05', 'SYYYY-MM-DD HH24:MI:SS'), '下单满10赠送1积分', 'SYSTEM', '1', 'ORDER_SCORE', '4');

