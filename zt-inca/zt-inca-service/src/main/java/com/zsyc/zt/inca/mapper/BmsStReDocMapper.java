package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.BmsStReDoc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-03
 */
@Mapper
public interface BmsStReDocMapper extends BaseMapper<BmsStReDoc> {

    void updateByRedocid(@Param("redocId") Long redocId);
}
