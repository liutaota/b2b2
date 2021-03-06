package com.zsyc.zt.inca.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.inca.entity.*;
import com.zsyc.zt.inca.mapper.*;
import com.zsyc.zt.inca.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    BmsStSumQtyMapper bmsStSumQtyMapper;
    @Resource
    ResaGspQualityCheckMapper resaGspQualityCheckMapper;
    @Resource
    GpcsPlaceAcceptDtlMapper gpcsPlaceAcceptDtlMapper;
    @Resource
    GpcsPlaceAcceptDocMapper gpcsPlaceAcceptDocMapper;
    @Resource
    GspLotConservedateMapper gspLotConservedateMapper;
    @Resource
    BmsStorerPosMapper bmsStorerPosMapper;
    @Resource
    GpcsPlacesupplyTinyMapper gpcsPlacesupplyTinyMapper;
    @Resource
    GpcsPlacepointMapper gpcsPlacepointMapper;
    @Resource
    CustomService customService;
    @Resource
    GoodsService goodsService;
    @Resource
    BmsPresOutDocMapper bmsPresOutDocMapper;
    @Resource
    BmsSaDocMapper bmsSaDocMapper;
    @Resource
    BmsSaDtlMapper bmsSaDtlMapper;
    @Resource
    BmsPresOutDtlMapper bmsPresOutDtlMapper;
    @Resource
    BmsStIoDocTmpMapper bmsStIoDocTmpMapper;
    @Resource
    BmsStIoDtlTmpMapper bmsStIoDtlTmpMapper;
    @Resource
    GpcsPlacesupplyMapper gpcsPlacesupplyMapper;
    @Resource
    GpcsPlacesupplydtlMapper gpcsPlacesupplydtlMapper;
    @Resource
    GpcsPlacesupplydtlStMapper gpcsPlacesupplydtlStMapper;
    @Resource
    GpcsPlacesupplydtlStdtlMapper gpcsPlacesupplydtlStdtlMapper;
    @Resource
    RedissonClient redissonClient;
    @Resource
    B2bGiftDocComfirmService b2bGiftDocComfirmService;
    @Resource
    BmsSaConDocMapper bmsSaConDocMapper;
    @Resource
    BmsSaConDtlMapper bmsSaConDtlMapper;
    @Resource
    BmsSaContodocMapper bmsSaConToDocMapper;
    @Resource
    BmsTrDocMapper bmsTrDocMapper;
    @Resource
    BmsTrDtlMapper bmsTrDtlMapper;
    @Resource
    IncaTrBackInsertMapper incaTrBackInsertMapper;
    @Resource
    BmsCertDtlTmpMapper bmsCertDtlTmpMapper;

    @Resource
    BmsCreditBillDtlMapper bmsCreditBillDtlMapper;

    @Resource
    BmsStDefMapper bmsStDefMapper;

    @Resource
    B2bOrderListMapper b2bOrderListMapper;
    @Resource
    B2bGiftDocComfirmMapper b2bGiftDocComfirmMapper;

    @Resource
    BmsStQtyLstMapper bmsStQtyLstMapper;
    @Resource
    BmsStIoDocMapper bmsStIoDocMapper;
    @Resource
    BmsStIoDtlMapper bmsStIoDtlMapper;
    @Resource
    BmsTrPickDocMapper bmsTrPickDocMapper;
    @Resource
    BmsSaDtlTmpMaper bmsSaDtlTmpMaper;
    @Resource
    PubEmployeeMapper pubEmployeeMapper;
    @Resource
    BmsCreditBillDocMapper bmsCreditBillDocMapper;
    private List<Integer> platformList = new ArrayList<Integer>() {{
        this.add(11);
    }};

    @Override
    public OrderResultDocVo genOrder(OrderInfoDocVo orderInfoDocVo) {


        AssertExt.notNull(orderInfoDocVo, "??????????????????");

        OrderResultDocVo resultVo = null;


        String orderNo = orderInfoDocVo.getOrderNo();
        Long orderId = orderInfoDocVo.getOrderId();
        Long customId = orderInfoDocVo.getCustomId();
        Integer entryId = orderInfoDocVo.getEntryId();

        ResultVo result1 = customService.valid(customId, entryId);

        ArrayList<Long> goodsIdList = new ArrayList<>();
        orderInfoDocVo.getOrderInfoDtlList().forEach(item -> {
            goodsIdList.add(item.getGoodsId());
        });
        result1 = ResultVo.failAppend(result1, goodsService.valid(customId, entryId, goodsIdList));

        if (result1.isError()) {
            return OrderResultDocVo.validReturn(result1, orderNo, customId);
        }


        RLock lock = redissonClient.getLock("order_gen");
        /**
         * ???????????????
         */
        lock.lock(2, TimeUnit.MINUTES);

        try {

            log.info("??????????????????---------????????????----------");

            QueryWrapper<B2bOrderList> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(B2bOrderList::getB2bOrderId, orderId);
            queryWrapper.lambda().eq(B2bOrderList::getB2bOrderType, orderInfoDocVo.getOrderType());
            queryWrapper.lambda().eq(B2bOrderList::getVersion, orderInfoDocVo.getVersion());
            if (b2bOrderListMapper.selectCount(queryWrapper) == 0) {

                B2bOrderList b2bOrderList = new B2bOrderList();

                b2bOrderList.setB2bOrderNo(orderNo);
                b2bOrderList.setB2bOrderId(orderId);
                b2bOrderList.setB2bOrderType(orderInfoDocVo.getOrderType());
                b2bOrderList.setVersion(orderInfoDocVo.getVersion());
                b2bOrderList.setCreateDate(LocalDateTime.now());

                b2bOrderList.setHintCount(1);

                b2bOrderList.setSrdData(JSON.toJSONString(orderInfoDocVo, SerializerFeature.PrettyFormat,
                        SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.UseSingleQuotes));

                b2bOrderListMapper.insert(b2bOrderList);


                /**
                 * ????????????????????????
                 */
                OrderInfoDocVo giftOrder = decomposeOrderDetails(orderInfoDocVo, true);

                /**
                 * ????????????????????????
                 */
                OrderInfoDocVo normalOrder = decomposeOrderDetails(orderInfoDocVo, false);

                if (ObjectUtil.isNotNull(giftOrder)) {
                    resultVo = OrderResultDocVo.undoneAppend(resultVo, genOrderGift(giftOrder), orderNo, customId);
                    /**
                     *  2020/10/29 ??????????????????  ?????????
                      */
                    confirmGiftOrder(orderNo, entryId, 2, 2, platformList);
                }

                if (customService.isPsCustom(orderInfoDocVo.getCustomId(), orderInfoDocVo.getEntryId())) {
                    resultVo = OrderResultDocVo.undoneAppend(resultVo, genOrderPs(normalOrder), orderNo, customId);
                    //// TODO: 2020/9/11 ???????????????????????????
                    //confirmPsOrder(orderNo, entryId, 1, 2, platformList);
                } else {
                    resultVo = OrderResultDocVo.undoneAppend(resultVo, genOrderSa(normalOrder), orderNo, customId);
                    //// TODO: 2020/9/11 ???????????????????????????
                    //confirmSaOrder(orderNo, entryId, 2, 1, platformList);
                }


            } else {

                b2bOrderListMapper.add1HintCount(queryWrapper);
                AssertExt.fail("????????????????????????!!!!");
            }

        } catch (Throwable e) {
            log.error("?????????????????????!", e);
            AssertExt.fail("?????????????????????:" + e.getMessage(), e);
        } finally {
            log.info("??????????????????---------??????----------");
            lock.unlock();
        }

        return OrderResultDocVo.validReturn(resultVo, orderNo, customId);
    }

    /**
     * ????????????
     *
     * @param orderInfoDocVo
     * @return
     */
    @Override
    public OrderResultDocVo receiptOrder(OrderInfoDocVo orderInfoDocVo) {
        AssertExt.notNull(orderInfoDocVo, "??????????????????");

        OrderResultDocVo resultVo = null;

        String orderId = orderInfoDocVo.getOrderNo();
        AssertExt.notNull(orderId, "????????????id??????");

        Long customId = orderInfoDocVo.getCustomId();
        AssertExt.notNull(customId, "??????id??????");

        Integer entryId = orderInfoDocVo.getEntryId();
        AssertExt.notNull(entryId, "????????????id??????");

        if (customService.isPsCustom(orderInfoDocVo.getCustomId(), orderInfoDocVo.getEntryId())) {
            /**
             * ????????????????????????
             */
            resultVo = receiptOrderPs(orderInfoDocVo);

            /**
             * ????????????????????????
             */
            resultVo = acceptanceOrderPs(orderInfoDocVo);
        }
        return OrderResultDocVo.validReturn(resultVo, orderId, customId);
    }

    /***
     * ????????????
     * @param orderInfoDocVo
     * @return
     */
    private OrderResultDocVo acceptanceOrderPs(OrderInfoDocVo orderInfoDocVo) {
        OrderResultDocVo resultVo = null;
        String orderId = orderInfoDocVo.getOrderNo();
        Long customId = orderInfoDocVo.getCustomId();
        Integer entryId = orderInfoDocVo.getEntryId();
        Long receiptManId = orderInfoDocVo.getReceiptManId();

        /**
         * ???????????????????????????????????????????????????
         */
        /**
         * ?????????????????????????????????
         */
        for (OrderInfoDtlVo orderInfoDtlVo : orderInfoDocVo.getOrderInfoDtlList()) {
            Long goodsid = orderInfoDtlVo.getGoodsId();
            AssertExt.notNull(goodsid, "?????????????????????id?????????");

            Long srcErpOrderId = orderInfoDtlVo.getSrcErpOrderId();
            AssertExt.notNull(srcErpOrderId, "????????????id?????????");

            Long srcErpOrderDtlId = orderInfoDtlVo.getSrcErpOrderDtlId();
            AssertExt.notNull(srcErpOrderDtlId, "????????????id?????????");
        }

        /**
         * ??????????????????gpcs_place_accept_doc
         */
        GpcsPlaceAcceptDoc gpcsPlaceAcceptDoc = new GpcsPlaceAcceptDoc();
        gpcsPlaceAcceptDoc.setPlacepointid(customId);
        gpcsPlaceAcceptDoc.setAcceptmanid(receiptManId);
        gpcsPlaceAcceptDoc.setAcceptdate(LocalDateTime.now());
        gpcsPlaceAcceptDoc.setSource("B2B?????????" + orderInfoDocVo.getOrderNo());

        this.gpcsPlaceAcceptDocMapper.insert(gpcsPlaceAcceptDoc);
        /**
         * ?????????????????????????????????????????????
         */
        for (OrderInfoDtlVo orderInfoDtlVo : orderInfoDocVo.getOrderInfoDtlList()) {
            Long goodsid = orderInfoDtlVo.getGoodsId();
            Long srcErpOrderId = orderInfoDtlVo.getSrcErpOrderId();
            Long srcErpOrderDtlId = orderInfoDtlVo.getSrcErpOrderDtlId();


            /**
             * ?????????????????????gpcs_placesupply???
             */
            GpcsPlacesupply gpcsPlacesupply = this.gpcsPlacesupplyMapper.selectById(srcErpOrderId);
            if (ObjectUtils.isEmpty(gpcsPlacesupply)) {
                return OrderResultDocVo.failErrorMessageAppend(resultVo, "??????????????????" + srcErpOrderId + "?????????", orderId, customId);
            }
            /**
             * ?????????????????????gpcs_placesupplydtl???
             */
            GpcsPlacesupplydtl gpcsPlacesupplydtl = this.gpcsPlacesupplydtlMapper.selectById(srcErpOrderDtlId);
            if (ObjectUtils.isEmpty(gpcsPlacesupply)) {
                return OrderResultDocVo.failErrorMessageAppend(resultVo, "??????????????????" + srcErpOrderDtlId + "?????????", orderId, customId);
            }
            /**
             * ???????????????????????????gpcs_placepoint???
             */
            GpcsPlacepoint gpcsPlacepoint = this.gpcsPlacepointMapper.selectById(customId);
            if (ObjectUtils.isEmpty(gpcsPlacepoint)) {
                return OrderResultDocVo.failErrorMessageAppend(resultVo, "???????????????" + customId + "?????????", orderId, customId);
            }
            /**
             * ??????gpcs_placesupplydtl_st??????
             */
            GpcsPlacesupplydtlSt gpcsPlacesupplydtlSt = this.getGpcsPlacesupplydtl(gpcsPlacesupplydtl.getPlacesupplydtlid());
            if (ObjectUtils.isEmpty(gpcsPlacesupplydtlSt)) {
                return OrderResultDocVo.failErrorMessageAppend(resultVo, "????????????ST?????????" + gpcsPlacesupplydtl.getPlacesupplydtlid() + "?????????", orderId, customId);
            }
            /**
             * ???????????????????????????gpcs_placesupply_tiny???
             */
            GpcsPlacesupplyTiny gpcsPlacesupplyTiny = this.getGpcsPlacesupplyTinyId(gpcsPlacesupplydtlSt.getPlacesupplydtlstid());
            if (ObjectUtils.isEmpty(gpcsPlacesupplyTiny)) {
                return OrderResultDocVo.failErrorMessageAppend(resultVo, "???????????????????????????", orderId, customId);
            }

            /**
             * ???????????????????????????
             */
            if (this.isExistGpcsPlaceAcceptDtl(gpcsPlacesupplyTiny.getPlacesupplytinyid())) {
                return OrderResultDocVo.failErrorMessageAppend(resultVo, "???????????????(" + gpcsPlacesupplyTiny.getPlacesupplytinyid() + ")???????????????????????????", orderId, customId);
            }

            /**
             * ???????????????????????????
             */
            BmsStorerPos bmsStorerPos = this.getBmsStorerPos(goodsid, customId);
            Long sthouseid = bmsStorerPos.getSthouseid();//??????id

            /**
             * ????????????????????????
             */
            if (ObjectUtils.isEmpty(bmsStorerPos.getPosid())) {
                return OrderResultDocVo.failErrorMessageAppend(resultVo, "????????????ID???" + gpcsPlacesupplyTiny.getPlacesupplytinyid() + "???????????????????????????,?????????????????????", orderId, customId);
            }

            /**
             * ??????????????????
             */
            GpcsPlacesupplyAcceptTinyVo gpcsPlacesupplyAcceptTinyVo = this.getGpcsPlacesupplyAcceptTinyVo(gpcsPlacesupplyTiny.getPlacesupplytinyid());
            Long lotid = gpcsPlacesupplyAcceptTinyVo.getLotid();

            /**
             * ????????????????????????
             */
            GpcsPlaceSupplyAcceptInfoVo gpcsPlaceSupplyAcceptInfoVo = this.getGpcsPlaceSupplyAcceptInfoVo(gpcsPlacesupplyTiny.getPlacesupplytinyid());

            /**
             * ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
             */
            if (this.isNotExistGspLotConservedate(lotid, sthouseid)) {
                GspLotConservedate gspLotConservedate = new GspLotConservedate();
                gspLotConservedate.setLotid(lotid);
                gspLotConservedate.setSthouseid(sthouseid);
                gspLotConservedate.setConservedate(LocalDateTime.now());

                this.gspLotConservedateMapper.insert(gspLotConservedate);
            }

            /**
             *
             */

            /**
             * ??????resaleprice????????????
             */
            Double resaleprice = this.getResaleprice(gpcsPlacesupplyAcceptTinyVo.getGoodsid(), customId);
            /**
             * ??????????????????
             */
            String placecentername = this.getPlacecentername(gpcsPlacesupplyAcceptTinyVo.getPlacecenterid());

            /**
             * ????????????????????????
             */
            GpcsPlaceAcceptDtl gpcsPlaceAcceptDtl = new GpcsPlaceAcceptDtl();
            this.insertGpcsPlaceAcceptDtl(gpcsPlaceAcceptDtl, gpcsPlaceAcceptDoc,
                    gpcsPlacesupplyAcceptTinyVo, goodsid, lotid, bmsStorerPos,
                    orderInfoDtlVo, gpcsPlaceSupplyAcceptInfoVo, receiptManId,
                    gpcsPlacesupplyTiny, resaleprice, orderInfoDocVo);

            /**
             * ??????????????????
             */
            ResaGspQualityCheck resaGspQualityCheck = new ResaGspQualityCheck();
            this.insertResaGspQualityCheck(resaGspQualityCheck, gpcsPlaceAcceptDoc,
                    gpcsPlacesupplyAcceptTinyVo, goodsid, lotid, bmsStorerPos,
                    orderInfoDtlVo, gpcsPlaceSupplyAcceptInfoVo, receiptManId,
                    gpcsPlacesupplyTiny, resaleprice, orderInfoDocVo, gpcsPlaceAcceptDtl);
            /**
             *
             */
            /**
             * ???????????????
             */
            BmsStIoDoc bmsStIoDoc = new BmsStIoDoc();
            bmsStIoDoc.setCredate(LocalDateTime.now());
            bmsStIoDoc.setComefrom(76);
            bmsStIoDoc.setCompanyid(gpcsPlacesupplyAcceptTinyVo.getPlacecenterid());
            bmsStIoDoc.setCompanyname(placecentername);
            bmsStIoDoc.setGoodsid(goodsid);
            bmsStIoDoc.setInoutflag(1);
            bmsStIoDoc.setInqty(orderInfoDtlVo.getNum());
            bmsStIoDoc.setOldqty(orderInfoDtlVo.getNum());
            bmsStIoDoc.setGoodsunit(gpcsPlacesupplyAcceptTinyVo.getGoodsunit());
            bmsStIoDoc.setUsestatus(1);
            bmsStIoDoc.setStorageid(gpcsPlacesupplyAcceptTinyVo.getStorageid());
            bmsStIoDoc.setSourcetable(76);
            bmsStIoDoc.setSourceid(gpcsPlaceAcceptDtl.getAcceptdtlid());
            bmsStIoDoc.setPlacetable(2);
            bmsStIoDoc.setPlacedtlid(gpcsPlaceAcceptDtl.getAcceptdtlid());
            bmsStIoDoc.setEntryid(orderInfoDocVo.getEntryId().longValue());
            bmsStIoDoc.setUpstatus(3);

            this.bmsStIoDocMapper.insert(bmsStIoDoc);
            /**
             *
             */
            /**
             * ??????????????????
             */
            BmsStIoDtl bmsStIoDtl = new BmsStIoDtl();
            bmsStIoDtl.setInoutid(bmsStIoDoc.getInoutid());
            bmsStIoDtl.setBatchid(gpcsPlacesupplyAcceptTinyVo.getBatchid());
            bmsStIoDtl.setLotid(lotid);
            bmsStIoDtl.setPosid(bmsStorerPos.getPosid());
            bmsStIoDtl.setGoodsdtlid(gpcsPlacesupplyAcceptTinyVo.getGoodsdtlid());
            bmsStIoDtl.setGoodsstatusid(1L);
            bmsStIoDtl.setDtlgoodsqty(gpcsPlacesupplyAcceptTinyVo.getGoodsqty());
            bmsStIoDtl.setGoodsunitflag(2);

            this.bmsStIoDtlMapper.insert(bmsStIoDtl);
            /**
             *
             */
            /**
             * ????????????????????????
             */
            Long usemm = this.getUsemm(bmsStIoDoc.getEntryid());
            /** ?????????????????????????????????????????????????????? */
            if (this.isNotExistBmsStSumQty(usemm, gpcsPlacesupplyAcceptTinyVo.getStorageid(), gpcsPlacesupplyAcceptTinyVo.getGoodsid())) {
                BmsStSumQty bmsStSumQty = new BmsStSumQty();
                bmsStSumQty.setUsemm(usemm);
                bmsStSumQty.setStorageid(gpcsPlacesupplyAcceptTinyVo.getStorageid());
                bmsStSumQty.setGoodsid(gpcsPlacesupplyAcceptTinyVo.getGoodsid());

                this.bmsStSumQtyMapper.insert(bmsStSumQty);
            }
            /**
             * ??????????????????????????????????????????
             */
            this.bmsStSumQtyMapper.updateOtherinqty(orderInfoDtlVo.getNum(), usemm,
                    gpcsPlacesupplyAcceptTinyVo.getStorageid(),
                    gpcsPlacesupplyAcceptTinyVo.getGoodsid());
            /**
             *
             */
            /**
             * ?????????????????????????????????
             *
             */
            this.bmsStSumQtyMapper.deleteReceiveGoodsDtlTmp(bmsStIoDtl.getIodtlid());

            /**
             * ???????????????????????????
             */
            BmsStQtyLst bmsStQtyLst = new BmsStQtyLst();
            bmsStQtyLst.setStorageid(gpcsPlacesupplyAcceptTinyVo.getStorageid().intValue());
            bmsStQtyLst.setGoodsid(gpcsPlacesupplyAcceptTinyVo.getGoodsid());
            bmsStQtyLst.setGoodsdetailid(gpcsPlacesupplyAcceptTinyVo.getGoodsdtlid());
            bmsStQtyLst.setBatchid(gpcsPlacesupplyAcceptTinyVo.getBatchid());
            bmsStQtyLst.setLotid(gpcsPlacesupplyAcceptTinyVo.getLotid());
            bmsStQtyLst.setPosid(bmsStorerPos.getPosid());
            bmsStQtyLst.setGoodsstatusid(1);
            bmsStQtyLst.setGoodsqty(orderInfoDtlVo.getNum());

            this.bmsStQtyLstMapper.insert(bmsStQtyLst);
            /***/
            /**
             * ?????????????????????
             */
            this.bmsStIoDocMapper.updateUsestatus(orderInfoDocVo.getReceiptManId(), bmsStIoDoc.getInoutid());

            /**
             * ????????????????????????
             */
            this.bmsStIoDocMapper.updateGoodstopos(bmsStorerPos.getPosid(), orderInfoDtlVo.getGoodsId(), customId);
            /**
             * ????????????????????????????????????
             */
            this.gpcsPlacesupplyTinyMapper.updateCheck(orderInfoDocVo.getReceiptManId(), gpcsPlacesupplyTiny.getPlacesupplytinyid());
        }

        /**
         * ??????????????????
         */
        return OrderResultDocVo.validReturn(resultVo, orderId, customId);
    }

    /**
     * ?????????????????????????????????????????????
     *
     * @param usemm
     * @param storageid
     * @param goodsid
     * @return
     */
    private boolean isNotExistBmsStSumQty(Long usemm, Long storageid, Long goodsid) {
        boolean result = true;

        QueryWrapper<BmsStSumQty> queryWrapper = new QueryWrapper();

        queryWrapper.lambda().eq(BmsStSumQty::getUsemm, usemm);
        queryWrapper.lambda().eq(BmsStSumQty::getStorageid, storageid);
        queryWrapper.lambda().eq(BmsStSumQty::getGoodsid, goodsid);

        List<BmsStSumQty> list = this.bmsStSumQtyMapper.selectList(queryWrapper);
        if (ObjectUtils.isNotEmpty(list)) {
            result = false;
        }

        return true;
    }

    /**
     * ?????????????????????
     *
     * @param entryid
     * @return
     */
    private Long getUsemm(Long entryid) {
        Long usemm = 0l;

        List<Long> list = this.bmsStIoDocMapper.getUsemm(entryid);
        if (ObjectUtils.isNotEmpty(list)) {
            usemm = list.get(0);
        }
        return usemm;
    }

    private void insertResaGspQualityCheck(ResaGspQualityCheck resaGspQualityCheck, GpcsPlaceAcceptDoc gpcsPlaceAcceptDoc,
                                           GpcsPlacesupplyAcceptTinyVo gpcsPlacesupplyAcceptTinyVo, Long goodsid, Long lotid,
                                           BmsStorerPos bmsStorerPos, OrderInfoDtlVo orderInfoDtlVo,
                                           GpcsPlaceSupplyAcceptInfoVo gpcsPlaceSupplyAcceptInfoVo, Long receiptManId,
                                           GpcsPlacesupplyTiny gpcsPlacesupplyTiny, Double resaleprice,
                                           OrderInfoDocVo orderInfoDocVo, GpcsPlaceAcceptDtl gpcsPlaceAcceptDtl) {

        resaGspQualityCheck.setGoodsid(orderInfoDtlVo.getGoodsId());
        resaGspQualityCheck.setGoodsdetailid(gpcsPlacesupplyAcceptTinyVo.getGoodsdtlid());
        resaGspQualityCheck.setCheckdate(LocalDateTime.now());
        resaGspQualityCheck.setGoodsqty(orderInfoDtlVo.getNum());
        resaGspQualityCheck.setUnqualifiedqty(0.0);
        resaGspQualityCheck.setGoodsunit(gpcsPlacesupplyAcceptTinyVo.getGoodsunit());
        resaGspQualityCheck.setCompanyid(gpcsPlacesupplyAcceptTinyVo.getPlacecenterid());
        resaGspQualityCheck.setLotid(lotid);
        resaGspQualityCheck.setGoodsstatusid(1l);
        resaGspQualityCheck.setPosid(bmsStorerPos.getPosid());
        resaGspQualityCheck.setCertifiedflag(0);
        resaGspQualityCheck.setCheckconclusion(1);
        resaGspQualityCheck.setCheckmanid(receiptManId);
        resaGspQualityCheck.setSourceid(gpcsPlaceAcceptDtl.getAcceptdtlid());
        resaGspQualityCheck.setSourcetable(1);
        resaGspQualityCheck.setRefuseflag(1);
        resaGspQualityCheck.setArrivedate(LocalDateTime.now());
        resaGspQualityCheck.setFacequality(1);
        resaGspQualityCheck.setStorageid(gpcsPlacesupplyAcceptTinyVo.getStorageid());
        resaGspQualityCheck.setTransportid(gpcsPlaceSupplyAcceptInfoVo.getTransportid());
        resaGspQualityCheck.setTranmethod(gpcsPlaceSupplyAcceptInfoVo.getTranmethod() == null ? 0 : gpcsPlaceSupplyAcceptInfoVo.getTranmethod().intValue());
        resaGspQualityCheck.setStartplace(gpcsPlaceSupplyAcceptInfoVo.getStartplace());
        resaGspQualityCheck.setStartdate(gpcsPlaceSupplyAcceptInfoVo.getStartdate());
        resaGspQualityCheck.setReachdate(gpcsPlaceSupplyAcceptInfoVo.getReachdate());
        resaGspQualityCheck.setStarttemperature(gpcsPlaceSupplyAcceptInfoVo.getStarttemperature());
        resaGspQualityCheck.setReachtemperature(gpcsPlaceSupplyAcceptInfoVo.getReachtemperature());

        this.resaGspQualityCheckMapper.insert(resaGspQualityCheck);
    }

    /**
     * ????????????????????????
     *
     * @param gpcsPlaceAcceptDtl
     * @param gpcsPlaceAcceptDoc
     * @param gpcsPlacesupplyAcceptTinyVo
     * @param goodsid
     * @param lotid
     * @param bmsStorerPos
     * @param orderInfoDtlVo
     * @param gpcsPlaceSupplyAcceptInfoVo
     * @param receiptManId
     * @param gpcsPlacesupplyTiny
     * @param resaleprice
     * @param orderInfoDocVo
     */
    private void insertGpcsPlaceAcceptDtl(GpcsPlaceAcceptDtl gpcsPlaceAcceptDtl, GpcsPlaceAcceptDoc gpcsPlaceAcceptDoc,
                                          GpcsPlacesupplyAcceptTinyVo gpcsPlacesupplyAcceptTinyVo, Long goodsid, Long lotid,
                                          BmsStorerPos bmsStorerPos, OrderInfoDtlVo orderInfoDtlVo,
                                          GpcsPlaceSupplyAcceptInfoVo gpcsPlaceSupplyAcceptInfoVo, Long receiptManId,
                                          GpcsPlacesupplyTiny gpcsPlacesupplyTiny, Double resaleprice,
                                          OrderInfoDocVo orderInfoDocVo) {
        gpcsPlaceAcceptDtl.setAcceptdocid(gpcsPlaceAcceptDoc.getAcceptdocid());
        gpcsPlaceAcceptDtl.setGoodsid(goodsid);
        gpcsPlaceAcceptDtl.setGoodsdetailid(gpcsPlacesupplyAcceptTinyVo.getGoodsdtlid());
        gpcsPlaceAcceptDtl.setBatchid(gpcsPlacesupplyAcceptTinyVo.getBatchid());
        gpcsPlaceAcceptDtl.setLotid(lotid);
        gpcsPlaceAcceptDtl.setPosid(bmsStorerPos.getPosid());
        /*????????????????????????????????????????????????????????????????????????????????????   2017.12.26  liuwd*/
        gpcsPlaceAcceptDtl.setGoodsstatusid(1l);
        gpcsPlaceAcceptDtl.setPsgoodsqty(gpcsPlacesupplyAcceptTinyVo.getGoodsqty());
        gpcsPlaceAcceptDtl.setAcceptqty(orderInfoDtlVo.getNum());
        gpcsPlaceAcceptDtl.setUnqualifiedqty(0.0);
        gpcsPlaceAcceptDtl.setOldsupplytinyid(gpcsPlacesupplyTiny.getPlacesupplytinyid());
        gpcsPlaceAcceptDtl.setPlaceprice(orderInfoDtlVo.getPrice());
        gpcsPlaceAcceptDtl.setAccmoney(this.multi(orderInfoDtlVo.getNum(), orderInfoDtlVo.getPrice()));
        gpcsPlaceAcceptDtl.setResaleprice(resaleprice);
        gpcsPlaceAcceptDtl.setResalemoney(this.multi(orderInfoDtlVo.getNum(), resaleprice));
        gpcsPlaceAcceptDtl.setTransportid(gpcsPlaceSupplyAcceptInfoVo.getTransportid());
        gpcsPlaceAcceptDtl.setTranmethod(gpcsPlaceSupplyAcceptInfoVo.getTranmethod() == null ? 0 : gpcsPlaceSupplyAcceptInfoVo.getTranmethod().intValue());
        gpcsPlaceAcceptDtl.setColdequip(gpcsPlaceSupplyAcceptInfoVo.getColdequip() == null ? 0 : gpcsPlaceSupplyAcceptInfoVo.getColdequip().intValue());
        gpcsPlaceAcceptDtl.setStartplace(gpcsPlaceSupplyAcceptInfoVo.getStartplace());
        gpcsPlaceAcceptDtl.setStartdate(gpcsPlaceSupplyAcceptInfoVo.getStartdate());
        gpcsPlaceAcceptDtl.setReachdate(gpcsPlaceSupplyAcceptInfoVo.getReachdate());
        gpcsPlaceAcceptDtl.setStarttemperature(gpcsPlaceSupplyAcceptInfoVo.getStarttemperature());
        gpcsPlaceAcceptDtl.setReachtemperature(gpcsPlaceSupplyAcceptInfoVo.getReachtemperature());
        gpcsPlaceAcceptDtl.setIsreach(gpcsPlaceSupplyAcceptInfoVo.getIsreach());
        gpcsPlaceAcceptDtl.setCheckmanid(receiptManId);
        gpcsPlaceAcceptDtl.setCheckmanid2(receiptManId);
        gpcsPlaceAcceptDtl.setMemo(orderInfoDocVo.getRemark());
        gpcsPlaceAcceptDtl.setPending(gpcsPlaceSupplyAcceptInfoVo.getPending() == null ? "" : gpcsPlaceSupplyAcceptInfoVo.getPending().toString());

        this.gpcsPlaceAcceptDtlMapper.insert(gpcsPlaceAcceptDtl);
    }

    /**
     * ??????
     *
     * @param num
     * @param price
     * @return
     */
    private Double multi(Double num, Double price) {
        BigDecimal result = BigDecimal.valueOf(0.0);
        BigDecimal numb = BigDecimal.valueOf(num);
        BigDecimal priceb = BigDecimal.valueOf(price);

        result = numb.multiply(priceb);

        return result.doubleValue();
    }

    /**
     * ??????????????????
     *
     * @param placecenterid
     * @return
     */
    private String getPlacecentername(Long placecenterid) {
        String placecentername = "";

        List<String> placecenternameList = this.gpcsPlacesupplyTinyMapper.getPlacecentername(placecenterid);
        if (ObjectUtils.isNotEmpty(placecenternameList)) {
            placecentername = placecenternameList.get(0).toString();
        }

        return placecentername;
    }

    /**
     * ??????resaleprice????????????
     *
     * @param goodsid
     * @param customId
     * @return
     */
    private Double getResaleprice(Long goodsid, Long customId) {
        Double resaleprice = 0.0;

        List<Double> resalepriceList = this.gpcsPlacesupplyTinyMapper.getResaleprice(goodsid, customId);
        if (ObjectUtils.isNotEmpty(resalepriceList)) {
            resaleprice = resalepriceList.get(0) == null ? 0.0 : resalepriceList.get(0).doubleValue();
        }

        return resaleprice;
    }

    /**
     * ???????????????????????????????????????
     *
     * @param lotid
     * @param sthouseid
     * @return
     */
    private boolean isNotExistGspLotConservedate(Long lotid, Long sthouseid) {
        Boolean result = true;

        QueryWrapper<GspLotConservedate> queryWrapper = new QueryWrapper();

        queryWrapper.lambda().eq(GspLotConservedate::getLotid, lotid);
        queryWrapper.lambda().eq(GspLotConservedate::getSthouseid, sthouseid);

        List<GspLotConservedate> gspLotConservedateList = this.gspLotConservedateMapper.selectList(queryWrapper);
        if (ObjectUtils.isNotEmpty(gspLotConservedateList)) {
            result = true;
        }

        return result;
    }

    /**
     * ????????????????????????Vo
     *
     * @param placesupplytinyid
     * @return
     */
    private GpcsPlaceSupplyAcceptInfoVo getGpcsPlaceSupplyAcceptInfoVo(Long placesupplytinyid) {
        GpcsPlaceSupplyAcceptInfoVo result = null;

        List<GpcsPlaceSupplyAcceptInfoVo> list = this.gpcsPlacesupplyTinyMapper.getGpcsPlaceSupplyAcceptInfoVo(placesupplytinyid);
        if (ObjectUtils.isNotEmpty(list)) {
            result = list.get(0);
        }

        return result;
    }

    /**
     * ????????????????????????VO
     *
     * @param placesupplytinyid
     * @return
     */
    private GpcsPlacesupplyAcceptTinyVo getGpcsPlacesupplyAcceptTinyVo(Long placesupplytinyid) {
        GpcsPlacesupplyAcceptTinyVo result = null;

        List<GpcsPlacesupplyAcceptTinyVo> list = this.gpcsPlacesupplyTinyMapper.getGpcsPlacesupplyAcceptTinyVo(placesupplytinyid);
        if (ObjectUtils.isNotEmpty(list)) {
            result = list.get(0);
        }

        return result;
    }

    /**
     * ????????????????????????
     *
     * @param goodsid
     * @param customId
     * @return
     */
    private BmsStorerPos getBmsStorerPos(Long goodsid, Long customId) {
        BmsStorerPos result = null;

        List<BmsStorerPos> list = this.bmsStorerPosMapper.getBmsStorerPos(goodsid, customId);
        if (ObjectUtils.isNotEmpty(list)) {
            result = list.get(0);
        }

        return result;
    }

    /**
     * ????????????????????????
     *
     * @param customId
     * @param goodsid
     * @return
     */
    private boolean isNotExistPos(Long customId, Long goodsid) {
        boolean result = false;

        List<Long> posList = this.gpcsPlacesupplyTinyMapper.getGoodstopos(customId, goodsid);
        if (ObjectUtils.isEmpty(posList)) {
            result = true;
        }

        return result;
    }

    /**
     * ??????????????????????????????????????????
     *
     * @param placesupplytinyid
     * @return
     */
    private boolean isExistGpcsPlaceAcceptDtl(Long placesupplytinyid) {
        boolean result = false;

        List<Object> list = this.gpcsPlacesupplyTinyMapper.getGpcsPlaceAcceptDtlList(placesupplytinyid);
        if (ObjectUtils.isNotEmpty(list)) {
            result = true;
        }

        return result;
    }

    private OrderResultDocVo receiptOrderPs(OrderInfoDocVo orderInfoDocVo) {
        OrderResultDocVo resultVo = null;
        String orderId = orderInfoDocVo.getOrderNo();
        Long customId = orderInfoDocVo.getCustomId();
        Integer entryId = orderInfoDocVo.getEntryId();

        /**
         * ???????????????????????????????????????????????????
         */
        /**
         * ?????????????????????????????????
         */
        for (OrderInfoDtlVo orderInfoDtlVo : orderInfoDocVo.getOrderInfoDtlList()) {
            Long goodsid = orderInfoDtlVo.getGoodsId();
            AssertExt.notNull(goodsid, "?????????????????????id?????????");

            Long srcErpOrderId = orderInfoDtlVo.getSrcErpOrderId();
            AssertExt.notNull(srcErpOrderId, "????????????id?????????");

            Long srcErpOrderDtlId = orderInfoDtlVo.getSrcErpOrderDtlId();
            AssertExt.notNull(srcErpOrderDtlId, "????????????id?????????");
        }

        /**
         * ???????????????????????????????????????????????????
         */
        for (OrderInfoDtlVo orderInfoDtlVo : orderInfoDocVo.getOrderInfoDtlList()) {
            Long goodsid = orderInfoDtlVo.getGoodsId();
            Long srcErpOrderId = orderInfoDtlVo.getSrcErpOrderId();
            Long srcErpOrderDtlId = orderInfoDtlVo.getSrcErpOrderDtlId();


            /**
             * ?????????????????????gpcs_placesupply???
             */
            GpcsPlacesupply gpcsPlacesupply = this.gpcsPlacesupplyMapper.selectById(srcErpOrderId);
            if (ObjectUtils.isEmpty(gpcsPlacesupply)) {
                return OrderResultDocVo.failErrorMessageAppend(resultVo, "??????????????????" + srcErpOrderId + "?????????", orderId, customId);
            }


            /**
             * ?????????????????????gpcs_placesupplydtl???
             */
            GpcsPlacesupplydtl gpcsPlacesupplydtl = this.gpcsPlacesupplydtlMapper.selectById(srcErpOrderDtlId);
            if (ObjectUtils.isEmpty(gpcsPlacesupply)) {
                return OrderResultDocVo.failErrorMessageAppend(resultVo, "??????????????????" + srcErpOrderDtlId + "?????????", orderId, customId);
            }
            /**
             * ???????????????????????????gpcs_placepoint???
             */
            GpcsPlacepoint gpcsPlacepoint = this.gpcsPlacepointMapper.selectById(customId);
            if (ObjectUtils.isEmpty(gpcsPlacepoint)) {
                return OrderResultDocVo.failErrorMessageAppend(resultVo, "???????????????" + customId + "?????????", orderId, customId);
            }
            /**
             * ??????gpcs_placesupplydtl_st??????
             */
            GpcsPlacesupplydtlSt gpcsPlacesupplydtlSt = this.getGpcsPlacesupplydtl(gpcsPlacesupplydtl.getPlacesupplydtlid());
            if (ObjectUtils.isEmpty(gpcsPlacesupplydtlSt)) {
                return OrderResultDocVo.failErrorMessageAppend(resultVo, "????????????ST?????????" + gpcsPlacesupplydtl.getPlacesupplydtlid() + "?????????", orderId, customId);
            }
            /**
             * ???????????????????????????gpcs_placesupply_tiny???
             */
            GpcsPlacesupplyTiny gpcsPlacesupplyTiny = this.getGpcsPlacesupplyTinyId(gpcsPlacesupplydtlSt.getPlacesupplydtlstid());
            if (ObjectUtils.isEmpty(gpcsPlacesupplyTiny)) {
                return OrderResultDocVo.failErrorMessageAppend(resultVo, "???????????????????????????", orderId, customId);
            }

            /**
             * 1.?????????????????????????????????
             */
            this.gpcsPlacesupplyTinyMapper.updateTinyRecieveQty(orderInfoDocVo.getReceiptManId(), orderInfoDtlVo.getNum(), gpcsPlacesupplyTiny.getPlacesupplytinyid());
            /**
             * 2.??????????????????????????????????????????
             */
            this.gpcsPlacepointMapper.updateGpcsLastSupplySalesDate(gpcsPlacepoint.getPlacepointid(), orderInfoDtlVo.getGoodsId());
            /**
             * 3.????????????????????????
             */
            this.gpcsPlacepointMapper.updatePubEntryGoods(gpcsPlacesupplyTiny.getAcceptdate(), orderInfoDocVo.getEntryId(), orderInfoDtlVo.getGoodsId());
        }

        /**
         * ????????????????????????
         */
        return OrderResultDocVo.validReturn(resultVo, orderId, customId);
    }

    /**
     * ???????????????????????????gpcs_placesupply_tiny???
     *
     * @param placesupplydtlstid
     * @return
     */
    private GpcsPlacesupplyTiny getGpcsPlacesupplyTinyId(Long placesupplydtlstid) {
        GpcsPlacesupplyTiny result = null;

        QueryWrapper<GpcsPlacesupplyTiny> queryWrapper = new QueryWrapper();

        queryWrapper.lambda().eq(GpcsPlacesupplyTiny::getPlacesupplydtlstid, placesupplydtlstid);

        List<GpcsPlacesupplyTiny> gpcsPlacesupplyTinyList = this.gpcsPlacesupplyTinyMapper.selectList(queryWrapper);
        if (ObjectUtils.isNotEmpty(gpcsPlacesupplyTinyList)) {
            result = gpcsPlacesupplyTinyList.get(0);
        }

        return result;
    }

    /**
     * ??????gpcs_placesupplydtl_st??????
     *
     * @param placesupplydtlid
     * @return
     */
    private GpcsPlacesupplydtlSt getGpcsPlacesupplydtl(Long placesupplydtlid) {
        GpcsPlacesupplydtlSt result = null;

        QueryWrapper<GpcsPlacesupplydtlSt> queryWrapper = new QueryWrapper();

        queryWrapper.lambda().eq(GpcsPlacesupplydtlSt::getPlacesupplydtlid, placesupplydtlid);

        List<GpcsPlacesupplydtlSt> gpcsPlacesupplydtlSts = this.gpcsPlacesupplydtlStMapper.selectList(queryWrapper);
        if (ObjectUtils.isNotEmpty(gpcsPlacesupplydtlSts)) {
            result = gpcsPlacesupplydtlSts.get(0);
        }

        return result;
    }


    @Override
    public void updateCallbackStatus(List<String> orderIds, Integer i) {
        bmsSaDocMapper.updateCallbackStatus(orderIds, i);
    }

    @Override
    public Integer getCountBy(Long saleId, Long b2bOrderId) {
        return bmsSaDocMapper.getCountBy(saleId, b2bOrderId);
    }


    private OrderResultDocVo genOrderGift(OrderInfoDocVo giftOrder) {

        OrderResultDocVo orderResultDocVo = null;
        Long orderId = giftOrder.getOrderId();
        String orderNo = giftOrder.getOrderNo();
        Long customId = giftOrder.getCustomId();
        Integer entryId = giftOrder.getEntryId();

        PubCustomToSaler saler = customService.getSalerByCustomId(customId, entryId);


        /**
         * ????????????????????????????????????id
         */
        if(ObjectUtil.equal(giftOrder.getOrderFrom(),4)){
            customId = customService.getByCustomStoreId(customId,giftOrder.getStoreId(),entryId);
        }

        PubCustomer customer = customService.getById(customId);
        /**
         * ????????????
         */
        BmsPresOutDoc bmsPresOutDoc = new BmsPresOutDoc();


        bmsPresOutDoc.setApiOrderId(giftOrder.getSrcOrderId());
        bmsPresOutDoc.setApiOrderNo(giftOrder.getSrcOrderNo());
        bmsPresOutDoc.setApiOrderTime(giftOrder.getSrcOrderTime());

        bmsPresOutDoc.setB2bStoreId(giftOrder.getStoreId());


        bmsPresOutDoc.setMemo((ObjectUtil.isNotNull(giftOrder.getRemark()) ? "???B2B??????,???" + giftOrder.getRemark() : "") + "???B2B????????????" + giftOrder.getOrderNo());
        bmsPresOutDoc.setB2bOrderNo(orderNo);
        bmsPresOutDoc.setB2bOrderId(orderId);
        bmsPresOutDoc.setB2bWriteBackFlag(1);
        bmsPresOutDoc.setB2bAmountTotal(giftOrder.getAmountTotal());
        bmsPresOutDoc.setB2bAmountPay(giftOrder.getAmountPay());
        bmsPresOutDoc.setB2bAmountDelivery(giftOrder.getAmountDelivery());
        bmsPresOutDoc.setB2bAmountDiscount(giftOrder.getAmountDiscount());

        bmsPresOutDoc.setB2bOrderType(giftOrder.getOrderType());
        bmsPresOutDoc.setB2bPayType(giftOrder.getPayType());

        bmsPresOutDoc.setB2bOrderFrom(giftOrder.getOrderFrom());


        bmsPresOutDoc.setB2bPayOrderNo(giftOrder.getPayOrderNo());
        bmsPresOutDoc.setB2bPayFlowNo(giftOrder.getPayFlowNo());

        bmsPresOutDoc.setCustomid(customId);
        bmsPresOutDoc.setCustomname(customer.getCustomname());
        bmsPresOutDoc.setEntryid(entryId);
        bmsPresOutDoc.setPolicytype(1);
        bmsPresOutDoc.setComefrom(1);
        bmsPresOutDoc.setPrintflag(0);
        bmsPresOutDoc.setEntryid(1);
        /**
         * 11 ?????? b2b??????
         */
        bmsPresOutDoc.setZxBhFlag(11);
        //????????????
        bmsPresOutDoc.setCredate(LocalDateTime.now());

        bmsPresOutDoc.setUsestatus(2);

        bmsPresOutDoc.setInputmanid(saler.getSalerid());

        bmsPresOutDocMapper.insert(bmsPresOutDoc);
        Integer dtlLines = 0;
        for (OrderInfoDtlVo orderInfoDtlVo : giftOrder.getOrderInfoDtlList()) {
            OrderResultDocVo result2 = genIOStockForGift(bmsPresOutDoc, orderInfoDtlVo, customId, entryId);

            if (ObjectUtil.isNotEmpty(result2.getStdDocIds())) {
                dtlLines += result2.getStdDocIds().size();
            }
            orderResultDocVo = OrderResultDocVo.undoneAppend(orderResultDocVo, result2, orderNo, customId);
        }

        if (dtlLines == 0) {
            bmsPresOutDocMapper.deleteById(bmsPresOutDoc.getPresoutid());
            orderResultDocVo = OrderResultDocVo.failErrorMessageAppend(orderResultDocVo, "????????????????????????????????????????????????????????????", orderNo, customId);


        } else {
            bmsPresOutDoc.setDtlLines(dtlLines);
            bmsPresOutDocMapper.updateById(bmsPresOutDoc);
        }


        return orderResultDocVo;
    }


    /**
     * ???????????????????????????
     *
     * @param bmsPresOutDoc
     * @param dtl
     * @return
     */
    private OrderResultDocVo genIOStockForGift(BmsPresOutDoc bmsPresOutDoc, OrderInfoDtlVo dtl, Long customId, Integer entryId) {

        PubCustomer customer = customService.getById(customId);

        List<Long> stdDocIds = new ArrayList<>();

        String orderNo = dtl.getOrderNo();
        String orderDtId = dtl.getOrderDtlId();

        OrderResultDocVo orderResultDocVo = new OrderResultDocVo(orderNo, customer.getCustomid());
        /**
         * ??????ID
         */
        Long docId = bmsPresOutDoc.getPresoutid();

        Long goodsId = dtl.getGoodsId();

        PubGoods goods = goodsService.getById(goodsId);

        double goodQty = dtl.getNum();

        List<BmsStQtyLst> stockList = goodsService.selectStockBy(customer.getCustomid(), goodsId, dtl.getLotNo(), 2, dtl.getStorageId());


        /**
         * ?????????????????????
         */
        double totalQty = 0;

        for (int i = 0; totalQty < goodQty && i < stockList.size(); i++) {
            double currentQty = 0.0;
            BmsStIoDtlTmp stIoDtlTmp = new BmsStIoDtlTmp();

            BmsPresOutDtl bmsPresOutDtl = new BmsPresOutDtl();
            BeanUtil.copyProperties(bmsPresOutDoc, bmsPresOutDtl);

            bmsPresOutDtl.setApiOrderDtlId(dtl.getSrcOrderDtlId());

            bmsPresOutDtl.setPresoutid(docId);

            bmsPresOutDtl.setGoodsid(goodsId);

            BmsStQtyLst stQtyLst = stockList.get(i);

            Double qtyFact = stQtyLst.getQtyFact();
            /**
             * ???????????????????????????
             */
            if (qtyFact <= 0) {
                continue;
            }
            /**
             * ????????????????????????
             */
            if (qtyFact < goodQty - totalQty) {
                totalQty += qtyFact;
                currentQty = qtyFact;
            } else {
                currentQty = goodQty - totalQty;
                totalQty = goodQty;
            }


            bmsPresOutDtl.setGoodsqty(currentQty);
            bmsPresOutDtl.setStorageid(stQtyLst.getStorageid());

            bmsPresOutDtl.setSendwmsflag(1);

            bmsPresOutDtl.setRecst(0);

            bmsPresOutDtl.setGoodsstatusid(stQtyLst.getGoodsstatusid());

            bmsPresOutDtl.setGoodsuseqty(currentQty);

            bmsPresOutDtl.setGoodsqty(currentQty);

            bmsPresOutDtl.setLotid(stQtyLst.getLotid());

            bmsPresOutDtl.setGoodsuseunit(goods.getGoodsunit());

            bmsPresOutDtl.setGoodsdtlid(stQtyLst.getGoodsdetailid());


            bmsPresOutDtl.setB2bOrderDtlId(Long.valueOf(dtl.getOrderDtlId()));

            if (isUseWms(stQtyLst.getStorageid(), entryId)) {
                bmsPresOutDtl.setSendwmsflag(1);
            } else {
                bmsPresOutDtl.setSendwmsflag(0);
            }

            bmsPresOutDtlMapper.insert(bmsPresOutDtl);


            stIoDtlTmp.setBatchid(stQtyLst.getBatchid());
            stIoDtlTmp.setDtlgoodsqty(currentQty);
            stIoDtlTmp.setGoodsdtlid(stQtyLst.getGoodsdetailid());
            stIoDtlTmp.setLotid(stQtyLst.getLotid());
            stIoDtlTmp.setPosid(stQtyLst.getPosid());
            stIoDtlTmp.setGoodsstatusid(stQtyLst.getGoodsstatusid());


            bmsStIoDtlTmpMapper.insert(stIoDtlTmp);


            BmsStIoDocTmp stIoDocTmp = new BmsStIoDocTmp();

            stIoDocTmp.setEntryid(entryId);
            stIoDocTmp.setGoodsid(goodsId);
            stIoDocTmp.setCompanyid(customId);
            stIoDocTmp.setCompanyname(customer.getCustomname());
            stIoDocTmp.setOutqty(currentQty);



            /**
             * ???????????????
             */
            stIoDocTmp.setComefrom(18);
            stIoDocTmp.setSourcetable(18);

            /**
             * ?????????????????????
             */
            stIoDocTmp.setPlacetable(0);






            /**
             * ????????????---????????????????????????????????????????????????   begin
             */
            stIoDocTmp.setCredate(LocalDateTime.now());
            stIoDocTmp.setOldqty(currentQty);
            stIoDocTmp.setGoodsunit(goods.getGoodsunit());
            stIoDocTmp.setInoutflag(0);


            /**
             * ????????????---????????????????????????????????????????????????   end
             */

            stIoDocTmp.setStorageid(stQtyLst.getStorageid());


            stIoDocTmp.setSourceid(bmsPresOutDtl.getPresoutdtlid());


            bmsStIoDocTmpMapper.insert(stIoDocTmp);

            stdDocIds.add(stIoDocTmp.getInoutid());

            stIoDtlTmp.setInoutid(stIoDocTmp.getInoutid());

            bmsStIoDtlTmpMapper.updateById(stIoDtlTmp);

        }

        orderResultDocVo.setStdDocIds(stdDocIds);

        if (totalQty < goodQty) {
            return OrderResultDocVo.undoneAppend(orderResultDocVo, orderNo, orderDtId, goodsId, goodQty - totalQty);
        }

        return OrderResultDocVo.validReturn(orderResultDocVo, orderNo, customId);
    }

    /**
     * ????????????????????????
     */
    private OrderInfoDocVo decomposeOrderDetails(OrderInfoDocVo orderInfoDocVo, Boolean isGift) {

        OrderInfoDocVo factOrder = new OrderInfoDocVo();

        BeanUtil.copyProperties(orderInfoDocVo, factOrder);
        List<OrderInfoDtlVo> orderInfoDtlList = new ArrayList<>();

        orderInfoDocVo.getOrderInfoDtlList().forEach(item -> {
            PubGoods pubGoods = goodsService.getById(item.getGoodsId());

            if (ObjectUtil.equal(pubGoods.getAccflag(), 5) && isGift.equals(true)) {

                orderInfoDtlList.add(item);

            }
            if (ObjectUtil.notEqual(pubGoods.getAccflag(), 5) && isGift.equals(false)) {

                orderInfoDtlList.add(item);
            }


        });

        if (ObjectUtil.isNotEmpty(orderInfoDtlList)) {

            factOrder.setOrderInfoDtlList(orderInfoDtlList);
            return factOrder;
        }
        return null;

    }


    private OrderResultDocVo genOrderSa(OrderInfoDocVo orderInfoDocVo) {
        Long orderId = orderInfoDocVo.getOrderId();
        String orderNo = orderInfoDocVo.getOrderNo();
        Long customId = orderInfoDocVo.getCustomId();
        Integer entryId = orderInfoDocVo.getEntryId();


        /**
         * ????????????????????????????????????id
         */
        if(ObjectUtil.equal(orderInfoDocVo.getOrderFrom(),4)){
            customId = customService.getByCustomStoreId(customId,orderInfoDocVo.getStoreId(),entryId);
        }


        CustomerVo customer = customService.getByCustomId(customId,entryId);

        BmsTrPosDef address = customService.getAddressByCustomId(customId, entryId);

        PubCustomToSaler saler = customService.getSalerByCustomId(customId, entryId);

        OrderResultDocVo orderResultDocVo = new OrderResultDocVo(orderNo, customId);
        /**
         * ??????????????????????????? ???????????????
         */
        List<OrderInfoDocVo> orderList = splitOrder(orderInfoDocVo);


        for (OrderInfoDocVo infoDocVo : orderList) {


            BmsSaDoc bmsSaDoc = new BmsSaDoc();

            if(customId.longValue() != orderInfoDocVo.getCustomId().longValue()){
                bmsSaDoc.setB2bSubCustomId(orderInfoDocVo.getCustomId());
            }

            bmsSaDoc.setApiOrderId(infoDocVo.getSrcOrderId());
            bmsSaDoc.setApiOrderNo(infoDocVo.getSrcOrderNo());
            bmsSaDoc.setApiOrderTime(infoDocVo.getSrcOrderTime());

            bmsSaDoc.setB2bStoreId(infoDocVo.getStoreId());

            bmsSaDoc.setB2bOrderNo(orderNo);

            bmsSaDoc.setB2bOrderId(orderId);

            bmsSaDoc.setB2bWriteBackFlag(1);

            bmsSaDoc.setB2bAmountTotal(orderInfoDocVo.getAmountTotal());
            bmsSaDoc.setB2bAmountPay(orderInfoDocVo.getAmountPay());
            bmsSaDoc.setB2bAmountDelivery(orderInfoDocVo.getAmountDelivery());
            bmsSaDoc.setB2bAmountDiscount(orderInfoDocVo.getAmountDiscount());
            bmsSaDoc.setB2bOrderType(orderInfoDocVo.getOrderType());
            bmsSaDoc.setB2bOrderFrom(orderInfoDocVo.getOrderFrom());
            bmsSaDoc.setB2bPayType(orderInfoDocVo.getPayType());


            bmsSaDoc.setB2bPayOrderNo(orderInfoDocVo.getPayOrderNo());
            bmsSaDoc.setB2bPayFlowNo(orderInfoDocVo.getPayFlowNo());

            bmsSaDoc.setSettletypeid(customer.getSettletypeid());


            bmsSaDoc.setMemo((ObjectUtil.isNotNull(infoDocVo.getRemark()) ? "???B2B??????,???" + infoDocVo.getRemark() : "") + "???B2B????????????" + infoDocVo.getOrderNo());

            bmsSaDoc.setZxMemo(infoDocVo.getRemark());


            //  ?????????????????????????????????????????????
            bmsSaDoc.setComefrom(1);

            bmsSaDoc.setUsestatus(2);
            bmsSaDoc.setSatypeid(1);
            bmsSaDoc.setExchange(1.0);

            bmsSaDoc.setEntryid(entryId);

            bmsSaDoc.setCustomid(customId);

            bmsSaDoc.setTotal(infoDocVo.getAmountTotal());
            bmsSaDoc.setRecmoney(infoDocVo.getAmountPay());
            bmsSaDoc.setCustomname(customer.getCustomname());

            bmsSaDoc.setInvtype(customer.getInvtype());
            bmsSaDoc.setSalesdeptid(saler.getSalerdeptid());

            //????????????
            bmsSaDoc.setCredate(orderInfoDocVo.getCreateDate());
            bmsSaDoc.setAssessdate(orderInfoDocVo.getCreateDate());
            bmsSaDoc.setZxBhFlag(11);

            bmsSaDoc.setDelivermethod(1);
            /**
             * ???????????????
             */
            bmsSaDoc.setApproveflag(0);


            bmsSaDoc.setSalerid(saler.getSalerid());

            bmsSaDoc.setInputmanid(saler.getSalerid());

            bmsSaDoc.setTargetposid(address.getTranposid());


            bmsSaDocMapper.insert(bmsSaDoc);
            Integer dtlLines = 0;
            for (OrderInfoDtlVo orderInfoDtlVo : infoDocVo.getOrderInfoDtlList()) {
                OrderResultDocVo result2 = genIOStockForSa(bmsSaDoc, orderInfoDtlVo, customId, entryId);

                if (ObjectUtil.isNotEmpty(result2.getStdDocIds())) {
                    dtlLines += result2.getStdDocIds().size();
                }
                orderResultDocVo = OrderResultDocVo.undoneAppend(orderResultDocVo, result2, orderNo, customId);
            }

            if (dtlLines == 0) {
                bmsSaDocMapper.deleteById(bmsSaDoc.getSalesid());
                orderResultDocVo = OrderResultDocVo.failErrorMessageAppend(orderResultDocVo, "????????????????????????????????????????????????????????????", orderNo, customId);
            } else {


                bmsSaDocMapper.updateSummary(bmsSaDoc.getSalesid(), entryId);
            }

        }


        return orderResultDocVo;
    }

    private OrderResultDocVo genIOStockForSa(BmsSaDoc bmsSaDoc, OrderInfoDtlVo orderInfoDtlVo, Long customId, Integer entryId) {
        CustomerVo customer = customService.getByCustomId(customId,entryId);


        List<Long> stdDocIds = new ArrayList<>();

        String orderNo = orderInfoDtlVo.getOrderNo();
        String orderDtId = orderInfoDtlVo.getOrderDtlId();

        //Double price = orderInfoDtlVo.getPriceDiscount();
        Double price = NumberUtil.round(orderInfoDtlVo.getAmountDiscount() / orderInfoDtlVo.getNum(), 4).doubleValue();

        OrderResultDocVo orderResultDocVo = new OrderResultDocVo(orderNo, customer.getCustomid());
        /**
         * ??????ID
         */
        Long docId = bmsSaDoc.getSalesid();

        Long goodsId = orderInfoDtlVo.getGoodsId();

        PubGoods goods = goodsService.getById(goodsId);


        double goodQty = orderInfoDtlVo.getNum();
        List<BmsStQtyLst> stockList = null;


        stockList = goodsService.selectStockBy(customer.getCustomid(), goodsId, orderInfoDtlVo.getLotNo(), 1, orderInfoDtlVo.getStorageId());

        double totalQty = 0.0;


        for (int i = 0; totalQty < goodQty && i < stockList.size(); i++) {
            double currentQty = 0;
            BmsStIoDtlTmp stIoDtlTmp = new BmsStIoDtlTmp();

            BmsSaDtl bmsSaleDtl = new BmsSaDtl();

            BeanUtil.copyProperties(bmsSaDoc, bmsSaleDtl);
            bmsSaleDtl.setApiOrderDtlId(orderInfoDtlVo.getSrcOrderDtlId());

            BmsStQtyLst stQtyLst = stockList.get(i);

            double qtyFact = stQtyLst.getQtyFact();
            /**
             * ???????????????????????????
             */
            if (qtyFact <= 0) {
                continue;
            }
            /**
             * ????????????????????????
             */
            if (qtyFact < goodQty - totalQty) {
                totalQty += qtyFact;
                currentQty = qtyFact;
            } else {
                currentQty = goodQty - totalQty;
                totalQty = goodQty;
            }

            bmsSaleDtl.setSalesid(docId);

            bmsSaleDtl.setGoodsid(goodsId);
            bmsSaleDtl.setGoodsdtlid(stQtyLst.getGoodsdetailid());
            bmsSaleDtl.setTaxrate(goods.getSalestaxrate());
            bmsSaleDtl.setUnitprice(price);

            bmsSaleDtl.setReqprintquflag(customer.getReqprintquflag());

            bmsSaleDtl.setGoodsqty(currentQty);
            bmsSaleDtl.setGoodsuseqty(currentQty);

            bmsSaleDtl.setDiscount(1.0);

            bmsSaleDtl.setPriceid(orderInfoDtlVo.getPriceId());

            bmsSaleDtl.setGoodsuseunit(goods.getGoodsunit());

            bmsSaleDtl.setBatchid(stQtyLst.getBatchid());
            bmsSaleDtl.setLotid(stQtyLst.getLotid());
            bmsSaleDtl.setPosid(stQtyLst.getPosid());
            bmsSaleDtl.setGoodsstatusid(stQtyLst.getGoodsstatusid());


            /**
             * ?????????????????????????????????
             */
            bmsSaleDtl.setCorrectflag(0);


            /**
             * ?????????????????????
             */

            Double totalLine = NumberUtil.round(price * currentQty, 4).doubleValue();
            bmsSaleDtl.setTotalLine(totalLine);


            bmsSaleDtl.setStorageid(stQtyLst.getStorageid());


            bmsSaleDtl.setTotalLine(totalLine);


            bmsSaleDtl.setB2bOrderDtlId(Long.valueOf(orderInfoDtlVo.getOrderDtlId()));
            bmsSaleDtl.setB2bNum(orderInfoDtlVo.getNum());
            bmsSaleDtl.setB2bPrice(orderInfoDtlVo.getPrice());
            bmsSaleDtl.setB2bPriceDiscount(price);

            if (isUseWms(stQtyLst.getStorageid(), entryId)) {
                bmsSaleDtl.setSendwmsflag(1);
            } else {
                bmsSaleDtl.setSendwmsflag(0);
            }

            bmsSaDtlMapper.insert(bmsSaleDtl);


            stIoDtlTmp.setBatchid(stQtyLst.getBatchid());
            stIoDtlTmp.setDtlgoodsqty(currentQty);
            stIoDtlTmp.setGoodsdtlid(stQtyLst.getGoodsdetailid());
            stIoDtlTmp.setLotid(stQtyLst.getLotid());
            stIoDtlTmp.setPosid(stQtyLst.getPosid());
            stIoDtlTmp.setGoodsstatusid(stQtyLst.getGoodsstatusid());


            //stIoDtlTmpList.add(stIoDtlTmp);


            BmsStIoDocTmp stIoDocTmp = new BmsStIoDocTmp();
            stIoDocTmp.setSourceid(bmsSaleDtl.getSalesdtlid());

            stIoDocTmp.setCredate(LocalDateTime.now());


            stIoDocTmp.setStorageid(stQtyLst.getStorageid());
            stIoDocTmp.setInoutflag(0);
            stIoDocTmp.setUsestatus(1);

            stIoDocTmp.setEntryid(1);


            stIoDocTmp.setGoodsid(goodsId);
            stIoDocTmp.setCompanyid(customId);
            stIoDocTmp.setCompanyname(customer.getCustomname());
            stIoDocTmp.setOutqty(currentQty);



            stIoDocTmp.setComefrom(3);
            stIoDocTmp.setSourcetable(2);


            stIoDocTmp.setStorageid(stQtyLst.getStorageid());


            stIoDocTmp.setGoodsunit(goods.getGoodsunit());

            bmsStIoDocTmpMapper.insert(stIoDocTmp);
            stIoDtlTmp.setInoutid(stIoDocTmp.getInoutid());
            bmsStIoDtlTmpMapper.insert(stIoDtlTmp);

            stdDocIds.add(stIoDocTmp.getInoutid());

        }

        orderResultDocVo.setStdDocIds(stdDocIds);

        if (totalQty < goodQty) {
            return OrderResultDocVo.undoneAppend(orderResultDocVo, orderNo, orderDtId, goodsId, goodQty - totalQty);
        }

        return OrderResultDocVo.validReturn(orderResultDocVo, orderNo, customId);


    }

    private OrderResultDocVo genOrderPs(OrderInfoDocVo orderInfoDocVo) {

        String orderNo = orderInfoDocVo.getOrderNo();
        Long orderId = orderInfoDocVo.getOrderId();
        Long customId = orderInfoDocVo.getCustomId();
        Integer entryId = orderInfoDocVo.getEntryId();


        CustomerVo customer = customService.getByCustomId(customId,entryId);

        BmsTrPosDef address = customService.getAddressByCustomId(customId, entryId);

        PubCustomToSaler saler = customService.getSalerByCustomId(customId, entryId);

        OrderResultDocVo orderResultDocVo = new OrderResultDocVo(orderNo, customId);
        /**
         * ??????????????????????????? ???????????????
         */
        List<OrderInfoDocVo> orderList = splitOrder(orderInfoDocVo);

        for (OrderInfoDocVo infoDocVo : orderList) {


            GpcsPlacesupply gpcsPlacesupply = new GpcsPlacesupply();

            gpcsPlacesupply.setApiOrderId(infoDocVo.getSrcOrderId());
            gpcsPlacesupply.setApiOrderNo(infoDocVo.getSrcOrderNo());
            gpcsPlacesupply.setApiOrderTime(infoDocVo.getSrcOrderTime());

            gpcsPlacesupply.setB2bStoreId(infoDocVo.getStoreId());

            gpcsPlacesupply.setB2bOrderNo(orderNo);
            gpcsPlacesupply.setB2bOrderId(orderId);
            gpcsPlacesupply.setB2bWriteBackFlag(1);
            gpcsPlacesupply.setB2bAmountTotal(orderInfoDocVo.getAmountTotal());
            gpcsPlacesupply.setB2bAmountPay(orderInfoDocVo.getAmountPay());
            gpcsPlacesupply.setB2bAmountDelivery(orderInfoDocVo.getAmountDelivery());

            gpcsPlacesupply.setB2bAmountDiscount(orderInfoDocVo.getAmountDiscount());


            gpcsPlacesupply.setB2bPayType(orderInfoDocVo.getPayType());
            gpcsPlacesupply.setB2bOrderType(orderInfoDocVo.getOrderType());


            gpcsPlacesupply.setB2bOrderFrom(orderInfoDocVo.getOrderFrom());
            gpcsPlacesupply.setB2bPayOrderNo(orderInfoDocVo.getPayOrderNo());
            gpcsPlacesupply.setB2bPayFlowNo(orderInfoDocVo.getPayFlowNo());
            gpcsPlacesupply.setPlacedate(orderInfoDocVo.getCreateDate());

            gpcsPlacesupply.setMemo((ObjectUtil.isNotNull(infoDocVo.getRemark()) ? "???B2B??????,???" + infoDocVo.getRemark() : "") + "???B2B????????????" + infoDocVo.getOrderNo());


            /**
             * 7??????B2B?????????
             */
            //gpcsPlacesupply.setJdOrderType(7);

            //gpcsPlacesupply.setMemo("???B2B????????????" + orderId);

            gpcsPlacesupply.setPlacepointid(customId);

            gpcsPlacesupply.setTotal(infoDocVo.getAmountTotal());

            gpcsPlacesupply.setZxBhFlag(11);

            gpcsPlacesupply.setPlacecenterid(1);

            //????????????  ????????????
            gpcsPlacesupply.setPlacemethod(7);
            gpcsPlacesupply.setUrgentflag(0);
            gpcsPlacesupply.setDocdiscount(1.0);

            gpcsPlacesupply.setUsestatus(1);


            gpcsPlacesupply.setPlacemanid(saler.getSalerid());
            //???????????? ??????
            gpcsPlacesupply.setDelivermethod(1);

            //????????????
            gpcsPlacesupplyMapper.insert(gpcsPlacesupply);
            Integer dtlLines = 0;
            for (OrderInfoDtlVo orderInfoDtlVo : infoDocVo.getOrderInfoDtlList()) {
                OrderResultDocVo result2 = genIOStockForPs(gpcsPlacesupply, orderInfoDtlVo, customId, entryId);

                if (ObjectUtil.isNotEmpty(result2.getStdDocIds())) {
                    dtlLines += result2.getStdDocIds().size();
                }
                orderResultDocVo = OrderResultDocVo.undoneAppend(orderResultDocVo, result2, orderNo, customId);
            }

            if (dtlLines == 0) {
                gpcsPlacesupplyMapper.deleteById(gpcsPlacesupply.getPlacesupplyid());
                orderResultDocVo = OrderResultDocVo.failErrorMessageAppend(orderResultDocVo, "????????????????????????????????????????????????????????????", orderNo, customId);
            } else {

                gpcsPlacesupplyMapper.updateSummary(gpcsPlacesupply.getPlacesupplyid());
                /*gpcsPlacesupply.setDtlLines(dtlLines);
                gpcsPlacesupplyMapper.updateById(gpcsPlacesupply);*/
            }

        }

        return orderResultDocVo;
    }

    private OrderResultDocVo genIOStockForPs(GpcsPlacesupply gpcsPlacesupply, OrderInfoDtlVo orderInfoDtlVo, Long customId, Integer entryId) {
        PubCustomer customer = customService.getById(customId);

        List<Long> stdDocIds = new ArrayList<>();

        String orderNo = orderInfoDtlVo.getOrderNo();
        String orderDtId = orderInfoDtlVo.getOrderDtlId();

        //Double price = orderInfoDtlVo.getPriceDiscount();

        Double price = NumberUtil.round(orderInfoDtlVo.getAmountDiscount() / orderInfoDtlVo.getNum(), 4).doubleValue();

        OrderResultDocVo orderResultDocVo = new OrderResultDocVo(orderNo, customer.getCustomid());
        /**
         * ??????ID
         */
        Long docId = gpcsPlacesupply.getPlacesupplyid();

        Long goodsId = orderInfoDtlVo.getGoodsId();

        PubGoods goods = goodsService.getById(goodsId);


        double goodQty = orderInfoDtlVo.getNum();
        List<BmsStQtyLst> stockList = null;

       /* if (ObjectUtil.equal(orderInfoDtlVo.getGoodsType(), 7)) {
            stockList = goodsService.selectStockBy(customer.getCustomid(), goodsId, orderInfoDtlVo.getLotNo(), false, orderInfoDtlVo.getStorageId());
        } else {*/
        stockList = goodsService.selectStockBy(customer.getCustomid(), goodsId, orderInfoDtlVo.getLotNo(), 1, orderInfoDtlVo.getStorageId());
        //}

        double totalQty = 0.0;

        /**
         * ?????????
         */
        Double placePrice = goodsService.selectPriceBy(goodsId,105,entryId);

        for (int i = 0; totalQty < goodQty && i < stockList.size(); i++) {
            double currentQty = 0;
            BmsStIoDtlTmp stIoDtlTmp = new BmsStIoDtlTmp();
            GpcsPlacesupplydtl placesupplyDtl = new GpcsPlacesupplydtl();

            BeanUtil.copyProperties(gpcsPlacesupply, placesupplyDtl);
            placesupplyDtl.setApiOrderDtlId(orderInfoDtlVo.getSrcOrderDtlId());

            BmsStQtyLst stQtyLst = stockList.get(i);
            //Integer qtyFact = stQtyLst.getQtyFact();

            double qtyFact = stQtyLst.getQtyFact();
            /**
             * ???????????????????????????
             */
            if (qtyFact <= 0) {
                continue;
            }
            /**
             * ????????????????????????
             */
            if (qtyFact < goodQty - totalQty) {
                totalQty += qtyFact;
                currentQty = qtyFact;
            } else {
                currentQty = goodQty - totalQty;
                totalQty = goodQty;
            }

            stIoDtlTmp.setBatchid(stQtyLst.getBatchid());
            stIoDtlTmp.setDtlgoodsqty(currentQty);
            stIoDtlTmp.setGoodsdtlid(stQtyLst.getGoodsdetailid());
            stIoDtlTmp.setLotid(stQtyLst.getLotid());
            stIoDtlTmp.setPosid(stQtyLst.getPosid());
            stIoDtlTmp.setGoodsstatusid(stQtyLst.getGoodsstatusid());

            BmsStIoDocTmp stIoDocTmp = new BmsStIoDocTmp();
            stIoDocTmp.setCredate(LocalDateTime.now());
            stIoDocTmp.setInoutflag(0);
            stIoDocTmp.setUsestatus(1);
            stIoDocTmp.setGoodsid(goodsId);
            stIoDocTmp.setCompanyid(customId);
            stIoDocTmp.setCompanyname(customer.getCustomname());
            stIoDocTmp.setOutqty(currentQty);
            /**
             * ???????????????
             */
            stIoDocTmp.setComefrom(16);
            stIoDocTmp.setSourcetable(10);

            stIoDocTmp.setPlacetable(1);

            stIoDocTmp.setStorageid(stQtyLst.getStorageid());


            stIoDocTmp.setGoodsunit(goods.getGoodsunit());

            placesupplyDtl.setGoodsqty(currentQty);


            placesupplyDtl.setPlacesupplyid(docId);


            Double totalLine = NumberUtil.round(price * currentQty, 4).doubleValue();

            placesupplyDtl.setTotalLine(totalLine);
            placesupplyDtl.setGoodsqty(currentQty);


            placesupplyDtl.setGoodsid(goodsId);
            placesupplyDtl.setGoodsdtlid(stQtyLst.getGoodsdetailid());
            //placesupplyDtl.setTaxrate(goods.getSalestaxrate());

            placesupplyDtl.setPlacepriceid(orderInfoDtlVo.getPriceId());


            placesupplyDtl.setPlaceprice(placePrice);

            placesupplyDtl.setGoodsunit(goods.getGoodsunit());

            placesupplyDtl.setDiscount(1.0);


            placesupplyDtl.setB2bOrderDtlId(Long.valueOf(orderInfoDtlVo.getOrderDtlId()));
            placesupplyDtl.setB2bNum(orderInfoDtlVo.getNum());
            placesupplyDtl.setB2bPrice(orderInfoDtlVo.getPrice());
            placesupplyDtl.setB2bPriceDiscount(orderInfoDtlVo.getPriceDiscount());

            placesupplyDtl.setZxSplitType(goods.getZxSplitType());

          /*  if (isUseWms(stQtyLst.getStorageid(), entryId)) {
                placesupplyDtl.setSendwmsflag(1);
            } else {
                placesupplyDtl.setSendwmsflag(0);
            }
*/

            gpcsPlacesupplydtlMapper.insert(placesupplyDtl);

            /**
             * ??????St????????????
             */

            GpcsPlacesupplydtlSt placesupplyDtlSt = new GpcsPlacesupplydtlSt();
            if (isUseWms(stQtyLst.getStorageid(), entryId)) {
                placesupplyDtlSt.setSendwmsflag(1);
            } else {
                placesupplyDtlSt.setSendwmsflag(0);
            }


            /**
             * ?????????????????????
             */
            placesupplyDtlSt.setTotalLine(totalLine);
            placesupplyDtlSt.setGoodsqty(currentQty);


            placesupplyDtlSt.setStorageid(stQtyLst.getStorageid());

            placesupplyDtlSt.setPlacesupplydtlid(placesupplyDtl.getPlacesupplydtlid());

            placesupplyDtlSt.setPlacepriceid(orderInfoDtlVo.getPriceId());
            placesupplyDtlSt.setPlaceprice(price);
            placesupplyDtlSt.setUnitprice(price);


            gpcsPlacesupplydtlStMapper.insert(placesupplyDtlSt);

            stIoDocTmp.setSourceid(placesupplyDtlSt.getPlacesupplydtlstid());
            stIoDocTmp.setPlacedtlid(placesupplyDtlSt.getPlacesupplydtlstid());

            stIoDocTmp.setPlacepointid(customId);


            stIoDocTmp.setEntryid(entryId);


            /**
             * ??????St??????
             */
            GpcsPlacesupplydtlStdtl gpcsPlacesupplyDtlStDtl = new GpcsPlacesupplydtlStdtl();

            gpcsPlacesupplyDtlStDtl.setBatchid(stQtyLst.getBatchid());
            gpcsPlacesupplyDtlStDtl.setLotid(stQtyLst.getLotid());
            gpcsPlacesupplyDtlStDtl.setPosid(stQtyLst.getPosid());
            gpcsPlacesupplyDtlStDtl.setGoodsstatusid(stQtyLst.getGoodsstatusid());

            gpcsPlacesupplyDtlStDtl.setPlacesupplydtlstid(placesupplyDtlSt.getPlacesupplydtlstid());


            gpcsPlacesupplydtlStdtlMapper.insert(gpcsPlacesupplyDtlStDtl);


            bmsStIoDocTmpMapper.insert(stIoDocTmp);
            stIoDtlTmp.setInoutid(stIoDocTmp.getInoutid());
            bmsStIoDtlTmpMapper.insert(stIoDtlTmp);

            stdDocIds.add(stIoDocTmp.getInoutid());

        }

        orderResultDocVo.setStdDocIds(stdDocIds);

        if (totalQty < goodQty) {
            return OrderResultDocVo.undoneAppend(orderResultDocVo, orderNo, orderDtId, goodsId, goodQty - totalQty);
        }

        return OrderResultDocVo.validReturn(orderResultDocVo, orderNo, customId);
    }

    private List<OrderInfoDocVo> splitOrder(OrderInfoDocVo orderInfoDocVo) {
        Map<String, List<OrderInfoDtlVo>> map = new HashMap<>();
        List<OrderInfoDocVo> docList = new ArrayList<>();
        List<OrderInfoDtlVo> detailList = orderInfoDocVo.getOrderInfoDtlList();
        for (int i = 0; i < detailList.size(); i++) {
            OrderInfoDtlVo dtl = detailList.get(i);
            Long goodsId = dtl.getGoodsId();
            PubGoods goods = goodsService.getById(goodsId);

            String splitFlag = goods.getZxSplitType() + "-" + goods.getSalestaxrate();

            List<OrderInfoDtlVo> list = map.get(splitFlag);
            if (ObjectUtil.isNull(list)) {
                list = new ArrayList<>();
                map.put(splitFlag, list);
            }
            list.add(dtl);
        }
        Set<String> keySet = map.keySet();
        for (String key : keySet) {

            OrderInfoDocVo oneDoc = new OrderInfoDocVo();
            BeanUtil.copyProperties(orderInfoDocVo, oneDoc);
            List<OrderInfoDtlVo> oneDtlList = map.get(key);

            /**
             * ????????????
             */
            oneDoc.setOrderInfoDtlList(oneDtlList);

            docList.add(oneDoc);
        }
        return docList;
    }


    /**
     * ????????????
     *
     * @param platformList
     */
    @Override
    public void confirmGiftOrder(String orderNo, Integer entryId, Integer srcStatus, Integer targetStatus, List<Integer> platformList) {


        QueryWrapper<BmsPresOutDoc> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BmsPresOutDoc::getB2bOrderNo, orderNo);
        queryWrapper.lambda().eq(BmsPresOutDoc::getUsestatus, srcStatus);
        queryWrapper.lambda().in(BmsPresOutDoc::getZxBhFlag, platformList);
        queryWrapper.lambda().notIn(BmsPresOutDoc::getCustomid,2086);
        List<BmsPresOutDoc> bmsPresOutDocs = bmsPresOutDocMapper.selectList(queryWrapper);


        if (ObjectUtil.isNotEmpty(bmsPresOutDocs)) {


            bmsPresOutDocs.forEach(item -> {

                /**
                 * ????????????????????????????????????
                 */
                /*????????????*/
                Long presoutid = item.getPresoutid();
                Long inputmanid = item.getInputmanid();
                String inputname = b2bGiftDocComfirmService.getInputname(inputmanid);
                b2bGiftDocComfirmService.updateUsestatus(inputmanid, presoutid);
                IncaAddressVo incaAddressdocVo = b2bGiftDocComfirmService.queryAddress(presoutid);
                BmsTrDoc bmsTrDoc = new BmsTrDoc();
                bmsTrDoc.setSourceid(presoutid);
                bmsTrDoc.setTargetposid(incaAddressdocVo.getTranposid());
                bmsTrDoc.setSourcetable(18);
                bmsTrDoc.setComefrom(18);
                bmsTrDoc.setCredate(LocalDateTime.now());
                bmsTrDoc.setInputmanid(inputmanid);
                bmsTrDoc.setTranslineid((long)incaAddressdocVo.getTranslineid());
                bmsTrDoc.setEntryid(incaAddressdocVo.getEntryid());
                bmsTrDocMapper.insert(bmsTrDoc);
                String tocompanyid = b2bGiftDocComfirmService.queryCustomid(presoutid);
                Long Trid = bmsTrDoc.getTrid();
                b2bGiftDocComfirmService.updateCompanyid(tocompanyid, Trid);

                Integer phystoreid = b2bGiftDocComfirmService.getStorerid(presoutid);
                BmsTrDtl bmsTrDtl = new BmsTrDtl();
                bmsTrDtl.setTrid(Trid);
                bmsTrDtl.setStorerid(phystoreid);
                bmsTrDtl.setPreparestatus(1);
                bmsTrDtl.setAllowmanid(item.getInputmanid());
                bmsTrDtl.setSignflowflag(1);
                bmsTrDtl.setSignformman(item.getInputmanid());
                bmsTrDtl.setSignformmanname(inputname);
                bmsTrDtlMapper.insert(bmsTrDtl);
                Long trdtlid = bmsTrDtl.getTrdtlid();
                b2bGiftDocComfirmService.updateBmstmp(trdtlid, presoutid, phystoreid);

                /**
                 * ?????????????????? ????????????????????????
                 */
                if (isUseWms(phystoreid, entryId)) {

                } else {
                    QueryWrapper<BmsPresOutDtl> queryWrapper1 = new QueryWrapper<>();
                    queryWrapper1.lambda().eq(BmsPresOutDtl::getPresoutid, presoutid);
                    List<BmsPresOutDtl> BmsPresOutDtls = bmsPresOutDtlMapper.selectList(queryWrapper1);
                    if (ObjectUtil.isNotEmpty(BmsPresOutDtls)) {
                        BmsPresOutDtls.forEach(BmsPresOutDtl -> {
                            Double goodsQty = BmsPresOutDtl.getGoodsqty();
                            Long goodsId = BmsPresOutDtl.getGoodsid();
                            Long presoutdtlId = BmsPresOutDtl.getPresoutdtlid();
                            Long goodsdtlId = BmsPresOutDtl.getGoodsdtlid();
                            Long usemm = b2bGiftDocComfirmMapper.getUsemm(entryId);
                            b2bGiftDocComfirmMapper.updateQty(goodsQty, usemm, phystoreid, goodsId);
                            BmsStIoDtl bmsStIoDtl = b2bGiftDocComfirmMapper.selectListBy(presoutdtlId, goodsdtlId);
                            if (!ObjectUtil.isNotEmpty(bmsStIoDtl)) {
                                return;
                            }
                            Long iodtlId = bmsStIoDtl.getIodtlid();
                            Long batchid = bmsStIoDtl.getBatchid();
                            Long lotid = bmsStIoDtl.getLotid();
                            Long posid = bmsStIoDtl.getPosid();
                            Long inoutId = bmsStIoDtl.getInoutid();
                            b2bGiftDocComfirmMapper.UpdateCheckManId(inputmanid, iodtlId);

                            /*??????????????????????????????????????????*/
                            DataGiftVo dataGiftVo = bmsStQtyLstMapper.selectListBy(phystoreid, goodsId, batchid, lotid, posid, goodsdtlId);
                            if (ObjectUtil.isNotEmpty(dataGiftVo)) {
                                /*????????????*/
                                try {
                                    String rowId = dataGiftVo.getRowid();
                                    Double oldgoodsqty = dataGiftVo.getGoodsqty();
                                    Double breakqty = dataGiftVo.getBreakqty();
                                    Double qty = oldgoodsqty - goodsQty;
                                    if (qty > 0 && qty.compareTo(breakqty) >= 0) {
                                        bmsStQtyLstMapper.updateQty(qty, rowId);
                                    } else if (qty == 0 || qty.compareTo(breakqty) < 0) {
                                        bmsStQtyLstMapper.deleteQty(rowId);
                                    }

                                } catch (Exception e) {
                                    log.error("??????????????????", e.getMessage());
                                    AssertExt.fail("????????????????????????????????????ID???" + goodsId + " ??????ID???" + lotid + " ??????????????????????????????,??????????????????");

                                }

                                bmsPresOutDtlMapper.updateBy(presoutdtlId);
                                /*?????????????????????*/
                                BmsStIoDoc bmsStIoDoc = bmsStIoDocMapper.save(inoutId);
                                BmsStIoDtl bSDtl = bmsStIoDtlMapper.save(inoutId);
                                bmsStIoDocMapper.updateUsestatus(inputmanid, inoutId);
                                /*?????????????????????*/
                                bmsStIoDtlTmpMapper.deleteById(inoutId);
                                bmsStIoDocTmpMapper.deleteById(inoutId);


                                bmsTrDtlMapper.updateBy(trdtlid);

                            }


                        });


                    }
                }


            });
        }


    }

    @Override
    public void confirmSaOrder(String orderNo, Integer entryId, Integer srcStatus, Integer targetStatus, List<Integer> platformList) {

        QueryWrapper<BmsSaDoc> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BmsSaDoc::getB2bOrderNo, orderNo);
        queryWrapper.lambda().eq(BmsSaDoc::getUsestatus, srcStatus);
        queryWrapper.lambda().in(BmsSaDoc::getZxBhFlag, platformList);
        List<BmsSaDoc> bmsSaDocs = bmsSaDocMapper.selectList(queryWrapper);


        if (ObjectUtil.isNotEmpty(bmsSaDocs)) {
            bmsSaDocs.forEach(item -> {
                /**
                 * ????????????????????????????????????
                 */
                Long salesId = item.getSalesid();
                long customId = item.getCustomid();
                Long salerId = item.getSalerid();
                Long salesdeptId = item.getSalesdeptid();
                Double total = item.getTotal();
                List<BmsSaDtl> bmsSaDtls = bmsSaDtlMapper.selectListBy(salesId,1);

                bmsSaDocMapper.updateUsetatus(salerId, salesId);
                BmsSaConDoc bmsSaConDoc = genBmsSaConDoc(item);
                Long conId = bmsSaConDoc.getConid();
                bmsSaConDocMapper.updateBy(conId);
                BmsTrDoc bmsTrDoc = genBmsTrDoc(salesId);
                BmsTrDtl bmsTrDtl = genBmsTrDtl(bmsTrDoc);
                if (ObjectUtil.isNotEmpty(bmsSaDtls)) {

                    bmsSaDtls.forEach(bmsSaDtl -> {
                        /**
                         *
                         * peiqy ??????
                         */

                        Long trId = bmsTrDoc.getTrid();
                        Long trdtlId = bmsTrDtl.getTrdtlid();
                        bmsStIoDocTmpMapper.updateBy(trdtlId, salesId);
                        genBmsTrBackInsert(salesId, trdtlId);
                        bmsTrDtlMapper.updateByTrid(salerId, bmsTrDtl.getTrid());



                        BmsTrPickDoc bmsTrPickDoc = new BmsTrPickDoc();
                        bmsTrPickDoc.setInputmanid(salerId);
                        bmsTrPickDoc.setTrid(trId);
                        List<IncaIoDtlVo> incaIoDtlVos = bmsStIoDtlTmpMapper.selectBy(trdtlId);
                        incaIoDtlVos.forEach(incaIoDtlVo -> {
                            bmsTrPickDoc.setTranslineid(incaIoDtlVo.getTranslineid() + "");
                            bmsTrPickDoc.setZxCompanyid(customId);
                            bmsTrPickDoc.setZxCompanyname(item.getCustomname());
                            bmsTrPickDoc.setUsestatus(1);
                            bmsTrPickDoc.setPickareasid(incaIoDtlVo.getPickareasid());
                            bmsTrPickDoc.setCredate(LocalDateTime.now());
                            bmsTrPickDocMapper.insert(bmsTrPickDoc);
                            Long iodtlid = incaIoDtlVo.getIodtlid();
                            Long pickdocid = bmsTrPickDoc.getPickdocid();
                            bmsStIoDtlTmpMapper.updateBy(pickdocid, iodtlid);
                            bmsTrPickDocMapper.updateBy(pickdocid);
                        });

                        BmsSaDtlTmp bmsSaDtlTmp = new BmsSaDtlTmp();
                        bmsSaDtlTmp.setSalesdtlid(bmsSaDtl.getSalesdtlid());
                        bmsSaDtlTmpMaper.insert(bmsSaDtlTmp);


                        Long goodsId = bmsSaDtl.getGoodsid();
                        IncaGoodsCustomVo incaGoodsCustomVo = bmsCreditBillDtlMapper.queryByCust(customId, goodsId);
                        if (incaGoodsCustomVo != null && incaGoodsCustomVo.getLastsaprice() > 0) {
                            Long lastsadtlid = incaGoodsCustomVo.getLastsadtlid();
                            Double lastsaprice = incaGoodsCustomVo.getLastsaprice();
                            bmsCreditBillDtlMapper.updateByPrice(lastsadtlid, lastsaprice, customId, goodsId);
                        }
                    });

                }

                String serialno = bmsTrPickDocMapper.getSerialno();
                String waveno = DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN) + "-" + serialno;
                bmsTrDtlMapper.updateWaveno(waveno, bmsTrDtl.getTrdtlid());
                bmsTrPickDocMapper.updateWaveno(waveno, bmsTrDtl.getTrdtlid());
                genBmsCertDtlTmp(salesId);
                if (!ObjectUtils.isNull(customId) && !ObjectUtils.isNull(salerId) && !ObjectUtils.isNull(salesdeptId)) {
                    BmsCreditBillVo bmsCreditBillVo = bmsCreditBillDtlMapper.queryByid(customId, salerId, salesdeptId);
                    if (!ObjectUtils.isNull(bmsCreditBillVo)) {
                        Long billid = bmsCreditBillVo.getBillid();
                        Double owemoney = bmsCreditBillVo.getOwemoney();
                        owemoney += total;
                        bmsCreditBillDtlMapper.updateByBillid(billid, owemoney);
                        BmsCreditBillDtl bmsCreditBillDtl = genBmsCreditBillDtl(billid,total,salesId);


                    }else{
                        BmsCreditBillDoc   bmsCreditBillDoc =new   BmsCreditBillDoc();
                        bmsCreditBillDoc.setCustomid(customId);
                        bmsCreditBillDoc.setSalerid(salerId);
                        bmsCreditBillDoc.setSalerdeptid(salesdeptId);
                        bmsCreditBillDoc.setEntryid((long)1);
                        bmsCreditBillDoc.setOwemoney(total);
                        bmsCreditBillDoc.setOwedate(LocalDateTime.now());
                        bmsCreditBillDocMapper.insert(bmsCreditBillDoc);
                        Long billid=bmsCreditBillDoc.getBillid();
                        BmsCreditBillDtl bmsCreditBillDtl = genBmsCreditBillDtl(billid,total,salesId);
                    }

                }

                
            });
        }


    }

    /**
     * ??????????????????
     * @param billid
     * @param total
     * @param salesId
     * @return
     */
    private BmsCreditBillDtl genBmsCreditBillDtl(Long billid, Double total, Long salesId) {
        BmsCreditBillDtl bmsCreditBillDtl = new BmsCreditBillDtl();
        bmsCreditBillDtl.setBillid(billid);
        bmsCreditBillDtl.setBusimoney(total);
        bmsCreditBillDtl.setBusitype(1);
        bmsCreditBillDtl.setSourceid(salesId);
        bmsCreditBillDtl.setSourcetable("BMS_SA_DOC");
        bmsCreditBillDtl.setBusicredate(LocalDateTime.now());
        bmsCreditBillDtl.setBusiconfirmdate(LocalDateTime.now());
        bmsCreditBillDtlMapper.insert(bmsCreditBillDtl);
        return bmsCreditBillDtl;
    }

    /**
     * ?????????????????????
     *
     * @param salesId
     * @return
     */
    private BmsCertDtlTmp genBmsCertDtlTmp(Long salesId) {
        Long traId = bmsCertDtlTmpMapper.getTraId();
        BmsCertDtlTmp bmsCertDtlTmp = new BmsCertDtlTmp();
        bmsCertDtlTmp.setSourceid(salesId);
        bmsCertDtlTmp.setSourcetable("BMS_SA_DOC");
        bmsCertDtlTmp.setAcctype(2);
        bmsCertDtlTmp.setCerttype(0);
        bmsCertDtlTmp.setTransactionid(traId);
        bmsCertDtlTmpMapper.insert(bmsCertDtlTmp);
        return bmsCertDtlTmp;
    }

    /**
     * ?????????????????????????????????
     *
     * @param salesId
     * @param
     * @return
     */

    private BmsTrBackInsert genBmsTrBackInsert(Long salesId, Long trdtlId) {
        BmsTrBackInsert bmsTrBackInsert = new BmsTrBackInsert();
        bmsTrBackInsert.setComefrom(3);
        bmsTrBackInsert.setType(1);
        bmsTrBackInsert.setSourceid(salesId);
        bmsTrBackInsert.setTrdtlid(trdtlId);
        bmsTrBackInsert.setCredate(new Date());
        incaTrBackInsertMapper.insert(bmsTrBackInsert);
        return bmsTrBackInsert;
    }

    /**
     * ?????????????????????
     *
     * @param
     * @return
     */
    private BmsTrDtl genBmsTrDtl(BmsTrDoc bmsTrDoc) {
        BmsTrDtl bmsTrDtl = new BmsTrDtl();
        bmsTrDtl.setTrid(bmsTrDoc.getTrid());
        bmsTrDtl.setPreparestatus(0);
        bmsTrDtl.setSignflowflag(1);
        bmsTrDtl.setStorerid(7368);
        bmsTrDtl.setSignformman(bmsTrDoc.getSalerid());

        String employeename = pubEmployeeMapper.getEmployeename(bmsTrDoc.getSalerid());
        bmsTrDtl.setSignformmanname(employeename);
        bmsTrDtl.setSignflowtime(LocalDateTime.now());
        bmsTrDtlMapper.insert(bmsTrDtl);
        return bmsTrDtl;
    }

    /**
     * ??????????????????
     *
     * @param salesId
     * @return
     */
    private BmsTrDoc genBmsTrDoc(Long salesId) {
        BmsTrDoc bmsTrDoc = bmsTrDocMapper.selectListBy(salesId);
        bmsTrDoc.setComefrom(3);
        bmsTrDoc.setSourcetable(2);
        bmsTrDoc.setSourceid(salesId);
        bmsTrDoc.setTranpriority(1);
        bmsTrDoc.setCredate(LocalDateTime.now());
        bmsTrDocMapper.insert(bmsTrDoc);
        return bmsTrDoc;
    }

    /**
     * ??????????????????????????????????????????
     *
     * @param bmsSaConDtl
     * @return
     */
    private BmsSaContodoc genBmsSaConToDoc(BmsSaConDtl bmsSaConDtl) {
        BmsSaContodoc bmsSaConToDoc = new BmsSaContodoc();
        bmsSaConToDoc.setCondtlid(bmsSaConDtl.getCondtlid());
        bmsSaConToDoc.setGoodsqty(bmsSaConDtl.getGoodsqty());
        bmsSaConToDoc.setSalesdtlid(bmsSaConDtl.getSalesdtlid());
        bmsSaConToDoc.setTotalLine(bmsSaConDtl.getTotalLine());
        bmsSaConToDocMapper.insert(bmsSaConToDoc);

        return bmsSaConToDoc;
    }

    /**
     * ????????????????????????
     *
     * @param item
     * @return
     */
    private BmsSaConDoc genBmsSaConDoc(BmsSaDoc item) {
        BmsSaConDoc bmsSaConDoc = new BmsSaConDoc();
        bmsSaConDoc.setContype(2);
        bmsSaConDoc.setComefrom(2);
        bmsSaConDoc.setCustomname(item.getCustomname());
        bmsSaConDoc.setSigndate(LocalDateTime.now());
        bmsSaConDoc.setAssessdate(item.getCredate());
        bmsSaConDoc.setInvtype(item.getInvtype());
        bmsSaConDoc.setDelivermethod(item.getDelivermethod());
        bmsSaConDoc.setUsestatus(1);
        bmsSaConDoc.setConfirmdate(LocalDateTime.now());
        bmsSaConDoc.setDtlLines(item.getDtlLines());
        bmsSaConDoc.setTotal(item.getTotal());
        bmsSaConDoc.setApproveflag(item.getApproveflag());
        bmsSaConDoc.setCustomid(item.getCustomid());
        bmsSaConDoc.setSalerid(item.getSalerid());
        bmsSaConDoc.setSalesdeptid(item.getSalesdeptid());
        bmsSaConDoc.setSignmanid(item.getSalerid());
        bmsSaConDoc.setTargetposid(item.getTargetposid());
        bmsSaConDoc.setInputmanid(item.getSalerid());
        bmsSaConDoc.setEntryid(item.getEntryid());
        bmsSaConDoc.setConfirmanid(item.getSalerid());
        bmsSaConDocMapper.insert(bmsSaConDoc);
        return bmsSaConDoc;
    }

    @Override
    public void confirmPsOrder(String orderNo, Integer entryId, Integer srcStatus, Integer targetStatus, List<Integer> platformList) {

        log.info("???????????????");
        QueryWrapper<GpcsPlacesupply> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(GpcsPlacesupply::getB2bOrderNo, orderNo);
        queryWrapper.lambda().eq(GpcsPlacesupply::getUsestatus, srcStatus);
        queryWrapper.lambda().in(GpcsPlacesupply::getZxBhFlag, platformList);

        List<GpcsPlacesupply> gpcsPlacesupplies = gpcsPlacesupplyMapper.selectList(queryWrapper);

        if (ObjectUtil.isNotEmpty(gpcsPlacesupplies)) {
            gpcsPlacesupplies.forEach(item -> {
                /**
                 * ????????????????????????????????????
                 */
                Long placesupplyId = item.getPlacesupplyid();
                Long placepointId = item.getPlacepointid();
                Long placemanId = item.getPlacemanid();
                gpcsPlacesupplyMapper.updateUsestatus(placesupplyId);
                /*???????????????????????????*/
                BmsPresDataVo bmsPresDataVo = gpcsPlacesupplyMapper.selectByData(placesupplyId);
                Long targetposId = gpcsPlacesupplyMapper.getTargetposId(placepointId);
                BmsTrDoc bmsTrDoc = new BmsTrDoc();
                bmsTrDoc.setCredate(LocalDateTime.now());
                bmsTrDoc.setSourceid(placesupplyId);
                bmsTrDoc.setComefrom(18);
                bmsTrDoc.setSourcetable(10);
                bmsTrDoc.setInputmanid(item.getPlacemanid());
                bmsTrDoc.setTranslineid(bmsPresDataVo.getTranslineid());
                bmsTrDoc.setColdstorageflag(bmsPresDataVo.getColdStorageFlag());
                bmsTrDoc.setAgentid(bmsPresDataVo.getAgentId());
                bmsTrDoc.setAddress(bmsPresDataVo.getAddress());
                bmsTrDoc.setSalerid(item.getPlacemanid());
                bmsTrDoc.setSalesdeptid(7628L);
                bmsTrDoc.setEntryid(bmsPresDataVo.getEntryId());
                bmsTrDoc.setTargetposid(targetposId);
                bmsTrDocMapper.insert(bmsTrDoc);
                Long trId = bmsTrDoc.getTrid();
                bmsTrDocMapper.updateFlag(placepointId, trId);
                BmsTrDtl bmsTrDtl = new BmsTrDtl();
                bmsTrDtl.setTrid(trId);
                bmsTrDtl.setStorerid(7368);
                bmsTrDtl.setPreparestatus(1);
                bmsTrDtl.setAllowmanid(item.getPlacemanid());
                bmsTrDtl.setAllowtime(LocalDateTime.now());
                bmsTrDtl.setSignflowflag(1);
                bmsTrDtl.setSignflowtime(LocalDateTime.now());
                bmsTrDtlMapper.insert(bmsTrDtl);
                Long trdtlId = bmsTrDtl.getTrdtlid();
                bmsStIoDocTmpMapper.updateByTrId(trdtlId, placesupplyId);


            });

        }
    }


    @Override
    public Boolean isUseWms(Integer storageId, Integer entryId) {

        Integer useWms = bmsStDefMapper.getUseWms(storageId, entryId);

        if (ObjectUtil.equal(1, useWms)) {
            return true;
        } else {
            return false;
        }

    }


    /**
     * ??????????????????  1,??????????????????     2   ?????????  ???????????????    3   ????????????
     *
     * @param orderIdList
     * @return
     */
    @Override
    public List<OrderStatusVo> selectOrderStatusList(List<Long> orderIdList) {

        AssertExt.notEmpty(orderIdList, "???????????????????????????");


        List<Long> existsInList = bmsSaDocMapper.selectExistsInList(11, orderIdList);
        List<Long> notCallbackInList = bmsSaDocMapper.selectNotCallbackInList(11, orderIdList);

        List<OrderStatusVo> orderStatusVoList = new ArrayList<>();

        if (ObjectUtil.isNotEmpty(existsInList)) {
            orderIdList.removeAll(existsInList);
            for (Long aLong : orderIdList) {
                OrderStatusVo orderStatusVo = new OrderStatusVo();
                orderStatusVo.setB2bOrderId(aLong);
                orderStatusVo.setStatus(1);
                orderStatusVoList.add(orderStatusVo);
            }

        } else {
            for (Long aLong : orderIdList) {
                OrderStatusVo orderStatusVo = new OrderStatusVo();
                orderStatusVo.setB2bOrderId(aLong);
                orderStatusVo.setStatus(1);
                orderStatusVoList.add(orderStatusVo);
            }

            return orderStatusVoList;
        }


        if (ObjectUtil.isAllNotEmpty(notCallbackInList)) {
            for (Long aLong : notCallbackInList) {
                OrderStatusVo orderStatusVo = new OrderStatusVo();
                orderStatusVo.setB2bOrderId(aLong);
                orderStatusVo.setStatus(3);
                orderStatusVoList.add(orderStatusVo);
            }
        }

        return orderStatusVoList;
    }


    /**
     * ??????????????????
     *
     * @param orderId
     * @return
     */
    @Override
    public Integer getOrderStatus(Long orderId) {
        QueryWrapper<BmsSaDtl> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BmsSaDtl::getB2bOrderId, orderId);
        List<BmsSaDtl> bmsSaDtls = bmsSaDtlMapper.selectList(queryWrapper);

        if (ObjectUtil.isEmpty(bmsSaDtls)) {
            return 1;
        }

        for (BmsSaDtl bmsSaDtl : bmsSaDtls) {
            if (ObjectUtil.isEmpty(bmsSaDtl.getWmsbackdate())) {

                return 2;
            }
        }

        return 3;

    }

    @Override
    public void writeBackPayOrderAndFlowNo(Long customId,Integer entryId,List<Long> orderIds, String payFlowNo, String payOrderNo) {

        Boolean psCustom = customService.isPsCustom(customId, entryId);
        bmsSaDocMapper.writeBackPayOrderAndFlowNo(psCustom,orderIds,payFlowNo,payOrderNo);

    }
}