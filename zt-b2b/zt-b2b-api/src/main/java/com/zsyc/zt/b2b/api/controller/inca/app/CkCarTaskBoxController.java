package com.zsyc.zt.b2b.api.controller.inca.app;


import cn.hutool.core.util.ObjectUtil;
import com.zsyc.zt.inca.service.ICkCarTaskBoxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author peiqy
 * @since 2020-12-07
 */
@Api(tags = "任务销售单装箱表API接口",value = "任务销售单装箱表")
@RestController
@RequestMapping("api/inca/ckCarTaskBox")
public class CkCarTaskBoxController {

    @Reference
    private ICkCarTaskBoxService ckCarTaskBoxService;

    @ApiOperation(value =   "根据箱号查询任务细单客户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "taskPackId", value = "箱号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "customId", value = "客户ID", required = true, dataType = "Long")
    })
    //@GetMapping("/get/custom/v2/{taskId}/{taskPackId}/{customId}")
    @GetMapping("/get/custom/v2")
    public Map<String, Object> getCustomAndPackId(@RequestParam("taskId") Long taskId,
                                                     @RequestParam("taskPackId") String packId,
                                                     @RequestParam("customId") Long currentCustomId){
        return ckCarTaskBoxService.getCustomAndPackId(taskId, packId, currentCustomId);
    }

    @ApiOperation(value =   "根据箱号ID删除箱号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskDtlId", value = "任务细单ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "taskPackId", value = "箱号信息ID", required = true, dataType = "Long")
    })
    //@DeleteMapping("/delete/{taskDtlId}/{taskBoxId}")
    @DeleteMapping("/delete")
    public Map<String, Integer> getCustomAndPackId(@RequestParam("taskDtlId") Long taskDtlId,
                                                     @RequestParam("taskBoxId") Long taskBoxId){
        return ckCarTaskBoxService.deleteTaskBoxByTaskBoxId(taskDtlId, taskBoxId);
    }

    @ApiOperation(value =   "展示客户的订单集")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskDtlId", value = "任务细单ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "customId", value = "客户ID", required = true, dataType = "Long")
    })
    //@GetMapping("/get/sales/list/{taskDtlId}/{customId}")
    @GetMapping("/get/sales/list")
    public Map<String, Object> getSalesAndReceiptList(@RequestParam("taskDtlId") Long taskDtlId, @RequestParam("customId") Long customId){
        return ckCarTaskBoxService.getSalesAndReceiptList(taskDtlId,customId);
    }

}
