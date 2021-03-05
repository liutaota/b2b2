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
 * 公告
 * </p>
 *
 * @author MP
 * @since 2020-09-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("NOTICE")
@ApiModel(value="Notice对象", description="公告")
@KeySequence(value = "SEQ_NOTICE")
public class Notice extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId("ID")
    private Long id;

    /**
     * 公告内容
     */
    @ApiModelProperty(value = "公告内容")
    @TableField("CONTENT")
    private String content;

    /**
     * 操作人ID
     */
    @ApiModelProperty(value = "操作人ID")
    @TableField("ADMIN_ID")
    private Long adminId;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @TableField("START_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @TableField("END_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    @TableField("ADD_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime addTime;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @TableField("SORT")
    private Integer sort;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 是否启用
     * OFF 关闭
     * ON 启用
     */
    @ApiModelProperty(value = "是否启用 OFF 关闭 ON 启用")
    @TableField("IS_USE")
    private String isUse;

    /**
     * 是否删除 0 否 1 是
     */
    @ApiModelProperty(value = "是否删除 0 否 1 是")
    @TableField("IS_DEL")
    private Integer isDel;

    /**
     * 公告类型
     */
    @ApiModelProperty(value = "公告类型")
    @TableField("NOTICE_TYPE")
    private String noticeType;

    /**
     * 颜色
     */
    @ApiModelProperty(value = "颜色")
    @TableField("NOTICE_COLOR")
    private String noticeColor;

    /**
     * 操作人
     */
    @ApiModelProperty(value = "操作人")
    @TableField("ADMIN_NAME")
    private String adminName;

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
}
