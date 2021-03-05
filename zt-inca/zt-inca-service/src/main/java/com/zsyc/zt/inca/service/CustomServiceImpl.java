package com.zsyc.zt.inca.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.inca.entity.*;
import com.zsyc.zt.inca.mapper.*;
import com.zsyc.zt.inca.vo.*;
import com.zsyc.zt.util.NumericHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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


    @Resource
    PubEntryCustomerMapper pubEntryCustomerMapper;

    @Resource
    PubGoodsClassMapper pubGoodsClassMapper;
    @Resource
    PubTranslineDefMapper pubTranslineDefMapper;

    @Resource
    PubEmployeeMapper pubEmployeeMapper;


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

        AssertExt.notNull(customId, "客户ID" + customId + "不能为空");
        CustomerVo customerVo = pubCustomerMapper.getByCustomId(customId,entryId);
        if (ObjectUtil.isNull(customerVo)) {

            result = ResultVo.failErrorMessageAppend(result, "客户ID" + customId + "资料不存在");
        }


        if(ObjectUtil.notEqual(1,customerVo.getSausestatus())){
            AssertExt.fail("客户ID" + customId + "不可销售");
        }

        if(ObjectUtil.notEqual(1,customerVo.getGspusestatus())){
            AssertExt.fail("客户ID" + customId + "质量状态非正常");
        }

      /*  PubEntryCustomer pubEntryCustomer = pubEntryCustomerMapper.selectById(customId);*/



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
                queryWrapperLicense.lambda().eq(GspCompanyLicense::getUsestatus, 1);

                if(isPsCustom(customId,1)){
                    queryWrapperLicense.lambda().eq(GspCompanyLicense::getEntryid, 3);
                }else{
                    queryWrapperLicense.lambda().eq(GspCompanyLicense::getEntryid, 1);
                }

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
                        if (LocalDate.now().compareTo(gspCompanyLicense.getValidenddate()) > 0) {
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
                            if (LocalDate.now().compareTo(gspCompanyLicense.getValidenddate()) > 0) {
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
        if (ObjectUtil.isNotNull(creditDay) && ObjectUtil.isNotNull(creditDay.getDays()) && creditDay.getDays() > 0) {
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

                result = ResultVo.failErrorMessageAppend(result, "客户:" + customId + "欠款：" + overdraftAmount + "，大于设定的欠款额度" + credit.getCredit());
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

            Integer days = bmsCreditDays.getDays();

            if (ObjectUtil.isNotNull(days)) {
                return bmsCreditDays;

            }

        }

        return null;

    }

    @Override
    public Integer getCreditDays(Long customId, Integer entryId) {

        BmsCreditDays creditDay = getCreditDay(customId, entryId);
        if(ObjectUtil.isNotEmpty(creditDay)){
            return creditDay.getDays();
        }
        return 0;
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
    public BmsTrPosDef getAddressByCustomId(Long customId, Integer entryId) {
        QueryWrapper<BmsTrPosDef> queryWrapper = new QueryWrapper<>();

        queryWrapper.lambda().eq(BmsTrPosDef::getCompanyid, customId);

        queryWrapper.lambda().orderByAsc(BmsTrPosDef::getDefaultflag);
        List<BmsTrPosDef> result = bmsTrPosDefMapper.selectList(queryWrapper);
        return result.get(0);
    }

    @Override
    public Integer getPlacepointStorageIdByCustomId(Long customid) {
        return gpcsPlacepointMapper.getPlacepointStorageIdByCustomId(customid);
    }

    @Override
    public BmsTrFetchDoc getBmsTrFetchByCustomId(Long customId) {
        return bmsTrFetchDocMapper.getByCustomId(customId);

    }

    @Override
    public Long selectTranposIdBy(Long customId) {

        return bmsTrFetchDocMapper.getTranposIdBy(customId);

    }

    @Override
    public CustomerVo getByCustomId(Long customId, Integer entryId) {
        CustomerVo customerVo = new CustomerVo();
        PubCustomer pubCustomer = pubCustomerMapper.selectById(customId);



        QueryWrapper<PubEntryCustomer> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PubEntryCustomer::getCustomid,customId);
        queryWrapper.lambda().eq(PubEntryCustomer::getEntryid  ,entryId);
        PubEntryCustomer pubEntryCustomer  =  pubEntryCustomerMapper.selectOne(queryWrapper);

       BeanUtil.copyProperties(pubCustomer, customerVo);

        if (isPsCustom(customId, entryId)) {

            GpcsPlacepoint gpcsPlacepoint = gpcsPlacepointMapper.selectById(customId);

            BeanUtil.copyProperties(gpcsPlacepoint, customerVo);

        }
        customerVo.setSettletypeid(pubEntryCustomer.getSettletypeid());
        customerVo.setReqprintquflag(pubEntryCustomer.getReqprintquflag());
        return customerVo;
    }

    @Override
    public PubCustomToSaler getSalerByCustomId(Long customId, Integer entryId) {

        PubCustomToSaler result = pubCustomToSalerMapper.getSalerByCustomId(customId,entryId);
        return result;
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
        System.out.println(forbidGoods);


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
        List<Long> canNotBuy = selectCanNotBuy(customId, entryId);
        //List<Long> allGoodIds = pubGoods.stream().map(PubGoods::getGoodsid).collect(Collectors.toList());
        return allGoodIds.stream().parallel().filter(item->{
            return !canNotBuy.contains(item);
        }).collect(Collectors.toList());

        //return ListUtil.toList(CollectionUtil.subtract(allGoodIds, ));

    }


    @Override
    public List<CustomBusinessScopeVo> selectBusinessScopeByCustomId(Long customId){
        return pubCustomerMapper.selectBusinessScopeByCustomId(customId);
    }

    @Override
    public List<CustomBusinessScopeVo> selectBusinessScopeByCustomIdAndLicenseType(Long customId, Integer licenseType){
        return pubCustomerMapper.selectBusinessScopeByCustomIdAndLicenseType(customId,licenseType);
    }

    @Override
    public List<CustomBusinessScopeVo> selectBusinessScopeByCustomIdAndLicenseId(Long customId, Long licenseId) {
        return pubCustomerMapper.selectBusinessScopeByCustomIdAndLicenseId(customId,licenseId);
    }


    @Override
    public List<CustomLicenseVo> selectCustomLicenseByCustomId(Long customId){
        return pubCustomerMapper.selectCustomLicenseByCustomId(customId);
    }

    /**
     * 证照类型   1  客户证照    2 供应商证照 3 货品证照
     * @param licenseType
     * @return
     */
    @Override
    public List<GspLicenseType> selectLicenseType(Integer licenseType){

        QueryWrapper<GspLicenseType> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(GspLicenseType::getCustomflag,1);
        queryWrapper.ne("LICENSETYPEID",562);
        List<GspLicenseType> gspLicenseTypes = gspLicenseTypeMapper.selectList(queryWrapper);
        return gspLicenseTypes;
    }

    /**
     * 经营范围   1  顶层
     * @param level
     * @return
     */
    @Override
    public List<BusinessScopeVo> selectBusinessScope(Integer level){

        if(level == 1){
            return pubGoodsClassMapper.selectTopBusinessScope();
        }else{
            return null;
        }

    }

    /**
     * 获取所有的线路
     * @return
     */
    @Override
    public List<PubTranslineDef> selectAllTransline() {
        return pubTranslineDefMapper.selectList(new QueryWrapper<>());
    }

    /**
     * 校验人员是否存在
     * @param erpEmployeeId
     * @return
     */
    @Override
    public PubEmployee validEmployee(Long erpEmployeeId){

        PubEmployee pubEmployee = pubEmployeeMapper.selectById(erpEmployeeId);
        if(ObjectUtil.isEmpty(pubEmployee)){
            AssertExt.fail("人员ID不存在："+erpEmployeeId);
        }
        if(ObjectUtil.notEqual(pubEmployee.getUsestatus(),1)){
            AssertExt.fail("人员ID账号已经被停用："+erpEmployeeId);
        }
        return  pubEmployee;

    }

    @Override
    public Double getNotRecAmount(Long customId, Integer entryId) {
        return NumberUtil.round(getOverdraftAmount(customId, entryId),2).doubleValue();
    }

    @Override
    public Integer getRemainRecDays(Long customId, Integer entryId) {

        Integer longestOverdraftDay = pubCustomerMapper.getLongestOverdraftDay(customId, entryId);
        BmsCreditDays creditDay = this.getCreditDay(customId, entryId);
        if (ObjectUtil.isNotNull(creditDay) && ObjectUtil.isNotNull(creditDay.getDays()) && creditDay.getDays() > 0) {
            return creditDay.getDays() - longestOverdraftDay;
        }
        return 0;
    }

    /**
     * 通过仓库编号获取客户id
     * @param customId
     * @param storeId
     * @param entryId
     * @return
     */
    @Override
    public Long getByCustomStoreId(Long customId, Long storeId, Integer entryId) {
        QueryWrapper<BmsTrPosDef> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BmsTrPosDef::getB2bStoreId,storeId);
        queryWrapper.lambda().eq(BmsTrPosDef::getB2bSubCustomId,customId);
        BmsTrPosDef bmsTrPosDef = bmsTrPosDefMapper.selectOne(queryWrapper);
        return bmsTrPosDef.getCompanyid();
    }

}
