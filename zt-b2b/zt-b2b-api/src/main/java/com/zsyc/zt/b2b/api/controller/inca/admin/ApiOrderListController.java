package com.zsyc.zt.b2b.api.controller.inca.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.order.service.IApiOrderListService;
import com.zsyc.zt.order.vo.ApiOrderListVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Api(tags = "商品销售计划API接口")
@RestController
@RequestMapping("/api/apiOrderList")
@Slf4j
public class ApiOrderListController {

    @Reference
    private IApiOrderListService iApiOrderListService;

    @ApiOperation(value =   "分页查询对接订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "startDate", value = "开始查询日期",  dataType = "LocalDate"),
            @ApiImplicitParam(name = "endDate", value = "截止查询日期",  dataType = "LocalDate"),
            @ApiImplicitParam(name = "", value = "", dataType = "")
    })
    @GetMapping("/list")
    public IPage<ApiOrderListVo> selectListPage(Page<ApiOrderListVo> page, ApiOrderListVo apiOrderListVo){
        return iApiOrderListService.selectListPage(page,apiOrderListVo);
    }

    @ApiOperation(value =   "ID查询对接订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "Long")
    })
    @GetMapping("/get")
    public ApiOrderListVo getApiOrder(@RequestParam("id") Long id){
        return iApiOrderListService.getApiOrder(id);
    }

    @ApiOperation(value =   "ID删除对接订单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "Long")
    })
    @PostMapping("/delete")
    public Integer deleteApiOrder(@RequestBody List<Long> ids){
        return iApiOrderListService.deleteApiOrder(ids);
    }

}
