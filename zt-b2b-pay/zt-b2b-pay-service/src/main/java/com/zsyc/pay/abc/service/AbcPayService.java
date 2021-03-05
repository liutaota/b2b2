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
     * 时间格式
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
        AssertExt.notBlank(payOrder.getOrderNo(), "业务单号为空");
        AssertExt.notBlank(payOrder.getDataSource(), "未填系统来源");
        AssertExt.notNull(payOrder.getTotalFee(), "未填支付金额");
        AssertExt.notBlank(payOrder.getBody(), "没有商品描述");
        AssertExt.notBlank(payOrder.getSpbillCreateIp(), "无效SpbillCreateIp");

//      PayOrder payOrderDB = this.payOrderMapper.selectOne(new QueryWrapper<PayOrder>().eq("order_no", payOrder.getOrderNo()));

        payOrder.setCreateTime(LocalDateTime.now());
        payOrder.setFeeType(CURRENCY_CODE_156);
        String[] now = ISO_LOCAL_DATE_TIME.format(payOrder.getCreateTime()).split("\\.")[0].split("T");

        LinkedHashMap dicOrder = new LinkedHashMap();
        LinkedHashMap dicRequest = new LinkedHashMap();
        LinkedHashMap orderItems = new LinkedHashMap();

        dicOrder.put("PayTypeID", "ImmediatePay");//设定交易类型
        dicOrder.put("OrderDate", now[0].replaceAll("-","/"));                  //设定订单日期 （必要信息 - YYYY/MM/DD）
        dicOrder.put("OrderTime", now[1]);                   //设定订单时间 （必要信息 - HH:MM:SS）
        Long payTime= Long.valueOf(this.settingService.genPCMINTime("ABC_PAY_TIME").getValue());
        dicOrder.put("orderTimeoutDate", LOCAL_DATE_TIME.format(LocalDateTime.now().plusDays(payTime)));     //设定订单有效期,默认三天
//        dicOrder.put("orderTimeoutDate", LOCAL_DATE_TIME.format(LocalDateTime.now().plusMinutes(30L)));     //设定订单有效期
        dicOrder.put("OrderNo",payOrder.getOrderNo());                       //设定订单编号 （必要信息）
        dicOrder.put("CurrencyCode", payOrder.getFeeType());             //设定交易币种
        dicOrder.put("OrderAmount", formatAmount(payOrder.getTotalFee()));      //设定交易金额
//        dicOrder.put("Fee", request.getParameter("Fee"));                               //设定手续费金额
//        dicOrder.put("AccountNo", request.getParameter("AccountNo"));                   //设定支付账户
        dicOrder.put("OrderDesc", payOrder.getBody());                   //设定订单说明
//        dicOrder.put("OrderURL","" );                     //设定订单地址
//        dicOrder.put("ReceiverAddress", request.getParameter("ReceiverAddress"));       //收货地址
//        dicOrder.put("InstallmentMark", request.getParameter("InstallmentMark"));       //分期标识
        dicOrder.put("OpenID", payOrder.getOpenid());
        dicOrder.put("CommodityType", COMMODITY_TYPE_0201);           //设置商品种类 CommodityType: 0101:支付账户充值，0201:虚拟类，0202:传统类，0203:实名类，0301:本行转账，0302:他行转账，0401:水费，0402:电费，0403:煤气费，0404:有线电视费，0405:通讯费，0406:物业费，0407:保险费，0408:行政费用，0409:税费，0410:学费，0499:其他，0501:基金，0502:理财产品，0599:其他
        dicOrder.put("BuyIP", payOrder.getSpbillCreateIp());                           //IP
//        dicOrder.put("ExpiredDate", request.getParameter("ExpiredDate"));               //设定订单保存时间
        dicRequest.put("NotifyType", NOTIFY_TYPE);      //支付结果通知方式，0：仅URL页面通知 1：服务器通知和URL页面通知，
        dicRequest.put("ResultNotifyURL", this.abcPayProperties.getPayCallback());      //设定交易金额
        dicRequest.put("PaymentLinkType", PaymentLinkType);       // 支付交易渠道，1：internet网络接入 2：手机网络接入 3:数字电视网络接入 4:智能客户端 5：线下渠道
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
     * 构建 TrxRequest
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
        tRequest.dicRequest.put("OrderDate", now[0].replaceAll("-", "/"));  //订单日期（必要信息）
        tRequest.dicRequest.put("OrderTime", now[1]); //订单时间（必要信息）
        //tRequest.dicRequest.put("MerRefundAccountNo", request.getParameter("txtMerRefundAccountNo"));  //商户退款账号
        //tRequest.dicRequest.put("MerRefundAccountName", request.getParameter("txtMerRefundAccountName")); //商户退款名
        tRequest.dicRequest.put("OrderNo", payOrder.getOrderNo()); //原交易编号（必要信息）
        tRequest.dicRequest.put("NewOrderNo", payOrder.getRefundNo()); //交易编号（必要信息）
        tRequest.dicRequest.put("CurrencyCode", CURRENCY_CODE_156); //交易币种（必要信息）
        tRequest.dicRequest.put("TrxAmount", formatAmount(payOrder.getRefundFee())); //退货金额 （必要信息）
//        tRequest.dicRequest.put("RefundType", "0"); //退货类型 （非必要信息）
//        tRequest.dicRequest.put("MerchantRemarks", request.getParameter("txtMerchantRemarks"));  //附言
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
