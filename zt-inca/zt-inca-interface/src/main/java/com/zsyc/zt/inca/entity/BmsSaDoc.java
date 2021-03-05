package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2020-07-22
 */
@Data
@Accessors(chain = true)
@TableName("BMS_SA_DOC")
@ApiModel(value="BmsSaDoc对象", description="")
@KeySequence(value = "BMS_SA_DOC_SEQ")
public class BmsSaDoc  {

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
     * 1 未回写 2 已回写
     */
    @ApiModelProperty(value = "1 未回写 2 已回写")
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
     * 京东支付方式 ：1、在线支付2、帐期支付3、线上支付4、月结支付5、线下支付6、货到付款
     */
    @ApiModelProperty(value = "京东支付方式 ：1、在线支付2、帐期支付3、线上支付4、月结支付5、线下支付6、货到付款 ")
    @TableField("JD_PAY_TYPE")
    private Integer jdPayType;

    /**
     * 京东订单类型  1，销售单  2  配送单转移过来
     */
    @ApiModelProperty(value = "京东订单类型  1，销售单  2  配送单转移过来")
    @TableField("JD_ORDER_TYPE")
    private Integer jdOrderType;

    /**
     * 开票标记 1 开票  0 不开票
     */
    @ApiModelProperty(value = "开票标记 1 开票  0 不开票")
    @TableField("ZX_KP_FLAG")
    private Integer zxKpFlag;

    @TableField("JD_SHOP_FLAG")
    private Double jdShopFlag;

    /**
     * 2 自动确认成功 3  不成功
     */
    @ApiModelProperty(value = "2 自动确认成功 3  不成功")
    @TableField("AUTO_CONFIRM")
    private Integer autoConfirm;

    @TableField("B2B_WRITE_BACK_FLAG")
    private Integer b2bWriteBackFlag;

    @TableField("B2B_ORDER_ID")
    private Long b2bOrderId;

    @TableField("B2B_AMOUNT_TOTAL")
    private Double b2bAmountTotal;

    @TableField("B2B_AMOUNT_DISCOUNT")
    private Double b2bAmountDiscount;

    @TableField("B2B_AMOUNT_PAY")
    private Double b2bAmountPay;

    @TableField("B2B_AMOUNT_DELIVERY")
    private Double b2bAmountDelivery;

    @TableField("B2B_ORDER_TYPE")
    private String b2bOrderType;
    @TableField("B2B_ORDER_FROM")
    private Integer b2bOrderFrom;

    @TableField("B2B_PAY_TYPE")
    private String b2bPayType;

    @TableField("B2B_ORDER_NO")
    private String b2bOrderNo;

    @TableField("B2B_STORE_ID")
    private Long b2bStoreId;

    @TableField("B2B_PAY_ORDER_NO")
    private String b2bPayOrderNo;
    @TableField("B2B_PAY_FLOW_NO")
    private String b2bPayFlowNo;

    @TableField("API_ORDER_ID")
    private Long apiOrderId;

    @TableField("API_ORDER_NO")
    private String apiOrderNo;

    @TableField("API_ORDER_TIME")
    private LocalDateTime apiOrderTime;

    @TableField("B2B_SUB_CUSTOM_ID")
    private Long b2bSubCustomId;
}
