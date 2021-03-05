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
 * @since 2021-01-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("BMS_TR_FETCH_DOC")
@ApiModel(value="BmsTrFetchDoc对象", description="")
@KeySequence(value = "BMS_TR_FETCH_DOC_SEQ")
public class BmsTrFetchDoc extends BaseBean {

    private static final long serialVersionUID = 1L;

    @TableId("FETCHID")
    private Long fetchid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("FETCHTYPE")
    private Integer fetchtype;

    @TableField("FETCHNO")
    private String fetchno;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("COMPANYNAME")
    private String companyname;

    @TableField("STORAGEID")
    private Integer storageid;

    @TableField("TRANSFLAG")
    private Integer transflag;

    @TableField("TRPOSID")
    private Long trposid;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("MEMO")
    private String memo;

    @TableField("WFUSESTATUS")
    private Integer wfusestatus;

    @TableField("WFPROCESS")
    private Integer wfprocess;

    @TableField("WFMEMO")
    private String wfmemo;

    @TableField("ENTRYID")
    private Integer entryid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("SALERID")
    private Long salerid;

    @TableField("SALESDEPTID")
    private Long salesdeptid;

    /**
     * 送货方式，0 送回 1 取货
     */
    @TableField("DELIVERMETHOD")
    private Integer delivermethod;

    @TableField("MIDPOSID")
    private Long midposid;

    @TableField("STORERID")
    private Long storerid;

    @TableField("NOWMSFLAG")
    private Integer nowmsflag;

    @TableField("PRINTCOUNT")
    private Long printcount;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("INVDEMAND")
    private Integer invdemand;

    @TableField("PLANINVDATE")
    private LocalDateTime planinvdate;

    @TableField("SETTLETYPEID")
    private Integer settletypeid;

    @TableField("INVTYPE")
    private Integer invtype;

    @TableField("EXPECTGETDATE")
    private LocalDateTime expectgetdate;

    @TableField("TRANMETHOD")
    private Integer tranmethod;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("ZX_TOTAL")
    private Double zxTotal;

    @TableField("ZX_BH_FLAG")
    private Integer zxBhFlag;

    @TableField("B2B_SRC_ORDER_NO")
    private String b2bSrcOrderNo;

    @TableField("B2B_SRC_ORDER_ID")
    private Long b2bSrcOrderId;

    @TableField("B2B_ORDER_NO")
    private String b2bOrderNo;

    @TableField("B2B_ORDER_ID")
    private Long b2bOrderId;


}
