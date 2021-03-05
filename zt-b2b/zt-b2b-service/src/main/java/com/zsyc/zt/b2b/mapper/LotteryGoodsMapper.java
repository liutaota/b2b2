package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.LotteryGoods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.LotteryGoodsVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 抽奖商品 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2021-01-18
 */
public interface LotteryGoodsMapper extends BaseMapper<LotteryGoods> {
    /**
     * 抽奖商品列表
     * @param page
     * @param lotteryGoods
     * @return
     */
    IPage<LotteryGoodsVo> getLotteryGoodsList(@Param("page") Page page, @Param("lotteryGoods") LotteryGoodsVo lotteryGoods);
}
