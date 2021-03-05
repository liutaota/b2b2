package com.zsyc.zt.b2b.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.zt.b2b.vo.GoodsImagesVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 * 新品登记
 * </p>
 *
 * @author MP
 * @since 2020-09-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("NEW_PRODUCT")
@ApiModel(value="NewProduct对象", description="新品登记")
@KeySequence(value = "SEQ_NEW_PRODUCT")
public class NewProduct extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId("ID")
    private Long id;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    @TableField("GOODS_NAME")
    private String goodsName;

    /**
     * 商品规格
     */
    @ApiModelProperty(value = "商品规格")
    @TableField("SPEC")
    private String spec;

    /**
     * 需求数量
     */
    @ApiModelProperty(value = "需求数量")
    @TableField("NUM")
    private Long num;

    /**
     * 生产厂家，默认"不限"
     */
    @ApiModelProperty(value = "生产厂家，默认->不限")
    @TableField("COMPANY_NAME")
    private String companyName;

    /**
     * 批准文号
     */
    @ApiModelProperty(value = "批准文号")
    @TableField("APPROVEDOCNO")
    private String approvedocno;

    /**
     * 建议价格
     */
    @ApiModelProperty(value = "建议价格")
    @TableField("PRICE")
    private Long price;

    /**
     * 货源渠道
     */
    @ApiModelProperty(value = "货源渠道")
    @TableField("CHANNEL")
    private String channel;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    /**
     * 图片，分割符隔开
     */
    @ApiModelProperty(value = "图片，分割符隔开")
    @TableField("PHOTOS")
    private String photos;

    @ApiModelProperty(value = "手机号")
    @TableField("TELEPHONE")
    private String telephone;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    @TableField("MEMBER_ID")
    private Long memberId;

    /**
     * 发布时间
     */
    @ApiModelProperty(value = "发布时间")
    @TableField("CREATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;


}
