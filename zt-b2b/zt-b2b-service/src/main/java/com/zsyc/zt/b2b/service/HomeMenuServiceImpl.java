package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.HomeMenu;
import com.zsyc.zt.b2b.mapper.HomeMenuMapper;
import com.zsyc.zt.b2b.vo.FloorVo;
import com.zsyc.zt.b2b.vo.HomeMenuVo;
import com.zsyc.zt.b2b.vo.NoticeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class HomeMenuServiceImpl implements HomeMenuService {
    @Autowired
    private HomeMenuMapper homeMenuMapper;

    @Override
    public void addHomeMenu(HomeMenu homeMenu) {
        AssertExt.notNull(homeMenu.getHmName(), "菜单名为空");
        AssertExt.notNull(homeMenu.getHmZoneId(), "页面id为空");
        AssertExt.notNull(homeMenu.getHintIcon(), "提示图标为空");
        //AssertExt.notNull(homeMenu.getHmPic(),"图标为空");
        List<HomeMenu> homeMenuList = this.homeMenuMapper.selectList(new QueryWrapper<HomeMenu>().eq("IS_DEL", Constant.IS_DEL.NO).isNotNull("SORT"));
        List<Long> sortList = homeMenuList.stream().map(HomeMenu::getSort).collect(Collectors.toList());
        if (sortList.size() < 1) {
            homeMenu.setSort(1L);
        } else {
            homeMenu.setSort(Collections.max(sortList) + 1);
        }
        homeMenu.setCreateTime(LocalDateTime.now());
        homeMenu.setIsDel(Constant.IS_DEL.NO);
        homeMenu.setIsUse(HomeMenu.EIsUse.OFF.val());
        this.homeMenuMapper.insert(homeMenu);
    }

    @Override
    public void updateHomeMenu(HomeMenu homeMenu) {
        AssertExt.hasId(homeMenu.getId(), "菜单设置id无效");
        HomeMenu homeMenuDB = this.homeMenuMapper.selectById(homeMenu.getId());
        AssertExt.notNull(homeMenuDB, "菜单设置不存在");
        AssertExt.notNull(homeMenu.getHmName(), "菜单名为空");
        AssertExt.notNull(homeMenu.getHmZoneId(), "页面id为空");
        AssertExt.notNull(homeMenu.getHintIcon(), "提示图标为空");
        //AssertExt.notNull(homeMenu.getHmPic(),"图标为空");
        AssertExt.checkEnum(HomeMenu.EHintIcon.class,homeMenu.getHintIcon(),"提示图标不匹配");
        BeanUtils.copyProperties(homeMenu, homeMenuDB);
        this.homeMenuMapper.updateById(homeMenuDB);
    }

    @Override
    public void deleteHomeMenu(Long id) {
        AssertExt.hasId(id, "菜单设置id无效");
        HomeMenu homeMenu = this.homeMenuMapper.selectById(id);
        AssertExt.notNull(homeMenu, "菜单设置不存在");
        homeMenu.setIsDel(Constant.IS_DEL.YES);
        this.homeMenuMapper.updateById(homeMenu);
    }

    @Override
    public void homeMenuIsUse(Long id, String isUse) {
        AssertExt.hasId(id, "菜单设置id无效");
        AssertExt.notNull(isUse, "开启状态为空");
        HomeMenu homeMenu = this.homeMenuMapper.selectById(id);
        AssertExt.notNull(homeMenu, "菜单设置不存在");
        homeMenu.setIsUse(isUse);
        this.homeMenuMapper.updateById(homeMenu);
    }

    @Override
    public IPage<HomeMenuVo> getHomeMenuList(Page page) {
        AssertExt.notNull(page,"页面为空");
        IPage<HomeMenuVo> homeMenuVoIPage = this.homeMenuMapper.getHomeMenuList(page);
        List<HomeMenuVo> homeMenuVoList = this.homeMenuMapper.getHomeList();
        homeMenuVoIPage.getRecords().forEach(homeMenuVo -> {
                if (null != homeMenuVoList) {
                    List<HomeMenuVo> homeMenuVoList2 = new ArrayList<>();
                    for (HomeMenuVo homeMenuVo1 : homeMenuVoList) {
                        if (homeMenuVo1.getParentId().equals(homeMenuVo.getId())) {
                            homeMenuVo1.setHomeName(homeMenuVo.getHmName());
                            homeMenuVoList2.add(homeMenuVo1);
                        }
                    }
                    if (homeMenuVoList2.size() > 0){
                        if (homeMenuVoList2.get(0).getParentId().equals(homeMenuVo.getId())){
                            homeMenuVo.setHomeMenuVoList(homeMenuVoList2.stream().sorted(Comparator.comparing(HomeMenu::getSort).reversed()).collect(Collectors.toList()));
                        }
                    }
                }
            });
        return homeMenuVoIPage;
    }

    @Override
    public void updateHomeMenuSort(HomeMenuVo homeMenuVo) {
        AssertExt.hasId(homeMenuVo.getId(), "菜单设置id无效");
        AssertExt.notBlank(homeMenuVo.getSortType(), "排序类型为空");
        AssertExt.checkEnum(FloorVo.ESortType.class, homeMenuVo.getSortType(), "排序类型不匹配");
        HomeMenu homeMenu = this.homeMenuMapper.selectById(homeMenuVo.getId());
        AssertExt.notNull(homeMenu,"菜单设置不存在");
        List<HomeMenu> homeMenuList = this.homeMenuMapper.selectList(new QueryWrapper<>());
        List<Long> sortList = homeMenuList.stream().filter(homeMenu1 -> homeMenu1.getIsDel().equals(Constant.IS_DEL.NO)).map(HomeMenu::getSort).collect(Collectors.toList());
        sortList.remove(null);
        if (homeMenuVo.getSortType().equals(HomeMenuVo.ESortType.TOP.val())){
            AssertExt.isFalse(homeMenu.getSort() >= Collections.max(sortList),"此信息已在顶部");
            homeMenu.setSort(Collections.max(sortList) + 1);
            this.homeMenuMapper.updateById(homeMenu);
        }else {
            AssertExt.isFalse(homeMenu.getSort() <= Collections.min(sortList),"此信息已在底部");
            homeMenu.setSort(Collections.min(sortList) -1);
            this.homeMenuMapper.updateById(homeMenu);
        }
    }

    @Override
    public void homeMenuSort(Long homeMenuIdPrev, Long homeMenuIdNext) {
        AssertExt.hasId(homeMenuIdPrev,"homeMenuIdPrev无效");
        AssertExt.hasId(homeMenuIdNext,"homeMenuIdNext无效");
        HomeMenu homeMenu = this.homeMenuMapper.selectById(homeMenuIdPrev);
        HomeMenu homeMenu1 = this.homeMenuMapper.selectById(homeMenuIdNext);
        if (homeMenu.getParentId() != null && homeMenu1.getParentId() != null){
            Long sort = 0L;
            sort = homeMenu.getSort();
            homeMenu.setSort(homeMenu1.getSort());
            homeMenu1.setSort(sort);
            this.homeMenuMapper.updateById(homeMenu);
            this.homeMenuMapper.updateById(homeMenu1);
        }else if (homeMenu.getParentId() == null && homeMenu1.getParentId() == null){
            Long sort = 0L;
            sort = homeMenu.getSort();
            homeMenu.setSort(homeMenu1.getSort());
            homeMenu1.setSort(sort);
            this.homeMenuMapper.updateById(homeMenu);
            this.homeMenuMapper.updateById(homeMenu1);
        }
    }
}
