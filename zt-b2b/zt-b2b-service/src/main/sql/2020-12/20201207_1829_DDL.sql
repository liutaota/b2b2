/***************************************************************/
-- 功能描述: 账单加还款日期
-- 创建人员：tang
-- 创建日期：2020-12-07
-- /**************************************************************/


ALTER TABLE "B2B"."STATEMENT"
ADD ( "PAY_STATEMENT_TIME" DATE NULL  ) ;

COMMENT ON COLUMN "B2B"."STATEMENT"."PAY_STATEMENT_TIME" IS '还款日期';


INSERT INTO "B2B"."SETTING" ("ID", "NAME", "VALUE", "CREATE_TIME", "REMARK", "TYPE", "SORT", "KEY", "USER_ID") VALUES ('9', '最长还款日期', :"VALUE", TO_DATE('2020-12-07 18:35:29', 'SYYYY-MM-DD HH24:MI:SS'), '最长还款日期', 'SYSTEM', '24', 'PAY_STATEMENT_DATE', '8');
