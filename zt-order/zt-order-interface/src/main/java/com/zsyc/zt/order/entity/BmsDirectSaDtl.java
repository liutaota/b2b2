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
@TableName("BMS_DIRECT_SA_DTL")
public class BmsDirectSaDtl extends Model<BmsDirectSaDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("DTLID")
    private Long dtlid;

    @TableField("DOCID")
    private Long docid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("SUPRICE")
    private BigDecimal suprice;

    @TableField("SAPRICE")
    private BigDecimal saprice;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("SUTAXRATE")
    private BigDecimal sutaxrate;

    @TableField("SATAXRATE")
    private BigDecimal sataxrate;

    @TableField("LOTID")
    private Long lotid;

    @TableField("LOTNO")
    private String lotno;

    @TableField("PRODDATE")
    private LocalDateTime proddate;

    @TableField("INVALIDDATE")
    private LocalDateTime invaliddate;

    @TableField("SUCONDOCID")
    private Long sucondocid;

    @TableField("SUCONDTLID")
    private Long sucondtlid;

    @TableField("STRGDOCID")
    private Long strgdocid;

    @TableField("STRGDTLID")
    private Long strgdtlid;

    @TableField("SUDOCID")
    private Long sudocid;

    @TableField("SUDTLID")
    private Long sudtlid;

    @TableField("STINDOCID")
    private Long stindocid;

    @TableField("STINDTLID")
    private Long stindtlid;

    @TableField("SALESID")
    private Long salesid;

    @TableField("SALEDTLID")
    private Long saledtlid;

    @TableField("STOUTDOCID")
    private Long stoutdocid;

    @TableField("STOUTDTLID")
    private Long stoutdtlid;

    @TableField("FINISHFLAG")
    private Integer finishflag;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("GOODSUSEQTY")
    private BigDecimal goodsuseqty;

    @TableField("GOODSUSEUNIT")
    private String goodsuseunit;

    @TableField("SUTOTALLINE")
    private BigDecimal sutotalline;

    @TableField("SATOTALLINE")
    private BigDecimal satotalline;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("SAPRICEID")
    private Long sapriceid;

    @TableField("TRID")
    private Long trid;

    @TableField("TRDTLID")
    private Long trdtlid;


    @Override
    protected Serializable pkVal() {
        return this.dtlid;
    }

}
