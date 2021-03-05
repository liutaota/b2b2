package com.zsyc.zt.order.inca.mapper;

import com.zsyc.zt.order.entity.BmsSaConDtl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
public interface BmsSaConDtlMapper extends BaseMapper<BmsSaConDtl> {

    List<BmsSaConDtl> selectListBy(@Param("salesId") Long salesId);
}
