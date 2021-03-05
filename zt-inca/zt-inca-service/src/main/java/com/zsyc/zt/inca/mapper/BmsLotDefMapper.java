package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.BmsLotDef;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-31
 */
@Mapper
public interface BmsLotDefMapper extends BaseMapper<BmsLotDef> {

    void updateBy(@Param("proddate") Date proddate, @Param("invaliddate") Date invaliddate, @Param("lotId") Long lotId);
}
