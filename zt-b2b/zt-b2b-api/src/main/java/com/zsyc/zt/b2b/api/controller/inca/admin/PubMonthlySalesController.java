package com.zsyc.zt.b2b.api.controller.inca.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.fs.service.MinioFileService;
import com.zsyc.zt.report.entity.PubMonthlySales;
import com.zsyc.zt.report.service.PubMonthlySalesService;
import com.zsyc.zt.report.vo.PubMonthlySalesVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

///**
// * @author
// */
@Api("销售报表")
@RestController
@RequestMapping("api/report")
@Log4j
public class PubMonthlySalesController {

    @Reference
    PubMonthlySalesService pubMonthlySalesService;

    @Autowired
    MinioFileService minioFileService;


    @RequestMapping("/list")
    @PreAuthorize("isAuthenticated()")
    public IPage<PubMonthlySales> selectPageList(@RequestParam(name = "sales_id_list", required = false) List<Long> salesIdList,
                                                 @RequestParam(name = "customids", required = false) String customids,
                                                 @RequestParam(name = "tomonthss", required = false) String tomonthss,
                                                 @RequestParam(name = "startTime", required = false) String startTime,
                                                 @RequestParam(name = "endTime", required = false) String endTime,
                                                 @RequestParam(name = "page_no", defaultValue = "1") Integer pageNo,
                                                 @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize,
                                                 @RequestParam(name = "customname", required = false) String customname
    ) {


        Page<PubMonthlySales> userPage = new Page<>(pageNo, pageSize);

        IPage<PubMonthlySales> result = pubMonthlySalesService.selectPageList(userPage, salesIdList, customids, tomonthss, startTime, endTime,customname);
        return result;

    }

    @GetMapping(value = "/exportCandidateTempt")
    @ApiOperation(value = "销售报表", notes = "销售报表")
    public String exportCandidateTempt(PubMonthlySalesVo pubMonthlySalesVo) throws Exception {
        String startTime= pubMonthlySalesVo.getStartTime();
        String endTime= pubMonthlySalesVo.getEndTime();
       // System.out.print(endTime);
        String start=startTime.substring(0,7);
        String Time=endTime.substring(0,7);
        List<PubMonthlySales> pubMonthlySalesList = pubMonthlySalesService.selectListByTime(startTime, endTime);

        ExportParams entity = new ExportParams();
        entity.setSheetName("销售报表");
        try {
            //ExcelUtils.export(fileName, pubMonthlySalesList, PubMonthlySales.class);

            Workbook sheets = ExcelExportUtil.exportExcel(entity, PubMonthlySales.class, pubMonthlySalesList);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            sheets.write(byteArrayOutputStream);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            return minioFileService.write("report/"+start+"-01——"+Time+"-01销售报表.xls",byteArrayInputStream);
        } catch (IOException e) {
            log.error("导出Excel异常,exception={}", e);
            //throw new Exception("导出Excel异常：" + e.getMessage());
        }
        return null;
    }

}