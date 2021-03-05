package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author peiqy
 * @since 2020-01-11
 */
@TableName("GPCS_PLACESUPPLY")
@Data
@KeySequence("GPCS_PLACESUPPLY_SEQ")
@EqualsAndHashCode(callSuper = false)
public class GpcsPlacesupply extends Model<GpcsPlacesupply> {

    private static final long serialVersionUID = 1L;

    @TableId("PLACESUPPLYID")
    private Long placesupplyid;

    @TableField("PLACECENTERID")
    private Integer placecenterid;

    @TableField("PLACEPOINTID")
    private Long placepointid;

    @TableField("COUNTERID")
    private Long counterid;

    @TableField("REQSUPPLYID")
    private Long reqsupplyid;

    @TableField("PLACEMETHOD")
    private Integer placemethod;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("PLACEMANID")
    private Long placemanid;

    @TableField("PLACEDATE")
    private LocalDateTime placedate;

    @TableField("URGENTFLAG")
    private Integer urgentflag;

    @TableField("DTL_LINES")
    private Integer dtlLines;

    @TableField("TOTAL")
    private Double total;

    @TableField("PRINTFLAG")
    private Integer printflag;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("GENBILLFLAG")
    private Integer genbillflag;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("MEMO")
    private String memo;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("GENPOSTFLAG")
    private Integer genpostflag;

    @TableField("DOCDISCOUNT")
    private Double docdiscount;

    @TableField("DELIVERMETHOD")
    private Integer delivermethod;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("ZX_OLDPLACESUPPLYID")
    private Long zxOldplacesupplyid;

    @TableField("ZX_BH_FLAG")
    private Integer zxBhFlag;

    @TableField("ZX_ORDERNUMBER")
    private String zxOrdernumber;

    @TableField("CMDSETID")
    private String cmdsetid;

    @TableField("ZXDOCCOL1")
    private String zxdoccol1;

    @TableField("ZXDOCCOL2")
    private String zxdoccol2;

    @TableField("ZXDOCCOL3")
    private String zxdoccol3;

    @TableField("ZX_SJ")
    private String zxSj;

    @TableField("ZX_DH")
    private String zxDh;

    @TableField("ZX_YSXL")
    private String zxYsxl;

    @TableField("SOURCE_ORDER_NUM")
    private String sourceOrderNum;

    @TableField("SOURCE_DESC")
    private String sourceDesc;

    /**
     * 1 未回写 2 已回写
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
     * 京东支付方式 ：1、在线支付2、帐期支付3、线上支付4、月结支付5、线下支付6、货到付款
     */
    @TableField("JD_PAY_TYPE")
    private Integer jdPayType;

    /**
     * 京东订单类型  1，销售单  2  配送单转移过来
     */
    @TableField("JD_ORDER_TYPE")
    private Integer jdOrderType;

    @TableField("ZX_KP_FLAG")
    private Integer zxKpFlag;

    @TableField("APPROVEFLAG")
    private Integer approveflag;

    @TableField("APPROVEMANNAME")
    private String approvemanname;

    @TableField("APPROVEMANID")
    private Long approvemanid;

    @TableField("APPROVEDATE")
    private LocalDateTime approvedate;

    @TableField("APPROVEMEMO")
    private String approvememo;

    /**
     * 2 自动确认成功 3  不成功
     */
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


    @TableField(exist = false)
    private List<GpcsPlacesupplydtl> detailList;




}
