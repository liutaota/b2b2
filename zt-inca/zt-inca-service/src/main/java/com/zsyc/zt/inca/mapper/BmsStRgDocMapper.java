package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.entity.BmsStRgDoc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-24
 */
@Mapper
public interface BmsStRgDocMapper  extends BaseMapper<BmsStRgDoc> {
    void updateByRgid(@Param("approvemanId") Long approvemanId,@Param("rgid") Long rgid);

    void updateBy(@Param("rgid") Long rgid);
}
