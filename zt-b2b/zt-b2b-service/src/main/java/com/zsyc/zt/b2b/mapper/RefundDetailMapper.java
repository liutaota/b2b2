package com.zsyc.zt.b2b.mapper;

import com.zsyc.zt.b2b.entity.RefundDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.RefundDetailVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 退单详情 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-30
 */
public interface RefundDetailMapper extends BaseMapper<RefundDetail> {

    /**
     * 退货订单详情列表
     * @param id
     * @return
     */
    List<RefundDetailVo> getRefundDetailList(@Param("id") Long id);

}
