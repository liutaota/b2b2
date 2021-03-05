package com.zsyc.zt.b2b.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsyc.zt.b2b.IEnum;
import com.zsyc.zt.b2b.entity.Factory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "FactoryVo对象", description = "厂家")
public class FactoryVo extends Factory {

    @ApiModelProperty(value = "厂家id")
    private Long factoryid;

    @ApiModelProperty("厂家名称")
    private String factoryname;

    @ApiModelProperty("分类1")
    private String classname1;

    @ApiModelProperty("分类2")
    private String classname2;

    @ApiModelProperty("分类3")
    private String classname3;

    @ApiModelProperty("是否有图片")
    private Boolean isImg;

    @ApiModelProperty("厂家数量")
    private Integer factoryCount;

    /**
     * 排序类型
     */
    @ApiModelProperty(value = "排序类型")
    private String sortType;

    @ApiModelProperty(value = "标签")
    private String title;

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

}
