package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("BMS_SA_DOC")
@KeySequence("BMS_SA_DOC_SEQ")
public class BmsSaDoc extends Model<BmsSaDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("SALESID")
    private Long salesid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("CUSTOMNAME")
    private String customname;

    @TableField("INVTYPE")
    private Integer invtype;

    @TableField("SETTLETYPEID")
    private Integer settletypeid;

    @TableField("SALERID")
    private Long salerid;

    @TableField("SALESDEPTID")
    private Long salesdeptid;

    @TableField("DELIVERMETHOD")
    private Integer delivermethod;

    @TableField("TRANMETHOD")
    private Integer tranmethod;

    @TableField("TARGETPOSID")
    private Long targetposid;

    @TableField("URGENTFLAG")
    private Integer urgentflag;

    @TableField("TRANPRIORITY")
    private Integer tranpriority;

    @TableField("TARGETDATE")
    private LocalDateTime targetdate;

    @TableField("TOTAL")
    private Double total;

    @TableField("CONFIRMANID")
    private Long confirmanid;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("MEMO")
    private String memo;

    @TableField("DTL_LINES")
    private Integer dtlLines;

    @TableField("SATYPEID")
    private Integer satypeid;

    @TableField("INITFLAG")
    private Integer initflag;

    @TableField("ENTRYID")
    private Integer entryid;

    @TableField("STSETID")
    private Long stsetid;

    @TableField("FMID")
    private Long fmid;

    @TableField("EXCHANGE")
    private Double exchange;

    @TableField("CREDITFLAG")
    private Integer creditflag;

    @TableField("CREDIT")
    private Double credit;

    @TableField("RECMONEY")
    private Double recmoney;

    @TableField("CREDITDAYSFLAG")
    private Integer creditdaysflag;

    @TableField("CREDITDAYS")
    private Long creditdays;

    @TableField("RECDATE")
    private LocalDateTime recdate;

    @TableField("LOWPRICEFLAG")
    private Integer lowpriceflag;

    @TableField("APPROVEFLAG")
    private Integer approveflag;

    @TableField("APPROVEMANID")
    private Long approvemanid;

    @TableField("APPROVEDATE")
    private LocalDateTime approvedate;

    @TableField("APPROVEMEMO")
    private String approvememo;

    @TableField("ASSESSDATE")
    private LocalDateTime assessdate;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("RECEIVEFLAG")
    private Integer receiveflag;

    @TableField("RECEIVEMANID")
    private Long receivemanid;

    @TableField("RECEIVEDATE")
    private LocalDateTime receivedate;

    @TableField("PRINTCOUNT")
    private Long printcount;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("SACERTID")
    private Long sacertid;

    @TableField("RGID")
    private Long rgid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("TOEDISTATUS")
    private Integer toedistatus;

    @TableField("INVALIDSTATUS")
    private Integer invalidstatus;

    @TableField("SETDAYS")
    private Long setdays;

    @TableField("AWARDRATE")
    private Double awardrate;

    @TableField("PUNISHRATE")
    private Double punishrate;

    @TableField("PLACESUPPLYID")
    private Long placesupplyid;

    @TableField("NOWMSFLAG")
    private Integer nowmsflag;

    @TableField("PLANINVDATE")
    private LocalDateTime planinvdate;

    @TableField("INVDEMAND")
    private Integer invdemand;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("DISCOUNTTYPE")
    private Integer discounttype;

    @TableField("TRANSDOCNO")
    private String transdocno;

    @TableField("TWOINVFLAG")
    private Integer twoinvflag;

    @TableField("TWOINVMETHOD")
    private Integer twoinvmethod;

    @TableField("ZX_OLDSALESID")
    private Long zxOldsalesid;

    @TableField("ZX_BH_FLAG")
    private Integer zxBhFlag;

    @TableField("ZX_ORDERNUMBER")
    private String zxOrdernumber;

    @TableField("ZX_MEMO")
    private String zxMemo;

    @TableField("INVOICE")
    private String invoice;

    @TableField("INVDATE")
    private String invdate;

    @TableField("ZX_INITSATUS")
    private Integer zxInitsatus;

    @TableField("INVMETHOD")
    private Integer invmethod;

    @TableField("BAK")
    private Long bak;

    @TableField("CMDSETID")
    private String cmdsetid;

    @TableField("ZXDOCCOL1")
    private String zxdoccol1;

    @TableField("ZXDOCCOL2")
    private String zxdoccol2;

    @TableField("ZXDOCCOL3")
    private String zxdoccol3;

    @TableField("CMDSETID2")
    private String cmdsetid2;

    @TableField("ZX_INVSTATUS")
    private Integer zxInvstatus;

    @TableField("ZX_INVBACKDATE")
    private LocalDateTime zxInvbackdate;

    @TableField("ZX_INVDATE")
    private LocalDateTime zxInvdate;

    @TableField("ZX_ERRORMEMO")
    private String zxErrormemo;

    @TableField("ZX_INVNO")
    private String zxInvno;

    @TableField("ZX_INVCODE")
    private String zxInvcode;

    @TableField("SOURCE_ORDER_NUM")
    private String sourceOrderNum;

    @TableField("SOURCE_DESC")
    private String sourceDesc;

    /**
     * 1 ??? 2 ???
     */
    @TableField("JD_WRITE_BACK_FLAG")
    private Integer jdWriteBackFlag;

    @TableField("JD_ORDER_ID")
    private String jdOrderId;

    @TableField("JD_ORDER_DESC")
    private String jdOrderDesc;

    @TableField("JD_TOTAL_MONEY")
    private Double jdTotalMoney;

    @TableField("JD_DISCOUNT")
    private Double jdDiscount;

    @TableField("JD_PAY_MONEY")
    private Double jdPayMoney;

    /**
     * ?????? ?1?????2?????3?????4?????5?????6????? 
     */
    @TableField("JD_PAY_TYPE")
    private Integer jdPayType;

    /**
     * ??????  1????  2  ???????
     */
    @TableField("JD_ORDER_TYPE")
    private Integer jdOrderType;

    /**
     * ???? 1 ??  0 ???
     */
    @TableField("ZX_KP_FLAG")
    private Integer zxKpFlag;

    @TableField("JD_SHOP_FLAG")
    private Double jdShopFlag;

    /**
     * 2 ?????? 3  ???
     */
    @TableField("AUTO_CONFIRM")
    private Integer autoConfirm;

    /**
     * 1 初始 2 下发物流  3 出库
     */
    @TableField("B2B_WRITE_BACK_FLAG")
    private Integer b2bWriteBackFlag;

    @TableField("B2B_ORDER_ID")
    private Long b2bOrderId;

    /**
     * b2b总额度
     */
    @TableField("B2B_AMOUNT_TOTAL")
    private Double b2bAmountTotal;

    /**
     * b2b优惠额度
     */
    @TableField("B2B_AMOUNT_DISCOUNT")
    private Double b2bAmountDiscount;

    /**
     * b2b付额度
     */
    @TableField("B2B_AMOUNT_PAY")
    private Double b2bAmountPay;

    /**
     * b2b快递额度
     */
    @TableField("B2B_AMOUNT_DELIVERY")
    private Double b2bAmountDelivery;

    /**
     * 订单类型
     */
    @TableField("B2B_ORDER_TYPE")
    private String b2bOrderType;

    /**
     * 支付类型
     */
    @TableField("B2B_PAY_TYPE")
    private Integer b2bPayType;

    @TableField("B2B_ORDER_NO")
    private String b2bOrderNo;
    @TableField("b2b_store_id")
    private Long b2bStoreId;
    @TableField("b2b_sub_custom_id")
    private Long b2bSubCustomId;

    @TableField("API_ORDER_ID")
    private Long apiOrderId;

    @TableField("API_ORDER_NO")
    private String apiOrderNo;

    @TableField("API_ORDER_TIME")
    private LocalDateTime apiOrderTime;

    @Override
    protected Serializable pkVal() {
        return this.salesid;
    }

}
