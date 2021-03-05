package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.DeliveryAmount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.DeliveryAmountVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 起送规则 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-15
 */
public interface DeliveryAmountMapper extends BaseMapper<DeliveryAmount> {
    /**
     * 起送规则列表
     *
     * @param page
     * @param deliveryAmountVo
     * @return
     */
    IPage<DeliveryAmount> getDeliveryAmount(@Param("page") Page page, @Param("deliveryAmountVo") DeliveryAmountVo deliveryAmountVo);

    /**
     * 根据客户id查询客户的起送金额
     *
     * @param customid
     * @return
     */
    Long getDeliveryAmountByCustomerId(@Param("customid") Long customid);
}
