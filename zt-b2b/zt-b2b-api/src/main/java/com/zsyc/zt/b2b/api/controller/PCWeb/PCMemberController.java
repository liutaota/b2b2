package com.zsyc.zt.b2b.api.controller.PCWeb;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.service.*;
import com.zsyc.zt.b2b.vo.*;
import com.zsyc.zt.inca.entity.BmsSaInvInfo;
import com.zsyc.zt.inca.entity.GspLicenseType;
import com.zsyc.zt.inca.vo.BusinessScopeVo;
import com.zsyc.zt.inca.vo.CustomBusinessScopeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.List;

/**
 * Created by tang on 2020-07-24.
 */
@Api
@RestController
@RequestMapping("api/pcMember")
@Slf4j
public class PCMemberController {
    @Autowired
    private AccountHelper accountHelper;
    @Reference
    private MemberService memberService;
    @Reference
    private LoginService loginService;
    @Reference
    private ScoreRecordService scoreRecordService;
    @Reference
    private SettingService settingService;

    @Reference
    private OrderInfoService orderInfoService;
    @Reference
    private LicenceApplyService licenceApplyService;
    @Reference
    private HelpService helpService;

    @ApiOperation("客户注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "telephone", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "code", value = "code", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "rePassword", value = "第二次密码", required = true, dataType = "String"),
    })
    @GetMapping("register")
    public Member register(String telephone, String code, String password, String rePassword, HttpServletRequest request) {
        return this.loginService.register(telephone, code, password, rePassword, this.accountHelper.getIP(request));
    }

    @ApiOperation("客户修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "rePassword", value = "第二次密码", required = true, dataType = "String"),
    })
    @GetMapping("updateMemberPassword")
    public void updateMemberPassword(String telephone, String rePassword, String newPassword, HttpServletRequest request) {
        this.memberService.updateMemberPassword(telephone, newPassword, rePassword, this.accountHelper.getIP(request));
    }

    @ApiOperation("原密码+修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oldPassword", value = "原密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "rePassword", value = "第二次密码", required = true, dataType = "String"),
    })
    @PostMapping("updateMemberPasswordById")
    public void updateMemberPasswordById(@RequestBody JSONObject jsonObject, HttpServletRequest request) {
        this.memberService.updateMemberPasswordById(this.accountHelper.getUserId(), jsonObject.getString("oldPassword"),
                jsonObject.getString("newPassword"),
                jsonObject.getString("rePassword"), this.accountHelper.getIP(request));
    }

    @ApiOperation("验证原密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "telephone", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "oldPassword", value = "原密码", required = true, dataType = "String"),
    })
    @GetMapping("checkOldPassword")
    public void checkOldPassword(String telephone, String oldPassword) {
        this.memberService.checkOldPassword(telephone, oldPassword);
    }

    @ApiOperation("检验图片验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "telephone", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "code", value = "code", required = true, dataType = "String"),
    })
    @GetMapping("validateCaptchaCode")
    public void validateCaptchaCode(String telephone, String code) {
        this.loginService.validateCaptchaCode(telephone, code);
    }

    @ApiOperation("发送验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "telephone", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "logType", value = "短信类型:注册,登录,找回密码", required = true, dataType = "String")
    })
    @GetMapping("sendSmsCode")
    public String sendSmsCode(String telephone, String logType, HttpServletRequest request) {
        //String code = this.loginService.sendSmsCode(telephone, logType, this.accountHelper.getIP(request));
        //return log.isDebugEnabled() ? code : null;
        return this.loginService.sendSmsCode(telephone, logType, this.accountHelper.getIP(request));
    }

    @ApiOperation("校验验证码是否正确")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "telephone", value = "手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "code", value = "code", required = true, dataType = "String"),
    })
    @GetMapping("validateSmsCode")
    public void validateSmsCode(String telephone, String code) {
        this.loginService.validateSmsCode(telephone, code);
    }

    @ApiOperation("个人信息")
    @ApiImplicitParams({

    })
    @GetMapping("getCustomerVoInfo")
    public CustomerVo getCustomerVoInfo() {
        return this.memberService.getCustomerVoInfo(this.accountHelper.getUserId());
    }

    @ApiOperation("erp信用")
    @ApiImplicitParams({

    })
    @GetMapping("getErpCreditDays")
    public CustomerVo getErpCreditDays(){
        return this.memberService.getErpCreditDays(this.accountHelper.getUserId());
    }

    @ApiOperation("我的运输地址")
    @ApiImplicitParams({

    })
    @GetMapping("getMyBmsTrPosDef")
    public List<BmsTrPosDefVo> getMyBmsTrPosDef() {
        return this.memberService.getMyBmsTrPosDef(this.accountHelper.getUserId());
    }

    @ApiOperation("我的发票信息")
    @ApiImplicitParams({

    })
    @GetMapping("getMyBmsSaInvInfo")
    public List<BmsSaInvInfo> getMyBmsSaInvInfo() {
        return this.memberService.getMyBmsSaInvInfo(this.accountHelper.getUserId());
    }

    /*@ApiOperation("我的经营范围")
    @ApiImplicitParams({

    })
    @GetMapping("getCustomerLicenseAll")
    public List<CustomerLicenseVo> getCustomerLicenseAll() {
        return this.memberService.getCustomerLicenseAll(this.accountHelper.getUserId());
    }

    @ApiOperation("客户证照-证照经营范围")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "id", value = "memberId", required = true, dataType = "Long"),
    })
    @GetMapping("getMemberLicense")
    public IPage<CustomerLicenseVo> getMemberLicense(Page page, CustomerVo customerVo) {
        customerVo.setId(this.accountHelper.getUserId());
        return this.memberService.getMemberLicense(page, customerVo);
    }*/

    @ApiOperation("前台客户证照信息")
    @ApiImplicitParam(name = "id", value = "客户id", required = true, dataType = "Long")
    @GetMapping("getPcMemberLicenceList")
    public List<MemberLicenceVo> getPcMemberLicenceList(Long id) {
        return this.memberService.getMemberLicenceList(id);
    }

    @ApiOperation("前台客户经营范围")
    @GetMapping("getPcMemberBusinessScope")
    public List<CustomBusinessScopeVo> getPcMemberBusinessScope() {
        return this.memberService.getAdminMemberBusinessScope(this.accountHelper.getUserId());
    }

    @ApiOperation("药品经营范围列表")
    @GetMapping("getDrugBusinessScope")
    public List<BusinessScopeVo> getDrugBusinessScope() {
        return this.memberService.getDrugBusinessScope();
    }

    @ApiOperation("证照类型列表")
    @GetMapping("getLicenceType")
    public List<GspLicenseType> getLicenceType() {
        return this.memberService.getLicenceType();
    }

    @ApiOperation("用户注册->基本信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyType", value = "企业类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "companyName", value = "企业名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "areaName", value = "公司所在地->只存名称不需要id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "address", value = "详细地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goodsbusiscopeids", value = "经营范围id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goodsbusiscope", value = "经营范围", required = true, dataType = "String"),
            @ApiImplicitParam(name = "contactName", value = "联系人", required = true, dataType = "String"),
            @ApiImplicitParam(name = "contactPhone", value = "联系人电话", required = true, dataType = "String"),
    })
    @PostMapping("userRegistration")
    public void userRegistration(@RequestBody MemberApplyVo memberApply, HttpServletRequest request) {
        this.memberService.userRegistration(memberApply, null, this.accountHelper.getIP(request));
    }

    @ApiOperation("用户注册->提交资质")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "licenceName", value = "证照名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "licenceImages", value = "证照图片", dataType = "String"),
            @ApiImplicitParam(name = "licenceTypeId", value = "证照类型id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "licenceCode", value = "证照号", dataType = "String"),
            @ApiImplicitParam(name = "signTime", value = "签发日期", dataType = "String"),
            //@ApiImplicitParam(name = "validOnTime", value = "开始时间", required = true, dataType = "String"),
            @ApiImplicitParam(name = "validEndTime", value = "有效期至", dataType = "String"),
    })
    @PostMapping("submitQualifications")
    public void submitQualifications(@RequestBody List<LicenceApplyVo> licenceApplyVoList, HttpServletRequest request) {
        this.memberService.submitQualifications(licenceApplyVoList, null, this.accountHelper.getIP(request));
    }

    @ApiOperation("用户注册->等待审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long"),
    })
    @GetMapping("getAuditResultList")
    public List<LicenceApplyVo> getAuditResultList(Long id) {
        return this.memberService.getAuditResultList(id);
    }

    @ApiOperation("个人资质信息-->不通过-->重新编辑")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "licenceName", value = "证照名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "licenceImages", value = "证照图片", dataType = "String"),
            @ApiImplicitParam(name = "licenceTypeId", value = "证照类型id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "licenceCode", value = "证照号", dataType = "String"),
            @ApiImplicitParam(name = "signTime", value = "签发日期", dataType = "String"),
            @ApiImplicitParam(name = "validOnTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "validEndTime", value = "有效期至", dataType = "String"),
    })
    @PostMapping("editCertificationList")
    public void editCertificationList(@RequestBody List<LicenceApplyVo> licenceApplyVoList, HttpServletRequest request) {
        this.memberService.editCertificationList(licenceApplyVoList, null, this.accountHelper.getIP(request));
    }

    @ApiOperation("个人基本信息-->不通过-->重新编辑")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyType", value = "企业类型", required = true, dataType = "String"),
            @ApiImplicitParam(name = "companyName", value = "企业名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "areaName", value = "公司所在地->只存名称不需要id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "address", value = "详细地址", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goodsbusiscopeids", value = "经营范围id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "goodsbusiscope", value = "经营范围", required = true, dataType = "String"),
            @ApiImplicitParam(name = "contactName", value = "联系人", required = true, dataType = "String"),
            @ApiImplicitParam(name = "contactPhone", value = "联系人电话", required = true, dataType = "String"),
    })
    @PostMapping("editMemberApply")
    public void editMemberApply(@RequestBody MemberApplyVo memberApply, HttpServletRequest request) {
        this.memberService.editMemberApply(memberApply, null, this.accountHelper.getIP(request));
    }


    @ApiOperation("生成二维码code")

    @GetMapping("getScanCode")
    public String getScanCode() {
        return this.loginService.getScanCode();
    }

    @ApiOperation("客户修改手机号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "telephone", value = "telephone", required = true, dataType = "String"),
            @ApiImplicitParam(name = "code", value = "code", required = true, dataType = "String")
    })
    @GetMapping("updateMemberTelephoneCode")
    public void updateMemberTelephoneCode(String telephone, String code, HttpServletRequest request) {
        this.memberService.updateMemberTelephone(this.accountHelper.getUserId(), telephone, code, this.accountHelper.getIP(request));
    }


    @ApiOperation("获取扫码信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "scanCode", value = "scanCode", required = true, dataType = "String")
    })
    @GetMapping("getScanInfoByScanCode")
    public ScanInfoVo getScanInfoByScanCode(String scanCode) {
        return this.loginService.getScanInfoByScanCode(scanCode);
    }


    @ApiOperation("最新一条申请记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "applyId", required = true, dataType = "Long")
    })
    @GetMapping("getNewMemberApplyInfo")
    public MemberApply getNewMemberApplyInfo(Long applyId) {
        return this.memberService.getNewMemberApplyInfo(applyId);
    }


    @ApiOperation("客户积分记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "memberId", value = "客户id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "startTime", value = "开始时间", dataType = "String"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String"),
            @ApiImplicitParam(name = "memberName", value = "客户名称", dataType = "String"),
            @ApiImplicitParam(name = "telephone", value = "手机号", dataType = "String"),
    })
    @GetMapping("getPcScoreRecordList")
    public IPage<ScoreRecordVo> getPcScoreRecordList(Page page, ScoreRecordVo scoreRecordVo) {
        scoreRecordVo.setMemberId(this.accountHelper.getUserId());
        return this.scoreRecordService.getScoreRecordList(page, scoreRecordVo);
    }

    @ApiOperation("证件管理：1.增加证照 2.证件上传 3.重新上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "licenceTypeId", value = "证照类型id", dataType = "Long"),
            @ApiImplicitParam(name = "licenceName", value = "证照名", dataType = "String"),
            @ApiImplicitParam(name = "licenceImages", value = "证照图片", dataType = "String"),
            @ApiImplicitParam(name = "licenceCode", value = "证照号", dataType = "String"),
            @ApiImplicitParam(name = "signTime", value = "签发日期", dataType = "String"),
            @ApiImplicitParam(name = "validOnTime", value = "发证日期", dataType = "String"),
            @ApiImplicitParam(name = "validEndTime", value = "有效期至", dataType = "String"),
            @ApiImplicitParam(name = "certificationType", value = "认证类型", dataType = "String"),
    })
    @PostMapping("editLicence")
    public void editLicence(@RequestBody LicenceApply licenceApply, HttpServletRequest request) {
        this.memberService.editLicence(licenceApply, this.accountHelper.getUserId(), this.accountHelper.getIP(request));
    }

    @ApiOperation("根据手机号查认证信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "telephone", value = "telephone", required = true, dataType = "Long")
    })
    @GetMapping("getTelephoneMemberApplyVo")
    public MemberApplyVo getTelephoneMemberApplyVo(String telephone) {
        return this.memberService.getTelephoneMemberApplyVo(telephone);
    }

    @ApiOperation("商城设置-->内容列表")
    @GetMapping("getPcSetting")
    public List<Setting> getPcSetting(String keys) throws AccessDeniedException {
        return this.settingService.getPcSetting(keys);
    }

    @ApiOperation("看板采购入库未上架")
    @ApiImplicitParams({
    })
    @GetMapping("getTVGoodsOff")
    public IPage<TVDates> getTVGoodsOff(Page page) {
        return this.orderInfoService.getTVGoodsOff(page);
    }


    @ApiOperation("看板当天销售单")
    @ApiImplicitParams({
    })
    @GetMapping("getTVToDaySales")
    public IPage<TVDates> getTVToDaySales(Page page) {
        return this.orderInfoService.getTVToDaySales(page);
    }

    @ApiOperation("看板当天销售订单量")
    @ApiImplicitParams({
    })
    @GetMapping("getTVToDaySalesTotal")
    public TVDates getTVToDaySalesTotal() {
        return this.orderInfoService.getTVToDaySalesTotal();
    }

    @ApiOperation("看板赠品采购入库未上架")
    @ApiImplicitParams({
    })
    @GetMapping("getTVGiftGoodsOff")
    public IPage<TVDates> getTVGiftGoodsOff(Page page) {
        return this.orderInfoService.getTVGiftGoodsOff(page);
    }

    @ApiOperation("拦截订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "interceptStatus", value = "interceptStatus", required = true, dataType = "String"),
    })
    @GetMapping("getAdminOrderInfoInterceptList")
    public IPage<OrderInfoVo> getAdminOrderInfoInterceptList(Page page, OrderInfoVo orderInfoVo) {
        return this.orderInfoService.getAdminErpOrderInfoInterceptList(page, orderInfoVo);

    }

    @ApiOperation("客户证件信息-即将到期-已过期")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "times", value = "1即将过期，2已过期", required = true, dataType = "String"),
    })
    @GetMapping("getMemberLicenceTimeList")
    public List<MemberLicenceVo> getMemberLicenceTimeList(MemberLicenceVo memberLicenceVo) {
        memberLicenceVo.setMemberId(this.accountHelper.getUserId());
        return this.licenceApplyService.getMemberLicenceList(memberLicenceVo);
    }


    @ApiOperation("app版本号")
    @GetMapping("getAPPVersion")
    public Setting getAPPVersion(String version) {
        return this.settingService.getAPPVersion(version);
    }

    @ApiOperation("系统参数详情")
    @GetMapping("getSettingInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keys", value = "LOTTERY_RULE", required = true, dataType = "String"),
    })
    public Setting getSettingInfo(String keys) {
        return this.settingService.genPCMINTime(keys);
    }

    @ApiOperation("系统预计启动时间")
    @GetMapping("genPCMINTime")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keys", value = "MIN_TIME/PC_TIME", required = true, dataType = "String"),
    })
    public Setting genPCMINTime(String keys){
        return this.settingService.genPCMINTime(keys);
    }

    @ApiOperation("帮助指南详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
    })
    @GetMapping("getHelpByIdInfo")
    public Help getHelpByIdInfo(Long id) {
        return this.helpService.getHelpByIdInfo(id);
    }

    @ApiOperation("客户搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "userName", required = true, dataType = "Long"),
    })
    @GetMapping("getCustomerVoByName")
    public List<com.zsyc.zt.inca.vo.CustomerVo> getCustomerVoByName(String userName){
        return this.memberService.getCustomerVoByName(userName);
    }
}
