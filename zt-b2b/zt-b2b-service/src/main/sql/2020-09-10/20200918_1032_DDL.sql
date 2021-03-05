/***************************************************************/
-- 功能描述：优惠券-COUPON
-- 创建人员：sjl
-- 创建日期：2020-09-18
-- /**************************************************************/

create table COUPON
(
	ID NUMBER(18) not null
		constraint COUPON_pk
			primary key,
	CUSTOM_SET_TYPE VARCHAR2(20),
	COUPON_NAME VARCHAR2(20),
	GOODS_SET_TYPE VARCHAR2(20),
	TYPE VARCHAR2(50),
	EXPLAIN VARCHAR2(1024),
	FULL_AMOUNT NUMBER(18),
	REDUCE_AMOUNT NUMBER(18),
	DISCOUNT NUMBER(10),
	CREATE_USER_ID NUMBER(18),
	COUPON_ACCEPT NUMBER(18),
	REMAIN_NUM NUMBER(18),
	USED_AMOUNT NUMBER(18),
	LIMIT_NUM NUMBER(18),
	USE_DAY NUMBER(18),
	RECEIVE_START_TIME DATE,
	RECEIVE_END_TIME DATE,
	USE_START_TIME DATE,
	USE_END_TIME DATE,
	IS_RECEIVE NUMBER(1) default 1,
	IS_USE NUMBER(1) default 1,
	IS_DEL NUMBER(1) default 0,
	CREATE_TIME DATE
)
/

comment on table COUPON is '优惠券'
/

comment on column COUPON.ID is '主键ID'
/

comment on column COUPON.CUSTOM_SET_TYPE is '客户集合类型
10-ALL 全部可见
20-VISIBLE 部分可见
30- UN_VISIBLE 部分不可见'
/

comment on column COUPON.COUPON_NAME is '优惠券名称'
/

comment on column COUPON.GOODS_SET_TYPE is '商品集合
1 全部
2 部分商品集合'
/

comment on column COUPON.TYPE is '优惠券类型
1-满减券
2-折扣券
3-现金券'
/

comment on column COUPON.EXPLAIN is '优惠券说明'
/

comment on column COUPON.FULL_AMOUNT is '满足金额，0为不限满足金额'
/

comment on column COUPON.REDUCE_AMOUNT is '优惠金额'
/

comment on column COUPON.DISCOUNT is '折扣'
/

comment on column COUPON.CREATE_USER_ID is '创建人ID'
/

comment on column COUPON.COUPON_ACCEPT is '总发放数量'
/

comment on column COUPON.REMAIN_NUM is '剩余数量'
/

comment on column COUPON.USED_AMOUNT is '优惠券已使用数量'
/

comment on column COUPON.LIMIT_NUM is '用户限领数量'
/

comment on column COUPON.USE_DAY is '有效使用天数'
/

comment on column COUPON.RECEIVE_START_TIME is '发放开始时间'
/

comment on column COUPON.RECEIVE_END_TIME is '可领取结束时间'
/

comment on column COUPON.USE_START_TIME is '可使用开始时间'
/

comment on column COUPON.USE_END_TIME is '可使用结束时间'
/

comment on column COUPON.IS_RECEIVE is '是否可发放，0为否，1为是，默认1'
/

comment on column COUPON.IS_USE is '是否可用，0为否，1为是，默认1'
/

comment on column COUPON.IS_DEL is '是否删除，0为否，1为是，默认0'
/

comment on column COUPON.CREATE_TIME is '创建时间'
/

create sequence SEQ_COUPON
/


/***************************************************************/
-- 功能描述：优惠券客户集合-COUPON_MEMBER
-- 创建人员：sjl
-- 创建日期：2020-09-18
-- /**************************************************************/

create table COUPON_MEMBER
(
	ID NUMBER(18) not null
		constraint COUPON_MEMBER_pk
			primary key,
	COUPON_ID NUMBER(18),
	CUSTOM_SET_ID NUMBER(18),
	CREATE_TIME DATE,
	ADMIN_ID NUMBER(18)
)
/

comment on table COUPON_MEMBER is '优惠券客户集合'
/

comment on column COUPON_MEMBER.ID is '主键ID'
/

comment on column COUPON_MEMBER.COUPON_ID is '优惠券ID'
/

comment on column COUPON_MEMBER.CUSTOM_SET_ID is '客户集合ID'
/

comment on column COUPON_MEMBER.CREATE_TIME is '添加时间'
/

comment on column COUPON_MEMBER.ADMIN_ID is '添加者id'
/

create sequence SEQ_COUPON_MEMBER
/


/***************************************************************/
-- 功能描述：优惠券商品集合-COUPON_GOODS
-- 创建人员：sjl
-- 创建日期：2020-09-18
-- /**************************************************************/

create table COUPON_GOODS
(
	ID NUMBER(18) not null
		constraint COUPON_GOODS_pk
			primary key,
	COUPON_ID NUMBER(18),
	GOODS_SET_ID NUMBER(18),
	IS_USE NUMBER(1) default 1,
	CREATE_TIME DATE
)
/

comment on table COUPON_GOODS is '优惠券商品集合'
/

comment on column COUPON_GOODS.ID is '主键ID'
/

comment on column COUPON_GOODS.COUPON_ID is '优惠券ID'
/

comment on column COUPON_GOODS.GOODS_SET_ID is '商品集合ID'
/

comment on column COUPON_GOODS.IS_USE is '是否可用，0为否，1为是'
/

comment on column COUPON_GOODS.CREATE_TIME is '创建时间'
/

create sequence SEQ_COUPON_GOODS
/

/***************************************************************/
-- 功能描述：用户优惠券-COUPON_RECEIVE
-- 创建人员：sjl
-- 创建日期：2020-09-18
-- /**************************************************************/

create table COUPON_RECEIVE
(
	ID NUMBER(18) not null
		constraint COUPON_RECEIVE_pk
			primary key,
	MEMBER_ID NUMBER(18),
	COUPON_ID NUMBER(18),
	COUPON_CODE VARCHAR2(20),
	STATUS VARCHAR2(20),
	USE_START_TIME DATE,
	USE_END_TIME DATE,
	IS_DEL NUMBER(1),
	CREATE_TIME DATE
)
/

comment on table COUPON_RECEIVE is '用户优惠券'
/

comment on column COUPON_RECEIVE.ID is '主键ID'
/

comment on column COUPON_RECEIVE.MEMBER_ID is '用户ID'
/

comment on column COUPON_RECEIVE.COUPON_ID is '优惠券ID'
/

comment on column COUPON_RECEIVE.COUPON_CODE is '优惠券码'
/

comment on column COUPON_RECEIVE.STATUS is '状态，0-未使用，1-已使用'
/

comment on column COUPON_RECEIVE.USE_START_TIME is '使用开始时间'
/

comment on column COUPON_RECEIVE.USE_END_TIME is '使用结束时间'
/

comment on column COUPON_RECEIVE.IS_DEL is '是否删除，0否，1是'
/

comment on column COUPON_RECEIVE.CREATE_TIME is '创建时间'
/

create sequence SEQ_COUPON_RECEIVE
/

/***************************************************************/
-- 功能描述：优惠券日志-COUPON_LOG
-- 创建人员：sjl
-- 创建日期：2020-09-18
-- /**************************************************************/

create table COUPON_LOG
(
	ID NUMBER(18) not null
		constraint COUPON_LOG_pk
			primary key,
	MEMBER_ID NUMBER(18),
	COUPON_ID NUMBER(18),
	COUPON_CODE NUMBER(18),
	ORDER_NO VARCHAR2(50),
	ORDER_AMOUNT NUMBER(18),
	COUPON_AMOUNT NUMBER(18),
	ORDER_FINAL_AMOUNT NUMBER(18),
	CREATE_TIME DATE,
	STATUS NUMBER(1) default 0
)
/

comment on table COUPON_LOG is '优惠券日志'
/

comment on column COUPON_LOG.ID is '主键ID'
/

comment on column COUPON_LOG.MEMBER_ID is '用户ID'
/

comment on column COUPON_LOG.COUPON_ID is '优惠券ID'
/

comment on column COUPON_LOG.COUPON_CODE is '优惠券码'
/

comment on column COUPON_LOG.ORDER_NO is '订单号'
/

comment on column COUPON_LOG.ORDER_AMOUNT is '原订单金额'
/

comment on column COUPON_LOG.COUPON_AMOUNT is '优惠金额'
/

comment on column COUPON_LOG.ORDER_FINAL_AMOUNT is '抵扣优惠后的订单金额'
/

comment on column COUPON_LOG.CREATE_TIME is '添加时间'
/

comment on column COUPON_LOG.STATUS is '日志状态，默认0，支付回调成功后为1'
/

create sequence SEQ_COUPON_LOG
/