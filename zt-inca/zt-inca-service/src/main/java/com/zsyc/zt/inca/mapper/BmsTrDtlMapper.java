package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zsyc.zt.inca.entity.BmsTrDtl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BmsTrDtlMapper extends BaseMapper<BmsTrDtl> {
    @Update("update bms_tr_dtl set preparestatus = 2,configmanid =#{signformman},configtime = sysdate,allowmanid =#{signformman} ,allowtime= sysdate where trid =#{trid}")
    void updateByTrid(@Param("signformman") Long signformman, @Param("trid") Long trid);

    @Update("update bms_tr_dtl set preparestatus=3 where trdtlid=#{trdtlid} and not exists (select 1  from bms_st_io_doc_tmp  where bms_st_io_doc_tmp.usestatus in (0, 1)    and bms_st_io_doc_tmp.trdtlid = bms_tr_dtl.trdtlid)")
    void updateBy(@Param("trdtlid") Long trdtlid);


    @Update("update bms_tr_dtl set waveno = #{waveno} where trdtlid =#{trdtlid}")
    void updateWaveno(@Param("waveno") String waveno, @Param("trdtlid") Long trdtlid);
}
