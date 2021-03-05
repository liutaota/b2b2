package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.GspCompanyManagerage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.vo.CustomManageScopeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-24
 */
public interface GspCompanyManagerageMapper extends BaseMapper<GspCompanyManagerage> {


    List<CustomManageScopeVo> selectBy(@Param("customId") Long customId, @Param("entryId")Integer entryId,@Param("bussinessScope") Long bussinessScope,@Param("useStatus") Integer useStatus);

}
