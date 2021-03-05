package com.zsyc.zt.b2b.api.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.entity.AdminLog;
import com.zsyc.zt.b2b.entity.Feedback;
import com.zsyc.zt.b2b.service.AdminService;
import com.zsyc.zt.b2b.service.FeedbackService;
import com.zsyc.zt.b2b.vo.FeedbackVo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api
@RestController
@RequestMapping("api/feedback")
@Slf4j
public class AdminFeedbackController {
    @Autowired
    private AccountHelper accountHelper;
    @Reference
    private FeedbackService feedbackService;
    @Reference
    private AdminService adminService;

    @ApiOperation("产品咨询/用户反馈列表 type区分")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getAdminFeedbackVoList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "type", value = "GOODS/PLATFROM", dataType = "String"),
    })
    public IPage<FeedbackVo> getAdminFeedbackVoList(Page page, FeedbackVo feedbackVo) {
        return this.feedbackService.getAdminFeedbackVoList(page, feedbackVo);
    }

    @ApiOperation("删除产品咨询/用户反馈")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "delFeedbackVo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
    })
    public void delFeedbackVo(Long id, HttpServletRequest request) {
        this.feedbackService.delFeedbackVo(id);
        this.adminService.addAdminLog(new AdminLog().setContent("删除id：" + id + "-->产品咨询/用户反馈").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminFeedbackController&delFeedbackVo"));
    }

    @ApiOperation("修改产品咨询/用户反馈状态")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "updateStatus")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodShow", value = "ON/OFF", required = true, dataType = "String"),
    })
    public void updateStatus(Long id, String goodShow, HttpServletRequest request) {
        this.feedbackService.updateStatus(id, goodShow);
        this.adminService.addAdminLog(new AdminLog().setContent("修改id：" + id + "-->产品咨询/用户反馈状态").setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminFeedbackController&updateStatus"));
    }

    @ApiOperation("后台回复")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @PostMapping(value = "updateFeedbackVo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "replyContent", value = "回复内容", required = true, dataType = "Long"),

    })
    public void updateFeedbackVo(@RequestBody Feedback feedback, HttpServletRequest request) {
        feedback.setReplyUserId(this.accountHelper.getUserId());
        feedback.setReplyUserName(this.accountHelper.getUserName());
        this.feedbackService.updateFeedbackVo(feedback);
        this.adminService.addAdminLog(new AdminLog().setContent("后台回复-->id：" + feedback.getId()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminFeedbackController&updateFeedbackVo"));
    }

}
