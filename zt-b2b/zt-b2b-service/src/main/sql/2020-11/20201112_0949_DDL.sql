/***************************************************************/
-- 功能描述: 广告，楼层，页面，菜单---加备注
-- 创建人员：tang
-- 创建日期：2020-11-12
-- /**************************************************************/

ALTER TABLE "B2B"."ADV_POSITION"
ADD ("REMARK" CLOB );

COMMENT ON COLUMN "B2B"."ADV_POSITION"."REMARK" IS '备注';


ALTER TABLE "B2B"."FLOOR"
ADD ("REMARK" CLOB );

COMMENT ON COLUMN "B2B"."FLOOR"."REMARK" IS '备注';


ALTER TABLE "B2B"."WEB_PAGE"
ADD ("REMARK" CLOB );

COMMENT ON COLUMN "B2B"."WEB_PAGE"."REMARK" IS '备注';



ALTER TABLE "B2B"."HOME_MENU"
ADD ("REMARK" CLOB );

COMMENT ON COLUMN "B2B"."HOME_MENU"."REMARK" IS '备注'




ALTER TABLE "B2B"."ACTIVITY_STRATEGY"
ADD ("SORT" NUMBER(11) DEFAULT 1 );

COMMENT ON COLUMN "B2B"."ACTIVITY_STRATEGY"."SORT" IS '排序'