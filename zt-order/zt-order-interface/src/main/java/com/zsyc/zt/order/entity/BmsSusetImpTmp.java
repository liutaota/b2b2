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
@TableName("BMS_SUSET_IMP_TMP")
public class BmsSusetImpTmp extends Model<BmsSusetImpTmp> {

    private static final long serialVersionUID = 1L;

    @TableId("TMPID")
    private Long tmpid;

    @TableField("DOCID")
    private Long docid;

    @TableField("IMPFLAG")
    private Integer impflag;

    @TableField("ERRORMESSAGE")
    private String errormessage;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("SUPPLYNAME")
    private String supplyname;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("AGENTMAN")
    private String agentman;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("ENTRYNAME")
    private String entryname;

    @TableField("SETTLEMODENAME")
    private String settlemodename;

    @TableField("SETTLEMODE")
    private Integer settlemode;

    @TableField("SETTLETYPEID")
    private Integer settletypeid;

    @TableField("SETTLETYPE")
    private String settletype;

    @TableField("FMID")
    private Long fmid;

    @TableField("EXCHANGE")
    private BigDecimal exchange;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSNAME")
    private String goodsname;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("SUPPLYERNAME")
    private String supplyername;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;

    @TableField("TAXRATE")
    private BigDecimal taxrate;

    @TableField("COSTPRICE")
    private BigDecimal costprice;

    @TableField("COST")
    private BigDecimal cost;

    @TableField("PAYMODE")
    private Integer paymode;

    @TableField("PAYMODENAME")
    private String paymodename;

    @TableField("PAYLIMIT")
    private Integer paylimit;

    @TableField("INVDATE")
    private LocalDateTime invdate;

    @TableField("INVNO")
    private String invno;

    @TableField("ZXCOLNUM1")
    private String zxcolnum1;

    @TableField("ZXCOLNUM2")
    private String zxcolnum2;

    @TableField("ZXCOLNUM3")
    private String zxcolnum3;

    @TableField("ZXCOLNUM4")
    private String zxcolnum4;

    @TableField("ZXCOLNUM5")
    private String zxcolnum5;

    @TableField("ZXCOLNUM6")
    private String zxcolnum6;

    @TableField("ZXCOLNUM7")
    private String zxcolnum7;

    @TableField("ZXCOLNUM8")
    private String zxcolnum8;

    @TableField("ZXCOLNUM9")
    private String zxcolnum9;

    @TableField("ZXCOLNUM10")
    private String zxcolnum10;


    @Override
    protected Serializable pkVal() {
        return this.tmpid;
    }

}
