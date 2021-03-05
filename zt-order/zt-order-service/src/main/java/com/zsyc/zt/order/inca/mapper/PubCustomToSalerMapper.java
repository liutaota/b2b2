package com.zsyc.zt.order.inca.mapper;

import com.zsyc.zt.order.entity.PubCustomToSaler;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
public interface PubCustomToSalerMapper extends BaseMapper<PubCustomToSaler> {

    /**
     * 获取门店使用的保管账
     * @param placepointId
     * @return
     */
    @Select("select t.storageid from gpcs_placepoint t where t.placepointid =#{placepointId}")
    String getStorageIdByPlacepointId(@Param("placepointId") Long placepointId);

}
