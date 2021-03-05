package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


/**
 * <p>
 * 
 * </p>
 *
 * @author peiqy
 * @since 2020-01-11
 */
@TableName("GPCS_PLACESUPPLYDTL")
@Data
@KeySequence("GPCS_PLACESUPPLYDTL_SEQ")
@EqualsAndHashCode(callSuper = false)
public class GpcsPlacesupplydtl extends Model<GpcsPlacesupplydtl> {

    private static final long serialVersionUID = 1L;

    @TableId("PLACESUPPLYDTLID")
    private Long placesupplydtlid;

    @TableField("PLACESUPPLYID")
    private Long placesupplyid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("GOODSUNIT")
    private String goodsunit;

    @TableField("GOODSQTY")
    private Double goodsqty;

    @TableField("REFUSEQTY")
    private Double refuseqty;

    @TableField("PLACEPRICEID")
    private Integer placepriceid;

    @TableField("PLACEPRICE")
    private Double placeprice;

    @TableField("TOTAL_LINE")
    private Double totalLine;

    @TableField("ACCEPTQTY")
    private Double acceptqty;

    @TableField("TOTALRETURNQTY")
    private Double totalreturnqty;

    @TableField("PLACESTATUS")
    private Integer placestatus;

    @TableField("RESALEPRICE")
    private Double resaleprice;

    @TableField("ACCMEMO")
    private String accmemo;

    @TableField("DISCOUNT")
    private Double discount;

    @TableField("OLDPLACEPRICE")
    private Double oldplaceprice;

    @TableField("CHANGEABLE")
    private Integer changeable;

    @TableField("ZX_SPLIT_TYPE")
    private Integer zxSplitType;

    @TableField("ZX_RELETEID")
    private Long zxReleteid;

    @TableField("ZX_BHRELETEID")
    private Long zxBhreleteid;

    @TableField("ZX_CXINFO")
    private String zxCxinfo;

    @TableField("JD_SOURCE_NUM")
    private Double jdSourceNum;

    @TableField("JD_ORDER_ID")
    private Long jdOrderId;

    @TableField("JD_ORDER_DTL_ID")
    private Long jdOrderDtlId;

    @TableField("JD_TOTAL_MONEY")
    private Double jdTotalMoney;

    @TableField("JD_DISCOUNT")
    private Double jdDiscount;

    @TableField("JD_PAY_MONEY")
    private Double jdPayMoney;

    /**
     * 京东优惠单价
     */
    @TableField("JD_UNIT_PRICE")
    private Double jdUnitPrice;

    /**
     * 京东单项金额
     */
    @TableField("JD_MONEY")
    private Double jdMoney;

    /**
     * 京东订单类型  1，销售单  2  配送单转移过来
     */
    @TableField("JD_ORDER_TYPE")
    private Integer jdOrderType;

    /**
     * 1 表示不启用阶梯价
     */
    @TableField("ZX_PRICE_MODIFY_FLAG")
    private Integer zxPriceModifyFlag;

    @TableField("JD_PAY_TYPE")
    private Integer jdPayType;

    @TableField("B2B_ORDER_ID")
    private Double b2bOrderId;

    @TableField("B2B_ORDER_DTL_ID")
    private Long b2bOrderDtlId;

    @TableField("B2B_AMOUNT_TOTAL")
    private Double b2bAmountTotal;

    @TableField("B2B_AMOUNT_DISCOUNT")
    private Double b2bAmountDiscount;

    @TableField("B2B_AMOUNT_PAY")
    private Double b2bAmountPay;

    @TableField("B2B_AMOUNT_DELIVERY")
    private Double b2bAmountDelivery;

    @TableField("B2B_NUM")
    private Double b2bNum;

    @TableField("B2B_PRICE")
    private Double b2bPrice;

    @TableField("B2B_PRICE_DISCOUNT")
    private Double b2bPriceDiscount;

    @TableField("B2B_ORDER_NO")
    private String b2bOrderNo;

    @TableField("B2B_ORDER_TYPE")
    private String b2bOrderType;

    @TableField("API_ORDER_ID")
    private Long apiOrderId;

    @TableField("API_ORDER_DTL_ID")
    private Long apiOrderDtlId;


    @TableField("API_ORDER_NO")
    private String apiOrderNo;



}
