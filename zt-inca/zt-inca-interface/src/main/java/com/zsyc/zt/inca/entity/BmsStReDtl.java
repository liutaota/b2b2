package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2020-07-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("BMS_ST_RE_DTL")
@ApiModel(value="BmsStReDtl对象", description="")
@KeySequence(value = "BMS_ST_RE_DTL_SEQ")
public class BmsStReDtl extends Model<BmsStReDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("REDOCID")
    private Long redocid;

    @TableId("REDTLID")
    private Long redtlid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("OLDGOODSQTY")
    private Double oldgoodsqty;

    @TableField("CANGOODSQTY")
    private Double cangoodsqty;

    @TableField("QUALIFIEDQTY")
    private Double qualifiedqty;

    @TableField("UNQUALIFIEDQTY")
    private Double unqualifiedqty;

    @TableField("GOODSQTY")
    private Double goodsqty;

    @TableField("HASPERSENDFLAG")
    private Integer haspersendflag;

    @TableField("PERSENDINFO")
    private String persendinfo;

    @TableField("SALERID")
    private Long salerid;

    @TableField("UNITPRICE")
    private Double unitprice;

    @TableField("TOTAL_LINE")
    private Double totalLine;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("PRESENTSTORAGEID")
    private Long presentstorageid;

    @TableField("ERASTORAGEID")
    private Long erastorageid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("PACKFALG")
    private Integer packfalg;

    @TableField("UNPACKFALG")
    private Integer unpackfalg;

    @TableField("SUCONDTLID")
    private Long sucondtlid;

    @TableField("FETCHDTLID")
    private Long fetchdtlid;

    @TableField("PLACERETURNDTLID")
    private Long placereturndtlid;

    @TableField("GSPUNCONFIRMFLAG")
    private Integer gspunconfirmflag;

    @TableField("REALPRICE")
    private Double realprice;

    @TableField("REALMONEY")
    private Double realmoney;

    @TableField("TWOINVOICEINFLAG")
    private Integer twoinvoiceinflag;

    @TableField("SUCONID")
    private Long suconid;

    @TableField("FETCHID")
    private Long fetchid;

    @TableField("UNINVQTY")
    private Double uninvqty;

    @TableField("FILECOUNT")
    private Long filecount;

    @TableField("UPLOADQTY")
    private Double uploadqty;

    @TableField("INVQTY")
    private Double invqty;

    @TableField("REALQTY")
    private Double realqty;

    @TableField("ZX_RECMANID")
    private Long zxRecmanid;

    @TableField("ZX_RECDATE")
    private LocalDateTime zxRecdate;

    @TableField("BACKWHYID")
    private Long backwhyid;

    @TableField("JD_ORDER_ID")
    private Double jdOrderId;


}
