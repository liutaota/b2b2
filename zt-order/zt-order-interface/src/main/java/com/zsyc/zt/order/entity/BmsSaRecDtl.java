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
@TableName("BMS_SA_REC_DTL")
public class BmsSaRecDtl extends Model<BmsSaRecDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("SARECID")
    private Long sarecid;

    @TableId("SARECDTLID")
    private Long sarecdtlid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("TOTAL_LINE")
    private BigDecimal totalLine;

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
    private BigDecimal exchange;

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


    @Override
    protected Serializable pkVal() {
        return this.sarecdtlid;
    }

}
