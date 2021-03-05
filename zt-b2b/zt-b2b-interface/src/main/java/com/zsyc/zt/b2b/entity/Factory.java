package com.zsyc.zt.b2b.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.sql.Blob;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.zt.b2b.IEnum;
import com.zsyc.zt.b2b.vo.FactoryImagesVo;
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
 * 厂家
 * </p>
 *
 * @author MP
 * @since 2020-07-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("factory")
@ApiModel(value = "factory对象", description = "厂家")
@KeySequence(value = "SEQ_FACTORY")
public class Factory extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * ERP厂家表pub_factory.supplyid
     */
    @ApiModelProperty(value = "ERP厂家表pub_factory.supplyid")
    @TableField("ERP_SUPPLY_ID")
    private Long erpSupplyId;

    /**
     * 厂家简称
     */
    @ApiModelProperty(value = "厂家简称")
    @TableField("FACTORY_SHORT")
    private String factoryShort;

    /**
     * 厂家首字母
     */
    @ApiModelProperty(value = "厂家首字母")
    @TableField("FACTORY_INITIAL")
    private String factoryInitial;

    /**
     * 类别名称
     */
    @ApiModelProperty(value = "类别名称")
    @TableField("FACTORY_CLASS")
    private String factoryClass;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
    @TableField("FACTORY_PIC")
    private String factoryPic;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @TableField("FACTORY_SORT")
    private Integer factorySort;

    /**
     * 推荐，0为否，1为是，默认0
     */
    @ApiModelProperty(value = "推荐，0为否，1为是，默认0")
    @TableField("FACTORY_RECOMMEND")
    private Integer factoryRecommend;

    /**
     * 厂家详情介绍
     */
    @ApiModelProperty(value = "厂家详情介绍")
    @TableField("FACTORY_INTRODUCTION")
    private String factoryIntroduction;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
