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
 * 收款细单
 * </p>
 *
 * @author MP
 * @since 2020-11-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("REC_DTL")
@ApiModel(value="RecDtl对象", description="收款细单")
@KeySequence(value = "SEQ_REC_DTL")
public class RecDtl extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId("ID")
    private Long id;

    /**
     * 收款总单id
     */
    @ApiModelProperty(value = "收款总单id")
    @TableField("SA_REC_ID")
    private Long saRecId;

    /**
     * 收款金额
     */
    @ApiModelProperty(value = "收款金额")
    @TableField("TOTAL_LINE")
    private double totalLine;

    /**
     * 单价
     */
    @ApiModelProperty(value = "单价")
    @TableField("UNT_PRICE")
    private double untPrice;

    /**
     * 货品id
     */
    @ApiModelProperty(value = "货品id")
    @TableField("GOODS_ID")
    private Long goodsId;

    /**
     * 收款数量
     */
    @ApiModelProperty(value = "收款数量")
    @TableField("GOODS_QTY")
    private double goodsQty;

    /**
     * 收款员id
     */
    @ApiModelProperty(value = "收款员id")
    @TableField("REC_SALER_ID")
    private Long recSalerId;

    /**
     * 作废标志
     */
    @ApiModelProperty(value = "作废标志")
    @TableField("INVALID_FLAG")
    private String invalidFlag;

    /**
     * 作废日期
     */
    @ApiModelProperty(value = "作废日期")
    @TableField("INVALID_DATE")
    private String invalidDate;

    /**
     * 作废人id
     */
    @ApiModelProperty(value = "作废人id")
    @TableField("INVALID_USER_ID")
    private Long invalidUserId;

    /**
     * 业务员id
     */
    @ApiModelProperty(value = "业务员id")
    @TableField("SALER_ID")
    private Long salerId;

    /**
     * 冲调标识
     */
    @ApiModelProperty(value = "冲调标识")
    @TableField("CORRECT_FLAG")
    private String correctFlag;

    /**
     * 打印列号
     */
    @ApiModelProperty(value = "打印列号")
    @TableField("PRINT_LINE")
    private String printLine;

    /**
     * 打印号
     */
    @ApiModelProperty(value = "打印号")
    @TableField("PRINT_NO")
    private String printNo;

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

    /**
     * erp最小销售数量
     */
    @ApiModelProperty(value = "erp最小销售数量")
    @TableField("ERP_LEASTSALEQTY")
    private double erpLeastsaleqty;
}
