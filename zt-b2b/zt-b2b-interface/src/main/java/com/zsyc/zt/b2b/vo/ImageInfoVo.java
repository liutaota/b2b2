package com.zsyc.zt.b2b.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.zt.b2b.IEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel(value = "ImageInfoVo对象", description = "广告图片信息")
public class ImageInfoVo implements Serializable {

    @ApiModelProperty("Pc图片")
    private String image;

    @ApiModelProperty(value = "小程序图片")
    private String imageWx;

    @ApiModelProperty("图片链接")
    private String imageUrl;

    @ApiModelProperty("imageStartTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime imageStartTime;

    @ApiModelProperty("imageEndTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime imageEndTime;

    @ApiModelProperty("点击事件")
    private String clickEvent;

    @ApiModelProperty(value = "多商品")
    private List<Goods> goodsList;

    @ApiModelProperty(value = "单商品")
    private Long erpGoodsId;

    @ApiModelProperty(value = "是否开启")
    private String imageIsUse;

    @ApiModelProperty(value = "可见客户集合")
    private VisibleSet visibleSet;

    /**
     * @see #imageIsUse
     */
    public enum EImageIsUse implements IEnum {
        OFF("不启用"),
        ON("启用"),
        ;
        private String desc;

        EImageIsUse(String desc) {
            this.desc = desc;
        }

        @Override
        public String desc() {
            return this.desc;
        }

        @Override
        public String val() {
            return this.name();
        }
    }

    @Data
    @Accessors(chain = true)
    @ApiModel(value = "MemberSet对象", description = "广告客户集合")
    public static class MemberSet implements Serializable {
        @ApiModelProperty(value = "客户集合id")
        private Long memberSetId;

        @ApiModelProperty(value = "客户集合名称")
        private String memberSetName;

    }

    @Data
    @Accessors(chain = true)
    @ApiModel(value = "VisibleSet对象", description = "广告可见集合")
    public static class VisibleSet implements Serializable {
        @ApiModelProperty(value = "可见类型：全部可见 ALL_VISIBLE 部分可见 PARTIALLY_VISIBLE 部分不可见 UN_VISIBLE ")
        private String type;
        @ApiModelProperty(value = "客户集合")
        private List<MemberSet> memberSetList;
        /**
         * @see #type
         */
        public enum EType implements IEnum {
            ALL_VISIBLE("全部可见"),
            PARTIALLY_VISIBLE("部分可见"),
            UN_VISIBLE("部分不可见");
            ;
            private String desc;

            EType(String desc) {
                this.desc = desc;
            }

            @Override
            public String desc() {
                return this.desc;
            }

            @Override
            public String val() {
                return this.name();
            }
        }
    }


    @Data
    @Accessors(chain = true)
    @ApiModel(value = "Goods对象", description = "广告商品")
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
