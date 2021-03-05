package com.zsyc.zt.b2b.api.controller.inca.app;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.service.AdminService;
import com.zsyc.zt.b2b.service.ExcelService;
import com.zsyc.zt.inca.service.B2bFinancialStatementService;
import com.zsyc.zt.inca.vo.others.B2bFinancialStatementVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Api(tags = "B2B财务报表")
@RestController
@RequestMapping("api/statement")
@Slf4j
public class B2bFinancialStatementController {

    @Reference
    private B2bFinancialStatementService b2bFinancialStatementService;
    @Reference
    private ExcelService excelService;
    @Reference
    private AdminService adminService;
    @Autowired
    private AccountHelper accountHelper;

    @ApiOperation(value =   "获取b2b财务报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long")
    })
    @GetMapping("/list")
    public IPage<B2bFinancialStatementVo> getB2bStatementList(Page<B2bFinancialStatementVo> page, B2bFinancialStatementVo b2bFinancialStatementVo){
        return b2bFinancialStatementService.getB2bStatementList(page,b2bFinancialStatementVo);
    }

    @ApiOperation(value =   "导出Excel财务报表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "开始查询日期",required = true, dataType = "LocalDateTime"),
            @ApiImplicitParam(name = "endDate", value = "截止查询日期",required = true, dataType = "LocalDateTime")
    })
    @GetMapping("/exportStatementXls")
    public void exportStatementXls(B2bFinancialStatementVo b2bFinancialStatementVo, HttpServletResponse response) throws IOException {
        AssertExt.notNull(b2bFinancialStatementVo.getStartDate(),"数据为空");
        AssertExt.notNull(b2bFinancialStatementVo.getEndDate(),"数据为空");
        IPage<B2bFinancialStatementVo> page = new Page<>(0,100);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String startStr = formatter.format(b2bFinancialStatementVo.getStartDate());
        String endStr = formatter.format(b2bFinancialStatementVo.getStartDate());
        String fileName = startStr+"-"+ endStr +"b2b对账单";

        Workbook workbook = null;
        Date start = new Date();
        ExportParams params = new ExportParams(fileName, "底表");
        for(long i = 1,j=2;i<j;i++){
            page.setCurrent(i);
            IPage<B2bFinancialStatementVo> result = b2bFinancialStatementService.selectExportStatementDataPage(page,b2bFinancialStatementVo);
            workbook =  ExcelExportUtil.exportExcel(params,B2bFinancialStatementVo.class, result.getRecords());
            j = result.getPages();
        }

        System.out.println(new Date().getTime() - start.getTime());
        File saveFile = new File("D:/excel/");
        if (!saveFile.exists()) {
            saveFile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D:/excel/"+fileName+".xlsx");
        workbook.write(fos);
        //fos.close();

        OutputStream outputStream = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx","utf-8"));
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            workbook.close();
            log.info("导出文件:" + LocalDateTime.now().toString());
        } catch (Exception e) {
            log.error("导出文件错误:" + e.getMessage());
        }
    }

}
