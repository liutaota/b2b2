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
@TableName("BMS_DIRECT_SA_DOC")
public class BmsDirectSaDoc extends Model<BmsDirectSaDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("DOCID")
    private Long docid;

    @TableField("SUCONDATE")
    private LocalDateTime sucondate;

    @TableField("SUDATE")
    private LocalDateTime sudate;

    @TableField("KEEPINDATE")
    private LocalDateTime keepindate;

    @TableField("SADATE")
    private LocalDateTime sadate;

    @TableField("KEEPOUTDATE")
    private LocalDateTime keepoutdate;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("POSID")
    private Long posid;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("SALERID")
    private Long salerid;

    @TableField("INPUTDATE")
    private LocalDateTime inputdate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("MEMO")
    private String memo;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("DTL_LINES")
    private Integer dtlLines;

    @TableField("SUTOTAL")
    private BigDecimal sutotal;

    @TableField("SATOTAL")
    private BigDecimal satotal;

    @TableField("SUPPLYAGENTID")
    private Long supplyagentid;

    @TableField("CUSTOMAGENTID")
    private Long customagentid;

    @TableField("PLANINVDATE")
    private LocalDateTime planinvdate;

    @TableField("INVDEMAND")
    private Integer invdemand;

    @TableField("SETTLETYPEID")
    private Integer settletypeid;

    @TableField("INVTYPE")
    private Integer invtype;

    @TableField("SUSETTLETYPEID")
    private Integer susettletypeid;


    @Override
    protected Serializable pkVal() {
        return this.docid;
    }

}
