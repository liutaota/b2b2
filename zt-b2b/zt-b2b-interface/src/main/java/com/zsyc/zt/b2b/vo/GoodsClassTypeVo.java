package com.zsyc.zt.b2b.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.zt.inca.entity.PubGoodsClass;
import com.zsyc.zt.inca.entity.PubGoodsClassDtl;
import com.zsyc.zt.inca.entity.PubGoodsClasstype;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by tang on 2020-07-27.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "GoodsClassTypeVo对象", description = "商品分类")
public class GoodsClassTypeVo extends BaseBean {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "分类名称全称")
    private String classname;

    @ApiModelProperty(value = "分类名称")
    private String oldclassname;

    @ApiModelProperty(value = "分类id")
    private Long classid;

    @ApiModelProperty(value = "父类id")
    private Long parentclassid;

    @ApiModelProperty(value = "分类号")
    private Long classnRow;

    @ApiModelProperty(value = "分类图片")
    private String classImg;

    @ApiModelProperty("无图片")
    private Boolean isImg;

    @ApiModelProperty("分类级别")
    private Integer classNo;

    List<GoodsClassTypeVo> subGoodsClassList;

}
