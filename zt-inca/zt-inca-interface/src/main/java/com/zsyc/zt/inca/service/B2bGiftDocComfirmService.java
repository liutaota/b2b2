package com.zsyc.zt.inca.service;


import com.zsyc.zt.inca.entity.BmsPresOutDoc;

import com.zsyc.zt.inca.vo.IncaAddressVo;


public interface B2bGiftDocComfirmService {

    void updateUsestatus(Long inputmanid, Long presoutid);

    IncaAddressVo queryAddress(Long presoutid);


    String queryCustomid(Long presoutid);

    void updateCompanyid(String tocompanyid, Long Trid);

    Integer getStorerid(Long presoutid);

    void updateBmstmp(Long trdtlid, Long presoutid, Integer phystoreid);


    BmsPresOutDoc selectBy(String orderNo, Integer entryId, Integer srcStatus);


    String getInputname(Long inputmanid);
}
