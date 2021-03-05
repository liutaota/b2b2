package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("BMS_PRES_OUT_DTL")
public class BmsPresOutDtl extends Model<BmsPresOutDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("PRESOUTID")
    private Long presoutid;

    @TableId("PRESOUTDTLID")
    private Long presoutdtlid;

    @TableField("STORAGEID")
    private Long storageid;

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
    private Long goodsstatusid;

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
    private String b2bOrderId;

    @TableField("B2B_ORDER_DTL_ID")
    private String b2bOrderDtlId;

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

    @TableField("B2B_NUM")
    private Double b2bNum;

    @TableField("B2B_PRICE")
    private Double b2bPrice;


    @Override
    protected Serializable pkVal() {
        return this.presoutdtlid;
    }

}
