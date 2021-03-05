/***************************************************************/
-- 功能描述：修正字段歧义
-- 创建人员：tang
-- 创建日期：2020-09-10
-- /**************************************************************/

ALTER TABLE "B2B"."SYS_ROLE" RENAME COLUMN "USER_ID" TO "CREATE_USER_ID";

ALTER TABLE "B2B"."ROLE_PERMISSION" RENAME COLUMN "USER_ID" TO "CREATE_USER_ID";

ALTER TABLE "B2B"."USER_ROLE" RENAME COLUMN "USER_ID" TO "CREATE_USER_ID";