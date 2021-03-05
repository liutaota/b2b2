package com.zsyc.zt.b2b.api.controller.PCWeb;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.entity.Coupon;
import com.zsyc.zt.b2b.entity.CouponReceive;
import com.zsyc.zt.b2b.service.CouponReceiveService;
import com.zsyc.zt.b2b.service.CouponService;
import com.zsyc.zt.b2b.vo.CouponReceiveVo;
import com.zsyc.zt.b2b.vo.CouponVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api
@RestController
@Slf4j
@RequestMapping("api/pcCoupon")
public class PCCouponController {
    @Reference
    private CouponReceiveService couponReceiveService;
    @Reference
    private CouponService couponService;
    @Autowired
    private AccountHelper accountHelper;

    @ApiOperation("客户优惠券列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "memberId", value = "客户id", dataType = "Long"),
            @ApiImplicitParam(name = "status", value = "状态", dataType = "String"),
    })
    @GetMapping("getCouponReceiveListUnUsed")
    public IPage<CouponReceiveVo> getCouponReceiveListUnUsed(Page page, CouponReceiveVo couponReceiveVo) {
        couponReceiveVo.setMemberId(this.accountHelper.getUserId());
        return this.couponReceiveService.getCouponReceiveListUnUsed(page, couponReceiveVo);
    }

    @ApiOperation("客户优惠券列表不分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "过滤优惠券状态", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "过滤优惠券类型", required = true, dataType = "String")
    })
    @GetMapping("getCouponReceiveListIsNotPage")
    public List<CouponReceiveVo> getCouponReceiveListIsNotPage(CouponReceiveVo couponReceiveVo) {
        return this.couponReceiveService.getCouponReceiveListIsNotPage(this.accountHelper.getUserId(),couponReceiveVo);
    }

    @ApiOperation("前台优惠券")
    @ApiImplicitParam(name = "id",value = "优惠券id",required = true,dataType = "Long")
    @GetMapping("getPcCouponById")
    public Coupon getCouponById(Long id){
        return this.couponService.getCouponById(id);
    }

    @ApiOperation("前台优惠券列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "couponName", value = "优惠券名称", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    @GetMapping("getPcCouponList")
    public List<CouponVo> getCouponList(CouponVo couponVo){
        return this.couponService.getCouponListIsNotPage(couponVo);
    }

    @ApiOperation("客户优惠券立即领取")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id",required = true,dataType = "Long"),
            @ApiImplicitParam(name = "memberId",value = "th",required = true,dataType = "Long")
    })
    @GetMapping("immediatelyToReceive")
    public void immediatelyToReceive(Long id){
        this.couponReceiveService.immediatelyToReceive(id,this.accountHelper.getUserId());
    }
}
