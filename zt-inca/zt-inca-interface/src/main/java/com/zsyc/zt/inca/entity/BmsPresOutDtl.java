package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2020-07-25
 */
@Data

@Accessors(chain = true)
@TableName("BMS_PRES_OUT_DTL")
@ApiModel(value="BmsPresOutDtl对象", description="")
@KeySequence(value = "BMS_PRES_OUT_DTLID_SEQ")
public class BmsPresOutDtl {

    private static final long serialVersionUID = 1L;



    @TableField("PRESOUTID")
    private Long presoutid;

    @TableId("PRESOUTDTLID")
    private Long presoutdtlid;

    @TableField("STORAGEID")
    private Integer storageid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("GOODSQTY")
    private Double goodsqty;

    @TableField("GOODSUSEQTY")
    private Double goodsuseqty;

    @TableField("GOODSUSEUNIT")
    private String goodsuseunit;

    @TableField("LOTID")
    private Long lotid;

    @TableField("POSID")
    private Long posid;

    @TableField("GOODSSTATUSID")
    private Integer goodsstatusid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("RECST")
    private Integer recst;

    @TableField("RECSTDATE")
    private LocalDateTime recstdate;

    @TableField("INVALIDFLAG")
    private Integer invalidflag;

    @TableField("INVALIDMANID")
    private Long invalidmanid;

    @TableField("INVALIDDATE")
    private LocalDateTime invaliddate;

    @TableField("MEMO")
    private String memo;

    @TableField("SOURCETABLE")
    private Integer sourcetable;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("POLICYID")
    private Long policyid;

    @TableField("PLACESUPPLYDTLSTID")
    private Long placesupplydtlstid;

    @TableField("SENDFLAG")
    private Integer sendflag;

    @TableField("WMSSTATUS")
    private Integer wmsstatus;

    @TableField("SENDWMSFLAG")
    private Integer sendwmsflag;

    @TableField("SENDWMSDATE")
    private LocalDateTime sendwmsdate;

    @TableField("WMSBACKDATE")
    private LocalDateTime wmsbackdate;

    @TableField("ZX_RELETEID")
    private Long zxReleteid;

    @TableField("ZX_BHRELETEID")
    private Long zxBhreleteid;

    @TableField("JD_SOURCE_NUM")
    private Double jdSourceNum;

    @TableField("JD_ORDER_ID")
    private Long jdOrderId;

    @TableField("JD_ORDER_DTL_ID")
    private Long jdOrderDtlId;

    @TableField("B2B_ORDER_ID")
    private Long b2bOrderId;

    @TableField("B2B_ORDER_DTL_ID")
    private Long b2bOrderDtlId;

    /**
     * b2b总额度
     */
    @ApiModelProperty(value = "b2b总额度")
    @TableField("B2B_AMOUNT_TOTAL")
    private Double b2bAmountTotal;

    /**
     * b2b优惠额度
     */
    @ApiModelProperty(value = "b2b优惠额度")
    @TableField("B2B_AMOUNT_DISCOUNT")
    private Double b2bAmountDiscount;

    /**
     * b2b付额度
     */
    @ApiModelProperty(value = "b2b付额度")
    @TableField("B2B_AMOUNT_PAY")
    private Double b2bAmountPay;

    /**
     * b2b快递额度
     */
    @ApiModelProperty(value = "b2b快递额度")
    @TableField("B2B_AMOUNT_DELIVERY")
    private Double b2bAmountDelivery;

    @TableField("B2B_NUM")
    private Double b2bNum;

    @TableField("B2B_PRICE")
    private Double b2bPrice;

    @TableField("B2B_PRICE_DISCOUNT")
    private Double b2bPriceDiscount;

    /**
     * b2b订单类型
     */
    @ApiModelProperty(value = "b2b订单类型")
    @TableField("B2B_ORDER_TYPE")
    private String b2bOrderType;

    @TableField("B2B_ORDER_NO")
    private String b2bOrderNo;

    @TableField("B2B_PAY_TYPE")
    private String b2bPayType;

    @TableField("API_ORDER_ID")
    private Long apiOrderId;

    @TableField("API_ORDER_DTL_ID")
    private Long apiOrderDtlId;

    @TableField("API_ORDER_NO")
    private String apiOrderNo;

}
