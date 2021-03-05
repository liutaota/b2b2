package com.zsyc.zt.report.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.report.entity.GoodsSalesPlanDoc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.report.vo.GoodsSalesPlanDocVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2021-01-15
 */
public interface GoodsSalesPlanDocMapper extends BaseMapper<GoodsSalesPlanDoc> {

    /**
     * 分页查询商品销售计划数据
     * @param page
     * @param goodsSalesPlanDocVo
     * @param goodsId
     * @return
     */
    IPage<GoodsSalesPlanDocVo> selectPlanListPage(Page<GoodsSalesPlanDocVo> page,
                                                  @Param("goodsSalesPlanDocVo") GoodsSalesPlanDocVo goodsSalesPlanDocVo,
                                                  @Param("goodsId") Long goodsId);

    /**
     * 查询计划状态
     * @param planId
     * @return
     */
    Integer getUseStatusById(@Param("planId") Long planId);

    /**
     * 查询计划报表数据
     * @param page
     * @param goodsSalesPlanDocVo
     * @return
     */
    IPage<GoodsSalesPlanDocVo> selectReportPage(IPage<GoodsSalesPlanDocVo> page, GoodsSalesPlanDocVo goodsSalesPlanDocVo);
}
