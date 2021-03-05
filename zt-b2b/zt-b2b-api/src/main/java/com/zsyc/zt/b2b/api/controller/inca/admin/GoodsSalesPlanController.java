package com.zsyc.zt.b2b.api.controller.inca.admin;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.service.AdminService;
import com.zsyc.zt.b2b.vo.UserVo;
import com.zsyc.zt.inca.service.GoodsService;
import com.zsyc.zt.inca.vo.GoodsVo;
import com.zsyc.zt.report.entity.GoodsSalesPlanDtl;
import com.zsyc.zt.report.service.IGoodsSalesPlanDocService;
import com.zsyc.zt.report.service.IGoodsSalesPlanDtlService;
import com.zsyc.zt.report.vo.GoodsSalesPlanDocVo;
import com.zsyc.zt.report.vo.GoodsSalesPlanDtlVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = "商品销售计划API接口")
@RestController
@RequestMapping("api/report/goods/plan")
@Slf4j
public class GoodsSalesPlanController {

    @Reference
    private IGoodsSalesPlanDocService iGoodsSalesPlanDocService;
    @Reference
    private IGoodsSalesPlanDtlService iGoodsSalesPlanDtlService;
    @Reference
    private GoodsService goodsService;

    @Autowired
    private AccountHelper accountHelper;

    @Reference
    private AdminService adminService;

    @ApiOperation(value =   "获取商品销售计划数据(分页)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodsId", value = "商品ID", dataType = "Long"),
            @ApiImplicitParam(name = "useStatus", value = "状态", dataType = "Integer"),
            @ApiImplicitParam(name = "planName", value = "计划名称",  dataType = "String"),
            @ApiImplicitParam(name = "startDate", value = "开始查询日期",  dataType = "LocalDate"),
            @ApiImplicitParam(name = "endDate", value = "截止查询日期",  dataType = "LocalDate"),
    })
    @GetMapping("/list")
    public IPage<GoodsSalesPlanDocVo> selectPlanListPage(Page<GoodsSalesPlanDocVo> page, GoodsSalesPlanDocVo goodsSalesPlanDocVo,Long goodsId){
        return iGoodsSalesPlanDocService.selectPlanListPage(page, goodsSalesPlanDocVo, goodsId);
    }

    @ApiOperation(value =   "新增计划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsSalesPlanDocVo", value = "商品销售计划对象", required = true, dataType = "GoodsSalesPlanDocVo")
    })
    @PostMapping("/addPlan")
    public Map<String,Object> savePlan(@RequestBody GoodsSalesPlanDocVo goodsSalesPlanDocVo){
        AssertExt.notNull(goodsSalesPlanDocVo,"参数为空");
        UserVo userInfo = adminService.getUserInfo(this.accountHelper.getUserId());
        goodsSalesPlanDocVo.setInputId(userInfo.getErpEmployeeId());
        goodsSalesPlanDocVo.setInputName(userInfo.getEmployeeName());
        return iGoodsSalesPlanDocService.insertPlan(goodsSalesPlanDocVo);
    }

    @ApiOperation(value =   "新增计划商品(临时状态)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsSalesPlanDtlVoList", value = "商品销售计划对象", required = true, dataType = "List"),
    })
    @PostMapping("/addPlanGoods")
    public Map<String, Object> insertDtlList(@RequestBody List<GoodsSalesPlanDtlVo> goodsSalesPlanDtlVoList){
        AssertExt.notEmpty(goodsSalesPlanDtlVoList,"参数不存在");
        UserVo userInfo = adminService.getUserInfo(this.accountHelper.getUserId());
        for (GoodsSalesPlanDtlVo vo : goodsSalesPlanDtlVoList) {
            AssertExt.hasId(vo.getPlanId(),"计划ID为空");
            vo.setOperatorId(userInfo.getErpEmployeeId());
            vo.setOperator(userInfo.getEmployeeName());
        }
        return iGoodsSalesPlanDtlService.changDtlList(goodsSalesPlanDtlVoList);
    }

    @ApiOperation(value =   "校验商品基本单位")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsIds", value = "计划ID数组", required = true, dataType = "List")
    })
    @PostMapping("/checkGoods")
    public Map<String,Object> checkGoods(@RequestBody List<GoodsSalesPlanDtlVo> goodsSalesPlanDtlVoList){
        return iGoodsSalesPlanDtlService.checkGoodsV2(goodsSalesPlanDtlVoList);
    }

    @ApiOperation(value =   "查询计划状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "planId", value = "计划ID", required = true, dataType = "Long")
    })
    @GetMapping("/getPlanUseStatus")
    public Integer getPlanUseStatus(@RequestParam("planId") Long planId){
        return iGoodsSalesPlanDocService.getPlanUseStatus(planId);
    }

    @ApiOperation(value =   "删除计划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "planId", value = "计划ID", required = true, dataType = "Long")
    })
    @GetMapping("/deletePlanById")
    public Map<String,Object> deletePlanById(@RequestParam("planId") Long planId){
        return iGoodsSalesPlanDocService.deletePlanById(planId);
    }

    @ApiOperation(value =   "删除计划商品(所有)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "planId", value = "计划ID", required = true, dataType = "Long")
    })
    @GetMapping("/deletePlanGoods")
    public Map<String,Object> deletePlanGoods(@RequestParam("planId") Long planId){
        return iGoodsSalesPlanDtlService.deletePlanGoods(planId);
    }

    @ApiOperation(value =   "删除计划商品(单个)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "planDtlId", value = "计划细单ID", required = true, dataType = "Long")
    })
    @GetMapping("/deleteGoodsByPlanDtlId")
    public Map<String,Object> deleteGoodsByPlanId(@RequestParam("planDtlId") Long planDtlId){
        return iGoodsSalesPlanDtlService.deleteGoodsByPlanId(planDtlId);
    }

    @ApiOperation(value =   "查询ERP货品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "goodsId", value = "商品ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "goodsName", value = "商品名称", dataType = "String"),
            @ApiImplicitParam(name = "factoryId", value = "生产厂商ID", dataType = "Long"),
            @ApiImplicitParam(name = "factoryName", value = "生产厂商名称", dataType = "String")
    })
    @GetMapping("/selectGoodsList")
    public IPage<GoodsVo> selectGoodsList(Page<GoodsVo> page,GoodsVo goodsVo){
        return iGoodsSalesPlanDocService.selectGoodsList(page,goodsVo);
    }

    @ApiOperation(value =   "查看计划商品(分页)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "planId", value = "计划ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "useStatus", value = "状态",  dataType = "Long"),
            @ApiImplicitParam(name = "goodsId", value = "商品ID",  dataType = "Long"),
            @ApiImplicitParam(name = "goodsName", value = "商品名称",  dataType = "Long"),
            @ApiImplicitParam(name = "factoryId", value = "生产厂商ID",  dataType = "Long"),
            @ApiImplicitParam(name = "factoryName", value = "生产厂商名称",  dataType = "Long")
    })
    @GetMapping("/selectPlanGoodsListPage")
    public List<GoodsSalesPlanDtlVo> selectPlanGoodsListPage(GoodsSalesPlanDtlVo goodsSalesPlanDtlVo){
        return iGoodsSalesPlanDtlService.selectPlanGoodsListPage(goodsSalesPlanDtlVo);
    }

    @ApiOperation(value =   "修改计划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "planName", value = "计划名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "planNum", value = "计划销售量", required = true, dataType = "Double"),
            @ApiImplicitParam(name = "planStartDate", value = "计划开始日期", required = true, dataType = "LocalDate"),
            @ApiImplicitParam(name = "planEndDate", value = "计划结束日期", required = true, dataType = "LocalDate"),
    })
    @PostMapping("/modifyPlanById")
    public Map<String,Object> modifyPlanById(@RequestBody GoodsSalesPlanDocVo goodsSalesPlanDocVo){
        return iGoodsSalesPlanDocService.modifyPlanById(goodsSalesPlanDocVo);
    }

    @ApiOperation(value =   "变更计划状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "planId", value = "计划ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "useStatus", value = "状态", required = true, dataType = "Integer")
    })
    @GetMapping("/updatePlanUseStatusById")
    public Integer updatePlanUseStatusById(@RequestParam("planId") Long planId,
                                           @RequestParam("useStatus") Integer useStatus){
        UserVo userInfo = adminService.getUserInfo(this.accountHelper.getUserId());
        return iGoodsSalesPlanDocService.updatePlanUseStatusById(planId,useStatus,userInfo.getErpEmployeeId(),userInfo.getEmployeeName());
    }

}
