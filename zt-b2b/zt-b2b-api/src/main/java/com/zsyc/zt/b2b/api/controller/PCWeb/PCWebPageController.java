package com.zsyc.zt.b2b.api.controller.PCWeb;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.entity.AdvPosition;
import com.zsyc.zt.b2b.entity.Floor;
import com.zsyc.zt.b2b.entity.Notice;
import com.zsyc.zt.b2b.entity.Setting;
import com.zsyc.zt.b2b.service.NoticeService;
import com.zsyc.zt.b2b.service.SettingService;
import com.zsyc.zt.b2b.service.WebPageService;
import com.zsyc.zt.b2b.vo.*;
import io.swagger.annotations.*;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api
@RestController
@RequestMapping("api/pcWebPage")
public class PCWebPageController {
    @Reference
    private WebPageService webPageService;
    @Autowired
    private AccountHelper accountHelper;
    @Reference
    private NoticeService noticeService;
    @Reference
    private SettingService settingService;

    @ApiOperation("专区列表")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping("getWebPageList")
    public List<HomeMenuVo> getWebPageList() {
        return this.webPageService.getWebPageList(this.accountHelper.getUserId());
    }

    @ApiOperation("专区广告详情")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @ApiImplicitParam(name = "id", value = "广告ID", required = true, dataType = "Long")
    @GetMapping("getAdvPositionDetail")
    public AdvPositionVo getAdvPositionDetail(Long id) {
        return this.webPageService.getAdvPositionDetail(id,this.accountHelper.getUserId());
    }

    @ApiOperation("专区楼层详情")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @ApiImplicitParam(name = "floorId", value = "楼层ID", required = true, dataType = "Long")
    @GetMapping("getFloorDetail")
    public FloorVo getFloorDetail(Page page,Long floorId) {
        return this.webPageService.getFloorDetail(page,this.accountHelper.getUserId(), floorId);
    }

    @ApiOperation("专区详情")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @ApiImplicitParam(name = "id", value = "专区ID", required = true, dataType = "Long")
    @GetMapping("getWebPageVoInfo")
    public WebPageVo getWebPageVoInfo(Long id) {
        return this.webPageService.getWebPageVoInfo(id, this.accountHelper.getUserId());
    }

    @ApiOperation("专区搜索")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getWebPageSearchList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "webPageId", value = "专区id", required = true, dataType = "Long"),

            @ApiImplicitParam(name = "classname", value = "分类名称", dataType = "String"),
            @ApiImplicitParam(name = "goodspinyin", value = "搜索（拼音首字母，通用名，商品名，商品ID，分类，条形码，厂家，功效）", dataType = "String"),
            @ApiImplicitParam(name = "goodsname", value = "商品名", dataType = "String"),
            @ApiImplicitParam(name = "currencyname", value = "通用名", dataType = "String"),
            @ApiImplicitParam(name = "factoryname", value = "厂家", dataType = "String"),
            @ApiImplicitParam(name = "classnRow3", value = "分类号3", dataType = "Long"),
            @ApiImplicitParam(name = "classnRow2", value = "分类号2", dataType = "Long"),
            @ApiImplicitParam(name = "classnRow1", value = "分类号1", dataType = "Long"),
            @ApiImplicitParam(name = "stairClassify", value = "专区分类1", dataType = "String"),
            @ApiImplicitParam(name = "accessName", value = "专区分类2", dataType = "String"),

    })
    public IPage<GoodsInfoVo> getWebPageSearchList(Page page, GoodsInfoVo goodsInfoVo) {
        goodsInfoVo.setMemberId(this.accountHelper.getUserId());
        return this.webPageService.getWebPageSearchList(page, goodsInfoVo);
    }

    @ApiOperation("专区搜索-->搜索提示")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getWebPageSearchListReturnName")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "webPageId", value = "专区id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodspinyin", value = "拼音", dataType = "String"),
    })
    public List<SearchTipsVo> getWebPageSearchListReturnName(Long webPageId,String goodspinyin) {
        return this.webPageService.getWebPageSearchListReturnName(this.accountHelper.getUserId(),webPageId,goodspinyin);
    }

    @ApiOperation("专区厂家分类列表")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getWebPageFactoryVoList")
    @ApiImplicitParams({

    })
    public List<FactoryVo> getWebPageFactoryVoList(GoodsInfoVo goodsInfoVo) {
        goodsInfoVo.setMemberId(this.accountHelper.getUserId());
        return this.webPageService.getWebPageFactoryVoList(goodsInfoVo);
    }

    @ApiOperation("前台公告列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
    })
    @GetMapping("getPcNoticeList")
    public IPage<Notice> getPcNoticeList(Page page) {
        return this.noticeService.getPcNoticeList(page);
    }

    @ApiOperation("不分页公告列表")
    @GetMapping("getPcNoticeListIsNotPage")
    public List<Notice> getPcNoticeListIsNotPage(){
        return this.noticeService.getPcNoticeListIsNotPage();
    }

}
