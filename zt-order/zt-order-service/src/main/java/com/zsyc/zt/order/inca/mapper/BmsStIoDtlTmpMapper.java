package com.zsyc.zt.order.inca.mapper;

import com.zsyc.zt.order.vo.IncaIoDtlVo;
import com.zsyc.zt.order.entity.BmsStIoDtlTmp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
public interface BmsStIoDtlTmpMapper extends BaseMapper<BmsStIoDtlTmp> {

    @Update("update bms_st_io_dtl_tmp set pickdocid =#{pickdocid} where iodtlid =#{iodtlid}")
    void updateBy(@Param("pickdocid") Long pickdocid, @Param("iodtlid") Long iodtlid);

    @Update("update bms_st_io_dtl_tmp set checkmanid = salerId where iodtlid =#{iodtlid}")
    void updateCheckmanid(@Param("salerId") Long salerId, @Param("iodtlid") Long iodtlid);

    List<IncaIoDtlVo> selectBy(@Param("trdtlId") Long trdtlId);

    void updateDtlGoodsQty(@Param("goodsqty") Double goodsqty, @Param("inoutid") Long inoutid);
}
