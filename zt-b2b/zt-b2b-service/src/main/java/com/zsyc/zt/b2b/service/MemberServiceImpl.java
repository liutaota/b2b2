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
        AssertExt.hasId(id, "id??????");
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
        AssertExt.notNull(oldPassword, "???????????????");
        AssertExt.notNull(telephone, "???????????????");
        Member memberDB = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("telephone", telephone));
        AssertExt.isTrue(memberDB != null, "??????????????????????????????");
        if (null != memberDB && !memberDB.getTelephone().equals(memberDB.getPassword())) {
            boolean check = DigestUtils.sha1Hex(oldPassword + memberDB.getSalt()).equals(memberDB.getPassword());
            AssertExt.isTrue(check, "???????????????");

        }
    }

    @Override
    public void updateMemberPassword(String telephone, String newPassword, String rePassword, String ip) {
        AssertExt.notNull(telephone, "???????????????");
        AssertExt.matches(".{6,16}", newPassword, "????????????6???16???");
        AssertExt.isTrue(newPassword.equals(rePassword), "?????????????????????");
        Member memberDB = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("telephone", telephone));
        AssertExt.isTrue(memberDB != null, "??????????????????????????????");
        memberDB.setSalt(RandomStringUtils.random(10, true, true));
        memberDB.setPassword(DigestUtils.sha1Hex(newPassword + memberDB.getSalt()));
        memberDB.setAuthModifyPwdTime(LocalDateTime.now());
        this.memberMapper.updateById(memberDB);
        MemberLogVo memberLog = new MemberLogVo();
        memberLog.setContent("??????-????????????");
        memberLog.setMemberId(memberDB.getId());
        memberLog.setIp(ip);
        this.addMemberLog(memberLog);
    }

    @Override
    public void updateMemberPasswordById(Long id, String oldPassword, String newPassword, String rePassword, String ip) {
        AssertExt.matches(".{6,16}", newPassword, "????????????6???16???");
        AssertExt.isTrue(newPassword.equals(rePassword), "?????????????????????");
        Member memberDB = this.memberMapper.selectById(id);

        this.checkOldPassword(memberDB.getTelephone(), oldPassword);

        memberDB.setSalt(RandomStringUtils.random(10, true, true));
        memberDB.setPassword(DigestUtils.sha1Hex(newPassword + memberDB.getSalt()));
        memberDB.setAuthModifyPwdTime(LocalDateTime.now());
        this.memberMapper.updateById(memberDB);
        MemberLogVo memberLog = new MemberLogVo();
        memberLog.setContent("?????????+????????????");
        memberLog.setMemberId(memberDB.getId());
        memberLog.setIp(ip);
        this.addMemberLog(memberLog);
    }

    @Override
    public IPage<CustomerVo> getCustomerByCustomerInfoVo(Page page, CustomerInfoVo customerInfoVo) {
        AssertExt.notNull(page, "???????????????");
        IPage<CustomerVo> customerVoList = this.memberMapper.getCustomerByCustomerInfoVo(page, customerInfoVo);
        log.info("page size {}", customerVoList.getSize());
        return customerVoList;
    }

    @Override
    public CustomerVo getCustomerListInfoById(Long memberId) {
        AssertExt.hasId(memberId, "????????????ID");
        //TODO select???????????????
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
        AssertExt.hasId(customerVo.getId(), "??????ID??????");
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
        AssertExt.hasId(customid, "id??????");
        return this.memberMapper.getMemberPubGoodsPriceList(page, customid);
    }

    @Override
    public void rePassword(MemberVo memberVo, String ip) {
        AssertExt.hasId(memberVo.getMemberId(), "????????????ID");
        AssertExt.notBlank(memberVo.getRePassword(), "????????????????????????");
        AssertExt.matches(".{6,20}", memberVo.getRePassword(), "????????????8???20???");
        Member member = this.memberMapper.selectById(memberVo.getMemberId());
        AssertExt.notNull(member, "??????????????????,?????????pcWeb??????");
        member.setSalt(RandomStringUtils.random(10, true, true));
        member.setPassword(DigestUtils.sha1Hex(memberVo.getRePassword() + member.getSalt()));
        member.setAuthModifyPwdTime(LocalDateTime.now());
        AssertExt.isFalse(memberVo.getRePassword().equals(member.getPassword()), "????????????????????????");
        this.memberMapper.updateById(member);
        MemberLogVo memberLogVo = new MemberLogVo();
        memberLogVo.setContent("????????????");
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
        AssertExt.hasId(id, "??????ID??????");
        Member member = this.memberMapper.selectById(id);
        if (null != member) {
            return this.memberMapper.getMemberLicenceList(member.getErpUserId());
        }

        return this.memberMapper.getMemberLicenceList(id);
    }

    @Override
    public List<MemberLicenceVo> getMemberLicenceList(Long id) {
        AssertExt.hasId(id, "??????ID??????");
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
        AssertExt.hasId(id, "??????ID??????");
        Member member = this.memberMapper.selectById(id);

        if (null != member) {
            return this.customService.selectBusinessScopeByCustomId(member.getErpUserId());
        }
        return this.customService.selectBusinessScopeByCustomId(id);
    }

    @Override
    public void userRegistration(MemberApplyVo memberApply, Long userId, String ip) {
        AssertExt.notBlank(memberApply.getTelephone(), "???????????????");
        AssertExt.notBlank(memberApply.getCompanyType(), "?????????????????????");
        AssertExt.checkEnum(MemberApply.ECompanyType.class, memberApply.getCompanyType(), "????????????????????????????????????");
        AssertExt.notBlank(memberApply.getCompanyName(), "?????????????????????");
        AssertExt.notBlank(memberApply.getAreaName(), "????????????????????????");
        AssertExt.notBlank(memberApply.getAddress(), "?????????????????????");
        AssertExt.notBlank(memberApply.getGoodsbusiscopeids(), "?????????????????????id");
        AssertExt.notBlank(memberApply.getGoodsbusiscope(), "?????????????????????");
        AssertExt.notBlank(memberApply.getContactName(), "??????????????????");
        AssertExt.notBlank(memberApply.getContactPhone(), "????????????????????????");
        memberApply.setStatus(MemberApply.EStatus.APPLYING.val());// ??????????????????????????????-->?????????????????????????????????????????????????????????????????????
        memberApply.setCreateTime(LocalDateTime.now());
        this.memberApplyMapper.insert(memberApply);

        Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("TELEPHONE", memberApply.getTelephone()));
        member.setApply(memberApply.getId());
        this.memberMapper.updateById(member);

        MemberLogVo memberLog = new MemberLogVo();
        memberLog.setIp(ip);
        memberLog.setMemberId(member.getId());
        memberLog.setType("??????");
        memberLog.setContent("????????????");
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
        AssertExt.notNull(licenceApplyVoList, "????????????????????????");
        Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("TELEPHONE", licenceApplyVoList.get(0).getTelephone()));
        this.licenceApplyMapper.delLicenceApplyByMemberId(member.getId());
        licenceApplyVoList.forEach(licenceApplyVo -> {
            if (null != licenceApplyVo.getLicenceImages()) {
                //AssertExt.notNull(licenceApplyVo.getLicenceName(), "??????????????????");
                AssertExt.notNull(licenceApplyVo.getStatus(), "????????????");
                //AssertExt.notNull(licenceApplyVo.getLicenceImages(), "?????????????????????");
                //AssertExt.notNull(licenceApplyVo.getLicenceTypeId(), "?????????????????????id");
                //AssertExt.notNull(licenceApplyVo.getLicenceCode(), "??????????????????");
                //AssertExt.notNull(licenceApplyVo.getSignDate(), "?????????????????????");
                //AssertExt.notNull(licenceApplyVo.getValidOnDate(), "?????????????????????");
                //AssertExt.notNull(licenceApplyVo.getValidEndDate(), "?????????????????????");
                licenceApplyVo.setMemberId(member.getId());
                licenceApplyVo.setErpCustomerId(member.getErpUserId());
                licenceApplyVo.setCreateTime(LocalDateTime.now());
                //licenceApplyVo.setStatus(LicenceApply.EStatus.IN_REVIEW.val());// ?????????????????????????????????-->????????????????????????????????????????????????/??????
                if (null == licenceApplyVo.getId()) this.licenceApplyMapper.insert(licenceApplyVo);

                String[] licenceImages = licenceApplyVo.getLicenceImages().split(",");
                String licencePic = this.fileService.coverImageById(licenceApplyVo.getId(), Constant.IMAGE_PREFIX.LICENCE_APPLY, licenceImages);
                licenceApplyVo.setLicenceImages(licencePic);

                this.licenceApplyMapper.updateById(licenceApplyVo);

                MemberApply memberApply = this.memberApplyMapper.selectById(member.getApply());
                AssertExt.notNull(memberApply, "[%s]id??????", member.getApply());
                memberApply.setStatus(licenceApplyVo.getStatus());
                this.memberApplyMapper.updateById(memberApply);
            } else {
                licenceApplyVo.setLicenceImages("");
                //AssertExt.notNull(licenceApplyVo.getLicenceName(), "??????????????????");
                //AssertExt.notNull(licenceApplyVo.getLicenceTypeId(), "?????????????????????id");
                AssertExt.notNull(licenceApplyVo.getStatus(), "????????????");
                licenceApplyVo.setMemberId(member.getId());
                licenceApplyVo.setErpCustomerId(member.getErpUserId());
                licenceApplyVo.setCreateTime(LocalDateTime.now());
                //licenceApplyVo.setStatus(LicenceApply.EStatus.IN_REVIEW.val());// ?????????????????????????????????-->????????????????????????????????????????????????/??????
                if (null != licenceApplyVo.getId()) this.licenceApplyMapper.updateById(licenceApplyVo);
                else this.licenceApplyMapper.insert(licenceApplyVo);

                MemberApply memberApply = this.memberApplyMapper.selectById(member.getApply());
                AssertExt.notNull(memberApply, "[%s]id??????", member.getApply());
                memberApply.setStatus(licenceApplyVo.getStatus());
                this.memberApplyMapper.updateById(memberApply);
            }

            MemberLogVo memberLog = new MemberLogVo();
            memberLog.setIp(ip);
            memberLog.setMemberId(member.getId());
            memberLog.setType("??????");
            memberLog.setContent("????????????");
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
         * ???????????????
         * 1?????????????????????id?????????????????????????????????
         * 2???????????????????????????/????????????/??????
         * 3?????????????????????id???????????????????????????->????????????apply??????????????????id
         * 4???????????????id??????????????????????????????->??????????????????memberId????????????id
         * 5???????????????????????????/????????????/??????
         * 6????????????????????????+??????????????????????????????????????????????????????
         */
        AssertExt.hasId(memberApplyVo.getMemberApply().getId(), "????????????id??????");
        AssertExt.notNull(memberApplyVo.getErpCustomId(), "erp??????id??????");
        // ??????erp ??????????????? ??????id
        List<Long> erpUserIdList = this.memberMapper.getCustomIdList();
        for (Long id : erpUserIdList) {
            AssertExt.isFalse(memberApplyVo.getErpCustomId().equals(id), "erp??????id????????????????????????????????????????????????");
        }
        MemberApply memberApply = this.memberApplyMapper.selectById(memberApplyVo.getMemberApply().getId());
        AssertExt.notNull(memberApply, "???????????????????????????");
        //AssertExt.isTrue(memberApply.getStatus().equals(MemberApply.EStatus.IN_REVIEW.val()), "?????????????????????????????????????????????????????????????????????????????????");
        AssertExt.notNull(memberApplyVo.getMemberApply().getStatus(), "??????????????????????????????");
        AssertExt.notNull(memberApplyVo.getMemberApply().getReason(), "??????????????????????????????");
        AssertExt.checkEnum(MemberApply.EStatus.class, memberApplyVo.getMemberApply().getStatus(), "???????????????????????????");
        AssertExt.notNull(memberApplyVo.getMemberApply().getGoodsbusiscopeids(), "????????????????????????id??????");
        AssertExt.notNull(memberApplyVo.getMemberApply().getGoodsbusiscope(), "??????????????????????????????");
        BeanUtils.copyProperties(memberApplyVo.getMemberApply(), memberApply);
        this.memberApplyMapper.updateById(memberApply);

        Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("APPLY", memberApply.getId()));
        AssertExt.notNull(member, "???????????????");
        List<LicenceApply> licenceApplyList = this.licenceApplyMapper.selectList(new QueryWrapper<LicenceApply>().eq("MEMBER_ID", member.getId()));
        if (null != licenceApplyList) {
            AssertExt.notNull(memberApplyVo.getLicenceApplyList(), "????????????????????????");
            for (LicenceApply licenceApply : licenceApplyList) {
                //AssertExt.isTrue(licenceApply.getStatus().equals(LicenceApply.EStatus.IN_REVIEW.val()), "???????????????????????????????????????????????????????????????????????????");
                for (LicenceApply licenceApplyDB : memberApplyVo.getLicenceApplyList()) {
                    if (licenceApplyDB.getId().equals(licenceApply.getId())) {
                        AssertExt.notNull(licenceApplyDB.getStatus(), "????????????????????????");
                        AssertExt.checkEnum(LicenceApply.EStatus.class, licenceApplyDB.getStatus(), "???????????????????????????");
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
                         * ?????????????????????
                         * 1.???????????????????????????1?????????      ???????????????
                         * 2.???????????????????????????1?????????      ?????????
                         * 3.???????????????????????????             ?????????
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
        AssertExt.hasId(memberVo.getErpUserId(), "erp??????id??????");
        AssertExt.notNull(memberVo.getTelephone(), "???????????????");
        AssertExt.notNull(memberVo.getPassword(), "??????????????????");
        AssertExt.notNull(memberVo.getRePassword(), "????????????????????????");
        // 1.??????ERP_CUSTOMER_V -->??????erpUserId
        CustomerVo customerVo = this.memberMapper.getCustomerVoInfo(memberVo.getErpUserId());
        log.info("customerVo {}", customerVo);
        // 2.????????????MEMBER -->??????erpUserId/telephone
        Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("ERP_USER_ID", memberVo.getErpUserId())
                .or().eq("TELEPHONE", memberVo.getTelephone()));
        log.info("member {}", member);
        AssertExt.isFalse(member != null, "???????????????");
        Member memberDB = new Member();
        memberDB.setErpUserId(customerVo.getCustomid());
        memberDB.setTelephone(memberVo.getTelephone());
        memberDB.setPassword(memberVo.getTelephone());
        AssertExt.isTrue(memberVo.getPassword().equals(memberVo.getRePassword()), "?????????????????????");
        memberDB.setPassword(memberVo.getPassword());
        memberDB.setCreateTime(LocalDateTime.now());
        memberDB.setUserName(customerVo.getCustomname());
        memberDB.setIsErp(Constant.NEW_MEMBER.FORMER);
        this.memberMapper.insert(memberDB);
    }

    @Override
    public void editCertificationList(List<LicenceApplyVo> licenceApplyVoList, Long userId, String ip) {
        AssertExt.notNull(licenceApplyVoList, "??????????????????");
        Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("TELEPHONE", licenceApplyVoList.get(0).getTelephone()));
        licenceApplyVoList.forEach(licenceApplyVo -> {
            AssertExt.notNull(licenceApplyVo.getStatus(), "????????????");
            if (null != licenceApplyVo.getLicenceImages()) {
                licenceApplyVo.setStatus(LicenceApply.EStatus.IN_REVIEW.val());
                if (null == licenceApplyVo.getId())
                    this.licenceApplyMapper.insert(licenceApplyVo);

                //LicenceApply licenceApplyDB = this.licenceApplyMapper.selectOne(new QueryWrapper<LicenceApply>().eq("id",licenceApplyVo.getId()).eq("MEMBER_ID", userId).eq("????", licenceApplyVo.getLicenceTypeId()));
                //AssertExt.notNull(licenceApplyVo.getLicenceName(), "???????????????");
                //AssertExt.notNull(licenceApplyVo.getLicenceImages(), "??????????????????");

                String[] licenceImages = licenceApplyVo.getLicenceImages().split(",");
                String licencePic = this.fileService.coverImageById(licenceApplyVo.getId(), Constant.IMAGE_PREFIX.LICENCE_APPLY, licenceImages);
                licenceApplyVo.setLicenceImages(licencePic);

                //AssertExt.notNull(licenceApplyVo.getLicenceTypeId(), "????????????id??????");
                //AssertExt.notNull(licenceApplyVo.getLicenceCode(), "???????????????");
                //AssertExt.notNull(licenceApplyVo.getSignDate(), "??????????????????");
                //AssertExt.notNull(licenceApplyVo.getValidOnDate(), "??????????????????");
                //AssertExt.notNull(licenceApplyVo.getValidEndDate(), "??????????????????");
                //licenceApplyVo.setStatus(LicenceApply.EStatus.IN_REVIEW.val());
                //BeanUtils.copyProperties(licenceApplyVo, licenceApplyDB);
                this.licenceApplyMapper.updateById(licenceApplyVo);
            } else {
                licenceApplyVo.setLicenceImages("");
                LicenceApply licenceApplyDB = this.licenceApplyMapper.selectOne(new QueryWrapper<LicenceApply>().eq("MEMBER_ID", member.getId()));
                //AssertExt.notNull(licenceApplyVo.getLicenceName(), "???????????????");
                //AssertExt.notNull(licenceApplyVo.getLicenceTypeId(), "????????????id??????");
                BeanUtils.copyProperties(licenceApplyVo, licenceApplyDB);
                licenceApplyDB.setStatus(LicenceApply.EStatus.IN_REVIEW.val());
                this.licenceApplyMapper.updateById(licenceApplyDB);

            }

            MemberApply memberApply = this.memberApplyMapper.selectById(member.getApply());
            AssertExt.notNull(memberApply, "[%s]id??????", member.getApply());
            memberApply.setStatus(LicenceApply.EStatus.IN_REVIEW.val());
            this.memberApplyMapper.updateById(memberApply);

            MemberLogVo memberLog = new MemberLogVo();
            memberLog.setIp(ip);
            memberLog.setCreateTime(LocalDateTime.now());
            memberLog.setMemberId(member.getId());
            memberLog.setType("??????");
            memberLog.setContent("????????????????????????");
            this.memberLogMapper.insert(memberLog);
        });
    }

    @Override
    public void editMemberApply(MemberApplyVo memberApply, Long userId, String ip) {
        AssertExt.notBlank(memberApply.getCompanyType(), "??????????????????");
        AssertExt.checkEnum(MemberApply.ECompanyType.class, memberApply.getCompanyType(), "?????????????????????????????????");
        AssertExt.notBlank(memberApply.getCompanyName(), "??????????????????");
        AssertExt.notBlank(memberApply.getAreaName(), "?????????????????????");
        AssertExt.notBlank(memberApply.getAddress(), "??????????????????");
        AssertExt.notBlank(memberApply.getGoodsbusiscopeids(), "????????????id??????");
        AssertExt.notBlank(memberApply.getGoodsbusiscope(), "??????????????????");
        AssertExt.notBlank(memberApply.getContactName(), "???????????????");
        AssertExt.notBlank(memberApply.getContactPhone(), "?????????????????????");
        Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("TELEPHONE", memberApply.getTelephone()));
        AssertExt.notNull(member, "??????????????????????????????");
        MemberApply memberApplyDB = this.memberApplyMapper.selectById(member.getApply());
        AssertExt.notNull(memberApplyDB, "???????????????????????????");
        BeanUtils.copyProperties(memberApply, memberApplyDB);
        this.memberApplyMapper.updateById(memberApplyDB);

        MemberLogVo memberLog = new MemberLogVo();
        memberLog.setType("??????");
        memberLog.setContent("????????????????????????");
        memberLog.setMemberId(member.getId());
        memberLog.setIp(ip);
        memberLog.setCreateTime(LocalDateTime.now());
        this.memberLogMapper.insert(memberLog);
    }

    @Override
    public void updateMemberTelephone(Long customerid, String telephone) {
        AssertExt.hasId(customerid, "customerid??????");
        AssertExt.notNull(telephone, "???????????????");

        Member memberDB = this.memberMapper.selectOne(new QueryWrapper<Member>().eq("ERP_USER_ID", customerid));
        if (null != memberDB) {
            Member member = this.memberMapper.selectOne(new QueryWrapper<Member>().ne("ERP_USER_ID", customerid).eq("telephone", telephone));
            AssertExt.isFalse(member != null, "??????????????????");
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
        AssertExt.notNull(page, "????????????");
        return this.memberLogMapper.getMemberLogList(page,memberLogVo);
    }

    @Override
    public MemberApply getNewMemberApplyInfo(Long id) {
        AssertExt.hasId(id, "id??????");
        return this.memberApplyMapper.selectById(id);
    }

    @Override
    public List<LicenceApplyVo> getAuditResultList(Long userId) {
        AssertExt.hasId(userId, "id??????");
        return this.licenceApplyMapper.getAuditResultList(userId);
    }

    @Override
    public void editLicence(LicenceApply licenceApply, Long userId, String ip) {
        AssertExt.notNull(licenceApply.getLicenceTypeId(), "????????????id??????");
       // AssertExt.notNull(licenceApply.getLicenceId(), "??????id??????");
        MemberLogVo memberLog = new MemberLogVo();
        LicenceApply licenceApplyDB = this.licenceApplyMapper.selectOne(new QueryWrapper<LicenceApply>().eq("MEMBER_ID", userId).eq("LICENCE_TYPE_ID", licenceApply.getLicenceTypeId()));
        AssertExt.notNull(licenceApply.getLicenceName(), "??????????????????");
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
            memberLog.setType("????????????");
            memberLog.setContent("????????????");
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
            memberLog.setType("????????????");
            memberLog.setContent("????????????");
            memberLog.setCreateTime(LocalDateTime.now());
            this.memberLogMapper.insert(memberLog);
        }
    }

    @Override
    public MemberApplyVo getTelephoneMemberApplyVo(String telephone) {
        AssertExt.notNull(telephone, "???????????????");
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
        AssertExt.hasId(id, "id??????");
        AssertExt.notNull(status, "????????????");
        LicenceApply licenceApply = this.licenceApplyMapper.selectById(id);
        AssertExt.notNull(licenceApply, "??????id");
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
             * ?????????????????????
             * 1.???????????????????????????1?????????      ???????????????
             * 2.???????????????????????????1?????????      ?????????
             * 3.???????????????????????????             ?????????
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
        AssertExt.hasId(licenceApply.getId(), "id??????");
        Licence licenceApplyDB = this.licenceMapper.selectById(licenceApply.getId());
        AssertExt.notNull(licenceApplyDB, "??????id");
        this.licenceMapper.updateById(licenceApply);
    }

    @Override
    public void updateMemberTelephone(Long id, String telephone, String code,String ip) {
        AssertExt.hasId(id, "id");
        AssertExt.notNull(telephone, "???????????????");
        AssertExt.notNull(code, "???????????????");
        this.loginService.validateSmsCode(telephone, code);
        Member memberDB = this.memberMapper.selectById(id);
        memberDB.setTelephone(telephone);
        this.memberMapper.updateById(memberDB);

        MemberLogVo memberLog = new MemberLogVo();
        memberLog.setMemberId(memberDB.getId());
        memberLog.setIp(ip);
        memberLog.setContent("?????????????????????");
        memberLog.setCreateTime(LocalDateTime.now());
        this.memberLogMapper.insert(memberLog);

    }

    @Override
    public List<com.zsyc.zt.inca.vo.CustomerVo> getCustomerVoByName(String userName) {
        return this.memberMapper.getCustomerVoByName(userName);
    }

    @Override
    public IPage<ApiMemberVo> selectApiMemberVoPage(Page<ApiMemberVo> page, ApiMemberVo apiMemberVo) {
        AssertExt.notNull(page,"??????????????????");
        return this.memberMapper.selectApiMemberVoPage(page,apiMemberVo);
    }


}




