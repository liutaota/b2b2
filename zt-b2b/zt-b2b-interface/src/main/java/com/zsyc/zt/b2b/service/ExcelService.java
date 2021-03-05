package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.Member;
import com.zsyc.zt.b2b.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExcelService {
    /**
     * 订单明细表导出
     *
     * @param orderInfoVo
     * @return
     */
    String getOrderInfoOrderGoodsExcel(OrderInfoVo orderInfoVo);

    /**
     * 货品列表导出
     *
     * @param goodsInfoVo
     * @return
     */
    String getGoodsListExcel(GoodsInfoVo goodsInfoVo);

    /**
     * 缺货列表导出
     *
     * @param arrivalNoticeVo
     * @return
     */
    String getArrivalNoticeListExcel(ArrivalNoticeVo arrivalNoticeVo);

    /**
     * 新品列表导出
     *
     * @param newProductVo
     * @return
     */
    String getNewProductExcel(NewProductVo newProductVo);

    /**
     * 客户列表导出
     *
     * @param customerVo
     * @return
     */
    String getCustomerExcel(CustomerInfoVo customerVo);

    /**
     * APP收款单列表导出
     *
     * @param erpB2bOrderRecDocVo
     * @return
     */
    String getErpB2bOrderRecDocExcel(ErpB2bOrderRecDocVo erpB2bOrderRecDocVo);

    /**
     * 收款单列表导出
     *
     * @param recDocVo
     * @return
     */
    String getRecDocList(RecDocVo recDocVo);

    /**
     * 退款单列表导出
     *
     * @param recDocVo
     * @return
     */
    String getRefundRecDocList(RecDocVo recDocVo);


    /**
     * 财务报表总计-明细
     *
     * @return
     */
    String getFinancialStatementTotalList(OrderInfoVo orderInfoVo);

    /**
     * 财务报表底表
     *
     * @return
     */
    String getFinancialStatementList(OrderInfoVo orderInfoVo);


    /**
     * 订单列表导出
     *
     * @return
     */
    String getOrderInfoListExcel(OrderInfoVo orderInfoVo);

    /**
     * 送货列表导出
     *
     * @return
     */
    String getErpOrderInfoListExcel(OrderInfoVo orderInfoVo);

    /**
     * 支付流水导出
     * @return
     */
    String getPayFlowNoOrder(RecDocVo recDocVo);

}
