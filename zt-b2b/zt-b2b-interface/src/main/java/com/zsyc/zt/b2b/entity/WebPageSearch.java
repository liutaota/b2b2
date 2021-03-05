package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 专区搜索商品分类
 * </p>
 *
 * @author MP
 * @since 2020-08-30
 */
@Data
@Accessors(chain = true)
@TableName("WEB_PAGE_SEARCH")
@ApiModel(value = "WebPageSearch对象", description = "专区搜索商品分类")
@KeySequence(value = "SEQ_WEB_PAGE_SEARCH")
public class WebPageSearch implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 楼层id
     */
    @ApiModelProperty(value = "楼层id")
    @TableField("FLOOR_ID")
    private Long floorId;

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    @TableField("ERP_GOODS_ID")
    private Long erpGoodsId;

    /**
     * 商品名
     */
    @ApiModelProperty(value = "商品名")
    @TableField("GOODS_NAME")
    private String goodsName;

    /**
     * 专区id
     */
    @ApiModelProperty(value = "专区id")
    @TableField("WEB_PAGE_ID")
    private Long webPageId;

    /**
     * 分类1
     */
    @ApiModelProperty(value = "分类1")
    @TableField("CLASS_NAME_1")
    private String className1;

    /**
     * 分类2
     */
    @ApiModelProperty(value = "分类2")
    @TableField("CLASS_NAME_2")
    private String className2;

    /**
     * erp分类1
     */
    @ApiModelProperty(value = "erp分类1")
    @TableField("ERP_CLASS_ID_1")
    private Long erpClassId1;

    /**
     * erp分类2
     */
    @ApiModelProperty(value = "erp分类2")
    @TableField("ERP_CLASS_ID_2")
    private Long erpClassId2;

    /**
     * erp分类3
     */
    @ApiModelProperty(value = "erp分类3")
    @TableField("ERP_CLASS_ID_3")
    private Long erpClassId3;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签")
    @TableField("TITLE")
    private String title;
}
