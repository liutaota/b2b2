package com.zsyc.zt.b2b.common;

/**
 * Created by lcs on 2019-05-04.
 */
public class Constant {
    public static final Long[] STO_RANGE_IDS = {1L, 732L};//正常 保管帐id
    public static final Long[] STO_RANGE_IDS_V = {5L};//近效期 保管帐id
    public static final Long STORER_ID = 7368L;//仓库id

    public static final String TELEPHONE = "18012345678";

    public static final String SYSTEM_B2B_STATUS = "ON";
    public static final String SYSTEM_MIN_STATUS = "ON";

    //是否启用
    public static class IS_USE {
        public static final Integer ON = 1;//是
        public static final Integer OFF = 0;//否
    }

    //是否选中
    public static class IS_PITCH_ON {
        public static final Long ON = 1L;//是
        public static final Long OFF = 2L;//否
    }

    //是否删除
    public static class IS_DEL {
        public static final Integer YES = 1;//是
        public static final Integer NO = 0;//否
    }

    //支付数据源
    public static class PAY_DATA_SOURCE {
        //订单的
        public static final String ORDER_INFO = "orderInfo";
    }
    // 用户数据源
    public static class NEW_MEMBER {
        public static final Integer FORMER = 1;//是旧用户
        public static final String INITIAL_PWD = "123456";//初始密码
    }

    /**
     * dubbo版本
     */
    public final static class DUBBO_VERSION {
        /**
         * 英克
         */
        public final static String INCA = "${zsyc.dubbo.configs.inca.version:${dubbo.consumer.version}}";
    }

    //导出Excel文件夹名称
    public final static class EXCEL_PREFIX {
        public final static String GOODS = "excel/goodsList";
        public final static String ARRIVAL_NOTICE = "excel/arrivalNoticeList";
        public final static String NEW_PRODUCT = "excel/newProduct";
        public final static String CUSTOMER_INFO = "excel/customerInfo";
        public final static String APP_ERP = "excel/appErpList";
        public final static String ORDER = "excel/orderList";
    }

    //图片文件夹名称
    public final static class IMAGE_PREFIX {
        /**
         * 厂家
         */
        public final static String FACTORY = "factorys";

        /**
         * 商品
         */
        public final static String GOODS = "goods";

        /**
         * 广告位
         */
        public final static String ADV_POSITION = "advPosition";
        /**
         * 药检报告
         */
        public final static String QUALITY = "quality";
        /**
         * 证照图片
         */
        public final static String LICENCE = "licence";
        /**
         * 申请证照图片
         */
        public final static String LICENCE_APPLY = "licenceApply";
        /**
         * 新品图片
         */
        public final static String NEW_PRODUCT = "newProduct";
        /**
         * 分类图片
         */
        public final static String CLASS = "class";
        /**
         * 商城设置
         */
        public final static String STORE = "store";
    }

    /**
     * redis key 前缀
     */
    public static class REDIS_KEY_PREFIX {
        /**
         * 同步药检报告最大时间
         */
        public final static String SYNC_GOODS_QUALITY_IMAGE_MAX_TIME = "syncGoodsQualityImage:maxTime";

    }
}
