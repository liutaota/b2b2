package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.Coupon;
import com.zsyc.zt.b2b.vo.CouponGiveRecordVo;
import com.zsyc.zt.b2b.vo.CouponVo;
import com.zsyc.zt.b2b.vo.CustomerInfoVo;
import com.zsyc.zt.b2b.vo.CustomerVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CouponService {
    /**
     * 新增优惠券
     * @param couponVo
     * @param userId
     */
    void addCoupon(CouponVo couponVo, Long userId);

    /**
     * 编辑优惠券
     * @param couponVo
     * @param userId
     */
    void updateCoupon(CouponVo couponVo, Long userId);

    /**
     * 删除优惠券
     * @param id
     */
    void delCoupon(Long id);

    /**
     * 停用优惠券
     * @param id
     * @param isUse
     */
    void closeCoupon(Long id, Integer isUse);

    /**
     * 优惠券列表
     * @param page
     * @param couponVo
     * @return
     */
    IPage<CouponVo> getCouponList(Page page, CouponVo couponVo);

    /**
     * 不分页优惠券列表
     * @param couponVo
     * @return
     */
    List<CouponVo> getCouponListIsNotPage(CouponVo couponVo);

    /**
     * 后台赠送优惠券->客户集合
     * @param couponVo
     */
    CouponGiveRecordVo adminGiveCouponToMemberSet(CouponVo couponVo);

    /**
     * 后台赠送优惠券->客户
     * @param couponVo
     */
    CouponGiveRecordVo adminGiveCouponToMember(CouponVo couponVo);

    /**
     * 前台优惠券
     * @param id
     * @return
     */
    Coupon getCouponById(Long id);

    /**
     * 切换发放状态
     * @param id
     * @param isReceive
     */
    void cutIsReceive(Long id, Integer isReceive);

    /**
     * 优惠券可见客户集合
     * @param couponVo
     * @param memberId
     */
    void addCouponCustomerSet(CouponVo couponVo,Long memberId);

    /**
     * 优惠券可用商品集合
     * @param couponVo
     */
    void addCouponGoodsSet(CouponVo couponVo);


    /**
     * 客户列表不分页
     *
     * @param customerInfoVo
     * @return
     */
    List<CustomerVo> getCustomerByCustomerInfoVo(CustomerInfoVo customerInfoVo);
}
