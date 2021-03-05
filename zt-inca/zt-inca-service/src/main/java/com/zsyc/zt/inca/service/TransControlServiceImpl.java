package com.zsyc.zt.inca.service;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsyc.zt.inca.entity.BmsTranscontrolDtl;
import com.zsyc.zt.inca.entity.CustomTransVo;
import com.zsyc.zt.inca.mapper.BmsTranscontrolDocMapper;
import com.zsyc.zt.inca.mapper.BmsTranscontrolDtlMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class TransControlServiceImpl implements TransControlService{

    @Autowired
    private BmsTranscontrolDtlMapper bmsTranscontrolDtlMapper;

    @Autowired
    private BmsTranscontrolDocMapper bmsTranscontrolDocMapper;

    /**
     * 通过路单总单，方法已经过期，请使用  {@link TransControlServiceImpl#getByTransDocId(Long, Long)}
     * @param customId
     * @param transDocId
     * @return
     */
    @Deprecated
    @Override
    public CustomTransVo get(Long customId, Long transDocId) {
        QueryWrapper<BmsTranscontrolDtl> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(BmsTranscontrolDtl::getCompanyid,customId);
        queryWrapper.lambda().eq(BmsTranscontrolDtl::getRoadid,transDocId);
        List<BmsTranscontrolDtl> bmsTranscontrolDtls = bmsTranscontrolDtlMapper.selectList(queryWrapper);
        List<Long> salesIdList = bmsTranscontrolDtls.stream().map(BmsTranscontrolDtl::getSourceid).collect(Collectors.toList());
        return CustomTransVo.builder().
                customId(customId).
                transDocId(transDocId).
                salesIdList(salesIdList).build();
    }

    /**
     * 根据路单细单检索
     * @param transDtlId
     * @return
     */
    @Override
    public CustomTransVo getByTransDtlId(Long transDtlId) {

        QueryWrapper<BmsTranscontrolDtl> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(BmsTranscontrolDtl::getRoaddtlid,transDtlId);
        BmsTranscontrolDtl  bmsTranscontrolDtl = bmsTranscontrolDtlMapper.selectById(queryWrapper);
        Long companyid = bmsTranscontrolDtl.getCompanyid();

        Long roadid = bmsTranscontrolDtl.getRoadid();
        return get(companyid,roadid);

    }


    /**
     * 根据路单总单，检索
     * @param customId
     * @param transDocId
     * @return
     */
    @Override
    public CustomTransVo getByTransDocId( Long customId,Long transDocId) {

        QueryWrapper<BmsTranscontrolDtl> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(BmsTranscontrolDtl::getCompanyid,customId);
        queryWrapper.lambda().eq(BmsTranscontrolDtl::getRoadid,transDocId);
        List<BmsTranscontrolDtl> bmsTranscontrolDtls = bmsTranscontrolDtlMapper.selectList(queryWrapper);
        List<Long> salesIdList = bmsTranscontrolDtls.stream().map(BmsTranscontrolDtl::getSourceid).collect(Collectors.toList());
        return CustomTransVo.builder().
                customId(customId).
                transDocId(transDocId).
                salesIdList(salesIdList).build();

    }

    /**
     * 根据客户ID，检索出最近的排单的订单
     * @param customId
     * @return
     */
    @Override
    public CustomTransVo getByCustomId( Long customId) {

        Long transDocId =  bmsTranscontrolDocMapper.getRoadidOfLastest(customId);
        if(ObjectUtil.isNull(transDocId)){
            return null;
        }

        QueryWrapper<BmsTranscontrolDtl> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(BmsTranscontrolDtl::getCompanyid,customId);
        queryWrapper.lambda().eq(BmsTranscontrolDtl::getRoadid,transDocId);
        List<BmsTranscontrolDtl> bmsTranscontrolDtls = bmsTranscontrolDtlMapper.selectList(queryWrapper);
        List<Long> salesIdList = bmsTranscontrolDtls.stream().map(BmsTranscontrolDtl::getSourceid).collect(Collectors.toList());
        return CustomTransVo.builder().
                customId(customId).
                transDocId(transDocId).
                salesIdList(salesIdList).build();

    }
}
