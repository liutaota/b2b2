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
@TableName("BMS_CHANGEPLANPAYDATE_DTL")
public class BmsChangeplanpaydateDtl extends Model<BmsChangeplanpaydateDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("CHANGEDTLID")
    private Long changedtlid;

    @TableField("CHANGEID")
    private Long changeid;

    @TableField("DLTTYPE")
    private Integer dlttype;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("PAYMETHOD")
    private Integer paymethod;

    @TableField("PAYLIMIT")
    private String paylimit;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("TOTAL")
    private BigDecimal total;

    @TableField("TYPE")
    private Integer type;

    @TableField("INVNO")
    private String invno;

    @TableField("DTLID")
    private Long dtlid;

    @TableField("DOCID")
    private Long docid;


    @Override
    protected Serializable pkVal() {
        return this.changedtlid;
    }

}
