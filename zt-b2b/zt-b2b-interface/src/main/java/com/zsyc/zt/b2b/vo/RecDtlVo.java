package com.zsyc.zt.b2b.vo;

import com.zsyc.zt.b2b.entity.RecDtl;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "RecDtlVo", description = "收款细单")
public class RecDtlVo extends RecDtl {
    @ApiModelProperty("厂家名称")
    private String factoryname;

    @ApiModelProperty("货品分类名")
    private String classname;

    @ApiModelProperty("商品定位， X为新品")
    private String goodsidGps;

    @ApiModelProperty("剂型")
    private String medicinetypename;

    @ApiModelProperty("商品名称")
    private String currencyname;

    @ApiModelProperty("基本单位")
    private String goodsunit;

    @ApiModelProperty("商品名称")
    private String goodsname;

    @ApiModelProperty("是否赠品标志")
    private Long accflag;
}
