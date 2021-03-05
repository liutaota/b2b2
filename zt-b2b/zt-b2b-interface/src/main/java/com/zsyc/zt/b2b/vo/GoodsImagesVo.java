package com.zsyc.zt.b2b.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
//@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "GoodsImagesVo对象", description = "图片消息")
public class GoodsImagesVo implements Serializable {

    @ApiModelProperty("文件类型：img/video")
    private String type;
    @ApiModelProperty("code")
    private String code;

}
