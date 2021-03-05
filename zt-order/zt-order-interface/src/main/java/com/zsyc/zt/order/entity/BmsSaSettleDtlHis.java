package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("BMS_SA_SETTLE_DTL_HIS")
public class BmsSaSettleDtlHis extends Model<BmsSaSettleDtlHis> {

    private static final long serialVersionUID = 1L;

    @TableField("SASETTLEID")
    private Long sasettleid;

    @TableField("SASETTLEDTLID")
    private Long sasettledtlid;

    @TableField("PRINTNO")
    private String printno;

    @TableField("PRINTLINE")
    private Integer printline;

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

    @TableField("TAXMONEY")
    private BigDecimal taxmoney;

    @TableField("NOTAXMONEY")
    private BigDecimal notaxmoney;

    @TableField("SALERID")
    private Long salerid;

    @TableField("SALESDEPTID")
    private Long salesdeptid;

    @TableField("COSTINGMONEY")
    private BigDecimal costingmoney;

    @TableField("DTLMEMO")
    private String dtlmemo;

    @TableField("RECFINFLAG")
    private Integer recfinflag;

    @TableField("SHOULESETTLEMONEY")
    private BigDecimal shoulesettlemoney;

    @TableField("INVDATE")
    private LocalDateTime invdate;

    @TableField("INVNO")
    private String invno;

    @TableField("TOTALRECQTY")
    private BigDecimal totalrecqty;

    @TableField("TOTALRECMONEY")
    private BigDecimal totalrecmoney;

    @TableField("CORRECTFLAG")
    private Integer correctflag;

    @TableField("SKCERTID")
    private Long skcertid;

    @TableField("TAXFLAG")
    private Integer taxflag;

    @TableField("INVALIDFLAG")
    private Integer invalidflag;

    @TableField("INVALIDMANID")
    private Long invalidmanid;

    @TableField("INVALIDTIME")
    private LocalDateTime invalidtime;

    @TableField("COSTINGPRICE")
    private BigDecimal costingprice;

    @TableField("OLDSASETTLEDTLID")
    private Long oldsasettledtlid;

    @TableField("ADJSTFLAG")
    private Integer adjstflag;

    @TableField("OLDMONEY")
    private BigDecimal oldmoney;

    @TableField("BILLID")
    private Long billid;

    @TableField("DISCOUNTCOST")
    private BigDecimal discountcost;

    @TableField("INVPRICE")
    private BigDecimal invprice;

    @TableField("INVCODE")
    private String invcode;

    @TableField("IMPDTLID")
    private Long impdtlid;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
