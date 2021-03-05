package com.zsyc.zt.b2b.vo;

import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "DeliveryAmountVo对象", description = "省市区")
public class DeliveryAmountVo extends BaseBean {
    @ApiModelProperty("省ID")
    private Long provinceId;

    @ApiModelProperty("市ID")
    private Long cityId;

    @ApiModelProperty("区ID")
    private Long countryId;

    @ApiModelProperty("省名称")
    private String provinceName;

    @ApiModelProperty("市名称")
    private String cityName;

    @ApiModelProperty("区名称")
    private String countryName;

    @ApiModelProperty("地区名称")
    private String areaName;

    @ApiModelProperty("规则名称")
    private String daName;
}
