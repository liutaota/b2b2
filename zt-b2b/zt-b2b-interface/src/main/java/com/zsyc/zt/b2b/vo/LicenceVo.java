package com.zsyc.zt.b2b.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.zt.b2b.entity.Licence;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@ApiModel(value = "LicenceVo对象", description = "客户证照信息（记录）")
public class LicenceVo extends Licence implements Serializable {
    @ApiModelProperty("文件类型：img/video")
    private String type;
    @ApiModelProperty("code")
    private String code;

    @ApiModelProperty(value = "客户名称")
    private String customname;

    @ApiModelProperty(value = "注册手机号")
    private String telephone;

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
}
