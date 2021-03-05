package com.zsyc.zt.order.inca.mapper;

import com.zsyc.zt.order.vo.CustomManageScopeVo;
import com.zsyc.zt.order.entity.GspCompanyManagerage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-08-19
 */
public interface GspCompanyManagerageMapper extends BaseMapper<GspCompanyManagerage> {
    List<CustomManageScopeVo> selectBy(@Param("customId") Long customId, @Param("entryId")Integer entryId, @Param("bussinessScope") Long bussinessScope, @Param("useStatus") Integer useStatus);
}
