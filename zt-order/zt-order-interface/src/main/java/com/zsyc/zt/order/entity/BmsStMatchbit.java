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
@TableName("BMS_ST_MATCHBIT")
public class BmsStMatchbit extends Model<BmsStMatchbit> {

    private static final long serialVersionUID = 1L;

    @TableId("MATCHBITID")
    private Long matchbitid;

    @TableField("MATCHAREAID")
    private Long matchareaid;

    @TableField("MATCHBITNO")
    private String matchbitno;

    @TableField("MATCHFLAG")
    private Integer matchflag;

    @TableField("PREPAREFLAG")
    private Integer prepareflag;


    @Override
    protected Serializable pkVal() {
        return this.matchbitid;
    }

}
