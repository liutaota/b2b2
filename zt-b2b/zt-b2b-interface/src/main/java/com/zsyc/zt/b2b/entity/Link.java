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
 * 友情链接
 * </p>
 *
 * @author MP
 * @since 2020-08-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("LINK")
@ApiModel(value="Link对象", description="友情链接")
@KeySequence(value = "SEQ_LINK")
public class Link extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    @TableField("LINK_TITLE")
    private String linkTitle;

    /**
     * 链接
     */
    @ApiModelProperty(value = "链接")
    @TableField("LINK_URL")
    private String linkUrl;

    /**
     * 图片
     */
    @ApiModelProperty(value = "图片")
    @TableField("LINK_PIC")
    private String linkPic;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    @TableField("LINK_SORT")
    private Integer linkSort;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;


}
