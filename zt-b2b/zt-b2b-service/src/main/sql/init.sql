create sequence SEQ_USER_INFO
	order
	nocache
/

create sequence SEQ_MEMBER
	order
	nocache
/

create sequence SEQ_MEMBER_LOG
	order
	nocache
/

create sequence SEQ_ORDER_LOG
	order
	nocache
/

create sequence SEQ_ORDER_GOODS
	order
	nocache
/

create sequence SEQ_ORDER_INFO
	order
	nocache
/

create sequence SEQ_SMS_LOG
	order
	nocache
/

create sequence SEQ_CART
	order
	nocache
/

create sequence SEQ_FAVORITES
	order
	nocache
/

create sequence SEQ_ARRIVAL_NOTICE
	order
	nocache
/

create sequence SEQ_GOODS
	order
	nocache
/

create sequence SEQ_REASON
	order
	nocache
/

create sequence SEQ_ADMIN_LOG
	order
	nocache
/

create sequence SEQ_FACTORY
	order
	nocache
/

create sequence SEQ_REFUND_RETURN
	order
	nocache
/

create sequence SEQ_REFUND_DETAIL
	order
	nocache
/

create sequence SEQ_ADV_POSITION
	order
	nocache
/

create sequence SEQ_EVALUATE_GOODS
	order
	nocache
/

create sequence SEQ_HELP
	order
	nocache
/

create sequence SEQ_HELP_TYPE
	order
	nocache
/

create sequence SEQ_WEB_PAGE
	order
	nocache
/

create sequence SEQ_LINK
	order
	nocache
/

create sequence SEQ_FEEDBACK
	order
	nocache
/

create sequence SEQ_SETTING
	order
	nocache
/

create sequence SEQ_DOCUMENT
	order
	nocache
/

create sequence SEQ_CHAT_LOG
	order
	nocache
/

create sequence SEQ_CHAT_MSG
	order
	nocache
/

create sequence SEQ_DELIVERY_AMOUNT
/

create sequence SEQ_CACHE_CUSTOMER_GOODS
	order
	cache 500
/

create sequence SEQ_WEB_PAGE_CUSTOMER_SET
/

create sequence SEQ_ACTIVITY
	order
/

create sequence SEQ_ACTIVITY_SET
	order
/

create sequence SEQ_ACTIVITY_GOODS
	order
/

create sequence SEQ_ACTIVITY_STRATEGY
	order
/

create sequence SEQ_ACTIVITY_GIFT
	order
/

create sequence SEQ_ACTIVITY_ORDER
	order
/

create sequence SEQ_NOTICE
/

create sequence SEQ_FLOOR
/

create sequence SEQ_LICENCE
/

create sequence SEQ_LICENCE_APPLY
/

create sequence SEQ_MEMBER_APPLY
/

create table ADMIN_LOG
(
    ID NUMBER(18) not null
        constraint ADMIN_LOG_PK
        primary key,
    CONTENT VARCHAR2(4000),
    CREATE_TIME DATE,
    USER_NAME VARCHAR2(20),
    USER_ID NUMBER(18),
    IP VARCHAR2(20),
    URL VARCHAR2(100)
)
    /

comment on table ADMIN_LOG is '后台管理员操作日志'
/

comment on column ADMIN_LOG.ID is 'id'
/

comment on column ADMIN_LOG.CONTENT is '操作内容'
/

comment on column ADMIN_LOG.CREATE_TIME is '创建时间'
/

comment on column ADMIN_LOG.USER_NAME is '管理员'
/

comment on column ADMIN_LOG.USER_ID is '管理员Id'
/

comment on column ADMIN_LOG.IP is '登录IP'
/

comment on column ADMIN_LOG.URL is '（类名&方法名）'
/

create table ARRIVAL_NOTICE
(
    ID NUMBER(18) not null
        constraint ARRIVAL_NOTICE_PK
        primary key,
    ERP_GOODS_ID NUMBER(18) not null
        constraint SYS_C0069140
        check ("ERP_GOODS_ID" IS NOT NULL),
    GOODS_NAME VARCHAR2(50),
    MEMBER_ID NUMBER(18) not null
        constraint SYS_C0069141
        check ("MEMBER_ID" IS NOT NULL),
    SELL_TIME VARCHAR2(20),
    CREATE_TIME DATE,
    AN_EMAIL VARCHAR2(20),
    AN_MOBILE VARCHAR2(20),
    AN_TYPE VARCHAR2(20) default 1,
    GOODS_NUM NUMBER(11),
    REMARK VARCHAR2(255),
    ERP_GOODS_UNIT VARCHAR2(20),
    ERP_GOODS_TYPE VARCHAR2(20),
    ERP_FACTORY_NAME VARCHAR2(50),
    PACKING_BOX VARCHAR2(50),
    GOODS_ID NUMBER(18),
    AN_STATUS VARCHAR2(20),
    SEND_TIME DATE,
    ACCFLAG NUMBER(11),
    LOT_NO VARCHAR2(100),
    LOT_ID NUMBER(18),
    STORAGE_ID NUMBER(18)
)
    /

comment on table ARRIVAL_NOTICE is '缺货登记'
/

comment on column ARRIVAL_NOTICE.ID is 'id'
/

comment on column ARRIVAL_NOTICE.ERP_GOODS_ID is 'ERP商品id'
/

comment on column ARRIVAL_NOTICE.GOODS_NAME is '商品名称'
/

comment on column ARRIVAL_NOTICE.MEMBER_ID is '会员id'
/

comment on column ARRIVAL_NOTICE.SELL_TIME is '预计购买时间'
/

comment on column ARRIVAL_NOTICE.CREATE_TIME is '添加时间'
/

comment on column ARRIVAL_NOTICE.AN_EMAIL is '邮箱'
/

comment on column ARRIVAL_NOTICE.AN_MOBILE is '手机号'
/

comment on column ARRIVAL_NOTICE.AN_TYPE is '类型：1到货通知'
/

comment on column ARRIVAL_NOTICE.GOODS_NUM is '数量'
/

comment on column ARRIVAL_NOTICE.REMARK is '备注'
/

comment on column ARRIVAL_NOTICE.ERP_GOODS_UNIT is 'erp基本单位'
/

comment on column ARRIVAL_NOTICE.ERP_GOODS_TYPE is 'erp规格'
/

comment on column ARRIVAL_NOTICE.ERP_FACTORY_NAME is '厂家'
/

comment on column ARRIVAL_NOTICE.PACKING_BOX is '包装'
/

comment on column ARRIVAL_NOTICE.GOODS_ID is '商品id'
/

comment on column ARRIVAL_NOTICE.AN_STATUS is '状态：1未处理，2已发知道，3已加入购物车，4已删除'
/

comment on column ARRIVAL_NOTICE.SEND_TIME is '发通知的时间'
/

comment on column ARRIVAL_NOTICE.ACCFLAG is '是否赠品标志'
/

comment on column ARRIVAL_NOTICE.LOT_NO is '批号'
/

comment on column ARRIVAL_NOTICE.LOT_ID is '批号id'
/

comment on column ARRIVAL_NOTICE.STORAGE_ID is '保管帐ID'
/

create table CART
(
    ID NUMBER(18) not null
        constraint CART_PK
        primary key,
    MEMBER_ID NUMBER(18) not null
        constraint SYS_C0069490
        check ("MEMBER_ID" IS NOT NULL),
    ERP_GOODS_ID NUMBER(18) not null
        constraint SYS_C0069491
        check ("ERP_GOODS_ID" IS NOT NULL),
    GOODS_ID NUMBER(18),
    GOODS_NAME VARCHAR2(100),
    GOODS_PRICE NUMBER(18),
    GOODS_NUM NUMBER(4) not null
        constraint SYS_C0069493
        check ("GOODS_NUM" IS NOT NULL),
    GOODS_IMAGE VARCHAR2(500),
    BL_ID NUMBER(18),
    CREATE_TIME DATE not null
        constraint SYS_C0069494
        check ("CREATE_TIME" IS NOT NULL),
    GC_ID NUMBER(18),
    PRICE_ID NUMBER(18),
    STORAGE_ID NUMBER(18),
    ACCFLAG NUMBER(11),
    LOT_NO VARCHAR2(50),
    LOT_ID NUMBER(18),
    GOOD_TYPE NUMBER(10),
    AS_ID NUMBER(18),
    ACTIVITY_ID NUMBER(18),
    GIFT_ID NUMBER(18)
)
    /

comment on table CART is '购物车'
/

comment on column CART.ID is '主键'
/

comment on column CART.MEMBER_ID is '客户id'
/

comment on column CART.ERP_GOODS_ID is 'ERP商品表pub_goods'
/

comment on column CART.GOODS_ID is '商品id'
/

comment on column CART.GOODS_NAME is '商品名称'
/

comment on column CART.GOODS_PRICE is '加入购物车时的商品价格'
/

comment on column CART.GOODS_NUM is '商品数量'
/

comment on column CART.GOODS_IMAGE is '商品图片'
/

comment on column CART.BL_ID is '组合套装id'
/

comment on column CART.CREATE_TIME is '创建时间'
/

comment on column CART.GC_ID is '分类id'
/

comment on column CART.PRICE_ID is '价格类型id'
/

comment on column CART.STORAGE_ID is '保管帐ID'
/

comment on column CART.ACCFLAG is '是否赠品标志'
/

comment on column CART.LOT_NO is '批号'
/

comment on column CART.LOT_ID is '批号id'
/

comment on column CART.GOOD_TYPE is '1默认2限时折扣商品3组合套装4赠品5加价购活动商品6加价购换购商品'
/

comment on column CART.AS_ID is '活动策略ID'
/

comment on column CART.ACTIVITY_ID is '活动id'
/

comment on column CART.GIFT_ID is '赠品id'
/

create index "index"
    on CART (MEMBER_ID, ERP_GOODS_ID)
    /

create table CHAT_LOG
(
    ID NUMBER(18) not null
        constraint CHAT_LOG_PK
        primary key,
    MEMBER_ID NUMBER(18),
    MEMBER_NAME VARCHAR2(20) not null
        constraint SYS_C0069631
        check ("MEMBER_NAME" IS NOT NULL),
    MEMBER_IP VARCHAR2(20) not null
        constraint SYS_C0069632
        check ("MEMBER_IP" IS NOT NULL),
    ZT_ID NUMBER(18) not null
        constraint SYS_C0069633
        check ("ZT_ID" IS NOT NULL),
    ZT_NAME VARCHAR2(20) not null
        constraint SYS_C0069634
        check ("ZT_NAME" IS NOT NULL),
    ZT_MSG VARCHAR2(100) not null
        constraint SYS_C0069635
        check ("ZT_MSG" IS NOT NULL),
    CREATE_TIME DATE,
    MSG_ID NUMBER(18) not null
        constraint SYS_C0069636
        check ("MSG_ID" IS NOT NULL)
)
    /

comment on table CHAT_LOG is '聊天记录日志'
/

comment on column CHAT_LOG.ID is 'id'
/

comment on column CHAT_LOG.MEMBER_ID is '客户id'
/

comment on column CHAT_LOG.MEMBER_NAME is '客户名称'
/

comment on column CHAT_LOG.MEMBER_IP is '客户ip'
/

comment on column CHAT_LOG.ZT_ID is '接收人 ID'
/

comment on column CHAT_LOG.ZT_NAME is '接收人名称'
/

comment on column CHAT_LOG.ZT_MSG is '消息内容'
/

comment on column CHAT_LOG.CREATE_TIME is '创建时间'
/

comment on column CHAT_LOG.MSG_ID is '记录id'
/

create table CHAT_MSG
(
    ID NUMBER(18) not null
        constraint CHAT_MSG_PK
        primary key,
    MEMBER_ID NUMBER(18),
    MEMBER_NAME VARCHAR2(20),
    MEMBER_IP VARCHAR2(20) not null
        constraint SYS_C0069638
        check ("MEMBER_IP" IS NOT NULL),
    ZT_ID NUMBER(18) not null
        constraint SYS_C0069639
        check ("ZT_ID" IS NOT NULL),
    ZT_NAME VARCHAR2(20) not null
        constraint SYS_C0069640
        check ("ZT_NAME" IS NOT NULL),
    ZT_MSG VARCHAR2(100) not null
        constraint SYS_C0069641
        check ("ZT_MSG" IS NOT NULL),
    STATUS VARCHAR2(20) not null
        constraint SYS_C0069642
        check ("STATUS" IS NOT NULL),
    CREATE_TIME DATE
)
    /

comment on table CHAT_MSG is '聊天记录'
/

comment on column CHAT_MSG.ID is 'id'
/

comment on column CHAT_MSG.MEMBER_ID is '客户id'
/

comment on column CHAT_MSG.MEMBER_NAME is '客户名称'
/

comment on column CHAT_MSG.MEMBER_IP is '客户ip'
/

comment on column CHAT_MSG.ZT_ID is '接收人id'
/

comment on column CHAT_MSG.ZT_NAME is '接收人名称'
/

comment on column CHAT_MSG.ZT_MSG is '消息内容'
/

comment on column CHAT_MSG.STATUS is '状态：1为已读，2为未读，默认2'
/

comment on column CHAT_MSG.CREATE_TIME is '创建时间'
/

create table DOCUMENT
(
    ID NUMBER(18) not null
        constraint DOCUMENT_PK
        primary key,
    DOC_CODE VARCHAR2(20),
    DOC_TITLE VARCHAR2(50),
    DOC_CONTENT CLOB,
    CREATE_TIME DATE,
    DOC_MOFIDY_TIME DATE not null
        constraint SYS_C0069629
        check ("DOC_MOFIDY_TIME" IS NOT NULL)
)
    /

comment on table DOCUMENT is '协议详情'
/

comment on column DOCUMENT.ID is 'id'
/

comment on column DOCUMENT.DOC_CODE is '调用标识码，REG'
/

comment on column DOCUMENT.DOC_TITLE is '标题'
/

comment on column DOCUMENT.DOC_CONTENT is '内容'
/

comment on column DOCUMENT.CREATE_TIME is '创建时间'
/

comment on column DOCUMENT.DOC_MOFIDY_TIME is '修改时间'
/

create table EVALUATE_GOODS
(
    ID NUMBER(18) not null
        constraint EVALUATE_GOODS_PK
        primary key,
    EVAL_ORDER_ID NUMBER(18),
    EVAL_ORDER_NO VARCHAR2(100) not null
        constraint SYS_C0069512
        check ("EVAL_ORDER_NO" IS NOT NULL),
    EVAL_GOODS_ID NUMBER(18),
    EVAL_GOODS_NAME VARCHAR2(50) not null
        constraint SYS_C0069513
        check ("EVAL_GOODS_NAME" IS NOT NULL),
    EVAL_SCORES NUMBER(4) not null
        constraint SYS_C0069514
        check ("EVAL_SCORES" IS NOT NULL),
    EVAL_CONTENT VARCHAR2(100) not null
        constraint SYS_C0069515
        check ("EVAL_CONTENT" IS NOT NULL),
    EVAL_IS_ANONYMOUS NUMBER(4) default 0 not null
        constraint SYS_C0069516
        check ("EVAL_IS_ANONYMOUS" IS NOT NULL),
    CREATE_TIME DATE,
    EVAL_MEMBER_ID NUMBER(18),
    EVAL_MEMBER_NAME VARCHAR2(50) not null
        constraint SYS_C0069517
        check ("EVAL_MEMBER_NAME" IS NOT NULL),
    EVAL_EXPLAIN VARCHAR2(100) not null
        constraint SYS_C0069518
        check ("EVAL_EXPLAIN" IS NOT NULL),
    EVAL_IMAGE VARCHAR2(500) not null
        constraint SYS_C0069519
        check ("EVAL_IMAGE" IS NOT NULL),
    EVAL_CONTENT_AGAIN VARCHAR2(100) not null
        constraint SYS_C0069520
        check ("EVAL_CONTENT_AGAIN" IS NOT NULL),
    EVAL_ADDTIME_AGAIN DATE not null
        constraint SYS_C0069521
        check ("EVAL_ADDTIME_AGAIN" IS NOT NULL),
    EVAL_IMAGE_AGAIN VARCHAR2(500) not null
        constraint SYS_C0069522
        check ("EVAL_IMAGE_AGAIN" IS NOT NULL),
    EVAL_EXPLAIN_AGAIN VARCHAR2(100) not null
        constraint SYS_C0069523
        check ("EVAL_EXPLAIN_AGAIN" IS NOT NULL)
)
    /

comment on table EVALUATE_GOODS is '商品评价'
/

comment on column EVALUATE_GOODS.ID is 'id'
/

comment on column EVALUATE_GOODS.EVAL_ORDER_ID is '订单表id'
/

comment on column EVALUATE_GOODS.EVAL_ORDER_NO is '订单编号'
/

comment on column EVALUATE_GOODS.EVAL_GOODS_ID is '商品id'
/

comment on column EVALUATE_GOODS.EVAL_GOODS_NAME is '商品名称'
/

comment on column EVALUATE_GOODS.EVAL_SCORES is '1-5分'
/

comment on column EVALUATE_GOODS.EVAL_CONTENT is '信誉评价内容'
/

comment on column EVALUATE_GOODS.EVAL_IS_ANONYMOUS is '是否匿名，0否，1是，默认0'
/

comment on column EVALUATE_GOODS.CREATE_TIME is '创建时间/评价时间'
/

comment on column EVALUATE_GOODS.EVAL_MEMBER_ID is '评价人id'
/

comment on column EVALUATE_GOODS.EVAL_MEMBER_NAME is '评价人名称'
/

comment on column EVALUATE_GOODS.EVAL_EXPLAIN is '解释内容'
/

comment on column EVALUATE_GOODS.EVAL_IMAGE is '评价图片'
/

comment on column EVALUATE_GOODS.EVAL_CONTENT_AGAIN is '追加评价内容'
/

comment on column EVALUATE_GOODS.EVAL_ADDTIME_AGAIN is '追加评价时间'
/

comment on column EVALUATE_GOODS.EVAL_IMAGE_AGAIN is '追加评价图片'
/

comment on column EVALUATE_GOODS.EVAL_EXPLAIN_AGAIN is '追加评价解释'
/

create table FACTORY
(
    ID NUMBER(18) not null
        constraint FACTORY_PK
        primary key
        constraint SYS_C0069032
        check ("ID" IS NOT NULL),
    ERP_SUPPLY_ID NUMBER(18) not null
        constraint SYS_C0069033
        check ("ERP_SUPPLY_ID" IS NOT NULL),
    FACTORY_SHORT VARCHAR2(50)
        constraint SYS_C0069034
        check ("FACTORY_SHORT" IS NOT NULL),
    FACTORY_INITIAL VARCHAR2(50),
    FACTORY_CLASS VARCHAR2(20),
    FACTORY_PIC VARCHAR2(500),
    FACTORY_SORT NUMBER(8) default 1,
    FACTORY_RECOMMEND NUMBER(4) default 0,
    FACTORY_INTRODUCTION CLOB,
    CREATE_TIME DATE
)
    /

comment on table FACTORY is '厂家'
/

comment on column FACTORY.ID is 'id'
/

comment on column FACTORY.ERP_SUPPLY_ID is 'ERP厂家表pub_factory.supplyid'
/

comment on column FACTORY.FACTORY_SHORT is '厂家简称'
/

comment on column FACTORY.FACTORY_INITIAL is '厂家首字母'
/

comment on column FACTORY.FACTORY_CLASS is '类别名称'
/

comment on column FACTORY.FACTORY_PIC is '图片'
/

comment on column FACTORY.FACTORY_SORT is '排序'
/

comment on column FACTORY.FACTORY_RECOMMEND is '推荐，0为否，1为是，默认0'
/

comment on column FACTORY.FACTORY_INTRODUCTION is '厂家详情介绍'
/

comment on column FACTORY.CREATE_TIME is '创建时间'
/

create table FAVORITES
(
    ID NUMBER(18) not null
        constraint FAVORITES_PK
        primary key,
    MEMBER_ID NUMBER(18),
    MEMBER_NAME VARCHAR2(50),
    FAV_ID NUMBER(18),
    FAV_TYPE VARCHAR2(50),
    CREATE_TIME DATE,
    GOODS_NAME VARCHAR2(100),
    GOODS_IMAGE VARCHAR2(500),
    GC_ID NUMBER(18),
    LOG_PRICE NUMBER(11),
    LOG_MSG VARCHAR2(50),
    PRICE_ID NUMBER(18),
    STORAGE_ID NUMBER(18),
    ACCFLAG NUMBER(18),
    LOT_NO VARCHAR2(100),
    LOT_ID NUMBER(18)
)
    /

comment on table FAVORITES is '收藏表'
/

comment on column FAVORITES.ID is '主键'
/

comment on column FAVORITES.MEMBER_ID is '会员ID'
/

comment on column FAVORITES.MEMBER_NAME is '会员名称'
/

comment on column FAVORITES.FAV_ID is '收藏对像id，如商品id'
/

comment on column FAVORITES.FAV_TYPE is '类型：goods为商品'
/

comment on column FAVORITES.CREATE_TIME is '收藏时间'
/

comment on column FAVORITES.GOODS_NAME is '商品名称'
/

comment on column FAVORITES.GOODS_IMAGE is '商品图片'
/

comment on column FAVORITES.GC_ID is '商品分类id'
/

comment on column FAVORITES.LOG_PRICE is '商品收藏时价格'
/

comment on column FAVORITES.LOG_MSG is '收藏备注'
/

comment on column FAVORITES.PRICE_ID is '价格类型id'
/

comment on column FAVORITES.STORAGE_ID is '保管帐ID'
/

comment on column FAVORITES.ACCFLAG is '是否赠品标志'
/

comment on column FAVORITES.LOT_NO is '批号'
/

comment on column FAVORITES.LOT_ID is '批号id'
/

create table FEEDBACK
(
    ID NUMBER(18) not null
        constraint FEEDBACK_PK
        primary key,
    CONTENT VARCHAR2(100),
    TYPE VARCHAR2(20),
    CREATE_TIME DATE,
    MEMBER_ID NUMBER(18),
    MEMBER_NAME VARCHAR2(20),
    MEMBER_PHONE VARCHAR2(20)
)
    /

comment on table FEEDBACK is '用户反馈'
/

comment on column FEEDBACK.ID is 'id'
/

comment on column FEEDBACK.CONTENT is '反馈内容'
/

comment on column FEEDBACK.TYPE is '类型'
/

comment on column FEEDBACK.CREATE_TIME is '反馈时间'
/

comment on column FEEDBACK.MEMBER_ID is '客户id'
/

comment on column FEEDBACK.MEMBER_NAME is '客户 名称'
/

comment on column FEEDBACK.MEMBER_PHONE is '手机号'
/

create table GOODS
(
    ID NUMBER(18) not null
        constraint GOODS_PK
        primary key,
    ERP_GOODS_ID NUMBER(18) not null
        constraint SYS_C0069487
        check ("ERP_GOODS_ID" IS NOT NULL),
    FACTORY_ID NUMBER(4),
    HAVE_GIFT NUMBER(4) default 0,
    EVALUATION_GOOD_STAR NUMBER(18),
    EVALUATION_COUNT NUMBER(4),
    GOODS_COMMEND NUMBER(4),
    GOODS_IMG VARCHAR2(500),
    GOODS_COLLECT NUMBER(10) default 0,
    GOODS_CLICK NUMBER(10) default 0,
    CREATE_TIME DATE not null
        constraint SYS_C0069489
        check ("CREATE_TIME" IS NOT NULL),
    ERP_ACC_FLAG NUMBER(4),
    ERP_STORAGE_ID NUMBER(18),
    ERP_LOT_NO VARCHAR2(50),
    DESCRIPTION CLOB,
    IMG_NUM NUMBER(1),
    UPDATE_TIME DATE,
    CREATE_USER_ID NUMBER(18),
    UPDATE_USER_ID NUMBER(18)
)
    /

comment on table GOODS is '商品表'
/

comment on column GOODS.ID is 'id'
/

comment on column GOODS.ERP_GOODS_ID is '关联ERP货品表'
/

comment on column GOODS.FACTORY_ID is '厂家id'
/

comment on column GOODS.HAVE_GIFT is '是否有赠品'
/

comment on column GOODS.EVALUATION_GOOD_STAR is '好评星级'
/

comment on column GOODS.EVALUATION_COUNT is '评价数'
/

comment on column GOODS.GOODS_COMMEND is '商品推荐，1是，0否，默认0'
/

comment on column GOODS.GOODS_IMG is '商品图片，分号隔开'
/

comment on column GOODS.GOODS_COLLECT is '收藏数'
/

comment on column GOODS.GOODS_CLICK is '商品点击数量'
/

comment on column GOODS.CREATE_TIME is '创建时间'
/

comment on column GOODS.ERP_ACC_FLAG is 'ERP商品表类型=5为赠品'
/

comment on column GOODS.ERP_STORAGE_ID is '保管账'
/

comment on column GOODS.ERP_LOT_NO is '批号'
/

comment on column GOODS.DESCRIPTION is '详情'
/

comment on column GOODS.IMG_NUM is '货品图片数量'
/

comment on column GOODS.UPDATE_TIME is '修改时间'
/

comment on column GOODS.CREATE_USER_ID is '创建人ID'
/

comment on column GOODS.UPDATE_USER_ID is '修改人ID'
/

create table HELP
(
    ID NUMBER(18) not null
        constraint HELP_PK
        primary key,
    HELP_TITLE VARCHAR2(50)
        constraint SYS_C0069526
        check ("HELP_TITLE" IS NOT NULL),
    HELP_INFO CLOB
        constraint SYS_C0069527
        check ("HELP_INFO" IS NOT NULL),
    HELP_URL VARCHAR2(50)
        constraint SYS_C0069528
        check ("HELP_URL" IS NOT NULL),
    UPDATE_TIME DATE,
    TYPE_ID NUMBER(18)
        constraint SYS_C0069530
        check ("TYPE_ID" IS NOT NULL),
    CREATE_TIME DATE,
    HELP_SHOW VARCHAR2(20) default 'OFF',
    IS_DEL NUMBER(1) default 0,
    CREATE_USER_ID NUMBER(18),
    UPDATE_USER_ID NUMBER(18),
    HELP_SORT NUMBER(4)
)
    /

comment on table HELP is '帮助指南'
    /

    comment on column HELP.ID is 'id'
    /

    comment on column HELP.HELP_TITLE is '标题'
    /

    comment on column HELP.HELP_INFO is '帮助内容'
    /

    comment on column HELP.HELP_URL is '跳转链接'
    /

    comment on column HELP.UPDATE_TIME is '更新时间'
    /

    comment on column HELP.TYPE_ID is '帮助类型'
    /

    comment on column HELP.CREATE_TIME is '创建时间'
    /

    comment on column HELP.HELP_SHOW is '是否显示：OFF为不显示，ON显示'
    /

    comment on column HELP.IS_DEL is '是否删除：0为否，1为是'
    /

    comment on column HELP.CREATE_USER_ID is '创建人ID'
    /

    comment on column HELP.UPDATE_USER_ID is '修改人ID'
    /

    comment on column HELP.HELP_SORT is '排序'
    /

create table HELP_TYPE
(
    ID NUMBER(18) not null
        constraint HELP_TYPE_PK
        primary key,
    TYPE_NAME VARCHAR2(20),
    HELP_SHOW VARCHAR2(20) default 'OFF',
    CREATE_TIME DATE,
    IS_DEL NUMBER(1) default 0,
    UPDATE_TIME DATE,
    CREATE_USER_ID NUMBER(18),
    UPDATE_USER_ID NUMBER(18),
    TYPE_SORT NUMBER(4)
)
    /

comment on table HELP_TYPE is '帮助类型'
/

comment on column HELP_TYPE.ID is 'id'
/

comment on column HELP_TYPE.TYPE_NAME is '类型名称'
/

comment on column HELP_TYPE.HELP_SHOW is '是否显示，OFF 不显示 ON 显示'
/

comment on column HELP_TYPE.CREATE_TIME is '创建时间'
/

comment on column HELP_TYPE.IS_DEL is '是否删除： 0为否 1为是 默认0'
/

comment on column HELP_TYPE.UPDATE_TIME is '更新时间'
/

comment on column HELP_TYPE.CREATE_USER_ID is '创建人ID'
/

comment on column HELP_TYPE.UPDATE_USER_ID is '修改人ID'
/

comment on column HELP_TYPE.TYPE_SORT is '类型排序'
/

create table LINK
(
    ID NUMBER(18) not null
        constraint LINK_PK
        primary key,
    LINK_TITLE VARCHAR2(50) not null
        constraint SYS_C0069548
        check ("LINK_TITLE" IS NOT NULL),
    LINK_URL VARCHAR2(50) not null
        constraint SYS_C0069549
        check ("LINK_URL" IS NOT NULL),
    LINK_PIC VARCHAR2(500) not null
        constraint SYS_C0069550
        check ("LINK_PIC" IS NOT NULL),
    LINK_SORT NUMBER(4) default 0 not null
        constraint SYS_C0069551
        check ("LINK_SORT" IS NOT NULL),
    CREATE_TIME DATE
)
    /

comment on table LINK is '友情链接'
/

comment on column LINK.ID is 'id'
/

comment on column LINK.LINK_TITLE is '标题'
/

comment on column LINK.LINK_URL is '链接'
/

comment on column LINK.LINK_PIC is '图片'
/

comment on column LINK.LINK_SORT is '排序'
/

comment on column LINK.CREATE_TIME is '创建时间'
/

create table MEMBER
(
    ID NUMBER(18) not null
        constraint MEMBER_PK
        primary key
        constraint SYS_C0068681
        check ("ID" IS NOT NULL),
    USER_NAME VARCHAR2(50),
    NAME VARCHAR2(50),
    OPENID VARCHAR2(50),
    SEX NUMBER(1),
    HEAD_IMG VARCHAR2(50),
    CREATE_TIME DATE not null
        constraint SYS_C0068884
        check ("CREATE_TIME" IS NOT NULL),
    PASSWORD VARCHAR2(50) not null
        constraint SYS_C0068885
        check ("PASSWORD" IS NOT NULL),
    SALT VARCHAR2(50),
    ERP_USER_ID NUMBER(18) default 0,
    TELEPHONE VARCHAR2(20) not null
        constraint SYS_C0068755
        check ("TELEPHONE" IS NOT NULL),
    MEMBER_LOGIN_NUM NUMBER(18) default 0,
    AUTH_MODIFY_PWD_TIME DATE,
    SEND_ACODE_TIMES NUMBER(4) default 0,
    LOCK_TIME DATE,
    IS_ERP NUMBER(4),
    APPLY NUMBER(18)
)
    /

comment on table MEMBER is '客户'
/

comment on column MEMBER.ID is '主键'
/

comment on column MEMBER.USER_NAME is '用户名'
/

comment on column MEMBER.NAME is '姓名'
/

comment on column MEMBER.OPENID is 'openid'
/

comment on column MEMBER.SEX is '性别'
/

comment on column MEMBER.HEAD_IMG is '头像'
/

comment on column MEMBER.CREATE_TIME is '创建时间'
/

comment on column MEMBER.PASSWORD is '密码'
/

comment on column MEMBER.SALT is 'salt'
/

comment on column MEMBER.ERP_USER_ID is 'erp用户id'
/

comment on column MEMBER.TELEPHONE is '手机号'
/

comment on column MEMBER.MEMBER_LOGIN_NUM is '登录次数'
/

comment on column MEMBER.AUTH_MODIFY_PWD_TIME is '修改密码时间'
/

comment on column MEMBER.SEND_ACODE_TIMES is '发送验证码次数'
/

comment on column MEMBER.LOCK_TIME is '锁定时间'
/

comment on column MEMBER.IS_ERP is '是否ERP旧用户'
/

comment on column MEMBER.APPLY is '用户申请ID'
/

create table MEMBER_LOG
(
    ID NUMBER(18) not null
        constraint MEMBER_LOG_PK
        primary key
        constraint SYS_C0069164
        check ("ID" IS NOT NULL),
    MEMBER_ID NUMBER(18),
    CREATE_TIME DATE,
    IP VARCHAR2(20) not null
        constraint SYS_C0069165
        check ("IP" IS NOT NULL),
    META_DATA CLOB
)
    /

comment on table MEMBER_LOG is '客户日志'
/

comment on column MEMBER_LOG.ID is '主键'
/

comment on column MEMBER_LOG.MEMBER_ID is '客户id'
/

comment on column MEMBER_LOG.CREATE_TIME is '创建时间'
/

comment on column MEMBER_LOG.IP is '登录地址'
/

create table ORDER_GOODS
(
    ID NUMBER(18) not null
        constraint ORDER_GOODS_PK
        primary key,
    CREATE_TIME DATE,
    GOODS_NUM NUMBER(18) default 1,
    ORDER_ID NUMBER(18),
    GOODS_ID NUMBER(18),
    GOODS_NAME VARCHAR2(50),
    GOODS_PRICE NUMBER(18),
    GOODS_IMAGE VARCHAR2(500),
    GOODS_PAY_PRICE NUMBER(18),
    MEMBER_ID NUMBER(18),
    GOOD_TYPE NUMBER(10) default 1,
    ACTIVITY_ID NUMBER(18),
    GC_ID NUMBER(18),
    GOODS_SPEC VARCHAR2(100),
    ERP_LOT_NO VARCHAR2(50),
    ERP_STORAGE_ID NUMBER(18),
    SELL_NUM NUMBER(18) default 0,
    REFUND_NUM NUMBER(18) default 0,
    ERP_LEASTSALEQTY NUMBER(18),
    ERP_GOODS_UNIT VARCHAR2(20),
    ERP_GOODS_ID NUMBER(18),
    PRICE_ID NUMBER(18),
    AMOUNT_NUM NUMBER(18),
    AMOUNT_PAY NUMBER(18),
    LOT_ID NUMBER(18),
    GIFT_ID NUMBER(18),
    AS_ID NUMBER(18)
)
    /

comment on table ORDER_GOODS is '订单快照表'
/

comment on column ORDER_GOODS.CREATE_TIME is '时间'
/

comment on column ORDER_GOODS.GOODS_NUM is '数量'
/

comment on column ORDER_GOODS.ORDER_ID is '订单id'
/

comment on column ORDER_GOODS.GOODS_ID is '商品id'
/

comment on column ORDER_GOODS.GOODS_NAME is '商品名称'
/

comment on column ORDER_GOODS.GOODS_PRICE is '商品价格'
/

comment on column ORDER_GOODS.GOODS_IMAGE is '商品图片'
/

comment on column ORDER_GOODS.GOODS_PAY_PRICE is '商品实际成交价(折扣价)'
/

comment on column ORDER_GOODS.MEMBER_ID is '客户id'
/

comment on column ORDER_GOODS.GOOD_TYPE is '1默认2限时折扣商品3组合套装4赠品5加价购活动商品6加价购换购商品'
/

comment on column ORDER_GOODS.ACTIVITY_ID is '促销活动ID（限时折扣ID/优惠套装ID）与goods_type搭配使用'
/

comment on column ORDER_GOODS.GC_ID is '分类id'
/

comment on column ORDER_GOODS.GOODS_SPEC is '商品规格'
/

comment on column ORDER_GOODS.ERP_LOT_NO is '批号'
/

comment on column ORDER_GOODS.ERP_STORAGE_ID is '保管帐id'
/

comment on column ORDER_GOODS.SELL_NUM is 'erp实际发货数量'
/

comment on column ORDER_GOODS.REFUND_NUM is '已退数量'
/

comment on column ORDER_GOODS.ERP_LEASTSALEQTY is 'erp最小销售数量'
/

comment on column ORDER_GOODS.ERP_GOODS_UNIT is 'erp基本单位'
/

comment on column ORDER_GOODS.ERP_GOODS_ID is 'erp商品id'
/

comment on column ORDER_GOODS.PRICE_ID is '价格类型id'
/

comment on column ORDER_GOODS.AMOUNT_NUM is '小计'
/

comment on column ORDER_GOODS.AMOUNT_PAY is '优惠后小计'
/

comment on column ORDER_GOODS.LOT_ID is '批号id'
/

comment on column ORDER_GOODS.GIFT_ID is '赠品id'
/

comment on column ORDER_GOODS.AS_ID is '活动策略ID'
/

create table ORDER_INFO
(
    ID NUMBER(20) not null
        constraint ORDER_INFO_PK
        primary key
        constraint SYS_C0068731
        check ("ID" IS NOT NULL),
    ORDER_NO VARCHAR2(50) not null
        constraint SYS_C0069106
        check ("ORDER_NO" IS NOT NULL),
    ORDER_TYPE VARCHAR2(20) not null
        constraint SYS_C0069107
        check ("ORDER_TYPE" IS NOT NULL),
    MEMBER_ID NUMBER(20) not null
        constraint SYS_C0069108
        check ("MEMBER_ID" IS NOT NULL),
    ORDER_AMOUNT NUMBER(18) default NULL,
    GOODS_AMOUNT NUMBER(18) default NULL,
    ACTUALLY_MONEY NUMBER(18) default NULL,
    CREATE_TIME DATE not null
        constraint SYS_C0069110
        check ("CREATE_TIME" IS NOT NULL),
    PAY_TIME DATE default NULL,
    PAY_METHOD VARCHAR2(50) default NULL,
    PAY_FLOW_NO VARCHAR2(50) default NULL,
    REFUND_FLOW_NO VARCHAR2(50) default NULL,
    LOGISTICS VARCHAR2(50) default NULL,
    ADDRESS_DETAIL VARCHAR2(255) default NULL,
    REMARK VARCHAR2(255) default NULL,
    REFUND_AMOUNT NUMBER(18) default NULL,
    RETURN_LOGISTICS VARCHAR2(50) default NULL,
    MEMBER_NAME VARCHAR2(50),
    MEMBER_EMAIL VARCHAR2(50),
    MEMBER_PHONE VARCHAR2(20),
    FINNSHED_TIME DATE,
    EVALUATION_STATE VARCHAR2(20),
    EVALUATION_AGAIN_STATE VARCHAR2(20),
    ORDER_STATE VARCHAR2(20),
    REFUND_STATE VARCHAR2(20),
    LOCK_STATE VARCHAR2(20),
    DELETE_STATE NUMBER(4) default 0,
    DELAY_TIME DATE,
    ORDER_FROM NUMBER(4) not null
        constraint SYS_C0069097
        check ("ORDER_FROM" IS NOT NULL),
    RPT_AMOUNT NUMBER(18),
    ERP_ORDER_ID NUMBER(18),
    SETTLE_TYPE_ID NUMBER(18),
    INV_TYPE VARCHAR2(20),
    INV_DEMAND VARCHAR2(50),
    REC_MONEY NUMBER(18),
    CREDIT_DAYS NUMBER(11),
    TRANSLINENAME VARCHAR2(20),
    ZX_PH_ORDER NUMBER(11),
    ERP_CUSTOMER_ID NUMBER(18),
    TARGET_POS_ID NUMBER(18),
    EXP_STATUS VARCHAR2(255),
    EXP_REMARK VARCHAR2(4000),
    ALL_DISCOUNT NUMBER(18)
)
    /

comment on table ORDER_INFO is '订单信息'
/

comment on column ORDER_INFO.ORDER_NO is '订单号'
/

comment on column ORDER_INFO.ORDER_TYPE is '订单类型'
/

comment on column ORDER_INFO.MEMBER_ID is '客户id'
/

comment on column ORDER_INFO.ORDER_AMOUNT is '总额'
/

comment on column ORDER_INFO.GOODS_AMOUNT is '应付金额'
/

comment on column ORDER_INFO.ACTUALLY_MONEY is '实付金额'
/

comment on column ORDER_INFO.CREATE_TIME is '创建时间'
/

comment on column ORDER_INFO.PAY_TIME is '支付时间'
/

comment on column ORDER_INFO.PAY_METHOD is '支付方式'
/

comment on column ORDER_INFO.PAY_FLOW_NO is '支付流水号'
/

comment on column ORDER_INFO.REFUND_FLOW_NO is '退款流水号'
/

comment on column ORDER_INFO.LOGISTICS is '物流号'
/

comment on column ORDER_INFO.ADDRESS_DETAIL is '地址详情'
/

comment on column ORDER_INFO.REMARK is '备注'
/

comment on column ORDER_INFO.REFUND_AMOUNT is '退款金额'
/

comment on column ORDER_INFO.RETURN_LOGISTICS is '退货物流号'
/

comment on column ORDER_INFO.MEMBER_NAME is '客户姓名'
/

comment on column ORDER_INFO.MEMBER_EMAIL is '客户电子邮箱'
/

comment on column ORDER_INFO.MEMBER_PHONE is '客户手机'
/

comment on column ORDER_INFO.FINNSHED_TIME is '订单完成时间'
/

comment on column ORDER_INFO.EVALUATION_STATE is '评价状态 0未评价，1已评价，2已过期未评价'
/

comment on column ORDER_INFO.EVALUATION_AGAIN_STATE is '追加评价状态 0未评价，1已评价，2已过期未评价'
/

comment on column ORDER_INFO.ORDER_STATE is '订单状态：0(已取消)10(默认):未付款;20:已付款;30:已发货;40:已收货;'
/

comment on column ORDER_INFO.REFUND_STATE is '退款状态:0是无退款,1是部分退款,2是全部退款'
/

comment on column ORDER_INFO.LOCK_STATE is '冗余，锁定状态:0是正常,大于0是锁定,默认是0'
/

comment on column ORDER_INFO.DELETE_STATE is '删除状态0未删除1放入回收站2彻底删除'
/

comment on column ORDER_INFO.DELAY_TIME is '延迟时间,默认为0'
/

comment on column ORDER_INFO.ORDER_FROM is '1WEB2mobile'
/

comment on column ORDER_INFO.RPT_AMOUNT is '优惠金额'
/

comment on column ORDER_INFO.ERP_ORDER_ID is '同步ERP销售主表id'
/

comment on column ORDER_INFO.SETTLE_TYPE_ID is 'ERP结算方式'
/

comment on column ORDER_INFO.INV_TYPE is '发票类型'
/

comment on column ORDER_INFO.INV_DEMAND is '发票要求'
/

comment on column ORDER_INFO.REC_MONEY is '欠款金额'
/

comment on column ORDER_INFO.CREDIT_DAYS is '可用信用天数'
/

comment on column ORDER_INFO.TRANSLINENAME is '运输线路'
/

comment on column ORDER_INFO.ZX_PH_ORDER is '线序'
/

comment on column ORDER_INFO.ERP_CUSTOMER_ID is 'erp客户id'
/

comment on column ORDER_INFO.TARGET_POS_ID is '送货地址id'
/

comment on column ORDER_INFO.EXP_STATUS is '异常状态'
/

comment on column ORDER_INFO.EXP_REMARK is '异常备注'
/

comment on column ORDER_INFO.ALL_DISCOUNT is '是否全场折扣'
/

create table ORDER_LOG
(
    ID NUMBER(18) not null
        constraint ORDER_LOG_PK
        primary key,
    ORDER_ID NUMBER(18) not null
        constraint SYS_C0069139
        check ("ORDER_ID" IS NOT NULL),
    ORDER_STATUS VARCHAR2(20),
    CREATE_TIME DATE,
    USER_ID NUMBER(18),
    LOG_ROLE VARCHAR2(20),
    LOG_MSG CLOB,
    DONE_TIME DATE,
    TO_ERP_NUM VARCHAR2(20)
)
    /

comment on table ORDER_LOG is '订单日志'
/

comment on column ORDER_LOG.ORDER_ID is '订单id'
/

comment on column ORDER_LOG.ORDER_STATUS is '订单状态'
/

comment on column ORDER_LOG.CREATE_TIME is '时间'
/

comment on column ORDER_LOG.USER_ID is '操作人'
/

comment on column ORDER_LOG.LOG_ROLE is '操作角色'
/

comment on column ORDER_LOG.LOG_MSG is '文字描述'
/

comment on column ORDER_LOG.DONE_TIME is '完成时间'
/

comment on column ORDER_LOG.TO_ERP_NUM is '下发版本（次数）'
/

create table REASON
(
    ID NUMBER(18) not null
        constraint REASON_PK
        primary key,
    REASON_INFO VARCHAR2(50),
    SORT NUMBER(4) default 0,
    CREATE_TIME DATE,
    UPDATE_TIME DATE default null,
    IS_DEL NUMBER(1) default 0,
    ERP_REASON_ID NUMBER(18),
    CREATE_USER_ID NUMBER(18),
    UPDATE_USER_ID NUMBER(18)
)
    /

comment on table REASON is '退货退款原因'
/

comment on column REASON.ID is 'id'
/

comment on column REASON.REASON_INFO is '退换货原因'
/

comment on column REASON.SORT is '排序 '
/

comment on column REASON.CREATE_TIME is '创建时间'
/

comment on column REASON.UPDATE_TIME is '更新时间'
/

comment on column REASON.IS_DEL is '是否删除：0为否，1为是，默认0'
/

comment on column REASON.ERP_REASON_ID is 'erp退货原因ID'
/

comment on column REASON.CREATE_USER_ID is '创建人ID'
/

comment on column REASON.UPDATE_USER_ID is '修改人ID'
/

create table REFUND_DETAIL
(
    ID NUMBER(18) not null,
    REFUND_ID NUMBER(18),
    ORDER_ID NUMBER(18),
    LOT_NO VARCHAR2(50),
    REFUND_AMOUNT NUMBER(11),
    PAY_AMOUNT NUMBER(11),
    PD_AMOUNT NUMBER(11),
    REFUND_CODE VARCHAR2(50),
    REFUND_STATE VARCHAR2(20),
    REFUND_FINNSHED_TIME DATE,
    CREATE_TIME DATE,
    GOODS_ID NUMBER(18),
    ORDER_GOODS_ID NUMBER(18),
    GOODS_NAME VARCHAR2(255),
    GOODS_NUM NUMBER(11),
    GODOS_IMAGE VARCHAR2(255),
    ORDER_GOODS_TYPE VARCHAR2(255),
    LOT_ID NUMBER(18),
    BATCH_ID NUMBER(18),
    SRC_ERP_ORDER_ID NUMBER(18),
    SRC_ERP_ORDER_DTL_ID NUMBER(18),
    GOODS_SOURCE NUMBER(11),
    ERP_STORAGE_ID NUMBER(18),
    REASON_ID NUMBER(18),
    REASON_INFO VARCHAR2(255),
    GOODS_PRICE NUMBER(18),
    GOODS_PAY_PRICE NUMBER(18)
)
    /

comment on table REFUND_DETAIL is '退单详情'
/

comment on column REFUND_DETAIL.ID is 'id'
/

comment on column REFUND_DETAIL.REFUND_ID is '退货退款单id'
/

comment on column REFUND_DETAIL.ORDER_ID is '订单id'
/

comment on column REFUND_DETAIL.LOT_NO is '批号'
/

comment on column REFUND_DETAIL.REFUND_AMOUNT is '退款金额'
/

comment on column REFUND_DETAIL.PAY_AMOUNT is '在线退款金额'
/

comment on column REFUND_DETAIL.PD_AMOUNT is '账期退款金额'
/

comment on column REFUND_DETAIL.REFUND_CODE is '退款支付代码'
/

comment on column REFUND_DETAIL.REFUND_STATE is '退款状态:1为处理中,2为已完成,默认为1'
/

comment on column REFUND_DETAIL.REFUND_FINNSHED_TIME is '退款完成时间'
/

comment on column REFUND_DETAIL.CREATE_TIME is '创建时间'
/

comment on column REFUND_DETAIL.GOODS_ID is '商品id'
/

comment on column REFUND_DETAIL.ORDER_GOODS_ID is '订单商品id'
/

comment on column REFUND_DETAIL.GOODS_NAME is '商品名称'
/

comment on column REFUND_DETAIL.GOODS_NUM is '数量'
/

comment on column REFUND_DETAIL.GODOS_IMAGE is '商品图片'
/

comment on column REFUND_DETAIL.ORDER_GOODS_TYPE is '商品类型'
/

comment on column REFUND_DETAIL.LOT_ID is '批号ID'
/

comment on column REFUND_DETAIL.BATCH_ID is '批次ID'
/

comment on column REFUND_DETAIL.SRC_ERP_ORDER_ID is '销售总单ID'
/

comment on column REFUND_DETAIL.SRC_ERP_ORDER_DTL_ID is '销售细单ID'
/

comment on column REFUND_DETAIL.GOODS_SOURCE is '单据类型ID'
/

comment on column REFUND_DETAIL.ERP_STORAGE_ID is '保管帐id'
/

comment on column REFUND_DETAIL.REASON_ID is '原因id，0为其他'
/

comment on column REFUND_DETAIL.REASON_INFO is '原因内容'
/

comment on column REFUND_DETAIL.GOODS_PRICE is '单价'
/

comment on column REFUND_DETAIL.GOODS_PAY_PRICE is '商品优惠平均成交价'
/

create table REFUND_RETURN
(
    ID NUMBER(18) not null
        constraint REFUND_RETURN_PK
        primary key,
    ORDER_ID NUMBER(18),
    ORDER_NO VARCHAR2(100),
    APPLY_NO VARCHAR2(100),
    MEMBER_ID NUMBER(18),
    MEMBER_NAME VARCHAR2(50),
    GOODS_ID NUMBER(18),
    ORDER_GOODS_ID NUMBER(18),
    REFUND_AMOUNT NUMBER(11),
    APPLY_TYPE VARCHAR2(50),
    REFUND_STATE VARCHAR2(50),
    RETURN_TYPE VARCHAR2(50),
    CREATE_TIME DATE,
    USER_ID NUMBER(18),
    USER_TIME DATE,
    REASON_ID NUMBER(18),
    REASON_INFO VARCHAR2(100),
    PIC_INFO VARCHAR2(500),
    MEMBER_MESSAGE VARCHAR2(50),
    USER_MESSAGE VARCHAR2(50),
    SHIP_TIME DATE,
    DELAY_TIME DATE,
    RECEIVE_TIME DATE,
    RECEIVE_MESSAGE VARCHAR2(50),
    FINNSHED_TIME DATE,
    ERP_CUSTOMER_ID NUMBER(18),
    REFUND_MESSAGE VARCHAR2(4000)
)
    /

comment on table REFUND_RETURN is '退货退款单'
/

comment on column REFUND_RETURN.ID is 'id'
/

comment on column REFUND_RETURN.ORDER_ID is '订单ID'
/

comment on column REFUND_RETURN.ORDER_NO is '订单编号'
/

comment on column REFUND_RETURN.APPLY_NO is '申请编号'
/

comment on column REFUND_RETURN.MEMBER_ID is '客户id'
/

comment on column REFUND_RETURN.MEMBER_NAME is '买家会员名'
/

comment on column REFUND_RETURN.GOODS_ID is '商品id，0为全部退款'
/

comment on column REFUND_RETURN.ORDER_GOODS_ID is '订单商品ID,全部退款是0'
/

comment on column REFUND_RETURN.REFUND_AMOUNT is '退款金额'
/

comment on column REFUND_RETURN.APPLY_TYPE is '申请类型:1为退款,2为退货,默认为1'
/

comment on column REFUND_RETURN.REFUND_STATE is '申请状态:1为处理中,2为待管理员处理,3为已完成,默认为1'
/

comment on column REFUND_RETURN.RETURN_TYPE is '退货类型:1为不用退货,2为需要退货,默认为1'
/

comment on column REFUND_RETURN.CREATE_TIME is '创建时间'
/

comment on column REFUND_RETURN.USER_ID is '管理 员id'
/

comment on column REFUND_RETURN.USER_TIME is '管理内容审核时间'
/

comment on column REFUND_RETURN.REASON_ID is '原因id，0为其他'
/

comment on column REFUND_RETURN.REASON_INFO is '原因内容 '
/

comment on column REFUND_RETURN.PIC_INFO is '图片'
/

comment on column REFUND_RETURN.MEMBER_MESSAGE is '申请原因'
/

comment on column REFUND_RETURN.USER_MESSAGE is '管理员备注'
/

comment on column REFUND_RETURN.SHIP_TIME is '发货时间'
/

comment on column REFUND_RETURN.DELAY_TIME is '收货延迟时间'
/

comment on column REFUND_RETURN.RECEIVE_TIME is '收货时间'
/

comment on column REFUND_RETURN.RECEIVE_MESSAGE is '收货备注'
/

comment on column REFUND_RETURN.FINNSHED_TIME is '完成时间'
/

comment on column REFUND_RETURN.ERP_CUSTOMER_ID is 'erp客户id'
/

comment on column REFUND_RETURN.REFUND_MESSAGE is '退货单异常信息'
/

create table SETTING
(
    ID NUMBER(18) not null
        constraint SETTING_PK
        primary key,
    NAME VARCHAR2(20),
    VALUE CLOB,
    CREATE_TIME DATE,
    REMARK VARCHAR2(255),
    TYPE VARCHAR2(255),
    SORT NUMBER(4),
    KEY VARCHAR2(255),
    USER_ID NUMBER(18)
)
    /

comment on table SETTING is '系统设置'
/

comment on column SETTING.ID is 'id'
/

comment on column SETTING.NAME is '名称'
/

comment on column SETTING.VALUE is '值'
/

comment on column SETTING.CREATE_TIME is '创建时间'
/

comment on column SETTING.REMARK is '备注'
/

comment on column SETTING.TYPE is '类型'
/

comment on column SETTING.SORT is '排序'
/

comment on column SETTING.KEY is 'KEY'
/

comment on column SETTING.USER_ID is '创建人'
/

create table SMS_LOG
(
    ID NUMBER(18) not null
        constraint SMS_LOG_PK
        primary key,
    LOG_PHONE VARCHAR2(20),
    LOG_CAPTCHA VARCHAR2(20),
    LOG_IP VARCHAR2(20),
    LOG_MSG VARCHAR2(50),
    LOG_TYPE VARCHAR2(50),
    CREATE_TIME DATE,
    MEMBER_ID NUMBER(18),
    MEMBER_NAME VARCHAR2(20)
)
    /

comment on table SMS_LOG is '短信日志'
/

comment on column SMS_LOG.ID is '主键'
/

comment on column SMS_LOG.LOG_PHONE is '手机号'
/

comment on column SMS_LOG.LOG_CAPTCHA is '短信动态码'
/

comment on column SMS_LOG.LOG_IP is '请求IP'
/

comment on column SMS_LOG.LOG_MSG is '短信内容'
/

comment on column SMS_LOG.LOG_TYPE is '短信类型:1为注册,2为登录,3为找回密码,默认为1'
/

comment on column SMS_LOG.CREATE_TIME is '创建时间'
/

comment on column SMS_LOG.MEMBER_ID is '会员 id，注册为0'
/

comment on column SMS_LOG.MEMBER_NAME is '会员名'
/

create table TMP_GOODS_PIC
(
    GOODS_ID NUMBER,
    PIC VARCHAR2(100)
)
    /

comment on table TMP_GOODS_PIC is '临时表，货品图片迁移用'
/

create table USER_INFO
(
    ID NUMBER(18) not null
        constraint USER_PK
        primary key
        constraint SYS_C0068676
        check ("ID" IS NOT NULL),
    USER_NAME VARCHAR2(50) not null
        constraint SYS_C0068677
        check ("USER_NAME" IS NOT NULL),
    NAME VARCHAR2(50),
    PASSWORD VARCHAR2(50) not null
        constraint SYS_C0068678
        check ("PASSWORD" IS NOT NULL),
    SALT VARCHAR2(20) not null
        constraint SYS_C0068679
        check ("SALT" IS NOT NULL),
    ROLE VARCHAR2(50),
    CREATE_TIME DATE,
    IS_DEL NUMBER(1),
    ERP_EMPLOYEE_ID NUMBER(18)
)
    /

comment on table USER_INFO is '后台用户'
/

comment on column USER_INFO.ID is '主键'
/

comment on column USER_INFO.USER_NAME is '用户名'
/

comment on column USER_INFO.NAME is '姓名'
/

comment on column USER_INFO.PASSWORD is '密码'
/

comment on column USER_INFO.SALT is 'salt值'
/

comment on column USER_INFO.ROLE is '角色'
/

comment on column USER_INFO.CREATE_TIME is '创建时间'
/

comment on column USER_INFO.IS_DEL is '是否删除'
/

comment on column USER_INFO.ERP_EMPLOYEE_ID is 'erp账号id'
/

create unique index DATE_USER_NAME_UINDEX
    on USER_INFO (USER_NAME)
    /

create table WEB_PAGE
(
    ID NUMBER(18) not null
        constraint WEB_PK
        primary key,
    TITLE VARCHAR2(50),
    UPDATE_TIME DATE,
    CREATE_TIME DATE,
    META_DATA CLOB,
    CREATE_USER_ID NUMBER(18),
    UPDATE_USER_ID NUMBER(18),
    IS_DEL NUMBER(1) default 0,
    HINT_ICON VARCHAR2(20),
    SORT_NUM NUMBER(4),
    IS_USE VARCHAR2(20) default 'OFF'
)
    /

comment on table WEB_PAGE is '专区页'
/

comment on column WEB_PAGE.ID is 'id'
/

comment on column WEB_PAGE.TITLE is '名称'
/

comment on column WEB_PAGE.UPDATE_TIME is '更新时间'
/

comment on column WEB_PAGE.CREATE_TIME is '创建时间'
/

comment on column WEB_PAGE.META_DATA is '页面数据'
/

comment on column WEB_PAGE.CREATE_USER_ID is '创建人ID'
/

comment on column WEB_PAGE.UPDATE_USER_ID is '更新人ID'
/

comment on column WEB_PAGE.IS_DEL is '是否删除：0为否 1为是'
/

comment on column WEB_PAGE.HINT_ICON is '提示图标
NULL 无
NEW 新上市
HOT 热门'
/

comment on column WEB_PAGE.SORT_NUM is '页面排序'
/

comment on column WEB_PAGE.IS_USE is '是否开启
OFF 关闭
ON 启用'
/

create table ADV_POSITION
(
    ID NUMBER(18) not null
        primary key,
    AP_DISPLAY VARCHAR2(20),
    CLICK_NUM NUMBER(11) default 0,
    CREATE_TIME DATE,
    ADV_START_DATE DATE,
    ADV_END_DATE DATE,
    IS_DEL NUMBER(1) default 0,
    IS_USE VARCHAR2(20) default 'OFF',
    ADV_NAME VARCHAR2(50),
    IMG_NUM NUMBER(5),
    META_DATA CLOB,
    UPDATE_TIME DATE,
    CREATE_USER_ID NUMBER(18),
    UPDATE_USER_ID NUMBER(18)
)
    /

comment on table ADV_POSITION is '广告位'
/

comment on column ADV_POSITION.ID is '广告位id'
/

comment on column ADV_POSITION.AP_DISPLAY is '广告位展示样式
TOP_BANNER           顶部广告位
CAROUSEL_BANNER      轮播广告位
CENTER_ONLY          中部单图
CENTER_MORE_LITTLE   中部多图小
CENTER_MORE_BIG      中部多图大
BROADSIDE_BANNER     侧边广告位
POPUP_BANNER         弹窗广告位'
/

comment on column ADV_POSITION.CREATE_TIME is '创建时间'
/

comment on column ADV_POSITION.ADV_START_DATE is '广告开始时间'
/

comment on column ADV_POSITION.ADV_END_DATE is '广告结束时间'
/

comment on column ADV_POSITION.IS_DEL is '是否删除：0为否，1为是'
/

comment on column ADV_POSITION.IS_USE is '广告位是否启用
OFF 关闭
ON 启用'
/

comment on column ADV_POSITION.ADV_NAME is '广告名称'
/

comment on column ADV_POSITION.IMG_NUM is '图片数量'
/

comment on column ADV_POSITION.META_DATA is '广告数据：
image广告图片
imageUrl图片链接
imageStartTime图片开始时间
imageEndTime图片结束时间'
/

comment on column ADV_POSITION.UPDATE_TIME is '更新时间'
/

comment on column ADV_POSITION.CREATE_USER_ID is '创建者ID'
/

comment on column ADV_POSITION.UPDATE_USER_ID is '修改者ID'
/

create table FLOOR
(
    ID NUMBER(18) not null
        constraint FLOOR_PK
        primary key,
    TYPE VARCHAR2(20),
    TITLE VARCHAR2(100),
    META_DATA CLOB not null,
    CREATE_TIME DATE,
    UPDATE_TIME DATE,
    CREATE_USER_ID NUMBER(18),
    UPDATE_USER_ID NUMBER(18),
    IS_DEL NUMBER(1) default 0,
    GOODS_NUM NUMBER(5),
    FLOOR_KEY VARCHAR2(20),
    HUE VARCHAR2(20),
    IS_USE VARCHAR2(20) default 'OFF',
    FLOOR_SORT NUMBER(4)
)
    /

comment on table FLOOR is '楼层'
/

comment on column FLOOR.ID is '主键'
/

comment on column FLOOR.TYPE is '类型:

SINGLE_LINE 单行样式
MULTI_LINE 多行样式
BG_IMAGE 背景图样式
SUGGEST_GOODS 商品推荐样式
'
/

comment on column FLOOR.TITLE is '楼层名称'
/

comment on column FLOOR.META_DATA is '楼层数据'
/

comment on column FLOOR.CREATE_TIME is '创建时间'
/

comment on column FLOOR.UPDATE_TIME is '更新时间'
/

comment on column FLOOR.CREATE_USER_ID is '创建用户ID'
/

comment on column FLOOR.UPDATE_USER_ID is '更新用户ID'
/

comment on column FLOOR.IS_DEL is '是否删除 0 否 1 是'
/

comment on column FLOOR.GOODS_NUM is '商品数量'
/

comment on column FLOOR.FLOOR_KEY is '楼层KEY'
/

comment on column FLOOR.HUE is '色调'
/

comment on column FLOOR.IS_USE is '是否启动
OFF 关闭
ON 启用'
/

comment on column FLOOR.FLOOR_SORT is '楼层排序'
/

create table CACHE_CUSTOMER_GOODS
(
    CUSTOMER_ID NUMBER(18),
    GOODS_ID NUMBER(18)
)
    /

comment on table CACHE_CUSTOMER_GOODS is '客户可购货品缓存表'
/

comment on column CACHE_CUSTOMER_GOODS.CUSTOMER_ID is '客户ID'
/

comment on column CACHE_CUSTOMER_GOODS.GOODS_ID is '货品ID'
/

create index "un_index"
    on CACHE_CUSTOMER_GOODS (CUSTOMER_ID, GOODS_ID)
    /

create table DELIVERY_AMOUNT
(
    ID NUMBER(18) not null
        constraint DELIVERY_AMOUNT_PK
        primary key,
    AREA_ID VARCHAR2(20),
    AREA_NAME VARCHAR2(60),
    DA_AMOUNT NUMBER(11),
    DA_NAME VARCHAR2(50),
    TYPE VARCHAR2(20),
    CREATE_TIME DATE,
    CREATE_USER_ID NUMBER(18),
    UPDATE_TIME DATE,
    UPDATE_USER_ID NUMBER(18),
    IS_USE VARCHAR2(20)
)
    /

comment on table DELIVERY_AMOUNT is '起送规则'
/

comment on column DELIVERY_AMOUNT.ID is 'ID'
/

comment on column DELIVERY_AMOUNT.AREA_ID is 'erp地区id'
/

comment on column DELIVERY_AMOUNT.AREA_NAME is 'erp地区名称'
/

comment on column DELIVERY_AMOUNT.DA_AMOUNT is '起送金额'
/

comment on column DELIVERY_AMOUNT.DA_NAME is '起送规则名称'
/

comment on column DELIVERY_AMOUNT.TYPE is '地区类型
 default 默认
 province 省
 city 市
 district 区'
/

comment on column DELIVERY_AMOUNT.CREATE_TIME is '创建时间'
/

comment on column DELIVERY_AMOUNT.CREATE_USER_ID is '创建人ID'
/

comment on column DELIVERY_AMOUNT.UPDATE_TIME is '修改时间'
/

comment on column DELIVERY_AMOUNT.UPDATE_USER_ID is '修改人ID'
/

comment on column DELIVERY_AMOUNT.IS_USE is '是否启用：OFF 停用 ON 启用'
/

create table WEB_PAGE_CUSTOMER_SET
(
    ID NUMBER(18) not null
        constraint WEB_PAGE_CUSTOMER_SET_PK
        primary key,
    WEB_PAGE_ID NUMBER(18),
    CUSTOMER_SET_ID NUMBER(18),
    CREATE_TIME DATE,
    TYPE VARCHAR2(20)
)
    /

comment on table WEB_PAGE_CUSTOMER_SET is '专区客户集合'
/

comment on column WEB_PAGE_CUSTOMER_SET.ID is '主键ID'
/

comment on column WEB_PAGE_CUSTOMER_SET.WEB_PAGE_ID is '专区ID'
/

comment on column WEB_PAGE_CUSTOMER_SET.CUSTOMER_SET_ID is '客户集合ID'
/

comment on column WEB_PAGE_CUSTOMER_SET.CREATE_TIME is '创建时间'
/

comment on column WEB_PAGE_CUSTOMER_SET.TYPE is '可见类型
全部可见 ALL_VISIBLE
部分可见 PARTIALLY_VISIBLE
部分不可见 PARTIALLY_IN_VISIBLE'
/

create table ACTIVITY
(
    ID NUMBER(18) not null
        constraint ACTIVITY_PK
        primary key,
    CONTENT VARCHAR2(2000),
    ABBREVIATION VARCHAR2(2000),
    CUSTOM_SET_TYPE NUMBER(10),
    ACTIVITY_STRATEGY NUMBER(10),
    WARM_TIME DATE,
    START_EFFECTIVE_TIME VARCHAR2(20),
    END_EFFECTIVE_TIME VARCHAR2(20),
    START_TIME DATE,
    END_TIME DATE,
    TIMES NUMBER(11),
    IS_SUPERPOSITION NUMBER(4) default 0,
    CREATE_TIME DATE,
    USER_ID NUMBER(18),
    WEEK NUMBER(4),
    TIMES_STRATEGY NUMBER(10),
    IS_USE NUMBER(18)
)
    /

comment on table ACTIVITY is '活动主表'
/

comment on column ACTIVITY.ID is 'id'
/

comment on column ACTIVITY.CONTENT is '促销内容'
/

comment on column ACTIVITY.ABBREVIATION is '活动简称'
/

comment on column ACTIVITY.CUSTOM_SET_TYPE is '客户集合类型，1，全部可见，2，部分可见'
/

comment on column ACTIVITY.ACTIVITY_STRATEGY is '活动策略  0全场折扣，1商品折扣，2满减，3满赠，4赠优惠券，5限时秒杀&限时特价，6组合套餐'
/

comment on column ACTIVITY.WARM_TIME is '预热时间'
/

comment on column ACTIVITY.START_EFFECTIVE_TIME is '有效开始时段 存时分秒'
/

comment on column ACTIVITY.END_EFFECTIVE_TIME is '有效结束时段 存时分秒'
/

comment on column ACTIVITY.START_TIME is '活动开始时间'
/

comment on column ACTIVITY.END_TIME is '活动结束时间'
/

comment on column ACTIVITY.TIMES is '同一客户次数限制'
/

comment on column ACTIVITY.IS_SUPERPOSITION is '是否叠加使用，1是，0否'
/

comment on column ACTIVITY.CREATE_TIME is '创建时间'
/

comment on column ACTIVITY.USER_ID is '创建人'
/

comment on column ACTIVITY.WEEK is '星期1-7，0每天'
/

comment on column ACTIVITY.TIMES_STRATEGY is '限制策略，0全场一次，1每天，2周，3月，4年'
/

comment on column ACTIVITY.IS_USE is '是否启用，0否，1是'
/

create table ACTIVITY_SET
(
    ID NUMBER(18) not null
        constraint ACTIVITY_SET_PK
        primary key,
    ACTIVITY_ID NUMBER(18),
    CUSTOM_SET_ID NUMBER(18),
    CREATE_TIME DATE,
    USER_ID NUMBER(18)
)
    /

comment on table ACTIVITY_SET is '活动客户集合'
/

comment on column ACTIVITY_SET.ID is 'id'
/

comment on column ACTIVITY_SET.ACTIVITY_ID is '活动id'
/

comment on column ACTIVITY_SET.CUSTOM_SET_ID is '客户集合ID'
/

comment on column ACTIVITY_SET.CREATE_TIME is '创建时间'
/

comment on column ACTIVITY_SET.USER_ID is '创建人'
/

create table ACTIVITY_GOODS
(
    ID NUMBER(18) not null
        constraint ACTIVITY_GOODS_PK
        primary key,
    ACTIVITY_ID NUMBER(18),
    AS_ID NUMBER(18),
    ERP_GOODS_ID NUMBER(18),
    CONDITION_NUM NUMBER(11),
    TOP_LIMIT NUMBER(11) default 0,
    CONDITION_PRICE NUMBER(18),
    GOODS_PRICE NUMBER(18),
    IS_USE NUMBER(10),
    CREATE_TIME DATE,
    USER_ID NUMBER(18),
    GOODS_NAME VARCHAR2(255)
)
    /

comment on table ACTIVITY_GOODS is '活动商品'
/

comment on column ACTIVITY_GOODS.ID is 'id'
/

comment on column ACTIVITY_GOODS.ACTIVITY_ID is '活动id'
/

comment on column ACTIVITY_GOODS.AS_ID is '活动策略ID'
/

comment on column ACTIVITY_GOODS.ERP_GOODS_ID is 'erp商品id'
/

comment on column ACTIVITY_GOODS.CONDITION_NUM is '策略满足数量'
/

comment on column ACTIVITY_GOODS.TOP_LIMIT is '商品上限0为无上限，默认为0'
/

comment on column ACTIVITY_GOODS.CONDITION_PRICE is '策略满足金额'
/

comment on column ACTIVITY_GOODS.GOODS_PRICE is '商品活动价格'
/

comment on column ACTIVITY_GOODS.IS_USE is '是否启用 0否，1是'
/

comment on column ACTIVITY_GOODS.CREATE_TIME is '创建时间'
/

comment on column ACTIVITY_GOODS.USER_ID is '创建人'
/

comment on column ACTIVITY_GOODS.GOODS_NAME is '商品名称'
/

create table ACTIVITY_STRATEGY
(
    ID NUMBER(18) not null
        constraint ACTIVITY_STRATEGY_PK
        primary key,
    ACTIVITY_ID NUMBER(18),
    STRATEGY_NAME VARCHAR2(1000),
    GOODS_STRATEGY NUMBER(10),
    MEET_QUANTITY NUMBER(11),
    TOP_LIMIT NUMBER(11) default 0,
    AMOUNT_SATISFIED NUMBER(18),
    GIFT_NUM NUMBER(11),
    REDUCED_AMOUNT NUMBER(18),
    DISCOUNT NUMBER(11),
    CASH NUMBER(18),
    COUPON_ID NUMBER(18),
    GIFT_STRATEGY NUMBER(10),
    IS_USE NUMBER(10),
    CREATE_TIME DATE,
    USER_ID NUMBER(18)
)
    /

comment on table ACTIVITY_STRATEGY is '活动策略'
/

comment on column ACTIVITY_STRATEGY.ACTIVITY_ID is '活动id'
/

comment on column ACTIVITY_STRATEGY.STRATEGY_NAME is '策略名字'
/

comment on column ACTIVITY_STRATEGY.GOODS_STRATEGY is '商品策略，1-任选商品数量，2-任选商品策略数量，3-任选商品金额，4-任选商品策略金额'
/

comment on column ACTIVITY_STRATEGY.MEET_QUANTITY is '满足数量'
/

comment on column ACTIVITY_STRATEGY.TOP_LIMIT is '商品上限0为无上限，默认为0'
/

comment on column ACTIVITY_STRATEGY.AMOUNT_SATISFIED is '满足金额'
/

comment on column ACTIVITY_STRATEGY.GIFT_NUM is '赠送数量'
/

comment on column ACTIVITY_STRATEGY.REDUCED_AMOUNT is '减少金额'
/

comment on column ACTIVITY_STRATEGY.DISCOUNT is '折扣'
/

comment on column ACTIVITY_STRATEGY.CASH is '返现金'
/

comment on column ACTIVITY_STRATEGY.COUPON_ID is '优惠券id'
/

comment on column ACTIVITY_STRATEGY.GIFT_STRATEGY is '赠品策略，1-任选赠品数量，2-任选赠品策略数量，3-固定策略数量，4,不赠'
/

comment on column ACTIVITY_STRATEGY.IS_USE is '是否启用'
/

comment on column ACTIVITY_STRATEGY.CREATE_TIME is '创建时间'
/

comment on column ACTIVITY_STRATEGY.USER_ID is '创建人'
/

create table ACTIVITY_GIFT
(
    ID NUMBER(18) not null
        constraint ACTIVITY_GIFT_PK
        primary key,
    AS_ID NUMBER(18),
    ERP_GOODS_ID NUMBER(18),
    GOODS_PRICE NUMBER(18),
    STORAGE_ID NUMBER(18),
    LOTNO VARCHAR2(50),
    ERP_ACCFLAG NUMBER(18),
    GIFT_MULTIPLE NUMBER(18) default 1,
    GIFT_NUM NUMBER(18),
    IS_USE NUMBER(10),
    CREATE_TIME DATE,
    USER_ID VARCHAR2(50),
    GOODS_NAME VARCHAR2(255),
    LOT_ID NUMBER(18),
    ACTIVITY_ID NUMBER(18)
)
    /

comment on table ACTIVITY_GIFT is '活动赠品'
/

comment on column ACTIVITY_GIFT.ID is 'id'
/

comment on column ACTIVITY_GIFT.AS_ID is '活动策略ID'
/

comment on column ACTIVITY_GIFT.ERP_GOODS_ID is 'erp商品id'
/

comment on column ACTIVITY_GIFT.GOODS_PRICE is '赠品价格，0为免费，100为1分钱，erp设置'
/

comment on column ACTIVITY_GIFT.STORAGE_ID is '保管账id（=5为近效商品）'
/

comment on column ACTIVITY_GIFT.LOTNO is '批号'
/

comment on column ACTIVITY_GIFT.ERP_ACCFLAG is 'ERP商品属性（=5为赠品）(先不存)'
/

comment on column ACTIVITY_GIFT.GIFT_MULTIPLE is '赠送倍数,默认1'
/

comment on column ACTIVITY_GIFT.GIFT_NUM is '赠送数量，0为不限，>0为固定值不可选'
/

comment on column ACTIVITY_GIFT.IS_USE is '是否启用，0否，1是'
/

comment on column ACTIVITY_GIFT.CREATE_TIME is '创建时间'
/

comment on column ACTIVITY_GIFT.USER_ID is '创建人'
/

comment on column ACTIVITY_GIFT.GOODS_NAME is '商品名称'
/

comment on column ACTIVITY_GIFT.LOT_ID is '批号id'
/

comment on column ACTIVITY_GIFT.ACTIVITY_ID is '活动id'
/

create table ACTIVITY_ORDER
(
    ID NUMBER(18) not null
        constraint ACTIVITY_ORDER_PK
        primary key,
    ACTIVITY_ID NUMBER(18),
    ORDER_ID NUMBER(18),
    ORDER_NO VARCHAR2(100),
    STATUS VARCHAR2(20),
    MEMBER_ID NUMBER(18),
    CREATE_TIME DATE
)
    /

comment on table ACTIVITY_ORDER is '活动订单表'
/

comment on column ACTIVITY_ORDER.ID is 'id'
/

comment on column ACTIVITY_ORDER.ACTIVITY_ID is '活动ID'
/

comment on column ACTIVITY_ORDER.ORDER_ID is '订单id'
/

comment on column ACTIVITY_ORDER.ORDER_NO is '订单号'
/

comment on column ACTIVITY_ORDER.STATUS is '状态'
/

comment on column ACTIVITY_ORDER.MEMBER_ID is '会员id'
/

comment on column ACTIVITY_ORDER.CREATE_TIME is '创建时间'
/

create table WEB_PAGE_SEARCH
(
    FLOOR_ID NUMBER(18),
    ERP_GOODS_ID NUMBER(18),
    GOODS_NAME VARCHAR2(100),
    WEB_PAGE_ID NUMBER(18),
    CLASS_NAME_1 VARCHAR2(50),
    CLASS_NAME_2 VARCHAR2(50),
    ERP_CLASS_ID_1 NUMBER(18),
    ERP_CLASS_ID_2 VARCHAR2(255),
    ERP_CLASS_ID_3 VARCHAR2(255),
    TITLE VARCHAR2(255)
)
    /

comment on table WEB_PAGE_SEARCH is '专区搜索商品分类'
/

comment on column WEB_PAGE_SEARCH.FLOOR_ID is '楼层id'
/

comment on column WEB_PAGE_SEARCH.ERP_GOODS_ID is '商品id'
/

comment on column WEB_PAGE_SEARCH.GOODS_NAME is '商品名'
/

comment on column WEB_PAGE_SEARCH.WEB_PAGE_ID is '专区id'
/

comment on column WEB_PAGE_SEARCH.CLASS_NAME_1 is '分类1'
/

comment on column WEB_PAGE_SEARCH.CLASS_NAME_2 is '分类2'
/

comment on column WEB_PAGE_SEARCH.ERP_CLASS_ID_1 is 'ERP分类1'
/

comment on column WEB_PAGE_SEARCH.ERP_CLASS_ID_2 is 'ERP分类2'
/

comment on column WEB_PAGE_SEARCH.ERP_CLASS_ID_3 is 'ERP分类3'
/

comment on column WEB_PAGE_SEARCH.TITLE is '标签'
/

create table NOTICE
(
    ID NUMBER(18) not null
        constraint NOTICE_PK
        primary key,
    CONTENT VARCHAR2(500),
    ADMIN_ID NUMBER(18),
    START_TIME DATE,
    END_TIME DATE,
    ADD_TIME DATE,
    CREATE_TIME DATE,
    IS_USE VARCHAR2(20) default 'OFF',
    IS_DEL NUMBER(1) default 0,
    NOTICE_TYPE VARCHAR2(20),
    NOTICE_COLOR VARCHAR2(20),
    ADMIN_NAME VARCHAR2(20),
    SORT NUMBER(4)
)
    /

comment on table NOTICE is '公告'
/

comment on column NOTICE.ID is '主键ID'
/

comment on column NOTICE.CONTENT is '公告内容'
/

comment on column NOTICE.ADMIN_ID is '操作人ID'
/

comment on column NOTICE.START_TIME is '开始时间'
/

comment on column NOTICE.END_TIME is '结束时间'
/

comment on column NOTICE.ADD_TIME is '发布时间'
/

comment on column NOTICE.CREATE_TIME is '创建时间'
/

comment on column NOTICE.IS_USE is '是否启用
OFF 关闭
ON 启用'
/

comment on column NOTICE.IS_DEL is '是否删除 0 否 1 是'
/

comment on column NOTICE.NOTICE_TYPE is '公告类型'
/

comment on column NOTICE.NOTICE_COLOR is '颜色'
/

comment on column NOTICE.ADMIN_NAME is '操作人'
/

comment on column NOTICE.SORT is '排序'
/

create table LICENCE
(
    ID NUMBER(18) not null
        constraint LICENCE_PK
        primary key,
    ERP_LICENCE_ID NUMBER(18),
    LICENCE_IMAGES VARCHAR2(500),
    LICENCE_NAME VARCHAR2(128),
    LICENCE_TYPE_ID NUMBER(18),
    ERP_CUSTOMER_ID NUMBER(18),
    LICENCE_CODE VARCHAR2(20),
    STATUS VARCHAR2(20),
    REMARK VARCHAR2(1024),
    SIGN_DATE DATE,
    VALID_ON_DATE DATE,
    VALID_END_DATE DATE,
    APPLY_ID NUMBER(18),
    CREATE_DATE DATE
)
    /

comment on table LICENCE is '用户证照'
/

comment on column LICENCE.ID is '主键ID'
/

comment on column LICENCE.ERP_LICENCE_ID is 'erp证照ID'
/

comment on column LICENCE.LICENCE_IMAGES is '证照图片，多个逗号隔开'
/

comment on column LICENCE.LICENCE_NAME is '证照名'
/

comment on column LICENCE.LICENCE_TYPE_ID is '证照类型ID'
/

comment on column LICENCE.ERP_CUSTOMER_ID is 'erp客户ID'
/

comment on column LICENCE.LICENCE_CODE is '证照号'
/

comment on column LICENCE.STATUS is '状态
NORMAL 正常
DISABLE 停用
EXPIRES 过期'
/

comment on column LICENCE.REMARK is '备注'
/

comment on column LICENCE.SIGN_DATE is '签发日期'
/

comment on column LICENCE.VALID_ON_DATE is '开始时间'
/

comment on column LICENCE.VALID_END_DATE is '有效期至'
/

comment on column LICENCE.APPLY_ID is '证照申请ID'
/

comment on column LICENCE.CREATE_DATE is '创建日期'
/

create table LICENCE_APPLY
(
    ID NUMBER(18) not null
        constraint LICENCE_APPLY_PK
        primary key,
    LICENCE_IMAGES VARCHAR2(1024),
    LICENCE_NAME VARCHAR2(128),
    LICENCE_TYPE_ID NUMBER(18),
    ERP_CUSTOMER_ID NUMBER(18),
    LICENCE_CODE VARCHAR2(20),
    STATUS VARCHAR2(20),
    REMARK VARCHAR2(1024),
    SIGN_DATE DATE,
    VALID_ON_DATE DATE,
    VALID_END_DATE DATE,
    CREATE_DATE DATE,
    REASON VARCHAR2(1024),
    AUDIT_USER_ID NUMBER(18),
    AUDIT_DATE DATE,
    MEMBER_ID NUMBER(18)
)
    /

comment on table LICENCE_APPLY is '用户证照申请'
/

comment on column LICENCE_APPLY.ID is '主键ID'
/

comment on column LICENCE_APPLY.LICENCE_IMAGES is '证照图片'
/

comment on column LICENCE_APPLY.LICENCE_NAME is '证照名'
/

comment on column LICENCE_APPLY.LICENCE_TYPE_ID is '证照类型id'
/

comment on column LICENCE_APPLY.ERP_CUSTOMER_ID is 'erp客户id'
/

comment on column LICENCE_APPLY.LICENCE_CODE is '证照号'
/

comment on column LICENCE_APPLY.STATUS is '状态
IN_REVIEW 审核中
APPROVE 审核通过
REJECT 驳回'
/

comment on column LICENCE_APPLY.REMARK is '备注'
/

comment on column LICENCE_APPLY.SIGN_DATE is '签发日期'
/

comment on column LICENCE_APPLY.VALID_ON_DATE is '开始时间'
/

comment on column LICENCE_APPLY.VALID_END_DATE is '有效期至'
/

comment on column LICENCE_APPLY.CREATE_DATE is '创建时间'
/

comment on column LICENCE_APPLY.REASON is '审核结果：通过/不通过原因'
/

comment on column LICENCE_APPLY.AUDIT_USER_ID is '审核人'
/

comment on column LICENCE_APPLY.AUDIT_DATE is '审核时间'
/

comment on column LICENCE_APPLY.MEMBER_ID is '客户ID'
/

create table MEMBER_APPLY
(
    ID NUMBER(18) not null
        constraint MEMBER_APPLY_PK
        primary key,
    COMPANY_NAME VARCHAR2(20),
    GOODSBUSISCOPEIDS VARCHAR2(500),
    GOODSBUSISCOPE VARCHAR2(500),
    AREA_NAME VARCHAR2(50),
    ADDRESS VARCHAR2(50),
    CONTACT_NAME VARCHAR2(20),
    CONTACT_PHONE VARCHAR2(20),
    STATUS VARCHAR2(20),
    REASON VARCHAR2(1024),
    REMARK VARCHAR2(1024),
    CREAT_TIME DATE,
    COMPANY_TYPE VARCHAR2(50)
)
    /

comment on table MEMBER_APPLY is '用户注册申请'
/

comment on column MEMBER_APPLY.ID is '主键ID'
/

comment on column MEMBER_APPLY.COMPANY_NAME is '公司名称'
/

comment on column MEMBER_APPLY.GOODSBUSISCOPEIDS is '证照经营范围IDs'
/

comment on column MEMBER_APPLY.GOODSBUSISCOPE is '经营范围'
/

comment on column MEMBER_APPLY.AREA_NAME is '区域名，省市区'
/

comment on column MEMBER_APPLY.ADDRESS is '详细地址'
/

comment on column MEMBER_APPLY.CONTACT_NAME is '联系人'
/

comment on column MEMBER_APPLY.CONTACT_PHONE is '联系电话'
/

comment on column MEMBER_APPLY.STATUS is '状态
申请中
审核中
审核通过
审核不通过'
/

comment on column MEMBER_APPLY.REASON is '审核结果（通过不通过原因）'
/

comment on column MEMBER_APPLY.REMARK is '备注'
/

comment on column MEMBER_APPLY.CREAT_TIME is '添加时间'
/

comment on column MEMBER_APPLY.COMPANY_TYPE is '企业类型'
/

create view ERP_GOODS_V as
SELECT REGEXP_SUBSTR(t.classname_3, '[^\-]+$') classname, --货品分类名
    t.classname_3, --货品完整分类名
    t.accflag,
       t.medicinetypename, --剂型
    t.goodsname, --商品名称
    t.goodspinyin,
       t.goodsunit, --基本单位
    c.price * 10000 price_2, --批发二价
    c3.price * 10000 price_try, --商品指导价
    t.approvedocno, --批准文号
    e.factoryname, -- 厂家名称
       e.factoryid, --厂家id
    t.goodsid, --产品编号
    t.goodstype, --规格
    t.goodsid_gps, --商品定位， X为新品
    t.barcode, --条形码
    t.currencyname, --通用名
    t.credate, --创建时间
    t.classn_row_1, --分类编号1
    t.classname_1, --分类1
    t.classname_2, --分类2
    t.classn_row_2, --分类编号2
    t.classn_row_3, --分类号
    t.busiscope, --经营范围id
    x1.classname busiscope_name,
       (CASE
            WHEN sum(d.goodsqty) is null THEN
                0
            WHEN sum(d.goodsqty) <0 THEN
                0
            ELSE
                sum(d.goodsqty)
           END) goodsqty, --商品可销库存
                                               nvl((CASE

             WHEN 1 < 2 THEN
              to_char((SELECT b.invaliddate
                        FROM (SELECT a2.goodsid,
                                     a2.goodsqty,
                                     a.proddate,
                                     a.invaliddate
                                FROM incaerp.bms_st_qty_lst a2, incaerp.bms_lot_def a
                               WHERE a2.goodsstatusid = 1
                                 AND a2.storageid IN (1, 732)
                                 AND a2.goodsid = a.goodsid(+)
                                 AND a2.lotid = a.lotid(+)
                                 AND a.invaliddate IS NOT NULL
                               ORDER BY a.invaliddate) b
                       WHERE ROWNUM = 1
                         AND b.goodsid = d.goodsid),
                      'yyyy-mm-dd')
             ELSE
              ' '
           END),
           0000 - 00 - 00) invaliddate, --商品最近的一个效期
    f.ddlname otcflag, --OTC标志
    f1.ddlname storagecondition, --运输条件
    t.prodarea, --产地
    f2.ddlname transcondition, --运输条件
           --货品说明
    t.dosage, --用法用量
    t.usegoodstime, --用药时间
    t.diagnosticinfo, --诊断信息
    t.transporttime, --运输时限
    t.treatment, --疗程说明
    t.memo, --备注
    t1.supplyerid, --采购ID
    t2.employeename, --采购员中名称
    (CASE
         WHEN sum(t1.zx_b2b_num_limit) IS NULL THEN
             1
         ELSE
             sum(t1.zx_b2b_num_limit)
            END) zx_b2b_num_limit, --B2B最小销售数量
    d.storageid, --保管帐ID
    d.storagename --保管帐名称
FROM incaerp.pub_goods t,
     incaerp.pub_entry_goods t1,
     incaerp.pub_employee t2,
     (SELECT a.goodsid, a.price
      FROM incaerp.bms_entry_goods_price a
      WHERE a.priceid IN (105)
        AND a.entryid = 1) c,
     (SELECT a.goodsid, a.price
      FROM incaerp.bms_entry_goods_price a
      WHERE a.priceid IN (68)
        AND a.entryid = 1) c3,

     (SELECT n.goodsid,
             n.storageid,
             nn.storagename,
             sum(n.goodsqty) - nvl(e.outqty, 0) goodsqty
      FROM incaerp.bms_st_qty_lst n,
           incaerp.bms_st_def nn,
           (SELECT e.goodsid, sum(e.outqty) outqty
            FROM incaerp.bms_st_io_doc_tmp e
            WHERE e.inoutflag = 0
              and e.entryid = 1
              AND storageid IN (1, 732)
            GROUP BY e.goodsid) e
      WHERE n.storageid IN (1, 732)
        AND n.goodsid = e.goodsid(+)
        AND n.goodsstatusid = 1
        and n.storageid = nn.storageid(+)
      GROUP BY n.goodsid, n.storageid, e.outqty, nn.storagename) d,

     incaerp.pub_factory e,
     (SELECT a.ddlid, a.ddlname
      FROM incaerp.sys_ddl_dtl a
      WHERE a.sysid = 5140) f,
     (SELECT a.ddlid, a.ddlname FROM incaerp.pub_ddl_dtl a WHERE a.sysid = 1) f1,
     (SELECT a.ddlid, a.ddlname FROM incaerp.pub_ddl_dtl a WHERE a.sysid = 2) f2,
     incaerp.pub_goods_class x1
WHERE t.goodsid = d.goodsid(+)
  AND t.goodsid = c.goodsid(+)
  AND t.goodsid = c3.goodsid(+)
  AND t.b2b_shop_flag = 1 --b2b_shop_flag=1 只显示上架的商品

   AND t.factoryid = e.factoryid(+)
   AND t.otcflag = f.ddlid(+)
   AND t.storagecondition = f1.ddlid(+)
   AND t.transcondition = f2.ddlid(+)
   and t1.entryid = 1
   and t.goodsid = t1.goodsid(+)
   and t1.supplyerid = t2.employeeid(+)
   and x1.classid = t.busiscope
 GROUP BY t.goodsid,
          t.accflag,
          d.goodsid,
          c.price,
          t.goodsname,
          t.approvedocno,
          e.factoryname,
          e.factoryid,
          t.goodsunit,
          t.goodstype,
          c.price,
          c3.price,
          t.otcflag,
          f.ddlname,
          t.classname_3,
          t.medicinetypename,
          t.storagecondition,
          f1.ddlname,
          t.prodarea,
          t.transcondition,
          f2.ddlname,
          t.dosage,
          t.usegoodstime,
          t.diagnosticinfo,
          t.transporttime,
          t.treatment,
          t.memo,
          t.goodsid_gps,
          t.barcode,
          t.classn_row_3,
          t.currencyname,
          t.credate,
          t.classn_row_1,
          t.classname_1,
          t.classn_row_2,
          t.classname_2,
          t.busiscope,
          t2.employeename,
          t1.supplyerid,
          t1.zx_b2b_num_limit,
          d.storageid,
          d.storagename,
           t.goodspinyin,
          x1.classname
 order by goodsqty desc
    /

create view ERP_CUSTOMER_V as
select
    a.customid, --客户ID
    b.customname, --客户名称
        to_char(b.zx_phone) zx_phone, --手机号
    b.custompinyin, --客户名首拼
    j.ddlname customertype, --客户性质
    h1.classno,--客户分类编码
    b.corpcode, --组织代码
    b.taxnumber, --税号
    b.registadd, --注册地址
    b.legalperson, --法人代表
    b.credate, --建立日期
    b.customopcode, --客户操作码
        decode(b.usestatus, 1, '正式', 0, '临时') usestatus, --状态
    a.gspusestatus, --客户质量类别
    b.medicode, --药监编码
        decode(b.gspflag, 1, '是', '') gspflag, --医药行业标志
    j1.ddlname invmethod_name, --开票策略
    j2.ddlname invtype,  --系统发票类型
    d.bankname, --开户
    i.employeename, --联系人
    case
         when a.firstapprovedate is null then
          '期初'
         else
          to_char(a.firstapprovedate)
       end firstapprovedate, --首营通过日期
       j2.settletype, --结算方式
       j3.discount, --客户折扣
       j4.factory_discount, --按商品厂商给客户折扣
       k.pricename, --缺省价格类型客户折扣
       decode(a.sausestatus, 1, '可销售', 2, '不可销售') sausestatus, --销售状态
       case
         when nvl(a.gspusestatus, 0) = 1 then
          '质量可用'
         else
          '质量禁止'
       end gspusestatusname , --质量状态
       b.memo 备注

  from incaerp.pub_entry_customer a,
       incaerp.pub_customer b,
       (select c.companyid, c.taxno, c.bankname, c.accno
          from incaerp.bms_sa_inv_info c
         where c.taxno is not null
           and c.companyfunction = 2) d,
       (select f.customid,f.salerid from
               (select max(e.seqid) seqid,e.customid
                  from incaerp.pub_custom_to_saler e
                 where e.entryid = 1
                 group by e.customid ) g, incaerp.pub_custom_to_saler f
         where g.seqid = f.seqid(+)) h,
   (select  customid,classno
  from incaerp.pub_custom_class, incaerp.pub_custom_class_dtl
 where pub_custom_class.classid = pub_custom_class_dtl.classid
   and pub_custom_class.classtypeid = 4) h1,
       incaerp.pub_employee i,
       (select a.ddlid, a.ddlname from incaerp.sys_ddl_dtl a where a.sysid = 781) j,
       (select a.ddlid, a.ddlname from incaerp.sys_ddl_dtl a where a.sysid = 100652) j1,
       (select a.ddlid, a.ddlname from incaerp.sys_ddl_dtl a where a.sysid = 5) j2,
       (select settletypeid, settletype
          from incaerp.pub_settletype_sa_v
         where useforsal = 1) j2,
       (select a.customerid, a.discount
          from incaerp.bms_cus_discount_def a
         where a.entryid = 1
           and a.invalidenddate >= trunc(sysdate)) j3,
       (select a.customerid, a.factory_discount
          from incaerp.bms_cus_discount_def a
         where a.entryid = 1
           and a.invalidenddate >= trunc(sysdate)) j4,
       incaerp.pub_price_type k
 where a.entryid = 1
   and a.customid = b.customid(+)
   and a.customid = d.companyid(+)
   and a.customid = h.customid(+)
   and h.salerid = i.employeeid(+)
   and b.customertype = j.ddlid(+)
   and a.priceid = k.priceid(+)
   and b.invmethod = j1.ddlid(+)
   and a.settletypeid = j2.settletypeid(+)
   and a.customid = j3.customerid(+)
   and a.customid = j4.customerid(+)
   and a.customid = h1.customid(+)
   and b.invtype=j2.ddlid(+)
/

create view ERP_CUSTOMER_LICENSE_V as
select c.licenseid,            --证照ID
    c.companyid customerid, --客户ID
    c.licensename,          --证照名称
    c.signdate,             --签发日期
    c.validenddate          --效期至
from (
         select a.licenseid,
                a.companyid,
                case
                    when a.licensename is null then
                        'null '
                    else
                        a.licensename
                    end            licensename,
                a.signdate,
                a.validenddate + 1 validenddate,
                c.approvedate,
                a.sys_modifydate
         from incaerp.gsp_company_license_v a,
              (select a.licenseid,
                      a.approvedate
               from incaerp.gsp_license_alter_approve a,
                    (select b.licenseid,
                            max(b.approvedate) approvedate
                     from incaerp.gsp_license_alter_approve b
                     where b.entryid in (1, 3)
                     group by b.licenseid) c
               where a.entryid in (1, 3)
                 and a.licenseid = c.licenseid(+)
                 and a.approvedate = c.approvedate) c
         where a.entryid = 1
           and a.licenseid = c.licenseid(+)
           and a.companyid not in (select placepointid from incaerp.gpcs_placepoint)
           and a.usestatus = 1
           and a.validenddate is not null
         union all
         select a.licenseid,
                a.companyid,
                case
                    when a.licensename is null then
                        'null '
                    else
                        a.licensename
                    end            licensename,
                a.signdate,
                a.validenddate + 1 validenddate,
                c.approvedate,
                a.sys_modifydate
         from incaerp.gsp_company_license_v a,
              (select a.licenseid,
                      a.approvedate
               from incaerp.gsp_license_alter_approve a,
                    (select b.licenseid,
                            max(b.approvedate) approvedate
                     from incaerp.gsp_license_alter_approve b
                     where b.entryid in (1, 3)
                     group by b.licenseid) c
               where a.entryid in (1, 3)
                 and a.licenseid = c.licenseid(+)
                 and a.approvedate = c.approvedate) c
         where a.entryid = 3
           and a.licenseid = c.licenseid(+)
           and a.companyid in (select placepointid from incaerp.gpcs_placepoint)
           and a.usestatus = 1
           and a.validenddate is not null) c
where 1 = 1
    /

create view ERP_CUSTOMER_SCOPE_V as
select distinct a.companyid customerid, --客户ID
    c.companyname, --客户名称
    a.scopedefid, --经营范围ID
    b.classname  scopename, --经营范围名称
    a.licenseid,  --客户证照ID
    f.licensename --客户证照名称
from incaerp.gsp_company_managerage a,
     incaerp.pub_goods_class        b,
     incaerp.pub_company            c,
     incaerp.gsp_company_license    e,
     incaerp.gsp_license_type       f
where a.scopedefid = b.classid(+)
  and a.companyid = c.companyid
  and a.licenseid =e.licenseid
  and e.licensetypeid = f.licensetypeid(+)
  and e.entryid=1
  and e.usestatus = 1
  and e.validenddate >=trunc(sysdate)
  and a.companyid not in (select placepointid from incaerp.gpcs_placepoint)
union all
select distinct a.companyid, --客户ID
    c.companyname, --客户名称
    a.scopedefid, --经营范围ID
    b.classname  scopename, --经营范围名称
    a.licenseid,  --客户证照ID
    f.licensename --客户证照名称
from incaerp.gsp_company_managerage a,
     incaerp.pub_goods_class        b,
     incaerp.pub_company            c,
     incaerp.gsp_company_license    e,
     incaerp.gsp_license_type       f
where a.scopedefid = b.classid(+)
  and a.companyid = c.companyid
  and a.licenseid =e.licenseid
  and e.licensetypeid = f.licensetypeid(+)
  and e.entryid=3
  and a.companyid in (select placepointid from incaerp.gpcs_placepoint)
    /

create view ERP_GOODS_PRICE_V as
select a.customid,
       a.goodsid,
       a.entryid,
       CASE

           WHEN sign(nvl(c.price, 0)) = 0 THEN
               a.priceid
           ELSE
                   3 ---协议价
           END priceid,
       CASE

           WHEN sign(nvl(c.price, 0)) = 0 THEN
                   a.price*10000
           ELSE
                   c.price*10000
           END price
from (SELECT a.price, a.priceid, b.customid, a.goodsid, a.entryid
      FROM incaerp.bms_entry_goods_price a, incaerp.pub_entry_customer b

      WHERE 1 = 1
        AND a.entryid = b.entryid

        AND a.priceid = b.priceid

        AND a.entryid = 1) a
         left join incaerp.bms_pr_custom c
                   on c.customid = a.customid
                       AND a.goodsid = c.goodsid
                       AND a.entryid = c.entryid
                       and c.enddate >=trunc(sysdate)
                       AND c.usestatus = 1
                           /

create view ERP_CUSTOMER_PRID_V as
select a.prid,--协议价ID
    a.customid, --客户ID
    b.customname, --客户名称
    a.goodsid,  --商品ID
    c.goodsname, --货品名称
    c.currencyname, --商品名
    c.goodstype,  --规格
    c.goodsunit,  --基本单位
    c.factoryid,  --厂家ID
    d.factoryname, --厂家名称
    c.prodarea,  --产地
    a.price,     --协议价
    a.startdate,  --生效开始日期
    a.enddate,    --失效结束日期
           decode(a.usestatus,0,'停用',1,'正常') usestatus --状态

from incaerp.bms_pr_custom a,
     incaerp.pub_customer  b,
     incaerp.pub_goods     c,
     incaerp.pub_factory   d,
     incaerp.pub_employee  e,
     incaerp.pub_entry f
where a.customid = b.customid
  and a.goodsid = c.goodsid
  and c.factoryid = d.factoryid(+)
  and a.inputmanid = e.employeeid
  and a.entryid=f.entryid(+)
  and a.entryid=1
    /

create view ERP_VALIDITY_GOODS_V as
SELECT REGEXP_SUBSTR(t.classname_3, '[^\-]+$') classname, --货品分类名
    t.classname_3, --货品完整分类名
    t.accflag,
       t.medicinetypename, --剂型
    t.goodsname, --商品名称
    t.goodsunit, --基本单位
                                               nvl(c1.price*10000,c.price*10000) price,
       c.price*10000 price_2, --商品效期价
    c3.price*10000 price_try, --商品指导价
    t.approvedocno, --批准文号
    e.factoryname, -- 厂家名称
       e.factoryid, --厂家id
    t.goodsid, --产品编号
    t.goodstype, --规格
    t.goodsid_gps, --商品定位， X为新品
    t.barcode, --条形码
    t.currencyname, --通用名
    t.goodspinyin,
       t.credate, --创建时间
    t.classn_row_1, --分类编号1
    t.classname_1, --分类1
    t.classname_2, --分类2
    t.classn_row_2, --分类编号2
    t.classn_row_3, --分类号
    (CASE
         WHEN sum(d.goodsqty) <0 THEN
             0
         ELSE
             sum(d.goodsqty)
            END) goodsqty, --商品可销库存
    d.storageid,

       t.busiscope, --经营范围id
    x1.classname busiscope_name,

       d.lotno, --批号
    d.lotid,
       d.proddate, --生产日期
    d.invaliddate, --有效期
    f.ddlname     otcflag, --OTC标志
    f1.ddlname    storagecondition, --运输条件
    t.prodarea, --产地
    f2.ddlname    transcondition, --运输条件
           --货品说明
    t.dosage, --用法用量
    t.usegoodstime, --用药时间
    t.diagnosticinfo, --诊断信息
    t.transporttime, --运输时限
    t.treatment, --疗程说明
    t.memo, --备注
    t1.supplyerid, --采购ID
    t2.employeename, --采购员中名称
    t1.zx_b2b_num_limit --B2B最小销售数量
FROM incaerp.pub_goods t,
     incaerp.pub_entry_goods t1,
     incaerp.pub_employee t2,
     (SELECT a.goodsid, a.price
      FROM incaerp.bms_entry_goods_price a
      WHERE a.priceid IN (226)
        AND a.entryid = 1) c1,
     (SELECT a.goodsid, a.price
      FROM incaerp.bms_entry_goods_price a
      WHERE a.priceid IN (105)
        AND a.entryid = 1) c,
     (SELECT a.goodsid, a.price
      FROM incaerp.bms_entry_goods_price a
      WHERE a.priceid IN (68)
        AND a.entryid = 1) c3,
     (select n.storageid,
             n.goodsid,
             sum(n.goodsqty) - nvl(e.outqty, 0) goodsqty,
             n1.lotno,
             n1.lotid,
             n1.proddate,
             n1.invaliddate
      from incaerp.bms_st_qty_lst n,
           incaerp.bms_lot_def n1,
           (select e.goodsid, sum(e.outqty) outqty
            from incaerp.bms_st_io_doc_tmp e
            where e.inoutflag = 0
              and storageid in (5)
            group by e.goodsid) e
      where n.storageid in (5)
        and n.goodsid = e.goodsid(+)
        and n.lotid = n1.lotid(+)
        and n.goodsstatusid = 1
      group by n.storageid,
               n.goodsid,
               e.outqty,
               n1.lotno,
               n1.lotid,
               n1.proddate,
               n1.invaliddate) d,
     incaerp.pub_factory e,
     (SELECT a.ddlid, a.ddlname
      FROM incaerp.sys_ddl_dtl a
      WHERE a.sysid = 5140) f,
     (SELECT a.ddlid, a.ddlname FROM incaerp.pub_ddl_dtl a WHERE a.sysid = 1) f1,
     (SELECT a.ddlid, a.ddlname FROM incaerp.pub_ddl_dtl a WHERE a.sysid = 2) f2,
     incaerp.pub_goods_class x1
WHERE t.goodsid = d.goodsid
  AND t.goodsid = c.goodsid(+)
  AND t.goodsid = c3.goodsid(+)
  and t.goodsid = c1.goodsid(+)
  AND t.b2b_shop_flag = 1 --b2b_shop_flag=1 只显示上架的商品

   AND t.factoryid = e.factoryid(+)
   AND t.otcflag = f.ddlid(+)
   AND t.storagecondition = f1.ddlid(+)
   AND t.transcondition = f2.ddlid(+)
   and t1.entryid = 1
   and t.goodsid = t1.goodsid(+)
   and t1.supplyerid = t2.employeeid(+)
   and x1.classid = t.busiscope
 --  and t.goodsid = 2478
 GROUP BY t.goodsid,
          t.accflag,
          t.busiscope,
          x1.classname,
          d.storageid,
          d.goodsid,
          c.price,
          t.goodsname,
          t.goodspinyin,
          t.approvedocno,
          e.factoryname,
          e.factoryid,
          t.goodsunit,
          t.goodstype,
          c.price,
          c3.price,
          t.otcflag,
          f.ddlname,
          t.classname_3,
          t.medicinetypename,
          t.storagecondition,
          f1.ddlname,
          t.prodarea,
          t.transcondition,
          f2.ddlname,
          t.dosage,
          t.usegoodstime,
          t.diagnosticinfo,
          t.transporttime,
          t.treatment,
          t.memo,
          t.goodsid_gps,
          t.barcode,
          t.classn_row_3,
          t.currencyname,
          t.credate,
          t.classn_row_1,
          t.classname_1,
          t.classn_row_2,
          t.classname_2,
          t2.employeename,
          t1.supplyerid,
          t1.zx_b2b_num_limit,
          d.lotno,
          d.lotid,
          d.proddate,
          d.invaliddate,
           c1.price
					 order by goodsqty  desc
    /

create view ERP_GOODS_QUALITY_V as
select b.fileid,
       a.goodsid,                                     --商品ID
    d.goodsname,--通用名
    d.currencyname,--  商品名称
       d.goodstype,                                   -- 商品规格
       d.approvedocno,--商品批准文号
    d.goodsunit,--商品单位
    e.factoryid,--厂家ID
    e.factoryname,--厂家名称
    c.lotno,--批号
    a.filecount,--文件数量
                                       'http://183.62.183.130:7200/'   IMGURL_TOP,
       a.fcheckid || '/' || b.filename IMGURL_BOTTOM, --药检图片本地路径
    a.fcheckid                      fcheckid,
       b.filename,
       (case
            when b.filename is null then
                '无'
            else
                b.filename
           end)                        IMGURL,        --药检图片名称
    b.credate                       create_time
from INCAERP.gsp_goods_factory_check a,
     INCAERP.np_efiles_op_file b,
     INCAERP.bms_lot_def c,
     INCAERP.pub_goods d,
     INCAERP.pub_factory e
where a.fcheckid = b.pkid
  and a.lotid = c.lotid
  and a.goodsid = d.goodsid
  and d.factoryid = e.factoryid
    /

create view ERP_VALIDITY_PRICE_V as
select a.customid,
       a.goodsid,
       a.entryid,
       CASE

           WHEN sign(nvl(c.price, 0)) = 0 THEN
               a.priceid
           ELSE
                   226 ---近效期价
           END priceid,
       CASE

           WHEN sign(nvl(c.price, 0)) = 0 THEN
                   a.price*10000
           ELSE
                   c.price*10000
           END price
from (SELECT a.price, a.priceid, b.customid, a.goodsid, a.entryid
      FROM inca.bms_entry_goods_price a, inca.pub_entry_customer b

      WHERE 1 = 1
        AND a.entryid = b.entryid

        AND a.priceid = b.priceid

        AND a.entryid = 1) a  left join
     (select a.goodsid, a.entryid, priceid, price
      from inca.bms_entry_goods_price a
      where a.priceid = 226) c

     on a.goodsid = c.goodsid
         AND a.entryid = c.entryid
where 1=1  --and  a.goodsid = 6102
    -- and a.customid  = 2086
    /

create view ACTIVITY_GOODS_V as
SELECT
    g.id,
    g.ERP_GOODS_ID,
    g.FACTORY_ID,
    g.HAVE_GIFT,
    g.EVALUATION_GOOD_STAR,
    g.EVALUATION_COUNT,
    g.GOODS_COMMEND,
    g.GOODS_IMG,
    g.GOODS_COLLECT,
    g.GOODS_CLICK,
    g.CREATE_TIME,
    g.ERP_ACC_FLAG,
    g.ERP_STORAGE_ID,
    g.ERP_LOT_NO,
    g.DESCRIPTION,
    gv.classname,
    gv.classname_3,
    gv.medicinetypename,
    gv.goodsname,
    gv.goodspinyin,
    gv.goodsunit,
    gv.price_2,
    gv.price_try,
    gv.approvedocno,
    gv.factoryname,
    gv.factoryid,
    gv.goodsid,
    gv.goodstype,
    gv.goodsid_gps,
    gv.barcode,
    gv.currencyname,
    gv.credate,
    gv.classn_row_1,
    gv.classname_1,
    gv.classname_2,
    gv.classn_row_2,
    gv.classn_row_3,
    gv.busiscope,
    gv.busiscope_name,
    gv.goodsqty,
    gv.invaliddate,
    gv.otcflag,
    gv.storagecondition,
    gv.prodarea,
    gv.transcondition,
    gv.dosage,
    gv.usegoodstime,
    gv.diagnosticinfo,
    gv.transporttime,
    gv.treatment,
    gv.memo,
    gv.supplyerid,
    gv.employeename,
    gv.zx_b2b_num_limit,
    gv.storageid,
    gv.storagename,
    gv.accflag,
    ag.ACTIVITY_ID,
    ag.AS_ID,
    ag.CONDITION_NUM,
    ag.TOP_LIMIT,
    ag.CONDITION_PRICE,
    ag.GOODS_PRICE,
    ag.IS_USE,
    a.content,
    a.abbreviation,
    a.CUSTOM_SET_TYPE,
    a.ACTIVITY_STRATEGY,
    a.WARM_TIME,
    a.START_EFFECTIVE_TIME,
    a.END_EFFECTIVE_TIME,
    a.START_TIME A_START_TIME,
    a.END_TIME A_END_TIME,
    a.TIMES,
    a.IS_SUPERPOSITION,
    a.WEEK,
    a.TIMES_STRATEGY,
    ast.STRATEGY_NAME,
    ast.GOODS_STRATEGY,
    ast.MEET_QUANTITY,
    ast.TOP_LIMIT as_top_limit,
    ast.AMOUNT_SATISFIED,
    ast.GIFT_NUM,
    ast.REDUCED_AMOUNT,
    ast.DISCOUNT,
    ast.CASH,
    ast.COUPON_ID,
    ast.GIFT_STRATEGY
from
    erp_goods_v gv
        LEFT JOIN goods g ON g.erp_goods_id = gv.goodsid
        left join activity_goods ag on ag.ERP_GOODS_ID=gv.GOODSID  and ag.is_use=1
        left join activity a on a.id=ag.ACTIVITY_ID and a.is_use=1 and ( to_char(sysdate - 1 ,'d')=a.WEEK or a.week=0 or a.week  is null )
        --and sysdate BETWEEN a.WARM_TIME and a.END_TIME
        left join activity_strategy ast on ast.ACTIVITY_ID=a.id  and ast.is_use=1
order by  (CASE
               when a.id is not null then 1
               when gv.goodsid_gps='X' then 0 END) asc, gv.goodsqty DESC
    /

