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
@TableName("PUB_CUSTOM_TO_SALER")
@ApiModel(value="PubCustomToSaler对象", description="")
@KeySequence(value = "PUB_CUSTOM_TO_SALER_SEQ")
public class PubCustomToSaler extends Model<PubCustomToSaler> {

    private static final long serialVersionUID = 1L;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("SALERID")
    private Long salerid;

    @TableField("SALERDEPTID")
    private Long salerdeptid;

    @TableId("SEQID")
    private Long seqid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("ENTRYCUSTOMERID")
    private Long entrycustomerid;

    @TableField("ZX_FLAG")
    private Integer zxFlag;


}
