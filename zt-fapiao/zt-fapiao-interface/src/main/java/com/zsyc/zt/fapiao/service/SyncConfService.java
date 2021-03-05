package com.zsyc.zt.fapiao.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zsyc.zt.fapiao.entity.SyncConf;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author peiqy
 * @since 2020-11-09
 */
public interface SyncConfService extends IService<SyncConf> {

    LocalDateTime getGoodsSyncBeginTime();

    LocalDateTime getSyncBeginTime(String syncFlag);

    void updateGoodsSyncBeginTime(LocalDateTime now);

    void updateyncBeginTime(String kpxxWbSync, LocalDateTime now);
}
