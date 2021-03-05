package com.zsyc.zt.inca.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "运输地点返回类")
@Data
public class BmsTrPosDefVo implements Serializable {

    @ApiModelProperty(name = "运输地点ID")
    private Long tranposid;

    @ApiModelProperty(name = "联系人")
    private String contractman;

    @ApiModelProperty(name = "电话号码")
    private String telephone;

    @ApiModelProperty(name = "地址")
    private String address;

    @ApiModelProperty(name = "运输线路ID")
    private Long translineid;

    @ApiModelProperty(name = "运输线路序号")
    private Long transortno;

    @ApiModelProperty(name = "主客户id")
    @TableField("B2B_SUB_CUSTOM_ID")
    private Long b2bSubCustomId;


}
