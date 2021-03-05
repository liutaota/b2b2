package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.CouponReceive;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.CouponReceiveVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户优惠券 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-09-18
 */
public interface CouponReceiveMapper extends BaseMapper<CouponReceive> {
    /**
     * 前台客户优惠券列表
     *
     * @param page
     * @param couponReceiveVo
     * @return
     */
    IPage<CouponReceiveVo> getCouponReceiveList(@Param("page") Page page, @Param("couponReceiveVo") CouponReceiveVo couponReceiveVo);

    /**
     * 前台客户优惠券列表
     *
     * @param couponReceiveVo
     * @return
     */
    List<CouponReceiveVo> getCouponReceiveList(@Param("couponReceiveVo") CouponReceiveVo couponReceiveVo);
    /**
     * 后台客户优惠券列表
     *
     * @param page
     * @param couponReceiveVo
     * @return
     */
    IPage<CouponReceiveVo> getAdminCouponReceiveList(@Param("page") Page page, @Param("couponReceiveVo") CouponReceiveVo couponReceiveVo);

    /**
     * 客户优惠券列表不分页
     *
     *
     * @param userId
     * @param couponReceiveVo
     * @return
     */
    List<CouponReceiveVo> getCouponReceiveListIsNotPage(@Param("userId") Long userId, @Param("couponReceiveVo") CouponReceiveVo couponReceiveVo);

    /**
     * 删除优惠券->废弃用户优惠券
     * @param id
     */
    void updateByCouponReceive(@Param("id") Long id);
}
