package com.zsyc.zt.inca.service;

import com.zsyc.zt.inca.vo.OrderInfoDocVo;
import com.zsyc.zt.inca.vo.OrderResultDocVo;
import com.zsyc.zt.inca.vo.OrderStatusVo;

import java.util.List;


/**
 * @date
 * @author peiqy
 */
public interface OrderService {


    /**
     * peiqy 生成订单
     * @return
     */
    OrderResultDocVo genOrder(OrderInfoDocVo orderInfoDocVo);


    /**
     * 订单收货
     * @return
     */
    OrderResultDocVo receiptOrder(OrderInfoDocVo orderInfoDocVo);



    void updateCallbackStatus(List<String> orderIds, Integer i);

    Integer getCountBy(Long saleId, Long b2bOrderId);

    /**
     * 确定赠品单
     * @param orderNo  报货平台订单号
     * @param srcStatus
     * @param targetStatus
     * @param entryId
     * @param platformList 对应的zx_bh_flag
     */
    void confirmGiftOrder(String orderNo, Integer entryId, Integer srcStatus,Integer targetStatus,List<Integer> platformList);



    /**
     * 确定销售单
     * @param orderNo  报货平台订单号
     * @param srcStatus
     * @param targetStatus
     * @param entryId
     * @param platformList 对应的zx_bh_flag
     */
    void confirmSaOrder(String orderNo, Integer entryId, Integer srcStatus,Integer targetStatus,List<Integer> platformList);


    /**
     * 确定配送单
     * @param orderNo  报货平台订单号
     * @param srcStatus
     * @param targetStatus
     * @param entryId
     * @param platformList 对应的zx_bh_flag
     */
    void confirmPsOrder(String orderNo, Integer entryId, Integer srcStatus,Integer targetStatus,List<Integer> platformList);

    Boolean isUseWms(Integer storageId, Integer entryId);

    //@Cached(name="OrderService-selectOrderStatusList-", key="#orderIdList", expire = 3600)
    List<OrderStatusVo> selectOrderStatusList(List<Long> orderIdList);
    //@Cached(name="OrderService-getOrderStatus-", key="#orderId", expire = 1200)
    Integer getOrderStatus(Long orderId);

    /**
     *
     * @param orderIds
     * @param payFlowNo
     * @param payOrderNo
     */
    void writeBackPayOrderAndFlowNo(Long customId,Integer entryId,List<Long> orderIds, String payFlowNo, String payOrderNo);
}
