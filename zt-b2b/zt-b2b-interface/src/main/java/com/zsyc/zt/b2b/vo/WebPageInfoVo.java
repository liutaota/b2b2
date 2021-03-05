package com.zsyc.zt.b2b.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zsyc.zt.b2b.IEnum;
import com.zsyc.zt.b2b.entity.AdvPosition;
import com.zsyc.zt.b2b.entity.Floor;
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
@ApiModel(value = "WebPageInfoVo对象", description = "专区数据信息")
public class WebPageInfoVo implements Serializable {

    @Data
    @Accessors(chain = true)
    @ApiModel(value = "MemberSet对象", description = "专区客户集合")
    public static class MemberSet implements Serializable {
        @ApiModelProperty(value = "客户集合ID")
        private Long memberSetId;
        @ApiModelProperty(value = "客户集合名称")
        private String memberSetName;
    }

    @Data
    @Accessors(chain = true)
    @ApiModel(value = "MemberSet对象", description = "专区可见集合")
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
            UN_VISIBLE("部分不可见")
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
    @ApiModel(value = "Goods对象", description = "专区商品集合")
    public static class Goods implements Serializable {
        @ApiModelProperty(value = "商品id")
        private Long goodsId;

        @ApiModelProperty(value = "商品名称")
        private String goodsName;

        @ApiModelProperty("批发二价")
        private Long price2;

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

    @Data
    @Accessors(chain = true)
    @ApiModel(value = "Classify对象", description = "专区分类集合")
    public static class Classify implements Serializable {
        @ApiModelProperty(value = "分类ID")
        private Long[][] classifyId;

    }

    @Data
    @Accessors(chain = true)
    @ApiModel(value = "Access对象", description = "专区二级分类")
    public static class Access implements Serializable {
        @ApiModelProperty(value = "二级分类名称")
        private String accessName;
        @ApiModelProperty(value = "类型选择：GOODS 商品 CLASS 分类")
        private String type;
        @ApiModelProperty(value = "商品选择")
        private List<Goods> goodsList;
        @ApiModelProperty(value = "分类选择")
        private List<Classify> classifyList;

        /**
         * @see #type
         */
        public enum EType implements IEnum {
            GOODS("商品"),
            CLASS("分类"),
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
    @ApiModel(value = "ClassifySet对象", description = "专区分类设置")
    public static class ClassifySet implements Serializable {
        @ApiModelProperty(value = "一级分类名称")
        private String stairClassify;
        @ApiModelProperty(value = "二级分类集合")
        private List<Access> accessList;
    }

    @Data
    @Accessors(chain = true)
    @ApiModel(value = "PageSet对象", description = "专区页面设置")
    public static class PageSet implements Serializable {
        @ApiModelProperty(value = "页面类型：ADV 广告位 FLOOR 楼层")
        private String type;
        @ApiModelProperty(value = "广告位ID")
        private Long advId;
        @ApiModelProperty(value = "广告位名称")
        private String advTitle;
        @ApiModelProperty(value = "楼层ID")
        private Long floorId;
        @ApiModelProperty(value = "楼层名称")
        private String floorTitle;

        @ApiModelProperty(value = "广告位信息")
        private  AdvPositionVo advPosition;

        @ApiModelProperty(value = "楼层信息")
        private FloorVo floor;

        @ApiModelProperty(value = "（广告/楼层）什么类型")
        private String whatType;

        @ApiModelProperty(value = "（广告/楼层）是否启用")
        private String isUse;

        /**
         * @see #type
         */
        public enum EType implements IEnum {
            ADV("广告位"),
            FLOOR("楼层"),
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
    @ApiModel(value = "GoodsSet对象", description = "优惠券商品集合信息")
    public static class GoodsSet implements Serializable {
        @ApiModelProperty(value = "商品集合id")
        private Long goodsSetId;

        @ApiModelProperty(value = "商品集合名称")
        private String goodsSetName;
    }
}
