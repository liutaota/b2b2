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
@RequestMapping("api/webPage")
public class AdminWebPageController {
    @Autowired
    private AccountHelper accountHelper;
    @Reference
    private WebPageService webPageService;
    @Reference
    private AdminService adminService;
    @Reference
    private NoticeService noticeService;
    @Reference
    private HomeMenuService homeMenuService;

    @ApiOperation("专区列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "title", value = "专区名称", required = true, dataType = "String")
    })
    @GetMapping("getWebPageList")
    public IPage<WebPageVo> getWebPageList(Page page, String title) {
        return this.webPageService.getWebPageList(page, title);
    }

    @ApiOperation("新增页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "页面名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "VisibleSet[].type", value = "集合类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "memberSetList[].memberSetId", value = "客户集合ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "memberSetList[].memberSetName", value = "客户集合名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "hintIcon", value = "提示图片", required = true, dataType = "String"),
            @ApiImplicitParam(name = "stairClassify", value = "一级分类名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "accessList[].accessName", value = "二级分类名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "accessList[].type", value = "类型选择", required = true, dataType = "String"),
            @ApiImplicitParam(name = "classifyList[].classifyId[][]", value = "分类ID", dataType = "Long"),
            @ApiImplicitParam(name = "classifyList[].classifyName", value = "分类名称", dataType = "String"),
            @ApiImplicitParam(name = "goodsList[].goodsId", value = "商品Id", dataType = "Long"),
            @ApiImplicitParam(name = "goodsList[].goodsName", value = "商品名称", dataType = "String"),
            @ApiImplicitParam(name = "pageSetList[].type", value = "页面类型", dataType = "String"),
            @ApiImplicitParam(name = "pageSetList[].advId", value = "广告位ID", dataType = "String"),
            @ApiImplicitParam(name = "pageSetList[].advTitle", value = "广告位名称", dataType = "String"),
            @ApiImplicitParam(name = "pageSetList[].floorId", value = "楼层ID", dataType = "String"),
            @ApiImplicitParam(name = "pageSetList[].floorTitle", value = "楼层名称", dataType = "String"),
    })
    @PostMapping("addWebPage")
    public void addWebPage(@RequestBody WebPageVo webPageVom, HttpServletRequest request) {
        this.webPageService.addWebPage(webPageVom, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("新增页面-->名称：" + webPageVom.getTitle()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminWebPageController&addWebPage"));
    }

    @ApiOperation("编辑页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "title", value = "页面名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "VisibleSet[].type", value = "集合类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "memberSetList[].memberSetId", value = "客户集合ID", required = true, dataType = "String"),
            @ApiImplicitParam(name = "memberSetList[].memberSetName", value = "客户集合名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "hintIcon", value = "提示图片", required = true, dataType = "String"),
            @ApiImplicitParam(name = "stairClassify", value = "一级分类名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "accessList[].accessName", value = "二级分类名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "accessList[].type", value = "类型选择", required = true, dataType = "String"),
            @ApiImplicitParam(name = "classifyList[].classifyId[][]", value = "分类ID", dataType = "Long"),
            @ApiImplicitParam(name = "classifyList[].classifyName", value = "分类名称", dataType = "String"),
            @ApiImplicitParam(name = "goodsList[].goodsId", value = "商品Id", dataType = "Long"),
            @ApiImplicitParam(name = "goodsList[].goodsName", value = "商品名称", dataType = "String"),
            @ApiImplicitParam(name = "pageSetList[].type", value = "页面类型", dataType = "String"),
            @ApiImplicitParam(name = "pageSetList[].advId", value = "广告位ID", dataType = "String"),
            @ApiImplicitParam(name = "pageSetList[].advTitle", value = "广告位名称", dataType = "String"),
            @ApiImplicitParam(name = "pageSetList[].floorId", value = "楼层ID", dataType = "String"),
            @ApiImplicitParam(name = "pageSetList[].floorTitle", value = "楼层名称", dataType = "String"),
    })
    @PostMapping("updateWebPage")
    public void updateWebPage(@RequestBody WebPageVo webPageVo, HttpServletRequest request) {
        this.webPageService.updateWebPage(webPageVo, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("编辑页面-->id：" + webPageVo.getId()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminWebPageController&updateWebPage"));
    }

    @ApiOperation("组合编辑使用，根据ID查专区")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long")
    @GetMapping("getWebPageById")
    public WebPageVo getWebPageById(Long id) {
        return this.webPageService.getWebPageById(id);
    }

    @ApiOperation("删除页面")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long")
    @GetMapping("updateWebPageIsDel")
    public void updateWebPageIsDel(Long id, HttpServletRequest request) {
        this.webPageService.updateWebPageIsDel(id, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("页面id：" + id + "-->删除").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminWebPageController&updateWebPageIsDel"));
    }

    @ApiOperation("页面置顶/置底排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "页面ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "sortType", value = "排序类型", required = true, dataType = "String")
    })
    @GetMapping("updateWebPageSort")
    public void updateWebPageSort(WebPageVo webPageVo, HttpServletRequest request) {
        this.webPageService.updateWebPageSort(webPageVo);
        this.adminService.addAdminLog(new AdminLog().setContent("页面id：" + webPageVo.getId() + (webPageVo.getSortType().equals("TOP") ? "-->置顶排序" : "-->置底排序")).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminWebPageController&updateWebPageSort"));
    }

    @ApiOperation("页面状态修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "页面id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "isUse", value = "启用状态", required = true, dataType = "String")
    })
    @GetMapping("updateWebPageIsUse")
    public void updateWebPageIsUse(Long id, String isUse, HttpServletRequest request) {
        this.webPageService.updateWebPageIsUse(id, isUse, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("页面id：" + id + (isUse.equals("ON") ? "-->启用" : "-->关闭")).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminWebPageController&updateWebPageIsUse"));
    }

    @ApiOperation("页面上下排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "webPageIdPrev", value = "上一个页面id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "webPageIdNext", value = "下一个页面id", required = true, dataType = "Long")
    })
    @GetMapping("webPageSort")
    public void webPageSort(Long webPageIdPrev, Long webPageIdNext, HttpServletRequest request) {
        this.webPageService.webPageSort(webPageIdPrev, webPageIdNext);
        this.adminService.addAdminLog(new AdminLog().setContent("页面id：" + webPageIdPrev + "," + webPageIdNext + "-->上下排序").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminWebPageController&webPageSort"));
    }

    @ApiOperation("后台公告列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "公告内容", required = true, dataType = "String"),
    })
    @GetMapping("getAdminNoticeList")
    public IPage<Notice> getAdminNoticeList(Page page, String content) {
        return this.noticeService.getAdminNoticeList(page, content);
    }

    @ApiOperation("新增公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "content", value = "公告内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "noticeType", value = "公告类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "noticeColor", value = "颜色", required = true, dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, dataType = "String"),
    })
    @PostMapping("addNotice")
    public void addNotice(@RequestBody Notice notice, HttpServletRequest request) {
        notice.setAdminId(this.accountHelper.getUserId());
        notice.setAdminName(this.accountHelper.getUserName());
        this.noticeService.addNotice(notice);
        this.adminService.addAdminLog(new AdminLog().setContent("新增公告-->内容：" + notice.getContent()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminWebPageController&addNotice"));
    }

    @ApiOperation("修改公告")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "公告ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "公告内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "noticeType", value = "公告类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "noticeColor", value = "颜色", required = true, dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", required = true, dataType = "String"),
    })
    @PostMapping("updateNotice")
    public void updateNotice(@RequestBody Notice notice, HttpServletRequest request) {
        this.noticeService.updateNotice(notice);
        this.adminService.addAdminLog(new AdminLog().setContent("修改公告-->id：" + notice.getId()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminWebPageController&updateNotice"));
    }

    @ApiOperation("是否启用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "公告ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "isUse", value = "启动状态", required = true, dataType = "String")
    })
    @GetMapping("updateNoticeIsUse")
    public void updateNoticeIsUse(Long id, String isUse, HttpServletRequest request) {
        this.noticeService.updateNoticeIsUse(id, isUse);
        this.adminService.addAdminLog(new AdminLog().setContent("公告id：" + id + (isUse.equals("ON") ? "-->启用" : "-->关闭")).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminWebPageController&updateNoticeIsUse"));
    }

    @ApiOperation("是否删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "公告ID", required = true, dataType = "Long"),
    })
    @GetMapping("noticeIsDel")
    public void noticeIsDel(Long id, HttpServletRequest request) {
        this.noticeService.noticeIsDel(id);
        this.adminService.addAdminLog(new AdminLog().setContent("公告id：" + id + "-->删除").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminWebPageController&noticeIsDel"));
    }

    @ApiOperation("公告置顶/置底排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "公告ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "sortType", value = "排序类型", required = true, dataType = "String")
    })
    @GetMapping("updateNoticeSort")
    public void updateNoticeSort(NoticeVo noticeVo, HttpServletRequest request) {
        this.noticeService.updateNoticeSort(noticeVo);
        this.adminService.addAdminLog(new AdminLog().setContent("公告id：" + noticeVo.getId() + (noticeVo.getSortType().equals("TOP") ? "-->置顶排序" : "-->置底排序")).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminWebPageController&updateNoticeSort"));
    }

    @ApiOperation("公告上下排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "noticeIdPrev", value = "上一个公告id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "noticeIdNext", value = "下一个公告id", required = true, dataType = "Long")
    })
    @GetMapping("noticeSort")
    public void noticeSort(Long noticeIdPrev, Long noticeIdNext, HttpServletRequest request) {
        this.noticeService.noticeSort(noticeIdPrev, noticeIdNext);
        this.adminService.addAdminLog(new AdminLog().setContent("公告id：" + noticeIdPrev + "," + noticeIdNext + "-->上下排序").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminWebPageController&noticeSort"));
    }

    @ApiOperation("今日待办")
    @GetMapping("todayEvents")
    public AdminInfoVo todayEvents() {
        return this.webPageService.todayEvents();
    }

    @ApiOperation("今日订单实时情况")
    @GetMapping("realTimeSituation")
    public AdminInfoVo realTimeSituation() {
        return this.webPageService.realTimeSituation();
    }

    @ApiOperation("添加菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "一级菜单", dataType = "Long"),
            @ApiImplicitParam(name = "hmName", value = "菜单名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "hmZoneId", value = "页面id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "hintIcon", value = "提示图标", required = true, dataType = "String"),
    })
    @PostMapping("addHomeMenu")
    public void addHomeMenu(@RequestBody HomeMenu homeMenu, HttpServletRequest request) {
        this.homeMenuService.addHomeMenu(homeMenu);
        this.adminService.addAdminLog(new AdminLog().setContent("添加菜单-->名称：" + homeMenu.getHmName()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminWebPageController&addHomeMenu"));
    }

    @ApiOperation("修改菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "parentId", value = "一级菜单", dataType = "Long"),
            @ApiImplicitParam(name = "hmName", value = "菜单名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "hmZoneId", value = "页面id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "hintIcon", value = "提示图标", required = true, dataType = "String"),
    })
    @PostMapping("updateHomeMenu")
    public void updateHomeMenu(@RequestBody HomeMenu homeMenu, HttpServletRequest request) {
        this.homeMenuService.updateHomeMenu(homeMenu);
        this.adminService.addAdminLog(new AdminLog().setContent("修改菜单-->id：" + homeMenu.getId()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminWebPageController&updateHomeMenu"));
    }

    @ApiOperation("删除菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单id", required = true, dataType = "Long")
    })
    @GetMapping("deleteHomeMenu")
    public void deleteHomeMenu(Long id, HttpServletRequest request) {
        this.homeMenuService.deleteHomeMenu(id);
        this.adminService.addAdminLog(new AdminLog().setContent("菜单id：" + id + "-->删除").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminWebPageController&deleteHomeMenu"));
    }

    @ApiOperation("菜单是否启用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "isUse", value = "是否开启", required = true, dataType = "String"),
    })
    @GetMapping("homeMenuIsUse")
    public void homeMenuIsUse(Long id, String isUse, HttpServletRequest request) {
        this.homeMenuService.homeMenuIsUse(id, isUse);
        this.adminService.addAdminLog(new AdminLog().setContent("菜单id:" + id + (isUse.equals("ON") ? "-->启用" : "-->关闭")).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminWebPageController&homeMenuIsUse"));
    }


    @ApiOperation("菜单列表->一级菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "页面", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
    })
    @GetMapping("getHomeMenuList")
    public IPage<HomeMenuVo> getHomeMenuList(Page page) {
        return this.homeMenuService.getHomeMenuList(page);
    }

    @ApiOperation("菜单置顶/置底排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单设置ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "sortType", value = "排序类型", required = true, dataType = "String")
    })
    @GetMapping("updateHomeMenuSort")
    public void updateHomeMenuSort(HomeMenuVo homeMenuVo, HttpServletRequest request) {
        this.homeMenuService.updateHomeMenuSort(homeMenuVo);
        this.adminService.addAdminLog(new AdminLog().setContent("菜单id：" + homeMenuVo.getId() + (homeMenuVo.getSortType().equals("TOP") ? "-->置顶排序" : "-->置底排序")).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminWebPageController&updateHomeMenuSort"));
    }

    @ApiOperation("菜单设置上下排序")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "homeMenuIdPrev", value = "上一个菜单设置id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "homeMenuIdNext", value = "下一个菜单设置id", required = true, dataType = "Long")
    })
    @GetMapping("homeMenuSort")
    public void homeMenuSort(Long homeMenuIdPrev, Long homeMenuIdNext, HttpServletRequest request) {
        this.homeMenuService.homeMenuSort(homeMenuIdPrev, homeMenuIdNext);
        this.adminService.addAdminLog(new AdminLog().setContent("菜单id：" + homeMenuIdPrev + "," + homeMenuIdNext + "-->设置上下排序").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminWebPageController&homeMenuSort"));
    }
}
