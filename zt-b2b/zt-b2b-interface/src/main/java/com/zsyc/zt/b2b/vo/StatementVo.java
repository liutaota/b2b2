package com.zsyc.zt.b2b.vo;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.zt.b2b.entity.Statement;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "StatementVo", description = "账单")
public class StatementVo extends Statement {

    @ApiModelProperty(value = "订单id")
    private Long orderId;

    @ApiModelProperty(value = "客户id")
    private Long erpUserId;

    @ApiModelProperty(value = "用户手机号")
    private String telephone;

    @ApiModelProperty(value = "用户名称")
    private String userName;

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


    @ApiModelProperty(value = "账单明细")
    List<RecDocVo> recDocVoList;

    @ApiModelProperty(value = "未结账单")
    private Long noPayTotal;

    @ApiModelProperty(value = "订单数")
    private Long orderNum;
}
