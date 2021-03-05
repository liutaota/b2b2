package com.zsyc.test;

import com.zsyc.zt.b2b.entity.User;
import com.zsyc.zt.b2b.mapper.UserMapper;
import com.zsyc.zt.b2b.service.AdminService;
import com.zsyc.zt.b2b.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Created by lcs on 2020/7/15.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
		Config.class,
		AnnotationConfigContextLoader.class,
})
@Slf4j
public class AdminServiceTest {
	@Autowired
	private AdminService adminService;
	@Autowired
	private UserMapper userMapper;

	@Test
	public void testAddUser() {
		UserVo userVo = new UserVo();
		userVo.setUserName("test1");
		userVo.setRePassword("test12");
		userVo.setPassword("test12");
		userVo.setName("admin");
		userVo.setRole("admin");
		log.info("{}",this.adminService.addUser(userVo));
	}

	@Test
	public void testLogin() {
		UserVo user = new UserVo();
		user.setUserName("tjd");
		user.setPassword("test1234");
		log.info("{}", this.adminService.login(user,"196.10.20.195"));
	}

	@Test
	public void testGetUseNameFromInca() {
		log.info("{}",this.userMapper.getUserNameFromInca());
	}
}
