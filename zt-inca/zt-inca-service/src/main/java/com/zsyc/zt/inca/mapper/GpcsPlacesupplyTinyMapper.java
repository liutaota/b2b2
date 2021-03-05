package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.GpcsPlacesupplyTiny;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.vo.GpcsPlaceSupplyAcceptInfoVo;
import com.zsyc.zt.inca.vo.GpcsPlacesupplyAcceptTinyVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-19
 */
public interface GpcsPlacesupplyTinyMapper extends BaseMapper<GpcsPlacesupplyTiny> {
    /**
     * 更新配送明细的收货数量
     * @param customId
     * @param num
     * @param placesupplytinyid
     */
    @Update("update gpcs_placesupply_tiny" +
            "   set recieveflag   = 1," +
            "       acceptmanid   = #{customId}," +
            "       acceptdate    = sysdate," +
            "       recheckstatus = 0," +
            "       recieveqty    = #{num}" +
            " where placesupplytinyid = #{placesupplytinyid}")
    void updateTinyRecieveQty(@Param("customId")Long customId, @Param("num")Double num, @Param("placesupplytinyid")Long placesupplytinyid);

    /**
     * 获取总店名称
     * @param placecenterid
     * @return
     */
    @Select("select placecentername from gpcs_placecenter where placecenterid = #{placecenterid}")
    List<String> getPlacecentername(@Param("placecenterid")Long placecenterid);

    /**
     * 获取resaleprice零售价格
     * @param goodsid
     * @param customId
     * @return
     */
    @Select("select a.price from ngpcs_all_price a, pub_price_type b " +
            " where a.priceid = b.priceid and b.wholeresaleflag = 2 " +
            " and a.goodsid = #{goodsid} and a.placepointid = #{customId}")
    List<Double> getResaleprice(@Param("goodsid") Long goodsid, @Param("customId") Long customId);

    /**
     * 获取配送明细信息VO
     * @param placesupplytinyid
     * @return
     */
    @Select("select a.placepointid," +
            "        b.goodsid," +
            "        d.goodsdtlid," +
            "        d.goodsqty," +
            "        c.unitprice," +
            "        d.lotid," +
            "        d.batchid," +
            "        f.goodsunit," +
            "        e.storageid," +
            "        a.placecenterid," +
            "        e.presstockflag," +
            "        nvl(f.accflag, 0) accflag," +
            "        decode(f.accflag, 5, 1, 0) presendflag," +
            "        nvl(e.updatepos, 0) updatepos," +
            "        b.placesupplydtlid," +
            "        a.memo," +
            "        c.postplaceprice," +
            "        c.entryid placeentryid," +
            "        c.placesupplydtlstid," +
            "        c.placepriceid" +
            "   from gpcs_placesupply       a," +
            "        gpcs_placesupplydtl    b," +
            "        gpcs_placesupplydtl_st c," +
            "        gpcs_placesupply_tiny  d," +
            "        gpcs_placepoint        e," +
            "        pub_goods              f" +
            "  where a.placesupplyid = b.placesupplyid" +
            "    and b.placesupplydtlid = c.placesupplydtlid" +
            "    and c.placesupplydtlstid = d.placesupplydtlstid" +
            "    and a.placepointid = e.placepointid" +
            "    and b.goodsid = f.goodsid" +
            "    and d.placesupplytinyid = #{placesupplytinyid}")
    List<GpcsPlacesupplyAcceptTinyVo> getGpcsPlacesupplyAcceptTinyVo(@Param("placesupplytinyid") Long placesupplytinyid);

    /**
     * 获取配送收货信息Vo
     * @param placesupplytinyid
     * @return
     */
    @Select("select TRANSPORTID," +
            "       TRANMETHOD," +
            "       COLDEQUIP," +
            "       STARTPLACE," +
            "       accqty," +
            "       unqualifiedqty," +
            "       nvl(accqty, 0) + nvl(unqualifiedqty, 0) shqty," +
            "       startdate," +
            "       reachdate," +
            "       reachdate - startdate days," +
            "       starttemperature," +
            "       reachtemperature," +
            "       isreach," +
            "       accmemo," +
            "       pending" +
            "  from gpcs_place_supply_accept_info" +
            " where placesupplytinyid = #{placesupplytinyid}")
    List<GpcsPlaceSupplyAcceptInfoVo> getGpcsPlaceSupplyAcceptInfoVo(@Param("placesupplytinyid")Long placesupplytinyid);

    /**
     * 验证是否存在相应的配送收货单
     * @param placesupplytinyid
     * @return
     */
    @Select("select acceptdtlid from gpcs_place_accept_dtl where oldsupplytinyid=#{placesupplytinyid}")
    List<Object> getGpcsPlaceAcceptDtlList(@Param("placesupplytinyid")Long placesupplytinyid);

    /**
     * 验证货架是否为空
     * @param customId
     * @param goodsid
     * @return
     */
    List<Long> getGoodstopos(@Param("customId")Long customId, @Param("goodsid")Long goodsid);

    /**
     * 配送明细确认收货
     * @param receiptManId
     * @param placesupplytinyid
     */
    @Update("update gpcs_placesupply_tiny set checkstatus = 1,checkmanid = #{receiptManId}," +
            "checkdate = sysdate where placesupplytinyid = #{placesupplytinyid}")
    void updateCheck(@Param("receiptManId") Long receiptManId,@Param("placesupplytinyid") Long placesupplytinyid);
}
