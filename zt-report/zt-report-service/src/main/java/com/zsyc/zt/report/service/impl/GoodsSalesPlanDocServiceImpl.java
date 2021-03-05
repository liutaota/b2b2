package com.zsyc.zt.report.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.inca.service.GoodsService;
import com.zsyc.zt.inca.vo.GoodsVo;
import com.zsyc.zt.report.constants.ReportUtils;
import com.zsyc.zt.report.entity.GoodsSalesPlanDoc;
import com.zsyc.zt.report.entity.GoodsSalesPlanDtl;
import com.zsyc.zt.report.mapper.GoodsSalesPlanDocMapper;
import com.zsyc.zt.report.mapper.GoodsSalesPlanDtlMapper;
import com.zsyc.zt.report.service.IGoodsSalesPlanDocService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsyc.zt.report.service.IGoodsSalesPlanDtlService;
import com.zsyc.zt.report.vo.GoodsSalesPlanDocVo;
import com.zsyc.zt.report.vo.GoodsSalesPlanDtlVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MP
 * @since 2021-01-15
 */
@Primary
@Service
@Slf4j
public class GoodsSalesPlanDocServiceImpl extends ServiceImpl<GoodsSalesPlanDocMapper, GoodsSalesPlanDoc> implements IGoodsSalesPlanDocService {

    @Autowired
    private GoodsSalesPlanDocMapper goodsSalesPlanDocMapper;

    @Reference
    private GoodsService goodsService;

    @Autowired
    private IGoodsSalesPlanDtlService iGoodsSalesPlanDtlService;

    @Autowired
    private GoodsSalesPlanDtlMapper goodsSalesPlanDtlMapper;

    @Override
    public IPage<GoodsSalesPlanDocVo> selectPlanListPage(Page<GoodsSalesPlanDocVo> page, GoodsSalesPlanDocVo goodsSalesPlanDocVo, Long goodsId) {
        AssertExt.notNull(page, "分页数据为空");
        IPage<GoodsSalesPlanDocVo> listPage = goodsSalesPlanDocMapper.selectPlanListPage(page, goodsSalesPlanDocVo, goodsId);
        log.info("page size {}", listPage.getSize());
        return listPage;
    }

    @Transactional
    @Override
    public Map<String, Object> insertPlan(GoodsSalesPlanDocVo goodsSalesPlanDocVo) {
        log.info("insert data: {}", goodsSalesPlanDocVo.toString());
        AssertExt.isTrue(goodsSalesPlanDocVo.getPlanName() != null && !"".equals(goodsSalesPlanDocVo.getPlanName()),"计划名称为空");
        AssertExt.isTrue(goodsSalesPlanDocVo.getStatisticsUnits() != null
                && Arrays
                    .asList(ReportUtils.GoodsUnits.list)
                    .contains(goodsSalesPlanDocVo.getStatisticsUnits()),"统计基本单位不合法");

        AssertExt.notNull(goodsSalesPlanDocVo.getPlanStartDate(),"开始日期为空");
        AssertExt.notNull(goodsSalesPlanDocVo.getPlanEndDate(),"结束日期为空");

        List<GoodsSalesPlanDtlVo> dtlVoList = goodsSalesPlanDocVo.getGoodsSalesPlanDtlVoList();

        // 计划对象
        GoodsSalesPlanDoc goodsSalesPlanDoc = new GoodsSalesPlanDoc();
        BeanUtils.copyProperties(goodsSalesPlanDocVo, goodsSalesPlanDoc);
        goodsSalesPlanDoc.setCreateDate(LocalDateTime.now());
        goodsSalesPlanDoc.setUseStatus(1);
        goodsSalesPlanDoc.setIsDelete(2);
        int docAffectedCount = goodsSalesPlanDocMapper.insert(goodsSalesPlanDoc);

        Long planId = goodsSalesPlanDoc.getPlanId();
        Long inputId = goodsSalesPlanDocVo.getInputId();
        String inputName = goodsSalesPlanDocVo.getInputName();
        int dtlAffectedCount = 0;
        if(dtlVoList != null && dtlVoList.size() > 0){
            for (GoodsSalesPlanDtlVo vo : dtlVoList) {
                AssertExt.hasId(vo.getGoodsId(),"商品ID为空");
                vo.setPlanId(planId);
                vo.setOperatorId(inputId);
                vo.setOperator(inputName);
            }

            dtlAffectedCount = iGoodsSalesPlanDtlService.insertDtlList(dtlVoList);
        }

        Map<String, Object> rsMap = new HashMap<>();
        String result = (docAffectedCount > 0) ? "success":"failed";
        rsMap.put("result",result);
        rsMap.put("docAffectedCount",docAffectedCount);
        rsMap.put("dtlAffectedCount",dtlAffectedCount);
        return rsMap;
    }

    @Override
    public Integer getPlanUseStatus(Long planId) {
        AssertExt.hasId(planId,"ID为空");
        return  goodsSalesPlanDocMapper.getUseStatusById(planId);
    }

    @Transactional
    @Override
    public Map<String,Object> deletePlanById(Long planId) {
        AssertExt.hasId(planId,"ID为空");
        GoodsSalesPlanDoc plan = new GoodsSalesPlanDoc();
        plan.setIsDelete(1);
        int docRs = goodsSalesPlanDocMapper.update(plan, new QueryWrapper<GoodsSalesPlanDoc>().eq("plan_id", planId).ne("use_status", 2));
        int dtlRs = goodsSalesPlanDtlMapper.delete(new QueryWrapper<GoodsSalesPlanDtl>().eq("plan_id", planId));
        Map<String,Object> rsMap = new HashMap<>(3);
        String result = (docRs > 0) ? "success":"failed";
        rsMap.put("result",result);
        rsMap.put("docAffectedCount",docRs);
        rsMap.put("dtlAffectedCount",dtlRs);
        return rsMap;
    }

    @Override
    public IPage<GoodsVo> selectGoodsList(Page<GoodsVo> page,GoodsVo goodsVo) {
        AssertExt.notNull(page, "分页数据为空");
        //AssertExt.notNull(goodsVo.getGoodsId(),"商品ID为空");
        return goodsService.selectGoodsList( page, goodsVo);
    }

    @Transactional
    @Override
    public Map<String, Object> modifyPlanById(GoodsSalesPlanDocVo goodsSalesPlanDocVo) {
        AssertExt.notNull(goodsSalesPlanDocVo,"参数为空");
        AssertExt.hasId(goodsSalesPlanDocVo.getPlanId(),"ID为空");

        Long planId = goodsSalesPlanDocVo.getPlanId();
        Integer useStatus = this.getPlanUseStatus(planId);
        AssertExt.isTrue(useStatus != null && useStatus == 1,"正式数据不允许修改");

        GoodsSalesPlanDoc plan = new GoodsSalesPlanDoc();
        plan.setPlanName(goodsSalesPlanDocVo.getPlanName().trim());
        plan.setPlanNum(goodsSalesPlanDocVo.getPlanNum());
        plan.setPlanStartDate(goodsSalesPlanDocVo.getPlanStartDate());
        plan.setPlanEndDate(goodsSalesPlanDocVo.getPlanEndDate());
        plan.setRemark(goodsSalesPlanDocVo.getRemark().trim());

        int affectedCount = goodsSalesPlanDocMapper.update(plan, new QueryWrapper<GoodsSalesPlanDoc>().eq("plan_id", planId).eq("use_status",1));
        Map<String,Object> rsMap = new HashMap<>(2);
        String result = (affectedCount > 0) ? "success":"failed";
        rsMap.put("result",result);
        rsMap.put("affectedCount",affectedCount);
        log.info("update plan success : affected planId-"+planId);
        return rsMap;
    }

    @Override
    public Integer updatePlanUseStatusById(Long planId, Integer useStatus, Long erpEmployeeId, String erpEmployeeName) {
        AssertExt.hasId(planId,"计划ID为空");
        AssertExt.isTrue(useStatus ==1 || useStatus==2,"使用状态有误");
        GoodsSalesPlanDoc plan = new GoodsSalesPlanDoc();
        if(useStatus == 1){
            plan.setUseStatus(2);
            plan.setConfirmId(erpEmployeeId);
            plan.setConfirmName(erpEmployeeName);
            plan.setConfirmDate(LocalDateTime.now());
        }else {
            plan.setUseStatus(1);
            plan.setUnConfirmId(erpEmployeeId);
            plan.setUnConfirmName(erpEmployeeName);
            plan.setUnConfirmDate(LocalDateTime.now());
        }
        return goodsSalesPlanDocMapper.update(plan,new QueryWrapper<GoodsSalesPlanDoc>().eq("plan_id",planId));
    }

    @Override
    public IPage<GoodsSalesPlanDocVo> selectReportPage(IPage<GoodsSalesPlanDocVo> page, GoodsSalesPlanDocVo goodsSalesPlanDocVo) {
        AssertExt.notNull(page, "分页数据为空");
        return goodsSalesPlanDocMapper.selectReportPage(page,goodsSalesPlanDocVo);
    }
}
