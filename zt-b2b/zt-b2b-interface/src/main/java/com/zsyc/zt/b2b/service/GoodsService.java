package com.zsyc.zt.b2b.service;

import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.vo.*;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface GoodsService {

    /**
     * 商品分类列表
     *
     * @param goodsClassTypeVo
     * @return
     */
    @Cached(name = "GoodsService-getGoodsClassTypeVoList-", key = "#goodsClassTypeVo", expire = 1)
    List<GoodsClassTypeVo> getGoodsClassTypeVoList(GoodsClassTypeVo goodsClassTypeVo);

    /**
     * 商品列表
     *
     * @param page
     * @param goodsInfoVo
     * @return
     */
    IPage<GoodsInfoVo> getGoodsInfoList(Page page, GoodsInfoVo goodsInfoVo);

    /**
     * 积分商品列表
     *
     * @param page
     * @param goodsInfoVo
     * @return
     */
    IPage<GoodsInfoVo> getIntegralGoodsInfoList(Page page, GoodsInfoVo goodsInfoVo);

    /**
     * 新品上架
     *
     * @param page
     * @param goodsInfoVo
     * @return
     */
    IPage<GoodsInfoVo> getNewGoodsInfoVoList(Page page, GoodsInfoVo goodsInfoVo);

    /**
     * 厂家列表
     *
     * @param goodsInfoVo
     * @return
     */
    List<FactoryVo> getFactoryVoList(GoodsInfoVo goodsInfoVo);

    /**
     * 后台商品列表
     *
     * @param page
     * @param goodsInfoVo
     * @return
     */
    IPage<GoodsInfoVo> getAdminGoodsInfoList(Page page, GoodsInfoVo goodsInfoVo);

    /**
     * 商品详情
     *
     * @param goodsid
     * @return
     */
    // @Cached(name = "GoodsService-getGoodsInfo-", key = "#goodsid+''+#customerId+''#lotid+''#storageId", expire = 3600)
    GoodsInfoVo getGoodsInfo(Long goodsid, Long customerId, Long lotid, Integer storageId);

    /**
     * 活动商品的赠品信息
     *
     * @param asId
     * @return
     */
    List<ActivityGiftVo> getActivityGiftVoList(Long asId);

    /**
     * 后台商品详情
     *
     * @param goodsid
     * @return
     */
    GoodsInfoVo getAdminGoodsInfo(Long goodsid,Integer storageId);

    /**
     * 添加商品到购物车
     *
     * @param cartList
     */
    void addMemberCart(List<Cart> cartList, Long memberId);

    /**
     * 修改购物车商品
     *
     * @param cart
     */
    void updateMemberCart(Cart cart);

    /**
     * 修改商品活动id
     * @param memberId
     * @param activityId
     */
    void updateCartActivity(Long memberId ,Long activityId);

    /**
     * 购物车商品参与的活动
     * @param memberId
     * @return
     */
    List<ActivityVo> getMyCartJoinActivity(Long memberId);
    /**
     * 是否全选购物车商品
     * @param pitchOn
     * @param memberId
     */
    void isPitchOnCart(Long pitchOn,Long memberId);

    /**
     * 删除购物车商品
     *
     * @param ids
     */
    void delMemberCart(Long[] ids);

    /**
     * 购物车列表
     *
     * @param page
     * @param cartVo
     * @return
     */
    IPage<CartVo> getMemberCartList(Page page, CartVo cartVo);

    /**
     * 购物车不分页
     *
     * @param cartVo
     * @return
     */
    List<CartVo> getMemberCartAll(CartVo cartVo);

    /**
     * 我的购物车数量
     *
     * @param memberId
     * @return
     */
    Integer getMemberCartTotal(Long memberId);

    /**
     * 商品活动
     *
     * @param id
     * @param goodsId
     * @param erpUserId
     * @return
     */
    List<ActivityVo> getActivityVoById(Long id, Long goodsId, Long erpUserId, Integer fromTo);

    /**
     * 收藏商品/店铺
     *
     * @param favoritesList
     */
    List<Favorites> addMemberFavorites(List<Favorites> favoritesList, Long memberId);

    /**
     * 修改收藏的商品/店铺
     *
     * @param favorites
     */
    void updateMemberFavorites(Favorites favorites);

    /**
     * 删除收藏的商品/店铺
     *
     * @param ids
     */
    void delMemberFavorites(Long[] ids);

    /**
     * 收藏的商品/店铺列表
     *
     * @param page
     * @param memberId
     * @return
     */
    IPage<FavoritesVo> getMemberFavoritesList(Page page, Long memberId, Long goodsId);

    /**
     * 缺货登记
     *
     * @param arrivalNotice
     * @param userId
     */
    void addArrivalNotice(ArrivalNotice arrivalNotice, Long userId);

    /**
     * 编辑货品
     *
     * @param goods
     */
    void editGoods(Goods goods, Long userId);

    /**
     * 添加厂家
     *
     * @param factory
     */
    void addFactory(Factory factory);

    /**
     * 修改厂家
     *
     * @param factory
     */
    void updateFactory(Factory factory);

    /**
     * 厂家是否推荐
     *
     * @param erpSupplyId
     * @param factoryRecommend
     */
    void updateFactoryRecommend(Long erpSupplyId, Integer factoryRecommend);

    /**
     * 编辑货品选厂家
     *
     * @return
     */
    List<Factory> getAllFactory(String factoryName);

    /**
     * 厂家列表
     *
     * @param page
     * @param factory
     * @return
     */
    IPage<FactoryVo> getFactoryList(Page page, FactoryVo factory);

    /**
     * 缺货列表
     *
     * @param page
     * @param arrivalNoticeVo
     * @return
     */
    IPage<ArrivalNoticeVo> getArrivalNoticeList(Page page, ArrivalNoticeVo arrivalNoticeVo);

    /**
     * 商品评价
     *
     * @param page
     * @param goodsId
     * @return
     */
    IPage<EvaluateGoods> getEvaluateGoodsList(Page page, Long goodsId);

    /**
     * 禁销限销列表
     *
     * @param page
     * @param bannedVo
     * @return
     */
    IPage<BannedVo> getBannedList(Page page, BannedVo bannedVo);

    /**
     * 货品集合总单
     *
     * @param page
     * @param bannedVo
     * @return
     */
    IPage<BannedVo> getAdminGoodsList(Page page, BannedVo bannedVo);

    /**
     * 货品集合细单
     *
     * @param page
     * @param bannedVo
     * @return
     */
    IPage<BannedVo> getAdminGoodsListById(Page page, BannedVo bannedVo);

    /**
     * 客户确认通知
     *
     * @param id
     */
    void informArrivalNotice(Long id);

    /**
     * 到货通知列表
     *
     * @param memberId
     * @return
     */
    List<ArrivalNoticeVo> getMemberArrivalNoticeList(Long memberId);

    /**
     * 修改到货通知加入购物车的状态
     *
     * @param ids
     */
    void updateArrivalNoticeStatus(Long[] ids);

    /**
     * 判断库存，处理到货通知
     */
    void dealWithArrivalNotice();

    /**
     * 近效期商品
     *
     * @param page
     * @param goodsInfoVo
     * @return
     */
    IPage<GoodsInfoVo> getValidityGoodsInfo(Page page, GoodsInfoVo goodsInfoVo);

    /**
     * 限销商品
     *
     * @param memberId
     * @param goodsIds
     * @return
     */
    List<GoodsInfoVo> goodsFilter(Long memberId, List<Long> goodsIds);

    /**
     * 不分页查询货品列表
     *
     * @param goodsInfoVo
     * @return
     */
    List<GoodsInfoVo> getGoodsListNotPage(GoodsInfoVo goodsInfoVo);

    /**
     * 同步erp药检报告到oss
     */
    void syncGoodsQualityImage(LocalDateTime startDate);

    /**
     * 活动策略列表
     *
     * @param page
     * @param activityVo
     * @return
     */
    IPage<ActivityVo> getActivityVoList(Page page, ActivityVo activityVo);

    /**
     * 活动商品
     *
     * @param page
     * @param asId
     * @return
     */
    IPage<ActivityGoods> getActivityGoodsList(Page page, Long asId);

    /**
     * 活动策略
     *
     * @param page
     * @param activityId
     * @return
     */
    IPage<ActivityStrategy> getActivityStrategyList(Page page, Long activityId);

    /**
     * 活动客户集合
     *
     * @param page
     * @param activityId
     * @return
     */
    IPage<ActivitySetVo> getActivitySetList(Page page, Long activityId);

    /**
     * 活动赠品
     *
     * @param page
     * @param erpGoodsId
     * @return
     */
    IPage<ActivityGift> getActivityGiftList(Page page, Long erpGoodsId, Long asId);

    /**
     * 热门品牌
     *
     * @return
     */
    List<FactoryVo> getHoldFactoryVoList();

    /**
     * 厂家置顶/置底排序
     *
     * @param factoryVo
     */
    void updateFactorySort(FactoryVo factoryVo);

    /**
     * 厂家上下排序
     *
     * @param erpSupplyIdPrev
     * @param erpSupplyIdNext
     */
    void factorySort(Long erpSupplyIdPrev, Long erpSupplyIdNext);

    /**
     * 前台缺货列表
     *
     * @param page
     * @param arrivalNoticeVo
     * @return
     */
    IPage<ArrivalNoticeVo> getPcArrivalNoticeList(Page page, ArrivalNoticeVo arrivalNoticeVo);

    /**
     * 热销商品
     *
     * @return
     */
    List<GoodsInfoVo> getGoodsInfoVoThree(Long customerId);

    /**
     * 我的足迹
     *
     * @param memberId
     * @param goodsId
     * @return
     */
    List<GoodsInfoVo> getMyFootAll(Long memberId, Long[] goodsId);

    /**
     * 后台分类列表
     *
     * @param page
     * @param goodsClassTypeVo
     * @return
     */
    IPage<GoodsClassTypeVo> getAdminGoodsClassTypeVoList(Page page, GoodsClassTypeVo goodsClassTypeVo);

    /**
     * 限时秒杀
     *
     * @param page
     * @param goodsInfoVo
     * @return
     */
    IPage<GoodsInfoVo> getGoodsInfoVoSecKill(Page page, GoodsInfoVo goodsInfoVo);

    /**
     * 积分编辑
     * @param goods
     */
    void editIntegral(Goods goods);

    /**
     * 搜索提示
     *
     * @param userId
     * @param goodspinyin
     * @return
     */
    List<SearchTipsVo> getGoodsInfoListReturnName(Long userId, String goodspinyin);

    /**
     * 热门搜索
     * @return
     */
    List<String> getHotSearch();

    /**
     * 替换购物车过期活动
     */
    void updateCartExpiredActivity();

    /**
     * 同一活动下的商品
     *
     * @param page
     * @param goodsInfoVo
     * @return
     */
    IPage<GoodsInfoVo> getActivityGoodsInfoList(Page page, GoodsInfoVo goodsInfoVo);

    /**
     * 优惠券下的商品
     *
     * @param page
     * @param couponId
     * @return
     */
    IPage<GoodsInfoVo> getCouponGoodsInfoList(@Param("page") Page page, @Param("customerId") Long customerId, @Param("couponId") Long couponId);

    /**
     * 接口下单清空客户对应门店的购物车商品
     *
     * @param memberId
     * @param storeId
     */
    void delMemberStoreCart(Long memberId,Long storeId);
}
