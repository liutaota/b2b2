package com.zsyc.zt.b2b.service;

import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.AdvPosition;
import com.zsyc.zt.b2b.entity.Floor;
import com.zsyc.zt.b2b.vo.*;

import java.util.List;

/**
 * <p>
 * 专区页 服务类
 * </p>
 *
 * @author MP
 * @since 2020-08-21
 */
public interface WebPageService {
    /**
     * 专区列表
     *
     * @param page
     * @param title
     * @return
     */
    IPage<WebPageVo> getWebPageList(Page page, String title);

    /**
     * 专区楼层详情
     *
     * @param memberId
     * @param floorId
     * @return
     */
    @Cached(name = "WebPageService-getFloorDetail-", key="#memberId+''+#floorId", expire = 300)
    FloorVo getFloorDetail(Page page,Long memberId, Long floorId);

    /**
     * 专区广告详情
     *
     * @param id
     * @return
     */
   // @Cached(name = "WebPageService-getAdvPositionDetail-", key="#id", expire = 3600)
    AdvPositionVo getAdvPositionDetail(Long id,Long memberId);

    /**
     * 首页专区列表
     *
     * @param memberId
     * @return
     */
    //@Cached(name = "WebPageService-getWebPageList-", key="#memberId", expire = 3600)
    List<HomeMenuVo> getWebPageList(Long memberId);

    /**
     * 新增页面
     *
     * @param webPageVom
     * @param userId
     */
    void addWebPage(WebPageVo webPageVom, Long userId);

    /**
     * 编辑页面
     *
     * @param webPageVo
     * @param userId
     */
    void updateWebPage(WebPageVo webPageVo, Long userId);

    /**
     * 组合编辑使用，根据ID查专区
     *
     * @param id
     * @return
     */
    WebPageVo getWebPageById(Long id);

    /**
     * 删除页面
     *
     * @param id
     * @param userId
     */
    void updateWebPageIsDel(Long id, Long userId);

    /**
     * 专区详情
     *
     * @param id
     * @return
     */
    //@Cached(name = "WebPageService-getWebPageVoInfo-", key="#id+''+#memberId", expire = 3600)
    WebPageVo getWebPageVoInfo(Long id, Long memberId);

    /**
     * 专区搜索
     *
     * @return
     */
    IPage<GoodsInfoVo> getWebPageSearchList(Page page,  GoodsInfoVo goodsInfoVo);

    /**
     * 专区搜索-->只返回名称
     *
     * @param userId
     * @param webPageId
     * @param goodspinyin
     * @return
     */
    List<SearchTipsVo> getWebPageSearchListReturnName(Long userId, Long webPageId, String goodspinyin);

    /**
     * 专区厂家分类列表
     *
     * @param goodsInfoVo
     * @return
     */
    List<FactoryVo> getWebPageFactoryVoList(GoodsInfoVo goodsInfoVo);

    /**
     * 专区json数据新增到专区搜索表
     * @param webPageVo
     */
    void addWebPageSearch(WebPageVo webPageVo);

    /**
     * 页面客户集合可见数据
     * @param webPageVo
     */
    void addWebPageCustomerSet(WebPageVo webPageVo);

    /**
     * 页面置顶/置底排序
     * @param webPageVo
     */
    void updateWebPageSort(WebPageVo webPageVo);

    /**
     * 页面上下排序
     * @param webPageIdPrev
     * @param webPageIdNext
     */
    void webPageSort(Long webPageIdPrev, Long webPageIdNext);

    /**
     * 页面状态修改
     * @param id
     * @param isUse
     * @param userId
     */
    void updateWebPageIsUse(Long id, String isUse, Long userId);

    /**
     * 今日待办
     * @return
     */
    AdminInfoVo todayEvents();

    /**
     * 今日订单实时情况
     * @return
     */
    AdminInfoVo realTimeSituation();

}
