package com.zsyc.zt.report.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.report.entity.GoodsSalesPlanDtl;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsyc.zt.report.vo.GoodsSalesPlanDtlVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MP
 * @since 2021-01-16
 */
public interface IGoodsSalesPlanDtlService extends IService<GoodsSalesPlanDtl> {

    /**
     * 保存计划商品数据
     * @param dtlVoList
     * @return
     */
    int insertDtlList(List<GoodsSalesPlanDtlVo> dtlVoList);

    /**
     * 新增或删除计划商品
     * @param dtlVoList
     * @return
     */
    Map<String,Object> changDtlList(List<GoodsSalesPlanDtlVo> dtlVoList);

    /**
     * 删除计划商品
     * @param planDtlId
     * @return
     */
    Map<String, Object> deleteGoodsByPlanId(Long planDtlId);

    /**
     * 查询计划商品
     * @param goodsSalesPlanDtlVo
     * @return
     */
    List<GoodsSalesPlanDtlVo> selectPlanGoodsListPage(GoodsSalesPlanDtlVo goodsSalesPlanDtlVo);

    /**
     * 查询计划商品
     * @param planId
     * @return
     */
    Map<String, Object> getGoodsStrById(Long planId);

    /**
     * 检验商品
     * @param goodsSalesPlanDtlVoList
     * @return
     */
    Map<String, Object> checkGoods(List<GoodsSalesPlanDtlVo> goodsSalesPlanDtlVoList);

    /**
     * 查询计划商品
     * @param planId
     * @param goodsId
     * @return
     */
    GoodsSalesPlanDtlVo getPlanDtl(Long planId, Long goodsId);

    /**
     * 删除计划商品(所有)
     * @param planId
     * @return
     */
    Map<String, Object> deletePlanGoods(Long planId);

    Map<String, Object> checkGoodsV2(List<GoodsSalesPlanDtlVo> goodsSalesPlanDtlVoList);
}
