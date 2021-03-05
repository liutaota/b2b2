package com.zsyc.zt.b2b.api.controller.PCWeb;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.pay.entity.PayOrder;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.service.*;
import com.zsyc.zt.b2b.vo.*;
import com.zsyc.zt.inca.vo.FapiaoAddressVo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by tang on 2020-07-30.
 */
@Api
@RestController
@RequestMapping("api/pcOrderInfo")
@Slf4j
public class PCOrderInfoController {
    @Autowired
    private AccountHelper accountHelper;
    @Reference
    private OrderInfoService orderInfoService;
    @Reference
    private ReasonService reasonService;
    @Reference
    private DeliveryAmountService deliveryAmountService;
    @Reference
    private SettingService settingService;
    @Reference
    private RefundReturnService refundReturnService;

    @ApiOperation("客户下单")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @PostMapping(value = "memberPayOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "payMethod", value = "支付方式", required = true, dataType = "String"),
            @ApiImplicitParam(name = "addressDetail", value = "地址详情", required = true, dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
            @ApiImplicitParam(name = "memberName", value = "客户姓名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "memberEmail", value = "客户电子邮箱", required = true, dataType = "String"),
            @ApiImplicitParam(name = "memberPhone", value = "客户手机", required = true, dataType = "String"),
            @ApiImplicitParam(name = "invType", value = "发票类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "invDemand", value = "发票要求", dataType = "String"),
            @ApiImplicitParam(name = "zxPhOrder", value = "线序", dataType = "String"),
            @ApiImplicitParam(name = "translinename", value = "运输路线", dataType = "String"),
            @ApiImplicitParam(name = "orderInfo.orderGoodsList.goodsPriceId", value = "goodsPriceId", dataType = "String"),
            @ApiImplicitParam(name = "orderInfo.orderGoodsList.goodsNum", value = "数量", required = true, dataType = "String"),
            @ApiImplicitParam(name = "orderInfo.orderGoodsList.goodsId", value = "商品id", dataType = "String"),
            @ApiImplicitParam(name = "orderInfo.orderGoodsList.goodsName", value = "商品名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "orderInfo.orderGoodsList.goodsPrice", value = "商品价格", required = true, dataType = "String"),
            @ApiImplicitParam(name = "orderInfo.orderGoodsList.goodsImage", value = "商品图片", required = true, dataType = "String"),
            @ApiImplicitParam(name = "orderInfo.orderGoodsList.goodsPayPrice", value = "商品实际成交价", required = true, dataType = "String"),
            @ApiImplicitParam(name = "orderInfo.orderGoodsList.goodsType", value = "1默认2限时折扣商品3组合套装4赠品5加价购活动商品6加价购换购商品", dataType = "String"),
            @ApiImplicitParam(name = "orderInfo.orderGoodsList.promotionsId", value = "促销活动ID（限时折扣ID/优惠套装ID）与goods_type搭配使用", dataType = "String"),
            @ApiImplicitParam(name = "orderInfo.orderGoodsList.gcId", value = "分类id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "orderInfo.orderGoodsList.goodsSpec", value = "商品规格", required = true, dataType = "String"),
            @ApiImplicitParam(name = "orderInfo.orderGoodsList.erpLeastsaleqty", value = "erp最小销售数量", required = true, dataType = "int"),
            @ApiImplicitParam(name = "orderInfo.orderGoodsList.erpGoodsUnit", value = "erp基本单位", required = true, dataType = "String"),
            @ApiImplicitParam(name = "orderInfo.orderGoodsList.erpGoodsId", value = "erp商品id", required = true, dataType = "String")

    })
    public OrderInfoVo memberPayOrder(@RequestBody OrderInfoVo orderInfo, HttpServletRequest request) {
        orderInfo.setMemberId(this.accountHelper.getUserId());
        orderInfo.setIp(this.accountHelper.getIP(request));
        //orderInfo.setOpenid(this.accountHelper.getOriginOpenid());
        return this.orderInfoService.memberPayOrder(orderInfo);
    }

    @ApiOperation("是否加单")
    @ApiImplicitParams({
    })
    @GetMapping("addOrderToDay")
    public Integer addOrderToDay() {
        return this.orderInfoService.addOrderToDay(this.accountHelper.getUserId());
    }

    @ApiOperation("支付订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "orderFrom", value = "订单来源", required = true, dataType = "Long"),
    })
    @GetMapping("memberRePayOrder")
    public PayOrder memberRePayOrder(Long orderId, HttpServletRequest request, Long orderFrom,String paymentType) {
        if (orderFrom == 1) {
            return this.orderInfoService.memberRePayOrder(orderId, this.accountHelper.getIP(request), null, orderFrom,paymentType);
        } else if (orderFrom == 2) {
            return this.orderInfoService.memberRePayOrder(orderId, this.accountHelper.getIP(request), this.accountHelper.getOriginOpenid(), orderFrom,paymentType);
        }
        return null;
    }


    @ApiOperation("我的订单")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getMyOrderInfoList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "deleteState", value = "默认传0，我的回收站传1", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "orderState", value = "状态,多个逗号隔开", dataType = "String", example = " 'UNPAID','DONE'   "),
            @ApiImplicitParam(name = "orderNo", value = "订单号", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    public IPage<OrderInfoVo> getMyOrderInfoList(Page page, OrderInfoVo orderInfoVo) {
        orderInfoVo.setMemberId(this.accountHelper.getUserId());
        return this.orderInfoService.getMyOrderInfoList(page, orderInfoVo);
    }

    @ApiOperation("订单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
    })
    @GetMapping("getOrderInfoById")
    public OrderInfoVo getOrderInfoById(Long id) {
        return this.orderInfoService.getOrderInfoById(id);
    }


    @ApiOperation("删除我的订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "deleteState", value = "删除状态0未删除1放入回收站2彻底删除", required = true, dataType = "Integer"),
    })
    @GetMapping("delMyOrderInfo")
    public void delMyOrderInfo(Long id, Integer deleteState) {
        this.orderInfoService.delMyOrderInfo(id, deleteState);
    }


    @ApiOperation("我的常购清单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
    })
    @GetMapping("getMyOrderGoods")
    public IPage<OrderGoodsVo> getMyOrderGoods(Page page) {
        return this.orderInfoService.getMyOrderGoods(this.accountHelper.getUserId(), page);
    }

    @ApiOperation("我的订单不分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderState", value = "订单状态", required = true, dataType = "Integer"),
    })
    @GetMapping("getOrderInfoVoAll")
    public List<OrderInfoVo> getOrderInfoVoAll(String orderState) {
        return this.orderInfoService.getOrderInfoVoAll(this.accountHelper.getUserId(), orderState);
    }


    @ApiOperation("取消订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
    })
    @GetMapping("cancelOrder")
    public void cancelOrder(Long id) {
        this.orderInfoService.cancelOrder(id);
    }

    @ApiOperation("退货原因列表")
    @GetMapping("getReasonList")
    public List<Reason> getReasonList() {
        return this.reasonService.getReasonList();
    }


    @ApiOperation("申请退货")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @PostMapping(value = "applyRefundReturn")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodsId", value = "商品id，0为全部退款,其他1", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "orderGoodsId", value = "订单商品ID,全部退款是0，其他1", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "picInfo", value = "图片", dataType = "String"),
            @ApiImplicitParam(name = "memberMessage", value = "申请原因", required = true, dataType = "String"),
            @ApiImplicitParam(name = "shipTime", value = "发货时间", dataType = "String"),
            @ApiImplicitParam(name = "applyType", value = "申请类型:1为退款,2为退货,默认为1", required = true, dataType = "String"),
            @ApiImplicitParam(name = "returnType", value = "退货类型:1为不用退货,2为需要退货,默认为1", required = true, dataType = "String"),
            @ApiImplicitParam(name = "refundReturnVo.refundDetailList.lotNo", value = "批号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "refundReturnVo.refundDetailList.goodsNum", value = "数量", required = true, dataType = "String"),
            @ApiImplicitParam(name = "refundReturnVo.refundDetailList.goodsId", value = "商品id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "refundReturnVo.refundDetailList.orderGoodsId", value = "商品id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "refundReturnVo.refundDetailList.goodsName", value = "商品名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "refundReturnVo.refundDetailList.goodsImage", value = "商品图片", required = true, dataType = "String"),
            @ApiImplicitParam(name = "refundReturnVo.refundDetailList.refundAmount", value = "退款金额", required = true, dataType = "String"),
            @ApiImplicitParam(name = "refundReturnVo.refundDetailList.lotId", value = "批号ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "refundReturnVo.refundDetailList.batchId", value = "批次ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "refundReturnVo.refundDetailList.srcErpOrderId", value = "erp销售总单ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "refundReturnVo.refundDetailList.srcErpOrderDtlId", value = "erp销售细单ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "refundReturnVo.refundDetailList.goodsSource", value = "erp单据类型ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "refundReturnVo.refundDetailList.reasonId", value = "原因id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "refundReturnVo.refundDetailList.reasonInfo", value = "原因内容", required = true, dataType = "String"),
    })
    public void applyRefundReturn(@RequestBody RefundReturnVo refundReturnVo) {
        refundReturnVo.setMemberId(this.accountHelper.getUserId());
        this.orderInfoService.applyRefundReturn(refundReturnVo);
    }

    @ApiOperation("我的退货单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
            @ApiImplicitParam(name = "keyword", value = "关键字搜索", dataType = "String"),
            @ApiImplicitParam(name = "refundState", value = "状态", dataType = "String"),
    })
    @GetMapping("getMyRefundReturnVoList")
    public IPage<RefundReturnVo> getMyRefundReturnVoList(Page page, RefundReturnVo refundReturnVo) {
        refundReturnVo.setMemberId(this.accountHelper.getUserId());
        return this.orderInfoService.getMyRefundReturnVoList(page, refundReturnVo);
    }

    @ApiOperation("获取系统设置订单可取消时间")
    @ApiImplicitParams({
    })
    @GetMapping("getOrderCancelTime")
    public Long getOrderCancelTime() {
        return this.settingService.getOrderCancelTime();
    }


    @ApiOperation("根据客户id查询客户的起送金额")
    @ApiImplicitParams({
    })
    @GetMapping("getDeliveryAmountByCustomerId")
    public Long getDeliveryAmountByCustomerId() {
        return this.deliveryAmountService.getDeliveryAmountByCustomerId(this.accountHelper.getUserId());
    }

    @ApiOperation("确认收货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
    })
    @GetMapping("sureOrderToDelivery")
    public void sureOrderToDelivery(Long id) {
        this.orderInfoService.sureOrderToDelivery(id);
    }


    @ApiOperation("再次购买")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "memberId", value = "memberId", required = true, dataType = "Long"),
    })
    @GetMapping("getTwoBuyGoodsInfoVo")
    public List<OrderGoodsVo> getTwoBuyGoodsInfoVo(Long orderId, Long memberId,HttpServletRequest request) {
        return this.orderInfoService.getTwoBuyGoodsInfoVo(orderId, memberId != null ? memberId : this.accountHelper.getUserId(),this.accountHelper.getIP(request));
    }

    @ApiOperation("下单赠送的优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId", required = true, dataType = "Long"),
    })
    @GetMapping("getCouponByOrder")
    public List<CouponReceiveVo> getCouponByOrder(Long orderId) {
        return this.orderInfoService.getCouponByOrder(orderId);
    }


    @ApiOperation("购物车商品可使用的优惠券")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "erpGoodsId", value = "商品id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodsNum", value = "数量", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "storageId", value = "保管账id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "type", value = "优惠券类型", dataType = "String"),
            @ApiImplicitParam(name = "lotId", value = "批号id", dataType = "Long"),
    })
    @PostMapping("getByCartCoupon")
    public List<CouponVo> getByCartCoupon(@RequestBody List<Cart> cartList, String type) {
        return this.orderInfoService.getByCartCoupon(this.accountHelper.getUserId(), cartList, type);
    }

    @ApiOperation("购物车商品可使用的优惠券1")
    @ApiImplicitParams({
    })
    @PostMapping("getByCartCoupon1")
    public List<CouponVo> getByCartCoupon1() {
        return this.orderInfoService.getByCartCoupon1(this.accountHelper.getUserId());
    }

    @ApiOperation("购物车计算")
    @ApiImplicitParams({
    })
    @PostMapping("getCartToActivity")
    public Map<Long, List<CartVo>> getCartToActivity() {

        /*0是未选中的正常商品
        1是选中的商品
        5是选中的特价商品
        -5是未选中的特价商品
         9是选中的特价商品
         -9是未选中的特价商品
        其他的是选中的活动商品*/

        Map<Long, List<CartVo>> map = this.orderInfoService.getCartToActivity(this.accountHelper.getUserId(), 0L);
     /*   List<CartVo> cartVoList = this.orderInfoService.getCartToPitchOff(this.accountHelper.getUserId());
        if (null != cartVoList && cartVoList.size() > 0) {
            map.put(0L, cartVoList);
        }
        List<CartVo> cartVoList5 = this.orderInfoService.getCartToPitchOff5(this.accountHelper.getUserId());
        if (null != cartVoList5 && cartVoList5.size() > 0) {
            map.put(-5L, cartVoList5);
        }
        List<CartVo> cartVoList9 = this.orderInfoService.getCartToPitchOff9(this.accountHelper.getUserId());
        if (null != cartVoList9 && cartVoList9.size() > 0) {
            map.put(-9L, cartVoList9);
        }*/

        return map;
    }


    @ApiOperation("修改购物车临时表赠品数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodsNum", value = "数量", required = true, dataType = "Long"),
    })
    @PostMapping("updateCartGiftTmp")
    public void updateCartGiftTmp(@RequestBody CartGiftTmp cartGiftTmp) {
        this.orderInfoService.updateCartGiftTmp(cartGiftTmp);
    }

    @ApiOperation("快速下单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "memberPhone", value = "客户手机", dataType = "String"),
            @ApiImplicitParam(name = "memberId", value = "客户ID", dataType = "Long"),
            @ApiImplicitParam(name = "memberName", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "applyNo", value = "退单号", dataType = "String"),
            @ApiImplicitParam(name = "orderNo", value = "原单号", dataType = "String"),
    })
    @GetMapping("getFastOrderInfo")
    public IPage<OrderInfoVo> getFastOrderInfo(Page page, OrderInfoVo orderInfoVo) {
        orderInfoVo.setMemberId(this.accountHelper.getUserId());
        return this.orderInfoService.getFastOrderInfo(page, orderInfoVo);
    }


    @ApiOperation("我的药检报告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "memberPhone", value = "客户手机", dataType = "String"),
            @ApiImplicitParam(name = "memberId", value = "客户ID", dataType = "Long"),
            @ApiImplicitParam(name = "memberName", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "applyNo", value = "退单号", dataType = "String"),
            @ApiImplicitParam(name = "orderNo", value = "原单号", dataType = "String"),
    })
    @GetMapping("getMyGoodsReportList")
    public IPage<OrderGoodsVo> getMyGoodsReportList(Page page, OrderGoodsVo orderGoodsVo) {
        orderGoodsVo.setMemberId(this.accountHelper.getUserId());
        return this.orderInfoService.getMyGoodsReport(page, orderGoodsVo);
    }

    @ApiOperation("一键下载药检报告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId", required = true, dataType = "Long"),
    })
    @GetMapping("getOneGoodsReport")
    public String getOneGoodsReport(Page page, OrderGoodsVo orderGoodsVo) {
        orderGoodsVo.setMemberId(this.accountHelper.getUserId());
        return this.orderInfoService.getOneGoodsReport(page, orderGoodsVo);
    }

    @ApiOperation("客户在24小时的订单量")
    @ApiImplicitParams({
    })
    @GetMapping("get24MemberOrderInfo")
    public Integer get24MemberOrderInfo() {
        return this.orderInfoService.get24MemberOrderInfo(this.accountHelper.getUserId());
    }

    @ApiOperation("申请退款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    @GetMapping("applyRefund")
    public void applyRefund(Long orderId, String remark) {
        this.orderInfoService.applyRefund(orderId, remark);
    }

    @ApiOperation("在线支付开关")
    @GetMapping("getOpenOrderPay")
    public String getOpenOrderPay() {
        return this.settingService.getOneOrderPay("ONLINE_PAY");
    }


    @ApiOperation("发票信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId", required = true, dataType = "Long"),
    })
    @GetMapping("getOrderInfoFPInfo")
    public String getOrderInfoFPInfo(Long orderId) {
        return this.orderInfoService.getOrderInfoFPInfo(orderId);
    }

    @ApiOperation("开票地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId", required = true, dataType = "Long"),
    })
    @GetMapping("getErpKpAddressInfo")
    public FapiaoAddressVo getErpKpAddressInfo(Long orderId) {
        return this.orderInfoService.getErpKpAddressInfo(orderId);
    }

    @ApiOperation("退货订单详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId", required = true, dataType = "Long"),
    })
    @GetMapping("getRefundDetailByOrderIdList")
    public RefundReturnVo getRefundDetailByOrderIdList(Long orderId) {
        return this.refundReturnService.getRefundDetailByOrderIdList(orderId);
    }
}
