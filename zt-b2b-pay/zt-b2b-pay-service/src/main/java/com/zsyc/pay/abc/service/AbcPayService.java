package com.zsyc.pay.abc.service;

import com.abc.pay.client.JSON;
import com.abc.pay.client.TrxException;
import com.abc.pay.client.TrxRequest;
import com.abc.pay.client.ebus.*;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.pay.abc.config.AbcPayProperties;
import com.zsyc.pay.entity.PayOrder;
import com.zsyc.pay.service.InnerCallbackService;
import com.zsyc.pay.service.PayOrderService;
import com.zsyc.pay.vo.AbcRefundVo;
import com.zsyc.pay.vo.PayOrderVo;
import com.zsyc.pay.vo.PayWithdrawVo;
import com.zsyc.zt.b2b.service.SettingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.SignStyle;
import java.util.*;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static java.time.temporal.ChronoField.*;

/**
 * Created by lcs on 2020/10/15.
 */
@Service
//@org.springframework.stereotype.Service
@Slf4j
public class AbcPayService implements PayOrderService<PayOrderVo> {
    private final static String CURRENCY_CODE_156 = "156";
    private final static String COMMODITY_TYPE_0201 = "0201";
    private final static String NOTIFY_TYPE = "1";
    private final static String PaymentLinkType = "1";
    private final static String PAYMENT_TYPE_8 = "8";
    private final static String IS_BREAK_ACCOUNT = "0";
    private final static String SUCCESS_CODE = "0000";
    private final static String DARA_SOURCE_PC = "1";
    private final static String DARA_SOURCE_PC_MONTH = "6";
    private final static BigDecimal num_100 = BigDecimal.valueOf(100L);
    @Autowired(required = false)
    private InnerCallbackService innerCallbackService;
    @Autowired
    private AbcPayProperties abcPayProperties;
    @Autowired
    private SettingService settingService;
    /**
     * ????????????
     * yyyymmddhhMMss
     */
    public static final DateTimeFormatter LOCAL_DATE_TIME = new DateTimeFormatterBuilder()
            .appendValue(YEAR, 4, 10, SignStyle.EXCEEDS_PAD)
            .appendValue(MONTH_OF_YEAR, 2)
            .appendValue(DAY_OF_MONTH, 2)
            .appendValue(HOUR_OF_DAY, 2)
            .appendValue(MINUTE_OF_HOUR, 2)
            .appendValue(SECOND_OF_MINUTE, 2).toFormatter();

    @Override
    public PayOrderVo payOrder(PayOrderVo payOrder) {
        AssertExt.notBlank(payOrder.getOrderNo(), "??????????????????");
        AssertExt.notBlank(payOrder.getDataSource(), "??????????????????");
        AssertExt.notNull(payOrder.getTotalFee(), "??????????????????");
        AssertExt.notBlank(payOrder.getBody(), "??????????????????");
        AssertExt.notBlank(payOrder.getSpbillCreateIp(), "??????SpbillCreateIp");

//      PayOrder payOrderDB = this.payOrderMapper.selectOne(new QueryWrapper<PayOrder>().eq("order_no", payOrder.getOrderNo()));

        payOrder.setCreateTime(LocalDateTime.now());
        payOrder.setFeeType(CURRENCY_CODE_156);
        String[] now = ISO_LOCAL_DATE_TIME.format(payOrder.getCreateTime()).split("\\.")[0].split("T");

        LinkedHashMap dicOrder = new LinkedHashMap();
        LinkedHashMap dicRequest = new LinkedHashMap();
        LinkedHashMap orderItems = new LinkedHashMap();

        dicOrder.put("PayTypeID", "ImmediatePay");//??????????????????
        dicOrder.put("OrderDate", now[0].replaceAll("-","/"));                  //?????????????????? ??????????????? - YYYY/MM/DD???
        dicOrder.put("OrderTime", now[1]);                   //?????????????????? ??????????????? - HH:MM:SS???
        Long payTime= Long.valueOf(this.settingService.genPCMINTime("ABC_PAY_TIME").getValue());
        dicOrder.put("orderTimeoutDate", LOCAL_DATE_TIME.format(LocalDateTime.now().plusDays(payTime)));     //?????????????????????,????????????
//        dicOrder.put("orderTimeoutDate", LOCAL_DATE_TIME.format(LocalDateTime.now().plusMinutes(30L)));     //?????????????????????
        dicOrder.put("OrderNo",payOrder.getOrderNo());                       //?????????????????? ??????????????????
        dicOrder.put("CurrencyCode", payOrder.getFeeType());             //??????????????????
        dicOrder.put("OrderAmount", formatAmount(payOrder.getTotalFee()));      //??????????????????
//        dicOrder.put("Fee", request.getParameter("Fee"));                               //?????????????????????
//        dicOrder.put("AccountNo", request.getParameter("AccountNo"));                   //??????????????????
        dicOrder.put("OrderDesc", payOrder.getBody());                   //??????????????????
//        dicOrder.put("OrderURL","" );                     //??????????????????
//        dicOrder.put("ReceiverAddress", request.getParameter("ReceiverAddress"));       //????????????
//        dicOrder.put("InstallmentMark", request.getParameter("InstallmentMark"));       //????????????
        dicOrder.put("OpenID", payOrder.getOpenid());
        dicOrder.put("CommodityType", COMMODITY_TYPE_0201);           //?????????????????? CommodityType: 0101:?????????????????????0201:????????????0202:????????????0203:????????????0301:???????????????0302:???????????????0401:?????????0402:?????????0403:????????????0404:??????????????????0405:????????????0406:????????????0407:????????????0408:???????????????0409:?????????0410:?????????0499:?????????0501:?????????0502:???????????????0599:??????
        dicOrder.put("BuyIP", payOrder.getSpbillCreateIp());                           //IP
//        dicOrder.put("ExpiredDate", request.getParameter("ExpiredDate"));               //????????????????????????
        dicRequest.put("NotifyType", NOTIFY_TYPE);      //???????????????????????????0??????URL???????????? 1?????????????????????URL???????????????
        dicRequest.put("ResultNotifyURL", this.abcPayProperties.getPayCallback());      //??????????????????
        dicRequest.put("PaymentLinkType", PaymentLinkType);       // ?????????????????????1???internet???????????? 2????????????????????? 3:???????????????????????? 4:??????????????? 5???????????????
        dicRequest.put("IsBreakAccount", IS_BREAK_ACCOUNT);
        dicRequest.put("CommodityType", COMMODITY_TYPE_0201);

        orderItems.put("ProductName", payOrder.getBody());

        TrxRequest tPaymentRequest = makePaymentRequest(payOrder.getDataSource(), dicOrder, dicRequest, orderItems, this.abcPayProperties.getAppId(), payOrder.getPaymentType());

        JSON json = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            json = tPaymentRequest.extendPostRequest(1);
            String returnCode = json.GetKeyValue("ReturnCode");
            if (!returnCode.equals(SUCCESS_CODE)) {
                throw new RuntimeException(json.getIJsonString());
            }

            Map payInfo = (Map) objectMapper.readValue(json.GetKeyValue("MSG"), HashMap.class).get("Message");
            payInfo.remove("Merchant");
            payOrder.setPayInfo(payInfo );
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException", e);
        }finally {
            try {
                log.info("tPaymentRequest.extendPostRequest {}, {} , {}", tPaymentRequest.getClass().getSimpleName(), objectMapper.writer().writeValueAsString(tPaymentRequest), json == null ? "null" : json.getIJsonString());
            } catch (JsonProcessingException e) {
                log.error("JsonProcessingException", e);
            }
        }
        return payOrder;
    }

    /**
     * ?????? TrxRequest
     * @param dataSource
     * @param dicOrder
     * @param dicRequest
     * @param orderItems
     * @return
     */
    private static TrxRequest makePaymentRequest(String dataSource, LinkedHashMap dicOrder, LinkedHashMap dicRequest, LinkedHashMap orderItems,String appId,String paymentType) {
        if (DARA_SOURCE_PC.equals(dataSource) || DARA_SOURCE_PC_MONTH.equals(dataSource)) {
            dicRequest.put("PaymentType", paymentType);
            PaymentRequest paymentRequest = new PaymentRequest();
            paymentRequest.dicOrder.putAll(dicOrder);
            paymentRequest.dicRequest.putAll(dicRequest);
            paymentRequest.orderitems.put(0, orderItems);
            return paymentRequest;
        } else {
            dicRequest.put("PaymentType", PAYMENT_TYPE_8);
            dicOrder.put("PayTypeID", "JSAPI");
            dicOrder.put("AccountNo", appId);
            dicRequest.put("PaymentLinkType", "2");
            UnifiedPaymentRequest paymentRequest = new UnifiedPaymentRequest();
            paymentRequest.dicOrder.putAll(dicOrder);
            paymentRequest.dicRequest.putAll(dicRequest);
            paymentRequest.orderitems.put(0, orderItems);
            return paymentRequest;
        }
    }

    @Override
    public PayOrder payCallback(String requestBody) {
        PaymentResult tResult = null;
        try {
            tResult = new PaymentResult(requestBody);
        } catch (TrxException e) {
            log.error("PaymentResult error ", e);
            throw new RuntimeException(e);
        }
        log.info("payCallback {}", JSONObject.toJSONString(tResult));
        if(!tResult.isSuccess()){
            throw new RuntimeException(String.format("%s,%s", tResult.getReturnCode(), tResult.getErrorMessage()));
        }
        PayOrder payOrder = new PayOrder();
        payOrder.setOrderNo(tResult.getValue("OrderNo"));
        payOrder.setPayFlowNo(tResult.getValue("iRspRef"));
        payOrder.setPayType(tResult.getValue("PayType"));
        payOrder.setTotalFee(new BigDecimal(tResult.getValue("Amount")).multiply(num_100).intValue());

        this.innerCallbackService.payCallback(payOrder);

        // tResult.getValue("TrxType"        );
        // tResult.getValue("OrderNo"        );
        // tResult.getValue("Amount"         );
        // tResult.getValue("BatchNo"        );
        // tResult.getValue("VoucherNo"      );
        // tResult.getValue("HostDate"       );
        // tResult.getValue("HostTime"       );
        // tResult.getValue("MerchantRemarks");
        // tResult.getValue("PayType"        );
        // tResult.getValue("NotifyType"     );
        // tResult.getValue("iRspRef"        );
        // tResult.getValue("bank_type"      );
        // tResult.getValue("ThirdOrderNo"   );

//            tResult.getReturnCode  ();
//            tResult.getErrorMessage();

        return payOrder;
    }

    @Override
    public PayOrder refundCallback(String requestBody) {
        PaymentResult tResult = null;
        try {
            tResult = new PaymentResult(requestBody);
        } catch (TrxException e) {
            log.error("PaymentResult error ", e);
            throw new RuntimeException(e);
        }
        log.info("refundCallback {}", JSONObject.toJSONString(tResult));
        if(!tResult.isSuccess()){
            throw new RuntimeException(String.format("%s,%s", tResult.getReturnCode(), tResult.getErrorMessage()));
        }
        PayOrder payOrder = new PayOrder();
        payOrder.setOrderNo(tResult.getValue("OrderNo"));
        payOrder.setPayFlowNo(tResult.getValue("iRspRef"));
        payOrder.setRefundFee(new BigDecimal(tResult.getValue("TrxAmount")).multiply(num_100).intValue());
        this.innerCallbackService.refundCallback(payOrder);
        return payOrder;
    }

    @Override
    public PayOrder queryOrder(String orderNo) {
        return null;
    }

    @Override
    public PayOrderVo refund(String orderNo, String ref, Integer refundFree) {
        AssertExt.notBlank(orderNo, "orderNo is blank");
        AssertExt.isTrue(refundFree != null && refundFree > 0, "refundFree error");

        PayOrderVo payOrder = new PayOrderVo();
        payOrder.setRefundFee(refundFree);
        payOrder.setOrderNo(orderNo);
        payOrder.setRefundNo(String.format("%s_%s", orderNo, ref));

        RefundRequest tRequest = new RefundRequest();
        String[] now = ISO_LOCAL_DATE_TIME.format(LocalDateTime.now()).split("\\.")[0].split("T");
        tRequest.dicRequest.put("OrderDate", now[0].replaceAll("-", "/"));  //??????????????????????????????
        tRequest.dicRequest.put("OrderTime", now[1]); //??????????????????????????????
        //tRequest.dicRequest.put("MerRefundAccountNo", request.getParameter("txtMerRefundAccountNo"));  //??????????????????
        //tRequest.dicRequest.put("MerRefundAccountName", request.getParameter("txtMerRefundAccountName")); //???????????????
        tRequest.dicRequest.put("OrderNo", payOrder.getOrderNo()); //?????????????????????????????????
        tRequest.dicRequest.put("NewOrderNo", payOrder.getRefundNo()); //??????????????????????????????
        tRequest.dicRequest.put("CurrencyCode", CURRENCY_CODE_156); //??????????????????????????????
        tRequest.dicRequest.put("TrxAmount", formatAmount(payOrder.getRefundFee())); //???????????? ??????????????????
//        tRequest.dicRequest.put("RefundType", "0"); //???????????? ?????????????????????
//        tRequest.dicRequest.put("MerchantRemarks", request.getParameter("txtMerchantRemarks"));  //??????
//        tRequest.dicRequest.put("MerRefundAccountFlag", request.getParameter("txtMerRefundAccountFlag"));
        JSON json = null;
        try {
            json = tRequest.postRequest();
        }finally {
            log.info("RefundRequest {},{}", JSONObject.toJSONString(tRequest), json == null ? "null" : json.getIJsonString());
        }
        String returnCode = json.GetKeyValue("ReturnCode");
        if (!returnCode.equals(SUCCESS_CODE)) {
            throw new RuntimeException(json.getIJsonString());
        }
        payOrder.setRefundFlowNo(json.GetKeyValue("iRspRef"));
        return payOrder;
    }

    @Override
    public PayWithdrawVo widthDraw(PayWithdrawVo payWithdrawVo) {
        return null;
    }

    private static String formatAmount(Integer num){
        return BigDecimal.valueOf(num).divide(num_100).toString();
    }

}
