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
@TableName("BMS_SU_SET_DTL")
public class BmsSuSetDtl extends Model<BmsSuSetDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("SUSETDTLID")
    private Long susetdtlid;

    @TableField("SUSETDOCID")
    private Long susetdocid;

    @TableField("PRINTNO")
    private String printno;

    @TableField("PRINTLINE")
    private Integer printline;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("DEPTID")
    private Long deptid;

    @TableField("GOODSID")
    private Long goodsid;

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

    @TableField("BUYCOST")
    private BigDecimal buycost;

    @TableField("EXPENSECOST")
    private BigDecimal expensecost;

    @TableField("CUSTOMSTAX")
    private BigDecimal customstax;

    @TableField("CUSTOMSMONEY")
    private BigDecimal customsmoney;

    @TableField("TAXMONEY")
    private BigDecimal taxmoney;

    @TableField("PAYACCQTY")
    private BigDecimal payaccqty;

    @TableField("PAYACCMONEY")
    private BigDecimal payaccmoney;

    @TableField("PAYFINFLAG")
    private Integer payfinflag;

    @TableField("PAYFINDATE")
    private LocalDateTime payfindate;

    @TableField("INVDATE")
    private LocalDateTime invdate;

    @TableField("CORRECTFLAG")
    private Integer correctflag;

    @TableField("INVNO")
    private String invno;

    @TableField("PAYLIMIT")
    private String paylimit;

    @TableField("PLNPAYDATE")
    private LocalDateTime plnpaydate;

    @TableField("MEMO")
    private String memo;

    @TableField("INVALIDFLAG")
    private Integer invalidflag;

    @TableField("INVALIDMANID")
    private Long invalidmanid;

    @TableField("INVALIDTIME")
    private LocalDateTime invalidtime;

    @TableField("PAYMETHOD")
    private Integer paymethod;

    @TableField("SUUNITPRICE")
    private BigDecimal suunitprice;

    @TableField("SUDOCDTLID")
    private Long sudocdtlid;

    @TableField("SUCOST")
    private BigDecimal sucost;

    @TableField("INVCODE")
    private String invcode;

    @TableField("SUPATCHED")
    private Integer supatched;

    @TableField("ADJSTFLAG")
    private Integer adjstflag;

    @TableField("OLDSUSETDTLID")
    private Long oldsusetdtlid;

    @TableField("IMPDTLID")
    private Long impdtlid;

    @TableField("INVTYPE")
    private Integer invtype;

    @TableField("TRANSDOCNO")
    private String transdocno;

    @TableField("ZX_PREPAYFLAG")
    private Integer zxPrepayflag;


    @Override
    protected Serializable pkVal() {
        return this.susetdtlid;
    }

}
