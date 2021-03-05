package com.zsyc.zt.b2b.api.controller.inca.app;

import com.zsyc.zt.inca.dto.CkCarScheduleDocDto;
import com.zsyc.zt.inca.service.ICkCarScheduleDocService;
import com.zsyc.zt.inca.vo.CkCarScheduleDocVo;
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
 * @since 2020-12-02
 */

@Api(tags = "出车调度API接口",value = "出车调度表")
@RestController
@RequestMapping("api/inca/ckCarScheduleDoc")
public class CkCarScheduleDocController {

    @Reference
    private ICkCarScheduleDocService ckCarScheduleDocService;

    @ApiOperation(value =   "查询调度信息")
    @GetMapping("/list")
    public List<CkCarScheduleDocVo> selectPubGoods(){
        return this.ckCarScheduleDocService.listByObj();
    }

    @ApiOperation(value =   "根据条件查询调度信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "paramObj", value = "搜索内容", required = true, dataType = "String")
    })
    @GetMapping("/selectByObjOption")
    public List<CkCarScheduleDocVo> selectByObjOption(@RequestBody CkCarScheduleDocDto paramObj){

        return this.ckCarScheduleDocService.selectByObjOption(paramObj);
    }

    @ApiOperation(value =   "根据调度id或者姓名查找信息")
    @GetMapping("/getByScheduleId")
    public CkCarScheduleDocVo getByScheduleId(CkCarScheduleDocDto paramObj){
        return this.ckCarScheduleDocService.getByScheduleId(paramObj);
    }

    @ApiOperation(value =   "扫码匹配当天调度单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "vehicleId", value = "车辆ID", required = true, dataType = "Long")
    })
    //@GetMapping("/get/{vehicleId}")
    @GetMapping("/get/scheduleByVehicleId")
    public CkCarScheduleDocVo getByScheduleBill(@RequestParam("vehicleId") Long vehicleId){
        return this.ckCarScheduleDocService.getByScheduleBill(vehicleId);
    }

}
