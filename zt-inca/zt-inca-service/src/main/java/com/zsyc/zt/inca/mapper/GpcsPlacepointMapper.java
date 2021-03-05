package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.GpcsPlacepoint;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;

/**
 * 门店信息
 *
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-25
 */
public interface GpcsPlacepointMapper extends BaseMapper<GpcsPlacepoint> {
    /**
     * 更新门店商品上次配送日期信息
     *
     * @param placepointid
     * @param goodsId
     */
    @Update(" update gpcs_last_supply_sales_date " +
            "    set lastpsupplydate = sysdate " +
            "  where placepointid = #{placepointid}" +
            "    and goodsid = #{goodsId}")
    void updateGpcsLastSupplySalesDate(@Param("placepointid")Long placepointid, @Param("goodsId")Long goodsId);
    @Update("update pub_entry_goods a " +
            "   set sys_modifydate = sysdate," +
//            "       a.firstsudate  = #{acceptdate} " +
            "       a.firstsudate  = to_date(#{acceptdate,jdbcType=DATE}, 'yyyy-mm-dd hh24:mi:ss')" +
            " where a.entryid = #{entryId,jdbcType=NUMERIC}" +
            "   and a.goodsid = #{goodsId,jdbcType=NUMERIC}" +
            "   and a.firstsudate is null")
    void updatePubEntryGoods(@Param("acceptdate")LocalDateTime acceptdate,@Param("entryId")Integer entryId, @Param("goodsId")Long goodsId);


    /**
     * 获取门店使用的保管账
     * @param placepointId
     * @return
     */
    @Select("select t.storageid from gpcs_placepoint t where t.placepointid =#{placepointId}")
    Integer getPlacepointStorageIdByCustomId(@Param("placepointId") Long placepointId);
}
