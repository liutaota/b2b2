package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-01-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("BMS_TR_FETCH_DTL")
@ApiModel(value="BmsTrFetchDtl对象", description="")
@KeySequence(value = "BMS_TR_FETCH_DTL_SEQ")
public class BmsTrFetchDtl extends BaseBean {

    private static final long serialVersionUID = 1L;

    @TableField("FETCHID")
    private Long fetchid;

    @TableId("FETCHDTLID")
    private Long fetchdtlid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("GOODSNAME")
    private String goodsname;

    @TableField("GOODSQTY")
    private Double goodsqty;

    @TableField("INVALIDMANID")
    private Long invalidmanid;

    @TableField("INVALIDDATE")
    private LocalDateTime invaliddate;

    @TableField("SALEDTLID")
    private Long saledtlid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("DTLMEMO")
    private String dtlmemo;

    @TableField("IODTLID")
    private Long iodtlid;

    @TableField("PRICEID")
    private Long priceid;

    @TableField("UNITPRICE")
    private Double unitprice;

    @TableField("BATCHFLAG")
    private Integer batchflag;

    @TableField("BATCHPRICE")
    private Double batchprice;

    @TableField("SUCONDTLID")
    private Long sucondtlid;

    @TableField("WMSPRINTDATE")
    private LocalDateTime wmsprintdate;

    @TableField("DRIVERGETBACKDATE")
    private LocalDateTime drivergetbackdate;

    @TableField("DRIVERGETBACKDEMO")
    private String drivergetbackdemo;

    @TableField("BACKWHYID")
    private Integer backwhyid;

    @TableField("BACKWAYID")
    private Integer backwayid;

    @TableField("INQTY")
    private Double inqty;

    @TableField("REFUSEQTY")
    private Double refuseqty;

    @TableField("INTRUSTQTY")
    private Double intrustqty;

    @TableField("TOTAL_LINE")
    private Double totalLine;

    @TableField("COLDEQUIP")
    private Integer coldequip;

    @TableField("RECEIVEQTY")
    private Double receiveqty;

    @TableField("SENDWMSDATE")
    private LocalDateTime sendwmsdate;

    @TableField("WMSBACKDATE")
    private LocalDateTime wmsbackdate;

    @TableField("SENDWMSFLAG")
    private Integer sendwmsflag;

    @TableField("ACCQTY")
    private Double accqty;

    @TableField("UNQUALIFIEDQTY")
    private Double unqualifiedqty;

    @TableField("REUSESTATUS")
    private Integer reusestatus;

    @TableField("RGUSESTATUS")
    private Integer rgusestatus;

    @TableField("ZX_SALESDOCID")
    private Long zxSalesdocid;

    @TableField("ZX_SALECREDATE")
    private LocalDateTime zxSalecredate;

    @TableField("ZX_TAXRATE")
    private Double zxTaxrate;


}
