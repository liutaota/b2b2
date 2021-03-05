package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.RecDoc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.RecDocVo;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 收款总单 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-11-06
 */
public interface RecDocMapper extends BaseMapper<RecDoc> {
    /**
     * 收款单
     * @param page
     * @param recDocVo
     * @return
     */
    IPage<RecDocVo> getRecDocList(@Param("page") Page page, @Param("recDocVo") RecDocVo recDocVo);

    /**
     * 收款单
     * @param recDocVo
     * @return
     */
    List<RecDocVo> getRecDocList(@Param("recDocVo") RecDocVo recDocVo);

    /**
     * 收款单
     * @param page
     * @param recDocVo
     * @return
     */
    IPage<RecDocVo> getMyRecDocList(@Param("page") Page page, @Param("recDocVo") RecDocVo recDocVo);

    /**
     * 账单明细
     * @param id
     * @return
     */
    List<RecDocVo> getMyRecDocAll(@Param("id") Long id);

    /**
     * 账单明细
     * @param id
     * @return
     */
    List<RecDocVo> getMyRecDocListById(@Param("id") Long id, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    /**
     *我的未结账单
     * @param memberId
     * @return
     */
    Long getMyNoPayTotal(@Param("memberId") Long memberId);

    /**
     * 账单订单数量
     * @param id
     * @return
     */
    Long getStatementOrderNum(@Param("id") Long id);

    /**
     * 客户的待收货账单
     * @param memberId
     * @return
     */
    List<RecDoc> getRecDocByMemberId(@Param("memberId") Long memberId);
}
