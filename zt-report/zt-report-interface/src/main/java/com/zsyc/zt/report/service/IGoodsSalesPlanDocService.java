package com.zsyc.zt.report.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.inca.vo.GoodsVo;
import com.zsyc.zt.report.entity.GoodsSalesPlanDoc;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsyc.zt.report.vo.GoodsSalesPlanDocVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MP
 * @since 2021-01-15
 */
public interface IGoodsSalesPlanDocService extends IService<GoodsSalesPlanDoc> {

    /**
     * 分页获取商品销售计划
     * @param page
     * @param goodsSalesPlanDocVo
     * @param goodsId
     * @return
     */
    IPage<GoodsSalesPlanDocVo> selectPlanListPage(Page<GoodsSalesPlanDocVo> page, GoodsSalesPlanDocVo goodsSalesPlanDocVo,Long goodsId);

    /**
     * 录入商品销售计划
     * @param goodsSalesPlanDocVo
     * @return
     */
    Map<String, Object> insertPlan(GoodsSalesPlanDocVo goodsSalesPlanDocVo);

    /**
     * 查询计划状态
     * @param planId
     * @return
     */
    Integer getPlanUseStatus(Long planId);

    /**
     * 删除计划
     * @param planId
     * @return
     */
    Map<String,Object> deletePlanById(Long planId);

    /**
     * 查询商品规格、基本单位
     * @param page
     * @param goodsVo
     * @return
     */
    IPage<GoodsVo> selectGoodsList(Page<GoodsVo> page,GoodsVo goodsVo);

    /**
     * 修改计划
     * @param goodsSalesPlanDocVo
     * @return
     */
    Map<String,Object> modifyPlanById(GoodsSalesPlanDocVo goodsSalesPlanDocVo);

    /**
     * 变更计划状态
     * @param planId
     * @param useStatus
     * @param erpEmployeeId
     * @param erpEmployeeName
     * @return
     */
    Integer updatePlanUseStatusById(Long planId,Integer useStatus,Long erpEmployeeId,String erpEmployeeName);

    /**
     * 分页获取商品销售计划报表
     * @param page
     * @param goodsSalesPlanDocVo
     * @return
     */
    IPage<GoodsSalesPlanDocVo> selectReportPage(IPage<GoodsSalesPlanDocVo> page, GoodsSalesPlanDocVo goodsSalesPlanDocVo);

}
