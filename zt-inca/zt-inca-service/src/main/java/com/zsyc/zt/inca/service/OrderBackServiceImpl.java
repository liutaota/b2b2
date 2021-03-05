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
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author peiqy
 * @version 1.0
 * @email peiqy@foxmail.com
 * @date 2020/7/29 11:12
 */



@Service
@Slf4j
public class OrderBackServiceImpl implements OrderBackService {


    @Resource
    private GpcsPlaceReturnMapper gpcsPlaceReturnMapper;
    @Resource
    private GpcsPlaceReturnDtlMapper gpcsPlaceReturnDtlMapper;
    @Resource
    private BmsStIoDocTmpMapper bmsStIoDocTmpMapper;
    @Resource
    private BmsStIoDtlMapper bmsStIoDtlMapper;
    @Resource
    private GoodsService goodsService;
    @Resource
    private CustomService customService;
    @Resource
    private BmsStIoDtlTmpMapper bmsStIoDtlTmpMapper;
    @Resource
    private BmsTrFetchDocMapper bmsTrFetchDocMapper;
    @Resource
    private BmsTrFetchDtlMapper bmsTrFetchDtlMapper;
    @Resource
    private PubCustomerMapper pubCustomerMapper;
    @Resource
    private BmsSaDtlMapper bmsSaDtlMapper;
    @Resource
    private BmsStReTinyMapper bmsStReTinyMapper;
    @Resource
    BmsPresOutDocMapper bmsPresOutDocMapper;
    @Resource
    OrderService orderService;
    @Resource
    BmsStReDocMapper bmsStReDocMapper;
    @Resource
    BmsLotDefMapper bmsLotDefMapper;
    @Resource
    BmsStReDtlMapper bmsStReDtlMapper;
    @Resource
    BmsStRgDtlMapper bmsStRgDtlMapper;
    @Resource
    BmsStRgDocMapper bmsStRgDocMapper;
    @Resource
    GspPhysicQualityCheckMapper gspPhysicQualityCheckMapper;
    @Resource
    BmsStSumQtyMapper bmsStSumQtyMapper;
    @Resource
    BmsFetchToSaioMapper bmsFetchToSaioMapper;

    @Resource
    PubStorerMapper pubStorerMapper;

    @Resource
    RedissonClient redissonClient;
    @Resource
    B2bOrderBackListMapper b2bOrderBackListMapper;

    @Resource
    PubEmployeeMapper pubEmployeeMapper;


    @Resource
    BmsStQtyLstMapper bmsStQtyLstMapper;


    /**
     * 生产退货单
     *
     * @param orderBackInfoDocVo
     * @return
     */
    @Override
    public OrderResultDocVo genBackOrderV1(OrderBackInfoDocVo orderBackInfoDocVo) {
        /**
         * 先做最基本的校验
         */
        AssertExt.notNull(orderBackInfoDocVo, "对象不能为空");
        OrderResultDocVo resultVo = null;
        OrderResultDocVo result = null;
        Long customId = orderBackInfoDocVo.getCustomId();
        AssertExt.notNull("customId", "客户id不能为空");
        AssertExt.notNull(orderBackInfoDocVo.getOrderId(), "订单id不能为空");

        Long orderId = orderBackInfoDocVo.getOrderId();
        String orderNo = orderBackInfoDocVo.getOrderNo();

        RLock lock = redissonClient.getLock("order_back_gen:"+orderId);
        /**
         * 获取锁资源
         */
        lock.lock(2, TimeUnit.MINUTES);
        try {

            log.info("订单退单下发程序---------业务逻辑----------");

            QueryWrapper<B2bOrderBackList> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(B2bOrderBackList::getB2bOrderId, orderId);
            queryWrapper.lambda().eq(B2bOrderBackList::getVersion, orderBackInfoDocVo.getVersion());
            if (b2bOrderBackListMapper.selectCount(queryWrapper) == 0) {

                B2bOrderBackList b2bOrderList = new B2bOrderBackList();

                b2bOrderList.setB2bOrderNo(orderNo);
                b2bOrderList.setB2bOrderId(orderId);
                b2bOrderList.setB2bSrcOrderId(orderBackInfoDocVo.getSrcOrderId());
                b2bOrderList.setB2bSrcOrderNo(orderBackInfoDocVo.getSrcOrderNo());
                b2bOrderList.setVersion(orderBackInfoDocVo.getVersion());
                b2bOrderList.setCreateDate(LocalDateTime.now());

                b2bOrderList.setSrcData(JSON.toJSONString(orderBackInfoDocVo, SerializerFeature.PrettyFormat,
                        SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.UseSingleQuotes));

                b2bOrderBackListMapper.insert(b2bOrderList);

                Boolean psCustom = customService.isPsCustom(orderBackInfoDocVo.getCustomId(), orderBackInfoDocVo.getEntryId());
                if(psCustom){
                    validBackDataPs(orderBackInfoDocVo);
                }else{
                    validBackDataSa(orderBackInfoDocVo);
                }
                if (customService.isPsCustom(orderBackInfoDocVo.getCustomId(), orderBackInfoDocVo.getEntryId())) {
                    result = OrderResultDocVo.failAppend(result, genBackOrderPsV1(orderBackInfoDocVo), orderNo, customId);
                } else {
                    result = OrderResultDocVo.failAppend(result, genBackOrderSaV1(orderBackInfoDocVo), orderNo, customId);

                }

            } else {
                AssertExt.fail("订单退单重复下发！！!!!!");
            }

        } catch (Throwable e) {
            e.printStackTrace();
            log.error("执行出现了异常!", e.getMessage());
            AssertExt.fail("执行出现了异常:" + e.getMessage(), e);
        } finally {
            log.info("订单退单下发程序---------解锁----------");
            lock.unlock();
        }

        return result;


    }

    private OrderResultDocVo genBackOrderSaV1(OrderBackInfoDocVo orderBackInfoDocVo) {

        List<BmsTrFetchDoc>  bmsTrFetchDocList = genTmpBackOrderSa(orderBackInfoDocVo);
        confirmTmpBackOrderSa(bmsTrFetchDocList,orderBackInfoDocVo);
        return null;
    }

    private void confirmTmpBackOrderSa( List<BmsTrFetchDoc>  bmsTrFetchDocList,OrderBackInfoDocVo orderBackInfoDocVo) {

        for (BmsTrFetchDoc bmsTrFetchDoc : bmsTrFetchDocList) {
            bmsTrFetchDoc.setPlaninvdate(LocalDateTime.now());
            bmsTrFetchDoc.setConfirmdate(LocalDateTime.now());
            bmsTrFetchDoc.setConfirmmanid(orderBackInfoDocVo.getApproveManId());
            bmsTrFetchDocMapper.updateById(bmsTrFetchDoc);
            bmsTrFetchDtlMapper.confirmByFetchId(bmsTrFetchDoc.getFetchid());

        }

    }

    private List<BmsTrFetchDoc> genTmpBackOrderSa(OrderBackInfoDocVo orderBackInfoDocVo) {

        List<BmsTrFetchDoc> bmsTrFetchDocList = new ArrayList<>();
        CustomerVo customerVo = customService.getByCustomId(orderBackInfoDocVo.getCustomId(), orderBackInfoDocVo.getEntryId());

        /**
         * 制单人信息
         */
        PubEmployee inputManL = pubEmployeeMapper.selectById(orderBackInfoDocVo.getApproveManId());//获取制单人信息
        /**
         * 根据保管账不同，分离订单
         */
        Collection<OrderBackInfoDocVo> orderBackInfoDocVos = splitOrder(orderBackInfoDocVo);

        Integer userWms = pubStorerMapper.getUseWms(orderBackInfoDocVo.getStorerId());
        for (OrderBackInfoDocVo backInfoDocVo : orderBackInfoDocVos) {

            /**
             * 生成退单总记录
             */
            BmsTrFetchDoc bmsTrFetchDoc = new BmsTrFetchDoc();
            bmsTrFetchDoc.setCompanyname(customerVo.getCustomname());
            bmsTrFetchDoc.setSettletypeid(customerVo.getSettletypeid());
            bmsTrFetchDoc.setInvdemand(customerVo.getInvdemand());
            bmsTrFetchDoc.setInvtype(customerVo.getInvtype());


            bmsTrFetchDoc.setMemo("B2B退单号：" + backInfoDocVo.getOrderNo()+"，源订单号:"+backInfoDocVo.getSrcOrderNo()+",退货备注:"+backInfoDocVo.getRemark());
            bmsTrFetchDoc.setCompanyid(backInfoDocVo.getCustomId());
            bmsTrFetchDoc.setZxTotal(backInfoDocVo.getAmountTotal());

            bmsTrFetchDoc.setB2bOrderNo(backInfoDocVo.getOrderNo());
            bmsTrFetchDoc.setB2bSrcOrderId(backInfoDocVo.getSrcOrderId());
            bmsTrFetchDoc.setB2bOrderId(backInfoDocVo.getOrderId());
            bmsTrFetchDoc.setB2bSrcOrderNo(backInfoDocVo.getSrcOrderNo());
            bmsTrFetchDoc.setCredate(orderBackInfoDocVo.getCreateDate());

            bmsTrFetchDoc.setInputmanid(backInfoDocVo.getApproveManId());

            bmsTrFetchDoc.setStorerid(backInfoDocVo.getStorerId());
            bmsTrFetchDoc.setStorageid(backInfoDocVo.getStoragetId());
            bmsTrFetchDoc.setFetchtype(2);


            bmsTrFetchDoc.setDelivermethod(1);

            bmsTrFetchDoc.setEntryid(backInfoDocVo.getEntryId());
            bmsTrFetchDoc.setSalerid(backInfoDocVo.getApproveManId());
            bmsTrFetchDoc.setSalesdeptid(inputManL.getDeptid());

            bmsTrFetchDocMapper.insert(bmsTrFetchDoc);

            for (OrderBackInfoDtlVo orderBackInfoDtlVo : backInfoDocVo.getOrderInfoDtlList()) {

                //Long iodtlId =bmsStIoDtlMapper.getBySaleDtlId(orderBackInfoDtlVo.getSrcErpOrderDtlId());
                BmsTrFetchDtl bmsTrFetchDtl = bmsTrFetchDtlMapper.selectBySalesDtlId(orderBackInfoDtlVo.getSrcErpOrderDtlId());
                /**
                 * 生成销退单
                 */
                //BmsTrFetchDtl bmsTrFetchDtl = new BmsTrFetchDtl();

                bmsTrFetchDtl.setFetchid(bmsTrFetchDoc.getFetchid());
                bmsTrFetchDtl.setZxSalesdocid(orderBackInfoDtlVo.getSrcErpOrderId());
                bmsTrFetchDtl.setSaledtlid(orderBackInfoDtlVo.getSrcErpOrderDtlId());
                bmsTrFetchDtl.setUsestatus(2);
                /*退货原因id*/
                bmsTrFetchDtl.setBackwhyid(orderBackInfoDtlVo.getReason());
                bmsTrFetchDtl.setBatchflag(1);
                bmsTrFetchDtl.setGoodsqty(orderBackInfoDtlVo.getNum());
                //bmsTrFetchDtl.setIodtlId(iodtlId);
                bmsTrFetchDtl.setSendwmsflag(userWms);

                bmsTrFetchDtl.setTotalLine(orderBackInfoDtlVo.getAmount());


                bmsTrFetchDtlMapper.insert(bmsTrFetchDtl);
                BmsFetchToSaio bmsFetchToSaio =new BmsFetchToSaio();
                bmsFetchToSaio.setFetchdtlid(bmsTrFetchDtl.getFetchdtlid());
                bmsFetchToSaio.setIodtlid(bmsTrFetchDtl.getIodtlid());
                bmsFetchToSaio.setGoodsqty(bmsTrFetchDtl.getGoodsqty());
                bmsFetchToSaio.setSalesdtlid(bmsTrFetchDtl.getSaledtlid());
                bmsFetchToSaioMapper.insert(bmsFetchToSaio);
            }

            bmsTrFetchDocList.add(bmsTrFetchDoc);
        }
        return bmsTrFetchDocList;

    }

    private OrderResultDocVo genBackOrderPsV1(OrderBackInfoDocVo orderBackInfoDocVo) {

        GpcsPlaceReturn gpcsPlaceReturn = genTmpBackOrderPs(orderBackInfoDocVo);
        confirmTmpBackOrderPs(gpcsPlaceReturn,orderBackInfoDocVo);
        return null;



    }

    private void confirmTmpBackOrderPs(GpcsPlaceReturn gpcsPlaceReturn, OrderBackInfoDocVo orderBackInfoDocVo) {



    }

    private GpcsPlaceReturn genTmpBackOrderPs(OrderBackInfoDocVo orderBackInfoDocVo) {
        Long customId = orderBackInfoDocVo.getCustomId();

        Integer placepointStorageId = customService.getPlacepointStorageIdByCustomId(orderBackInfoDocVo.getCustomId());


        GpcsPlaceReturn gpcsPlaceReturn = new GpcsPlaceReturn();
        gpcsPlaceReturn.setB2bOrderId(orderBackInfoDocVo.getOrderId());
        gpcsPlaceReturn.setB2bOrderNo(orderBackInfoDocVo.getOrderNo());

        gpcsPlaceReturn.setB2bSrcOrderId(orderBackInfoDocVo.getSrcOrderId());
        gpcsPlaceReturn.setB2bSrcOrderNo(orderBackInfoDocVo.getSrcOrderNo());

        gpcsPlaceReturn.setZxBhFlag(11);
        gpcsPlaceReturn.setPlacepointid(customId);
        gpcsPlaceReturn.setPlacecenterid(1);
        //todo 需要修复！！
        gpcsPlaceReturn.setPlacemandid(1L);
        gpcsPlaceReturn.setInputflag(1);
        gpcsPlaceReturn.setApprovestatusdoc(4);
        gpcsPlaceReturn.setPLACEDATE(LocalDateTime.now());
        gpcsPlaceReturn.setB2bnotwriteback(1);

        gpcsPlaceReturn.setMemo("B2B退单号：" + orderBackInfoDocVo.getOrderNo()+"，源订单号:"+orderBackInfoDocVo.getSrcOrderNo()+",退货备注:"+orderBackInfoDocVo.getRemark());
        /*核销加入的字段*/

        gpcsPlaceReturnMapper.insert(gpcsPlaceReturn);

        for (OrderBackInfoDtlVo orderBackInfoDtlVo : orderBackInfoDocVo.getOrderInfoDtlList()) {

            Long goodsId = orderBackInfoDtlVo.getGoodsId();
            Double goodQty = orderBackInfoDtlVo.getNum();
            Long lotId = orderBackInfoDtlVo.getLotId() ;
            Long batchId = orderBackInfoDtlVo.getBatchId()  ;
            Double unitprice=null;
            if(orderBackInfoDtlVo.getPriceDiscount()>0){
                unitprice =orderBackInfoDtlVo.getPriceDiscount();
            }else{
                unitprice=orderBackInfoDtlVo.getPrice();
            }

            Double resaleprice = goodsService.selectResalePriceBy(goodsId, customId);



            List<BmsStQtyLst> bmsStQtyLsts = bmsStQtyLstMapper.selectPlacepointStQtyList(placepointStorageId,orderBackInfoDtlVo.getGoodsDtlId(),orderBackInfoDtlVo.getLotId());
            double totalQty = 0;
            for (int i = 0; totalQty < goodQty && i < bmsStQtyLsts.size(); i++) {
                double currentQty = 0.0;
                BmsStQtyLst bmsStQtyLst = bmsStQtyLsts.get(i);
                Double qtyFact = bmsStQtyLst.getGoodsqty();
                /**
                 * 如果当前没有库存了
                 */
                if (qtyFact <= 0) {
                    continue;
                }
                /**
                 *
                 * 当前批次数量不够
                 */
                if (qtyFact < goodQty - totalQty) {
                    totalQty += qtyFact;
                    currentQty = qtyFact;
                } else {
                    currentQty = goodQty - totalQty;
                    totalQty = goodQty;
                }
                GpcsPlaceReturnDtl gpcsPlaceReturnDtl = new GpcsPlaceReturnDtl();
                BeanUtil.copyProperties(bmsStQtyLst,gpcsPlaceReturnDtl);

                gpcsPlaceReturnDtl.setPlacereturnid(gpcsPlaceReturn.getPlacereturnid());
                gpcsPlaceReturnDtl.setGoodsqty(currentQty);
                gpcsPlaceReturnDtl.setApprovestatus(3);
                gpcsPlaceReturnDtl.setResaleprice(resaleprice);
                gpcsPlaceReturnDtl.setBackwhyid(2);
                gpcsPlaceReturnDtl.setPlacemoney(currentQty * unitprice);
                gpcsPlaceReturnDtl.setCansaleqty(qtyFact);
                gpcsPlaceReturnDtl.setPlaceprice(unitprice);
                gpcsPlaceReturnDtl.setPlacepriceid(1);
                gpcsPlaceReturnDtl.setUsestatus("3");
                gpcsPlaceReturnDtl.setApprovedate(LocalDateTime.now());
                gpcsPlaceReturnDtl.setSendwmsflag(0);
                gpcsPlaceReturnDtl.setB2bOrderId(orderBackInfoDtlVo.getOrderId());
                gpcsPlaceReturnDtl.setB2bOrderNo(orderBackInfoDtlVo.getOrderNo());
                gpcsPlaceReturnDtl.setB2bSrcOrderId(orderBackInfoDtlVo.getSrcOrderId());
                gpcsPlaceReturnDtl.setB2bSrcOrderNo(orderBackInfoDtlVo.getSrcOrderNo());
                gpcsPlaceReturnDtl.setB2bSrcOrderDtlId(orderBackInfoDtlVo.getSrcOrderDtlId());
                gpcsPlaceReturnDtl.setB2bSrcErpOrderDtlId(orderBackInfoDtlVo.getSrcErpOrderDtlId());
                gpcsPlaceReturnDtl.setB2bSrcErpOrderId(orderBackInfoDtlVo.getSrcErpOrderId());
                gpcsPlaceReturnDtlMapper.insert(gpcsPlaceReturnDtl);
                BmsStIoDocTmp bmsStIoDocTmp = new BmsStIoDocTmp();
                bmsStIoDocTmp.setCredate(LocalDateTime.now());
                bmsStIoDocTmp.setComefrom(77);
                bmsStIoDocTmp.setSourcetable(77);
                bmsStIoDocTmp.setSourceid(gpcsPlaceReturnDtl.getPlacereturndtlid());
                bmsStIoDocTmp.setCompanyid((long) 1);
                bmsStIoDocTmp.setCompanyname("广东中天医药有限公司");
                bmsStIoDocTmp.setGoodsid(goodsId);
                bmsStIoDocTmp.setStorageid(placepointStorageId);
                bmsStIoDocTmp.setInoutflag(0);
                bmsStIoDocTmp.setOutqty(currentQty);
                bmsStIoDocTmp.setOldqty(currentQty);
                bmsStIoDocTmp.setGoodsunit(gpcsPlaceReturnDtl.getGoodsunit());
                bmsStIoDocTmp.setUsestatus(1);
                bmsStIoDocTmp.setEntryid(3);
                bmsStIoDocTmp.setPlacedtlid(gpcsPlaceReturnDtl.getPlacereturndtlid());
                bmsStIoDocTmp.setPlacetable(3);
                bmsStIoDocTmpMapper.insert(bmsStIoDocTmp);
                BmsStIoDtlTmp bmsStIoDtlTmp = new BmsStIoDtlTmp();
                bmsStIoDtlTmp.setInoutid(bmsStIoDocTmp.getInoutid());
                bmsStIoDtlTmp.setBatchid(bmsStQtyLst.getBatchid());
                bmsStIoDtlTmp.setLotid(bmsStQtyLst.getLotid());
                bmsStIoDtlTmp.setPosid(bmsStQtyLst.getPosid());
                bmsStIoDtlTmp.setGoodsdtlid((gpcsPlaceReturnDtl.getGoodsdtlid()));
                bmsStIoDtlTmp.setGoodsstatusid(bmsStQtyLst.getGoodsstatusid());
                bmsStIoDtlTmp.setDtlgoodsqty(currentQty);
                bmsStIoDtlTmpMapper.insert(bmsStIoDtlTmp);

            }
        }
        return gpcsPlaceReturn;
    }

    /**
     * 校验退单数据  销售
     * @param orderBackInfoDocVo
     */
    private void validBackDataSa(OrderBackInfoDocVo orderBackInfoDocVo) {

        orderBackInfoDocVo.getOrderInfoDtlList().forEach(orderBackInfoDtlVo -> {
            BmsSaDtl bmsSaDtl =  bmsSaDtlMapper.selectBySalesId(orderBackInfoDtlVo.getSrcErpOrderDtlId(),false);
            if(ObjectUtil.isEmpty(bmsSaDtl)){
                AssertExt.fail("销售细单："+orderBackInfoDtlVo.getSrcErpOrderDtlId()+"不存在");
            }
            if(bmsSaDtl.getGoodsqty()<orderBackInfoDtlVo.getNum()){
                AssertExt.fail("销售细单："+orderBackInfoDtlVo.getSrcErpOrderDtlId()+"数量不够");
            }
            if(ObjectUtil.notEqual(bmsSaDtl.getUnitprice()*orderBackInfoDtlVo.getNum(), orderBackInfoDtlVo.getAmount())){
                AssertExt.fail("销售细单："+orderBackInfoDtlVo.getSrcErpOrderDtlId()+"传递的金额不对"+
                                "退单金额："+orderBackInfoDtlVo.getAmount()+"退单数量："+orderBackInfoDtlVo.getNum()+"实际单价"+bmsSaDtl.getUnitprice());
            }
        });
    }


    /**
     * 校验退单数据  配送
     * @param orderBackInfoDocVo
     */
    private void validBackDataPs(OrderBackInfoDocVo orderBackInfoDocVo) {
        //Boolean psCustom = customService.isPsCustom(orderBackInfoDocVo.getCustomId(), orderBackInfoDocVo.getEntryId());
        /*查询每个门店的保管账*/
        Integer placepointStorageId = customService.getPlacepointStorageIdByCustomId(orderBackInfoDocVo.getCustomId());
        orderBackInfoDocVo.getOrderInfoDtlList().forEach(orderBackInfoDtlVo -> {
            BmsSaDtl bmsSaDtl =  bmsSaDtlMapper.selectBySalesId(orderBackInfoDtlVo.getSrcErpOrderDtlId(),true);
            if(ObjectUtil.isEmpty(bmsSaDtl)){
                AssertExt.fail( "配送细单："+orderBackInfoDtlVo.getSrcErpOrderDtlId()+"不存在");
            }
            if(bmsSaDtl.getGoodsqty()<orderBackInfoDtlVo.getNum()){
                AssertExt.fail("配送细单："+orderBackInfoDtlVo.getSrcErpOrderDtlId()+"数量不够");
            }
            if(ObjectUtil.notEqual(bmsSaDtl.getUnitprice()*orderBackInfoDtlVo.getNum(), orderBackInfoDtlVo.getAmount())){
                AssertExt.fail("配送细单："+orderBackInfoDtlVo.getSrcErpOrderDtlId()+"传递的金额不对"+
                        "退单金额："+orderBackInfoDtlVo.getAmount()+"退单数量："+orderBackInfoDtlVo.getNum()+"实际单价"+bmsSaDtl.getUnitprice());
            }

            AssertExt.notNull(orderBackInfoDtlVo.getGoodsDtlId(),"配送细单："+orderBackInfoDtlVo.getSrcErpOrderDtlId()+",商品细ID不能为空");
            AssertExt.notNull(orderBackInfoDtlVo.getLotId(),"配送细单："+orderBackInfoDtlVo.getSrcErpOrderDtlId()+",批号ID不能为空");
            Long canBackStock = bmsStQtyLstMapper.selectCanBackStock(placepointStorageId,orderBackInfoDtlVo.getGoodsDtlId(),orderBackInfoDtlVo.getLotId());
            if(canBackStock<orderBackInfoDtlVo.getNum()){
                AssertExt.fail("配送细单："+orderBackInfoDtlVo.getSrcErpOrderDtlId()+"可退数量："+canBackStock+
                        "小于申请的数量："+orderBackInfoDtlVo.getNum());
            }
        });

    }


    /**
     * 生产退货单
     *
     * @param orderBackInfoDocVo
     * @return
     */
    @Override
    public OrderResultDocVo genBackOrder(OrderBackInfoDocVo orderBackInfoDocVo) {

        /**
         * 测试------------------ 不能执行
         */
//        AssertExt.notNull(null, "参数对象为空");

        AssertExt.notNull(orderBackInfoDocVo, "对象不能为空");
        OrderResultDocVo resultVo = null;
        OrderResultDocVo result = null;
        Long customId = orderBackInfoDocVo.getCustomId();
        AssertExt.notNull("customId", "客户id不能为空");
        AssertExt.notNull(orderBackInfoDocVo.getOrderId(), "订单id不能为空");

        Long orderId = orderBackInfoDocVo.getOrderId();
        String orderNo = orderBackInfoDocVo.getOrderNo();


        RLock lock = redissonClient.getLock("order_back_gen:"+orderId);
        /**
         * 获取锁资源
         */
        lock.lock(2, TimeUnit.MINUTES);

        try {

            log.info("订单退单下发程序---------业务逻辑----------");

            QueryWrapper<B2bOrderBackList> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(B2bOrderBackList::getB2bOrderId, orderId);
            queryWrapper.lambda().eq(B2bOrderBackList::getVersion, orderBackInfoDocVo.getVersion());
            if (b2bOrderBackListMapper.selectCount(queryWrapper) == 0) {

                B2bOrderBackList b2bOrderList = new B2bOrderBackList();

                b2bOrderList.setB2bOrderNo(orderNo);
                b2bOrderList.setB2bOrderId(orderId);
                b2bOrderList.setB2bSrcOrderId(orderBackInfoDocVo.getSrcOrderId());
                b2bOrderList.setB2bSrcOrderNo(orderBackInfoDocVo.getSrcOrderNo());
                b2bOrderList.setVersion(orderBackInfoDocVo.getVersion());
                b2bOrderList.setCreateDate(LocalDateTime.now());

                b2bOrderList.setSrcData(JSON.toJSONString(orderBackInfoDocVo, SerializerFeature.PrettyFormat,
                        SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.UseSingleQuotes));

                b2bOrderBackListMapper.insert(b2bOrderList);

                if (customService.isPsCustom(orderBackInfoDocVo.getCustomId(), orderBackInfoDocVo.getEntryId())) {
                    result = OrderResultDocVo.failAppend(result, genBackOrderPs(orderBackInfoDocVo), orderNo, customId);

                } else {
                    result = OrderResultDocVo.failAppend(result, genBackOrderSa(orderBackInfoDocVo), orderNo, customId);

                }

            } else {
                AssertExt.fail("订单退单重复下发！！!!!!");
            }

        } catch (Throwable e) {
            e.printStackTrace();
            log.error("执行出现了异常!", e.getMessage());
            AssertExt.fail("执行出现了异常:" + e.getMessage(), e);
        } finally {
            log.info("订单退单下发程序---------解锁----------");
            lock.unlock();
        }

        //result = OrderResultDocVo.failAppend(result, receiptOrder(orderBackInfoDocVo), orderNo, customId);

       // result = OrderResultDocVo.failAppend(result, acceptanceOrder(orderBackInfoDocVo), orderNo, customId);

        return result;

    }

    /**
     * 验收订单
     *
     * @param orderBackInfoDocVo
     * @return
     */
    private OrderResultDocVo acceptanceOrder(OrderBackInfoDocVo orderBackInfoDocVo) {
        OrderResultDocVo result = null;
        String orderNo = orderBackInfoDocVo.getOrderNo();
        Long customId = orderBackInfoDocVo.getCustomId();
        if (customService.isPsCustom(orderBackInfoDocVo.getCustomId(), orderBackInfoDocVo.getEntryId())) {
            result = OrderResultDocVo.failAppend(result, acceptanceOrderPs(orderBackInfoDocVo), orderNo, customId);

        } else {
            result = OrderResultDocVo.failAppend(result, acceptanceOrderSa(orderBackInfoDocVo), orderNo, customId);
        }
        return result;
    }

    /**
     * 销售验收
     *
     * @param orderBackInfoDocVo
     * @return
     */
    @Override
    public OrderResultDocVo acceptanceOrderSa(OrderBackInfoDocVo orderBackInfoDocVo) {
        return null;
    }

    /**
     * 配送验收
     *
     * @param orderBackInfoDocVo
     * @return
     */
    @Override
    public OrderResultDocVo acceptanceOrderPs(OrderBackInfoDocVo orderBackInfoDocVo) {
        return null;
    }

    /**
     * 退货收货
     *
     * @param orderInfoDocVo
     * @return
     */
    @Override
    public OrderResultDocVo receiptOrder(OrderBackInfoDocVo orderInfoDocVo) {
        OrderResultDocVo result = null;
        String orderNo = orderInfoDocVo.getOrderNo();
        Long customId = orderInfoDocVo.getCustomId();
        if (customService.isPsCustom(orderInfoDocVo.getCustomId(), orderInfoDocVo.getEntryId())) {
            result = OrderResultDocVo.failAppend(result, receiptOrderPs(orderInfoDocVo), orderNo, customId);

        } else {
            result = OrderResultDocVo.failAppend(result, receiptOrderSa(orderInfoDocVo), orderNo, customId);
        }
        return result;
    }

    /**
     * 配送收货
     *
     * @param orderInfoDocVo
     * @return
     */
    @Override
    public OrderResultDocVo receiptOrderPs(OrderBackInfoDocVo orderInfoDocVo) {
        Long orderId = orderInfoDocVo.getOrderId();
        GpcsPlaceReturn gpcsPlaceReturn = gpcsPlaceReturnMapper.getByB2bOrderId(orderId);
        AssertExt.notNull("GpcsPlaceReturn", "对象不能为空");
        Long placereturnId = gpcsPlaceReturn.getPlacereturnid();
        List<GpcsPlaceReturnDtl> gpcsPlaceReturnDtls = gpcsPlaceReturnDtlMapper.selectBy(placereturnId);
        BmsStReDoc bmsStReDoc = genBmsStReDoc(orderInfoDocVo);
        Long redocId = bmsStReDoc.getRedocid();
        if (ObjectUtil.isNotEmpty(gpcsPlaceReturnDtls)) {
            gpcsPlaceReturnDtls.forEach(gpcsPlaceReturnDtl -> {
                gpcsPlaceReturnDtl.setRedocid(redocId);
                //gpcsPlaceReturnDtl.setApproveManid(orderInfoDocVo.getApproveManId());
                BmsStReDtl bmsStReDtl = genBmsStReDtl(gpcsPlaceReturnDtl);
                Long lotId = gpcsPlaceReturnDtl.getLotid();
                Date proddate = gpcsPlaceReturnDtl.getProddate();
                Date invaliddate = gpcsPlaceReturnDtl.getInvaliddate();
                bmsLotDefMapper.updateBy(proddate, invaliddate, lotId);
                gpcsPlaceReturnDtl.setRedtlid(bmsStReDtl.getRedtlid());
                BmsStReTiny bmsStReTiny = genBmsStReTiny(gpcsPlaceReturnDtl);
                gpcsPlaceReturnDtl.setCustomid(orderInfoDocVo.getCustomId());
                BmsStRgDoc bmsStRgDoc = genBmsStRgDoc(gpcsPlaceReturnDtl);
                gpcsPlaceReturnDtl.setRetinyid(bmsStReTiny.getRetinyid());
                gpcsPlaceReturnDtl.setRgid(bmsStRgDoc.getRgid());
                BmsStRgDtl bmsStRgDtl = genBmsStRgDtl(gpcsPlaceReturnDtl);
                bmsStReDtlMapper.updateBy(orderInfoDocVo.getApproveManId(), bmsStReDtl.getRedtlid());
                Integer usestatus = 5;
                gpcsPlaceReturnDtlMapper.updateBy(usestatus, gpcsPlaceReturnDtl.getPlacereturndtlid());
                BmsStReceiveRecord bmsStReceiveRecord = new BmsStReceiveRecord();
                bmsStReceiveRecord.setUsestatus(1);
                bmsStReceiveRecord.setReid(redocId);
                bmsStReceiveRecord.setCredate(LocalDateTime.now());
                bmsStReceiveRecord.setInputmanid(gpcsPlaceReturnDtl.getApprovemanid());
                bmsStReceiveRecord.setCompanyid(orderInfoDocVo.getCustomId());
                bmsStReceiveRecord.setStorerid((long) 7368);
                bmsStReceiveRecord.setRetype(3);
                bmsStReceiveRecord.setEntryid((long) 1);
                bmsStReceiveRecord.setRedtlid(bmsStReDtl.getRedtlid());
                bmsStReceiveRecord.setStorageid((long) gpcsPlaceReturnDtl.getStorageid());
                bmsStReceiveRecord.setGoodsid(gpcsPlaceReturnDtl.getGoodsid());
                bmsStReceiveRecord.setGoodsqty(gpcsPlaceReturnDtl.getGoodsqty());
                bmsStReceiveRecord.setGoodsdtlid(gpcsPlaceReturnDtl.getGoodsdtlid());
                bmsStReceiveRecord.setLotid(gpcsPlaceReturnDtl.getLotid());
                bmsStReceiveRecord.setPackquality(1);
                bmsStReceiveRecord.setFacequality(1);
                bmsStReceiveRecord.setUnitprice(gpcsPlaceReturnDtl.getPlaceprice());
                bmsStReceiveRecord.setSucongoodsqty(gpcsPlaceReturnDtl.getGoodsqty());
                bmsStReceiveRecord.setSalerid(gpcsPlaceReturnDtl.getApprovemanid());
                bmsStReceiveRecord.setScatterqty(gpcsPlaceReturnDtl.getGoodsqty());
                bmsStReceiveRecord.setRecieveqty(gpcsPlaceReturnDtl.getGoodsqty());
                Long approvemanId = gpcsPlaceReturnDtl.getApprovemanid();
                bmsStRgDocMapper.updateByRgid(approvemanId, bmsStRgDoc.getRgid());
                GspPhysicQualityCheck gspPhysicQualityCheck = new GspPhysicQualityCheck();

                gspPhysicQualityCheck.setGoodsid(gpcsPlaceReturnDtl.getGoodsid());
                gspPhysicQualityCheck.setCredate(LocalDateTime.now());
                gspPhysicQualityCheck.setGoodsqty(gpcsPlaceReturnDtl.getGoodsqty());
                gspPhysicQualityCheck.setGoodsunit(gpcsPlaceReturnDtl.getGoodsunit());
                gspPhysicQualityCheck.setCompanyid(orderInfoDocVo.getCustomId());
                gspPhysicQualityCheck.setLotid(gpcsPlaceReturnDtl.getLotid());
                gspPhysicQualityCheck.setCertifiedflag(1);
                gspPhysicQualityCheck.setCheckmanid(gpcsPlaceReturnDtl.getApprovemanid());
                gspPhysicQualityCheck.setArrivedate(LocalDateTime.now());
                gspPhysicQualityCheck.setFacequality(1);
                gspPhysicQualityCheck.setPackquality(1);
                gspPhysicQualityCheck.setRgtype(3);
                gspPhysicQualityCheck.setQucheckid((long) 23);
                gspPhysicQualityCheck.setGenlqchkflag(0);
                gspPhysicQualityCheck.setSourceid(bmsStRgDtl.getRgdtlid());
                gspPhysicQualityCheck.setComefrom(1);
                gspPhysicQualityCheck.setCheckdate(LocalDateTime.now());
                gspPhysicQualityCheck.setEntryid((long) 1);
                gspPhysicQualityCheck.setUsestatus(1);
                bmsStRgDocMapper.updateBy(bmsStRgDoc.getRgid());
                usestatus = 4;
                gpcsPlaceReturnDtlMapper.updateBy(usestatus, gpcsPlaceReturnDtl.getPlacereturndtlid());
                Long inoutid = bmsStIoDocTmpMapper.getSourceid(gpcsPlaceReturnDtl.getPlacereturndtlid());
                bmsStIoDocTmpMapper.updateOutqty(gpcsPlaceReturnDtl.getGoodsqty(), inoutid);
                bmsStIoDtlTmpMapper.updateDtlGoodsQty(gpcsPlaceReturnDtl.getGoodsqty(), inoutid);
                Integer usemm = bmsStIoDocTmpMapper.getUsemm();
                BmsStSumQty bmsStSumQty = new BmsStSumQty();
                bmsStSumQty.setUsemm((long) usemm);
                bmsStSumQty.setStorageid((long) 53);
                bmsStSumQty.setGoodsid(gpcsPlaceReturnDtl.getGoodsid());
                bmsStSumQtyMapper.insert(bmsStSumQty);
            });
            bmsStReDocMapper.updateByRedocid(redocId);
        }
        return null;
    }

    /**
     * 验收细单
     *
     * @param gpcsPlaceReturnDtl
     * @return
     */
    private BmsStRgDtl genBmsStRgDtl(GpcsPlaceReturnDtl gpcsPlaceReturnDtl) {

        BmsStRgDtl bmsStRgDtl = new BmsStRgDtl();
        bmsStRgDtl.setRgid(gpcsPlaceReturnDtl.getRgid());
        bmsStRgDtl.setGoodsdtlid(gpcsPlaceReturnDtl.getGoodsdtlid());
        bmsStRgDtl.setGoodsqty(gpcsPlaceReturnDtl.getGoodsqty());
        bmsStRgDtl.setScatterqty(gpcsPlaceReturnDtl.getGoodsqty());
        bmsStRgDtl.setRegoodsqty(gpcsPlaceReturnDtl.getGoodsqty());
        bmsStRgDtl.setLotid(gpcsPlaceReturnDtl.getLotid());
        bmsStRgDtl.setBatchid(gpcsPlaceReturnDtl.getBatchid());
        bmsStRgDtl.setPackquality(1);
        bmsStRgDtl.setFacequality(1);
        bmsStRgDtl.setRggoodsqty(gpcsPlaceReturnDtl.getGoodsqty());
        bmsStRgDtl.setRetinyid(gpcsPlaceReturnDtl.getRetinyid());
        bmsStRgDtl.setRecheckstatus(0);
        bmsStRgDtl.setZxSendwmsflag(1);
        bmsStRgDtlMapper.insert(bmsStRgDtl);
        return bmsStRgDtl;
    }

    /**
     * 收货单
     *
     * @param gpcsPlaceReturnDtl
     * @return
     */
    private BmsStRgDoc genBmsStRgDoc(GpcsPlaceReturnDtl gpcsPlaceReturnDtl) {
        BmsStRgDoc bmsStRgDoc = new BmsStRgDoc();
        bmsStRgDoc.setRgtype(3);
        bmsStRgDoc.setGoodsid(gpcsPlaceReturnDtl.getGoodsid());
        bmsStRgDoc.setQualifiedqty(gpcsPlaceReturnDtl.getGoodsqty());
        bmsStRgDoc.setRequalifiedqty(gpcsPlaceReturnDtl.getGoodsqty());
        bmsStRgDoc.setRegoodsqty(gpcsPlaceReturnDtl.getGoodsqty());
        bmsStRgDoc.setSalerid(gpcsPlaceReturnDtl.getApprovemanid());
        bmsStRgDoc.setUnitprice(gpcsPlaceReturnDtl.getResaleprice());
        bmsStRgDoc.setTotalline(gpcsPlaceReturnDtl.getTotalLine());
        bmsStRgDoc.setUsestatus(1);

        bmsStRgDoc.setCompanyid(gpcsPlaceReturnDtl.getCustomid());
        bmsStRgDoc.setStorerid((long) 7368);
        bmsStRgDoc.setStorageid((long) gpcsPlaceReturnDtl.getStorageid());
        bmsStRgDoc.setEntryid(1);
        bmsStRgDoc.setRealprice(gpcsPlaceReturnDtl.getTotalLine());
        bmsStRgDoc.setRealmoney(gpcsPlaceReturnDtl.getTotalLine());
        return bmsStRgDoc;
    }

    /**
     * 收货单明细
     *
     * @param gpcsPlaceReturnDtl
     * @return
     */
    private BmsStReTiny genBmsStReTiny(GpcsPlaceReturnDtl gpcsPlaceReturnDtl) {
        BmsStReTiny bmsStReTiny = new BmsStReTiny();
        bmsStReTiny.setRedtlid(gpcsPlaceReturnDtl.getRedtlid());
        bmsStReTiny.setGoodsdtlid(gpcsPlaceReturnDtl.getGoodsdtlid());
        bmsStReTiny.setScatterqty(gpcsPlaceReturnDtl.getGoodsqty());
        bmsStReTiny.setLotid(gpcsPlaceReturnDtl.getLotid());
        bmsStReTiny.setFacequality(1);
        bmsStReTiny.setPackquality(1);
        bmsStReTiny.setGoodsqtyLine(gpcsPlaceReturnDtl.getTotalLine());
        bmsStReTiny.setBatchid(gpcsPlaceReturnDtl.getBatchid());
        bmsStReTiny.setZxGoodsstatusid((long) 1);
        bmsStReTiny.setGoodsqty(gpcsPlaceReturnDtl.getGoodsqty());
        bmsStReTinyMapper.insert(bmsStReTiny);
        return bmsStReTiny;
    }

    /**
     * 收货细单
     *
     * @param gpcsPlaceReturnDtl
     * @return
     */
    private BmsStReDtl genBmsStReDtl(GpcsPlaceReturnDtl gpcsPlaceReturnDtl) {
        BmsStReDtl bmsStReDtl = new BmsStReDtl();
        bmsStReDtl.setStorageid((long) gpcsPlaceReturnDtl.getStorageid());
        bmsStReDtl.setPackfalg(1);
        bmsStReDtl.setUnpackfalg(1);
        bmsStReDtl.setGoodsid(gpcsPlaceReturnDtl.getGoodsid());
        bmsStReDtl.setOldgoodsqty(gpcsPlaceReturnDtl.getGoodsqty());
        bmsStReDtl.setCangoodsqty(gpcsPlaceReturnDtl.getGoodsqty());
        bmsStReDtl.setGoodsqty(gpcsPlaceReturnDtl.getGoodsqty());
        bmsStReDtl.setQualifiedqty(gpcsPlaceReturnDtl.getGoodsqty());
        bmsStReDtl.setUnitprice(gpcsPlaceReturnDtl.getResaleprice());
        bmsStReDtl.setTotalLine(gpcsPlaceReturnDtl.getTotalLine());
        bmsStReDtl.setUsestatus(1);
        //bmsStReDtl.setSalerid(gpcsPlaceReturnDtl.getApproveManid());
        bmsStReDtl.setPlacereturndtlid(gpcsPlaceReturnDtl.getPlacereturndtlid());
        bmsStReDtl.setRedocid(gpcsPlaceReturnDtl.getRedocid());
        bmsStReDtl.setRealprice(gpcsPlaceReturnDtl.getResaleprice());
        bmsStReDtl.setRealmoney(gpcsPlaceReturnDtl.getTotalLine());
        bmsStReDtl.setRealqty(gpcsPlaceReturnDtl.getGoodsqty());
        bmsStReDtlMapper.insert(bmsStReDtl);
        return bmsStReDtl;
    }

    /**
     * 生成收货总单
     *
     * @param orderInfoDocVo
     * @return
     */
    private BmsStReDoc genBmsStReDoc(OrderBackInfoDocVo orderInfoDocVo) {
        BmsStReDoc bmsStReDoc = new BmsStReDoc();
        bmsStReDoc.setRetype(3);
        bmsStReDoc.setCredate(new Date());
        bmsStReDoc.setTwoinvoiceinflag(0);
        bmsStReDoc.setUsestatus(0);
        bmsStReDoc.setInputmanid(orderInfoDocVo.getApproveManId());
        bmsStReDoc.setEntryid((long) orderInfoDocVo.getEntryId());
        bmsStReDoc.setCompanyid(orderInfoDocVo.getCustomId());
        bmsStReDoc.setStorerid((long) 7368);
        bmsStReDoc.setB2bOrderId(orderInfoDocVo.getOrderId());
        bmsStReDoc.setB2bOrderNo(orderInfoDocVo.getOrderNo());
        bmsStReDoc.setZxBhFlag(11);
        bmsStReDocMapper.insert(bmsStReDoc);
        return bmsStReDoc;
    }

    /**
     * 销售收货
     *
     * @param orderInfoDocVo
     * @return
     */
    @Override
    public OrderResultDocVo receiptOrderSa(OrderBackInfoDocVo orderInfoDocVo) {
        Long orderId = orderInfoDocVo.getSrcOrderId();
        BmsTrFetchDoc bmsTrFetchDoc=bmsTrFetchDocMapper.selectByOrderId(orderId);
        AssertExt.notNull("bmsTrFetchDoc", "对象不能为空");
        Long fetchId = bmsTrFetchDoc.getFetchid();
        List<BmsTrFetchDtl> bmsTrFetchDtls = bmsTrFetchDtlMapper.selectBy(fetchId);
        BmsStReDoc bmsStReDoc = genBmsStReDoc(orderInfoDocVo);
        if (ObjectUtil.isNotEmpty(bmsTrFetchDtls)) {
            bmsTrFetchDtls.forEach(bmsTrFetchDtl->{

            });
        }

        return null;
    }

    @Override
    public OrderResultDocVo genBackOrderSa(OrderBackInfoDocVo orderBackInfoDocVo) {
        AssertExt.notNull("orderBackInfoDocVo", "对象不能为空");
        OrderResultDocVo resultVo = null;
        ResultVo result = null;
        Long customId = orderBackInfoDocVo.getCustomId();
        AssertExt.notNull("customId", "客户id不能为空");
        Long orderId = orderBackInfoDocVo.getOrderId();
        String orderNo = orderBackInfoDocVo.getOrderNo();

        Long srcOrderId = orderBackInfoDocVo.getSrcOrderId();
        String srcOrderNo = orderBackInfoDocVo.getSrcOrderNo();


        try {
            /**
             * 根据保管账的拆弹标记 ，拆分订单
             */
            Collection<OrderBackInfoDocVo> orderList = splitOrder(orderBackInfoDocVo);
            int count = 0;
            int row = 0;
            List<Long> ids = new ArrayList<Long>();
            for (OrderBackInfoDocVo infoDocVo : orderList) {
                BmsTrFetchDoc bmsTrFetchDoc = customService.getBmsTrFetchByCustomId(customId);

                bmsTrFetchDoc.setMemo("B2B退单号：" + infoDocVo.getOrderNo()+"，源订单号:"+infoDocVo.getSrcOrderNo()+",退货备注:"+infoDocVo.getRemark());

                Long tranposId = customService.selectTranposIdBy(customId);
                bmsTrFetchDoc.setCompanyid(customId);
                bmsTrFetchDoc.setTrposid(tranposId);
                bmsTrFetchDoc.setB2bOrderId(orderId);
                bmsTrFetchDoc.setB2bOrderNo(orderNo);


                bmsTrFetchDoc.setB2bSrcOrderId(srcOrderId);
                bmsTrFetchDoc.setB2bSrcOrderNo(srcOrderNo);
                //bmsTrFetchDoc.setB2bnotwriteback(1);
                bmsTrFetchDoc.setEntryid( 1);
                bmsTrFetchDoc.setStorageid(1);
                bmsTrFetchDoc.setComefrom(4);
                bmsTrFetchDoc.setZxBhFlag(11);
                bmsTrFetchDoc.setCredate(LocalDateTime.now());
                bmsTrFetchDoc.setInputmanid(infoDocVo.getApproveManId());
                bmsTrFetchDoc.setSalerid(infoDocVo.getApproveManId());
                bmsTrFetchDoc.setSalesdeptid((long) 7628);
                bmsTrFetchDoc.setStorerid(infoDocVo.getStorerId());
                bmsTrFetchDoc.setZxTotal(infoDocVo.getAmountTotal());
                bmsTrFetchDoc.setPlaninvdate(LocalDateTime.now());
                bmsTrFetchDoc.setConfirmdate(LocalDateTime.now());
                bmsTrFetchDoc.setConfirmmanid(infoDocVo.getApproveManId());
                bmsTrFetchDoc.setInvtype(bmsTrFetchDoc.getInvtype());



                bmsTrFetchDocMapper.insert(bmsTrFetchDoc);
                ids.add(bmsTrFetchDoc.getFetchid());
                for (OrderBackInfoDtlVo orderBackInfo : infoDocVo.getOrderInfoDtlList()) {
                    Long salesId = orderBackInfo.getSrcErpOrderId();
                    Long lotId = orderBackInfo.getLotId();
                    Long batchId = orderBackInfo.getBatchId();
                    double num=orderBackInfo.getNum();
                    /*验证是否存在此销售单*/
                    count = orderService.getCountBy(salesId, srcOrderId);
                    if (count == 0) {
                        bmsTrFetchDocMapper.deleteById(bmsTrFetchDoc.getFetchid());
                        //result = ResultVo.failErrorMessageAppend(result, "订单号:" + orderId + "不存在");
                        AssertExt.fail("订单号:" + srcOrderId + "不存在");
                    }
                    BmsTrFetchDtl bmsTrFetchDtl = bmsTrFetchDtlMapper.selectDtlBy(salesId, lotId, batchId, customId);
                    int i = 0;
                    if (!ObjectUtils.isEmpty(bmsTrFetchDtl)) {
                        row = i + 1;
                        bmsTrFetchDtl.setFetchid(bmsTrFetchDoc.getFetchid());
                        bmsTrFetchDtl.setZxSalesdocid(salesId);
                        bmsTrFetchDtl.setSaledtlid(orderBackInfo.getSrcErpOrderDtlId());
                        bmsTrFetchDtl.setUsestatus(2);
                        /*退货原因id*/
                        bmsTrFetchDtl.setBackwhyid(orderBackInfo.getReason());
                        bmsTrFetchDtl.setBatchflag(1);
                        bmsTrFetchDtl.setGoodsqty(num);
                        bmsTrFetchDtlMapper.insert(bmsTrFetchDtl);
                        BmsFetchToSaio bmsFetchToSaio =new BmsFetchToSaio();
                        bmsFetchToSaio.setFetchdtlid(bmsTrFetchDtl.getFetchdtlid());
                        bmsFetchToSaio.setIodtlid(bmsTrFetchDtl.getIodtlid());
                        bmsFetchToSaio.setGoodsqty(bmsTrFetchDtl.getGoodsqty());
                        bmsFetchToSaio.setSalesdtlid(bmsTrFetchDtl.getSaledtlid());
                        bmsFetchToSaioMapper.insert(bmsFetchToSaio);
                    } else {
                        //result = ResultVo.failErrorMessageAppend(result, "订单号:" + salesId + "不存在");
                        AssertExt.fail("订单号:" + salesId + "不存在");
                    }


                }
            }

            if (count == row) {
                result = ResultVo.validReturn(result);
            } else {
                /*删除不成功的订单*/
                for (int y = 0; y < ids.size(); y++) {
                    Long fetchid = ids.get(y);
                    bmsTrFetchDocMapper.deleteById(fetchid);
                    bmsTrFetchDtlMapper.deleteById(fetchid);
                }
                //result = ResultVo.failErrorMessageAppend(result, "订单号:" + orderId + "不存在");

                AssertExt.fail("订单号:" + srcOrderId + "不存在");
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return OrderResultDocVo.validReturn(resultVo, orderNo, customId);
    }

    private Collection<OrderBackInfoDocVo> splitOrder(OrderBackInfoDocVo orderBackInfoDocVo) {
        Map<Integer,  OrderBackInfoDocVo > map = new HashMap<>();
        orderBackInfoDocVo.getOrderInfoDtlList().stream().forEach(orderBackInfoDtlVo -> {
            OrderBackInfoDocVo orderBackInfoDocVo1 = map.get(orderBackInfoDtlVo.getStorageId());
            if (ObjectUtil.isNull(orderBackInfoDocVo1)) {
                orderBackInfoDocVo1 = new OrderBackInfoDocVo();
                BeanUtil.copyProperties(orderBackInfoDocVo,orderBackInfoDocVo1);
                orderBackInfoDocVo1.setOrderInfoDtlList(new ArrayList<>());
                orderBackInfoDocVo1.setStoragetId(orderBackInfoDtlVo.getStorageId());
                map.put(orderBackInfoDtlVo.getStorageId(),orderBackInfoDocVo1);
            }
            orderBackInfoDocVo1.getOrderInfoDtlList().add(orderBackInfoDtlVo);

        });
        return  map.values().stream().map(orderBackInfoDocVo1 -> {
            double x = orderBackInfoDocVo1.getOrderInfoDtlList().stream().map(orderBackInfoDtlVo -> {
                return orderBackInfoDtlVo.getAmount();
            }).reduce(0.0, NumberUtil::add);
            orderBackInfoDocVo1.setAmountTotal(x);
            return orderBackInfoDocVo1;
        }).collect(Collectors.toList());

    }


    @Override
    public OrderResultDocVo genBackOrderPs(OrderBackInfoDocVo orderBackInfoDocVo) {
        AssertExt.notNull(orderBackInfoDocVo, "不能为空");
        OrderResultDocVo resultVo = null;
        ResultVo result = null;
        List<GpcsPlaceIds> ids = new ArrayList<GpcsPlaceIds>();
        Long customId = orderBackInfoDocVo.getCustomId();
        String orderNo = orderBackInfoDocVo.getOrderNo();
        Long placereturnid=null;
        try {
            GpcsPlaceReturn gpcsPlaceReturn = new GpcsPlaceReturn();
            gpcsPlaceReturn.setB2bOrderId(orderBackInfoDocVo.getOrderId());
            gpcsPlaceReturn.setB2bOrderNo(orderBackInfoDocVo.getOrderNo());

            gpcsPlaceReturn.setB2bSrcOrderId(orderBackInfoDocVo.getSrcOrderId());
            gpcsPlaceReturn.setB2bSrcOrderNo(orderBackInfoDocVo.getSrcOrderNo());
            gpcsPlaceReturn.setZxBhFlag(11);
            gpcsPlaceReturn.setPlacepointid(customId);
            gpcsPlaceReturn.setPlacecenterid(1);
            //gpcsPlaceReturn.setPlacemandid("14082");
            gpcsPlaceReturn.setInputflag(1);
            gpcsPlaceReturn.setApprovestatusdoc(4);
            gpcsPlaceReturn.setPLACEDATE(LocalDateTime.now());
            gpcsPlaceReturn.setB2bnotwriteback(1);

            gpcsPlaceReturn.setMemo("B2B退单号：" + orderBackInfoDocVo.getOrderNo()+"，源订单号:"+orderBackInfoDocVo.getSrcOrderNo()+",退货备注:"+orderBackInfoDocVo.getRemark());
            /*核销加入的字段*/

            gpcsPlaceReturnMapper.insert(gpcsPlaceReturn);
            placereturnid=gpcsPlaceReturn.getPlacereturnid();
            for (OrderBackInfoDtlVo orderBackInfoDtlVo : orderBackInfoDocVo.getOrderInfoDtlList()) {

                Long goodsId = orderBackInfoDtlVo.getGoodsId();
                Double goodQty = orderBackInfoDtlVo.getNum();
                Long lotId = orderBackInfoDtlVo.getLotId() ;
                Long batchId = orderBackInfoDtlVo.getBatchId()  ;
                Double unitprice=null;
                if(orderBackInfoDtlVo.getPriceDiscount()>0){
                    unitprice =orderBackInfoDtlVo.getPriceDiscount();
                }else{
                    unitprice=orderBackInfoDtlVo.getPrice();
                }


                AssertExt.notNull(goodsId,"商品ID不能为空");
                AssertExt.notNull(lotId,"批号ID不能为空");
                AssertExt.notNull(batchId,"批次ID不能为空");


                /*查询每个门店的保管账*/
                Integer storageId = customService.getPlacepointStorageIdByCustomId(customId);
                AssertExt.notNull(storageId, "保管账ID不能为空");
                /*查询当前门店的可销库存*/
                List<GpcsPlaceReturnDtl> gpcsPlaceReturnDtls = goodsService.selectCanBackQtyBy(goodsId, lotId,storageId);
                double totalQty = 0;
                for (int i = 0; totalQty < goodQty && i < gpcsPlaceReturnDtls.size(); i++) {
                    double currentQty = 0.0;
                    GpcsPlaceReturnDtl gpcsPlaceReturnDtl = gpcsPlaceReturnDtls.get(i);
                    Double qtyFact = gpcsPlaceReturnDtl.getGoodsqty();
                    /**
                     * 如果当前没有库存了
                     */
                    if (qtyFact <= 0) {
                        result = ResultVo.failErrorMessageAppend(result, "商品id:" + goodsId + "库存不足");
                        break;
                    }
                    /**
                     *
                     * 当前批次数量不够
                     */
                    if (qtyFact < goodQty - totalQty) {
                        totalQty += qtyFact;
                        currentQty = qtyFact;
                    } else {
                        currentQty = goodQty - totalQty;
                        totalQty = goodQty;
                    }

                        GpcsPlaceIds id = new GpcsPlaceIds();
                        //Double unitprice = goodsService.selectPriceBy(batchId);
                        Double resaleprice = goodsService.selectResalePriceBy(goodsId, customId);
                        gpcsPlaceReturnDtl.setPlacereturnid(gpcsPlaceReturn.getPlacereturnid());
                        gpcsPlaceReturnDtl.setGoodsqty(currentQty);
                        gpcsPlaceReturnDtl.setApprovestatus(3);
                        gpcsPlaceReturnDtl.setResaleprice(resaleprice);
                        gpcsPlaceReturnDtl.setBackwhyid(2);

                        gpcsPlaceReturnDtl.setPlacemoney(currentQty * unitprice);
                        gpcsPlaceReturnDtl.setCansaleqty(qtyFact);

                        gpcsPlaceReturnDtl.setPlaceprice(unitprice);
                        gpcsPlaceReturnDtl.setPlacepriceid(1);
                        gpcsPlaceReturnDtl.setUsestatus("3");
                        gpcsPlaceReturnDtl.setApprovedate(LocalDateTime.now());
                        gpcsPlaceReturnDtl.setSendwmsflag(0);
                        gpcsPlaceReturnDtl.setB2bOrderDtlId(orderBackInfoDtlVo.getOrderDtlId());
                        gpcsPlaceReturnDtl.setB2bOrderId(orderBackInfoDtlVo.getOrderId());
                        gpcsPlaceReturnDtl.setB2bOrderNo(orderBackInfoDtlVo.getOrderNo());
                        gpcsPlaceReturnDtl.setB2bSrcOrderId(orderBackInfoDtlVo.getSrcOrderId());
                        gpcsPlaceReturnDtl.setB2bSrcOrderNo(orderBackInfoDtlVo.getSrcOrderNo());
                        gpcsPlaceReturnDtl.setB2bSrcOrderDtlId(orderBackInfoDtlVo.getSrcOrderDtlId());
                        gpcsPlaceReturnDtl.setB2bSrcErpOrderDtlId(orderBackInfoDtlVo.getSrcErpOrderDtlId());
                        gpcsPlaceReturnDtl.setB2bSrcErpOrderId(orderBackInfoDtlVo.getSrcErpOrderId());
                        gpcsPlaceReturnDtlMapper.insert(gpcsPlaceReturnDtl);
                        id.setPlacereturndtlid(gpcsPlaceReturnDtl.getPlacereturndtlid());
                        BmsStIoDocTmp bmsStIoDocTmp = new BmsStIoDocTmp();
                        bmsStIoDocTmp.setCredate(LocalDateTime.now());
                        bmsStIoDocTmp.setComefrom(77);
                        bmsStIoDocTmp.setSourcetable(77);
                        bmsStIoDocTmp.setSourceid(gpcsPlaceReturnDtl.getPlacereturndtlid());
                        bmsStIoDocTmp.setCompanyid((long) 1);
                        bmsStIoDocTmp.setCompanyname("广东中天医药有限公司");
                        bmsStIoDocTmp.setGoodsid(goodsId);
                        bmsStIoDocTmp.setStorageid(storageId.intValue());
                        bmsStIoDocTmp.setInoutflag(0);
                        bmsStIoDocTmp.setOutqty(currentQty);
                        bmsStIoDocTmp.setOldqty(currentQty);
                        bmsStIoDocTmp.setGoodsunit(gpcsPlaceReturnDtl.getGoodsunit());
                        bmsStIoDocTmp.setUsestatus(1);
                        bmsStIoDocTmp.setEntryid(3);
                        bmsStIoDocTmp.setPlacedtlid(gpcsPlaceReturnDtl.getPlacereturndtlid());
                        bmsStIoDocTmp.setPlacetable(3);
                        bmsStIoDocTmpMapper.insert(bmsStIoDocTmp);

                        id.setInoutid(bmsStIoDocTmp.getInoutid());
                        BmsStIoDtlTmp bmsStIoDtlTmp = new BmsStIoDtlTmp();
                        bmsStIoDtlTmp.setInoutid(bmsStIoDocTmp.getInoutid());
                        bmsStIoDtlTmp.setBatchid(gpcsPlaceReturnDtl.getBatchid());
                        bmsStIoDtlTmp.setLotid(gpcsPlaceReturnDtl.getLotid());
                        bmsStIoDtlTmp.setPosid(gpcsPlaceReturnDtl.getPosid());
                        bmsStIoDtlTmp.setGoodsdtlid((gpcsPlaceReturnDtl.getGoodsdtlid()));
                        bmsStIoDtlTmp.setGoodsstatusid(gpcsPlaceReturnDtl.getGoodsstatusid());
                        bmsStIoDtlTmp.setDtlgoodsqty(currentQty);
                        bmsStIoDtlTmpMapper.insert(bmsStIoDtlTmp);
                        id.setIodtlid(bmsStIoDtlTmp.getIodtlid());
                }
            }
            int row = orderBackInfoDocVo.getOrderInfoDtlList().size();
            int count = gpcsPlaceReturnDtlMapper.getCountBy(gpcsPlaceReturn.getPlacereturnid());
            if (count >= row) {
                result = ResultVo.validReturn(result);
            } else {
                /*删除不成功的单*/

                for (int y = 0; y < ids.size(); y++) {
                    GpcsPlaceIds GpcsPlaceIds = ids.get(y);
                    Long inoutid = GpcsPlaceIds.getInoutid();
                    Long iodtlid = GpcsPlaceIds.getIodtlid();
                    Long placereturndtlid = GpcsPlaceIds.getPlacereturndtlid();
                    bmsStIoDtlTmpMapper.deleteById(iodtlid);
                    bmsStIoDocTmpMapper.deleteById(inoutid);
                    gpcsPlaceReturnDtlMapper.deleteById(placereturndtlid);
                }
                gpcsPlaceReturnMapper.deleteById(gpcsPlaceReturn.getPlacereturnid());
                AssertExt.fail("生成配送退订单失败"+result);
            }
        } catch (Exception e) {
            gpcsPlaceReturnMapper.deleteById(placereturnid);
            AssertExt.fail("生成配送退订单失败"+e.getMessage());
            log.error(e.getMessage());
        }
        return OrderResultDocVo.validReturn(resultVo, orderNo, customId);
    }

    @Override
    public List<String> selectOrderPSNotCallback(int b2bShopFlag, int approveStatusDoc, int b2bNotWriteBack) {
        return gpcsPlaceReturnMapper.selectOrderPSNotCallback(b2bShopFlag, approveStatusDoc, b2bNotWriteBack);
    }

    @Override
    public void updatePSCallbackStatus(List<String> b2bOrderIds, int b2bNotWriteBack) {
        gpcsPlaceReturnMapper.updatePSCallbackStatus(b2bOrderIds, b2bNotWriteBack);
    }

}
