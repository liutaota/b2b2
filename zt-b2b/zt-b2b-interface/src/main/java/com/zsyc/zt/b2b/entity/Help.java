package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.sql.Clob;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.zt.b2b.IEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 帮助指南
 * </p>
 *
 * @author MP
 * @since 2020-08-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("HELP")
@ApiModel(value="Help对象", description="帮助指南")
@KeySequence(value = "SEQ_HELP")
public class Help extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @TableField("HELP_SORT")
    private Integer helpSort;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    @TableField("HELP_TITLE")
    private String helpTitle;

    /**
     * 帮助内容
     */
    @ApiModelProperty(value = "帮助内容")
    @TableField("HELP_INFO")
    private String helpInfo;

    /**
     * 跳转链接
     */
    @ApiModelProperty(value = "跳转链接")
    @TableField("HELP_URL")
    private String helpUrl;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 帮助类型
     */
    @ApiModelProperty(value = "帮助类型")
    @TableField("TYPE_ID")
    private Long typeId;

    /**
     * 创建人ID
     */
    @ApiModelProperty(value = "创建人ID")
    @TableField("CREATE_USER_ID")
    private Long createUserId;


    /**
     * 修改人ID
     */
    @ApiModelProperty(value = "修改人ID")
    @TableField("UPDATE_USER_ID")
    private Long updateUserId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 是否显示
     */
    @ApiModelProperty(value = "是否显示：OFF 不显示 ON 显示")
    @TableField("HELP_SHOW")
    private String helpShow;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除：0 否 1 是")
    @TableField("IS_DEL")
    private Integer isDel;

    /**
     * @see #helpShow
     */
    public enum EHelpShow implements IEnum {
        OFF("不显示"),
        ON("显示"),
        ;
        private String desc;

        EHelpShow(String desc) {
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
