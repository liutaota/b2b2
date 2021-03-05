package com.zsyc.zt.b2b.vo;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.zt.b2b.entity.Favorites;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by tang on 2020/07/29.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "FavoritesVo对象", description = "收藏表信息")
public class FavoritesVo extends Favorites implements Serializable {

    @ApiModelProperty("是否销售，1联系客服")
    private Integer b2bNotSaleFlag;

    @ApiModelProperty(value = "厂家简称")
    private String factoryShort;

    @TableField("CURRENCYNAME")
    private String currencyname;

    @TableField("规格")
    private String goodstype;

    @TableField("基本单位")
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

    @ApiModelProperty("商品指导价")
    private double priceTry;

    /**
     * 商品图片
     */
    @ApiModelProperty(value = "商品图片")
    @TableField("GOODS_IMAGE")
    private String goodsImage;

    @ApiModelProperty("库存")
    private Integer stqty;

    @ApiModelProperty(value = "经营范围id")
    private Long busiscope;

    @ApiModelProperty(value = "经营范围名称")
    private String busiscopeName;

    //价格表
    List<PubGoodsPriceVo> pubGoodsPriceList;

    /**
     * [{img:xxx},{video:xxx}]
     * @return
     */
    @ApiModelProperty(value = "[{img:xxx},{video:xxx}]")
    public List getGoodsImgList() {
        if (StringUtils.isBlank(this.goodsImage)) {

            return Collections.emptyList();
        }
        return JSON.parseArray(this.goodsImage);
    }

    @ApiModelProperty(value = "活动")
    List<ActivityVo> activityVoList;

    @ApiModelProperty(value = "可兑换积分")
    private Long convertibleIntegral;

    @ApiModelProperty(value = "赠品标识")
    private Long erpAccFlag;
}
