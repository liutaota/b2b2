package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.User;
import com.zsyc.zt.b2b.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台用户 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-15
 */
public interface UserMapper extends BaseMapper<User> {

	/**
	 * 测试代码
	 * @return
	 */
	List<String> getUserNameFromInca();

	/**
	 * 所有管理员
	 *
	 * @param page
	 * @param userVo
	 * @return
	 */
	IPage<UserVo> getUserList(@Param("page") Page page, @Param("userVo") UserVo userVo);
}
