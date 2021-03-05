package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.BmsStIoDtl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-22
 */
@Mapper
public interface BmsStIoDtlMapper extends BaseMapper<BmsStIoDtl> {

    BmsStIoDtl save(@Param("inoutId") Long inoutId);

    Long getBySaleDtlId(@Param("salesdtlid") Long srcErpOrderDtlId);
}
