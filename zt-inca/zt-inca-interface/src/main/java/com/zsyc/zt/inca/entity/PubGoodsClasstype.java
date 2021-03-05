package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
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
@TableName("PUB_GOODS_CLASSTYPE")
@ApiModel(value="PubGoodsClasstype对象", description="")
@KeySequence(value = "PUB_GOODS_CLASSTYPE_SEQ")
public class PubGoodsClasstype extends BaseBean {

    private static final long serialVersionUID = 1L;

    @TableId("CLASSTYPEID")
    private Long classtypeid;

    @TableField("CLASSTYPE")
    private String classtype;

    @TableField("INNERTYPE")
    private String innertype;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("USESTATUS")
    private Integer usestatus;


}
