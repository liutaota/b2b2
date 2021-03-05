package com.zsyc.zt.fapiao.service;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsyc.zt.fapiao.entity.BwfpFpxx;
import com.zsyc.zt.fapiao.mapper.BwfpFpxxMapper;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 发票回传主表 服务实现类
 * </p>
 *
 * @author peiqy
 * @since 2020-11-09
 */
@Service
@Slf4j
public class BwfpFpxxServiceImpl extends ServiceImpl<BwfpFpxxMapper, BwfpFpxx> implements BwfpFpxxService {


    @Override
    public List<BwfpFpxx> selectList(LocalDateTime syncBeginTime) {

        QueryWrapper<BwfpFpxx> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().gt(BwfpFpxx::getModifyTime, DateUtil.format(syncBeginTime, DatePattern.PURE_DATETIME_PATTERN));

        List<BwfpFpxx> list = this.baseMapper.selectList(queryWrapper);



        return list;
    }
}
