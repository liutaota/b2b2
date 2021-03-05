package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.util.Date;

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
 * @since 2020-07-25
 */
@Data
@Accessors(chain = true)
@TableName("BMS_PRES_OUT_DOC")
@ApiModel(value="BmsPresOutDoc对象", description="")
@KeySequence(value = "BMS_PRES_OUT_DOCID_SEQ")
public class BmsPresOutDoc {

    private static final long serialVersionUID = 1L;

    @TableId("PRESOUTID")
    private Long presoutid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("ENTRYID")
    private Integer entryid;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("CUSTOMNAME")
    private String customname;

    @TableField("POLICYTYPE")
    private Integer policytype;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("DTL_LINES")
    private Integer dtlLines;

    @TableField("TARGETDATE")
    private LocalDateTime targetdate;

    @TableField("PRINTFLAG")
    private Integer printflag;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("CONFIRMMANID")
    private Long confirmmanid;

    @TableField("CONFIRMDATE")
    private LocalDateTime confirmdate;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("MEMO")
    private String memo;

    @TableField("SOURCETABLE")
    private Integer sourcetable;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("POLICYID")
    private Long policyid;

    @TableField("PLACEPOINTID")
    private Long placepointid;

    @TableField("PLACESUPPLYID")
    private Long placesupplyid;

    @TableField("ZX_BH_FLAG")
    private Integer zxBhFlag;

    @TableField("ZX_ORDERNUMBER")
    private String zxOrdernumber;

    @TableField("CMDSETID")
    private String cmdsetid;

    @TableField("ZXDOCCOL1")
    private String zxdoccol1;

    @TableField("ZXDOCCOL2")
    private String zxdoccol2;

    @TableField("ZXDOCCOL3")
    private String zxdoccol3;

    @TableField("JD_ORDER_ID")
    private Long jdOrderId;

    @TableField("JD_WRITE_BACK_FLAG")
    private Integer jdWriteBackFlag;

    /**
     * 2 自动确认成功 3  不成功
     */
    @ApiModelProperty(value = "2 自动确认成功 3  不成功")
    @TableField("AUTO_CONFIRM")
    private Integer autoConfirm;

    /**
     * 1 初始 2 下发物流  3 出库
     */
    @ApiModelProperty(value = "1 初始 2 下发物流  3 出库")
    @TableField("B2B_WRITE_BACK_FLAG")
    private Integer b2bWriteBackFlag;

    @TableField("B2B_ORDER_ID")
    private Long b2bOrderId;

    /**
     * b2b总额度
     */
    @ApiModelProperty(value = "b2b总额度")
    @TableField("B2B_AMOUNT_TOTAL")
    private Double b2bAmountTotal;

    /**
     * b2b优惠额度
     */
    @ApiModelProperty(value = "b2b优惠额度")
    @TableField("B2B_AMOUNT_DISCOUNT")
    private Double b2bAmountDiscount;

    /**
     * b2b付额度
     */
    @ApiModelProperty(value = "b2b付额度")
    @TableField("B2B_AMOUNT_PAY")
    private Double b2bAmountPay;

    /**
     * b2b快递额度
     */
    @ApiModelProperty(value = "b2b快递额度")
    @TableField("B2B_AMOUNT_DELIVERY")
    private Double b2bAmountDelivery;

    @TableField("B2B_ORDER_TYPE")
    private String b2bOrderType;

    @TableField("B2B_ORDER_NO")
    private String b2bOrderNo;

    @TableField("B2B_PAY_TYPE")
    private String b2bPayType;

    @TableField("B2B_STORE_ID")
    private Long b2bStoreId;


    @TableField("B2B_ORDER_FROM")
    private Integer b2bOrderFrom;
    @TableField("B2B_PAY_ORDER_NO")
    private String b2bPayOrderNo;
    @TableField("B2B_PAY_FLOW_NO")
    private String b2bPayFlowNo;

    @TableField("API_ORDER_ID")
    private Long apiOrderId;

    @TableField("API_ORDER_NO")
    private String apiOrderNo;

    @TableField("API_ORDER_TIME")
    private LocalDateTime apiOrderTime;

}
