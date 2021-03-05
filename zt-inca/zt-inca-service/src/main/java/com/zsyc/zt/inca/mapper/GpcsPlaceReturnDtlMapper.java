package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.entity.GpcsPlaceReturnDtl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 订单
 * @author  liutao
 */
@Mapper
public interface GpcsPlaceReturnDtlMapper extends BaseMapper<GpcsPlaceReturnDtl> {
    @Select("select count(*) from gpcs_placereturndtl a where a.placereturnid=#{placeReturnId}")
    Integer getCountBy(@Param("placeReturnId") Long placeReturnId);

    List<GpcsPlaceReturnDtl> selectBy(@Param("placereturnId") Long placereturnId);

    void updateBy(@Param("usestatus") Integer usestatus, @Param("placereturndtlid") Long placereturndtlid);


    List<GpcsPlaceReturnDtl> selectCanBackQtyBy(@Param("goodsId") Long goodsId, @Param("lotId") Long lotId, @Param("storageId") Integer storageId);
}
