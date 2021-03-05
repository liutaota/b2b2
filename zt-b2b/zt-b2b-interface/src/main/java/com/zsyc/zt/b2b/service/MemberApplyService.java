package com.zsyc.zt.b2b.service;

import com.zsyc.zt.b2b.entity.MemberApply;
import com.zsyc.zt.b2b.vo.MemberApplyVo;

public interface MemberApplyService {
    /**
     * 客户注册申请详情
     * @param id
     * @return
     */
    MemberApplyVo getMemberApplyInfo(Long id);
}
