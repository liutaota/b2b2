package com.zsyc.zt.report.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.report.entity.GoodsSalesReport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.report.vo.GoodsSalesReportVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2021-01-15
 */
public interface GoodsSalesReportMapper extends BaseMapper<GoodsSalesReport> {

    /**
     * 分页查询商品销售报表数据
     * @param page
     * @param goodsSalesReportVo
     * @return
     */
    IPage<GoodsSalesReportVo> selectReportListPage(IPage<GoodsSalesReportVo> page
            ,@Param("goodsSalesReportVo") GoodsSalesReportVo goodsSalesReportVo);

    /**
     * 查询需导出数据
     * @param goodsSalesReportVo
     * @return
     */
    List<GoodsSalesReportVo> selectExportData(@Param("goodsSalesReportVo") GoodsSalesReportVo goodsSalesReportVo);
}
