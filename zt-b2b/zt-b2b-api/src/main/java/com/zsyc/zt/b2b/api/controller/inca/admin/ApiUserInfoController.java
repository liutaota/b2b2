package com.zsyc.zt.b2b.api.controller.inca.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.service.MemberService;
import com.zsyc.zt.b2b.vo.ApiMemberVo;
import com.zsyc.zt.b2b.vo.CustomerInfoVo;
import com.zsyc.zt.b2b.vo.CustomerVo;
import com.zsyc.zt.b2b.vo.MemberVo;
import com.zsyc.zt.order.service.IApiUserInfoService;
import com.zsyc.zt.order.vo.ApiOrderListVo;
import com.zsyc.zt.order.vo.ApiUserInfoVo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Api(tags = "对接账号")
@RestController
@RequestMapping("/api/apiUserInfo")
@Slf4j
public class ApiUserInfoController {

    @Reference
    private IApiUserInfoService iApiUserInfoService;

    @Reference
    private MemberService memberService;

    @ApiOperation(value =   "分页查询对接账号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "startDate", value = "开始查询日期",  dataType = "LocalDate"),
            @ApiImplicitParam(name = "endDate", value = "截止查询日期",  dataType = "LocalDate"),
            @ApiImplicitParam(name = "", value = "", dataType = "")
    })
    @GetMapping("/list")
    public IPage<ApiUserInfoVo>  selectListPage(Page<ApiUserInfoVo> page,ApiUserInfoVo apiUserInfoVo){
        return iApiUserInfoService.selectListPage(page,apiUserInfoVo);
    }

    @ApiOperation(value =   "ID查询对接账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "Long")
    })
    @GetMapping("/get")
    public ApiUserInfoVo getApiUserInfo(@RequestParam("id") Long id){
        return iApiUserInfoService.getApiUserInfo(id);
    }

    @ApiOperation(value =   "批量删除对接账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "Long")
    })
    @PostMapping("/delete")
    public Integer deleteUserInfos(@RequestBody Long[] ids){
        return iApiUserInfoService.deleteUserInfos(Arrays.asList(ids));
    }

    @ApiOperation(value =   "修改账号信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "Long")
    })
    @PostMapping("/update")
    public Integer updateUserInfo(@RequestBody ApiUserInfoVo apiUserInfoVo){
        return iApiUserInfoService.updateUserInfo(apiUserInfoVo);
    }

    @ApiOperation(value =   "新增对接账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键ID", required = true, dataType = "Long")
    })
    @PostMapping("/save")
    public Integer saveUserInfo(@RequestBody ApiUserInfoVo apiUserInfoVo){
        return iApiUserInfoService.saveUserInfo(apiUserInfoVo);
    }

    @ApiOperation("客户列表")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "telephone", value = "手机号", dataType = "String"),
            @ApiImplicitParam(name = "id", value = "客户ID", dataType = "Long"),
            @ApiImplicitParam(name = "name", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    @GetMapping("selectApiMemberVoPage")
    public IPage<ApiMemberVo> selectApiMemberVoPage(Page<ApiMemberVo> page, ApiMemberVo apiMemberVo) {
        return memberService.selectApiMemberVoPage(page, apiMemberVo);
    }

}
