package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.Cart;
import com.zsyc.zt.b2b.entity.Coupon;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.entity.OrderGoods;
import com.zsyc.zt.b2b.vo.CouponReceiveVo;
import com.zsyc.zt.b2b.vo.CouponVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 优惠券 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-09-18
 */
public interface CouponMapper extends BaseMapper<Coupon> {
    /**
     * 优惠券列表
     *
     * @param page
     * @param couponVo
     * @return
     */
    IPage<CouponVo> getCouponList(@Param("page") Page page, @Param("couponVo") CouponVo couponVo);

    /**
     * 不分页优惠券列表
     *
     * @param couponVo
     * @return
     */
    List<CouponVo> getCouponListIsNotPage(@Param("couponVo") CouponVo couponVo);

    /**
     * 下单赠送的优惠券
     *
     * @param orderId
     * @return
     */
    List<CouponReceiveVo> getCouponByOrder(@Param("orderId") Long orderId);

    /**
     * 购物车商品可使用的优惠券
     *
     * @param memberId
     * @param cartList
     * @return
     */
    List<CouponVo> getByCartCoupon(@Param("memberId") Long memberId, @Param("cartList") List<Long> cartList, @Param("type") String type);

    /**
     * 商品id在集合里
     * @param cartList
     * @return
     */
    List<Long> getErpGoodsSetById(@Param("cartList") List<Long> cartList, @Param("couponId") Long couponId);

    /**
     * 订单商品可用优惠券
     * @param orderId
     * @param couponId
     * @return
     */
    List<OrderGoods> getOrderGoodsByCouponId(@Param("orderId") Long orderId, @Param("couponId") Long couponId);


    /**
     * 商品id不在集合里
     * @param couponId
     * @return
     */
    List<Long> getErpGoodsSetByIdNoSee(@Param("couponId") Long couponId);

    /**
     * 客户id不在集合里
     * @param couponId
     * @return
     */
    List<Long> getErpCustomSetByIdNoSee(@Param("couponId") Long couponId);

    /**
     * 客户是否存在集合里
     * @param memberList
     * @param customerId
     * @return
     */
    Integer isExistMemberSet(@Param("memberList") List<Long> memberList, @Param("customerId") Long customerId);
}
