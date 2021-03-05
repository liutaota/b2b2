package com.zsyc.zt.b2b.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel(value = "AdvertiseImagesVo对象", description = "广告数据消息")
public class AdvertiseImagesVo implements Serializable {

    @ApiModelProperty("文件类型：img")
    private String type;

    @ApiModelProperty("code")
    private String code;

    @Data
    @Accessors(chain = true)
    @ApiModel(value = "Goods对象", description = "楼层商品")
    public static class Goods implements Serializable {
        @ApiModelProperty(value = "商品id")
        private Long goodsId;

        @ApiModelProperty(value = "商品名称")
        private String goodsName;

        @ApiModelProperty("批发二价")
        private Long price2;

        @ApiModelProperty("单价")
        private Long price;

        @ApiModelProperty("价格id")
        private Long priceid;

        @ApiModelProperty("规格")
        private String goodstype;

        @ApiModelProperty("商品可销库存")
        private Integer goodsqty;

        @ApiModelProperty("基本单位")
        private String goodsunit;

        @ApiModelProperty("商品名称")
        private String currencyname;

        @ApiModelProperty(value = "B2B最小销售数量")
        private Integer zxB2bNumLimit;

        @ApiModelProperty(value = "经营范围名称")
        private String busiscopeName;

        @ApiModelProperty(value = "收藏id")
        private Long isFavorites;

        @ApiModelProperty(value = "保管帐ID")
        private Integer storageid;

        @ApiModelProperty(value = "分类id")
        private Long gcId;

        @ApiModelProperty(value = "商品图片，分号隔开")
        private String goodsImg;

        @ApiModelProperty(value = "厂家")
        private String factoryname;

        @ApiModelProperty(value = "是否赠品标志")
        private Long accflag;

        @ApiModelProperty("产地")
        private String prodarea;

        @ApiModelProperty("商品定位， X为新品")
        private String goodsidGps;

        @ApiModelProperty(value = "[{img/video:xxx,code:xxx}]")
        public List<GoodsImagesVo> getGoodsImgList() {
            if (StringUtils.isBlank(this.goodsImg)) {
                return Collections.emptyList();
            }
            return JSONArray.parseArray((this.goodsImg), GoodsImagesVo.class);
        }

        @ApiModelProperty(value = "[{img/video:xxx,code:xxx}]")
        public void setGoodsImgList(List imageList) {
            if (CollectionUtils.isEmpty(imageList)) {
                this.goodsImg = null;
            } else {
                this.goodsImg = JSON.toJSONString(imageList);
            }

        }
    }
}