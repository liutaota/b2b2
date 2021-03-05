package com.zsyc.zt.report.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.report.entity.GoodsSalesPlanDtl;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.report.vo.GoodsSalesPlanDtlVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2021-01-16
 */
public interface GoodsSalesPlanDtlMapper extends BaseMapper<GoodsSalesPlanDtl> {

    /**
     * 保存计划商品
     * @param dtlVoList
     * @return
     */
    int insertDtlList(@Param("dtlVoList") List<GoodsSalesPlanDtlVo> dtlVoList);

    /**
     * 查询计划商品
     * @param goodsSalesPlanDtlVo
     * @return
     */
    List<GoodsSalesPlanDtlVo> selectPlanGoodsListPage(@Param("goodsSalesPlanDtlVo") GoodsSalesPlanDtlVo goodsSalesPlanDtlVo);

    /**
     * 查询计划商品
     * @param planId
     * @param goodsId
     * @return
     */
    GoodsSalesPlanDtlVo getPlanDtl(@Param("planId") Long planId, @Param("goodsId") Long goodsId);

}
