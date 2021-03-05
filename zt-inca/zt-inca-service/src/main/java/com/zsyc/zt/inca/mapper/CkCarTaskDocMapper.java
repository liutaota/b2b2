package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.entity.CkCarTaskDoc;
import com.zsyc.zt.inca.vo.CkCarTaskDocVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-12-05
 */
@Mapper
public interface CkCarTaskDocMapper extends BaseMapper<CkCarTaskDoc> {

    /**
     * 根据出车调度ID查询任务信息
     * @param scheduleId
     * @return
     */
    CkCarTaskDocVo getTaskByScheduleId(@Param("scheduleId") Long scheduleId);

    /**
     * 更新任务状态
     * @param taskId
     * @return
     */
    int updateTaskStatusById(@Param("taskId") Long taskId);

    /**
     * 查询任务是否存在
     * @param taskId
     * @return
     */
    int queryTaskCount(@Param("taskId") Long taskId);

    /**
     * 根据ID修改任务状态
     * @param taskId
     * @return
     */
    int updateTaskStatus(@Param("taskId") Long taskId,
                         @Param("confirmManId") Long confirmManId,
                         @Param("confirmDate") LocalDate confirmDate);

    /**
     * 更新细单数量: 增加1
     * @param taskId
     * @return
     */
    int updateDtlLines(@Param("taskId")Long taskId);

    /**
     * 更新细单数量: 减少1
     * @param taskId
     * @return
     */
    int updateReduceDtlLines(@Param("taskId") Long taskId);

    /**
     * 查询任务状态
     * @param taskId
     * @return
     */
    Integer getUseStatusById(Long taskId);
}
