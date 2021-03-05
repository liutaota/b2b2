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
@TableName("BMS_ST_ADJUST_TMP_BAK")
public class BmsStAdjustTmpBak extends Model<BmsStAdjustTmpBak> {

    private static final long serialVersionUID = 1L;

    @TableId("STADJUSTID")
    private Long stadjustid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("MEMO")
    private String memo;

    @TableField("ADJUSTMANID")
    private Long adjustmanid;

    @TableField("ADJUSTDATE")
    private LocalDateTime adjustdate;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("ADDRESS")
    private String address;

    @TableField("TRANSLINEID")
    private Long translineid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("POSID")
    private Long posid;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("NEWPOSID")
    private Long newposid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("PRINTCOUNT")
    private Integer printcount;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("ENTRYID")
    private Long entryid;


    @Override
    protected Serializable pkVal() {
        return this.stadjustid;
    }

}
