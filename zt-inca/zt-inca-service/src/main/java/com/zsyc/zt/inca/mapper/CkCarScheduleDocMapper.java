package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.dto.CkCarScheduleDocDto;
import com.zsyc.zt.inca.entity.CkCarScheduleDoc;
import com.zsyc.zt.inca.vo.CkCarScheduleDocVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-12-02
 */
@Mapper
public interface CkCarScheduleDocMapper extends BaseMapper<CkCarScheduleDoc> {

    List<CkCarScheduleDocVo> selectByObj();

    /**
     * 根据车牌号码或者姓名查找信息
     */
    List<CkCarScheduleDocVo> selectByObjOption(@Param("ckCarScheduleDocDto") CkCarScheduleDocDto paramObj);

    /**
     * 根据调度id或者姓名查找信息
     */
    CkCarScheduleDocVo getByScheduleId(@Param("ckCarScheduleDocDto") CkCarScheduleDocDto paramObj);

    /**
     * 车辆ID匹配当天出车调度单信息
     * @param vehicle_id
     * @return
     */
    CkCarScheduleDocVo getByScheduleBill(@Param("vehicleId") Long vehicle_id);
}
