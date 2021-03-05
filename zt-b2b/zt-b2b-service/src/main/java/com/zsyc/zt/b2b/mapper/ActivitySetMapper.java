package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.ActivitySet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.ActivitySetVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 活动客户集合 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-28
 */
public interface ActivitySetMapper extends BaseMapper<ActivitySet> {


    /**
     * 活动客户集合
     *
     * @param page
     * @param activityId
     * @return
     */
    IPage<ActivitySetVo> getActivitySetList(@Param("page") Page page, @Param("activityId") Long activityId);

    /**
     * 根据id查询客户是否在客户集合里
     *
     * @param activityId
     * @param customId
     * @return
     */
    Long getActivitySetByCustomId(@Param("activityId") Long activityId, @Param("customId") Long customId);
}
