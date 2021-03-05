package com.zsyc.zt.fapiao.service;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsyc.zt.fapiao.commom.SyncConstant;
import com.zsyc.zt.fapiao.entity.SyncConf;
import com.zsyc.zt.fapiao.mapper.SyncConfMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author peiqy
 * @since 2020-11-09
 */
@Service
@Transactional
public class SyncConfServiceImpl extends ServiceImpl<SyncConfMapper, SyncConf> implements SyncConfService {
    @Override

    @Cached(name = "SyncConfServiceImpl-getGoodsSyncBeginTime",key = "",expire=3)
    public LocalDateTime getGoodsSyncBeginTime(){
        QueryWrapper<SyncConf> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SyncConf::getParamName, SyncConstant.GOODS_SYNC);
        List<SyncConf> list = this.list(queryWrapper);

        if(ObjectUtil.isEmpty(list)){
            SyncConf syncConf = new SyncConf();
            syncConf.setParamName( SyncConstant.GOODS_SYNC);
            syncConf.setParamValue(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            save(syncConf);
            return LocalDateTime.now();
        }else {
            return LocalDateTime.parse(list.get(0).getParamValue());
        }
    }

    @Override
    public LocalDateTime getSyncBeginTime(String syncFlag) {
        QueryWrapper<SyncConf> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SyncConf::getParamName,syncFlag);
        List<SyncConf> list = this.list(queryWrapper);

        if(ObjectUtil.isEmpty(list)){
            SyncConf syncConf = new SyncConf();
            syncConf.setParamName( syncFlag);
            syncConf.setParamValue(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            save(syncConf);
            return LocalDateTime.now();
        }else {
            return LocalDateTime.parse(list.get(0).getParamValue());
        }
    }


    @Override
    public void updateGoodsSyncBeginTime(LocalDateTime now) {
        QueryWrapper<SyncConf> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SyncConf::getParamName,SyncConstant.GOODS_SYNC);
        List<SyncConf> list = this.list(queryWrapper);
        saveOrUpdate(list.get(0));
    }

    @Override
    public void updateyncBeginTime(String kpxxWbSync, LocalDateTime now) {
        UpdateWrapper<SyncConf> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().eq(SyncConf::getParamName,kpxxWbSync);

        QueryWrapper<SyncConf> queryWrapper = new QueryWrapper<>();

        SyncConf entity = this.list(queryWrapper).get(0);
        entity.setParamValue(DateUtil.format(now, DatePattern.UTC_SIMPLE_PATTERN));
        update(entity,updateWrapper);
    }
}
