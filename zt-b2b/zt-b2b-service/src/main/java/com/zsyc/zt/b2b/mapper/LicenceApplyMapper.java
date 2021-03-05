package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.LicenceApply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.LicenceApplyVo;
import com.zsyc.zt.b2b.vo.MemberLicenceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户证照申请 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-09-03
 */
public interface LicenceApplyMapper extends BaseMapper<LicenceApply> {
    /**
     * 提交资质（申请）列表
     *
     * @param page
     * @param licenceApplyVo
     * @return
     */
    IPage<LicenceApplyVo> getLicenceApplyList(@Param("page") Page page, @Param("licenceApplyVo") LicenceApplyVo licenceApplyVo);

    /**
     * @param id
     * @return
     */
    List<LicenceApplyVo> getAuditResultList(@Param("id") Long id);

    /**
     * 删除用户认证信息
     *
     * @param memberId
     */
    void delLicenceApplyByMemberId(@Param("memberId") Long memberId);

    /**
     * erp+ b2b 客户证件信息-即将到期-已过期
     * @param
     * @return
     */
    List<MemberLicenceVo> getMemberLicenceList(@Param("memberLicenceVo") MemberLicenceVo memberLicenceVo);

}
