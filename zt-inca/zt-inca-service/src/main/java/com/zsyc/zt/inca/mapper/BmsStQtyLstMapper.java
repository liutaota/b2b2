package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.BmsStQtyLst;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.entity.GpcsPlaceReturnDtl;
import com.zsyc.zt.inca.vo.DataGiftVo;
import org.apache.ibatis.annotations.*;

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
public interface BmsStQtyLstMapper extends BaseMapper<BmsStQtyLst> {

    /**
     *
     * @param customId
     * @param goodsId
     * @param lotNo
     * @param goodsType  货品类型   1 药瓶  2 赠品 （accflag = 5）
     * @param storageId
     * @return
     */
    List<BmsStQtyLst> selectStockBy(@Param("customId") Long customId, @Param("goodsId") Long goodsId,@Param("lotNo") String lotNo,  @Param("goodsType")Integer goodsType,@Param("storageId") Integer storageId);

    /**
     *
     * @param customId
     * @param goodsId
     * @param lotNo
     * @param goodsType  货品类型   1 药瓶  2 赠品 （accflag = 5）
     * @param storageId
     * @return
     */
    Integer getStockBy(@Param("customId") Long customId, @Param("goodsId") Long goodsId, @Param("lotNo") String lotNo,@Param("goodsType")Integer goodsType,@Param("storageId") Integer storageId);




    DataGiftVo selectListBy(@Param("phystoreid") Integer phystoreid, @Param("goodsId") Long goodsId, @Param("batchid") Long batchid, @Param("lotid") Long lotid, @Param("posid") Long posid, @Param("goodsdtlId") Long goodsdtlId);
    @Update("update bms_st_qty_lst set goodsqty =#{qty} where rowid =#{rowId}")
    void updateQty(@Param("qty") Double qty, @Param("rowId") String rowId);
    @Delete("delete from bms_st_qty_lst where rowid = #{rowId}")
    void deleteQty(@Param("rowId") String rowId);

    Long selectCanBackStock(@Param("placepointStorageId")Integer placepointStorageId, @Param("goodsDtlId")Long goodsDtlId, @Param("lotId")Long lotId );

    List<BmsStQtyLst> selectPlacepointStQtyList(@Param("placepointStorageId")Integer placepointStorageId, @Param("goodsDtlId")Long goodsDtlId,  @Param("lotId")Long lotId);
}
