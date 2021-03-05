package com.zsyc.zt.b2b.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.gson.reflect.TypeToken;
import com.zsyc.zt.b2b.IEnum;
import com.zsyc.zt.b2b.entity.Floor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "FloorVo对象", description = "楼层数据")
public class FloorVo extends Floor implements Serializable {

    @ApiModelProperty(value = "显示商品数量")
    private Integer goodNum;

    @ApiModelProperty(value = "关联页面")
    private String[] linkPage;

    public static enum EMetaData {goodsList, tabs, backgroundImage, floodImage, advImage, advImageUrl, erpGoodsId, clickEvent, visibleSet,advImageWx,backgroundImageWx}

    /**
     * 单行样式：商品选择
     */
    @ApiModelProperty(value = "商品ID/商品名称")
    public List<FloorInfoVo.Goods> getGoodsList() {
        return super.getMetaData(EMetaData.goodsList.name(), new TypeToken<List<FloorInfoVo.Goods>>() {
        }.getType());
    }

    /**
     * 单行样式：商品选择
     */
    @ApiModelProperty(value = "商品ID/商品名称")
    public void setGoodsList(List<FloorInfoVo.Goods> goodsList) {
        super.setMetaData(EMetaData.goodsList.name(), goodsList);
    }

    /**
     * 页签名称
     */
    @ApiModelProperty(value = "页签名称")
    public List<FloorInfoVo.Tabs> getTabs() {
        return super.getMetaData(EMetaData.tabs.name(), new TypeToken<List<FloorInfoVo.Tabs>>() {
        }.getType());
    }

    /**
     * 页签名称
     */
    @ApiModelProperty(value = "页签名称")
    public void setTabs(List<FloorInfoVo.Tabs> tabs) {
        super.setMetaData(EMetaData.tabs.name(), tabs);
    }

    /**
     * 单商品
     */
    @ApiModelProperty(value = "单商品")
    public Long getErpGoodsId() {
        return super.getMetaData(EMetaData.erpGoodsId.name(), Long.class);
    }

    /**
     * 单商品
     */
    @ApiModelProperty(value = "单商品")
    public void setErpGoodsId(Long erpGoodsId) {
        super.setMetaData(EMetaData.erpGoodsId.name(), erpGoodsId);
    }

    /**
     * Pc背景图
     * @return
     */
    @ApiModelProperty(value = "Pc背景图")
    public String getBackgroundImage() {
        return super.getMetaData(EMetaData.backgroundImage.name(), String.class);
    }

    /**
     * Pc背景图
     */
    @ApiModelProperty(value = "Pc背景图")
    public void setBackgroundImage(String backgroundImage) {
        super.setMetaData(EMetaData.backgroundImage.name(), backgroundImage);
    }
    /**
     * 小程序背景图
     */
    @ApiModelProperty(value = "小程序背景图")
    public String getBackgroundImageWx() {
        return super.getMetaData(EMetaData.backgroundImageWx.name(), String.class);
    }

    /**
     * 小程序背景图
     * @param backgroundImageWx
     */
    @ApiModelProperty(value = "小程序背景图")
    public void setBackgroundImageWx(String backgroundImageWx) {
        super.setMetaData(EMetaData.backgroundImageWx.name(), backgroundImageWx);
    }

    /**
     * 点击事件
     */
    @ApiModelProperty(value = "点击事件")
    public String getClickEvent() {
        return super.getMetaData(EMetaData.clickEvent.name(), String.class);
    }

    /**
     * 点击事件
     */
    @ApiModelProperty(value = "点击事件")
    public void setClickEvent(String clickEvent) {
        super.setMetaData(EMetaData.clickEvent.name(), clickEvent);
    }

    /**
     * 图片链接
     */
    @ApiModelProperty(value = "图片链接")
    public List<FloorInfoVo.FloorImage> getFloorImage() {
        return super.getMetaData(EMetaData.floodImage.name(), new TypeToken<List<FloorInfoVo.FloorImage>>() {
        }.getType());
    }

    /**
     * 图片链接
     */
    @ApiModelProperty(value = "图片链接")
    public void setFloorImage(List<FloorInfoVo.FloorImage> floodImage) {
        super.setMetaData(EMetaData.floodImage.name(), floodImage);
    }

    /**
     * Pc广告图片
     */
    @ApiModelProperty(value = "Pc广告图片")
    public String getAdvImage() {
        return super.getMetaData(EMetaData.advImage.name(), String.class);
    }

    /**
     * Pc广告图片
     *
     * @param advImage
     */
    @ApiModelProperty(value = "Pc广告图片")
    public void setAdvImage(String advImage) {
        super.setMetaData(EMetaData.advImage.name(), advImage);
    }

    /**
     * 小程序广告图片
     */
    @ApiModelProperty(value = "小程序广告图片")
    public String getAdvImageWx() {
        return super.getMetaData(EMetaData.advImageWx.name(), String.class);
    }

    /**
     * 小程序广告图片
     *
     * @param advImageWx
     */
    @ApiModelProperty(value = "小程序广告图片")
    public void setAdvImageWx(String advImageWx) {
        super.setMetaData(EMetaData.advImageWx.name(), advImageWx);
    }

    /**
     * 广告图片链接
     */
    @ApiModelProperty(value = "Pc广告图片链接")
    public String getAdvImageUrl() {
        return super.getMetaData(EMetaData.advImageUrl.name(), String.class);
    }

    /**
     * 广告图片链接
     * @param advImageUrl
     */
    @ApiModelProperty(value = "Pc广告图片链接")
    public void setAdvImageUrl(String advImageUrl) {
        super.setMetaData(EMetaData.advImageUrl.name(), advImageUrl);
    }

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    private Long memberId;

    /**
     * 排序类型
     */
    @ApiModelProperty(value = "排序类型")
    private String sortType;

    /**
     * @see #sortType
     */
    public enum ESortType implements IEnum {
        TOP("置顶"),
        DOWN("置底"),
        ;
        private String desc;

        ESortType(String desc) {
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

    /**
     * 可见集合
     *
     * @return
     */
    @ApiModelProperty(value = "可见集合")
    public List<FloorInfoVo.VisibleSet> getVisibleSet() {
        return super.getMetaData(EMetaData.visibleSet.name(), new TypeToken<List<FloorInfoVo.VisibleSet>>() {
        }.getType());
    }

    /**
     * 可见集合
     *
     * @param visibleSet
     */
    @ApiModelProperty(value = "可见集合")
    public void setVisibleSet(List<FloorInfoVo.VisibleSet> visibleSet) {
        super.setMetaData(EMetaData.visibleSet.name(), visibleSet);
    }

    @ApiModelProperty(value = "可见集合")
    private IPage<FloorInfoVo.Goods> goodsIPage;
}
