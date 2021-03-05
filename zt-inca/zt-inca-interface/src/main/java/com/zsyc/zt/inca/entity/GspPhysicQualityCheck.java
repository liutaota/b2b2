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
@TableName("GSP_PHYSIC_QUALITY_CHECK")
@ApiModel(value="GspPhysicQualityCheck对象", description="")
@KeySequence(value = "SEQ_GSP_PHYSIC_QUALITY_CHECK")
public class GspPhysicQualityCheck extends BaseBean {

    private static final long serialVersionUID = 1L;

    @TableId("CHECKID")
    private Long checkid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("QUCHECKID")
    private Long qucheckid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("GOODSQTY")
    private Double goodsqty;

    @TableField("GOODSUNIT")
    private String goodsunit;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("LOTID")
    private Long lotid;

    @TableField("CERTIFIEDFLAG")
    private Integer certifiedflag;

    @TableField("CHECKCONCLUSION")
    private Integer checkconclusion;

    @TableField("GOODSSTATUSID")
    private Long goodsstatusid;

    @TableField("CHECKMANID")
    private Long checkmanid;

    @TableField("SOURCETABLE")
    private Integer sourcetable;

    @TableField("REFUSEFLAG")
    private Integer refuseflag;

    @TableField("PRINTNO")
    private String printno;

    @TableField("MEMO")
    private String memo;

    @TableField("ARRIVEDATE")
    private LocalDateTime arrivedate;

    @TableField("CHECKDOCNO")
    private String checkdocno;

    @TableField("FACEQUALITY")
    private Integer facequality;

    @TableField("PACKQUALITY")
    private Integer packquality;

    @TableField("GSPFLAG")
    private Integer gspflag;

    @TableField("PRINTLINE")
    private Integer printline;

    @TableField("RGTYPE")
    private Integer rgtype;

    @TableField("BACKWHYID")
    private Long backwhyid;

    @TableField("CHECKMANID2")
    private Long checkmanid2;

    @TableField("IMPORTREPFLAG")
    private Integer importrepflag;

    @TableField("PASSGATEFLAG")
    private Integer passgateflag;

    @TableField("REGISTEFLAG")
    private Integer registeflag;

    @TableField("GENLQCHKFLAG")
    private Integer genlqchkflag;

    @TableField("RGDTLID")
    private Long rgdtlid;

    @TableField("RGID")
    private Long rgid;

    @TableField("CHECKDATE")
    private LocalDateTime checkdate;

    @TableField("GENCOLORCKFLAG")
    private Integer gencolorckflag;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("OPERATIONTYPE")
    private Long operationtype;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("WAREHID")
    private Long warehid;

    @TableField("GOODSOWNERID")
    private Long goodsownerid;

    @TableField("QUANSTATUS")
    private Integer quanstatus;

    @TableField("TRANSTEMP")
    private String transtemp;

    @TableField("MEDCHECKFLAG")
    private Integer medcheckflag;

    @TableField("ELIGIBILITYFLAG")
    private Integer eligibilityflag;

    @TableField("APPROVEDOCNO")
    private String approvedocno;

    @TableField("REGISTNO")
    private String registno;

    @TableField("RPTNO")
    private String rptno;

    @TableField("CHECKMANID1")
    private Long checkmanid1;

    @TableField("RECEIVEID")
    private Long receiveid;

    @TableField("DECISIONFLAG")
    private Long decisionflag;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("EXPFLAG")
    private Integer expflag;

    @TableField("EXPDATE")
    private LocalDateTime expdate;

    @TableField("EXPFILENAME")
    private String expfilename;

    @TableField("LQCCHECK")
    private Integer lqccheck;

    @TableField("PASSGATENO")
    private String passgateno;

    @TableField("PIJIANNO")
    private String pijianno;

    @TableField("OWNERGOODSID")
    private Long ownergoodsid;

    @TableField("SENDNO")
    private String sendno;

    @TableField("LPNCODE")
    private String lpncode;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("TRANSMETHOD")
    private Integer transmethod;

    @TableField("TRANSTIMES")
    private String transtimes;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("UNQUALIFIEDQTY")
    private Double unqualifiedqty;

    @TableField("COLDEQUIP")
    private Integer coldequip;

    @TableField("REACHDATE")
    private LocalDateTime reachdate;

    @TableField("STARTDATE")
    private LocalDateTime startdate;

    @TableField("STARTTEMPERATURE")
    private Double starttemperature;

    @TableField("REACHTEMPERATURE")
    private Double reachtemperature;


}
