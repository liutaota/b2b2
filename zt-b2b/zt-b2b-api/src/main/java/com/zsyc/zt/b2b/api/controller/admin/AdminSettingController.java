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

@Api
@RestController
@RequestMapping("api/setting")
public class AdminSettingController {
    @Reference
    private SettingService settingService;
    @Reference
    private AdminService adminService;
    @Autowired
    private AccountHelper accountHelper;


    @ApiOperation("添加设置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "value", value = "值", required = true, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "名称", required = true, dataType = "String"),
            //@ApiImplicitParam(name = "sort", value = "排序，默认0", required = true,dataType = "Integer"),
            @ApiImplicitParam(name = "key", value = "key", required = true, dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "String"),
    })
    @PostMapping("addSetting")
    public void addSetting(@RequestBody SettingVo settingVo, HttpServletRequest request) {
        settingVo.setUserId(this.accountHelper.getUserId());
        this.settingService.addSetting(settingVo);
        this.adminService.addAdminLog(new AdminLog().setContent("添加设置-->名称：" + settingVo.getName()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminSettingController&addSetting"));

    }

    @ApiOperation("修改设置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "value", value = "值", required = true, dataType = "String"),
            //@ApiImplicitParam(name = "sort", value = "排序，默认0", required = true,dataType = "Integer"),
    })
    @PostMapping("updateSetting")
    public void updateSetting(@RequestBody SettingVo settingVo, HttpServletRequest request) {
        settingVo.setUpdateUserId(this.accountHelper.getUserId());
        this.settingService.updateSetting(settingVo);
        this.adminService.addAdminLog(new AdminLog().setContent("修改设置-->id：" + settingVo.getId()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminSettingController&updateSetting"));

    }

    @ApiOperation("设置列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "name", value = "名称", dataType = "Long"),
            @ApiImplicitParam(name = "type", value = "类型", dataType = "String"),
    })
    @GetMapping(value = "getSettingList")
    public IPage<Setting> getSettingList(Page page, Setting setting) {
        return this.settingService.getSettingList(page, setting);
    }

    @ApiOperation("参数设置置顶/置底排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "参数设置ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "sortType", value = "排序类型", required = true, dataType = "String")
    })
    @GetMapping("updateSettingSort")
    public void updateSettingSort(SettingVo settingVo, HttpServletRequest request) {
        this.settingService.updateSettingSort(settingVo);
        this.adminService.addAdminLog(new AdminLog().setContent("参数设置id：" + settingVo.getId() + (settingVo.getSortType().equals("TOP") ? "-->置顶排序" : "-->置底排序")).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminSettingController&updateSettingSort"));
    }

    @ApiOperation("参数设置上下排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "settingIdPrev", value = "上一个参数设置id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "settingIdNext", value = "下一个参数设置id", required = true, dataType = "Long")
    })
    @GetMapping("settingSort")
    public void settingSort(Long settingIdPrev, Long settingIdNext, HttpServletRequest request) {
        this.settingService.settingSort(settingIdPrev, settingIdNext);
        this.adminService.addAdminLog(new AdminLog().setContent("参数设置id：" + settingIdPrev + "," + settingIdNext + "-->上下排序").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminSettingController&settingSort"));
    }
}
