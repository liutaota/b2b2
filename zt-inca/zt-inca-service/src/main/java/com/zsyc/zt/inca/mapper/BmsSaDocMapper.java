package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.BmsSaDoc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.vo.BmsSaByFaPiaoVo;
import com.zsyc.zt.inca.vo.IncaIoDtlVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-22
 */
public interface BmsSaDocMapper extends BaseMapper<BmsSaDoc> {

    List<Long> selectNotCallbackInList(@Param("zxBhFlag") Integer zxBhFlag, @Param("orderIdList")List<Long> orderIdList);

    List<Long> selectExistsInList(@Param("zxBhFlag") Integer zxBhFlag,  @Param("orderIdList")List<Long> orderIdList);


    void updateCallbackStatus(@Param("orderIds") List<String> orderIds, @Param("b2bWriteBackFlag") Integer b2bWriteBackFlag);

    @Select("select count(1) from bms_sa_doc a  where salesid =#{salesId} and a.B2B_ORDER_ID=#{b2bOrderId}")
    Integer getCountBy(@Param("salesId") Long salesId, @Param("b2bOrderId") Long b2bOrderId);

    @Update("update bms_sa_doc set usestatus = 1,confirmanid =#{signformman},confirmdate =sysdate where salesid = #{sourceid}")
    void updateBySaleid(@Param("signformman") Long signformman, @Param("sourceid") Long sourceid);

    @Select("select t.invmethod from pub_customer  t where t.customid =#{customId}")
    Integer[] queryByCustomid(@Param("customId") Long customId);

    @Update("update bms_sa_doc t  set t.planinvdate = sysdate  where t.salesid =#{salesId}")
    void updateBy(Long salesId);

    void updateSummary(@Param("salesId")Long salesid, @Param("entryId")Integer entryId);

    //根据配送主单id查找销售主单 jie
    @Select("select salesid from bms_sa_doc where placesupplyid = #{placesupplyId} and customid = #{customId}")
    Long  getBmsSaDocByPlacesupplyId(@Param("placesupplyId") Long placesupplyId,@Param("customId") Long customId);

    @Update("update bms_sa_doc set usestatus = 1,approveflag = 1,confirmanid =#{salerId},confirmdate =sysdate where salesid = #{salesId}")
    void updateUsetatus(@Param("salerId") Long salerId, @Param("salesId") Long salesId);

    //根据报货平台订单id获取erp发货单id集合
    List<Long> erpOrderIdListByOrderId(@Param("b2bOrderId") Long b2bOrderId);

    //根据报货平台订单id获取erp发货单
    BmsSaByFaPiaoVo getBmsSaDocListByB2bOrderId(@Param("b2bOrderId") Long b2bOrderId);

    //根据报货平台订单id获取erp发货单集合
    List<BmsSaByFaPiaoVo> getBmsSaDocListByB2bOrderIdList(@Param("b2bOrderIdList") List<Long> b2bOrderIdList);

    Double getNotRecMoney(@Param("srcErpOrderId")Long srcErpOrderId,@Param("sourceType") Integer sourceType);

    Long getSalesIdByPlacesupplyId(@Param("srcErpOrderId")Long srcErpOrderId,@Param("customId")Long customId);

    Long getSalesIdByPlacesupplyDtlId(@Param("srcErpOrderDtlId")Long srcErpOrderDtlId);

    void writeBackPayOrderAndFlowNo(@Param("isPsCustom") Boolean isPsCustom,@Param("orderIds")List<Long> orderIds, @Param("payFlowNo")String payFlowNo, @Param("payOrderNo")String payOrderNo);
}
