package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.Factory;
import com.zsyc.zt.b2b.vo.FactoryVo;
import com.zsyc.zt.b2b.vo.GoodsInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 厂家 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-29
 */
public interface FactoryMapper extends BaseMapper<Factory> {

    /**
     * 后台厂家列表
     * @param page
     * @param factory
     * @return
     */
    IPage<FactoryVo> getFactoryList(@Param("page") Page page, @Param("factory") FactoryVo factory);

    /**
     * 厂家列表
     * @param goodsInfoVo
     * @return
     */
    List<FactoryVo> getFactoryVoList(@Param("goodsInfoVo") GoodsInfoVo goodsInfoVo);


    /**
     * 专区厂家分类列表
     *
     * @param goodsInfoVo
     * @return
     */
    List<FactoryVo> getWebPageFactoryVoList(@Param("goodsInfoVo") GoodsInfoVo goodsInfoVo);

    /**
     * 热门品牌
     * @return
     */
    List<FactoryVo> getHoldFactoryVoList();

    /**
     * 近效期厂家分类列表
     *
     * @param goodsInfoVo
     * @return
     */
    List<FactoryVo> getValidityFactoryVoList(@Param("goodsInfoVo") GoodsInfoVo goodsInfoVo);
}
