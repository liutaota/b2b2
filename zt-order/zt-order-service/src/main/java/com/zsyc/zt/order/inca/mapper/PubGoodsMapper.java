package com.zsyc.zt.order.inca.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.order.vo.GoodsVo;
import com.zsyc.zt.order.entity.PubGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
public interface PubGoodsMapper extends BaseMapper<PubGoods> {
    /**
     * 查询所有的id
     * @param queryWrapper
     * @return
     */
    List<Long> selectGoodsIdList(@Param(Constants.WRAPPER) QueryWrapper<PubGoods> queryWrapper);

    /**
     * 分页
     * @param page
     * @param customId
     * @return
     */
    IPage<GoodsVo> selectPageList(Page page, @Param("goodsIdList") List<Long> goodsIdList,@Param("goodsName") String goodsName,@Param("factoryName") String factoryName,  @Param("customId")Long customId);

    Double getPrice(@Param("customId")Long customId, @Param("goodsId")Long goodsId, @Param("entryId")Integer entryId);
}
