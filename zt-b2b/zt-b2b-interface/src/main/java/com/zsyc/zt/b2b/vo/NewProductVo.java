package com.zsyc.zt.b2b.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zsyc.zt.b2b.entity.NewProduct;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "NewProductVo对象", description = "新品登记数据")
public class NewProductVo extends NewProduct {
    @ApiModelProperty(value = "会员名称")
    private String memberName;
    @ApiModelProperty(value = "商品名称")
    private String goodsName;
    @ApiModelProperty(value = "批准文号")
    private String approvedocno;
    @ApiModelProperty(value = "用户id")
    private Long memberId;
    @ApiModelProperty(value = "图片数量")
    private Integer imageNum;
    @ApiModelProperty(value = "图片")
    private String photos;

    /**
     * [{img/video:xxx,code:xxx}]
     *
     * @return
     */
    @ApiModelProperty(value = "[{img/video:xxx,code:xxx}]")
    public List<NewProductImageVo> getPhotosList() {
        if (StringUtils.isBlank(this.photos)) {
            return Collections.emptyList();
        }
        return JSONArray.parseArray((this.photos), NewProductImageVo.class);
    }

    @ApiModelProperty(value = "[{img/video:xxx,code:xxx}]")
    public void setPhotosList(List imageList) {
        if (CollectionUtils.isEmpty(imageList)) {
            this.photos = null;
        } else {
            this.photos = JSON.toJSONString(imageList);
        }

    }
}
