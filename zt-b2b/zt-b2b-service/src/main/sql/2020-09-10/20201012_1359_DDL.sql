/***************************************************************/
-- 功能描述：用户注册申请-->时间字段/限时秒杀--楼层
-- 创建人员：tang
-- 创建日期：2020-10-12
-- /**************************************************************/


ALTER TABLE "B2B"."MEMBER_APPLY" RENAME COLUMN "CREAT_TIME" TO "CREATE_TIME";


--限时秒杀--楼层
INSERT INTO "B2B"."FLOOR"("ID", "TYPE", "TITLE", "META_DATA", "CREATE_TIME", "UPDATE_TIME", "CREATE_USER_ID", "UPDATE_USER_ID", "IS_DEL", "GOODS_NUM", "FLOOR_KEY", "HUE", "IS_USE", "FLOOR_SORT") VALUES ('3', 'SINGLE_LINE', '限时秒杀', '{}', TO_DATE('2020-10-12 15:20:41', 'SYYYY-MM-DD HH24:MI:SS'), TO_DATE('2020-10-12 15:20:45', 'SYYYY-MM-DD HH24:MI:SS'), '115', '115', '0', '20', 'XSMS', NULL, 'ON', '11');


INSERT INTO "B2B"."FLOOR_CUSTOMER_SET"("ID", "FLOOR_ID", "CUSTOMER_SET_ID", "TYPE", "CREATE_TIME") VALUES ('3', '3', NULL, 'ALL_VISIBLE', TO_DATE('2020-10-12 15:51:29', 'SYYYY-MM-DD HH24:MI:SS'));

