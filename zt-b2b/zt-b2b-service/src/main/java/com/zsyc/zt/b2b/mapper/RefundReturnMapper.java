package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.RefundReturn;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.RefundReturnVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 退货退款单 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-30
 */
public interface RefundReturnMapper extends BaseMapper<RefundReturn> {
    /**
     * 退货订单列表
     * @param page
     * @param refundReturnVo
     * @return
     */
    IPage<RefundReturnVo> getRefundReturnList(@Param("page") Page page, @Param("refundReturnVo") RefundReturnVo refundReturnVo);

    /**
     * 我的退货单列表
     *
     * @param page
     * @param refundReturnVo
     * @return
     */
    IPage<RefundReturnVo> getMyRefundReturnVoList(@Param("page") Page page, @Param("refundReturnVo") RefundReturnVo refundReturnVo);

    /**
     * 退货单详情
     * @param orderId
     * @return
     */
    RefundReturnVo getRefundReturnVoInfo(@Param("orderId") Long orderId);
}
