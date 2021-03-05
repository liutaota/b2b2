package com.zsyc.zt.b2b.mapper;

import com.zsyc.zt.b2b.entity.InOrderDate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * <p>
 * 订单中间表 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-12-07
 */
public interface InOrderDateMapper extends BaseMapper<InOrderDate> {
    /**
     * 客户1分钟之前未取消的订单
     * @param customerId
     * @return
     */
    Integer getOrderInfoByCustomerId(@Param("customerId") Long customerId, @Param("time") Long time, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);
}
