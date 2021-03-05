package com.zsyc.zt.inca.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsyc.zt.inca.vo.others.B2bFinancialStatementVo;

import java.util.List;

public interface B2bFinancialStatementService {

    /**
     * 查询报表信息
     * @param page
     * @param b2bFinancialStatementVo
     * @return
     */
    IPage<B2bFinancialStatementVo> getB2bStatementList(Page<B2bFinancialStatementVo> page, B2bFinancialStatementVo b2bFinancialStatementVo);

    /**
     * 获取b2b财务对账单数据   fenye
     * @param b2bFinancialStatementVo
     * @return
     */
    IPage<B2bFinancialStatementVo> selectExportStatementDataPage(IPage page, B2bFinancialStatementVo b2bFinancialStatementVo);
}
