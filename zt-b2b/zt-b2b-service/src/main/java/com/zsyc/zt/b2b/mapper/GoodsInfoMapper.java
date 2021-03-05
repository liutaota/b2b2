package com.zsyc.zt.b2b.mapper;

import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.Goods;
import com.zsyc.zt.b2b.entity.MemberLog;
import com.zsyc.zt.b2b.vo.*;
import com.zsyc.zt.inca.entity.BmsForbidSales;
import com.zsyc.zt.inca.entity.PubGoodsClass;
import com.zsyc.zt.inca.entity.PubGoodsClassDtl;
import com.zsyc.zt.inca.entity.PubGoodsPrice;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface GoodsInfoMapper extends BaseMapper<GoodsInfoVo> {

    /**
     * 商品分类列表
     *
     * @param goodsClassTypeVo
     * @return
     */
    List<GoodsClassTypeVo> getGoodsClassTypeVoList(@Param("goodsClassTypeVo") GoodsClassTypeVo goodsClassTypeVo);

    /**
     * 商品详情-分类
     */
    List<GoodsClassTypeVo> goodsClassTypeVoList(@Param("classnRow") Long classnRow);

    /**
     * 商品列表
     *
     * @param page
     * @param goodsInfoVo
     * @return
     */
    IPage<GoodsInfoVo> getGoodsInfoList(@Param("page") Page page, @Param("goodsInfoVo") GoodsInfoVo goodsInfoVo);

    /**
     * 新品上架
     *
     * @param page
     * @param goodsInfoVo
     * @return
     */
    IPage<GoodsInfoVo> getNewGoodsInfoVoList(@Param("page") Page page, @Param("goodsInfoVo") GoodsInfoVo goodsInfoVo);

    /**
     * 商品列表不分页
     *
     * @param goodsInfoVo
     * @return
     */
    List<GoodsInfoVo> getGoodsInfoList(@Param("goodsInfoVo") GoodsInfoVo goodsInfoVo);

    /**
     * 后台商品列表
     *
     * @param page
     * @param goodsInfoVo
     * @return
     */
    IPage<GoodsInfoVo> getAdminGoodsInfoList(@Param("page") Page page, @Param("goodsInfoVo") GoodsInfoVo goodsInfoVo);

    /**
     * 商品详情
     *
     * @param id
     * @return
     */
    GoodsInfoVo getGoodsInfo(@Param("id") Long id, @Param("customerId") Long customerId, @Param("storageId") Integer storageId);

    /**
     * 后台商品详情
     *
     * @param goodsid
     * @return
     */
    GoodsInfoVo getAdminGoodsInfo(@Param("goodsid") Long goodsid, @Param("storageId") Integer storageId);


    /**
     * 商品价格
     *
     * @param goodsId
     * @return
     */
    @Cached(name = "getPubGoodsPriceList",key="#goodsId+''+#customerId", expire = 1)
    List<PubGoodsPriceVo> getPubGoodsPriceList(@Param("goodsId") Long goodsId, @Param("customerId") Long customerId);

    /**
     * 特价商品价格
     *
     * @param goodsId
     * @return
     */
    @Cached(name = "getPubGoodsPriceList5",key="#goodsId+''+#customerId", expire = 1)
    List<PubGoodsPriceVo> getPubGoodsPriceList5(@Param("goodsId") Long goodsId, @Param("customerId") Long customerId);

    /**
     * 商品库存
     *
     * @param goodsId
     * @param customId
     * @param storageIds
     * @return
     */
    Integer getStqty(@Param("goodsId") Long goodsId, @Param("customId") Long customId, @Param("storageIds") Long[] storageIds, @Param("isGift") Long isGift, @Param("lotid") Long lotid);


    /**
     * 商品库存
     *
     * @param goodsId
     * @param customId
     * @param storageIds
     * @return
     */
    Integer getStqty1(@Param("goodsId") Long goodsId, @Param("customId") Long customId, @Param("storageIds") Long[] storageIds, @Param("isGift") Long isGift, @Param("lotid") Long lotid);

    /**
     * 判断禁销
     *
     * @param customId
     * @param goodsId
     * @param entryId
     * @param useStatuss
     * @return
     */
    List<BmsForbidSales> selectOfForbidBy(@Param("customId") Long customId, @Param("goodsId") Long goodsId, @Param("entryId") int entryId, @Param("useStatus") int useStatuss);


    /**
     * 判断限销
     *
     * @param goodsId
     * @param entryId
     * @param useStatuss
     * @return
     */
    List<BmsForbidSales> selectOfRestrictBy(@Param("customId") Long customId, @Param("goodsId") Long goodsId, @Param("entryId") int entryId, @Param("useStatus") int useStatuss);

    /**
     * 禁销限销列表
     *
     * @param page
     * @param bannedVo
     * @return
     */
    IPage<BannedVo> getBannedList(@Param("page") Page page, @Param("bannedVo") BannedVo bannedVo);

    /**
     * 货品集合总单
     *
     * @param page
     * @param bannedVo
     * @return
     */
    IPage<BannedVo> getAdminGoodsList(@Param("page") Page page, @Param("bannedVo") BannedVo bannedVo);

    /**
     * 货品集合
     *
     * @param goodsSetId
     * @return
     */
    BannedVo getAdminGoods(@Param("goodsSetId") Long goodsSetId);

    /**
     * 货品集合细单
     *
     * @param page
     * @param bannedVo
     * @return
     */
    IPage<BannedVo> getAdminGoodsListById(@Param("page") Page page, @Param("bannedVo") BannedVo bannedVo);

    /**
     * 货品导出
     *
     * @param goodsInfoVo
     * @return
     */
    List<GoodsInfoVo> getGoodsListExcel(@Param("goodsInfoVo") GoodsInfoVo goodsInfoVo);

    /**
     * 近效期商品
     *
     * @param page
     * @param goodsInfoVo
     * @return
     */
    IPage<GoodsInfoVo> getValidityGoodsInfo(@Param("page") Page page, @Param("goodsInfoVo") GoodsInfoVo goodsInfoVo);

    /**
     * 近效期商品
     *
     * @param goodsInfoVo
     * @return
     */
    List<GoodsInfoVo> getValidityGoodsInfo(@Param("goodsInfoVo") GoodsInfoVo goodsInfoVo);

    /**
     * 近销商品详情
     *
     * @param goodsId
     * @param customid
     * @return
     */
    GoodsInfoVo getValidityGoodsInfoById(@Param("goodsId") Long goodsId, @Param("customid") Long customid, @Param("lotid") Long lotid);

    /**
     * 货品列表不分页
     *
     * @param goodsInfoVo
     * @return
     */
    List<GoodsInfoVo> getGoodsListNotPage(@Param("goodsInfoVo") GoodsInfoVo goodsInfoVo);

    /**
     * 获取某一时间之后的所有药检图片
     *
     * @param startTime
     * @return
     */
    List<GoodsQualityVo> getGoodsQuality(@Param("startTime") LocalDateTime startTime);

    /**
     * 热销商品
     *
     * @return
     */
    List<GoodsInfoVo> getGoodsInfoVoThree(@Param("customerId") Long customerId);

    /**
     * 我的足迹
     *
     * @param memberId
     * @param goodsId
     * @return
     */
    List<GoodsInfoVo> getMyFootAll(@Param("memberId") Long memberId, @Param("goodsId") Long[] goodsId);

    /**
     * 后台分类列表
     *
     * @param page
     * @param goodsClassTypeVo
     * @return
     */
    IPage<GoodsClassTypeVo> getAdminGoodsClassTypeVoList(@Param("page") Page page, @Param("goodsClassTypeVo") GoodsClassTypeVo goodsClassTypeVo);

    /**
     * 限时秒杀
     *
     * @param page
     * @param goodsInfoVo
     * @return
     */
    IPage<GoodsInfoVo> getGoodsInfoVoSecKill(@Param("page") Page page, @Param("goodsInfoVo") GoodsInfoVo goodsInfoVo);

    /**
     * 积分商品列表
     *
     * @param page
     * @param goodsInfoVo
     * @return
     */
    IPage<GoodsInfoVo> getIntegralGoodsInfoList(@Param("page") Page page, @Param("goodsInfoVo") GoodsInfoVo goodsInfoVo);

    /**
     * 积分商品详情
     * @param goodsId
     * @return
     */
    GoodsInfoVo getIntegralGoodsInfo(@Param("goodsId") Long goodsId, @Param("memberId") Long memberId);

    /**
     * 同一活动下的商品
     *
     * @param page
     * @param goodsInfoVo
     * @return
     */
    IPage<GoodsInfoVo> getActivityGoodsInfoList(@Param("page") Page page, @Param("goodsInfoVo") GoodsInfoVo goodsInfoVo);

    /**
     * 优惠券下的商品
     *
     * @param page
     * @param couponId
     * @return
     */
    IPage<GoodsInfoVo> getCouponGoodsInfoList(@Param("page") Page page,@Param("customerId") Long customerId, @Param("couponId") Long couponId);
}
