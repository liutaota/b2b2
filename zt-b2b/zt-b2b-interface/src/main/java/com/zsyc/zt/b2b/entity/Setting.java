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
 * 系统设置
 * </p>
 *
 * @author MP
 * @since 2020-08-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("SETTING")
@ApiModel(value = "Setting对象", description = "系统设置")
@KeySequence(value = "SEQ_SETTING")
public class Setting extends BaseBean {

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
    @TableField("NAME")
    private String name;

    /**
     * 值
     */
    @ApiModelProperty(value = "值")
    @TableField("VALUE")
    private String value;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    @TableField("TYPE")
    private String type;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @TableField("SORT")
    private Integer sort;

    /**
     * KEY 存变量名用来通过key来找到value
     */
    @ApiModelProperty(value = "KEY")
    @TableField("KEY")
    private String key;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    @TableField("USER_ID")
    private Long userId;

    @ApiModelProperty(value = "修改时间")
    @TableField("UPDATE_TIME")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    @TableField("UPDATE_USER_ID")
    private Long updateUserId;

    /**
     * @see #type
     */
    public enum EType implements IEnum {
        STORE("商城设置"),
        SYSTEM("系统参数"),
        ;
        private String desc;

        EType(String desc) {
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
