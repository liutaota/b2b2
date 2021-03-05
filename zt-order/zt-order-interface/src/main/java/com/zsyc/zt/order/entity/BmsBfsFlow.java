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
@TableName("BMS_BFS_FLOW")
public class BmsBfsFlow extends Model<BmsBfsFlow> {

    private static final long serialVersionUID = 1L;

    @TableId("FLOWID")
    private Long flowid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("LOTNO")
    private String lotno;

    @TableField("PRODDATE")
    private LocalDateTime proddate;

    @TableField("INVALIDDATE")
    private LocalDateTime invaliddate;

    @TableField("INOUTDTLID")
    private Long inoutdtlid;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("MEMO")
    private String memo;

    @TableField("APPUSESTATUS")
    private Integer appusestatus;

    @TableField("APPMEMO")
    private String appmemo;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("INPUTDATE")
    private LocalDateTime inputdate;

    @TableField("CONFIRMID")
    private Long confirmid;

    @TableField("CUSTOMNAME")
    private String customname;

    @TableField("GOODSUNIT")
    private String goodsunit;

    @TableField("INPUTTYPE")
    private Integer inputtype;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("FLOWTYPE")
    private Integer flowtype;

    @TableField("TMPID")
    private Long tmpid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("DOCID")
    private Long docid;

    @TableField("DTLID")
    private Long dtlid;


    @Override
    protected Serializable pkVal() {
        return this.flowid;
    }

}
