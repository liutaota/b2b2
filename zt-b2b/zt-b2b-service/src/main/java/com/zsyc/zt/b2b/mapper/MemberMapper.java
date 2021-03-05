package com.zsyc.zt.b2b.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.Member;
import com.zsyc.zt.b2b.vo.*;
import com.zsyc.zt.inca.entity.BmsSaInvInfo;
import com.zsyc.zt.inca.entity.BmsTrPosDef;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-24
 */
public interface MemberMapper extends BaseMapper<Member> {
    /**
     * 查询手机号是否存在
     *
     * @param customid
     * @return
     */
    CustomerVo getPhone(@Param("customid") Long customid);

    /**
     * 个人信息
     *
     * @param id
     * @return
     */
    CustomerVo getCustomerVoInfo(@Param("id") Long id);

    /**
     * 个人信息-b2b
     *
     * @param id
     * @return
     */
    CustomerVo getMemberInfo(@Param("id") Long id);

    /**
     * 客户列表
     *
     * @param customerInfoVo
     * @return
     */
    IPage<CustomerVo> getCustomerByCustomerInfoVo(Page page, @Param("customerInfoVo") CustomerInfoVo customerInfoVo);

    /**
     * 获取erp所有客户id
     * @return
     */
    List<Long> getCustomIdList();

    /**
     * 客户列表不分页
     *
     * @param customerInfoVo
     * @return
     */
    List<CustomerVo> getCustomerByCustomerInfoVo(@Param("customerInfoVo") CustomerInfoVo customerInfoVo);

    /**
     * 客户详情（基本信息）
     *
     * @param memberId
     * @return
     */
    CustomerVo getCustomerListInfoById(@Param("memberId") Long memberId);

    /**
     * 我的运输地址
     *
     * @param memberId
     * @return
     */
    List<BmsTrPosDefVo> getMyBmsTrPosDef(@Param("memberId") Long memberId);

    /**
     * 我的发票信息
     *
     * @param memberId
     * @return
     */
    List<BmsSaInvInfo> getMyBmsSaInvInfo(@Param("memberId") Long memberId);

    /**
     * 客户证照/证照经营范围
     *
     * @param page
     * @param customerVo
     * @return
     */
    IPage<CustomerLicenseVo> getMemberLicense(@Param("page") Page page, @Param("customerVo") CustomerVo customerVo);

    /**
     * 客户经营范围不分页
     *
     * @param customerId
     * @return
     */
    List<CustomerLicenseVo> getMemberLicenseAll(@Param("customerId") Long customerId);

    /**
     * 客户协议价
     *
     * @param page
     * @param customid
     * @return
     */
    IPage<PubGoodsPriceVo> getMemberPubGoodsPriceList(@Param("page") Page page, @Param("customid") Long customid);

    /**
     * 客户集合总单
     *
     * @param page
     * @param bannedVo
     * @return
     */
    IPage<BannedVo> getAdminMemberList(@Param("page") Page page, @Param("bannedVo") BannedVo bannedVo);

    /**
     * 客户集合
     * @param customSetId
     * @return
     */
    BannedVo getAdminMember(@Param("customSetId") Long customSetId);

    /**
     * 客户集合细单
     *
     * @param page
     * @param bannedVo
     * @return
     */
    IPage<BannedVo> getAdminMemberListById(@Param("page") Page page, @Param("bannedVo") BannedVo bannedVo);

    /**
     * 客户集合细单不分页-->优惠券专用
     * @param setIdList
     * @return
     */
    List<BannedVo> getAdminMemberListByIdIsNotPage(@Param("setIdList") List<Long> setIdList);

    /**
     * 可登陆客户id
     *
     * @return
     */
    List<Long> getCustomerGspusestatus();

    /**
     * 客户证件信息
     * @param memberId
     * @return
     */
    List<MemberLicenceVo> getMemberLicenceList(@Param("memberId") Long memberId);

    /**
     * erp+ b2b 客户证件信息
     * @param memberId
     * @return
     */
    List<MemberLicenceVo> getPCMemberLicenceList(@Param("erpUserId") Long erpUserId, @Param("memberId") Long memberId);

    /**
     * 清空openid
     * @param id
     * @param openid
     */
    void updateMemberOpenidSetNull(@Param("id") Long id, @Param("openid") String openid);

    /**
     * 客户搜索
     * @param userName
     * @return
     */
    List<com.zsyc.zt.inca.vo.CustomerVo> getCustomerVoByName(@Param("userName") String userName);

    /**
     * API对接客户查询
     * @param page
     * @param apiMemberVo
     * @return
     */
    IPage<ApiMemberVo> selectApiMemberVoPage(Page<ApiMemberVo> page,@Param("apiMemberVo") ApiMemberVo apiMemberVo);
}
