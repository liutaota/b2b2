package com.zsyc.zt.b2b.mapper;

import com.zsyc.zt.b2b.entity.RecDtl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.RecDtlVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 收款细单 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-11-06
 */
public interface RecDtlMapper extends BaseMapper<RecDtl> {
    /**
     * 收款细单
     */
    List<RecDtlVo> getRecDtlList(@Param("recDocId") Long recDocId);
}
