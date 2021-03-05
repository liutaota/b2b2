package com.zsyc.zt.b2b.vo;

import com.zsyc.zt.b2b.IEnum;
import com.zsyc.zt.b2b.entity.Setting;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "SettingVo对象", description = "参数设置信息")
public class SettingVo extends Setting {
    @ApiModelProperty(value = "是否图片/图标值")
    private Boolean isImg;
    /**
     * 排序类型
     */
    @ApiModelProperty(value = "排序类型")
    private String sortType;

    @ApiModelProperty(value = "版本号")
    private String version;

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
