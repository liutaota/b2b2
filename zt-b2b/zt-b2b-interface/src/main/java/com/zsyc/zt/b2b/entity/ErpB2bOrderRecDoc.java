package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * erpb2b收款单总表
 * </p>
 *
 * @author MP
 * @since 2021-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ERP_B2B_ORDER_REC_DOC")
@ApiModel(value="ErpB2bOrderRecDoc对象", description="erpb2b收款单总表")
@KeySequence(value = "SEQ_ERP_B2B_ORDER_REC_DOC")
public class ErpB2bOrderRecDoc extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    @TableField("ERP_USER_ID")
    private Long erpUserId;

    /**
     * 支付单号
     */
    @ApiModelProperty(value = "支付单号")
    @TableField("TMP_NO")
    private String tmpNo;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    @TableField("PAY_TIME")
    private LocalDateTime payTime;

    /**
     * 应付金额
     */
    @ApiModelProperty(value = "应付金额")
    @TableField("ONLINE_TOTAL")
    private Double onlineTotal;

    /**
     * 现付金额
     */
    @ApiModelProperty(value = "现付金额")
    @TableField("CASH_TOTAL")
    private Double cashTotal;

    /**
     * 实付金额
     */
    @ApiModelProperty(value = "实付金额")
    @TableField("PAY_TOTAL")
    private Double payTotal;

    /**
     * 支付流水号
     */
    @ApiModelProperty(value = "支付流水号")
    @TableField("PAY_FLOW_NO")
    private String payFlowNo;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    @TableField("USER_ID")
    private Long userId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    @TableField("TYPE")
    private String type;

    /**
     * 1,已核销，0未核销,2核销中，3核销失败
     */
    @ApiModelProperty(value = "核销是否通过")
    @TableField("FINANCE_TRUE")
    private Integer financeTrue;

    @ApiModelProperty(value = "erp核销备注")
    @TableField("ERP_FINANCE_REMARK")
    private String erpFinanceRemark;

    @ApiModelProperty(value = "核销操作人")
    @TableField("FINANCE_USER_ID")
    private Long financeUserId;

    @ApiModelProperty(value = "核销时间")
    @TableField("FINANCE_TIME")
    private LocalDateTime financeTime;

    @ApiModelProperty(value = "核销备注")
    @TableField("FINANCE_REMARK")
    private String financeRemark;


    @ApiModelProperty(value = "退款金额")
    @TableField("REFUND_MONEY")
    private double refundMoney;

    @ApiModelProperty(value = "退款操作人")
    @TableField("REFUND_USER_ID")
    private Long refundUserId;


    @ApiModelProperty(value = "退款备注")
    @TableField("REFUND_REMARK")
    private String refundRemark;

    @ApiModelProperty(value = "退款时间")
    @TableField("REFUND_TIME")
    private LocalDateTime refundTime;

    @ApiModelProperty(value = "退款流水号")
    @TableField("PAY_REFUND_NO")
    private String payRefundNo;

    @ApiModelProperty(value = "农行日志")
    @TableField("ABC_MESSAGE")
    private String abcMessage;

    @ApiModelProperty(value = "退款状态")
    @TableField("REFUND_STATE")
    private String refundState;

    @ApiModelProperty(value = "退款单号")
    @TableField("PAY_ABC_NO")
    private String payAbcNo;

    @ApiModelProperty(value = "确定时间")
    @TableField("CONFIRM_TIME")
    private LocalDateTime confirmTime;

    @ApiModelProperty(value = "确定人")
    @TableField("CONFIRM_USER_ID")
    private Long confirmUserId;

    @ApiModelProperty(value = "确定备注")
    @TableField("CONFIRM_REMARK")
    private String confirmRemark;

    @ApiModelProperty(value = "线路")
    @TableField("TRANPOSNAME")
    private String tranposname;

    @ApiModelProperty(value = "线序")
    @TableField("TRANSORTNO")
    private String transortno;

    @ApiModelProperty(value = "支付类型")
    @TableField("PAY_TYPE")
    private String payType;

    @ApiModelProperty(value = "支付说明")
    @TableField("PAY_ABC_DOC")
    private String payAbcDoc;

    @ApiModelProperty(value = "是否删除")
    @TableField("IS_DEL")
    private Integer isDel;

    @ApiModelProperty(value = "删除人")
    @TableField("DEL_USER_ID")
    private Long delUserId;

    @ApiModelProperty(value = "删除时间")
    @TableField("DEL_TIME")
    private LocalDateTime delTime;

    @ApiModelProperty(value = "删除备注")
    @TableField("DEL_REMARK")
    private String  delRemark;

    @ApiModelProperty(value = "差异金额")
    @TableField("DIFFERENCE_AMOUNT")
    private double differenceAmount;

    @ApiModelProperty(value = "下发版本（次数）")
    @TableField("TO_ERP_NUM")
    private double toErpNum;

    @ApiModelProperty(value = "修改时间")
    @TableField("UPDATE_TIME")
    private LocalDateTime updateTime;

    /**
     * @see #payType
     */
    public enum EPayType implements IEnum {
        CASH("现金付款"),
        ON_LINE("在线支付"),
        ;
        private String desc;

        EPayType(String desc) {
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

    /**
     * @see #refundState
     */
    public enum ERefundState implements IEnum {
        NO_REFUND("未退款"),
        REFUND_SUCCESS("退款成功"),
        REFUND_FAIL("退款失败"),
        ;
        private String desc;

        ERefundState(String desc) {
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
