package com.zsyc.zt.b2b.api.controller.PCWeb;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.service.FeedbackService;
import com.zsyc.zt.b2b.vo.FeedbackVo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping("api/feedback")
@Slf4j
public class PCFeedbackController {
    @Autowired
    private AccountHelper accountHelper;
    @Reference
    private FeedbackService feedbackService;

    @ApiOperation("产品咨询")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @PostMapping(value = "addFeedbackByGoods")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "erpGoodsId", value = "erp商品id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "erpGoodsName", value = "商品名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "memberPhone", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "code", value = "code", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "邮箱", dataType = "String"),

    })
    public void addFeedbackByGoods(@RequestBody FeedbackVo feedback) {
        feedback.setMemberId(this.accountHelper.getUserId());
        this.feedbackService.addFeedbackByGoods(feedback);
    }

    @ApiOperation("用户反馈/平台建议")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @PostMapping(value = "addFeedbackByPlat")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberPhone", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "邮箱", dataType = "String"),

    })
    public void addFeedbackByPlat(@RequestBody FeedbackVo feedback) {
        feedback.setMemberId(this.accountHelper.getUserId());
        this.feedbackService.addFeedbackByPlat(feedback);
    }

    @ApiOperation("产品咨询/用户反馈列表 type区分")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getFeedbackVoList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "type", value = "GOODS/PLATFROM", dataType = "String"),
    })
    public IPage<FeedbackVo> getFeedbackVoList(Page page, FeedbackVo feedbackVo) {
        feedbackVo.setMemberId(this.accountHelper.getUserId());
        return this.feedbackService.getFeedbackVoList(page, feedbackVo);
    }


    @ApiOperation("产品咨询前台显示")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getFeedbackGoodsShowList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "erpGoodsId", value = "erp商品id", required = true, dataType = "Long"),
    })
    public IPage<FeedbackVo> getFeedbackGoodsShowList(Page page, FeedbackVo feedbackVo) {
        return this.feedbackService.getFeedbackGoodsShowList(page, feedbackVo);
    }
}
