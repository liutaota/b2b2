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
@TableName("BMS_SA_SETTLE_DOC")
@ApiModel(value="BmsSaSettleDoc对象", description="")
@KeySequence(value = "BMS_SA_SETTLE_DOC_SEQ")
public class BmsSaSettleDoc extends BaseBean {

    private static final long serialVersionUID = 1L;

    @TableId("SASETTLEID")
    private Long sasettleid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("CUSTOMNAME")
    private String customname;

    @TableField("TOTAL")
    private Double total;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("DTL_LINES")
    private Integer dtlLines;

    @TableField("MEMO")
    private String memo;

    @TableField("CERTID")
    private Long certid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("INVTYPE")
    private Integer invtype;

    @TableField("INITFLAG")
    private Integer initflag;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("FMID")
    private Long fmid;

    @TableField("EXCHANGE")
    private Double exchange;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("ASSESSDATE")
    private LocalDateTime assessdate;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("SACERTID")
    private Long sacertid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("QUOTA")
    private Double quota;

    @TableField("INVCODE")
    private String invcode;

    @TableField("PRINTSITEID")
    private Long printsiteid;

    @TableField("RECEIVEDATE")
    private LocalDateTime receivedate;

    @TableField("RECEIVEMANID")
    private Long receivemanid;

    @TableField("INVALIDDATE")
    private LocalDateTime invaliddate;

    @TableField("INVALIDMANID")
    private Long invalidmanid;

    @TableField("REDFLAG")
    private Integer redflag;

    @TableField("PRINTCOUNT")
    private Long printcount;

    @TableField("TAXFLAG")
    private Integer taxflag;

    @TableField("TAXMANID")
    private Long taxmanid;

    @TableField("TAXDATE")
    private LocalDateTime taxdate;

    @TableField("INVNO")
    private String invno;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("ZX_TOTALRECMONEY")
    private Double zxTotalrecmoney;

    @TableField("ZX_INITSATUS")
    private Integer zxInitsatus;

    @TableField("ZX_INVSTATUS")
    private Integer zxInvstatus;

    @TableField("ZX_INVBACKDATE")
    private LocalDateTime zxInvbackdate;

    @TableField("ZX_INVDATE")
    private LocalDateTime zxInvdate;

    @TableField("ZX_ERRORMEMO")
    private String zxErrormemo;


}
