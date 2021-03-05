package com.zsyc.zt.b2b.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@ApiModel(value = "SearchTipsVo对象", description = "搜索提示信息")
public class SearchTipsVo implements Serializable {
    @ApiModelProperty(value = "通用名")
    private String goodsname;

    @ApiModelProperty(value = "商品名")
    private String currencyname;

    @ApiModelProperty(value = "商品id")
    private Long goodsid;

    @ApiModelProperty(value = "条形码")
    private String barcode;

    @ApiModelProperty(value = "厂家")
    private String factoryname;

    @ApiModelProperty(value = "规格")
    private String goodstype;

    @ApiModelProperty(value = "基本单位")
    private String goodsunit;

    @ApiModelProperty(value = "保管账id")
    private Long storageid;
}
