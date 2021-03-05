package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author MP
 * @since 2020-08-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("B2B_FINANCE_LIST")
@ApiModel(value="B2bFinanceList对象", description="财务表")
@KeySequence(value = "B2B_FINANCE_LIST_SEQ")
public class B2bFinanceList extends Model<B2bFinanceList> {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private Long id;

    @TableField("B2B_ORDER_NO")
    private String b2bOrderNo;

    @TableField("B2B_ORDER_ID")
    private Long b2bOrderId;
    /**
     * 1 普通订单 2 补货单
     */
    @TableField("B2B_CUSTOM_ID")
    private Long b2bCustomId;

    @TableField("SRC_DATA")
    private String srdData;


    @TableField("VERIFICATION_TYPE")
    private Integer verificationType;


    @TableField("CREATE_DATE")
    private LocalDateTime createDate;


    /**
     * 下发版本号
     */
    private String version;


}
