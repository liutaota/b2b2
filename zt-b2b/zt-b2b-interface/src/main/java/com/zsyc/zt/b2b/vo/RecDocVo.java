package com.zsyc.zt.b2b.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.zt.b2b.entity.RecDoc;
import com.zsyc.zt.b2b.entity.RecDtl;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by tang on 2020/11/23.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "RecDocVo", description = "收款总单")
public class RecDocVo extends RecDoc {
    @ApiModelProperty(value = "退款单状态")
    private String refundStatus;

    @ApiModelProperty(value = "确认人id")
    private Long confirmErpId;

    @ApiModelProperty(value = "确认人")
    private String   confirmName;

    @ApiModelProperty(value = "待确认")
    private String confirm;

    @ApiModelProperty(value = "客户名称")
    private String  userName;

    @ApiModelProperty(value = "手机号")
    private String telephone;

    @ApiModelProperty(value = "客户id")
    private Long erpUserId;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "支付交易码")
    @TableField("PAY_TYPE")
    private String payType;

    @ApiModelProperty(value = "支付交易码说明")
    @TableField("PAY_TYPE_DOC")
    private String payTypeDoc;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    @TableField("PAY_METHOD")
    private String payMethod;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 收款细单
     */
    List<RecDtlVo> recDtlList;

    @ApiModelProperty(value = "退款单号")
    private String applyNo;

    @ApiModelProperty(value = "退款人")
    private String refundName;

    @ApiModelProperty(value = "核销人")
    private String financeName;

    /**
     * 支付流水号
     */
    @ApiModelProperty(value = "支付流水号")
    private String payFlowNo;

    /**
     * 1,已核销，0未核销,2核销中，3核销失败
     */
    @ApiModelProperty(value = "核销是否通过")
    private String financeTrues;


    @ApiModelProperty(value = "订单状态")
    private String  orderState;


    /**
     * 异常状态
     */
    @ApiModelProperty(value = "异常状态")
    private String expStatus;

    /**
     * 异常备注
     */
    @ApiModelProperty(value = "异常备注")
    private String expRemark;

}
