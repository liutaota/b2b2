package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.BmsStIoDocTmp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-25
 */
@Mapper
public interface BmsStIoDocTmpMapper extends BaseMapper<BmsStIoDocTmp> {
    /*配送更新临时出库总单*/
    @Update("update bms_st_io_doc_tmp set trdtlid =#{trdtlId} ,preparestatus=1 where placetable=1 and placedtlid in(select a.placesupplydtlstid from gpcs_placesupplydtl_st a, gpcs_placesupplydtl b   where a.placesupplydtlid = b.placesupplydtlid and b.placesupplyid = #{placesupplyId})  and exists (select 1 from bms_st_def a where a.storageid = bms_st_io_doc_tmp.storageid and a.phystoreid =7368)")
    void updateByTrId(@Param("trdtlId") Long trdtlId, @Param("placesupplyId") Long placesupplyId);

    /*销售更新临时出库总单*/
    @Update("update bms_st_io_doc_tmp set trdtlid = #{trdtlId},preparestatus=1 where sourcetable = 2 and sourceid in (select salesdtlid from bms_sa_dtl where salesid =#{salesId})  and exists (select 1 from bms_st_def a where a.storageid = bms_st_io_doc_tmp.storageid and a.phystoreid = 7368)")
    void updateBy(@Param("trdtlId") Long trdtlId, @Param("salesId") Long salesId);

    @Update("update bms_st_io_doc_tmp a set a.preparestatus=1 where a.trdtlid in(select b.trdtlid from bms_tr_dtl b where b.trid=#{trId})")
    void updateStatus(@Param("trId") Long trId);


    Long getSourceid(@Param("placereturndtlid") Long placereturndtlid);

    void updateOutqty(@Param("goodsqty") Double goodsqty, @Param("inoutid") Long inoutid);

    Integer getUsemm();
}
