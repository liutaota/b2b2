package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.entity.BmsPresOutDoc;
import com.zsyc.zt.inca.entity.BmsStIoDtl;

import com.zsyc.zt.inca.vo.IncaAddressVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;



@Mapper
public interface B2bGiftDocComfirmMapper extends BaseMapper<BmsPresOutDoc> {

    @Update("update bms_pres_out_doc set usestatus = 1,confirmmanid=#{inputmanid},confirmdate = sysdate,comefrom=2 where presoutid =#{presoutid}")
    void updateUsestatus(@Param("inputmanid") Long inputmanid, @Param("presoutid") Long presoutid);

    IncaAddressVo queryAddress(@Param("presoutid") Long presoutid);


    String queryCustomid(@Param("presoutid") Long presoutid);

    @Update(" update bms_tr_doc set tocompanyid = #{tocompanyid} where trid =#{Trid}")
    void updateCompanyid(@Param("tocompanyid") String tocompanyid, @Param("Trid") Long Trid);

    Integer getStorerid(@Param("presoutid") Long presoutid);

    @Update("update bms_st_io_doc_tmp set trdtlid =#{trdtlid},preparestatus=1 where sourcetable = 18 and sourceid in (select presoutdtlid from bms_pres_out_dtl where presoutid =#{presoutid})  and exists (select 1 from bms_st_def a where a.storageid = bms_st_io_doc_tmp.storageid and a.phystoreid = #{phystoreid})")
    void updateBmstmp(@Param("trdtlid") Long trdtlid, @Param("presoutid") Long presoutid, @Param("phystoreid") Integer phystoreid);

    BmsPresOutDoc selectBy(@Param("orderNo") String orderNo, @Param("entryId") Integer entryId, @Param("srcStatus") Integer srcStatus);
    @Select("select * from pub_employee where employeeid=#{inputmanid}")
    String getInputName(@Param("inputmanid") Long inputmanid);
    /*取逻辑月*/
    @Select("select usemm from pub_settle_account_v where to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd')>startdate  and to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd')<=enddate and entryid=#{entryId}")
    Long getUsemm(@Param("entryId") Integer entryId);
    @Update("update bms_st_sum_qty set PRESENTOUTQTY = nvl(PRESENTOUTQTY,0) + #{goodsQty} where usemm=#{usemm} and storageid=#{phystoreid} and goodsid=#{goodsId}")
    void updateQty(@Param("goodsQty") Double goodsQty, @Param("usemm") Long usemm, @Param("phystoreid") Integer phystoreid, Long goodsId);
    /*查询临时出库商品明细*/
    @Select(" select b.goodsstatusid,b.goodsdtlid,b.batchid,b.lotid,b.posid,a.inoutid,b.iodtlid from bms_st_io_doc_tmp a, bms_st_io_dtl_tmp b where a.inoutid=b.inoutid and a.sourceid=#{presoutdtlId}  and b.goodsdtlid=#{goodsdtlId}")
    BmsStIoDtl selectListBy(@Param("presoutdtlId") Long presoutdtlId,@Param("goodsdtlId") Long goodsdtlId);
    /*更新复核人*/
    @Update("update bms_st_io_dtl_tmp set checkmanid =#{inputmanid} where iodtlid = #{iodtlId}")
    void UpdateCheckManId(@Param("inputmanid") Long inputmanid,@Param("iodtlId") Long iodtlId );
}
