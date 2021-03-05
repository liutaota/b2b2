package com.zsyc.zt.b2b.service;

import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.Reason;
import com.zsyc.zt.b2b.mapper.ReasonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Slf4j
public class ReasonServiceImpl implements ReasonService{
    @Autowired
    private ReasonMapper reasonMapper;

    @Override
    public List<Reason> getReasonList() {
        return this.reasonMapper.getReasonList();
    }

    @Override
    public void addReason(Reason reason,Long userId) {
        reason.setCreateUserId(userId);
        reason.setCreateTime(LocalDateTime.now());
        this.reasonMapper.insert(reason);
    }

    @Override
    public void updateReason(Reason reason,Long userId) {
        AssertExt.hasId(reason.getId(),"id无效");
        reason.setUpdateUserId(userId);
        reason.setUpdateTime(LocalDateTime.now());
        this.reasonMapper.updateById(reason);
    }

    @Override
    public void updateReasonIsDel(Long id,Long userId) {
        AssertExt.hasId(id,"无效id");
        Reason reason = this.reasonMapper.selectById(id);
        AssertExt.notNull(reason,"退货原因不存在");
        AssertExt.isTrue(Constant.IS_DEL.NO.equals(reason.getIsDel()),"退货原因已删除");
        reason.setIsDel(Constant.IS_DEL.YES);
        reason.setUpdateUserId(userId);
        reason.setUpdateTime(LocalDateTime.now());
        this.reasonMapper.updateById(reason);
    }
}
