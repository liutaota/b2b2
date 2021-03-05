package com.zsyc.zt.b2b.service;

import com.zsyc.zt.b2b.entity.LicenceApply;
import com.zsyc.zt.b2b.vo.LicenceApplyVo;
import com.zsyc.zt.b2b.vo.MemberLicenceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LicenceApplyService {
    /**
     * 客户认证申请详情
     * @param id
     * @return
     */
    List<LicenceApply> getLicenceApplyInfo(Long id);


    /**
     * erp+ b2b 客户证件信息-即将到期-已过期
     * @param
     * @return
     */
    List<MemberLicenceVo> getMemberLicenceList(MemberLicenceVo memberLicenceVo);

}
