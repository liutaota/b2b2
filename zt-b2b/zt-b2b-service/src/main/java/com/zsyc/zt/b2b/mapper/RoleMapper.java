package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.RoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统角色 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-09-10
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 角色列表
     *
     * @param page
     * @param roleVo
     * @return
     */
    IPage<RoleVo> getRoleList(@Param("page") Page page, @Param("roleVo") RoleVo roleVo);

    /**
     * 账户角色
     *
     * @param userId
     * @return
     */
    List<RoleVo> getRoleListByUserId(@Param("userId") Long userId);

    /**
     * 角色列表不分页
     * @param roleVo
     * @return
     */
    List<RoleVo> getRoleList(@Param("roleVo") RoleVo roleVo);
}
