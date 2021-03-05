package com.zsyc.zt.fapiao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zsyc.zt.fapiao.entity.BwfpFpxx;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 发票回传主表 服务类
 * </p>
 *
 * @author peiqy
 * @since 2020-11-09
 */
public interface BwfpFpxxService extends IService<BwfpFpxx> {
    /**
     * 获取最新的发票数据
     * @param syncBeginTime
     * @return
     */
    List<BwfpFpxx> selectList(LocalDateTime syncBeginTime);
}
