package com.zsyc.zt.b2b.service;

import com.zsyc.zt.b2b.entity.Help;
import com.zsyc.zt.b2b.vo.FloorVo;
import com.zsyc.zt.b2b.vo.HelpVo;

import java.util.List;

public interface HelpService {
    /**
     * 帮助指南列表
     * @return
     */
    List<Help> getHelpList();

    /**
     * 新增帮助指南
     * @param help
     * @param userId
     */
    void addHelp(Help help,Long userId);

    /**
     * 编辑帮助指南
     * @param help
     * @param userId
     */
    void updateHelp(Help help,Long userId);

    /**
     * 是否显示
     * @param id
     * @param helpShow
     * @param userId
     */
    void updateHelpShow(Long id, String helpShow,Long userId);

    /**
     * 是否删除
     * @param id
     * @param userId
     */
    void updateHelpIsDel(Long id,Long userId);

    /**
     * 帮助指南置顶/置底排序
     * @param helpVo
     * @param userId
     */
    void updateHelpSort(HelpVo helpVo, Long userId);

    /**
     * 帮助指南上下排序
     * @param helpIdPrev
     * @param helpIdNext
     */
    void helpSort(Long helpIdPrev, Long helpIdNext);

    /**
     * 帮助指南详情
     * @param id
     * @return
     */
    Help getHelpByIdInfo(Long id);
}
