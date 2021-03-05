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
 * @since 2020-08-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("BMS_SA_SETTLE_DTL")
@ApiModel(value="BmsSaSettleDtl对象", description="")
@KeySequence(value = "BMS_SA_SETTLE_DTL_SEQ")
public class BmsSaSettleDtl extends BaseBean {

    private static final long serialVersionUID = 1L;

    @TableField("SASETTLEID")
    private Long sasettleid;

    @TableId("SASETTLEDTLID")
    private Long sasettledtlid;

    @TableField("PRINTNO")
    private String printno;

    @TableField("PRINTLINE")
    private Integer printline;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSQTY")
    private Double goodsqty;

    @TableField("UNITPRICE")
    private Double unitprice;

    @TableField("TOTAL_LINE")
    private Double totalLine;

    @TableField("TAXRATE")
    private Double taxrate;

    @TableField("TAXMONEY")
    private Double taxmoney;

    @TableField("NOTAXMONEY")
    private Double notaxmoney;

    @TableField("SALERID")
    private Long salerid;

    @TableField("SALESDEPTID")
    private Long salesdeptid;

    @TableField("COSTINGMONEY")
    private Double costingmoney;

    @TableField("DTLMEMO")
    private String dtlmemo;

    @TableField("RECFINFLAG")
    private Integer recfinflag;

    @TableField("SHOULESETTLEMONEY")
    private Double shoulesettlemoney;

    @TableField("TOTALRECQTY")
    private Double totalrecqty;

    @TableField("TOTALRECMONEY")
    private Double totalrecmoney;

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
    private Double costingprice;

    @TableField("OLDSASETTLEDTLID")
    private Long oldsasettledtlid;

    @TableField("ADJSTFLAG")
    private Integer adjstflag;

    @TableField("OLDMONEY")
    private Double oldmoney;

    @TableField("BILLID")
    private Long billid;

    @TableField("DISCOUNTCOST")
    private Double discountcost;

    @TableField("INVPRICE")
    private Double invprice;

    @TableField("INVCODE")
    private String invcode;

    @TableField("IMPDTLID")
    private Long impdtlid;

    @TableField("SETTLETYPEID")
    private Integer settletypeid;

    @TableField("SETTYPE")
    private Integer settype;

    @TableField("TRANSDOCNO")
    private String transdocno;


}
