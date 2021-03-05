package com.zsyc.zt.b2b.mapper;

import com.zsyc.zt.b2b.entity.Reason;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 退货退款原因 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-30
 */
public interface ReasonMapper extends BaseMapper<Reason> {
    /**
     * 退货原因列表
     * @return
     */
    List<Reason> getReasonList();
}
