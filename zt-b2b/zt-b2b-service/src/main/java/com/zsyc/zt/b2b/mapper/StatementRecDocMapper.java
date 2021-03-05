package com.zsyc.zt.b2b.mapper;

import com.zsyc.zt.b2b.entity.StatementRecDoc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 账单-收款单中间表 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-11-19
 */
public interface StatementRecDocMapper extends BaseMapper<StatementRecDoc> {
    /**
     * 根据客户查询账单
     * @param memberId
     * @return
     */
    StatementRecDoc getStatementRecDocByMemberId(@Param("memberId") Long memberId);
}
