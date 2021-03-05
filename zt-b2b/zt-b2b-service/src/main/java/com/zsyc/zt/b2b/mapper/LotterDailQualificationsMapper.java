package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.LotterDailQualifications;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.LotterDailQualificationsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 可抽奖次数 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-12-30
 */
public interface LotterDailQualificationsMapper extends BaseMapper<LotterDailQualifications> {
    /**
     * 删除临时抽奖次数
     *
     * @param memberId
     */
    void delLotterDailQualificationsByMemberId(@Param("memberId") Long memberId);

    /**
     * 我的抽奖次数
     *
     * @param page
     * @param lotterDailQualifications
     * @return
     */
    IPage<LotterDailQualificationsVo> getMyLotterDailQualificationsList(@Param("page") Page page, @Param("lotterDailQualifications") LotterDailQualificationsVo lotterDailQualifications);

    /**
     * 我的抽奖不分页
     * @param memberId
     * @return
     */
    List<LotterDailQualifications> getLotterDailQualificationsList(@Param("memberId") Long memberId);

    /**
     * 我的抽奖次数
     * @param memberId
     * @return
     */
    Integer getMyLotterCount(@Param("memberId") Long memberId, @Param("orderId") Long orderId);
}
