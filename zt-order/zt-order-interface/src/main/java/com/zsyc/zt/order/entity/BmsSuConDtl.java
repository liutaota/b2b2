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
@TableName("BMS_SU_CON_DTL")
public class BmsSuConDtl extends Model<BmsSuConDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("SUCONDTLID")
    private Long sucondtlid;

    @TableField("SUCONID")
    private Long suconid;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("DEPTID")
    private Long deptid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("GOODSUSEUNIT")
    private String goodsuseunit;

    @TableField("GOODSUSEQTY")
    private BigDecimal goodsuseqty;

    @TableField("TAXRATE")
    private BigDecimal taxrate;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;

    @TableField("CUSTOMSTAX")
    private BigDecimal customstax;

    @TableField("CUSTOMSMONEY")
    private BigDecimal customsmoney;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("FINDATE")
    private LocalDateTime findate;

    @TableField("ACCQTY")
    private BigDecimal accqty;

    @TableField("MEMO")
    private String memo;

    @TableField("INVALIDMANID")
    private Long invalidmanid;

    @TableField("INVALIDDATE")
    private LocalDateTime invaliddate;

    @TableField("AGREEDOCFLAG")
    private Integer agreedocflag;

    @TableField("LASTPRICE")
    private BigDecimal lastprice;

    @TableField("PRINTNO")
    private String printno;

    @TableField("PRINTLINE")
    private Integer printline;

    @TableField("LOWESTPRICE")
    private BigDecimal lowestprice;

    @TableField("SUPPLYLASTPRICE")
    private BigDecimal supplylastprice;

    @TableField("PAYMETHOD")
    private Long paymethod;

    @TableField("PAYLIMIT")
    private String paylimit;

    @TableField("RECEIVEQTY")
    private BigDecimal receiveqty;

    @TableField("WHOLESALEPRICE")
    private BigDecimal wholesaleprice;

    @TableField("RETAILPRICE")
    private BigDecimal retailprice;

    @TableField("CRETAILPRICE")
    private BigDecimal cretailprice;

    @TableField("LIMITID")
    private Long limitid;

    @TableField("PROTOCALDTLID")
    private Long protocaldtlid;

    @TableField("HASPRESENDFLAG")
    private Integer haspresendflag;

    @TableField("PRESENDINFO")
    private String presendinfo;

    @TableField("LIMITCUSTOMSETID")
    private Long limitcustomsetid;

    @TableField("USEPACKSIZE")
    private BigDecimal usepacksize;

    @TableField("BANNEDCUSTOMERID")
    private Long bannedcustomerid;

    @TableField("BANNEDCUSTOMERSETID")
    private Long bannedcustomersetid;

    @TableField("IMPDTLID")
    private Long impdtlid;

    @TableField("INQTY")
    private BigDecimal inqty;

    @TableField("REFUSEQTY")
    private BigDecimal refuseqty;

    @TableField("INTRUSTQTY")
    private BigDecimal intrustqty;

    @TableField("COLDEQUIP")
    private Integer coldequip;

    @TableField("SENDWMSFLAG")
    private Integer sendwmsflag;

    @TableField("SENDWMSDATE")
    private LocalDateTime sendwmsdate;

    @TableField("WMSBACKDATE")
    private LocalDateTime wmsbackdate;

    @TableField("REQUIREDDATE")
    private LocalDateTime requireddate;

    @TableField("EXPECTEDDATE")
    private LocalDateTime expecteddate;

    @TableField("LIMITAGENTID")
    private Long limitagentid;

    @TableField("BANNEDAGENTID")
    private Long bannedagentid;

    @TableField("LIMITCONTACTID")
    private Long limitcontactid;

    @TableField("BANNEDCONTACTID")
    private Long bannedcontactid;

    @TableField("UNQUALIFIEDQTY")
    private BigDecimal unqualifiedqty;

    @TableField("REUSESTATUS")
    private Integer reusestatus;

    @TableField("RGUSESTATUS")
    private Integer rgusestatus;

    @TableField("ZX_LOTNO")
    private String zxLotno;

    @TableField("ZX_PRODDATE")
    private LocalDateTime zxProddate;

    @TableField("ZX_INVALIDDATE")
    private LocalDateTime zxInvaliddate;

    @TableField("ZX_LOTID")
    private Long zxLotid;

    @TableField("BACKQTY")
    private BigDecimal backqty;

    @TableField("BACKMEMO")
    private String backmemo;

    /**
     * ???? 1????? 0 ???
     */
    @TableField("ZX_KP_FLAG")
    private Integer zxKpFlag;

    @TableField("SU_FLAG")
    private BigDecimal suFlag;


    @Override
    protected Serializable pkVal() {
        return this.sucondtlid;
    }

}
