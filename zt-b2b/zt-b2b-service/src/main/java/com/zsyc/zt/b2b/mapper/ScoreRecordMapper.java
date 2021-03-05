package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.ScoreRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.ScoreRecordVo;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * <p>
 * 用户积分记录 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-09-26
 */
public interface ScoreRecordMapper extends BaseMapper<ScoreRecord> {
    /**
     * 客户积分记录
     * @param page
     * @param scoreRecordVo
     * @return
     */
    IPage<ScoreRecordVo> getScoreRecordList(@Param("page")Page page,@Param("scoreRecordVo") ScoreRecordVo scoreRecordVo);
}
