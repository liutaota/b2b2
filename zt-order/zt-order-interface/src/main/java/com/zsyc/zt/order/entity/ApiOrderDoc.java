package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 对接订单数据总单
 * </p>
 *
 * @author MP
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("API_ORDER_DOC")
@ApiModel(value="ApiOrderDoc对象", description="对接订单数据总单")
@KeySequence(value = "API_ORDER_DOC_SEQ")
public class ApiOrderDoc extends Model<ApiOrderDoc> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId("ID")
    private Long id;

    /**
     * b2b客户ID
     */
    @ApiModelProperty(value = "b2b客户ID")
    @TableField("B2B_CUSTOM_ID")
    private Long b2bCustomId;

    /**
     * ERP客户ID
     */
    @ApiModelProperty(value = "ERP客户ID")
    @TableField("CUSTOM_ID")
    private Long customId;

    /**
     * 独立单元
     */
    @ApiModelProperty(value = "独立单元")
    @TableField("ENTRY_ID")
    private Integer entryId;

    /**
     * 门店编号
     */
    @ApiModelProperty(value = "门店编号")
    @TableField("STORE_ID")
    private Long storeId;

    /**
     * 订单ID
     */
    @ApiModelProperty(value = "订单ID")
    @TableField("ORDER_ID")
    private Long orderId;

    /**
     * 订单编号
     */
    @ApiModelProperty(value = "订单编号")
    @TableField("ORDER_NO")
    private String orderNo;

    /**
     * 订单类型
     */
    @ApiModelProperty(value = "订单类型")
    @TableField("ORDER_TYPE")
    private String orderType;

    /**
     * 支付类型
     */
    @ApiModelProperty(value = "支付类型")
    @TableField("PAY_TYPE")
    private String payType;

    /**
     * 版本号
     */
    @ApiModelProperty(value = "版本号")
    @TableField("VERSION")
    private String version;

    /**
     * 业务日期
     */
    @ApiModelProperty(value = "业务日期")
    @TableField("CREATE_DATE")
    private LocalDateTime createDate;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;


}
