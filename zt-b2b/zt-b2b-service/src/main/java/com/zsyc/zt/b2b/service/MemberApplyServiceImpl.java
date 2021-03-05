package com.zsyc.zt.b2b.service;

import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.entity.Member;
import com.zsyc.zt.b2b.entity.MemberApply;
import com.zsyc.zt.b2b.mapper.MemberApplyMapper;
import com.zsyc.zt.b2b.mapper.MemberMapper;
import com.zsyc.zt.b2b.vo.MemberApplyVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class MemberApplyServiceImpl implements MemberApplyService{
    @Autowired
    private MemberApplyMapper memberApplyMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Override
    public MemberApplyVo getMemberApplyInfo(Long id) {
        AssertExt.hasId(id,"ID无效");
        Member member = this.memberMapper.selectById(id);
        AssertExt.notNull(member,"客户不存在");
        MemberApply memberApply = this.memberApplyMapper.selectById(member.getApply());
        MemberApplyVo memberApplyVo = new MemberApplyVo();
        if (null != member.getErpUserId()) {
            memberApplyVo.setErpCustomId(member.getErpUserId());
        }
        BeanUtils.copyProperties(memberApply,memberApplyVo);
        memberApplyVo.setTelephone(member.getTelephone());
        return memberApplyVo;
    }
}
