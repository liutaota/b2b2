package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.Favorites;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.FavoritesVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 收藏表 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-29
 */
public interface FavoritesMapper extends BaseMapper<Favorites> {
    /**
     * 收藏的商品/店铺列表
     * @param page
     * @param memberId
     * @return
     */
    IPage<FavoritesVo> getMemberFavoritesList(@Param("page") Page page, @Param("memberId") Long memberId, @Param("goodsId") Long goodsId);
}
