package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.PubStorer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-01
 */
public interface PubStorerMapper extends BaseMapper<PubStorer> {

    Integer getUseWms(@Param("storerid") Long storerid);
}
