package com.zsyc.zt.b2b.vo;

import com.zsyc.zt.b2b.IEnum;
import com.zsyc.zt.b2b.entity.HomeMenu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "HomeMenuVo对象", description = "菜单设置数据")
public class HomeMenuVo extends HomeMenu {
    @ApiModelProperty(value = "一级菜单名称")
    private String homeName;

    @ApiModelProperty(value = "二级菜单")
    private List<HomeMenuVo> homeMenuVoList;

    @ApiModelProperty(value = "页面名称")
    private String webPageName;
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

}
