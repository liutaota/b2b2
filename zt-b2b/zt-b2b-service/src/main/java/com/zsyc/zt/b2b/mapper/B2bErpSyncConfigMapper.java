package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.B2bErpSyncConfig;
import com.zsyc.zt.b2b.entity.ErpB2bOrderRecDoc;
import com.zsyc.zt.b2b.vo.ErpB2bOrderRecDocVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * B2b回写订单号码
 * </p>
 *
 * @author peiqy
 * @since 2021-01-07
 */
public interface B2bErpSyncConfigMapper extends BaseMapper<B2bErpSyncConfig> {

    @Select("select * from b2b_erp_sync_config where var_name=#{varName}")
    B2bErpSyncConfig getByVarName(@Param("varName") String varName);
}
