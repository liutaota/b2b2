/***************************************************************/
-- 功能描述：赠品可多个; 用户反馈新增字段
-- 创建人员：tang
-- 创建日期：2020-09-10
-- /**************************************************************/

--赠品可多个
ALTER TABLE "B2B"."CART"
MODIFY ("GIFT_ID" VARCHAR(50));


--用户反馈新增字段
-- Add/modify columns
alter table FEEDBACK add erp_goods_id NUMBER(18);
alter table FEEDBACK add erp_goods_name VARCHAR2(100);
alter table FEEDBACK add goods_show VARCHAR2(20);
alter table FEEDBACK add reply_content VARCHAR2(100);
alter table FEEDBACK add reply_time date;
alter table FEEDBACK add reply_user_id NUMBER(4);
alter table FEEDBACK add reply_user_name VARCHAR2(100);
alter table FEEDBACK add is_del NUMBER(4) default 0;
-- Add comments to the columns
comment on column FEEDBACK.erp_goods_id
  is 'erp商品id';
comment on column FEEDBACK.erp_goods_name
  is '商品名称';
comment on column FEEDBACK.goods_show
  is '是否显示在商品详情页';
comment on column FEEDBACK.reply_content
  is '回复内容';
comment on column FEEDBACK.reply_time
  is '回复时间';
comment on column FEEDBACK.reply_user_id
  is '回复人';
comment on column FEEDBACK.reply_user_name
  is '回复名称';
comment on column FEEDBACK.is_del
  is '是否删除';
