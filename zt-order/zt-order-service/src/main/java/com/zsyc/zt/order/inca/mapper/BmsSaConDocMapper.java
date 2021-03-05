package com.zsyc.zt.order.inca.mapper;

import com.zsyc.zt.order.entity.BmsSaConDoc;
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
public interface BmsSaConDocMapper extends BaseMapper<BmsSaConDoc> {

    @Update("update bms_sa_con_doc set usestatus = 3,approveflag = 1 where conid =#{conId}")
    void updateBy(@Param("conId") Long conId);
}
