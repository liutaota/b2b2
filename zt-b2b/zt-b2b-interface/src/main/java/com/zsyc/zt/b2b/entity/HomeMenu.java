package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.zt.b2b.IEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 首页菜单
 * </p>
 *
 * @author MP
 * @since 2020-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("HOME_MENU")
@ApiModel(value="HomeMenu对象", description="首页菜单")
@KeySequence(value = "SEQ_HOME_MENU")
public class HomeMenu extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    @TableId("ID")
    private Long id;

    /**
     * 菜单名
     */
    @ApiModelProperty(value = "菜单名")
    @TableField("HM_NAME")
    private String hmName;

    /**
     * 页面id
     */
    @ApiModelProperty(value = "页面id")
    @TableField("HM_ZONE_ID")
    private Long hmZoneId;

    /**
     * 上级id
     */
    @ApiModelProperty(value = "上级id")
    @TableField("PARENT_ID")
    private Long parentId;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @TableField("SORT")
    private Long sort;

    /**
     * 是否显示：ON 是 OFF 否
     */
    @ApiModelProperty(value = "是否显示：ON 是 OFF 否")
    @TableField("IS_USE")
    private String isUse;

    /**
     * 是否删除：0 否 1 是
     */
    @ApiModelProperty(value = "是否删除：0 否 1 是")
    @TableField("IS_DEL")
    private Integer isDel;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField("MODIFY_TIME")
    private LocalDateTime modifyTime;

    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 提示图标
     */
    @ApiModelProperty(value = "提示图标")
    @TableField("HINT_ICON")
    private String hintIcon;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    @TableField("HM_PIC")
    private String hmPic;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    /**
     * @see #isUse
     */
    public enum EIsUse implements IEnum {
        ON("显示"),
        OFF("不显示"),
        ;
        private String desc;

        EIsUse(String desc) {
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
     * @see #hintIcon
     */
    public enum EHintIcon implements IEnum {
        WITHOUT("无"),
        HOT("热门"),
        NEW_RELEASES("最新上架"),
        ;
        private String desc;

        EHintIcon(String desc) {
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
