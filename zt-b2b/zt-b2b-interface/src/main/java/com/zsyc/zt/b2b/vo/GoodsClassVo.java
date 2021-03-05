package com.zsyc.zt.b2b.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zsyc.zt.inca.entity.PubGoodsClass;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
//@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "GoodsClassVo对象", description = "商品分类-2-3")
public class GoodsClassVo implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;


    @ApiModelProperty(value = "分类名称2")
    private String classname2;

    @ApiModelProperty(value = "分类id2")
    private Long  classnRow2;

    @ApiModelProperty(value = "分类名称3")
    private String classname3;

    @ApiModelProperty(value = "分类id3")
    private Long  classnRow3;

    //货品分类3
    List<GoodsClassVo> goodsClassList3;

}
