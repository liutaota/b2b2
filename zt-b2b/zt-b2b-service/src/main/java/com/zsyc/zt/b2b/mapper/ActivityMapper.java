package com.zsyc.zt.b2b.mapper;

import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.entity.ActivityStrategy;
import com.zsyc.zt.b2b.entity.Cart;
import com.zsyc.zt.b2b.entity.OrderGoods;
import com.zsyc.zt.b2b.vo.ActivityVo;
import com.zsyc.zt.b2b.vo.OrderGoodsVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 活动主表 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-28
 */
public interface ActivityMapper extends BaseMapper<Activity> {
    /**
     * 活动策略列表
     *
     * @param page
     * @param activityVo
     * @return
     */
    IPage<ActivityVo> getActivityVoList(@Param("page") Page page, @Param("activityVo") ActivityVo activityVo);

    /**
     * 查询全场折扣活动
     *
     * @return
     */
    @Cached(name = "getActivityAllDiscount",key="#customid+''+#dayOfWeek", expire = 1)
    ActivityVo getActivityAllDiscount(@Param("customid") Long customid, @Param("dayOfWeek") int dayOfWeek);

    /**
     * 活动详情
     *
     * @param id
     * @return
     */
    @Cached(name = "getActivityById",key="#id+''+#goodsId+''+#fromTo+''+#customid+''+#dayOfWeek", expire = 1)
    List<ActivityVo> getActivityById(@Param("id") Long id, @Param("goodsId") Long goodsId, @Param("fromTo") Integer fromTo, @Param("customid") Long customid, @Param("dayOfWeek") int dayOfWeek);

    /**
     * 商品id活动详情
     * @param goodsId
     * @return
     */
    // List<ActivityVo> getActivityByGoodsId(@Param("activityId") Long activityId, @Param("goodsId") Long goodsId, @Param("customid") Long customid, @Param("dayOfWeek") int dayOfWeek);

    /**
     * 商品id活动详情--秒杀/折扣/品牌/限购
     *
     * @param goodsId
     * @return
     */
    @Cached(name = "getActivityByGoodsId",key="#activityStrategy+''+#activityId+''+#fromTo+''+#goodsId+''+#customid+''+#dayOfWeek", expire = 1)
    List<ActivityVo> getActivityByGoodsId(@Param("activityStrategy") Long activityStrategy, @Param("activityId") Long activityId, @Param("goodsId") Long goodsId, @Param("customid") Long customid, @Param("dayOfWeek") int dayOfWeek);


    /**
     * 活动详情--商品折扣和秒杀
     *
     * @return
     */
    @Cached(name = "getActivityByGoods",key="#goodsId+''+#customid+''+#dayOfWeek", expire = 1)
    List<ActivityVo> getActivityByGoods(@Param("goodsId") Long goodsId, @Param("customid") Long customid, @Param("dayOfWeek") int dayOfWeek);

    /**
     * 活动详情--全场优惠券
     *
     * @return
     */
    @Cached(name = "getActivityByCoupon",key="#customid+''+#dayOfWeek", expire = 1)
    ActivityVo getActivityByCoupon(@Param("customid") Long customid, @Param("dayOfWeek") int dayOfWeek);


    /**
     * 商品折扣活动
     *
     * @return
     */
    @Cached(name = "getActivityGoodsDiscount",key="#customid+''+#goodsId+''+#dayOfWeek", expire = 1)
    List<ActivityStrategy> getActivityGoodsDiscount(@Param("customid") Long customid, @Param("goodsId") Long goodsId, @Param("dayOfWeek") int dayOfWeek);

    /**
     * 满减满赠活动--某商品
     *
     * @return
     */
    @Cached(name = "getActivity30Or40ByGoods",key="#goodsId+''+#customid+''+#dayOfWeek", expire = 1)
    ActivityVo getActivity30Or40ByGoods(@Param("goodsId") Long goodsId, @Param("customid") Long customid, @Param("dayOfWeek") int dayOfWeek);

    /**
     * 单品赠活动--某商品/抽奖
     *
     * @return
     */
    @Cached(name = "getActivity90ByGoods",key="#activityStrategy+''+#goodsId+''+#customid+''+#dayOfWeek", expire = 1)
    ActivityVo getActivity90ByGoods(@Param("activityStrategy") Long activityStrategy,@Param("goodsId") Long goodsId, @Param("customid") Long customid, @Param("dayOfWeek") int dayOfWeek);


    /**
     * 下单选中的商品是否存在赠送优惠券活动
     * @param goodsId
     * @param customid
     * @param dayOfWeek
     * @return
     */
    @Cached(name = "getActivityByPitchOnGoodsId",key="#goodsId+''+#customid+''+#dayOfWeek+''+#orderId", expire = 1)
    List<OrderGoodsVo> getActivityByPitchOnGoodsId(@Param("goodsId") List<Long> goodsId, @Param("customid") Long customid, @Param("dayOfWeek") int dayOfWeek,@Param("orderId") Long orderId);

    /**
     * 查询购物车相同商品活动id
     * @param
     * @param activityId
     */
    List<Cart> getCartActivity(@Param("activityId") Long activityId, @Param("memberId") Long memberId, @Param("erpUserId") Long erpUserId, @Param("dayOfWeek") int dayOfWeek);

    /**
     * 我的购物车参与的活动
     * @param memberId
     * @param erpUserId
     * @param dayOfWeek
     * @return
     */
    List<ActivityVo> getMyCartJoinActivity(@Param("memberId") Long memberId, @Param("erpUserId") Long erpUserId, @Param("dayOfWeek") int dayOfWeek);
}
