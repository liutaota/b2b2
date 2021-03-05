package com.zsyc.zt.order.inca.mapper;

import com.zsyc.zt.order.entity.BmsStIoDocTmp;
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
public interface BmsStIoDocTmpMapper extends BaseMapper<BmsStIoDocTmp> {

    /*销售更新临时出库总单*/
    @Update("update bms_st_io_doc_tmp set trdtlid = #{trdtlId},preparestatus=1 where sourcetable = 2 and sourceid in (select salesdtlid from bms_sa_dtl where salesid =#{salesId})  and exists (select 1 from bms_st_def a where a.storageid = bms_st_io_doc_tmp.storageid and a.phystoreid = 7368)")
    void updateBy(@Param("trdtlId") Long trdtlId, @Param("salesId") Long salesId);
}
