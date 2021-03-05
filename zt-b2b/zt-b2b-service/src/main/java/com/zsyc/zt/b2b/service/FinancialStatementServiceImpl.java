package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.Coupon;
import com.zsyc.zt.b2b.mapper.CouponMapper;
import com.zsyc.zt.b2b.mapper.OrderGoodsMapper;
import com.zsyc.zt.b2b.mapper.OrderInfoMapper;
import com.zsyc.zt.b2b.vo.OrderInfoVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class FinancialStatementServiceImpl implements FinancialStatementService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;
    @Autowired
    private CouponMapper couponMapper;

    @Override
    public IPage<OrderInfoVo> getFinancialStatementTotalList(Page page, OrderInfoVo orderInfoVo) {
        IPage<OrderInfoVo> orderInfoVoIPage = this.orderInfoMapper.getFinancialStatementTotalList(page, orderInfoVo);
        orderInfoVoIPage.getRecords().forEach(orderInfoVo1 -> {
            orderInfoVo1.setOrderGoodsList(this.orderGoodsMapper.getOrderGoodsVoInfo(orderInfoVo1.getId()));
            String couponName = "";
            String couponAmount = "";
            String couponLimAmount = "";
            if (StringUtils.isNotBlank(orderInfoVo.getCouponIds())) {
                String[] couponIds1 = StringUtils.split(orderInfoVo.getCouponIds(), ',');
                List<Coupon> couponList = this.couponMapper.selectList(new QueryWrapper<Coupon>().in("id", couponIds1));

                for (Coupon coupon : couponList) {
                    couponName += "-" + coupon.getCouponName();
                    couponAmount += "-" + coupon.getReduceAmount();
                    couponLimAmount += "-" + coupon.getFullAmount();
                }
            }
            orderInfoVo1.setCouponName(couponName);
            orderInfoVo1.setCouponAmount(couponAmount);
            orderInfoVo1.setCouponLimAmount(couponLimAmount);
        });
        return orderInfoVoIPage;
    }

    @Override
    public IPage<OrderInfoVo> getFinancialStatementList(Page page, OrderInfoVo orderInfoVo) {
        IPage<OrderInfoVo> orderInfoVoIPage = this.orderInfoMapper.getFinancialStatementList(page, orderInfoVo);
        orderInfoVoIPage.getRecords().forEach(orderInfoVo1 -> {
            String couponName = "";
            String couponAmount = "";
            String couponLimAmount = "";
            if (StringUtils.isNotBlank(orderInfoVo.getCouponIds())) {
                String[] couponIds1 = StringUtils.split(orderInfoVo.getCouponIds(), ',');
                List<Coupon> couponList = this.couponMapper.selectList(new QueryWrapper<Coupon>().in("id", couponIds1));

                for (Coupon coupon : couponList) {
                    couponName += "-" + coupon.getCouponName();
                    couponAmount += "-" + coupon.getReduceAmount();
                    couponLimAmount += "-" + coupon.getFullAmount();
                }
            }
            orderInfoVo1.setCouponName(couponName);
            orderInfoVo1.setCouponAmount(couponAmount);
            orderInfoVo1.setCouponLimAmount(couponLimAmount);
        });
        return orderInfoVoIPage;
    }
}
