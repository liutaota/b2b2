package com.zsyc.zt.inca.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.inca.entity.*;
import com.zsyc.zt.inca.mapper.*;
import com.zsyc.zt.inca.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.dubbo.config.annotation.Service;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 财务相关业务
 *
 * @author peiqy
 * @version 1.0
 * @email peiqy@foxmail.com
 * @date 2020/8/7 18:22
 */
@Service
@Slf4j
public class FinanceServiceImpl implements FinanceService {
    @Autowired
    BmsCreditBillDocMapper bmsCreditBillDocMapper;
    @Autowired
    BmsCreditBillDtlMapper bmsCreditBillDtlMapper;
    @Autowired
    BmsCertDtlTmpMapper bmsCertDtlTmpMapper;
    @Autowired
    BmsSaDtlMapper bmsSaDtlMapper;
    @Autowired
    BmsSaSettleDocMapper bmsSaSettleDocMapper;
    @Autowired
    BmsSaSettorecMapper bmsSaSettorecMapper;
    @Autowired
    BmsSaDoctosetMapper bmsSaDoctosetMapper;
    @Autowired
    BmsSaSettleDtlMapper bmsSaSettleDtlMapper;
    @Autowired
    PubCustomerMapper pubCustomerMapper;
    @Resource
    BmsSaRecDocMapper bmsSaRecDocMapper;
    @Resource
    PubEmployeeMapper pubEmployeeMapper;
    @Resource
    BmsSaRecDtlMapper bmsSaRecDtlMapper;

    @Resource
    B2bFinanceListMapper b2bFinanceListMapper;

    @Resource
    CustomService customService;

    @Resource
    RedissonClient redissonClient;

    @Resource
    BmsSaDocMapper bmsSaDocMapper;

    @Resource
    BmsSaToSaMapper bmsSaToSaMapper;


    @Resource
    IncaService incaService;

    /**
     * @param financeVerificationDocVo
     * @return
     * @Author peiqy
     * 核销接口升级 版本
     */
    @Override
    public FinanceVerificationResultDocVo verificationV3(FinanceVerificationDocVo financeVerificationDocVo) {


        FinanceVerificationResultDocVo result = null;
        AssertExt.notNull(financeVerificationDocVo, "参数对象为空");

        Long customId = financeVerificationDocVo.getCustomId();
        String orderNo = financeVerificationDocVo.getOrderNo();
        Long orderId = financeVerificationDocVo.getOrderId();
        Integer verificationType = financeVerificationDocVo.getVerificationType();

        AssertExt.notNull(customId, "客户ID为空");

        CustomerVo cusl = pubCustomerMapper.getCustomInfo(customId);

        //客户类型 配送客户 true
        boolean isPs = customService.isPsCustom(customId, financeVerificationDocVo.getEntryId());

        /**
         * 制单人信息
         */
        PubEmployee inputManL = pubEmployeeMapper.selectById(financeVerificationDocVo.getInputManId());//获取制单人信息
        AssertExt.notNull(inputManL, "制单人信息为空");

        if (ObjectUtil.isEmpty(financeVerificationDocVo.getFinanceVerificationDtlVoList())) {
            AssertExt.fail("核销数据是空的！无法核销");
        }


        RLock lock = redissonClient.getLock("order_finance_verification:" + orderId);
        /**
         * 获取锁资源
         * 2分钟自动解锁
         */
        lock.lock(2, TimeUnit.MINUTES);

        try {

            log.info("订单下发程序---------业务逻辑----------");
            log.info("订单下发程序---------业务逻辑----------");
            QueryWrapper<B2bFinanceList> queryWrapper = new QueryWrapper<>();
            /**
             *
             */
            queryWrapper.lambda().eq(B2bFinanceList::getB2bOrderNo, orderNo);
            queryWrapper.lambda().eq(B2bFinanceList::getVerificationType, verificationType);
            queryWrapper.lambda().eq(B2bFinanceList::getVersion, financeVerificationDocVo.getVersion());

            if (b2bFinanceListMapper.selectCount(queryWrapper) == 0) {
                /**
                 * 核销信息保留一份，留底
                 */
                B2bFinanceList b2bFinanceList = new B2bFinanceList();

                b2bFinanceList.setB2bOrderNo(financeVerificationDocVo.getOrderNo());
                b2bFinanceList.setB2bCustomId(financeVerificationDocVo.getCustomId());
                b2bFinanceList.setB2bOrderId(orderId);
                b2bFinanceList.setCreateDate(LocalDateTime.now());
                b2bFinanceList.setVersion(financeVerificationDocVo.getVersion());
                b2bFinanceList.setVerificationType(financeVerificationDocVo.getVerificationType());

                b2bFinanceList.setSrdData(JSON.toJSONString(financeVerificationDocVo, SerializerFeature.PrettyFormat,
                        SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.UseSingleQuotes));

                b2bFinanceListMapper.insert(b2bFinanceList);
            } else {
                AssertExt.fail("核销数据重复下发！！!!!!");
            }

            if (!incaService.checkBusinessOperationIsPermit(financeVerificationDocVo.getVerificationDate(), financeVerificationDocVo.getEntryId())) {
                AssertExt.fail("当前的核销日期不允许业务操作！！!!!!");
            }

            validFinanceVerificationData(financeVerificationDocVo);


            /**
             * 在线支付核销
             */
            if (ObjectUtil.equal(verificationType, 1)) {


                this.verificationOnlineV3(financeVerificationDocVo);


            }
            /**
             * 现结核销
             */
            if (ObjectUtil.equal(verificationType, 2)) {
                this.verificationOnfaceV3(financeVerificationDocVo);
            }

            /**
             * 月结核销
             */
            if (ObjectUtil.equal(verificationType, 3)) {
                this.verificationOnmonthV3(financeVerificationDocVo);
            }

            /**
             * 退单核销
             */
            if (ObjectUtil.equal(verificationType, 3)) {
                this.verificationOnbackV3(financeVerificationDocVo);
            }


        } catch (Throwable e) {
            e.printStackTrace();
            log.error("执行出现了异常!", e.getMessage());
            AssertExt.fail("执行出现了异常:" + e.getMessage(), e);
        } finally {
            log.info("核销订单下发程序---------解锁----------");
            lock.unlock();
        }


        return FinanceVerificationResultDocVo.validReturn(result, financeVerificationDocVo.getOrderNo(), financeVerificationDocVo.getCustomId());


    }


    /**
     * @param financeVerificationDocVo
     * @return
     * @Author peiqy
     * 核销接口升级 版本
     */
    @Override
    public FinanceVerificationResultDocVo verificationV2(FinanceVerificationDocVo financeVerificationDocVo) {


        FinanceVerificationResultDocVo result = null;
        AssertExt.notNull(financeVerificationDocVo, "参数对象为空");

        Long customId = financeVerificationDocVo.getCustomId();
        String orderNo = financeVerificationDocVo.getOrderNo();
        Long orderId = financeVerificationDocVo.getOrderId();
        Integer verificationType = financeVerificationDocVo.getVerificationType();

        AssertExt.notNull(customId, "客户ID为空");

        CustomerVo cusl = pubCustomerMapper.getCustomInfo(customId);

        //客户类型 配送客户 true
        boolean isPs = customService.isPsCustom(customId, financeVerificationDocVo.getEntryId());

        /**
         * 制单人信息
         */
        PubEmployee inputManL = pubEmployeeMapper.selectById(financeVerificationDocVo.getInputManId());//获取制单人信息
        AssertExt.notNull(inputManL, "制单人信息为空");

        if (ObjectUtil.isEmpty(financeVerificationDocVo.getFinanceVerificationDtlVoList())) {
            AssertExt.fail("核销数据是空的！无法核销");
        }


        RLock lock = redissonClient.getLock("order_finance_verification:" + orderId);
        /**
         * 获取锁资源
         * 2分钟自动解锁
         */
        lock.lock(2, TimeUnit.MINUTES);

        try {

            log.info("订单下发程序---------业务逻辑----------");
            log.info("订单下发程序---------业务逻辑----------");
            QueryWrapper<B2bFinanceList> queryWrapper = new QueryWrapper<>();
            /**
             *
             */
            queryWrapper.lambda().eq(B2bFinanceList::getB2bOrderNo, orderNo);
            queryWrapper.lambda().eq(B2bFinanceList::getVerificationType, verificationType);
            queryWrapper.lambda().eq(B2bFinanceList::getVersion, financeVerificationDocVo.getVersion());

            if (b2bFinanceListMapper.selectCount(queryWrapper) == 0) {
                /**
                 * 核销信息保留一份，留底
                 */
                B2bFinanceList b2bFinanceList = new B2bFinanceList();

                b2bFinanceList.setB2bOrderNo(financeVerificationDocVo.getOrderNo());
                b2bFinanceList.setB2bCustomId(financeVerificationDocVo.getCustomId());
                b2bFinanceList.setB2bOrderId(orderId);
                b2bFinanceList.setCreateDate(LocalDateTime.now());
                b2bFinanceList.setVersion(financeVerificationDocVo.getVersion());
                b2bFinanceList.setVerificationType(financeVerificationDocVo.getVerificationType());

                b2bFinanceList.setSrdData(JSON.toJSONString(financeVerificationDocVo, SerializerFeature.PrettyFormat,
                        SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.UseSingleQuotes));

                b2bFinanceListMapper.insert(b2bFinanceList);
            } else {
                AssertExt.fail("核销数据重复下发！！!!!!");
            }

            if (!incaService.checkBusinessOperationIsPermit(financeVerificationDocVo.getVerificationDate(), financeVerificationDocVo.getEntryId())) {
                AssertExt.fail("当前的核销日期不允许业务操作！！!!!!");
            }

            /**
             * 手工补上一些数据
             */
            financeVerificationDocVo.getFinanceVerificationDtlVoList().forEach(financeVerificationDtlVo -> {
                /**
                 * 在线支付核销
                 */
                if (ObjectUtil.equal(verificationType, 1)) {
                    if(isPs){
                        financeVerificationDtlVo.setSourceType(2);
                    }else{
                        financeVerificationDtlVo.setSourceType(1);
                    }

            }});

            validFinanceVerificationData(financeVerificationDocVo);


            /**
             * 在线支付核销
             */
            if (ObjectUtil.equal(verificationType, 1)) {


                this.verificationOnlineV2(financeVerificationDocVo);
            }
            /**
             * 现结核销
             */
            if (ObjectUtil.equal(verificationType, 2)) {
                this.verificationOnfaceV2(financeVerificationDocVo);
            }

            /**
             * 月结核销
             */
            if (ObjectUtil.equal(verificationType, 3)) {
                this.verificationOnmonthV2(financeVerificationDocVo);
            }

            /**
             * 退单核销
             */
            if (ObjectUtil.equal(verificationType, 3)) {
                this.verificationOnbackV2(financeVerificationDocVo);
            }


        } catch (Throwable e) {
            e.printStackTrace();
            log.error("执行出现了异常!", e.getMessage());
            AssertExt.fail("执行出现了异常:" + e.getMessage(), e);
        } finally {
            log.info("核销订单下发程序---------解锁----------");
            lock.unlock();
        }


        return FinanceVerificationResultDocVo.validReturn(result, financeVerificationDocVo.getOrderNo(), financeVerificationDocVo.getCustomId());


    }

    private void validFinanceVerificationData(FinanceVerificationDocVo financeVerificationDocVo) {
        //校验重复
        List<FinanceVerificationDtlVo> financeVerificationDocList = financeVerificationDocVo.getFinanceVerificationDtlVoList();
        if (financeVerificationDocList != null && financeVerificationDocList.size() > 0) {
            //Set<Long> check = new HashSet<>(financeVerificationDocList.size());
            List<Long> temp = financeVerificationDocList.stream().map(FinanceVerificationDtlVo::getSrcErpOrderId).distinct().collect(Collectors.toList());
            if(temp.size()<financeVerificationDocList.size()){
                AssertExt.fail("有重复的核销订单,请检查核销数据"  );
            }
            /*for (Long srcErpOrderDtlId : temp) {
                if (check.contains(srcErpOrderDtlId)) {
                    AssertExt.fail("有重复的订单:" + srcErpOrderDtlId);
                }
                check.add(srcErpOrderDtlId);
            }*/
        }


        //客户类型 配送客户 true
        //boolean isPs = customService.isPsCustom(financeVerificationDocVo.getCustomId(), financeVerificationDocVo.getEntryId());


        /**
         * 配送客户,销售客户，查配送单总单金额是否对的上
         */
        if (financeVerificationDocList != null && financeVerificationDocList.size() > 0) {
            financeVerificationDocList.forEach(financeVerificationDtlVo -> {
                Double notRecMoney = bmsSaDocMapper.getNotRecMoney(financeVerificationDtlVo.getSrcErpOrderId(), financeVerificationDtlVo.getSourceType());
                if (ObjectUtil.notEqual(notRecMoney, financeVerificationDtlVo.getAmount())) {
                    AssertExt.fail("核销金额与订单金额不一致,%s:%d,ERP未核销金额：%f,核销金额却为：%f", getSourceTypeStr(financeVerificationDtlVo.getSourceType()), financeVerificationDtlVo.getSrcErpOrderId(), notRecMoney, financeVerificationDtlVo.getAmount());
                }
            });
        }


    }

    private String getSourceTypeStr(Integer sourceType){
        if(ObjectUtil.equal(sourceType,1)){
            return "销售单";
        }
        if(ObjectUtil.equal(sourceType,2)){
            return "配送单";
        }
        if(ObjectUtil.equal(sourceType,3)){
            return "销退单";
        }
        return "问题单";
    }


    /**
     * 到货支付核销
     *
     * @param financeVerificationDocVo
     */
    private void verificationOnlineV3(FinanceVerificationDocVo financeVerificationDocVo) {
        /**
         * 生成临时收款记录
         */
        BmsSaRecDoc bmsSaRecDoc = genTmpRecRecordV3(financeVerificationDocVo);
        confirmTmpRecRecordV3(bmsSaRecDoc, financeVerificationDocVo);
    }

    /**
     * 在线支付核销
     *
     * @param financeVerificationDocVo
     */
    private void verificationOnlineV2(FinanceVerificationDocVo financeVerificationDocVo) {
        Boolean isPs = customService.isPsCustom(financeVerificationDocVo.getCustomId(), financeVerificationDocVo.getEntryId());

        /**
         * 单据开始核销，生成临时单
         */
        financeVerificationDocVo.getFinanceVerificationDtlVoList().forEach(financeVerificationDtlVo -> {
            Long srcErpOrderId = financeVerificationDtlVo.getSrcErpOrderId();
            if (isPs) {
                srcErpOrderId = bmsSaDocMapper.getSalesIdByPlacesupplyId(srcErpOrderId,financeVerificationDocVo.getCustomId());
            }

            /**
             * 生成临时收款记录
             */
            BmsSaRecDoc bmsSaRecDoc = genTmpRecRecordV2(srcErpOrderId, financeVerificationDtlVo, financeVerificationDocVo);
            confirmTmpRecRecordV2(bmsSaRecDoc, financeVerificationDtlVo, financeVerificationDocVo);

        });

        /* *//**
         * 单据开始核销，确定订单
         *//*
        financeVerificationDocVo.getFinanceVerificationDtlVoList().forEach(financeVerificationDtlVo -> {

            *//**
         * 生成业务产品与核算产品接口表记录bms_cert_dtl_tmp
         *//*
            //addBmsCertDtlTmp(bmsSaRecDoc.getSarecid());
        });*/

    }

    private void confirmTmpRecRecordV2(BmsSaRecDoc bmsSaRecDoc, FinanceVerificationDtlVo financeVerificationDtlVo, FinanceVerificationDocVo financeVerificationDocVo) {


        List<BmsSaSettorecVo> bmsSaSettorecList = bmsSaSettorecMapper.selectBySaRecId(bmsSaRecDoc.getSarecid());


        bmsSaSettorecList.forEach(bmsSaSettorecVo -> {
            /**
             * 更新发票总单最终收款金额
             */
           // bmsSaSettleDocMapper.updateZxTotalRecmoney(bmsSaSettorecVo.getSasettledtlid(), bmsSaSettorecVo.getTotalLine());
            bmsSaSettleDtlMapper.updateTotalRecMoney(bmsSaSettorecVo.getSasettledtlid(), bmsSaSettorecVo.getTotalLine());

            /**
             * 更新销售细单的最终回款金额
             */
            bmsSaDtlMapper.updateTotalRecMoney(bmsSaSettorecVo.getSasettledtlid(), bmsSaSettorecVo.getSalesdtlid());

            bmsSaSettleDtlMapper.deleteBmsSaSettleDtlTmp(bmsSaSettorecVo.getSasettledtlid());
        });


        /**
         * 确认收款单
         * 填写确认人，状态变正式，确认时间
         */
        bmsSaRecDoc.setConfirmmanid(financeVerificationDocVo.getConfirmManId());
        bmsSaRecDoc.setUsestatus(1);
        bmsSaRecDoc.setConfirmdate(LocalDateTime.now());
        bmsSaRecDocMapper.updateById(bmsSaRecDoc);


    }

    private void confirmTmpRecRecordV3(BmsSaRecDoc bmsSaRecDoc,  FinanceVerificationDocVo financeVerificationDocVo) {


        List<BmsSaSettorecVo> bmsSaSettorecList = bmsSaSettorecMapper.selectBySaRecId(bmsSaRecDoc.getSarecid());


        bmsSaSettorecList.forEach(bmsSaSettorecVo -> {
            /**
             * 更新发票总单最终收款金额
             */
            // bmsSaSettleDocMapper.updateZxTotalRecmoney(bmsSaSettorecVo.getSasettledtlid(), bmsSaSettorecVo.getTotalLine());
            bmsSaSettleDtlMapper.updateTotalRecMoney(bmsSaSettorecVo.getSasettledtlid(), bmsSaSettorecVo.getTotalLine());

            /**
             * 更新销售细单的最终回款金额
             */
            bmsSaDtlMapper.updateTotalRecMoney(bmsSaSettorecVo.getSasettledtlid(), bmsSaSettorecVo.getSalesdtlid());

            bmsSaSettleDtlMapper.deleteBmsSaSettleDtlTmp(bmsSaSettorecVo.getSasettledtlid());
        });


        /**
         * 确认收款单
         * 填写确认人，状态变正式，确认时间
         */
        bmsSaRecDoc.setConfirmmanid(financeVerificationDocVo.getConfirmManId());
        bmsSaRecDoc.setUsestatus(1);
        bmsSaRecDoc.setConfirmdate(LocalDateTime.now());
        bmsSaRecDocMapper.updateById(bmsSaRecDoc);

        /**
         * 裴秋云 财务需要
         * 生成业务产品与核算产品接口表记录bms_cert_dtl_tmp
         */
        saveBmsCertDtlTmp(bmsSaRecDoc.getSarecid());
        /** */

    }


    /**
     * 退单核销
     *
     * @param srcErpOrderDtlId
     * @param financeVerificationDtlVo
     * @param financeVerificationDocVo
     * @return
     */
    private BmsSaRecDoc genTmpRecRecordForBackV2(Long srcErpOrderDtlId, FinanceVerificationDtlVo financeVerificationDtlVo, FinanceVerificationDocVo financeVerificationDocVo) {
        BmsSaRecDoc bmsSaRecDoc = new BmsSaRecDoc();
        CustomerVo customerVo = customService.getByCustomId(financeVerificationDocVo.getCustomId(), financeVerificationDocVo.getEntryId());
        bmsSaRecDoc.setCredate(financeVerificationDocVo.getVerificationDate());//业务日期

        /**
         * 制单人信息
         */
        PubEmployee inputManL = pubEmployeeMapper.selectById(financeVerificationDocVo.getInputManId());//获取制单人信息
        /**
         * 收款类别:1.预收款;2.现收;3.收欠款
         */
        bmsSaRecDoc.setRecmethod(3);
        /**
         * 使用状态:0.作废;1.正式;2.临时
         */
        bmsSaRecDoc.setUsestatus(2);
        /**
         * 客户信息
         */
        /**
         * customid,customname,fmid,fmname,fmrate,fmopcode,settletypeid
         */
        /**
         * 客户ID
         */
        bmsSaRecDoc.setCustomid(customerVo.getCustomid());
        /**
         * 客户
         */
        bmsSaRecDoc.setCustomname(customerVo.getCustomname());
        /**
         * 结算方式：1.现结；2.立即支票；3.电汇；4.银行转账；5.承兑汇票；6.应收抵应付；8.内销；11.麻黄碱；9.半月结；10.月结；12.周结
         */
        //bmsSaRecDoc.setRectypeid(cusl.getSettletypeid());
        bmsSaRecDoc.setRectypeid(4);
        /**
         * 外币id
         */
        bmsSaRecDoc.setFmid(customerVo.getFmid());
        /**
         * 汇率
         */
        bmsSaRecDoc.setExchange(customerVo.getFmrate());
        /**
         * 汇票日期
         */
        //        bmsSaRecDoc.setDraftdate(credate);//汇票日期
        /**
         * 独立单元
         */
        bmsSaRecDoc.setEntryid(financeVerificationDocVo.getEntryId().longValue());
        /**
         * 制单人ID
         */
        bmsSaRecDoc.setInputmanid(financeVerificationDocVo.getInputManId());
        /**
         * 收款人ID
         */
        bmsSaRecDoc.setRecmanid(financeVerificationDocVo.getInputManId());

        bmsSaRecDoc.setMemo("B2B线上退单,支付流水号：" + financeVerificationDocVo.getPayFlowNo() + ",订单总金额：" + financeVerificationDtlVo.getAmount());
        bmsSaRecDoc.setBankid(financeVerificationDocVo.getBankId());
        bmsSaRecDoc.setCredate(financeVerificationDocVo.getPayDate());

        bmsSaRecDoc.setPaytype(financeVerificationDocVo.getPayType());

        bmsSaRecDoc.setIsAutoVerification(2);
        bmsSaRecDoc.setTotal(financeVerificationDtlVo.getAmount());


        BmsSaDtl bmsSaDtl = bmsSaDtlMapper.selectById(srcErpOrderDtlId);


        bmsSaRecDoc.setDtlLines(1);

        /**
         * 细单条数
         */
        bmsSaRecDocMapper.insert(bmsSaRecDoc);


        BmsSaRecDtl bmsSaRecDtl = new BmsSaRecDtl();


        //List<BmsSaDoctoset> bmsSaDoctosetList = getBmsSaDoctosetList(bmsSaDtl.getSalesdtlid());
        BmsSaSettleDtl bmsSaSettleDtl = bmsSaSettleDtlMapper.getBySalesdtlid(bmsSaDtl.getSalesdtlid());
        BeanUtil.copyProperties(bmsSaSettleDtl, bmsSaRecDtl);

        /**
         * 业务员id
         */
        bmsSaRecDtl.setSalerid(bmsSaSettleDtl.getSalerid());
        /**
         * 业务部门ID
         */
        bmsSaRecDtl.setSalesdeptid(bmsSaSettleDtl.getSalesdeptid());
        /**
         * 收款员ID
         */
        bmsSaRecDtl.setRecsalerid(inputManL.getEmployeeid());
        /**
         * 收款部门ID
         */
        bmsSaRecDtl.setRecsalesdeptid(inputManL.getDeptid());

        bmsSaRecDtl.setSarecid(bmsSaRecDoc.getSarecid());
        /**
         * 先保存细单
         */
        bmsSaRecDtlMapper.insert(bmsSaRecDtl);

        BmsSaSettorec bmsSaSettorec = new BmsSaSettorec();
        bmsSaSettorec.setGoodsqty(bmsSaRecDtl.getGoodsqty());
        /**
         * 收款金额
         */
        bmsSaSettorec.setTotalLine(bmsSaRecDtl.getTotalLine());
        /**
         * 回款单号
         */
        bmsSaSettorec.setSarecdtlid(bmsSaRecDtl.getSarecdtlid());
        /**
         * 发票单号
         * 备注：分析ERP系统代码发现，bms_sa_settorec表的sasettledtlid存的是销售细单id
         * 个人认为正确的数据存取代码：str.setSasettledtlid(bmsSaDoctosetList.get(0).getSasettledtlid());
         */
        bmsSaSettorec.setSasettledtlid(bmsSaSettleDtl.getSasettledtlid());

        bmsSaSettorecMapper.insert(bmsSaSettorec);


        //bmsSaRecDocMapper.updateById(bmsSaRecDoc);
        return bmsSaRecDoc;
    }


    private BmsSaRecDoc genTmpRecRecordV3(FinanceVerificationDocVo financeVerificationDocVo) {
        BmsSaRecDoc bmsSaRecDoc = new BmsSaRecDoc();
        CustomerVo customerVo = customService.getByCustomId(financeVerificationDocVo.getCustomId(), financeVerificationDocVo.getEntryId());
        bmsSaRecDoc.setCredate(financeVerificationDocVo.getVerificationDate());//业务日期

        /**
         * 制单人信息
         */
        PubEmployee inputManL = pubEmployeeMapper.selectById(financeVerificationDocVo.getInputManId());//获取制单人信息

        bmsSaRecDoc.setB2bOrderNo(financeVerificationDocVo.getOrderNo());
        bmsSaRecDoc.setB2bPayFlowNo(financeVerificationDocVo.getPayFlowNo());

        /**
         * 方便修复数据！
         */
        bmsSaRecDoc.setB2bOrderId(financeVerificationDocVo.getOrderId());
        bmsSaRecDoc.setZxBhFlag(11);
        /**
         * 收款类别:1.预收款;2.现收;3.收欠款
         */
        bmsSaRecDoc.setRecmethod(3);
        /**
         * 使用状态:0.作废;1.正式;2.临时
         */
        bmsSaRecDoc.setUsestatus(2);
        /**
         * 客户信息
         */
        /**
         * customid,customname,fmid,fmname,fmrate,fmopcode,settletypeid
         */
        /**
         * 客户ID
         */
        bmsSaRecDoc.setCustomid(customerVo.getCustomid());
        /**
         * 客户
         */
        bmsSaRecDoc.setCustomname(customerVo.getCustomname());
        /**
         * 结算方式：1.现结；2.立即支票；3.电汇；4.银行转账；5.承兑汇票；6.应收抵应付；8.内销；11.麻黄碱；9.半月结；10.月结；12.周结
         */
        //bmsSaRecDoc.setRectypeid(cusl.getSettletypeid());
        if(ObjectUtil.equal("CASH",financeVerificationDocVo.getPayType())){
            bmsSaRecDoc.setRectypeid(1);
           // bmsSaRecDoc.setMemo("B2B现金支付,订单总金额：" + financeVerificationDocVo.getAmountVerification());
            bmsSaRecDoc.setMemo(financeVerificationDocVo.getRemark());
        }else if(ObjectUtil.equal("ON_LINE",financeVerificationDocVo.getPayType())){
            bmsSaRecDoc.setRectypeid(4);
            bmsSaRecDoc.setBankid(financeVerificationDocVo.getBankId());
            //bmsSaRecDoc.setMemo("B2B线上支付,支付流水号：" + financeVerificationDocVo.getPayFlowNo() + ",订单总金额：" + financeVerificationDocVo.getAmountVerification());
            bmsSaRecDoc.setMemo(financeVerificationDocVo.getRemark());
        }
        /**
         * 外币id
         */
        bmsSaRecDoc.setFmid(customerVo.getFmid());
        /**
         * 汇率
         */
        bmsSaRecDoc.setExchange(customerVo.getFmrate());
        /**
         * 汇票日期
         */
        //        bmsSaRecDoc.setDraftdate(credate);//汇票日期
        /**
         * 独立单元
         */
        bmsSaRecDoc.setEntryid(financeVerificationDocVo.getEntryId().longValue());
        /**
         * 制单人ID
         */
        bmsSaRecDoc.setInputmanid(financeVerificationDocVo.getInputManId());
        /**
         * 收款人ID
         */
        bmsSaRecDoc.setRecmanid(financeVerificationDocVo.getInputManId());

        bmsSaRecDoc.setPaytype(financeVerificationDocVo.getPayType());

        bmsSaRecDoc.setIsAutoVerification(2);

        bmsSaRecDoc.setTotal(financeVerificationDocVo.getAmountVerification());
        bmsSaRecDoc.setPremoney(financeVerificationDocVo.getAmountVerification()+financeVerificationDocVo.getAmountDiscrepancy());

        if(ObjectUtil.notEqual(financeVerificationDocVo.getAmountDiscrepancy(),0)){
            /**
             * 加上差异金额
             */
            bmsSaRecDoc.setRecexpmoney(financeVerificationDocVo.getAmountDiscrepancy());
            /**
             * 转出类型  1  让利  9  其他
             */
            bmsSaRecDoc.setRecexptype(9);
        }

        /**
         * 细单条数
         */
        bmsSaRecDocMapper.insert(bmsSaRecDoc);

        List<FinanceVerificationDtlVo> financeVerificationDtlVoList = financeVerificationDocVo.getFinanceVerificationDtlVoList();

        //Boolean isPs = customService.isPsCustom(financeVerificationDocVo.getCustomId(), financeVerificationDocVo.getEntryId());

        Integer dtlLines = 0;

        for (FinanceVerificationDtlVo financeVerificationDtlVo : financeVerificationDtlVoList) {


            Long srcErpOrderId = financeVerificationDtlVo.getSrcErpOrderId();
            /*if (isPs) {
                srcErpOrderId = bmsSaDocMapper.getSalesIdByPlacesupplyId(srcErpOrderId, financeVerificationDocVo.getCustomId());
            }*/

            List<BmsSaDtl> bmsSaDtls = bmsSaDtlMapper.selectListBy(srcErpOrderId,financeVerificationDtlVo.getSourceType());


            //bmsSaRecDoc.setDtlLines(bmsSaDtls.size());
            dtlLines += bmsSaDtls.size();


            bmsSaDtls.forEach(bmsSaDtl -> {

                BmsSaRecDtl bmsSaRecDtl = new BmsSaRecDtl();


                //List<BmsSaDoctoset> bmsSaDoctosetList = getBmsSaDoctosetList(bmsSaDtl.getSalesdtlid());
                BmsSaSettleDtl bmsSaSettleDtl = bmsSaSettleDtlMapper.getBySalesdtlid(bmsSaDtl.getSalesdtlid());
                BeanUtil.copyProperties(bmsSaSettleDtl, bmsSaRecDtl);
                /**
                 * 业务员id
                 */
                bmsSaRecDtl.setSalerid(bmsSaSettleDtl.getSalerid());
                /**
                 * 业务部门ID
                 */
                bmsSaRecDtl.setSalesdeptid(bmsSaSettleDtl.getSalesdeptid());
                /**
                 * 收款员ID
                 */
                bmsSaRecDtl.setRecsalerid(inputManL.getEmployeeid());
                /**
                 * 收款部门ID
                 */
                bmsSaRecDtl.setRecsalesdeptid(inputManL.getDeptid());

                bmsSaRecDtl.setSarecid(bmsSaRecDoc.getSarecid());
                /**
                 * 先保存细单
                 */
                bmsSaRecDtlMapper.insert(bmsSaRecDtl);

                BmsSaSettorec bmsSaSettorec = new BmsSaSettorec();
                bmsSaSettorec.setGoodsqty(bmsSaRecDtl.getGoodsqty());
                /**
                 * 收款金额
                 */
                bmsSaSettorec.setTotalLine(bmsSaRecDtl.getTotalLine());
                /**
                 * 回款单号
                 */
                bmsSaSettorec.setSarecdtlid(bmsSaRecDtl.getSarecdtlid());
                /**
                 * 发票单号
                 * 备注：分析ERP系统代码发现，bms_sa_settorec表的sasettledtlid存的是销售细单id
                 * 个人认为正确的数据存取代码：str.setSasettledtlid(bmsSaDoctosetList.get(0).getSasettledtlid());
                 */
                bmsSaSettorec.setSasettledtlid(bmsSaSettleDtl.getSasettledtlid());

                bmsSaSettorecMapper.insert(bmsSaSettorec);

            });

        }
        bmsSaRecDoc.setDtlLines(dtlLines);
        bmsSaRecDocMapper.updateById(bmsSaRecDoc);

        return bmsSaRecDoc;

    }

    private BmsSaRecDoc genTmpRecRecordV2(Long srcErpOrderId, FinanceVerificationDtlVo financeVerificationDtlVo, FinanceVerificationDocVo financeVerificationDocVo) {
        BmsSaRecDoc bmsSaRecDoc = new BmsSaRecDoc();
        CustomerVo customerVo = customService.getByCustomId(financeVerificationDocVo.getCustomId(), financeVerificationDocVo.getEntryId());
        bmsSaRecDoc.setCredate(financeVerificationDocVo.getVerificationDate());//业务日期

        /**
         * 制单人信息
         */
        PubEmployee inputManL = pubEmployeeMapper.selectById(financeVerificationDocVo.getInputManId());//获取制单人信息
        /**
         * 收款类别:1.预收款;2.现收;3.收欠款
         */
        bmsSaRecDoc.setRecmethod(3);
        /**
         * 使用状态:0.作废;1.正式;2.临时
         */
        bmsSaRecDoc.setUsestatus(2);
        /**
         * 客户信息
         */
        /**
         * customid,customname,fmid,fmname,fmrate,fmopcode,settletypeid
         */
        /**
         * 客户ID
         */
        bmsSaRecDoc.setCustomid(customerVo.getCustomid());
        /**
         * 客户
         */
        bmsSaRecDoc.setCustomname(customerVo.getCustomname());
        /**
         * 结算方式：1.现结；2.立即支票；3.电汇；4.银行转账；5.承兑汇票；6.应收抵应付；8.内销；11.麻黄碱；9.半月结；10.月结；12.周结
         */
        //bmsSaRecDoc.setRectypeid(cusl.getSettletypeid());
        bmsSaRecDoc.setRectypeid(4);
        /**
         * 外币id
         */
        bmsSaRecDoc.setFmid(customerVo.getFmid());
        /**
         * 汇率
         */
        bmsSaRecDoc.setExchange(customerVo.getFmrate());
        /**
         * 汇票日期
         */
        //        bmsSaRecDoc.setDraftdate(credate);//汇票日期
        /**
         * 独立单元
         */
        bmsSaRecDoc.setEntryid(financeVerificationDocVo.getEntryId().longValue());
        /**
         * 制单人ID
         */
        bmsSaRecDoc.setInputmanid(financeVerificationDocVo.getInputManId());
        /**
         * 收款人ID
         */
        bmsSaRecDoc.setRecmanid(financeVerificationDocVo.getInputManId());

        bmsSaRecDoc.setMemo("B2B线上支付,支付流水号：" + financeVerificationDocVo.getPayFlowNo() + ",订单总金额：" + financeVerificationDtlVo.getAmount());
        bmsSaRecDoc.setBankid(financeVerificationDocVo.getBankId());
        bmsSaRecDoc.setCredate(financeVerificationDocVo.getPayDate());

        bmsSaRecDoc.setPaytype(financeVerificationDocVo.getPayType());

        bmsSaRecDoc.setIsAutoVerification(2);
        bmsSaRecDoc.setTotal(financeVerificationDtlVo.getAmount());


        List<BmsSaDtl> bmsSaDtls = bmsSaDtlMapper.selectListBy(srcErpOrderId,financeVerificationDtlVo.getSourceType());


        bmsSaRecDoc.setDtlLines(bmsSaDtls.size());

        /**
         * 细单条数
         */
        bmsSaRecDocMapper.insert(bmsSaRecDoc);

        bmsSaDtls.forEach(bmsSaDtl -> {

            BmsSaRecDtl bmsSaRecDtl = new BmsSaRecDtl();


            //List<BmsSaDoctoset> bmsSaDoctosetList = getBmsSaDoctosetList(bmsSaDtl.getSalesdtlid());
            BmsSaSettleDtl bmsSaSettleDtl = bmsSaSettleDtlMapper.getBySalesdtlid(bmsSaDtl.getSalesdtlid());
            BeanUtil.copyProperties(bmsSaSettleDtl, bmsSaRecDtl);

            /**
             * 业务员id
             */
            bmsSaRecDtl.setSalerid(bmsSaSettleDtl.getSalerid());
            /**
             * 业务部门ID
             */
            bmsSaRecDtl.setSalesdeptid(bmsSaSettleDtl.getSalesdeptid());
            /**
             * 收款员ID
             */
            bmsSaRecDtl.setRecsalerid(inputManL.getEmployeeid());
            /**
             * 收款部门ID
             */
            bmsSaRecDtl.setRecsalesdeptid(inputManL.getDeptid());

            bmsSaRecDtl.setSarecid(bmsSaRecDoc.getSarecid());
            /**
             * 先保存细单
             */
            bmsSaRecDtlMapper.insert(bmsSaRecDtl);

            BmsSaSettorec bmsSaSettorec = new BmsSaSettorec();
            bmsSaSettorec.setGoodsqty(bmsSaRecDtl.getGoodsqty());
            /**
             * 收款金额
             */
            bmsSaSettorec.setTotalLine(bmsSaRecDtl.getTotalLine());
            /**
             * 回款单号
             */
            bmsSaSettorec.setSarecdtlid(bmsSaRecDtl.getSarecdtlid());
            /**
             * 发票单号
             * 备注：分析ERP系统代码发现，bms_sa_settorec表的sasettledtlid存的是销售细单id
             * 个人认为正确的数据存取代码：str.setSasettledtlid(bmsSaDoctosetList.get(0).getSasettledtlid());
             */
            bmsSaSettorec.setSasettledtlid(bmsSaSettleDtl.getSasettledtlid());

            bmsSaSettorecMapper.insert(bmsSaSettorec);


        });


        //bmsSaRecDocMapper.updateById(bmsSaRecDoc);
        return bmsSaRecDoc;


    }


    /**
     * 到货支付核销
     *
     * @param financeVerificationDocVo
     */
    private void verificationOnmonthV3(FinanceVerificationDocVo financeVerificationDocVo) {
        /**
         * 生成临时收款记录
         */
        BmsSaRecDoc bmsSaRecDoc = genTmpRecRecordV3(financeVerificationDocVo);
        confirmTmpRecRecordV3(bmsSaRecDoc, financeVerificationDocVo);
    }
    /**
     * 月结支付核销
     *
     * @param financeVerificationDocVo
     */
    private void verificationOnmonthV2(FinanceVerificationDocVo financeVerificationDocVo) {
        Boolean isPs = customService.isPsCustom(financeVerificationDocVo.getCustomId(), financeVerificationDocVo.getEntryId());

        /**
         * 单据开始核销，生成临时单
         */
        financeVerificationDocVo.getFinanceVerificationDtlVoList().forEach(financeVerificationDtlVo -> {
            Long srcErpOrderId = financeVerificationDtlVo.getSrcErpOrderId();
            if (isPs) {
                srcErpOrderId = bmsSaDocMapper.getSalesIdByPlacesupplyId(srcErpOrderId,financeVerificationDocVo.getCustomId());
            }

            /**
             * 生成临时收款记录
             */
            BmsSaRecDoc bmsSaRecDoc = genTmpRecRecordV2(srcErpOrderId, financeVerificationDtlVo, financeVerificationDocVo);
            confirmTmpRecRecordV2(bmsSaRecDoc, financeVerificationDtlVo, financeVerificationDocVo);

        });

    }


    /**
     * 到货支付核销
     *
     * @param financeVerificationDocVo
     */
    private void verificationOnfaceV3(FinanceVerificationDocVo financeVerificationDocVo) {
        /**
         * 生成临时收款记录
         */
        BmsSaRecDoc bmsSaRecDoc = genTmpRecRecordV3(financeVerificationDocVo);
        confirmTmpRecRecordV3(bmsSaRecDoc, financeVerificationDocVo);
    }



    /**
     * 到货支付核销
     *
     * @param financeVerificationDocVo
     */
    private void verificationOnfaceV2(FinanceVerificationDocVo financeVerificationDocVo) {

        Boolean isPs = customService.isPsCustom(financeVerificationDocVo.getCustomId(), financeVerificationDocVo.getEntryId());

        /**
         * 单据开始核销，生成临时单
         */
        financeVerificationDocVo.getFinanceVerificationDtlVoList().forEach(financeVerificationDtlVo -> {
            Long srcErpOrderId = financeVerificationDtlVo.getSrcErpOrderId();
            if (isPs) {
                srcErpOrderId = bmsSaDocMapper.getSalesIdByPlacesupplyId(srcErpOrderId,financeVerificationDocVo.getCustomId());
            }

            /**
             * 生成临时收款记录
             */
            BmsSaRecDoc bmsSaRecDoc = genTmpRecRecordV2(srcErpOrderId, financeVerificationDtlVo, financeVerificationDocVo);
            confirmTmpRecRecordV2(bmsSaRecDoc, financeVerificationDtlVo, financeVerificationDocVo);

        });
    }

    /**
     * 退单核销
     *
     * @param financeVerificationDocVo
     */
    private void verificationOnbackV2(FinanceVerificationDocVo financeVerificationDocVo) {
        Boolean isPs = customService.isPsCustom(financeVerificationDocVo.getCustomId(), financeVerificationDocVo.getEntryId());

        /**
         * 单据开始核销，生成临时单
         */
        financeVerificationDocVo.getFinanceVerificationDtlVoList().forEach(financeVerificationDtlVo -> {
            Long srcErpOrderDtlId = financeVerificationDtlVo.getSrcErpOrderDtlId();
            if (isPs) {
                srcErpOrderDtlId = bmsSaDocMapper.getSalesIdByPlacesupplyDtlId(srcErpOrderDtlId);
            }
            /**
             * 生成临时收款记录
             */
            BmsSaRecDoc bmsSaRecDoc = genTmpRecRecordForBackV2(srcErpOrderDtlId, financeVerificationDtlVo, financeVerificationDocVo);
            confirmTmpRecRecordV2(bmsSaRecDoc, financeVerificationDtlVo, financeVerificationDocVo);

        });
    }



    /**
     * 退单核销
     *
     * @param financeVerificationDocVo
     */
    private void verificationOnbackV3(FinanceVerificationDocVo financeVerificationDocVo) {

        BmsSaRecDoc bmsSaRecDoc = genTmpRecRecordForBackV3( financeVerificationDocVo);
        confirmTmpRecRecordV3(bmsSaRecDoc, financeVerificationDocVo);

    }

    private BmsSaRecDoc genTmpRecRecordForBackV3(FinanceVerificationDocVo financeVerificationDocVo) {

        BmsSaRecDoc bmsSaRecDoc = new BmsSaRecDoc();
        CustomerVo customerVo = customService.getByCustomId(financeVerificationDocVo.getCustomId(), financeVerificationDocVo.getEntryId());
        bmsSaRecDoc.setCredate(financeVerificationDocVo.getVerificationDate());//业务日期



        bmsSaRecDoc.setB2bOrderNo(financeVerificationDocVo.getOrderNo());
        bmsSaRecDoc.setB2bPayFlowNo(financeVerificationDocVo.getPayFlowNo());
        /**
         * 方便修复数据！
         */
        bmsSaRecDoc.setB2bOrderId(financeVerificationDocVo.getOrderId());
        bmsSaRecDoc.setZxBhFlag(11);
        /**
         * 制单人信息
         */
        PubEmployee inputManL = pubEmployeeMapper.selectById(financeVerificationDocVo.getInputManId());//获取制单人信息
        /**
         * 收款类别:1.预收款;2.现收;3.收欠款
         */
        bmsSaRecDoc.setRecmethod(3);
        /**
         * 使用状态:0.作废;1.正式;2.临时
         */
        bmsSaRecDoc.setUsestatus(2);
        /**
         * 客户信息
         */
        /**
         * customid,customname,fmid,fmname,fmrate,fmopcode,settletypeid
         */
        /**
         * 客户ID
         */
        bmsSaRecDoc.setCustomid(customerVo.getCustomid());
        /**
         * 客户
         */
        bmsSaRecDoc.setCustomname(customerVo.getCustomname());
        /**
         * 结算方式：1.现结；2.立即支票；3.电汇；4.银行转账；5.承兑汇票；6.应收抵应付；8.内销；11.麻黄碱；9.半月结；10.月结；12.周结
         */
        //bmsSaRecDoc.setRectypeid(cusl.getSettletypeid());

        /**
         * 结算方式：1.现结；2.立即支票；3.电汇；4.银行转账；5.承兑汇票；6.应收抵应付；8.内销；11.麻黄碱；9.半月结；10.月结；12.周结
         */
        //bmsSaRecDoc.setRectypeid(cusl.getSettletypeid());
        if(ObjectUtil.equal("CASH",financeVerificationDocVo.getPayType())){
            bmsSaRecDoc.setRectypeid(1);
            bmsSaRecDoc.setMemo("B2B现金支付,订单总金额：" + financeVerificationDocVo.getAmountVerification());
        }else if(ObjectUtil.equal("ON_LINE",financeVerificationDocVo.getPayType())){
            bmsSaRecDoc.setRectypeid(4);
            bmsSaRecDoc.setBankid(financeVerificationDocVo.getBankId());
            bmsSaRecDoc.setMemo("B2B线上支付,支付流水号：" + financeVerificationDocVo.getPayFlowNo() + ",订单总金额：" + financeVerificationDocVo.getAmountVerification());
        }
        /**
         * 外币id
         */
        bmsSaRecDoc.setFmid(customerVo.getFmid());
        /**
         * 汇率
         */
        bmsSaRecDoc.setExchange(customerVo.getFmrate());
        /**
         * 汇票日期
         */
        //        bmsSaRecDoc.setDraftdate(credate);//汇票日期
        /**
         * 独立单元
         */
        bmsSaRecDoc.setEntryid(financeVerificationDocVo.getEntryId().longValue());
        /**
         * 制单人ID
         */
        bmsSaRecDoc.setInputmanid(financeVerificationDocVo.getInputManId());
        /**
         * 收款人ID
         */
        bmsSaRecDoc.setRecmanid(financeVerificationDocVo.getInputManId());

        bmsSaRecDoc.setCredate(financeVerificationDocVo.getPayDate());

        bmsSaRecDoc.setPaytype(financeVerificationDocVo.getPayType());

        bmsSaRecDoc.setIsAutoVerification(2);
        bmsSaRecDoc.setTotal(financeVerificationDocVo.getAmountVerification());


        List<FinanceVerificationDtlVo> financeVerificationDtlVoList = financeVerificationDocVo.getFinanceVerificationDtlVoList();

        bmsSaRecDoc.setDtlLines(financeVerificationDtlVoList.size());

        /**
         * 细单条数
         */

        /**
         * 细单条数
         */
        bmsSaRecDocMapper.insert(bmsSaRecDoc);

        Boolean isPs = customService.isPsCustom(financeVerificationDocVo.getCustomId(), financeVerificationDocVo.getEntryId());


        for (FinanceVerificationDtlVo financeVerificationDtlVo : financeVerificationDtlVoList) {
            Long srcErpOrderDtlId = financeVerificationDtlVo.getSrcErpOrderDtlId();
            if (isPs) {
                srcErpOrderDtlId = bmsSaDocMapper.getSalesIdByPlacesupplyDtlId(srcErpOrderDtlId);
            }


            BmsSaRecDtl bmsSaRecDtl = new BmsSaRecDtl();
            BmsSaDtl bmsSaDtl = bmsSaDtlMapper.selectById(srcErpOrderDtlId);
            BmsSaSettleDtl bmsSaSettleDtl = bmsSaSettleDtlMapper.getBySalesdtlid(bmsSaDtl.getSalesdtlid());
            BeanUtil.copyProperties(bmsSaSettleDtl, bmsSaRecDtl);

            /**
             * 业务员id
             */
            bmsSaRecDtl.setSalerid(bmsSaSettleDtl.getSalerid());
            /**
             * 业务部门ID
             */
            bmsSaRecDtl.setSalesdeptid(bmsSaSettleDtl.getSalesdeptid());
            /**
             * 收款员ID
             */
            bmsSaRecDtl.setRecsalerid(inputManL.getEmployeeid());
            /**
             * 收款部门ID
             */
            bmsSaRecDtl.setRecsalesdeptid(inputManL.getDeptid());

            bmsSaRecDtl.setSarecid(bmsSaRecDoc.getSarecid());
            /**
             * 先保存细单
             */
            bmsSaRecDtlMapper.insert(bmsSaRecDtl);

            BmsSaSettorec bmsSaSettorec = new BmsSaSettorec();
            bmsSaSettorec.setGoodsqty(bmsSaRecDtl.getGoodsqty());
            /**
             * 收款金额
             */
            bmsSaSettorec.setTotalLine(bmsSaRecDtl.getTotalLine());
            /**
             * 回款单号
             */
            bmsSaSettorec.setSarecdtlid(bmsSaRecDtl.getSarecdtlid());
            /**
             * 发票单号
             * 备注：分析ERP系统代码发现，bms_sa_settorec表的sasettledtlid存的是销售细单id
             * 个人认为正确的数据存取代码：str.setSasettledtlid(bmsSaDoctosetList.get(0).getSasettledtlid());
             */
            bmsSaSettorec.setSasettledtlid(bmsSaSettleDtl.getSasettledtlid());

            bmsSaSettorecMapper.insert(bmsSaSettorec);

        }

        return bmsSaRecDoc;
    }


    @Override
    public FinanceVerificationResultDocVo verification(FinanceVerificationDocVo financeVerificationDocVo) {


        FinanceVerificationResultDocVo result = null;
        AssertExt.notNull(financeVerificationDocVo, "参数对象为空");

        Long customId = financeVerificationDocVo.getCustomId();
        String orderNo = financeVerificationDocVo.getOrderNo();
        Long orderId = financeVerificationDocVo.getOrderId();

        AssertExt.notNull(customId, "客户ID为空");

        CustomerVo cusl = pubCustomerMapper.getCustomInfo(customId);

        //客户类型 配送客户 true
        boolean isCusType = customService.isPsCustom(customId, financeVerificationDocVo.getEntryId());

        /**
         * 制单人信息
         */
        PubEmployee inputManL = pubEmployeeMapper.selectById(financeVerificationDocVo.getInputManId());//获取制单人信息
        AssertExt.notNull(inputManL, "制单人信息为空");


        RLock lock = redissonClient.getLock("order_finance_verification:" + orderId);
        /**
         * 获取锁资源
         * 2分钟自动解锁
         */
        lock.lock(2, TimeUnit.MINUTES);

        try {

            log.info("订单下发程序---------业务逻辑----------");
            log.info("订单下发程序---------业务逻辑----------");
            QueryWrapper<B2bFinanceList> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(B2bFinanceList::getB2bOrderId, orderId);
            queryWrapper.lambda().eq(B2bFinanceList::getVersion, financeVerificationDocVo.getVersion());

            if (b2bFinanceListMapper.selectCount(queryWrapper) == 0) {
                /**
                 * 核销信息保留一份，留底
                 */
                B2bFinanceList b2bFinanceList = new B2bFinanceList();

                b2bFinanceList.setB2bOrderNo(financeVerificationDocVo.getOrderNo());
                b2bFinanceList.setB2bCustomId(financeVerificationDocVo.getCustomId());
                b2bFinanceList.setB2bOrderId(orderId);
                b2bFinanceList.setCreateDate(LocalDateTime.now());
                b2bFinanceList.setVersion(financeVerificationDocVo.getVersion());

                b2bFinanceList.setSrdData(JSON.toJSONString(financeVerificationDocVo, SerializerFeature.PrettyFormat,
                        SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.UseSingleQuotes));

                b2bFinanceListMapper.insert(b2bFinanceList);

                //校验重复
                List<FinanceVerificationDtlVo> financeVerificationDocList = financeVerificationDocVo.getFinanceVerificationDtlVoList();
                if (financeVerificationDocList != null && financeVerificationDocList.size() > 0) {
                    Set<Long> check = new HashSet<>(financeVerificationDocList.size());
                    List<Long> temp = financeVerificationDocList.stream().map(FinanceVerificationDtlVo::getSrcErpOrderDtlId).collect(Collectors.toList());
                    for (Long srcErpOrderDtlId : temp) {
                        if (check.contains(srcErpOrderDtlId)) {
                            AssertExt.fail("有重复的订单:" + srcErpOrderDtlId);
                        }
                        check.add(srcErpOrderDtlId);
                    }
                }

                //验证数据是否可以进行核销
                for (FinanceVerificationDtlVo dtlVo : financeVerificationDocList) {
                    /**
                     * 赠品保管账不需要核销
                     *
                     */
                    if (ObjectUtil.equal(dtlVo.getStorageId(), 691)) {
                        continue;
                    }
                    BmsSaRecDtl bmsSaRecDtl = new BmsSaRecDtl();
                    /**
                     * financeVerificationDocVo.getOrderType() == 1 还是 3 判断 是正式单还是退单
                     * jie
                     *
                     */
                    BmsSaDtl tempSaDtl;
                    //退货核销
                    if (3 == financeVerificationDocVo.getOrderType()) {
                        /**
                         * 配送客户，根据配送单id反查销售单id jie
                         */
                        if (isCusType) {
                            //配退核销
                            tempSaDtl = bmsSaDtlMapper.getBmsSaDtlByErpOrderDtlId(dtlVo.getSrcErpOrderId(), dtlVo.getSrcErpOrderDtlId());
                            AssertExt.notNull(tempSaDtl.getSalesid(), "配退销售总单ID为空");
                            AssertExt.notNull(tempSaDtl.getSalesdtlid(), "配退销售细单ID为空");
                        } else {
                            //销退核销
                            Long salesDtlId = bmsSaToSaMapper.getsadtlid2(dtlVo.getSrcErpOrderDtlId());
                            tempSaDtl = bmsSaDtlMapper.selectById(salesDtlId);
                            AssertExt.notNull(tempSaDtl.getSalesid(), "销退销售总单ID为空");
                            AssertExt.notNull(tempSaDtl.getSalesdtlid(), "销退销售细单ID为空");
                        }
                        //设置销售退款细单id
                        dtlVo.setSrcErpOrderDtlId(tempSaDtl.getSalesdtlid());
                        //设置销售退款主单id
                        dtlVo.setSrcErpOrderId(tempSaDtl.getSalesid());
                        //设置销售退款细单金额
                        dtlVo.setAmount(tempSaDtl.getTotalLine());
                    }

                    //收货核销
                    if (1 == financeVerificationDocVo.getOrderType()) {
                        if (isCusType) {
                            //收货核销
                            Long salesId = bmsSaDocMapper.getBmsSaDocByPlacesupplyId(dtlVo.getSrcErpOrderId(), customId);
                            AssertExt.notNull(salesId, "根据手工配送主单ID查询的销售总单ID为空");
                            Long salesDtlId = bmsSaDtlMapper.getSalesdtlid(dtlVo.getSrcErpOrderDtlId(), salesId);
                            AssertExt.notNull(salesDtlId, "根据手工配送细单ID查询的销售细单ID为空");
                            //设置销售退款主单id
                            dtlVo.setSrcErpOrderId(salesId);
                            //设置销售退款细单id
                            dtlVo.setSrcErpOrderDtlId(salesDtlId);
                        }
                    }

                    //细单
                    Long dtlId = dtlVo.getSrcErpOrderDtlId();
                    //总单
                    Long srcErpOrderId = dtlVo.getSrcErpOrderId();
                    /**
                     * 销售总单不能为空
                     */
                    AssertExt.notNull(srcErpOrderId, "销售总单ID为空");

                    /**
                     * 验证该销售细单是否已经合法
                     */
                    List<BmsSaDoctoset> bmsSaDoctosetList = getBmsSaDoctosetList(dtlId);
                    if (ObjectUtils.isEmpty(bmsSaDoctosetList)) {
                        return FinanceVerificationResultDocVo.failErrorMessageAppend(result, "核销失败！原因：该销售细单（" + dtlId + "）没有发票信息！", orderNo, customId);
                    }

                    /**
                     * 验证该销售细单是否已经核销
                     */
                    Long sasettledtlid = getBmsSaSettleDtlTmp(bmsSaDoctosetList.get(0).getSasettledtlid());
                    if (sasettledtlid == null) {
                        return FinanceVerificationResultDocVo.failErrorMessageAppend(result, "核销失败！原因：该销售细单已经进行核销！！", orderNo, customId);
                    }
                }


                /**
                 * 销售收款单核销
                 */
                BmsSaRecDoc bmsSaRecDoc = new BmsSaRecDoc();

                /**
                 * 新增订单回款单数据
                 */
                if (ObjectUtils.isNotEmpty(cusl)) {
                    addBmsSaRecDoc(bmsSaRecDoc, financeVerificationDocVo, cusl);
                } else {
                    return FinanceVerificationResultDocVo.failErrorMessageAppend(result, "客户信息为空！！", orderNo, customId);
                }


                /**
                 * 收款额
                 */
                Double total = ObjectUtils.isEmpty(bmsSaRecDoc.getTotal()) ? 0.0 : bmsSaRecDoc.getTotal();

                /**
                 * 细单金额
                 */
                Double premoney = ObjectUtils.isEmpty(bmsSaRecDoc.getPremoney()) ? 0.0 : bmsSaRecDoc.getPremoney();

                /**
                 * 销售收款细单核销
                 */
                Integer dtlLines = 0;
                for (FinanceVerificationDtlVo dtlVo : financeVerificationDocVo.getFinanceVerificationDtlVoList()) {
                    /**
                     * 赠品保管账不需要核销
                     *
                     */
                    if (ObjectUtil.equal(dtlVo.getStorageId(), 691)) {
                        continue;
                    }

                    BmsSaRecDtl bmsSaRecDtl = new BmsSaRecDtl();

                    Long dtlId = dtlVo.getSrcErpOrderDtlId();


                    List<BmsSaDoctoset> bmsSaDoctosetList = getBmsSaDoctosetList(dtlId);
                    Long saSettleDtlid = bmsSaDoctosetList.get(0).getSasettledtlid();

                    List<BmsSaSettorec> bmsSaSettorecsList = getBmsSaSettorecList(bmsSaDoctosetList.get(0).getSasettledtlid());

                    /**
                     * 核销金额
                     */

                    Double amount = dtlVo.getAmount();
                    BmsSaSettleDtl dtll = bmsSaSettleDtlMapper.selectById(bmsSaDoctosetList.get(0).getSasettledtlid());

                    if (dtll != null) {
                        /**
                         * 新增回款细单数据
                         */
                        /**
                         * 回款单的收款金额说明：
                         * 以实际收款金额为主，
                         * 欠款金额可以拆，欠款商品数量不可拆
                         */
                        bmsSaRecDtl.setTotalLine(dtlVo.getAmount());
                        bmsSaRecDtl.setGoodsqty(dtlVo.getNum());
                        addBmsSaRecDtl(bmsSaRecDtl, inputManL, dtll, bmsSaRecDoc);
                        /**
                         * 销售细单条数+1
                         */
                        dtlLines += 1;
                        /**
                         * 计算收款额
                         */
                        total += amount;
                        /**
                         * 细单金额
                         */
                        premoney += bmsSaRecDtl.getTotalLine();


                        /**
                         * 填写发票汇款关联信息
                         */
                        Boolean isDeleteTmpFlag = false;
                        Double deleteTmpFlag = this.transDeleteTmpFlag(dtll.getTotalLine(), dtll.getTotalrecmoney(), dtlVo.getAmount());
                        if (deleteTmpFlag <= 0.0) {
                            isDeleteTmpFlag = true;
                        }
                        BmsSaSettorec str = new BmsSaSettorec();
                        addBmsSaSettorec(saSettleDtlid, bmsSaRecDtl, bmsSaDoctosetList, str);
                        /** */

                        /**
                         * 写入汇款单后，更新相应的发票细单状态
                         */
                        upSettleInfo(saSettleDtlid, str.getSasettledtlid(), bmsSaRecDtl.getGoodsqty(), bmsSaRecDtl.getTotalLine(), isDeleteTmpFlag, dtlId);
                        /** */
                        /**
                         * 生成欠款流水记录（bms_credit_bill_doc）
                         * 备注：由于欠款流水表（表头以及细表）在数据库的结构没有主键设置，因此只能手工生成然后插入
                         */
//                BmsCreditBillDoc tdoc=this.getBmsCreditBillDocInfo(bmsSaRecDoc.getCustomid(),dtlVo.getSrcErpOrderId());
//                ih.bindParam("customid", model.getItemValue(row, "customid"));
//                ih.bindParam("billid", billid);
//                ih.bindParam("salerid", model.getItemValue(row, "salerid"));
//                ih.bindParam("salerdeptid", model.getItemValue(row, "salerdeptid"));
//                ih.bindParam("agentid", model.getItemValue(row, "agentid"));
//                ih.bindParam("contactid", model.getItemValue(row, "contactid"));
//                ih.bindParam("entryid", model.getItemValue(row, "entryid"));
//                ih.bindDateParam("owedate", model.getItemValue(row, "busiconfirmdate"));
//                ih.bindParam("owemoney", model.getItemValue(row, "busimoney"));
                        BmsCreditBillDoc bmsCreditBillDoc = new BmsCreditBillDoc();
                        bmsCreditBillDoc.setBillid(bmsCreditBillDocMapper.getBmsCreditBillDocSeq());
                        bmsCreditBillDoc.setCustomid(financeVerificationDocVo.getCustomId());
                        /**
                         * 使用制单人ID
                         * jie
                         */
                        bmsCreditBillDoc.setSalerid(financeVerificationDocVo.getInputManId());
                        //查询部门id
                        PubEmployee pubEmployee = pubEmployeeMapper.getPubEmployee(financeVerificationDocVo.getInputManId());
                        AssertExt.notNull(pubEmployee, "制单人为空");
                        bmsCreditBillDoc.setSalerdeptid(pubEmployee.getDeptid());
                        /**
                         * 这两个数据目前填空
                         *
                         * ERP系统找不到数据来源，看到数据库已生成单子里也没有这两个数据
                         *
                         */
                        bmsCreditBillDoc.setAgentid(null);
                        bmsCreditBillDoc.setContactid(null);
                        /** */
                        bmsCreditBillDoc.setEntryid(financeVerificationDocVo.getEntryId().longValue());
                        bmsCreditBillDoc.setOwedate(bmsSaRecDoc.getConfirmdate());
                        bmsCreditBillDoc.setOwemoney(bmsSaRecDtl.getTotalLine());
                        this.bmsCreditBillDocMapper.insert(bmsCreditBillDoc);

                        BmsCreditBillDtl bmsCreditBillDtl = new BmsCreditBillDtl();
                        saveBmsCreditBillDtl(bmsCreditBillDtl, bmsCreditBillDoc, bmsSaRecDoc, bmsSaRecDtl, "BMS_SA_REC_DTL");
//                insert into BMS_CREDIT_BILL_DTL(billdtlid,billid,busitype,sourcetable,sourceid,busicredate,busiconfirmdate,busimoney)values(bms_credit_bill_dtl_seq.nextval,?,?,?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),to_date(?,'yyyy-mm-dd hh24:mi:ss'),?)
                    }
                }


                /**
                 * 更新收款单的收款额、细单金额跟细单记录条数
                 */
                bmsSaRecDoc.setTotal(total);
                bmsSaRecDoc.setDtlLines(dtlLines);
                bmsSaRecDoc.setPremoney(premoney);

                bmsSaRecDoc.setMemo("B2B线上支付,支付流水号：" + financeVerificationDocVo.getPayFlowNo() + ",订单总金额：" + total);
                bmsSaRecDoc.setBankid(financeVerificationDocVo.getBankId());
                bmsSaRecDoc.setCredate(financeVerificationDocVo.getPayDate());

                bmsSaRecDoc.setPaytype(financeVerificationDocVo.getPayType());

                bmsSaRecDoc.setIsAutoVerification(2);
                bmsSaRecDocMapper.updateById(bmsSaRecDoc);


                /**
                 * 确认收款单
                 * 填写确认人，状态变正式，确认时间
                 */
                bmsSaRecDoc.setConfirmmanid(financeVerificationDocVo.getConfirmManId());
                bmsSaRecDoc.setUsestatus(1);
                bmsSaRecDoc.setConfirmdate(LocalDateTime.now());
                bmsSaRecDocMapper.updateById(bmsSaRecDoc);


            } else {
                AssertExt.fail("核销订单重复下发！！!!!!");
            }

        } catch (Throwable e) {
            e.printStackTrace();
            log.error("执行出现了异常!", e.getMessage());
            AssertExt.fail("执行出现了异常:" + e.getMessage(), e);
        } finally {
            log.info("核销订单下发程序---------解锁----------");
            lock.unlock();
        }


        return FinanceVerificationResultDocVo.validReturn(result, financeVerificationDocVo.getOrderNo(), financeVerificationDocVo.getCustomId());
    }

    /**
     * 验证发票细单号是否已经核销
     *
     * @param sasettledtlid
     * @return
     */
    private Long getBmsSaSettleDtlTmp(Long sasettledtlid) {
        Long result = null;
        result = this.bmsSaSettleDtlMapper.getBmsSaSettleDtlTmp(sasettledtlid);

        return result;
    }

    /**
     * 计算发票单的剩余总金额，公式：发票单的总金额-总收款金额-目前回款金额
     *
     * @param totalLine
     * @param totalrecmoney
     * @param amount
     * @return
     */
    private Double transDeleteTmpFlag(Double totalLine, Double totalrecmoney, Double amount) {
        BigDecimal result = null;
        BigDecimal tl = BigDecimal.valueOf(ObjectUtils.isEmpty(totalLine) ? 0.0 : totalLine);
        BigDecimal trm = BigDecimal.valueOf(ObjectUtils.isEmpty(totalrecmoney) ? 0.0 : totalrecmoney);
        BigDecimal a = BigDecimal.valueOf(ObjectUtils.isEmpty(amount) ? 0.0 : amount);

        result = tl.subtract(trm).subtract(a);

        return result.doubleValue();
    }

    /**
     * 生成欠款流水细单
     *
     * @param tdoc
     * @param bmsSaRecDoc
     * @param bmsSaRecDtl
     * @param bms_sa_rec_dtl
     */
    private void saveBmsCreditBillDtl(BmsCreditBillDtl bmsCreditBillDtl, BmsCreditBillDoc tdoc, BmsSaRecDoc bmsSaRecDoc, BmsSaRecDtl bmsSaRecDtl, String bms_sa_rec_dtl) {

        bmsCreditBillDtl.setBillid(tdoc.getBillid());
        bmsCreditBillDtl.setBusitype(bmsSaRecDoc.getRectypeid());
        bmsCreditBillDtl.setSourcetable("BMS_SA_REC_DTL");
        bmsCreditBillDtl.setSourceid(bmsSaRecDtl.getSarecdtlid());
        bmsCreditBillDtl.setBusicredate(bmsSaRecDoc.getCredate());
        bmsCreditBillDtl.setBusiconfirmdate(bmsSaRecDoc.getConfirmdate());
        bmsCreditBillDtl.setBusimoney(-bmsSaRecDtl.getTotalLine());

        bmsCreditBillDtlMapper.insert(bmsCreditBillDtl);
    }

    /**
     * 获取billid，owemoney
     *
     * @return
     */
    private BmsCreditBillDoc getBmsCreditBillDocInfo(Long customid, Long srcErpOrderId) {
        BmsCreditBillDoc result = new BmsCreditBillDoc();
        QueryWrapper<BmsCreditBillDoc> queryWrapper = new QueryWrapper();

        queryWrapper.lambda().eq(BmsCreditBillDoc::getCustomid, customid);
        queryWrapper.lambda().eq(BmsCreditBillDoc::getSalerid, srcErpOrderId);

        List<BmsCreditBillDoc> bmsCreditBillDocs = bmsCreditBillDocMapper.selectList(queryWrapper);
        if (ObjectUtils.isNotEmpty(bmsCreditBillDocs)) {
            result = bmsCreditBillDocs.get(0);
        }

        return result;
    }

    /**
     * 生成业务产品与核算产品接口表记录bms_cert_dtl_tmp
     *
     * @param sarecid
     */
    private void addBmsCertDtlTmp(Long sarecid) {
        BmsCertDtlTmp bmsCertDtlTmp = new BmsCertDtlTmp();

        bmsCertDtlTmp.setSourceid(sarecid);
        bmsCertDtlTmp.setSourcetable("BMS_SA_REC_DOC");
        bmsCertDtlTmp.setCerttype(3);
        bmsCertDtlTmp.setAcctype(3);
        //bms_cert_dtl_tmp_trans_seq.nextval
        bmsCertDtlTmp.setTransactionid(bmsCertDtlTmpMapper.getTransactionId());

        bmsCertDtlTmpMapper.insert(bmsCertDtlTmp);
        //certtype,acctype,transactionid) values ('BMS_SA_REC_DOC',3,3,bms_cert_dtl_tmp_trans_seq.nextval)
    }


    /**
     * 生成业务产品与核算产品接口表记录bms_cert_dtl_tmp
     *
     * @param sarecId
     */
    private void saveBmsCertDtlTmp(Long sarecId) {
        BmsCertDtlTmp bmsCertDtlTmp = new BmsCertDtlTmp();
        bmsCertDtlTmp.setSourceid(sarecId);
        bmsCertDtlTmp.setSourcetable("BMS_SA_REC_DOC");
        bmsCertDtlTmp.setCerttype(3);
        bmsCertDtlTmp.setAcctype(3);
        bmsCertDtlTmp.setTransactionid(bmsCertDtlTmpMapper.getTransactionId());
        bmsCertDtlTmpMapper.insert(bmsCertDtlTmp);
    }

    /**
     * 新增订单汇款单数据
     *
     * @param bmsSaRecDoc
     * @param financeVerificationDocVo
     * @param cusl
     */
    private void addBmsSaRecDoc(BmsSaRecDoc bmsSaRecDoc, FinanceVerificationDocVo financeVerificationDocVo, CustomerVo cusl) {

        bmsSaRecDoc.setCredate(financeVerificationDocVo.getVerificationDate());//业务日期
        /**
         * 收款类别:1.预收款;2.现收;3.收欠款
         */
        bmsSaRecDoc.setRecmethod(3);
        /**
         * 使用状态:0.作废;1.正式;2.临时
         */
        bmsSaRecDoc.setUsestatus(2);
        /**
         * 客户信息
         */
        /**
         * customid,customname,fmid,fmname,fmrate,fmopcode,settletypeid
         */
        /**
         * 客户ID
         */
        bmsSaRecDoc.setCustomid(cusl.getCustomid());
        /**
         * 客户
         */
        bmsSaRecDoc.setCustomname(cusl.getCustomname());
        /**
         * 结算方式：1.现结；2.立即支票；3.电汇；4.银行转账；5.承兑汇票；6.应收抵应付；8.内销；11.麻黄碱；9.半月结；10.月结；12.周结
         */
        //bmsSaRecDoc.setRectypeid(cusl.getSettletypeid());
        bmsSaRecDoc.setRectypeid(4);
        /**
         * 外币id
         */
        bmsSaRecDoc.setFmid(cusl.getFmid());
        /**
         * 汇率
         */
        bmsSaRecDoc.setExchange(cusl.getFmrate());
        /**
         * 汇票日期
         */
        //        bmsSaRecDoc.setDraftdate(credate);//汇票日期
        /**
         * 独立单元
         */
        bmsSaRecDoc.setEntryid(financeVerificationDocVo.getEntryId().longValue());
        /**
         * 制单人ID
         */
        bmsSaRecDoc.setInputmanid(financeVerificationDocVo.getInputManId());
        /**
         * 收款人ID
         */
        bmsSaRecDoc.setRecmanid(financeVerificationDocVo.getInputManId());
        /**
         * 细单条数
         */
        bmsSaRecDocMapper.insert(bmsSaRecDoc);

        /**
         * 生成业务产品与核算产品接口表记录bms_cert_dtl_tmp
         */
        addBmsCertDtlTmp(bmsSaRecDoc.getSarecid());
        /** */
    }

    /**
     * 新增回款细单数据
     *
     * @param bmsSaRecDtl
     * @param inputManL
     * @param dtll
     * @param bmsSaRecDoc
     */
    private void addBmsSaRecDtl(BmsSaRecDtl bmsSaRecDtl, PubEmployee inputManL, BmsSaSettleDtl dtll, BmsSaRecDoc bmsSaRecDoc) {
        /**
         * 核销总单ID
         */
        bmsSaRecDtl.setSarecid(bmsSaRecDoc.getSarecid());
        /**
         * 商品ID
         */
        bmsSaRecDtl.setGoodsid(dtll.getGoodsid());
        /**
         * 收款数量
         */
//        Double tTotalQty = this.transTotalQty(bmsSaRecDtl.getTotalLine(), dtll.getUnitprice());
//        bmsSaRecDtl.setGoodsqty(tTotalQty);
        //bmsSaRecDtl.setGoodsqty((ObjectUtils.isEmpty(dtll.getGoodsqty()) ? 0.0 : dtll.getGoodsqty()) - (ObjectUtils.isEmpty(dtll.getTotalrecqty()) ? 0.0 : dtll.getTotalrecqty()));
        /**
         *单价
         */
        bmsSaRecDtl.setUnitprice(dtll.getUnitprice());
//        /**
//         * 收款金额
//         * 收款金额：选取实际回填金额，不自动计算
//         */
//        bmsSaRecDtl.setTotalLine((ObjectUtils.isEmpty(dtll.getTotalLine()) ? 0.0 : dtll.getTotalLine()) - (ObjectUtils.isEmpty(dtll.getTotalrecmoney()) ? 0.0 : dtll.getTotalrecmoney()));
        /**
         * 业务员id
         */
        bmsSaRecDtl.setSalerid(dtll.getSalerid());
        /**
         * 业务部门ID
         */
        bmsSaRecDtl.setSalesdeptid(dtll.getSalesdeptid());
        /**
         * 收款员ID
         */
        bmsSaRecDtl.setRecsalerid(inputManL.getEmployeeid());
        /**
         * 收款部门ID
         */
        bmsSaRecDtl.setRecsalesdeptid(inputManL.getDeptid());

        bmsSaRecDtlMapper.insert(bmsSaRecDtl);
    }

    /**
     * 计算本次收款金额转相应回款数量
     *
     * @param totalLine
     * @param unitprice
     * @return
     */
    private Double transTotalQty(Double totalLine, Double unitprice) {
        Double result = 0.0;

        result = totalLine / unitprice;

        return result;
    }

    /**
     * 填写发票汇款关联信息
     *
     * @param sasettledtlid
     * @param bmsSaRecDtl
     * @param bmsSaDoctosetList
     * @param str
     */
    private void addBmsSaSettorec(Long sasettledtlid, BmsSaRecDtl bmsSaRecDtl, List<BmsSaDoctoset> bmsSaDoctosetList, BmsSaSettorec str) {
        /**
         * 收款数量
         */
        str.setGoodsqty(bmsSaRecDtl.getGoodsqty());
        /**
         * 收款金额
         */
        str.setTotalLine(bmsSaRecDtl.getTotalLine());
        /**
         * 回款单号
         */
        str.setSarecdtlid(bmsSaRecDtl.getSarecdtlid());
        /**
         * 发票单号
         * 备注：分析ERP系统代码发现，bms_sa_settorec表的sasettledtlid存的是销售细单id
         * 个人认为正确的数据存取代码：str.setSasettledtlid(bmsSaDoctosetList.get(0).getSasettledtlid());
         */
        str.setSasettledtlid(sasettledtlid);

        bmsSaSettorecMapper.insert(str);
    }

    /**
     * 写入汇款单后，更新相应的发票细单状态
     *
     * @param saSettleDtlid
     * @param sasettledtlid
     * @param goodsqty
     * @param totalLine
     * @param isDeleteTmpFlag
     */
    private void upSettleInfo(Long saSettleDtlid, Long sasettledtlid, Double goodsqty, Double totalLine, Boolean isDeleteTmpFlag, Long saleDtlId) {
        bmsSaSettleDtlMapper.updateRecfinflag(sasettledtlid, goodsqty, totalLine);
        /**
         * 删除临时发票细单信息
         */
        if (isDeleteTmpFlag) {
            bmsSaSettleDtlMapper.deleteBmsSaSettleDtlTmp(saSettleDtlid);
        }
        /**
         * 更新发票总单最终收款金额
         */
        bmsSaSettleDocMapper.updateZxTotalRecmoney(sasettledtlid, totalLine);
        /**
         * 更新销售细单的最终回款金额
         */
        bmsSaDtlMapper.updateTotalRecMoney(sasettledtlid, saleDtlId);
    }

    private List<BmsSaSettorec> getBmsSaSettorecList(Long sasettledtlid) {


        QueryWrapper<BmsSaSettorec> queryWrapper = new QueryWrapper();

        queryWrapper.lambda().eq(BmsSaSettorec::getSasettledtlid, sasettledtlid);

        List<BmsSaSettorec> bmsSaSettorecs = bmsSaSettorecMapper.selectList(queryWrapper);

        return bmsSaSettorecs;
    }

    private List<BmsSaDoctoset> getBmsSaDoctosetList(Long srcErpOrderDtlId) {
        QueryWrapper<BmsSaDoctoset> queryWrapper = new QueryWrapper();

        queryWrapper.lambda().eq(BmsSaDoctoset::getSalesdtlid, srcErpOrderDtlId);

        List<BmsSaDoctoset> list = bmsSaDoctosetMapper.selectList(queryWrapper);
        return list;
    }
}
