package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.AdvPosition;
import com.zsyc.zt.b2b.entity.Floor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.FloorVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 楼层 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-17
 */
public interface FloorMapper extends BaseMapper<Floor> {
    /**
     * 楼层列表
     * @param page
     * @param title
     * @param type
     * @return
     */
    IPage<FloorVo> getFloorList(@Param("page") Page page, @Param("title") String title,@Param("type")String type);

    /**
     * 楼层列表
     * @return
     */
    List<FloorVo> getFloorListAll(@Param("floorVo") FloorVo floorVo);

    /**
     * 客户是否存在集合里
     * @param memberList
     * @param customerId
     * @return
     */
    Integer isExistMemberSet(@Param("memberList") List<Long> memberList, @Param("customerId") Long customerId);

    /**
     * 获取一个客户可见楼层
     * 1. 楼层全部可见
     * 2. 楼层部分可见，并且customerId在已选的客户集合里
     * @param id
     * @param customerId
     * @return
     */
    Floor getAvailableFloor(@Param("id") Long id, @Param("customerId") Long customerId);
}
