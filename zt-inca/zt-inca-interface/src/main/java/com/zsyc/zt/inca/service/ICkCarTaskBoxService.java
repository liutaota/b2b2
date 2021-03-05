package com.zsyc.zt.inca.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsyc.zt.inca.dto.CkCarTaskBoxDto;
import com.zsyc.zt.inca.entity.CkCarTaskBox;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author peiqy
 * @since 2020-12-07
 */
public interface ICkCarTaskBoxService extends IService<CkCarTaskBox> {

    /**
     * 查询箱号是否录入
     * @param boxDto
     * @return
     */
    int getBoxCount(CkCarTaskBoxDto boxDto);

    /**
     * 录入客户信息、箱号、订单信息业务
     * @param taskId
     * @param packId
     * @param currentCustomId
     * @return
     */
    Map<String, Object> getCustomAndPackId(Long taskId, String packId, Long currentCustomId);

    /**
     * 删除细单下所有箱号信息
     * @param taskDtlId
     * @return
     */
    int deleteTaskBoxByTaskDtlId(Long taskDtlId);

    /**
     * 删除：根据想好信息ID删除箱号信息
     * @param taskDtlId
     * @param taskBoxId
     * @return
     */
    Map<String, Integer> deleteTaskBoxByTaskBoxId(Long taskDtlId, Long taskBoxId);

    /**
     * 查询运输任务客户所有订单、金额、箱号
     * @param customId
     * @return
     */
    Map<String, Object> getSalesAndReceiptList(Long taskDtlId, Long customId);

    /**
     * 查询任务下未录入箱号
     * @param tackId
     * @param customId
     * @return
     */
    List<Long> selectNotInputPackId(Long tackId, Long customId);
}
