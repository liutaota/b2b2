package com.zsyc.zt.report.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.report.entity.PubMonthlySales;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MP
 * @since 2021-01-08
 */

public interface PubMonthlySalesService {
   /* *//**
     * 客户列表
     *
     * @param page
     * @param
     * @return
     */

     IPage<PubMonthlySales> selectPageList(Page page, List<Long> salesIdList, String customids, String tomonthss, String startTime, String endTime,String customname);


    List<PubMonthlySales> selectListByTime(String startTime, String endTime);
}
