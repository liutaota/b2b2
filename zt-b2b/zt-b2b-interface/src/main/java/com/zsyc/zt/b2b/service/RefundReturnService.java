package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.RefundDetail;
import com.zsyc.zt.b2b.vo.RefundDetailVo;
import com.zsyc.zt.b2b.vo.RefundReturnVo;

import java.util.List;

public interface RefundReturnService {
    /**
     * 退货订单列表
     *
     * @param page
     * @param refundReturnVo
     * @return
     */
    IPage<RefundReturnVo> getRefundReturnList(Page page, RefundReturnVo refundReturnVo);

    /**
     * 退货订单详情
     *
     * @param id
     * @return
     */
    List<RefundDetailVo> getRefundDetailList(Long id);

    /**
     * 审核退货订单
     *
     * @param id
     * @param userMessage
     */
    void checkRefundReturn(Long id, String userMessage,String refundState);

    /**
     * 退货单状态更新
     *
     * @param id
     * @param refundState
     */
    void updateRefundReturnReturnType(Long id, String refundState);

    /**
     * 一分钟处理一个申请退货请求
     */
    void dealWithApplyRefundOrder();

    /**
     * 重新下发退货单
     *
     * @param id
     */
    void reRefundOrderErp(Long id,String remark,Long userId);

    /**
     * 确认收货
     * @param id
     * @param receiveMessage
     * @param userId
     */
    void sureReceive(Long id,String receiveMessage,Long userId);

    /**
     * 退货订单详情
     *
     * @param orderId
     * @return
     */
    RefundReturnVo getRefundDetailByOrderIdList(Long orderId);
}
