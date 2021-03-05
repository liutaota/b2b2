package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.entity.BmsTranscontrolDoc;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2021-01-28
 */
public interface BmsTranscontrolDocMapper extends BaseMapper<BmsTranscontrolDoc> {

    Long getRoadidOfLastest(@Param("customId") Long customId);
}
