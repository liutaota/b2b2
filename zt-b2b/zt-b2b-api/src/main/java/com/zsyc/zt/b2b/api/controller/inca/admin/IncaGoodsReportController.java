package com.zsyc.zt.b2b.api.controller.inca.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.DeliveryAmount;
import com.zsyc.zt.b2b.vo.DeliveryAmountVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("货品相关报表")
@RestController
@RequestMapping("api/incaGoodsPutUpReport")
public class IncaGoodsReportController {

    @ApiOperation("货品上架")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "daName", value = "规则名称", dataType = "String"),
            @ApiImplicitParam(name = "areaName", value = "起送地区名称", required = true, dataType = "String")
    })
    @GetMapping("pageForGoodsPutUp")
    public IPage<DeliveryAmount> pageForGoodsPutUp(Page page, DeliveryAmountVo deliveryAmountVo) {
       /* return this.deliveryAmountService.getDeliveryAmount(page, deliveryAmountVo);*/

        return null;
    }
}
