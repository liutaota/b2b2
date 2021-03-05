package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.Feedback;
import com.zsyc.zt.b2b.vo.FeedbackVo;

public interface FeedbackService {

    /**
     * 产品咨询
     *
     * @param feedback
     */
    void addFeedbackByGoods(FeedbackVo feedback);


    /**
     * 用户反馈/平台建议
     *
     * @param feedback
     */
    void addFeedbackByPlat(FeedbackVo feedback);

    /**
     * 产品咨询/用户反馈列表 type区分
     *
     * @param page
     * @param feedbackVo
     * @return
     */
    IPage<FeedbackVo> getFeedbackVoList(Page page, FeedbackVo feedbackVo);

    /**
     * 删除产品咨询/用户反馈
     *
     * @param id
     */
    void delFeedbackVo(Long id);

    /**
     * 修改产品咨询/用户反馈状态
     *
     * @param id
     * @param goodShow
     */
    void updateStatus(Long id, String goodShow);

    /**
     * 后台回复
     *
     * @param feedback
     */
    void updateFeedbackVo(Feedback feedback);

    /**
     * 产品咨询前台显示
     *
     * @param page
     * @param feedbackVo
     * @return
     */
    IPage<FeedbackVo> getFeedbackGoodsShowList(Page page, FeedbackVo feedbackVo);

    /**
     * 后台客户反馈列表
     * @param page
     * @param feedbackVo
     * @return
     */
    IPage<FeedbackVo> getAdminFeedbackVoList(Page page, FeedbackVo feedbackVo);
}
