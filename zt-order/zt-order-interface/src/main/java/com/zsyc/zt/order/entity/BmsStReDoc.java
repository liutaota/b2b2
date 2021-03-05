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
@TableName("BMS_ST_RE_DOC")
public class BmsStReDoc extends Model<BmsStReDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("REDOCID")
    private Long redocid;

    @TableField("RETYPE")
    private Integer retype;

    @TableField("FREIGHTBILLNO")
    private String freightbillno;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("STORERID")
    private Long storerid;

    @TableField("MEMO")
    private String memo;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("TWOINVOICEINFLAG")
    private Integer twoinvoiceinflag;

    @TableField("FILECOUNT")
    private Long filecount;

    @TableField("PLACERETURNID")
    private Long placereturnid;

    @TableField("ZX_MEMO")
    private String zxMemo;

    @TableField("ZX_RECMANID")
    private Long zxRecmanid;

    @TableField("ZX_RECDATE")
    private LocalDateTime zxRecdate;

    @TableField("JD_ORDER_ID")
    private BigDecimal jdOrderId;


    @Override
    protected Serializable pkVal() {
        return this.redocid;
    }

}
