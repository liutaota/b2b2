package com.zsyc.zt.b2b.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zsyc.zt.b2b.entity.LotteryDialDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "LotterDailQualificationsVo", description = "转盘抽奖详情")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LotteryDialDetailVo extends LotteryDialDetail {

    @ApiModelProperty("通用名")
    private String goodsname;

    /**
     * 商品图片，分号隔开
     */
    @ApiModelProperty(value = "商品图片，分号隔开")
    @TableField("GOODS_IMG")
    private String goodsImg;

    @ApiModelProperty("厂家名称")
    private String factoryname;

    @ApiModelProperty("产品id")
    private Long goodsid;

    @ApiModelProperty("规格")
    private String goodstype;

    @ApiModelProperty(value = "促销内容")
    private String content;

    @ApiModelProperty(value = "B2B最小销售数量")
    private double zxB2bNumLimit;

    @ApiModelProperty("商品名称")
    private String currencyname;

    @ApiModelProperty("基本单位")
    private String goodsunit;

    @ApiModelProperty("活动id")
    private Long activityId;

}
