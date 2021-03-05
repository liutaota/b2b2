package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.entity.PubCustomer;
import com.zsyc.zt.inca.vo.CustomBusinessScopeVo;
import com.zsyc.zt.inca.vo.CustomLicenseVo;
import com.zsyc.zt.inca.vo.CustomerVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-22
 */
public interface PubCustomerMapper extends BaseMapper<PubCustomer> {

    Integer getLongestOverdraftDay(@Param("customId") Long customId, @Param("entryId")Integer entryId);

    Double getAmountNoSettle(@Param("customId") Long customId, @Param("entryId")Integer entryId);

    Double getAmountSettle(@Param("customId") Long customId, @Param("entryId")Integer entryId);

    Double getAmountWriteOff(@Param("customId") Long customId, @Param("entryId")Integer entryId);

    CustomerVo getCustomInfo(@Param("customId") Long customId);

    CustomerVo getByCustomId(@Param("customId") Long customId, @Param("entryId")Integer entryId);

    List<CustomBusinessScopeVo> selectBusinessScopeByCustomId(@Param("customId")Long customId);

    List<CustomLicenseVo> selectCustomLicenseByCustomId(@Param("customId")Long customId);

    Integer getSettletypeid(Long customId, Integer entryId);

    List<CustomBusinessScopeVo> selectBusinessScopeByCustomIdAndLicenseType(@Param("customId")Long customId, @Param("licenseType")Integer licenseType);

    List<CustomBusinessScopeVo> selectBusinessScopeByCustomIdAndLicenseId(@Param("customId")Long customId,@Param("licenseId") Long licenseId);
}
