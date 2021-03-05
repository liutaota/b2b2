package com.zsyc.zt.b2b.api.controller.inca.app;


import com.zsyc.zt.inca.dto.CkCarTaskDtlDto;
import com.zsyc.zt.inca.service.ICkCarTaskDtlService;
import com.zsyc.zt.inca.vo.CkCarTaskDtlVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author peiqy
 * @since 2020-12-05
 */
@Api(tags = "出车任务细单API接口",value = "出车任务细单表")
@RestController
@RequestMapping("api/inca/ckCarTaskDtl")
public class CkCarTaskDtlController {

    @Reference
    private ICkCarTaskDtlService ckCarTaskDtlService;

    @ApiOperation(value =   "查询出车任务细单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paramObj", value = "查询参数", required = true, dataType = "CkCarTaskDtlDto")
    })
    @GetMapping("/list")
    public List<CkCarTaskDtlVo> selectPubGoods(@RequestBody CkCarTaskDtlDto paramObj){
        return ckCarTaskDtlService.selectByObj(paramObj);
    }

    @ApiOperation(value =   "根据出车任务细单id查找信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paramObj", value = "查询参数", required = true, dataType = "CkCarTaskDtlDto")
    })
    @GetMapping("/getByTaskDtlId")
    public CkCarTaskDtlVo getByTaskDtlId(@RequestBody CkCarTaskDtlDto paramObj){
        return ckCarTaskDtlService.getByTaskDtlId(paramObj);
    }

    @ApiOperation(value =   "删除细单及西单下所有箱号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "taskId", value = "任务ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "taskDtlId", value = "细单ID", required = true, dataType = "Long")
    })
//    @DeleteMapping("/delete/{taskId}/{taskDtlId}")
    @DeleteMapping("/delete/all")
    public Map<String, Integer> deleteTaskDtl(@RequestParam("taskId") Long taskId, @RequestParam("taskDtlId") Long taskDtlId){
        return ckCarTaskDtlService.deleteTaskDtlById(taskId,taskDtlId);
    }

    @ApiOperation(value =   "查询车辆运输货品对应的客户集")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scheduleId", value = "调度单ID", required = true, dataType = "Long")
    })
    //@GetMapping("/get/dtl/list/{scheduleId}")
    @GetMapping("/get/dtl/list")
    public List<CkCarTaskDtlVo> getCustomList(@RequestParam("scheduleId") Long scheduleId){
        return ckCarTaskDtlService.getCustomList(scheduleId);
    }

    @ApiOperation(value =   "搜索车辆运输货品对应的客户集")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scheduleId", value = "调度单ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "searchStr", value = "搜索内容", required = true, dataType = "String")
    })
    @GetMapping("/search/custom")
    public List<CkCarTaskDtlVo> searchCustom(@RequestParam("scheduleId") Long scheduleId, @RequestParam("searchStr") String searchStr){
        return ckCarTaskDtlService.searchCustom(scheduleId,searchStr);
    }

}