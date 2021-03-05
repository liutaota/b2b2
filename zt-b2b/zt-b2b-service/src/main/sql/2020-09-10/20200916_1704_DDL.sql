/***************************************************************/
-- 功能描述：新品登记
-- 创建人员：sjl
-- 创建日期：2020-09-16
-- /**************************************************************/
create table NEW_PRODUCT
(
    ID NUMBER(18) not null
        constraint NEW_PRODUCT_pk
            primary key,
    GOODS_NAME VARCHAR2(20),
    SPEC VARCHAR2(20),
    NUM NUMBER(18),
    COMPANY_NAME VARCHAR2(200),
    APPROVEDOCNO VARCHAR2(20),
    PRICE NUMBER(18),
    CHANNEL VARCHAR2(50),
    REMARK VARCHAR2(1024),
    PHOTOS VARCHAR2(500),
    MEMBER_ID NUMBER(18),
    CREATE_TIME DATE
)
/

comment on table NEW_PRODUCT is '新品登记'
/

comment on column NEW_PRODUCT.ID is '主键ID'
/

comment on column NEW_PRODUCT.GOODS_NAME is '商品名称'
/

comment on column NEW_PRODUCT.SPEC is '商品规格'
/

comment on column NEW_PRODUCT.NUM is '需求数量'
/

comment on column NEW_PRODUCT.COMPANY_NAME is '生产厂家，默认"不限"'
/

comment on column NEW_PRODUCT.APPROVEDOCNO is '批准文号'
/

comment on column NEW_PRODUCT.PRICE is '建议价格'
/

comment on column NEW_PRODUCT.CHANNEL is '货源渠道'
/

comment on column NEW_PRODUCT.REMARK is '备注'
/

comment on column NEW_PRODUCT.PHOTOS is '图片，分割符隔开'
/

comment on column NEW_PRODUCT.MEMBER_ID is '用户ID'
/

comment on column NEW_PRODUCT.CREATE_TIME is '发布时间'
/


create sequence SEQ_NEW_PRODUCT
/


