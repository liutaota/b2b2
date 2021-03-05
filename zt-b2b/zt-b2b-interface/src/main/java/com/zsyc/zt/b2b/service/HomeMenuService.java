package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.HomeMenu;
import com.zsyc.zt.b2b.vo.HomeMenuVo;
import com.zsyc.zt.b2b.vo.NoticeVo;

import java.util.List;

public interface HomeMenuService {
    /**
     * 添加菜单
     * @param homeMenu
     */
    void addHomeMenu(HomeMenu homeMenu);

    /**
     * 修改菜单
     * @param homeMenu
     */
    void updateHomeMenu(HomeMenu homeMenu);

    /**
     * 删除菜单
     * @param id
     */
    void deleteHomeMenu(Long id);

    /**
     * 菜单列表
     * @param page
     * @return
     */
    IPage<HomeMenuVo> getHomeMenuList(Page page);

    /**
     * 菜单是否启用
     * @param id
     */
    void homeMenuIsUse(Long id,String isUse);

    /**
     * 菜单置顶/置底排序
     * @param homeMenuVo
     */
    void updateHomeMenuSort(HomeMenuVo homeMenuVo);

    /**
     * 菜单设置上下排序
     * @param homeMenuIdPrev
     * @param homeMenuIdNext
     */
    void homeMenuSort(Long homeMenuIdPrev, Long homeMenuIdNext);

}
