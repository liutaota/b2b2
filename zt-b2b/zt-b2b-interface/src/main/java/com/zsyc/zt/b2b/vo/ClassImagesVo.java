package com.zsyc.zt.b2b.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
//@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "ClassImagesVo对象", description = "分类图片消息")
public class ClassImagesVo implements Serializable {
    @ApiModelProperty("文件类型：img")
    private String type;
    @ApiModelProperty("code")
    private String code;
}
