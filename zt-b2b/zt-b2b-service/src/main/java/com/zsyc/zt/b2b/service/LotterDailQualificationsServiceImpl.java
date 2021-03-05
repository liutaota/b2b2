package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.mapper.*;
import com.zsyc.zt.b2b.vo.GoodsInfoVo;
import com.zsyc.zt.b2b.vo.LotterDailQualificationsVo;
import com.zsyc.zt.b2b.vo.LotteryDialDetailVo;
import com.zsyc.zt.b2b.vo.LotteryGoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@Transactional
public class LotterDailQualificationsServiceImpl implements LotterDailQualificationsService {
    @Autowired
    private LotterDailQualificationsMapper lotterDailQualificationsMapper;
    @Autowired
    private LotteryLogMapper lotteryLogMapper;

    @Autowired
    private LotteryDialDetailMapper lotteryDialDetailMapper;
    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private LotteryGoodsMapper lotteryGoodsMapper;

    @Override
    public IPage<LotterDailQualificationsVo> getMyLotterDailQualificationsList(Page page, LotterDailQualificationsVo lotterDailQualifications) {
        return this.lotterDailQualificationsMapper.getMyLotterDailQualificationsList(page, lotterDailQualifications);
    }

    @Override
    public IPage<LotteryDialDetailVo> getMyLotteryDialDetailVoList(Page page, LotteryDialDetailVo lotteryDialDetailVo) {
        return this.lotteryDialDetailMapper.getMyLotteryDialDetailVoList(page, lotteryDialDetailVo);
    }

    @Override
    public LotteryDialDetail addLotteryDialDetailVo(LotteryDialDetail lotteryDialDetailVo) {
        List<LotterDailQualifications> lotterDailQualificationsList = this.lotterDailQualificationsMapper.getLotterDailQualificationsList(lotteryDialDetailVo.getMemberId());

        AssertExt.isTrue(lotterDailQualificationsList.size() != 0, "暂无抽奖次数");

        List<LotteryGoodsVo> goodsInfoVoList = this.getGoodsInfoVoByLotteryList();

        AssertExt.isTrue(goodsInfoVoList.size() != 0, "很遗憾,您未中奖");
        List<LotteryGoodsVo> goodsInfoVoListNew =new ArrayList<>();
        for (LotteryGoodsVo goodsInfoVo : goodsInfoVoList) {
            if (goodsInfoVo.getLotNum() > 0) {
                goodsInfoVoListNew.add(goodsInfoVo);
            }
        }

        LotteryDialDetail lotteryDialDetailDB = new LotteryDialDetail();
        LotteryLog lotteryLog = new LotteryLog();

        Member memberDB = this.memberMapper.selectById(lotteryDialDetailVo.getMemberId());
        Random r = new Random();
        Integer random = r.nextInt(goodsInfoVoListNew.size() + 1);
        if (random >= goodsInfoVoListNew.size()) {
            lotteryLog.setRemark("客户未中奖");


            lotteryDialDetailDB = new LotteryDialDetail();
            lotteryDialDetailDB.setRateType("0");
            lotteryDialDetailDB.setMemberId(lotteryDialDetailVo.getMemberId());
            lotteryDialDetailDB.setErpGoodsId(0L);
            lotteryDialDetailDB.setPrizeInfo("");
            lotteryDialDetailDB.setPrizeNum(1);
            lotteryDialDetailDB.setLotId(lotterDailQualificationsList.get(0).getLotId());
            lotteryDialDetailDB.setPrizeState(LotteryDialDetail.EPrizeState.TO_PRIZE.val());
            lotteryDialDetailDB.setMemberName(memberDB.getUserName());
            lotteryDialDetailDB.setCreateTime(LocalDateTime.now());
            this.lotteryDialDetailMapper.insert(lotteryDialDetailDB);

        } else {
            LotteryGoodsVo goodsInfoVo = goodsInfoVoListNew.get(random);

            lotteryDialDetailDB = this.lotteryDialDetailMapper.selectOne(new QueryWrapper<LotteryDialDetail>()
                    .eq("member_id", lotteryDialDetailVo.getMemberId()).eq("PRIZE_STATE", LotteryDialDetail.EPrizeState.UN_PRIZE.val())
                    .eq("ERP_GOODS_ID", goodsInfoVo.getErpGoodsId()));
            if (null != lotteryDialDetailDB) {
                lotteryDialDetailDB.setPrizeNum(lotteryDialDetailDB.getPrizeNum() + 1);
                this.lotteryDialDetailMapper.updateById(lotteryDialDetailDB);
            } else {

                lotteryDialDetailDB = new LotteryDialDetail();
                lotteryDialDetailDB.setRateType("2");
                lotteryDialDetailDB.setMemberId(lotteryDialDetailVo.getMemberId());
                lotteryDialDetailDB.setErpGoodsId(goodsInfoVo.getErpGoodsId());
                lotteryDialDetailDB.setPrizeInfo(goodsInfoVo.getGoodsname());
                lotteryDialDetailDB.setPrizeNum(1);
                lotteryDialDetailDB.setLotId(lotterDailQualificationsList.get(0).getLotId());
                lotteryDialDetailDB.setPrizeState(LotteryDialDetail.EPrizeState.UN_PRIZE.val());
                lotteryDialDetailDB.setMemberName(memberDB.getUserName());
                lotteryDialDetailDB.setCreateTime(LocalDateTime.now());
                this.lotteryDialDetailMapper.insert(lotteryDialDetailDB);

            }
            lotteryLog.setRemark("客户抽奖");
            goodsInfoVo.setLotNum(goodsInfoVo.getLotNum() - 1);
            this.lotteryGoodsMapper.updateById(goodsInfoVo);

        }
        lotterDailQualificationsList.get(0).setLotNum(lotterDailQualificationsList.get(0).getLotNum() - 1);
        this.lotterDailQualificationsMapper.updateById(lotterDailQualificationsList.get(0));

        lotteryLog.setLotId(lotterDailQualificationsList.get(0).getLotId());
        lotteryLog.setMemberId(lotterDailQualificationsList.get(0).getMemberId());

        lotteryLog.setCreateTime(LocalDateTime.now());
        this.lotteryLogMapper.insert(lotteryLog);

        return lotteryDialDetailDB;
    }

    @Override
    public List<LotteryGoodsVo> getGoodsInfoVoByLotteryList() {
        List<LotteryGoodsVo> goodsInfoVoList = this.lotteryDialDetailMapper.getGoodsInfoVoByLotteryList();
       List<LotteryGoodsVo> integerList=new ArrayList<>();
        for (LotteryGoodsVo goodsInfoVo : goodsInfoVoList) {
            if (goodsInfoVo.getLotNum() == 0) {
                integerList.add(goodsInfoVo);
            }
        }
        if (integerList.size()==goodsInfoVoList.size()) {
            goodsInfoVoList = new ArrayList<>();
        }
       /* if (goodsInfoVoList.size() < 5) {
            goodsInfoVoList = new ArrayList<>();
        }*/

        return goodsInfoVoList;
    }

    @Override
    public List<LotteryDialDetailVo> getMyGoodsInfoVoByLotteryList(Long memberId) {
        return this.lotteryDialDetailMapper.getMyGoodsInfoVoByLotteryList(memberId);
    }

    @Override
    public Integer getMyLotterCount(Long memberId, Long orderId) {
        AssertExt.hasId(orderId, "订单id为空");
        return this.lotterDailQualificationsMapper.getMyLotterCount(memberId, orderId);
    }

    @Override
    public void addLotteryGoods(LotteryGoods lotteryGoods) {
        AssertExt.hasId(lotteryGoods.getErpGoodsId(),"商品id为空");
        LotteryGoods lotteryGoodsDB = this.lotteryGoodsMapper.selectOne(new QueryWrapper<LotteryGoods>().eq("erp_goods_id", lotteryGoods.getErpGoodsId()));
        if(null!=lotteryGoodsDB){
            lotteryGoods.setId(lotteryGoodsDB.getId());
            this.lotteryGoodsMapper.updateById(lotteryGoods);
        }else {
            lotteryGoods.setCreateTime(LocalDateTime.now());
            this.lotteryGoodsMapper.insert(lotteryGoods);
        }

    }

    @Override
    public void updateLotteryGoods(LotteryGoods lotteryGoods) {
        AssertExt.hasId(lotteryGoods.getId(),"id为空");
        this.lotteryGoodsMapper.updateById(lotteryGoods);
    }

    @Override
    public IPage<LotteryGoodsVo> getLotteryGoodsList(Page page, LotteryGoodsVo lotteryGoods) {
        return this.lotteryGoodsMapper.getLotteryGoodsList(page,lotteryGoods);
    }
}
