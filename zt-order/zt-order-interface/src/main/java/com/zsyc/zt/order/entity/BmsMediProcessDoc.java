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
@TableName("BMS_MEDI_PROCESS_DOC")
public class BmsMediProcessDoc extends Model<BmsMediProcessDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("PROCESSDOCID")
    private Long processdocid;

    @TableField("FORMULADOCID")
    private Long formuladocid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("TOSTOREID")
    private Long tostoreid;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("EXCHANGEMANID")
    private Long exchangemanid;

    @TableField("EXCHANGEDATE")
    private LocalDateTime exchangedate;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("POSID")
    private Long posid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("PROCESS_TOTAL")
    private BigDecimal processTotal;

    @TableField("MEMO")
    private String memo;

    @TableField("PLACEPOINTID")
    private Long placepointid;


    @Override
    protected Serializable pkVal() {
        return this.processdocid;
    }

}
