package com.zsyc.zt.b2b.service;

import com.zsyc.zt.b2b.entity.HelpType;
import com.zsyc.zt.b2b.vo.HelpTypeVo;
import com.zsyc.zt.b2b.vo.HelpVo;

import java.util.List;

public interface HelpTypeService {
    /**
     * 帮助类型列表
     *
     * @return
     */
    List<HelpType> getHelpTypeList();

    /**
     * 新增帮助类型
     *
     * @param helpType
     * @param userId
     */
    void addHelpType(HelpType helpType, Long userId);

    /**
     * 编辑帮助类型
     *
     * @param helpType
     * @param userId
     */
    void updateHelpType(HelpType helpType, Long userId);

    /**
     * 是否显示
     *
     * @param id
     * @param helpShow
     * @param userId
     */
    void updateHelpTypeShow(Long id, String helpShow, Long userId);

    /**
     * 是否删除
     *
     * @param id
     * @param userId
     */
    void updateHelpTypeIsDel(Long id, Long userId);

    /**
     * 帮助类型-指南
     *
     * @return
     */
    List<HelpTypeVo> getHelpTypeVoList();

    /**
     * 帮助类型置顶/置底排序
     * @param helpTypeVo
     * @param userId
     */
    void updateHelpTypeSort(HelpTypeVo helpTypeVo, Long userId);

    /**
     * 帮助类型上下排序
     * @param helpTypeIdPrev
     * @param helpTypeIdNext
     */
    void helpTypeSort(Long helpTypeIdPrev, Long helpTypeIdNext);
}
