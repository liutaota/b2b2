package com.zsyc.test;

import com.zsyc.zt.b2b.entity.MemberLog;
import com.zsyc.zt.b2b.mapper.MemberLogMapper;
import com.zsyc.zt.b2b.service.LoginService;
import com.zsyc.zt.b2b.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import oracle.jdbc.OracleBlob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;
import java.time.LocalDateTime;

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
public class MemberServiceTest {
    @Autowired
   public LoginService loginService;
    @Autowired
    public MemberService memberService;
    @Autowired
	private MemberLogMapper memberLogMapper;
    @Test
    public void testLogin(){
		this.loginService.memberLogin("18823097610", "18823097610", null, "10.8.1.990");
    }

    @Test
    public void updatePassword(){
        this.memberService.updateMemberPassword("","17665073990","123456789","10.8.1.990");
    }

	@Test
	public void testMemberLogWidthBlob() {
		MemberLog memberLog = new MemberLog();
		memberLog.setCreateTime(LocalDateTime.now())
				.setIp("1.1")
				.setMemberId(10086L)
				.setMetaData("haha", "bulubulu");

		this.memberLogMapper.insert(memberLog);

		memberLog = this.memberLogMapper.selectById(108L);
		log.info("{}", memberLog.getMetaData());
	}
}
