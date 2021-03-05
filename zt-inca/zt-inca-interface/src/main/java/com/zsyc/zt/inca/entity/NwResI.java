package com.zsyc.zt.inca.entity;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author peiqy
 * @since 2020-11-25
 */
// todo 注解
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("NW_RES_I")
@KeySequence("NW_RES_I_SEQ")
public class NwResI extends Model<NwResI> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="")
    @TableId("WMS_ITEM_ID")
    private String wmsItemId;

    @ApiModelProperty(value="")
    @TableField("ERP_ID")
    private String erpId;

    @ApiModelProperty(value="")
    @TableField("ERP_TYPE_ID")
    private String erpTypeId;

    @ApiModelProperty(value="")
    @TableField("ITEM_ID")
    private String itemId;

    @ApiModelProperty(value="")
    @TableField("INV_ID")
    private String invId;

    @ApiModelProperty(value="")
    @TableField("BAT_CODE")
    private String batCode;

    @ApiModelProperty(value="")
    @TableField("PROD_AT")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime prodAt;

    @ApiModelProperty(value="")
    @TableField("EXPR_AT")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime exprAt;

    @ApiModelProperty(value="")
    @TableField("QTY")
    private Long qty;

    @ApiModelProperty(value="")
    @TableField("QTY1")
    private BigDecimal qty1;

    @ApiModelProperty(value="")
    @TableField("MOVED_AT")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime movedAt;

    @ApiModelProperty(value="")
    @TableField("CHECKED_QTY")
    private Long checkedQty;

    @ApiModelProperty(value="")
    @TableField("CHECKED_QTY1")
    private BigDecimal checkedQty1;

    @ApiModelProperty(value="")
    @TableField("MOVED_BY")
    private String movedBy;

    @ApiModelProperty(value="")
    @TableField("MOVED_BY_NAME")
    private String movedByName;

    @ApiModelProperty(value="")
    @TableField("CHECKED_AT")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime checkedAt;

    @ApiModelProperty(value="")
    @TableField("CHECKED_BY")
    private String checkedBy;

    @ApiModelProperty(value="")
    @TableField("CHECKED_BY_NAME")
    private String checkedByName;

    @ApiModelProperty(value="")
    @TableField("CHECKED_BY2")
    private String checkedBy2;

    @ApiModelProperty(value="")
    @TableField("CHECKED_BY_NAME2")
    private String checkedByName2;

    @ApiModelProperty(value="")
    @TableField("IS_FCL")
    private Integer isFcl;

    @ApiModelProperty(value="")
    @TableField("SEAT_ID")
    private String seatId;

    @ApiModelProperty(value="")
    @TableField("MOVED_BY2")
    private String movedBy2;

    @ApiModelProperty(value="")
    @TableField("MOVED_BY_NAME2")
    private String movedByName2;

    @ApiModelProperty(value="")
    @TableField("FDA_CODE")
    private String fdaCode;

    @ApiModelProperty(value="")
    @TableField("SID")
    private String sid;

    @ApiModelProperty(value="")
    @TableField("PACK_ID")
    private String packId;

    @ApiModelProperty(value="")
    @TableField("CANCELLED_BY")
    private String cancelledBy;

    @ApiModelProperty(value="")
    @TableField("ZX_ISLOAD")
    private Integer zxIsload;


    @Override
    protected Serializable pkVal() {
        return this.wmsItemId;
    }

}
