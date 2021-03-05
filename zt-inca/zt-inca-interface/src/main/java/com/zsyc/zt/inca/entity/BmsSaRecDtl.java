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
@TableName("BMS_SA_REC_DTL")
@ApiModel(value="BmsSaRecDtl对象", description="")
@KeySequence(value = "BMS_SA_REC_DTL_SEQ")
public class BmsSaRecDtl extends BaseBean {

    private static final long serialVersionUID = 1L;

    @TableField("SARECID")
    private Long sarecid;

    @TableId("SARECDTLID")
    private Long sarecdtlid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSQTY")
    private Double goodsqty;

    @TableField("UNITPRICE")
    private Double unitprice;

    @TableField("TOTAL_LINE")
    private Double totalLine;

    @TableField("SALERID")
    private Long salerid;

    @TableField("SALESDEPTID")
    private Long salesdeptid;

    @TableField("DTLMEMO")
    private String dtlmemo;

    @TableField("CERTID")
    private Long certid;

    @TableField("CDATE")
    private LocalDateTime cdate;

    @TableField("RECSALERID")
    private Long recsalerid;

    @TableField("RECSALESDEPTID")
    private Long recsalesdeptid;

    @TableField("EXCHANGE")
    private Double exchange;

    @TableField("INVALIDFLAG")
    private Integer invalidflag;

    @TableField("INVALIDMANID")
    private Long invalidmanid;

    @TableField("INVALIDTIME")
    private LocalDateTime invalidtime;

    @TableField("PRINTNO")
    private String printno;

    @TableField("PRINTLINE")
    private Integer printline;

    @TableField("CORRECTFLAG")
    private Integer correctflag;

    @TableField("TRANSDOCNO")
    private String transdocno;


}
