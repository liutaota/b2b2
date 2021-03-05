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
@TableName("BMS_SU_PLAN_IMP_TMP")
public class BmsSuPlanImpTmp extends Model<BmsSuPlanImpTmp> {

    private static final long serialVersionUID = 1L;

    @TableField("DOCID")
    private Long docid;

    @TableId("TMPID")
    private Long tmpid;

    @TableField("ERRMSG")
    private String errmsg;

    @TableField("IMPFLAG")
    private Integer impflag;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("SUPPLYNAME")
    private String supplyname;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("AGENTNAME")
    private String agentname;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("PROTOCALDTLID")
    private Long protocaldtlid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSNAME")
    private String goodsname;

    @TableField("GOODSTYPE")
    private String goodstype;

    @TableField("GOODSUNIT")
    private String goodsunit;

    @TableField("PROAREA")
    private String proarea;

    @TableField("FACTORYNAME")
    private String factoryname;

    @TableField("PROTOCALNO")
    private String protocalno;

    @TableField("PRICE")
    private BigDecimal price;

    @TableField("PLANID")
    private Long planid;

    @TableField("CURRENCYNAME")
    private String currencyname;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("ENTRYNAME")
    private String entryname;

    @TableField("PLANDOCID")
    private Long plandocid;


    @Override
    protected Serializable pkVal() {
        return this.tmpid;
    }

}
