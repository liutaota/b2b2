/***************************************************************/
-- 功能描述：优惠券添加备注
-- 创建人员：sjl
-- 创建日期：2020-10-12
-- /**************************************************************/
alter table COUPON
	add REMARK VARCHAR2(1024)
/

comment on column COUPON.REMARK is '备注'
/

/***************************************************************/
-- 功能描述：优惠券名称长度扩大
-- 创建人员：sjl
-- 创建日期：2020-10-12
-- /**************************************************************/
alter table COUPON modify COUPON_NAME VARCHAR2(1024)
/

/***************************************************************/
-- 功能描述：数据库VARCHAR2类型 全改为 长度 1024
-- 创建人员：sjl
-- 创建日期：2020-10-12
-- /**************************************************************/
alter table ACTIVITY_GIFT modify LOTNO VARCHAR2(1024)
/

alter table ACTIVITY_GIFT modify USER_ID VARCHAR2(1024)
/

alter table ACTIVITY_GIFT modify GOODS_NAME VARCHAR2(1024)
/

alter table ACTIVITY_GOODS modify GOODS_NAME VARCHAR2(1024)
/

alter table ACTIVITY_ORDER modify ORDER_NO VARCHAR2(1024)
/

alter table ACTIVITY_ORDER modify STATUS VARCHAR2(1024)
/

alter table ADMIN_LOG modify USER_NAME VARCHAR2(1024)
/

alter table ADMIN_LOG modify IP VARCHAR2(1024)
/

alter table ADMIN_LOG modify URL VARCHAR2(1024)
/

alter table ADV_POSITION modify AP_DISPLAY VARCHAR2(1024)
/

alter table ADV_POSITION modify IS_USE VARCHAR2(1024)
/

alter table ADV_POSITION modify ADV_NAME VARCHAR2(1024)
/

alter table ADV_POSITION_CUSTOMER_SET modify TYPE VARCHAR2(1024)
/

alter table ARRIVAL_NOTICE modify GOODS_NAME VARCHAR2(1024)
/

alter table ARRIVAL_NOTICE modify SELL_TIME VARCHAR2(1024)
/

alter table ARRIVAL_NOTICE modify AN_EMAIL VARCHAR2(1024)
/

alter table ARRIVAL_NOTICE modify AN_MOBILE VARCHAR2(1024)
/

alter table ARRIVAL_NOTICE modify AN_TYPE VARCHAR2(1024)
/

alter table ARRIVAL_NOTICE modify REMARK VARCHAR2(1024)
/

alter table ARRIVAL_NOTICE modify ERP_GOODS_UNIT VARCHAR2(1024)
/

alter table ARRIVAL_NOTICE modify ERP_GOODS_TYPE VARCHAR2(1024)
/

alter table ARRIVAL_NOTICE modify ERP_FACTORY_NAME VARCHAR2(1024)
/

alter table ARRIVAL_NOTICE modify PACKING_BOX VARCHAR2(1024)
/

alter table ARRIVAL_NOTICE modify AN_STATUS VARCHAR2(1024)
/

alter table ARRIVAL_NOTICE modify LOT_NO VARCHAR2(1024)
/

alter table CART modify GOODS_NAME VARCHAR2(1024)
/

alter table CART modify GOODS_IMAGE VARCHAR2(1024)
/

alter table CART modify LOT_NO VARCHAR2(1024)
/

alter table CART modify GIFT_ID VARCHAR2(1024)
/

alter table CHAT_LOG modify MEMBER_NAME VARCHAR2(1024)
/

alter table CHAT_LOG modify MEMBER_IP VARCHAR2(1024)
/

alter table CHAT_LOG modify ZT_NAME VARCHAR2(1024)
/

alter table CHAT_LOG modify ZT_MSG VARCHAR2(1024)
/

alter table CHAT_MSG modify MEMBER_NAME VARCHAR2(1024)
/

alter table CHAT_MSG modify MEMBER_IP VARCHAR2(1024)
/

alter table CHAT_MSG modify ZT_NAME VARCHAR2(1024)
/

alter table CHAT_MSG modify ZT_MSG VARCHAR2(1024)
/

alter table CHAT_MSG modify STATUS VARCHAR2(1024)
/

alter table CLASS modify CLASS_NAME VARCHAR2(1024)
/

alter table CLASS modify CLASS_IMG VARCHAR2(1024)
/

alter table COUPON modify CUSTOM_SET_TYPE VARCHAR2(1024)
/

alter table COUPON modify GOODS_SET_TYPE VARCHAR2(1024)
/

alter table COUPON modify TYPE VARCHAR2(1024)
/

alter table COUPON_GOODS modify VISIBLE_TYPE VARCHAR2(1024)
/

alter table COUPON_LOG modify COUPON_CODE VARCHAR2(1024)
/

alter table COUPON_LOG modify ORDER_NO VARCHAR2(1024)
/

alter table COUPON_MEMBER modify VISIBLE_TYPE VARCHAR2(1024)
/

alter table COUPON_RECEIVE modify COUPON_CODE VARCHAR2(1024)
/

alter table COUPON_RECEIVE modify STATUS VARCHAR2(1024)
/

alter table COUPON_RECEIVE modify SOURCE VARCHAR2(1024)
/

alter table DELIVERY_AMOUNT modify AREA_ID VARCHAR2(1024)
/

alter table DELIVERY_AMOUNT modify AREA_NAME VARCHAR2(1024)
/

alter table DELIVERY_AMOUNT modify DA_NAME VARCHAR2(1024)
/

alter table DELIVERY_AMOUNT modify TYPE VARCHAR2(1024)
/

alter table DELIVERY_AMOUNT modify IS_USE VARCHAR2(1024)
/

alter table DOCUMENT modify DOC_CODE VARCHAR2(1024)
/

alter table DOCUMENT modify DOC_TITLE VARCHAR2(1024)
/

alter table EVALUATE_GOODS modify EVAL_ORDER_NO VARCHAR2(1024)
/

alter table EVALUATE_GOODS modify EVAL_GOODS_NAME VARCHAR2(1024)
/

alter table EVALUATE_GOODS modify EVAL_CONTENT VARCHAR2(1024)
/

alter table EVALUATE_GOODS modify EVAL_MEMBER_NAME VARCHAR2(1024)
/

alter table EVALUATE_GOODS modify EVAL_EXPLAIN VARCHAR2(1024)
/

alter table EVALUATE_GOODS modify EVAL_IMAGE VARCHAR2(1024)
/

alter table EVALUATE_GOODS modify EVAL_CONTENT_AGAIN VARCHAR2(1024)
/

alter table EVALUATE_GOODS modify EVAL_IMAGE_AGAIN VARCHAR2(1024)
/

alter table EVALUATE_GOODS modify EVAL_EXPLAIN_AGAIN VARCHAR2(1024)
/

alter table FACTORY modify FACTORY_SHORT VARCHAR2(1024)
/

alter table FACTORY modify FACTORY_INITIAL VARCHAR2(1024)
/

alter table FACTORY modify FACTORY_CLASS VARCHAR2(1024)
/

alter table FACTORY modify FACTORY_PIC VARCHAR2(1024)
/

alter table FAVORITES modify MEMBER_NAME VARCHAR2(1024)
/

alter table FAVORITES modify FAV_TYPE VARCHAR2(1024)
/

alter table FAVORITES modify GOODS_NAME VARCHAR2(1024)
/

alter table FAVORITES modify GOODS_IMAGE VARCHAR2(1024)
/

alter table FAVORITES modify LOG_MSG VARCHAR2(1024)
/

alter table FAVORITES modify LOT_NO VARCHAR2(1024)
/

alter table FEEDBACK modify CONTENT VARCHAR2(1024)
/

alter table FEEDBACK modify TYPE VARCHAR2(1024)
/

alter table FEEDBACK modify MEMBER_NAME VARCHAR2(1024)
/

alter table FEEDBACK modify MEMBER_PHONE VARCHAR2(1024)
/

alter table FEEDBACK modify ERP_GOODS_NAME VARCHAR2(1024)
/

alter table FEEDBACK modify GOODS_SHOW VARCHAR2(1024)
/

alter table FEEDBACK modify REPLY_CONTENT VARCHAR2(1024)
/

alter table FEEDBACK modify REPLY_USER_NAME VARCHAR2(1024)
/

alter table FEEDBACK modify EMAIL VARCHAR2(1024)
/

alter table FEEDBACK modify STATUS VARCHAR2(1024)
/

alter table FLOOR modify TYPE VARCHAR2(1024)
/

alter table FLOOR modify TITLE VARCHAR2(1024)
/

alter table FLOOR modify FLOOR_KEY VARCHAR2(1024)
/

alter table FLOOR modify HUE VARCHAR2(1024)
/

alter table FLOOR modify IS_USE VARCHAR2(1024)
/

alter table FLOOR_CUSTOMER_SET modify TYPE VARCHAR2(1024)
/

alter table GOODS modify GOODS_IMG VARCHAR2(1024)
/

alter table GOODS modify ERP_LOT_NO VARCHAR2(1024)
/

alter table GOODS modify ACTIVITY_CONTENT VARCHAR2(1024)
/

alter table HELP modify HELP_TITLE VARCHAR2(1024)
/

alter table HELP modify HELP_URL VARCHAR2(1024)
/

alter table HELP modify HELP_SHOW VARCHAR2(1024)
/

alter table HELP_TYPE modify TYPE_NAME VARCHAR2(1024)
/

alter table HELP_TYPE modify HELP_SHOW VARCHAR2(1024)
/

alter table LICENCE modify LICENCE_IMAGES VARCHAR2(1024)
/

alter table LICENCE modify LICENCE_NAME VARCHAR2(1024)
/

alter table LICENCE modify LICENCE_CODE VARCHAR2(1024)
/

alter table LICENCE modify STATUS VARCHAR2(1024)
/

alter table LICENCE_APPLY modify LICENCE_NAME VARCHAR2(1024)
/

alter table LICENCE_APPLY modify LICENCE_CODE VARCHAR2(1024)
/

alter table LICENCE_APPLY modify STATUS VARCHAR2(1024)
/

alter table LINK modify LINK_TITLE VARCHAR2(1024)
/

alter table LINK modify LINK_URL VARCHAR2(1024)
/

alter table LINK modify LINK_PIC VARCHAR2(1024)
/

alter table MEMBER modify USER_NAME VARCHAR2(1024)
/

alter table MEMBER modify NAME VARCHAR2(1024)
/

alter table MEMBER modify OPENID VARCHAR2(1024)
/

alter table MEMBER modify HEAD_IMG VARCHAR2(1024)
/

alter table MEMBER modify PASSWORD VARCHAR2(1024)
/

alter table MEMBER modify SALT VARCHAR2(1024)
/

alter table MEMBER modify TELEPHONE VARCHAR2(1024)
/

alter table MEMBER_APPLY modify COMPANY_NAME VARCHAR2(1024)
/

alter table MEMBER_APPLY modify GOODSBUSISCOPEIDS VARCHAR2(1024)
/

alter table MEMBER_APPLY modify GOODSBUSISCOPE VARCHAR2(1024)
/

alter table MEMBER_APPLY modify AREA_NAME VARCHAR2(1024)
/

alter table MEMBER_APPLY modify ADDRESS VARCHAR2(1024)
/

alter table MEMBER_APPLY modify CONTACT_NAME VARCHAR2(1024)
/

alter table MEMBER_APPLY modify CONTACT_PHONE VARCHAR2(1024)
/

alter table MEMBER_APPLY modify STATUS VARCHAR2(1024)
/

alter table MEMBER_APPLY modify COMPANY_TYPE VARCHAR2(1024)
/

alter table MEMBER_LOG modify IP VARCHAR2(1024)
/

alter table NEW_PRODUCT modify GOODS_NAME VARCHAR2(1024)
/

alter table NEW_PRODUCT modify SPEC VARCHAR2(1024)
/

alter table NEW_PRODUCT modify COMPANY_NAME VARCHAR2(1024)
/

alter table NEW_PRODUCT modify APPROVEDOCNO VARCHAR2(1024)
/

alter table NEW_PRODUCT modify CHANNEL VARCHAR2(1024)
/

alter table NEW_PRODUCT modify PHOTOS VARCHAR2(1024)
/

alter table NOTICE modify CONTENT VARCHAR2(1024)
/

alter table NOTICE modify IS_USE VARCHAR2(1024)
/

alter table NOTICE modify NOTICE_TYPE VARCHAR2(1024)
/

alter table NOTICE modify NOTICE_COLOR VARCHAR2(1024)
/

alter table NOTICE modify ADMIN_NAME VARCHAR2(1024)
/

alter table ORDER_GOODS modify GOODS_NAME VARCHAR2(1024)
/

alter table ORDER_GOODS modify GOODS_IMAGE VARCHAR2(1024)
/

alter table ORDER_GOODS modify GOODS_SPEC VARCHAR2(1024)
/

alter table ORDER_GOODS modify ERP_LOT_NO VARCHAR2(1024)
/

alter table ORDER_GOODS modify ERP_GOODS_UNIT VARCHAR2(1024)
/

alter table ORDER_GOODS modify REMARK VARCHAR2(1024)
/

alter table ORDER_LOG modify ORDER_STATUS VARCHAR2(1024)
/

alter table ORDER_LOG modify LOG_ROLE VARCHAR2(1024)
/

alter table ORDER_LOG modify TO_ERP_NUM VARCHAR2(1024)
/

alter table REASON modify REASON_INFO VARCHAR2(1024)
/

alter table REFUND_DETAIL modify LOT_NO VARCHAR2(1024)
/

alter table REFUND_DETAIL modify REFUND_CODE VARCHAR2(1024)
/

alter table REFUND_DETAIL modify REFUND_STATE VARCHAR2(1024)
/

alter table REFUND_DETAIL modify GOODS_NAME VARCHAR2(1024)
/

alter table REFUND_DETAIL modify GODOS_IMAGE VARCHAR2(1024)
/

alter table REFUND_DETAIL modify ORDER_GOODS_TYPE VARCHAR2(1024)
/

alter table REFUND_DETAIL modify REASON_INFO VARCHAR2(1024)
/

alter table REFUND_RETURN modify ORDER_NO VARCHAR2(1024)
/

alter table REFUND_RETURN modify APPLY_NO VARCHAR2(1024)
/

alter table REFUND_RETURN modify MEMBER_NAME VARCHAR2(1024)
/

alter table REFUND_RETURN modify APPLY_TYPE VARCHAR2(1024)
/

alter table REFUND_RETURN modify REFUND_STATE VARCHAR2(1024)
/

alter table REFUND_RETURN modify RETURN_TYPE VARCHAR2(1024)
/

alter table REFUND_RETURN modify REASON_INFO VARCHAR2(1024)
/

alter table REFUND_RETURN modify PIC_INFO VARCHAR2(1024)
/

alter table REFUND_RETURN modify MEMBER_MESSAGE VARCHAR2(1024)
/

alter table REFUND_RETURN modify USER_MESSAGE VARCHAR2(1024)
/

alter table REFUND_RETURN modify RECEIVE_MESSAGE VARCHAR2(1024)
/

alter table ROLE_PERMISSION modify PERMISSION_NAME VARCHAR2(1024)
/

alter table ROLE_PERMISSION modify PERMISSION_PATH VARCHAR2(1024)
/

alter table SCORE_RECORD modify CONTENT VARCHAR2(1024)
/

alter table SCORE_RECORD modify FROM_TYPE VARCHAR2(1024)
/

alter table SETTING modify NAME VARCHAR2(1024)
/

alter table SETTING modify REMARK VARCHAR2(1024)
/

alter table SETTING modify TYPE VARCHAR2(1024)
/

alter table SETTING modify KEY VARCHAR2(1024)
/

alter table SMS_LOG modify LOG_PHONE VARCHAR2(1024)
/

alter table SMS_LOG modify LOG_CAPTCHA VARCHAR2(1024)
/

alter table SMS_LOG modify LOG_IP VARCHAR2(1024)
/

alter table SMS_LOG modify LOG_MSG VARCHAR2(1024)
/

alter table SMS_LOG modify LOG_TYPE VARCHAR2(1024)
/

alter table SMS_LOG modify MEMBER_NAME VARCHAR2(1024)
/

alter table SYS_ROLE modify ROLE_NAME VARCHAR2(1024)
/

alter table SYS_ROLE modify ROLE_PY VARCHAR2(1024)
/

alter table TMP_GOODS_PIC modify PIC VARCHAR2(1024)
/

alter table USER_INFO modify USER_NAME VARCHAR2(1024)
/

alter table USER_INFO modify NAME VARCHAR2(1024)
/

alter table USER_INFO modify PASSWORD VARCHAR2(1024)
/

alter table USER_INFO modify SALT VARCHAR2(1024)
/

alter table USER_INFO modify ROLE VARCHAR2(1024)
/

alter table USER_INFO modify EMPLOYEE_NO VARCHAR2(1024)
/

alter table USER_INFO modify EMAIL VARCHAR2(1024)
/

alter table USER_INFO modify IS_USE VARCHAR2(1024)
/

alter table USER_INFO modify TELEPHONE VARCHAR2(1024)
/

alter table WEB_PAGE modify TITLE VARCHAR2(1024)
/

alter table WEB_PAGE modify HINT_ICON VARCHAR2(1024)
/

alter table WEB_PAGE modify IS_USE VARCHAR2(1024)
/

alter table WEB_PAGE_CUSTOMER_SET modify TYPE VARCHAR2(1024)
/

alter table WEB_PAGE_SEARCH modify GOODS_NAME VARCHAR2(1024)
/

alter table WEB_PAGE_SEARCH modify CLASS_NAME_1 VARCHAR2(1024)
/

alter table WEB_PAGE_SEARCH modify CLASS_NAME_2 VARCHAR2(1024)
/

alter table WEB_PAGE_SEARCH modify ERP_CLASS_ID_2 VARCHAR2(1024)
/

alter table WEB_PAGE_SEARCH modify ERP_CLASS_ID_3 VARCHAR2(1024)
/

alter table WEB_PAGE_SEARCH modify TITLE VARCHAR2(1024)
/

