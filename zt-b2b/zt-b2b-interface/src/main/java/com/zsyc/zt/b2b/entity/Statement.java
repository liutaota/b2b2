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
 * 账单
 * </p>
 *
 * @author MP
 * @since 2020-11-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("STATEMENT")
@ApiModel(value = "Statement对象", description = "账单")
@KeySequence(value = "SEQ_STATEMENT")
public class Statement extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    @TableField("MEMBER_ID")
    private Long memberId;

    /**
     * 账单金额
     */
    @ApiModelProperty(value = "账单金额")
    @TableField("TOTEL")
    private double totel;

    /**
     * 账单细单条数
     */
    @ApiModelProperty(value = "账单细单条数")
    @TableField("LINES")
    private Integer lines;

    /**
     * 支付类型
     */
    @ApiModelProperty(value = "支付类型")
    @TableField("PAY_TYPE")
    private String payType;

    /**
     * 来源（1号自动生成，2手动生成）
     */
    @ApiModelProperty(value = "来源（1号自动生成，2手动生成）")
    @TableField("SOURCE")
    private String source;

    /**
     * 支付流水号
     */
    @ApiModelProperty(value = "支付流水号")
    @TableField("PAY_FLOW_NO")
    private String payFlowNo;

    /**
     * 支付时间
     */
    @ApiModelProperty(value = "支付时间")
    @TableField("PAY_TIME")
    private LocalDateTime payTime;

    /**
     * 状态：支付，未支付
     */
    @ApiModelProperty(value = "状态：支付，未支付")
    @TableField("STATUS")
    private String status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;


    @ApiModelProperty(value = "支付订单号")
    @TableField("PAY_ORDER_NO")
    private String payOrderNo;

    @ApiModelProperty(value = "实际金额")
    @TableField("PAY_AMOUNT")
    private double payAmount;

    @ApiModelProperty(value = "还款日期")
    @TableField("PAY_STATEMENT_TIME")
    private LocalDateTime payStatementTime;


    @ApiModelProperty(value = "单号")
    @TableField("STATEMENT_NO")
    private String statementNo;

    /**
     * @see # status
     */
    public enum EStatus implements IEnum {
        UNPAID("未支付"),
        PAID("已支付"),
        ;
        private String desc;

        EStatus(String desc) {
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
     * @see #payType
     */
    public enum ERecType implements IEnum {
        ON_LINE("在线支付"),
        CASH("现金"),
        OFF_LINE("线下支付"),
        ;
        private String desc;

        ERecType(String desc) {
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
