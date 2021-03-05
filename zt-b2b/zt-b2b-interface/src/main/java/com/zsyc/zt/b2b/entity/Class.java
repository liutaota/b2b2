package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品分类
 * </p>
 *
 * @author MP
 * @since 2020-09-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("CLASS")
@ApiModel(value="Class对象", description="商品分类")
@KeySequence(value = "SEQ_CLASS")
public class Class extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    @TableId("ID")
    private Long id;

    /**
     * erp分类id
     */
    @ApiModelProperty(value = "erp分类id")
    @TableField("ERP_CLASS_ID")
    private Long erpClassId;

    /**
     * 分类简称
     */
    @ApiModelProperty(value = "分类简称")
    @TableField("CLASS_NAME")
    private String className;

    /**
     * 分类图片
     */
    @ApiModelProperty(value = "分类图片")
    @TableField("CLASS_IMG")
    private String classImg;

    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;


}
