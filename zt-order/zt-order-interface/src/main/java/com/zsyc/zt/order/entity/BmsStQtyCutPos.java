package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("BMS_ST_QTY_CUT_POS")
public class BmsStQtyCutPos extends Model<BmsStQtyCutPos> {

    private static final long serialVersionUID = 1L;

    @TableId("CUTID")
    private Long cutid;

    @TableField("POSID")
    private Long posid;


    @Override
    protected Serializable pkVal() {
        return this.cutid;
    }

}
