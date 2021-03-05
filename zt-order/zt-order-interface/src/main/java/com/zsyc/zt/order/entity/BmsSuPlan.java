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
@TableName("BMS_SU_PLAN")
public class BmsSuPlan extends Model<BmsSuPlan> {

    private static final long serialVersionUID = 1L;

    @TableId("PLANID")
    private Long planid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("PLANDATE")
    private LocalDateTime plandate;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("ARRIVEDATE")
    private LocalDateTime arrivedate;

    @TableField("PAYLIMIT")
    private String paylimit;

    @TableField("CLAUSE")
    private String clause;

    @TableField("MEMO")
    private String memo;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("SUCONDTLID")
    private Long sucondtlid;

    @TableField("GOODSUSEQTY")
    private BigDecimal goodsuseqty;

    @TableField("PROTOCALDTLID")
    private Long protocaldtlid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("PLANDOCID")
    private Long plandocid;

    @TableField("ASKPRICEID")
    private Long askpriceid;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("PAYMETHOD")
    private Integer paymethod;

    @TableField("SETTLETYPEID")
    private Integer settletypeid;

    @TableField("RULE")
    private Integer rule;

    @TableField("VALIDITY")
    private Long validity;

    @TableField("HYCOUNT")
    private Long hycount;

    @TableField("UPQTY")
    private BigDecimal upqty;

    @TableField("DOWNQTY")
    private BigDecimal downqty;

    @TableField("COLLIGATEAVGSALEQTY")
    private BigDecimal colligateavgsaleqty;

    @TableField("CANSALEQTY")
    private BigDecimal cansaleqty;

    @TableField("REBATEPOLICY")
    private String rebatepolicy;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("DEPTID")
    private Long deptid;

    @TableField("SUMONEY")
    private BigDecimal sumoney;

    @TableField("COSTINGPRICE")
    private BigDecimal costingprice;

    @TableField("COSTINGMONEY")
    private BigDecimal costingmoney;

    @TableField("GOODSUSEUNIT")
    private String goodsuseunit;

    @TableField("SUPPLYTAXRATE")
    private BigDecimal supplytaxrate;

    @TableField("ISPROTOCAL")
    private Integer isprotocal;

    @TableField("USEPACKSIZE")
    private BigDecimal usepacksize;

    @TableField("PLANQTY")
    private BigDecimal planqty;

    @TableField("EMPID")
    private Long empid;

    @TableField("INVALIDMANID")
    private Long invalidmanid;

    @TableField("INVALIDDATE")
    private LocalDateTime invaliddate;

    @TableField("HYEXPIRYDATE")
    private LocalDateTime hyexpirydate;

    @TableField("PAYPARAM")
    private String payparam;


    @Override
    protected Serializable pkVal() {
        return this.planid;
    }

}
