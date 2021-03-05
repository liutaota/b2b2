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
@TableName("BMS_ST_IO_DOC_TMP_BAK")
public class BmsStIoDocTmpBak extends Model<BmsStIoDocTmpBak> {

    private static final long serialVersionUID = 1L;

    @TableField("INOUTID")
    private Long inoutid;

    @TableField("TRDTLID")
    private Long trdtlid;

    @TableField("PRINTNO")
    private String printno;

    @TableField("PRINTLINE")
    private Integer printline;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("SOURCETABLE")
    private Integer sourcetable;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("COMPANYNAME")
    private String companyname;

    @TableField("KEEPDATE")
    private LocalDateTime keepdate;

    @TableField("KEEPMANID")
    private Long keepmanid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("INOUTFLAG")
    private Integer inoutflag;

    @TableField("INQTY")
    private BigDecimal inqty;

    @TableField("OUTQTY")
    private BigDecimal outqty;

    @TableField("OLDQTY")
    private BigDecimal oldqty;

    @TableField("GOODSUNIT")
    private String goodsunit;

    @TableField("GOODSWEIGHT")
    private BigDecimal goodsweight;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("PREPARESTATUS")
    private Integer preparestatus;

    @TableField("PLACETABLE")
    private Integer placetable;

    @TableField("PLACEDTLID")
    private Long placedtlid;

    @TableField("PLACEPOINTID")
    private Long placepointid;

    @TableField("MEMO")
    private String memo;

    @TableField("GROUPTABLE")
    private Integer grouptable;

    @TableField("GROUPDTLID")
    private Long groupdtlid;

    @TableField("GROUPCUSTOMERID")
    private Long groupcustomerid;

    @TableField("WMSPROFLAG")
    private Integer wmsproflag;

    @TableField("GSPUNCONFIRMFLAG")
    private Integer gspunconfirmflag;

    @TableField("UPSTATUS")
    private Integer upstatus;

    @TableField("RGDTLID")
    private Long rgdtlid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("CTRLTWOINVFLAG")
    private Integer ctrltwoinvflag;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
