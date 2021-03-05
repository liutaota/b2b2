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
@TableName("BMS_BOXUP_DTL")
public class BmsBoxupDtl extends Model<BmsBoxupDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("BOXDTLID")
    private Long boxdtlid;

    @TableField("IODTLID")
    private Long iodtlid;

    @TableField("BOXDOCID")
    private Long boxdocid;

    @TableField("TRID")
    private Long trid;

    @TableField("DESKID")
    private Long deskid;


    @Override
    protected Serializable pkVal() {
        return this.boxdtlid;
    }

}
