package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.CartVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 购物车 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-29
 */
public interface CartMapper extends BaseMapper<Cart> {
    /**
     * 购物车列表
     * @param page
     * @param cartVo
     * @return
     */
    IPage<CartVo> getMemberCartList(@Param("page") Page page, @Param("cartVo") CartVo cartVo);

    /**
     * 购物车不分页
     * @param cartVo
     * @return
     */
    List<CartVo> getMemberCartList(@Param("cartVo") CartVo cartVo);

    /**
     * 不分页购物车信息
     * @return
     */
    List<CartVo> getCartList();

    /**
     * 客户选中的购物车商品
     * @param memberId
     * @return
     */
    List<CartVo> getPitchOnList(@Param("memberId") Long memberId, @Param("pitchOn") Long pitchOn, @Param("storageId") Long storageId, @Param("goodType") int goodType);


    /**
     * 是否全选购物车商品
     * @param pitchOn
     * @param memberId
     */
    void isPitchOnCart(@Param("pitchOn") Long pitchOn, @Param("memberId") Long memberId);

    /**
     * 删除购物车选中的商品
     * @param memberId
     */
    void delCartPitchOnGoods(@Param("pitchOn") Long pitchOn, @Param("memberId") Long memberId);

    /**
     * 购物车过期活动商品
     * @return
     */
    List<Cart> getCartExpiredActivity();

    /**
     * 没有活动设为null
     * @param id
     */
    void updateCartSetNull(@Param("id") Long id);

    /**
     * 给商品设活动ID
     * @param id
     * @param activityId
     */
    void updateCartActivityId(@Param("id") Long id, @Param("activityId") Long activityId);

    /**
     * 接口下单清空客户对应门店的购物车商品
     *
     * @param memberId
     * @param storeId
     */
    void delMemberStoreCart(@Param("memberId") Long memberId, @Param("storeId") Long storeId);
}
