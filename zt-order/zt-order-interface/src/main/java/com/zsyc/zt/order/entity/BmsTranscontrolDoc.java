package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
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
@TableName("BMS_TRANSCONTROL_DOC")
public class BmsTranscontrolDoc extends Model<BmsTranscontrolDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("ROADID")
    private Long roadid;

    @TableField("VEHICLEID")
    private Long vehicleid;

    @TableField("DRIVERID")
    private Long driverid;

    @TableField("DELIDATE")
    private LocalDateTime delidate;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("DELIMANID")
    private Long delimanid;

    @TableField("SUMQUANTITY")
    private BigDecimal sumquantity;

    @TableField("SUMCOST")
    private BigDecimal sumcost;

    @TableField("DTLCOUNT")
    private Long dtlcount;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("MEMO")
    private String memo;

    @TableField("PRINTFLAG")
    private Integer printflag;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("GSPUNCONFIRMFLAG")
    private Integer gspunconfirmflag;

    @TableField("COLDSTORAGEFLAG")
    private Long coldstorageflag;

    @TableField("TRANSPORTID")
    private Long transportid;

    @TableField("TRANSPORTTYPE")
    private Long transporttype;

    @TableField("TRANMETHOD")
    private Integer tranmethod;

    @TableField("STARTDATE")
    private LocalDateTime startdate;

    @TableField("EXTERNALDRIVER")
    private String externaldriver;

    @TableField("EXTERNALDELIMAN")
    private String externaldeliman;

    @TableField("FILECOUNT")
    private Integer filecount;

    @TableField("STARTTEMPERATURE")
    private BigDecimal starttemperature;

    @TableField("ZX_SARECTYPE")
    private Integer zxSarectype;

    @TableField("ZX_DELIVERYUNIT")
    private Integer zxDeliveryunit;

    @TableField("ZX_DELIVERYDATE")
    private LocalDateTime zxDeliverydate;

    @TableField("ZX_DELIVERYNUM")
    private String zxDeliverynum;

    @TableField("DISPATCHFLAG")
    private Integer dispatchflag;

    @TableField("ZX_PRINTHOMEFLAG")
    private Integer zxPrinthomeflag;

    @TableField("ZX_PRINTTRANSCERTIFLAG")
    private Integer zxPrinttranscertiflag;

    @TableField("ZX_PRINTCCBFLAG")
    private Integer zxPrintccbflag;


    @Override
    protected Serializable pkVal() {
        return this.roadid;
    }

}
