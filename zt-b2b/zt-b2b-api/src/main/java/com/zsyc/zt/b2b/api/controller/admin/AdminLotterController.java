package com.zsyc.zt.b2b.api.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.entity.AdminLog;
import com.zsyc.zt.b2b.entity.LotteryGoods;
import com.zsyc.zt.b2b.service.AdminService;
import com.zsyc.zt.b2b.service.LotterDailQualificationsService;
import com.zsyc.zt.b2b.vo.LotterDailQualificationsVo;
import com.zsyc.zt.b2b.vo.LotteryGoodsVo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api
@RestController
@RequestMapping("api/adminLotter")
@Slf4j
public class AdminLotterController {
    @Autowired
    private AccountHelper accountHelper;
    @Reference
    private LotterDailQualificationsService lotterDailQualificationsService;
    @Reference
    private AdminService adminService;

    @ApiOperation("抽奖商品列表")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getLotteryGoodsList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
    })
    public IPage<LotteryGoodsVo> getLotteryGoodsList(Page page, LotteryGoodsVo lotteryGoodsVo) {
        return this.lotterDailQualificationsService.getLotteryGoodsList(page, lotteryGoodsVo);
    }

    @ApiOperation("添加抽奖商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "erpGoodsId", value = "erp商品id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "lotNum", value = "抽奖数量", required = true, dataType = "String"),
            @ApiImplicitParam(name = "lotTotalNum", value = "抽奖原数量", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态，上下架， OFF：不启用  ON:启用", dataType = "Integer"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "Integer"),
    })
    @PostMapping("addLotteryGoods")
    public void addLotteryGoods(@RequestBody LotteryGoods lotteryGoods, HttpServletRequest request){
        lotteryGoods.setUserId(this.accountHelper.getUserId());
        this.lotterDailQualificationsService.addLotteryGoods(lotteryGoods);
        this.adminService.addAdminLog(new AdminLog().setContent("添加抽奖商品").setIp(this.accountHelper.getIP(request))
                .setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminLotterController&addLotteryGoods"));

    }

    @ApiOperation("修改抽奖商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "erp商品id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "erpGoodsId", value = "erp商品id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "lotNum", value = "抽奖数量", required = true, dataType = "String"),
            @ApiImplicitParam(name = "lotTotalNum", value = "抽奖原数量", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态，上下架， OFF：不启用  ON:启用", dataType = "Integer"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "Integer"),
    })
    @PostMapping("updateLotteryGoods")
    public  void updateLotteryGoods(@RequestBody LotteryGoods lotteryGoods, HttpServletRequest request){
        this.lotterDailQualificationsService.updateLotteryGoods(lotteryGoods);
        this.adminService.addAdminLog(new AdminLog().setContent("修改抽奖商品").setIp(this.accountHelper.getIP(request))
                .setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminLotterController&editGoods"));

    }

}
