package com.zsyc.zt.b2b.mapper;

import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.ManuallyIntercept;
import com.zsyc.zt.b2b.entity.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单信息 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-30
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {
    /**
     * 我的订单
     * @param page
     * @return
     */
    IPage<OrderInfoVo> getMyOrderInfoList(@Param("page") Page page, @Param("orderInfoVo") OrderInfoVo orderInfoVo);

    /**
     * 订单详情
     * @param id
     * @return
     */
    OrderInfoVo getOrderInfoById(@Param("id") Long id);

    /**
     * 后台订单列表
     * @param page
     * @param orderInfoVo
     * @return
     */
    IPage<OrderInfoVo> getAdminOrderInfoList(@Param("page") Page page, @Param("orderInfoVo") OrderInfoVo orderInfoVo);

    /**
     * 后台订单列表导出
     * @param orderInfoVo
     * @return
     */
    List<OrderInfoVo> getAdminOrderInfoList(@Param("orderInfoVo") OrderInfoVo orderInfoVo);

    /**
     * 我的订单不分页
     * @return
     */
    @Cached(name = "getMyOrderInfoAll",key="#memberId+''+#orderState", expire = 10)
    List<OrderInfoVo> getMyOrderInfoAll(@Param("memberId") Long memberId, @Param("orderState") String orderState);

    /**
     * 异常订单
     *
     * @param page
     * @param orderInfoVo
     * @return
     */
    IPage<OrderInfoVo> getOrderInfoExceptionList(@Param("page") Page page, @Param("orderInfoVo") OrderInfoVo orderInfoVo);


    /**
     * 当前时间十分钟之前的订单
     * @return
     */
    List<OrderInfo> getNowOrderInfo(@Param("minutes") Long minutes);

    /**
     * 订单导出
     *
     * @param orderInfoVo
     * @return
     */
    List<OrderInfoVo> getOrderInfoOrderGoodsExcel(@Param("orderInfoVo") OrderInfoVo orderInfoVo);

    /**
     * 获取所有待收货订单的id
     * @return
     */
    List<Long> getOrderByToSend(@Param("page") Page page);

    List<Long> getOrderByToSend();

    /**
     * 根据商品查药检报告
     * @param goodsId
     * @return
     */
    String getGoodsInfoReport(@Param("goodsId") Long goodsId, @Param("lotno") String lotno);

    /**
     * 客户1分钟之前未取消的订单
     * @param customerId
     * @return
     */
    Integer getOrderInfoByCustomerId(@Param("customerId") Long customerId, @Param("time") Long time);

    /**
     * 客户最近一条订单
     * @param customerId
     * @return
     */
    OrderInfo getOrderInfoByCustomerIdInfo(@Param("customerId") Long customerId);

    /**
     * 7天自动确认收货
     * @return
     */
    List<OrderInfo> getAutoOrderToDelivery(@Param("minutes") Long minutes);

    /**
     * 快速下单
     * @param page
     * @param orderInfoVo
     * @return
     */
    IPage<OrderInfoVo> getFastOrderInfo(@Param("page") Page page, @Param("orderInfoVo") OrderInfoVo orderInfoVo);

    /**
     * 我的药检报告
     * @param page
     * @param orderGoodsVo
     * @return
     */
    IPage<OrderGoodsVo> getMyGoodsReport(@Param("page") Page page, @Param("orderGoodsVo") OrderGoodsVo orderGoodsVo);

    /**
     * 半小时自动取消未支付订单
     */
    List<OrderInfo> autoCancelOrder();

    /**
     * 确认收货订单
     */
    List<OrderInfoVo> autoFinanceOrder();

    /**
     * 订单确认收货短减、整单不出退款
     */
    List<OrderInfoVo> autoReturnOrder();

    /**
     * 看板采购入库未上架
     *
     * @return
     */
    IPage<TVDates> getTVGoodsOff(@Param("page") Page page);

    /**
     * 看板采购入库未上架统计
     *
     * @return
     */
    Integer getTVToGoodsTotal();

    /**
     * 看板采购入库未上架统计
     *
     * @return
     */
    Integer getTVGoodsTotal();

    /**
     * 看板当天销售单
     *
     * @return
     */
    IPage<TVDates> getTVToDaySales(@Param("page") Page page);

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
    IPage<OrderInfoVo> getOrderInfoInterceptList(@Param("page") Page page, @Param("orderInfoVo") OrderInfoVo orderInfoVo);

    /**
     * 拦截订单->erp
     *
     * @param page
     * @param orderInfoVo
     * @return
     */
    IPage<OrderInfoVo> getAdminErpOrderInfoInterceptList(@Param("page") Page page, @Param("orderInfoVo") OrderInfoVo orderInfoVo);

    /**
     * 客户在24小时的订单量
     * @param memberId
     * @return
     */
    Integer get24MemberOrderInfo(@Param("memberId") Long memberId);

    /**
     * 手工拦截订单
     *
     * @param page
     * @param manuallyIntercept
     * @return
     */
    IPage<ManuallyIntercept> getManuallyInterceptList(@Param("page") Page page, @Param("manuallyIntercept") ManuallyIntercept manuallyIntercept);


    /**
     *统计订单数据
     * @return
     */
    OrderInfoVo getOrderInfoDate();

    /**
     * 今天是否有满足300的订单
     * @param memberId
     * @return
     */
    Integer getAddOrderToDayAmount(@Param("memberId") Long memberId, @Param("amount") Long amount);

    /**
     * 是否加单
     * @param memberId
     * @return
     */
    Integer getAddOrderToDay(@Param("memberId") Long memberId, @Param("amount") Long amount);

    /**
     * 小计异常
     * @return
     */
    Integer getOrderGoodsAmountToday();

    /**
     * 总金额异常
     * @return
     */
    Integer getOrderInfoAmountToday();

    /**
     * 该客户是否参与商品限购
     * @return
     */
    Integer getOrderGoodsCountPurchase(@Param("memberId") Long memberId, @Param("purchaseId") Long purchaseId, @Param("erpGoodsId") Long erpGoodsId, @Param("timesStrategy") Integer timesStrategy);


    /**
     * 根据线序查客户订单
     *
     * @param page
     * @param orderInfoVo
     * @return
     */
    IPage<OrderInfoVo> getOrderInfoVoByTranslinename(@Param("page") Page page, @Param("orderInfoVo") OrderInfoVo orderInfoVo);

    /**
     * 根据线序查客户订单
     *
     * @param orderInfoVo
     * @return
     */
    List<OrderInfoVo> getOrderInfoVoByTranslinename(@Param("orderInfoVo") OrderInfoVo orderInfoVo);

    /**
     * erp订单号，细单
     * @param orderNo
     * @return
     */
    @Cached(name = "getErpOrderId",key="#orderNo", expire = 10)
    List<ErpOrderInfo> getErpOrderId(@Param("orderNo") String orderNo);

    /**
     * 客户待收货订单id
     * @param memberId
     * @return
     */
    List<Long> getOrderInfoByMemberId(@Param("memberId") Long memberId);

    /**
     * erp未付款订单
     * @return
     */
    List<ErpOrderInfo> getErpOrderInfoByMemberId(@Param("erpOrderInfo") ErpOrderInfo erpOrderInfo);

    /**
     * erp未付款订单
     * @return
     */
    ErpOrderInfo getErpOrderInfoBySalesid(@Param("erpUserId") Long erpUserId, @Param("salesid") Long salesid);


    /**
     * 财务报表总计-明细
     *
     * @return
     */
    IPage<OrderInfoVo> getFinancialStatementTotalList(@Param("page") Page page, @Param("orderInfoVo") OrderInfoVo orderInfoVo);

    /**
     * 财务报表总计-明细
     *
     * @return
     */
    List<OrderInfoVo> getFinancialStatementTotalList(@Param("orderInfoVo") OrderInfoVo orderInfoVo);

    /**
     * 财务报表底表
     *
     * @return
     */
    IPage<OrderInfoVo> getFinancialStatementList(@Param("page") Page page, @Param("orderInfoVo") OrderInfoVo orderInfoVo);



    /**
     * 财务报表底表
     *
     * @return
     */
    List<OrderInfoVo> getFinancialStatementList(@Param("orderInfoVo") OrderInfoVo orderInfoVo);
}
