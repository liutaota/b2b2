package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.Feedback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.FeedbackVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户反馈 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-04
 */
public interface FeedbackMapper extends BaseMapper<Feedback> {

    /**
     * 产品咨询/用户反馈列表 type区分
     *
     * @param page
     * @param feedbackVo
     * @return
     */
    IPage<FeedbackVo> getFeedbackVoList(@Param("page") Page page, @Param("feedbackVo") FeedbackVo feedbackVo);


    /**
     * 产品咨询前台显示
     *
     * @param page
     * @param feedbackVo
     * @return
     */
    IPage<FeedbackVo> getFeedbackGoodsShowList(@Param("page") Page page, @Param("feedbackVo") FeedbackVo feedbackVo);

    /**
     * 后台客户反馈列表
     * @param page
     * @param feedbackVo
     * @return
     */
    IPage<FeedbackVo> getAdminFeedbackVoList(Page page, FeedbackVo feedbackVo);
}
