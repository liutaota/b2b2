package com.zsyc.zt.b2b.service;

import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.pay.entity.PayOrder;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.vo.*;
import com.zsyc.zt.inca.vo.FapiaoAddressVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderInfoService {
    /**
     * 客户下单
     *
     * @param orderInfo
     */
    OrderInfoVo memberPayOrder(OrderInfoVo orderInfo);

    /**
     * 是否加单
     *
     * @param memberId
     * @return
     */
    Integer addOrderToDay(Long memberId);

    /**
     * erp下单
     *
     * @param orderInfo
     */
    void erpOrder(OrderInfoVo orderInfo, String toErpNum);

    /**
     * 支付订单
     *
     * @param orderId
     */
    PayOrder memberRePayOrder(Long orderId, String ip, String openId, Long orderFrom,String paymentType);

    /**
     * app订单收款
     *
     * @param orderId
     */
    PayOrder appSentPayOrder(Long orderId, String ip, Long sentUserId, Long orderFrom,String paymentType);

    /**
     * 我的订单
     *
     * @param page
     * @return
     */
    IPage<OrderInfoVo> getMyOrderInfoList(Page page, OrderInfoVo orderInfoVo);

    /**
     * 订单详情
     *
     * @param id
     * @return
     */
    OrderInfoVo getOrderInfoById(Long id);

    /**
     * 删除我的订单
     *
     * @param id
     */
    void delMyOrderInfo(Long id, Integer deleteState);

    /**
     * 恢复删除的订单
     *
     * @param id
     */
    void recoverDelMyOrderInfo(Long id);

    /**
     * 后台订单列表
     *
     * @param page
     * @param orderInfo
     * @return
     */
    IPage<OrderInfoVo> getAdminOrderInfoList(Page page, OrderInfoVo orderInfo);

    /**
     * 我的常购清单
     *
     * @param memberId
     * @return
     */
    IPage<OrderGoodsVo> getMyOrderGoods(Long memberId, Page page);

    /**
     * 我的订单不分页
     *
     * @param memberId
     * @param orderState
     * @return
     */
    List<OrderInfoVo> getOrderInfoVoAll(Long memberId, String orderState);

    /**
     * 取消订单
     *
     * @param id
     */
    void cancelOrder(Long id);

    /**
     * 订单重新下发
     *
     * @param id
     */
    void reErpOrder(Long id);

    /**
     * 5分钟下发erp
     */
    void minutesToErpOrder();

    /**
     * 异常订单
     *
     * @param page
     * @param orderInfoVo
     * @return
     */
    IPage<OrderInfoVo> getOrderInfoExceptionList(Page page, OrderInfoVo orderInfoVo);

    /**
     * 申请退货
     *
     * @param refundReturnVo
     */
    void applyRefundReturn(RefundReturnVo refundReturnVo);

    /**
     * 我的退货单列表
     *
     * @param page
     * @param refundReturnVo
     * @return
     */
    IPage<RefundReturnVo> getMyRefundReturnVoList(Page page, RefundReturnVo refundReturnVo);

    /**
     * 整单不出生成退款单
     *
     * @param id
     */
    RecDoc noOrderAddRecOrder(Long id, String noOrderRemark, Long noOrderUserId);

    /**
     * 修改订单状态 --待收货
     *
     * @param
     */
    void updateOrderState();

    /**
     * 订单短减补货
     *
     * @param id
     */
    void getOrderInfoCpfr(Long id);

    /**
     * 订单日志
     *
     * @param orderId
     * @return
     */
    List<OrderLog> getOrderLogList(Long orderId);

    /**
     * 确认收货
     *
     * @param id
     */
    void sureOrderToDelivery(Long id);

    /**
     * erp确认收货
     */
    void sureOrderToDeliveryJob();

    /**
     * 修改订单状态
     *
     * @param id
     * @param orderState
     * @param userId
     */
    void updateAdminOrderState(Long id, String orderState, Long userId);

    /**
     * 今日b2b订单量
     *
     * @return
     */
    Integer getB2BOrderNum();

    /**
     * 获取当天待拦截订单
     *
     * @return
     */
    List<OrderInfo> getInterceptOrderList();

    /**
     * 再次购买
     *
     * @param orderId
     * @param memberId
     * @return
     */
    List<OrderGoodsVo> getTwoBuyGoodsInfoVo(Long orderId, Long memberId,String ip);

    /**
     * 下单赠送的优惠券
     *
     * @param orderId
     * @return
     */
    List<CouponReceiveVo> getCouponByOrder(Long orderId);

    /**
     * 购物车商品可使用的优惠券
     *
     * @param memberId
     * @param cartList
     * @return
     */
    List<CouponVo> getByCartCoupon(Long memberId, List<Cart> cartList, String type);

    /**
     * 购物车商品可使用的优惠券
     *
     * @param memberId
     * @return
     */
    List<CouponVo> getByCartCoupon1(Long memberId);

    /**
     * 购物车计算
     *
     * @return
     */
    Map<Long, List<CartVo>> getCartToActivity(Long memberId, Long pitchOn);

    /**
     * 下单
     *
     * @return
     */
    void getCartUseCoupon(OrderInfoVo orderInfoVo);

    /**
     * 未选中的购物车商品
     *
     * @return
     */
    //@Cached(name = "OrderInfoService-getCartToPitchOff-", key = "#memberId", expire = 1)
    List<CartVo> getCartToPitchOff(Long memberId);

    /**
     * 未选中的购物车特价商品
     *
     * @return
     */
    @Cached(name = "OrderInfoService-getCartToPitchOff5-", key = "#memberId", expire = 1)
    List<CartVo> getCartToPitchOff5(Long memberId);

    /**
     * 未选中的购物车积分商品
     *
     * @return
     */
    @Cached(name = "OrderInfoService-getCartToPitchOff9-", key = "#memberId", expire = 1)
    List<CartVo> getCartToPitchOff9(Long memberId);

    /**
     * 修改购物车临时表数量
     *
     * @param cartGiftTmp
     */
    void updateCartGiftTmp(CartGiftTmp cartGiftTmp);

    /**
     * 订单日志列表
     *
     * @param page
     * @return
     */
    IPage<OrderLog> getOrderLogListPage(Page page);

    /**
     * 7天自动确认收货
     */
    void autoOrderToDelivery();

    /**
     * 快速下单
     *
     * @param page
     * @param orderInfoVo
     * @return
     */
    IPage<OrderInfoVo> getFastOrderInfo(Page page, OrderInfoVo orderInfoVo);

    /**
     * 确认异常订单
     *
     * @param ids
     * @param userRemark
     */
    void isTrueExpOrder(Long[] ids, String userRemark,Long userId);

    /**
     * 我的药检报告
     *
     * @param page
     * @param orderGoodsVo
     * @return
     */
    IPage<OrderGoodsVo> getMyGoodsReport(Page page, OrderGoodsVo orderGoodsVo);

    /**
     * 一键下载药检报告
     *
     * @param orderGoodsVo
     * @return
     */
    String getOneGoodsReport(Page page, OrderGoodsVo orderGoodsVo);

    /**
     * 半小时自动取消未支付订单
     */
    void autoCancelOrder();

    /**
     * 订单确认收货核销
     */
    void autoFinanceOrder();

    /**
     * 订单确认收货短减、整单不出退款
     */
    void autoReturnOrder();

    /**
     * 订单的账单明细
     *
     * @param orderId
     * @return
     */
    List<RecDoc> getRecDocVoByOrderId(Long orderId);

    /**
     * 看板采购入库未上架
     *
     * @return
     */
    IPage<TVDates> getTVGoodsOff(Page page);

    /**
     * 看板当天销售单
     *
     * @return
     */
    IPage<TVDates> getTVToDaySales(Page page);

    /**
     * 看板当天销售订单量
     *
     * @return
     */
    TVDates getTVToDaySalesTotal();

    /**
     * 看板赠品采购入库未上架
     *
     * @return
     */
    IPage<TVDates> getTVGiftGoodsOff(@Param("page") Page page);

    /**
     * 拦截订单
     *
     * @param page
     * @param orderInfoVo
     * @return
     */
    IPage<OrderInfoVo> getOrderInfoInterceptList(Page page, OrderInfoVo orderInfoVo);

    /**
     * 拦截订单状态变更
     *
     * @param id
     * @param remark
     * @param interceptStatus
     */
    void updateOrderInfoVoIntercept(Long id, String remark, String interceptStatus);

    /**
     * 拦截订单->erp
     *
     * @param page
     * @param orderInfoVo
     * @return
     */
    IPage<OrderInfoVo> getAdminErpOrderInfoInterceptList(Page page, OrderInfoVo orderInfoVo);

    /**
     * 客户在24小时的订单量
     *
     * @param memberId
     * @return
     */
    Integer get24MemberOrderInfo(Long memberId);

    /**
     * 申请退款
     *
     * @param orderId
     * @param remark
     */
    void applyRefund(Long orderId, String remark);

    /**
     * 手工拦单
     *
     * @param manuallyIntercept
     */
    void addManuallyIntercept(ManuallyIntercept manuallyIntercept);

    /**
     * 手工拦截订单
     *
     * @param page
     * @param manuallyIntercept
     * @return
     */
    IPage<ManuallyIntercept> getManuallyInterceptList(Page page, ManuallyIntercept manuallyIntercept);

    /**
     * 手工拦截订单状态变更
     *
     * @param manuallyIntercept
     */
    void updateManuallyIntercept(ManuallyIntercept manuallyIntercept);

    /**
     *手动核销
     *
     * @param id
     */
    void reFinanceOrder(Long id, Long financeTrue,String remark);

    /**
     * 确认送货
     *
     * @param id
     */
    void sureSenTTime(Long id);

    /**
     * 统计订单数据
     *
     * @return
     */
    OrderInfoVo getOrderInfoDate();

    /**
     * 发票信息
     *
     * @param orderId
     * @return
     */
    String getOrderInfoFPInfo(Long orderId);

    /**
     * 金额异常
     */
    void toDayExpAmountOrder();

    /**
     * 开票地址
     *
     * @param orderId
     * @return
     */
    FapiaoAddressVo getErpKpAddressInfo(Long orderId);

    /**
     * 兑换积分商品
     *
     * @param page
     * @param orderGoods
     * @return
     */
    IPage<OrderGoodsVo> getOrderGoodsBy9(Page page, OrderGoodsVo orderGoods);

    /**
     * 根据线序查客户
     *
     * @param page
     * @param orderInfoVo
     * @return
     */
    IPage<OrderInfoVo> getOrderInfoVoByTranslinename(Page page, OrderInfoVo orderInfoVo);

    /**
     * 根据线序查客户订单
     *
     * @param memberId
     * @return
     */
    List<OrderInfoVo> getOrderInfoVoByTranslinenameList(Long memberId);

    /**
     * erp未付款订单
     * @return
     */
    List<ErpOrderInfo> getErpOrderInfoByMemberId(ErpOrderInfo erpOrderInfo);
}