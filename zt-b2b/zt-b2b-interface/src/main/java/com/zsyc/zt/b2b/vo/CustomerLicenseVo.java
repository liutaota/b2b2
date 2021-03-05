package com.zsyc.zt.b2b.vo;

import com.zsyc.zt.b2b.entity.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Created by tang on 2020/07/27.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "CustomerLicenseVo对象", description = "客户证件信息")
public class CustomerLicenseVo extends Member {
    //证件信息
    @ApiModelProperty(value = "证件ID")
    private Long licenseid;

    @ApiModelProperty(value = "证件名称")
    private String licensename;

    @ApiModelProperty(value = "证件编码")
    private String licensecode;

    @ApiModelProperty(value = "签发日期")
    private LocalDateTime signdate;

    @ApiModelProperty(value = "期限至")
    private LocalDateTime validenddate;

    @ApiModelProperty(value = "证照状态")
    private Long licensestatus;

    @ApiModelProperty(value = "经营范围")
    private String scopeName;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "经营范围id")
    private Long scopeId;


}
