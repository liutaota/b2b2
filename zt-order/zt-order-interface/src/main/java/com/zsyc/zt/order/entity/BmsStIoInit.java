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
@TableName("BMS_ST_IO_INIT")
public class BmsStIoInit extends Model<BmsStIoInit> {

    private static final long serialVersionUID = 1L;

    @TableId("INITID")
    private Long initid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("POSID")
    private Long posid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("SKINITFLAG")
    private Integer skinitflag;

    @TableField("COSTINGPRICE")
    private BigDecimal costingprice;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("SUPPLYTAXRATE")
    private BigDecimal supplytaxrate;

    @TableField("IMPORTID")
    private Long importid;

    @TableField("SUDATE")
    private LocalDateTime sudate;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("LOTNO")
    private String lotno;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.initid;
    }

}
