package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.HomeMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.HomeMenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 首页菜单 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-10-29
 */
public interface HomeMenuMapper extends BaseMapper<HomeMenu> {
    /**
     * 菜单列表-->一级菜单
     * @param page
     * @return
     */
    IPage<HomeMenuVo> getHomeMenuList(@Param("page")Page page);

    /**
     * 菜单列表-->一级菜单
     * @return
     */
    List<HomeMenuVo> getHomeMenuList();

    /**
     * 菜单列表-->二级菜单
     * @return
     */
    List<HomeMenuVo> getHomeList();

    /**
     * 菜单列表-->二级菜单
     * @return
     */
    List<HomeMenuVo> getHomeList1(@Param("id") Long id);

}
