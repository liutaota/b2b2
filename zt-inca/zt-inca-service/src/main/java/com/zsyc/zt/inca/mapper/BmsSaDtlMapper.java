package com.zsyc.zt.inca.mapper;


import com.zsyc.zt.inca.entity.BmsSaDtl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-22
 */
public interface BmsSaDtlMapper extends BaseMapper<BmsSaDtl> {
    /**
     * 更新销售细单的最终回款金额
     *
     * @param sasettledtlid
     */
    void updateTotalRecMoney(@Param("sasettledtlid")Long sasettledtlid,@Param("saleDtlId")Long saleDtlId);


    /**
     * 获取细单
     * @param salesId
     * @return
     */
    List<BmsSaDtl> selectListBy(@Param("salesId") Long salesId,@Param("sourceType")Integer sourceType);

    //根据配送细单id获取销售细单id jie
    Long   getSalesdtlid(@Param("placespplyDtlId") Long placespplyDtlId,@Param("salesId") Long salesId);


    //根据srcErpOrderId和srcErpOrderDtlId获取配退销售细单 jie
    BmsSaDtl getBmsSaDtlByErpOrderDtlId(@Param("srcErpOrderId")Long srcErpOrderId,@Param("srcErpOrderDtlId")Long srcErpOrderDtlId);

    BmsSaDtl selectBySalesId(@Param("srcErpOrderDtlId")Long srcErpOrderDtlId, @Param("psCustom") Boolean psCustom);
}
