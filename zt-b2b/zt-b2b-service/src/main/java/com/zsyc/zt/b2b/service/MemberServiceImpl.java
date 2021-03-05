package com.zsyc.zt.b2b.service;

import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.mapper.*;
import com.zsyc.zt.b2b.vo.*;
import com.zsyc.zt.fs.service.B2BFileService;
import com.zsyc.zt.inca.entity.BmsSaInvInfo;
import com.zsyc.zt.inca.entity.GspLicenseType;
import com.zsyc.zt.inca.service.CustomService;
import com.zsyc.zt.inca.vo.BusinessScopeVo;
import com.zsyc.zt.inca.vo.CustomBusinessScopeVo;
import com.zsyc.zt.inca.vo.CustomLicenseVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tang on 2020/7/24.
 */
@Service
@Transactional
@Slf4j
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberLogMapper memberLogMapper;
    @Autowired
    private LicenceApplyMapper licenceApplyMapper;
    @Autowired
    private MemberApplyMapper memberApplyMapper;
    @Autowired
    private LicenceMapper licenceMapper;
    @Autowired
    private B2BFileService fileService;
    @Reference
    private OauthService oauthService;
    @Reference(version = Constant.DUBBO_VERSION.INCA)
    private CustomService customService;
    @Autowired
    private FavoritesMapper favoritesMapper;
    @Reference
    private LoginService loginService;
    @Autowired
    private LotterDailQualificationsMapper lotterDailQualificationsMapper;

    @Override
    public CustomerVo getCustomerVoInfo(Long id) {
        AssertExt.hasId(id, "id为空");
        Member memberDB = this.memberMapper.selectById(id);

        if (null == memberDB) {
            return this.memberMapper.getCustomerVoInfo(id);
        }
        if (memberDB.getErpUserId() == 0) {
            return this.memberMapper.getMemberInfo(id);
        }

        CustomerVo customerVo = this.memberMapper.getCustomerVoInfo(memberDB.getErpUserId());

        if (null != memberDB) {
            List<BmsSaInvInfo> bmsSaInvInfoList = this.memberMapper.getMyBmsSaInvInfo(memberDB.getErpUserId());

            if (bmsSaInvInfoList.size() > 0) {
                customerVo.setBmsSaInvInfoList(bmsSaInvInfoList);
            }
            List<BmsTrPosDefVo> bmsTrPosDefList = this.memberMapper.getMyBmsTrPosDef(memberDB.getErpUserId());
            if (bmsTrPosDefList.size() > 0) {
                customerVo.setBmsTrPosDefList(bmsTrPosDefList);
            }

        }
        Integer favoritesCount = this.favoritesMapper.selectCount(new QueryWrapper<Favorites>().eq("MEMBER_ID", memberDB.getId()));
        if (favoritesCount > 0) {
            customerVo.setMyFavoritesCount(favoritesCount);
        }
        Integer lotterCount=this.lotterDailQualificationsMapper.getMyLotterCount(memberDB.getId(),null);
        customerVo.setLotterCount(lotterCount);
        return customerVo;
    }

    @Override
    public CustomerVo getErpCreditDays(Long erpUserId) {
        Member memberDB = this.memberMapper.selectById(erpUserId);
        CustomerVo customerVo=new CustomerVo();

        customerVo.setCreditDays(this.customService.getCreditDays(memberDB.getErpUserId(),1));
        customerVo.setNotRecAmount(this.customService.getNotRecAmount(memberDB.getErpUserId(),1));
        customerVo.setRemainRecDays(this.customService.getRemainRecDays(memberDB.getErpUserId(),1));
        return customerVo;
    }


    @Override
    public void addMemberLog(MemberLogVo memberLog) {
        memberLog.setCreateTime(LocalDateTime.now());
        this.memberLogMapper.insert(memberLog);
    }


    @Override
    public void checkOldPassword(String telephone, String oldPassword) {
        AssertExt.notNull(oldPassword, "原密码为空");
        AssertExt.notNull(telephone, "手机号为空");
        Member memberDB = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("telephone", telephone));
        AssertExt.isTrue(memberDB != null, "账户不存在，去注册吧");
        if (null != memberDB && !memberDB.getTelephone().equals(memberDB.getPassword())) {
            boolean check = DigestUtils.sha1Hex(oldPassword + memberDB.getSalt()).equals(memberDB.getPassword());
            AssertExt.isTrue(check, "原密码错误");

        }
    }

    @Override
    public void updateMemberPassword(String telephone, String newPassword, String rePassword, String ip) {
        AssertExt.notNull(telephone, "手机号为空");
        AssertExt.matches(".{6,16}", newPassword, "密码长度6到16位");
        AssertExt.isTrue(newPassword.equals(rePassword), "两次密码不一致");
        Member memberDB = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("telephone", telephone));
        AssertExt.isTrue(memberDB != null, "账户不存在，去注册吧");
        memberDB.setSalt(RandomStringUtils.random(10, true, true));
        memberDB.setPassword(DigestUtils.sha1Hex(newPassword + memberDB.getSalt()));
        memberDB.setAuthModifyPwdTime(LocalDateTime.now());
        this.memberMapper.updateById(memberDB);
        MemberLogVo memberLog = new MemberLogVo();
        memberLog.setContent("忘记-修改密码");
        memberLog.setMemberId(memberDB.getId());
        memberLog.setIp(ip);
        this.addMemberLog(memberLog);
    }

    @Override
    public void updateMemberPasswordById(Long id, String oldPassword, String newPassword, String rePassword, String ip) {
        AssertExt.matches(".{6,16}", newPassword, "密码长度6到16位");
        AssertExt.isTrue(newPassword.equals(rePassword), "两次密码不一致");
        Member memberDB = this.memberMapper.selectById(id);

        this.checkOldPassword(memberDB.getTelephone(), oldPassword);

        memberDB.setSalt(RandomStringUtils.random(10, true, true));
        memberDB.setPassword(DigestUtils.sha1Hex(newPassword + memberDB.getSalt()));
        memberDB.setAuthModifyPwdTime(LocalDateTime.now());
        this.memberMapper.updateById(memberDB);
        MemberLogVo memberLog = new MemberLogVo();
        memberLog.setContent("原密码+修改密码");
        memberLog.setMemberId(memberDB.getId());
        memberLog.setIp(ip);
        this.addMemberLog(memberLog);
    }

    @Override
    public IPage<CustomerVo> getCustomerByCustomerInfoVo(Page page, CustomerInfoVo customerInfoVo) {
        AssertExt.notNull(page, "页码不为空");
        IPage<CustomerVo> customerVoList = this.memberMapper.getCustomerByCustomerInfoVo(page, customerInfoVo);
        log.info("page size {}", customerVoList.getSize());
        return customerVoList;
    }

    @Override
    public CustomerVo getCustomerListInfoById(Long memberId) {
        AssertExt.hasId(memberId, "无效客户ID");
        //TODO select语句要完善
        CustomerVo customerVo = this.memberMapper.getCustomerListInfoById(memberId);
        log.info("customer info {}", customerVo);
        return customerVo;
    }

    @Override
    public List<BmsTrPosDefVo> getMyBmsTrPosDef(Long memberId) {
        Member memberDB = this.memberMapper.selectById(memberId);
        return this.memberMapper.getMyBmsTrPosDef(memberDB.getErpUserId());
    }

    @Override
    public List<BmsSaInvInfo> getMyBmsSaInvInfo(Long memberId) {
        Member memberDB = this.memberMapper.selectById(memberId);
        return this.memberMapper.getMyBmsSaInvInfo(memberDB.getErpUserId());
    }

    @Override
    public List<CustomerLicenseVo> getCustomerLicenseAll(Long memberId) {
        Member memberDB = this.memberMapper.selectById(memberId);
        return this.memberMapper.getMemberLicenseAll(memberDB.getErpUserId());
    }

    @Override
    public IPage<CustomerLicenseVo> getMemberLicense(Page page, CustomerVo customerVo) {
        AssertExt.hasId(customerVo.getId(), "客户ID为空");
        Member memberDB = this.memberMapper.selectById(customerVo.getId());
        if (null != memberDB) {
            customerVo.setCustomid(memberDB.getErpUserId());
        } else {
            customerVo.setCustomid(customerVo.getId());
        }

        return this.memberMapper.getMemberLicense(page, customerVo);
    }

    @Override
    public IPage<PubGoodsPriceVo> getMemberPubGoodsPriceList(Page page, Long customid) {
        AssertExt.hasId(customid, "id为空");
        return this.memberMapper.getMemberPubGoodsPriceList(page, customid);
    }

    @Override
    public void rePassword(MemberVo memberVo, String ip) {
        AssertExt.hasId(memberVo.getMemberId(), "无效客户ID");
        AssertExt.notBlank(memberVo.getRePassword(), "重置密码不能为空");
        AssertExt.matches(".{6,20}", memberVo.getRePassword(), "密码长度8到20位");
        Member member = this.memberMapper.selectById(memberVo.getMemberId());
        AssertExt.notNull(member, "数据暂未同步,请先去pcWeb登录");
        member.setSalt(RandomStringUtils.random(10, true, true));
        member.setPassword(DigestUtils.sha1Hex(memberVo.getRePassword() + member.getSalt()));
        member.setAuthModifyPwdTime(LocalDateTime.now());
        AssertExt.isFalse(memberVo.getRePassword().equals(member.getPassword()), "新旧密码不能一样");
        this.memberMapper.updateById(member);
        MemberLogVo memberLogVo = new MemberLogVo();
        memberLogVo.setContent("重置密码");
        memberLogVo.setMemberId(member.getId());
        memberLogVo.setIp(ip);
        this.addMemberLog(memberLogVo);
    }

    @Override
    public IPage<BannedVo> getAdminMemberList(Page page, BannedVo bannedVo) {
        return this.memberMapper.getAdminMemberList(page, bannedVo);
    }

    @Override
    public IPage<BannedVo> getAdminMemberListById(Page page, BannedVo bannedVo) {
        return this.memberMapper.getAdminMemberListById(page, bannedVo);
    }

    @Override
    public List<MemberLicenceVo> getAdminMemberLicenceList(Long id) {
        AssertExt.hasId(id, "客户ID无效");
        Member member = this.memberMapper.selectById(id);
        if (null != member) {
            return this.memberMapper.getMemberLicenceList(member.getErpUserId());
        }

        return this.memberMapper.getMemberLicenceList(id);
    }

    @Override
    public List<MemberLicenceVo> getMemberLicenceList(Long id) {
        AssertExt.hasId(id, "客户ID无效");
        Member member = this.memberMapper.selectById(id);
        List<MemberLicenceVo> memberLicenceVoList = this.memberMapper.getPCMemberLicenceList(member.getErpUserId(), id);

        for (MemberLicenceVo memberLicenceVo : memberLicenceVoList) {
            if (null != memberLicenceVo.getLicenseId()) {
                memberLicenceVo.setCustomBusinessScopeVoList(this.customService.selectBusinessScopeByCustomIdAndLicenseId(member.getErpUserId(), memberLicenceVo.getLicenseId()));
            }
        }

        return memberLicenceVoList;
    }

    @Override
    public List<CustomBusinessScopeVo> getAdminMemberBusinessScope(Long id) {
        AssertExt.hasId(id, "客户ID无效");
        Member member = this.memberMapper.selectById(id);

        if (null != member) {
            return this.customService.selectBusinessScopeByCustomId(member.getErpUserId());
        }
        return this.customService.selectBusinessScopeByCustomId(id);
    }

    @Override
    public void userRegistration(MemberApplyVo memberApply, Long userId, String ip) {
        AssertExt.notBlank(memberApply.getTelephone(), "手机号为空");
        AssertExt.notBlank(memberApply.getCompanyType(), "请输入企业类型");
        AssertExt.checkEnum(MemberApply.ECompanyType.class, memberApply.getCompanyType(), "没有对应类型，请重新选择");
        AssertExt.notBlank(memberApply.getCompanyName(), "请输入企业名称");
        AssertExt.notBlank(memberApply.getAreaName(), "请输入公司所在地");
        AssertExt.notBlank(memberApply.getAddress(), "请输入详细地址");
        AssertExt.notBlank(memberApply.getGoodsbusiscopeids(), "请选择经营范围id");
        AssertExt.notBlank(memberApply.getGoodsbusiscope(), "请选择经营范围");
        AssertExt.notBlank(memberApply.getContactName(), "请输入联系人");
        AssertExt.notBlank(memberApply.getContactPhone(), "请输入联系人电话");
        memberApply.setStatus(MemberApply.EStatus.APPLYING.val());// 初次操作状态为申请中-->待进入管理员审核时（资料填充完毕时转为审核中）
        memberApply.setCreateTime(LocalDateTime.now());
        this.memberApplyMapper.insert(memberApply);

        Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("TELEPHONE", memberApply.getTelephone()));
        member.setApply(memberApply.getId());
        this.memberMapper.updateById(member);

        MemberLogVo memberLog = new MemberLogVo();
        memberLog.setIp(ip);
        memberLog.setMemberId(member.getId());
        memberLog.setType("注册");
        memberLog.setContent("基本信息");
        memberLog.setCreateTime(LocalDateTime.now());
        this.memberLogMapper.insert(memberLog);

    }

    @Override
    public List<BusinessScopeVo> getDrugBusinessScope() {
        return this.customService.selectBusinessScope(1);
    }

    @Override
    public List<GspLicenseType> getLicenceType() {
        return this.customService.selectLicenseType(1);
    }

    @Override
    public void submitQualifications(List<LicenceApplyVo> licenceApplyVoList, Long userId, String ip) {
        AssertExt.notNull(licenceApplyVoList, "提交资质信息为空");
        Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("TELEPHONE", licenceApplyVoList.get(0).getTelephone()));
        this.licenceApplyMapper.delLicenceApplyByMemberId(member.getId());
        licenceApplyVoList.forEach(licenceApplyVo -> {
            if (null != licenceApplyVo.getLicenceImages()) {
                //AssertExt.notNull(licenceApplyVo.getLicenceName(), "请输入证照名");
                AssertExt.notNull(licenceApplyVo.getStatus(), "状态为空");
                //AssertExt.notNull(licenceApplyVo.getLicenceImages(), "请上传证照图片");
                //AssertExt.notNull(licenceApplyVo.getLicenceTypeId(), "请输入证照类型id");
                //AssertExt.notNull(licenceApplyVo.getLicenceCode(), "请输入证照号");
                //AssertExt.notNull(licenceApplyVo.getSignDate(), "请输入签发日期");
                //AssertExt.notNull(licenceApplyVo.getValidOnDate(), "请输入发证日期");
                //AssertExt.notNull(licenceApplyVo.getValidEndDate(), "请输入有效期至");
                licenceApplyVo.setMemberId(member.getId());
                licenceApplyVo.setErpCustomerId(member.getErpUserId());
                licenceApplyVo.setCreateTime(LocalDateTime.now());
                //licenceApplyVo.setStatus(LicenceApply.EStatus.IN_REVIEW.val());// 点击下一步状态为审核中-->过了管理员审核则为最终状态：通过/驳回
                if (null == licenceApplyVo.getId()) this.licenceApplyMapper.insert(licenceApplyVo);

                String[] licenceImages = licenceApplyVo.getLicenceImages().split(",");
                String licencePic = this.fileService.coverImageById(licenceApplyVo.getId(), Constant.IMAGE_PREFIX.LICENCE_APPLY, licenceImages);
                licenceApplyVo.setLicenceImages(licencePic);

                this.licenceApplyMapper.updateById(licenceApplyVo);

                MemberApply memberApply = this.memberApplyMapper.selectById(member.getApply());
                AssertExt.notNull(memberApply, "[%s]id无效", member.getApply());
                memberApply.setStatus(licenceApplyVo.getStatus());
                this.memberApplyMapper.updateById(memberApply);
            } else {
                licenceApplyVo.setLicenceImages("");
                //AssertExt.notNull(licenceApplyVo.getLicenceName(), "请输入证照名");
                //AssertExt.notNull(licenceApplyVo.getLicenceTypeId(), "请输入证照类型id");
                AssertExt.notNull(licenceApplyVo.getStatus(), "状态为空");
                licenceApplyVo.setMemberId(member.getId());
                licenceApplyVo.setErpCustomerId(member.getErpUserId());
                licenceApplyVo.setCreateTime(LocalDateTime.now());
                //licenceApplyVo.setStatus(LicenceApply.EStatus.IN_REVIEW.val());// 点击下一步状态为审核中-->过了管理员审核则为最终状态：通过/驳回
                if (null != licenceApplyVo.getId()) this.licenceApplyMapper.updateById(licenceApplyVo);
                else this.licenceApplyMapper.insert(licenceApplyVo);

                MemberApply memberApply = this.memberApplyMapper.selectById(member.getApply());
                AssertExt.notNull(memberApply, "[%s]id无效", member.getApply());
                memberApply.setStatus(licenceApplyVo.getStatus());
                this.memberApplyMapper.updateById(memberApply);
            }

            MemberLogVo memberLog = new MemberLogVo();
            memberLog.setIp(ip);
            memberLog.setMemberId(member.getId());
            memberLog.setType("注册");
            memberLog.setContent("认证申请");
            memberLog.setCreateTime(LocalDateTime.now());
            this.memberLogMapper.insert(memberLog);
        });
    }

    @Override
    public IPage<MemberApplyVo> getAuthenticationList(Page page, MemberApplyVo memberApplyVo) {
        IPage<MemberApplyVo> memberApplyVoIPage = this.memberApplyMapper.getAuthenticationList(page, memberApplyVo);
        memberApplyVoIPage.getRecords().forEach(memberApplyVo1 -> {
            Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("APPLY", memberApplyVo1.getId()));
            if (null != member) {
                memberApplyVo1.setMemberId(member.getId());
                memberApplyVo1.setTelephone(member.getTelephone());
            }
        });
        return memberApplyVoIPage;
    }

    @Override
    public void editors(MemberApplyVo memberApplyVo, Long userId) {

        /**
         * 编辑审核：
         * 1、根据用户申请id获得对应的用户申请信息
         * 2、修改用户申请状态/审核结果/备注
         * 3、根据用户申请id获得对应的客户信息->客户通过apply关联用户申请id
         * 4、根据用户id获得证照申请信息列表->证照申请通过memberId关联客户id
         * 5、修改证照申请状态/审核结果/备注
         * 6、将证照申请信息+审核的状态新增到证照表，记录相关信息
         */
        AssertExt.hasId(memberApplyVo.getMemberApply().getId(), "客户申请id无效");
        AssertExt.notNull(memberApplyVo.getErpCustomId(), "erp客户id为空");
        // 获取erp 所有已设置 客户id
        List<Long> erpUserIdList = this.memberMapper.getCustomIdList();
        for (Long id : erpUserIdList) {
            AssertExt.isFalse(memberApplyVo.getErpCustomId().equals(id), "erp客户id已经存在，不可重复！！请重新输入");
        }
        MemberApply memberApply = this.memberApplyMapper.selectById(memberApplyVo.getMemberApply().getId());
        AssertExt.notNull(memberApply, "客户申请信息不存在");
        //AssertExt.isTrue(memberApply.getStatus().equals(MemberApply.EStatus.IN_REVIEW.val()), "该用户注册申请信息不符合审核条件，请确认清楚再操作！！");
        AssertExt.notNull(memberApplyVo.getMemberApply().getStatus(), "客户申请审核状态为空");
        AssertExt.notNull(memberApplyVo.getMemberApply().getReason(), "客户申请审核结果为空");
        AssertExt.checkEnum(MemberApply.EStatus.class, memberApplyVo.getMemberApply().getStatus(), "客户审核状态不匹配");
        AssertExt.notNull(memberApplyVo.getMemberApply().getGoodsbusiscopeids(), "客户申请经营范围id为空");
        AssertExt.notNull(memberApplyVo.getMemberApply().getGoodsbusiscope(), "客户申请经营范围为空");
        BeanUtils.copyProperties(memberApplyVo.getMemberApply(), memberApply);
        this.memberApplyMapper.updateById(memberApply);

        Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("APPLY", memberApply.getId()));
        AssertExt.notNull(member, "用户不存在");
        List<LicenceApply> licenceApplyList = this.licenceApplyMapper.selectList(new QueryWrapper<LicenceApply>().eq("MEMBER_ID", member.getId()));
        if (null != licenceApplyList) {
            AssertExt.notNull(memberApplyVo.getLicenceApplyList(), "证照申请信息为空");
            for (LicenceApply licenceApply : licenceApplyList) {
                //AssertExt.isTrue(licenceApply.getStatus().equals(LicenceApply.EStatus.IN_REVIEW.val()), "该企业认证申请不符合审核条件，请确认清楚再操作！！");
                for (LicenceApply licenceApplyDB : memberApplyVo.getLicenceApplyList()) {
                    if (licenceApplyDB.getId().equals(licenceApply.getId())) {
                        AssertExt.notNull(licenceApplyDB.getStatus(), "证照审核状态为空");
                        AssertExt.checkEnum(LicenceApply.EStatus.class, licenceApplyDB.getStatus(), "证照审核状态不匹配");
                        licenceApply.setErpCustomerId(memberApplyVo.getErpCustomId());
                        licenceApply.setAuditUserId(member.getId());
                        licenceApply.setAuditTime(LocalDateTime.now());
                        BeanUtils.copyProperties(licenceApplyDB, licenceApply);
                        this.licenceApplyMapper.updateById(licenceApply);

                        Licence licence = new Licence();
                        licence.setErpCustomerId(memberApplyVo.getErpCustomId());
                        //licence.setErpLicenceId(memberApplyVo.getErpLicenceId());
                        licence.setLicenceImages(licenceApply.getLicenceImages());
                        licence.setLicenceName(licenceApply.getLicenceName());
                        licence.setLicenceTypeId(licenceApply.getLicenceTypeId());
                        licence.setErpCustomerId(licenceApply.getErpCustomerId());
                        licence.setLicenceCode(licenceApply.getLicenceCode());
                        licence.setSignTime(licenceApply.getSignTime());
                        /**
                         * 设置证照状态：
                         * 1.有效期大于当前时间1个月内      为即将过期
                         * 2.有效期大于当前时间1个月后      为正常
                         * 3.有效期小于当前时间             为过期
                         */
                       /* if (null != licenceApply.getValidEndTime()) {
                            if (licenceApply.getValidEndTime().isAfter(LocalDateTime.now().plusMonths(1L))) {
                                licence.setStatus(Licence.EStatus.NORMAL.val());
                            } else if (licenceApply.getValidEndTime().isBefore(LocalDateTime.now().plusMonths(1L))) {
                                licence.setStatus(Licence.EStatus.BE_ABOUT_TO_EXPIRES.val());
                            }
                            licence.setStatus(Licence.EStatus.EXPIRES.val());
                        }*/
                        licence.setStatus(Licence.EStatus.NORMAL.val());
                        licence.setValidOnTime(licenceApply.getValidOnTime());
                        licence.setValidEndTime(licenceApply.getValidEndTime());
                        licence.setApplyId(licenceApply.getId());
                        licence.setLicenceId(licenceApply.getLicenceId());
                        licence.setCreateTime(LocalDateTime.now());
                        this.licenceMapper.insert(licence);
                    }
                }
            }
        }
        member.setErpUserId(memberApplyVo.getErpCustomId());
        this.memberMapper.updateById(member);
    }

    @Override
    public void addMember(MemberVo memberVo) {
        AssertExt.hasId(memberVo.getErpUserId(), "erp用户id无效");
        AssertExt.notNull(memberVo.getTelephone(), "手机号为空");
        AssertExt.notNull(memberVo.getPassword(), "登录密码为空");
        AssertExt.notNull(memberVo.getRePassword(), "二次登录密码为空");
        // 1.先查ERP_CUSTOMER_V -->根据erpUserId
        CustomerVo customerVo = this.memberMapper.getCustomerVoInfo(memberVo.getErpUserId());
        log.info("customerVo {}", customerVo);
        // 2.然后查询MEMBER -->根据erpUserId/telephone
        Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("ERP_USER_ID", memberVo.getErpUserId())
                .or().eq("TELEPHONE", memberVo.getTelephone()));
        log.info("member {}", member);
        AssertExt.isFalse(member != null, "用户已存在");
        Member memberDB = new Member();
        memberDB.setErpUserId(customerVo.getCustomid());
        memberDB.setTelephone(memberVo.getTelephone());
        memberDB.setPassword(memberVo.getTelephone());
        AssertExt.isTrue(memberVo.getPassword().equals(memberVo.getRePassword()), "两次密码不一致");
        memberDB.setPassword(memberVo.getPassword());
        memberDB.setCreateTime(LocalDateTime.now());
        memberDB.setUserName(customerVo.getCustomname());
        memberDB.setIsErp(Constant.NEW_MEMBER.FORMER);
        this.memberMapper.insert(memberDB);
    }

    @Override
    public void editCertificationList(List<LicenceApplyVo> licenceApplyVoList, Long userId, String ip) {
        AssertExt.notNull(licenceApplyVoList, "认证信息为空");
        Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("TELEPHONE", licenceApplyVoList.get(0).getTelephone()));
        licenceApplyVoList.forEach(licenceApplyVo -> {
            AssertExt.notNull(licenceApplyVo.getStatus(), "状态为空");
            if (null != licenceApplyVo.getLicenceImages()) {
                licenceApplyVo.setStatus(LicenceApply.EStatus.IN_REVIEW.val());
                if (null == licenceApplyVo.getId())
                    this.licenceApplyMapper.insert(licenceApplyVo);

                //LicenceApply licenceApplyDB = this.licenceApplyMapper.selectOne(new QueryWrapper<LicenceApply>().eq("id",licenceApplyVo.getId()).eq("MEMBER_ID", userId).eq("????", licenceApplyVo.getLicenceTypeId()));
                //AssertExt.notNull(licenceApplyVo.getLicenceName(), "证照名为空");
                //AssertExt.notNull(licenceApplyVo.getLicenceImages(), "证照图片为空");

                String[] licenceImages = licenceApplyVo.getLicenceImages().split(",");
                String licencePic = this.fileService.coverImageById(licenceApplyVo.getId(), Constant.IMAGE_PREFIX.LICENCE_APPLY, licenceImages);
                licenceApplyVo.setLicenceImages(licencePic);

                //AssertExt.notNull(licenceApplyVo.getLicenceTypeId(), "证照类型id为空");
                //AssertExt.notNull(licenceApplyVo.getLicenceCode(), "证照号为空");
                //AssertExt.notNull(licenceApplyVo.getSignDate(), "签发日期为空");
                //AssertExt.notNull(licenceApplyVo.getValidOnDate(), "发证日期为空");
                //AssertExt.notNull(licenceApplyVo.getValidEndDate(), "有效期至为空");
                //licenceApplyVo.setStatus(LicenceApply.EStatus.IN_REVIEW.val());
                //BeanUtils.copyProperties(licenceApplyVo, licenceApplyDB);
                this.licenceApplyMapper.updateById(licenceApplyVo);
            } else {
                licenceApplyVo.setLicenceImages("");
                LicenceApply licenceApplyDB = this.licenceApplyMapper.selectOne(new QueryWrapper<LicenceApply>().eq("MEMBER_ID", member.getId()));
                //AssertExt.notNull(licenceApplyVo.getLicenceName(), "证照名为空");
                //AssertExt.notNull(licenceApplyVo.getLicenceTypeId(), "证照类型id为空");
                BeanUtils.copyProperties(licenceApplyVo, licenceApplyDB);
                licenceApplyDB.setStatus(LicenceApply.EStatus.IN_REVIEW.val());
                this.licenceApplyMapper.updateById(licenceApplyDB);

            }

            MemberApply memberApply = this.memberApplyMapper.selectById(member.getApply());
            AssertExt.notNull(memberApply, "[%s]id无效", member.getApply());
            memberApply.setStatus(LicenceApply.EStatus.IN_REVIEW.val());
            this.memberApplyMapper.updateById(memberApply);

            MemberLogVo memberLog = new MemberLogVo();
            memberLog.setIp(ip);
            memberLog.setCreateTime(LocalDateTime.now());
            memberLog.setMemberId(member.getId());
            memberLog.setType("注册");
            memberLog.setContent("认证申请重新编辑");
            this.memberLogMapper.insert(memberLog);
        });
    }

    @Override
    public void editMemberApply(MemberApplyVo memberApply, Long userId, String ip) {
        AssertExt.notBlank(memberApply.getCompanyType(), "企业类型为空");
        AssertExt.checkEnum(MemberApply.ECompanyType.class, memberApply.getCompanyType(), "类型不存在，请重新选择");
        AssertExt.notBlank(memberApply.getCompanyName(), "企业名称为空");
        AssertExt.notBlank(memberApply.getAreaName(), "公司所在地为空");
        AssertExt.notBlank(memberApply.getAddress(), "详细地址为空");
        AssertExt.notBlank(memberApply.getGoodsbusiscopeids(), "经营范围id为空");
        AssertExt.notBlank(memberApply.getGoodsbusiscope(), "经营范围为空");
        AssertExt.notBlank(memberApply.getContactName(), "联系人为空");
        AssertExt.notBlank(memberApply.getContactPhone(), "联系人电话为空");
        Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("TELEPHONE", memberApply.getTelephone()));
        AssertExt.notNull(member, "用户不存在，请先注册");
        MemberApply memberApplyDB = this.memberApplyMapper.selectById(member.getApply());
        AssertExt.notNull(memberApplyDB, "用户申请信息不存在");
        BeanUtils.copyProperties(memberApply, memberApplyDB);
        this.memberApplyMapper.updateById(memberApplyDB);

        MemberLogVo memberLog = new MemberLogVo();
        memberLog.setType("注册");
        memberLog.setContent("基本信息重新编辑");
        memberLog.setMemberId(member.getId());
        memberLog.setIp(ip);
        memberLog.setCreateTime(LocalDateTime.now());
        this.memberLogMapper.insert(memberLog);
    }

    @Override
    public void updateMemberTelephone(Long customerid, String telephone) {
        AssertExt.hasId(customerid, "customerid为空");
        AssertExt.notNull(telephone, "手机号为空");

        Member memberDB = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("ERP_USER_ID", customerid));
        if (null != memberDB) {
            Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().ne("ERP_USER_ID", customerid).eq("telephone", telephone));
            AssertExt.isFalse(member != null, "手机号已存在");
            memberDB.setTelephone(telephone);
            this.memberMapper.updateById(memberDB);

        } else {
            memberDB = new Member();
            memberDB.setCreateTime(LocalDateTime.now());
            memberDB.setTelephone(telephone);
            memberDB.setErpUserId(customerid);
            memberDB.setIsErp(1);
            memberDB.setPassword(telephone);
            memberDB.setMemberLoginNum(0);
            memberDB.setIsErp(1);
            this.memberMapper.insert(memberDB);
        }

    }

    @Override
    public IPage<MemberLogVo> getMemberLogList(Page page,MemberLogVo memberLogVo) {
        AssertExt.notNull(page, "无效页面");
        return this.memberLogMapper.getMemberLogList(page,memberLogVo);
    }

    @Override
    public MemberApply getNewMemberApplyInfo(Long id) {
        AssertExt.hasId(id, "id为空");
        return this.memberApplyMapper.selectById(id);
    }

    @Override
    public List<LicenceApplyVo> getAuditResultList(Long userId) {
        AssertExt.hasId(userId, "id无效");
        return this.licenceApplyMapper.getAuditResultList(userId);
    }

    @Override
    public void editLicence(LicenceApply licenceApply, Long userId, String ip) {
        AssertExt.notNull(licenceApply.getLicenceTypeId(), "证照类型id为空");
       // AssertExt.notNull(licenceApply.getLicenceId(), "证照id为空");
        MemberLogVo memberLog = new MemberLogVo();
        LicenceApply licenceApplyDB = this.licenceApplyMapper.selectOne(new QueryWrapper<LicenceApply>().eq("MEMBER_ID", userId).eq("LICENCE_TYPE_ID", licenceApply.getLicenceTypeId()));
        AssertExt.notNull(licenceApply.getLicenceName(), "证照名称为空");
        if (null != licenceApplyDB) {
            licenceApply.setId(licenceApplyDB.getId());
            if (null != licenceApply.getLicenceImages()) {
                String[] licenceImages = licenceApply.getLicenceImages().split(",");
                String licencePic = this.fileService.coverImageById(licenceApplyDB.getId(), Constant.IMAGE_PREFIX.LICENCE_APPLY, licenceImages);
                BeanUtils.copyProperties(licenceApply, licenceApplyDB);
                licenceApplyDB.setLicenceImages(licencePic);
                licenceApplyDB.setStatus(LicenceApply.EStatus.IN_REVIEW.val());
                this.licenceApplyMapper.updateById(licenceApplyDB);
            } else {
                licenceApplyDB.setLicenceImages("");
                licenceApplyDB.setStatus(LicenceApply.EStatus.IN_REVIEW.val());
                BeanUtils.copyProperties(licenceApply, licenceApplyDB);
                this.licenceApplyMapper.updateById(licenceApplyDB);
            }
            memberLog.setIp(ip);
            memberLog.setMemberId(userId);
            memberLog.setType("证件管理");
            memberLog.setContent("证件上传");
            memberLog.setCreateTime(LocalDateTime.now());
            this.memberLogMapper.insert(memberLog);
        } else {
            Member member = this.memberMapper.selectById(userId);
            LicenceApply licenceApply1 = new LicenceApply();
            if (null != member) {
                if (null != licenceApply.getLicenceImages()) {

                    licenceApply1.setStatus(LicenceApply.EStatus.IN_REVIEW.val());
                    licenceApply1.setMemberId(userId);
                    licenceApply1.setErpCustomerId(member.getErpUserId());
                    licenceApply1.setSignTime(licenceApply.getSignTime());
                    licenceApply1.setValidOnTime(licenceApply.getValidOnTime());
                    licenceApply1.setValidEndTime(licenceApply.getValidEndTime());
                    licenceApply1.setCreateTime(LocalDateTime.now());
                    licenceApply1.setLicenceCode(licenceApply.getLicenceCode());
                    licenceApply1.setLicenceName(licenceApply.getLicenceName());
                    licenceApply1.setLicenceTypeId(licenceApply.getLicenceTypeId());
                    this.licenceApplyMapper.insert(licenceApply1);

                    String[] licenceImages = licenceApply.getLicenceImages().split(",");
                    String licencePic = this.fileService.coverImageById(licenceApply1.getId(), Constant.IMAGE_PREFIX.LICENCE_APPLY, licenceImages);
                    licenceApply1.setLicenceImages(licencePic);
                    this.licenceApplyMapper.updateById(licenceApply1);

                } else {
                    licenceApply1.setLicenceImages("");
                    licenceApply1.setStatus(LicenceApply.EStatus.IN_REVIEW.val());
                    licenceApply1.setMemberId(userId);
                    licenceApply1.setErpCustomerId(member.getErpUserId());
                    licenceApply1.setSignTime(licenceApply.getSignTime());
                    licenceApply1.setValidOnTime(licenceApply.getValidOnTime());
                    licenceApply1.setValidEndTime(licenceApply.getValidEndTime());
                    licenceApply1.setLicenceCode(licenceApply.getLicenceCode());
                    licenceApply1.setLicenceName(licenceApply.getLicenceName());
                    licenceApply1.setLicenceTypeId(licenceApply.getLicenceTypeId());
                    licenceApply1.setCreateTime(LocalDateTime.now());
                    this.licenceApplyMapper.insert(licenceApply1);
                }
            }
            memberLog.setIp(ip);
            memberLog.setMemberId(userId);
            memberLog.setType("证件管理");
            memberLog.setContent("增加证件");
            memberLog.setCreateTime(LocalDateTime.now());
            this.memberLogMapper.insert(memberLog);
        }
    }

    @Override
    public MemberApplyVo getTelephoneMemberApplyVo(String telephone) {
        AssertExt.notNull(telephone, "手机号为空");
        MemberApplyVo memberApplyVo = this.memberApplyMapper.getTelephoneMemberApplyVo(telephone);
        if (null != memberApplyVo) {
            memberApplyVo.setLicenceApplyList(this.licenceApplyMapper.selectList(new QueryWrapper<LicenceApply>().eq("MEMBER_ID", memberApplyVo.getMemberId())));
        }

        return memberApplyVo;
    }

    @Override
    public IPage<LicenceApplyVo> getLicenceApplyVoList(Page page, LicenceApplyVo licenceApplyVo) {
        return this.licenceApplyMapper.getLicenceApplyList(page, licenceApplyVo);
    }

    @Override
    public IPage<LicenceVo> getLicenceVoList(Page page, LicenceVo licenceVo) {
        return this.licenceMapper.getLicenceVoList(page, licenceVo);
    }

    @Override
    public void checkLicenceApplyVoStatus(Long id, String status, String remark) {
        AssertExt.hasId(id, "id为空");
        AssertExt.notNull(status, "状态为空");
        LicenceApply licenceApply = this.licenceApplyMapper.selectById(id);
        AssertExt.notNull(licenceApply, "无效id");
        licenceApply.setStatus(status);
        licenceApply.setReason(remark);
        this.licenceApplyMapper.updateById(licenceApply);
        if (status.equals(LicenceApply.EStatus.APPROVE.val())) {
            Licence licence = new Licence();
            licence.setErpCustomerId(licenceApply.getErpCustomerId());
            //licence.setErpLicenceId(memberApplyVo.getErpLicenceId());
            licence.setLicenceImages(licenceApply.getLicenceImages());
            licence.setLicenceName(licenceApply.getLicenceName());
            licence.setLicenceTypeId(licenceApply.getLicenceTypeId());
            licence.setErpCustomerId(licenceApply.getErpCustomerId());
            licence.setLicenceCode(licenceApply.getLicenceCode());
            /**
             * 设置证照状态：
             * 1.有效期大于当前时间1个月内      为即将过期
             * 2.有效期大于当前时间1个月后      为正常
             * 3.有效期小于当前时间             为过期
             */
           /* if (null != licenceApply.getValidEndTime()) {
                if (licenceApply.getValidEndTime().isAfter(LocalDateTime.now().plusMonths(1L))) {
                    licence.setStatus(Licence.EStatus.NORMAL.val());
                } else if (licenceApply.getValidEndTime().isBefore(LocalDateTime.now().plusMonths(1L))) {
                    licence.setStatus(Licence.EStatus.BE_ABOUT_TO_EXPIRES.val());
                }
                licence.setStatus(Licence.EStatus.EXPIRES.val());
            }*/
            licence.setStatus(Licence.EStatus.NORMAL.val());
            licence.setSignTime(licenceApply.getSignTime());
            licence.setValidOnTime(licenceApply.getValidOnTime());
            licence.setValidEndTime(licenceApply.getValidEndTime());
            licence.setApplyId(licenceApply.getId());
            licence.setLicenceId(licenceApply.getLicenceId());
            licence.setCreateTime(LocalDateTime.now());
            this.licenceMapper.insert(licence);
        }
    }

    @Override
    public void updateLicenceApply(Licence licenceApply) {
        AssertExt.hasId(licenceApply.getId(), "id为空");
        Licence licenceApplyDB = this.licenceMapper.selectById(licenceApply.getId());
        AssertExt.notNull(licenceApplyDB, "无效id");
        this.licenceMapper.updateById(licenceApply);
    }

    @Override
    public void updateMemberTelephone(Long id, String telephone, String code,String ip) {
        AssertExt.hasId(id, "id");
        AssertExt.notNull(telephone, "手机号为空");
        AssertExt.notNull(code, "验证码为空");
        this.loginService.validateSmsCode(telephone, code);
        Member memberDB = this.memberMapper.selectById(id);
        memberDB.setTelephone(telephone);
        this.memberMapper.updateById(memberDB);

        MemberLogVo memberLog = new MemberLogVo();
        memberLog.setMemberId(memberDB.getId());
        memberLog.setIp(ip);
        memberLog.setContent("客户修改手机号");
        memberLog.setCreateTime(LocalDateTime.now());
        this.memberLogMapper.insert(memberLog);

    }

    @Override
    public List<com.zsyc.zt.inca.vo.CustomerVo> getCustomerVoByName(String userName) {
        return this.memberMapper.getCustomerVoByName(userName);
    }

    @Override
    public IPage<ApiMemberVo> selectApiMemberVoPage(Page<ApiMemberVo> page, ApiMemberVo apiMemberVo) {
        AssertExt.notNull(page,"分页参数为空");
        return this.memberMapper.selectApiMemberVoPage(page,apiMemberVo);
    }


}




