package com.zsyc.zt.inca.service;


import com.zsyc.zt.inca.entity.BmsPresOutDoc;
import com.zsyc.zt.inca.mapper.B2bGiftDocComfirmMapper;
import com.zsyc.zt.inca.vo.IncaAddressVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
@Slf4j
public class B2bGiftDocComfirmServiceImpl  implements B2bGiftDocComfirmService {
    @Resource
    B2bGiftDocComfirmMapper b2bGiftDocComfirmMapper;


    @Override
    public void updateUsestatus(Long inputmanid, Long presoutid) {
        b2bGiftDocComfirmMapper.updateUsestatus(inputmanid,presoutid);
    }

    @Override
    public IncaAddressVo queryAddress(Long presoutid) {
        return b2bGiftDocComfirmMapper.queryAddress(presoutid);
    }

    @Override
    public String queryCustomid(Long presoutid) {
        return b2bGiftDocComfirmMapper.queryCustomid(presoutid);
    }

    @Override
    public void updateCompanyid(String tocompanyid, Long Trid) {
         b2bGiftDocComfirmMapper.updateCompanyid(tocompanyid,Trid);
    }

    @Override
    public Integer getStorerid(Long presoutid) {
        return b2bGiftDocComfirmMapper.getStorerid(presoutid);
    }

    @Override
    public void updateBmstmp(Long trdtlid, Long presoutid, Integer phystoreid) {
        b2bGiftDocComfirmMapper.updateBmstmp(trdtlid,presoutid,phystoreid);
    }

    @Override
    public BmsPresOutDoc selectBy(String orderNo, Integer entryId, Integer srcStatus) {
        return b2bGiftDocComfirmMapper.selectBy(orderNo, entryId, srcStatus);
    }

    @Override
    public String getInputname(Long inputmanid) {
        return b2bGiftDocComfirmMapper.getInputName(inputmanid);
    }


}
