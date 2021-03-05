package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.Coupon;
import com.zsyc.zt.b2b.entity.CouponReceive;
import com.zsyc.zt.b2b.entity.Member;
import com.zsyc.zt.b2b.mapper.CouponMapper;
import com.zsyc.zt.b2b.mapper.CouponReceiveMapper;
import com.zsyc.zt.b2b.mapper.MemberMapper;
import com.zsyc.zt.b2b.vo.CouponReceiveVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CouponReceiveServiceImpl implements CouponReceiveService {
    @Autowired
    private CouponReceiveMapper couponReceiveMapper;
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public IPage<CouponReceiveVo> getCouponReceiveListUnUsed(Page page, CouponReceiveVo couponReceiveVo) {
        AssertExt.notNull(page, "页码为空");
        AssertExt.hasId(couponReceiveVo.getMemberId(), "客户id无效");
//        AssertExt.notNull(couponReceiveVo.getStatus(), "状态为空");
//        AssertExt.checkEnum(CouponReceiveVo.EStatus.class, couponReceiveVo.getStatus(), "状态不匹配");
        Member memberDB = this.memberMapper.selectById(couponReceiveVo.getMemberId());
        couponReceiveVo.setMemberId(memberDB.getErpUserId());
        IPage<CouponReceiveVo> couponReceiveVoIPage = this.couponReceiveMapper.getCouponReceiveList(page, couponReceiveVo);
        // 检查优惠券的有效期是否已过期-->过期需要修改状态为已过期
        /*LocalDateTime localDateTime = LocalDateTime.now();
        List<CouponReceiveVo> couponReceiveVoList = new ArrayList<>();
        couponReceiveVoIPage.getRecords().forEach(couponReceiveVoDB -> {
            if (couponReceiveVoDB.getStatus().equals(CouponReceiveVo.EStatus.TO_RECEIVE.val())) {
                Coupon coupon = this.couponMapper.selectById(couponReceiveVoDB.getCouponId());
                if (null != coupon) {
                    if (coupon.getUseEndTime().isBefore(localDateTime)) {
                        CouponReceiveVo couponReceiveVo1 = new CouponReceiveVo();
                        couponReceiveVoDB.setStatus(CouponReceiveVo.EStatus.OVERDUE.val());
                        BeanUtils.copyProperties(couponReceiveVoDB, couponReceiveVo1);
                        this.couponReceiveMapper.updateById(couponReceiveVo1);
                    }
                }
                couponReceiveVoList.add(couponReceiveVoDB);
            } else if (couponReceiveVoDB.getStatus().equals(CouponReceiveVo.EStatus.UNUSED.val())) {
                CouponReceive couponReceive = this.couponReceiveMapper.selectById(couponReceiveVoDB.getId());
                if (null != couponReceive) {
                    if (couponReceive.getUseEndTime().isBefore(localDateTime)) {
                        couponReceive.setStatus(CouponReceive.EStatus.OVERDUE.val());
                        this.couponReceiveMapper.updateById(couponReceive);
                    }
                }
                couponReceiveVoList.add(couponReceiveVoDB);
            } else {
                couponReceiveVoList.add(couponReceiveVoDB);
            }
            couponReceiveVoIPage.setRecords(couponReceiveVoList);
        });*/

        return couponReceiveVoIPage;
    }

    @Override
    public List<CouponReceiveVo> getCouponReceiveListIsNotPage(Long userId, CouponReceiveVo couponReceiveVo) {
        AssertExt.notNull(couponReceiveVo.getType(), "类型为空");

        Member memberDB = this.memberMapper.selectById(userId);
        couponReceiveVo.setMemberId(memberDB.getErpUserId());
        //couponReceiveVo.setStatus("All");
        return this.couponReceiveMapper.getCouponReceiveList(couponReceiveVo);
    }

    @Override
    public void immediatelyToReceive(Long id, Long memberId) {
        AssertExt.hasId(id, "客户优惠券id无效");
        CouponReceive couponReceive = this.couponReceiveMapper.selectById(id);
        Member memberDB = this.memberMapper.selectById(memberId);
        AssertExt.notNull(memberDB, "客户不存在");
        if (couponReceive != null) {
            Coupon coupon = this.couponMapper.selectById(couponReceive.getCouponId());
            AssertExt.notNull(coupon, "优惠券不存在");
            couponReceive.setUseStartTime(LocalDateTime.now());
            if (couponReceive.getUseStartTime().isBefore(coupon.getUseStartTime())) {
                couponReceive.setUseStartTime(coupon.getUseStartTime());
                if (couponReceive.getUseStartTime().plusDays(coupon.getUseDay()).isAfter(coupon.getUseEndTime())) {
                    couponReceive.setUseEndTime(coupon.getUseEndTime());
                } else {
                    couponReceive.setUseEndTime(couponReceive.getUseStartTime().plusDays(coupon.getUseDay() - 1).with(LocalTime.MAX));
                }
            } else {
                if (couponReceive.getUseStartTime().plusDays(coupon.getUseDay()).isAfter(coupon.getUseEndTime())) {
                    couponReceive.setUseEndTime(coupon.getUseEndTime());
                } else {
                    couponReceive.setUseEndTime(couponReceive.getUseStartTime().plusDays(coupon.getUseDay()));
                }
            }
            couponReceive.setToTime(LocalDateTime.now());
            couponReceive.setStatus(CouponReceive.EStatus.UNUSED.val());
            this.couponReceiveMapper.updateById(couponReceive);
        }
       /* if (null != coupon) {
            CouponReceive couponReceive = new CouponReceive();
            couponReceive.setMemberId(memberId);
            couponReceive.setStatus(CouponReceive.EStatus.UNUSED.val());
            couponReceive.setCreateTime(LocalDateTime.now());
            couponReceive.setIsDel(Constant.IS_DEL.NO);
            couponReceive.setCouponId(coupon.getId());
            couponReceive.setUseStartTime(LocalDateTime.now());
            if (couponReceive.getUseStartTime().plusDays(coupon.getUseDay()).isBefore(coupon.getUseEndTime())) {
                couponReceive.setUseEndTime(couponReceive.getUseStartTime().plusDays(coupon.getUseDay()));
            } else {
                couponReceive.setUseEndTime(coupon.getUseEndTime());
            }
            this.couponReceiveMapper.insert(couponReceive);
        }*/
    }

    @Override
    public IPage<CouponReceiveVo> getCouponReceiveList(Page page, CouponReceiveVo couponReceiveVo) {
        AssertExt.notNull(page, "页码为空");
        return this.couponReceiveMapper.getAdminCouponReceiveList(page, couponReceiveVo);
    }

    @Override
    public void delCouponReceive(Long[] ids) {
        AssertExt.notNull(ids, "ids为空");
        for (Long id : ids) {
            CouponReceive couponReceiveDB = this.couponReceiveMapper.selectById(id);
            couponReceiveDB.setStatus(CouponReceive.EStatus.DEL.val());
            this.couponReceiveMapper.updateById(couponReceiveDB);
        }
    }

    @Override
    public void updateCouponReceiveStatus() {
        List<CouponReceive> couponReceiveVoList = this.couponReceiveMapper.selectList(new QueryWrapper<CouponReceive>().in("status", CouponReceive.EStatus.UNUSED.val(), CouponReceive.EStatus.TO_RECEIVE.val()));
        for (CouponReceive couponReceiveVo : couponReceiveVoList) {
            if (couponReceiveVo.getStatus().equals(CouponReceiveVo.EStatus.TO_RECEIVE.val())) {
                Coupon coupon = this.couponMapper.selectById(couponReceiveVo.getCouponId());
                if (null != coupon) {
                    if (coupon.getUseEndTime().isBefore(LocalDateTime.now())) {
                        couponReceiveVo.setStatus(CouponReceiveVo.EStatus.OVERDUE.val());
                        this.couponReceiveMapper.updateById(couponReceiveVo);
                    }
                }
            } else if (couponReceiveVo.getStatus().equals(CouponReceiveVo.EStatus.UNUSED.val())) {
                if (couponReceiveVo.getUseEndTime().isBefore(LocalDateTime.now())) {
                    couponReceiveVo.setStatus(CouponReceive.EStatus.OVERDUE.val());
                    this.couponReceiveMapper.updateById(couponReceiveVo);
                }

            }

        }
    }

}
