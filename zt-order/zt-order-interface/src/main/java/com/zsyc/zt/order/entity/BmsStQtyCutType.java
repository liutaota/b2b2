package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
@TableName("BMS_ST_QTY_CUT_TYPE")
public class BmsStQtyCutType extends Model<BmsStQtyCutType> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Long id;

    @TableField("CUTID")
    private Long cutid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("STATUS")
    private Integer status;

    @TableField("CREATEDATE")
    private LocalDateTime createdate;

    @TableField("ISCRE")
    private Integer iscre;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
