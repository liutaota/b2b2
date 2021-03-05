package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.Feedback;
import com.zsyc.zt.b2b.entity.Member;
import com.zsyc.zt.b2b.mapper.FeedbackMapper;
import com.zsyc.zt.b2b.mapper.MemberMapper;
import com.zsyc.zt.b2b.vo.FeedbackVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public void addFeedbackByGoods(FeedbackVo feedback) {
        AssertExt.notNull(feedback.getMemberPhone(), "手机号为空");
        //AssertExt.notNull(feedback.getCode(), "验证码为空");
        AssertExt.notNull(feedback.getContent(), "内容为空");
        AssertExt.hasId(feedback.getErpGoodsId(), "商品id为空");
        feedback.setType(Feedback.EType.GOODS.val());
        feedback.setGoodsShow(Feedback.EGoodsShow.OFF.val());
        feedback.setIsDel(Constant.IS_DEL.NO);
        feedback.setCreateTime(LocalDateTime.now());
        this.feedbackMapper.insert(feedback);
    }

    @Override
    public void addFeedbackByPlat(FeedbackVo feedback) {
        AssertExt.notNull(feedback.getContent(), "内容为空");
        feedback.setCreateTime(LocalDateTime.now());
        feedback.setType(Feedback.EType.PLATFROM.val());
        feedback.setGoodsShow(Feedback.EGoodsShow.OFF.val());
        feedback.setIsDel(Constant.IS_DEL.NO);
        feedback.setStatus(Feedback.EStatus.UNTREATED.val());
        this.feedbackMapper.insert(feedback);
    }

    @Override
    public IPage<FeedbackVo> getFeedbackVoList(Page page, FeedbackVo feedbackVo) {
        return this.feedbackMapper.getFeedbackVoList(page, feedbackVo);
    }

    @Override
    public void delFeedbackVo(Long id) {
        AssertExt.hasId(id, "id为空");
        Feedback feedbackDB = this.feedbackMapper.selectById(id);
        AssertExt.notNull(feedbackDB, "无效id[%s]", id);
        feedbackDB.setIsDel(Constant.IS_DEL.YES);
        this.feedbackMapper.updateById(feedbackDB);
    }

    @Override
    public void updateStatus(Long id, String goodShow) {
        AssertExt.hasId(id, "id为空");
        AssertExt.notNull(goodShow, "goodShow为空");
        Feedback feedbackDB = this.feedbackMapper.selectById(id);
        AssertExt.notNull(feedbackDB, "无效id[%s]", id);
        feedbackDB.setGoodsShow(goodShow);
        this.feedbackMapper.updateById(feedbackDB);
    }

    @Override
    public void updateFeedbackVo(Feedback feedback) {
        AssertExt.hasId(feedback.getId(), "id为空");
        AssertExt.notNull(feedback.getReplyContent(), "回复内容为空");
        Feedback feedbackDB = this.feedbackMapper.selectById(feedback.getId());
        AssertExt.notNull(feedbackDB, "无效id[%s]", feedback.getId());
        feedback.setStatus(Feedback.EStatus.REPLIED.val());
        feedback.setReplyTime(LocalDateTime.now());
        this.feedbackMapper.updateById(feedback);
    }

    @Override
    public IPage<FeedbackVo> getFeedbackGoodsShowList(Page page, FeedbackVo feedbackVo) {
        AssertExt.hasId(feedbackVo.getErpGoodsId(), "商品id为空");
        return this.feedbackMapper.getFeedbackGoodsShowList(page, feedbackVo);
    }

    @Override
    public IPage<FeedbackVo> getAdminFeedbackVoList(Page page, FeedbackVo feedbackVo) {
        AssertExt.notNull(page,"无效页码");
        return this.feedbackMapper.getAdminFeedbackVoList(page,feedbackVo);
    }
}
