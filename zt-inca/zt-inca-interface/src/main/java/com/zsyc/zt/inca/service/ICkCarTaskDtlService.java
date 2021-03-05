package com.zsyc.zt.inca.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsyc.zt.inca.dto.CkCarTaskDtlDto;
import com.zsyc.zt.inca.entity.CkCarTaskDtl;
import com.zsyc.zt.inca.vo.CkCarTaskDtlVo;

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
public interface ICkCarTaskDtlService extends IService<CkCarTaskDtl> {
    /**
     * 根据出车调度单id查询出车任务细单
     * @param paramObj
     * @return
     */
    List<CkCarTaskDtlVo> selectByObj(CkCarTaskDtlDto paramObj);

    /**
     * 根据出车任务细单id查找信息
     * @param paramObj
     * @return
     */
    CkCarTaskDtlVo getByTaskDtlId(CkCarTaskDtlDto paramObj);

    /**
     * 查询细单
     * @param taskId
     * @return
     */
    List<CkCarTaskDtlVo> selectByTaskId(Long taskId);

    /**
     *
     * @param taskId
     * @return
     */
    List<CkCarTaskDtlVo> getCarTaskDtlList(Long taskId);

    /**
     * 查询箱号对应的任务总单、细单是否存在
     * @param packId
     * @return
     */
    CkCarTaskDtlVo getCustomByPackId(Long taskId ,Long packId);

    /**
     * 查询
     * @param ckCarTaskDtlDto
     * @return
     */
    int getDtlCount(CkCarTaskDtlDto ckCarTaskDtlDto);

    /**
     * 更新细单状态
     * @param taskId
     * @return
     */
    int updateDtlStatus(Long taskId);

    /**
     * 删除任务细单及所有箱号记录
     * @param taskDtlId
     * @return
     */
    Map<String, Integer> deleteTaskDtlById(Long taskId, Long taskDtlId);

    /**
     * 查询当前细单状态
     * @param taskDtlId
     * @return
     */
    int queryUseStatus(Long taskDtlId);

    /**
     * 根据当前客户ID查询，任务ID
     * @param currentCustomId
     * @return
     */
    Long getTaskByCustomId(Long currentCustomId);

    /**
     * 查询当前车辆需要运输的所有客户信息
     * @param scheduleId
     * @return
     */
    List<CkCarTaskDtlVo> getCustomList(Long scheduleId);

    /**
     * 搜索当前任务下细单司机信息
     * @param scheduleId
     * @param searchStr
     * @return
     */
    List<CkCarTaskDtlVo> searchCustom(Long scheduleId, String searchStr);

    /**
     * 根据客户ID，查询任务细单信息
     * @param currentCustomId
     * @return
     */
    CkCarTaskDtlVo getDtlByCustomId(Long taskId,Long currentCustomId);
}
