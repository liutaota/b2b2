package com.zsyc.zt.b2b.api.controller.PCWeb;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.entity.*;

import com.zsyc.zt.b2b.service.*;
import com.zsyc.zt.b2b.vo.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by tang on 2020-07-27.
 */
@Api
@RestController
@RequestMapping("api/pcGoods")
@Slf4j
public class PCGoodsController {
    @Reference
    private GoodsService goodsService;
    @Autowired
    private AccountHelper accountHelper;
    @Reference
    private HelpService helpService;
    @Reference
    private HelpTypeService helpTypeService;
    @Reference
    private AdvPositionService advPositionService;
    @Reference
    private FloorService floorService;
    @Reference
    private WebPageService webPageService;
    @Reference
    private NewProductService newProductService;

    @ApiOperation("商品分类列表")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getGoodsClassTypeVoList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classnRow1", value = "分类id", dataType = "Long"),
            @ApiImplicitParam(name = "classname", value = "分类名称", dataType = "String"),
    })
    public List<GoodsClassTypeVo> getGoodsClassTypeVoList(GoodsClassTypeVo goodsClassTypeVo) {
        return this.goodsService.getGoodsClassTypeVoList(goodsClassTypeVo);
    }

    @ApiOperation("商品列表")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getGoodsInfoList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "classname", value = "分类名称", dataType = "String"),
            @ApiImplicitParam(name = "goodspinyin", value = "搜索（拼音首字母，通用名，商品名，商品ID，分类，条形码，厂家，功效）", dataType = "String"),
            @ApiImplicitParam(name = "goodsname", value = "商品名", dataType = "String"),
            @ApiImplicitParam(name = "currencyname", value = "通用名", dataType = "String"),
            @ApiImplicitParam(name = "factoryname", value = "厂家", dataType = "String"),
            @ApiImplicitParam(name = "factoryid", value = "厂家id", dataType = "String"),
            @ApiImplicitParam(name = "classnRow3", value = "分类号3", dataType = "Long"),
            @ApiImplicitParam(name = "classnRow2", value = "分类号2", dataType = "Long"),
            @ApiImplicitParam(name = "classnRow1", value = "分类号1", dataType = "Long"),
            @ApiImplicitParam(name = "floorId", value = "楼层id", dataType = "Long"),
            @ApiImplicitParam(name = "webPageId", value = "专区id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "stairClassify", value = "专区分类1", dataType = "String"),
            @ApiImplicitParam(name = "accessName", value = "专区分类2", dataType = "String"),
            @ApiImplicitParam(name = "AdvGoodsId", value = "广告里商品id搜索", dataType = "String"),
    })
    public IPage<GoodsInfoVo> getGoodsInfoList(Page page, GoodsInfoVo goodsInfoVo) {
        goodsInfoVo.setMemberId(goodsInfoVo.getMemberId() != null ? goodsInfoVo.getMemberId() : this.accountHelper.getUserId());
        if (null != goodsInfoVo.getWebPageId() && goodsInfoVo.getWebPageId() > 0) {
            return this.webPageService.getWebPageSearchList(page, goodsInfoVo);
        }
        return this.goodsService.getGoodsInfoList(page, goodsInfoVo);
    }

    @ApiOperation("搜索提示")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @ApiImplicitParam(name = "goodspinyin", value = "拼音", dataType = "String")
    @GetMapping("getGoodsInfoListReturnName")
    public List<SearchTipsVo> getGoodsInfoListReturnName(String goodspinyin) {
        return this.goodsService.getGoodsInfoListReturnName(this.accountHelper.getUserId(), goodspinyin);
    }

    @ApiOperation("热门搜索")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping("getHotSearch")
    public List<String> getHotSearch() {
        return this.goodsService.getHotSearch();
    }

    @ApiOperation("积分商品列表")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getIntegralGoodsInfoList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodspinyin", value = "拼音搜索", dataType = "String"),
            @ApiImplicitParam(name = "goodsname", value = "商品名称", dataType = "String"),
    })
    public IPage<GoodsInfoVo> getIntegralGoodsInfoList(Page page, GoodsInfoVo goodsInfoVo) {
        goodsInfoVo.setMemberId(this.accountHelper.getUserId());
        return this.goodsService.getIntegralGoodsInfoList(page, goodsInfoVo);
    }

    @ApiOperation("厂家搜索")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getFactoryVoList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classname", value = "分类名称", dataType = "String"),
            @ApiImplicitParam(name = "goodspinyin", value = "搜索（拼音首字母，通用名，商品名，商品ID，分类，条形码，厂家，功效）", dataType = "String"),
            @ApiImplicitParam(name = "goodsname", value = "商品名", dataType = "String"),
            @ApiImplicitParam(name = "currencyname", value = "通用名", dataType = "String"),
            @ApiImplicitParam(name = "factoryname", value = "厂家", dataType = "String"),
            @ApiImplicitParam(name = "factoryid", value = "厂家id", dataType = "String"),
            @ApiImplicitParam(name = "classnRow3", value = "分类号3", dataType = "Long"),
            @ApiImplicitParam(name = "classnRow2", value = "分类号2", dataType = "Long"),
            @ApiImplicitParam(name = "classnRow1", value = "分类号1", dataType = "Long"),
            @ApiImplicitParam(name = "webPageId", value = "专区id", required = true, dataType = "Long"),
    })
    public List<FactoryVo> getFactoryVoList(GoodsInfoVo goodsInfoVo) {
        goodsInfoVo.setMemberId(this.accountHelper.getUserId());
        if (null != goodsInfoVo.getWebPageId() && goodsInfoVo.getWebPageId() > 0) {
            return this.webPageService.getWebPageFactoryVoList(goodsInfoVo);
        }
        return this.goodsService.getFactoryVoList(goodsInfoVo);
    }


    @ApiOperation("商品详情")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getGoodsInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsid", value = "商品id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "lotid", value = "批号id", dataType = "Integer"),
            @ApiImplicitParam(name = "storageId", value = "保管帐id", required = true, dataType = "Integer"),
    })
    public GoodsInfoVo getGoodsInfo(Long goodsid, Long lotid, Integer storageId) {
        return this.goodsService.getGoodsInfo(goodsid, this.accountHelper.getUserId() != null ? this.accountHelper.getUserId() : 0L, lotid, storageId);
    }

    @ApiOperation("活动商品的赠品信息")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getActivityGiftVoList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "asId", value = "策略id", required = true, dataType = "Long"),
    })
    public List<ActivityGiftVo> getActivityGiftVoList(Long asId) {
        return this.goodsService.getActivityGiftVoList(asId);
    }

    @ApiOperation("添加商品到购物车")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @PostMapping(value = "addMemberCart")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", value = "商品id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "erpGoodsId", value = "erp商品id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodsName", value = "商品名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goodsPrice", value = "价格", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "goodsNum", value = "数量", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "goodsImage", value = "商品图片", required = true, dataType = "Integer"),

    })
    public void addMemberCart(@RequestBody List<Cart> cartList) {
        this.goodsService.addMemberCart(cartList, this.accountHelper.getUserId());
    }

    @ApiOperation("修改购物车商品")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @PostMapping(value = "updateMemberCart")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "购物车id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodsId", value = "商品id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodsName", value = "商品名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goodsPrice", value = "价格", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "goodsNum", value = "数量", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "goodsImage", value = "商品图片", required = true, dataType = "Integer"),
    })
    public void updateMemberCart(@RequestBody Cart cart) {
        this.goodsService.updateMemberCart(cart);
    }

    @ApiOperation("修改商品活动id")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "updateCartActivity")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "活动id", required = true, dataType = "Long"),
    })
    public void updateCartActivity(Long activityId) {
        this.goodsService.updateCartActivity(this.accountHelper.getUserId(), activityId);
    }

    @ApiOperation("购物车商品参与的活动")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getMyCartJoinActivity")
    @ApiImplicitParams({
    })
    public List<ActivityVo> getMyCartJoinActivity() {
        return this.goodsService.getMyCartJoinActivity(this.accountHelper.getUserId());
    }

    @ApiOperation("是否全选购物车商品")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "isPitchOnCart")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pitchOn", value = "2/1", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "memberId", value = "memberId", required = true, dataType = "Long"),
    })
    public void isPitchOnCart(Long pitchOn, Long memberId) {
        this.goodsService.isPitchOnCart(pitchOn, memberId != null ? memberId : this.accountHelper.getUserId());
    }

    @ApiOperation("删除购物车商品")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "delMemberCart")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "购物车id", required = true, dataType = "Long[]"),
    })
    public void delMemberCart(Long[] ids) {
        this.goodsService.delMemberCart(ids);
    }


    @ApiOperation("购物车列表")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getMemberCartList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodsName", value = "商品名称/厂家/erpgoodsId", dataType = "String"),
    })
    public IPage<CartVo> getMemberCartList(Page page, CartVo cartVo) {
        cartVo.setMemberId(this.accountHelper.getUserId());
        return this.goodsService.getMemberCartList(page, cartVo);
    }

    @ApiOperation("购物车不分页")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getMemberCartAll")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsName", value = "商品名称/厂家/erpgoodsId", dataType = "String"),
    })
    public List<CartVo> getMemberCartAll(CartVo cartVo) {
        cartVo.setMemberId(this.accountHelper.getUserId());
        return this.goodsService.getMemberCartAll(cartVo);
    }

    @ApiOperation("我的购物车数量")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getMemberCartTotal")
    @ApiImplicitParams({

    })
    public Integer getMemberCartTotal() {
        return this.goodsService.getMemberCartTotal(this.accountHelper.getUserId());
    }


    @ApiOperation("收藏商品/店铺")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @PostMapping(value = "addMemberFavorites")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gcId", value = "商品分类id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "favId", value = "收藏对像id，如商品id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "favType", value = "类型：goods为商品", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goodsName", value = "类型：goods为商品", required = true, dataType = "String"),
            @ApiImplicitParam(name = "logPrice", value = "商品收藏时价格", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "logMsg", value = "收藏备注", dataType = "String"),
    })
    public List<Favorites> addMemberFavorites(@RequestBody List<Favorites> favoritesList) {
        return this.goodsService.addMemberFavorites(favoritesList, this.accountHelper.getUserId());
    }


    @ApiOperation("修改收藏的商品/店铺")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @PostMapping(value = "updateMemberFavorites")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "收藏id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "gcId", value = "商品分类id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "favId", value = "收藏对像id，如商品id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "favType", value = "类型：goods为商品", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goodsName", value = "类型：goods为商品", required = true, dataType = "String"),
            @ApiImplicitParam(name = "logPrice", value = "商品收藏时价格", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "logMsg", value = "收藏备注", dataType = "String"),
    })
    public void updateMemberFavorites(@RequestBody Favorites favorites) {
        this.goodsService.updateMemberFavorites(favorites);
    }


    @ApiOperation("删除收藏的商品/店铺")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "delMemberFavorites")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "收藏id", required = true, dataType = "Long[]"),
    })
    public void delMemberFavorites(Long[] ids) {
        this.goodsService.delMemberFavorites(ids);
    }

    @ApiOperation("收藏的商品/店铺列表")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getMemberFavoritesList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
    })
    public IPage<FavoritesVo> getMemberFavoritesList(Page page, Long memberId, Long goodsId) {
        return this.goodsService.getMemberFavoritesList(page, this.accountHelper.getUserId(), goodsId);
    }


    @ApiOperation("缺货登记")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @PostMapping(value = "addArrivalNotice")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "erpGoodsId", value = "erp商品id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodsName", value = "商品名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "sellTime", value = "预计购买时间", dataType = "String"),
            @ApiImplicitParam(name = "anEmail", value = "邮箱", dataType = "String"),
            @ApiImplicitParam(name = "anMobile", value = "手机号", dataType = "String"),
            @ApiImplicitParam(name = "goodsNum", value = "数量", required = true, dataType = "int"),
            @ApiImplicitParam(name = "erpGoodsUnit", value = "erp单位", required = true, dataType = "String"),
            @ApiImplicitParam(name = "erpGoodsType", value = "erp规格", required = true, dataType = "String"),
            @ApiImplicitParam(name = "erpFactoryName", value = "厂家", required = true, dataType = "String"),
            @ApiImplicitParam(name = "packingBox", value = "包装", required = true, dataType = "String"),

    })
    public void addArrivalNotice(@RequestBody ArrivalNotice arrivalNotice) {
        arrivalNotice.setMemberId(this.accountHelper.getUserId());
        this.goodsService.addArrivalNotice(arrivalNotice, this.accountHelper.getUserId());
    }


    @ApiOperation("商品评价")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getEvaluateGoodsList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodsId", value = "商品id", required = true, dataType = "Long"),
    })
    public IPage<EvaluateGoods> getEvaluateGoodsList(Page page, Long goodsId) {
        return this.goodsService.getEvaluateGoodsList(page, goodsId);
    }

    @ApiOperation("客户确认通知")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "informArrivalNotice")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long[]"),
    })
    public void informArrivalNotice(Long id) {
        this.goodsService.informArrivalNotice(id);
    }

    @ApiOperation("到货通知列表")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getMemberArrivalNoticeList")
    @ApiImplicitParams({
    })
    public List<ArrivalNoticeVo> getMemberArrivalNoticeList() {
        return this.goodsService.getMemberArrivalNoticeList(this.accountHelper.getUserId());
    }


    @ApiOperation("修改到货通知加入购物车的状态")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "updateArrivalNoticeStatus")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "id", required = true, dataType = "Long[]"),
    })
    public void updateArrivalNoticeStatus(Long[] ids) {
        this.goodsService.updateArrivalNoticeStatus(ids);
    }

    @ApiOperation("帮助类型列表")
    @GetMapping("getHelpTypeList")
    public List<HelpTypeVo> getHelpTypeList() {
        return this.helpTypeService.getHelpTypeVoList();
    }

    @ApiOperation("广告位列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "advName", value = "广告位名称", dataType = "String"),
            @ApiImplicitParam(name = "apDisplay", value = "广告样式", dataType = "String")
    })
    @GetMapping(value = "getAdvPosition")
    public List<AdvPositionVo> getAdvPosition(AdvPositionVo advPositionVo) {
        return this.advPositionService.getAdvPositionAll(advPositionVo);
    }

    @ApiOperation("近效期商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "classname", value = "分类名称", dataType = "String"),
            @ApiImplicitParam(name = "goodspinyin", value = "搜索（拼音首字母，通用名，商品名，商品ID，分类，条形码，厂家，功效）", dataType = "String"),
            @ApiImplicitParam(name = "goodsname", value = "商品名", dataType = "String"),
            @ApiImplicitParam(name = "currencyname", value = "通用名", dataType = "String"),
            @ApiImplicitParam(name = "factoryname", value = "厂家", dataType = "String"),
            @ApiImplicitParam(name = "classnRow3", value = "分类号3", dataType = "Long"),
            @ApiImplicitParam(name = "classnRow2", value = "分类号2", dataType = "Long"),
            @ApiImplicitParam(name = "classnRow1", value = "分类号1", dataType = "Long"),
    })
    @GetMapping("getValidityGoodsInfo")
    public IPage<GoodsInfoVo> getValidityGoodsInfo(Page page, GoodsInfoVo goodsInfoVo) {
        goodsInfoVo.setMemberId(this.accountHelper.getUserId());
        return this.goodsService.getValidityGoodsInfo(page, goodsInfoVo);
    }

    @ApiOperation("新品上架")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
    })
    @GetMapping("getNewGoodsInfoVoList")
    public IPage<GoodsInfoVo> getNewGoodsInfoVoList(Page page, GoodsInfoVo goodsInfoVo) {
        goodsInfoVo.setMemberId(this.accountHelper.getUserId());
        return this.goodsService.getNewGoodsInfoVoList(page, goodsInfoVo);
    }

    @ApiOperation("热门品牌")
    @GetMapping(value = "getHoldFactoryVoList")
    @ApiImplicitParams({
    })
    public List<FactoryVo> getHoldFactoryVoList() {
        return this.goodsService.getHoldFactoryVoList();
    }

    @ApiOperation("前台缺货列表")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getPcArrivalNoticeList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "memberId", value = "客户Id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodsId", value = "商品编号", dataType = "Long"),
            @ApiImplicitParam(name = "anStatus", value = "状态", dataType = "String"),
            @ApiImplicitParam(name = "goodsPinYin", value = "商品编号/名称查询", dataType = "String")
    })
    public IPage<ArrivalNoticeVo> getPcArrivalNoticeList(Page page, ArrivalNoticeVo arrivalNoticeVo) {
        arrivalNoticeVo.setMemberId(this.accountHelper.getUserId());
        return this.goodsService.getPcArrivalNoticeList(page, arrivalNoticeVo);
    }

    @ApiOperation("新品登记")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsName", value = "药品名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "spec", value = "药品规格", required = true, dataType = "String"),
            @ApiImplicitParam(name = "num", value = "需求数量", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "approvedocno", value = "批准文号", dataType = "String"),
            @ApiImplicitParam(name = "price", value = "建议价格", dataType = "Long"),
            @ApiImplicitParam(name = "channel", value = "货源渠道", dataType = "String"),
            @ApiImplicitParam(name = "companyName", value = "生产厂家", dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
            @ApiImplicitParam(name = "photos", value = "图片", dataType = "String"),
    })
    @PostMapping("newProductRegistration")
    public void newProductRegistration(@RequestBody NewProductVo newProduct) {
        newProduct.setMemberId(this.accountHelper.getUserId());
        this.newProductService.newProductRegistration(newProduct);
    }

    @ApiOperation("前台新品登记列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodsName", value = "商品名称", dataType = "String"),
            @ApiImplicitParam(name = "approvedocno", value = "批准文号", dataType = "String"),
    })
    @GetMapping("getNewProducts")
    public IPage<NewProductVo> getNewProducts(Page page, NewProductVo newProductVo) {
        newProductVo.setMemberId(this.accountHelper.getUserId());
        return this.newProductService.getNewProducts(page, newProductVo);
    }

    @ApiOperation("热销商品")
    @ApiImplicitParams({

    })
    @GetMapping("getGoodsInfoVoThree")
    public List<GoodsInfoVo> getGoodsInfoVoThree() {
        return this.goodsService.getGoodsInfoVoThree(this.accountHelper.getUserId());
    }

    @ApiOperation("我的足迹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", value = "goodsId", required = true, dataType = "Long[]"),
    })
    @GetMapping("getMyFootAll")
    public List<GoodsInfoVo> getMyFootAll(Long[] goodsId) {
        return this.goodsService.getMyFootAll(this.accountHelper.getUserId(), goodsId);
    }


    @ApiOperation("限时秒杀")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
    })
    @GetMapping("getGoodsInfoVoSecKill")
    public IPage<GoodsInfoVo> getGoodsInfoVoSecKill(Page page, GoodsInfoVo goodsInfoVo) {
        goodsInfoVo.setMemberId(this.accountHelper.getUserId());
        return this.goodsService.getGoodsInfoVoSecKill(page, goodsInfoVo);
    }

    @ApiOperation("同一活动下的商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "activityId", value = "activityId", required = true, dataType = "Long"),
    })
    @GetMapping("getActivityGoodsInfoList")
    public IPage<GoodsInfoVo> getActivityGoodsInfoList(Page page, GoodsInfoVo goodsInfoVo) {
        goodsInfoVo.setMemberId(this.accountHelper.getUserId());
        return this.goodsService.getActivityGoodsInfoList(page, goodsInfoVo);
    }

    @ApiOperation("优惠券下的商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "couponId", value = "couponId", required = true, dataType = "Long"),
    })
    @GetMapping("getCouponGoodsInfoList")
    public IPage<GoodsInfoVo> getCouponGoodsInfoList(Page page, Long couponId) {
        return this.goodsService.getCouponGoodsInfoList(page, this.accountHelper.getUserId(), couponId);
    }

    @ApiOperation("帮助指南详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
    })
    @GetMapping("getHelpByIdInfo")
    public Help getHelpByIdInfo(Long id) {
        return this.helpService.getHelpByIdInfo(id);
    }
}
