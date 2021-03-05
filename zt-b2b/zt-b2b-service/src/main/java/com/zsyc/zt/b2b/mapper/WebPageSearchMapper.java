package com.zsyc.zt.b2b.mapper;

import com.zsyc.zt.b2b.entity.WebPageSearch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.entity.PubGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 专区搜索商品分类 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-30
 */
public interface WebPageSearchMapper extends BaseMapper<WebPageSearch> {
    /**
     * erp商品
     *
     * @param goodsid
     * @return
     */
    PubGoods getPubGoodsInfo(@Param("goodsid") Long goodsid);

    /**
     * erp集合里的商品
     *
     * @param goodsSetId
     * @return
     */
    List<PubGoods> getPubGoodsInfoBySet(@Param("goodsSetId") Long goodsSetId);
}
