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
 * @since 2020-08-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("BMS_ST_RECEIVE_RECORD")
@ApiModel(value="BmsStReceiveRecord对象", description="")
@KeySequence(value = "SEQ_BMS_ST_RECEIVE_RECORD")
public class BmsStReceiveRecord extends BaseBean {

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
    private Double goodsqty;

    @TableField("REALPRICE")
    private Double realprice;

    @TableField("LOWESTPRICE")
    private Double lowestprice;

    @TableField("LASTPRICE")
    private Double lastprice;

    @TableField("SUPPLYLASTPRICE")
    private Double supplylastprice;

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
    private Double unitprice;

    @TableField("SUCONGOODSQTY")
    private Double sucongoodsqty;

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
    private Double packqty;

    @TableField("WHOLEQTY")
    private Double wholeqty;

    @TableField("SCATTERQTY")
    private Double scatterqty;

    @TableField("UNQUALIFIEDQTY")
    private Double unqualifiedqty;

    @TableField("UNWHOLEQTY")
    private Double unwholeqty;

    @TableField("UNSCATTERQTY")
    private Double unscatterqty;

    @TableField("UNPACKQTY")
    private Double unpackqty;

    @TableField("UNINTRUSTQTY")
    private Double unintrustqty;

    @TableField("UNINTRUSTWHOLEQTY")
    private Double unintrustwholeqty;

    @TableField("UNINTRUSTSCATTERQTY")
    private Double unintrustscatterqty;

    @TableField("UNINTRUSTPACKQTY")
    private Double unintrustpackqty;

    @TableField("UNREFUSEQTY")
    private Double unrefuseqty;

    @TableField("UNREFUSEWHOLEQTY")
    private Double unrefusewholeqty;

    @TableField("UNREFUSESCATTERQTY")
    private Double unrefusescatterqty;

    @TableField("UNREFUSEPACKQTY")
    private Double unrefusepackqty;

    @TableField("FETCHGOODSQTY")
    private Double fetchgoodsqty;

    @TableField("STARTTEMPERATURE")
    private Double starttemperature;

    @TableField("REACHTEMPERATURE")
    private Double reachtemperature;

    @TableField("TEMPERATURESTATUS")
    private Integer temperaturestatus;

    @TableField("COLDEQUIP")
    private Long coldequip;

    @TableField("INVALIDDATE")
    private LocalDateTime invaliddate;

    @TableField("INVALIDMANID")
    private Long invalidmanid;

    @TableField("RECIEVEQTY")
    private Double recieveqty;

    @TableField("RECIEVEPACKQTY")
    private Double recievepackqty;

    @TableField("CONTACTID")
    private Long contactid;


}
