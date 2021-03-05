package com.zsyc.zt.b2b.api.controller.PCWeb;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.entity.LotteryDialDetail;
import com.zsyc.zt.b2b.service.LotterDailQualificationsService;
import com.zsyc.zt.b2b.vo.GoodsInfoVo;
import com.zsyc.zt.b2b.vo.LotterDailQualificationsVo;
import com.zsyc.zt.b2b.vo.LotteryDialDetailVo;
import com.zsyc.zt.b2b.vo.LotteryGoodsVo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tang on 2021-01-04.
 */
@Api
@RestController
@RequestMapping("api/pcLotter")
@Slf4j
public class PCLotterController {
    @Autowired
    private AccountHelper accountHelper;
    @Reference
    private LotterDailQualificationsService lotterDailQualificationsService;

    @ApiOperation("我的抽奖次数记录")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getMyLotterDailQualificationsList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "orderId", value = "订单id",  dataType = "Long"),
    })
    public IPage<LotterDailQualificationsVo> getMyLotterDailQualificationsList(Page page, LotterDailQualificationsVo lotterDailQualifications) {
        lotterDailQualifications.setMemberId(this.accountHelper.getUserId());
        return this.lotterDailQualificationsService.getMyLotterDailQualificationsList(page, lotterDailQualifications);
    }


    @ApiOperation("我的抽奖奖品记录")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getMyLotteryDialDetailVoList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
    })
    public IPage<LotteryDialDetailVo> getMyLotteryDialDetailVoList(Page page, LotteryDialDetailVo lotteryDialDetailVo) {
        lotteryDialDetailVo.setMemberId(this.accountHelper.getUserId());
        return this.lotterDailQualificationsService.getMyLotteryDialDetailVoList(page, lotteryDialDetailVo);
    }

    @ApiOperation("抽奖")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "addLotteryDialDetailVo")
    public LotteryDialDetail addLotteryDialDetailVo(LotteryDialDetail lotteryDialDetailVo) {
        lotteryDialDetailVo.setMemberId(this.accountHelper.getUserId());
        return this.lotterDailQualificationsService.addLotteryDialDetailVo(lotteryDialDetailVo);
    }

    @ApiOperation("抽奖商品")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getGoodsInfoVoByLotteryList")
    public List<LotteryGoodsVo> getGoodsInfoVoByLotteryList() {
        return this.lotterDailQualificationsService.getGoodsInfoVoByLotteryList();
    }

    @ApiOperation("我的奖品")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getMyGoodsInfoVoByLotteryList")
    public List<LotteryDialDetailVo> getMyGoodsInfoVoByLotteryList() {
        return this.lotterDailQualificationsService.getMyGoodsInfoVoByLotteryList(this.accountHelper.getUserId());
    }

    @ApiOperation("我的抽奖次数--订单")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @GetMapping(value = "getMyLotterCount")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "orderId", required = true, dataType = "Long"),
    })
    public Integer getMyLotterCount(Long orderId){
        return this.lotterDailQualificationsService.getMyLotterCount(this.accountHelper.getUserId(),orderId);
    }
}
