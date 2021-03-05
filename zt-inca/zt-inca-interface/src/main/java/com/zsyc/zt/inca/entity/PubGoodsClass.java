package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2020-07-24
 */
@Data

@Accessors(chain = true)
@TableName("PUB_GOODS_CLASS")
@ApiModel(value="PubGoodsClass对象", description="")
@KeySequence(value = "PUB_GOODS_CLASS_SEQ")
public class PubGoodsClass extends BaseBean{

    private static final long serialVersionUID = 1L;

    @TableId("CLASSID")
    private Long classid;

    @TableField("CLASSTYPEID")
    private Long classtypeid;

    @TableField("CLASSNO")
    private String classno;

    @TableField("CLASSNAME")
    private String classname;

    @TableField("LEAFFLAG")
    private Integer leafflag;

    @TableField("PARENTCLASSID")
    private Long parentclassid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private Date credate;

    @TableField("CLASSN_ROW")
    private Long classnRow;


}
