package com.zsyc.zt.b2b.api.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.service.*;
import com.zsyc.zt.b2b.vo.*;
import com.zsyc.zt.inca.vo.CustomBusinessScopeVo;
import com.zsyc.zt.inca.vo.CustomLicenseVo;
import io.swagger.annotations.*;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;


@Api
@RestController
@RequestMapping("api/member")
public class AdminMemberController {

    @Reference
    private MemberService memberService;
    @Autowired
    private AccountHelper accountHelper;
    @Reference
    private AdminService adminService;
    @Reference
    private ScoreRecordService scoreRecordService;
    @Reference
    private LicenceApplyService licenceApplyService;
    @Reference
    private MemberApplyService memberApplyService;

    @ApiOperation("客户列表")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "zxPhone", value = "手机号", dataType = "String"),
            @ApiImplicitParam(name = "telephone", value = "平台账号", dataType = "String"),
            @ApiImplicitParam(name = "id", value = "客户ID", dataType = "Long"),
            @ApiImplicitParam(name = "name", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "起始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
    })
    @GetMapping("getCustomerByCustomerInfo")
    public IPage<CustomerVo> getCustomerByCustomerInfoVo(Page page, CustomerInfoVo customerInfoVo) {
        return this.memberService.getCustomerByCustomerInfoVo(page, customerInfoVo);
    }


    @ApiOperation("客户详情")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @ApiImplicitParam(name = "id", value = "客户ID", required = true, dataType = "Long")
    @GetMapping("getCustomerListInfoById")
    public CustomerVo getCustomerListInfoById(Long id) {
        return this.memberService.getCustomerVoInfo(id);
    }


    @ApiOperation("重置密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rePassword", value = "重置的密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "memberId", value = "客户ID", required = true, dataType = "Long")
    })
    @PostMapping("rePassword")
    public void rePassword(@RequestBody MemberVo memberVo, HttpServletRequest servletRequest) {
        String ip = this.accountHelper.getIP(servletRequest);
        this.memberService.rePassword(memberVo, ip);
        this.adminService.addAdminLog(new AdminLog().setContent("重置密码-->id：" + memberVo.getMemberId()).setIp(ip).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminMemberController&rePassword"));
    }

    @ApiOperation("个人证件信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "客户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long")
    })
    @GetMapping("getLicenseInfo")
    public IPage<CustomerLicenseVo> getLicenseInfo(Page page, CustomerVo customerVo) {
        return this.memberService.getMemberLicense(page, customerVo);
    }

    @ApiOperation("客户协议价")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "客户ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long")
    })
    @GetMapping("getMemberPubGoodsPriceList")
    public IPage<PubGoodsPriceVo> getMemberPubGoodsPriceList(Page page, Long id) {
        return this.memberService.getMemberPubGoodsPriceList(page, id);
    }

    @ApiOperation("客户集合总单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "customsetname", value = "客户集合名称", dataType = "String"),
            @ApiImplicitParam(name = "customsetid", value = "客户集合ID", dataType = "Long")
    })
    @GetMapping("getAdminMemberList")
    public IPage<BannedVo> getAdminMemberList(Page page, BannedVo bannedVo) {
        return this.memberService.getAdminMemberList(page, bannedVo);
    }

    @ApiOperation("客户集合细单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "customsetid", value = "客户ID", required = true, dataType = "Long")
    })
    @GetMapping("getAdminMemberListById")
    public IPage<BannedVo> getAdminMemberListById(Page page, BannedVo bannedVo) {
        return this.memberService.getAdminMemberListById(page, bannedVo);
    }

    @ApiOperation("后台客户证照信息")
    @ApiImplicitParam(name = "id", value = "客户id", required = true, dataType = "Long")
    @GetMapping("getAdminMemberLicenceList")
    public List<MemberLicenceVo> getAdminMemberLicenceList(Long id) {
        return this.memberService.getAdminMemberLicenceList(id);
    }

    @ApiOperation("后台客户经营范围")
    @ApiImplicitParam(name = "id", value = "客户id", required = true, dataType = "Long")
    @GetMapping("getAdminMemberBusinessScope")
    public List<CustomBusinessScopeVo> getAdminMemberBusinessScope(Long id) {
        return this.memberService.getAdminMemberBusinessScope(id);
    }

    @ApiOperation("企业认证列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "companyName", value = "企业名称", dataType = "String"),
            @ApiImplicitParam(name = "companyType", value = "企业类型", dataType = "String"),
            @ApiImplicitParam(name = "contactName", value = "联系人", dataType = "String"),
            @ApiImplicitParam(name = "contactPhone", value = "联系人手机", dataType = "String"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "认证审核状态", required = true, dataType = "String"),
    })
    @GetMapping("getAuthenticationList")
    public IPage<MemberApplyVo> getAuthenticationList(Page page, MemberApplyVo memberApplyVo) {
        return this.memberService.getAuthenticationList(page, memberApplyVo);
    }

    @ApiOperation("客户认证申请详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "客户id", required = true, dataType = "Long")
    })
    @GetMapping("getLicenceApplyInfo")
    public List<LicenceApply> getLicenceApplyInfo(Long id) {
        return this.licenceApplyService.getLicenceApplyInfo(id);
    }

    @ApiOperation("客户注册申请详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "客户id", required = true, dataType = "Long")
    })
    @GetMapping("getMemberApplyInfo")
    public MemberApplyVo getMemberApplyInfo(Long id) {
        return this.memberApplyService.getMemberApplyInfo(id);
    }

    @ApiOperation("编辑审核->认证申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "申请id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "licenceStatus", value = "状态", required = true, dataType = "String"),
            @ApiImplicitParam(name = "licenceReason", value = "审核结果", dataType = "String"),
            @ApiImplicitParam(name = "licenceRemark", value = "备注", dataType = "String"),
            @ApiImplicitParam(name = "memberStatus", value = "状态", required = true, dataType = "String"),
            @ApiImplicitParam(name = "memberReason", value = "审核结果", dataType = "String"),
            @ApiImplicitParam(name = "memberRemark", value = "备注", dataType = "String"),
            @ApiImplicitParam(name = "erpCustomId", value = "erp客户id", required = true, dataType = "Long")
    })
    @PostMapping("editors")
    public void editors(@RequestBody MemberApplyVo memberApplyVo, HttpServletRequest request) {
        this.memberService.editors(memberApplyVo, this.accountHelper.getUserId());
        this.adminService.addAdminLog(new AdminLog().setContent("编辑审核/认证申请-->申请id：" + memberApplyVo.getId()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminMemberController&editors"));
    }

    @ApiOperation("新增用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "erpUserId", value = "erp用户id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "telephone", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "rePassword", value = "二次密码", required = true, dataType = "String"),
    })
    @PostMapping("addMember")
    public void addMember(@RequestBody MemberVo memberVo, HttpServletRequest request) {
        this.memberService.addMember(memberVo);
        this.adminService.addAdminLog(new AdminLog().setContent("新增用户-->id：" + memberVo.getErpUserId()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminMemberController&addMember"));
    }


    @ApiOperation("修改手机号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "telephone", value = "telephone", required = true, dataType = "String"),
            @ApiImplicitParam(name = "customerid", value = "customerid", required = true, dataType = "String")
    })
    @GetMapping("updateMemberTelephone")
    public void updateMemberTelephone(Long customerid, String telephone, HttpServletRequest request) {
        this.memberService.updateMemberTelephone(customerid, telephone);
        this.adminService.addAdminLog(new AdminLog().setContent("修改手机号-->id：" + customerid).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminMemberController&updateMemberTelephone"));

    }

    @ApiOperation("客户日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long")
    })
    @GetMapping("getMemberLogList")
    public IPage<MemberLogVo> getMemberLogList(Page page,MemberLogVo memberLogVo) {
        return this.memberService.getMemberLogList(page,memberLogVo);
    }

    @ApiOperation("积分记录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "memberId", value = "客户id", dataType = "Long"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
            @ApiImplicitParam(name = "memberName", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "telephone", value = "手机号", dataType = "String"),
    })
    @GetMapping("getAdminScoreRecordList")
    public IPage<ScoreRecordVo> getAdminScoreRecordList(Page page, ScoreRecordVo scoreRecordVo) {
        return this.scoreRecordService.getScoreRecordList(page, scoreRecordVo);
    }

    @ApiOperation("积分记录变更")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "积分记录id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "content", value = "变更内容", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "chanScore", value = "变更积分", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "Long"),
    })
    @PostMapping("updateScoreRecord")
    public void updateScoreRecord(@RequestBody ScoreRecord scoreRecord, HttpServletRequest request) {
        this.scoreRecordService.updateScoreRecord(scoreRecord);
        this.adminService.addAdminLog(new AdminLog().setContent("积分记录变更-->id：" + scoreRecord.getId()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminMemberController&updateScoreRecord"));
    }

    @ApiOperation("我的运输地址")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "memberId", value = "客户id", dataType = "Long"),
    })
    @GetMapping("getAdminBmsTrPosDef")
    public List<BmsTrPosDefVo> getAdminBmsTrPosDef(Long memberId) {
        return this.memberService.getMyBmsTrPosDef(memberId);
    }

    @ApiOperation("所有证件照信息-申请的")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "erpCustomerId", value = "客户id", dataType = "Long"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
            @ApiImplicitParam(name = "customname", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "telephone", value = "手机号", dataType = "String"),
    })
    @GetMapping("getLicenceApplyVoList")
    public IPage<LicenceApplyVo> getLicenceApplyVoList(Page page,LicenceApplyVo licenceApplyVo){
        return this.memberService.getLicenceApplyVoList(page,licenceApplyVo);
    }

    @ApiOperation("所有证件照信息-审核通过的")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "erpCustomerId", value = "客户id", dataType = "Long"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
            @ApiImplicitParam(name = "customname", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "telephone", value = "手机号", dataType = "String"),
    })
    @GetMapping("getLicenceVoList")
    public  IPage<LicenceVo> getLicenceVoList(Page page,LicenceVo licenceVo){
        return this.memberService.getLicenceVoList(page,licenceVo);
    }

    @ApiOperation("审核证照认证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true,dataType = "Long"),
            @ApiImplicitParam(name = "status", value = "状态", required = true,dataType = "String"),
            @ApiImplicitParam(name = "remark", value = "备注",dataType = "String"),
    })
    @GetMapping("checkLicenceApplyVoStatus")
    public void checkLicenceApplyVoStatus(Long id,String status,String remark, HttpServletRequest request){
        this.memberService.checkLicenceApplyVoStatus(id,status,remark);
        this.adminService.addAdminLog(new AdminLog().setContent("审核证照认证-->id：" + id).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminMemberController&checkLicenceApplyVoStatus"));

    }


    @ApiOperation("编辑证照认证")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true,dataType = "Long"),
    })
    @PostMapping("updateLicenceApply")
    public void updateLicenceApply(@RequestBody Licence licenceApply, HttpServletRequest request){
        this.memberService.updateLicenceApply(licenceApply);
        this.adminService.addAdminLog(new AdminLog().setContent("编辑证照认证-->id：" + licenceApply.getId()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId()).setUserName(this.accountHelper.getUserName()).setUrl("AdminMemberController&updateLicenceApply"));
    }
}
