package com.zsyc.zt.order.api.service;

import com.zsyc.zt.order.vo.OrderDetailVo;
import com.zsyc.zt.order.vo.OrderInfoDocVo;
import com.zsyc.zt.order.vo.OrderResultDocVo;

import java.util.List;

/**
 * @date
 * @author peiqy
 */
public interface OrderService {


    /**
     * peiqy 生成订单
     * @param orderInfoDocVo
     * @return
     */
    OrderResultDocVo genOrder(OrderInfoDocVo orderInfoDocVo);


    /**
     * 订单收货
     * @param orderInfoDocVo
     * @return
     */
    OrderResultDocVo receiptOrder(OrderInfoDocVo orderInfoDocVo);


    /**
     *
     * @param zxBhFlag
     * @param b2bNotWriteBack
     * @param useStatus
     * @return
     */
    List<String> selectOrderNotCallback(Integer zxBhFlag, Integer b2bNotWriteBack, Integer useStatus);


    /**
     *  回写状态
     * @param orderIds
     * @param i
     */
    void updateCallbackStatus(List<String> orderIds, Integer i);

    /**
     *写状态
     * @param saleId
     * @param orderId
     * @return
     */
    Integer getCountBy(Long saleId, String orderId);



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

    /**
     * 是否使用物流
     * @param storageId
     * @param entryId
     * @return
     */
    Boolean isUseWms(Long storageId, Integer entryId);

    /**
     * 查询订单状态
     * @param orderId
     * @return
     */
    //@Cached(name="OrderService-getOrderStatus-", key="#orderId", expire = 36000)
    Integer getOrderStatus(String orderId);

    List<OrderDetailVo> selectOrderDetails(String orderId);

    OrderResultDocVo genOrderV2(OrderInfoDocVo orderInfoDocVo);
}