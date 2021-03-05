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
 * 退货退款原因
 * </p>
 *
 * @author MP
 * @since 2020-07-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("REASON")
@ApiModel(value = "Reason对象", description = "退货退款原因")
@KeySequence(value = "SEQ_REASON")
public class Reason extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 退换货原因
     */
    @ApiModelProperty(value = "退换货原因")
    @TableField("REASON_INFO")
    private String reasonInfo;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序 ")
    @TableField("SORT")
    private Integer sort;

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
     * 是否删除：0为否，1为是
     */
    @ApiModelProperty(value = "是否删除：0为否，1为是")
    @TableField("IS_DEL")
    private Integer isDel;

    /**
     * erp退货原因ID
     */
    @ApiModelProperty(value = "erp退货原因ID")
    @TableField("ERP_REASON_ID")
    private Long erpReasonId;
    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    @TableField("CREATE_USER_ID")
    private Long createUserId;
    /**
     * 修改人ID
     */
    @ApiModelProperty(value = "修改人ID")
    @TableField("UPDATE_USER_ID")
    private Long updateUserId;

}
