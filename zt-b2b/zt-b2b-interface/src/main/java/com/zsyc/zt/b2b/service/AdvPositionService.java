package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.AdvPosition;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsyc.zt.b2b.vo.AdvPositionVo;
import com.zsyc.zt.b2b.vo.ImageInfoVo;

import java.util.List;

/**
 * <p>
 * 广告位 服务类
 * </p>
 *
 * @author MP
 * @since 2020-08-18
 */
public interface AdvPositionService extends IService<AdvPosition> {
    /**
     * 广告位列表
     * @param page
     * @param advName
     * @param apDisplay
     * @return
     */
    IPage<AdvPositionVo> getAdvPosition(Page page, String advName,String apDisplay);

    /**
     * 新增广告位
     * @param advPositionVo
     * @param userId
     */
    void addAdvPosition(AdvPositionVo advPositionVo, Long userId);

    /**
     * 广告位是否启用
     * @param id
     * @param isUse
     * @param userId
     */
    void updateAdvPositionIsUse(Long id, String isUse, Long userId);

    /**
     * 广告位是否删除
     * @param id
     * @param userId
     */
    void updateAdvPositionIsDel(Long id,Long userId);

    /**
     * 编辑广告位
     * @param advPositionVo
     * @param userId
     */
    void updateAdvPosition(AdvPositionVo advPositionVo, Long userId);

    /**
     * 根据ID获取广告位，组合编辑使用
     * @param id
     * @return
     */
    AdvPositionVo getAdvPositionById(Long id);

    /**
     * 广告位列表不分页
     * @return
     */
    List<AdvPositionVo> getAdvPositionAll(AdvPositionVo advPositionVo);

    /**
     * 无分页广告列表
     * @return
     */
    List<AdvPosition> isNotPageAdvPositionList();

    /**
     * 广告客户集合
     * @param advPositionVo
     */
    void addAdvPositionCustomerSet(AdvPositionVo advPositionVo);
}
