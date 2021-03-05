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
@TableName("BMS_MV_DTL")
public class BmsMvDtl extends Model<BmsMvDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("MOVEDTLID")
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

    @TableField("SENDWMSFLAG")
    private Integer sendwmsflag;

    @TableField("SENDWMSDATE")
    private LocalDateTime sendwmsdate;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("HANDLENAME")
    private String handlename;

    @TableField("SUDATE")
    private LocalDateTime sudate;

    @TableField("ZX_DTLMEMO")
    private String zxDtlmemo;


    @Override
    protected Serializable pkVal() {
        return this.movedtlid;
    }

}
