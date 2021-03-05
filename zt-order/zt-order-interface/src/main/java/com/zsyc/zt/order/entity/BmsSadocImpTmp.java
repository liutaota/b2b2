package com.zsyc.zt.order.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.KeySequence;
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
@TableName("BMS_SADOC_IMP_TMP")
@KeySequence("BMS_SADOC_IMP_TMP_SEQ")
public class BmsSadocImpTmp extends Model<BmsSadocImpTmp> {

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

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("CUSTOMNAME")
    private String customname;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("AGENTNAME")
    private String agentname;

    @TableField("INVTYPE")
    private Integer invtype;

    @TableField("INVTYPENAME")
    private String invtypename;

    @TableField("SETTLETYPEID")
    private Integer settletypeid;

    @TableField("SETTLETYPE")
    private String settletype;

    @TableField("SALERID")
    private Long salerid;

    @TableField("SALERNAME")
    private String salername;

    @TableField("SALESDEPTID")
    private Long salesdeptid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("ENTRYNAME")
    private String entryname;

    @TableField("FMID")
    private Integer fmid;

    @TableField("FMNAME")
    private String fmname;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSNAME")
    private String goodsname;

    @TableField("TAXRATE")
    private BigDecimal taxrate;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;

    @TableField("NOTAXMONEY")
    private BigDecimal notaxmoney;

    @TableField("PRICEID")
    private Long priceid;

    @TableField("PRICENAME")
    private String pricename;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("STORAGENAME")
    private String storagename;

    @TableField("DISCOUNT")
    private BigDecimal discount;

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

    @TableField("ISBACKFLAG")
    private Integer isbackflag;


    @Override
    protected Serializable pkVal() {
        return this.tmpid;
    }

}
