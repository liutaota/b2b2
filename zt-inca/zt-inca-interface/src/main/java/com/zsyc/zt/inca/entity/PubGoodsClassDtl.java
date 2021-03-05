package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2020-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("PUB_GOODS_CLASS_DTL")
@ApiModel(value="PubGoodsClassDtl对象", description="")
@KeySequence(value = "PUB_GOODS_CLASS_DTL_SEQ")
public class PubGoodsClassDtl extends BaseBean {

    private static final long serialVersionUID = 1L;

    @TableField("CLASSTYPEID")
    private Long classtypeid;

    @TableField("CLASSID")
    private Long classid;

    @TableId("GOODSID")
    private Long goodsid;


}
