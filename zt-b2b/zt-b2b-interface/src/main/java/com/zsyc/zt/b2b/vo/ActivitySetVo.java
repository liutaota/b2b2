package com.zsyc.zt.b2b.vo;

import com.zsyc.zt.b2b.entity.ActivitySet;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "ActivitySetVo对象", description = "活动集合")
public class ActivitySetVo extends ActivitySet {

    @ApiModelProperty(value = "建立人名称")
    private String employeename;

    @ApiModelProperty(value = "集合名称")
    private String setname;

}
