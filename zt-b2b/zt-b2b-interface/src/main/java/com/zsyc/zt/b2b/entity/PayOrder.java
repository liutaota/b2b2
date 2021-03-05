package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.sql.Clob;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 支付订单
 * </p>
 *
 * @author MP
 * @since 2020-11-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("PAY_ORDER")
@ApiModel(value="PayOrder对象", description="支付订单")
@KeySequence(value = "SEQ_PAY_ORDER")
public class PayOrder extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId("ID")
    private Long id;

    /**
     * 商户号
     */
    @ApiModelProperty(value = "商户号")
    @TableField("MERCHANT_ID")
    private String merchantId;

    /**
     * 商户key(appid)
     */
    @ApiModelProperty(value = "商户key(appid)")
    @TableField("MERCHANT_KEY")
    private String merchantKey;

    /**
     * 业务订单号
     */
    @ApiModelProperty(value = "业务订单号")
    @TableField("ORDER_NO")
    private String orderNo;

    /**
     * 支付订单号
     */
    @ApiModelProperty(value = "支付订单号")
    @TableField("PAY_FLOW_NO")
    private String payFlowNo;

    /**
     * 币种
     */
    @ApiModelProperty(value = "币种")
    @TableField("FEE_TYPE")
    private String feeType;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    @TableField("TOTAL_FEE")
    private Long totalFee;

    /**
     * 退款金额
     */
    @ApiModelProperty(value = "退款金额")
    @TableField("REFUND_FEE")
    private Long refundFee;

    /**
     * 交易起始时间	
     */
    @ApiModelProperty(value = "交易起始时间	")
    @TableField("TIME_START")
    private LocalDateTime timeStart;

    /**
     * 交易过期时间
     */
    @ApiModelProperty(value = "交易过期时间")
    @TableField("TIME_EXPIRE")
    private LocalDateTime timeExpire;

    /**
     * 交易完成时间	
     */
    @ApiModelProperty(value = "交易完成时间	")
    @TableField("TIME_END")
    private LocalDateTime timeEnd;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("STATUS")
    private String status;

    /**
     * 通知地址
     */
    @ApiModelProperty(value = "通知地址")
    @TableField("NOTIFY_URL")
    private String notifyUrl;

    /**
     * 数据来源
     */
    @ApiModelProperty(value = "数据来源")
    @TableField("DATA_SOURCE")
    private String dataSource;

    /**
     * 支付方式
     */
    @ApiModelProperty(value = "支付方式")
    @TableField("PAY_TYPE")
    private String payType;

    /**
     * 扩展内容
     */
    @ApiModelProperty(value = "扩展内容")
    @TableField("META_DATA")
    private Clob metaData;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * 商品描述	
     */
    @ApiModelProperty(value = "商品描述	")
    @TableField("BODY")
    private String body;


}
