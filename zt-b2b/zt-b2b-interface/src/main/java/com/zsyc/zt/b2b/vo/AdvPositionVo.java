package com.zsyc.zt.b2b.vo;

import com.google.gson.reflect.TypeToken;
import com.zsyc.zt.b2b.entity.AdvPosition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "AdvPositionVo对象", description = "广告数据")
public class AdvPositionVo extends AdvPosition {
    public static enum EMetaData {imageInfoVoList,visibleSet}

    /**
     * image             图片
     * imageUrl          链接
     * imageStartTime    开始时间
     * imageEndTime      结束时间
     * goodsList         多商品
     * clickEvent        点击事件
     * type              轮播图-可见集合类型
     * memberSetList     轮播图-可见集合
     *
     * @return
     */
    @ApiModelProperty("图片、链接、开始时间、结束时间、多商品、点击事件")
    public List<ImageInfoVo> getImageInfoVoList() {
        return super.getMetaData(EMetaData.imageInfoVoList.name(), new TypeToken<List<ImageInfoVo>>() {
        }.getType());
    }

    @ApiModelProperty("图片、链接、开始时间、结束时间")
    public void setImageInfoVoList(List<ImageInfoVo> imageInfoVoList) {
        super.setMetaData(EMetaData.imageInfoVoList.name(), imageInfoVoList);
    }

    /**
     * 可见集合
     *
     * @return
     */
    @ApiModelProperty(value = "可见集合")
    public List<ImageInfoVo.VisibleSet> getVisibleSet() {
        return super.getMetaData(EMetaData.visibleSet.name(), new TypeToken<List<ImageInfoVo.VisibleSet>>() {}.getType());
    }

    /**
     * 可见集合
     *
     * @param visibleSet
     */
    @ApiModelProperty(value = "可见集合")
    public void setVisibleSet(List<ImageInfoVo.VisibleSet> visibleSet) {
        super.setMetaData(EMetaData.visibleSet.name(), visibleSet);
    }
}
