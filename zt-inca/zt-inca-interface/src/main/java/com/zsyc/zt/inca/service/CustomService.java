package com.zsyc.zt.inca.service;

import com.alicp.jetcache.anno.Cached;
import com.zsyc.zt.inca.entity.*;
import com.zsyc.zt.inca.vo.*;

import java.util.List;


/**
 * @date
 * @author peiqy
 */
public interface CustomService {

    @Cached(name="CustomService-valid-", key="#customId+''+#entryId", expire = 3)
    ResultVo valid(Long customId, Integer entryId);

    /**
     * 校验客户资料
     * @param customId
     * @return
     */

    @Cached(name="CustomService-validCustomData-", key="#customId+''+#entryId", expire = 3)
    ResultVo validCustomData(Long customId, Integer entryId);


    /**
     * 校验客户资料
     * @param customId
     * @return
     */
    @Cached(name="CustomService-validCustomLicense-", key="#customId+''+#entryId", expire =3)
    ResultVo validCustomLicense(Long customId, Integer entryId);


    @Cached(name="CustomService-getSalerByCustomId-", key="#customId+''+#entryId", expire = 3)
    PubCustomToSaler getSalerByCustomId(Long customId, Integer entryId);

    @Cached(name="CustomService-getCreditQuota-", key="#customId+''+#entryId", expire = 3)
    BmsCredit getCreditQuota(Long customId, Integer entryId);

    /**
     * 校验客户信用
     * @param customId
     * @param entryId
     * @return
     */
    @Cached(name="CustomService-validCredit-", key="#customId+''+#entryId", expire = 3)
    ResultVo validCredit(Long customId, Integer entryId);

    @Cached(name="CustomService-getOverdraftAmount-", key="#customId+''+#entryId", expire = 3)
    public Double getOverdraftAmount(Long customId,Integer entryId);

    @Cached(name="CustomService-getCreditDay-", key="#customId+''+#entryId", expire = 3)
    BmsCreditDays getCreditDay(Long customId, Integer entryId);

    @Cached(name="CustomService-getCreditDays-", key="#customId+''+#entryId", expire = 3)
    Integer getCreditDays(Long customId, Integer entryId);


    /**
     * 判断是否配送客户
     * @param customId
     * @param entryId
     * @return
     */
    @Cached(name="CustomService-isPsCustom-", key="#customId+':'+#entryId", expire = 3)
    Boolean isPsCustom(Long customId,Integer entryId);

    /**
     * 通过客户ID 查询客户信息
     * @param customId
     * @return
     */
    @Cached(name="CustomService-userCache-", key="#customId", expire = 3)
    PubCustomer getById(Long customId);

    /**
     * 通过客户ID 得到客户的地址
     * @param customId
     * @param entryId
     * @return
     */
    @Cached(name="CustomService-getAddressByCustomId-", key="#customId+':'+#entryId", expire = 3)
    BmsTrPosDef getAddressByCustomId(Long customId, Integer entryId);

    Integer getPlacepointStorageIdByCustomId(Long customid);
    /**
     * 通过客户ID 查询客户开票类型 ，结算方式，发票要求
     * @param customId
     * @return
     */
    @Cached(name="CustomService-getBmsTrFetchByCustomId-", key="#customId", expire = 3)
    BmsTrFetchDoc getBmsTrFetchByCustomId(Long customId);


    @Cached(name="CustomService-selectTranposIdBy-", key="#customId", expire = 3)
    Long selectTranposIdBy(Long customId);

    /**
     * 获取客户资料
     * @param customId
     * @param entryId
     * @return
     */
    @Cached(name = "CustomService-getByCustomId-",key = "#customId+''+#entryId",expire = 1)
    CustomerVo getByCustomId(Long customId, Integer entryId);

    @Cached(name = "CustomService-selectCanNotBuy-",key = "#customId+''+#entryId",expire = 1)
    List<Long> selectCanNotBuy(Long customId, Integer entryId);

    @Cached(name = "CustomService-selectCanBuy-",key = "#customId+''+#entryId",expire = 1)
    List<Long> selectCanBuy(Long customId, Integer entryId);

    /**
     * 获取客户经营范围
     * @param customId
     * @return
     */
    @Cached(name="CustomService-selectBusinessScopeByCustomId-", key="#customId", expire = 3)
    List<CustomBusinessScopeVo> selectBusinessScopeByCustomId(Long customId);

    List<CustomBusinessScopeVo> selectBusinessScopeByCustomIdAndLicenseType(Long customId, Integer licenseType);

    List<CustomBusinessScopeVo> selectBusinessScopeByCustomIdAndLicenseId(Long customId, Long licenseId);

    /**
     * 获取客户证照
     * @param customId
     * @return
     */
    @Cached(name="CustomService-selectCustomLicenseByCustomId-", key="#customId", expire = 3)
    List<CustomLicenseVo> selectCustomLicenseByCustomId(Long customId);

    /**
     * 证照类型   1  客户证照    2 供应商证照 3 货品证照
     * @param licenseType
     * @return
     */
    List<GspLicenseType> selectLicenseType(Integer licenseType);

    /**
     * 经营范围   1  顶层
     * @param level
     * @return
     */
    List<BusinessScopeVo> selectBusinessScope(Integer level);


    /**
     * 获取所有的线路
     * @return
     */
    @Cached(name="CustomService-selectAllTransline-", expire = 10)
    List<PubTranslineDef> selectAllTransline();


    PubEmployee validEmployee(Long erpEmployeeId);


    /**
     * 欠款
     * @param customId
     * @param entryId
     * @return
     */
    @Cached(name="CustomService-getNotRecAmount-", key="#customId+':'+#entryId", expire = 3)
    Double getNotRecAmount(Long customId,Integer entryId);

    /**
     * 剩余还款天数
     * @param customId
     * @param entryId
     * @return
     */
    @Cached(name="CustomService-getRemainRecDays-", key="#customId+':'+#entryId", expire = 3)
    Integer getRemainRecDays(Long customId,Integer entryId);
    @Cached(name="CustomService-getByCustomStoreId-", key="#customId+':'+#storeId+':'+#entryId", expire = 3)
    Long getByCustomStoreId(Long customId, Long storeId, Integer entryId);
}
