package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.BmsBatchDef;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-31
 */
public interface BmsBatchDefMapper extends BaseMapper<BmsBatchDef> {

    @Select("select unitprice from bms_batch_def where batchid=#{batchId}")
    Double selectPriceBy(@Param("batchId") Long batchId);

}
