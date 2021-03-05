package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.entity.BmsStIoDocTmp;
import com.zsyc.zt.inca.entity.GpcsPlaceReturn;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author
 * @since 2020-07-30
 */
@Mapper
public interface GpcsPlaceReturnMapper extends BaseMapper<GpcsPlaceReturn> {
    List<String> selectOrderPSNotCallback(@Param("b2bShopFlag") int b2bShopFlag, @Param("approveStatusDoc") int approveStatusDoc, @Param("b2bNotWriteBack") int b2bNotWriteBack);

    void updatePSCallbackStatus(@Param("b2bOrderIds") List<String> b2bOrderIds, @Param("b2bNotWriteBack") int b2bNotWriteBack);

    GpcsPlaceReturn getByB2bOrderId(@Param("orderId") Long orderId);
}
