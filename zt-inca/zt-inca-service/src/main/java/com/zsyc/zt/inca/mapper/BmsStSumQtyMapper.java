package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.BmsStSumQty;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.mapstruct.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-21
 */
@Mapper
public interface BmsStSumQtyMapper extends BaseMapper<BmsStSumQty> {

    /**
     * 更新商品的保管账发生额表记录
     * @param num
     * @param usemm
     * @param storageid
     * @param goodsid
     */
    @Update("update bms_st_sum_qty set otherinqty = nvl(otherinqty,0) + #{num} where usemm=#{usemm} and storageid=#{storageid} and goodsid=#{goodsid}")
    void updateOtherinqty(@Param("num")Double num, @Param("usemm")Long usemm, @Param("storageid")Long storageid, @Param("goodsid")Long goodsid);

    /**
     * 删除临时收货信息表记录
     * @param iodtlid
     */
    @Delete("delete from bms_st_receive_goods_dtl_tmp a where a.rggoodsdtlid = #{iodtlid}")
    void deleteReceiveGoodsDtlTmp(@Param("iodtlid") Long iodtlid);
}
