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
@TableName("PUB_SUPPLY_CLASS_DTL")
public class PubSupplyClassDtl extends Model<PubSupplyClassDtl> {

    private static final long serialVersionUID = 1L;

    @TableField("CLASSTYPEID")
    private Long classtypeid;

    @TableField("CLASSID")
    private Long classid;

    @TableField("SUPPLYID")
    private Long supplyid;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
