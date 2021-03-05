package com.zsyc.zt.b2b.vo;

import com.google.gson.reflect.TypeToken;
import com.zsyc.zt.b2b.IEnum;
import com.zsyc.zt.b2b.entity.HomeMenu;
import com.zsyc.zt.b2b.entity.WebPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "WebPageVo对象", description = "专区数据")
public class WebPageVo extends WebPage {
    public static enum EMetaData {visibleSet,classifySetList, pageSetList,goodsSetList}

    /**
     * 可见集合
     *
     * @return
     */
    @ApiModelProperty(value = "可见集合")
    public List<WebPageInfoVo.VisibleSet> getVisibleSet() {
        return super.getMetaData(EMetaData.visibleSet.name(), new TypeToken<List<WebPageInfoVo.VisibleSet>>() {}.getType());
    }

    /**
     * 可见集合
     *
     * @param visibleSet
     */
    @ApiModelProperty(value = "可见集合")
    public void setVisibleSet(List<WebPageInfoVo.VisibleSet> visibleSet) {
        super.setMetaData(EMetaData.visibleSet.name(), visibleSet);
    }

    /**
     * 页面设置
     * @return
     */
    @ApiModelProperty(value = "页面设置")
    public List<WebPageInfoVo.PageSet> getPageSetList() {
        return super.getMetaData(EMetaData.pageSetList.name(), new TypeToken<List<WebPageInfoVo.PageSet>>() {}.getType());
    }

    /**
     * 页面设置
     * @param pageSetList
     */
    @ApiModelProperty(value = "页面设置")
    public void setPageSetList(List<WebPageInfoVo.PageSet> pageSetList) {
        super.setMetaData(EMetaData.pageSetList.name(), pageSetList);
    }

    /**
     * 分类设置
     * @return
     */
    @ApiModelProperty(value = "分类设置")
    public List<WebPageInfoVo.ClassifySet> getClassifySetList(){
        return super.getMetaData(EMetaData.classifySetList.name(), new TypeToken<List<WebPageInfoVo.ClassifySet>>() {}.getType());
    }

    /**
     * 分类设置
     * @param classifySetList
     */
    @ApiModelProperty(value = "分类设置")
    public void setClassifySetList(List<WebPageInfoVo.ClassifySet> classifySetList){
        super.setMetaData(EMetaData.classifySetList.name(), classifySetList);
    }

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
     * 商品集合
     *
     * @return
     */
    @ApiModelProperty(value = "商品集合")
    public List<WebPageInfoVo.GoodsSet> getGoodsSetList() {
        return super.getMetaData(EMetaData.goodsSetList.name(), new TypeToken<List<WebPageInfoVo.GoodsSet>>() {}.getType());
    }

    /**
     * 商品集合
     *
     * @param goodsSetList
     */
    @ApiModelProperty(value = "商品集合")
    public void setGoodsSetList(List<WebPageInfoVo.GoodsSet> goodsSetList) {
        super.setMetaData(EMetaData.goodsSetList.name(), goodsSetList);
    }

    @ApiModelProperty(value = "菜单设置数据")
    private List<HomeMenu> homeMenuList;
}