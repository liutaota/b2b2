package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.AdvPosition;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.AdvPositionVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 广告位 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-18
 */
public interface AdvPositionMapper extends BaseMapper<AdvPosition> {
    /**
     * 广告位列表
     *
     * @param page
     * @param advName
     * @param apDisplay
     * @return
     */
    IPage<AdvPositionVo> getAdvPosition(@Param("page") Page page, @Param("advName") String advName, @Param("apDisplay") String apDisplay);

    /**
     * 广告位列表不分页
     *
     * @return
     */
    List<AdvPositionVo> getAdvPositionAll(@Param("advPositionVo") AdvPositionVo advPositionVo);

    /**
     * 客户是否存在集合里
     * @param memberList
     * @param customerId
     * @return
     */
    Integer isExistMemberSet(@Param("memberList") List<Long> memberList, @Param("customerId") Long customerId);

    /**
     * 获取一个客户可见广告位
     * 1. 广告位全部可见
     * 2. 广告位部分可见，并且customerId在已选的客户集合里
     * @param id
     * @param customerId
     * @return
     */
    AdvPosition getAvailableAdvPosition(@Param("id") Long id, @Param("customerId") Long customerId);
}
