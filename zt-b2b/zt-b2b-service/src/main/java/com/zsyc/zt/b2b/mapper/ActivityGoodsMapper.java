package com.zsyc.zt.b2b.mapper;

import com.alicp.jetcache.anno.Cached;
import com.zsyc.zt.b2b.entity.ActivityGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 活动商品 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-28
 */
public interface ActivityGoodsMapper extends BaseMapper<ActivityGoods> {

    /**
     * 相同策略下的活动商品
     * @param asId
     * @param erpGoodsId
     * @return
     */
    @Cached(name = "getActivityGoodsByAsId",key="#asId+''+#erpGoodsId", expire = 1)
    List<ActivityGoods> getActivityGoodsByAsId(@Param("asId") Long asId, @Param("erpGoodsId") List<Long> erpGoodsId);

    /**
     * 相同策略下的活动商品
     * @param asId
     * @param asId
     * @return
     */
    @Cached(name = "getActivityGoodsByAcIdAsId",key="#acId+''+#asId", expire = 1)
    List<ActivityGoods> getActivityGoodsByAcIdAsId(@Param("acId") Long acId, @Param("asId") Long asId);


    /**
     * 活动商品详情
     * @param asId
     * @param asId
     * @param erpGoodsId
     * @return
     */
    @Cached(name = "getActivityGoodsInfo",key="#acId+''+#asId+''+#erpGoodsId", expire = 1)
    ActivityGoods getActivityGoodsInfo(@Param("acId") Long acId, @Param("asId") Long asId,@Param("erpGoodsId") Long erpGoodsId);
}
