package com.zsyc.zt.b2b.mapper;

import com.zsyc.zt.b2b.entity.CartGiftTmp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 购物车商品赠品临时表 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-10-23
 */
public interface CartGiftTmpMapper extends BaseMapper<CartGiftTmp> {


    /**
     * 删除赠品表不存在的赠品
     * @param memberId
     * @param acId
     */
    void delCartGiftTmp(@Param("memberId") Long memberId, @Param("acId") Long acId);

    /**
     * 删除我的赠品临时表记录
     * @param memberId
     */
    void delMyCartGiftTmp(@Param("memberId") Long memberId);
}
