package com.zsyc.zt.b2b.service;

import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.Licence;
import com.zsyc.zt.b2b.entity.LicenceApply;
import com.zsyc.zt.b2b.entity.MemberApply;
import com.zsyc.zt.b2b.entity.MemberLog;
import com.zsyc.zt.b2b.vo.*;
import com.zsyc.zt.inca.entity.BmsSaInvInfo;
import com.zsyc.zt.inca.entity.GspLicenseType;
import com.zsyc.zt.inca.vo.BusinessScopeVo;
import com.zsyc.zt.inca.vo.CustomBusinessScopeVo;
import com.zsyc.zt.inca.vo.CustomLicenseVo;

import java.util.List;

/**
 * Created by tang on 2020/7/24.
 */
public interface MemberService {
    /**
     * 个人信息
     *
     * @param id
     * @return
     */
    @Cached(name="MemberService-getCustomerVoInfo-", key="#id", expire = 3)
    CustomerVo getCustomerVoInfo(Long id);

    /**
     * erp信用
     * @param erpUserId
     * @return
     */
    @Cached(name="MemberService-getErpCreditDays-", key="#erpUserId", expire = 3)
    CustomerVo getErpCreditDays(Long erpUserId);
    /**
     * 添加客户日志
     *
     * @param memberLog
     */
    void addMemberLog(MemberLogVo memberLog);

    /**
     * 重置密码
     *
     * @param memberVo
     * @param ip
     */
    void rePassword(MemberVo memberVo, String ip);

    /**
     * 验证原密码
     *
     * @param telephone
     * @param oldPassword
     * @return
     */
    void checkOldPassword(String telephone, String oldPassword);

    /**
     * 忘记修改密码
     *
     * @param telephone
     * @param rePassword
     * @param newPassword
     */
    void updateMemberPassword(String telephone, String newPassword, String rePassword, String ip);

    /**
     * 原密码+修改密码
     *
     * @param id
     * @param newPassword
     * @param rePassword
     * @param ip
     */
    void updateMemberPasswordById(Long id, String oldPassword, String newPassword, String rePassword, String ip);

    /**
     * 客户列表
     *
     * @param page
     * @param customerInfoVo
     * @return
     */
    IPage<CustomerVo> getCustomerByCustomerInfoVo(Page page, CustomerInfoVo customerInfoVo);

    /**
     * 客户详情（基本信息）
     *
     * @param memberId
     * @return
     */
    CustomerVo getCustomerListInfoById(Long memberId);

    /**
     * 我的运输地址
     *
     * @param memberId
     * @return
     */
    List<BmsTrPosDefVo> getMyBmsTrPosDef(Long memberId);

    /**
     * 我的发票信息
     *
     * @param memberId
     * @return
     */
    List<BmsSaInvInfo> getMyBmsSaInvInfo(Long memberId);

    /**
     * 我的经营范围
     *
     * @param memberId
     * @return
     */
    List<CustomerLicenseVo> getCustomerLicenseAll(Long memberId);

    /**
     * 客户证照
     *
     * @param page
     * @param customerVo
     * @return
     */
    IPage<CustomerLicenseVo> getMemberLicense(Page page, CustomerVo customerVo);

    /**
     * 客户协议价
     *
     * @param page
     * @param customid
     * @return
     */
    IPage<PubGoodsPriceVo> getMemberPubGoodsPriceList(Page page, Long customid);

    /**
     * 客户集合总单
     *
     * @param page
     * @return
     */
    IPage<BannedVo> getAdminMemberList(Page page, BannedVo bannedVo);

    /**
     * 客户集合细单
     *
     * @param page
     * @param bannedVo
     * @return
     */
    IPage<BannedVo> getAdminMemberListById(Page page, BannedVo bannedVo);

    /**
     * 后台客户证照信息
     *
     * @param id
     * @return
     */
    List<MemberLicenceVo> getAdminMemberLicenceList(Long id);

    /**
     * 后台客户证照信息
     *
     * @param id
     * @return
     */
    List<MemberLicenceVo> getMemberLicenceList(Long id);

    /**
     * 后台客户经营范围
     *
     * @param id
     * @return
     */
    List<CustomBusinessScopeVo> getAdminMemberBusinessScope(Long id);

    /**
     * 用户注册->基本信息
     *
     * @param memberApply
     * @param userId
     * @param ip
     */
    void userRegistration(MemberApplyVo memberApply, Long userId, String ip);

    /**
     * 药品经营范围列表
     *
     * @return
     */
    List<BusinessScopeVo> getDrugBusinessScope();

    /**
     * 用户注册->提交资质
     *
     * @param licenceApplyVoList
     * @param userId
     * @param ip
     */
    void submitQualifications(List<LicenceApplyVo> licenceApplyVoList, Long userId, String ip);

    /**
     * 证照类型列表
     *
     * @return
     */
    List<GspLicenseType> getLicenceType();

    /**
     * 企业认证列表->待审核
     *
     * @param page
     * @param memberApplyVo
     * @return
     */
    IPage<MemberApplyVo> getAuthenticationList(Page page, MemberApplyVo memberApplyVo);

    /**
     * 编辑审核
     *
     * @param memberApplyVo
     * @param userId
     */
    void editors(MemberApplyVo memberApplyVo, Long userId);

    /**
     * 新增用户
     *
     * @param memberVo
     */
    void addMember(MemberVo memberVo);

    /**
     * 个人资质信息-->不通过-->重新编辑
     *
     * @param licenceApplyVoList
     * @param userId
     * @param ip
     */
    void editCertificationList(List<LicenceApplyVo> licenceApplyVoList, Long userId, String ip);

    /**
     * 个人基本信息-->不通过-->重新编辑
     *
     * @param memberApply
     * @param userId
     * @param ip
     */
    void editMemberApply(MemberApplyVo memberApply, Long userId, String ip);

    /**
     * 修改手机号
     *
     * @param id
     * @param telephone
     */
    void updateMemberTelephone(Long id, String telephone);

    /**
     * 最新一条申请记录
     *
     * @param id
     * @return
     */
    MemberApply getNewMemberApplyInfo(Long id);

    /**
     * 客户日志
     * @param page
     * @return
     */
    IPage<MemberLogVo> getMemberLogList(Page page,MemberLogVo memberLogVo);

    /**
     * 用户注册->等待审核
     * @param userId
     * @return
     */
    List<LicenceApplyVo> getAuditResultList(Long userId);

    /**
     * 证件管理：1.增加证照 2.证件上传 3.重新上传
     * @param licenceApply
     * @param userId
     * @param ip
     */
    void editLicence(LicenceApply licenceApply, Long userId, String ip);

    /**
     * 根据手机号查认证信息
     * @param telephone
     * @return
     */
    MemberApplyVo getTelephoneMemberApplyVo(String telephone);

    /**
     * 所有证件照信息-申请的
     * @param page
     * @param licenceApplyVo
     * @return
     */
    IPage<LicenceApplyVo> getLicenceApplyVoList(Page page,LicenceApplyVo licenceApplyVo);

    /**
     * 所有证件照信息-审核通过的
     * @param page
     * @param licenceVo
     * @return
     */
    IPage<LicenceVo> getLicenceVoList(Page page,LicenceVo licenceVo);

    /**
     * 审核证照认证
     * @param id
     * @param status
     */
    void checkLicenceApplyVoStatus(Long id,String status,String remark);

    /**
     * 编辑证照认证
     * @param licenceApply
     */
    void updateLicenceApply(Licence licenceApply);

    /**
     * 客户修改手机号
     * @param id
     * @param telephone
     * @param code
     */
    void updateMemberTelephone(Long id,String telephone, String code,String ip);

    /**
     * 客户搜索
     * @param userName
     * @return
     */
    List<com.zsyc.zt.inca.vo.CustomerVo> getCustomerVoByName(String userName);

    /**
     * API对接客户查询
     * @param page
     * @param apiMemberVo
     * @return
     */
    IPage<ApiMemberVo> selectApiMemberVoPage(Page<ApiMemberVo> page, ApiMemberVo apiMemberVo);
}
