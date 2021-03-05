package com.zsyc.zt.inca.service;

import com.zsyc.zt.inca.mapper.PubSettleAccountMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * 英克Service
 */
@Service
public class IncaServiceImpl implements  IncaService{

    @Autowired
    PubSettleAccountMapper pubSettleAccountMapper;
    /**
     * 判断是否允许业务操作
     * @return
     */
    @Override
    public Boolean checkBusinessOperationIsPermit(LocalDateTime dateTime, Integer entryId){
        return pubSettleAccountMapper.checkBusinessOperationIsPermit(dateTime,entryId);
    }
}
