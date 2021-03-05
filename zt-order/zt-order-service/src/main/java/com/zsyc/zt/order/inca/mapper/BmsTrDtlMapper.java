package com.zsyc.zt.order.inca.mapper;

import com.zsyc.zt.order.entity.BmsTrDtl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
public interface BmsTrDtlMapper extends BaseMapper<BmsTrDtl> {
    @Update("update bms_tr_dtl set preparestatus = 1,allowmanid =#{signformman} ,allowtime= sysdate where trid =#{trid}")
    void updateByTrid(@Param("signformman") Long signformman, @Param("trid") Long trid);

    @Update("update bms_tr_dtl set preparestatus=3 where trdtlid=#{trdtlid} and not exists (select 1  from bms_st_io_doc_tmp  where bms_st_io_doc_tmp.usestatus in (0, 1)    and bms_st_io_doc_tmp.trdtlid = bms_tr_dtl.trdtlid)")
    void updateBy(@Param("trdtlid") Long trdtlid);


    @Update("update bms_tr_pick_doc set waveno = #{waveno} where pickdocid in (select b.pickdocid from bms_st_io_doc_tmp a, bms_st_io_dtl_tmp b where a.trdtlid =#{trdtlid} and a.inoutid = b.inoutid)")
    void updateWaveno(String waveno, Long trdtlid);
}
