package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.BmsSaSettleDtl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-12
 */
public interface BmsSaSettleDtlMapper extends BaseMapper<BmsSaSettleDtl> {

    /**
     * 更新相应的发票细单状态
     *
     * @param sasettledtlid 发票细单号
     * @param goodsqty 应收数量
     * @param totalLine 应收款金额
     */
    void updateRecfinflag(@Param("sasettledtlid")Long sasettledtlid,@Param("goodsqty") Double goodsqty,@Param("totalLine") Double totalLine);

    /**
     * 删除临时待回款发票细单
     * @param sasettledtlid
     */
    void deleteBmsSaSettleDtlTmp(@Param("sasettledtlid")Long sasettledtlid);

    /**
     * 验证发票单号是否已经核销
     * @param sasettledtlid
     * @return
     */
    Long getBmsSaSettleDtlTmp(@Param("sasettledtlid")Long sasettledtlid);

    BmsSaSettleDtl getBySalesdtlid(@Param("salesdtlid")Long salesdtlid);

    void updateTotalRecMoney(@Param("sasettledtlid")Long sasettledtlid, @Param("totalLine")Double totalLine);
}
