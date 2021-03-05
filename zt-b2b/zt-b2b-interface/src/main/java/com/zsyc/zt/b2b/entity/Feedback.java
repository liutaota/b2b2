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
 * 用户反馈
 * </p>
 *
 * @author MP
 * @since 2020-09-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("FEEDBACK")
@ApiModel(value = "Feedback对象", description = "用户反馈")
@KeySequence(value = "SEQ_FEEDBACK")
public class Feedback extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 反馈内容
     */
    @ApiModelProperty(value = "反馈内容")
    @TableField("CONTENT")
    private String content;

    /**
     * 类型
     */
    @ApiModelProperty(value = "类型")
    @TableField("TYPE")
    private String type;

    /**
     * 反馈时间
     */
    @ApiModelProperty(value = "反馈时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    @TableField("MEMBER_ID")
    private Long memberId;

    /**
     * 客户 名称
     */
    @ApiModelProperty(value = "客户 名称")
    @TableField("MEMBER_NAME")
    private String memberName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @TableField("MEMBER_PHONE")
    private String memberPhone;

    /**
     * erp商品id
     */
    @ApiModelProperty(value = "erp商品id")
    @TableField("ERP_GOODS_ID")
    private Long erpGoodsId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    @TableField("ERP_GOODS_NAME")
    private String erpGoodsName;

    /**
     * 是否显示在商品详情页
     */
    @ApiModelProperty(value = "是否显示在商品详情页")
    @TableField("GOODS_SHOW")
    private String goodsShow;

    /**
     * 回复内容
     */
    @ApiModelProperty(value = "回复内容")
    @TableField("REPLY_CONTENT")
    private String replyContent;

    /**
     * 回复时间
     */
    @ApiModelProperty(value = "回复时间")
    @TableField("REPLY_TIME")
    private LocalDateTime replyTime;

    /**
     * 回复人
     */
    @ApiModelProperty(value = "回复人")
    @TableField("REPLY_USER_ID")
    private Long replyUserId;

    /**
     * 回复名称
     */
    @ApiModelProperty(value = "回复名称")
    @TableField("REPLY_USER_NAME")
    private String replyUserName;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    @TableField("IS_DEL")
    private Integer isDel;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @TableField("EMAIL")
    private String email;

    @ApiModelProperty(value = "状态")
    @TableField("STATUS")
    private String status;


    /**
     * @see #type
     */
    public enum EType implements IEnum {
        GOODS("商品咨询"),
        PLATFROM("平台建议"),
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
     * @see #goodsShow
     */
    public enum EGoodsShow implements IEnum {
        OFF("关闭"),
        ON("启动"),
        ;
        private String desc;

        EGoodsShow(String desc) {
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
     * @see #status
     */
    public enum EStatus implements IEnum {
        REPLIED("已回复"),
        UNTREATED("未处理"),
        ;
        private String desc;

        EStatus(String desc) {
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
