package com.zsyc.zt.order.inca.mapper;

import com.zsyc.zt.order.vo.OrderDetailVo;
import com.zsyc.zt.order.entity.BmsSaDoc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
public interface BmsSaDocMapper extends BaseMapper<BmsSaDoc> {

    List<String> selectNotCallbackBy(@Param("zxBhFlag") Integer zxBhFlag, @Param("b2bNotWriteBack")Integer b2bNotWriteBack, @Param("useStatus")Integer useStatus);


    void updateCallbackStatus(@Param("orderIds") List<String> orderIds, @Param("b2bWriteBackFlag")Integer b2bWriteBackFlag);

    @Select("select dtl_lines from bms_sa_doc a  where salesid =#{saleId} and a.B2B_ORDER_ID=#{orderId}")
    int selectCountBy(@Param("saleId") Long saleId, @Param("orderId") String orderId);


    @Update("update bms_sa_doc set usestatus = 1,confirmanid =#{signformman},confirmdate =sysdate where salesid = #{sourceid}")
    void updateBySaleid(@Param("signformman") Long signformman, @Param("sourceid") Long sourceid);

    @Select("select t.invmethod from pub_customer  t where t.customid =#{customId}")
    Integer[] queryByCustomid(@Param("customId") Long customId);


    @Update("update bms_sa_doc t  set t.planinvdate = sysdate  where t.salesid =#{salesId}")
    void updateBy(Long salesId);

    void updateSummary(@Param("salesId")Long salesid, @Param("entryId")Integer entryId);

    List<OrderDetailVo> selectOrderDetails( @Param("orderNo")String orderNo);

    @Update("update bms_sa_doc set usestatus = 1,approveflag = 1,confirmanid =#{salerId},confirmdate =sysdate where salesid = #{salesId}")
    void updateUsetatus(@Param("salerId") Long salerId, @Param("salesId") Long salesId);
}
