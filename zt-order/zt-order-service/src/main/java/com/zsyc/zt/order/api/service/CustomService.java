package com.zsyc.zt.order.api.service;

import com.alicp.jetcache.anno.Cached;
import com.zsyc.zt.order.vo.CustomerVo;
import com.zsyc.zt.order.vo.ResultVo;
import com.zsyc.zt.order.entity.*;

import java.util.List;


/**
 * @date
 * @author peiqy
 */
public interface CustomService {

    @Cached(name="CustomService-valid-", key="#customId+#entryId", expire = 3600)
    ResultVo valid(Long customId, Integer entryId);

    /**
     * 校验客户资料
     * @param customId
     * @return
     */

    @Cached(name="CustomService-validCustomData-", key="#customId+#entryId", expire = 3600)
    ResultVo validCustomData(Long customId, Integer entryId);


    /**
     * 校验客户资料
     * @param customId
     * @return
     */
    @Cached(name="CustomService-validCustomLicense-", key="#customId+#entryId", expire = 3600)
    ResultVo validCustomLicense(Long customId, Integer entryId);


    @Cached(name="CustomService-getSalerByCustomId-", key="#customId+#entryId", expire = 3600)
    PubCustomToSaler getSalerByCustomId(Long customId, Integer entryId);

    @Cached(name="CustomService-getCreditQuota-", key="#customId+#entryId", expire = 3600)
    BmsCredit getCreditQuota(Long customId, Integer entryId);

    /**
     * 校验客户信用
     * @param customId  ·
     * @param entryId
     * @return
     */
    @Cached(name="CustomService-validCredit-", key="#customId+#entryId", expire = 3600)
    ResultVo validCredit(Long customId, Integer entryId);

    @Cached(name="CustomService-getOverdraftAmount-", key="#customId+#entryId", expire = 3600)
    public Double getOverdraftAmount(Long customId,Integer entryId);

    @Cached(name="CustomService-getCreditDay-", key="#customId+#entryId", expire = 3600)
    BmsCreditDays getCreditDay(Long customId, Integer entryId);


    /**
     * 判断是否配送客户
     * @param customId
     * @param entryId
     * @return
     */
    @Cached(name="CustomService-isPsCustom-", key="#customId+':'+#entryId", expire = 3600)
    Boolean isPsCustom(Long customId,Integer entryId);

    /**
     * 通过客户ID 查询客户信息
     * @param customId
     * @return
     */
    @Cached(name="CustomService-userCache-", key="#customId", expire = 3600)
    PubCustomer getById(Long customId);

    /**
     * 通过客户ID 得到客户的地址
     * @param customId
     * @param entryId
     * @return
     */
    @Cached(name="CustomService-getAddressByCustomId-", key="#customId", expire = 3600)
    BmsTrPosDef getAddressByCustomId(Long customId, Integer entryId,Long b2bStoreId);

    String selectByCustomid(Long customid);
    /**
     * 通过客户ID 查询客户开票类型 ，结算方式，发票要求
     * @param customId
     * @return
     */
    BmsTrFetchDoc selectByCustomId(Long customId);

    String selectTranposIdBy(Long customId);

    /**
     * 获取客户资料
     * @param customId
     * @param entryId
     * @return
     */
    CustomerVo getByCustomId(Long customId, Integer entryId);

    List<Long> selectCanNotBuy(Long customId, Integer entryId);

    List<Long> selectCanBuy(Long customId, Integer entryId);

    BmsTrPosDef getCustomIdByStoreId(Integer entryId, Long b2bStoreId);
}
