package com.zsyc.zt.b2b.api.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.entity.AdminLog;
import com.zsyc.zt.b2b.entity.CouponLog;
import com.zsyc.zt.b2b.service.AdminService;
import com.zsyc.zt.b2b.service.CouponLogService;
import com.zsyc.zt.b2b.service.CouponReceiveService;
import com.zsyc.zt.b2b.service.CouponService;
import com.zsyc.zt.b2b.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Api
@RestController
@RequestMapping("api/coupon")
public class AdminCouponController {
    @Reference
    private AdminService adminService;
    @Reference
    private CouponService couponService;
    @Reference
    private CouponReceiveService couponReceiveService;
    @Reference
    private CouponLogService couponLogService;
    @Autowired
    private AccountHelper accountHelper;

    @ApiOperation("新增优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "couponName", value = "优惠券名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "customSetType", value = "客户集合类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goodsSetType", value = "商品集合类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "explain", value = "优惠券说明", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "优惠券类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "fullAmount", value = "满足金额", dataType = "String"),
            @ApiImplicitParam(name = "reduceAmount", value = "优惠金额", dataType = "String"),
            @ApiImplicitParam(name = "discount", value = "折扣", dataType = "String"),
            @ApiImplicitParam(name = "couponAccept", value = "总发放数量", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limitNum", value = "用户限领数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "receiveStartTime", value = "发放开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "receiveEndTime", value = "可领取结束时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "useDay", value = "有效使用天数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "useStartTime", value = "可使用开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "useEndTime", value = "可使用结束时间", required = true, dataType = "String"),
    })
    @PostMapping("addCoupon")
    public void addCoupon(@RequestBody CouponVo couponVo, HttpServletRequest request) {
        this.couponService.addCoupon(couponVo, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("新增优惠券-->名称：" + couponVo.getCouponName()).setIp(this.accountHelper.getIP(request))
                .setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminCouponController&addCoupon"));
    }

    @ApiOperation("编辑优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "优惠券id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "couponName", value = "优惠券名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "customSetType", value = "客户集合类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goodsSetType", value = "商品集合类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "explain", value = "优惠券说明", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "优惠券类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "fullAmount", value = "满足金额", dataType = "String"),
            @ApiImplicitParam(name = "reduceAmount", value = "优惠金额", dataType = "String"),
            @ApiImplicitParam(name = "discount", value = "折扣", dataType = "String"),
            @ApiImplicitParam(name = "couponAccept", value = "总发放数量", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limitNum", value = "用户限领数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "receiveStartTime", value = "发放开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "receiveEndTime", value = "可领取结束时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "useDay", value = "有效使用天数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "useStartTime", value = "可使用开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "useEndTime", value = "可使用结束时间", required = true, dataType = "String"),
    })
    @PostMapping("updateCoupon")
    public void updateCoupon(@RequestBody CouponVo couponVo, HttpServletRequest request) {
        this.couponService.updateCoupon(couponVo, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("编辑优惠券-->id：" + couponVo.getId()).setIp(this.accountHelper.getIP(request))
                .setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminCouponController&updateCoupon"));
    }

    @ApiOperation("删除优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "优惠券id", required = true, dataType = "Long"),
    })
    @GetMapping("delCoupon")
    public void delCoupon(Long id, HttpServletRequest request) {
        this.couponService.delCoupon(id);
        this.adminService.addAdminLog(new AdminLog().setContent("优惠券id：" + id + "-->删除").setIp(this.accountHelper.getIP(request))
                .setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminCouponController&delCoupon"));
    }

    @ApiOperation("停用优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "优惠券id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "isUse", value = "是否可用", required = true, dataType = "Integer")
    })
    @GetMapping("downCoupon")
    public void closeCoupon(Long id, Integer isUse, HttpServletRequest request) {
        this.couponService.closeCoupon(id, isUse);
        this.adminService.addAdminLog(new AdminLog().setContent("优惠券id：" + id + (isUse.equals(1) ? "-->启用" : "-->关闭")).setIp(this.accountHelper.getIP(request))
                .setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminCouponController&closeCoupon"));
    }

    @ApiOperation("切换发放状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "优惠券id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "isReceive", value = "是否发放", required = true, dataType = "Integer")
    })
    @GetMapping("cutIsReceive")
    public void cutIsReceive(Long id, Integer isReceive, HttpServletRequest request) {
        this.couponService.cutIsReceive(id, isReceive);
        this.adminService.addAdminLog(new AdminLog().setContent("优惠券id：" + id + (isReceive.equals(1) ? "-->发放" : "-->不发放")).setIp(this.accountHelper.getIP(request))
                .setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminCouponController&cutIsReceive"));
    }


    @ApiOperation("优惠券列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "couponName", value = "优惠券名称", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    @GetMapping("getCouponList")
    public IPage<CouponVo> getCouponList(Page page, CouponVo couponVo) {
        return this.couponService.getCouponList(page, couponVo);
    }

    @ApiOperation("不分页优惠券列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "couponName", value = "优惠券名称", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    @GetMapping("getCouponListIsNotPage")
    public List<CouponVo> getCouponListIsNotPage(CouponVo couponVo) {
        return this.couponService.getCouponListIsNotPage(couponVo);
    }

    @ApiOperation("后台赠送优惠券->客户集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberSet", value = "客户集合", required = true, dataType = "List"),
            @ApiImplicitParam(name = "couponId", value = "优惠券id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "giveNum", value = "赠送数量", required = true, dataType = "Integer")
    })
    @PostMapping("adminGiveCouponToMemberSet")
    public CouponGiveRecordVo adminGiveCouponToMemberSet(@RequestBody CouponVo couponVo, HttpServletRequest request) {
        this.adminService.addAdminLog(new AdminLog().setContent("后台赠送优惠券->客户集合-->集合id：" + couponVo.getCustomerSetList().stream().map(CouponVo.CustomerSet::getCustomerSetId).collect(Collectors.toList()))
                .setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminCouponController&adminGiveCouponToMemberSet"));
        return this.couponService.adminGiveCouponToMemberSet(couponVo);
    }

    @ApiOperation("后台赠送优惠券->客户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberId", value = "客户id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "couponId", value = "优惠券id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "giveNum", value = "赠送数量", required = true, dataType = "Integer")
    })
    @GetMapping("adminGiveCouponToMember")
    public CouponGiveRecordVo adminGiveCouponToMember(CouponVo couponVo, HttpServletRequest request) {
        this.adminService.addAdminLog(new AdminLog().setContent("后台赠送优惠券->客户-->客户id：" + couponVo.getCustomerIdList()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminCouponController&adminGiveCouponToMember"));
        return this.couponService.adminGiveCouponToMember(couponVo);
    }

    @ApiOperation("客户优惠券列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "memberId", value = "用户id", dataType = "Long"),
            @ApiImplicitParam(name = "couponId", value = "优惠券id", dataType = "Long"),
            @ApiImplicitParam(name = "status", value = "状态", dataType = "String"),
            @ApiImplicitParam(name = "telephone", value = "手机号", dataType = "String"),
            @ApiImplicitParam(name = "userName", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "couponCode", value = "优惠券码", dataType = "Long"),
            @ApiImplicitParam(name = "couponName", value = "优惠券名称", dataType = "Long"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),

    })
    @GetMapping("getCouponReceiveList")
    public IPage<CouponReceiveVo> getCouponReceiveList(Page page, CouponReceiveVo couponReceiveVo) {
        return this.couponReceiveService.getCouponReceiveList(page, couponReceiveVo);
    }

    @ApiOperation("客户列表不分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "模糊搜索名称和id", dataType = "Long"),
    })
    @GetMapping("getCustomerByCustomerInfoVo")
    public List<CustomerVo> getCustomerByCustomerInfoVo(@Param("customerInfoVo") CustomerInfoVo customerInfoVo) {
        return this.couponService.getCustomerByCustomerInfoVo(customerInfoVo);
    }

    @ApiOperation("废弃客户优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "id", required = true, dataType = "Long[]"),
    })
    @GetMapping("delCouponReceive")
    public void delCouponReceive(Long[] ids, HttpServletRequest request) {
        this.couponReceiveService.delCouponReceive(ids);
        this.adminService.addAdminLog(new AdminLog().setContent("废弃客户优惠券-->id：" + Arrays.toString(ids)).setIp(this.accountHelper.getIP(request))
                .setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminCouponController&delCouponReceive"));
    }

    @ApiOperation("优惠券日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
    })
    @GetMapping("getCouponLogList")
    public IPage<CouponLog> getCouponLogList(Page page) {
        return this.couponLogService.getCouponLogList(page);
    }
}
