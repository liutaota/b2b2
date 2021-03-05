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
@TableName("BMS_ST_RECEIVE_RECORD")
public class BmsStReceiveRecord extends Model<BmsStReceiveRecord> {

    private static final long serialVersionUID = 1L;

    @TableId("RECORDID")
    private Long recordid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("REID")
    private Long reid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("STORERID")
    private Long storerid;

    @TableField("MEMO")
    private String memo;

    @TableField("RETYPE")
    private Integer retype;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("FREIGHTBILLNO")
    private String freightbillno;

    @TableField("TRANSPORTID")
    private Long transportid;

    @TableField("TRANMETHOD")
    private Integer tranmethod;

    @TableField("STARTPLACE")
    private String startplace;

    @TableField("STARTDATE")
    private LocalDateTime startdate;

    @TableField("REACHDATE")
    private LocalDateTime reachdate;

    @TableField("REDTLID")
    private Long redtlid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("REALPRICE")
    private BigDecimal realprice;

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

    @TableField("HASPERSENDFLAG")
    private Integer haspersendflag;

    @TableField("PERSENDINFO")
    private String persendinfo;

    @TableField("GOODSDTLID")
    private Long goodsdtlid;

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

    @TableField("UNITPRICE")
    private BigDecimal unitprice;

    @TableField("SUCONGOODSQTY")
    private BigDecimal sucongoodsqty;

    @TableField("SALERID")
    private Long salerid;

    @TableField("DTLMEMO")
    private String dtlmemo;

    @TableField("UNQUALIFIEDMETHOD")
    private Integer unqualifiedmethod;

    @TableField("UNQUALTFIEDMEMO")
    private String unqualtfiedmemo;

    @TableField("BATCHID")
    private Long batchid;

    @TableField("BACKWHYID")
    private Integer backwhyid;

    @TableField("PACKQTY")
    private BigDecimal packqty;

    @TableField("WHOLEQTY")
    private BigDecimal wholeqty;

    @TableField("SCATTERQTY")
    private BigDecimal scatterqty;

    @TableField("UNQUALIFIEDQTY")
    private BigDecimal unqualifiedqty;

    @TableField("UNWHOLEQTY")
    private BigDecimal unwholeqty;

    @TableField("UNSCATTERQTY")
    private BigDecimal unscatterqty;

    @TableField("UNPACKQTY")
    private BigDecimal unpackqty;

    @TableField("UNINTRUSTQTY")
    private BigDecimal unintrustqty;

    @TableField("UNINTRUSTWHOLEQTY")
    private BigDecimal unintrustwholeqty;

    @TableField("UNINTRUSTSCATTERQTY")
    private BigDecimal unintrustscatterqty;

    @TableField("UNINTRUSTPACKQTY")
    private BigDecimal unintrustpackqty;

    @TableField("UNREFUSEQTY")
    private BigDecimal unrefuseqty;

    @TableField("UNREFUSEWHOLEQTY")
    private BigDecimal unrefusewholeqty;

    @TableField("UNREFUSESCATTERQTY")
    private BigDecimal unrefusescatterqty;

    @TableField("UNREFUSEPACKQTY")
    private BigDecimal unrefusepackqty;

    @TableField("FETCHGOODSQTY")
    private BigDecimal fetchgoodsqty;

    @TableField("STARTTEMPERATURE")
    private BigDecimal starttemperature;

    @TableField("REACHTEMPERATURE")
    private BigDecimal reachtemperature;

    @TableField("TEMPERATURESTATUS")
    private Integer temperaturestatus;

    @TableField("COLDEQUIP")
    private Long coldequip;

    @TableField("INVALIDDATE")
    private LocalDateTime invaliddate;

    @TableField("INVALIDMANID")
    private Long invalidmanid;

    @TableField("RECIEVEQTY")
    private BigDecimal recieveqty;

    @TableField("RECIEVEPACKQTY")
    private BigDecimal recievepackqty;

    @TableField("CONTACTID")
    private Long contactid;


    @Override
    protected Serializable pkVal() {
        return this.recordid;
    }

}
