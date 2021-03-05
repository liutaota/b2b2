package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.BmsStReDtl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-31
 */
@Mapper
public interface BmsStReDtlMapper extends BaseMapper<BmsStReDtl> {

    void updateBy(@Param("approveManId") Long approveManId, @Param("redtlid") Long redtlid);
}
