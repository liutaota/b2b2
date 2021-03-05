package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.entity.PubSettleAccount;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2021-01-07
 */
public interface PubSettleAccountMapper extends BaseMapper<PubSettleAccount> {

    Boolean checkBusinessOperationIsPermit(@Param("dateTime") LocalDateTime dateTime, @Param("entryId")Integer entryId);
}
