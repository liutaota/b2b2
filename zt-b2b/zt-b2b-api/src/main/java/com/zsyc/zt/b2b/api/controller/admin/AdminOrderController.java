package com.zsyc.zt.b2b.api.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.pay.entity.PayOrder;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.service.*;
import com.zsyc.zt.b2b.vo.*;
import io.swagger.annotations.*;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Api
@RestController
@RequestMapping("api/order")
public class AdminOrderController {
    @Reference
    private OrderInfoService orderInfoService;
    @Reference
    private RefundReturnService refundReturnService;
    @Autowired
    private AccountHelper accountHelper;
    @Reference
    private ReasonService reasonService;
    @Reference
    private AdminService adminService;
    @Reference
    private DayBillService dayBillService;
    @Reference
    private DeliveryAmountService deliveryAmountService;

    @ApiOperation("后台订单列表")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getAdminOrderInfoList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "orderState", value = "状态,多个逗号隔开", dataType = "String"),
            @ApiImplicitParam(name = "orderNo", value = "订单号", dataType = "String"),
            @ApiImplicitParam(name = "memberName", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "memberPhone", value = "手机号", dataType = "String"),
            @ApiImplicitParam(name = "erpCustomerId", value = "erp客户id", dataType = "Long"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    public IPage<OrderInfoVo> getAdminOrderInfoList(Page page, OrderInfoVo orderInfo) {
        return this.orderInfoService.getAdminOrderInfoList(page, orderInfo);
    }

    @ApiOperation("后台订单详情")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @ApiImplicitParam(name = "id", value = "订单ID", required = true, dataType = "Long")
    @GetMapping("getOrderInfoById")
    public OrderInfoVo getOrderInfoById(Long id) {
        return this.orderInfoService.getOrderInfoById(id);
    }

    @ApiOperation("退货原因列表")
    @GetMapping("getReasonList")
    public List<Reason> getReasonList() {
        return this.reasonService.getReasonList();
    }

    @ApiOperation("新增退货原因")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reasonInfo", value = "退货原因", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "排序", required = true, dataType = "Integer")
    })
    @PostMapping("addReason")
    public void addReason(@RequestBody Reason reason, HttpServletRequest request) {
        this.reasonService.addReason(reason, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("新增退货原因-->退货原因：" + reason.getReasonInfo()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminOrderController&addReason"));
    }

    @ApiOperation("编辑退货原因")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reasonInfo", value = "退货原因", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sort", value = "排序", required = true, dataType = "Integer")
    })
    @PostMapping("updateReason")
    public void updateReason(@RequestBody Reason reason, HttpServletRequest request) {
        this.reasonService.updateReason(reason, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("编辑退货原因-->id：" + reason.getId()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminOrderController&updateReason"));
    }

    @ApiOperation("退货原因删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "退货原因ID", required = true, dataType = "Long")
    })
    @GetMapping("updateReasonIsDel")
    public void updateReasonIsDel(Long id, HttpServletRequest request) {
        this.reasonService.updateReasonIsDel(id, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("退货原因删除-->id：" + id).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminOrderController&updateReasonIsDel"));
    }


    @ApiOperation("订单重新下发")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
    })
    @GetMapping("reErpOrder")
    public void reErpOrder(Long id, HttpServletRequest request) {
        this.orderInfoService.reErpOrder(id);
        this.adminService.addAdminLog(new AdminLog().setContent("订单重新下发：ID-->" + id).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminOrderController&reErpOrder"));

    }


    @ApiOperation("异常订单")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getOrderInfoExceptionList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "orderNo", value = "订单号", dataType = "String"),
            @ApiImplicitParam(name = "memberName", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "memberPhone", value = "手机号", dataType = "String"),
            @ApiImplicitParam(name = "erpCustomerId", value = "erp客户id", dataType = "Long"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    public IPage<OrderInfoVo> getOrderInfoExceptionList(Page page, OrderInfoVo orderInfoVo) {
        return this.orderInfoService.getOrderInfoExceptionList(page, orderInfoVo);
    }

    @ApiOperation("订单日志")
    @ApiImplicitParam(name = "orderId", value = "订单id", required = true, dataType = "Long")
    @GetMapping("getOrderLogList")
    public List<OrderLog> getOrderLogList(Long orderId) {
        return this.orderInfoService.getOrderLogList(orderId);
    }

    @ApiOperation("订单日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
    })
    @GetMapping("getOrderLogListPage")
    public IPage<OrderLog> getOrderLogListPage(Page page) {
        return this.orderInfoService.getOrderLogListPage(page);
    }


    @ApiOperation("退货订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "memberPhone", value = "客户手机", dataType = "String"),
            @ApiImplicitParam(name = "memberId", value = "客户ID", dataType = "Long"),
            @ApiImplicitParam(name = "memberName", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "applyNo", value = "退单号", dataType = "String"),
            @ApiImplicitParam(name = "orderNo", value = "原单号", dataType = "String"),
    })
    @GetMapping("getRefundReturnList")
    public IPage<RefundReturnVo> getRefundReturnList(Page page, RefundReturnVo refundReturnVo) {
        return this.refundReturnService.getRefundReturnList(page, refundReturnVo);
    }

    @ApiOperation("退货订单详情")
    @ApiImplicitParam(name = "id", value = "退货单ID", required = true, dataType = "Long")
    @GetMapping("getRefundDetail")
    public List<RefundDetailVo> getRefundDetailList(Long id) {
        return this.refundReturnService.getRefundDetailList(id);
    }

    @ApiOperation("审核退货订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "退货单ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "userMessage", value = "审核意见", required = true, dataType = "String")
    })
    @GetMapping("checkRefundReturn")
    public void checkRefundReturn(Long id, String userMessage, HttpServletRequest request,String refundState) {
        this.refundReturnService.checkRefundReturn(id, userMessage,refundState);
        this.adminService.addAdminLog(new AdminLog().setContent("审核退货订单：退货单ID-->" + id).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminOrderController&updateRefundReturnReturnType"));

    }

    @ApiOperation("退货订单状态更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "退货单ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "refundState", value = "退货单状态", required = true, dataType = "String")
    })
    @GetMapping("updateRefundReturnReturnType")
    public void updateRefundReturnReturnType(Long id, String refundState, HttpServletRequest request) {
        this.adminService.addAdminLog(new AdminLog().setContent("退货订单状态更新：退货单ID-->" + id).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminOrderController&updateRefundReturnReturnType"));

        this.refundReturnService.updateRefundReturnReturnType(id, refundState);
    }

    @ApiOperation("重新下发退货单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "退货单ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "reErpRemark", value = "备注", required = true, dataType = "String")
    })
    @GetMapping("reRefundOrderErp")
    public void reRefundOrderErp(Long id, String reErpRemark, HttpServletRequest request) {
        this.adminService.addAdminLog(new AdminLog().setContent("重新下发退货单：退货单ID-->" + id).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminOrderController&reRefundOrderErp"));

        this.refundReturnService.reRefundOrderErp(id, reErpRemark, this.accountHelper.getUserId());
    }

    @ApiOperation("修改订单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "orderState", value = "订单状态", required = true, dataType = "String")
    })
    @GetMapping("updateAdminOrderState")
    public void updateAdminOrderState(Long id, String orderState, HttpServletRequest request) {
        this.orderInfoService.updateAdminOrderState(id, orderState, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("后台修改id：" + id + "订单状态").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminOrderController&updateAdminOrderState"));
    }

    @ApiOperation("今日b2b订单量")
    @GetMapping("getB2BOrderNum")
    public Integer getB2BOrderNum() {
        return this.orderInfoService.getB2BOrderNum();
    }

    @ApiOperation("获取当天待拦截订单)")
    @GetMapping("getInterceptOrderList")
    public List<OrderInfo> getInterceptOrderList() {
        return this.orderInfoService.getInterceptOrderList();
    }


    @ApiOperation("后台删除客户订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "deleteState", value = "删除状态，值传2", required = true, dataType = "Integer")
    })
    @GetMapping("adminDelOrderInfo")
    public void adminDelOrderInfo(Long id, Integer deleteState, HttpServletRequest request) {
        this.orderInfoService.delMyOrderInfo(id, deleteState);
        this.adminService.addAdminLog(new AdminLog().setContent("后台删除客户订单-->id：" + id).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminOrderController&adminDelOrderInfo"));

    }

    @ApiOperation("恢复删除的订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
    })
    @GetMapping("recoverDelMyOrderInfo")
    public void recoverDelMyOrderInfo(Long id, HttpServletRequest request) {
        this.orderInfoService.recoverDelMyOrderInfo(id);
        this.adminService.addAdminLog(new AdminLog().setContent("恢复删除的订单-->id：" + id).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminOrderController&recoverDelMyOrderInfo"));

    }


    @ApiOperation("确认异常订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "userRemark", value = "备注", required = true, dataType = "String")
    })
    @GetMapping("isTrueExpOrder")
    public void isTrueExpOrder(Long[] ids, String userRemark, HttpServletRequest request) {
        this.orderInfoService.isTrueExpOrder(ids, userRemark,this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("确认异常订单-->id：" + ids).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminOrderController&isTrueExpOrder"));

    }

    @ApiOperation("购物车计算")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberId", value = "memberId", required = true, dataType = "Long"),
    })
    @GetMapping("getAdminCartToActivity")
    public Map<Long, List<CartVo>> getCartToActivity(Long memberId) {
        Map<Long, List<CartVo>> map = this.orderInfoService.getCartToActivity(memberId, 0L);
        return map;
    }

    @ApiOperation("后台下单")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @PostMapping(value = "adminMemberPayOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberId", value = "客户id", required = true, dataType = "String"),
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
    public OrderInfoVo adminMemberPayOrder(@RequestBody OrderInfoVo orderInfo, HttpServletRequest request) {
        orderInfo.setIp(this.accountHelper.getIP(request));
        //orderInfo.setOpenid(this.accountHelper.getOriginOpenid());
        orderInfo.setAdminUserId(this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("后台下单：").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminOrderController&adminMemberPayOrder"));
        return this.orderInfoService.memberPayOrder(orderInfo);
    }

    @ApiOperation("app订单收款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "orderFrom", value = "订单来源", required = true, dataType = "Long"),
    })
    @GetMapping("appSentPayOrder")
    public PayOrder appSentPayOrder(Long orderId, Long orderFrom, HttpServletRequest request, String paymentType) {
        return this.orderInfoService.appSentPayOrder(orderId, this.accountHelper.getIP(request), this.accountHelper.getUserId(), orderFrom, paymentType);
    }

    @ApiOperation("购物车商品可使用的优惠券1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberId", value = "memberId", required = true, dataType = "Long"),
    })
    @GetMapping("getAdminByCartCoupon1")
    public List<CouponVo> getByCartCoupon1(Long memberId) {
        return this.orderInfoService.getByCartCoupon1(memberId);
    }

    @ApiOperation("订单的账单明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId", required = true, dataType = "Long"),
    })
    @GetMapping("getRecDocVoByOrderId")
    public List<RecDoc> getRecDocVoByOrderId(Long orderId) {
        return this.orderInfoService.getRecDocVoByOrderId(orderId);
    }

    @ApiOperation("拦截订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "id", value = "id", dataType = "Long"),
            @ApiImplicitParam(name = "interceptStatus", value = "interceptStatus", required = true, dataType = "String"),
    })
    @GetMapping("getOrderInfoInterceptList")
    public IPage<OrderInfoVo> getOrderInfoInterceptList(Page page, OrderInfoVo orderInfoVo) {
        return this.orderInfoService.getOrderInfoInterceptList(page, orderInfoVo);

    }

    @ApiOperation("拦截订单状态变更")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "interceptStatus", value = "状态", required = true, dataType = "String"),
            @ApiImplicitParam(name = "interceptRemark", value = "备注", dataType = "String"),
    })
    @GetMapping("updateOrderInfoVoIntercept")
    public void updateOrderInfoVoIntercept(Long id, String interceptRemark, String interceptStatus, HttpServletRequest request) {
        this.orderInfoService.updateOrderInfoVoIntercept(id, interceptRemark, interceptStatus);
        this.adminService.addAdminLog(new AdminLog().setContent("拦截订单状态变更：").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminOrderController&updateOrderInfoVoIntercept"));

    }

    @ApiOperation("手工拦单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "b2bOrderNo", value = "b2bOrderNo", required = true, dataType = "String"),
            @ApiImplicitParam(name = "erpOrderNo", value = "erpOrderNo", required = true, dataType = "String"),
    })
    @PostMapping("addManuallyIntercept")
    public void addManuallyIntercept(@RequestBody ManuallyIntercept manuallyIntercept, HttpServletRequest request) {
        this.orderInfoService.addManuallyIntercept(manuallyIntercept);
        this.adminService.addAdminLog(new AdminLog().setContent("手工拦单：").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminOrderController&addManuallyIntercept"));

    }

    @ApiOperation("手工拦截订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "status", value = "status", required = true, dataType = "String"),
    })
    @GetMapping("getManuallyInterceptList")
    public IPage<ManuallyIntercept> getManuallyInterceptList(Page page, ManuallyIntercept manuallyIntercept) {
        return this.orderInfoService.getManuallyInterceptList(page, manuallyIntercept);
    }

    @ApiOperation("手工拦截订单状态变更")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "b2bOrderNo", value = "b2bOrderNo", required = true, dataType = "String"),
            @ApiImplicitParam(name = "erpOrderNo", value = "erpOrderNo", required = true, dataType = "String"),
    })
    @PostMapping("updateManuallyIntercept")
    public void updateManuallyIntercept(@RequestBody ManuallyIntercept manuallyIntercept, HttpServletRequest request) {
        this.orderInfoService.updateManuallyIntercept(manuallyIntercept);
        this.adminService.addAdminLog(new AdminLog().setContent("手工拦单：").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminOrderController&updateManuallyIntercept"));

    }

    @ApiOperation("每日报表列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
    })
    @GetMapping("getDayBillList")
    public IPage<DayBill> getDayBillList(Page page, DayBill dayBill) {
        return this.dayBillService.getDayBillList(page, dayBill);
    }

    @ApiOperation("修改核销状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "financeTrue", value = "1/2", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "remark", value = "备注", required = true, dataType = "String"),
    })
    @GetMapping("reFinanceOrder")
    public void reFinanceOrder(Long orderId, Long financeTrue, String remark) {
        this.orderInfoService.reFinanceOrder(orderId, financeTrue, remark);
    }

    @ApiOperation("确认送货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
    })
    @GetMapping("sureSenTTime")
    public void sureSenTTime(Long id) {
        this.orderInfoService.sureSenTTime(id);
    }

    @ApiOperation("根据客户id查询客户的起送金额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberId", value = "memberId", required = true, dataType = "Long"),
    })
    @GetMapping("getDeliveryAmountByMemberId")
    public Long getDeliveryAmountByMemberId(Long memberId) {
        return this.deliveryAmountService.getDeliveryAmountByCustomerId(memberId);
    }

    @ApiOperation("统计订单数据")
    @ApiImplicitParams({
    })
    @GetMapping("getOrderInfoDate")
    public OrderInfoVo getOrderInfoDate() {
        return this.orderInfoService.getOrderInfoDate();
    }


    @ApiOperation("整单不出生成退款单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    @GetMapping("noOrderAddRecOrder")
    public RecDoc noOrderAddRecOrder(Long id, String noOrderRemark, HttpServletRequest request) {
        this.adminService.addAdminLog(new AdminLog().setContent("整单不出生成退款单：id->" + id).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminOrderController&noOrderAddRecOrder"));
        return this.orderInfoService.noOrderAddRecOrder(id, noOrderRemark, this.accountHelper.getUserId());
    }

    @ApiOperation("确认收货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "receiveMessage", value = "备注", dataType = "String"),
    })
    @GetMapping("sureReceive")
    public void sureReceive(Long id, String receiveMessage, HttpServletRequest request) {
        this.adminService.addAdminLog(new AdminLog().setContent("确认收货：id->" + id).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminOrderController&sureReceive"));
        this.refundReturnService.sureReceive(id, receiveMessage, this.accountHelper.getUserId());
    }

    @ApiOperation("兑换积分商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
    })
    @GetMapping("getOrderGoodsBy9")
    public IPage<OrderGoodsVo> getOrderGoodsBy9(Page page, OrderGoodsVo orderGoods) {
        return this.orderInfoService.getOrderGoodsBy9(page, orderGoods);
    }

    @ApiOperation("根据线序查客户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "translinename", value = "线路", required = true, dataType = "Long"),
    })
    @GetMapping("getOrderInfoVoByTranslinename")
    public IPage<OrderInfoVo> getOrderInfoVoByTranslinename(Page page, OrderInfoVo orderInfoVo) {
        return this.orderInfoService.getOrderInfoVoByTranslinename(page, orderInfoVo);
    }

    @ApiOperation("根据线序查客户订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberId", value = "memberId", required = true, dataType = "Long"),
    })
    @GetMapping("getOrderInfoVoByTranslinenameList")
    public List<OrderInfoVo> getOrderInfoVoByTranslinenameList(Long memberId) {
        return this.orderInfoService.getOrderInfoVoByTranslinenameList(memberId);
    }

    @ApiOperation("erp未付款订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "erpUserId", value = "erpUserId", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "transDocId", value = "路单id", required = true, dataType = "Long"),
    })
    @GetMapping("getErpOrderInfoByMemberId")
    public List<ErpOrderInfo> getErpOrderInfoByMemberId(ErpOrderInfo erpOrderInfo) {
        return this.orderInfoService.getErpOrderInfoByMemberId(erpOrderInfo);
    }
}
