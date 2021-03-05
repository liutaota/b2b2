package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.CacheCustomerGoods;
import com.zsyc.zt.b2b.entity.Floor;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsyc.zt.b2b.vo.FloorVo;

import java.util.List;

/**
 * <p>
 * 楼层 服务类
 * </p>
 *
 * @author MP
 * @since 2020-08-17
 */
public interface FloorService {
    /**
     * 楼层列表
     * @param page
     * @param title
     * @param type
     * @return
     */
    IPage<FloorVo> getFloorList(Page page, String title,String type);

    /**
     * 新增楼层
     * @param floorVo
     * @param userId
     */
    void addFloor(FloorVo floorVo,Long userId);

    /**
     * 编辑楼层
     * @param floorVo
     * @param userId
     */
    void updateFloor(FloorVo floorVo,Long userId);

    /**
     * 组合编辑使用，根据ID查看
     * @param id
     * @return
     */
    FloorVo getFloorById(Long id);

    /**
     * 楼层是否删除
     * @param id
     * @param userId
     */
    void updateFloorIsDel(Long id, Long userId);

    /**
     * 楼层是否启用
     * @param id
     * @param isUse
     * @param userId
     */
    void updateFloorIsUse(Long id, String isUse, Long userId);

    /**
     * 楼层置顶/置底排序
     * @param floorVo
     * @param userId
     */
    void updateFloorSort(FloorVo floorVo, Long userId);

    /**
     * 楼层上下排序
     * @param floorIdPrev
     * @param floorIdNext
     */
    void floorSort(Long floorIdPrev, Long floorIdNext);

    /**
     * 无分页楼层列表
     * @return
     */
    List<Floor> isNotPageFloorList();

    /**
     * 楼层客户集合
     * @param floorVo
     */
    void addFloorCustomerSetList(FloorVo floorVo);
}
