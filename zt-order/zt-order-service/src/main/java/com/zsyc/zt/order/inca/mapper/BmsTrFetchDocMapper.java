package com.zsyc.zt.order.inca.mapper;

import com.zsyc.zt.order.entity.BmsTrFetchDoc;
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
public interface BmsTrFetchDocMapper extends BaseMapper<BmsTrFetchDoc> {


    BmsTrFetchDoc getByCustomId(@Param("customId") Long customId);

    @Select("select a.tranposid from bms_tr_pos_def a where a.usestatus=1 and a.Defaultflag=1 and a.companyid=#{customId}")
    String getTranposIdBy(@Param("customId") Long customId);
}
