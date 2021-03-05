package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.SnowFlakeIdWorker;
import com.zsyc.zt.b2b.entity.DayBill;
import com.zsyc.zt.b2b.mapper.DayBillMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
@Slf4j
public class DayBillServiceImpl implements DayBillService {
    @Autowired
    private DayBillMapper dayBillMapper;

    @Autowired
    @Qualifier("dayBillWorker")
    private SnowFlakeIdWorker dayBillFlakeIdWorker;
    private final static DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public void addDayBill() {
        DayBill dayBill = this.dayBillMapper.selectDayBill();
        dayBill.setBillDate(DATE_TIME.format(LocalDateTime.now().minusDays(1)));
        dayBill.setBillNo(this.dayBillFlakeIdWorker.genNo());
        dayBill.setCreateTime(LocalDateTime.now());
        this.dayBillMapper.insert(dayBill);
    }

    @Override
    public IPage<DayBill> getDayBillList(Page page, DayBill dayBill) {
        return this.dayBillMapper.getDayBillList(page,dayBill);
    }
}
