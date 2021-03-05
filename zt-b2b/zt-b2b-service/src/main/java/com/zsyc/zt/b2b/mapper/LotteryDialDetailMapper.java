package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.LotteryDialDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.GoodsInfoVo;
import com.zsyc.zt.b2b.vo.LotteryDialDetailVo;
import com.zsyc.zt.b2b.vo.LotteryGoodsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 转盘抽奖详情 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-12-30
 */
public interface LotteryDialDetailMapper extends BaseMapper<LotteryDialDetail> {
    /**
     * 我的抽奖记录
     * @param page
     * @param lotteryDialDetailVo
     * @return
     */
    IPage<LotteryDialDetailVo> getMyLotteryDialDetailVoList(@Param("page") Page page, @Param("lotteryDialDetailVo") LotteryDialDetailVo lotteryDialDetailVo);

    /**
     * 抽奖商品
     *
     * @return
     */
    List<LotteryGoodsVo> getGoodsInfoVoByLotteryList();

    /**
     * 我的奖品
     * @param memberId
     * @return
     */
    List<LotteryDialDetailVo> getMyGoodsInfoVoByLotteryList(@Param("memberId") Long memberId);
}
