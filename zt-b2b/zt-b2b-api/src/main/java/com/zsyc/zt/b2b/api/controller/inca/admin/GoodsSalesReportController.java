package com.zsyc.zt.b2b.api.controller.inca.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.fs.service.MinioFileService;
import com.zsyc.zt.report.service.IGoodsSalesPlanDtlService;
import com.zsyc.zt.report.service.IGoodsSalesReportService;
import com.zsyc.zt.report.vo.GoodsSalesPlanDocVo;
import com.zsyc.zt.report.vo.GoodsSalesReportVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Api(tags = "商品销售报表API接口")
@RestController
@RequestMapping("api/report/goods/statistics")
@Slf4j
public class GoodsSalesReportController {

    @Reference
    private IGoodsSalesReportService iGoodsSalesReportService;
    @Reference
    private IGoodsSalesPlanDtlService iGoodsSalesPlanDtlService;

    @Autowired
    MinioFileService minioFileService;

    @ApiOperation(value = "获取商品销售报表数据(分页)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "startDate", value = "搜索开始日期"),
            @ApiImplicitParam(name = "endDate", value = "搜索截止日期")
    })
    @GetMapping("/list")
    public IPage<GoodsSalesReportVo> selectReportListPage(Page<GoodsSalesReportVo> page,GoodsSalesReportVo goodsSalesReportVo){

        return iGoodsSalesReportService.selectReportListPage(page,goodsSalesReportVo);
    }

    @ApiOperation(value = "获取商品销售报表数据(分页)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "startDate", value = "搜索开始日期"),
            @ApiImplicitParam(name = "endDate", value = "搜索截止日期")
    })
    @GetMapping("/list/v2")
    public IPage<GoodsSalesPlanDocVo> selectReportListPageV2(Page<GoodsSalesPlanDocVo> page, GoodsSalesPlanDocVo goodsSalesPlanDocVo){
        return iGoodsSalesReportService.selectReportListPageV2(page,goodsSalesPlanDocVo);
    }

    @ApiOperation(value = "修改备注")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "reportId", value = "ID", required = true, dataType = "Long")
    })
    @GetMapping("/updateRemarkById")
    public Map<String,String> updateRemarkById(@RequestParam("reportId") Long reportId,
                                               @RequestParam("remark") String remark){
        return iGoodsSalesReportService.updateRemarkById(reportId,remark);
    }

    @ApiOperation(value = "查询计划报表商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "planId", value = "计划ID", required = true, dataType = "Long")
    })
    @GetMapping("/getGoodsStrById")
    public Map<String,Object> getGoodsStrById(@RequestParam("planId") Long planId){
        return iGoodsSalesPlanDtlService.getGoodsStrById(planId);
    }

    @ApiOperation(value =   "导出Excel(商品销售报表)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "开始查询日期",required = true, dataType = "LocalDateTime"),
            @ApiImplicitParam(name = "endDate", value = "截止查询日期",required = true, dataType = "LocalDateTime")
    })
    @RequestMapping("/exportPlanReportXls")
    public String exportPlanReportXls(GoodsSalesPlanDocVo goodsSalesPlanDocVo ) throws Exception {
        IPage<GoodsSalesPlanDocVo> page = new Page<>(0,500);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timeStr = formatter.format(LocalDateTime.now());
        String name =  timeStr+"-"+"商品销售计划报表.xlsx";

        Workbook workbook = null;
        ExportParams params = new ExportParams(name, "商品销售计划报表");
        page.setCurrent(1);
        IPage<GoodsSalesPlanDocVo> result = iGoodsSalesReportService.selectReportListPageV2(page, goodsSalesPlanDocVo);
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            workbook =  ExcelExportUtil.exportExcel(params,GoodsSalesPlanDocVo.class, result.getRecords());
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            return minioFileService.write("report/"+name,byteArrayInputStream);
        } catch (IOException e) {
            log.error("导出Excel异常,exception={}", e.getMessage());
        }
        return null;
    }
}
