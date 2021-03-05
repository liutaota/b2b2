package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.CouponLog;

public interface CouponLogService {
    /**
     * 优惠券日志
     * @param page
     * @return
     */
    IPage<CouponLog> getCouponLogList(Page page);
}
