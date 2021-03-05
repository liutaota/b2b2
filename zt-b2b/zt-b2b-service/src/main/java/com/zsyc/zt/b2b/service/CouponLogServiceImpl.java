package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.entity.CouponLog;
import com.zsyc.zt.b2b.mapper.CouponLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class CouponLogServiceImpl implements CouponLogService{
    @Autowired
    private CouponLogMapper couponLogMapper;
    @Override
    public IPage<CouponLog> getCouponLogList(Page page) {
        AssertExt.notNull(page,"无效页面");
        return couponLogMapper.selectPage(page,new QueryWrapper<CouponLog>().orderByDesc("CREATE_TIME"));
    }
}
