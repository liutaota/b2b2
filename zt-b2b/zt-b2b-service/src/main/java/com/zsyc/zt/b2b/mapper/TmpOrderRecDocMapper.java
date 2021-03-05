package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.TmpOrderRecDoc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.TmpOrderRecDocVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 现结账单 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-12-29
 */
public interface TmpOrderRecDocMapper extends BaseMapper<TmpOrderRecDoc> {

    /**
     * 现结账单详情
     * @param tmpNo
     * @return
     */
    TmpOrderRecDoc getTmpOrderRecDoc(@Param("tmpNo") String tmpNo);

    /**
     * 已结算erp订单
     * @return
     */
    IPage<TmpOrderRecDocVo> getTmpOrderRecDocList(@Param("page") Page page, @Param("tmpOrderRecDoc") TmpOrderRecDocVo tmpOrderRecDoc);


    @Select("select salesid from  ERP_B2B_ORDER_REC_Dtl where erp_b2b_order_rec_doc_id = #{orderId} ")
    List<String> selectSalesList(@Param("orderId") String orderId);
}
