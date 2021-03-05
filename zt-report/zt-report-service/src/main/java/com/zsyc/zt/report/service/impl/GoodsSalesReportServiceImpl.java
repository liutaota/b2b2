package com.zsyc.zt.report.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.report.entity.GoodsSalesReport;
import com.zsyc.zt.report.mapper.GoodsSalesReportMapper;
import com.zsyc.zt.report.service.IGoodsSalesPlanDocService;
import com.zsyc.zt.report.service.IGoodsSalesReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsyc.zt.report.vo.GoodsSalesPlanDocVo;
import com.zsyc.zt.report.vo.GoodsSalesReportVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MP
 * @since 2021-01-15
 */
@Primary
@Service
@Slf4j
public class GoodsSalesReportServiceImpl extends ServiceImpl<GoodsSalesReportMapper, GoodsSalesReport> implements IGoodsSalesReportService {

    @Autowired
    private GoodsSalesReportMapper goodsSalesReportMapper;

    @Autowired
    private IGoodsSalesPlanDocService iGoodsSalesPlanDocService;

    @Override
    public IPage<GoodsSalesReportVo> selectReportListPage(IPage<GoodsSalesReportVo> page, GoodsSalesReportVo goodsSalesReportVo) {
        AssertExt.notNull(page, "分页数据为空");
        IPage<GoodsSalesReportVo> listPage = goodsSalesReportMapper.selectReportListPage((Page<GoodsSalesReportVo>) page,goodsSalesReportVo);
        log.info("page size {}", listPage.getSize());
        return listPage;
    }

    @Override
    public Map<String, String> updateRemarkById(Long reportId, String remark) {
        AssertExt.hasId(reportId,"ID为空");
        Map<String, String> rsMap = new HashMap<>();
        GoodsSalesReport goodsSalesReport = new GoodsSalesReport();
        goodsSalesReport.setRemark(remark);
        int result = goodsSalesReportMapper.update(goodsSalesReport, new QueryWrapper<GoodsSalesReport>().eq("report_id", reportId));
        if (result >0){
            rsMap.put("result","success");
        }else{
            rsMap.put("result","failed");
        }
        rsMap.put("count",result+"");
        return rsMap;
    }

    @Override
    public List<GoodsSalesReportVo> selectExportData(GoodsSalesReportVo goodsSalesReportVo) {
        AssertExt.notNull(goodsSalesReportVo, "分页数据为空");
        return goodsSalesReportMapper.selectExportData(goodsSalesReportVo);
    }

    @Override
    public IPage<GoodsSalesPlanDocVo> selectReportListPageV2(IPage<GoodsSalesPlanDocVo> page, GoodsSalesPlanDocVo goodsSalesPlanDocVo) {
        AssertExt.notNull(page, "分页数据为空");
        IPage<GoodsSalesPlanDocVo> goodsSalesPlanDocVoIPage = iGoodsSalesPlanDocService.selectReportPage(page, goodsSalesPlanDocVo);
        AssertExt.notNull(goodsSalesPlanDocVoIPage, "暂无数据");
        List<GoodsSalesPlanDocVo> records = goodsSalesPlanDocVoIPage.getRecords();
        for (GoodsSalesPlanDocVo vo : records) {
            Double planNum = vo.getPlanNum();
            Double completeGoodsQty = vo.getCompleteGoodsQty();
            Double num = null;
            if (planNum - completeGoodsQty > 0){
                num = planNum - completeGoodsQty;
            }else{
                num = 0D;
            }
            String completePercent = this.getPercent(completeGoodsQty,planNum);
            String notCompletePercent = this.getPercent(num, planNum);
            vo.setNotCompleteGoodsQty(num);
            vo.setCompletePercent(completePercent);
            vo.setNotCompletePercent(notCompletePercent);
        }
        return goodsSalesPlanDocVoIPage;
    }

    /**
     * 计算百分比
     * @param y
     * @param z
     * @return
     */
    public String getPercent(Double y, Double z) {

        String percentage = "";// 接受百分比的值
        double fen = y / z;
         NumberFormat nf = NumberFormat.getPercentInstance();   //注释掉的也是一种方法
         nf.setMinimumFractionDigits( 4 );  //保留到小数点后几位
        //DecimalFormat df1 = new DecimalFormat("##.0000%");
        // ##.0000%
        // 百分比格式，后面不足2位的用0补齐
        percentage = nf.format(fen);
        //percentage = df1.format(fen);
        return percentage;
    }
}
