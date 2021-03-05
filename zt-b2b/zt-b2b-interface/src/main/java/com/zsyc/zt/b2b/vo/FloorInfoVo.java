package com.zsyc.zt.b2b.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.reflect.TypeToken;
import com.zsyc.zt.b2b.IEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel(value = "FloorInfoVo对象", description = "楼层数据信息")
public class FloorInfoVo implements Serializable {

    @Data
    @Accessors(chain = true)
    @ApiModel(value = "Goods对象", description = "楼层商品")
    public static class Goods implements Serializable {
        @ApiModelProperty(value = "商品id")
        private Long goodsId;

        @ApiModelProperty(value = "商品名称")
        private String goodsName;

        @ApiModelProperty("批发二价")
        private double price2;

        @ApiModelProperty("单价")
        private double price;

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
        private double zxB2bNumLimit;

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

        @ApiModelProperty("建议零售价")
        private double priceTry;

        @ApiModelProperty("活动内容")
        private String content;

        @ApiModelProperty(value = "活动")
        List<ActivityVo> activityVoList;

        @ApiModelProperty(value = "积分商品是否上下架")
        private Long integralGoods;

        @ApiModelProperty(value = "积分兑换")
        private Long convertibleIntegral;

        @ApiModelProperty("商品最近的一个效期")
        private String invaliddate;

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
    @ApiModel(value = "Tabs对象", description = "楼层页签")
    public static class Tabs implements Serializable {
        @ApiModelProperty(value = "页签名称")
        private String title;

        @ApiModelProperty(value = "商品集合")
        private List<FloorInfoVo.Goods> goodsList;

      /*  @ApiModelProperty(value = "商品集合分页")
        private IPage<FloorInfoVo.Goods> goodsIPage;
*/
    }

    @Data
    @Accessors(chain = true)
    @ApiModel(value = "FloorImage对象", description = "楼层图片")
    public static class FloorImage implements Serializable {
        @ApiModelProperty(value = "Pc图片")
        private String image;

        @ApiModelProperty(value = "小程序图片")
        private String imageWx;

        @ApiModelProperty(value = "Pc链接")
        private String url;

        @ApiModelProperty(value = "小程序链接")
        private String xwUrl;

        @ApiModelProperty(value = "多商品")
        private List<Goods> goodsList;

        @ApiModelProperty(value = "单商品")
        private Long erpGoodsId;

        @ApiModelProperty("点击事件")
        private String clickEvent;
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
}
