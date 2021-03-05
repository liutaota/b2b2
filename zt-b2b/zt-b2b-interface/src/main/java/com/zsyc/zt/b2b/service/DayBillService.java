package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.DayBill;

public interface DayBillService {

    /**
     * 生成每日报表
     */
    void addDayBill();

    /**
     * 每日报表列表
     */
    IPage<DayBill> getDayBillList(Page page, DayBill dayBill);
}
