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
@TableName("BMS_ST_COMPLEMENT_APPLY")
public class BmsStComplementApply extends Model<BmsStComplementApply> {

    private static final long serialVersionUID = 1L;

    @TableId("APPLYID")
    private Long applyid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("FROMSTORAGEID")
    private Long fromstorageid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("REFAPPLYQTY")
    private BigDecimal refapplyqty;

    @TableField("APPLYQTY")
    private BigDecimal applyqty;

    @TableField("PLACEQTY")
    private BigDecimal placeqty;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.applyid;
    }

}
