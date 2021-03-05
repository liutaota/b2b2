package com.zsyc.zt.inca.service;

import com.zsyc.zt.inca.vo.OrderBackInfoDocVo;
import com.zsyc.zt.inca.vo.OrderInfoDocVo;
import com.zsyc.zt.inca.vo.OrderResultDocVo;

import java.util.List;

/**
 * 退货业务流程
 * @author yuxi
 */
public interface OrderBackService {


    OrderResultDocVo genBackOrderV1(OrderBackInfoDocVo orderBackInfoDocVo);

    OrderResultDocVo genBackOrder(OrderBackInfoDocVo orderBackInfoDocVo);


    OrderResultDocVo acceptanceOrderSa(OrderBackInfoDocVo orderBackInfoDocVo);

    OrderResultDocVo acceptanceOrderPs(OrderBackInfoDocVo orderBackInfoDocVo);

    /**
     * 退货收货
     * @param orderInfoDocVo
     * @return
     */
    OrderResultDocVo receiptOrder(OrderBackInfoDocVo orderInfoDocVo);

    /**
     * 退货收货  配送
     * @param orderInfoDocVo
     * @return
     */
    OrderResultDocVo receiptOrderPs(OrderBackInfoDocVo orderInfoDocVo);


    /**
     * 退货收货  销售
     * @param orderInfoDocVo
     * @return
     */
    OrderResultDocVo receiptOrderSa(OrderBackInfoDocVo orderInfoDocVo);

    /**
     * 生成销售退单
     * @return
     */

    public OrderResultDocVo genBackOrderSa(OrderBackInfoDocVo orderBackInfoDocVo);

    /**
     * 生成配送退单
     * @return
     */
    public OrderResultDocVo genBackOrderPs(OrderBackInfoDocVo orderBackInfoDocVo);


    List<String> selectOrderPSNotCallback(int b2bShopFlag, int approveStatusDoc, int b2bNotWriteBack);

    void updatePSCallbackStatus(List<String> b2bOrderIds, int b2bNotWriteBack);
}
