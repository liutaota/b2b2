package com.zsyc.zt.b2b.mapper;

import com.alicp.jetcache.anno.Cached;
import com.zsyc.zt.b2b.entity.ActivityGift;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.ActivityGiftVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 活动赠品 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-28
 */
public interface ActivityGiftMapper extends BaseMapper<ActivityGift> {

    /**
     * 活动商品的赠品信息
     *
     * @param asId
     * @return
     */
    @Cached(name = "getActivityGiftVoList",key="#asId", expire = 1)
    List<ActivityGiftVo> getActivityGiftVoList(@Param("asId") Long asId);

}
