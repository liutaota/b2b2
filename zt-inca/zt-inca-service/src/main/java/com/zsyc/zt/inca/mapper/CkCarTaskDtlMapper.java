package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.dto.CkCarTaskDtlDto;
import com.zsyc.zt.inca.entity.CkCarTaskDtl;
import com.zsyc.zt.inca.vo.CkCarTaskDtlVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-12-05
 */
@Mapper
public interface CkCarTaskDtlMapper extends BaseMapper<CkCarTaskDtl> {
    /**
     * 根据出车调度单id查询出车任务细单
     * @param paramObj
     * @return
     */
    List<CkCarTaskDtlVo> selectByObj(@Param("ckCarTaskDtlDto") CkCarTaskDtlDto paramObj);

    /**
     * 根据出车任务细单id查找信息
     * @param paramObj
     * @return
     */
    CkCarTaskDtlVo getByTaskDtlId(@Param("ckCarTaskDtlDto") CkCarTaskDtlDto paramObj);

    /**
     * 根据任务总单ID查询所有细单信息
     * @param taskId
     * @return
     */
    List<CkCarTaskDtlVo> getCarTaskDtlList(@Param("taskId") Long taskId);

    /**
     * 插入细单信息
     * @param ckCarTaskDtl
     * @return
     */
    int insertTaskDtl(@Param("ckCarTaskDtlVo") CkCarTaskDtlVo ckCarTaskDtl);

    /**
     * 查询客户ID对应的任务细单
     * @param customId
     * @return
     */
    CkCarTaskDtlVo getCustomByPackId(@Param("taskId")Long taskId, @Param("customId")Long customId);

    /**
     * 查询细单是否存在
     * @param ckCarTaskDtlDto
     * @return
     */
    int getDtlCount(@Param("ckCarTaskDtlDto") CkCarTaskDtlDto ckCarTaskDtlDto);

    /**
     * 更新任务细单状态
     * @param taskId
     * @return
     */
    int updateDtlStatus(@Param("taskId")Long taskId);

    /**
     * 删除任务细单
     * @param taskDtlId
     * @return
     */
    int deleteTaskDtlById(@Param("taskDtlId") Long taskDtlId);

    /**
     * 查询细单状态
     * @param taskDtlId
     * @return
     */
    int queryUseStatus(@Param("taskDtlId") Long taskDtlId);

    /**
     * 查询当前客户所属任务ID
     * @param currentCustomId
     * @return
     */
    Long getTaskByCustomId(@Param("currentCustomId") Long currentCustomId);

    /**
     * 查询当前车辆需要运输的所有客户信息
     * @param scheduleId
     * @return
     */
    List<CkCarTaskDtlVo> getCustomList(@Param("scheduleId") Long scheduleId);

    /**
     * 搜索客户信息
     * @param scheduleId
     * @param searchCustomId
     * @param searchStr
     * @return
     */
    List<CkCarTaskDtlVo> searchCustom(@Param("scheduleId") Long scheduleId,
                                      @Param("searchCustomId") Long searchCustomId,
                                      @Param("searchStr") String searchStr);

    /**
     * 根据客户ID 查询任务细单信息
     * @param customId
     * @return
     */
    CkCarTaskDtlVo getDtlByCustomId(@Param("taskId")Long taskId, @Param("customId")Long customId);
}
