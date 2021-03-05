package com.zsyc.test;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.service.MemberService;
import com.zsyc.zt.b2b.vo.BannedVo;
import com.zsyc.zt.b2b.vo.CustomerInfoVo;
import com.zsyc.zt.b2b.vo.CustomerVo;
import com.zsyc.zt.b2b.vo.MemberVo;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Created by lcs on 2019/4/07.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
        Config.class,
        AnnotationConfigContextLoader.class,
})
@Slf4j
public class AdminMemberServiceTest {
    @Reference
    private MemberService memberService;

    @Test
    public void rePassword() {
        MemberVo memberVo = new MemberVo();
        memberVo.setMemberId(81L);
        memberVo.setRePassword("1223213123");
        this.memberService.rePassword(memberVo,"192.168.20.1");
    }


    @Test
    public void getCustomerListInfoById() {
        memberService.getCustomerListInfoById(3150L);
    }

    @Test
    public void getCustomerByCustomerInfoVo() {
        CustomerInfoVo customerInfoVo = new CustomerInfoVo();
        LocalDateTime localDateTime = LocalDateTime.now();
        customerInfoVo.setStartTime(localDateTime.plus(-1, ChronoUnit.YEARS));
        customerInfoVo.setEndTime(localDateTime);
        IPage<CustomerVo> customerInfoVoIPage = memberService.getCustomerByCustomerInfoVo(new Page(1, 10), customerInfoVo);
        List list = customerInfoVoIPage.getRecords();
        for (Object c : list) {
            System.out.println(c);
        }
    }

    @Test
    public void getMemberPubGoodsPriceList(){
        this.memberService.getMemberPubGoodsPriceList(new Page(1,10),33224L);
    }

    @Test
    public void getAdminMemberList(){
        BannedVo bannedVo = new BannedVo();
//        bannedVo.setSetname("控销");
        this.memberService.getAdminMemberList(new Page(1,10),bannedVo);
    }


    @Test
    public void getAdminMemberListById(){
        BannedVo bannedVo = new BannedVo();
        bannedVo.setCustomid(3286L);
        this.memberService.getAdminMemberListById(new Page(1,10),bannedVo);
    }

}
