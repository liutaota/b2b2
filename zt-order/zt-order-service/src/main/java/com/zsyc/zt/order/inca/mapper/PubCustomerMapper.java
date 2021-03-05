package com.zsyc.zt.order.inca.mapper;

import com.zsyc.zt.order.vo.CustomerVo;
import com.zsyc.zt.order.entity.PubCustomer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
public interface PubCustomerMapper extends BaseMapper<PubCustomer> {

    Integer getLongestOverdraftDay(@Param("customId") Long customId, @Param("entryId")Integer entryId);

    Double getAmountNoSettle(@Param("customId") Long customId, @Param("entryId")Integer entryId);

    Double getAmountSettle(@Param("customId") Long customId, @Param("entryId")Integer entryId);

    Double getAmountWriteOff(@Param("customId") Long customId, @Param("entryId")Integer entryId);

    CustomerVo getCustomInfo(@Param("customId") Long customId);
}
