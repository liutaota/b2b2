package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.entity.Member;
import com.zsyc.zt.b2b.entity.ScoreRecord;
import com.zsyc.zt.b2b.mapper.MemberLogMapper;
import com.zsyc.zt.b2b.mapper.MemberMapper;
import com.zsyc.zt.b2b.mapper.ScoreRecordMapper;
import com.zsyc.zt.b2b.vo.MemberLogVo;
import com.zsyc.zt.b2b.vo.ScoreRecordVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@Slf4j
public class ScoreRecordServiceImpl implements ScoreRecordService {
    @Autowired
    private ScoreRecordMapper scoreRecordMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private MemberLogMapper memberLogMapper;

    @Override
    public IPage<ScoreRecordVo> getScoreRecordList(Page page, ScoreRecordVo scoreRecordVo) {
        AssertExt.notNull(page, "页面为空");
        //AssertExt.notNull(scoreRecordVo.getMemberId(),"请输入客户id");
        return this.scoreRecordMapper.getScoreRecordList(page, scoreRecordVo);
    }

    @Override
    public void updateScoreRecord(ScoreRecord scoreRecord) {
        AssertExt.hasId(scoreRecord.getId(), "无效id");
        AssertExt.hasId(scoreRecord.getMemberId(), "客户id为空");
        AssertExt.notNull(scoreRecord.getContent(), "变更内容为空");
        AssertExt.notNull(scoreRecord.getChanScore(), "变更积分为空");
        ScoreRecord scoreRecordDB = this.scoreRecordMapper.selectById(scoreRecord.getId());
        AssertExt.notNull(scoreRecordDB, "无效[%s]id", scoreRecord.getId());

        BeanUtils.copyProperties(scoreRecord, scoreRecordDB);
        this.scoreRecordMapper.updateById(scoreRecordDB);

        Member memberDB = this.memberMapper.selectById(scoreRecordDB.getMemberId());
        AssertExt.notNull(memberDB, "无效客户id【%s】", scoreRecord.getMemberId());
        memberDB.setIntegral(memberDB.getIntegral() + scoreRecord.getChanScore());
        this.memberMapper.updateById(memberDB);

        MemberLogVo memberLog = new MemberLogVo();
        memberLog.setMemberId(memberDB.getId());
        memberLog.setIp(null);
        memberLog.setContent("积分变更");
        memberLog.setCreateTime(LocalDateTime.now());
        this.memberLogMapper.insert(memberLog);
    }
}
