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
@TableName("BMS_MEDIPROC_MV")
public class BmsMediprocMv extends Model<BmsMediprocMv> {

    private static final long serialVersionUID = 1L;

    @TableId("PROCESSDTLID")
    private Long processdtlid;

    @TableField("PROCESSDOCID")
    private Long processdocid;

    @TableField("MOVDOCID")
    private Long movdocid;

    @TableField("MVDIR")
    private Long mvdir;


    @Override
    protected Serializable pkVal() {
        return this.processdtlid;
    }

}
