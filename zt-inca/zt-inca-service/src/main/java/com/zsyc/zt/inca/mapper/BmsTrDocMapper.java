package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zsyc.zt.inca.entity.BmsTrDoc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface BmsTrDocMapper extends BaseMapper<BmsTrDoc> {

    BmsTrDoc selectListBy(@Param("salesId") Long salesId);
    @Update("update bms_tr_doc set urgentflag=0 ,tocompanyid=#{placepointId} where trid = #{trId}")
    void updateFlag(@Param("placepointId") Long placepointId,@Param("trId") Long trId);
    @Update(" update bms_tr_doc set urgentflag= #{urgentFlag},tranpriority = #{tranpriority},tocompanyid=#{customId} where trid = trId ")
    void updateBy(@Param("urgentFlag") Integer urgentFlag, @Param("tranpriority") Integer tranpriority, @Param("customId") long customId,@Param("trId") Long trId );

}
