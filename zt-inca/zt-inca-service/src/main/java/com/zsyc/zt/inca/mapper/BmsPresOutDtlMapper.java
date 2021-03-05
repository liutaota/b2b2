package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.BmsPresOutDtl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-25
 */
public interface BmsPresOutDtlMapper extends BaseMapper<BmsPresOutDtl> {
    /*更新记账标志*/
    @Update("update bms_pres_out_dtl set recst = 1,recstdate = sysdate where presoutdtlid = #{presoutdtlId}")
    void updateBy(@Param("presoutdtlId") Long presoutdtlId);
}
