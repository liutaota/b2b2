package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.inca.vo.others.B2bFinancialStatementVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
@Mapper
public interface B2bFinancialStatementMapper {

    /**
     * 查询报表信息
     * @return
     */
    IPage<B2bFinancialStatementVo> getB2bStatementList(Page<B2bFinancialStatementVo> page,
                                                       @Param("b2bFinancialStatementVo") B2bFinancialStatementVo b2bFinancialStatementVo);

    /**
     * B2B财务对账单导出
     * @param b2bFinancialStatementVo
     * @return
     */
    List<B2bFinancialStatementVo> getB2bStatementOfAccountList(@Param("b2bFinancialStatementVo") B2bFinancialStatementVo b2bFinancialStatementVo);

    /**
     * 查询对账单数据
     * @param b2bFinancialStatementVo
     * @return
     */
    List<B2bFinancialStatementVo> selectExportStatementData(@Param("b2bFinancialStatementVo") B2bFinancialStatementVo b2bFinancialStatementVo);

    /**
     * 查询对账单数据（分页导出）
     * @param b2bFinancialStatementVo
     * @return
     */
    IPage<B2bFinancialStatementVo> selectExportStatementDataPage(IPage page, @Param("b2bFinancialStatementVo")B2bFinancialStatementVo b2bFinancialStatementVo);
}
