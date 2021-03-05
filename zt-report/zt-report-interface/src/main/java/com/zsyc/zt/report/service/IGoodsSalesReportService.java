package com.zsyc.zt.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zsyc.zt.report.entity.GoodsSalesReport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsyc.zt.report.vo.GoodsSalesPlanDocVo;
import com.zsyc.zt.report.vo.GoodsSalesReportVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MP
 * @since 2021-01-15
 */
public interface IGoodsSalesReportService extends IService<GoodsSalesReport> {

    /**
     * 获取商品销售报表数据(分页)
     * @param page
     * @param goodsSalesReportVo
     * @return
     */
    IPage<GoodsSalesReportVo> selectReportListPage(IPage<GoodsSalesReportVo> page, GoodsSalesReportVo goodsSalesReportVo);

    /**
     * 修改备注
     * @param reportId
     * @param remark
     * @return
     */
    Map<String, String> updateRemarkById(Long reportId,String remark);

    /**
     * 查询需导出商品报表数据
     * @param goodsSalesReportVo
     * @return
     */
    List<GoodsSalesReportVo> selectExportData(GoodsSalesReportVo goodsSalesReportVo);

    /**
     * V2 获取商品销售报表数据(分页)
     * @param page
     * @param goodsSalesPlanDocVo
     * @return
     */
    IPage<GoodsSalesPlanDocVo> selectReportListPageV2(IPage<GoodsSalesPlanDocVo> page, GoodsSalesPlanDocVo goodsSalesPlanDocVo);
}
