package com.zsyc.zt.inca.service;

import com.zsyc.framework.util.AssertExt;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * Created by lcs on 2020/7/15.
 */
@Service
@Slf4j
public class DemoServiceImpl implements DemoService {

    @Override
    public String echo(String hello) {
        AssertExt.notBlank(hello, "no hello");
        return String.format("hello %s", hello);
    }

//    /**
//     * 监听消息
//     *
//     * @param stringMessage
//     * @throws IOException
//     */
//    @RabbitListener(bindings = @QueueBinding(
//            exchange = @Exchange(value = Constant.RABBIT_MQ.EXCHANGE_DIRECT),
//            value = @Queue(value = "demo-mq-message", durable = "true"),
//            key = "demo-mq-message")
//    )
//    public void onMQMessage(String stringMessage) throws IOException {
//        log.info("onMQMessage {}", stringMessage);
//    }
//
//    /**
//     * 监听消息2
//     *
//     * @param stringMessage
//     * @throws IOException
//     */
//    @RabbitListener(bindings = @QueueBinding(
//            exchange = @Exchange(value = Constant.RABBIT_MQ.EXCHANGE_DIRECT),
//            value = @Queue(value = "demo-mq-message", durable = "true"),
//            key = "demo-mq-message")
//    )
//    public void onMQMessage2(String stringMessage) throws IOException {
//        log.info("onMQMessage2 {}", stringMessage);
//    }
	/*   @Resource
   private RabbitTemplate rabbitTemplate;*/

    /*
     * 监听消息
     * @param stringMessage
     * @throws IOException
     */
   /* @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange(value = com.zsyc.zt.inca.commom.Constant.RABBIT_MQ.EXCHANGE_DIRECT),
            value = @Queue(value = com.zsyc.zt.inca.commom.Constant.RABBIT_MQ.ROUTING_KEY_ORDER_STATUS_CALLBACK, durable = "true"),
            key = com.zsyc.zt.inca.commom.Constant.RABBIT_MQ.ROUTING_KEY_ORDER_STATUS_CALLBACK)
    )
    public void onOrderStatusCallback(String stringMessage) throws IOException {
        log.info("OrderCallbackVo {}", stringMessage);
        OrderCallbackVo orderCallbackVo = JSONObject.parseObject(stringMessage,OrderCallbackVo.class);
    }*/
//
//	/**
//	 * 监听消息
//	 * @param mqEventMessageVo
//	 */
//	@RabbitListener(bindings = @QueueBinding(
//			exchange = @Exchange(value = Constant.RABBIT_MQ.EXCHANGE,type = ExchangeTypes.FANOUT),
//			value = @Queue,
//			key = Constant.RABBIT_MQ.ROUTING_KEY)	)
//	public void onInnerMQMessage(String mqEventMessageVo){
//		log.debug("onMQMessage {}", mqEventMessageVo);
//
//	}
}
