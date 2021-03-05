package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.LotterDailQualifications;
import com.zsyc.zt.b2b.entity.LotteryDialDetail;
import com.zsyc.zt.b2b.entity.LotteryGoods;
import com.zsyc.zt.b2b.vo.GoodsInfoVo;
import com.zsyc.zt.b2b.vo.LotterDailQualificationsVo;
import com.zsyc.zt.b2b.vo.LotteryDialDetailVo;
import com.zsyc.zt.b2b.vo.LotteryGoodsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LotterDailQualificationsService {
    /**
     * 我的抽奖次数
     *
     * @param page
     * @param lotterDailQualifications
     * @return
     */
    IPage<LotterDailQualificationsVo> getMyLotterDailQualificationsList(Page page, LotterDailQualificationsVo lotterDailQualifications);


    /**
     * 我的抽奖记录
     *
     * @param page
     * @param lotteryDialDetailVo
     * @return
     */
    IPage<LotteryDialDetailVo> getMyLotteryDialDetailVoList(Page page, LotteryDialDetailVo lotteryDialDetailVo);

    /**
     * 抽奖
     *
     * @param lotteryDialDetailVo
     */
    LotteryDialDetail addLotteryDialDetailVo(LotteryDialDetail lotteryDialDetailVo);

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
    List<LotteryDialDetailVo> getMyGoodsInfoVoByLotteryList(Long memberId);

    /**
     * 我的抽奖次数--订单
     * @param memberId
     * @return
     */
    Integer getMyLotterCount( Long memberId,Long orderId);

    /**
     * 添加抽奖商品
     * @param lotteryGoods
     */
    void addLotteryGoods(LotteryGoods lotteryGoods);

    /**
     * 修改抽奖商品
     * @param lotteryGoods
     */
    void updateLotteryGoods(LotteryGoods lotteryGoods);

    /**
     * 抽奖商品列表
     * @param page
     * @param lotteryGoods
     * @return
     */
    IPage<LotteryGoodsVo> getLotteryGoodsList(Page page, LotteryGoodsVo lotteryGoods);
}
