package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.sql.Clob;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.framework.base.BaseMeta;
import com.zsyc.zt.b2b.IEnum;
import com.zsyc.zt.b2b.vo.FloorInfoVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 楼层
 * </p>
 *
 * @author MP
 * @since 2020-08-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("FLOOR")
@ApiModel(value = "Floor对象", description = "楼层")
@KeySequence(value = "SEQ_FLOOR")
public class Floor extends BaseMeta {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId("ID")
    private Long id;

    /**
     * 类型:
     * <p>
     * ROLL_LINE    单行样式
     * SINGLE_LINE  多行样式
     * MULTI_LINE   双行多页签样式
     * BG_IMAGE     多行背景图样式
     */
    @ApiModelProperty(value = "类型: ROLL_LINE 单行样式 SINGLE_LINE 多行样式 MULTI_LINE 双行多页签样式 BG_IMAGE 多行背景图样式 ")
    @TableField("TYPE")
    private String type;

    /**
     * 楼层名称
     */
    @ApiModelProperty(value = "楼层名称")
    @TableField("TITLE")
    private String title;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_TIME")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 创建用户ID
     */
    @ApiModelProperty(value = "创建用户ID")
    @TableField("CREATE_USER_ID")
    private Long createUserId;

    /**
     * 更新用户ID
     */
    @ApiModelProperty(value = "更新用户ID")
    @TableField("UPDATE_USER_ID")
    private Long updateUserId;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除：0为否，1为是")
    @TableField("IS_DEL")
    private Integer isDel;

    /**
     * 商品数量
     */
    @ApiModelProperty(value = "商品数量")
    @TableField("GOODS_NUM")
    private Integer goodsNum;

    /**
     * 楼层KEY
     */
    @ApiModelProperty(value = "楼层KEY")
    @TableField("FLOOR_KEY")
    private String floorKey;
    /**
     * 色调
     */
    @ApiModelProperty(value = "色调")
    @TableField("HUE")
    private String hue;

    /**
     * 楼层排序
     */
    @ApiModelProperty(value = "楼层排序")
    @TableField("FLOOR_SORT")
    private Integer floorSort;

    @ApiModelProperty(value = "是否启动")
    @TableField("IS_USE")
    private String isUse;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    /**
     * @see #type
     */
    public enum EType implements IEnum {
        ROLL_LINE("单行样式"),
        SINGLE_LINE("多行样式"),
        MULTI_LINE("双行多页签样式"),
        BG_IMAGE("多行背景图样式"),
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
        OFF("关闭"),
        ON("启动"),
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
