package com.zsyc.zt.b2b.api.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.entity.Class;
import com.zsyc.zt.b2b.service.*;
import com.zsyc.zt.b2b.vo.*;
import io.swagger.annotations.*;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api
@RestController
@RequestMapping("api/goods")
public class AdminGoodsController {

    @Reference
    private GoodsService goodsService;
    @Reference
    private ClassService classService;
    @Reference
    private NewProductService newProductService;

    @Autowired
    private AccountHelper accountHelper;

    @Reference
    private AdminService adminService;

    @ApiOperation("货品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodsid", value = "货品id", dataType = "Long"),
            @ApiImplicitParam(name = "barcode", value = "条形码", dataType = "String"),
            @ApiImplicitParam(name = "goodsname", value = "商品名", dataType = "String"),
            @ApiImplicitParam(name = "factoryname", value = "生产商", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
            @ApiImplicitParam(name = "isImg", value = "无图片", dataType = "Boolean"),
            @ApiImplicitParam(name = "isRacking", value = "是否上架", dataType = "String"),
            @ApiImplicitParam(name = "isMinimumSales", value = "是否有最小销售数量", dataType = "Boolean"),
            @ApiImplicitParam(name = "imgNum", value = "图片数量", dataType = "Integer"),
            @ApiImplicitParam(name = "classnRow3", value = "分类编号", dataType = "Long"),
    })
    @GetMapping(value = "goodsList")
    public IPage<GoodsInfoVo> goodsList(Page page, GoodsInfoVo goodsInfoVo) {
        return this.goodsService.getAdminGoodsInfoList(page, goodsInfoVo);
    }

    @ApiOperation("商品详情")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getAdminGoodsInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsid", value = "商品id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "storageId", value = "storageId", required = true, dataType = "Integer"),
    })
    public GoodsInfoVo getGoodsInfo(Long goodsid,Integer storageId) {
        return this.goodsService.getAdminGoodsInfo(goodsid,storageId);
    }

    @ApiOperation("编辑货品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "erpGoodsId", value = "erp商品id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodsImg", value = "商品图片，分号隔开", required = true, dataType = "String"),
            @ApiImplicitParam(name = "description", value = "详情", dataType = "String"),
            @ApiImplicitParam(name = "goodsCommend", value = "商品推荐，1是，0否，默认0", dataType = "Integer"),
            @ApiImplicitParam(name = "haveGift", value = "是否有赠品", dataType = "Integer"),
            @ApiImplicitParam(name = "factoryId", value = "厂家id", dataType = "Long"),
            @ApiImplicitParam(name = "erpAccFlag", value = "是否积分商品/赠品", dataType = "Integer"),
            @ApiImplicitParam(name = "convertibleIntegral", value = "可兑换积分", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "integralGoods", value = "积分商品上下架：0为下架，1为上架", required = true, dataType = "Long"),
    })
    @PostMapping("editGoods")
    public void editGoods(@RequestBody GoodsInfoVo goods, HttpServletRequest request) {
        this.goodsService.editGoods(goods, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("编辑货品-->id：" + goods.getErpGoodsId() + "图片：" + (goods.getGoodsImg() != null ? goods.getGoodsImg() : "")).setIp(this.accountHelper.getIP(request))
                .setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminGoodsController&editGoods"));
    }

    @ApiOperation("积分编辑")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "erpGoodsId", value = "erp商品id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "convertibleIntegral", value = "可兑换积分", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "integralGoods", value = "积分商品上下架：0为下架，1为上架", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "erpAccFlag", value = "是否积分商品", required = true, dataType = "Integer"),
    })
    @PostMapping("editIntegral")
    public void editIntegral(@RequestBody GoodsInfoVo goods, HttpServletRequest request) {
        this.goodsService.editIntegral(goods);
        this.adminService.addAdminLog(new AdminLog().setContent("积分编辑-->id：" + goods.getErpGoodsId() + "积分：" + (goods.getConvertibleIntegral())).setIp(this.accountHelper.getIP(request))
                .setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminGoodsController&editIntegral"));
    }

    @ApiOperation("添加厂家--应该用不上")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "factoryName", value = "厂家名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "factoryInitial", value = "厂家首字母", required = true, dataType = "String"),
            @ApiImplicitParam(name = "factoryClass", value = "类别名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "factoryPic", value = "图片", required = true, dataType = "String"),
            @ApiImplicitParam(name = "factorySort", value = "排序", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "factoryRecommend", value = "推荐，0为否，1为是，默认0", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "factoryIntroduction", value = "厂家详情介绍", required = true, dataType = "String"),
    })
    @PostMapping("addFactory")
    public void addFactory(@RequestBody Factory factory, HttpServletRequest request) {
        this.goodsService.addFactory(factory);
        this.adminService.addAdminLog(new AdminLog().setContent("添加厂家").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminGoodsController&addFactory"));
    }

    @ApiOperation("修改厂家")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "erpSupplyId", value = "erp厂家id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "factoryName", value = "厂家名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "factoryInitial", value = "厂家首字母", required = true, dataType = "String"),
            @ApiImplicitParam(name = "factoryClass", value = "类别名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "factoryPic", value = "图片", required = true, dataType = "String"),
            @ApiImplicitParam(name = "factorySort", value = "排序", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "factoryRecommend", value = "推荐，0为否，1为是，默认0", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "factoryIntroduction", value = "厂家详情介绍", required = true, dataType = "String"),
    })
    @PostMapping("updateFactory")
    public void updateFactory(@RequestBody Factory factory, HttpServletRequest request) {
        this.goodsService.updateFactory(factory);
        this.adminService.addAdminLog(new AdminLog().setContent("修改厂家-->id：" + factory.getId() + "图片：" + (factory.getFactoryPic() != null ? factory.getFactoryPic() : "")).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminGoodsController&updateFactory"));
    }

    @ApiOperation("厂家置顶/置底排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "erpSupplyId", value = "erp厂家id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "sortType", value = "排序类型", required = true, dataType = "String")
    })
    @GetMapping("updateFactorySort")
    public void updateFactorySort(FactoryVo factoryVo, HttpServletRequest request) {
        this.goodsService.updateFactorySort(factoryVo);
        this.adminService.addAdminLog(new AdminLog().setContent("厂家id：" + factoryVo.getErpSupplyId() + "-->置顶/置底排序").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminGoodsController&topFactory"));
    }

    @ApiOperation("厂家上下排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "erpSupplyIdPrev", value = "上一个erp厂家id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "erpSupplyIdNext", value = "下一个erp厂家id", required = true, dataType = "Long")
    })
    @GetMapping("factorySort")
    public void factorySort(Long erpSupplyIdPrev, Long erpSupplyIdNext, HttpServletRequest request) {
        this.goodsService.factorySort(erpSupplyIdPrev, erpSupplyIdNext);
        this.adminService.addAdminLog(new AdminLog().setContent("厂家id：" + erpSupplyIdPrev + "," + erpSupplyIdNext + "-->上下排序").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminGoodsController&topFactory"));
    }

    @ApiOperation("厂家是否推荐")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "updateFactoryRecommend")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "erpSupplyId", value = "erp厂家id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "factoryRecommend", value = "是否推荐", required = true, dataType = "Integer"),
    })
    public void updateFactoryRecommend(Long erpSupplyId, Integer factoryRecommend, HttpServletRequest request) {
        this.goodsService.updateFactoryRecommend(erpSupplyId, factoryRecommend);
        this.adminService.addAdminLog(new AdminLog().setContent("厂家是否推荐").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminGoodsController&updateFactoryRecommend"));
    }

    @ApiOperation("编辑货品选厂家")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getAllFactory")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "factoryName", value = "厂家名称", dataType = "String"),
    })
    public List<Factory> getAllFactory(String factoryName) {
        return this.goodsService.getAllFactory(factoryName);
    }


    @ApiOperation("厂家列表")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getFactoryList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "factoryName", value = "厂家名称", dataType = "String"),
            @ApiImplicitParam(name = "isImg", value = "是否有图片", dataType = "Boolean")
    })
    public IPage<FactoryVo> getFactoryList(Page page, FactoryVo factory) {
        return this.goodsService.getFactoryList(page, factory);
    }

    @ApiOperation("缺货列表")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getArrivalNoticeList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodsId", value = "商品编号", dataType = "Long"),
            @ApiImplicitParam(name = "anStatus", value = "状态", dataType = "String"),
            @ApiImplicitParam(name = "goodsPinYin", value = "商品编号/名称查询", dataType = "String")
    })
    public IPage<ArrivalNoticeVo> getArrivalNoticeList(Page page, ArrivalNoticeVo arrivalNoticeVo) {
       // arrivalNoticeVo.setMemberId(this.accountHelper.getUserId());
        return this.goodsService.getArrivalNoticeList(page, arrivalNoticeVo);
    }

    @ApiOperation("缺货登记")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "", value = "货品名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "", value = "商品编号", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "", value = "数量", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "", value = "会员名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "", value = "状态", required = true, dataType = "String"),
            @ApiImplicitParam(name = "", value = "备注", required = true, dataType = "String"),
            @ApiImplicitParam(name = "", value = "截止时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "", value = "添加时间", required = true, dataType = "String"),
    })
    @PostMapping(value = "addArrivalNotice")
    public void addArrivalNotice(ArrivalNoticeVo arrivalNoticeVo, HttpServletRequest request) {
        this.goodsService.addArrivalNotice(arrivalNoticeVo, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("缺货登记").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminGoodsController&addArrivalNotice"));
    }

    @ApiOperation("禁销限销列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "forbidid", value = "总单ID", dataType = "Long"),
            @ApiImplicitParam(name = "customsetid", value = "客户集合ID", dataType = "Long"),
            @ApiImplicitParam(name = "customsetname", value = "客户集合名称", dataType = "String"),
            @ApiImplicitParam(name = "goodssetid", value = "货品集合ID", dataType = "Long"),
            @ApiImplicitParam(name = "goodssetname", value = "货品集合名称", dataType = "String"),
            @ApiImplicitParam(name = "forbidflag", value = "禁销类型", dataType = "Long"),
            @ApiImplicitParam(name = "usestatus", value = "使用状态", dataType = "Long"),
    })
    @GetMapping(value = "getBannedList")
    public IPage<BannedVo> getBannedList(Page page, BannedVo bannedVo) {
        return this.goodsService.getBannedList(page, bannedVo);
    }

    @ApiOperation("货品集合-总单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodssetname", value = "货品集合名称", dataType = "String"),
            @ApiImplicitParam(name = "goodssetid", value = "货品集合id", dataType = "Long"),
    })
    @GetMapping("getAdminGoodsList")
    public IPage<BannedVo> getAdminGoodsList(Page page, BannedVo bannedVo) {
        return this.goodsService.getAdminGoodsList(page, bannedVo);
    }

    @ApiOperation("货品集合-细单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "setid", value = "货品集合ID", required = true, dataType = "Long")
    })
    @GetMapping("getAdminGoodsListById")
    public IPage<BannedVo> getAdminGoodsListById(Page page, BannedVo bannedVo) {
        return this.goodsService.getAdminGoodsListById(page, bannedVo);
    }

    @ApiOperation("商品分类列表")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getAdminGoodsClassTypeVoList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classnRow1", value = "分类id", dataType = "Long"),
            @ApiImplicitParam(name = "classname", value = "分类名称", dataType = "String"),
    })
    public List<GoodsClassTypeVo> getAdminGoodsClassTypeVoList(GoodsClassTypeVo goodsClassTypeVo) {
        return this.goodsService.getGoodsClassTypeVoList(goodsClassTypeVo);
    }

    @ApiOperation("货品列表-不分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsid", value = "货品id", dataType = "Long"),
            @ApiImplicitParam(name = "barcode", value = "条形码", dataType = "String"),
            @ApiImplicitParam(name = "goodsname", value = "商品名", dataType = "String"),
            @ApiImplicitParam(name = "factoryname", value = "生产商", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
            @ApiImplicitParam(name = "isImg", value = "无图片", dataType = "Boolean"),
            @ApiImplicitParam(name = "imgNum", value = "图片数量", dataType = "Integer"),
            @ApiImplicitParam(name = "classnRow3", value = "分类编号", dataType = "Long"),
    })
    @GetMapping(value = "getGoodsListNotPage")
    public List<GoodsInfoVo> getGoodsListNotPage(GoodsInfoVo goodsInfoVo) {
        return this.goodsService.getGoodsListNotPage(goodsInfoVo);
    }


    @ApiOperation("活动策略列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "促销内容", dataType = "String"),
            @ApiImplicitParam(name = "activityStrategy", value = "活动策略", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    @GetMapping("getActivityVoList")
    public IPage<ActivityVo> getActivityVoList(Page page, ActivityVo activityVo) {
        return this.goodsService.getActivityVoList(page, activityVo);
    }

    @ApiOperation("活动商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "asId", value = "策略id", required = true, dataType = "Long"),
    })
    @GetMapping("getActivityGoodsList")
    public IPage<ActivityGoods> getActivityGoodsList(Page page, Long asId) {
        return this.goodsService.getActivityGoodsList(page, asId);
    }

    @ApiOperation("活动策略")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "activityId", value = "活动id", required = true, dataType = "Long"),
    })
    @GetMapping("getActivityStrategyList")
    public IPage<ActivityStrategy> getActivityStrategyList(Page page, Long activityId) {
        return this.goodsService.getActivityStrategyList(page, activityId);
    }

    @ApiOperation("活动客户集合")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "activityId", value = "活动id", required = true, dataType = "Long"),
    })
    @GetMapping("getActivitySetList")
    public IPage<ActivitySetVo> getActivitySetList(Page page, Long activityId) {
        return this.goodsService.getActivitySetList(page, activityId);
    }

    @ApiOperation("活动赠品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "erpGoodsId", value = "商品id", dataType = "Long"),
            @ApiImplicitParam(name = "asId", value = "活动策略id", required = true, dataType = "Long"),
    })
    @GetMapping("getActivityGiftList")
    public IPage<ActivityGift> getActivityGiftList(Page page, Long erpGoodsId, Long asId) {
        return this.goodsService.getActivityGiftList(page, erpGoodsId, asId);
    }

    @ApiOperation("后台新品登记列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "approvedocno", value = "批准文号", dataType = "String"),
            @ApiImplicitParam(name = "goodsName", value = "商品名称", dataType = "String"),
    })
    @GetMapping("getNewProductList")
    public IPage<NewProductVo> getNewProductList(Page page, NewProductVo newProductVo) {
        newProductVo.setMemberName(this.accountHelper.getUserName());
        return this.newProductService.getNewProductList(page, newProductVo);
    }

    @ApiOperation("编辑商品分类")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classAbbreviation", value = "分类简称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "classImg", value = "分类图片", required = true, dataType = "String"),
    })
    @PostMapping("editClass")
    public void editClass(@RequestBody Class goodsClass, HttpServletRequest request) {
        this.classService.editClass(goodsClass);
        this.adminService.addAdminLog(new AdminLog().setContent("编辑商品分类-->id：" + goodsClass.getErpClassId() + "图片：" + (goodsClass.getClassImg() != null ? goodsClass.getClassImg() : "")).setIp(this.accountHelper.getIP(request))
                .setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminGoodsController&editClass"));
    }

    @ApiOperation("商品分类列表")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getGoodsClassTypeVoList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "classnRow1", value = "分类id", dataType = "Long"),
            @ApiImplicitParam(name = "classname", value = "分类名称", dataType = "String"),
    })
    public IPage<GoodsClassTypeVo> getGoodsClassTypeVoList(Page page, GoodsClassTypeVo goodsClassTypeVo) {
        return this.goodsService.getAdminGoodsClassTypeVoList(page, goodsClassTypeVo);
    }

    @ApiOperation("购物车不分页")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getAdminMemberCartAll")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberId", value = "memberId", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodsName", value = "商品名称/厂家/erpgoodsId", dataType = "String"),
    })
    public List<CartVo> getAdminMemberCartAll(CartVo cartVo) {
        return this.goodsService.getMemberCartAll(cartVo);
    }

    @ApiOperation("添加商品到购物车")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @PostMapping(value = "addAdminMemberCart")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", value = "商品id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "erpGoodsId", value = "erp商品id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodsName", value = "商品名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goodsPrice", value = "价格", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "goodsNum", value = "数量", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "goodsImage", value = "商品图片", required = true, dataType = "Integer"),

    })
    public void addAdminMemberCart(@RequestBody List<Cart> cartList, HttpServletRequest request) {
        this.goodsService.addMemberCart(cartList, null);
        this.adminService.addAdminLog(new AdminLog().setContent("后台添加商品到购物车").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminGoodsController&addAdminMemberCart"));
    }

    @ApiOperation("购物车商品参与的活动")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getAdminCartJoinActivity")
    @ApiImplicitParams({
    })
    public List<ActivityVo> getAdminCartJoinActivity(Long memberId){
        return this.goodsService.getMyCartJoinActivity(memberId);
    }

    @ApiOperation("修改商品活动id")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "updateAdminCartActivity")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "活动id", required = true, dataType = "Long"),
    })
    public void updateAdminCartActivity(Long memberId,Long activityId) {
        this.goodsService.updateCartActivity(memberId, activityId);
    }
}
