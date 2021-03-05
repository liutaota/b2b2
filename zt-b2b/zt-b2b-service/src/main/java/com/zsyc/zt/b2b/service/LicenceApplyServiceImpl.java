package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.entity.LicenceApply;
import com.zsyc.zt.b2b.entity.Member;
import com.zsyc.zt.b2b.mapper.LicenceApplyMapper;
import com.zsyc.zt.b2b.mapper.MemberMapper;
import com.zsyc.zt.b2b.vo.MemberLicenceVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class LicenceApplyServiceImpl implements LicenceApplyService {
    @Autowired
    private LicenceApplyMapper licenceApplyMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Override
    public List<LicenceApply> getLicenceApplyInfo(Long id) {
        AssertExt.hasId(id,"无效id");
        return this.licenceApplyMapper.selectList(new QueryWrapper<LicenceApply>().eq("MEMBER_ID", id));
    }

    @Override
    public List<MemberLicenceVo> getMemberLicenceList(MemberLicenceVo memberLicenceVo) {
        AssertExt.hasId(memberLicenceVo.getMemberId(),"客户id为空");
        Member member=this.memberMapper.selectById(memberLicenceVo.getMemberId());
        memberLicenceVo.setErpCustomerId(member.getErpUserId());
        return this.licenceApplyMapper.getMemberLicenceList(memberLicenceVo);
    }
}
