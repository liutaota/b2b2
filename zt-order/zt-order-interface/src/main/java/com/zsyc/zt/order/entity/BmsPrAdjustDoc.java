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
@TableName("BMS_PR_ADJUST_DOC")
public class BmsPrAdjustDoc extends Model<BmsPrAdjustDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("ADJUSTPRICEDOCID")
    private Long adjustpricedocid;

    @TableField("SUDOCDTLID")
    private Long sudocdtlid;

    @TableField("CREATEDATE")
    private LocalDateTime createdate;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("OLDBATCHID")
    private Long oldbatchid;

    @TableField("OLDSUPRICE")
    private BigDecimal oldsuprice;

    @TableField("OLDSUQTY")
    private BigDecimal oldsuqty;

    @TableField("ADJUSTREASON")
    private String adjustreason;

    @TableField("CENTERSTTOTALSQTY")
    private BigDecimal centersttotalsqty;

    @TableField("POINTSTTOTALSQTY")
    private BigDecimal pointsttotalsqty;

    @TableField("SUADJUSTQTY")
    private BigDecimal suadjustqty;

    @TableField("SAADJUSTSUMQTY")
    private BigDecimal saadjustsumqty;

    @TableField("DOCADJUSTPRICE")
    private BigDecimal docadjustprice;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("DOCMEMO")
    private String docmemo;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("INPUTDATE")
    private LocalDateTime inputdate;

    @TableField("MODIFYMANID")
    private Long modifymanid;

    @TableField("MODIFYDATE")
    private LocalDateTime modifydate;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("NEWBATCHID")
    private Long newbatchid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("CONTACTID")
    private Long contactid;


    @Override
    protected Serializable pkVal() {
        return this.adjustpricedocid;
    }

}
