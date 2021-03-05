package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("PUB_CUSTOM_SET_DTL")
@ApiModel(value="PubCustomSetDtl对象", description="")
@KeySequence(value = "PUB_CUSTOM_SET_DTL_SEQ")
public class PubCustomSetDtl extends Model<PubCustomSetDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("SETDTLID")
    private Long setdtlid;

    @TableField("SETID")
    private Long setid;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("IMPCUSTOMID")
    private Long impcustomid;

    @TableField("IMPCUSTOMNAME")
    private String impcustomname;


}
