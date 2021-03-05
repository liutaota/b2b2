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
@TableName("BMS_ST_RG_DTL_EMF")
public class BmsStRgDtlEmf extends Model<BmsStRgDtlEmf> {

    private static final long serialVersionUID = 1L;

    @TableField("RGID")
    private Long rgid;

    @TableId("RGDTLID")
    private Long rgdtlid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("REALPRICE")
    private BigDecimal realprice;

    @TableField("CHECKMANID")
    private Long checkmanid;

    @TableField("CHECKDATE")
    private LocalDateTime checkdate;

    @TableField("TRANSTEP")
    private String transtep;

    @TableField("IMPORTREPFLAG")
    private Integer importrepflag;

    @TableField("PASSGATEFLAG")
    private Integer passgateflag;

    @TableField("REGISTEFLAG")
    private Integer registeflag;

    @TableField("GENQUCHKFLAG")
    private Integer genquchkflag;

    @TableField("LOWESTPRICE")
    private BigDecimal lowestprice;

    @TableField("LASTPRICE")
    private BigDecimal lastprice;

    @TableField("SUPPLYLASTPRICE")
    private BigDecimal supplylastprice;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("LIMITID")
    private Long limitid;

    @TableField("LIMITCUSTOMERSETID")
    private Long limitcustomersetid;

    @TableField("BANNEDCUSTOMERID")
    private Long bannedcustomerid;

    @TableField("BANNEDCUSTOMERSETID")
    private Long bannedcustomersetid;

    @TableField("SUCONDTLID")
    private Long sucondtlid;

    @TableField("FETCHDTLID")
    private Long fetchdtlid;

    @TableField("HASPRESENDFLAG")
    private Integer haspresendflag;

    @TableField("PRESENDINFO")
    private String presendinfo;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("POSID")
    private Long posid;

    @TableField("POSMEMO")
    private String posmemo;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("PACKQUALITY")
    private Integer packquality;

    @TableField("FACEQUALITY")
    private Integer facequality;

    @TableField("ASKCHKFLAG")
    private Long askchkflag;

    @TableField("PACKQTY")
    private BigDecimal packqty;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("SUCONGOODSQTY")
    private BigDecimal sucongoodsqty;

    @TableField("POSFLAG")
    private Integer posflag;

    @TableField("SALERID")
    private Long salerid;

    @TableField("QUALITYCHECKMANID")
    private Long qualitycheckmanid;

    @TableField("TARGETSOURCEID")
    private Long targetsourceid;

    @TableField("TARGETGOODSQTY")
    private BigDecimal targetgoodsqty;

    @TableField("WHOLEQTY")
    private BigDecimal wholeqty;

    @TableField("SCATTERQTY")
    private BigDecimal scatterqty;

    @TableField("UNQUALIFIEDMETHOD")
    private Integer unqualifiedmethod;

    @TableField("TRANSMETHOD")
    private Integer transmethod;

    @TableField("TRANSTIMES")
    private String transtimes;

    @TableField("TRANSTEMP")
    private String transtemp;

    @TableField("UNQUALIFIEDMEMO")
    private String unqualifiedmemo;

    @TableField("OLDRGDTLID")
    private Long oldrgdtlid;

    @TableField("QUALITYDATE")
    private LocalDateTime qualitydate;

    @TableField("ASKCHECKID")
    private Long askcheckid;

    @TableField("CHECKDOCID")
    private Long checkdocid;

    @TableField("MEMO")
    private String memo;

    @TableField("UNELIGIBGOODSQTY")
    private BigDecimal uneligibgoodsqty;

    @TableField("ELIGIBGOODSQTY")
    private BigDecimal eligibgoodsqty;

    @TableField("PUTAWAYFLAG")
    private Integer putawayflag;

    @TableField("SOURCEID")
    private Long sourceid;


    @Override
    protected Serializable pkVal() {
        return this.rgdtlid;
    }

}
