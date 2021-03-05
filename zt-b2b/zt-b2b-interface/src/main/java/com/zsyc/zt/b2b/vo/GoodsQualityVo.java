package com.zsyc.zt.b2b.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by lcs on 2020/8/27.
 */


@Data
@Accessors(chain = true)
@ApiModel(value = "GoodsQualityVo", description = "药检报告视图")
public class GoodsQualityVo implements Serializable {
    /**
     * 商品批准文号
     */
    @ApiModelProperty("商品批准文号")
    private String approvedocno;
    /**
     * 批号
     */
    @ApiModelProperty("批号")
    private String lotno;
    /**
     * 货品ID
     */
    @ApiModelProperty("货品ID")
    private Long goodsid;
    /**
     * 图片地址
     */
    @ApiModelProperty("图片地址")
    private String imgurlTop;
    /**
     * 图片存放路径
     */
    @ApiModelProperty("图片存放路径")
    private String imgurlBottom;
    /**
     * 创建日期
     */
    @ApiModelProperty("创建日期")
    private LocalDateTime createTime;

    /**
     * 文件数量
     */
    @ApiModelProperty("文件数量")
    private Integer filecount;

    /**
     * fcheckid
     */
    @ApiModelProperty("fcheckid")
    private Long fcheckid;

    /**
     * 图片名称
     */
    @ApiModelProperty("图片名称")
    private String filename;
}
