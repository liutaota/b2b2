package com.zsyc.zt.b2b.entity;

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
 * 订单中间表
 * </p>
 *
 * @author MP
 * @since 2020-12-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("IN_ORDER_DATE")
@ApiModel(value="InOrderDate对象", description="订单中间表")
@KeySequence(value = "SEQ_IN_ORDER_DATE")
public class InOrderDate extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    @TableField("ORDER_NO")
    private String orderNo;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    @TableField("ERP_CUSTOMER_ID")
    private Long erpCustomerId;

    /**
     * b2b客户id
     */
    @ApiModelProperty(value = "b2b客户id")
    @TableField("MEMBER_ID")
    private Long memberId;


}
