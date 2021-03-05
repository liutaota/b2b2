package com.zsyc.zt.b2b.vo;

import com.zsyc.zt.b2b.IEnum;
import com.zsyc.zt.b2b.entity.Help;
import com.zsyc.zt.b2b.entity.HelpType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "HelpTypeVo对象", description = "帮助类型")
public class HelpTypeVo extends HelpType {

    /**
     * 帮助指南
     */
   private List<Help> helpList;

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
