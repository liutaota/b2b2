/***************************************************************/
-- 功能描述:确定备注
-- 创建人员：tang
-- 创建日期：2021-02-02
-- /**************************************************************/



    -- Add/modify columns
    alter table REC_DOC add confirm_remark VARCHAR2(255);
    -- Add comments to the columns
    comment on column REC_DOC.confirm_remark
      is '确定备注';
