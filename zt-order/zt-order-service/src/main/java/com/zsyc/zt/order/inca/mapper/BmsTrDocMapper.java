package com.zsyc.zt.order.inca.mapper;

import com.zsyc.zt.order.entity.BmsTrDoc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
public interface BmsTrDocMapper extends BaseMapper<BmsTrDoc> {

    BmsTrDoc selectListBy(@Param("salesId") Long salesId);
    @Update("update bms_tr_doc set urgentflag=0 ,tocompanyid=#{placepointId} where trid = #{trId}")
    void updateFlag(@Param("placepointId") Long placepointId,@Param("trId") Long trId);
}
