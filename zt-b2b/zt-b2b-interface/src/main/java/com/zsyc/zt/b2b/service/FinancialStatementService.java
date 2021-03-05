package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.vo.OrderInfoVo;

/**
 * 财务报表
 */
public interface FinancialStatementService {
    /**
     * 财务报表总计-明细
     *
     * @return
     */
    IPage<OrderInfoVo> getFinancialStatementTotalList(Page page, OrderInfoVo orderInfoVo);


    /**
     * 财务报表底表
     *
     * @return
     */
    IPage<OrderInfoVo> getFinancialStatementList(Page page, OrderInfoVo orderInfoVo);

}
