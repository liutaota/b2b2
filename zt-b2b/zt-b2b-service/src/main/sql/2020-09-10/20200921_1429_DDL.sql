/***************************************************************/
-- 功能描述：广告客户集合&&楼层客户集合
-- 创建人员：sjl
-- 创建日期：2020-09-21
-- /**************************************************************/
create table ADV_POSITION_CUSTOMER_SET
(
    ID NUMBER(18) not null
        constraint ADV_POSITION_CUSTOMER_SET_pk
            primary key,
    ADV_ID NUMBER(18),
    CUSTOMER_SET_ID NUMBER(18),
    TYPE VARCHAR2(50),
    CREATE_TIME DATE
)
/

comment on table ADV_POSITION_CUSTOMER_SET is '广告客户集合'
/

comment on column ADV_POSITION_CUSTOMER_SET.ID is '主键id'
/

comment on column ADV_POSITION_CUSTOMER_SET.ADV_ID is '广告id'
/

comment on column ADV_POSITION_CUSTOMER_SET.CUSTOMER_SET_ID is '客户集合id'
/

comment on column ADV_POSITION_CUSTOMER_SET.TYPE is '可见类型：ALL_VISIBLE 全部可见 PARTIALLY_VISIBLE 部分可见 UN_VISIBLE 部分不可见'
/

comment on column ADV_POSITION_CUSTOMER_SET.CREATE_TIME is '创建时间'
/

create sequence SEQ_ADV_POSITION_CUSTOMER_SET
/


create table FLOOR_CUSTOMER_SET
(
    ID NUMBER(18) not null
        constraint FLOOR_CUSTOMER_SET_pk
            primary key,
    FLOOR_ID NUMBER(18),
    CUSTOMER_SET_ID NUMBER(18),
    TYPE VARCHAR2(50),
    CREATE_TIME DATE
)
/

comment on table FLOOR_CUSTOMER_SET is '楼层客户集合'
/

comment on column FLOOR_CUSTOMER_SET.ID is '主键id'
/

comment on column FLOOR_CUSTOMER_SET.FLOOR_ID is '楼层id'
/

comment on column FLOOR_CUSTOMER_SET.CUSTOMER_SET_ID is '客户集合id'
/

comment on column FLOOR_CUSTOMER_SET.TYPE is '可见类型：ALL_VISIBLE 全部可见 PARTIALLY_VISIBLE 部分可见 UN_VISIBLE 部分不可见'
/

comment on column FLOOR_CUSTOMER_SET.CREATE_TIME is '创建时间'
/

create sequence SEQ_FLOOR_CUSTOMER_SET
/