package com.zsyc.zt.b2b.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.framework.base.BaseMeta;
import com.zsyc.zt.b2b.IEnum;
import com.zsyc.zt.b2b.vo.AdvertiseImagesVo;
import com.zsyc.zt.b2b.vo.GoodsImagesVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 * 广告位
 * </p>
 *
 * @author MP
 * @since 2020-08-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ADV_POSITION")
@ApiModel(value = "AdvPosition对象", description = "广告位")
@KeySequence(value = "SEQ_ADV_POSITION")
public class AdvPosition extends BaseMeta {

    private static final long serialVersionUID = 1L;

    /**
     * 广告位id
     */
    @ApiModelProperty(value = "广告位id")
    @TableId("ID")
    private Long id;

    /**
     * 广告位展示样式
     * TOP_BANNER           顶部广告位
     * CAROUSEL_BANNER      轮播广告位
     * CENTER_ONLY          中部单图
     * CENTER_MORE_LITTLE   中部多图小
     * CENTER_MORE_BIG      中部多图大
     * BROADSIDE_BANNER     侧边广告位
     * POPUP_BANNER         弹窗广告位
     * DISCOUNTS_BANNER     特价广告
     */
    @ApiModelProperty(value = "广告位展示样式：TOP_BANNER 顶部广告位，CAROUSEL_BANNER 轮播广告位，CENTER_ONLY 中部单图，CENTER_MORE_LITTLE 中部多图小，CENTER_MORE_BIG 中部多图大，BROADSIDE_BANNER 侧边广告位，POPUP_BANNER 弹窗广告位 DISCOUNTS_BANNER 特价广告")
    @TableField("AP_DISPLAY")
    private String apDisplay;

    @TableField("CLICK_NUM")
    private Long clickNum;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField("UPDATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 创建者ID
     */
    @ApiModelProperty(value = "创建者ID")
    @TableField("CREATE_USER_ID")
    private Long createUserId;

    /**
     * 修改者ID
     */
    @ApiModelProperty(value = "修改者ID")
    @TableField("UPDATE_USER_ID")
    private Long updateUserId;

    /**
     * 广告开始时间
     */
    @ApiModelProperty(value = "广告开始时间")
    @TableField("ADV_START_DATE")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime advStartDate;

    /**
     * 广告结束时间
     */
    @ApiModelProperty(value = "广告结束时间")
    @TableField("ADV_END_DATE")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime advEndDate;

    /**
     * 是否删除：0为否，1为是
     */
    @ApiModelProperty(value = "是否删除：0为否，1为是")
    @TableField("IS_DEL")
    private Integer isDel;

    /**
     * 广告位是否启用
     * IS_USE   不启用
     * USE      启用
     */
    @ApiModelProperty(value = "广告位是否启用：IS_USE 不启用，USE 启用")
    @TableField("IS_USE")
    private String isUse;

    /**
     * 广告名称
     */
    @ApiModelProperty(value = "广告名称")
    @TableField("ADV_NAME")
    private String advName;

    /**
     * 图片数量
     */
    @ApiModelProperty(value = "图片数量")
    @TableField("IMG_NUM")
    private Integer imgNum;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    /**
     * @see #apDisplay
     */
    public enum EApDisplay implements IEnum {
        TOP_BANNER("顶部广告位"),
        CAROUSEL_BANNER("轮播广告位"),
        CENTER_ONLY("中部单图"),
        CENTER_MORE_LITTLE("中部多图小"),
        CENTER_MORE_BIG("中部多图大"),
        BROADSIDE_BANNER("侧边广告位"),
        POPUP_BANNER("弹窗广告位"),
        DISCOUNTS_BANNER ("特价广告"),
        ;
        private String desc;

        EApDisplay(String desc) {
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
        OFF("不启用"),
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
