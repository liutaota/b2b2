package com.zsyc.zt.b2b.mapper;

import com.zsyc.zt.b2b.entity.ActivityOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 活动订单表 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-28
 */
public interface ActivityOrderMapper extends BaseMapper<ActivityOrder> {

    /**
     * 客户参与活动的次数
     * @param activityId
     * @param memberId
     * @param times
     * @return
     */
    List<ActivityOrder> getActivityOrderByMemberId(@Param("activityId") Long activityId, @Param("memberId") Long memberId, @Param("times") String times);

    /**
     * 取消订单的活动订单记录状态变更
     * @param orderId
     */
    void updateActivityOrderByOrderId(@Param("orderId") Long orderId);
}
