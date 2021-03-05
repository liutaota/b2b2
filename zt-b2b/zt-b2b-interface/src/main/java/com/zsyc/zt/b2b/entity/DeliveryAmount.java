package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.zt.b2b.IEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 起送规则
 * </p>
 *
 * @author MP
 * @since 2020-08-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("DELIVERY_AMOUNT")
@ApiModel(value = "DeliveryAmount对象", description = "起送规则")
@KeySequence(value = "SEQ_DELIVERY_AMOUNT")
public class DeliveryAmount extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * erp地区ID
     */
    @ApiModelProperty(value = "erp地区ID")
    @TableField("AREA_ID")
    private String areaId;

    /**
     * erp地区名
     */
    @ApiModelProperty(value = "erp地区名")
    @TableField("AREA_NAME")
    private String areaName;

    /**
     * 起送金额
     */
    @ApiModelProperty(value = "起送金额")
    @TableField("DA_AMOUNT")
    private Long daAmount;

    /**
     * 是否可用：ON 启用 OFF 停用
     */
    @ApiModelProperty(value = "是否可用：ON 启用 OFF 停用")
    @TableField("IS_USE")
    private String isUse;

    /**
     * 起送规则名称
     */
    @ApiModelProperty(value = "起送规则名称")
    @TableField("DA_NAME")
    private String daName;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期")
    @TableField("CREATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 修改日期
     */
    @ApiModelProperty(value = "修改日期")
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
     * 地区类型
     * DEFAULT  默认
     * PROVINCE 省
     * CITY 市
     * DISTRICT 区
     */
    @ApiModelProperty(value = "地区类型：DEFAULT 默认 PROVINCE 省 CITY 市 DISTRICT 区")
    @TableField("TYPE")
    private String type;

    /**
     * @see #type
     */
    public enum EType implements IEnum {
        DEFAULT("默认"),
        PROVINCE("省"),
        CITY("市"),
        DISTRICT("区"),
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

    /**
     * @see #isUse
     */
    public enum EIsUse implements IEnum {
        OFF("停用"),
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
}
