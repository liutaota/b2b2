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
@TableName("BMS_MV_DTL_HIS")
public class BmsMvDtlHis extends Model<BmsMvDtlHis> {

    private static final long serialVersionUID = 1L;

    @TableField("MOVEDTLID")
    private Long movedtlid;

    @TableField("MOVEID")
    private Long moveid;

    @TableField("PRINTNO")
    private String printno;

    @TableField("PRINTLINE")
    private Integer printline;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("POSID")
    private Long posid;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("GOODSUSEUNIT")
    private String goodsuseunit;

    @TableField("GOODSUSEQTY")
    private BigDecimal goodsuseqty;

    @TableField("COSTINGPRICE")
    private BigDecimal costingprice;

    @TableField("COSTINGMONEY")
    private BigDecimal costingmoney;

    @TableField("COSTCALCFLAG")
    private Integer costcalcflag;

    @TableField("STIFLAG")
    private Integer stiflag;

    @TableField("STOFLAG")
    private Integer stoflag;

    @TableField("DTLMEMO")
    private String dtlmemo;

    @TableField("SKCERTID")
    private Long skcertid;

    @TableField("INVALIDFLAG")
    private Integer invalidflag;

    @TableField("INVALIDMANID")
    private Long invalidmanid;

    @TableField("INVALIDTIME")
    private LocalDateTime invalidtime;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("PLACESUPPLYDTLSTID")
    private Long placesupplydtlstid;

    @TableField("CMDID")
    private Long cmdid;

    @TableField("CMDMATID")
    private Long cmdmatid;

    @TableField("OUTSENDWMSFLAG")
    private Integer outsendwmsflag;

    @TableField("OUTSENDWMSDATE")
    private LocalDateTime outsendwmsdate;

    @TableField("OUTWMSBACKDATE")
    private LocalDateTime outwmsbackdate;

    @TableField("INSENDWMSFLAG")
    private Integer insendwmsflag;

    @TableField("INSENDWMSDATE")
    private LocalDateTime insendwmsdate;

    @TableField("INWMSBACKDATE")
    private LocalDateTime inwmsbackdate;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
