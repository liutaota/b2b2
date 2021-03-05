package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.mapper.*;
import com.zsyc.zt.b2b.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 楼层 服务实现类
 * </p>
 *
 * @author MP
 * @since 2020-08-17
 */
@Service
@Transactional
@Slf4j
public class FloorServiceImpl implements FloorService {
    @Autowired
    private FloorMapper floorMapper;
    @Reference
    private GoodsService goodsService;
    @Autowired
    private FavoritesMapper favoritesMapper;
    @Autowired
    private WebPageMapper webPageMapper;
    @Autowired
    private WebPageSearchMapper webPageSearchMapper;
    @Autowired
    private FloorCustomerSetServiceImpl floorCustomerSetService;
    @Autowired
    private FloorCustomerSetMapper floorCustomerSetMapper;

    @Override
    public IPage<FloorVo> getFloorList(Page page, String title, String type) {
        IPage<FloorVo> floorVoIPage = this.floorMapper.getFloorList(page, title, type);
        floorVoIPage.getRecords().forEach(floorVo -> {
            List<WebPageVo> webPageVoList = this.getWebPageList(floorVo.getId());
            String[] strings = new String[webPageVoList.size()];
            for (int i = 0; i < webPageVoList.size(); i++) {
                strings[i] = webPageVoList.get(i).getTitle();
            }
            floorVo.setLinkPage(strings);
        });
        return floorVoIPage;
    }

    private List<WebPageVo> getWebPageList(Long floorId) {
        QueryWrapper<WebPage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("IS_DEL", Constant.IS_DEL.NO);
        List<WebPage> webPageList = this.webPageMapper.selectList(queryWrapper);
        return webPageList.stream().map(webPage -> {
            WebPageVo webPageVo = new WebPageVo();
            BeanUtils.copyProperties(webPage, webPageVo);
            return webPageVo;
        }).filter(webPageVo -> {
            return webPageVo.getPageSetList().stream().filter(pageSet -> WebPageInfoVo.PageSet.EType.FLOOR.val().equals(pageSet.getType()))
                    .filter(pageSet -> floorId != null && floorId.equals(pageSet.getFloorId())).count() > 0;
        }).collect(Collectors.toList());
    }

    @Override
    public void addFloor(FloorVo floorVo, Long userId) {
        AssertExt.notNull(floorVo, "请填写楼层数据信息");
        AssertExt.notBlank(floorVo.getType(), "请选择楼层样式");
        AssertExt.checkEnum(Floor.EType.class, floorVo.getType(), "楼层样式不匹配");
        AssertExt.notBlank(floorVo.getTitle(), "请输入楼层名称");
        AssertExt.notNull(floorVo.getVisibleSet(), "楼层可见客户集合为空");
        floorVo.getVisibleSet().forEach(visibleSet -> {
            AssertExt.notBlank(visibleSet.getType(), "可见类型为空");
            AssertExt.checkEnum(FloorInfoVo.VisibleSet.EType.class, visibleSet.getType(), "可见类型不匹配");
            if (FloorInfoVo.VisibleSet.EType.ALL_VISIBLE.val().equals(visibleSet.getType())) return;
            AssertExt.notEmpty(visibleSet.getMemberSetList(), "客户集合为空");
            visibleSet.getMemberSetList().forEach(memberSet -> {
                AssertExt.hasId(memberSet.getMemberSetId(), "客户集合Id为空");
                AssertExt.notBlank(memberSet.getMemberSetName(), "客户集合名称为空");
            });
        });

        AssertExt.notBlank(floorVo.getFloorKey(), "请输入楼层KEY");
        List<Floor> floorList = this.floorMapper.selectList(new QueryWrapper<Floor>().eq("IS_DEL", Constant.IS_DEL.NO));
        // 新增：楼层名称和楼层key不可重复，取没被删除的数据 -->不需要则去屏蔽
        List<String> titleList = floorList.stream().filter(floor -> !floor.getId().equals(floorVo.getId())).map(Floor::getTitle).collect(Collectors.toList());
        titleList.forEach(s -> AssertExt.isFalse(floorVo.getTitle().equals(s), "楼层名称已存在"));
        List<String> keyList = floorList.stream().filter(floor -> !floor.getId().equals(floorVo.getId())).map(Floor::getFloorKey).collect(Collectors.toList());
        keyList.forEach(s -> AssertExt.isFalse(floorVo.getFloorKey().equals(s), "楼层key已存在"));

        List<Integer> sortList = floorList.stream().map(Floor::getFloorSort).collect(Collectors.toList());
        if (floorVo.getType().equals(Floor.EType.ROLL_LINE.val()) || floorVo.getType().equals(Floor.EType.SINGLE_LINE.val())) {
            AssertExt.notEmpty(floorVo.getGoodsList(), "请选择商品");
            floorVo.setGoodsNum(floorVo.getGoodsList().size());
        } else if (floorVo.getType().equals(Floor.EType.MULTI_LINE.val())) {
            AssertExt.notBlank(floorVo.getHue(), "请选择楼层色号");
            AssertExt.notNull(floorVo.getAdvImage(), "请上传Pc广告图片");
            //AssertExt.notNull(floorVo.getAdvImageWx(), "请上传小程序广告图片");
            AssertExt.notNull(floorVo.getClickEvent(), "请选择点击事件");
            switch (floorVo.getClickEvent()) {
                case "erpGoodsId":
                    AssertExt.notNull(floorVo.getErpGoodsId(), "请输入erp商品编号");
                    break;
                case "advImageUrl":
                    AssertExt.notNull(floorVo.getAdvImageUrl(), "请输入图片链接地址");
                    break;
                case "goodsName":
                    AssertExt.notNull(floorVo.getGoodsList(), "请选择商品");
                    break;
            }
            floorVo.getTabs().forEach(tabs -> {
                AssertExt.notBlank(tabs.getTitle(), "请输入页签名称");
                AssertExt.notEmpty(tabs.getGoodsList(), "请选择页签商品");
                tabs.getGoodsList().forEach(goods -> {
                    AssertExt.hasId(goods.getGoodsId(), "商品id无效");
                    AssertExt.notBlank(goods.getGoodsName(), "商品名称为空");
                });
            });
            floorVo.setGoodsNum(floorVo.getTabs().stream().mapToInt(tab -> tab.getGoodsList().size()).sum());
        } else {
            AssertExt.notNull(floorVo.getBackgroundImage(), "请上传Pc背景图片");
            //AssertExt.notNull(floorVo.getBackgroundImageWx(), "请上传小程序背景图片");
            //AssertExt.notNull(floorVo.getFloorImage(), "楼层核心信息为空");
            //floorVo.getFloorImage().forEach(floorImage -> {
            //    AssertExt.notNull(floorImage.getImage(), "请上传楼层图片");
            //    AssertExt.notNull(floorImage.getClickEvent(), "请选择点击事件");
            //    switch (floorImage.getClickEvent()) {
            //        case "erpGoodsId":
            //            AssertExt.notNull(floorImage.getErpGoodsId(), "请输入erp商品编号");
            //            break;
            //        case "advImageUrl":
            //            AssertExt.notNull(floorImage.getUrl(), "请输入图片链接地址");
            //            break;
            //        case "goodsName":
            //            AssertExt.notNull(floorImage.getGoodsList(), "请选择商品");
            //            break;
            //    }
            //});
            AssertExt.notEmpty(floorVo.getGoodsList(), "商品未选择");
            floorVo.getGoodsList().forEach(goods -> {
                AssertExt.hasId(goods.getGoodsId(), "商品id无效");
                AssertExt.notBlank(goods.getGoodsName(), "商品名称为空");
            });
            AssertExt.notNull(floorVo.getGoodNum(),"请输入显示商品数量");
            floorVo.setGoodsNum(floorVo.getGoodNum());
        }
        if (sortList.size() < 1) {
            floorVo.setFloorSort(1);
        } else {
            floorVo.setFloorSort(Collections.max(sortList) + 1);
        }
        floorVo.setCreateUserId(userId);
        floorVo.setCreateTime(LocalDateTime.now());
        this.floorMapper.insert(floorVo);
        this.addFloorCustomerSetList(floorVo);
    }

    @Override
    public void updateFloor(FloorVo floorVo, Long userId) {
        AssertExt.notNull(floorVo, "请填写楼层数据信息");
        AssertExt.notBlank(floorVo.getTitle(), "请输入楼层名称");
        AssertExt.notNull(floorVo.getVisibleSet(), "楼层可见客户集合为空");
        floorVo.getVisibleSet().forEach(visibleSet -> {
            AssertExt.notBlank(visibleSet.getType(), "可见类型为空");
            AssertExt.checkEnum(FloorInfoVo.VisibleSet.EType.class, visibleSet.getType(), "可见类型不匹配");
            if (FloorInfoVo.VisibleSet.EType.ALL_VISIBLE.val().equals(visibleSet.getType())) return;
            AssertExt.notEmpty(visibleSet.getMemberSetList(), "客户集合为空");
            visibleSet.getMemberSetList().forEach(memberSet -> {
                AssertExt.hasId(memberSet.getMemberSetId(), "客户集合Id为空");
                AssertExt.notBlank(memberSet.getMemberSetName(), "客户集合名称为空");
            });
        });
        AssertExt.notBlank(floorVo.getFloorKey(), "请输入楼层KEY");
        List<Floor> floorList = this.floorMapper.selectList(new QueryWrapper<Floor>().eq("IS_DEL", Constant.IS_DEL.NO));
        List<String> titleList = floorList.stream().filter(floor -> !floor.getId().equals(floorVo.getId())).map(Floor::getTitle).collect(Collectors.toList());
        titleList.forEach(s -> AssertExt.isFalse(floorVo.getTitle().equals(s), "楼层名称已存在"));
        List<String> keyList = floorList.stream().filter(floor -> !floor.getId().equals(floorVo.getId())).map(Floor::getFloorKey).collect(Collectors.toList());
        keyList.forEach(s -> AssertExt.isFalse(floorVo.getFloorKey().equals(s), "楼层key已存在"));
        AssertExt.notBlank(floorVo.getType(), "请选择楼层样式");
        AssertExt.checkEnum(Floor.EType.class, floorVo.getType(), "楼层样式不匹配");
        if (floorVo.getType().equals(Floor.EType.ROLL_LINE.val()) || floorVo.getType().equals(Floor.EType.SINGLE_LINE.val())) {
            AssertExt.notEmpty(floorVo.getGoodsList(), "请选择商品");
            floorVo.setGoodsNum(floorVo.getGoodsList().size());
            floorVo.setUpdateUserId(userId);
            floorVo.setUpdateTime(LocalDateTime.now());
            this.floorMapper.updateById(floorVo);
            this.addFloorCustomerSetList(floorVo);
        } else if (floorVo.getType().equals(Floor.EType.MULTI_LINE.val())) {
            AssertExt.notBlank(floorVo.getHue(), "请选择楼层色号");
            AssertExt.notNull(floorVo.getAdvImage(), "请上传Pc广告图片");
            //AssertExt.notNull(floorVo.getAdvImageWx(), "请上传小程序广告图片");
            AssertExt.notNull(floorVo.getClickEvent(), "请选择点击事件");
            switch (floorVo.getClickEvent()) {
                case "erpGoodsId":
                    AssertExt.notNull(floorVo.getErpGoodsId(), "请输入erp商品编号");
                    break;
                case "advImageUrl":
                    AssertExt.notNull(floorVo.getAdvImageUrl(), "请输入图片链接地址");
                    break;
                case "goodsName":
                    AssertExt.notNull(floorVo.getGoodsList(), "请选择商品");
                    break;
            }
            floorVo.getTabs().forEach(tabs -> {
                AssertExt.notBlank(tabs.getTitle(), "请输入页签名称");
                AssertExt.notEmpty(tabs.getGoodsList(), "请选择页签商品");
                tabs.getGoodsList().forEach(goods -> {
                    AssertExt.hasId(goods.getGoodsId(), "商品id无效");
                    AssertExt.notBlank(goods.getGoodsName(), "商品名称为空");
                });
            });
            floorVo.setGoodsNum(floorVo.getTabs().stream().mapToInt(tab -> tab.getGoodsList().size()).sum());
            floorVo.setUpdateUserId(userId);
            floorVo.setUpdateTime(LocalDateTime.now());
            this.floorMapper.updateById(floorVo);
            this.addFloorCustomerSetList(floorVo);
        } else {
           AssertExt.notNull(floorVo.getBackgroundImage(), "请上传Pc背景图片");
           //AssertExt.notNull(floorVo.getBackgroundImageWx(), "请上传小程序背景图片");
           //AssertExt.notNull(floorVo.getFloorImage(), "楼层核心信息为空");
           //floorVo.getFloorImage().forEach(floorImage -> {
           //    AssertExt.notNull(floorImage.getImage(), "请上传楼层图片");
           //    AssertExt.notNull(floorImage.getClickEvent(), "请选择点击事件");
           //    switch (floorImage.getClickEvent()) {
           //        case "erpGoodsId":
           //            AssertExt.notNull(floorImage.getErpGoodsId(), "请输入erp商品编号");
           //            break;
           //        case "advImageUrl":
           //            AssertExt.notNull(floorImage.getUrl(), "请输入图片链接地址");
           //            break;
           //        case "goodsName":
           //            AssertExt.notNull(floorImage.getGoodsList(), "请选择商品");
           //            break;
           //    }
           //});
            AssertExt.notEmpty(floorVo.getGoodsList(), "商品未选择");
            floorVo.getGoodsList().forEach(goods -> {
                AssertExt.hasId(goods.getGoodsId(), "商品id无效");
                AssertExt.notBlank(goods.getGoodsName(), "商品名称为空");
            });
            AssertExt.notNull(floorVo.getGoodNum(),"请输入显示商品数量");
            floorVo.setGoodsNum(floorVo.getGoodNum());
            floorVo.setUpdateUserId(userId);
            floorVo.setUpdateTime(LocalDateTime.now());
            this.floorMapper.updateById(floorVo);
            this.addFloorCustomerSetList(floorVo);
        }
    }

    @Override
    public FloorVo getFloorById(Long id) {
        AssertExt.hasId(id, "楼层ID无效");
        Floor floor = this.floorMapper.selectById(id);
        AssertExt.notNull(floor, "楼层不存在");
        FloorVo floorVo = new FloorVo();
        BeanUtils.copyProperties(floor, floorVo);
        return floorVo;
    }

    @Override
    public void updateFloorIsDel(Long id, Long userId) {
        AssertExt.hasId(id, "楼层ID无效");
        Floor floor = this.floorMapper.selectById(id);
        AssertExt.notNull(floor, "楼层不存在");
        AssertExt.isFalse(floor.getIsUse().equals(Floor.EIsUse.ON.val()), "楼层正在启用，暂时无法删除，请先关闭再操作！");
        Integer count = this.webPageSearchMapper.selectCount(new QueryWrapper<WebPageSearch>().eq("FLOOR_ID", floor.getId()));
        AssertExt.isFalse(count > 0, "楼层正在关联，不可删除，请先取消关联再操作！");
        floor.setIsDel(Constant.IS_DEL.YES);
        floor.setUpdateUserId(userId);
        floor.setUpdateTime(LocalDateTime.now());
        this.floorMapper.updateById(floor);
    }

    @Override
    public void updateFloorIsUse(Long id, String isUse, Long userId) {
        AssertExt.hasId(id, "无效id");
        Floor floor = this.floorMapper.selectById(id);
        AssertExt.notNull(floor, "楼层不存在");
        FloorVo floorVo = new FloorVo();
        BeanUtils.copyProperties(floor, floorVo);
        AssertExt.notBlank(isUse, "启用状态为空");
        AssertExt.checkEnum(Floor.EIsUse.class, isUse, "启用状态不匹配");
        floorVo.setIsUse(isUse);
        floorVo.setUpdateUserId(userId);
        floorVo.setUpdateTime(LocalDateTime.now());
        this.floorMapper.updateById(floorVo);
    }

    @Override
    public void updateFloorSort(FloorVo floorVo, Long userId) {
        AssertExt.hasId(floorVo.getId(), "楼层id无效");
        AssertExt.notBlank(floorVo.getSortType(), "排序类型为空");
        AssertExt.checkEnum(FloorVo.ESortType.class, floorVo.getSortType(), "排序类型不匹配");
        Floor floor = this.floorMapper.selectOne(new QueryWrapper<Floor>().eq("id", floorVo.getId()));
        List<Floor> floorList = this.floorMapper.selectList(new QueryWrapper<Floor>().eq("IS_DEL", Constant.IS_DEL.NO));
        List<Integer> sortList = floorList.stream().map(Floor::getFloorSort).collect(Collectors.toList());
        if (FloorVo.ESortType.TOP.val().equals(floorVo.getSortType())) {
            AssertExt.isFalse(floor.getFloorSort() >= Collections.max(sortList), "此信息已在顶部");
            floor.setFloorSort(Collections.max(sortList) + 1);
            this.floorMapper.updateById(floor);
        } else {
            AssertExt.isFalse(floor.getFloorSort() <= Collections.min(sortList), "此信息已在底部");
            floor.setFloorSort(Collections.min(sortList) - 1);
            this.floorMapper.updateById(floor);
        }
    }

    @Override
    public void floorSort(Long floorIdPrev, Long floorIdNext) {
        AssertExt.hasId(floorIdPrev, "floorIdPrev无效");
        AssertExt.hasId(floorIdNext, "floorIdNext无效");
        Floor floor = this.floorMapper.selectById(floorIdPrev);
        Floor floor1 = this.floorMapper.selectById(floorIdNext);
        Integer sort = 0;
        sort = floor.getFloorSort();
        floor.setFloorSort(floor1.getFloorSort());
        floor1.setFloorSort(sort);
        this.floorMapper.updateById(floor);
        this.floorMapper.updateById(floor1);
    }

    @Override
    public List<Floor> isNotPageFloorList() {
        return this.floorMapper.selectList(new QueryWrapper<Floor>().eq("IS_DEL", Constant.IS_DEL.NO));
    }

    @Override
    public void addFloorCustomerSetList(FloorVo floorVo) {
        log.info("floorVo remove {}", floorVo.getId());
        this.floorCustomerSetService.remove(new QueryWrapper<FloorCustomerSet>().eq("FLOOR_ID", floorVo.getId()));
        if (null != floorVo.getVisibleSet()) {
            floorVo.getVisibleSet().forEach(visibleSet -> {
                FloorCustomerSet floorCustomerSet = new FloorCustomerSet();
                if (visibleSet.getType().equals(FloorInfoVo.VisibleSet.EType.ALL_VISIBLE.val())) {
                    floorCustomerSet.setFloorId(floorVo.getId());
                    floorCustomerSet.setType(visibleSet.getType());
                    floorCustomerSet.setCreateTime(LocalDateTime.now());
                    this.floorCustomerSetMapper.insert(floorCustomerSet);
                } else {
                    visibleSet.getMemberSetList().forEach(memberSet -> {
                        floorCustomerSet.setFloorId(floorVo.getId());
                        floorCustomerSet.setType(visibleSet.getType());
                        floorCustomerSet.setCustomerSetId(memberSet.getMemberSetId());
                        floorCustomerSet.setCreateTime(LocalDateTime.now());
                        this.floorCustomerSetMapper.insert(floorCustomerSet);
                    });
                }
            });
        }
    }
}
