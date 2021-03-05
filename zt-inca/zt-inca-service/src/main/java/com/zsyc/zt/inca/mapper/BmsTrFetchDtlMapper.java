package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.BmsTrFetchDtl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-31
 */
public interface BmsTrFetchDtlMapper extends BaseMapper<BmsTrFetchDtl> {

    BmsTrFetchDtl selectDtlBy(@Param("saleId") Long saleId, @Param("lotId") Long lotId, @Param("batchId") Long batchId,@Param("customId") Long customId);

    List<BmsTrFetchDtl> selectBy(@Param("fetchId") Long fetchId);

    void confirmByFetchId(@Param("fetchid") Long fetchid);

    BmsTrFetchDtl selectBySalesDtlId(Long srcErpOrderDtlId);
}
