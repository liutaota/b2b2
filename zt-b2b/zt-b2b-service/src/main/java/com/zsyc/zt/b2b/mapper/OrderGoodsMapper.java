package com.zsyc.zt.b2b.mapper;

import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.OrderGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.GoodsInfoVo;
import com.zsyc.zt.b2b.vo.OrderGoodsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单快照表 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-30
 */
public interface OrderGoodsMapper extends BaseMapper<OrderGoods> {
    /**
     * 订单商品详情
     *
     * @param orderId
     * @return
     */
    @Cached(name = "getOrderGoodsVoInfo",key="#orderId", expire = 10)
    List<OrderGoodsVo> getOrderGoodsVoInfo(@Param("orderId") Long orderId);

    /**
     * 我的常购清单
     *
     * @param memberId
     * @return
     */
    IPage<OrderGoodsVo> getMyOrderGoods(@Param("memberId") Long memberId, @Param("page") Page page);


    /**
     * 发货单
     */
    @Cached(name = "orderGoodsDeliveryList",key="#orderId", expire = 5)
    List<OrderGoodsVo> orderGoodsDeliveryList(@Param("orderId") Long orderId);

    /**
     * 再次购买
     *
     * @param orderId
     * @param memberId
     * @return
     */
    List<OrderGoodsVo> getTwoBuyGoodsInfoVo(@Param("orderId") Long orderId, @Param("memberId") Long memberId);

    /**
     * 该订单参与的活动内容
     * @param orderId
     * @return
     */
    List<OrderGoodsVo> getActivityContent(@Param("orderId") Long orderId);

    /**
     * 订单的商品id--非赠品/特价商品
     * @param orderId
     * @return
     */
    List<Long> getOrderIdByErpGoodsId(@Param("orderId") Long orderId);

    /**
     * 短减的商品
     * @param orderId
     * @return
     */
    List<OrderGoods> getShortOrderGoodsList(@Param("orderId") Long orderId);

    /**
     * 兑换积分商品
     * @param page
     * @param orderGoods
     * @return
     */
    IPage<OrderGoodsVo> getOrderGoodsBy9(@Param("page") Page page, @Param("orderGoods") OrderGoodsVo orderGoods);
}
