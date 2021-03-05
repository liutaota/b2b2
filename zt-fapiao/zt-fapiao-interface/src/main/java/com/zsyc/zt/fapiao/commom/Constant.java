package com.zsyc.zt.fapiao.commom;

/**
 * Created by lcs on 2020/7/24.
 */
public class Constant {
	public static class RABBIT_MQ {
		public static final String EXCHANGE = "amq.fanout";
		public static final String EXCHANGE_DIRECT = "amq.direct";
		public static final String ROUTING_KEY = "order.status.mq.message";
		public static final String ROUTING_KEY_DEMO = "order-status-mq-message";
		public static final String ROUTING_KEY_ORDER_STATUS_CALLBACK = "order-status-mq-message";
		/**
		 * header 中租户ID
		 */
		final String GOODS_SYNC = "GOODS_SYNC";


		/**
		 * header 中租户ID
		 */
		final String KPXX_WB_SYNC = "KPXX_WB_SYNC";

	}
}
