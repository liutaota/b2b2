package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.BmsCreditBillDoc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-14
 */
public interface BmsCreditBillDocMapper extends BaseMapper<BmsCreditBillDoc> {
    /**
     * 获取欠款流水表seq号
     * @return
     */
    Long getBmsCreditBillDocSeq();
}
