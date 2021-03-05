/***************************************************************/
-- 功能描述: 加随货备注
-- 创建人员：tang
-- 创建日期：2020-12-22
-- /**************************************************************/





      -- Add/modify columns
      alter table ORDER_INFO add goods_remark VARCHAR2(1024);
      -- Add comments to the columns
      comment on column ORDER_INFO.goods_remark
        is '随货备注';

        -- Add/modify columns
        alter table LICENCE_APPLY add licence_id NUMBER(18);
        -- Add comments to the columns
        comment on column LICENCE_APPLY.licence_id
          is '证照id';


          -- Add/modify columns
          alter table LICENCE add licence_id NUMBER(18);
          -- Add comments to the columns
          comment on column LICENCE.licence_id
            is '证照id';
