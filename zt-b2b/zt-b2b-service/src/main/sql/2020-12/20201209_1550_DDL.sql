/***************************************************************/
-- 功能描述: 收款单加退款流水号-农行日志
-- 创建人员：tang
-- 创建日期：2020-12-09
-- /**************************************************************/

-- Add/modify columns
alter table REC_DOC add pay_refund_no VARCHAR2(255);
alter table REC_DOC add abc_message clob;
-- Add comments to the columns
comment on column REC_DOC.pay_refund_no
  is '退款流水号';
comment on column REC_DOC.abc_message
  is '农行日志';

-- Add/modify columns
alter table REFUND_RETURN add receive_user_id NUMBER(18);
-- Add comments to the columns
comment on column REFUND_RETURN.receive_user_id
  is '收货人';

ALTER TABLE "B2B"."REC_DOC"
ADD ( "REFUND_STATE" VARCHAR2(255) NULL  ) ;

COMMENT ON COLUMN "B2B"."REC_DOC"."REFUND_STATE" IS '退款状态';

update REC_DOC set REFUND_STATE='NO_REFUND';