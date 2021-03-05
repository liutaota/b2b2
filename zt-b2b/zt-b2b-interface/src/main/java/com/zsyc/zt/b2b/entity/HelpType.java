package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * 帮助类型
 * </p>
 *
 * @author MP
 * @since 2020-08-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("HELP_TYPE")
@ApiModel(value="HelpType对象", description="帮助类型")
@KeySequence(value = "SEQ_HELP_TYPE")
public class HelpType extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 类型名称
     */
    @ApiModelProperty(value = "类型名称")
    @TableField("TYPE_NAME")
    private String typeName;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @TableField("TYPE_SORT")
    private Integer typeSort;

    /**
     * 是否显示
     */
    @ApiModelProperty(value = "是否显示，OFF 不显示 ON 显示")
    @TableField("HELP_SHOW")
    private String helpShow;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除：0 否 1 是")
    @TableField("IS_DEL")
    private Integer isDel;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

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
