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
@TableName("BMS_ST_RG_REFUSE")
public class BmsStRgRefuse extends Model<BmsStRgRefuse> {

    private static final long serialVersionUID = 1L;

    @TableId("REFID")
    private Long refid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("TRANSMANID")
    private Long transmanid;

    @TableField("TRANSFLAG")
    private Integer transflag;

    @TableField("TRANSDATE")
    private LocalDateTime transdate;

    @TableField("RGGOODSDTLID")
    private Long rggoodsdtlid;

    @TableField("TRANSTYPE")
    private Integer transtype;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("SOURCEID")
    private Long sourceid;


    @Override
    protected Serializable pkVal() {
        return this.refid;
    }

}
