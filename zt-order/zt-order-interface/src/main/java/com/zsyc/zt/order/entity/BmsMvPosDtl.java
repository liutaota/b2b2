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
@TableName("BMS_MV_POS_DTL")
public class BmsMvPosDtl extends Model<BmsMvPosDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("MVPOSID")
    private Long mvposid;

    @TableId("MVPOSDTLID")
    private Long mvposdtlid;

    @TableField("PRINTNO")
    private String printno;

    @TableField("PRINTLINE")
    private Integer printline;

    @TableField("PRINTTIME")
    private LocalDateTime printtime;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("FROMPOSID")
    private Long fromposid;

    @TableField("TOPOSID")
    private Long toposid;

    @TableField("DTLMEMO")
    private String dtlmemo;

    @TableField("TIMEQTY")
    private BigDecimal timeqty;

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
        return this.mvposdtlid;
    }

}
