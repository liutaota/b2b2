package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.BmsStDef;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-01
 */
public interface BmsStDefMapper extends BaseMapper<BmsStDef> {

    Integer getUseWms(@Param("storageId") Integer storageId, @Param("entryId")Integer entryId);
}
