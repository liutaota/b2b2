package com.zsyc.test;

import com.zsyc.zt.inca.service.FinanceService;
import com.zsyc.zt.inca.service.OrderBackService;
import com.zsyc.zt.inca.vo.FinanceVerificationDocVo;
import com.zsyc.zt.inca.vo.FinanceVerificationDtlVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
// import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author peiqy
 * @version 1.0
 * @email peiqy@foxmail.com
 * @date 2020/8/11 18:10
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
        Config.class,
        AnnotationConfigContextLoader.class,
})
@Slf4j
public class FinanceServiceTest {
    @Autowired
    public FinanceService financeService1;


    @Test
    public void test1(){
        FinanceVerificationDocVo doc=new FinanceVerificationDocVo();
        doc.setCustomId(2086l);
        doc.setCreateDate(LocalDateTime.now());
        doc.setInputManId(16l);
        doc.setEntryId(1);

        //
        List<FinanceVerificationDtlVo> financeVerificationDtlVoList=new ArrayList<FinanceVerificationDtlVo>();
        FinanceVerificationDtlVo dtlVo=new FinanceVerificationDtlVo();
        dtlVo.setAmount(293.97);
        dtlVo.setSrcErpOrderDtlId(5766614l);
        dtlVo.setSrcErpOrderId(777961l);

        financeVerificationDtlVoList.add(dtlVo);

        doc.setFinanceVerificationDtlVoList(financeVerificationDtlVoList);

        this.financeService1.verification(doc);

        System.out.println("啊打发撒旦：");
    }
}
