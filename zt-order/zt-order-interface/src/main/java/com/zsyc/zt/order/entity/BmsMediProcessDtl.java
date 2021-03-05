package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("BMS_MEDI_PROCESS_DTL")
public class BmsMediProcessDtl extends Model<BmsMediProcessDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("PROCESSDTLID")
    private Long processdtlid;

    @TableField("PROCESSDOCID")
    private Long processdocid;

    @TableField("FORMULADTLID")
    private Long formuladtlid;

    @TableField("COSTPRICE")
    private BigDecimal costprice;

    @TableField("REALUSEQTY")
    private BigDecimal realuseqty;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("OUTTOTAL")
    private BigDecimal outtotal;

    @TableField("FROMSTOREID")
    private Long fromstoreid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("MEMO")
    private String memo;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("POSID")
    private Long posid;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;


    @Override
    protected Serializable pkVal() {
        return this.processdtlid;
    }

}
