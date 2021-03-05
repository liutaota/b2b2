package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.WebPage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 专区页 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-21
 */
public interface WebPageMapper extends BaseMapper<WebPage> {
    /**
     * 后台专区列表
     *
     * @param page
     * @param title
     * @return
     */
    IPage<WebPageVo> getWebPageList(@Param("page") Page page, @Param("title") String title);

    /**
     * 专区列表不分页
     *
     * @param webPageVo
     * @return
     */
    List<WebPageVo> getWebPageAll(@Param("webPageVo") WebPageVo webPageVo);

    /**
     * 客户专区列表
     *
     * @param memberId
     * @return
     */
    List<WebPageVo> getWebPageLists(@Param("memberId") Long memberId);

    /**
     * 专区搜索
     *
     * @return
     */
    IPage<GoodsInfoVo> getWebPageSearchList(@Param("page") Page page, @Param("goodsInfoVo") GoodsInfoVo goodsInfoVo);

    /**
     * 专区搜索-->搜索提示
     *
     * @param webPageId
     * @param goodspinyin
     * @return
     */
    List<SearchTipsVo> getWebPageSearchListReturnName(@Param("webPageId") Long webPageId, @Param("goodspinyin") String goodspinyin);

    /**
     * 客户可见商品
     *
     * @param customerId
     * @param goodsId
     * @return
     */
    IPage<FloorInfoVo.Goods> getGoodsByCustomerId(@Param("page") Page page, @Param("customerId") Long customerId, @Param("goodsId") List<Long> goodsId, @Param("dayOfWeek") int dayOfWeek);


    /**
     * 客户专区列表-id
     *
     * @param memberId
     * @return
     */
    WebPageVo getWebPageListById(@Param("memberId") Long memberId, @Param("id") Long id);
}
