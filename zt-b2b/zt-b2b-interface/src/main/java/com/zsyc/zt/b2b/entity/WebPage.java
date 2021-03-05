package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.sql.Clob;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.framework.base.BaseMeta;
import com.zsyc.zt.b2b.IEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 专区页
 * </p>
 *
 * @author MP
 * @since 2020-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("WEB_PAGE")
@ApiModel(value="WebPage对象", description="专区页")
@KeySequence(value = "SEQ_WEB_PAGE")
public class WebPage extends BaseMeta {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @TableField("TITLE")
    private String title;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @TableField("SORT_NUM")
    private Integer sortNum;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    @TableField("CREATE_USER_ID")
    private Long createUserId;

    /**
     * 更新人ID
     */
    @ApiModelProperty(value = "更新人ID")
    @TableField("UPDATE_USER_ID")
    private Long updateUserId;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    @TableField("IS_DEL")
    private Integer isDel;

    /**
     * 提示图标
     */
    @ApiModelProperty(value = "提示图标")
    @TableField("HINT_ICON")
    private String hintIcon;

    /**
     * 是否开启
     */
    @ApiModelProperty(value = "是否开启")
    @TableField("IS_USE")
    private String isUse;

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
        OFF("关闭"),
        ON("启用"),
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
        NOT_HAVE("无"),
        HOT("热门"),
        NEW("新上市"),
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
