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
@TableName("BMS_SA_QTYSPLIT_LST")
public class BmsSaQtysplitLst extends Model<BmsSaQtysplitLst> {

    private static final long serialVersionUID = 1L;

    @TableId("SPLITID")
    private Long splitid;

    @TableField("SALESDEPTID")
    private Long salesdeptid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("SPLITQTY")
    private BigDecimal splitqty;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("STSETID")
    private Long stsetid;

    @TableField("QUONDAMID")
    private Long quondamid;

    @TableField("PLACEPOINTID")
    private Long placepointid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("QUBUFFID")
    private Long qubuffid;


    @Override
    protected Serializable pkVal() {
        return this.splitid;
    }

}
