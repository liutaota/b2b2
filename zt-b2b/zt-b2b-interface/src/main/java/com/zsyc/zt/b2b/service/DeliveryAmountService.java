package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.DeliveryAmount;
import com.zsyc.zt.b2b.vo.DeliveryAmountVo;

import java.util.List;

public interface DeliveryAmountService {
    /**
     * 起送规则列表
     *
     * @param page
     * @param deliveryAmountVo
     * @return
     */
    IPage<DeliveryAmount> getDeliveryAmount(Page page, DeliveryAmountVo deliveryAmountVo);

    /**
     * 新增起送规则
     *
     * @param deliveryAmount
     * @param userId
     */
    void addDeliveryAmount(DeliveryAmount deliveryAmount, Long userId);

    /**
     * 修改起送规则状态
     *
     * @param id
     * @param isUse
     * @param userId
     */
    void updateDeliveryAmountIsUse(Long id, String isUse, Long userId);

    /**
     * 编辑起送规则
     *
     * @param deliveryAmount
     * @param userId
     */
    void updateDeliveryAmount(DeliveryAmount deliveryAmount, Long userId);

    /**
     * 根据客户id查询客户的起送金额
     *
     * @param id
     * @return
     */
    Long getDeliveryAmountByCustomerId(Long id);

    /**
     * 删除起送规则
     * @param id
     */
    void delDeliveryAmount(Long id);
}
