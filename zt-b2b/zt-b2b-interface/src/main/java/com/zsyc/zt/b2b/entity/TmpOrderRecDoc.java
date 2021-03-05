package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.zt.b2b.IEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 现结账单
 * </p>
 *
 * @author MP
 * @since 2020-12-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ERP_B2B_ORDER_REC_Dtl")
@ApiModel(value="TmpOrderRecDoc对象", description="现结账单")
@KeySequence(value = "SEQ_TMP_ORDER_REC_DOC")
public class TmpOrderRecDoc extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableField("ID")
    private Long id;

    /**
     * 订单id
     */
    @ApiModelProperty(value = "订单id")
    @TableField("ORDER_ID")
    private Long orderId;

    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    @TableField("ORDER_NO")
    private String orderNo;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    @TableField("TOTAL")
    private Double total;

    /**
     * 支付单号
     */
    @ApiModelProperty(value = "支付单号")
    @TableField("TMP_NO")
    private String tmpNo;

    /**
     * 流水号
     */
    @ApiModelProperty(value = "流水号")
    @TableField("PAY_FLOW_NO")
    private String payFlowNo;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    @TableField("PAY_TIME")
    private LocalDateTime payTime;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    @TableField("USER_ID")
    private Long userId;

    @ApiModelProperty(value = "支付金额")
    @TableField("PAY_AMOUNT")
    private Double payAmount;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    @TableField("TYPE")
    private String type;


    /**
     * erp订单id
     */
    @ApiModelProperty(value = "erp订单id")
    @TableField("SALESID")
    private String salesid;

    @ApiModelProperty(value = "erp客户id")
    @TableField("ERP_USER_ID")
    private Long erpUserId;

    @ApiModelProperty(value = "erpb2b收款单总表id")
    @TableField("ERP_B2B_ORDER_REC_DOC_ID")
    private Long erpB2bOrderRecDocId;

    @ApiModelProperty(value = "销售单类型")
    @TableField("SATYPEID")
    private String satypeid;

    @ApiModelProperty(value = "是否删除")
    @TableField("IS_DEL")
    private Integer isDel;

    @ApiModelProperty(value = "1是销售类，2是配送类,3是销配退类")
    @TableField("SOURCETYPE")
    private Integer sourcetype;

    /**
     * @see #type
     */
    public enum EType implements IEnum {
        ERP_ORDER("erp订单"),
        B2B_ORDER("b2b订单"),
        ;
        private String desc;

        EType(String desc) {
            this.desc = desc;
        }

        @Override
        public String desc() {
            return this.desc;
        }

        @Override
        public String val() {
            return this.name();
        }
    }
}
