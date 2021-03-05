package com.zsyc.zt.order.api.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.zsyc.zt.b2b.entity.Cart;
import com.zsyc.zt.b2b.entity.OrderInfo;
import com.zsyc.zt.b2b.service.OrderInfoService;
import com.zsyc.zt.b2b.vo.OrderInfoVo;
import com.zsyc.zt.order.api.service.CustomService;
import com.zsyc.zt.order.api.service.GoodsService;
import com.zsyc.zt.order.api.service.OrderService;
import com.zsyc.zt.order.entity.ApiOrderList;
import com.zsyc.zt.order.entity.PubGoods;
import com.zsyc.zt.order.entity.*;
import com.zsyc.zt.order.inca.mapper.*;
import com.zsyc.zt.order.service.IApiOrderDocService;
import com.zsyc.zt.order.service.IApiOrderDtlService;
import com.zsyc.zt.order.util.AssertExt;
import com.zsyc.zt.order.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Resource
    GpcsPlacepointMapper gpcsPlacepointMapper;

    @Reference
    com.zsyc.zt.b2b.service.GoodsService b2bGoodsService;

    @Reference
    OrderInfoService b2bOrderInfoService;

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
    BmsTrDocMapper incaBmstrdocMapper;
    @Resource
    BmsTrDtlMapper incaBmstrdtlMapper;
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
    BmsTrBackInsertMapper incaTrBackInsertMapper;
    @Resource
    BmsCertDtlTmpMapper bmsCertDtlTmpMapper;

    @Resource
    BmsCreditBillDtlMapper bmsCreditBillDtlMapper;

    @Resource
    BmsStDefMapper bmsStDefMapper;

    @Resource
    ApiOrderListMapper apiOrderListMapper;
    @Resource
    BmsPresOutDocMapper b2bGiftDocComfirmMapper;

    @Resource
    BmsStQtyLstMapper bmsStQtyLstMapper;

    @Resource
    BmsStIoDocMapper bmsStIoDocMapper;
    @Resource
    BmsStIoDtlMapper bmsStIoDtlMapper;

    @Resource
    PubEmployeeMapper pubEmployeeMapper;

    @Resource
    BmsTrPickDocMapper bmsTrPickDocMapper;

    @Resource
    BmsSaDtlTmpMapper bmsSaDtlTmpMaper;

    @Resource
    TransactionTemplate transactionTemplate;

    @Reference
    private IApiOrderDocService iApiOrderDocService;

    @Reference
    private IApiOrderDtlService iApiOrderDtlService;

    @Resource
    private PubEntryGoodsMapper pubEntryGoodsMapper;

    private List<Integer> platformList = new ArrayList<Integer>() {{
        this.add(21);
    }};

    @Override
    public OrderResultDocVo genOrder(OrderInfoDocVo orderInfoDocVo) {
        AssertExt.notNull(orderInfoDocVo, "参数对象为空");
        AssertExt.notEmpty(orderInfoDocVo.getOrderInfoDtlList(), "没有报单的货品信息");

        OrderResultDocVo resultVo = null;


        String orderNo = orderInfoDocVo.getOrderNo();
        Long orderId = orderInfoDocVo.getOrderId();
        //Long customId = orderInfoDocVo.getCustomId();
        Integer entryId = orderInfoDocVo.getEntryId();
        Long b2bStoreId = orderInfoDocVo.getStoreId();
        AssertExt.notNull(b2bStoreId, "StoreId参数对象为空");
        BmsTrPosDef bmsTrPosDef = customService.getCustomIdByStoreId(entryId, b2bStoreId);
        Long customId = bmsTrPosDef.getCompanyid();
        orderInfoDocVo.setCustomId(customId);
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
         * 获取锁资源
         */
        lock.lock(6, TimeUnit.MINUTES);

        try {

            log.info("订单下发程序---------业务逻辑----------");
            // Thread.sleep(5000);
            QueryWrapper<ApiOrderList> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(ApiOrderList::getApiOrderId, orderId);
            queryWrapper.lambda().eq(ApiOrderList::getApiOrderType, orderInfoDocVo.getOrderType());
            queryWrapper.lambda().eq(ApiOrderList::getVersion, orderInfoDocVo.getVersion());
            if (apiOrderListMapper.selectCount(queryWrapper) == 0) {

                ApiOrderList b2bOrderList = new ApiOrderList();

                b2bOrderList.setApiOrderNo(orderNo);
                b2bOrderList.setApiOrderId(orderId);

                b2bOrderList.setApiOrderType(orderInfoDocVo.getOrderType());
                b2bOrderList.setVersion(orderInfoDocVo.getVersion());
                b2bOrderList.setCreateDate(LocalDateTime.now());
                // b2bOrderList.setb2bOrderId(b2bStoreId);
                b2bOrderList.setSrdData(JSON.toJSONString(orderInfoDocVo, SerializerFeature.PrettyFormat,
                        SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.UseSingleQuotes));
                b2bOrderList.setApiOrderId(b2bStoreId);
                b2bOrderList.setHintCount(1);
                apiOrderListMapper.insert(b2bOrderList);


                /**
                 * 分离赠品订单信息
                 */
                OrderInfoDocVo giftOrder = decomposeOrderDetails(orderInfoDocVo, true);

                /**
                 * 分离赠品订单信息
                 */
                OrderInfoDocVo normalOrder = decomposeOrderDetails(orderInfoDocVo, false);

                /*if (ObjectUtil.isNotNull(giftOrder)) {
                    resultVo = OrderResultDocVo.undoneAppend(resultVo, genOrderGift(giftOrder), orderNo, customId);
                    confirmGiftOrder(orderNo,entryId,1,2,platformList);
                }*/

                transactionTemplate.execute(transactionStatus -> {
                    if (customService.isPsCustom(orderInfoDocVo.getCustomId(), orderInfoDocVo.getEntryId())) {
                        genOrderPs(normalOrder);
                        //confirmPsOrder(orderNo,entryId,1,2,platformList);
                    } else {
                        genOrderSa(normalOrder);

                        //  confirmSaOrder(orderNo,entryId,2,1,platformList);
                    }
                    return 0L;
                });


            } else {
                transactionTemplate.execute(transactionStatus -> {
                    //apiOrderListMapper.add1HintCount(queryWrapper);
                    apiOrderListMapper.add1HintCount(queryWrapper, "订单重复下发！！!!!!");
                    return 0l;
                });

                AssertExt.fail("订单重复下发！！!!!!");
            }


        } catch (Throwable e) {
            log.error("执行出现了异常!", e);
            AssertExt.fail("执行出现了异常:" + e.getMessage(), e);

        } finally {
            log.info("订单下发程序---------解锁----------");
            lock.unlock();

        }
        return OrderResultDocVo.validReturn(resultVo, orderNo, customId);

    }

    /**
     * 订单收货
     *
     * @param orderInfoDocVo
     * @return
     */
    @Override
    public OrderResultDocVo receiptOrder(OrderInfoDocVo orderInfoDocVo) {
        AssertExt.notNull(orderInfoDocVo, "参数对象为空");

        OrderResultDocVo resultVo = null;

        String orderId = orderInfoDocVo.getOrderNo();
        AssertExt.notNull(orderId, "报货订单id为空");

        Long customId = orderInfoDocVo.getCustomId();
        AssertExt.notNull(customId, "客户id为空");

        Integer entryId = orderInfoDocVo.getEntryId();
        AssertExt.notNull(entryId, "独立单元id为空");

        if (customService.isPsCustom(orderInfoDocVo.getCustomId(), orderInfoDocVo.getEntryId())) {
            /**
             * 编写配送收货逻辑
             */
            resultVo = receiptOrderPs(orderInfoDocVo);

        }
        return OrderResultDocVo.validReturn(resultVo, orderId, customId);
    }

    private OrderResultDocVo receiptOrderPs(OrderInfoDocVo orderInfoDocVo) {
        OrderResultDocVo resultVo = null;
        String orderId = orderInfoDocVo.getOrderNo();
        Long customId = orderInfoDocVo.getCustomId();
        Integer entryId = orderInfoDocVo.getEntryId();

        /**
         * 对订单明细商品进行“配送收”货操作
         */
        /**
         * 验证订单明细数据有效性
         */
        for (OrderInfoDtlVo orderInfoDtlVo : orderInfoDocVo.getOrderInfoDtlList()) {
            Long goodsid = orderInfoDtlVo.getGoodsId();
            AssertExt.notNull(goodsid, "订单明细的商品id为空！");

            Long srcErpOrderId = orderInfoDtlVo.getSrcErpOrderId();
            AssertExt.notNull(srcErpOrderId, "订单明细的销售细单id为空！");

            Long srcErpOrderDtlId = orderInfoDtlVo.getSrcErpOrderDtlId();
            AssertExt.notNull(srcErpOrderDtlId, "订单明细的销售总单id为空！");
        }

        /**
         * 遍历操作订单明细，进行配送收货操作
         */
        for (OrderInfoDtlVo orderInfoDtlVo : orderInfoDocVo.getOrderInfoDtlList()) {
            Long goodsid = orderInfoDtlVo.getGoodsId();
            Long srcErpOrderId = orderInfoDtlVo.getSrcErpOrderId();
            Long srcErpOrderDtlId = orderInfoDtlVo.getSrcErpOrderDtlId();

            /**
             * 获取销售总单信息（bms_sa_doc）
             */
            BmsSaDoc bmsSaDoc = this.bmsSaDocMapper.selectById(srcErpOrderId);
            if (ObjectUtils.isEmpty(bmsSaDoc)) {
                return OrderResultDocVo.failErrorMessageAppend(resultVo, "销售总单信息(" + srcErpOrderId + ")为空！！", orderId, customId);
            }
            /**
             * 获取销售细单信息（bms_sa_dtl）
             */
            BmsSaDtl bmsSaDtl = this.bmsSaDtlMapper.selectById(srcErpOrderDtlId);
            if (ObjectUtils.isEmpty(bmsSaDtl)) {
                return OrderResultDocVo.failErrorMessageAppend(resultVo, "销售细单信息（" + srcErpOrderDtlId + "）为空", orderId, customId);
            }
            /**
             * 配送总单信息（gpcs_placesupply）
             */
            GpcsPlacesupply gpcsPlacesupply = this.gpcsPlacesupplyMapper.selectById(bmsSaDoc.getPlacesupplyid());
            if (ObjectUtils.isEmpty(gpcsPlacesupply)) {
                return OrderResultDocVo.failErrorMessageAppend(resultVo, "配送单信息（" + bmsSaDoc.getPlacesupplyid() + "）为空", orderId, customId);
            }
            /**
             * 获取门店管理信息（gpcs_placepoint）
             */
            GpcsPlacepoint gpcsPlacepoint = this.gpcsPlacepointMapper.selectById(customId);
            if (ObjectUtils.isEmpty(gpcsPlacepoint)) {
                return OrderResultDocVo.failErrorMessageAppend(resultVo, "门店信息（" + customId + "）为空", orderId, customId);
            }
            /**
             * 获取配送细单信息（gpcs_placesupplydtl）
             */
            GpcsPlacesupplydtl gpcsPlacesupplydtl = this.getGpcsPlacesupplydtl(gpcsPlacesupply.getPlacesupplyid(), bmsSaDtl.getGoodsid(), orderInfoDtlVo.getBatchId());
            if (ObjectUtils.isEmpty(gpcsPlacesupplydtl)) {
                return OrderResultDocVo.failErrorMessageAppend(resultVo, "配送单细单信息为空", orderId, customId);
            }
            /**
             * 获取配送明细信息（gpcs_placesupply_tiny）
             */
            Long gpcsPlacesupplyTiny = this.getGpcsPlacesupplyTinyId(gpcsPlacesupplydtl.getPlacesupplydtlid());
            if (ObjectUtils.isEmpty(gpcsPlacesupplyTiny)) {
                return OrderResultDocVo.failErrorMessageAppend(resultVo, "配送单明细信息为空", orderId, customId);
            }

            /**
             * 1.更新配送明细信息
             */
            /**
             * 2.更新门店商品上次配送更新日期信息
             */
            /**
             * 3.更新货品运营参数
             */
        }

        /**
         * 编写配送收货逻辑
         */
        return OrderResultDocVo.validReturn(resultVo, orderId, customId);
    }

    private Long getGpcsPlacesupplyTinyId(Long placesupplydtlid) {
        return null;
    }

    private GpcsPlacesupplydtl getGpcsPlacesupplydtl(Long placesupplyid, Long goodsid, Long batchId) {
        return null;
    }

    @Override
    public List<String> selectOrderNotCallback(Integer zxBhFlag, Integer b2bNotWriteBack, Integer useStatus) {
        return bmsSaDocMapper.selectNotCallbackBy(zxBhFlag, b2bNotWriteBack, useStatus);
    }


    @Override
    public void updateCallbackStatus(List<String> orderIds, Integer i) {
        bmsSaDocMapper.updateCallbackStatus(orderIds, i);
    }

    @Override
    public Integer getCountBy(Long saleId, String orderId) {
        return bmsSaDocMapper.selectCountBy(saleId, orderId);
    }


    private OrderResultDocVo genOrderGift(OrderInfoDocVo giftOrder) {

        OrderResultDocVo orderResultDocVo = null;
        Long orderId = giftOrder.getOrderId();
        String orderNo = giftOrder.getOrderNo();
        Long customId = giftOrder.getCustomId();
        Integer entryId = giftOrder.getEntryId();

        PubCustomToSaler saler = customService.getSalerByCustomId(customId, entryId);

        PubCustomer customer = customService.getById(customId);
        /**
         * 赠品主表
         */
        BmsPresOutDoc bmsPresOutDoc = new BmsPresOutDoc();


        bmsPresOutDoc.setMemo((ObjectUtil.isNotNull(giftOrder.getRemark()) ? "B2B备注,：" + giftOrder.getRemark() : "") + "B2B订单号：" + giftOrder.getOrderNo());
        bmsPresOutDoc.setB2bOrderNo(orderNo);
        bmsPresOutDoc.setB2bOrderId(orderId);
        bmsPresOutDoc.setB2bAmountTotal(giftOrder.getAmountTotal());
        bmsPresOutDoc.setB2bAmountPay(giftOrder.getAmountPay());
        bmsPresOutDoc.setB2bAmountDelivery(giftOrder.getAmountDelivery());
        bmsPresOutDoc.setB2bAmountDiscount(giftOrder.getAmountDiscount());

        bmsPresOutDoc.setCustomid(customId);
        bmsPresOutDoc.setCustomname(customer.getCustomname());
        bmsPresOutDoc.setEntryid(entryId);
        bmsPresOutDoc.setPolicytype(1);
        bmsPresOutDoc.setComefrom(1);
        bmsPresOutDoc.setPrintflag(0);
        bmsPresOutDoc.setEntryid(entryId);
        /**
         * 11 表示 b2b订单
         */
        bmsPresOutDoc.setZxBhFlag(21);
        //业务时间
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
            orderResultDocVo = OrderResultDocVo.failErrorMessageAppend(orderResultDocVo, "所有商品都不符合出库条件，订单生成失败！", orderNo, customId);


        } else {
            bmsPresOutDoc.setDtlLines(dtlLines);
            bmsPresOutDocMapper.updateById(bmsPresOutDoc);
        }


        return orderResultDocVo;
    }


    /**
     * 生成赠品出入库明细
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
         * 主单ID
         */
        Long docId = bmsPresOutDoc.getPresoutid();

        Long goodsId = dtl.getGoodsId();

        PubGoods goods = goodsService.getById(goodsId);

        double goodQty = dtl.getNum();

        List<BmsStQtyLst> stockList = goodsService.selectStockBy(customer.getCustomid(), goodsId, dtl.getLotNo(), 1, dtl.getStorageId());


        /**
         * 生成出入库明细
         */
        double totalQty = 0;

        for (int i = 0; totalQty < goodQty && i < stockList.size(); i++) {
            double currentQty = 0.0;
            BmsStIoDtlTmp stIoDtlTmp = new BmsStIoDtlTmp();

            BmsPresOutDtl bmsPresOutDtl = new BmsPresOutDtl();
            BeanUtil.copyProperties(bmsPresOutDoc, bmsPresOutDtl);

            bmsPresOutDtl.setPresoutid(docId);

            bmsPresOutDtl.setGoodsid(goodsId);

            BmsStQtyLst stQtyLst = stockList.get(i);

            Double qtyFact = stQtyLst.getQtyFact();
            /**
             * 如果当前没有库存了
             */
            if (qtyFact <= 0) {
                continue;
            }
            /**
             * 当前批次数量不够
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
             * 配送单专用
             */
            stIoDocTmp.setComefrom(18);
            stIoDocTmp.setSourcetable(18);

            /**
             * 配送单专用标记
             */
            stIoDocTmp.setPlacetable(0);

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
     * 分离赠品订单信息
     */
    private OrderInfoDocVo decomposeOrderDetails(OrderInfoDocVo orderInfoDocVo, Boolean isGift) {

        OrderInfoDocVo giftOrder = new OrderInfoDocVo();

        BeanUtil.copyProperties(orderInfoDocVo, giftOrder);
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

            return giftOrder;
        }
        return null;

    }


    private OrderResultDocVo genOrderSa(OrderInfoDocVo orderInfoDocVo) {
        Long orderId = orderInfoDocVo.getOrderId();
        String orderNo = orderInfoDocVo.getOrderNo();
        Long customId = orderInfoDocVo.getCustomId();
        Integer entryId = orderInfoDocVo.getEntryId();
        Long b2bStoreId = orderInfoDocVo.getStoreId();

        PubCustomer customer = customService.getById(customId);

        BmsTrPosDef address = customService.getAddressByCustomId(customId, entryId, b2bStoreId);

        PubCustomToSaler saler = customService.getSalerByCustomId(customId, entryId);

        OrderResultDocVo orderResultDocVo = new OrderResultDocVo(orderNo, customId);
        /**
         * 根据商品的拆弹标记 ，拆分订单
         */
        List<OrderInfoDocVo> orderList = splitOrder(orderInfoDocVo);


        for (OrderInfoDocVo infoDocVo : orderList) {


            BmsSaDoc bmsSaDoc = new BmsSaDoc();

            String address1 = address.getAddress();
            String store;
            if (address1.indexOf(">") > 0) {
                store = address1.substring(0, address1.indexOf(">"));
            } else {
                store = address1;
            }

            bmsSaDoc.setB2bOrderNo(orderNo);
            bmsSaDoc.setB2bOrderId(orderId);
            bmsSaDoc.setB2bStoreId(b2bStoreId);

            bmsSaDoc.setB2bWriteBackFlag(1);
            bmsSaDoc.setB2bSubCustomId((long) 2856);
            bmsSaDoc.setB2bAmountTotal(orderInfoDocVo.getAmountTotal());
            bmsSaDoc.setB2bAmountPay(orderInfoDocVo.getAmountPay());
            bmsSaDoc.setB2bAmountDelivery(orderInfoDocVo.getAmountDelivery());
            bmsSaDoc.setB2bAmountDiscount(orderInfoDocVo.getAmountDiscount());

            bmsSaDoc.setMemo((ObjectUtil.isNotNull(infoDocVo.getRemark()) ? "B2B备注,：" + infoDocVo.getRemark() : "") + store + "B2B订单号：" + infoDocVo.getOrderNo());

            bmsSaDoc.setZxMemo("B2B客户：" + store + "," + customer.getCustomname());

            bmsSaDoc.setUsestatus(2);
            bmsSaDoc.setSatypeid(1);
            bmsSaDoc.setExchange(1.0);
            bmsSaDoc.setSettletypeid(10);
            bmsSaDoc.setEntryid(entryId);

            bmsSaDoc.setCustomid(customId);

            bmsSaDoc.setTotal(infoDocVo.getAmountTotal());
            bmsSaDoc.setRecmoney(infoDocVo.getAmountPay());
            bmsSaDoc.setCustomname(customer.getCustomname());

            bmsSaDoc.setInvtype(customer.getInvtype());
            bmsSaDoc.setSalesdeptid(saler.getSalerdeptid());
            bmsSaDoc.setComefrom(1);
            bmsSaDoc.setApproveflag(0);

            //业务时间
            bmsSaDoc.setCredate(orderInfoDocVo.getCreateDate());
            bmsSaDoc.setAssessdate(orderInfoDocVo.getCreateDate());
            bmsSaDoc.setZxBhFlag(21);

            bmsSaDoc.setDelivermethod(1);
            /**
             * 不需要审批
             */
            //bmsSaDoc.setApproveflag(1);


            bmsSaDoc.setSalerid(saler.getSalerid());

            bmsSaDoc.setInputmanid(saler.getSalerid());

            bmsSaDoc.setTargetposid((long) 4199);


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
                orderResultDocVo = OrderResultDocVo.failErrorMessageAppend(orderResultDocVo, "所有商品都不符合出库条件，订单生成失败！", orderNo, customId);
            } else {


                bmsSaDocMapper.updateSummary(bmsSaDoc.getSalesid(), entryId);
            }

        }


        return orderResultDocVo;
    }

    private OrderResultDocVo genIOStockForSa(BmsSaDoc bmsSaDoc, OrderInfoDtlVo orderInfoDtlVo, Long customId, Integer entryId) {
        PubCustomer customer = customService.getById(customId);


        List<Long> stdDocIds = new ArrayList<>();

        String orderNo = orderInfoDtlVo.getOrderNo();
        String orderDtId = orderInfoDtlVo.getOrderDtlId();

        //Double price = orderInfoDtlVo.getPriceDiscount();
        Double price = NumberUtil.round(orderInfoDtlVo.getAmount() / orderInfoDtlVo.getNum(), 4).doubleValue();

        OrderResultDocVo orderResultDocVo = new OrderResultDocVo(orderNo, customer.getCustomid());
        /**
         * 主单ID
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

            BmsStQtyLst stQtyLst = stockList.get(i);

            double qtyFact = stQtyLst.getQtyFact();
            /**
             * 如果当前没有库存了
             */
            if (qtyFact <= 0) {
                continue;
            }
            /**
             * 当前批次数量不够
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

            bmsSaleDtl.setGoodsqty(currentQty);
            bmsSaleDtl.setGoodsuseqty(currentQty);
            bmsSaleDtl.setCorrectflag(0);
            bmsSaleDtl.setDiscount(1.0);

            bmsSaleDtl.setPriceid(124);


            bmsSaleDtl.setGoodsuseunit(goods.getGoodsunit());

            bmsSaleDtl.setBatchid(stQtyLst.getBatchid());
            bmsSaleDtl.setLotid(stQtyLst.getLotid());
            bmsSaleDtl.setPosid(stQtyLst.getPosid());
            bmsSaleDtl.setGoodsstatusid(stQtyLst.getGoodsstatusid());


            /**
             * 设置总价和数量
             */

            Double totalLine = NumberUtil.round(price * currentQty, 4).doubleValue();
            bmsSaleDtl.setTotalLine(totalLine);


            bmsSaleDtl.setStorageid(stQtyLst.getStorageid());


            bmsSaleDtl.setTotalLine(totalLine);


            bmsSaleDtl.setB2bOrderDtlId(orderInfoDtlVo.getOrderDtlId());
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


            /**
             * 配送单专用
             */
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
        Long b2bStoreId = orderInfoDocVo.getStoreId();

        PubCustomer customer = customService.getById(customId);

        BmsTrPosDef address = customService.getAddressByCustomId(customId, entryId, b2bStoreId);

        PubCustomToSaler saler = customService.getSalerByCustomId(customId, entryId);

        OrderResultDocVo orderResultDocVo = new OrderResultDocVo(orderNo, customId);
        /**
         * 根据商品的拆弹标记 ，拆分订单
         */
        List<OrderInfoDocVo> orderList = splitOrder(orderInfoDocVo);

        for (OrderInfoDocVo infoDocVo : orderList) {


            GpcsPlacesupply gpcsPlacesupply = new GpcsPlacesupply();


            gpcsPlacesupply.setB2bOrderNo(orderNo);
            gpcsPlacesupply.setB2bOrderId(orderId);
            gpcsPlacesupply.setB2bAmountTotal(orderInfoDocVo.getAmountTotal());
            gpcsPlacesupply.setB2bAmountPay(orderInfoDocVo.getAmountPay());
            gpcsPlacesupply.setB2bAmountDelivery(orderInfoDocVo.getAmountDelivery());

            gpcsPlacesupply.setB2bAmountDiscount(orderInfoDocVo.getAmountDiscount());


            gpcsPlacesupply.setB2bOrderType(orderInfoDocVo.getOrderType());

            gpcsPlacesupply.setPlacedate(orderInfoDocVo.getCreateDate());

            gpcsPlacesupply.setMemo((ObjectUtil.isNotNull(infoDocVo.getRemark()) ? "B2B备注,：" + infoDocVo.getRemark() : "") + "B2B订单号：" + infoDocVo.getOrderNo());


            /**
             * 7表示B2B配送单
             */
            //gpcsPlacesupply.setJdOrderType(7);

            //gpcsPlacesupply.setMemo("B2B订单号：" + orderId);

            gpcsPlacesupply.setPlacepointid(customId);

            gpcsPlacesupply.setTotal(infoDocVo.getAmountTotal());

            gpcsPlacesupply.setZxBhFlag(21);

            gpcsPlacesupply.setPlacecenterid(1);

            //配送方式  手工配送
            gpcsPlacesupply.setPlacemethod(7);
            gpcsPlacesupply.setUrgentflag(0);
            gpcsPlacesupply.setDocdiscount(1.0);

            gpcsPlacesupply.setUsestatus(1);


            gpcsPlacesupply.setPlacemanid(saler.getSalerid());
            //运输方式 送货
            gpcsPlacesupply.setDelivermethod(1);

            //业务时间
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
                orderResultDocVo = OrderResultDocVo.failErrorMessageAppend(orderResultDocVo, "所有商品都不符合出库条件，订单生成失败！", orderNo, customId);
            } else {
                gpcsPlacesupply.setDtlLines(dtlLines);
                gpcsPlacesupplyMapper.updateById(gpcsPlacesupply);
            }

        }

        return orderResultDocVo;
    }

    private OrderResultDocVo genIOStockForPs(GpcsPlacesupply gpcsPlacesupply, OrderInfoDtlVo orderInfoDtlVo, Long customId, Integer entryId) {
        PubCustomer customer = customService.getById(customId);

        List<Long> stdDocIds = new ArrayList<>();

        String orderNo = orderInfoDtlVo.getOrderNo();
        String orderDtId = orderInfoDtlVo.getOrderDtlId();

        Double price = orderInfoDtlVo.getPriceDiscount();

        OrderResultDocVo orderResultDocVo = new OrderResultDocVo(orderNo, customer.getCustomid());
        /**
         * 主单ID
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

        for (int i = 0; totalQty < goodQty && i < stockList.size(); i++) {
            double currentQty = 0;
            BmsStIoDtlTmp stIoDtlTmp = new BmsStIoDtlTmp();
            GpcsPlacesupplydtl placesupplyDtl = new GpcsPlacesupplydtl();

            BeanUtil.copyProperties(gpcsPlacesupply, placesupplyDtl);

            BmsStQtyLst stQtyLst = stockList.get(i);
            //Integer qtyFact = stQtyLst.getQtyFact();

            double qtyFact = stQtyLst.getQtyFact();
            /**
             * 如果当前没有库存了
             */
            if (qtyFact <= 0) {
                continue;
            }
            /**
             * 当前批次数量不够
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

            stIoDocTmp.setInoutflag(0);
            stIoDocTmp.setUsestatus(1);
            stIoDocTmp.setGoodsid(goodsId);
            stIoDocTmp.setCompanyid(customId);
            stIoDocTmp.setCompanyname(customer.getCustomname());
            stIoDocTmp.setOutqty(currentQty);
            /**
             * 配送单专用
             */
            stIoDocTmp.setComefrom(16);
            stIoDocTmp.setSourcetable(10);

            stIoDocTmp.setPlacetable(1);

            stIoDocTmp.setStorageid(stQtyLst.getStorageid());


            stIoDocTmp.setGoodsunit(goods.getGoodsunit());

            placesupplyDtl.setGoodsqty(currentQty);


            placesupplyDtl.setPlacesupplyid(docId);


            placesupplyDtl.setTotalLine(price * currentQty);
            placesupplyDtl.setGoodsqty(currentQty);


            placesupplyDtl.setGoodsid(goodsId);
            placesupplyDtl.setGoodsdtlid(stQtyLst.getGoodsdetailid());
            //placesupplyDtl.setta(goods.getSalestaxrate());

            placesupplyDtl.setPlacepriceid(orderInfoDtlVo.getPriceId());
            placesupplyDtl.setPlaceprice(price);

            placesupplyDtl.setGoodsunit(goods.getGoodsunit());

            placesupplyDtl.setDiscount(1.0);


            placesupplyDtl.setB2bOrderDtlId(orderInfoDtlVo.getOrderDtlId());
            placesupplyDtl.setB2bNum(orderInfoDtlVo.getNum());
            placesupplyDtl.setB2bPrice(orderInfoDtlVo.getPrice());
            placesupplyDtl.setB2bPriceDiscount(orderInfoDtlVo.getPriceDiscount());

            placesupplyDtl.setZxSplitType(goods.getZxSplitType());


            gpcsPlacesupplydtlMapper.insert(placesupplyDtl);

            /**
             * 添加St细表的缳
             */

            GpcsPlacesupplydtlSt placesupplyDtlSt = new GpcsPlacesupplydtlSt();


            /**
             * 设置总价和数量
             */
            placesupplyDtlSt.setTotalLine(price * currentQty);
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
             * 添加St细表
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
             * 设置细单
             */
            oneDoc.setOrderInfoDtlList(oneDtlList);

            docList.add(oneDoc);
        }
        return docList;
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
                 * 这里编写每个销售单的确定
                 */
                Long salesId = item.getSalesid();
                long customId = item.getCustomid();
                Long salerId = item.getSalerid();
                Long salesdeptId = item.getSalesdeptid();
                Double total = item.getTotal();
                List<BmsSaDtl> bmsSaDtls = bmsSaDtlMapper.selectListBy(salesId);

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
                         * peiqy 修改
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
                    BmsCreditBillVo bmsCreditBill = bmsCreditBillDtlMapper.queryByid(customId, salerId, salesdeptId);
                    if (!ObjectUtils.isNull(bmsCreditBill)) {
                        Long billid = bmsCreditBill.getBillid();
                        Double owemoney = bmsCreditBill.getOwemoney();
                        owemoney += total;
                        bmsCreditBillDtlMapper.updateByBillid(billid, owemoney);
                        BmsCreditBillDtl bmsCreditBillDtl = new BmsCreditBillDtl();
                        bmsCreditBillDtl.setBillid(billid);
                        bmsCreditBillDtl.setBusimoney(total);
                        bmsCreditBillDtl.setBusitype(1);
                        bmsCreditBillDtl.setSourceid(salesId);
                        bmsCreditBillDtl.setSourcetable("BMS_SA_DOC");
                        bmsCreditBillDtl.setBusicredate(LocalDateTime.now());
                        bmsCreditBillDtl.setBusiconfirmdate(LocalDateTime.now());
                        bmsCreditBillDtlMapper.insert(bmsCreditBillDtl);

                    }
                }


            });
        }


    }


    /**
     * 业务与财务对接
     *
     * @param salesId
     * @return
     */
    private BmsCertDtlTmp genBmsCertDtlTmp(Long salesId) {
        Long traId = bmsCertDtlTmpMapper.getTransactionId();
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
     * 生成总单和调度细单关系
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
        bmsTrBackInsert.setCredate(LocalDateTime.now());
        incaTrBackInsertMapper.insert(bmsTrBackInsert);
        return bmsTrBackInsert;
    }


    /**
     * 生成调度细单单
     *
     * @param
     * @return
     */
    private BmsTrDtl genBmsTrDtl(BmsTrDoc bmsTrDoc) {
        BmsTrDtl bmsTrDtl = new BmsTrDtl();
        bmsTrDtl.setTrid(bmsTrDoc.getTrid());
        bmsTrDtl.setPreparestatus(0);
        bmsTrDtl.setSignflowflag(1);
        bmsTrDtl.setStorerid(7368L);
        bmsTrDtl.setSignformman(bmsTrDoc.getSalerid());

        String employeename = pubEmployeeMapper.getEmployeename(bmsTrDoc.getSalerid());
        bmsTrDtl.setSignformmanname(employeename);
        bmsTrDtl.setSignflowtime(LocalDateTime.now());
        bmsTrDtlMapper.insert(bmsTrDtl);
        return bmsTrDtl;
    }


    /**
     * 生成调度总单
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
        bmsTrDoc.setTocompanyid(bmsTrDoc.getCustomid());
        bmsTrDocMapper.insert(bmsTrDoc);
        return bmsTrDoc;
    }

    /**
     * 销售单和出货单生成对应的关系
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
     * 生成销售订单总单
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

        QueryWrapper<GpcsPlacesupply> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(GpcsPlacesupply::getB2bOrderNo, orderNo);
        queryWrapper.lambda().eq(GpcsPlacesupply::getUsestatus, srcStatus);
        queryWrapper.lambda().in(GpcsPlacesupply::getZxBhFlag, platformList);

        List<GpcsPlacesupply> gpcsPlacesupplies = gpcsPlacesupplyMapper.selectList(queryWrapper);

        if (ObjectUtil.isNotEmpty(gpcsPlacesupplies)) {
            gpcsPlacesupplies.forEach(item -> {
                /**
                 * 这里编写每个配送单的确定
                 */


            });
        }
    }


    @Override
    public Integer getOrderStatus(String orderNo) {
        QueryWrapper<BmsSaDtl> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(BmsSaDtl::getApiOrderNo, orderNo);
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
    public List<OrderDetailVo> selectOrderDetails(String orderNo) {
        return bmsSaDocMapper.selectOrderDetails(orderNo);
    }


    @Override
    public OrderResultDocVo genOrderV2(OrderInfoDocVo orderInfoDocVo) {

        AssertExt.notNull(orderInfoDocVo, "参数对象为空");
        AssertExt.notEmpty(orderInfoDocVo.getOrderInfoDtlList(), "没有报单的货品信息");

        OrderResultDocVo resultVo = null;

        String orderNo = orderInfoDocVo.getOrderNo();
        Long orderId = orderInfoDocVo.getOrderId();
        Long customId = orderInfoDocVo.getCustomId();

        Long b2bStoreId = orderInfoDocVo.getStoreId();

        RLock lock = redissonClient.getLock("order_gen");
        /**
         * 获取锁资源
         */
        lock.lock(6, TimeUnit.MINUTES);

        try {

            log.info("订单下发程序---------业务逻辑----------");
            // Thread.sleep(5000);
            QueryWrapper<ApiOrderList> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(ApiOrderList::getApiOrderId, orderId);
            queryWrapper.lambda().eq(ApiOrderList::getApiOrderType, orderInfoDocVo.getOrderType());
            queryWrapper.lambda().eq(ApiOrderList::getVersion, orderInfoDocVo.getVersion());
            if (apiOrderListMapper.selectCount(queryWrapper) == 0) {

                /**
                 * 独立事务处理
                 */
                transactionTemplate.execute(transactionStatus -> {
                    try {
                        ApiOrderList b2bOrderList = new ApiOrderList();

                        b2bOrderList.setApiOrderNo(orderNo);
                        b2bOrderList.setApiOrderId(orderId);

                        b2bOrderList.setApiOrderType(orderInfoDocVo.getOrderType());
                        b2bOrderList.setVersion(orderInfoDocVo.getVersion());
                        b2bOrderList.setCreateDate(LocalDateTime.now());
                        // b2bOrderList.setb2bOrderId(b2bStoreId);
                        b2bOrderList.setSrdData(JSON.toJSONString(orderInfoDocVo, SerializerFeature.PrettyFormat,
                                SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.UseSingleQuotes));
                        b2bOrderList.setApiStoreId(b2bStoreId);
                        b2bOrderList.setHintCount(1);
                        apiOrderListMapper.insert(b2bOrderList);
                    } catch (Exception e) {
                        AssertExt.fail("保存原始数据出现异常:" + e.getMessage(), e);
                    }
                    return 0L;
                });

               /* ApiOrderList b2bOrderList = new ApiOrderList();

                b2bOrderList.setApiOrderNo(orderNo);
                b2bOrderList.setApiOrderId(orderId);

                b2bOrderList.setApiOrderType(orderInfoDocVo.getOrderType());
                b2bOrderList.setVersion(orderInfoDocVo.getVersion());
                b2bOrderList.setCreateDate(LocalDateTime.now());
                // b2bOrderList.setb2bOrderId(b2bStoreId);
                b2bOrderList.setSrdData(JSON.toJSONString(orderInfoDocVo, SerializerFeature.PrettyFormat,
                        SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.UseSingleQuotes));
                b2bOrderList.setApiStoreId(b2bStoreId);
                b2bOrderList.setHintCount(1);
                apiOrderListMapper.insert(b2bOrderList);*/


                OrderResultDocVo validGoodsRs = transactionTemplate.execute(transactionStatus -> {
                   return valid(orderInfoDocVo);
                });
                if (validGoodsRs != null){      // 校验不通过
                    return validGoodsRs;
                }

                transactionTemplate.execute(transactionStatus -> {
                    //push2B2b(orderInfoDocVo);
                    RLock lockx = redissonClient.getLock("order_gen_push");
                    lockx.lock(6, TimeUnit.MINUTES);
                    try {
                        push2B2bCart(orderInfoDocVo);
                        push2B2bPayOrder(orderInfoDocVo);
                        saveApiOrderData(orderInfoDocVo);
                        apiOrderListMapper.updateSuccess(queryWrapper, "下发成功");
                    } catch (Exception e) {
                        apiOrderListMapper.updateFailed(queryWrapper, "下发出现异常：" + e.getMessage() + "[" + e.getClass() + "]");
                        //AssertExt.fail("执行出现了异常:" + e.getMessage(), e);
                    }finally {
                        b2bGoodsService.delMemberStoreCart(orderInfoDocVo.getB2bCustomId(),orderInfoDocVo.getStoreId());
                        lockx.unlock();
                    }
                    return 0L;
                });

            } else {
                String message = "订单重复下发！！!!!!";
                apiOrderListMapper.add1HintCount(queryWrapper, message);
                AssertExt.fail(message);
            }


        } catch (Throwable e) {
            log.error("执行出现了异常!", e);
            AssertExt.fail("执行出现了异常:" + e.getMessage(), e);

        } finally {
            log.info("订单下发程序---------解锁----------");
            lock.unlock();
        }
        return OrderResultDocVo.validReturn(resultVo, orderNo, customId);

    }

    private OrderResultDocVo valid(OrderInfoDocVo orderInfoDocVo) {
        Integer entryId = orderInfoDocVo.getEntryId();
        Long b2bStoreId = orderInfoDocVo.getStoreId();
        Long orderId = orderInfoDocVo.getOrderId();
        String orderNo = orderInfoDocVo.getOrderNo();

        AssertExt.notNull(b2bStoreId, "StoreId参数对象为空");
        BmsTrPosDef bmsTrPosDef = customService.getCustomIdByStoreId(entryId, b2bStoreId);
        Long customId = bmsTrPosDef.getCompanyid();
        orderInfoDocVo.setCustomId(customId);
        ResultVo result1 = customService.valid(customId, entryId);

        ArrayList<Long> goodsIdList = new ArrayList<>();
        orderInfoDocVo.getOrderInfoDtlList().forEach(item -> {
            goodsIdList.add(item.getGoodsId());
        });
        result1 = ResultVo.failAppend(result1, goodsService.valid(customId, entryId, goodsIdList));

        if (result1.isError()) {
            QueryWrapper<ApiOrderList> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(ApiOrderList::getApiOrderId, orderId);
            queryWrapper.lambda().eq(ApiOrderList::getApiOrderType, orderInfoDocVo.getOrderType());
            queryWrapper.lambda().eq(ApiOrderList::getVersion, orderInfoDocVo.getVersion());
            apiOrderListMapper.updateFailed(queryWrapper, "商品校验异常：" + result1.getErrorMessage());
            log.error(result1.getErrorMessage());
            return OrderResultDocVo.validReturn(result1, orderNo, customId);
        }
        return null;
    }

    // 保存对接订单数据     --by oucs
    public void saveApiOrderData(OrderInfoDocVo orderInfoDocVo) {
        ApiOrderDoc doc = new ApiOrderDoc();
        List<ApiOrderDtl> dtlList = new ArrayList<>();
        doc.setB2bCustomId(orderInfoDocVo.getB2bCustomId());
        doc.setCreateDate(orderInfoDocVo.getCreateDate());
        doc.setCustomId(orderInfoDocVo.getCustomId());
        doc.setEntryId(orderInfoDocVo.getEntryId());
        doc.setOrderId(orderInfoDocVo.getOrderId());
        doc.setOrderNo(orderInfoDocVo.getOrderNo());
        doc.setOrderType(orderInfoDocVo.getOrderType());
        doc.setPayType(orderInfoDocVo.getPayType());
        doc.setRemark(orderInfoDocVo.getRemark());
        doc.setStoreId(orderInfoDocVo.getStoreId());
        doc.setVersion(orderInfoDocVo.getVersion());
        iApiOrderDocService.save(doc);

        orderInfoDocVo.getOrderInfoDtlList().forEach(item -> {
            ApiOrderDtl dtl = new ApiOrderDtl();
            dtl.setAmount(item.getAmount());
            dtl.setGoodsId(item.getGoodsId());
            dtl.setLotNo(item.getLotNo());
            dtl.setNum(item.getNum());
            dtl.setOrderDtlId(item.getOrderDtlId());
            dtl.setOrderNo(item.getOrderNo());
            dtl.setPrice(item.getPrice());
            dtl.setSrcOrderDtlId(item.getSrcOrderDtlId());
            dtl.setSrcorderid(item.getSrcOrderId());
            dtl.setStorageId(item.getStorageId());
            dtl.setApiOrderId(doc.getId());
            dtlList.add(dtl);
        });
        iApiOrderDtlService.saveBatch(dtlList);
    }

    private void push2B2b(OrderInfoDocVo orderInfoDocVo) {
        List<Cart> cartList = new ArrayList<>();
        orderInfoDocVo.getOrderInfoDtlList().forEach(orderInfoDtlVo -> {
            Cart cart = new Cart();
            cart.setErpGoodsId(orderInfoDtlVo.getGoodsId());
            cart.setGoodsNum(orderInfoDtlVo.getNum().intValue());
            cart.setStorageId(orderInfoDtlVo.getStorageId().longValue());
            PubGoods goods = goodsService.getById(orderInfoDtlVo.getGoodsId());
            cart.setGoodsName(goods.getGoodsname());
            cart.setStoreId(orderInfoDocVo.getStoreId());
            //cart.setSrcOrderDtlId(Long.valueOf(Optional.ofNullable()orderInfoDtlVo.getOrderDtlId()));
            cartList.add(cart);
        });
        //b2bGoodsService.addMemberCart(cartList, orderInfoDocVo.getB2bCustomId());

        QueryWrapper<ApiOrderList> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ApiOrderList::getApiOrderId, orderInfoDocVo.getOrderId());
        queryWrapper.lambda().eq(ApiOrderList::getApiOrderType, orderInfoDocVo.getOrderType());
        queryWrapper.lambda().eq(ApiOrderList::getVersion, orderInfoDocVo.getVersion());
        transactionTemplate.execute(transactionStatus -> {
            try {
                b2bGoodsService.addMemberCart(cartList, orderInfoDocVo.getB2bCustomId());
            } catch (Exception e) {
                apiOrderListMapper.updateFailed(queryWrapper, "添加购物车出现异常：" + e.getMessage() + "[" + e.getClass() + "]");
            }
            return 0L;
        });

        OrderInfoVo orderInfo = new OrderInfoVo();
        orderInfo.setAddressDetail("默认地址");
        orderInfo.setSrcOrderId(orderInfoDocVo.getOrderId());
        orderInfo.setSrcOrderNo(orderInfoDocVo.getOrderNo());
        orderInfo.setSrcOrderTime(orderInfoDocVo.getCreateDate());
        orderInfo.setOrderFrom(4);
        orderInfo.setMemberId(orderInfoDocVo.getB2bCustomId());
        orderInfo.setPayMethod(OrderInfo.EPayMethod.MONTH.val());
        orderInfo.setCreateTime(orderInfoDocVo.getCreateDate());
        orderInfo.setStoreId(orderInfoDocVo.getStoreId());
        /**
         * 门店ID,传到inca 是 b2b_store_id
         */
        //b2bOrderInfoService.memberPayOrder(orderInfo);
        transactionTemplate.execute(transactionStatus -> {
            try {
                b2bOrderInfoService.memberPayOrder(orderInfo);
            } catch (Exception e) {
                apiOrderListMapper.updateFailed(queryWrapper, "b2b下单出现异常：" + e.getMessage() + "[" + e.getClass() + "]");
            }
            return 0L;
        });
    }

    private QueryWrapper<ApiOrderList> createApiQueryWrapper(OrderInfoDocVo orderInfoDocVo){

        QueryWrapper<ApiOrderList> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(ApiOrderList::getApiOrderId, orderInfoDocVo.getOrderId());
        queryWrapper.lambda().eq(ApiOrderList::getApiOrderType, orderInfoDocVo.getOrderType());
        queryWrapper.lambda().eq(ApiOrderList::getVersion, orderInfoDocVo.getVersion());

        return queryWrapper;
    }

    /**
     * 报货数量换算
     *
     * @param goodsId
     * @param entryId
     * @param currNum
     * @return
     */
    private int divisionNum(Long goodsId,Integer entryId,Integer currNum){

        PubEntryGoods pubEntryGoods = pubEntryGoodsMapper.selectOne(new QueryWrapper<PubEntryGoods>().eq("GOODSID", goodsId).eq("ENTRYID", entryId));
        if (pubEntryGoods.getZxB2bNumLimit() != null && pubEntryGoods.getZxB2bNumLimit().intValue() > 1){
            return  currNum / pubEntryGoods.getZxB2bNumLimit().intValue();
        }
        return currNum;
    }

    /**
     * 添加购物车
     * @param orderInfoDocVo
     */
    private void push2B2bCart(OrderInfoDocVo orderInfoDocVo) {
        List<Cart> cartList = new ArrayList<>();
        Integer entryId = orderInfoDocVo.getEntryId();
        Long storeId = orderInfoDocVo.getStoreId();
        orderInfoDocVo.getOrderInfoDtlList().forEach(orderInfoDtlVo -> {
            Cart cart = new Cart();
            Long goodsId = orderInfoDtlVo.getGoodsId();
            PubGoods goods = goodsService.getById(goodsId);

            if(goods.getB2bShopFlag() == 1){    // 只允许添加b2b上架商品
                int num = divisionNum(goodsId, entryId, orderInfoDtlVo.getNum().intValue());
                cart.setErpGoodsId(goodsId);
                //cart.setGoodsNum(orderInfoDtlVo.getNum().intValue());
                cart.setGoodsNum(num);
                cart.setStorageId(orderInfoDtlVo.getStorageId().longValue());
                cart.setGoodsName(goods.getGoodsname());
                cart.setStoreId(storeId);
                cartList.add(cart);
            }
        });

        QueryWrapper<ApiOrderList> queryWrapper = createApiQueryWrapper(orderInfoDocVo);
        transactionTemplate.execute(transactionStatus -> {
            try {
                b2bGoodsService.addMemberCart(cartList, orderInfoDocVo.getB2bCustomId());
            } catch (Exception e) {
                apiOrderListMapper.updateFailed(queryWrapper, "添加购物车出现异常：" + e.getMessage() + "[" + e.getClass() + "]");
            }
            return 0L;
        });
    }

    /**
     * 下单
     * @param orderInfoDocVo
     */
    private void push2B2bPayOrder(OrderInfoDocVo orderInfoDocVo) {

        QueryWrapper<ApiOrderList> queryWrapper = createApiQueryWrapper(orderInfoDocVo);

        OrderInfoVo orderInfo = new OrderInfoVo();
        orderInfo.setAddressDetail("默认地址");
        orderInfo.setSrcOrderId(orderInfoDocVo.getOrderId());
        orderInfo.setSrcOrderNo(orderInfoDocVo.getOrderNo());
        orderInfo.setSrcOrderTime(orderInfoDocVo.getCreateDate());
        orderInfo.setOrderFrom(4);
        orderInfo.setMemberId(orderInfoDocVo.getB2bCustomId());
        orderInfo.setPayMethod(OrderInfo.EPayMethod.MONTH.val());
        orderInfo.setCreateTime(orderInfoDocVo.getCreateDate());
        orderInfo.setStoreId(orderInfoDocVo.getStoreId());
        /**
         * 门店ID,传到inca 是 b2b_store_id
         */
        //b2bOrderInfoService.memberPayOrder(orderInfo);
        transactionTemplate.execute(transactionStatus -> {
            try {
                b2bOrderInfoService.memberPayOrder(orderInfo);
            } catch (Exception e) {
                apiOrderListMapper.updateFailed(queryWrapper, "b2b下单出现异常：" + e.getMessage() + "[" + e.getClass() + "]");
            }
            return 0L;
        });
    }

    @Override
    public Boolean isUseWms(Long storageId, Integer entryId) {

        Integer useWms = bmsStDefMapper.getUseWms(storageId, entryId);

        if (ObjectUtil.equal(1, useWms)) {
            return true;
        } else {
            return false;
        }

    }
}
