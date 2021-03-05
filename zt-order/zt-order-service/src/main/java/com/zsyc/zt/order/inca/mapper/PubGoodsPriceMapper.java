package com.zsyc.zt.order.inca.mapper;

import com.zsyc.zt.order.entity.PubGoodsPrice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
public interface PubGoodsPriceMapper extends BaseMapper<PubGoodsPrice> {

    @Select("select PRICE from ngpcs_all_price_v where wholeresaleflag=2 and GOODSID=#{goodsId} and PLACEPOINTID=#{customId}")
    Double getResalePriceBy(@Param("goodsId") Long goodsId, @Param("customId") Long customId);

}
