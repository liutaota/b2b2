package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.RecDoc;
import com.zsyc.zt.b2b.entity.Statement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.StatementVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 账单 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-11-19
 */
public interface StatementMapper extends BaseMapper<Statement> {

    /**
     * 上个月的账单
     * @return
     */
    List<RecDoc> getMonthRecDoc(Long memberId);

    /**
     * 账单列表
     * @param page
     * @param statementVo
     * @return
     */
    IPage<StatementVo> getStateMentList(@Param("page") Page page, @Param("statementVo") StatementVo statementVo);

    /**
     * 我的账单列表
     * @param page
     * @param statementVo
     * @return
     */
    IPage<StatementVo> getMyStateMentList(@Param("page") Page page, @Param("statementVo") StatementVo statementVo);
}
