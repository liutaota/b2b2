package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.ErpB2bOrderRecDoc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.ErpB2bOrderRecDocVo;
import com.zsyc.zt.b2b.vo.ErpB2bPayOrderFlowNoSyncVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * erpb2b收款单总表 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2021-01-07
 */
public interface ErpB2bOrderRecDocMapper extends BaseMapper<ErpB2bOrderRecDoc> {

    /**
     * app收款单列表
     * @param page
     * @param erpB2bOrderRecDocVo
     * @return
     */
    IPage<ErpB2bOrderRecDocVo> getErpB2bOrderRecDocVoList(@Param("page") Page page, @Param("erpB2bOrderRecDocVo") ErpB2bOrderRecDocVo erpB2bOrderRecDocVo);

    /**
     * app收款单列表不分页
     * @param erpB2bOrderRecDocVo
     * @return
     */
    List<ErpB2bOrderRecDocVo> getErpB2bOrderRecDocVoExcel(@Param("erpB2bOrderRecDocVo") ErpB2bOrderRecDocVo erpB2bOrderRecDocVo);

    @Select("select id,a.tmp_No,a.pay_Flow_No,a.ERP_USER_ID from ERP_B2B_ORDER_REC_DOC a where a.is_del!=1 and   a.update_time>= #{updateTime}")
    @Results(id = "resultMap" , value = {
            @Result(property = "tmpNo",column = "tmp_No"),
            @Result(property = "payFlowNo",column = "pay_Flow_No"),
            @Result(property = "orderIds",column = "id",many = @Many(select = "com.zsyc.zt.b2b.mapper.TmpOrderRecDocMapper.selectSalesList",fetchType = FetchType.DEFAULT))
    })
    List<ErpB2bPayOrderFlowNoSyncVo> selectByUpdateTime(LocalDateTime updateTime);
}
