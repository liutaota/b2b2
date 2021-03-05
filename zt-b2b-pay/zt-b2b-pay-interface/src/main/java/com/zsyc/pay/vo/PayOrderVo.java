package com.zsyc.pay.vo;

import com.zsyc.pay.entity.PayOrder;
import com.zsyc.pay.entity.PayTrade;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lcs on 2019-04-07.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PayOrderVo extends PayOrder {

    /**
     * openid
     */
    private String openid;
    /**
     * spbillCreateIp
     */
    private String spbillCreateIp;
    /**
     * payInfo
     */
    private Map payInfo;
    /**
     * 退款单号
     */
    private String refundNo;
    /**
     * 退款流水号（银行返回的）
     */
    private String refundFlowNo;

    /**
     * PC 支付类型
     * 1：农行借记卡支付
     * 2：国际卡支付
     * 3：农行贷记卡支付
     * 5：B2B跨行支付
     * 6：银联跨行支付
     * 7：农行对公账户支付（含银联跨行B2B支付）
     * A:支付方式合并（包括1和3）(默认)
     */
    private String paymentType = "A";

//    public enum EMetaData {openid, appId, subAppId, spbillCreateIp, payInfo, refundNo,refundFlowNo,}
//
//    /**
//     * 退款单号
//     * @return
//     */
//    public String getRefundNo() {
//        return super.getMetaData(EMetaData.refundNo.name());
//    }
//
//    /**
//     * 退款单号
//     * @return
//     */
//    public void setRefundNo(String refundNo) {
//        super.setMetaData(EMetaData.refundNo.name(), refundNo);
//    }
//
//    /**
//     * 退款流水号（银行返回的）
//     * @return
//     */
//    public String getRefundFlowNo() {
//        return super.getMetaData(EMetaData.refundFlowNo.name());
//    }
//
//    /**
//     * 退款流水号（银行返回的）
//     * @param refundNo
//     */
//    public void setRefundFlowNo(String refundNo) {
//        super.setMetaData(EMetaData.refundFlowNo.name(), refundNo);
//    }
//
//    /**
//     * 交易流水
//     */
//    private List<PayTrade> tradeList;
//
//    /**
//     * openid
//     *
//     * @return
//     */
//
//    public String getOpenid() {
//        return super.getMetaData(EMetaData.openid.name());
//    }
//
//    /**
//     * openid
//     *
//     * @return
//     */
//    public void setOpenid(String openid) {
//        super.setMetaData(EMetaData.openid.name(), openid);
//    }
//
//    /**
//     * subAppId
//     *
//     * @return
//     */
//    public String getSubAppId() {
//        return super.getMetaData(EMetaData.subAppId.name());
//    }
//
//    /**
//     * subAppId
//     *
//     * @return
//     */
//    public void setSubAppId(String openid) {
//        super.setMetaData(EMetaData.subAppId.name(), openid);
//    }
//
//    /**
//     * spbillCreateIp
//     *
//     * @return
//     */
//    public String getSpbillCreateIp() {
//        return super.getMetaData(EMetaData.spbillCreateIp.name());
//    }
//
//    /**
//     * spbillCreateIp
//     *
//     * @return
//     */
//    public void setSpbillCreateIp(String spbillCreateIp) {
//        super.setMetaData(EMetaData.spbillCreateIp.name(), spbillCreateIp);
//    }
//
//    /**
//     * appId
//     *
//     * @return
//     */
//    public String getAppId() {
//        return super.getMetaData(EMetaData.appId.name());
//    }
//
//    /**
//     * appId
//     *
//     * @return
//     */
//    public void setAppId(String appId) {
//        super.setMetaData(EMetaData.appId.name(), appId);
//    }
//
//    /**
//     * payInfo
//     *
//     * @return
//     */
//    public Map getPayInfo() {
//        return super.getMetaData(EMetaData.payInfo.name(), HashMap.class);
//    }
//
//    /**
//     * payInfo
//     *
//     * @return
//     */
//    public void setPayInfo(Map payInfo) {
//        super.setMetaData(EMetaData.payInfo.name(), payInfo);
//    }
}
