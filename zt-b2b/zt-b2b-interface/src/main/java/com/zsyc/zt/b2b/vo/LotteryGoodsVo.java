package com.zsyc.zt.b2b.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zsyc.zt.b2b.entity.LotteryGoods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "LotteryGoodsVo", description = "转盘抽奖商品")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LotteryGoodsVo extends LotteryGoods {

    @ApiModelProperty("商品名")
    private String goodsname;

    @ApiModelProperty("通用名")
    private String currencyname;

    @ApiModelProperty("规格")
    private String goodstype;

    @ApiModelProperty("基本单位")
    private String goodsunit;

    @ApiModelProperty("厂家名称")
    private String factoryname;

    @ApiModelProperty("批准文号")
    private String approvedocno;

    @ApiModelProperty("剂型")
    private String medicinetypename;

    @ApiModelProperty("条形码")
    private String barcode;

    @ApiModelProperty(value = "B2B最小销售数量")
    private double zxB2bNumLimit;

    /**
     * 商品图片
     */
    @ApiModelProperty(value = "商品图片")
    private String goodsImage;
}
