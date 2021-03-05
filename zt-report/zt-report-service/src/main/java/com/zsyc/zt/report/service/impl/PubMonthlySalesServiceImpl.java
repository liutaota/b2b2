package com.zsyc.zt.report.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.report.entity.PubMonthlySales;
import com.zsyc.zt.report.mapper.PubMonthlySalesMapper;
import com.zsyc.zt.report.service.PubMonthlySalesService;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MP
 * @since 2021-01-08
 */
@Service
public class PubMonthlySalesServiceImpl extends ServiceImpl<PubMonthlySalesMapper, PubMonthlySales> implements PubMonthlySalesService {
    @Resource
    PubMonthlySalesMapper pubMonthlySalesMapper;



    @Override
    public IPage<PubMonthlySales> selectPageList(Page page, List<Long> salesIdList, String customids, String tomonthss,String startTime, String endTime,String customname) {
        System.out.print("severice 层");
        AssertExt.notNull(page, "页码不为空");
        return pubMonthlySalesMapper.selectPageList(page, salesIdList,customids, tomonthss,startTime,endTime,customname);
    }

    @Override
    public List<PubMonthlySales> selectListByTime(String startTime, String endTime) {
        return pubMonthlySalesMapper.selectListByTime(startTime,endTime);
    }
}
