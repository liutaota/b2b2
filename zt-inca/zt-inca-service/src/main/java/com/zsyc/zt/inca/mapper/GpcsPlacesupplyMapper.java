package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.entity.GpcsPlacesupply;
import com.zsyc.zt.inca.vo.BmsPresDataVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-01-11
 */
@Mapper
public interface GpcsPlacesupplyMapper extends BaseMapper<GpcsPlacesupply> {


    void updateUsestatus(@Param("placesupplyId") Long placesupplyId);

    BmsPresDataVo selectByData(@Param("placesupplyId") Long placesupplyId);
    @Select("select a.tranposid from bms_tr_pos_def a where a.companyid = #{placepointId} and a.tranpostype = 1 order by a.tranposid desc")
    Long getTargetposId(@Param("placepointId") Long placepointId);

    void updateSummary(@Param("placesupplyId")Long placesupplyid);
}
