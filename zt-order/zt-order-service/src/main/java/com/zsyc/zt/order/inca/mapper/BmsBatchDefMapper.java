package com.zsyc.zt.order.inca.mapper;

import com.zsyc.zt.order.entity.BmsBatchDef;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
public interface BmsBatchDefMapper extends BaseMapper<BmsBatchDef> {

    @Select("select unitprice from bms_batch_def where batchid=#{batchId}")
    Double selectPriceBy(@Param("batchId") String batchId);
}
