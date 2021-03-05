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
@TableName("BMS_RG_NOTICE_DTL")
public class BmsRgNoticeDtl extends Model<BmsRgNoticeDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("NOTICEDOCID")
    private Long noticedocid;

    @TableId("NOTICEDTLID")
    private Long noticedtlid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("GOODSNAME")
    private String goodsname;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("SOURCEDTLID")
    private Long sourcedtlid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("DTLMEMO")
    private String dtlmemo;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("IODTLID")
    private Long iodtlid;

    @TableField("PRICEID")
    private Long priceid;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("BATCHFLAG")
    private Integer batchflag;

    @TableField("BATCHPRICE")
    private BigDecimal batchprice;

    @TableField("CANFETCHQTY")
    private BigDecimal canfetchqty;


    @Override
    protected Serializable pkVal() {
        return this.noticedtlid;
    }

}
