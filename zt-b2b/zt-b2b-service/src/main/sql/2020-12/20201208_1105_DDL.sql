/***************************************************************/
-- 功能描述: 收款单加退款金额-退款单id
-- 创建人员：tang
-- 创建日期：2020-12-08
-- /**************************************************************/



ALTER TABLE "B2B"."REC_DOC"
ADD ( "REFUND_MONEY" NUMBER(18) NULL  )
ADD ( "REFUND_ID" NUMBER(18) NULL  ) ;

COMMENT ON COLUMN "B2B"."REC_DOC"."REFUND_MONEY" IS '退款金额';

COMMENT ON COLUMN "B2B"."REC_DOC"."REFUND_ID" IS '退款单id';


ALTER TABLE "B2B"."REC_DOC"
ADD ( "REFUND_USER_ID" NUMBER(18) NULL  )
ADD ( "FINANCE_USER_ID" NUMBER(18) NULL  )
ADD ( "REFUND_REMAK" VARCHAR2(1024) NULL  )
ADD ( "REFUND_TIME" DATE NULL  )
ADD ( "FINANCE_TIME" DATE NULL  ) ;

COMMENT ON COLUMN "B2B"."REC_DOC"."REFUND_USER_ID" IS '退款操作人';

COMMENT ON COLUMN "B2B"."REC_DOC"."FINANCE_USER_ID" IS '核销操作人';

COMMENT ON COLUMN "B2B"."REC_DOC"."REFUND_REMARK" IS '退款备注';

COMMENT ON COLUMN "B2B"."REC_DOC"."REFUND_TIME" IS '退款时间';

COMMENT ON COLUMN "B2B"."REC_DOC"."FINANCE_TIME" IS '核销时间';


ALTER TABLE "B2B"."STATEMENT"
ADD ( "STATEMENT_NO" VARCHAR2(100) NULL  ) ;

COMMENT ON COLUMN "B2B"."STATEMENT"."STATEMENT_NO" IS '单号';


ALTER TABLE "B2B"."ORDER_INFO"
ADD ( "NO_ORDER_USER_ID" NUMBER(18) NULL  )
ADD ( "NO_ORDER_REMARK" VARCHAR2(1024) NULL  ) ;

COMMENT ON COLUMN "B2B"."ORDER_INFO"."NO_ORDER_USER_ID" IS '整单不出生成人';

COMMENT ON COLUMN "B2B"."ORDER_INFO"."NO_ORDER_REMARK" IS '生成整单不出备注';


ALTER TABLE "B2B"."REFUND_RETURN"
ADD ( "RE_ERP_USER_ID" NUMBER(18) NULL  )
ADD ( "RE_ERP_REMARK" VARCHAR2(1024) NULL  ) ;

COMMENT ON COLUMN "B2B"."REFUND_RETURN"."RE_ERP_USER_ID" IS '重新下发操作人';

COMMENT ON COLUMN "B2B"."REFUND_RETURN"."RE_ERP_REMARK" IS '重新下发备注';


ALTER TABLE "B2B"."REC_DOC"
ADD ( "REC_DOC_NO" VARCHAR2(255) NULL  ) ;

COMMENT ON COLUMN "B2B"."REC_DOC"."REC_DOC_NO" IS '单号';


ALTER TABLE "B2B"."REC_DOC"
ADD ( "PAY_ABC_NO" VARCHAR2(255) NULL  ) ;

COMMENT ON COLUMN "B2B"."REC_DOC"."PAY_ABC_NO" IS '支付单号回写';
