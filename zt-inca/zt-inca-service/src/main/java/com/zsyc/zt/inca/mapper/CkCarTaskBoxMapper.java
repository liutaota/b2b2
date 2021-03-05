package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.dto.CkCarTaskBoxDto;
import com.zsyc.zt.inca.entity.CkCarTaskBox;
import com.zsyc.zt.inca.vo.CkCarTaskBoxVo;
import com.zsyc.zt.inca.vo.others.CarTaskBmsDocVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-12-07
 */
@Mapper
public interface CkCarTaskBoxMapper extends BaseMapper<CkCarTaskBox> {

    /**
     * 关联查询细单下所有箱号记录
     * @param taskDtlId
     * @return
     */
    List<CkCarTaskBoxVo> getCkCarTaskBoxByTaskDtlId(@Param("taskDtlId") Long taskDtlId);

    /**
     * 批量插入货品箱信息
     * @param ckCarTaskBoxList
     * @return
     */
    int insertTaskBox(@Param("list") List<CkCarTaskBoxVo> ckCarTaskBoxList);

    /**
     * 查询是否录入箱号信息
     * @param ckCarTaskBoxDto
     * @return
     */
    int getBoxCount(@Param("ckCarTaskBoxDto") CkCarTaskBoxDto ckCarTaskBoxDto);

    /**
     * 查询细单下所有销售单号
     * @param taskDtlId
     * @return
     */
    List<String> querySalesListByDtlId(@Param("taskDtlId")Long taskDtlId);

    /**
     * 根据细单ID删除箱号信息
     * @param taskDtlId
     * @return
     */
    int deleteTaskBoxByTaskDtlId(@Param("taskDtlId") Long taskDtlId);

    /**
     * 删除：根据箱号信息ID，删除箱号信息
     * @param taskDtlId
     * @param taskBoxId
     * @return
     */
    int deleteTaskBoxByTaskBoxId(@Param("taskDtlId") Long taskDtlId, @Param("taskBoxId")Long taskBoxId);

    /**
     * 查询出车任务中指定客户订单对应的收款凭证信息
     * @param customId
     * @param salesIdList
     * @return
     */
    List<CarTaskBmsDocVo> getSalesAndReceiptList(@Param("customId") Long customId, @Param("salesIdList") List<Long> salesIdList);

    /**
     * 查询任务下未录入箱号集合
     * @param tackId
     * @param customId
     * @return
     */
    List<Long> selectNotInputPackId(@Param("tackId") Long tackId, @Param("customId") Long customId);
}
