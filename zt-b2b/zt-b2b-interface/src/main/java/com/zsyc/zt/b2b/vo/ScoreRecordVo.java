package com.zsyc.zt.b2b.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.zt.b2b.entity.ScoreRecord;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("SCORE_RECORD")
@ApiModel(value="ScoreRecordVo对象", description="用户积分记录扩展")
public class ScoreRecordVo extends ScoreRecord {
    @ApiModelProperty("客户名称")
    private String memberName;

    @ApiModelProperty("手机号")
    private String telephone;

    @ApiModelProperty("结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty("开始时间")
    private LocalDateTime startTime;

    @ApiModelProperty("客户id")
    private Long memberId;

    @ApiModelProperty(value = "erp用户id")
    private Long erpUserId;
}
