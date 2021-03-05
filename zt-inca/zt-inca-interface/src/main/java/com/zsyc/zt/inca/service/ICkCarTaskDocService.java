package com.zsyc.zt.inca.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsyc.zt.inca.dto.CkCarTaskDtlDto;
import com.zsyc.zt.inca.entity.CkCarTaskDoc;
import com.zsyc.zt.inca.vo.CkCarTaskDocVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author peiqy
 * @since 2020-12-05
 */
public interface ICkCarTaskDocService extends IService<CkCarTaskDoc> {

    /**
     * 查询出车任务信息
     * @param scheduleId
     * @return
     */
    CkCarTaskDocVo getCarTask(Long scheduleId);

    /**
     * 上传任务数据
     * @param ckCarTaskDtlDto
     * @return
     */
    Map<String, String> saveCarTask(List<CkCarTaskDtlDto> ckCarTaskDtlDto);

    /**
     * 更新细单数量: 增加1
     * @param taskId
     * @return
     */
    int updateDtlLines(Long taskId);

    /**
     * 更新细单数量: 减少1
     * @param taskId
     * @return
     */
    int reduceDtlLines(Long taskId);

    /**
     * 查询任务状态: 数量
     * @param taskId
     * @return
     */
    Integer getUseStatusById(Long taskId);

}
