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
@TableName("BMS_PR_CUSTOM")
public class BmsPrCustom extends Model<BmsPrCustom> {

    private static final long serialVersionUID = 1L;

    @TableId("PRID")
    private Long prid;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("PRICE")
    private BigDecimal price;

    @TableField("STARTDATE")
    private LocalDateTime startdate;

    @TableField("ENDDATE")
    private LocalDateTime enddate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("WFUSESTATUS")
    private Integer wfusestatus;

    @TableField("WFPROCESS")
    private Integer wfprocess;

    @TableField("WFMEMO")
    private String wfmemo;

    @TableField("ENTRYID")
    private Long entryid;


    @Override
    protected Serializable pkVal() {
        return this.prid;
    }

}
