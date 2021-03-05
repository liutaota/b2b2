package com.zsyc.zt.order.api.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsyc.zt.order.api.service.CustomService;
import com.zsyc.zt.order.vo.CustomResultVo;
import com.zsyc.zt.order.vo.CustomerVo;
import com.zsyc.zt.order.vo.ResultVo;
import com.zsyc.zt.order.entity.*;
import com.zsyc.zt.order.inca.mapper.*;
import com.zsyc.zt.order.util.NumericHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class CustomServiceImpl implements CustomService {

    @Resource
    PubCustomerMapper pubCustomerMapper;

    @Resource
    BmsTrPosDefMapper bmsTrPosDefMapper;

    @Resource
    BmsSaInvInfoMapper bmsSaInvInfoMapper;

    @Resource
    PubCustomToSalerMapper pubCustomToSalerMapper;

    @Resource
    GspCompanyLicenseMapper gspCompanyLicenseMapper;

    @Resource
    GspLicenseTypeMapper gspLicenseTypeMapper;

    @Resource
    BmsCreditDaysMapper bmsCreditDaysMapper;

    @Resource
    BmsCreditMapper bmsCreditMapper;

    @Resource
    GpcsPlacepointMapper gpcsPlacepointMapper;
    @Resource
    BmsTrFetchDocMapper bmsTrFetchDocMapper;

    @Resource
    BmsForbidSalesMapper bmsForbidSalesMapper;

    @Resource
    PubGoodsMapper pubGoodsMapper;


    @Override
    public ResultVo valid(Long customId, Integer entryId) {

        ResultVo result = validCustomData(customId, entryId);
        result = ResultVo.failAppend(result, validCustomLicense(customId, entryId));
        result = ResultVo.failAppend(result, validCredit(customId, entryId));

        return ResultVo.validReturn(result);
    }

    /**
     * 校验客户资料
     *
     * @param customId
     * @return
     */
    @Override
    public ResultVo validCustomData(Long customId, Integer entryId) {

        ResultVo result = null;
        /**
         * 1，判断客户是否存在
         *
         * 2 线路
         *
         * 3 发票号
         *
         * 4 业务员
         */

        Assert.notNull(customId, "客户ID" + customId + "不能为空");
        PubCustomer pubCustomer = pubCustomerMapper.selectById(customId);
        if (ObjectUtil.isNull(pubCustomer)) {

            result = ResultVo.failErrorMessageAppend(result, "客户ID" + customId + "资料不存在");
        }
        QueryWrapper<BmsTrPosDef> queryWrapperLine = new QueryWrapper<>();
        /**
         *
         * 判断线路
         */
        queryWrapperLine.lambda().eq(BmsTrPosDef::getCompanyid, customId);
        queryWrapperLine.lambda().eq(BmsTrPosDef::getUsestatus, 1);
        List<BmsTrPosDef> lines = bmsTrPosDefMapper.selectList(queryWrapperLine);
        if (ObjectUtil.isEmpty(lines)) {
            result = CustomResultVo.failErrorMessageAppend(result, "客户ID" + customId + "线路没有维护");
        }


        /**
         * 判断发票号
         */

        QueryWrapper<BmsSaInvInfo> queryWrapperInv = new QueryWrapper<>();
        queryWrapperInv.lambda().eq(BmsSaInvInfo::getCompanyid, customId);
        List<BmsSaInvInfo> bmsSaInvInfos = bmsSaInvInfoMapper.selectList(queryWrapperInv);

        if (ObjectUtil.isEmpty(bmsSaInvInfos)) {
            result = CustomResultVo.failErrorMessageAppend(result, "客户ID" + customId + "发票号没有维护");
        }


        /**
         * 判断业务员
         */

        QueryWrapper<PubCustomToSaler> queryWrapperSaler = new QueryWrapper<>();

        queryWrapperSaler.lambda().eq(PubCustomToSaler::getCustomid, customId);


        queryWrapperSaler.lambda().eq(PubCustomToSaler::getEntryid, 1);


        List<PubCustomToSaler> pubCustomToSalers = pubCustomToSalerMapper.selectList(queryWrapperSaler);

        if (ObjectUtil.isEmpty(pubCustomToSalers)) {
            result = CustomResultVo.failErrorMessageAppend(result, "客户ID" + customId + "线路没有维护");
        }


        return ResultVo.validReturn(result);
    }

    @Override
    public ResultVo validCustomLicense(Long customId, Integer entryId) {


        ResultVo result = null;


        QueryWrapper<GspLicenseType> queryWrapperType = new QueryWrapper<>();


        queryWrapperType.lambda().in(GspLicenseType::getIsPremise, 1, 2);


        List<GspLicenseType> gspLicenseTypes = gspLicenseTypeMapper.selectList(queryWrapperType);

        /**
         * 如果 有设定先决条件，则必须校验这些证件是否过期
         */
        if (ObjectUtil.isNotEmpty(gspLicenseTypes)) {
            for (GspLicenseType gspLicenseType : gspLicenseTypes) {
                QueryWrapper<GspCompanyLicense> queryWrapperLicense = new QueryWrapper<>();

                queryWrapperLicense.lambda().eq(GspCompanyLicense::getCompanyid, customId);
                if(isPsCustom(customId,entryId)){
                    queryWrapperLicense.lambda().eq(GspCompanyLicense::getEntryid, 3);
                }else{
                    queryWrapperLicense.lambda().eq(GspCompanyLicense::getEntryid, 1);
                }
                queryWrapperLicense.lambda().eq(GspCompanyLicense::getUsestatus, 1);

                queryWrapperLicense.lambda().eq(GspCompanyLicense::getLicensetypeid, gspLicenseType.getLicensetypeid());

                List<GspCompanyLicense> gspCompanyLicenses = gspCompanyLicenseMapper.selectList(queryWrapperLicense);

                /**
                 * 1 必须有 并且 必须 时间期限有效
                 */
                if (1 == gspLicenseType.getIsPremise()) {
                    if (ObjectUtil.isEmpty(gspCompanyLicenses)) {
                        result = ResultVo.failErrorMessageAppend(result, "客户ID" + customId + "没有证照：" + gspLicenseType.getLicensename() + "");
                    }


                    for (GspCompanyLicense gspCompanyLicense : gspCompanyLicenses) {
                        /**
                         * 判断 证照 是否过期
                         */
                        if (LocalDateTime.now().compareTo(gspCompanyLicense.getValidenddate()) > 0) {
                            result = ResultVo.failErrorMessageAppend(result, "客户:" + customId + "的证照：" + gspLicenseType.getLicensename() + "已经过期");

                        }
                    }

                }

                /**
                 * 2 可以有 有话并且 必须 时间期限有效
                 */
                if (2 == gspLicenseType.getIsPremise()) {
                    if (ObjectUtil.isNotEmpty(gspCompanyLicenses)) {
                        for (GspCompanyLicense gspCompanyLicense : gspCompanyLicenses) {
                            /**
                             * 判断 证照 是否过期
                             */
                            if (LocalDateTime.now().compareTo(gspCompanyLicense.getValidenddate()) > 0) {
                                result = ResultVo.failErrorMessageAppend(result, "客户:" + customId + "的证照：" + gspLicenseType.getLicensename() + "已经过期");

                            }
                        }
                    }

                }

            }

        }


        return ResultVo.validReturn(result);
    }


    @Override
    public ResultVo validCredit(Long customId, Integer entryId) {
        ResultVo result = null;
        /**
         * 获取最长欠款天数
         */
        Integer longestOverdraftDay = pubCustomerMapper.getLongestOverdraftDay(customId, 1);


        BmsCreditDays creditDay = getCreditDay(customId, 1);
        if (creditDay != null) {

            if (longestOverdraftDay > creditDay.getDays()) {
                result = ResultVo.failErrorMessageAppend(result, "客户:" + customId + "的最长欠款天数为：" + longestOverdraftDay + "，大于设定的欠款天数" + creditDay.getDays());

            }
        }


        /**
         * 获取欠款
         */
        Double overdraftAmount = getOverdraftAmount(customId, entryId);

        BmsCredit credit = getCreditQuota(customId, 1);

        if (credit != null) {

            if (overdraftAmount > credit.getCredit()) {

                result = ResultVo.failErrorMessageAppend(result, "客户:" + customId + "欠款：" + overdraftAmount + "，大于设定的欠款天数" + credit.getCredit());
            }

        }
        return ResultVo.validReturn(result);
    }


    @Override
    public Double getOverdraftAmount(Long customId, Integer entryId) {

        /**
         * 存在还没生产发票的正式单,就表示还有单没有销账
         */

        Double amountNoSettle = pubCustomerMapper.getAmountNoSettle(customId, entryId);


        /**
         * 所有开了发票的单 的金额
         */

        Double amountSettle = pubCustomerMapper.getAmountSettle(customId, entryId);


        /**
         * 所有开了核销的金额
         */

        Double amountWriteOff = pubCustomerMapper.getAmountWriteOff(customId, entryId);


        Double totalNotRec = NumericHelper.null2Zero(amountNoSettle) + NumericHelper.null2Zero(amountSettle) - NumericHelper.null2Zero(amountWriteOff);
        return totalNotRec;
    }


    @Override
    public BmsCreditDays getCreditDay(Long customId, Integer entryId) {

        QueryWrapper<BmsCreditDays> queryWrapperCreditDays = new QueryWrapper<>();


        queryWrapperCreditDays.lambda().eq(BmsCreditDays::getEntryid, entryId);
        queryWrapperCreditDays.lambda().eq(BmsCreditDays::getCustomid, customId);
        List<BmsCreditDays> bmsCreditDaysList = bmsCreditDaysMapper.selectList(queryWrapperCreditDays);

        /***
         * 获取信用天数
         */
        if (ObjectUtil.isNotEmpty(bmsCreditDaysList)) {

            BmsCreditDays bmsCreditDays = bmsCreditDaysList.get(0);

            Double days = bmsCreditDays.getDays();

            if (ObjectUtil.isNotNull(days) && days > 0) {
                return bmsCreditDays;

            }

        }

        return null;

    }


    /**
     * 判断是否配送客户
     *
     * @param customId
     * @param entryId
     * @return
     */
    @Override
    public Boolean isPsCustom(Long customId, Integer entryId) {

        QueryWrapper<GpcsPlacepoint> QueryWrapper = new QueryWrapper<>();

        QueryWrapper.lambda().eq(GpcsPlacepoint::getPlacepointid, customId);

        Integer count = gpcsPlacepointMapper.selectCount(QueryWrapper);

        /**
         * 表示不存在此门店
         */
        if (count.equals(0)) {
            return false;
        }
        return true;
    }


    @Override
    public PubCustomer getById(Long customId) {

        return pubCustomerMapper.selectById(customId);

    }

    @Override
    public BmsTrPosDef getAddressByCustomId(Long customId, Integer entryId,Long b2bStoreId) {
        QueryWrapper<BmsTrPosDef> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda().eq(BmsTrPosDef::getCompanyid, customId);
        queryWrapper.lambda().eq(BmsTrPosDef::getB2bStoreId, b2bStoreId);
        queryWrapper.lambda().orderByAsc(BmsTrPosDef::getDefaultflag);
        List<BmsTrPosDef> result = bmsTrPosDefMapper.selectList(queryWrapper);
        return result.get(0);
    }

    @Override
    public String selectByCustomid(Long customid) {
        return pubCustomToSalerMapper.getStorageIdByPlacepointId(customid);
    }

    @Override
    public BmsTrFetchDoc selectByCustomId(Long customId) {
        return bmsTrFetchDocMapper.getByCustomId(customId);

    }

    @Override
    public String selectTranposIdBy(Long customId) {

        return bmsTrFetchDocMapper.getTranposIdBy(customId);

    }

    @Override
    public CustomerVo getByCustomId(Long customId, Integer entryId) {
        CustomerVo customerVo = new CustomerVo();
        PubCustomer pubCustomer = pubCustomerMapper.selectById(customId);

        BeanUtil.copyProperties(pubCustomer, customerVo);

        if (isPsCustom(customId, entryId)) {

            GpcsPlacepoint gpcsPlacepoint = gpcsPlacepointMapper.selectById(customId);

            BeanUtil.copyProperties(gpcsPlacepoint, customerVo);

        }
        return customerVo;
    }

    @Override
    public PubCustomToSaler getSalerByCustomId(Long customId, Integer entryId) {

        QueryWrapper<PubCustomToSaler> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda().eq(PubCustomToSaler::getEntryid, entryId);
        queryWrapper.lambda().eq(PubCustomToSaler::getCustomid, customId);
        queryWrapper.lambda().eq(PubCustomToSaler::getSalerid, 59);
        queryWrapper.lambda().orderByAsc(PubCustomToSaler::getZxFlag);
        List<PubCustomToSaler> result = pubCustomToSalerMapper.selectList(queryWrapper);
        return result.get(0);
    }


    @Override
    public BmsCredit getCreditQuota(Long customId, Integer entryId) {

        QueryWrapper<BmsCredit> queryWrapperCreditDays = new QueryWrapper<>();

        queryWrapperCreditDays.lambda().eq(BmsCredit::getEntryid, entryId);
        queryWrapperCreditDays.lambda().eq(BmsCredit::getCustomid, customId);
        List<BmsCredit> bmsCreditDaysList = bmsCreditMapper.selectList(queryWrapperCreditDays);
        /***
         * 获取信用天数
         */
        if (ObjectUtil.isNotEmpty(bmsCreditDaysList)) {

            BmsCredit bmsCreditDays = bmsCreditDaysList.get(0);
            Double days = bmsCreditDays.getCredit();

            if (ObjectUtil.isNotNull(days) && days > 0) {
                return bmsCreditDays;
            }
        }
        return null;
    }


    @Override
    public List<Long> selectCanNotBuy(Long customId, Integer entryId) {
        /**
         * 客户禁销集合
         */
        List<Long> forbidGoods = bmsForbidSalesMapper.selectForbidGoods(customId, entryId, 1);
        /**
         * 限制销售药品
         */
        List<Long> restrictGoodsForOther = bmsForbidSalesMapper.selectRestrictGoodsForOther(customId, entryId, 1);
        List<Long> restrictGoods = bmsForbidSalesMapper.selectRestrictGoods(customId, entryId, 1);

        return ListUtil.toList(CollectionUtil.subtract(CollectionUtil.union(forbidGoods, restrictGoodsForOther), restrictGoods));

    }

    @Override
    public List<Long> selectCanBuy(Long customId, Integer entryId) {

        QueryWrapper<PubGoods> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PubGoods::getB2bShopFlag, 1);
        List<Long> allGoodIds = pubGoodsMapper.selectGoodsIdList(queryWrapper);

        //List<Long> allGoodIds = pubGoods.stream().map(PubGoods::getGoodsid).collect(Collectors.toList());

        return ListUtil.toList(CollectionUtil.subtract(allGoodIds, selectCanNotBuy(customId, entryId)));

    }

    @Override
    public BmsTrPosDef getCustomIdByStoreId(Integer entryId, Long b2bStoreId) {
        //QueryWrapper<BmsTrPosDef> queryWrapperCreditDays = new QueryWrapper<>();

        //queryWrapperCreditDays.lambda().eq(BmsTrPosDef::getB2bStoreId, b2bStoreId);
        BmsTrPosDef bmsTrPosDef=bmsTrPosDefMapper.selectByStoreIdId(b2bStoreId);
        //queryWrapperCreditDays.lambda().eq(BmsCredit::getCustomid, customId);
        //List<BmsCredit> bmsCreditDaysList = bmsCreditMapper.selectList(queryWrapperCreditDays);
        return bmsTrPosDef;
    }

}
