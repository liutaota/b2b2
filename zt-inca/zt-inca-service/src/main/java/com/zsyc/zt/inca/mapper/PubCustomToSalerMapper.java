package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.PubCustomToSaler;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 *<p>
 *    Mapper 接口
 *</p>
 *@author MP
 *@since 2020-07-24
 */

@Mapper
public interface PubCustomToSalerMapper extends BaseMapper<PubCustomToSaler> {
/*    *//**
     * 获取门店使用的保管账
     * @param placepointId
     * @return
     *//*
    @Select("select t.storageid from gpcs_placepoint t where t.placepointid =#{placepointId}")
    Long getPlacepointStorageIdByCustomId(@Param("placepointId") Long placepointId);*/

    PubCustomToSaler getSalerByCustomId(@Param("customId")Long customId, @Param("entryId")Integer entryId);
}
