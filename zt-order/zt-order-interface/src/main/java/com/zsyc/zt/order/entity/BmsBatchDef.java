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
@TableName("BMS_BATCH_DEF")
public class BmsBatchDef extends Model<BmsBatchDef> {

    private static final long serialVersionUID = 1L;

    @TableId("BATCHID")
    private Long batchid;

    @TableField("BATCHNO")
    private String batchno;

    @TableField("BATCHSORTNO")
    private String batchsortno;

    @TableField("CREATEFROM")
    private Integer createfrom;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("MEMO")
    private String memo;

    @TableField("NOTAXSUPRICE")
    private BigDecimal notaxsuprice;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("OLDBATCHID")
    private Long oldbatchid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("EMPID")
    private Long empid;

    @TableField("DEPUTYID")
    private Long deputyid;

    @TableField("LIMITID")
    private Long limitid;

    @TableField("LIMITCUSTOMSETID")
    private Long limitcustomsetid;

    @TableField("TAXRATE")
    private BigDecimal taxrate;

    @TableField("QUALITYINFO")
    private String qualityinfo;

    @TableField("BANNEDCUSTOMERID")
    private Long bannedcustomerid;

    @TableField("BANNEDCUSTOMERSETID")
    private Long bannedcustomersetid;

    @TableField("POSTFLAG")
    private Integer postflag;

    @TableField("BANNEDAGENTID")
    private Long bannedagentid;

    @TableField("LIMITAGENTID")
    private Long limitagentid;

    @TableField("SUDATE")
    private LocalDateTime sudate;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("LIMITCONTACTID")
    private Long limitcontactid;

    @TableField("BANNEDCONTACTID")
    private Long bannedcontactid;

    @TableField("TWOINVOICEINFLAG")
    private Integer twoinvoiceinflag;

    @TableField("FREIGHTCOUNT")
    private Long freightcount;

    @TableField("INVCOUNT")
    private Long invcount;

    @TableField("ZX_KP_FLAG")
    private Integer zxKpFlag;


    @Override
    protected Serializable pkVal() {
        return this.batchid;
    }

}
