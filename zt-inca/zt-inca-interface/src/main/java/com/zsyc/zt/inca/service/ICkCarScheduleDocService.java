package com.zsyc.zt.inca.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsyc.zt.inca.dto.CkCarScheduleDocDto;
import com.zsyc.zt.inca.entity.CkCarScheduleDoc;
import com.zsyc.zt.inca.vo.CkCarScheduleDocVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author peiqy
 * @since 2020-12-02
 */
public interface ICkCarScheduleDocService extends IService<CkCarScheduleDoc> {


    List<CkCarScheduleDocVo> listByObj();

    /**
     * 根据车牌号码或者姓名查找信息
     */
    List<CkCarScheduleDocVo> selectByObjOption(CkCarScheduleDocDto paramObj);

    /**
     * 根据调度id或者姓名查找信息
     */
    CkCarScheduleDocVo getByScheduleId(CkCarScheduleDocDto paramObj);

    /**
     * 车辆ID匹配当天出车调度单信息
     * @param vehicleId
     * @return
     */
    CkCarScheduleDocVo getByScheduleBill(Long vehicleId);


}
