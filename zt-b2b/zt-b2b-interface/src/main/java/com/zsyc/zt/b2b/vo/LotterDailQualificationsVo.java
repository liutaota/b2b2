package com.zsyc.zt.b2b.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zsyc.zt.b2b.entity.LotterDailQualifications;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "LotterDailQualificationsVo", description = "可抽奖次数")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LotterDailQualificationsVo extends LotterDailQualifications {
    @ApiModelProperty(value = "活动名称")
    private String content;

    @ApiModelProperty(value = "订单号")
    private String orderNo;

    @ApiModelProperty(value = "过期状态")
    private String statusTime;
}
