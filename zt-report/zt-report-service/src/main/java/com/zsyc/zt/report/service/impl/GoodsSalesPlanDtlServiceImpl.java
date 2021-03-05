package com.zsyc.zt.report.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.inca.service.GoodsService;
import com.zsyc.zt.report.constants.ReportUtils;
import com.zsyc.zt.report.entity.GoodsSalesPlanDoc;
import com.zsyc.zt.report.entity.GoodsSalesPlanDtl;
import com.zsyc.zt.report.mapper.GoodsSalesPlanDtlMapper;
import com.zsyc.zt.report.service.IGoodsSalesPlanDocService;
import com.zsyc.zt.report.service.IGoodsSalesPlanDtlService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsyc.zt.report.vo.GoodsSalesPlanDtlVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MP
 * @since 2021-01-16
 */
@Service
@Slf4j
public class GoodsSalesPlanDtlServiceImpl extends ServiceImpl<GoodsSalesPlanDtlMapper, GoodsSalesPlanDtl> implements IGoodsSalesPlanDtlService {

    @Autowired
    private GoodsSalesPlanDtlMapper goodsSalesPlanDtlMapper;

    @Autowired
    private IGoodsSalesPlanDocService iGoodsSalesPlanDocService;

    @Reference
    private GoodsService goodsService;

    @Transactional
    @Override
    public Map<String, Object> changDtlList(List<GoodsSalesPlanDtlVo> dtlVoList) {
        AssertExt.notEmpty(dtlVoList,"商品为空");
        List<GoodsSalesPlanDtlVo> insertGoodsList = new ArrayList<>(dtlVoList.size());
        List<Long> planIds = new ArrayList<>();
        List<Long> existsIds = new ArrayList<>();
        for (GoodsSalesPlanDtlVo vo : dtlVoList) {
            Long planId = vo.getPlanId();
            Long planDtlId = vo.getPlanDtlId();
            if (planDtlId == null){
                insertGoodsList.add(vo);
            }else{
                existsIds.add(vo.getPlanDtlId());
            }
            if(!planIds.contains(planId)){
                planIds.add(planId);
            }
        }
        AssertExt.isTrue(planIds.size() == 1,"参数不符");
        Long planId = planIds.get(0);
        Integer planUseStatus = iGoodsSalesPlanDocService.getPlanUseStatus(planId);
        AssertExt.isTrue( planUseStatus == 1,"正式数据禁止修改" );

        int saveCount = 0;
        int deleteCount = 0;
        if (existsIds.size() > 0){
            deleteCount = goodsSalesPlanDtlMapper.delete(new QueryWrapper<GoodsSalesPlanDtl>().eq("plan_id",planId).notIn("plan_dtl_id", existsIds));
        }
        if(insertGoodsList.size() > 0){
            saveCount = goodsSalesPlanDtlMapper.insertDtlList(insertGoodsList);
        }
        Map<String, Object> rsMap = new HashMap<>(2);
        rsMap.put("saveCount",saveCount);
        rsMap.put("deleteCount",deleteCount);
        return rsMap;
    }

    @Transactional
    @Override
    public int insertDtlList(List<GoodsSalesPlanDtlVo> dtlVoList) {
        AssertExt.notEmpty(dtlVoList,"商品为空");
        return goodsSalesPlanDtlMapper.insertDtlList(dtlVoList);
    }

    @Transactional
    @Override
    public Map<String, Object> deleteGoodsByPlanId(Long planDtlId) {
        AssertExt.hasId(planDtlId,"计划商品信息ID为空");
        int dtlAffectedCount = this.goodsSalesPlanDtlMapper.deleteById(planDtlId);
        Map<String, Object> rsMap = new HashMap<>(2);
        String result = (dtlAffectedCount > 0) ? "success":"failed";
        rsMap.put("result",result);
        rsMap.put("dtlAffectedCount",dtlAffectedCount);
        return rsMap;
    }

    @Override
    public List<GoodsSalesPlanDtlVo> selectPlanGoodsListPage( GoodsSalesPlanDtlVo goodsSalesPlanDtlVo) {
        AssertExt.isTrue(goodsSalesPlanDtlVo != null && goodsSalesPlanDtlVo.getPlanId() != null,"计划ID为空");
        return this.goodsSalesPlanDtlMapper.selectPlanGoodsListPage(goodsSalesPlanDtlVo);
    }

    @Override
    public Map<String, Object> getGoodsStrById(Long planId) {
        AssertExt.hasId(planId,"计划ID为空");
        List<GoodsSalesPlanDtl> goodsSalesPlanDtlList = goodsSalesPlanDtlMapper.selectList(new QueryWrapper<GoodsSalesPlanDtl>().eq("plan_id", planId));
        StringBuffer buffer = new StringBuffer("");
        int index = 0;
        int size = goodsSalesPlanDtlList.size();
        for (GoodsSalesPlanDtl dtl : goodsSalesPlanDtlList) {
            buffer.append(dtl.getGoodsId()+"-"+dtl.getGoodsName());
            if (index == (size-1)){
                break;
            }
            buffer.append(", ");
            index++;
        }
        Map<String, Object> rsMap = new HashMap<>();
        rsMap.put("goodsCount",size);
        rsMap.put("goodsStr",buffer);
        return rsMap;
    }

    @Override
    public Map<String, Object> checkGoods(List<GoodsSalesPlanDtlVo> goodsSalesPlanDtlVoList) {
        /*AssertExt.notEmpty(goodsSalesPlanDtlVoList,"商品为空");

        List<Long> goodsIdList = new ArrayList<>(goodsSalesPlanDtlVoList.size());
        for (GoodsSalesPlanDtlVo vo: goodsSalesPlanDtlVoList) {
            goodsIdList.add(vo.getGoodsId());
        }
        List<Long> existsChineseMedicineIds = goodsService.selectExistsChineseMedicineIds(goodsIdList);
        Map<String, Object> rsMap = new HashMap<>();

        if (existsChineseMedicineIds == null || existsChineseMedicineIds.size() == 0){
            rsMap.put("result","success");
            rsMap.put("statisticsUnits", ReportUtils.GoodsUnits.BOX);
            rsMap.put("warn","非中药,参考商品基本单位:"+ReportUtils.GoodsUnits.BOX);
        }else if (existsChineseMedicineIds.size() == goodsIdList.size()){
            List<GoodsVo> rsGoodsList = goodsService.selectPlanGoodsList(goodsIdList);
            Set<String> unitSet = new HashSet<>();
            Set<String> otherUnitSet = new HashSet<>();
            for (GoodsVo vo : rsGoodsList) {
                String unit = vo.getGoodsUnit().toLowerCase().trim();
                if (unit.equals(ReportUtils.GoodsUnits.G) || unit.equals(ReportUtils.GoodsUnits.KG)){
                    unitSet.add(unit);
                }else {
                    otherUnitSet.add(unit);
                }
            }
            int unitSize = unitSet.size();
            int otherSize = otherUnitSet.size();
            if (unitSize == 1 && otherSize == 0){
                rsMap.put("result","success");
                rsMap.put("statisticsUnits", unitSet.iterator().next());
                rsMap.put("warn","中药参考商品基本单位:"+unitSet.iterator().next());
            } else if(unitSize == 0 && otherSize > 0){
                rsMap.put("result","success");
                rsMap.put("statisticsUnits", ReportUtils.GoodsUnits.BOX);
                rsMap.put("warn","中药参考商品基本单位:"+ReportUtils.GoodsUnits.BOX);
            } else{
                rsMap.put("result","failed");
                rsMap.put("massage","基本单位不符");
            }
        }else{
            rsMap.put("result","failed");
            rsMap.put("massage","请勿同时混合录入中西药商品");
        }*/
        return null;
    }

    @Override
    public Map<String, Object> checkGoodsV2(List<GoodsSalesPlanDtlVo> goodsSalesPlanDtlVoList) {
        AssertExt.notEmpty(goodsSalesPlanDtlVoList,"商品为空");

        int size = goodsSalesPlanDtlVoList.size();
        Map<String, Object> rsMap = new HashMap<>();
        List<Long> planIdList = new ArrayList<>(size);
        Set<String> unitSet = new HashSet<>();
        for (GoodsSalesPlanDtlVo vo: goodsSalesPlanDtlVoList) {
            String unit = vo.getGoodsUnit().toLowerCase().trim();
            unitSet.add(unit);
            if (vo.getPlanId() != null && !planIdList.contains(vo.getPlanId())){
                planIdList.add(vo.getPlanId());
            }
        }

        if (unitSet.size() == 1){
            // 已存在计划
            if (ObjectUtil.isNotNull(planIdList) && ObjectUtil.isNotEmpty(planIdList)){
                GoodsSalesPlanDoc plan = iGoodsSalesPlanDocService.getById(planIdList.get(0));
                String statisticsUnits = null;
                if (ObjectUtil.isNotNull(plan)){
                    statisticsUnits = plan.getStatisticsUnits();
                    if (statisticsUnits == null ){
                        rsMap.put("result","success");
                        rsMap.put("statisticsUnits", unitSet.iterator().next());
                        rsMap.put("warn","用于数据统计的参考基本单位:"+unitSet.iterator().next());
                    }else if(statisticsUnits.equals(unitSet.iterator().next())){
                        rsMap.put("result","success");
                        rsMap.put("statisticsUnits", statisticsUnits);
                        rsMap.put("warn","用于数据统计的参考基本单位:"+statisticsUnits);
                    }else{
                        rsMap.put("result","failed");
                        rsMap.put("warn","基本单位不匹配");
                    }
                }else{
                    rsMap.put("result","failed");
                    rsMap.put("warn","计划不存在");
                }
            }else{  // 新增计划(商品)
                String unitNext = unitSet.iterator().next();
                rsMap.put("result","success");
                if(!unitNext.equals(ReportUtils.GoodsUnits.G) && !unitNext.equals(ReportUtils.GoodsUnits.KG)){
                    rsMap.put("statisticsUnits", ReportUtils.GoodsUnits.BOX);
                    rsMap.put("warn","用于数据统计的参考基本单位:"+ReportUtils.GoodsUnits.BOX);
                }else{
                    rsMap.put("statisticsUnits", unitNext);
                    rsMap.put("warn","用于数据统计的参考基本单位:"+unitNext);
                }
            }
        }else if(unitSet.size() > 1 && !unitSet.contains(ReportUtils.GoodsUnits.G) && !unitSet.contains(ReportUtils.GoodsUnits.KG)){
            rsMap.put("result","success");
            rsMap.put("statisticsUnits", ReportUtils.GoodsUnits.BOX);
            rsMap.put("warn","用于数据统计的参考基本单位:"+ReportUtils.GoodsUnits.BOX);
        }else{
            rsMap.put("result","failed");
            rsMap.put("warn","不符合基本单位统计规则");
        }
        return rsMap;
    }

    @Override
    public GoodsSalesPlanDtlVo getPlanDtl(Long planId, Long goodsId) {
        AssertExt.hasId(planId,"计划ID为空");
        AssertExt.hasId(goodsId,"商品ID为空");
        return goodsSalesPlanDtlMapper.getPlanDtl(planId,goodsId);
    }

    @Override
    public Map<String, Object> deletePlanGoods(Long planId) {
        AssertExt.hasId(planId,"计划ID为空");
        Integer planUseStatus = iGoodsSalesPlanDocService.getPlanUseStatus(planId);
        AssertExt.isTrue(planUseStatus == 1,"请勿修改正式数据");
        int deleteCount = goodsSalesPlanDtlMapper.delete(new QueryWrapper<GoodsSalesPlanDtl>().eq("plan_id", planId));
        Map<String, Object> rsMap = new HashMap<>();
        rsMap.put("deleteCount",deleteCount);
        return rsMap;
    }

}
