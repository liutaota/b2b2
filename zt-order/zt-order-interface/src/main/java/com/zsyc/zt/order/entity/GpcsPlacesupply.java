package com.zsyc.zt.order.entity;

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
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("GPCS_PLACESUPPLY")
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

    @TableField("B2B_ORDER_TYPE")
    private String b2bOrderType;

    /**
     * 支付类型
     */
    @TableField("B2B_PAY_TYPE")
    private Integer b2bPayType;

    @TableField("B2B_ORDER_NO")
    private String b2bOrderNo;


    @Override
    protected Serializable pkVal() {
        return this.placesupplyid;
    }

}
