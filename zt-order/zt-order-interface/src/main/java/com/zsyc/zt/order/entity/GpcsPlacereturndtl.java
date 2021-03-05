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
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("GPCS_PLACERETURNDTL")
public class GpcsPlacereturndtl extends Model<GpcsPlacereturndtl> {

    private static final long serialVersionUID = 1L;

    @TableId("PLACERETURNDTLID")
    private Long placereturndtlid;

    @TableField("PLACERETURNID")
    private Long placereturnid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

    @TableField("GOODSUNIT")
    private String goodsunit;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("BACKWHYID")
    private Integer backwhyid;

    @TableField("OLDPLACEDTLID")
    private Long oldplacedtlid;

    @TableField("PLACEPRICEID")
    private Long placepriceid;

    @TableField("PLACEPRICE")
    private BigDecimal placeprice;

    @TableField("PLACEMONEY")
    private BigDecimal placemoney;

    @TableField("RESALEPRICE")
    private BigDecimal resaleprice;

    @TableField("LOTID")
    private Long lotid;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("POSID")
    private Long posid;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("APPROVEMANID")
    private Long approvemanid;

    @TableField("APPROVEDATE")
    private LocalDateTime approvedate;

    @TableField("APPROVEOPINION")
    private String approveopinion;

    @TableField("ACCSTORAGEID")
    private Long accstorageid;

    @TableField("PRINTNO")
    private String printno;

    @TableField("PRINTLINE")
    private Integer printline;

    @TableField("MEMO")
    private String memo;

    @TableField("ACCPOSID")
    private Long accposid;

    @TableField("OLDSUPPLYTINYID")
    private Long oldsupplytinyid;

    @TableField("DESTTABLE")
    private Long desttable;

    @TableField("DESTDTLID")
    private Long destdtlid;

    @TableField("GENBILLFLAG")
    private Integer genbillflag;

    @TableField("REFUSEMANID")
    private Long refusemanid;

    @TableField("REFUSEDATE")
    private LocalDateTime refusedate;

    @TableField("SENDFLAG")
    private Integer sendflag;

    @TableField("WMSSTATUS")
    private Integer wmsstatus;

    @TableField("APPROVESTATUS")
    private Integer approvestatus;

    @TableField("ACTUALQTY")
    private BigDecimal actualqty;

    @TableField("ECODE")
    private String ecode;

    @TableField("CANSALEQTY")
    private BigDecimal cansaleqty;

    @TableField("SENDWMSFLAG")
    private Integer sendwmsflag;

    @TableField("SENDWMSDATE")
    private LocalDateTime sendwmsdate;

    @TableField("WMSBACKDATE")
    private LocalDateTime wmsbackdate;

    @TableField("ACCMANID")
    private Long accmanid;

    @TableField("ACCDATE")
    private LocalDateTime accdate;

    @TableField("CHECKSTATUS")
    private Integer checkstatus;

    @TableField("CHECKDATE")
    private LocalDateTime checkdate;

    @TableField("CHECKMANID")
    private Long checkmanid;

    @TableField("CHECKMANID2")
    private Long checkmanid2;

    @TableField("RECHECKSTATUS")
    private Integer recheckstatus;

    @TableField("RECHECKMANID")
    private Long recheckmanid;

    @TableField("RECHECKDATE")
    private LocalDateTime recheckdate;

    @TableField("RECHECKMEMO")
    private String recheckmemo;

    @TableField("RECEIVEFLAG")
    private Integer receiveflag;


    @Override
    protected Serializable pkVal() {
        return this.placereturndtlid;
    }

}
