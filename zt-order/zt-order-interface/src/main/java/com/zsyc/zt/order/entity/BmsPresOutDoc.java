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
@TableName("BMS_PRES_OUT_DOC")
public class BmsPresOutDoc extends Model<BmsPresOutDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("PRESOUTID")
    private Long presoutid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("ENTRYID")
    private Integer entryid;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("CUSTOMNAME")
    private String customname;

    @TableField("POLICYTYPE")
    private Integer policytype;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("DTL_LINES")
    private Integer dtlLines;

    @TableField("TARGETDATE")
    private LocalDateTime targetdate;

    @TableField("PRINTFLAG")
    private Integer printflag;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("MEMO")
    private String memo;

    @TableField("SOURCETABLE")
    private Integer sourcetable;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("POLICYID")
    private Long policyid;

    @TableField("PLACEPOINTID")
    private Long placepointid;

    @TableField("PLACESUPPLYID")
    private Long placesupplyid;

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

    @TableField("JD_ORDER_ID")
    private Long jdOrderId;

    @TableField("JD_WRITE_BACK_FLAG")
    private Integer jdWriteBackFlag;

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
        return this.presoutid;
    }

}
