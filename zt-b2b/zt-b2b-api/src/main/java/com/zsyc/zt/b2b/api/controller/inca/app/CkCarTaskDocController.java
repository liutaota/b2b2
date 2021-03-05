package com.zsyc.zt.b2b.api.controller.inca.app;

import cn.hutool.core.util.ObjectUtil;
import com.zsyc.zt.inca.dto.CkCarTaskDtlDto;
import com.zsyc.zt.inca.service.ICkCarTaskDocService;
import com.zsyc.zt.inca.vo.CkCarTaskDocVo;
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
@Api(tags = "出车任务总单API接口")
@RestController
@RequestMapping("api/inca/ckCarTaskDoc")
public class CkCarTaskDocController {

    @Reference
    private ICkCarTaskDocService iCkCarTaskDocService;

    @ApiOperation(value =   "(分派任务)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scheduleId", value = "出车调度ID", required = true, dataType = "Long")
    })
//    @GetMapping("/get/{scheduleId}")
    @GetMapping("/get")
    public CkCarTaskDocVo checkTask(@RequestParam("scheduleId") Long scheduleId){
        return this.iCkCarTaskDocService.getCarTask(scheduleId);
    }

    @ApiOperation(value =   "上传任务数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ckCarTaskDtlDtoList", value = "任务细单集合", required = true, dataType = "List")
    })
    @PostMapping("/save")
    public Map<String,String> saveCarTask(@RequestBody List<CkCarTaskDtlDto> ckCarTaskDtlDtoList){
        return iCkCarTaskDocService.saveCarTask(ckCarTaskDtlDtoList);
    }

}
