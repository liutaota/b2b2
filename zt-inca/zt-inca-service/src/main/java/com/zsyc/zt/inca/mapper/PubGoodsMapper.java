package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.inca.entity.PubGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.vo.GoodsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-22
 */
public interface PubGoodsMapper extends BaseMapper<PubGoods> {

    List<Long> selectGoodsIdList(@Param(Constants.WRAPPER)QueryWrapper<PubGoods> queryWrapper);

    /**
     * 获取商品信息
     * @param goodsId
     * @return
     */
    GoodsVo getDetail(@Param("goodsId")Long goodsId,@Param("entryId") Integer entryId);


    Double selectPriceBy(@Param("goodsId")Long goodsId, @Param("priceId")Integer priceId,@Param("entryId")Integer entryId);

    /**
     * 查询商品(report模块)
     * @param page
     * @param goodsVo
     * @return
     */
    IPage<GoodsVo> selectGoodsList(Page<GoodsVo> page,@Param("goodsVo") GoodsVo goodsVo);

    /**
     * 过滤非中药药品IDs
     * @param goodsIds
     * @return
     */
    List<Long> selectExistsChineseMedicineIds(@Param("goodsIds") List<Long> goodsIds);

    /**
     * 查询计划新增商品
     * @param goodsIds
     * @return
     */
    List<GoodsVo> selectPlanGoodsList(@Param("goodsIds")List<Long> goodsIds);
}
