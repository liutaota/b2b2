package com.zsyc.zt.b2b.mapper;

import com.zsyc.zt.b2b.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.SearchTipsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-29
 */
public interface GoodsMapper extends BaseMapper<Goods> {
    /**
     * 搜索提示
     * @param memberId
     * @param goodspinyin
     * @return
     */
    List<SearchTipsVo> getGoodsInfoListReturnName(@Param("memberId") Long memberId, @Param("goodspinyin") String goodspinyin);

}
