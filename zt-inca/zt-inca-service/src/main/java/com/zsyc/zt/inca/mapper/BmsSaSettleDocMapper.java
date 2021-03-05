package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.BmsSaSettleDoc;
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
public interface BmsSaSettleDocMapper extends BaseMapper<BmsSaSettleDoc> {
    /**
     * 更新发票总单最终收款金额
     *
     * @param sasettledtlid
     * @param totalLine
     */
    void updateZxTotalRecmoney(@Param("sasettledtlid") Long sasettledtlid,@Param("totalLine") Double totalLine);
}
