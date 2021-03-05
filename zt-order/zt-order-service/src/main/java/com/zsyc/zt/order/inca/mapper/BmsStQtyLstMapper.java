package com.zsyc.zt.order.inca.mapper;

import com.zsyc.zt.order.entity.BmsStQtyLst;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.order.entity.GpcsPlacereturndtl;
import com.zsyc.zt.order.inca.vo.DataGiftVo;
import org.apache.ibatis.annotations.Delete;
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


    List<GpcsPlacereturndtl> selectQtyBy(@Param("goodsId") Long goodsId, @Param("lotId") String lotId, @Param("batchId") String batchId, @Param("storageId") String storageId);


    DataGiftVo selectListBy(@Param("phystoreid") Integer phystoreid, @Param("goodsId") Long goodsId, @Param("batchid") Long batchid, @Param("lotid") Long lotid, @Param("posid") Long posid, @Param("goodsdtlId") Long goodsdtlId);
    @Update("update bms_st_qty_lst set goodsqty =#{qty} where rowid =#{rowId}")
    void updateQty(@Param("qty") Double qty, @Param("rowId") String rowId);
    @Delete("delete from bms_st_qty_lst where rowid = #{rowId}")
    void deleteQty(@Param("rowId") String rowId);

}
