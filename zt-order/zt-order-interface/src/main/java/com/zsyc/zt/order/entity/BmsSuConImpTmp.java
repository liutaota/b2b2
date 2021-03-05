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
@TableName("BMS_SU_CON_IMP_TMP")
public class BmsSuConImpTmp extends Model<BmsSuConImpTmp> {

    private static final long serialVersionUID = 1L;

    @TableField("DOCID")
    private Long docid;

    @TableId("IMPID")
    private Long impid;

    @TableField("ERRMSG")
    private String errmsg;

    @TableField("IMPFLAG")
    private Integer impflag;

    @TableField("SUCONNO")
    private String suconno;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("SUPPLYNAME")
    private String supplyname;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("AGENTNAME")
    private String agentname;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSNO")
    private String goodsno;

    @TableField("GOODSNAME")
    private String goodsname;

    @TableField("GOODSTYPE")
    private String goodstype;

    @TableField("PRICE")
    private BigDecimal price;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("ENTRYNAME")
    private String entryname;

    @TableField("RATE")
    private BigDecimal rate;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("SUPPLYER")
    private String supplyer;

    @TableField("ZXCOLNAME1")
    private String zxcolname1;

    @TableField("ZXCOLNAME2")
    private String zxcolname2;

    @TableField("ZXCOLNAME3")
    private String zxcolname3;

    @TableField("ZXCOLNAME4")
    private String zxcolname4;

    @TableField("ZXCOLNAME5")
    private String zxcolname5;

    @TableField("ZXCOLNAME6")
    private String zxcolname6;

    @TableField("ZXCOLNAME7")
    private String zxcolname7;

    @TableField("ZXCOLNAME8")
    private String zxcolname8;

    @TableField("ZXCOLNAME9")
    private String zxcolname9;

    @TableField("ZXCOLNAME10")
    private String zxcolname10;

    @TableField("CREDATE")
    private LocalDateTime credate;


    @Override
    protected Serializable pkVal() {
        return this.impid;
    }

}
