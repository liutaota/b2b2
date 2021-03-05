package com.zsyc.zt.b2b.api.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.entity.*;
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
@RequestMapping("api/advPosition")
public class AdminAdvPositionController {

    @Reference
    private AdvPositionService advPositionService;
    @Reference
    private AdminService adminService;
    @Reference
    private DeliveryAmountService deliveryAmountService;
    @Reference
    private FloorService floorService;
    @Reference
    private HelpService helpService;
    @Reference
    private HelpTypeService helpTypeService;
    @Autowired
    private AccountHelper accountHelper;

    @ApiOperation("广告位列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "advName", value = "广告位名称", dataType = "String"),
            @ApiImplicitParam(name = "apDisplay", value = "广告样式", dataType = "String")
    })
    @GetMapping(value = "getAdvPosition")
    public IPage<AdvPositionVo> getAdvPosition(Page page, String advName, String apDisplay) {
        return this.advPositionService.getAdvPosition(page, advName, apDisplay);
    }

    @ApiOperation("新增广告位")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "apDisplay", value = "广告位样式", required = true, dataType = "String"),
            @ApiImplicitParam(name = "advName", value = "广告名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "isUse", value = "是否启用", required = true, dataType = "String"),
            @ApiImplicitParam(name = "advStartDate", value = "广告开始时间", dataType = "String"),
            @ApiImplicitParam(name = "advEndDate", value = "广告结束时间", dataType = "String"),
            @ApiImplicitParam(name = "imageInfoVoList[].image", value = "广告图片", required = true, dataType = "String"),
            @ApiImplicitParam(name = "imageInfoVoList[].imageUrl", value = "图片链接", dataType = "String"),
            @ApiImplicitParam(name = "imageInfoVoList[].imageStartTime", value = "图片开始时间", dataType = "String"),
            @ApiImplicitParam(name = "imageInfoVoList[].imageEndTime", value = "图片结束时间", dataType = "String"),
    })
    @PostMapping("addAdvPosition")
    public void addAdvPosition(@RequestBody AdvPositionVo advPositionVo, HttpServletRequest request) {
        this.advPositionService.addAdvPosition(advPositionVo, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("新增广告-->名称：" + advPositionVo.getAdvName()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&addAdvPosition"));
    }

    @ApiOperation("组合编辑使用，根据ID获取广告位")
    @ApiImplicitParam(name = "id", value = "广告位ID", required = true, dataType = "Long")
    @GetMapping("getAdvPositionById")
    public AdvPositionVo getAdvPositionById(Long id) {
        return this.advPositionService.getAdvPositionById(id);
    }

    @ApiOperation("编辑广告位")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "广告位ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "apDisplay", value = "广告位样式", required = true, dataType = "String"),
            @ApiImplicitParam(name = "advName", value = "广告名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "isUse", value = "是否启用", required = true, dataType = "String"),
            @ApiImplicitParam(name = "advStartDate", value = "广告开始时间", dataType = "String"),
            @ApiImplicitParam(name = "advEndDate", value = "广告结束时间", dataType = "String"),
            @ApiImplicitParam(name = "imageInfoVoList[].image", value = "广告图片", required = true, dataType = "String"),
            @ApiImplicitParam(name = "imageInfoVoList[].imageUrl", value = "图片链接", dataType = "String"),
            @ApiImplicitParam(name = "imageInfoVoList[].imageStartTime", value = "图片开始时间", dataType = "String"),
            @ApiImplicitParam(name = "imageInfoVoList[].imageEndTime", value = "图片结束时间", dataType = "String"),
    })
    @PostMapping("updateAdvPosition")
    public void updateAdvPosition(@RequestBody AdvPositionVo advPositionVo, HttpServletRequest request) {
        this.advPositionService.updateAdvPosition(advPositionVo, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("修改广告-->id：" + advPositionVo.getId()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&updateAdvPosition"));
    }

    @ApiOperation("广告位是否启用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "广告位ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "isUse", value = "是否启用", required = true, dataType = "String")
    })
    @GetMapping("updateAdvPositionIsUse")
    public void updateAdvPositionIsUse(Long id, String isUse, HttpServletRequest request) {
        this.advPositionService.updateAdvPositionIsUse(id, isUse, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("广告id：" + id + (isUse.equals("ON") ? "-->开启" : "-->关闭")).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&updateAdvPositionIsUse"));
    }

    @ApiOperation("广告位是否删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "广告位ID", required = true, dataType = "Long")
    })
    @GetMapping("updateAdvPositionIsDel")
    public void updateAdvPositionIsDel(Long id, HttpServletRequest request) {
        this.advPositionService.updateAdvPositionIsDel(id, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("广告id：" + id + "-->删除").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&updateAdvPositionIsDel"));
    }

    @ApiOperation("起送规则列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "daName", value = "规则名称", dataType = "String"),
            @ApiImplicitParam(name = "areaName", value = "起送地区名称", required = true, dataType = "String")
    })
    @GetMapping("getDeliveryAmount")
    public IPage<DeliveryAmount> getDeliveryAmount(Page page, DeliveryAmountVo deliveryAmountVo) {
        return this.deliveryAmountService.getDeliveryAmount(page, deliveryAmountVo);
    }

    @ApiOperation("新增起送规则")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "areaId", value = "erp地区ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "daName", value = "起送规则名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "areaName", value = "erp地区名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "daAmount", value = "起送金额", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "type", value = "类型", required = true, dataType = "String"),
    })
    @PostMapping("addDeliveryAmount")
    public void addDeliveryAmount(@RequestBody DeliveryAmount deliveryAmount, HttpServletRequest request) {
        this.deliveryAmountService.addDeliveryAmount(deliveryAmount, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("新增起送规则-->名称：" + deliveryAmount.getDaName()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&addDeliveryAmount"));
    }

    @ApiOperation("修改起送规则状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "isUse", value = "起送状态", required = true, dataType = "String")
    })
    @GetMapping("updateDeliveryAmountIsUse")
    public void updateDeliveryAmountIsUse(Long id, String isUse, HttpServletRequest request) {
        this.deliveryAmountService.updateDeliveryAmountIsUse(id, isUse, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("起送规则id：" + id + (isUse.equals("ON") ? "-->开启" : "-->关闭")).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&updateDeliveryAmountIsUse"));
    }

    @ApiOperation("编辑起送规则")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "areaId", value = "erp地区ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "daName", value = "起送规则名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "areaName", value = "erp地区名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "daAmount", value = "起送金额", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "type", value = "类型", required = true, dataType = "String"),
    })
    @PostMapping("updateDeliveryAmount")
    public void updateDeliveryAmount(@RequestBody DeliveryAmount deliveryAmount, HttpServletRequest request) {
        this.deliveryAmountService.updateDeliveryAmount(deliveryAmount, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("编辑起送规则-->id：" + deliveryAmount.getId()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&updateDeliveryAmount"));
    }

    @ApiOperation("删除起送规则")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long")
    @GetMapping("delDeliveryAmount")
    public void delDeliveryAmount(Long id, HttpServletRequest request) {
        this.deliveryAmountService.delDeliveryAmount(id);
        this.adminService.addAdminLog(new AdminLog().setContent("起送规则id：" + id + "-->删除").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&delDeliveryAmount"));
    }

    @ApiOperation("楼层列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "title", value = "楼层名称", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "楼层样式", dataType = "String"),
            @ApiImplicitParam(name = "floorId", value = "楼层ID", dataType = "Long"),
    })
    @GetMapping("getFloorList")
    public IPage<FloorVo> getFloorList(Page page, String title, String type) {
        return this.floorService.getFloorList(page, title, type);
    }

    @ApiOperation("无分页楼层列表")
    @GetMapping("isNotPageFloorList")
    public List<Floor> isNotPageFloorList() {
        return this.floorService.isNotPageFloorList();
    }

    @ApiOperation("无分页广告列表")
    @GetMapping("isNotPageAdvPositionList")
    public List<AdvPosition> isNotPageAdvPositionList() {
        return this.advPositionService.isNotPageAdvPositionList();
    }

    @ApiOperation("新增楼层")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "楼层名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "展示样式", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goodsId", value = "商品ID", dataType = "String"),
            @ApiImplicitParam(name = "goodsName", value = "商品名称", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "页签名称", dataType = "String"),
            @ApiImplicitParam(name = "tabSort", value = "页签排序", dataType = "Integer"),
            @ApiImplicitParam(name = "backgroundImage", value = "背景图片", dataType = "String"),
            @ApiImplicitParam(name = "image", value = "图片", dataType = "String"),
            @ApiImplicitParam(name = "url", value = "链接", dataType = "String"),
    })
    @PostMapping("addFloor")
    public void addFloor(@RequestBody FloorVo floorVo, HttpServletRequest request) {
        this.floorService.addFloor(floorVo, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("新增楼层-->名称：" + floorVo.getTitle()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&addFloor"));
    }

    @ApiOperation("组合编辑使用，根据ID查看")
    @ApiImplicitParam(name = "id", value = "楼层ID", required = true, dataType = "Long")
    @GetMapping("getFloorById")
    public FloorVo getFloorById(Long id) {
        return this.floorService.getFloorById(id);
    }

    @ApiOperation("编辑楼层")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "楼层ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "title", value = "楼层名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "type", value = "展示样式", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goodsId", value = "商品ID", dataType = "String"),
            @ApiImplicitParam(name = "goodsName", value = "商品名称", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "页签名称", dataType = "String"),
            @ApiImplicitParam(name = "tabSort", value = "页签排序", dataType = "Integer"),
            @ApiImplicitParam(name = "backgroundImage", value = "背景图片", dataType = "String"),
            @ApiImplicitParam(name = "image", value = "图片", dataType = "String"),
            @ApiImplicitParam(name = "url", value = "链接", dataType = "String"),
    })
    @PostMapping("updateFloor")
    public void updateFloor(@RequestBody FloorVo floorVo, HttpServletRequest request) {
        floorVo.setUpdateUserId(this.accountHelper.getUserId());
        this.floorService.updateFloor(floorVo, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("编辑楼层-->id：" + floorVo.getId()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&updateFloor"));
    }

    @ApiOperation("楼层是否删除")
    @ApiImplicitParam(name = "id", value = "楼层ID", required = true, dataType = "Long")
    @GetMapping("updateFloorIsDel")
    public void updateFloorIsDel(Long id, HttpServletRequest request) {
        this.floorService.updateFloorIsDel(id, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("楼层id：" + id + "-->删除").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&updateFloorIsDel"));
    }

    @ApiOperation("楼层是否启用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "楼层ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "isUse", value = "是否启用", required = true, dataType = "String")
    })
    @GetMapping("updateFloorIsUse")
    public void updateFloorIsUse(Long id, String isUse, HttpServletRequest request) {
        this.floorService.updateFloorIsUse(id, isUse, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("楼层id：" + id + (isUse.equals("ON") ? "-->开启" : "-->关闭")).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&updateFloorIsUse"));
    }

    @ApiOperation("楼层置顶/置底排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "楼层ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "sortType", value = "排序类型", required = true, dataType = "String")
    })
    @GetMapping("updateFloorSort")
    public void updateFloorSort(FloorVo floorVo, HttpServletRequest request) {
        this.floorService.updateFloorSort(floorVo, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("楼层id：" + floorVo.getId() + (floorVo.getSortType().equals("TOP") ? "-->置顶排序" : "-->置底排序")).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&updateFloorSort"));
    }

    @ApiOperation("楼层上下排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "floorIdPrev", value = "上一个楼层id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "floorIdNext", value = "下一个楼层id", required = true, dataType = "Long")
    })
    @GetMapping("floorSort")
    public void floorSort(Long floorIdPrev, Long floorIdNext, HttpServletRequest request) {
        this.floorService.floorSort(floorIdPrev, floorIdNext);
        this.adminService.addAdminLog(new AdminLog().setContent("楼层id：" + floorIdPrev + "," + floorIdNext + "-->上下排序").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminGoodsController&floorSort"));
    }

    @ApiOperation("帮助指南列表")
    @GetMapping("getHelpList")
    public List<Help> getHelpList() {
        return this.helpService.getHelpList();
    }

    @ApiOperation("新增帮助指南")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "helpTitle", value = "指南标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "helpInfo", value = "指南内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "helpUrl", value = "跳转链接", required = true, dataType = "String"),
            @ApiImplicitParam(name = "typeId", value = "帮助类型", required = true, dataType = "Long"),
    })
    @PostMapping("addHelp")
    public void addHelp(@RequestBody Help help, HttpServletRequest request) {
        this.helpService.addHelp(help, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("新增帮助指南-->名称：" + help.getHelpTitle()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&addHelp"));
    }

    @ApiOperation("编辑帮助指南")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "helpTitle", value = "指南标题", required = true, dataType = "String"),
            @ApiImplicitParam(name = "helpSort", value = "排序", required = true, dataType = "Integer"),
            @ApiImplicitParam(name = "helpInfo", value = "指南内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "helpUrl", value = "跳转链接", required = true, dataType = "String"),
            @ApiImplicitParam(name = "typeId", value = "帮助类型", required = true, dataType = "Long"),
    })
    @PostMapping("updateHelp")
    public void updateHelp(@RequestBody Help help, HttpServletRequest request) {
        this.helpService.updateHelp(help, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("编辑帮助指南-->id：" + help.getId()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&addHelp"));
    }

    @ApiOperation("帮助指南是否显示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "helpShow", value = "是否显示", required = true, dataType = "String"),
    })
    @GetMapping("updateHelpShow")
    public void updateHelpShow(Long id, String helpShow, HttpServletRequest request) {
        this.helpService.updateHelpShow(id, helpShow, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("帮助指南id：" + id + (helpShow.equals("ON") ? "-->显示" : "-->不显示")).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&updateHelpShow"));
    }

    @ApiOperation("帮助指南是否删除")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long")
    @GetMapping("updateHelpIsDel")
    public void updateHelpIsDel(Long id, HttpServletRequest request) {
        this.helpService.updateHelpIsDel(id, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("帮助指南id：" + id + "-->删除").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&updateHelpIsDel"));
    }

    @ApiOperation("帮助指南置顶/置底排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "帮助指南ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "sortType", value = "排序类型", required = true, dataType = "String")
    })
    @GetMapping("updateHelpSort")
    public void updateHelpSort(HelpVo helpVo, HttpServletRequest request) {
        this.helpService.updateHelpSort(helpVo, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("帮助指南id：" + helpVo.getId() + (helpVo.getSortType().equals("TOP") ? "-->置顶排序" : "-->置底排序")).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&updateHelpSort"));
    }

    @ApiOperation("帮助指南上下排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "helpIdPrev", value = "上一个帮助指南id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "helpIdNext", value = "下一个帮助指南id", required = true, dataType = "Long")
    })
    @GetMapping("helpSort")
    public void helpSort(Long helpIdPrev, Long helpIdNext, HttpServletRequest request) {
        this.helpService.helpSort(helpIdPrev, helpIdNext);
        this.adminService.addAdminLog(new AdminLog().setContent("帮助指南id：" + helpIdPrev + "," + helpIdNext + "-->上下排序").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminGoodsController&helpSort"));
    }

    @ApiOperation("帮助类型列表")
    @GetMapping("getHelpTypeList")
    public List<HelpType> getHelpTypeList() {
        return this.helpTypeService.getHelpTypeList();
    }

    @ApiOperation("新增帮助类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeName", value = "类型名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "helpSort", value = "排序", dataType = "String"),
    })
    @PostMapping("addHelpType")
    public void addHelpType(@RequestBody HelpType helpType, HttpServletRequest request) {
        this.helpTypeService.addHelpType(helpType, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("新增帮助类型-->名称：" + helpType.getTypeName()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&addHelpType"));
    }

    @ApiOperation("编辑帮助类型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "typeName", value = "类型名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "typeSort", value = "排序", dataType = "Integer"),
    })
    @PostMapping("updateHelpType")
    public void updateHelpType(@RequestBody HelpType helpType, HttpServletRequest request) {
        this.helpTypeService.updateHelpType(helpType, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("编辑帮助类型-->id：" + helpType.getId()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&updateHelpType"));
    }

    @ApiOperation("帮助类型是否显示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "helpShow", value = "是否显示", required = true, dataType = "String"),
    })
    @GetMapping("updateHelpTypeShow")
    public void updateHelpTypeShow(Long id, String helpShow, HttpServletRequest request) {
        this.helpTypeService.updateHelpTypeShow(id, helpShow, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("帮助类型id：" + id + (helpShow.equals("ON") ? "-->显示" : "-->不显示")).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&updateHelpTypeShow"));
    }

    @ApiOperation("帮助类型是否删除")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long")
    @GetMapping("updateHelpTypeIsDel")
    public void updateHelpTypeIsDel(Long id, HttpServletRequest request) {
        this.helpTypeService.updateHelpTypeIsDel(id, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("帮助类型id：" + id + "-->删除").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&updateHelpTypeIsDel"));
    }

    @ApiOperation("帮助类型置顶/置底排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "帮助类型ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "sortType", value = "排序类型", required = true, dataType = "String")
    })
    @GetMapping("updateHelpTypeSort")
    public void updateHelpTypeSort(HelpTypeVo helpTypeVo, HttpServletRequest request) {
        this.helpTypeService.updateHelpTypeSort(helpTypeVo, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("帮助类型id：" + helpTypeVo.getId() + (helpTypeVo.getSortType().equals("TOP") ? "-->置顶排序" : "-->置底排序")).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminAdvPositionController&updateHelpTypeSort"));
    }

    @ApiOperation("帮助类型上下排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "helpTypeIdPrev", value = "上一个帮助类型id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "helpTypeIdNext", value = "下一个帮助类型id", required = true, dataType = "Long")
    })
    @GetMapping("helpTypeSort")
    public void helpTypeSort(Long helpTypeIdPrev, Long helpTypeIdNext, HttpServletRequest request) {
        this.helpTypeService.helpTypeSort(helpTypeIdPrev, helpTypeIdNext);
        this.adminService.addAdminLog(new AdminLog().setContent("帮助类型id：" + helpTypeIdPrev + "," + helpTypeIdNext + "-->上下排序").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminGoodsController&helpTypeSort"));
    }
}
