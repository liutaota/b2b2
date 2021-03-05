package com.zsyc.test;

import com.zsyc.zt.inca.mapper.B2bFinancialStatementMapper;
import com.zsyc.zt.inca.service.B2bFinancialStatementService;
import com.zsyc.zt.inca.vo.others.B2bFinancialStatementVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/*@ContextConfiguration(classes = {
        Config.class,
        AnnotationConfigContextLoader.class,
})*/
@Slf4j
public class B2bFinancialStatementServiceTest {



    @Test
    public void testExport() throws IOException {
        List<B2bFinancialStatementVo> list = new ArrayList<B2bFinancialStatementVo>();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(
                "2412312", "测试", "测试"), B2bFinancialStatementVo.class, list);
        workbook.write(new FileOutputStream("1.xls"));
    }
}
