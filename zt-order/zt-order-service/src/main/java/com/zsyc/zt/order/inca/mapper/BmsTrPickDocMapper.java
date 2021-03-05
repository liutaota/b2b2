package com.zsyc.zt.order.inca.mapper;

import com.zsyc.zt.order.entity.BmsTrPickDoc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
public interface BmsTrPickDocMapper extends BaseMapper<BmsTrPickDoc> {

    void updateBy(@Param("pickdocid") Long pickdocid);

    @Select("select * from pub_serialno where serialnoid=0")
    String getSerialno();

    @Update("update bms_tr_pick_doc set waveno = #{waveno} where pickdocid in (select b.pickdocid from bms_st_io_doc_tmp a, bms_st_io_dtl_tmp b where a.trdtlid =#{trdtlid} and a.inoutid = b.inoutid)")
    void updateWaveno(String waveno, Long trdtlid);
}
