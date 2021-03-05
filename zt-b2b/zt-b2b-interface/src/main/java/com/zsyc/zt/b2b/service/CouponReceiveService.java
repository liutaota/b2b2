package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.vo.CouponReceiveVo;

import java.util.List;

public interface CouponReceiveService {
    /**
     * 客户优惠券列表
     *
     * @param page
     * @param couponReceiveVo
     * @return
     */
    IPage<CouponReceiveVo> getCouponReceiveListUnUsed(Page page, CouponReceiveVo couponReceiveVo);

    /**
     * 客户优惠券列表不分页
     *
     * @param userId
     * @param couponReceiveVo
     * @return
     */
    List<CouponReceiveVo> getCouponReceiveListIsNotPage(Long userId, CouponReceiveVo couponReceiveVo);

    /**
     * 客户优惠券立即领取
     *
     * @param id
     * @param memberId
     */
    void immediatelyToReceive(Long id, Long memberId);

    /**
     * 客户优惠券列表
     *
     * @param page
     * @param couponReceiveVo
     * @return
     */
    IPage<CouponReceiveVo> getCouponReceiveList(Page page, CouponReceiveVo couponReceiveVo);

    /**
     * 废弃客户优惠券
     *
     * @param ids
     */
    void delCouponReceive(Long[] ids);

    /**
     * 修改优惠券状态(跑调度)
     */
    void updateCouponReceiveStatus();
}
