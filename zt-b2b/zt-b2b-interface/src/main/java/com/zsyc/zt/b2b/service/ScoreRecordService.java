package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.ScoreRecord;
import com.zsyc.zt.b2b.vo.ScoreRecordVo;

import java.time.LocalDateTime;

public interface ScoreRecordService {
    /**
     * 积分记录列表
     * @param page
     * @param scoreRecordVo
     * @return
     */
    IPage<ScoreRecordVo> getScoreRecordList(Page page, ScoreRecordVo scoreRecordVo);

    /**
     * 积分记录变更
     * @param scoreRecord
     */
    void updateScoreRecord(ScoreRecord scoreRecord);

}
