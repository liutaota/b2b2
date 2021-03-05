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
@TableName("BMS_SA_REC_DOC")
@ApiModel(value="BmsSaRecDoc对象", description="")
@KeySequence(value = "BMS_SA_REC_DOC_SEQ")
public class BmsSaRecDoc extends BaseBean {

    private static final long serialVersionUID = 1L;

    @TableId("SARECID")
    private Long sarecid;

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

    @TableField("RECTYPEID")
    private Integer rectypeid;

    @TableField("RECMETHOD")
    private Integer recmethod;

    @TableField("BANKBILLNO")
    private String bankbillno;

    @TableField("CERTID")
    private Long certid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("PREMONEY")
    private Double premoney;

    @TableField("RECEXPMONEY")
    private Double recexpmoney;

    @TableField("RECEXPTYPE")
    private Integer recexptype;

    @TableField("INITFLAG")
    private Integer initflag;

    @TableField("PRESALERID")
    private Long presalerid;

    @TableField("PRESADEPTID")
    private Long presadeptid;

    @TableField("BANKID")
    private Long bankid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("OLDPRERECID")
    private Long oldprerecid;

    @TableField("FMID")
    private Long fmid;

    @TableField("EXCHANGE")
    private Double exchange;

    @TableField("DRAFTDATE")
    private LocalDateTime draftdate;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("PRINTCOUNT")
    private Long printcount;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("FINISHFLAG")
    private Integer finishflag;

    @TableField("NOTESID")
    private Long notesid;

    @TableField("RECMANID")
    private Long recmanid;

    @TableField("NOTESNO")
    private String notesno;

    @TableField("IMPDTLID")
    private Long impdtlid;

    @TableField("B2BCONID")
    private Long b2bconid;

    @TableField("ORDERID")
    private String orderid;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("ROADDTLID")
    private Long roaddtlid;

    @TableField("SWIFTNUMBER")
    private String swiftnumber;

    @TableField("PAYTYPE")
    private String paytype;

    @TableField("ZX_INITSATUS")
    private Integer zxInitsatus;
    /**
     * 2 自动核销（b2b平台）s
     */
    @TableField("IS_AUTO_VERIFICATION")
    private Integer  isAutoVerification;
    /**
     * 2 自动核销（b2b平台）s
     */
    @TableField("zx_bh_flag")
    private Integer  zxBhFlag;

    /**
     * 2 自动核销（b2b平台）s
     */
    @TableField("B2B_ORDER_NO")
    private String  b2bOrderNo;
    @TableField("B2B_PAY_FLOW_NO")
    private String  b2bPayFlowNo;

    /**
     * 2 自动核销（b2b平台）s
     */
    @TableField("B2B_ORDER_ID")
    private Long  b2bOrderId;


}
