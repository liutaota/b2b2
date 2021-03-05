package com.zsyc.test;

import com.zsyc.invoice.service.InvoiceService;
import com.zsyc.invoice.vo.ZtIssueVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Created by lcs on 2020/10/9.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
        Config.class,
        AnnotationConfigContextLoader.class,
})
@Slf4j
public class InvoiceServiceTest {
    @Autowired
    private InvoiceService invoiceService;

    @Test
    public void testFplgxxcx(){
        this.invoiceService.ztissue(
                "931854",
                "554433221100001",
                "cszh",
                "123456"
        );

    }
}
