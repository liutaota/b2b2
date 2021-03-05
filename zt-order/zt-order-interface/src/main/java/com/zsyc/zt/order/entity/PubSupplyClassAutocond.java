package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("PUB_SUPPLY_CLASS_AUTOCOND")
public class PubSupplyClassAutocond extends Model<PubSupplyClassAutocond> {

    private static final long serialVersionUID = 1L;

    @TableField("SEQID")
    private Long seqid;

    @TableField("CLASSID")
    private Long classid;

    @TableField("AUTOWHERES")
    private String autowheres;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
