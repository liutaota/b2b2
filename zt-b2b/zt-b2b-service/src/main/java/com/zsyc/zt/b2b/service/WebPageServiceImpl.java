package com.zsyc.zt.b2b.service;

import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.mapper.*;
import com.zsyc.zt.b2b.vo.*;
import com.zsyc.zt.inca.entity.PubGoods;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 专区页 服务实现类
 * </p>
 *
 * @author MP
 * @since 2020-08-21
 */
@Service
@Slf4j
public class WebPageServiceImpl implements WebPageService {
    @Autowired
    private WebPageMapper webPageMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private OrderGoodsMapper orderGoodsMapper;
    @Autowired
    private AdvPositionMapper advPositionMapper;
    @Autowired
    private FloorMapper floorMapper;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private WebPageSearchServiceImpl webPageSearchService;
    @Autowired
    private WebPageCustomerSetServiceImpl webPageCustomerSetService;
    @Autowired
    private WebPageSearchMapper webPageSearchMapper;
    @Autowired
    private WebPageCustomerSetMapper webPageCustomerSetMapper;
    @Autowired
    private FactoryMapper factoryMapper;
    @Autowired
    private MemberApplyMapper memberApplyMapper;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private ActivityMapper activityMapper;
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    @Autowired
    private HomeMenuMapper homeMenuMapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public IPage<WebPageVo> getWebPageList(Page page, String title) {
        return this.webPageMapper.getWebPageList(page, title);
    }

    @Override
    public void addWebPage(WebPageVo webPageVo, Long userId) {
        AssertExt.notBlank(webPageVo.getTitle(), "页面名称为空");
        AssertExt.notEmpty(webPageVo.getVisibleSet(), "可见集合为空");
        webPageVo.getVisibleSet().forEach(visibleSet -> {
            AssertExt.notBlank(visibleSet.getType(), "可见类型为空");
            AssertExt.checkEnum(WebPageInfoVo.VisibleSet.EType.class, visibleSet.getType(), "可见类型不匹配");
            if (WebPageInfoVo.VisibleSet.EType.ALL_VISIBLE.val().equals(visibleSet.getType())) return;
            AssertExt.notEmpty(visibleSet.getMemberSetList(), "客户集合为空");
            visibleSet.getMemberSetList().forEach(memberSet -> {
                AssertExt.hasId(memberSet.getMemberSetId(), "客户集合ID为空");
                AssertExt.notBlank(memberSet.getMemberSetName(), "客户集合名称为空");
            });
        });
        AssertExt.notBlank(webPageVo.getHintIcon(), "提示图标为空");
        AssertExt.checkEnum(WebPage.EHintIcon.class, webPageVo.getHintIcon(), "提示图标有误");

        AssertExt.notNull(webPageVo.getGoodsSetList(), "商品集合为空");

        if (null != webPageVo.getClassifySetList()) {
            webPageVo.getClassifySetList().forEach(classifySet -> {
                AssertExt.notBlank(classifySet.getStairClassify(), "一级分类名称为空");
                AssertExt.notEmpty(classifySet.getAccessList(), "二级分类数据为空");
                classifySet.getAccessList().forEach(access -> {
                    AssertExt.notBlank(access.getAccessName(), "二级分类名称为空");
                    AssertExt.notBlank(access.getType(), "分类设置类型为空");
                    AssertExt.checkEnum(WebPageInfoVo.Access.EType.class, access.getType(), "分类设置类型不匹配");
                    if (access.getType().equals(WebPageInfoVo.Access.EType.CLASS.val())) {
                        AssertExt.notEmpty(access.getClassifyList(), "分类集合为空");
                        access.getClassifyList().forEach(classify -> {
                            AssertExt.notEmpty(classify.getClassifyId(), "分类ID为空");
                        });
                    } else if (access.getType().equals(WebPageInfoVo.Access.EType.GOODS.val())) {
                        AssertExt.notEmpty(access.getGoodsList(), "商品集合为空");
                        access.getGoodsList().forEach(goods -> {
                            AssertExt.hasId(goods.getGoodsId(), "商品ID无效");
                            AssertExt.notBlank(goods.getGoodsName(), "商品名称为空");
                        });
                    }
                });
            });
        }
        AssertExt.notEmpty(webPageVo.getPageSetList(), "页面设置为空");
        webPageVo.getPageSetList().forEach(pageSet -> {
            AssertExt.checkEnum(WebPageInfoVo.PageSet.EType.class, pageSet.getType(), "页面设置类型不匹配");
            if (pageSet.getType().equals(WebPageInfoVo.PageSet.EType.ADV.val())) {
                AssertExt.hasId(pageSet.getAdvId(), "广告ID为空");
                AssertExt.notBlank(pageSet.getAdvTitle(), "广告名称为空");
            } else if (pageSet.getType().equals(WebPageInfoVo.PageSet.EType.FLOOR.val())) {
                AssertExt.hasId(pageSet.getFloorId(), "楼层ID为空");
                AssertExt.notBlank(pageSet.getFloorTitle(), "楼层名称为空");
            }
        });
        List<WebPage> webPageList = this.webPageMapper.selectList(new QueryWrapper<WebPage>().isNotNull("SORT_NUM").eq("IS_DEL", Constant.IS_DEL.NO));
        List<WebPage> webPageList1 = this.webPageMapper.selectList(new QueryWrapper<WebPage>().eq("IS_DEL", Constant.IS_DEL.NO));
        List<String> titleList = webPageList1.stream().filter(webPage -> !webPage.getId().equals(webPageVo.getId())).map(WebPage::getTitle).collect(Collectors.toList());
        titleList.forEach(s -> AssertExt.isFalse(webPageVo.getTitle().equals(s), "页面名称已被使用，请重新输入"));
        List<Integer> sortList = webPageList.stream().map(WebPage::getSortNum).collect(Collectors.toList());
        if (sortList.size() < 1) {
            webPageVo.setSortNum(1);
        } else {
            webPageVo.setSortNum(Collections.max(sortList) + 1);
        }
        webPageVo.setCreateUserId(userId);
        webPageVo.setCreateTime(LocalDateTime.now());
        this.webPageMapper.insert(webPageVo);
        this.addWebPageCustomerSet(webPageVo);
        this.addWebPageSearch(webPageVo);
        this.addWebPageGoodSearch(webPageVo.getId(), webPageVo.getGoodsSetList());
    }

    @Override
    public void updateWebPage(WebPageVo webPageVo, Long userId) {
        AssertExt.hasId(webPageVo.getId(), "页面ID无效");
        WebPage webPage = this.webPageMapper.selectById(webPageVo.getId());
        AssertExt.notBlank(webPageVo.getTitle(), "页面名称为空");
        /*if (webPage.getId() != 1 || webPage.getId() != 2) {
            AssertExt.notNull(webPageVo.getGoodsSetList(), "商品集合为空");
            this.addWebPageGoodSearch(webPage.getId(), webPageVo.getGoodsSetList());
        }*/
        AssertExt.notEmpty(webPageVo.getVisibleSet(), "可见集合为空");
        webPageVo.getVisibleSet().forEach(visibleSet -> {
            AssertExt.notBlank(visibleSet.getType(), "可见类型为空");
            AssertExt.checkEnum(WebPageInfoVo.VisibleSet.EType.class, visibleSet.getType(), "可见类型不匹配");
            if (WebPageInfoVo.VisibleSet.EType.ALL_VISIBLE.val().equals(visibleSet.getType())) return;
            AssertExt.notEmpty(visibleSet.getMemberSetList(), "客户集合为空");
            visibleSet.getMemberSetList().forEach(memberSet -> {
                AssertExt.hasId(memberSet.getMemberSetId(), "客户集合ID为空");
                AssertExt.notBlank(memberSet.getMemberSetName(), "客户集合名称为空");
            });
        });
        AssertExt.notBlank(webPageVo.getHintIcon(), "提示图标为空");
        AssertExt.checkEnum(WebPage.EHintIcon.class, webPageVo.getHintIcon(), "提示图标有误");
        if (null != webPageVo.getClassifySetList()) {
            webPageVo.getClassifySetList().forEach(classifySet -> {
                AssertExt.notBlank(classifySet.getStairClassify(), "一级分类名称为空");
                AssertExt.notEmpty(classifySet.getAccessList(), "二级分类数据为空");
                classifySet.getAccessList().forEach(access -> {
                    AssertExt.notBlank(access.getAccessName(), "二级分类名称为空");
                    AssertExt.notBlank(access.getType(), "分类设置类型为空");
                    AssertExt.checkEnum(WebPageInfoVo.Access.EType.class, access.getType(), "分类设置类型不匹配");
                    if (access.getType().equals(WebPageInfoVo.Access.EType.CLASS.val())) {
                        AssertExt.notEmpty(access.getClassifyList(), "分类集合为空");
                        access.getClassifyList().forEach(classify -> {
                            AssertExt.notEmpty(classify.getClassifyId(), "分类ID为空");
                        });
                    } else if (access.getType().equals(WebPageInfoVo.Access.EType.GOODS.val())) {
                        AssertExt.notEmpty(access.getGoodsList(), "商品集合为空");
                        access.getGoodsList().forEach(goods -> {
                            AssertExt.hasId(goods.getGoodsId(), "商品ID无效");
                            AssertExt.notBlank(goods.getGoodsName(), "商品名称为空");
                        });
                    }
                });
            });
        }
        AssertExt.notEmpty(webPageVo.getPageSetList(), "页面设置为空");
        webPageVo.getPageSetList().forEach(pageSet -> {
            AssertExt.checkEnum(WebPageInfoVo.PageSet.EType.class, pageSet.getType(), "页面设置类型不匹配");
            if (pageSet.getType().equals(WebPageInfoVo.PageSet.EType.ADV.val())) {
                AssertExt.hasId(pageSet.getAdvId(), "广告ID为空");
                AssertExt.notBlank(pageSet.getAdvTitle(), "广告名称为空");
            } else if (pageSet.getType().equals(WebPageInfoVo.PageSet.EType.FLOOR.val())) {
                AssertExt.hasId(pageSet.getFloorId(), "楼层ID为空");
                AssertExt.notBlank(pageSet.getFloorTitle(), "楼层名称为空");
            }
        });
        // 修改：页面名称不可重复，只取没被删除的数据(这里删除是后台管理不显示)
        List<WebPage> webPageList = this.webPageMapper.selectList(new QueryWrapper<WebPage>().eq("IS_DEL", Constant.IS_DEL.NO));
        List<String> titleList = webPageList.stream().filter(webPage1 -> !webPage1.getId().equals(webPageVo.getId())).map(WebPage::getTitle).collect(Collectors.toList());
        titleList.forEach(s -> AssertExt.isFalse(webPageVo.getTitle().equals(s), "页面名称已存在，请重新输入"));
        webPageVo.setUpdateUserId(userId);
        webPageVo.setUpdateTime(LocalDateTime.now());
        BeanUtils.copyProperties(webPageVo, webPage);
        this.webPageMapper.updateById(webPage);
        this.addWebPageCustomerSet(webPageVo);
        this.addWebPageSearch(webPageVo);
        this.addWebPageGoodSearch(webPageVo.getId(), webPageVo.getGoodsSetList());
    }

    @Override
    public WebPageVo getWebPageById(Long id) {
        AssertExt.hasId(id, "无效ID");
        WebPage webPage = this.webPageMapper.selectById(id);
        AssertExt.notNull(webPage, "页面不存在");
        WebPageVo webPageVo = new WebPageVo();
        BeanUtils.copyProperties(webPage, webPageVo);
        return webPageVo;
    }

    @Override
    public void updateWebPageIsDel(Long id, Long userId) {
        AssertExt.hasId(id, "无效ID");
        WebPage webPage = this.webPageMapper.selectById(id);
        AssertExt.notNull(webPage, "页面不存在");
        AssertExt.isFalse(webPage.getTitle().equals("首页"), "首页不可被删除！！");
        AssertExt.isFalse(webPage.getIsUse().equals(WebPage.EIsUse.ON.val()), "页面正在启用，暂时无法删除，请先关闭再操作");
        webPage.setIsDel(Constant.IS_DEL.YES);
        webPage.setUpdateUserId(userId);
        webPage.setUpdateTime(LocalDateTime.now());
        this.webPageMapper.updateById(webPage);
    }

    @Override
    public WebPageVo getWebPageVoInfo(Long id, Long memberId) {
        AssertExt.hasId(id, "id为空");
        WebPage webPage = this.webPageMapper.selectById(id);
        AssertExt.notNull(webPage, "无效id[%s]", id);
        WebPageVo webPageVo = new WebPageVo();
        BeanUtils.copyProperties(webPage, webPageVo);
        List<WebPageInfoVo.PageSet> pageSetList = new ArrayList<>();
        webPageVo.getPageSetList().forEach(pageSet -> {
            if (pageSet.getType().equals(WebPageInfoVo.PageSet.EType.ADV.val())) {
                AdvPositionVo advPositionVo = this.getAdvPositionDetail(pageSet.getAdvId(), memberId);
                if (null == advPositionVo) return;
                //pageSet.setAdvPosition(advPositionVo);
                pageSet.setWhatType(advPositionVo.getApDisplay());
                pageSet.setIsUse(advPositionVo.getIsUse());
                pageSet.setAdvId(advPositionVo.getId());
                pageSet.setAdvTitle(advPositionVo.getAdvName());
            } else {
                FloorVo floorVo = this.getFloorDetail(new Page(1,10),memberId, pageSet.getFloorId());
                if (null != floorVo) {
                    if (floorVo.getId() == 1 || floorVo.getId() == 2 || floorVo.getId() == 3) {
                        //pageSet.setFloor(floorVo);
                        pageSet.setWhatType(floorVo.getType());
                        pageSet.setIsUse(floorVo.getIsUse());
                        pageSet.setFloorId(floorVo.getId());
                        pageSet.setFloorTitle(floorVo.getTitle());
                    }else {
                        //pageSet.setFloor(floorVo);
                        pageSet.setWhatType(floorVo.getType());
                        pageSet.setIsUse(floorVo.getIsUse());
                        pageSet.setFloorId(floorVo.getId());
                        pageSet.setFloorTitle(floorVo.getTitle());
                    }
                }
            }
            pageSetList.add(pageSet);
        });
        if (pageSetList.size() > 0) {
            webPageVo.setPageSetList(pageSetList);
        }
        return webPageVo;
    }

    @Override
    public IPage<GoodsInfoVo> getWebPageSearchList(Page page, GoodsInfoVo goodsInfoVo) {
        Member memberDB = this.memberMapper.selectById(goodsInfoVo.getMemberId());
        if (null != memberDB) {
            goodsInfoVo.setMemberId(memberDB.getErpUserId());
        }
        AssertExt.hasId(goodsInfoVo.getWebPageId(), "专区id为空");

        if (null != goodsInfoVo.getFloorId()) {
            Integer count = this.webPageSearchMapper.selectCount(new QueryWrapper<WebPageSearch>().eq("WEB_PAGE_ID", goodsInfoVo.getWebPageId()).eq("FLOOR_ID", goodsInfoVo.getFloorId()));
            if (count == 0) {
                goodsInfoVo.setFloorId(null);
            }
        }

        if (goodsInfoVo.getWebPageId() == 7) {
            goodsInfoVo.setMemberId(memberDB.getErpUserId());
            return this.goodsInfoMapper.getIntegralGoodsInfoList(page, goodsInfoVo);
        }
        List<WebPageSearch> webPageSearchList = this.webPageSearchMapper.selectList(new QueryWrapper<WebPageSearch>().eq("WEB_PAGE_ID", goodsInfoVo.getWebPageId()));
        if (null != webPageSearchList) {
            goodsInfoVo.setWeek(LocalDateTime.now().getDayOfWeek().getValue());
            IPage<GoodsInfoVo> goodsInfoVoIPage = this.webPageMapper.getWebPageSearchList(page, goodsInfoVo);
            goodsInfoVoIPage.getRecords().forEach(goodsInfoVo1 -> {
                goodsInfoVo1.setActivityVoList(this.activityMapper.getActivityByGoods(goodsInfoVo1.getGoodsid(), goodsInfoVo.getMemberId(), LocalDateTime.now().getDayOfWeek().getValue()));
            });
            return goodsInfoVoIPage;
        }
        goodsInfoVo.setWeek(LocalDateTime.now().getDayOfWeek().getValue());
        IPage<GoodsInfoVo> goodsInfoVoIPage = this.goodsInfoMapper.getGoodsInfoList(page, goodsInfoVo);
        goodsInfoVoIPage.getRecords().forEach(goodsInfoVo1 -> {
            goodsInfoVo1.setActivityVoList(this.activityMapper.getActivityByGoods(goodsInfoVo1.getGoodsid(), goodsInfoVo.getMemberId(), LocalDateTime.now().getDayOfWeek().getValue()));
        });
        return goodsInfoVoIPage;
    }

    @Override
    public List<SearchTipsVo> getWebPageSearchListReturnName(Long userId,Long webPageId, String goodspinyin) {
        Member member = this.memberMapper.selectById(userId);
        AssertExt.notNull(member,"客户不存在");
        AssertExt.hasId(webPageId, "专区id为空");
        List<WebPageSearch> webPageSearchList = this.webPageSearchMapper.selectList(new QueryWrapper<WebPageSearch>().eq("WEB_PAGE_ID", webPageId).isNull("FLOOR_ID"));
        if (webPageSearchList.size() > 0) {
            if (null != goodspinyin) {
                return this.webPageMapper.getWebPageSearchListReturnName(webPageId, goodspinyin);
            }
        } else {
            if (null != goodspinyin) {
                return this.goodsMapper.getGoodsInfoListReturnName(member.getErpUserId(),goodspinyin);
            }
        }
        return null;
    }

    @Override
    public List<FactoryVo> getWebPageFactoryVoList(GoodsInfoVo goodsInfoVo) {
        Member memberDB = this.memberMapper.selectById(goodsInfoVo.getMemberId());
        goodsInfoVo.setMemberId(memberDB.getErpUserId());
        List<FactoryVo> factoryVoList = this.factoryMapper.getWebPageFactoryVoList(goodsInfoVo);
      /*  List<FactoryVo> factoryVoList1 = new ArrayList<>();
        factoryVoList.forEach(factoryVo -> {
            if (null == factoryVo.getFactoryid()) return;
            if (!factoryVoList1.stream().anyMatch(item -> item.getFactoryid().equals(factoryVo.getFactoryid()))) {
                factoryVoList1.add(factoryVo);
            }
        });*/


        return factoryVoList;
    }

    @Override
    public List<HomeMenuVo> getWebPageList(Long memberId) {
        Member member = this.memberMapper.selectById(memberId);
        AssertExt.notNull(member, "无效id[%s]", memberId);
        List<WebPageVo> webPageVoList = this.webPageMapper.getWebPageLists(member.getErpUserId());
        List<HomeMenuVo> homeMenuVoList = this.homeMenuMapper.getHomeMenuList().stream().filter(homeMenuVo -> homeMenuVo.getIsUse().equals(HomeMenu.EIsUse.ON.val())).collect(Collectors.toList());
        homeMenuVoList.forEach(homeMenuVo -> {
            List<HomeMenuVo> homeMenuList2 = this.homeMenuMapper.getHomeList1(homeMenuVo.getId());
            List<HomeMenuVo> homeMenuVoList2 = new ArrayList<>();
            List<HomeMenuVo> homeMenuVos = new ArrayList<>();
            for (HomeMenuVo homeMenuVo1 : homeMenuList2) {
                WebPage webPage = this.webPageMapper.selectById(homeMenuVo1.getHmZoneId());

                WebPageVo webPageVo = new WebPageVo();
                BeanUtils.copyProperties(webPage, webPageVo);

                if (webPageVo.getVisibleSet().get(0).getType().equals(WebPageInfoVo.VisibleSet.EType.PARTIALLY_VISIBLE.val())) {
                    WebPageVo webPageVoDB = this.webPageMapper.getWebPageListById(member.getErpUserId(), webPageVo.getId());
                    if (null != webPageVoDB) {
                        if (homeMenuVo1.getParentId().equals(homeMenuVo.getId())) {
                            homeMenuVoList2.add(homeMenuVo1);
                            homeMenuVos.add(homeMenuVo1);
                        }
                    }
                } else if (webPageVo.getVisibleSet().get(0).getType().equals(WebPageInfoVo.VisibleSet.EType.UN_VISIBLE.val())) {

                    WebPageVo webPageVoDB = this.webPageMapper.getWebPageListById(member.getErpUserId(), webPageVo.getId());
                    if (null != webPageVoDB) {
                        if (homeMenuVo1.getParentId().equals(homeMenuVo.getId())) {
                            homeMenuVoList2.add(homeMenuVo1);
                            homeMenuVos.add(homeMenuVo1);
                        }
                    }
                } else {
                    if (homeMenuVo1.getParentId().equals(homeMenuVo.getId())) {
                        homeMenuVoList2.add(homeMenuVo1);
                        homeMenuVos.add(homeMenuVo1);
                    }
                }

            }
            homeMenuVo.setHomeMenuVoList(homeMenuVos.stream().sorted(Comparator.comparing(HomeMenu::getSort).reversed()).collect(Collectors.toList()));
            log.info("homeMenuVoList2--{}", homeMenuVoList2);
          /*   if (homeMenuVoList2.size() > 0) {
               List<HomeMenuVo> homeMenuVos = new ArrayList<>();
                if (homeMenuVoList2.get(0).getParentId().equals(homeMenuVo.getId())) {
                    homeMenuVo.setHomeMenuVoList(homeMenuVoList2);
                    homeMenuVo.setHomeMenuVoList(homeMenuVos);
                }
            }
           if (null != webPageVoList) {
                List<HomeMenuVo> homeMenuVos = new ArrayList<>();
                for (WebPageVo webPageVo : webPageVoList) {
                    homeMenuVos = homeMenuVoList2.stream().filter(homeMenuVo1 -> !homeMenuVo1.getHmZoneId().equals(webPageVo.getId())).collect(Collectors.toList());
                }
                homeMenuVo.setHomeMenuVoList(homeMenuVos);
            }*/

        });

        log.info("homeMenuVoList--{}", homeMenuVoList);
        /*if (null != webPageVoList) {
            webPageVoList.forEach(webPageVo -> {
                List<WebPageInfoVo.PageSet> pageSetList = new ArrayList<>();
                webPageVo.getPageSetList().forEach(pageSet -> {
                    if (pageSet.getType().equals(WebPageInfoVo.PageSet.EType.ADV.val())) {
                        AdvPosition advPosition = this.advPositionMapper.getAvailableAdvPosition(pageSet.getAdvId(), memberId);
                        if (null == advPosition) return;
                        pageSet.setWhatType(advPosition.getApDisplay());
                        pageSet.setIsUse(advPosition.getIsUse());
                        pageSet.setAdvId(advPosition.getId());
                        pageSet.setAdvTitle(advPosition.getAdvName());
                    } else {
                        Floor floor = this.floorMapper.getAvailableFloor(pageSet.getFloorId(), memberId);
                        if (null == floor) return;
                        if (floor.getId() == 3) {
                            GoodsInfoVo goodsInfoVo = new GoodsInfoVo();
                            goodsInfoVo.setMemberId(member.getErpUserId());
                            goodsInfoVo.setWeek(LocalDateTime.now().getDayOfWeek().getValue());
                            IPage<GoodsInfoVo> goodsInfoVoIPage = this.goodsInfoMapper.getGoodsInfoVoSecKill(new Page(), goodsInfoVo);
                            if (null == goodsInfoVoIPage || goodsInfoVoIPage.getRecords().size() == 0) return;
                        }
                        pageSet.setWhatType(floor.getType());
                        pageSet.setIsUse(floor.getIsUse());
                        pageSet.setFloorId(floor.getId());
                        pageSet.setFloorTitle(floor.getTitle());
                    }
                    if (pageSet.getIsUse().equals(Floor.EIsUse.ON.val())) {
                        pageSetList.add(pageSet);
                    }

                });
                webPageVo.setPageSetList(pageSetList);
            });
        }*/
        return homeMenuVoList;
    }


    @Override
    @Cached(name = "WebPageService-getFloorDetail-", key="#page+''+#memberId+''+#floorId", expire = 300)
    public FloorVo getFloorDetail(Page page,Long memberId, Long floorId) {
        if(null==page){
            page=new Page(1,100);
        }
        AssertExt.hasId(floorId, "楼层ID为空");
        Floor floor = this.floorMapper.selectById(floorId);
        AssertExt.notNull(floor, "无效id[%s]", floorId);
        FloorVo floorVo = new FloorVo();
        Member member = this.memberMapper.selectById(memberId);

        BeanUtils.copyProperties(floor, floorVo);
        if (floorId.equals(1) || floorId.equals(2) || floorId.equals(3)) return floorVo;
        if (null == floorVo.getVisibleSet() || floorVo.getVisibleSet().size() == 0) return null;
        List<FloorInfoVo.VisibleSet> visibleSetList = new ArrayList<>();
        floorVo.getVisibleSet().forEach(visibleSet -> {
            if (visibleSet.getType().equals(FloorInfoVo.VisibleSet.EType.ALL_VISIBLE.val())) {
                visibleSetList.add(visibleSet);
            } else {
                List<Long> longList = visibleSet.getMemberSetList().stream().map(FloorInfoVo.MemberSet::getMemberSetId).collect(Collectors.toList());
                int count = this.floorMapper.isExistMemberSet(longList, member.getErpUserId());
                if (count > 0) {
                    visibleSetList.add(visibleSet);
                }
            }
        });
        floorVo.setVisibleSet(visibleSetList);
        if (floorVo.getVisibleSet().size() == 0) return null;
        if (floorVo.getType().equals(Floor.EType.MULTI_LINE.val())) {
            if (null != floorVo.getTabs() || floorVo.getTabs().size() > 0) {
                List<FloorInfoVo.Tabs> tabsList = new ArrayList<>();

                for(FloorInfoVo.Tabs tabs: floorVo.getTabs()){
                    IPage<FloorInfoVo.Goods> goodsIPage= this.getFloorGoodsInfo(page,tabs.getGoodsList(), memberId);
                    if(goodsIPage.getRecords().size()>0){
                        tabs.setGoodsList(goodsIPage.getRecords());
                        tabsList.add(tabs);
                    }
                }
                if(tabsList.size()>0){
                    floorVo.setTabs(tabsList);
                }

            }
        } else {
            if (null != floorVo.getGoodsList() || floorVo.getGoodsList().size() > 0) {
                IPage<FloorInfoVo.Goods> goodsList = this.getFloorGoodsInfo(page,floorVo.getGoodsList(), memberId);
                if(goodsList.getRecords().size()>0){
                    floorVo.setGoodsIPage(goodsList);
                }
            }
        }
        return floorVo;
    }

    private IPage<FloorInfoVo.Goods> getFloorGoodsInfo(Page page,List<FloorInfoVo.Goods> goodsList, Long memberId) {
        Member memberDB = this.memberMapper.selectById(memberId);
        List<Long> longList = goodsList.stream().map(FloorInfoVo.Goods::getGoodsId).collect(Collectors.toList());

        IPage<FloorInfoVo.Goods> goodsList1 = this.webPageMapper.getGoodsByCustomerId(page,memberDB.getErpUserId(), longList, LocalDateTime.now().getDayOfWeek().getValue());
        goodsList1.getRecords().forEach(goods -> {
            goods.setActivityVoList(this.activityMapper.getActivityByGoods(goods.getGoodsId(), memberDB.getErpUserId(), LocalDateTime.now().getDayOfWeek().getValue()));
        });
       /* goodsList.forEach(goods -> {
            GoodsInfoVo goodsInfoVo = this.goodsService.getGoodsInfo(goods.getGoodsId(), memberId, null, 0);
            goods.setCurrencyname(goodsInfoVo.getCurrencyname());
            goods.setGoodsName(goodsInfoVo.getGoodsname());
            goods.setBusiscopeName(goodsInfoVo.getBusiscopeName());
            goods.setGcId(goodsInfoVo.getClassnRow3());
            goods.setGoodsqty(goodsInfoVo.getStqty());
            goods.setGoodstype(goodsInfoVo.getGoodstype());
            goods.setGoodsunit(goodsInfoVo.getGoodsunit());
            goods.setPrice2(goodsInfoVo.getPrice2());
            goods.setPrice(goodsInfoVo.getPrice());
            goods.setPriceid(goodsInfoVo.getPriceid());
            goods.setStorageid(goodsInfoVo.getStorageid());
            goods.setGoodsImg(goodsInfoVo.getGoodsImg());
            goods.setZxB2bNumLimit(goodsInfoVo.getZxB2bNumLimit());
            goods.setIsFavorites(goodsInfoVo.getIsFavorites());
            goods.setAccflag(goodsInfoVo.getAccflag());
            goods.setFactoryname(goodsInfoVo.getFactoryname());
            goods.setProdarea(goodsInfoVo.getProdarea());
            goods.setGoodsidGps(goodsInfoVo.getGoodsidGps());
            log.info("goods---{}", goods);
        });*/

        //log.info("goodsList{}", goodsList);
        return goodsList1;
    }

    @Override
    public AdvPositionVo getAdvPositionDetail(Long id, Long memberId) {
        AssertExt.hasId(id, "无效广告id");
        AdvPosition advPosition = this.advPositionMapper.selectById(id);
        AssertExt.notNull(advPosition, "无效广告id[%s]", id);
        LocalDateTime localDateTime = LocalDateTime.now();
        Member member = this.memberMapper.selectById(memberId);
        AdvPositionVo advPositionVo = new AdvPositionVo();
        /**
         * 广告过滤-->根据样式
         * 1.过滤外层开启/时间
         * 2.过滤外层可见集合
         * 3.过滤内层开启/时间
         * 4.过滤内层可见集合
         */
        if (advPosition.getIsUse().equals(AdvPosition.EIsUse.ON.val()) && advPosition.getAdvStartDate().isBefore(localDateTime) && advPosition.getAdvEndDate().isAfter(localDateTime)) {
            if (advPosition.getApDisplay().equals(AdvPosition.EApDisplay.TOP_BANNER.val())
                    || advPosition.getApDisplay().equals(AdvPosition.EApDisplay.CENTER_ONLY.val())
                    || advPosition.getApDisplay().equals(AdvPosition.EApDisplay.POPUP_BANNER.val())) {
                BeanUtils.copyProperties(advPosition, advPositionVo);
                if (null != advPositionVo.getVisibleSet() || advPositionVo.getVisibleSet().size() > 0) {
                    List<ImageInfoVo.VisibleSet> visibleSetList = new ArrayList<>();
                    advPositionVo.getVisibleSet().forEach(visibleSet -> {
                        if (visibleSet.getType().equals(ImageInfoVo.VisibleSet.EType.ALL_VISIBLE.val())) {
                            visibleSetList.add(visibleSet);
                        } else {
                            List<Long> longList = visibleSet.getMemberSetList().stream().filter(memberSet -> memberSet.getMemberSetId() != null).map(ImageInfoVo.MemberSet::getMemberSetId).collect(Collectors.toList());
                            int count = this.advPositionMapper.isExistMemberSet(longList, member.getErpUserId());
                            if (count > 0) {
                                visibleSetList.add(visibleSet);
                            }
                        }
                    });
                    advPositionVo.setVisibleSet(visibleSetList);
                }
                if (advPositionVo.getVisibleSet().size() == 0) return null;
            } else {
                BeanUtils.copyProperties(advPosition, advPositionVo);
                if (null != advPositionVo.getVisibleSet() || advPositionVo.getVisibleSet().size() > 0) {
                    List<ImageInfoVo.VisibleSet> visibleSetList = new ArrayList<>();
                    advPositionVo.getVisibleSet().forEach(visibleSet -> {
                        if (visibleSet.getType().equals(ImageInfoVo.VisibleSet.EType.ALL_VISIBLE.val())) {
                            visibleSetList.add(visibleSet);
                        } else {
                            List<Long> longList = visibleSet.getMemberSetList().stream().filter(memberSet -> memberSet.getMemberSetId() != null).map(ImageInfoVo.MemberSet::getMemberSetId).collect(Collectors.toList());
                            int count = this.advPositionMapper.isExistMemberSet(longList, member.getErpUserId());
                            if (count > 0) {
                                visibleSetList.add(visibleSet);
                            }
                        }
                    });
                    advPositionVo.setVisibleSet(visibleSetList);
                }
                if (advPositionVo.getVisibleSet().size() == 0) return null;
                if (null != advPositionVo.getImageInfoVoList() || advPositionVo.getImageInfoVoList().size() > 0) {
                    List<ImageInfoVo> imageInfoVoList = new ArrayList<>();
                    advPositionVo.getImageInfoVoList().forEach(imageInfoVo -> {
                        if (imageInfoVo.getImageIsUse().equals(ImageInfoVo.EImageIsUse.ON.val()) && imageInfoVo.getImageStartTime().isBefore(localDateTime) && imageInfoVo.getImageEndTime().isAfter(localDateTime)) {
                            if (null != imageInfoVo.getVisibleSet().getMemberSetList() || imageInfoVo.getVisibleSet().getMemberSetList().size() > 0) {
                                if (imageInfoVo.getVisibleSet().getType().equals(ImageInfoVo.VisibleSet.EType.ALL_VISIBLE.val())) {
                                    imageInfoVoList.add(imageInfoVo);
                                } else {
                                    List<Long> longList = imageInfoVo.getVisibleSet().getMemberSetList().stream().filter(memberSet -> memberSet.getMemberSetId() != null).map(ImageInfoVo.MemberSet::getMemberSetId).collect(Collectors.toList());
                                    int count = this.advPositionMapper.isExistMemberSet(longList, member.getErpUserId());
                                    if (count > 0) {
                                        imageInfoVoList.add(imageInfoVo);
                                    }
                                }
                            }
                        }
                    });
                    advPositionVo.setImageInfoVoList(imageInfoVoList);
                }
            }
            advPositionVo.getVisibleSet().forEach(visibleSet -> {
                log.info("visibleSet {}", visibleSet);
            });
            advPositionVo.getImageInfoVoList().forEach(imageInfoVo -> {
                log.info("imageInfoVo {}", imageInfoVo);
            });
            return advPositionVo;
        }
        return null;
    }

    @Override
    public void addWebPageSearch(WebPageVo webPageVo) {
        log.info("webPageVo remove {}", webPageVo.getId());
        this.webPageSearchService.remove(new QueryWrapper<WebPageSearch>().eq("WEB_PAGE_ID", webPageVo.getId()).isNotNull("FLOOR_ID"));
        if (webPageVo.getPageSetList() != null) {
            webPageVo.getPageSetList().forEach(pageSet -> {
                // if (pageSet.getType().equals("FLOOR")) {
                if (WebPageInfoVo.PageSet.EType.FLOOR.val().equals(pageSet.getType())) {
                    Floor floor = this.floorMapper.selectById(pageSet.getFloorId());
                    FloorVo floorVo = new FloorVo();
                    BeanUtils.copyProperties(floor, floorVo);
                    if (Floor.EType.MULTI_LINE.val().equals(floorVo.getType())) {
                        floorVo.getTabs().forEach(tabs -> {
                            tabs.getGoodsList().forEach(goods -> {
                                PubGoods pubGoods = this.webPageSearchMapper.getPubGoodsInfo(goods.getGoodsId());

                                WebPageSearch webPageSearch = new WebPageSearch();
                                webPageSearch.setFloorId(pageSet.getFloorId());
                                webPageSearch.setErpGoodsId(goods.getGoodsId());
                                webPageSearch.setGoodsName(goods.getGoodsName());
                                webPageSearch.setWebPageId(webPageVo.getId());
                                webPageSearch.setErpClassId1(pubGoods.getClassnRow1());
                                webPageSearch.setErpClassId2(pubGoods.getClassnRow2());
                                webPageSearch.setErpClassId3(pubGoods.getClassnRow3());
                                webPageSearch.setTitle(tabs.getTitle());
                                this.webPageSearchMapper.insert(webPageSearch);
                            });
                        });
                    } else {
                        if (floorVo.getGoodsList() != null) {
                            floorVo.getGoodsList().forEach(goods -> {
                                PubGoods pubGoods = this.webPageSearchMapper.getPubGoodsInfo(goods.getGoodsId());
                                WebPageSearch webPageSearch = new WebPageSearch();
                                webPageSearch.setFloorId(pageSet.getFloorId());
                                webPageSearch.setErpGoodsId(goods.getGoodsId());
                                webPageSearch.setGoodsName(goods.getGoodsName());
                                webPageSearch.setWebPageId(webPageVo.getId());
                                webPageSearch.setErpClassId1(pubGoods.getClassnRow1());
                                webPageSearch.setErpClassId2(pubGoods.getClassnRow2());
                                webPageSearch.setErpClassId3(pubGoods.getClassnRow3());
                                this.webPageSearchMapper.insert(webPageSearch);
                            });
                        }
                    }
                }
            });
        }

        if (null != webPageVo.getClassifySetList() && webPageVo.getClassifySetList().size() > 0) {
            webPageVo.getClassifySetList().forEach(classifySet -> {
                classifySet.getAccessList().forEach(access -> {
                    //if (access.getType().equals("GOODS")) {
                    if (access.getType().equals(WebPageInfoVo.Access.EType.GOODS.val())) {
                        access.getGoodsList().forEach(goods -> {
                            PubGoods pubGoods = this.webPageSearchMapper.getPubGoodsInfo(goods.getGoodsId());
                            WebPageSearch webPageSearch = new WebPageSearch();
                            webPageSearch.setWebPageId(webPageVo.getId());
                            webPageSearch.setClassName1(classifySet.getStairClassify());
                            webPageSearch.setClassName2(access.getAccessName());
                            webPageSearch.setErpClassId1(pubGoods.getClassnRow1());
                            webPageSearch.setErpClassId2(pubGoods.getClassnRow2());
                            webPageSearch.setErpClassId3(pubGoods.getClassnRow3());
                            this.webPageSearchMapper.insert(webPageSearch);
                        });
                    } else {
                        access.getClassifyList().forEach(classify -> {
                            for (Long[] longs : classify.getClassifyId()) {
                                WebPageSearch webPageSearch = new WebPageSearch();
                                webPageSearch.setWebPageId(webPageVo.getId());
                                webPageSearch.setClassName1(classifySet.getStairClassify());
                                webPageSearch.setClassName2(access.getAccessName());
                                webPageSearch.setErpClassId1(longs[0]);
                                webPageSearch.setErpClassId2(longs[1]);
                                webPageSearch.setErpClassId3(longs[2]);
                                this.webPageSearchMapper.insert(webPageSearch);
                            }
                        });
                    }
                });
            });
        }


    }

    @Override
    public void updateWebPageSort(WebPageVo webPageVo) {
        AssertExt.hasId(webPageVo.getId(), "页面id无效");
        AssertExt.notBlank(webPageVo.getSortType(), "排序类型为空");
        AssertExt.checkEnum(FloorVo.ESortType.class, webPageVo.getSortType(), "排序类型不匹配");
        WebPage webPage = this.webPageMapper.selectOne(new QueryWrapper<WebPage>().eq("id", webPageVo.getId()));
        List<WebPage> webPageList = this.webPageMapper.selectList(new QueryWrapper<WebPage>().eq("IS_DEL", Constant.IS_DEL.NO));
        List<Integer> sortList = webPageList.stream().map(WebPage::getSortNum).collect(Collectors.toList());
        sortList.remove(null);
        if (WebPageVo.ESortType.TOP.val().equals(webPageVo.getSortType())) {
            AssertExt.isFalse(webPage.getSortNum() >= Collections.max(sortList), "此信息已在顶部");
            webPage.setSortNum(Collections.max(sortList) + 1);
            this.webPageMapper.updateById(webPage);
        } else {
            AssertExt.isFalse(webPage.getSortNum() <= Collections.min(sortList), "此信息已在底部");
            webPage.setSortNum(Collections.min(sortList) - 1);
            this.webPageMapper.updateById(webPage);
        }
    }

    @Override
    public void webPageSort(Long webPageIdPrev, Long webPageIdNext) {
        AssertExt.hasId(webPageIdPrev, "webPageIdPrev无效");
        AssertExt.hasId(webPageIdNext, "webPageIdNext无效");
        WebPage webPage = this.webPageMapper.selectById(webPageIdPrev);
        WebPage webPage1 = this.webPageMapper.selectById(webPageIdNext);
        Integer sort = 0;
        sort = webPage.getSortNum();
        webPage.setSortNum(webPage1.getSortNum());
        webPage1.setSortNum(sort);
        this.webPageMapper.updateById(webPage);
        this.webPageMapper.updateById(webPage1);
    }

    @Override
    public void updateWebPageIsUse(Long id, String isUse, Long userId) {
        AssertExt.hasId(id, "id无效");
        WebPage webPage = this.webPageMapper.selectById(id);
        AssertExt.notNull(webPage, "页面不存在");
        WebPageVo webPageVo = new WebPageVo();
        BeanUtils.copyProperties(webPage, webPageVo);
        AssertExt.notBlank(isUse, "状态为空");
        AssertExt.checkEnum(WebPage.EIsUse.class, isUse, "页面状态不匹配");
        webPageVo.setIsUse(isUse);
        webPageVo.setUpdateUserId(userId);
        webPageVo.setUpdateTime(LocalDateTime.now());
        this.webPageMapper.updateById(webPageVo);
    }

    @Override
    public AdminInfoVo todayEvents() {
        AdminInfoVo adminInfoVo = new AdminInfoVo();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime startTime = localDateTime.with(LocalTime.MIN);
        LocalDateTime endTime = localDateTime.with(LocalTime.MAX);
        Integer enterpriseNum = this.memberApplyMapper.selectCount(new QueryWrapper<MemberApply>()
                .eq("STATUS", MemberApply.EStatus.IN_REVIEW.val())
                .lt("CREAT_TIME", endTime)
                .gt("CREAT_TIME", startTime));
        // 售后待审核
        Integer orderStayIssueNum = this.orderInfoMapper.selectCount(new QueryWrapper<OrderInfo>()
                .eq("ORDER_STATE", OrderInfo.EOrderState.TO_ERP.val())
                .lt("CREATE_TIME", endTime)
                .gt("CREATE_TIME", startTime));
        Integer orderStayShipmentsNum = this.orderInfoMapper.selectCount(new QueryWrapper<OrderInfo>()
                .eq("ORDER_STATE", OrderInfo.EOrderState.TO_SEND.val())
                .lt("CREATE_TIME", endTime)
                .gt("CREATE_TIME", startTime));
        adminInfoVo.setEnterpriseNum(enterpriseNum);
        adminInfoVo.setOrderStayIssueNum(orderStayIssueNum);
        adminInfoVo.setOrderStayShipmentsNum(orderStayShipmentsNum);
        return adminInfoVo;
    }

    @Override
    public AdminInfoVo realTimeSituation() {
        AdminInfoVo adminInfoVo = new AdminInfoVo();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime startTime = localDateTime.with(LocalTime.MIN);
        LocalDateTime endTime = localDateTime.with(LocalTime.MAX);
        List<OrderInfo> orderInfoList = this.orderInfoMapper.selectList(new QueryWrapper<OrderInfo>()
                .lt("CREATE_TIME", endTime)
                .gt("CREATE_TIME", startTime));
        // 下单金额->应付金额
        List<Double> orderAmountList = orderInfoList.stream().map(OrderInfo::getGoodsAmount).collect(Collectors.toList());
        double orderAmount = orderAmountList.stream().mapToDouble(order -> order).sum();
        // 付款金额->实付金额
        List<Double> payAmountList = orderInfoList.stream().map(OrderInfo::getActuallyMoney).collect(Collectors.toList());
        double payAmount = payAmountList.stream().mapToDouble(order -> order).sum();
        // 已出库金额
        List<Double> refundAmountList = orderInfoList.stream().map(OrderInfo::getRefundAmount).collect(Collectors.toList());
        double refundAmount = refundAmountList.stream().mapToDouble(order -> order).sum();

        // 数量统计：-->下单订单数量-->下单用户数量-->已出库用户数量
        Integer orderNum = orderInfoList.size();
        Long memberNum = orderInfoList.stream().distinct().count();
        adminInfoVo.setOrderAmount(orderAmount);
        adminInfoVo.setPayAmount(payAmount);

        adminInfoVo.setRefundAmount(refundAmount);
        adminInfoVo.setOrderNum(orderNum);
        adminInfoVo.setMemberNum(memberNum);

        return adminInfoVo;
    }

    @Override
    public void addWebPageCustomerSet(WebPageVo webPageVo) {
        log.info("webPageVo remove {}", webPageVo.getId());
        this.webPageCustomerSetService.remove(new QueryWrapper<WebPageCustomerSet>().eq("WEB_PAGE_ID", webPageVo.getId()));
        if (null != webPageVo.getVisibleSet()) {
            webPageVo.getVisibleSet().forEach(visibleSet -> {
                WebPageCustomerSet webPageCustomerSet = new WebPageCustomerSet();
                if (visibleSet.getType().equals(WebPageInfoVo.VisibleSet.EType.ALL_VISIBLE.val())) {
                    webPageCustomerSet.setWebPageId(webPageVo.getId());
                    webPageCustomerSet.setType(visibleSet.getType());
                    webPageCustomerSet.setCreateTime(LocalDateTime.now());
                    this.webPageCustomerSetMapper.insert(webPageCustomerSet);
                } else {
                    visibleSet.getMemberSetList().forEach(memberSet -> {
                        webPageCustomerSet.setType(visibleSet.getType());
                        webPageCustomerSet.setWebPageId(webPageVo.getId());
                        webPageCustomerSet.setCustomerSetId(memberSet.getMemberSetId());
                        webPageCustomerSet.setCreateTime(LocalDateTime.now());
                        this.webPageCustomerSetMapper.insert(webPageCustomerSet);
                    });
                }
            });
        }
    }

    private void addWebPageGoodSearch(Long webPageId, List<WebPageInfoVo.GoodsSet> goodsSets) {
        this.webPageSearchService.remove(new QueryWrapper<WebPageSearch>().eq("WEB_PAGE_ID", webPageId).isNull("FLOOR_ID"));
        for (WebPageInfoVo.GoodsSet goodsSet : goodsSets) {

            List<PubGoods> pubGoodsList = this.webPageSearchMapper.getPubGoodsInfoBySet(goodsSet.getGoodsSetId());
            for (PubGoods pubGoods : pubGoodsList) {
                WebPageSearch webPageSearch = new WebPageSearch();
                webPageSearch.setErpGoodsId(pubGoods.getGoodsid());
                webPageSearch.setGoodsName(pubGoods.getGoodsname());
                webPageSearch.setWebPageId(webPageId);
                webPageSearch.setErpClassId1(pubGoods.getClassnRow1());
                webPageSearch.setErpClassId2(pubGoods.getClassnRow2());
                webPageSearch.setErpClassId3(pubGoods.getClassnRow3());
                this.webPageSearchMapper.insert(webPageSearch);
            }

        }
    }
}
