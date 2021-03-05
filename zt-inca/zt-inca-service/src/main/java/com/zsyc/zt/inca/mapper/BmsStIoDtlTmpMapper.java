package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.BmsStIoDtlTmp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.vo.IncaIoDtlVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-25
 */
@Mapper
public interface BmsStIoDtlTmpMapper extends BaseMapper<BmsStIoDtlTmp> {


    @Update("update bms_st_io_dtl_tmp set pickdocid =#{pickdocid} where iodtlid =#{iodtlid}")
    void updateBy(@Param("pickdocid") Long pickdocid, @Param("iodtlid") Long iodtlid);

    @Update("update bms_st_io_dtl_tmp set checkmanid = #{salerId} where iodtlid =#{iodtlid")
    void updateCheckmanid(@Param("salerId") Long salerId, @Param("iodtlid") Long iodtlid);

    List<IncaIoDtlVo> selectBy(@Param("trdtlId") Long trdtlId);

    void updateDtlGoodsQty(@Param("goodsqty") Double goodsqty, @Param("inoutid") Long inoutid);
}
