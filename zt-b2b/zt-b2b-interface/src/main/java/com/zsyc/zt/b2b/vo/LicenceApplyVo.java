package com.zsyc.zt.b2b.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.zt.b2b.entity.LicenceApply;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "LicenceApplyVo对象", description = "企业认证信息")
public class LicenceApplyVo extends LicenceApply {
    @ApiModelProperty(value = "企业名称")
    private String companyName;
    @ApiModelProperty(value = "联系人")
    private String contactName;
    @ApiModelProperty(value = "联系人手机")
    private String contactPhone;
    @ApiModelProperty(value = "企业类型")
    private String companyType;
    @ApiModelProperty(value = "审核意见")
    private String  applyReason;

    @ApiModelProperty(value = "客户名称")
    private String customname;

    @ApiModelProperty(value = "注册手机号")
    private String telephone;

    @ApiModelProperty(value = "客户id")
    private Long erpUserId;

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
